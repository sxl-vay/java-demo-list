package top.boking.exception;

public class NoPropertiesException extends NullPointerException {
    public NoPropertiesException() {
    }
    public NoPropertiesException(String s){
        super(s);
    }
}
