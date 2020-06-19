import java.io.IOException;

public class ReaderClientThread extends Thread {
    private Connection connection;

    public ReaderClientThread(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(connection.readMessage().getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ;
    }
}
