package com.example.ecommerce.exception;

public class InsufficientQuantityException extends RuntimeException{

    public InsufficientQuantityException(String message){
        super(message);
    }
}
