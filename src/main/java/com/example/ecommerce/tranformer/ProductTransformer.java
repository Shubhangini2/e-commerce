package com.example.ecommerce.tranformer;

import com.example.ecommerce.Enum.ProductStatus;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.dto.request.ProductRequestDto;
import com.example.ecommerce.dto.response.ProductResponseDto;

public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){

        return Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .availableQuantity(productRequestDto.getAvailableQuantity())
                .category(productRequestDto.getCategory())
                .status(ProductStatus.AVAILABLE) //Set it manually
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){

        return ProductResponseDto.builder()
                .sellerName(product.getSeller().getName())
                .productName(product.getProductName())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .productStatus(product.getStatus())
                .category(product.getCategory())
                .build();
    }
}
