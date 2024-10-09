@echo off
setlocal

cd /D "%~dp0"

for /f "tokens=1,2 delims==" %%a in ('type ".env"') do (
    set "%%a=%%b"
)

set OUTPUT=%OUTPUT:/=\%
if "%OUTPUT%"=="" set OUTPUT_DIR=bin


echo MAIN_FILE=%MAIN_FILE:/=\%
if "%MAIN_FILE%"=="" set MAIN_FILE=src\ui\Main.java

echo SRC=%SRC:/=\%
if "%SRC%"=="" set SRC=src

if not exist "%OUTPUT_DIR%"\ (
    mkdir "%OUTPUT_DIR%"
)

javac -d "%OUTPUT_DIR%" "%SRC%/*/*.java"

java -cp "%OUTPUT_DIR%" %MAIN_FILE%

endlocal
pause
