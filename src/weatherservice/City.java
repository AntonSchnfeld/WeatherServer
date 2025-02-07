package weatherservice;

public class City {
    protected static final String[] CITY_NAMES = {
            "Duisburg",
            "Dortmund",
            "Berlin",
            "Bochum",
            "Aachen",
            "Affel",
            "Gladbeck",
            "Remscheid",
            "Potsdam",
            "Warschau",
            "Kiev",
            "London",
            "Washington DC",
            "New York",
            "Paris",
            "Venedig"
    };

    protected static String getRandomCityName() {
        return CITY_NAMES[(int) Math.round(Math.random() * (CITY_NAMES.length - 1))];
    }

    protected final String name;

    public City() {
        this.name = getRandomCityName();
    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name:\"" + name + "\";}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof City city)) return false;

        return city.name.equals(this.name);
    }
}