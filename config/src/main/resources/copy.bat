@echo off

set PROJECT_DIR=%1
set DEFAULT_PROPERTIES=default.env.properties
set PROPERTIES=env.properties
set "TARGET_DIR=%CATALINA_HOME%\bin"

copy /y setenv.sh "%CATALINA_HOME%\bin"
copy /y setenv.bat "%CATALINA_HOME%\bin"

if exist "%PROJECT_DIR%\%PROPERTIES%" (
    echo Replacing default properties with user defined
    copy /y "%PROJECT_DIR%\%PROPERTIES%" "%TARGET_DIR%\%PROPERTIES%"
) else (
    copy /y NUL "%TARGET_DIR%\%PROPERTIES%" >NUL
)

for /f "tokens=1,2 delims==" %%A in (%PROJECT_DIR%\%DEFAULT_PROPERTIES%) do (
    find /c "%%A=" "%TARGET_DIR%\%PROPERTIES%" || ( echo %%A=%%B >> "%TARGET_DIR%\%PROPERTIES%" )
)

type "%TARGET_DIR%\%PROPERTIES%"
