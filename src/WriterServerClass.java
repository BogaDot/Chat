import java.io.IOException;

public class WriterServerClass extends Thread{
    Server server;

    public WriterServerClass(Server server) {
        this.server = server;
    }
    @Override
    public void run() {
        while (true){
            try {
                Message message = server.messages.take();
                for(Connection connection: server.connections){
                    if(!message.getAddress().equals(connection.getSocket().getRemoteSocketAddress())) {
                        try {
                            connection.sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
