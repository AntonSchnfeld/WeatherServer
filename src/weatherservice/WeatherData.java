package weatherservice;

import java.util.Date;

public class WeatherData {

    protected static final String[] DESCRIPTION = {
            "Cremig bewölkt",
            "Krümelig regnend",
            "Trocken schneiend",
            "Nasse Hitze",
            "Saucige Orkane",
            "Fettiger Schneeregen"
    };

    protected final Date date;
    protected final String description;
    protected final int temperature, amount;

    public WeatherData() {
        date = new Date();
        description = DESCRIPTION[(int) Math.round(Math.random() * (DESCRIPTION.length - 1))];
        temperature = ((int) (Math.random() * 70)) - 35;
        amount = (int) (Math.random() * 100);
    }

    public WeatherData(Date date, String description,
                       int temperature, int amount) {
        this.date = date;
        this.description = description;
        this.temperature = temperature;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{date:" + date.getTime() + ";" +
                "description:\"" + description + "\";" +
                "temperature:" + temperature + ";" +
                "amount:" + amount + ";}";
    }
}