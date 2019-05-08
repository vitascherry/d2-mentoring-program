@echo off

set BASEDIR=%~dp0

echo Importing configuration from %BASEDIR%env.properties

for /F "tokens=*" %%i in ('type "%BASEDIR%env.properties"') do set %%i
