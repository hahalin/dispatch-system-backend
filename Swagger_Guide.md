# Swagger API 文檔使用指南

## 概述

本專案已集成 Swagger (OpenAPI 3) 來自動生成和展示 REST API 文檔。Swagger 提供了一個互動式的 Web 界面，讓您可以：
- 瀏覽所有可用的 API 端點
- 查看 API 的詳細說明和參數
- 直接在瀏覽器中測試 API
- 查看請求和回應的資料格式

## 如何訪問 Swagger UI

1. **啟動應用程式**
   ```bash
   mvn spring-boot:run
   ```

2. **訪問 Swagger UI**
   - 開啟瀏覽器，訪問：`http://localhost:8080/swagger-ui.html`
   - 您將看到完整的 API 文檔界面

3. **訪問 OpenAPI JSON**
   - API 規格的 JSON 格式：`http://localhost:8080/api-docs`

## 功能特色

### 🔐 JWT 認證支援
- Swagger UI 已配置 JWT Bearer Token 認證
- 在頁面頂部點擊 "Authorize" 按鈕
- 輸入您的 JWT token（不需要 'Bearer ' 前綴）
- 認證後可以測試需要登入的 API

### 📋 API 分組
API 按功能分為以下群組：
- **認證管理** - 登入、註冊相關 API
- **人員管理** - 人員資料的 CRUD 操作
- **客戶管理** - 客戶資料的 CRUD 操作
- **系統狀態** - 系統健康檢查

### 🧪 直接測試
- 點擊任何 API 端點展開詳細資訊
- 點擊 "Try it out" 按鈕
- 填入必要的參數
- 點擊 "Execute" 執行請求
- 查看實際的回應結果

## 使用步驟

### 1. 獲取 JWT Token
1. 在 Swagger UI 中找到 `POST /api/auth/login`
2. 點擊 "Try it out"
3. 輸入登入資訊：
   ```json
   {
     "username": "admin",
     "password": "admin123"
   }
   ```
4. 複製回應中的 `accessToken`

### 2. 設定認證
1. 點擊頁面頂部的 "Authorize" 按鈕
2. 在 JWT 欄位中貼上您的 token
3. 點擊 "Authorize"
4. 點擊 "Close"

### 3. 測試其他 API
現在您可以測試所有需要認證的 API，例如：
- 取得人員列表：`GET /api/personnel`
- 新增客戶：`POST /api/customers`
- 更新人員資料：`PUT /api/personnel/{id}`

## 配置說明

### Swagger 配置檔案
- 位置：`src/main/java/com/example/dispatch/config/SwaggerConfig.java`
- 包含 API 基本資訊、安全性配置等

### 應用程式配置
在 `application.properties` 中的相關設定：
```properties
# Swagger/OpenAPI 配置
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
springdoc.packages-to-scan=com.example.dispatch.controller
springdoc.paths-to-match=/api/**
```

## 開發指南

### 為新的 Controller 添加 Swagger 註解

1. **添加 @Tag 註解到類別級別**
```java
@Tag(name = "功能名稱", description = "功能描述")
public class YourController {
    // ...
}
```

2. **為每個端點添加 @Operation 註解**
```java
@Operation(summary = "簡短描述", description = "詳細描述")
@ApiResponse(responseCode = "200", description = "成功")
public ResponseEntity<?> yourMethod() {
    // ...
}
```

3. **為參數添加 @Parameter 註解**
```java
public ResponseEntity<?> yourMethod(
    @Parameter(description = "參數描述", required = true) 
    @PathVariable Long id) {
    // ...
}
```

## 常見問題

### Q: 為什麼看不到某些 API？
A: 確認 Controller 類別是否在 `com.example.dispatch.controller` 包下，並且有正確的 Spring 註解。

### Q: 認證後仍然顯示 401 錯誤？
A: 檢查 JWT token 是否正確，並且沒有過期。

### Q: 如何自訂 API 文檔內容？
A: 在對應的 Controller 方法上添加更詳細的 Swagger 註解。

## 相關連結

- [Springdoc OpenAPI 官方文檔](https://springdoc.org/)
- [OpenAPI 3 規格](https://swagger.io/specification/)
- [Swagger UI 使用指南](https://swagger.io/tools/swagger-ui/) 