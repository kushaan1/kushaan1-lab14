import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;

public class Server {
    private ServerSocket serverSocket;
    private final ArrayList<LocalDateTime> connectedTimes = new ArrayList<>();

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

    public ArrayList<LocalDateTime> getConnectedTimes() {
        ArrayList<LocalDateTime> sorted = new ArrayList<>(connectedTimes);
        Collections.sort(sorted);
        return sorted;
    }

    private class clientThread extends Thread {
        private Socket clientSocket;

        public clientThread(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String handshake = in.readLine();
                if (!"12345".equals(handshake)) {
                    out.println("couldn't handshake");
                    clientSocket.close();
                    return;
                }

                String line;
                while ((line = in.readLine()) != null) {
                    try {
                        int number = Integer.parseInt(line);
                        int factors = factorize(number);
                        out.println("The number " + number + " has " + factors + " factors");
                    } catch (NumberFormatException e) {
                        out.println("There was an exception on the server");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int factorize(int number) {
        int count = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                count++;
            }
        }
        return count;
    }

    public void disconnect() throws IOException {
        this.serverSocket.close();
    }

}