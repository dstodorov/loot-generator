@echo off

REM Check if an argument is provided
if "%1"=="" (
  echo Usage: %0 "<directory>"
  exit /b 1
)

REM Check if the directory exists (handling quotes for paths with spaces)
if not exist "%~1" (
  echo Error: Directory "%~1" not found.
  exit /b 1
)

REM Recursively traverse the directory and print files and folders (handling quotes)
for /r "%~1" %%a in (*) do (
  echo "%%a"
)