package weatherservice.server;

import weatherservice.exceptions.InvalidWeatherRequestException;
import weatherservice.data.City;
import weatherservice.data.SerializationService;
import weatherservice.data.WeatherData;
import weatherservice.server.requesthandlers.CitiesRequestHandler;
import weatherservice.server.requesthandlers.RandomWeatherRequestHandler;
import weatherservice.server.requesthandlers.SpecificWeatherRequestHandler;

import java.util.Map;
import static weatherservice.WeatherProtocol.*;

public class WeatherRequestProcessor {
    private final Map<String, WeatherRequestHandler> handlerMap;

    public WeatherRequestProcessor(MessageSender sender,
                                   SerializationService<City> citySerialization,
                                   SerializationService<WeatherData> weatherSerialization) {
        WeatherDataStore dataStore = new WeatherDataStore();
        handlerMap = Map.of(
                CLIENT_REQUEST_SPECIFIC,
                new SpecificWeatherRequestHandler(dataStore, sender, weatherSerialization, citySerialization),
                CLIENT_REQUEST_RANDOM,
                new RandomWeatherRequestHandler(dataStore, sender, weatherSerialization, citySerialization),
                CLIENT_REQUEST_CITIES,
                new CitiesRequestHandler(dataStore, sender, citySerialization)
        );
    }

    public void processRequest(WeatherRequest request) {
        String command = request.getMessageParts()[0];
        WeatherRequestHandler requestHandler = handlerMap.get(command);

        if (requestHandler == null)
            throw new InvalidWeatherRequestException("Invalid message command: " + command);

        requestHandler.handle(request);
    }

    public interface WeatherRequestHandler {
        void handle(WeatherRequest request);
    }
}
