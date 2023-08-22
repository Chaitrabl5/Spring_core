package com.myapp.spring.dao;
import java.util.List;

import com.myapp.spring.model.Product;
public interface ProductDAO {

	List<Product> findAll();
	
	List<Product> findName(String productName);
	
	
	Product create(Product product);

   void delete(Integer productId);


}
