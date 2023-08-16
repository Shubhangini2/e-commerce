package com.example.ecommerce.exception;

public class InvalidCardException extends RuntimeException{

    public InvalidCardException (String message){
        super(message);
    }
}
