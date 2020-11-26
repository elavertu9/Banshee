package dev.lavertu.banshee.exception.api;

public class EmailAddressAlreadyExistsException extends Exception {
    public EmailAddressAlreadyExistsException(String message) {
        super(message);
    }
}
