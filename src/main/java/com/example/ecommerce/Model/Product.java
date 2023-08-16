package com.example.ecommerce.Model;

import com.example.ecommerce.Enum.ProductCategory;
import com.example.ecommerce.Enum.ProductStatus;
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
@Table(name="product")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String productName; //DTO

    int price; //DTO

    int availableQuantity;  //DTO

    @Enumerated(EnumType.STRING)
    ProductCategory category;  //DTO

    @Enumerated(EnumType.STRING)
    ProductStatus status;     //No because by default it was  Available when it is added(set at backend)

    @ManyToOne
    @JoinColumn
    Seller seller;   //seller id is needed in DTO

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Item> items= new ArrayList<>(); //already initialise to empty arrayList
}
