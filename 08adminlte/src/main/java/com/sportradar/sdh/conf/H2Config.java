package com.sportradar.sdh.conf;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/*
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "h2EntityManagerFactory",
		transactionManagerRef = "h2TransactionManager",
		basePackages = {"tw.elliot.adm.repo"}
)
*/
public class H2Config {
	@Bean(name = "h2DataSource")
	@ConfigurationProperties(prefix = "h2.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	/*
	@Bean(name="sdhDataSourceProperties")
	@ConfigurationProperties("sdp.datasource")
	public DataSourceProperties firstDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "sdhDataSource")
	@ConfigurationProperties("sdp.datasource.hikari")
	public HikariDataSource firstDataSource(@Qualifier("sdhDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}
	*/

	@Bean(name = "h2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean
	entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("h2DataSource") DataSource dataSource) {

		System.out.println("XXX");
		return builder
				.dataSource(dataSource)
				.packages("tw.elliot.adm.model")
				.persistenceUnit("h2")
				.build();
	}

	@Bean(name = "h2TransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("h2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
