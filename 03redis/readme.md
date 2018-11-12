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


## Redis GUI Client for MAC

[TablePlus](https://tableplus.io/blog/)

## Docker Compose

1. [redis.conf](http://download.redis.io/redis-stable/redis.conf)
2. 

```
version: "3.3"

services:
  redis:
    image: redis:5
    ports:
      - 6379:6379
    volumes:
      - ./redis.conf:/usr/local/etc/redis/redis.conf
```