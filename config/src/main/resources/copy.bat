set PROJECT_DIR=%1

xcopy %PROJECT_DIR%\env.properties %CATALINA_HOME%\bin
xcopy setenv.sh %CATALINA_HOME%\bin
xcopy setenv.bat %CATALINA_HOME%\bin
