package com.alex.zookeeper;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Demo1 {
    private static String connectString =
            "hadoop102:2181,hadoop103:2181,hadoop104:2181,hadoop105:2181";
    private static int sessionTimeout = 2000;
    private ZooKeeper zooKeeper = null;

    @Before
    public  void  getConn() throws IOException {

        //直接new获取连接
        zooKeeper= new ZooKeeper(connectString, sessionTimeout, new Watcher() {
           //接受到通知以后,立马执行process方法
            public void process(WatchedEvent event) {
                System.out.println(event.getType()+"--"+event.getPath());

                try {
                    List<String> bigdata2 = zooKeeper.getChildren("/bigdata2", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }) ;

    }


    //创建节点

    @Test
    public void  createNodeTest() throws KeeperException, InterruptedException {
        String s = zooKeeper.create("/bigdata2/test6",
                "test6".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println(s);

    }

    //查看
    @Test
    public void findNode() throws KeeperException, InterruptedException {
        ZooKeeper.States state = zooKeeper.getState();
        System.out.println(state);

        List<String> bigdata2 = zooKeeper.getChildren("/bigdata2", true);

        for (String s : bigdata2) {
            System.out.println(s);

        }

        Thread.sleep(Integer.MAX_VALUE);

    }
    //修改数据

    @Test
    public  void setTest() throws KeeperException, InterruptedException {
        zooKeeper.setData("/bigdata2/test6","change".getBytes(),-1);
    }

    @Test
    public  void getTest() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/bigdata2/test6", false, null);
        System.out.println(new String(data));
    }


    @Test
    public void deleteTest() throws KeeperException, InterruptedException {
        zooKeeper.delete("/bigdata2/test6",-1);
    }

}
