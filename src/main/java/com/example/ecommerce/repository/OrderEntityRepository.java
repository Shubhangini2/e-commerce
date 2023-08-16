package com.example.ecommerce.repository;

import com.example.ecommerce.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer>{

}
