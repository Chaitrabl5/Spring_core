package com.myapp.spring.dao;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.myapp.spring.model.Product;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
@Repository
@Scope("singleton")
public class ProductDAOImpl implements ProductDAO {


	private Map<Integer,Product> products;
	
	@PostConstruct
	public void initialize(){
		System.out.println("Post Construct");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("Pre Destroy");
	}
	
	public ProductDAOImpl(@Qualifier ("products1")Map<Integer, Product> products) {
		super();
		this.products = products;
	}

   


	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		
		return products.values().stream().collect(Collectors.toList());
	}




	@Override
	public List<Product> findName(String productName) {
		// TODO Auto-generated method stub
		return products.values().stream()
				.filter(p->p.getProductName().equalsIgnoreCase(productName))
				.collect(Collectors.toList());
	}




	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		return products.putIfAbsent(product.getProductId(), product);
	}




	@Override
	public void delete(Integer productId) {
		products.remove(productId);
	}

}
