package weatherservice.server.requesthandlers;

import weatherservice.data.City;
import weatherservice.data.SerializationService;
import weatherservice.data.WeatherData;
import weatherservice.server.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static weatherservice.WeatherProtocol.SERVER_ANSWER_RANDOM;
import static weatherservice.WeatherProtocol.buildMessage;

public class RandomWeatherRequestHandler
        implements WeatherRequestProcessor.WeatherRequestHandler {
    private final SerializationService<City> citySerialization;
    private final SerializationService<WeatherData> weatherSerialization;
    private final WeatherDataStore dataStore;
    private final MessageSender sender;

    public RandomWeatherRequestHandler(WeatherDataStore dataStore,
                                       MessageSender sender,
                                       SerializationService<WeatherData> weatherSerialization,
                                       SerializationService<City> citySerialization) {
        this.citySerialization = citySerialization;
        this.sender = sender;
        this.weatherSerialization = weatherSerialization;
        this.dataStore = dataStore;
    }

    @Override
    public void handle(WeatherRequest request) {

        List<City> cities = dataStore.getAllWeatherData()
                .keySet()
                .stream()
                .toList();

        City randomCity = cities.get(ThreadLocalRandom.current().nextInt(cities.size()));
        WeatherData weatherData = dataStore.getWeatherData(randomCity);

        String serializedWeather = weatherSerialization.serialize(weatherData);
        String serializedCity = citySerialization.serialize(randomCity);

        sender.send(request.getIp(), request.getPort(),
                buildMessage(SERVER_ANSWER_RANDOM, serializedCity, serializedWeather));
    }
}
