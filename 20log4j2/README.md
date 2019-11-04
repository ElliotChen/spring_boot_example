# Log4j2

## Apache log4j2 Site

[Apache Log4j 2](https://logging.apache.org/log4j/2.x/)

## Maven Dependencies

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
	<exclusions>
		<exclusion>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-logging</artifactId>
		</exclusion>
	</exclusions>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

## Configuration

### Spring預設

可以參考 SpringBoot本身的設定

1. [Spring boot default log4j2.xml](https://github.com/spring-projects/spring-boot/blob/master/spring-boot-project/spring-boot/src/main/resources/org/springframework/boot/logging/log4j2/log4j2.xml)
2. [Spring boot default log4j2-file.xml](https://github.com/spring-projects/spring-boot/blob/master/spring-boot-project/spring-boot/src/main/resources/org/springframework/boot/logging/log4j2/log4j2-file.xml)

這部份的設定若需修改，可透過```application.yml```裡相關的```logging```來處理

### Spring Source Code 解析

#### 寫入檔案最簡設定

```yaml
logging:
  path: ./logs
```

#### LoggingSystemProperties.java

Spring Logging 讀取 [LoggingSystemProperties.java](https://github.com/spring-projects/spring-boot/blob/master/spring-boot-project/spring-boot/src/main/java/org/springframework/boot/logging/LoggingSystemProperties.java)

我們可以看到其中主要設定參考

```
public void apply(LogFile logFile) {
	PropertyResolver resolver = getPropertyResolver();
	setSystemProperty(resolver, EXCEPTION_CONVERSION_WORD, "exception-conversion-word");
	setSystemProperty(PID_KEY, new ApplicationPid().toString());
	setSystemProperty(resolver, CONSOLE_LOG_PATTERN, "pattern.console");
	setSystemProperty(resolver, FILE_LOG_PATTERN, "pattern.file");
	setSystemProperty(resolver, FILE_CLEAN_HISTORY_ON_START, "file.clean-history-on-start");
	setSystemProperty(resolver, FILE_MAX_HISTORY, "file.max-history");
	setSystemProperty(resolver, FILE_MAX_SIZE, "file.max-size");
	setSystemProperty(resolver, FILE_TOTAL_SIZE_CAP, "file.total-size-cap");
	setSystemProperty(resolver, LOG_LEVEL_PATTERN, "pattern.level");
	setSystemProperty(resolver, LOG_DATEFORMAT_PATTERN, "pattern.dateformat");
	if (logFile != null) {
		logFile.applyToSystemProperties();
	}
}
```

對比Default的```log4j2-file.xml```

```xml
<RollingFile name="File" fileName="${sys:LOG_FILE}" filePattern="${sys:LOG_PATH}/$${date:yyyy-MM}/app-%d{yyyy-MM-dd-HH}-%i.log.gz">
...
</RollingFile>
```

可以發現缺少了```LOG_FILE```及```LOG_PATH```的設定，所以如果在設定中僅使用

```yaml
logging:
  config: classpath:org/springframework/boot/logging/log4j2/log4j2-file.xml
```

會無法正常產生log檔。

#### LogFile.java

實際會影響到log檔的設定出現在[LogFile.java](https://github.com/spring-projects/spring-boot/blob/master/spring-boot-project/spring-boot/src/main/java/org/springframework/boot/logging/LogFile.java)

```
public void applyTo(Properties properties) {
	put(properties, LoggingSystemProperties.LOG_PATH, this.path);
	put(properties, LoggingSystemProperties.LOG_FILE, toString());
}

public String toString() {
	if (StringUtils.hasLength(this.file)) {
		return this.file;
	}
	return new File(this.path, "spring.log").getPath();
}
```

可以看到這時才將```LOG_FILE```及```LOG_PATH```的設定置入，
也可以知道，如果有```LOG_PATH```而無```LOG_FILE```，就會以```spring.log```做為檔名。

所以最基本要將log寫入檔案，就是下列設定

```yaml
logging:
  path: ./logs
```

### 使用自訂log4j2.xml

若在```classpath```中有存在檔案```log4j2.xml```，SpringBoot就會自動載入做為設定檔


## Logstash

### layout

[jsonevent](https://github.com/logstash/log4j-jsonevent-layout)

[LogstashLayout](https://github.com/vy/log4j2-logstash-layout)

