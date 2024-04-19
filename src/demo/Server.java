package demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author sr
 * * @date Create at 20:48 2024/4/18
 */
public class Server {
    public static void main(String[] args) {
        s2();
    }
    public static void s2() {
        try {
            System.out.println("服务端已经启动");
            ServerSocket server = new ServerSocket(8080);
            //等客户端连接，accept()方法会阻止程序继续往下执行

            while (true) {
                Socket accept = server.accept();//只是阻塞当前程序
                Scanner sc = new Scanner(System.in);
                System.out.println("有客人来了");
                InputStream is = accept.getInputStream();
                OutputStream os = accept.getOutputStream();
                new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                byte[] bs = new byte[1024];
                                int len = 0;//实际读取的内容长度
                                len = is.read(bs);
                                String r = new String(bs, 0, len);
                                if (r.endsWith("bye")) {
                                    break;
                                }
                                System.out.println(r);
                                String content = sc.nextLine();//阻塞
                                content = "服务端：" + content;
                                os.write(content.getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("服务端已经启动完毕");
    }
    public static void s1(){
        try {
            System.out.println("服务端已经启动");
            ServerSocket server = new ServerSocket(8080);
            //等待客户端连接,accept()方法会阻止程序继续往下执行
//            Socket accept = server.accept();
            while(true){
                Socket accept = server.accept();
                InputStream is = accept.getInputStream();
                byte[] bs = new byte[1024];
                int len = 0;//实际读取的内容长度
                len = is.read(bs);
                System.out.println("服务器收到了客户端的信息："+new String(bs,0,len));
                OutputStream os = accept.getOutputStream();
                os.write("服务端已经收到了信息".getBytes());
                System.out.println("服务端已经启动完毕");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
