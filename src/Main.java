import weatherservice.*;

public class Main {
    public static void main(String[] args) {
        WeatherServer server = new WeatherServer(8080);
        WeatherClient client = new WeatherClient("localhost", 8080);
    }
}