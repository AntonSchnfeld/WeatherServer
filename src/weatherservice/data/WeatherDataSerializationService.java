package weatherservice.data;

import org.json.JSONObject;

import java.util.Date;

/**
 * Implementierung des {@link SerializationService} zur Serialisierung und Deserialisierung von {@link WeatherData}-Objekten im JSON-Format.
 */
public class WeatherDataSerializationService implements SerializationService<WeatherData> {

    /**
     * Erstellt eine neue Instanz des WeatherDataSerializationService.
     */
    public WeatherDataSerializationService() {}

    /**
     * Serialisiert ein {@link WeatherData}-Objekt in eine JSON-String-Darstellung.
     *
     * @param weatherData Das zu serialisierende WeatherData-Objekt.
     * @return Eine JSON-String-Darstellung der Wetterdaten.
     */
    public String serialize(WeatherData weatherData) {
        JSONObject json = new JSONObject();
        json.put("date", weatherData.getDate().getTime());
        json.put("description", weatherData.getDescription());
        json.put("temperature", weatherData.getTemperature());
        json.put("amount", weatherData.getAmount());
        return json.toString();
    }

    /**
     * Deserialisiert eine JSON-String-Darstellung in ein {@link WeatherData}-Objekt.
     *
     * @param str Der JSON-String, der Wetterdaten repräsentiert.
     * @return Das daraus erstellte WeatherData-Objekt.
     */
    public WeatherData deserialize(String str) {
        JSONObject json = new JSONObject(str);
        Date date = new Date(json.getLong("date"));
        String description = json.getString("description");
        int temperature = json.getInt("temperature");
        int amount = json.getInt("amount");

        return new WeatherData(date, description, temperature, amount);
    }
}
