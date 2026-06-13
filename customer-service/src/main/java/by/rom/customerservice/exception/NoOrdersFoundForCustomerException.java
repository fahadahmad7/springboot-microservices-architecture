package by.rom.customerservice.exception;

public class NoOrdersFoundForCustomerException
        extends RuntimeException {

    public NoOrdersFoundForCustomerException(String message) {
        super(message);
    }
}