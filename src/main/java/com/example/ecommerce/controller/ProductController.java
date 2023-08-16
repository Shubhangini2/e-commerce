package com.example.ecommerce.controller;

import com.example.ecommerce.Enum.ProductCategory;
import com.example.ecommerce.dto.request.ProductRequestDto;
import com.example.ecommerce.dto.response.ProductResponseDto;
import com.example.ecommerce.exception.SellerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.ecommerce.service.ProductService;
import java.util.List;
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    //ADD PRODUCT
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto) {

        //Not Found exception
        try {
            ProductResponseDto response = productService.addProduct(productRequestDto);
             return new ResponseEntity(response,HttpStatus.CREATED);
        }
        catch (SellerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //GET ALL THE PRODUCT OF A SPORTS CATEGORY AND A PRICE GETTER THAN 2000

    @GetMapping("/get_by_category_and_price_than")
    public ResponseEntity getProductByCategoryAndPriceGraterThan(@RequestParam ("price") int price ,
                                                                 @RequestParam("category")ProductCategory category){

         List<ProductResponseDto> productResponseDtoList = productService.getProductByCategoryAndPriceGraterThan(price,category);
         return  new ResponseEntity(productResponseDtoList,HttpStatus.FOUND);
    }

}