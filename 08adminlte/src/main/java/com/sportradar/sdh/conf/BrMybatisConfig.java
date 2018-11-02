package com.sportradar.sdh.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.sportradar.sdh.dao.br", sqlSessionFactoryRef = "brSqlSessionFactory")
public class BrMybatisConfig {
	@Bean(name = "brDataSource")
	@ConfigurationProperties(prefix = "br.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "brTransactionManager")
	public DataSourceTransactionManager masterTransactionManager(@Qualifier("brDataSource") DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "brSqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("brDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/br/br-mybatis-config.xml"));
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/br/mapper/*.xml"));
		return bean.getObject();
	}
}
