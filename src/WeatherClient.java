import netzklassen.Client;
import netzklassen.List;
import weatherservice.*;
import static weatherservice.WeatherProtocol.*;

public class WeatherClient extends Client {

    private final SerializationService<WeatherData> weatherSerialization;
    private final SerializationService<City> citySerialization;

    public WeatherClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
        weatherSerialization = new WeatherDataSerializationService();
        citySerialization = new CitySerializationService();
    }

    @Override
    public void processMessage(String pMessage) {
        // TODO: Implementationsaufgabe
        // 1. Zufälliges Wetter anfordern und sowohl Wetter als auch Stadt
        // in WeatherData und City-Objekte umwandeln, diese dann ausgeben
        // 2. Wetter in Dortmund anfordern und in ein WeatherData-Objekt
        // umwandeln, dieses ausgeben
        // 3. Alle Städte anfordern und diese alle in eine List<City> speichern,
        // dann alle Einträge ausgeben
        // 4. Die Verbindung mit dem Server beenden

        // Tipp: Benutze weatherSerialization,
        // um den Wetterstring des Servers in
        // ein WeatherData-Objekt umzuwandeln.
        // Genauso kannst du citySerialization benutzen,
        // um Stadtstrings in City-Objekte umzuwandeln.
        // Außerdem kannst du die Methode buildMessage benutzen,
        // um automatisch den SEPARATOR zwischen die einzelnen
        // Teile deiner Nachricht zu stecken
        // Beispiele:
        // City city = citySerialization.deserialize(messagePart[i]);
        // String cityString = citySerialization.serialize(city);
        // WeatherData weatherData = weatherSerialization.deserialize(messagePart[i]);
        // String weatherString = weatherSerialization.serialize(weatherData);
        // String myCoolMsg = buildMessage(CLIENT_REQUEST_SPECIFIC, cityString);

        String[] msgParts = pMessage.split(SEPARATOR);
        switch (msgParts[0]) {
            case SERVER_GREETING -> send(CLIENT_REQUEST_RANDOM);
            case SERVER_ANSWER_RANDOM -> {
                System.out.println("Aufgabe 1");
                City city = citySerialization.deserialize(msgParts[1]);
                WeatherData weatherData = weatherSerialization.deserialize(msgParts[2]);
                System.out.println(city);
                System.out.println(weatherData);

                String dortmund = citySerialization.serialize(new City("Dortmund"));
                send(buildMessage(CLIENT_REQUEST_SPECIFIC, dortmund));
            }
            case SERVER_ANSWER_SPECIFIC -> {
                System.out.println("Aufgabe 2");
                WeatherData weatherData = weatherSerialization.deserialize(msgParts[1]);
                System.out.println(weatherData);

                send(CLIENT_REQUEST_CITIES);
            }
            case SERVER_ANSWER_CITIES -> {
                System.out.println("Aufgabe 3");
                List<City> cities = new List<>();
                for (int i = 1; i < msgParts.length; i++) {
                    City city = citySerialization.deserialize(msgParts[i]);
                    cities.append(city);
                    System.out.println(city);
                }

                close();
            }
        }
    }
}
