package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.CardRequestDto;
import com.example.ecommerce.dto.response.CartResponseDto;
import com.example.ecommerce.exception.CustomerNotFoundException;
import com.example.ecommerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.example.ecommerce.dto.response.CardResponseDto;
@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;
    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){

        try {
            CardResponseDto response= cardService.addCard(cardRequestDto);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch(CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST );
        }
    }
}
