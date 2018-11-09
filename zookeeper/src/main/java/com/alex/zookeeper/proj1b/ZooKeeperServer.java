package com.alex.zookeeper.proj1b;

import org.apache.zookeeper.*;

import javax.net.ssl.HostnameVerifier;
import java.io.IOException;

public class ZooKeeperServer {

    private static String conn="hadoop102:2181,hadoop103:2181,hadoop104:2181,hadoop105:2181";
    private static int time=2000;
    static ZooKeeper zooKeeper=null;
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //获取连接
        getConnection();

        //2向服务器创建临时节点
        createNode(args[0]);


        //业务逻辑
        business(args[0]);
    }

    private static void createNode(String hostname) throws KeeperException, InterruptedException {
        zooKeeper.create("/servers/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,null);

    }

    private static void business(String hostname) {

        System.out.println( hostname +"上线了");
    }

    private static void getConnection() throws IOException {

        zooKeeper = new ZooKeeper(conn, time, new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println(event.getType() + "-->" + event.getPath());
            }
        });
    }

}
