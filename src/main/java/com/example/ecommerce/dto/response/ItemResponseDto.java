package com.example.ecommerce.dto.response;

import com.example.ecommerce.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {


    String itemName;

    int itemPrice;

    int quantityAdded;

    ProductCategory category;
}
