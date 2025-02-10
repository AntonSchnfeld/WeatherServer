package weatherservice.server.requesthandlers;

import weatherservice.data.City;
import weatherservice.data.SerializationService;
import weatherservice.server.*;

import java.util.stream.Collectors;

import static weatherservice.WeatherProtocol.*;

public class CitiesRequestHandler
        implements WeatherRequestProcessor.WeatherRequestHandler {

    private final WeatherDataStore dataStore;
    private final SerializationService<City> citySerialization;
    private final MessageSender sender;

    public CitiesRequestHandler(WeatherDataStore dataStore,
                                MessageSender sender,
                                SerializationService<City> citySerialization) {
        this.dataStore = dataStore;
        this.sender = sender;
        this.citySerialization = citySerialization;
    }

    @Override
    public void handle(WeatherRequest request) {
        String citiesSerialized = dataStore
                .getAllWeatherData()
                .keySet()
                .stream()
                .map(citySerialization::serialize)
                .collect(Collectors.joining(SEPARATOR));

        sender.send(request.getIp(), request.getPort(),
                buildMessage(SERVER_ANSWER_CITIES, citiesSerialized));
    }
}
