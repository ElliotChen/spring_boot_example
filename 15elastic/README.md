# ElasticSearch

## Docker for test

```
docker run -d -p 9300:9300 -p 9200:9200 -e "discovery.type=single-node" -e "network.bind_host=0.0.0.0" docker.elastic.co/elasticsearch/elasticsearch:6.5.4
```


## API 
```
http://localhost:9200/_search
```

```
http://localhost:9200/_search?q=id:1545294974883
```

```
curl -XGET 'http://localhost:9200/world/_search?pretty=true' -H 'Content-Type: application/json' -d '{"query":{"match":{"name":"uts"}}}'
```
## Query

### Field
url/?q=name:search_name

### QueryDSL

#### Match 

自動分詞

```
{
    "match": {
        "name": "your_name"
    }
}
```

#### Term

不分詞

Sample


