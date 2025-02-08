import netzklassen.Client;
import weatherservice.WeatherProtocol;
import weatherservice.data.*;

/**
 * Ein Client zur Kommunikation mit einem Wetterserver, der Wetterdaten und Städteinformationen anfordert.
 */
public class WeatherClient extends Client {

    /** Serialisierungsservice für Wetterdaten. */
    private final SerializationService<WeatherData> weatherSerialization;

    /** Serialisierungsservice für Städte. */
    private final SerializationService<City> citySerialization;

    /**
     * Erstellt einen neuen Wetter-Client und verbindet sich mit dem Server.
     *
     * @param pServerIP   Die IP-Adresse des Wetterservers.
     * @param pServerPort Der Port des Wetterservers.
     */
    public WeatherClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
        weatherSerialization = new WeatherDataSerializationService();
        citySerialization = new CitySerializationService();
    }

    /**
     * Verarbeitet eingehende Nachrichten vom Server und führt verschiedene Anfragen aus:
     * <ol>
     *     <li>Fordert zufälliges Wetter an, wandelt die Daten in Objekte um und gibt sie aus.</li>
     *     <li>Fordert das Wetter in Dortmund an, wandelt es um und gibt es aus.</li>
     *     <li>Fordert alle verfügbaren Städte an, speichert sie in einer Liste und gibt sie aus.</li>
     *     <li>Beendet die Verbindung mit dem Server.</li>
     * </ol>
     *
     * Tipp: Die Methoden {@link SerializationService#deserialize(String)} und
     * {@link SerializationService#serialize(Object)} helfen bei der Umwandlung von JSON-Daten.
     * Die Methode {@link WeatherProtocol#buildMessage(String...)} erleichtert das Erstellen von Nachrichten.
     *
     * @param pMessage Die empfangene Nachricht vom Server.
     */
    @Override
    public void processMessage(String pMessage) {
        // TODO: Implementieren der beschriebenen Schritte
    }
}
