package com.springboot.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.product.entity.Product;
import com.springboot.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> searchProducts(String query) {
		List<Product> products=productRepository.searchProducts(query);
		return products;
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

}
