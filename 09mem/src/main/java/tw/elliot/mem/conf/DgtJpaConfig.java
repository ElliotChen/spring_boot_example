package tw.elliot.mem.conf;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "dgtEntityManagerFactory",
		transactionManagerRef = "dgtTransactionManager",
		basePackages = {"tw.elliot.mem.dao.dgt"}
)
@EntityScan(basePackages="tw.elliot.mem.domain.dgt")
public class DgtJpaConfig {

	@Bean(name = "dgtDataSource")
	@ConfigurationProperties(prefix = "dgt.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	/*
	@Bean(name="dgtDataSourceProperties")
	@ConfigurationProperties("dgt.datasource")
	public DataSourceProperties brDataSourceProperties() {
		return new DataSourceProperties();
	}


	@Bean(name = "dgtDataSource")
	@ConfigurationProperties("dgt.datasource.hikari")
	public HikariDataSource brDataSource(@Qualifier("dgtDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}
	*/



	@Bean(name = "dgtEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean
	brEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("dgtDataSource") DataSource dataSource) {
		System.out.println("XXX");
		return builder
				.dataSource(dataSource)
				.packages("tw.elliot.mem.domain.dgt")
				.persistenceUnit("dgt")
				.build();
	}


	@Bean(name = "dgtTransactionManager")
	public PlatformTransactionManager brTransactionManager(@Qualifier("dgtEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
