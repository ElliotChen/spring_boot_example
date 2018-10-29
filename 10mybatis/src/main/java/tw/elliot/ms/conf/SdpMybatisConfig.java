package tw.elliot.ms.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "tw.elliot.ms.dao.sdp", sqlSessionFactoryRef = "sdpSqlSessionFactory")
public class SdpMybatisConfig {
	@Primary
	@Bean(name = "sdpDataSource")
	@ConfigurationProperties(prefix = "sdp.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "sdpTransactionManager")
	public DataSourceTransactionManager masterTransactionManager(@Qualifier("sdpDataSource") DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "sdpSqlSessionFactory")
	@Primary
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("sdpDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/sdp/sdp-mybatis-config.xml"));
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/sdp/mapper/*.xml"));
		return bean.getObject();
	}
}
