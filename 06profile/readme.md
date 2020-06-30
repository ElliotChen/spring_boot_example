# Profile

主要展示Spring Boot 中 ```Profile``` 的使用

profile的設定是Build中很重要的一環，在我大部份的經驗裡，如果Build完後不能直接上到各種環境，還需要做修改的，通常會遇到很多況狀。


## 早期構想

我早期的想法是在maven中，依據maven的機制，在pom.xml中設定不同的```<profile/>```.
並在maven-resource-plugin裡，設定不同的```resources```目錄，存放實際會因環境而有所改變的檔案。

像是下面這樣子

```
├── profiles
│   ├── prod
│   │   └── config.properties
│   ├── test
│   │   └── config.properties
│   ├── personal
│   │   └── config.properties
```

除了區別不同的profile外，還希望透過```.gitignore```的設定，將個人的```resources```目錄隔開不要上傳。
這樣的優點是，每次要commit修改，就只要```git add .```即可，不需要考慮哪些不該加入，也避免該加的沒加。

我不太採用在pom.xml中設定properties，然後config檔中設定變數來取代的方式，主要是因為在本機測試時會有點小麻煩。

## 現行情形

在Spring Boot 中如果要使用```application.yml```來設定的話，可以將共用的部份寫在```application.yml```，而其餘會隨著profile而有不同的部份，就各別寫在```application-${profile_name}.yml```中，並在測試或起動時還能動態指定profile。

另外是因為```Docker```實用度太高了，個人開發環境利用了Docker後，基本上也能統一所有的設定，所以在設定裡，建議不要寫定ip，而都使用hostname。
hostname對應ip可在hosts裡更改，這樣無論是在各種環境裡都能正常運作。

## Spring Boot 相關機制

以下的說明僅針對```application.yml```

### 區分profiles

使用```---```來區分

```
# 共用區
spring:
  application:
    name: 06profile

## 以下為prod
---
spring:
  profiles: prod

server:
  port: 8080

## 以下為stage
---
spring:
  profiles: stage

server:
  port: 9080
```

### 指定預設使用的profile

用```spring.profiles.active```來指定即可

```
spring:
  profiles:
    active: stage
```

### 動態指定使用的profile

在執行jar時，用```--spring.profiles.active```來指定

```
java -jar xxxx.jar --spring.profiles.active=prod
```

在UnitTest時，用```@ActiveProfiles()```來指定

### 在maven中執行spring-boot-maven-plugin時指定

參考[spring-boot-maven-plugin doc](https://docs.spring.io/spring-boot/docs/current/maven-plugin/examples/run-profiles.html)

在Maven裡要執行spring-boot:run時，以```spring-boot.run.profiles```指定

```
mvn -Pstage -Dspring-boot.run.profiles=dev spring-boot:run
```

更便利些,就在pom.xml中的profile的properties加入

```
<profile>
    <id>stage</id>
    <properties>
        <spring-boot.run.profiles>stage</spring-boot.run.profiles>
    </properties>
</profile>
```

之後在maven裡只要執行

```
mvn -Pstage spring-boot:run
```

### Resource Filtering

由於現在IDE都支援Maven Profile的切換，所以在開發上提供了一定的便利性，
也就改用Resource Filtering的方式來修改

#### application.yml

在application.yml加上active的設定

```yaml
spring:
  profiles:
    active: @activeProfile@
```

#### profile in pom.xml

profile 裡加上```activeProfile```的element，其中的資料在執行mvn resources時會replace ```@activeProfile@```

```xml
<profile>
	<id>stage</id>
	<properties>
		<activeProfile>stage</activeProfile>
	</properties>
</profile>
```

#### build in pom.xml

在resource裡加上```filtering```，才會進行replace的動作

```xml
<resource>
	<directory>src/main/resources</directory>
	<includes>
		<include>application.yml</include>
		<include>application-${activeProfile}.yml</include>
	</includes>
	<filtering>true</filtering>
</resource>
```

所以在build的時候指定profile，執行jar時就不再需要加上參數了。