package weatherservice;

import java.util.Arrays;

public class WeatherRequest {
    private final String ip;
    private final int port;
    private final String[] messageParts;

    public WeatherRequest(String ip, int port, String[] messageParts) {
        this.ip = ip;
        this.port = port;
        this.messageParts = messageParts;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String[] getMessageParts() {
        return messageParts;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof WeatherRequest weatherRequest)) return false;

        return this.ip.equals(weatherRequest.ip) &&
                this.port == weatherRequest.port &&
                Arrays.equals(this.messageParts, weatherRequest.messageParts);
    }
}
