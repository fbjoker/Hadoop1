package com.alex.zookeeper.proj1;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZooKeeperClient {
    static  ZooKeeper zooKeeper=null;
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //1获取连接
        getConn();

        //2监听指定的节点
        getServerList();


        //3.业务逻辑

        business();



    }

    private static void business() throws InterruptedException {
        System.out.println("Client is working");
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void getServerList() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/servers", true);
        ArrayList<String> list=new ArrayList();
        for (String child : children) {
            byte[] data = zooKeeper.getData("/servers/" + child, false, null);
            list.add(new String(data));

        }
        System.out.println(list);

    }

    private static void getConn() throws IOException {

        String conn="hadoop102:2181,hadoop103:2181,hadoop104:2181,hadoop105:2181";
        int timeout=2000;

        zooKeeper = new ZooKeeper(conn, timeout, new Watcher() {
            public void process(WatchedEvent event) {
                //System.out.println(event.getType()+"----"+ event.getPath());

                try {
                    getServerList();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
