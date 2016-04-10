package gk.potoo.exceptions;

public class AccountExistsException extends RuntimeException{

    public AccountExistsException(String s) {
        super(s);
    }
}
