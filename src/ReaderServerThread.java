import java.io.IOException;

public class ReaderServerThread extends Thread{
    Server server;
    Connection connection;

    public ReaderServerThread(Server server, Connection connection) {
        this.server = server;
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = connection.readMessage();
                System.out.println(message.getText());
                message.setAddress(connection.getSocket().getRemoteSocketAddress());
                server.messages.put(message);
            }

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
