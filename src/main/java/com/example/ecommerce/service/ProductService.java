package com.example.ecommerce.service;

import com.example.ecommerce.Enum.ProductCategory;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.dto.request.ProductRequestDto;
import com.example.ecommerce.dto.response.ProductResponseDto;
import com.example.ecommerce.exception.SellerNotFoundException;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.SellerRepository;
import com.example.ecommerce.tranformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecommerce.Model.Seller;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {

        //Check sellerId is valid or not you can check it by
        //define the function in seller repository

        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmail());
        //if seller null throw seller not found exception

        if(seller==null){
            throw new SellerNotFoundException("Seller doesn't exist");
        }

        //if seller present DTo----> Entity
        Product product= ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
    //Seller is not in Dto so we should set it
        product.setSeller(seller);
        //In bidirectional Mapping in seller the product should also be chang
        seller.getProducts().add(product);

        //Just save the parent (seller) product will Automatically saved

        Seller savedSeller = sellerRepository.save(seller);

        List<Product> productList = savedSeller.getProducts();
        Product latestProduct = productList.get(productList.size()-1);

        //Prespare Response Dto

        return ProductTransformer.ProductToProductResponseDto(latestProduct);
    }

    public List<ProductResponseDto> getProductByCategoryAndPriceGraterThan(int price, ProductCategory category) {

        //if exact amount is given instead of greater thann we can use custom function findByCategoryAndPrice

        //Custom Query
        //List of eligible product
        List<Product> products= productRepository.getProductByCategoryAndPriceGraterThan(price,category);

        //Prepare List of response Dto
          List<ProductResponseDto> productResponseDtos= new ArrayList<>();
          for(Product product : products){
              productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
          }
        return productResponseDtos;
    }
}
