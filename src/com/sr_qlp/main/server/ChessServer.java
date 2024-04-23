package com.sr_qlp.main.server;

import com.sr_qlp.main.model.Message;
import com.sr_qlp.main.util.SocketUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * @author sr
 * * @date Create at 15:06 2024/4/21
 */
public class ChessServer {
    //Map是键值对，key-value
    private static Map<String,ServerThread> clients;//保存登陆的所有客户端线程



    public static void main(String[] args) {
        new ChessServer().start();
    }

    public  void start(){
        try {
            ServerSocket server = new ServerSocket(8080);
            clients = new HashMap<>();
            System.out.println("服务端启动成功！");
            while(true){
                Socket accept = server.accept();
                ServerThread st = new ServerThread(accept);
                st.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class ServerThread extends Thread{
        private Socket socket;
        public ServerThread(Socket socket){
            this.socket = socket;
        }
        public Socket getSocket() {
            return socket;
        }

        @Override
        public void run() {
            while(true){
                Object receive = SocketUtil.receive(socket);
                System.out.println(receive);
                if(receive instanceof Message){
                    Message request = (Message) receive;
                    switch (request.getType()){
                        case LOGIN:
                            login(request);
                            break;
                        case REG:
                            break;
                        case FIGHT:
                            fight(request);
                            break;
                        case LIST:
                            list();
                            break;
                        case MOVE:
                            move(request);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        private void move(Message req){
            String from = req.getFrom();
            String to = req.getTo();
            ServerThread stFrom = clients.get(from);
            ServerThread stTo = clients.get(to);
            Message resp = new Message();
            resp.setFrom(req.getFrom());
            resp.setTo(req.getTo());
            resp.setContent(req.getContent());
            resp.setFromPlayer(0);
            resp.setToPlayer(1);
            resp.setType(Message.Type.MOVE);
            SocketUtil.send(stTo.getSocket(),resp);

        }
        private void list(){
            Message resp = new Message();
            resp.setType(Message.Type.SUCCESS);
            resp.setContent(getAccountList());
            clients.forEach((k,v)->{
                //System.out.println("Item: "+k+" Count "+v);
                SocketUtil.send(v.getSocket(),resp);
            });
        }
        private void fight(Message req){
            String from = req.getFrom();
            String to = req.getTo();
            ServerThread stFrom = clients.get(from);
            ServerThread stTo = clients.get(to);
            Message resp = new Message();
            //规定挑战发起者拿红棋
            resp.setFrom(req.getFrom());
            resp.setTo(req.getTo());
            resp.setFromPlayer(0);
            resp.setToPlayer(1);
            resp.setType(Message.Type.FIGHT_SUCCESS);
            SocketUtil.send(stFrom.getSocket(),resp);
            SocketUtil.send(stTo.getSocket(),resp);

        }
        private Vector getAccountList(){
            Vector<String> list = new Vector<>();
            Set<String> keySet = clients.keySet();
            Iterator<String> iterator = keySet.iterator();
            while(iterator.hasNext()){
                list.add(iterator.next());
            }
            return list.size() == 0 ? null:list;
        }
        private void login(Message msg){
            //假设登录成功
            //将数据保存到客户端map中
            clients.put(msg.getFrom(),this);
            //发送消息回去
            Message response = new Message();
            response.setType(Message.Type.SUCCESS);
            SocketUtil.send(socket,response);
        }
    }
}
