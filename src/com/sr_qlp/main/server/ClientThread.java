package com.sr_qlp.main.server;

import com.sr_qlp.main.model.Message;
import com.sr_qlp.main.util.SocketUtil;

import java.net.Socket;
import java.util.Vector;

/**
 * @author sr
 * * @date Create at 20:01 2024/4/21
 */
public class ClientThread extends Thread{
    private Socket socket;
    private ResponseListener l;

    public ClientThread(Socket socket,ResponseListener l){
        this.socket = socket;
        this.l = l;
    }

    public interface ResponseListener{
        void success(Message msg);
    }

    @Override
    public void run() {
        while(true){
            Object receive = SocketUtil.receive(socket);
            System.out.println(receive);
            if(receive instanceof Message){
                Message resp = (Message) receive;
                if(l != null){
                    l.success(resp);
                }
                //                model.clear();
//                Vector<String> v = (Vector<String>) resp.getContent();
//                v.forEach(item->{
//
//                    model.addElement(item);
//                });
//
//                list.validate();
            }
        }
    }
}
