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
@Table(name="seller")
@Builder
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Column(unique = true, nullable = false)
    String emailId;

    @Column(unique = true)
    String panNo;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
   List<Product> products= new ArrayList<>(); //Already intialising as empty arraylist
}
