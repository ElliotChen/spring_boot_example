#

## DB

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