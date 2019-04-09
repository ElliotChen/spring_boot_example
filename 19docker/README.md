# Docker

使用```dockerfile-maven-plugin```，依據```Dockerfile```裡的描述來產生docker image。


## dockerfile-maven-plugin

另一個```docker-maven-plugin```已停止維護，所以現行都是改以```dockerfile-maven-plugin```加上```Dockerfile```兩者合作的方式。

```xml
<plugin>
    <groupId>com.spotify</groupId>
    <artifactId>dockerfile-maven-plugin</artifactId>
    <version>1.4.10</version>
    <configuration>
        <contextDirectory>${project.basedir}</contextDirectory>
        <repository>${docker.image.prefix}/${project.artifactId}</repository>
        <tag>${project.version}</tag>
        <verbose>true</verbose>
        <buildArgs>
            <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
        </buildArgs>
    </configuration>
</plugin>
```

重點其實在```configuration```

對應實際docker image資訊就能明白```repository```、```tag```的用途

------------
|REPOSITORY|TAG|IMAGE ID|
|----|----|----|
|springboot/19docker|1.0-SNAPSHOT|ea012ea0b960|


而```buildArgs```則是讓我們將變數帶入```Dockerfile```中

在此，只加入了```JAR_FILE```，將maven產生的最後結果加入到```Dockerfile```使用。

## Dockerfile

主要內容可參考 [Dockerfile reference](https://docs.docker.com/engine/reference/builder/)

在此使用簡單的script

```
FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080

ARG JAR_FILE
ADD ${JAR_FILE} springdocker.jar
ENTRYPOINT ["java", "-jar", "springdocker.jar"]
```

```openjdk:8-jdk-alpine```算是SIZE較小的jdk image，約100MB左右，其他比較完整的都要600MB左右

```ENTRYPOINT```指出了docker container啟動後要執行```java -jar springdocker.jar```

SpringBoot 與 docker能這麼楔合也是這原因，直接執行一個jar即可，不需多餘的設定。
當然改用Assembly或Shade也可以，但能去掉tomcat來執行web程式還是很不錯的。

## Docker Command

與整個docker 執行相關的還有如何滙入滙出docker image。


### docker image

#### list all images

列出現有的docker image

```
docker images 
```

#### save image to file

```
docker save -o ${tarfile} ${repository}:${tag}
```

例如

```
docker save -o myspringdocker.tar springboot/19docker:1.0-SNAPSHOT
```

#### load image from file

```
docker load --input ${tarfile}
```

執行情形可能如下

```
> docker load --input myspringdocker.tar
a3f36d743131: Loading layer  18.44MB/18.44MB
Loaded image: springboot/19docker:1.0-SNAPSHOT
```

#### remove docker image

```
docker rmi ${imageId}
```

#### execute docker image

基本就是```docker run```加參數

```
> docker run -p 8080:8080 --name springdocker -d springboot/19docker:1.0-SNAPSHOT
5b48799e4e2cea8bfce9fc42f6cca2c277cc7d015729f41c0a1376e055fb3e2d
```


#### list docker containers

```
docker container ls -a
```

#### remove docker container

```
docker rm ${containerId}or${containerName}
```