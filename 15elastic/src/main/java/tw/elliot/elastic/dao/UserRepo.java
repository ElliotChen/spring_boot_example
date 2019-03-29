package tw.elliot.elastic.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import tw.elliot.elastic.domain.User;

import java.util.List;

@Repository
public interface UserRepo extends ElasticsearchRepository<User, String> {

	List<User> findByName(String name);

	List<User> findByTagsExists(String tag);
}
