package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Read extends Base {


    public Read(DatagramSocket datagramSocket) {
        super(datagramSocket);
        System.out.println(this.datagramSocket==null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] bytes = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(bytes, bytes.length);
                //阻塞线程一直读取数据
                datagramSocket.receive(receivePacket);
                System.out.println(datagramSocket==null);
                String ip = receivePacket.getAddress().getHostAddress();
                String message = new String(receivePacket.getData(),0, receivePacket.getData().length);
                if (message.trim().equals(brokeExit)) System.out.println(ip+"exit");
                else System.out.println("内容：" + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
