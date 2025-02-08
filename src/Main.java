import weatherservice.data.CitySerializationService;
import weatherservice.data.WeatherDataSerializationService;
import weatherservice.server.WeatherServer;

public class Main {
    public static void main(String[] args) {
        var weatherSerialization = new WeatherDataSerializationService();
        var citySerialization = new CitySerializationService();
        WeatherServer server = new WeatherServer(8080, weatherSerialization, citySerialization);
        WeatherClient client = new WeatherClient("localhost", 8080);
    }
}