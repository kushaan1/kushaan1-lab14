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
        for (int i = 0; i < clients; i++) {
            Socket clientSocket = serverSocket.accept();
            synchronized (connectedTimes) {
                connectedTimes.add(LocalDateTime.now());
            }
            new clientThread(clientSocket).start();
        }
    }

    public List<LocalDateTime> getConnectedTimes() {
        ArrayList<LocalDateTime> sorted = new ArrayList<>(connectedTimes);
        Collections.sort(sorted);
        return sorted;
    }

    private void handleClient(Socket clientSocket) {
    };

    private int factorize(long number) {
        int count = 0;
        for (long i = 1; i <= number; i++) {
            if (number % i == 0) {
                count++;
            }
        }
        return count;
    }

}