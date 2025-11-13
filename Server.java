import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;

public class Server {
    private ServerSocket serverSocket;
    private final List<LocalDateTime> connectedTimes = new ArrayList<>();

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);

    }

    public void serve(int clients) throws IOException {

    }

    public List<LocalDateTime> getConnectedTimes() {
        ArrayList<LocalDateTime> sorted = new ArrayList<>(connectedTimes);
        Collections.sort(sorted);
        return sorted;
    }

    private void handleClient(Socket clientSocket) {
    };

    private int factorize(int number) {

    };

}