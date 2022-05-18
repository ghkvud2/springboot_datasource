package com.example.multipledatasources.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.multipledatasources.entity.student.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	basePackages = "com.example.multipledatasources.repository.student",
	entityManagerFactoryRef = "studentEntityManager",
	transactionManagerRef = "studentTransactionManager"
)
public class StudentDataSourceConfig {

	// creates data-source properties bean with student database details

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.student")
	public DataSourceProperties studentDataSourceProperties() {
		return new DataSourceProperties();
	}

	// creates data-source bean

	@Bean
	@Primary
	public DataSource studentDataSource() {
		return studentDataSourceProperties().initializeDataSourceBuilder()
			.type(BasicDataSource.class).build();
	}

	// creates entity manager with scanned entity classes of student database
	@Bean(name = "studentEntityManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean studentEntityManager(
		EntityManagerFactoryBuilder builder) {
		return builder.dataSource(studentDataSource()).packages(Student.class)
			.build();
	}

	@Bean(name = "studentTransactionManager")
	@Primary
	public PlatformTransactionManager studentTransactionManager(
		@Qualifier("studentEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		return new JpaTransactionManager(entityManagerFactoryBean.getObject());
	}
}
