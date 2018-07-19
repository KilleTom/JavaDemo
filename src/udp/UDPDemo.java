package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPDemo {

    private static UDPDemo udpDemo;
    private String brokeSendUDPCode;
    private DatagramSocket sendSocket, receiveSocket;
    private DatagramPacket sendPacket, receivePacket;
    public String mIPAdress;
    public int sendPort, receivePort;

    public void setBrokeSendUDPCode(String brokeSendUDPCode) {
        this.brokeSendUDPCode = brokeSendUDPCode;
    }

    public void setmIPAdress(String mIPAdress) {
        this.mIPAdress = mIPAdress;
    }

    public void setSendPort(int sendPort) {
        this.sendPort = sendPort;
        try {
            sendSocket = new DatagramSocket(sendPort);
        } catch (Exception e) {
            sendSocket = null;
            System.out.println(e.getMessage());
        }
    }

    public void setReceivePort(int receivePort) {
        this.receivePort = receivePort;
    }

    private UDPDemo() {

    }

    public static UDPDemo getUDP() {
        if (udpDemo == null) {
            synchronized (UDPDemo.class) {
                udpDemo = new UDPDemo();
                udpDemo.setBrokeSendUDPCode("finsh_Ypz_OK");
            }
        }
        return udpDemo;
    }

    public void senMessage(BufferedReader reader) throws Exception {
        if (reader == null) throw new NullPointerException("reader is Null");
        if (mIPAdress == null) throw new NullPointerException("IPAdress is Null");
        try {
            if (sendSocket == null)
                if (sendPort != 0) sendSocket = new DatagramSocket();
                else sendSocket = new DatagramSocket(sendPort);
            String lines = null;
            if (sendSocket.isClosed()) sendSocket.connect(InetAddress.getByName(mIPAdress), receivePort);
            while ((lines = reader.readLine()) != null) {
                sendPacket = new DatagramPacket(
                        lines.getBytes(), lines.getBytes().length,
                        InetAddress.getByName(mIPAdress), receivePort);
                sendSocket.send(sendPacket);
                if (brokeSendUDPCode.equals(lines)) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sendSocket != null) sendSocket.close();
        }

    }

    public void readMessage() throws Exception {
        if (receiveSocket == null) receiveSocket = new DatagramSocket(receivePort);
        while (true) {
            byte[] bytes = new byte[1024];
            receivePacket = new DatagramPacket(bytes, bytes.length);
            //阻塞线程一直读取数据
            receiveSocket.receive(receivePacket);
            System.out.println("接收端端口:" + receivePacket.getPort() + "\n接收端地址" + receivePacket.getSocketAddress());
            System.out.println("内容：" + new String(receivePacket.getData(),0, receivePacket.getData().length));
        }
    }
}
