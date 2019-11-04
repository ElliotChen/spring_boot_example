# spring_boot_example

## Official Document

[Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

## application.properties
Spring Boot 要設定的太多，這份總表幫助不少

[commons properties](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

建議還是要用```application.yml```，分層看起來清楚又容易！


## 01Web

基本的Web，可以發現，連@EnableWebMvc都不用加，省太多。


## Maven 操作

### version

release

```
mvn release:update-versions -DautoVersionSubmodules=true
```

versions

指定
```
mvn versions:set -DnewVersion=1.0.1-SNAPSHOT -DprocessAllModules=true -DallowSnapshots=true
```

直接下一版
```
mvn versions:set -DnextSnapshot=true -DprocessAllModules=true -DallowSnapshots=true
```

去除Snapshot改為正式release

```
mvn versions:set -DremoveSnapshot=true -DprocessAllModules=true -DallowSnapshots=true
```