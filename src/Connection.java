import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

public class Connection implements Closeable {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    public void sendMessage(Message message) throws IOException {
        message.setDateTime(LocalDateTime.now());
        synchronized (this.output){
            output.writeObject(message);
            output.flush();
        }
    }

    public Message readMessage() throws IOException, ClassNotFoundException {
        synchronized (this.input){
           return (Message) input.readObject();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void close() throws IOException {
        input.close();
        output.close();
        socket.close();
    }
}
