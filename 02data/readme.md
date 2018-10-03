# Spring Data

## DataSource configuration

### Executing Initialize Script

1. Define platform is better
spring.datasource.platform = h2

2. Add script in src/main/resources
* schema-${platform}.sql
* data-${platform}.sql


## Pagination

### PagingAndSortingRepository<T,ID>

1. Create a Repository Interface and extend from PagingAndSortingRepository
2. Page<T> findAll/findBuXXX with a Pageable parameter

## Query By Example

### Example.of(T)

create an Example by Example.of(T)
using - findAll(Example)
We can use Pagination and QBE together

```java
Page<T> findAll(Example<T> customer, Pageable pageable);
```

## Pagination in webmvc

### PageableDefault
PageableDefault: assign default page, size and sort
Pageable: retrieve page, size and sort from request

```java
@GetMapping("/example")
public Page<Customer> findAll(@ModelAttribute Customer customer, @PageableDefault(value = 0, size = 5) Pageable pageable) {
	
}
```

### ModelAttribute


## Redis

## System

### How to start Redis

``` cmd
redis-server /usr/local/etc/redis.conf
```

### connect redis

```
redis-server redis-cli
```

### Maven Dependency

1. Add spring-boot-starter-data-redis, and spring-data-redis will be imported automatically.


### Usage

1. Cache
2. Session


## Reference 
[Github Example](https://github.com/spring-projects/spring-data-examples)

