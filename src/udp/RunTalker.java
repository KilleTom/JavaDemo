package udp;

import java.net.DatagramSocket;
import java.net.SocketException;

public class RunTalker {
    public static void main(String[] args) throws SocketException {
        new Thread(new Send(new DatagramSocket())).start();
        new Thread(new Read(new DatagramSocket(10399))).start();
    }
}
