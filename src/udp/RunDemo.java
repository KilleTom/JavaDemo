package udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunDemo {

    public static void main(String[] args) {
        UDPDemo udpDemo = UDPDemo.getUDP();
        udpDemo.setmIPAdress("192.168.100.9");
        udpDemo.setSendPort(8998);
        udpDemo.setReceivePort(9889);
        new Thread(() -> {
            try {
                udpDemo.getUDP().readMessage();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
        new Thread(() -> {
            try {
                udpDemo.senMessage(new BufferedReader(new InputStreamReader(System.in)));
            } catch (Exception e) {
                System.out.printf("e"+e.getMessage());
            }
        }).start();


    }
}
