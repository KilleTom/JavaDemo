package tcp;

import udp.UDPDemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

public class RunTCPDemo {

    public static void main(String[] args) {
        TCPDemo tcpDemo = TCPDemo.getTcpDemo();
        tcpDemo.setServicesIPAdress("192.168.100.9");
        tcpDemo.setServicesPort(9738);
        //初始化服务和读取服务信息都会堵塞线程
        new Thread(() -> {
            try {
                tcpDemo.initServerSocket();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
        new Thread(() -> {
            try {
                tcpDemo.initClientSocket();
                tcpDemo.readServicesMessage();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
        new Thread(() -> {
            try {

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String lines = null;
                while ((lines = reader.readLine()) != null) {
                    tcpDemo.sendServicesMessage(lines);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
