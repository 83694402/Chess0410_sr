package com.sr_qlp.main.util;

import com.sr_qlp.main.model.Message;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author sr
 * * @date Create at 10:50 2024/4/21
 */
public class SocketUtil {
    public static Socket create(String ip,int port){
        try {
            return new Socket(InetAddress.getByName(ip),port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Socket createLocalHost(int port){
        try {
            return new Socket(InetAddress.getLocalHost(),port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void send(Socket s, Message msg){
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try{
            os = s.getOutputStream();
            oos = new ObjectOutputStream(os);
            oos.writeObject(msg);
        }catch (IOException e){
            e.printStackTrace();
        }finally{

        }
    }

    public static Object receive(Socket s){
        InputStream is = null;
        ObjectInputStream ois = null;
        try{
            is = s.getInputStream();
            ois = new ObjectInputStream(is);
            return ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static void close(InputStream is,OutputStream os){
        if(is != null){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(os != null){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
