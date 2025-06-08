@echo off
set JAVA_HOME=C:\Program Files\Java\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%
echo Using Java version:
java -version
echo.
echo Maven version:
mvn -version
echo.
echo Starting Maven build with Java 21...
mvn clean install -e
echo.
echo Build completed with exit code: %ERRORLEVEL%
