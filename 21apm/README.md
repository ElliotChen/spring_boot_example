# APM

主要利用Prometheus + Grafana來展示基本的系統監控

## build

```
mvn clean package dockerfile:build
```

## Actuator - Production Ready Features!

[Production-ready Features](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html)

### HttpTrace

系統需要提供一個HttpTraceRepository的Bean

```
public interface HttpTraceRepository {
    List<HttpTrace> findAll();

    void add(HttpTrace trace);
}
```

然後就能透過[](http://localhost:8080/actuator/httptrace)