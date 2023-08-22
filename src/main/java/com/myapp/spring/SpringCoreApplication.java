package com.myapp.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.myapp.spring.config.AppConfig;
import com.myapp.spring.dao.ProductDAO;

public class SpringCoreApplication {

	public static void main(String[] args) {
	AbstractApplicationContext iocContainer
	=new AnnotationConfigApplicationContext(AppConfig.class);
		
	ProductDAO dao =iocContainer.getBean(ProductDAO.class);
	
	ProductDAO dao1 =iocContainer.getBean(ProductDAO.class);
	System.out.println("dao==dao1"+(dao.equals(dao1)));
	
	dao.findAll().forEach(System.out::println);
	
	iocContainer.close();
	
	
	}

}
