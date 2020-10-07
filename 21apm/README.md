# APM

主要利用```Prometheus``` + ```Grafana```來展示基本的SpringBoot應用程式系統監控

## 前言

先前我對系統監控，停留在使用JMX加JConsole，JMX打幾個洞，可以更改log level，可以重新執行batch task，可以重連DB，其它的我就沒什麼要求了。Jconsole裡顯示什麼我就看什麼，不挑的。

再來商業層的部份，就在log中做展示，ELK之於我，已是不可或缺的系統基本元件。

不過最近受到了點啟發，發現系統監控的部份可以再更好一些，但不需要耗費太多人力，才有了這篇筆記。

## SpringBoot

SpringBoot本來就有使用```micrometer```來記錄，所以提供做為```Prometheus```的資料來源其實很容易。

要注意的有下列幾項

### Dependency

在pom.xml裡要加入actuator及micrometer相關的library。

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
	<groupId>io.micrometer</groupId>
	<artifactId>micrometer-registry-prometheus</artifactId>
	<version>1.3.0</version>
</dependency>
```

### Management

在```application.yml```中要加入management的相關設定

```
management:
  metrics:
    export:
      prometheus:
        enabled: true
  endpoints:
    web:
      exposure:
        include: "*"
```

開啟prometheus的export及actuator相關的webapi。

### MeterRegistry

還要在SpringConfig裡多加一個Bean

```
@Bean
MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
	return registry -> registry.config().commonTags("application", "springapm");
}
```

這樣在Grafana裡才會出現可用的Application，否則只能自行手打instance。

### 測試

啟動Application後可以先打[http://localhost:8080/actuator](http://localhost:8080/actuator)，應該可以看到類似下列的回應

```
{"_links":{
...
"health":{"href":"http://localhost:8080/actuator/health","templated":false},
...
"prometheus":{"href":"http://localhost:8080/actuator/prometheus","templated":false},
...
}}
```

再來可以依內容提供的url，再試試health及prometheus的回應

[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

```
{"status":"UP"}
```

[http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus)

```
# HELP process_start_time_seconds Start time of the process since unix epoch.
# TYPE process_start_time_seconds gauge
process_start_time_seconds{application="springapm",} 1.571121446021E9
# HELP jvm_gc_memory_promoted_bytes_total Count of positive increases in the size of the old generation memory pool before GC to after GC
# TYPE jvm_gc_memory_promoted_bytes_total counter
jvm_gc_memory_promoted_bytes_total{application="springapm",} 9744416.0
# HELP tomcat_sessions_active_max_sessions  
# TYPE tomcat_sessions_active_max_sessions gauge
tomcat_sessions_active_max_sessions{application="springapm",} 0.0

....
```
這部份就是做為prometheus顯示用

## Docker Compose

為了方便測試，我將Springboot + Prometheus + Grafana包在同一個docker compose裡。
並且把prometheus跟grafana的設定都放在外部方便修改。

```
version: "3.3"

services:
  prometheus:
    image: prom/prometheus:latest
    ports:
      - '9090:9090'
    volumes:
      - ./config/prometheus.yml:/etc/prometheus/prometheus.yml
    depends_on:
      - app

  grafana:
    image: grafana/grafana:latest
    ports:
      - '3000:3000'
    volumes:
      - ./config/grafana.ini:/etc/grafana/grafana.ini
      - ./data/grafana/lib:/var/lib/grafana
      - ./data/grafana/log:/var/log/grafana
    depends_on:
      - prometheus

  app:
    image: elliot/21apm:1.0-SNAPSHOT
    ports:
      - '8080:8080'
```

## Prometheus

重點要看一下prometheus.yml

```
# my global config
global:
  scrape_interval:     15s # Set the scrape interval to every 15 seconds. Default is every 1 minute.
  evaluation_interval: 15s # Evaluate rules every 15 seconds. The default is every 1 minute.
  # scrape_timeout is set to the global default (10s).

# Alertmanager configuration
alerting:
  alertmanagers:
  - static_configs:
    - targets:

# Load rules once and periodically evaluate them according to the global 'evaluation_interval'.
rule_files:
  # - "first_rules.yml"
  # - "second_rules.yml"

# A scrape configuration containing exactly one endpoint to scrape:
# Here it's Prometheus itself.
scrape_configs:
  # The job name is added as a label `job=<job_name>` to any timeseries scraped from this config.
  - job_name: 'prometheus'
    static_configs:
    - targets: ['localhost:9090']
  - job_name: 'springboot_prometheus'
    scrape_interval: 5s
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['app:8080']
```

其中有兩個job，```springboot_prometheus```就是用來擷取先前[http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus)的資料。用app做為host name原因是因為放在docker compose裡使用的名稱為```app```。

## Grafana

這部份的設定都沒有特別需要修改的，Sample裡用的grafana.ini的內容都是註解掉的。要處理的都在畫面操作中。

## 整合

### 1. docker-compse up 

第一步當然是啟動docker

### 2. Login to Grafana

登入url : [http://localhost:3000/login](http://localhost:3000/login)

預設帳號 : admin

預設密碼 : admin

![Login](https://blog.elliot.tw/wp-content/uploads/2019/10/apm01_login.png)

### 3. Add Data Source
按下```Add data source```

![apm02](https://blog.elliot.tw/wp-content/uploads/2019/10/apm02_add_ds.png)

選擇```Prometheus```做為data source

![apm03](https://blog.elliot.tw/wp-content/uploads/2019/10/apm03_add_prometheus.png)

在url填入```http://prometheus:9090```後按下```Save & Test```，host name會用prometheus也是為在docker compose裡設定的關係。

