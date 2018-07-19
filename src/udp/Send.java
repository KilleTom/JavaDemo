package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Send extends Base {
    public Send(DatagramSocket datagramSocket) {
        super(datagramSocket);
    }
    private boolean isBroke;

    @Override
    public void run() {
        String lines = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        if (isBroke) return;
        try {
            bufferedReader = new BufferedReader(inputStreamReader = new InputStreamReader(System.in));
            while ((lines = bufferedReader.readLine()) != null) {
                DatagramPacket datagramPacket = new DatagramPacket(
                        lines.getBytes(), lines.getBytes().length,
                        InetAddress.getByName("192.168.100.255"), 10399);
                datagramSocket.send(datagramPacket);
                if (lines.equals(brokeExit)) isBroke = true;
                if (isBroke) break;
            }
            datagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
                if(inputStreamReader!=null) inputStreamReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
