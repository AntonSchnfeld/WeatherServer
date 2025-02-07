package weatherservice;

import org.json.JSONObject;
import java.util.Date;

public class WeatherDataSerializationService implements SerializationService<WeatherData> {

    public WeatherDataSerializationService() {}

    public String serialize(WeatherData weatherData) {
        JSONObject json = new JSONObject();
        json.put("date", weatherData.getDate().getTime());
        json.put("description", weatherData.getDescription());
        json.put("temperature", weatherData.getTemperature());
        json.put("amount", weatherData.getAmount());
        return json.toString();
    }

    public WeatherData deserialize(String str) {
        JSONObject json = new JSONObject(str);
        Date date = new Date(json.getLong("date"));
        String description = json.getString("description");
        int temperature = json.getInt("temperature");
        int amount = json.getInt("amount");

        return new WeatherData(date, description, temperature, amount);
    }
}
