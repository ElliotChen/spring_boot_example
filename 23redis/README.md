# Redis Master Slave Mode

各類的讀寫分離已經是常態了，
而且在容器化的幫助下，要模擬各種情形實在不是什麼太大的負擔，
在這簡單記錄一下Redis Master Slave的設定加上Spring Boot


透過下列幾個步驟設定後就可以試用

1. Master
2. Slave
3. Tool
4. Spring Boot

相關程式放在 [Github](https://github.com/ElliotChen/spring_boot_example/tree/master/23redis) 中

## Master

```yaml
  redis-master:
    container_name: redis-master
    image: redis:5-alpine
    ports:
      - 6379:6379
    volumes:
      - ./data/redis-master:/data
```

測試時使用alpine的版本，並注意port的對應。

## Slave

```yaml
  redis-slave:
    container_name: redis-slave
    image: redis:5-alpine
    command: redis-server --slaveof redis-master 6379
    ports:
      - 6380:6379
    volumes:
      - ./data/redis-slave:/data
    links:
      - redis-master
```

要注意的是command ```redis-server --slaveof redis-master 6379```
指定```slaveof```，，並注意port的對應即可。

## Tool

```yaml
  redis-commander:
    container_name: redis-commander
    hostname: redis-commander
    image: rediscommander/redis-commander:latest
    environment:
    - REDIS_HOSTS=master:redis-master,slave-1:redis-slave
    ports:
    - 8081:8081
```

提供一個簡單的介面，方便觀察多個redis的改變。
設定好```REDIS_HOSTS```即可

可透過[http://localhost:8081](http://localhost:8081)

就能看到類似下列畫面
![redis commander](https://blog.elliot.tw/wp-content/uploads/2020/04/redis-commander01.png)
透過refresh就能同時看到master, slaves key值的增減

## Spring Boot

感謝Spring，要做的事少了很多

### pom

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

只要加入```starter-data-redis```即可

### application.yml

```yaml
redis:
  mater:
    host: localhost
    port: 6379
  slaves:
    - host: localhost
      port: 6380
```

master只有一台
slaves可以設定多台

## Demo

透過url[http://localhost:8080/service/demo](http://localhost:8080/service/demo) 就能加入demo用的值，
可以觀察到Master及Slave都會有值的變化

![redis-commander02](https://blog.elliot.tw/wp-content/uploads/2020/04/redis-commander02.png)

![redis-commander03](https://blog.elliot.tw/wp-content/uploads/2020/04/redis-commander03.png)
