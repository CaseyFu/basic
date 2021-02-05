package org.casey.basic.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName SocketTest
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description todo
 * @Date 2020/9/24
 */

public class SocketTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress host = InetAddress.getLocalHost();
        String ip = host.getHostAddress();
        System.out.println("本机ip地址: " + ip);
    }
}
