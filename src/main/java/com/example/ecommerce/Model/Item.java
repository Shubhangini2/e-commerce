package com.example.ecommerce.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Table(name="item")
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int requiredQuantity; //Request DTO

    @ManyToOne
    @JoinColumn
    Cart cart; //Customer email or mobile through that we can get the cart

    @ManyToOne
    @JoinColumn
    OrderEntity orderEntity; //Not placing the order , only adding the order so null for ItemRequestDto

    @ManyToOne
    @JoinColumn
    Product product; //product id is needed to get the info about product
}
