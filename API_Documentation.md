# 派遣系統 API 文件

## 基礎資訊
- 基礎 URL: `http://localhost:8080`
- 所有 API 都需要 JWT 認證（除了登入和註冊）
- 認證方式：在 HTTP Header 中加入 `Authorization: Bearer {token}`

## 認證相關 API

### 登入
- **URL**: `/api/auth/login`
- **方法**: `POST`
- **請求體**:
```json
{
    "username": "string",
    "password": "string"
}
```
- **成功回應** (200):
```json
{
    "token": "string",
    "id": "integer",
    "username": "string",
    "name": "string",
    "role": "string"
}
```
- **錯誤回應** (401):
```json
{
    "message": "Invalid username or password",
    "success": false
}
```

### 註冊
- **URL**: `/api/auth/register`
- **方法**: `POST`
- **請求體**:
```json
{
    "username": "string",
    "password": "string",
    "name": "string"
}
```
- **成功回應** (200):
```json
{
    "message": "User registered successfully!",
    "success": true
}
```
- **錯誤回應** (400/500):
```json
{
    "message": "string",
    "success": false
}
```

## 人員管理 API

### 取得所有人員
- **URL**: `/api/personnel`
- **方法**: `GET`
- **成功回應** (200):
```json
[
    {
        "id": "integer",
        "name": "string",
        "englishName": "string",
        "gender": "string",
        "birthYear": "integer",
        "age": "integer",
        "phone": "string",
        "email": "string",
        "education": "string",
        "major": "string",
        "school": "string",
        "graduationYear": "integer",
        "status": "string",
        "currentClient": "string",
        "currentPosition": "string",
        "dispatchStartDate": "date",
        "experience": "integer",
        "currentSalary": "integer",
        "expectedSalary": "string",
        "interviewSkill": "integer",
        "appearance": "integer",
        "technicalAbility": "integer",
        "adaptability": "integer",
        "communicationSkill": "integer",
        "problemSolving": "integer",
        "learningAbility": "integer",
        "teamwork": "integer",
        "rating": "double",
        "introduction": "string",
        "notes": "string",
        "joinDate": "date",
        "lastDispatchDate": "date",
        "dispatchCount": "integer",
        "successRate": "integer",
        "skills": [
            {
                "id": "integer",
                "skillId": "integer",
                "skillName": "string",
                "categoryName": "string",
                "yearsOfExperience": "number",
                "proficiency": "integer",
                "description": "string"
            }
        ]
    }
]
```

### 取得特定人員
- **URL**: `/api/personnel/{id}`
- **方法**: `GET`
- **成功回應** (200): 同上單一人員物件
- **錯誤回應** (404): 找不到人員

### 依狀態查詢人員
- **URL**: `/api/personnel/status/{status}`
- **方法**: `GET`
- **成功回應** (200): 同上人員列表

### 搜尋人員
- **URL**: `/api/personnel/search`
- **方法**: `GET`
- **參數**: `keyword` (搜尋關鍵字)
- **成功回應** (200): 同上人員列表

### 取得人員統計
- **URL**: `/api/personnel/stats`
- **方法**: `GET`
- **成功回應** (200):
```json
{
    "totalCount": "integer",
    "statusDistribution": {
        "可派遣": "integer",
        "已派遣": "integer",
        "其他": "integer"
    },
    "skillDistribution": {
        "技能名稱": "integer"
    }
}
```

### 新增人員
- **URL**: `/api/personnel`
- **方法**: `POST`
- **請求體**: 同上人員物件（不含 id）
- **成功回應** (200): 新增的人員物件

### 更新人員
- **URL**: `/api/personnel/{id}`
- **方法**: `PUT`
- **請求體**: 同上人員物件
- **成功回應** (200): 更新後的人員物件

### 刪除人員
- **URL**: `/api/personnel/{id}`
- **方法**: `DELETE`
- **成功回應** (200):
```json
{
    "message": "Personnel deleted successfully",
    "success": true
}
```

## 客戶管理 API

### 取得所有客戶
- **URL**: `/api/customers`
- **方法**: `GET`
- **成功回應** (200):
```json
[
    {
        "id": "integer",
        "name": "string",
        "industry": "string",
        "address": "string"
    }
]
```

### 取得特定客戶
- **URL**: `/api/customers/{id}`
- **方法**: `GET`
- **成功回應** (200): 同上單一客戶物件

### 新增客戶
- **URL**: `/api/customers`
- **方法**: `POST`
- **請求體**: 同上客戶物件（不含 id）
- **成功回應** (200): 新增的客戶物件

### 更新客戶
- **URL**: `/api/customers/{id}`
- **方法**: `PUT`
- **請求體**: 同上客戶物件
- **成功回應** (200): 更新後的客戶物件

### 刪除客戶
- **URL**: `/api/customers/{id}`
- **方法**: `DELETE`
- **成功回應** (200):
```json
{
    "message": "Customer deleted successfully",
    "success": true
}
```

## 技能管理 API

### 取得所有技能
- **URL**: `/api/skills`
- **方法**: `GET`
- **成功回應** (200):
```json
[
    {
        "id": "integer",
        "name": "string",
        "categoryId": "integer",
        "categoryName": "string",
        "yearsRequired": "integer"
    }
]
```

### 取得特定技能
- **URL**: `/api/skills/{id}`
- **方法**: `GET`
- **成功回應** (200): 同上單一技能物件

### 依分類取得技能
- **URL**: `/api/skills/category/{categoryId}`
- **方法**: `GET`
- **成功回應** (200): 同上技能列表

### 新增技能
- **URL**: `/api/skills`
- **方法**: `POST`
- **請求體**: 同上技能物件（不含 id）
- **成功回應** (200): 新增的技能物件

### 更新技能
- **URL**: `/api/skills/{id}`
- **方法**: `PUT`
- **請求體**: 同上技能物件
- **成功回應** (200): 更新後的技能物件

## 技能分類管理 API

### 取得所有技能分類
- **URL**: `/api/skill-categories`
- **方法**: `GET`
- **成功回應** (200):
```json
[
    {
        "id": "integer",
        "name": "string"
    }
]
```

### 取得特定技能分類
- **URL**: `/api/skill-categories/{id}`
- **方法**: `GET`
- **成功回應** (200): 同上單一技能分類物件

### 新增技能分類
- **URL**: `/api/skill-categories`
- **方法**: `POST`
- **請求體**: 同上技能分類物件（不含 id）
- **成功回應** (200): 新增的技能分類物件

## 錯誤處理

所有 API 在發生錯誤時都會返回適當的 HTTP 狀態碼和錯誤訊息：

- 400 Bad Request: 請求格式錯誤
- 401 Unauthorized: 未認證或認證失敗
- 403 Forbidden: 權限不足
- 404 Not Found: 資源不存在
- 500 Internal Server Error: 伺服器內部錯誤

錯誤回應格式：
```json
{
    "message": "錯誤訊息",
    "success": false
}
```

## 權限控制
- ADMIN 角色可以存取所有資源
- USER 角色只能存取自己建立的資源
- 未登入用戶只能使用登入和註冊 API 