# 派遣系統後端部署注意事項

在構建並運行專案前，您需要解決以下問題：

## 1. POM文件標籤修正

在 `pom.xml` 文件中，有一個不標準的XML標籤需要修正：

1. 打開 `pom.xml` 文件（位於專案根目錄下）
2. 找到 `<version>0.0.1-SNAPSHOT</version>` 下面一行的標籤 `<n>dispatch-system-backend</n>`
3. 將該行修改為標準Maven標籤 `<n>dispatch-system-backend</n>`
4. 保存文件

## 2. Lombok配置問題

目前編譯失敗的主要原因是Lombok註解未能正確生成getter和setter方法。請參考 [Lombok配置指南](LOMBOK_GUIDE.md) 獲取詳細說明。

有以下幾種解決方法：

### 解決方法一：安裝Lombok插件（推薦）

1. 在您的IDE（如IntelliJ IDEA或Eclipse）中安裝Lombok插件
2. 重啟IDE
3. 在IDE中重新構建專案

### 解決方法二：添加Lombok處理器

1. 在pom.xml中修改Lombok依賴配置：

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.24</version>
    <optional>true</optional>
    <scope>provided</scope>
</dependency>
```

2. 添加Lombok註解處理器到Maven編譯配置：

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <annotationProcessorPaths>
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.24</version>
            </path>
        </annotationProcessorPaths>
        <source>${java.version}</source>
        <target>${java.version}</target>
    </configuration>
</plugin>
```

### 解決方法三：手動添加Getter和Setter方法

如果以上方法都不奏效，您可以手動為所有模型類和DTO類添加getter和setter方法。例如：

1. 對於模型類 `SkillCategory`：

```java
// 添加getter和setter方法
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

// 其他屬性的getter和setter方法...
```

2. 同樣的方式處理其他所有模型類和DTO類。

## 3. 快速修復腳本

專案根目錄下提供了一個`fix_project.bat`腳本，可以幫助您自動修復POM文件標籤問題。運行此腳本：

```
.\fix_project.bat
```

完成以上修正後，您應該能夠使用 `mvn` 命令正常構建並運行專案。
