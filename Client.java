import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;

    public Client(String host, int port) throws IOException {

        this.socket = new Socket(host, port);
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void handshake() throws IOException {
        this.socket.getOutputStream().write("12345\n".getBytes());
        this.socket.getOutputStream().flush();
    }

    public void disconnect() throws IOException {
        this.socket.close();
    }

}
