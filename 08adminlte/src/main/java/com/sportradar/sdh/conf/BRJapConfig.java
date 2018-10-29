package com.sportradar.sdh.conf;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
/*
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "brEntityManagerFactory",
		transactionManagerRef = "brTransactionManager",
		basePackages = {"com.sportradar.sdh.dao.br"}
)
@EntityScan(basePackages="com.sportradar.sdp.domain.br")
*/
public class BRJapConfig {
	/*
	@Bean(name = "brDataSource")
	@ConfigurationProperties(prefix = "br.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
*/

	@Primary
	@Bean(name="brDataSourceProperties")
	@ConfigurationProperties("br.datasource")
	public DataSourceProperties brDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "brDataSource")
	@ConfigurationProperties("br.datasource.hikari")
	public HikariDataSource brDataSource(@Qualifier("brDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}


	@Primary
	@Bean(name = "brEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean
	brEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("brDataSource") DataSource dataSource) {
		System.out.println("XXX");
		return builder
				.dataSource(dataSource)
				.packages("com.sportradar.sdp.domain.br")
				.persistenceUnit("br")
				.build();
	}

	@Primary
	@Bean(name = "brTransactionManager")
	public PlatformTransactionManager brTransactionManager(@Qualifier("brEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
