package weatherservice;

import java.util.Map;
import java.util.HashMap;

public class WeatherDataStore {
    private final Map<City, WeatherData> weatherDataMap;

    public WeatherDataStore() {
        this.weatherDataMap = new HashMap<>();
        // Initialize with default weather data for cities
        for (String cityName : City.CITY_NAMES) {
            weatherDataMap.put(new City(cityName), new WeatherData());
        }
    }

    public boolean containsCity(City city) {
        return weatherDataMap.containsKey(city);
    }

    public WeatherData getWeatherData(City city) {
        return weatherDataMap.get(city);
    }

    public void updateWeatherData(City city, WeatherData weatherData) {
        weatherDataMap.put(city, weatherData);
    }

    public Map<City, WeatherData> getAllWeatherData() {
        return new HashMap<>(weatherDataMap);  // Return a copy to prevent external modifications
    }
}
