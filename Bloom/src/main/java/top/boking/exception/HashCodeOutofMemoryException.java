package top.boking.exception;

public class HashCodeOutofMemoryException extends RuntimeException {
    public HashCodeOutofMemoryException() {
    }

    public HashCodeOutofMemoryException(String s) {
        super(s);
    }
}
