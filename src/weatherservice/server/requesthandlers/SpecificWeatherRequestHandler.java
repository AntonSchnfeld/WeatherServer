package weatherservice.server.requesthandlers;

import weatherservice.data.City;
import weatherservice.data.SerializationService;
import weatherservice.data.WeatherData;
import weatherservice.server.*;

import static weatherservice.WeatherProtocol.SERVER_ANSWER_SPECIFIC;
import static weatherservice.WeatherProtocol.buildMessage;

public class SpecificWeatherRequestHandler
        implements WeatherRequestProcessor.WeatherRequestHandler {

    private final WeatherDataStore dataStore;
    private final SerializationService<City> citySerialization;
    private final SerializationService<WeatherData> weatherSerialization;
    private final MessageSender sender;

    public SpecificWeatherRequestHandler(WeatherDataStore dataStore,
                                         MessageSender sender,
                                         SerializationService<WeatherData> weatherSerialization,
                                         SerializationService<City> citySerialization) {
        this.dataStore = dataStore;
        this.citySerialization = citySerialization;
        this.weatherSerialization = weatherSerialization;
        this.sender = sender;
    }

    @Override
    public void handle(WeatherRequest request) {

        City city = citySerialization.deserialize(request.getMessageParts()[1]);

        if (!dataStore.containsCity(city))
            dataStore.updateWeatherData(city, new WeatherData());

        WeatherData weatherData = dataStore.getWeatherData(city);
        String serializedWeather = weatherSerialization.serialize(weatherData);
        sender.send(request.getIp(), request.getPort(), buildMessage(SERVER_ANSWER_SPECIFIC, serializedWeather));
    }
}
