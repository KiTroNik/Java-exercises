package model;

public class InvalidAmountException extends Exception {
    public InvalidAmountException(String errorMessage) {
        super(errorMessage);
    }
}
