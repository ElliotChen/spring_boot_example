#

## 基本命名原则

1. 除Domain Model以package分别外，其馀Dao, Service皆在Class前加入SDP, GDT, BR等识别字做为区分。

## DB Alter

### BR
```$xslt
ALTER TABLE `League`
ADD COLUMN `leagueIdXRef` bigint(20) DEFAULT '-1';


ALTER TABLE `Market`
ADD COLUMN `marketIdXRef` varchar(32) DEFAULT '-1 -1';

ALTER TABLE `MarketOption`
ADD COLUMN `marketOptionIdXRef` varchar(32) DEFAULT '-1 -1';
```


### SDP
```$xslt
ALTER TABLE `MarketOption`
ADD COLUMN `marketOptionIdXRefs` varchar(64) DEFAULT '-1 -1|-1 -1 -1 -1';
```


### DGT
```$xslt
ALTER TABLE `MarketOption`
ADD COLUMN `marketOptionIdXRef` varchar(32) DEFAULT '-1 -1';
```

## DB Configuration

### conf

因需要多个Mybatis设定，所以需关闭Spring对Mybatis的Autoload

在Application.java中，加入以下Annotation
```
@SpringBootApplication(exclude = {MybatisAutoConfiguration.class, DataSourceAutoConfiguration.class})
```
并分别在下列java里设定各Mybatis需要的DataSource, SqlSessionFactory

1. SdpMybatisConfig.java (Primary)
2. DgtMybatisConfig.java
3. BrMybatisConfig.java

## Security

### SecurityConfig.java

1. 使用InMemoryUserDetailsManager提供登入帐密
2. "/bootstrap/**", "/dist/**", "/plugins/**"等不检查权限

### csrf

1. 若无_csrf token会显示```403```错误

2. 在layout.html中加入```meta```

```
<meta id="_csrf" name="_csrf" th:content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" th:content="${_csrf.headerName}"/>
```
并在其中加入

```
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e,xhr,options) {
	xhr.setRequestHeader(header, token);
});
```

故ajax的程式能保有csrf防护

3. 在其他的form里面，则需加入

```
<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
```

做为检核。

### 登入资料

除Spring内建```SecurityContextHolder```外，另加入```ctrl/GeneralCtrlAdvice.java```

其中```addGeneralModel```会在Model中加入User资料，让页面可以透过```${user}```取得登入者资讯。


### Pair, Data and Language

请参考DTO

#### Sport

SDP:    Sport
DGT:    Sport
BR:     Sport

Language: Sport_I18N

#### Region

SDP:    Region
DGT:    RegionSport
BR:     RegionSport

Language: Region_I18N

#### League

SDP:    League
DGT:    League
BR:     League

Language: League_I18N

#### Market

SDP:    Market
DGT:    SportMarket
BR:     Market

Language: Market_I18N

#### MarketOption

SDP:    MarketOption
DGT:    MarketOption
BR:     MarketOption

Language: MarketOption_I18N

### Domain

其本都是extend自```domain.commmon```,利用```IdCompositable```来取得或解析复合的id值。

因Bryan要求，所以Domain Model里求极简，就将转换的工作交给```TypeHandler```及```Dto```。

```dao.sdp.handler```里提供handler将```dgt_refId|br_refId```转换为对应的Object并加入List
```dto```里再依```SourceTypeEnum```将其分别。


