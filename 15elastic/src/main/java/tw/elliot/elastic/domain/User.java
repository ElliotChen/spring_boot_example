package tw.elliot.elastic.domain;

import lombok.Data;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Data
@Document(indexName = "estw", type = "user")
public class User {
	@Id
	private String id;
	@Field
	private String name;

	private String[] tags;
}
