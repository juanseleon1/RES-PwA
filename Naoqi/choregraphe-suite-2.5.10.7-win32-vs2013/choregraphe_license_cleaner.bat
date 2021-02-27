@echo off
setlocal
set SDK_DIR=%~dp0
"%SDK_DIR%\bin\choregraphe_license_cleaner.exe" %*
endlocal
pause
