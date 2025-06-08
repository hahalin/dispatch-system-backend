# 派遣系統後端快速入門指南

本指南將協助您設置並運行派遣系統的後端服務。

## 系統需求

- Java 11+
- PostgreSQL 10+
- Maven 3.6+

## 編譯錯誤修復


## 環境設置

### 安裝 Java

1. 訪問 [Oracle JDK 下載頁面](https://www.oracle.com/java/technologies/downloads/) 或使用OpenJDK
2. 下載並安裝Java 11或更高版本
3. 設置JAVA_HOME環境變數：
   - Windows: 設置`JAVA_HOME`環境變數指向Java安裝目錄
   - Linux/Mac: 在`~/.bashrc`或`~/.zshrc`中添加`export JAVA_HOME=/path/to/java`

### 安裝 PostgreSQL

1. 訪問 [PostgreSQL 下載頁面](https://www.postgresql.org/download/)
2. 下載並安裝PostgreSQL 10或更高版本
3. 安裝過程中設置管理員密碼
4. 創建資料庫和用戶：
   ```sql
   CREATE USER dispatch WITH PASSWORD 'dispatch123';
   CREATE DATABASE dispatch_db OWNER dispatch;
   ```

## 配置應用程式

應用程式的配置位於 `src/main/resources/application.yml` 和 `application.properties` 文件中。

主要配置項目：

```yaml
# 資料庫連接設置
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/dispatch_db
    username: dispatch
    password: dispatch123
    
# JWT設置
jwt:
  secret: 您的密鑰
  expiration: 86400000  # 24小時，以毫秒為單位
  
# 服務器端口設置
server:
  port: 8080
  servlet:
    context-path: /api
```

## 構建和運行

### 使用 Maven

1. 使用終端或命令提示符，導航到專案根目錄
2. 執行命令構建專案：
   ```bash
   mvn clean install
   ```
3. 執行命令啟動應用程式：
   ```bash
   mvn spring-boot:run
   ```

### 使用 JAR 文件

構建完成後，您也可以直接運行生成的JAR文件：

```bash
java -jar target/dispatch-system-backend-0.0.1-SNAPSHOT.jar
```

## 應用程式結構

- **控制器 (Controllers)**: 處理HTTP請求
- **服務 (Services)**: 實現業務邏輯
- **儲存庫 (Repositories)**: 資料庫訪問層
- **模型 (Models)**: 資料實體定義
- **DTO (Data Transfer Objects)**: 資料傳輸對象
- **安全性 (Security)**: 認證和授權邏輯

## API端點

啟動應用程式後，可以通過以下端點訪問API：

- **Base URL**: `http://localhost:8080/api`
- **登入**: `POST /auth/login`
- **健康檢查**: `GET /`

## 預設帳戶

系統默認創建了以下帳戶：

- **管理員**: 
  - 用戶名: admin
  - 密碼: password
- **普通用戶**:
  - 用戶名: melody
  - 密碼: password

## 資料庫結構

系統使用以下主要資料表：

### **核心表格**

1. **app_user** - 使用者帳號
2. **customer** - 客戶資料
3. **skill_category** - 技能分類（前端、後端、資料庫、作業系統）
4. **skill** - 技能詳細資料
5. **project** - 專案資料（客戶、名稱、期間、內容、職稱）
6. **personnel** - 駐點人員主表

### **關聯表格**

- **personnel_skill** - 人員技能關聯（包含年資、熟練度）
- **personnel_project** - 人員專案經驗
- **project_skill** - 專案所需技能
- **personnel_language** - 語言能力
- **personnel_industry** - 行業經驗

## 📊 **測試資料包含**

- 2個測試帳號（admin/testuser，密碼都是 password123）
- 6個技能分類，35種技能
- 3家客戶公司
- 4個專案
- 5位駐點人員
- 完整的技能關聯和專案經驗

## ⭐ **星等評分系統**

- 面談應對、外型形象、專業能力、適應能力都是 1-5 星等
- 技能熟練度也是 1-5 等級

## 故障排除

### 編譯錯誤："cannot find symbol"

如果您遇到類似 "cannot find symbol method getXXX()" 的錯誤：

1. 確保您已經正確安裝了Lombok插件
2. 重啟IDE並重新構建專案
3. 檢查DTO類和服務類中的命名一致性

### 資料庫連接問題

- 確保PostgreSQL服務正在運行
- 驗證資料庫用戶名和密碼是否正確
- 檢查資料庫是否已創建

### Java/Maven問題

- 確保已正確設置JAVA_HOME環境變數
- 確保使用的Java版本與配置相符
- 使用`mvn -v`檢查Maven是否正確安裝

### sql




## 支援與幫助

如有任何問題，請聯繫系統管理員。
