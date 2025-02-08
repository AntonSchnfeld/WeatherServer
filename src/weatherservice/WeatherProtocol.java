package weatherservice;

/**
 * Definiert das Kommunikationsprotokoll zwischen Client und Server für den Wetterdienst.
 * Diese Klasse enthält vordefinierte Nachrichten-Tags und eine Methode zum Erstellen von Protokollnachrichten.
 */
public final class WeatherProtocol {

    /** Privater Konstruktor, um Instanziierung zu verhindern. */
    private WeatherProtocol() {}

    /** Server-Begrüßungsnachricht. */
    public static final String SERVER_GREETING = "SGC";

    /** Trennzeichen für Nachrichtenbestandteile. */
    public static final String SEPARATOR = ";";

    /** Client-Anfragen: zufällige Wetterdaten, spezifische Stadt, verfügbare Städte. */
    public static final String CLIENT_REQUEST_RANDOM = "CRR";
    public static final String CLIENT_REQUEST_SPECIFIC = "CRS";
    public static final String CLIENT_REQUEST_CITIES = "CRC";

    /** Server-Antworten: zufällige Wetterdaten, spezifische Stadt, verfügbare Städte. */
    public static final String SERVER_ANSWER_RANDOM = "SAR";
    public static final String SERVER_ANSWER_SPECIFIC = "SAS";
    public static final String SERVER_ANSWER_CITIES = "SAC";

    /**
     * Erstellt eine formatierte Protokollnachricht aus mehreren Teilen, getrennt durch das festgelegte Trennzeichen.
     *
     * @param parts Die einzelnen Bestandteile der Nachricht.
     * @return Eine formatierte Nachricht als String.
     */
    public static String buildMessage(String... parts) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            builder.append(parts[i]);
            if (i < parts.length - 1) builder.append(SEPARATOR);
        }
        return builder.toString();
    }
}
