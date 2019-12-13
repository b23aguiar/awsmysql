package br.com.hazzac.awsmysql.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.hazzac.awsmysql.swa.entity.ProfissaoEntity;
import br.com.hazzac.awsmysql.swa.repository.ProfissaoRepository;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = ProfissaoRepository.class, entityManagerFactoryRef = "swaEntityManagerFactory", transactionManagerRef = "swaTransactionManager")
public class SWAConfig {
	
	@Bean
	@ConfigurationProperties("spring.datasource.swa")
	public DataSourceProperties swaProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	public DataSource swaDataSource(@Qualifier("swaProperties") DataSourceProperties swaProperties) {
		return swaProperties.initializeDataSourceBuilder().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean swaEntityManagerFactory(@Qualifier("swaDataSource") DataSource swaDataSource, EntityManagerFactoryBuilder builder) {
		return builder.dataSource(swaDataSource).packages(ProfissaoEntity.class).build();
	}
	
	@Bean
	public PlatformTransactionManager swaTransactionManager(EntityManagerFactory swaEntityManagerFactory) {
		return new JpaTransactionManager(swaEntityManagerFactory);
	}

}