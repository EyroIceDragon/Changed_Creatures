chcp 65001 > $null
Write-Host -ForegroundColor Cyan
"
 ____        _ _     _    ____ _
| __ ) _   _(_) | __| |  / ___| |__   __ _ _ __   __ _  ___  __| |  / ___|_ __ ___  __ _| |_ _   _ _ __ ___  ___
|  _ \| | | | | |/ _  | | |   | |_ \ / _  | '_ \ / _  |/ _ \/ _  | | |   | '__/ _ \/ _  | __| | | | '__/ _ \/ __|
| |_) | |_| | | | (_| | | |___| | | | (_| | | | | (_| |  __/ (_| | | |___| | |  __/ (_| | |_| |_| | | |  __/\__ \
|____/ \__,_|_|_|\__,_|  \____|_| |_|\__,_|_| |_|\__, |\___|\__,_|  \____|_|  \___|\__,_|\__|\__,_|_|  \___||___/
                                                  |___/
"

$env:GRADLE_OPTS = "-Xmx2G"
$resDir = "./src/generated/resources"

Write-Host "[1/3] Cleaning generated resources..." -ForegroundColor Yellow

if (Test-Path $resDir) {
    Remove-Item -Recurse -Force $resDir
} else {
    Write-Host "Dir not found. Skipping cleanup: $resDir" -ForegroundColor Green
}

Write-Host "[2/3] Running data generation..." -ForegroundColor Yellow
& ./gradlew.bat runData
if ($LASTEXITCODE -ne 0) {
    Write-Host "Data generation failed with exit code $LASTEXITCODE" -ForegroundColor Red
    exit $LASTEXITCODE
}

Write-Host "[3/3] Building..." -ForegroundColor Yellow
& ./gradlew.bat build
if ($LASTEXITCODE -ne 0) {
    Write-Host "Build failed with exit code $LASTEXITCODE" -ForegroundColor Red
    exit $LASTEXITCODE
}

Write-Host "Build completed successfully!" -ForegroundColor Green
exit 0
