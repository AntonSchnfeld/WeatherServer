package weatherservice.server;

import weatherservice.InvalidWeatherRequestException;
import weatherservice.data.City;
import weatherservice.data.SerializationService;
import weatherservice.data.WeatherData;
import weatherservice.server.requesthandlers.CitiesRequestHandler;
import weatherservice.server.requesthandlers.RandomWeatherRequestHandler;
import weatherservice.server.requesthandlers.SpecificWeatherRequestHandler;

import java.util.Map;
import static weatherservice.WeatherProtocol.*;

public class WeatherRequestProcessor {
    private final WeatherServer server;
    private final Map<String, WeatherRequestHandler> handlerMap;
    private final SerializationService<City> citySerialization;
    private final SerializationService<WeatherData> weatherSerialization;

    public WeatherRequestProcessor(WeatherServer server,
                                   SerializationService<City> citySerialization,
                                   SerializationService<WeatherData> weatherSerialization) {
        this.server = server;
        this.citySerialization = citySerialization;
        this.weatherSerialization = weatherSerialization;
        handlerMap = Map.of(
                CLIENT_REQUEST_SPECIFIC, new SpecificWeatherRequestHandler(),
                CLIENT_REQUEST_RANDOM, new RandomWeatherRequestHandler(),
                CLIENT_REQUEST_CITIES, new CitiesRequestHandler()
        );
    }

    public void processRequest(WeatherRequest request) {
        String command = request.getMessageParts()[0];
        WeatherRequestHandler requestHandler = handlerMap.get(command);

        if (requestHandler == null)
            throw new InvalidWeatherRequestException("Invalid message command: " + command);

        requestHandler.handle(server, request, citySerialization, weatherSerialization);
    }

    public interface WeatherRequestHandler {
        void handle(WeatherServer server, WeatherRequest request,
                    SerializationService<City> citySerialization,
                    SerializationService<WeatherData> weatherSerialization);
    }
}
