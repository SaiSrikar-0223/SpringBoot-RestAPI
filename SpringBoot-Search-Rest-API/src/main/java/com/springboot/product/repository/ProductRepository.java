package com.springboot.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("SELECT p FROM  Product p  WHERE  "+
	                "p.name LIKE CONCAT( '%' , :query, '%') " +
			        "Or p.description LIKE CONCAT( '%',  :query , '%')")
	List<Product> searchProducts(String query);
	
	/*
	 * @Query(value="SELECT * FROM  products p WHERE  "+
	 * "p.name LIKE CONCAT( '%' , :query,  '%') "+
	 * "p.description LIKE CONACT( '%', :query,  '%')",nativeQuery = true)
	 * List<Product> searchProductsSQL(String query);
	 */
	
}
