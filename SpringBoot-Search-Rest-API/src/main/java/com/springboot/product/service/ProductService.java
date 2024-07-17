package com.springboot.product.service;

import java.util.List;

import com.springboot.product.entity.Product;

public interface ProductService {
	
  List<Product> searchProducts(String query);

Product createProduct(Product product);
}
