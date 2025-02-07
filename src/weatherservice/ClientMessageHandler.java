package weatherservice;

import netzklassen.Server;

public interface ClientMessageHandler {
    void handle(Server server, String clientIp, int clientPort, String message);
}