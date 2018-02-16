call runcrud.bat
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo runcrud.bat has errors - breaking work
goto fail

:browser
@echo off
start /d "C:\Program Files (x86)\Google\Chrome\Application" chrome.exe http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Cannot open browser and show web
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished