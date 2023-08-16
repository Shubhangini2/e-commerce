package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.ItemRequestDto;
import com.example.ecommerce.dto.response.CartResponseDto;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ecommerce.Model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.ecommerce.dto.response.OrderResponseDto;
import com.example.ecommerce.dto.request.CheckoutCartRequestDto;
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){
        //Create item

        try {
            //get the item
            Item item = itemService.createItem(itemRequestDto);
            //add the item itno the cart
            CartResponseDto cartResponseDto = cartService.addItemToCart(itemRequestDto,item);
            return new ResponseEntity(cartResponseDto, HttpStatus.CREATED);
        }

        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/checkout")
    public ResponseEntity checkoutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto){

        try{
            OrderResponseDto response = cartService.checkoutCart(checkoutCartRequestDto);
            return new ResponseEntity(response,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
