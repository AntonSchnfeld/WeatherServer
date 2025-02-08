package weatherservice.server;

import weatherservice.WeatherProtocol;

public class WeatherRequestFactory {

    public static WeatherRequest createWeatherRequest(String ip, int port, String message) {
        // Parse the message to split by the separator
        String[] messageParts = message.split(WeatherProtocol.SEPARATOR);

        // Return a new WeatherRequest with the parsed data
        return new WeatherRequest(ip, port, messageParts);
    }
}
