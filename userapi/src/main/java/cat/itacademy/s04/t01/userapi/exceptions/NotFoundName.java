package cat.itacademy.s04.t01.userapi.exceptions;

public class NotFoundName extends RuntimeException {
    public NotFoundName(String message) {
        super(message);
    }
}
