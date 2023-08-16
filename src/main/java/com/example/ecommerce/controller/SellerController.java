package com.example.ecommerce.controller;


import com.example.ecommerce.dto.request.SellerRequestDto;
import com.example.ecommerce.dto.response.SellerResponseDto;
import com.example.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){

        SellerResponseDto response= sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
