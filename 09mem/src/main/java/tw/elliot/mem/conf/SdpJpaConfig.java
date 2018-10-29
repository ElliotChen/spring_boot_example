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
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
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
		repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class,
		entityManagerFactoryRef = "sdpEntityManagerFactory",
		transactionManagerRef = "sdpTransactionManager",
		basePackages = {"tw.elliot.mem.dao.sdp"}
)
@EntityScan(basePackages="tw.elliot.mem.domain.sdp")
public class SdpJpaConfig {
	@Primary
	@Bean(name = "sdpDataSource")
	@ConfigurationProperties(prefix = "sdp.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	/*
	@Primary
	@Bean(name="sdpDataSourceProperties")
	@ConfigurationProperties("sdp.datasource")
	public DataSourceProperties brDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "sdpDataSource")
	@ConfigurationProperties("sdp.datasource.hikari")
	public HikariDataSource brDataSource(@Qualifier("sdpDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}
	*/

	@Primary
	@Bean(name = "sdpEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean
	brEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("sdpDataSource") DataSource dataSource) {
		System.out.println("XXX");
		return builder
				.dataSource(dataSource)
				.packages("tw.elliot.mem.domain.sdp")
				.persistenceUnit("sdp")
				.build();
	}

	@Primary
	@Bean(name = "sdpTransactionManager")
	public PlatformTransactionManager brTransactionManager(@Qualifier("sdpEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
