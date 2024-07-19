package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;


@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod() {
        // Create a Product object
        Product product = new Product();
        product.setName("product 1");
        product.setDescription("product 1 description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageurl("product1.png");

        //save Product
        Product savedProduct = productRepository.save(product);

        //display product info
        System.out.println(savedProduct.getId());
        System.out.println(savedProduct.toString());
    }
        @Test
        void updateUsingSaveMethod(){

         // find or retrieve an entity by id
         Long id=1L;
         Product product=productRepository.findById(id).get();

        //update entity information
            product.setName("updated product 1");
            product.setDescription("updated product 1 desc");

        //save updated entity into database table
            productRepository.save(product);
    }

    @Test
    void findByMethod(){
        Long id=1L;
        Product product=productRepository.findById(id).get();
        //System.out.println(product.toString());
    }
    @Test
    void saveAllMethod(){
        // Create a Product object
        Product product = new Product();
        product.setName("product 2");
        product.setDescription("product 2 description");
        product.setSku("100ABCD");
        product.setPrice(new BigDecimal(200));
        product.setActive(true);
        product.setImageurl("product2.png");

        // Create a Product object
        Product product3 = new Product();
        product3.setName("product 3");
        product3.setDescription("product 3 description");
        product3.setSku("100ABCDE");
        product3.setPrice(new BigDecimal(300));
        product3.setActive(true);
        product3.setImageurl("product3.png");

        //saveAll Products
        productRepository.saveAll(List.of(product,product3));
    }

    @Test
    void findAllMethod(){

        List<Product> products=productRepository.findAll();
        products.forEach(product -> {
            System.out.println(product.getName());
        });
    }

    @Test
    void deleteByIdMethod(){
        Long id=1L;
        productRepository.deleteById(id);
    }

    @Test
    void deletedMethod(){
        //find an entity by Id
        Long id=2L;
        Product product=productRepository.findById(id).get();

        //delete an entity
        productRepository.delete(product);
    }
    @Test
    void deleteAllmethod(){

        //productRepository.deleteAll();
        Product product=productRepository.findById(102L).get();

        Product product1=productRepository.findById(103L).get();

        productRepository.deleteAll(List.of(product,product1));
    }
    @Test
    void countMethod(){
        Long count=productRepository.count();
        System.out.println(count);
    }

    @Test
    void existsByIdMethod(){
     Long id =203l;
     boolean result=productRepository.existsById(id);
        System.out.println(result);
    }
}