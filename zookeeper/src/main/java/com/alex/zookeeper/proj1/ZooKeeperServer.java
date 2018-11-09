package com.alex.zookeeper.proj1;

import org.apache.zookeeper.*;

import java.io.IOException;

public class ZooKeeperServer {
    static  ZooKeeper zooKeeper=null;
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        //1.创建连接
        getConn();

        //2.向服务器创建临时节点
        registServer(args[0]);

        //3.服务器业务逻辑
        business(args[0]);

    }

    private static void business(String hostname) throws InterruptedException {

        System.out.println(hostname+" is working");
        Thread.sleep(Integer.MAX_VALUE);

    }


    private static void registServer(String hostname) throws KeeperException, InterruptedException {
        String path = zooKeeper.create("/servers/server",
                hostname.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL
        );

        System.out.println(hostname+" is  online" +path);

    }

    private static void getConn() throws IOException {

        String conn="hadoop102:2181,hadoop103:2181,hadoop104:2181,hadoop105:2181";
       int timeout=2000;

        zooKeeper = new ZooKeeper(conn, timeout, new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println(event.getType()+"----"+ event.getPath());
            }
        });
    }
}
