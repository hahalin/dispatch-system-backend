# Lombok 配置指南

本項目使用 [Lombok](https://projectlombok.org/) 來減少樣板代碼，自動生成 getter、setter、constructors 等方法。

## 什麼是 Lombok?

Lombok 是一個 Java 庫，通過注解的方式減少樣板代碼。它可以在編譯時自動生成代碼，如:
- Getter 和 Setter 方法
- Constructors
- equals() 和 hashCode() 方法
- toString() 方法

## 目前遇到的問題

編譯錯誤顯示無法找到各種 getter 和 setter 方法，例如：
```
cannot find symbol method getName()
```

這表明 Lombok 注解未能正確處理，需要進行配置。

## 解決方案

### 1. IDE 插件安裝

#### IntelliJ IDEA
1. 打開 Settings (Ctrl+Alt+S)
2. 導航到 Plugins
3. 搜索 "Lombok"
4. 安裝插件並重啟 IDE

#### Eclipse
1. 下載 lombok.jar: https://projectlombok.org/downloads/lombok.jar
2. 執行 jar: `java -jar lombok.jar`
3. 按照安裝向導將 Lombok 安裝到 Eclipse
4. 重啟 Eclipse

#### VS Code
1. 安裝 "Lombok Annotations Support for VS Code" 擴展
2. 重啟 VS Code

### 2. Maven 配置

確保 pom.xml 中的 Lombok 依賴配置正確：

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.24</version>
    <scope>provided</scope>
</dependency>
```

並在 maven-compiler-plugin 中添加註解處理器配置：

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

### 3. 常用 Lombok 注解

- `@Getter` / `@Setter`: 自動生成 getter 和 setter 方法
- `@NoArgsConstructor`: 生成無參構造函數
- `@AllArgsConstructor`: 生成包含所有參數的構造函數
- `@RequiredArgsConstructor`: 為 final 和 @NonNull 字段生成構造函數
- `@Data`: 組合了 @Getter, @Setter, @ToString, @EqualsAndHashCode 和 @RequiredArgsConstructor
- `@Builder`: 生成建造者模式代碼
- `@Slf4j`: 自動生成 logger 字段

### 4. 示例代碼

請參考 `src/main/java/com/example/dispatch/example/LombokExample.java` 了解如何使用 Lombok 注解。

## 進一步閱讀

- [Lombok 官方文檔](https://projectlombok.org/features/all)
- [Lombok Maven 配置](https://projectlombok.org/setup/maven)
- [IDEA Lombok 插件](https://plugins.jetbrains.com/plugin/6317-lombok)
