@echo off
echo === 測試 Personnel API ===

echo 1. 測試 API 連接...
curl -X GET http://localhost:8080/api/personnel/test

echo.
echo.
echo 2. 測試取得所有人員 (需要認證)...
curl -X GET http://localhost:8080/api/personnel

echo.
echo.
echo 3. 測試註冊...
curl -X POST http://localhost:8080/api/auth/signup -H "Content-Type: application/json" -d "{\"username\": \"testuser\", \"password\": \"testpass\", \"name\": \"測試用戶\"}"

echo.
echo.
echo 4. 測試登入...
curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"username\": \"testuser\", \"password\": \"testpass\"}"

echo.
echo.
echo === 測試完成 ===
pause