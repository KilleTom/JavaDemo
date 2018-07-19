package tcp;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPDemo {
    private ServerSocket serverSocket;
    private Socket clientSocket, servicesSocket;
    public String servicesIPAdress;
    public int servicesPort;

    public void readServicesMessage() throws Exception {
        if (clientSocket == null) throw new NullPointerException("clientSocket is Null ");
        else {
            try {
                byte[] bytes = new byte[1024];
                int len = clientSocket.getInputStream().read(bytes);
                System.out.println("message:"+new String(bytes,0,len));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void setServicesIPAdress(String servicesIPAdress) {
        this.servicesIPAdress = servicesIPAdress;
    }

    public void setServicesPort(int servicesPort) {
        this.servicesPort = servicesPort;
    }

    public void initClientSocket(){
        if (servicesIPAdress==null) {
            clientSocket = null;
            System.out.println("initnull");
            return;
        }
        try {
            System.out.println("init");
            clientSocket = new Socket(servicesIPAdress,servicesPort);
        }catch (Exception e){
            clientSocket = null;
            System.out.println(e.getMessage());
        }
    }

    public void sendServicesMessage(String message)throws Exception{
        if (clientSocket == null) throw new NullPointerException("clientSocket is Null ");
        try {
            System.out.println("开始写入数据"+clientSocket.isConnected());
            clientSocket.getOutputStream().write(message.getBytes());
        }catch (Exception e){
            throw new IOException("服务写入数据异常");
        }
    }

    public void closeClientSocket()throws IOException{
        if (clientSocket!=null&&clientSocket.isConnected()) clientSocket.close();
    }

    public void initServerSocket(){
        try {
            System.out.println(servicesPort);
            serverSocket = new ServerSocket(servicesPort);
            servicesSocket = serverSocket.accept();
            while (true){
                //readClientMessage
                byte[] bytes = new byte[1024];
                int len = servicesSocket.getInputStream().read(bytes);
                System.out.println("Address:"+serverSocket.getInetAddress());
                System.out.println("message:"+new String(bytes,0,len));
                //writeClientMessage
                servicesSocket.getOutputStream().write("收到请求".getBytes());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    private static TCPDemo tcpDemo;

    private TCPDemo() {

    }

    public static TCPDemo getTcpDemo() {
        if (tcpDemo == null)
            synchronized (TCPDemo.class) {
                tcpDemo = new TCPDemo();
            }
        return tcpDemo;
    }
}
