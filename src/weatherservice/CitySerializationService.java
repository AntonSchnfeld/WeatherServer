package weatherservice;

import org.json.JSONObject;

public class CitySerializationService implements SerializationService<City> {

    public CitySerializationService() {

    }

    @Override
    public String serialize(City city) {
        JSONObject json = new JSONObject();
        json.put("name", city.getName());
        return json.toString();
    }

    @Override
    public City deserialize(String str) {
        JSONObject json = new JSONObject(str);
        return new City(json.getString("name"));
    }
}
