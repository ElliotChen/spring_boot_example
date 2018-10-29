package com.sportradar.sdh.conf;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class, basePackages = {"com.sportradar.sdh.dao.sdp"})
@EntityScan(basePackages="com.sportradar.sdp.domain.sdp")
*/
public class JpaConfig {

}
