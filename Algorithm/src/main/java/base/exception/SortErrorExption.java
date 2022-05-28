package base.exception;

/**
 * @author shxl
 * @data 2022/5/28 12:52
 **/
public class SortErrorExption extends Exception {
    String message;

    public SortErrorExption(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
