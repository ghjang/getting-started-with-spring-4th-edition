# 1. 프로파일 파일 경로 확인
$profilePath = $PROFILE
Write-Output "Profile path: $profilePath"

# 2. 프로파일 파일 경로의 디렉터리를 생성
$profileDir = Split-Path -Path $profilePath
if (-Not (Test-Path -Path $profileDir)) {
    New-Item -Path $profileDir -ItemType Directory
    Write-Output "Created directory: $profileDir"
}

# 3. 프로파일 파일에 'gss' 함수가 이미 정의되어 있는지 확인
$profileContent = Get-Content -Path $profilePath -Raw
if ($profileContent -match 'function gss') {
    Write-Output "Function 'gss' is already defined in the profile file. No further action required."
    return
}

# 4. 프로파일 파일 맨 뒷줄에 'git status -s' 함수를 추가
$gitPath = (Get-Command git).Source  # Git의 설치 경로를 확인
Add-Content -Path $profilePath -Value "`nfunction gss { & `"$gitPath`" status -s }"
Write-Output "Added function to profile file."

# 5. 프로파일 파일에 대해 실행 제한을 해제
Unblock-File -Path $profilePath
Write-Output "Unblocked profile file."

# 6. 프로파일 파일에 서명이 되어 있는지 확인
$signature = Get-AuthenticodeSignature -FilePath $profilePath
if ($signature.Status -eq 'NotSigned' -or $signature.Status -eq 'HashMismatch') {
    Write-Output "Profile file is not signed or signature is invalid. Creating self-signed certificate..."

    # 7. 기존에 같은 이름의 인증서가 있는지 확인하고 제거
    $certName = "CN=MyCodeSigningCert"
    $existingCert = Get-ChildItem -Path Cert:\CurrentUser\My | Where-Object { $_.Subject -eq $certName }
    if ($existingCert) {
        Remove-Item -Path "Cert:\CurrentUser\My\$($existingCert.Thumbprint)"
        Write-Output "Removed existing certificate: $certName"
    }

    # 8. 자체 서명 인증서 생성
    $cert = New-SelfSignedCertificate -Type CodeSigningCert -Subject $certName -CertStoreLocation Cert:\CurrentUser\My
    Write-Output "Created self-signed certificate: $certName"

    # 9. 생성된 인증서를 신뢰할 수 있는 루트 인증서 저장소로 이동
    $tempCertPath = "C:\Temp\MyCodeSigningCert.cer"
    $cert | Export-Certificate -Type CERT -FilePath $tempCertPath
    Import-Certificate -FilePath $tempCertPath -CertStoreLocation Cert:\CurrentUser\Root
    Write-Output "Imported certificate to trusted root."

    # 10. 프로파일 파일에 서명
    Set-AuthenticodeSignature -FilePath $profilePath -Certificate $cert
    Write-Output "Signed profile file with self-signed certificate."

    # 11. 임시 인증서 파일 제거
    Remove-Item -Path $tempCertPath
    Write-Output "Removed temporary certificate file: $tempCertPath"
} else {
    Write-Output "Profile file is already signed."
}

# 12. 프로파일 파일 내용 터미널에 출력
Get-Content $profilePath

# 13. 프로파일 파일을 현재 환경에 적용
. $profilePath
Write-Output "Profile file loaded into current session."