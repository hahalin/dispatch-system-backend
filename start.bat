@echo off
echo Starting Dispatch System Backend...
echo.

cd /d "D:\repo\dispatch-system-backend"

echo Checking database connection...
echo.

echo Building and starting Spring Boot application...
echo.

call mvnw.cmd clean compile spring-boot:run

pause
