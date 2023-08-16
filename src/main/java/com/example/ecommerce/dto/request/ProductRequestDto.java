package com.example.ecommerce.dto.request;

import com.example.ecommerce.Enum.ProductCategory;
import com.example.ecommerce.Model.Seller;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor

public class ProductRequestDto {

    String sellerEmail;

    String productName;

    int price;

    int availableQuantity;

    ProductCategory category;

}
