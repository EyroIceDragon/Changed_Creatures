@echo off
setlocal

echo  ____        _ _     _    ____ _
echo ^| __ ) _   _(_) ^| __^| ^|  / ___^| ^|__   __ _ _ __   __ _  ___  __^| ^|  / ___^|_ __ ___  __ _^| ^|_ _   _ _ __ ___  ___
echo ^|  _ \^| ^| ^| ^| ^| ^|/ _` ^| ^| ^|   ^| '_ \ / _` ^| '_ \ / _` ^|/ _ \/ _` ^| ^| ^|   ^| '__/ _ \/ _` ^| __^| ^| ^| ^| '__/ _ \/ __^|
echo ^| ^|_) ^| ^|_^| ^| ^| ^| (_^| ^| ^| ^|___^| ^| ^| ^| (_^| ^| ^| ^| ^| (_^| ^|  __/ (_^| ^| ^| ^|___^| ^| ^|  __/ (_^| ^| ^|_^| ^|_^| ^| ^| ^|  __/\__ \
echo ^|____/ \__,_^|_^|_^|\__,_^|  \____^|_^| ^|_^|\__,_^|_^| ^|_^|\__, ^|\___^|\__,_^|  \____^|_^|  \___^|\__,_^|\__^|\__,_^|_^|  \___^|^|___/
echo                                                   ^|___/

set "GRADLE_OPTS=-Xmx2G"

set "RES_DIR=%~dp0src\generated\resources"

echo [1/3] Cleaning generated resources...
if exist "%RES_DIR%" (
    del /q "%RES_DIR%\*.*" 2>nul
    for /d %%d in ("%RES_DIR%\*") do rmdir /s /q "%%d"
) else (
    echo Directory not found, skipping cleanup: %RES_DIR%
)

echo [2/3] Running data generation...
call gradlew.bat runData
if errorlevel 1 (
    echo Data generation failed, aborting.
    pause
    exit /b 1
)

echo [3/3] Building...
call gradlew.bat build
if errorlevel 1 (
    echo Build failed.
    pause
    exit /b 1
)

echo Done.
pause