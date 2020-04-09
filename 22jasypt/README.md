# Jasypt

[官網](http://www.jasypt.org/index.html)

## 起因

帳密都有加密的要求，例如DB
雖可使用config server來減少外洩的問題，但本機端要單獨測試時能加密還是不錯的。

## spring boot dependency

```xml
<dependency>
	<groupId>com.github.ulisesbocchio</groupId>
	<artifactId>jasypt-spring-boot-starter</artifactId>
	<version>${jasypt.version:3.0.2}</version>
</dependency>
```

## maven plugin 

```xml
<plugin>
	<groupId>com.github.ulisesbocchio</groupId>
	<artifactId>jasypt-maven-plugin</artifactId>
	<version>${jasypt.version:3.0.2}</version>
</plugin>
```

這可以協助加密的操作

```cmd
mvn jasypt:encrypt-value -Djasypt.encryptor.password="key" -Djasypt.plugin.value="password_value"
```

可以產生下列以"ENC"開頭的文字，再回寫至檔案中。

```
ENC(O+/mw0eLgXSx2S7+9UjMw3PppQVnwiNrzohYaE+brbkPzowfm7w7YnMKmEYEUCJ5)
```

也可藉由這個Plugin對properties或yml檔案內容進行處理。

## application.yml

基本的設定需要只需要下列的設定即可

```yaml
jasypt:
  encryptor:
    password: yourpassword
```

這樣只要在Spring中有以```ENC()```的字串就會被jasypt解析。

```yaml
spring:
  datasource:
  ....
    password: ENC(3HNOeOw62KXKgX/SJr8vRgXrXnVfTCphHtK7++hy4DKVliop08b43LvS5AcYMRkK)
```