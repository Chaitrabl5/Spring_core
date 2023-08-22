package com.myapp.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.myapp.spring.config.AppConfig;
import com.myapp.spring.dao.ProductDAO;
import com.myapp.spring.model.Product;

public class SpringCoreApplicationJdbcTemplate {

	
	public static void main(String[] args) {
		AbstractApplicationContext iocContainer
		=new AnnotationConfigApplicationContext(AppConfig.class);
			
		ProductDAO dao =iocContainer.getBean("productDaoJdbc",ProductDAO.class);
		Product product=new Product();
		product.setProductName("redmi");
		product.setPrice(35456.5);
		dao.create(product);
		
		//dao.delete(1);
		
		dao.findAll().forEach(System.out::println);
		
		iocContainer.close();
		
		
		}

}
