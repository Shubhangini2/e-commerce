package com.example.ecommerce.repository;

import com.example.ecommerce.Enum.ProductCategory;
import com.example.ecommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


    //CUSTOM QUERY
    //JPA QUERY SYNTAX
    //Use the variables
    @Query("select p from Product p where p.price >= :price and p.category = :category")

    public List<Product> getProductByCategoryAndPriceGraterThan(int price, ProductCategory category);

}
