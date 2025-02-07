package weatherservice;

import netzklassen.Server;

import java.util.*;

import static weatherservice.WeatherProtocol.*;

public class WeatherServer extends Server {
    private final SerializationService<WeatherData> weatherSerialization;
    private final SerializationService<City> citySerialization;
    private final Map<String, ClientMessageHandler> handlerMap;
    private final Map<City, WeatherData> weatherDataMap;

    public WeatherServer(int pPort) {
        super(pPort);
        weatherSerialization = new WeatherDataSerializationService();
        citySerialization = new CitySerializationService();
        handlerMap = new HashMap<>();
        weatherDataMap = new HashMap<>();

        handlerMap.put(CLIENT_REQUEST_RANDOM, this::handleCRR);
        handlerMap.put(CLIENT_REQUEST_SPECIFIC, this::handleCRS);
        handlerMap.put(CLIENT_REQUEST_CITIES, this::handleCRC);

        for (String cityName : City.CITY_NAMES) {
            weatherDataMap.put(new City(cityName), new WeatherData());
        }
    }

    private void handleCRR(Server server, String ip, int port, String clientMsg) {
        Set<City> cities = weatherDataMap.keySet();
        City randomCity = null;
        int i = 0, random = (int) (Math.random() * (cities.size() - 1));
        for (City c : cities) {
            if (i == random) {
                randomCity = c;
                break;
            }
            i++;
        }
        String serializedWeather = weatherSerialization.serialize(weatherDataMap.get(randomCity));
        String serializedCity = citySerialization.serialize(randomCity);
        send(ip, port, buildMessage(SERVER_ANSWER_RANDOM, serializedCity, serializedWeather));
    }

    private void handleCRS(Server server, String ip, int port, String clientMsg) {
        String[] msgParts = clientMsg.split(SEPARATOR);

        City city = citySerialization.deserialize(msgParts[1]);
        if (!weatherDataMap.containsKey(city)) weatherDataMap.put(city, new WeatherData());

        String serializedWeather = weatherSerialization.serialize(weatherDataMap.get(city));
        send(ip, port, buildMessage(SERVER_ANSWER_SPECIFIC, serializedWeather));
    }

    private void handleCRC(Server server, String ip, int port, String clientMsg) {
        Set<City> cities = weatherDataMap.keySet();
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<City> cityIterator = cities.iterator();
        for (City city = cityIterator.next(); cityIterator.hasNext(); city = cityIterator.next()) {
            stringBuilder.append(citySerialization.serialize(city));
            if (cityIterator.hasNext()) stringBuilder.append(SEPARATOR);
        }
        send(ip, port, buildMessage(SERVER_ANSWER_CITIES, stringBuilder.toString()));
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
