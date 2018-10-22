# Thymeleaf

## Maven

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<!-- For Layout Decorator -->
<dependency>
    <groupId>nz.net.ultraq.thymeleaf</groupId>
    <artifactId>thymeleaf-layout-dialect</artifactId>
    <version>2.3.0</version>
</dependency>
```

## Expression

### Simple expressions

1. Variable Expressions: ${...}
2. Selection Variable Expressions: *{...}
3. Message Expressions: #{...}
4. Link URL Expressions: @{...}
5. Fragment Expressions: ~{...}

### Text operations

1. String concatenation: +
2. Literal substitutions: |The name is ${name}|

### Conditional operators

1. If-then: (if) ? (then)
2. If-then-else: (if) ? (then) : (else)
3. Default: (value) ?: (defaultvalue)

### inlined expressions

[[...]] or [(...)] 


## Variable

#### web context

1. ${x} will return a variable x stored into the Thymeleaf context or as a request attribute.
2. ${param.x} will return a request parameter called x (which might be multivalued).
3. ${session.x} will return a session attribute called x .
4. ${application.x} will return a servlet context attribute called x .

#### OGNL 

```
example : 
${#locale.country}
```

1. ```#ctx``` : the context object.
2. ```#vars``` : the context variables.
3. ```#locale``` : the context locale.
4. ```#request``` : (only in Web Contexts) the HttpServletRequest object.
5. ```#response``` : (only in Web Contexts) the HttpServletResponse object.
6. ```#session``` : (only in Web Contexts) the HttpSession object.
7. ```#servletContext``` : (only in Web Contexts) the ServletContext object.

#### Utility Objects

```$xslt
#execInfo : information about the template being processed.
#messages : methods for obtaining externalized messages inside variables expressions, in the same way as they
would be obtained using #{…} syntax.
#uris : methods for escaping parts of URLs/URIs
#conversions : methods for executing the configured conversion service (if any).
#dates : methods for java.util.Date objects: formatting, component extraction, etc.
#calendars : analogous to #dates , but for java.util.Calendar objects.
#numbers : methods for formatting numeric objects.
#strings : methods for String objects: contains, startsWith, prepending/appending, etc.
#objects : methods for objects in general.
#bools : methods for boolean evaluation.
#arrays : methods for arrays.
#lists : methods for lists.
#sets : methods for sets.
#maps : methods for maps.
#aggregates : methods for creating aggregates on arrays or collections.
#ids : methods for dealing with id attributes that might be repeated (for example, as a result of an iteration).
```

Example:

```
<span th:text="${#lists.size(prod.comments)}">2</span>
```

## Layout

### xmlns

```
xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
```

### layout:decorate
在子模版中，指出要套用的母模版

### layout:fragment
1. 母模版：利用```layout:fragment```在母模版中加入一個可被取代的id。
2. 子模版：利用```layout:fragment```指出在母模版中要取代的內容。

### th:insert / th:replace

在母模版中，直接指定該區要用的子模版

```
母模版

<body>
<div th:insert="~{footer :: copy}"></div>
</body>


子模版
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div th:fragment="copy">
&copy; 2011 The Good Thymes Virtual Grocery
</div>
</body>
</html>
```

就是直接以th:insert要求加入```footer```裡的```copy``` fragment

而 ```th:insert```與```th:replace```的差異在於本身標註tag的留與不留

```
子模版
<footer th:fragment="copy">
&copy; 2011 The Good Thymes Virtual Grocery
</footer>

母模版使用th:insert與th:replace
<body>
<div th:insert="footer :: copy"></div> //會保留本身的div
<div th:replace="footer :: copy"></div> //本身的div會消失
</body>

結果如下
<body>

<div>
<footer>
&copy; 2011 The Good Thymes Virtual Grocery
</footer>
</div>

<footer>
&copy; 2011 The Good Thymes Virtual Grocery
</footer>

</body>
```

### 

### selector

## th

### xmlns

```
xmlns:th="http://www.thymeleaf.org"
```


### th:each Iteration
