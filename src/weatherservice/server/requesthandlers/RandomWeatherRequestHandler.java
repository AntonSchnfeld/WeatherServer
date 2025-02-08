package weatherservice.server.requesthandlers;

import weatherservice.data.City;
import weatherservice.data.SerializationService;
import weatherservice.data.WeatherData;
import weatherservice.server.WeatherDataStore;
import weatherservice.server.WeatherRequest;
import weatherservice.server.WeatherRequestProcessor;
import weatherservice.server.WeatherServer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static weatherservice.WeatherProtocol.SERVER_ANSWER_RANDOM;
import static weatherservice.WeatherProtocol.buildMessage;

public class RandomWeatherRequestHandler
        implements WeatherRequestProcessor.WeatherRequestHandler {
    @Override
    public void handle(WeatherServer server, WeatherRequest request,
                       SerializationService<City> citySerialization,
                       SerializationService<WeatherData> weatherSerialization) {

        WeatherDataStore weatherDataStore = new WeatherDataStore();
        List<City> cities = weatherDataStore.getAllWeatherData()
                .keySet()
                .stream()
                .toList();

        City randomCity = cities.get(ThreadLocalRandom.current().nextInt(cities.size()));
        WeatherData weatherData = weatherDataStore.getWeatherData(randomCity);

        String serializedWeather = weatherSerialization.serialize(weatherData);
        String serializedCity = citySerialization.serialize(randomCity);

        server.send(request.getIp(), request.getPort(),
                buildMessage(SERVER_ANSWER_RANDOM, serializedCity, serializedWeather));
    }
}
