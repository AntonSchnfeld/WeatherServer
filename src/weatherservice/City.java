package weatherservice;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Repräsentiert eine Stadt mit einem Namen.
 * Falls kein Name angegeben wird, wird ein zufälliger Name aus einer vordefinierten Liste gewählt.
 */
public class City {
    /** Liste der möglichen Städtenamen für zufällige Auswahl. */
    protected static final String[] CITY_NAMES = {
            "Duisburg", "Dortmund", "Berlin", "Bochum", "Aachen", "Affel",
            "Gladbeck", "Remscheid", "Potsdam", "Warschau", "Kiew", "London",
            "Washington DC", "New York", "Paris", "Venedig"
    };

    /**
     * Gibt einen zufälligen Städtenamen aus der vordefinierten Liste zurück.
     *
     * @return Ein zufällig gewählter Städtename.
     */
    protected static String getRandomCityName() {
        return CITY_NAMES[ThreadLocalRandom.current().nextInt(CITY_NAMES.length)];
    }

    /** Der Name der Stadt. */
    protected final String name;

    /**
     * Erstellt eine neue Stadt mit einem zufällig gewählten Namen.
     */
    public City() {
        this.name = getRandomCityName();
    }

    /**
     * Erstellt eine neue Stadt mit einem angegebenen Namen.
     *
     * @param name Der Name der Stadt.
     */
    public City(String name) {
        this.name = name;
    }

    /**
     * Gibt den Namen der Stadt zurück.
     *
     * @return Der Name der Stadt.
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt eine String-Darstellung der Stadt zurück.
     *
     * @return Eine String-Repräsentation der Stadt in der Form: City{name:"<Name>"}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name:\"" + name + "\";}";
    }

    /**
     * Vergleicht zwei City-Objekte auf Gleichheit anhand ihres Namens.
     *
     * @param o Das zu vergleichende Objekt.
     * @return true, wenn beide City-Objekte denselben Namen haben, sonst false.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof City city)) return false;

        return city.name.equals(this.name);
    }
}
