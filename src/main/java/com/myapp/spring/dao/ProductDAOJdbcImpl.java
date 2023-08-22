package com.myapp.spring.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.myapp.spring.model.Product;

@Repository("productDaoJdbc")
public class ProductDAOJdbcImpl implements ProductDAO {

	private JdbcTemplate jdbcTemplate;
	 private PlatformTransactionManager transactionManager;

	
	public ProductDAOJdbcImpl(JdbcTemplate jdbcTemplate,PlatformTransactionManager transactionManager) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.transactionManager=transactionManager;
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM products",BeanPropertyRowMapper.newInstance(Product.class));
	}

	@Override
	public List<Product> findName(String productName) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM products WHERE productName=?",BeanPropertyRowMapper.newInstance(Product.class),productName);
	}

	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		
		TransactionDefinition transactionDefinition=new DefaultTransactionDefinition();
		TransactionStatus transactionStatus=transactionManager.getTransaction(transactionDefinition);
		try {
		int count= jdbcTemplate.update("INSERT INTO products(productName,price) VALUES(?,?)",
			 new Object[] {product.getProductName(),product.getPrice()});
		transactionManager.commit(transactionStatus);
	if(count>0) {
		return product;
	}else
		return new Product(0,null,0.0);
	}catch(Exception ex) {
		transactionManager.rollback(transactionStatus);
	}
		return null;
	}

	@Override
	public void delete(Integer productId) {
		// TODO Auto-generated method stub
	
		TransactionDefinition transactionDefinition=new DefaultTransactionDefinition();
		TransactionStatus transactionStatus=transactionManager.getTransaction(transactionDefinition);
		try {
			jdbcTemplate.update("DELETE FROM PRODUCTS WHERE productId=?",productId);
		transactionManager.commit(transactionStatus);
	}catch(Exception ex) {
		transactionManager.rollback(transactionStatus);
	}
	}

}
