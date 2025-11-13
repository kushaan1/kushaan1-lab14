import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client(String host, int port) throws IOException {

        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void handshake() throws IOException {
        out.println("12345");
        out.flush();

        String response = in.readLine();
        if (!"OK".equals(response)) {
            throw new IOException("Handshake failed: " + response);
        }
    }

    public String request(String number) throws IOException {
        out.println(number);
        out.flush();

        return in.readLine();

    }

    public void disconnect() throws IOException {
        this.socket.close();
    }

}
