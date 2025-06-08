@echo off
echo 派遣系統後端啟動腳本
echo =============================================

REM 檢查 Java
java -version
if %ERRORLEVEL% NEQ 0 (
    echo 錯誤: Java 未安裝或未設置環境變數
    echo 請安裝 Java 並設置 JAVA_HOME 環境變數
    echo 參考 QUICK_START_GUIDE.md 中的說明
    goto END
)

REM 檢查 Maven
mvn -v
if %ERRORLEVEL% NEQ 0 (
    echo 錯誤: Maven 未安裝或未設置環境變數
    echo 請安裝 Maven 並將其添加到 PATH 環境變數
    echo 參考 QUICK_START_GUIDE.md 中的說明
    goto END
)

REM 嘗試構建並啟動應用程式
echo 正在構建並啟動應用程式...
echo 執行命令: mvn clean install spring-boot:run
mvn clean install spring-boot:run

:END
echo =============================================
echo 如需更多資訊，請參考 QUICK_START_GUIDE.md
pause
