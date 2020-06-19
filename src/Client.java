import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Scanner scanner;
    private Connection connection;

    public Client(String ip, int port) throws IOException {
        scanner = new Scanner(System.in);
        this.connection = new Connection(new Socket(ip, port));

    }

    private void read(Connection connection) {
        ReaderClientThread readThread = new ReaderClientThread(connection);
        readThread.start();
    }

    public void start() throws IOException {
        System.out.println("Введите имя пользователя");
        String name = scanner.nextLine();
        String text;
        while (true) {
            System.out.println("Введите сообщение");
            text = scanner.nextLine();
            if (text.equals("off")) {
                break;
            } else {
                connection.sendMessage(new Message(name, text));
            }
        }
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 8090);
            client.read(client.connection);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
