@echo off
setlocal
set SDK_DIR=%~dp0
"%SDK_DIR%\bin\memory_backup.exe" %*
endlocal
pause
