@echo off
set APP_NAME=java-cli
@for /f "skip=3 tokens=2" %%a in ('tasklist /fi "WINDOWTITLE eq %APP_NAME%"') do set pid=%%a
if defined pid (
goto toRun
) else (
goto tip
)

:toRun
echo Are you sure to stop %APP_NAME% running? (Input character 'Y' or 'y' to confirm execute stop command)：
set /p Y=
if /i "%Y%"=="Y" (
goto run
) else (
goto cancel
)

:run
taskkill /FI "WINDOWTITLE eq %APP_NAME%"
echo =============================================================================== 
echo   App-Name  : %APP_NAME%
echo   Run-State : shutdown successful.   
echo ===============================================================================
pause
exit

:tip
echo =============================================================================== 
echo   App-Name  : %APP_NAME%
echo   Run-State : is shutdown.   
echo ===============================================================================
pause
exit

:cancel
echo =============================================================================== 
echo   App-Name  : %APP_NAME%
echo   Run-State : cancel shutdown.   
echo ===============================================================================
pause
exit
