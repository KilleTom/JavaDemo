package udp;

import java.net.DatagramSocket;

public class Base implements Runnable{
    protected DatagramSocket datagramSocket;
    protected final String brokeExit = "sys_exit_ypz";

    public Base(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {

    }
}
