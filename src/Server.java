import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server {
    private final int port;
    Set<Connection> connections = new HashSet<>();
    BlockingQueue<Message> messages = new ArrayBlockingQueue<>(10, true);

    public Server(int port) {
        this.port = port;
    }

    public void start(Server server) throws IOException, ClassNotFoundException, InterruptedException {
        WriterServerClass writerServerClass = new WriterServerClass(server);
        writerServerClass.start();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен");
            while (true) {
                Socket clientSocket = serverSocket.accept();

                Connection connection = new Connection(clientSocket);
                connections.add(connection);
                new ReaderServerThread(server, connection).start();
            }
        }
    }

    public static void main(String[] args) {
        int port = 8090;
        Server server = new Server(port);

        try {
            server.start(server);
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }

    }


}
