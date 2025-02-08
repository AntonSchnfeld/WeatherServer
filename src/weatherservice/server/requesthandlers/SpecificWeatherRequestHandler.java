package weatherservice.server.requesthandlers;

import weatherservice.data.City;
import weatherservice.data.SerializationService;
import weatherservice.data.WeatherData;
import weatherservice.server.WeatherDataStore;
import weatherservice.server.WeatherRequest;
import weatherservice.server.WeatherRequestProcessor;
import weatherservice.server.WeatherServer;

import static weatherservice.WeatherProtocol.SERVER_ANSWER_SPECIFIC;
import static weatherservice.WeatherProtocol.buildMessage;

public class SpecificWeatherRequestHandler
        implements WeatherRequestProcessor.WeatherRequestHandler {

    @Override
    public void handle(WeatherServer server, WeatherRequest request,
                       SerializationService<City> citySerialization,
                       SerializationService<WeatherData> weatherSerialization) {

        City city = citySerialization.deserialize(request.getMessageParts()[1]);
        WeatherDataStore weatherDataStore = server.getWeatherDataStore();

        if (!weatherDataStore.containsCity(city))
            weatherDataStore.updateWeatherData(city, new WeatherData());

        WeatherData weatherData = weatherDataStore.getWeatherData(city);
        String serializedWeather = weatherSerialization.serialize(weatherData);
        server.send(request.getIp(), request.getPort(), buildMessage(SERVER_ANSWER_SPECIFIC, serializedWeather));
    }
}
