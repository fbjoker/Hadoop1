package com.alex.zookeeper.proj1b;

import org.apache.logging.log4j.core.config.plugins.PluginVisitorStrategy;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZookeeperClient {

    private static String conn="hadoop102:2181,hadoop103:2181,hadoop104:2181,hadoop105:2181";
    private static int timeout=2000;
    static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        //连接
        getConnection();

        //监听服务器上线下
        isOnline();

        //业务
        business();

    }

    private static void isOnline() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/servers", true);
        List<String> allNode=new ArrayList<String>();
        for (String child : children) {
            System.out.println(child);
            byte[] data = zooKeeper.getData("/servers/" + child, false, null);
            allNode.add(new String(data));
        }
        System.out.println(allNode);


    }

    private static void business() throws InterruptedException {
        System.out.println("客户端运行中.....");
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void getConnection() throws IOException {

        zooKeeper = new ZooKeeper(conn, timeout, new Watcher() {
            public void process(WatchedEvent event) {
                    //业务逻辑,循环调用
                try {
                    isOnline();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
