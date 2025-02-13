# NOTE
# 
# 파워쉘 스크립트 파일(.ps1)의 저장 인코딩이 BOM이 없는 경우에는 시스템 인코딩을 따라서 실행된다.
# 이때 스크립트 코드내에 한글이 포함되어 있을 경우 콘솔등에 한글이 깨져서 출력될 수 있다.
#
# 그런 한글 깨짐 현상을 해결하는 한가지 간단한 방법은 스크립트 파일을 'UTF-8 BOM'으로 저장후 실행하는 것이다.
# '메모장'의 경우 '다른 이름으로 저장'시 인코딩을 'UTF-8 BOM'으로 설정할 수 있는 '콤보 박스'가 있다.
# 'VSCode'의 경우 '상태 표시줄'에 오픈된 텍스트 파일의 인코딩을 변경할 수 있는 '현재 인코딩 표시 <버튼>'이 있다.
#
# 다음의 스크립트는 8888 포트를 사용하는 프로세스를 찾아서 출력하는 명령어이다:
# > netstat -ano | findstr 8888

Write-Host "`n[포트 8888 검사 시작]" -ForegroundColor Cyan

# 8888 포트를 사용하는 프로세스 찾기
$portInfo = Get-NetTCPConnection -LocalPort 8888 -ErrorAction SilentlyContinue | Select-Object -First 1

    
if ($portInfo) {
    $process = Get-Process -Id $portInfo.OwningProcess
    Write-Host "`n[발견된 프로세스 정보]" -ForegroundColor Green
    Write-Host "--------------------------------"
    Write-Host "로컬 포트: 8888"
    Write-Host "프로세스 ID: $($process.Id)"
    Write-Host "프로세스 이름: $($process.ProcessName)"
    
    # Get-WmiObject 대신 Get-CimInstance 사용
    $commandLine = (Get-CimInstance Win32_Process -Filter "ProcessId = $($process.Id)").CommandLine
    Write-Host "실행 명령어: $commandLine"
    Write-Host "--------------------------------`n"
} else {
    Write-Host "`n[알림] 포트 8888을 사용하는 프로세스가 없습니다." -ForegroundColor Yellow
}
