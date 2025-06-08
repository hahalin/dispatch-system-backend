#!/bin/bash

echo "=== 測試 Personnel API ==="

# 測試GET /api/personnel/test (不需要認證)
echo "1. 測試 API 連接..."
curl -X GET http://localhost:8080/api/personnel/test

echo -e "\n\n2. 測試取得所有人員 (需要認證)..."
# 這會返回401因為沒有token
curl -X GET http://localhost:8080/api/personnel

echo -e "\n\n3. 測試註冊..."
# 首先需要有用戶才能登入
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "testpass",
    "name": "測試用戶"
  }'

echo -e "\n\n4. 測試登入..."
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "testpass"
  }'

echo -e "\n\n=== 測試完成 ==="