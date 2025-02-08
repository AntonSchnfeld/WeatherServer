package weatherservice.data;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Repräsentiert Wetterdaten für einen bestimmten Zeitpunkt, einschließlich Temperatur,
 * Niederschlagsmenge und einer Beschreibung der Wetterlage.
 */
public class WeatherData {

    /** Liste möglicher Wetterbeschreibungen. */
    protected static final String[] DESCRIPTION = {
            "Cremig bewölkt", "Krümelig regnend", "Trocken schneiend",
            "Nasse Hitze", "Saucige Orkane", "Fettiger Schneeregen"
    };

    /** Datum der Wetteraufzeichnung. */
    protected final Date date;

    /** Beschreibung der Wetterlage. */
    protected final String description;

    /** Temperatur in Grad Celsius. */
    protected final int temperature;

    /** Niederschlagsmenge in mm. */
    protected final int amount;

    /**
     * Erstellt eine Wetterdateneinheit mit zufälligen Werten.
     * Das Datum wird auf die aktuelle Zeit gesetzt.
     */
    public WeatherData() {
        date = new Date();
        description = DESCRIPTION[ThreadLocalRandom.current().nextInt(DESCRIPTION.length)];
        temperature = ThreadLocalRandom.current().nextInt(70) - 35;
        amount = ThreadLocalRandom.current().nextInt(100);
    }

    /**
     * Erstellt eine Wetterdateneinheit mit den angegebenen Werten.
     *
     * @param date        Datum der Wetteraufzeichnung.
     * @param description Beschreibung der Wetterlage.
     * @param temperature Temperatur in Grad Celsius.
     * @param amount      Niederschlagsmenge in mm.
     */
    public WeatherData(Date date, String description, int temperature, int amount) {
        this.date = date;
        this.description = description;
        this.temperature = temperature;
        this.amount = amount;
    }

    /**
     * Gibt das Datum der Wetteraufzeichnung zurück.
     *
     * @return Das Datum der Wetteraufzeichnung.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gibt die Beschreibung der Wetterlage zurück.
     *
     * @return Die Beschreibung der Wetterlage.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gibt die Temperatur in Grad Celsius zurück.
     *
     * @return Die Temperatur in Grad Celsius.
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Gibt die Niederschlagsmenge in mm zurück.
     *
     * @return Die Niederschlagsmenge in mm.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Gibt eine String-Darstellung der Wetterdaten zurück.
     *
     * @return Eine String-Repräsentation der Wetterdaten.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{date:" + date.getTime() + ";" +
                "description:\"" + description + "\";" +
                "temperature:" + temperature + ";" +
                "amount:" + amount + ";}";
    }
}
