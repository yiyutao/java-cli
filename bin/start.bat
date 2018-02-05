@echo off
rem set DIRNAME=.\
rem if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
rem if "%OS%" == "Windows_NT" set PROGNAME=%~nx0%
rem set JAVA_HOME=D:\Java\jdk1.8.0_121
set APP_NAME=java-cli
setlocal enabledelayedexpansion
set JAVA=%JAVA_HOME%\bin\java
set OPTS=-Xms512M -Xmx1024M -XX:+AggressiveOpts -XX:+UseParallelGC -XX:NewSize=64M
set LIBPATH=lib
set CP=java-cli-0.0.1-SNAPSHOT.jar;
rem set MAIN=com.arcsoft.nosql.data.task.mixture.ClientFileOfCloudQuery
set MAIN=net.yiyutao.Bootstrap
rem set MAIN= com.arcsoft.nosql.data.test.MigrateMain

for /f %%i in ('dir/b %LIBPATH%\*.jar^|sort') do (set CP=!CP!%LIBPATH%\%%i;)

rem echo =============================================================================== 
rem echo.
rem echo   Engine Startup Environment    
rem echo.
rem echo   JAVA: %JAVA%    
rem echo.
rem echo   JAVA_OPTS: %OPTS%    
rem echo.
rem echo   CLASSPATH: %CP%
rem echo.
rem echo ===============================================================================
rem echo.

start "%APP_NAME%" %JAVA% %OPTS% -classpath %CP% %MAIN%
rem %JAVA% %OPTS% -classpath %CP% %MAIN% arg1 arg2
rem pause
rem exit