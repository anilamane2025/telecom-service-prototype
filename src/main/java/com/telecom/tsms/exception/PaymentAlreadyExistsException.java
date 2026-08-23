package com.telecom.tsms.exception;

public class PaymentAlreadyExistsException extends RuntimeException{

    public PaymentAlreadyExistsException(String message){
        super(message);
    }

}
