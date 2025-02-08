package weatherservice.server;

import netzklassen.Server;
import weatherservice.WeatherProtocol;
import weatherservice.data.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static weatherservice.WeatherProtocol.*;

public class WeatherServer extends Server {
    private final SerializationService<WeatherData> weatherSerialization;
    private final SerializationService<City> citySerialization;
    private final WeatherRequestProcessor requestProcessor;
    private final WeatherDataStore weatherDataStore;

    public WeatherServer(int port) {
        this(port, new WeatherDataSerializationService(), new CitySerializationService());
    }

    public WeatherServer(int port, SerializationService<WeatherData> weatherSerialization,
                         SerializationService<City> citySerialization) {
        super(port);
        this.weatherSerialization = weatherSerialization;
        this.citySerialization = citySerialization;
        requestProcessor = new WeatherRequestProcessor(this, citySerialization, weatherSerialization);
        weatherDataStore = new WeatherDataStore();
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        send(pClientIP, pClientPort, WeatherProtocol.SERVER_GREETING);
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        WeatherRequest request = WeatherRequestFactory
                .createWeatherRequest(pClientIP, pClientPort, pMessage);
        requestProcessor.processRequest(request);
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        close();
    }

    public WeatherDataStore getWeatherDataStore() {
        return weatherDataStore;
    }
}
