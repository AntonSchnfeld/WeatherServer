package weatherservice.server;

public interface MessageSender {
    void send(String ip, int port, String message);
}
