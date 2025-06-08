# 派遣系統後端
Spring Boot + PostgreSQL

## 快速入門

請參閱 [快速入門指南](QUICK_START_GUIDE.md) 獲取詳細的設置和使用說明。

## 部署前須知

在構建和執行專案前，請查看 [部署注意事項](DEPLOYMENT_NOTES.md) 以解決可能遇到的問題。

## 主要問題修復

目前專案存在兩個主要問題需要解決：

1. **POM文件標籤問題**: `<n>` 標籤需要修改為標準Maven標籤
2. **Lombok處理問題**: 需要配置Lombok插件或處理器以生成getter和setter方法

## 構建與運行

按 Win + R，輸入 sysdm.cpl，按 Enter

在修正上述問題後，執行以下命令：

```bash
mvn clean install

mvn spring-boot:run
```

## 技術堆疊

- Spring Boot 2.7.0
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- Flyway 數據庫遷移
- Lombok
- Apache POI (Word處理)
