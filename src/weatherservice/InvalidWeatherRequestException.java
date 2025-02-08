package weatherservice;

public class InvalidWeatherRequestException extends RuntimeException {
    public InvalidWeatherRequestException() {
        super();
    }

    public InvalidWeatherRequestException(String msg) {
        super(msg);
    }
}
