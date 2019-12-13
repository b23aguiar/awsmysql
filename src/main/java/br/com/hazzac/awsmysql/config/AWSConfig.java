package br.com.hazzac.awsmysql.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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

import br.com.hazzac.awsmysql.aws.entity.PessoaEntity;
import br.com.hazzac.awsmysql.aws.repository.PessoaRepository;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = PessoaRepository.class, entityManagerFactoryRef = "awsEntityManagerFactory", transactionManagerRef = "awsTransactionManager")
public class AWSConfig {
	
	@Primary
	@Bean
	@ConfigurationProperties("spring.datasource.aws")
	public DataSourceProperties awsProperties() {
		return new DataSourceProperties();
	}
	
	@Primary
	@Bean
	public DataSource awsDataSource(@Qualifier("awsProperties") DataSourceProperties awsProperties) {
		return awsProperties.initializeDataSourceBuilder().build();
	}
	
	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean awsEntityManagerFactory(@Qualifier("awsDataSource") DataSource awsDataSource, EntityManagerFactoryBuilder builder) {
		return builder.dataSource(awsDataSource).packages(PessoaEntity.class).build();
	}
	
	@Primary
	@Bean
	public PlatformTransactionManager awsTransactionManager(EntityManagerFactory awsTransactionManager) {
		return new JpaTransactionManager(awsTransactionManager);
	}

}