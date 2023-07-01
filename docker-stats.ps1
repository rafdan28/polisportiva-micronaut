while($true) {
    $output = docker stats --no-stream
    $output | Out-File -Append -Encoding utf8 stats.csv
    "`r`n" | Out-File -Append -Encoding utf8 stats.csv
    Start-Sleep -Seconds 1
}
