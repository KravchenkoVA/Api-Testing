**Environment requirements:**
1. JDK 8  (https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
2. Maven 3.1.1 or higher (https://maven.apache.org/download.cgi) installation guide: https://www.apache-maven.ru/install.html

**Run:**
Execute command: mvn clean test allure:aggregate -Dmaven.test.failure.ignore=true

**Note:**
1. You can get report after run by opening **index.html**. Path: _target\site\allure-maven-plugin\index.html_
2. File **config.properties** (_src\main\resources\config.properties_) has base urls and using browser configs.
You can set up **environment variable** (same as property in properties file) to provide using browser or base url for Api tests. 