package demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author sr
 * * @date Create at 20:48 2024/4/18
 */
public class Client {
    public static void main(String[] args) {
        c2();
    }
    public static void c2(){
        try {
            System.out.println("客户端已经启动");
            Socket socket = new Socket(InetAddress.getLocalHost(),8080);
            OutputStream os = socket.getOutputStream();
            //键盘输入
            Scanner sc =  new Scanner(System.in);
            String content = "客户端：1";
            os.write(content.getBytes());
            while(true){
//                String content = sc.nextLine();
//                content = "客户端："+content;


                //            os.write("客户端说：你好，服务器".getBytes());
                InputStream is = socket.getInputStream();
                byte[] bs = new byte[1024];
                int len = 0;//实际读取的内容长度
                len = is.read(bs);
                System.out.println(new String(bs,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void c1(){
        try {
            System.out.println("客户端已经启动");
            Socket socket = new Socket(InetAddress.getLocalHost(),8080);
            OutputStream os = socket.getOutputStream();
            os.write("客户端说：你好，服务器".getBytes());
            InputStream is = socket.getInputStream();
            byte[] bs = new byte[1024];
            int len = 0;//实际读取的内容长度
            len = is.read(bs);
            System.out.println("客户端收到的信息："+new String(bs,0,len));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
