package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ecommerce.Model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    public Seller findByEmailId(String email); //no implementation everything is done by jpa
}
