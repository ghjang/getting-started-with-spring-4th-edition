$chapterDirs = Get-ChildItem -Directory | Where-Object { $_.Name -like "chapter*" }

foreach ($chapter in $chapterDirs) {
    # 각 chapter 폴더 내의 하위 폴더들을 가져옵니다.
    $subDirs = Get-ChildItem -Directory -Path $chapter.FullName
    foreach ($sub in $subDirs) {
        $fullPath = $sub.FullName
        $outputFile = Join-Path $fullPath "folder_content_tree.txt"
        
        # 기존 파일이 있으면 삭제합니다.
        if (Test-Path $outputFile) {
            Remove-Item $outputFile -Force
            Write-Host "Removed existing $outputFile"
        }
        
        # target 폴더가 있으면 삭제합니다.
        $targetFolder = Join-Path $fullPath "target"
        if (Test-Path $targetFolder) {
            Remove-Item $targetFolder -Force -Recurse
            Write-Host "Removed target folder in $fullPath"
        }
        
        # tree 명령으로 출력된 결과를 가져옵니다.
        $treeOutput = & tree /F $fullPath
        $lines = $treeOutput -split "`n"
        
        if ($lines.Count -gt 2) {
            # 처음 두 줄(불필요한 내용)을 제거합니다.
            $lines = $lines[2..($lines.Count - 1)]
            # 첫 번째 줄에서 "GETTING" 이후의 부분만 남깁니다.
            if ($lines[0] -match "GETTING") {
                $idx = $lines[0].IndexOf("GETTING")
                if ($idx -ge 0) {
                    $lines[0] = $lines[0].Substring($idx)
                }
            }
        }
        
        # 수정된 내용을 folder_content_tree.txt 파일에 저장합니다.
        $lines | Out-File -FilePath $outputFile -Encoding UTF8
        Write-Host "Created $outputFile"
    }
}