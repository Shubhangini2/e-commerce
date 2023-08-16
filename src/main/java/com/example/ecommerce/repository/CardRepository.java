package com.example.ecommerce.repository;

import com.example.ecommerce.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {

    public Card findByCardNo(String cardNo);
}
