package weatherservice;

public final class WeatherProtocol {
    private WeatherProtocol() {}

    public static final String SERVER_GREETING = "SGC";
    public static final String SEPARATOR = ";";
    public static final String CLIENT_REQUEST_RANDOM = "CRR";
    public static final String CLIENT_REQUEST_SPECIFIC = "CRS";
    public static final String CLIENT_REQUEST_CITIES = "CRC";

    public static final String SERVER_ANSWER_RANDOM = "SAR";
    public static final String SERVER_ANSWER_SPECIFIC = "SAS";
    public static final String SERVER_ANSWER_CITIES = "SAC";

    public static String buildMessage(String... parts) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            builder.append(parts[i]);
            if (i < parts.length - 1) builder.append(SEPARATOR);
        }
        return builder.toString();
    }
}
