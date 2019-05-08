@echo off
set BASEDIR=%~dp0

echo Importing configuration from %BASEDIR%env.properties

@echo on
for /F "tokens=*" %%i in ('type "%BASEDIR%env.properties"') do set %%i
