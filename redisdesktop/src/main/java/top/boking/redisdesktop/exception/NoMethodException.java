package top.boking.redisdesktop.exception;

/**
 * @author shxl
 * @data 2022/6/1 18:13
 **/
public class NoMethodException extends Exception {
    String message;

    @Override
    public String getMessage() {
        return message;
    }

    public NoMethodException(String message, String message1) {
        super(message);
        this.message = message1;
    }
}
