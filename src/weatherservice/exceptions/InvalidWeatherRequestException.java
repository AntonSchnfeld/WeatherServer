package weatherservice.exceptions;

public class InvalidWeatherRequestException extends RuntimeException {

    public InvalidWeatherRequestException(String msg) {
        super(msg);
    }
}
