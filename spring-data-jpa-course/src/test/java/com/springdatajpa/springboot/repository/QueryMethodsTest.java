package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
public class QueryMethodsTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void findByNameMethod(){
        Product product=productRepository.findByName("product 2");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByIdMethod(){
        Product product=productRepository.findById(1L).get();
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByNameOrDescriptionMethod(){
    List<Product> productList=productRepository.findByNameOrDescription(
                     "product 1","product 1 description");

    productList.forEach(product -> {
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    });
   }

    @Test
    void findByNameAndDescriptionMethod(){
        List<Product> productList=productRepository.findByNameAndDescription(
                "product 1","product 1 description");

        productList.forEach(product -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }
    @Test
    void findDistinctByNameMethod(){
        Product product=productRepository.findDistinctByName("product 1");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findPriceGreaterThanMethod(){
     List<Product> productList=productRepository.findByPriceGreaterThan(new BigDecimal(100));

        productList.forEach(product -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }

    @Test
    void findPriceLessThanMethod(){
        List<Product> productList=productRepository.findByPriceLessThan(new BigDecimal(300));

        productList.forEach(product -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }

    @Test
    void findByNameContainingMethod(){
        List<Product> productList=productRepository.findByNameContaining("product 1");

        productList.forEach(product -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }

    @Test
    void findByNameLikeMethod(){
        List<Product> productList=productRepository.findByNameLike("product 1");

        productList.forEach(product -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }

    @Test
    void findByPriceBetweenMethod(){
        List<Product> productList=productRepository.findByPriceBetween(new BigDecimal(100),
                new BigDecimal(300));
        productList.forEach(product -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }

    @Test
    void findByDateCreatedBetweenMethod(){
        LocalDateTime startDate=LocalDateTime.of(2024,07,19,
                11,8,37);

        LocalDateTime endtDate=LocalDateTime.of(2024,07,19,
                11,36,11);

        List<Product> productList=productRepository.findByDateCreatedBetween(startDate,endtDate);

        productList.forEach(product -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });

    }

    @Test
    void findByNameInMethod() {

        List<Product> productList = productRepository.findByNameIn(List.of("product 1",
                "product 2", "product 3"));

        productList.forEach(product -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }

    @Test
    void findFirst2ByOrderByNameAscMethod(){
        List<Product> productList = productRepository.findFirst2ByOrderByNameAsc();
        productList.forEach(product -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }

    @Test
    void findTop2ByOrderByPriceDescMethod(){
        List<Product> productList = productRepository.findTop2ByOrderByPriceDesc();
        productList.forEach(product -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }
}
