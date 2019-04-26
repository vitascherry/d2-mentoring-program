set PROJECT_DIR=%1
set DEFAULT_PROPERTIES=default.env.properties
set PROPERTIES=env.properties
set "TARGET_DIR=%CATALINA_HOME%\bin"

copy /y NUL "%TARGET_DIR%\%PROPERTIES%" >NUL
copy /y setenv.sh "%CATALINA_HOME%\bin"
copy /y setenv.bat "%CATALINA_HOME%\bin"

for /F "tokens=1* delims==" %%A in ("%PROJECT_DIR%\%PROPERTIES%") do (
    for /F "tokens=1* delims==" %%C in ("%PROJECT_DIR%\%DEFAULT_PROPERTIES%") do (
        if "%%A"=="%%C"
            echo "%%A=%%B" >> "%TARGET_DIR%\%PROPERTIES%"
        else
            echo "%%C=%%D" >> "%TARGET_DIR%\%PROPERTIES%"
    )
)

echo "Printing merged env.properties"
type "%TARGET_DIR%\%PROPERTIES%"