![apm04](https://blog.elliot.tw/wp-content/uploads/2019/10/apm04_setting_prometheus.png)

### 4. Add Dashboard

利用import的方式加入其他作者提供的dash board範本

![apm05](https://blog.elliot.tw/wp-content/uploads/2019/10/apm05_import_dashboard.png)

在Dashboard裡填入範本id：```12272```

![apm06](https://blog.elliot.tw/wp-content/uploads/2019/10/apm06_import_sping_dashboard.png)

選擇Prometheus後按下```Import```

![apm07](https://blog.elliot.tw/wp-content/uploads/2019/10/apm07_add_spring_prometheus.png)

### 5. 完成

此時就有基本的畫面可用囉。

![apm08](https://blog.elliot.tw/wp-content/uploads/2019/10/apm08_demo.png)


## 總結

不花什麼力氣就能有一個可展示的效能監控畫面，經過簡單的設定還可以針對各種情形發出告警，實在是...

相關原始檔可在[github.com/ElliotChen](https://github.com/ElliotChen/spring_boot_example/tree/master/21apm)裡找到。


# Info

## spring-boot-maven-plugin

透過[build-info](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/#goals-build-info) 搭配actuator可以直接提供幾類的資訊

1. build-info
2. git
3. system environment

### build-info
```xml
<plugin>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-maven-plugin</artifactId>
	<executions>
		<execution>
			<goals>
				<goal>build-info</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```

在執行 ```mvn package``` 時，會透過plugin在```META-INF/```下產生```build-info.properties```
內容預設有下列這些

```properties
build.artifact=21apm
build.group=tw.elliot
build.name=21apm
build.time=2020-06-18T06\:40\:45.807Z
build.version=1.0.0-SNAPSHOT
```

配合```spring-boot-actuator```，啟動後可以透過 [http://localhost:8080/actuator/info](http://localhost:8080/actuator/info)
看到上列的資料以```JSON```的格式顯示

```json

{
    "build": {
        "artifact": "21apm",
        "group": "tw.elliot",
        "name": "21apm",
        "time": "2020-06-18T06:01:47.133Z",
        "version": "1.0.0-SNAPSHOT"
    }
}
```

若覺得這樣不足，還可透過在pom.xml中加入configuration來增加

```xml
<configuration>
	<additionalProperties>
		<encoding.source>UTF-8</encoding.source>
		<encoding.reporting>UTF-8</encoding.reporting>
		<java.source>${maven.compiler.source}</java.source>
		<java.target>${maven.compiler.target}</java.target>
	</additionalProperties>
</configuration>
```

這樣就會顯示

```json
{
    "build": {
        "artifact": "21apm",
        "encoding": {
            "reporting": "UTF-8",
            "source": "UTF-8"
        },
        "group": "tw.elliot",
        "java": {
            "source": "14",
            "target": "14"
        },
        "name": "21apm",
        "time": "2020-06-18T06:01:47.133Z",
        "version": "1.0.0-SNAPSHOT"
    }
}
```

### Git 

顯示build時使git repository的版本，這是我最需要的部份！
要能產生這資料，需要先加入一個plugin

```xml
<plugin>
	<groupId>pl.project13.maven</groupId>
	<artifactId>git-commit-id-plugin</artifactId>
	<version>4.0.0</version>
	<executions>
		<execution>
			<id>get-the-git-infos</id>
			<goals>
				<goal>revision</goal>
			</goals>
		</execution>
	</executions>
	<configuration>
		<verbose>true</verbose>
		<generateGitPropertiesFile>true</generateGitPropertiesFile>
	</configuration>
</plugin>
```

執行 ```mvn git-commit-id:revision``` 後會產生```classpath:git.properties```
內容大致如下

```properties
#Generated by Git-Commit-Id-Plugin
#Thu Jun 18 14:01:49 CST 2020
git.branch=master
git.build.host=ElliotChende-MacBook-Pro.local
git.build.time=2020-06-18T14\:01\:49+0800
git.build.user.email=elliot.chen@elliot.tw
git.build.user.name=Elliot Chen
git.build.version=1.0.0-SNAPSHOT
git.closest.tag.commit.count=
git.closest.tag.name=
git.commit.id=3b42412b3b3e86cc4ac0ca62a334777b9bae8c9d
git.commit.id.abbrev=3b42412
git.commit.id.describe=3b42412-dirty
git.commit.id.describe-short=3b42412-dirty
git.commit.message.full=add build-info
git.commit.message.short=add build-info
git.commit.time=2020-05-29T17\:17\:24+0800
git.commit.user.email=elliot.chen@elliot.tw
git.commit.user.name=Elliot Chen
git.dirty=true
git.local.branch.ahead=0
git.local.branch.behind=0
git.remote.origin.url=https\://github.com/ElliotChen/spring_boot_example.git
git.tags=
git.total.commit.count=54
```

配合```spring-boot-actuator```，啟動後一樣透過 [http://localhost:8080/actuator/info](http://localhost:8080/actuator/info) 看到下列資訊

```json
{
    "build": {
        ...
    },
    "git": {
        "branch": "master",
        "commit": {
            "id": "3b42412",
            "time": "2020-05-29T09:17:24Z"
        }
    }
}
```

除了build-info外，還多出了git commit相關的資料


### build Image

#### Dockerfile

```
mvn package dockerfile:build
```

#### Spring Boot Image