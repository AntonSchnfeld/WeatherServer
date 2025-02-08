package weatherservice;

import netzklassen.Server;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static weatherservice.WeatherProtocol.*;

public class WeatherServer extends Server {
    private final SerializationService<WeatherData> weatherSerialization;
    private final SerializationService<City> citySerialization;
    private final Map<String, ClientMessageHandler> handlerMap;
    private final WeatherDataStore weatherDataStore;

    public WeatherServer(int pPort) {
        super(pPort);
        weatherSerialization = new WeatherDataSerializationService();
        citySerialization = new CitySerializationService();
        handlerMap = new HashMap<>();
        weatherDataStore = new WeatherDataStore();

        handlerMap.put(CLIENT_REQUEST_RANDOM, this::handleCRR);
        handlerMap.put(CLIENT_REQUEST_SPECIFIC, this::handleCRS);
        handlerMap.put(CLIENT_REQUEST_CITIES, this::handleCRC);
    }

    private void handleCRR(Server server, String ip, int port, String clientMsg) {
        List<City> cities = weatherDataStore.getAllWeatherData()
                .keySet()
                .stream()
                .toList();

        City randomCity = cities.get(ThreadLocalRandom.current().nextInt(cities.size()));
        WeatherData weatherData = weatherDataStore.getWeatherData(randomCity);

        String serializedWeather = weatherSerialization.serialize(weatherData);
        String serializedCity = citySerialization.serialize(randomCity);
        send(ip, port, buildMessage(SERVER_ANSWER_RANDOM, serializedCity, serializedWeather));
    }

    private void handleCRS(Server server, String ip, int port, String clientMsg) {
        String[] msgParts = clientMsg.split(SEPARATOR);

        City city = citySerialization.deserialize(msgParts[1]);
        if (!weatherDataStore.containsCity(city))
            weatherDataStore.updateWeatherData(city, new WeatherData());

        WeatherData weatherData = weatherDataStore.getWeatherData(city);
        String serializedWeather = weatherSerialization.serialize(weatherData);
        send(ip, port, buildMessage(SERVER_ANSWER_SPECIFIC, serializedWeather));
    }

    private void handleCRC(Server server, String ip, int port, String clientMsg) {
        String citiesSerialized = weatherDataStore.getAllWeatherData().keySet().stream()
                .map(citySerialization::serialize)
                .collect(Collectors.joining(SEPARATOR));

        send(ip, port, buildMessage(SERVER_ANSWER_CITIES, citiesSerialized));
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        send(pClientIP, pClientPort, WeatherProtocol.SERVER_GREETING);
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        String command = pMessage.split(WeatherProtocol.SEPARATOR)[0];
        handlerMap.get(command).handle(this, pClientIP, pClientPort, pMessage);
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        close();
    }
}
