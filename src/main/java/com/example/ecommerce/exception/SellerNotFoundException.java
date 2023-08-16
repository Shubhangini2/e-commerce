package com.example.ecommerce.exception;

public class SellerNotFoundException extends RuntimeException{

    public SellerNotFoundException(String message){

        super(message);
    }

}
