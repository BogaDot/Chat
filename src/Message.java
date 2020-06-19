import java.io.Serializable;
import java.net.SocketAddress;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private LocalDateTime dateTime;
    private String text;
    private String name;
    private SocketAddress address;

    public Message(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SocketAddress getAddress() {
        return address;
    }

    public void setAddress(SocketAddress address) {
        this.address = address;
    }
}
