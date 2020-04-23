# Redis 應用

## Redis Master Slave Mode

各類的讀寫分離已經是常態了，
而且在容器化的幫助下，要模擬各種情形實在不是什麼太大的負擔，
在這簡單記錄一下Redis Master Slave的設定加上Spring Boot


透過下列幾個步驟設定後就可以試用

1. Master
2. Slave
3. Tool
4. Spring Boot

相關程式放在 [Github](https://github.com/ElliotChen/spring_boot_example/tree/master/23redis) 中

### Master

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

### Slave

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

### Tool

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

### Spring Boot

感謝Spring，要做的事少了很多

#### pom

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

只要加入```starter-data-redis```即可

#### application.yml

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

### Demo

透過url[http://localhost:8080/service/demo](http://localhost:8080/service/demo) 就能加入demo用的值，
可以觀察到Master及Slave都會有值的變化

![redis-commander02](https://blog.elliot.tw/wp-content/uploads/2020/04/redis-commander02.png)

![redis-commander03](https://blog.elliot.tw/wp-content/uploads/2020/04/redis-commander03.png)


## Distributed Lock

### 前言

分布式鎖應用場景相當廣，利用redis, RDB(mysql, oracle ...), ZK等來達成的例子所在多有。

Spring Integration 提供了一個可切換的分布式鎖機制，可以避免大家自行重新開發。

使用Redis主要是因為效率高，其他類型的效率慢上好幾個等級，當然如果是應用在早期的系統，使用mysql也是方便的選擇。

### 準備

#### Dependencies

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-integration</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.integration</groupId>
	<artifactId>spring-integration-redis</artifactId>
</dependency>
```

需要加入上列的libraries

#### configuration

```java
@Bean
public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
	return new RedisLockRegistry(redisConnectionFactory, "eckey");
}
```

配合Redis，使用```RedisLockRegistry```


### Sample

```java
	@GetMapping(path = "/lock")
	public String lockAndDo() {
		/*
		1. Obtain
		2. TryLock
		3. Unlock
		 */
		String lockKey = "SKU_2020_NIKE_AJ_M_BlUE_9";
		log.info("Try to get Lock with key[{}]", lockKey);
		Lock lock = redisLockRegistry.obtain(lockKey);

		try {
			boolean lockStatus = lock.tryLock(10, TimeUnit.SECONDS);
			log.info("Lock with key[{}] is [{}]", lockKey, lockStatus);

			if (lockStatus) {
				try {
					/*
					Do our process here
			 		*/
					// pretend to be busy.
					TimeUnit.SECONDS.sleep(5);
				} finally {
					log.info("Unlock key[{}] success!", lockKey);
					lock.unlock();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Finish";
	}
```

簡單4步驟

1. 決定要鎖的key，像是SKU
2. Obtain Lock
3. Try to Lock
4. Unlock

就同與Sample一樣，就能為系統增加分布鎖的功能。
 