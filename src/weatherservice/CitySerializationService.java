package weatherservice;

import org.json.JSONObject;

/**
 * Implementierung des {@link SerializationService} zur Serialisierung und Deserialisierung von {@link City}-Objekten im JSON-Format.
 */
public class CitySerializationService implements SerializationService<City> {

    /**
     * Erstellt eine neue Instanz des CitySerializationService.
     */
    public CitySerializationService() {
        // Kein spezieller Initialisierungscode erforderlich
    }

    /**
     * Serialisiert ein {@link City}-Objekt in eine JSON-String-Darstellung.
     *
     * @param city Das zu serialisierende City-Objekt.
     * @return Eine JSON-String-Darstellung der Stadt.
     */
    @Override
    public String serialize(City city) {
        JSONObject json = new JSONObject();
        json.put("name", city.getName());
        return json.toString();
    }

    /**
     * Deserialisiert eine JSON-String-Darstellung in ein {@link City}-Objekt.
     *
     * @param str Der JSON-String, der eine Stadt repräsentiert.
     * @return Das daraus erstellte City-Objekt.
     */
    @Override
    public City deserialize(String str) {
        JSONObject json = new JSONObject(str);
        return new City(json.getString("name"));
    }
}
