# Reids Usage

## Cache

### Maven Dependency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```
reference [MyConfigService]("./src/main/java/tw/elliot/redis/service/MyConfigService.java")
### @Cacheable
### @CachePut
### @CacheEvict

## Session

### Maven Dependency

```xml
<dependency>
    <groupId>org.springframework.session</groupId>
    <artifactId>spring-session-data-redis</artifactId>
</dependency>
```

### Spring Configuration

```java 
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig extends AbstractHttpSessionApplicationInitializer {
}
```

1. extends from AbstractHttpSessionApplicationInitializer
2. Add @EnableRedisHttpSession

## DAO