package com.disys.analyzer.persistence;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.disys.analyzer.config.util.Constants;

@Configuration
@EnableTransactionManagement
public class Database {

	private static final String DATASOURCE = "classpath:application.properties";
	private static final Log log = LogFactory.getLog(Database.class);

	@Bean
	public DataSource dataSource(@Value(DATASOURCE) Resource ds)
			throws Exception {
		log.info("Loading DataSource.");
		Properties properties = new Properties();
		properties.load(ds.getInputStream());
		Properties dbProperties = new Properties();
		if(properties.getProperty("environment").equals(Constants.ENVIRONMENT_DEVELOPMENT)){
			dbProperties.setProperty("driverClassName",properties.getProperty("development.driverClassName"));
			dbProperties.setProperty("url",properties.getProperty("development.url"));
			dbProperties.setProperty("username",properties.getProperty("development.username"));
			dbProperties.setProperty("password",properties.getProperty("development.password"));
		}else{
			dbProperties.setProperty("driverClassName",properties.getProperty("production.driverClassName"));
			dbProperties.setProperty("url",properties.getProperty("production.url"));
			dbProperties.setProperty("username",properties.getProperty("production.username"));
			dbProperties.setProperty("password",properties.getProperty("production.password"));
		}
		
		return BasicDataSourceFactory.createDataSource(dbProperties);
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		log.info("Loading PersistenceExceptionTranslationPostProcessor.");
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
		log.info("Loading PersistenceAnnotationBeanPostProcessor.");
		return new PersistenceAnnotationBeanPostProcessor();
	}

}