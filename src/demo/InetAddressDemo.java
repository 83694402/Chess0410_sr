package demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author sr
 * * @date Create at 20:32 2024/4/18
 */
public class InetAddressDemo {
    public static void main(String[] args) {
        InetAddress byName = null;
        InetAddress localHost = null;
        try {
            byName = InetAddress.getByName("192.168.130.188");
            localHost = InetAddress.getLocalHost();
        }catch(UnknownHostException e){
            e.printStackTrace();
        }
        System.out.println(byName);
        System.out.println(byName.getHostName());
        System.out.println(localHost);
        System.out.println(localHost.getHostName());
    }
}
