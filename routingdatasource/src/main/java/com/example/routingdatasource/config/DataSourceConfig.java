package com.example.routingdatasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.routingdatasource.constant.BranchEnum;
import com.example.routingdatasource.entity.Employee;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	basePackages = "com.example.routingdatasource.repository",
	entityManagerFactoryRef = "entityManager",
	transactionManagerRef = "transcationManager"
)
public class DataSourceConfig {

	@Bean
	@Primary
	public DataSource dataSource() {
		DataSourceRouting dataSourceRouting = new DataSourceRouting();
		dataSourceRouting.setTargetDataSources(targetDataSources());
		dataSourceRouting.setDefaultTargetDataSource(koreaDataSource());
		// dataSourceRouting.setDefaultTargetDataSource(japanDataSource());
		return dataSourceRouting;
	}

	private Map<Object, Object> targetDataSources() {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(BranchEnum.KOREA, koreaDataSource());
		targetDataSources.put(BranchEnum.JAPAN, japanDataSource());
		return targetDataSources;
	}

	@Bean
	@ConfigurationProperties("datasource.korea")
	public DataSourceProperties koreaDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource koreaDataSource() {
		return koreaDataSourceProperties()
			.initializeDataSourceBuilder()
			.type(HikariDataSource.class)
			.build();
	}

	@Bean
	@ConfigurationProperties("datasource.japan")
	public DataSourceProperties japanDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource japanDataSource() {
		return japanDataSourceProperties()
			.initializeDataSourceBuilder()
			.type(HikariDataSource.class)
			.build();
	}

	@Bean(name = "entityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
		EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource()).packages(Employee.class)
			.build();
	}

	@Bean(name = "transcationManager")
	public JpaTransactionManager transactionManager(
		@Autowired @Qualifier("entityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		return new JpaTransactionManager(entityManagerFactoryBean.getObject());
	}

}
