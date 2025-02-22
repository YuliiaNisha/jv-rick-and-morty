package mate.academy.rickandmorty.exception;

public class HttpResponseException extends RuntimeException {
    public HttpResponseException(String message) {
        super(message);
    }
}
