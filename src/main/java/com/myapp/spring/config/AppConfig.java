package com.myapp.spring.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.myapp.spring.model.Product;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages="com.myapp.spring")

public class AppConfig {
	
@Bean("products1")
Map<Integer,Product> map(){
	Map<Integer,Product> map=new HashMap<>();
	map.put(1,new Product(1,"Iphone", 145654.4));
	map.put(4,new Product(4,"OnePlus11", 44564.4));
	map.put(2,new Product(2,"SamsungFlip", 74564.4));
	map.put(3,new Product(3,"Redmi", 34564.4));
	
	return map;
}

@Bean
DataSource dataSource() {
	HikariDataSource dataSource= new HikariDataSource();
	dataSource.setDriverClassName("org.postgresql.Driver");
	dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/javafsd");
	dataSource.setUsername("admin");
	dataSource.setPassword("password");
	dataSource.setMaximumPoolSize(10);
	dataSource.setAutoCommit(false);
	return dataSource;
}

@Bean
JdbcTemplate jdbcTemplate(DataSource datasource) {
	return new JdbcTemplate(datasource);
}

@Bean
DataSourceTransactionManager datatransactionManager(DataSource dataSource) {
	return new DataSourceTransactionManager(dataSource);
}







}
