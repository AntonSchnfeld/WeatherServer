package weatherservice.server.requesthandlers;

import weatherservice.data.City;
import weatherservice.data.SerializationService;
import weatherservice.data.WeatherData;
import weatherservice.server.WeatherDataStore;
import weatherservice.server.WeatherRequest;
import weatherservice.server.WeatherRequestProcessor;
import weatherservice.server.WeatherServer;

import java.util.stream.Collectors;

import static weatherservice.WeatherProtocol.*;

public class CitiesRequestHandler
        implements WeatherRequestProcessor.WeatherRequestHandler {
    @Override
    public void handle(WeatherServer server, WeatherRequest request,
                       SerializationService<City> citySerialization,
                       SerializationService<WeatherData> weatherSerialization) {

        WeatherDataStore weatherDataStore = server.getWeatherDataStore();
        String citiesSerialized = weatherDataStore
                .getAllWeatherData()
                .keySet()
                .stream()
                .map(citySerialization::serialize)
                .collect(Collectors.joining(SEPARATOR));

        server.send(request.getIp(), request.getPort(),
                buildMessage(SERVER_ANSWER_CITIES, citiesSerialized));
    }
}
