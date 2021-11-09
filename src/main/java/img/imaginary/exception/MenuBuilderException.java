package img.imaginary.exception;

public class MenuBuilderException extends RuntimeException {

    public MenuBuilderException(String message) {
        super(message);
    }

    public MenuBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
