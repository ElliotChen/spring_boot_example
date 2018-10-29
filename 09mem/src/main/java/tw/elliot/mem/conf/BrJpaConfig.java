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
		entityManagerFactoryRef = "brEntityManagerFactory",
		transactionManagerRef = "brTransactionManager",
		basePackages = {"tw.elliot.mem.dao.br"}
)
@EntityScan(basePackages="tw.elliot.mem.domain.br")
public class BrJpaConfig {

	@Bean(name = "brDataSource")
	@ConfigurationProperties(prefix = "br.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	/*
	@Bean(name="brDataSourceProperties")
	@ConfigurationProperties("br.datasource")
	public DataSourceProperties brDataSourceProperties() {
		return new DataSourceProperties();
	}


	@Bean(name = "brDataSource")
	@ConfigurationProperties("br.datasource.hikari")
	public HikariDataSource brDataSource(@Qualifier("brDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}
	*/


	@Bean(name = "brEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean
	brEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("brDataSource") DataSource dataSource) {
		System.out.println("XXX");
		return builder
				.dataSource(dataSource)
				.packages("tw.elliot.mem.domain.br")
				.persistenceUnit("br")
				.build();
	}


	@Bean(name = "brTransactionManager")
	public PlatformTransactionManager brTransactionManager(@Qualifier("brEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
