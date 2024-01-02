package com.onlinestore.controlador;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }

}
