package weatherservice.server;

import netzklassen.Server;
import weatherservice.WeatherProtocol;
import weatherservice.data.*;

public class WeatherServer extends Server implements MessageSender {
    private final WeatherRequestProcessor requestProcessor;

    public WeatherServer(int port) {
        this(port, new WeatherDataSerializationService(), new CitySerializationService());
    }

    public WeatherServer(int port, SerializationService<WeatherData> weatherSerialization,
                         SerializationService<City> citySerialization) {
        super(port);
        requestProcessor = new WeatherRequestProcessor(this, citySerialization, weatherSerialization);
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
}
