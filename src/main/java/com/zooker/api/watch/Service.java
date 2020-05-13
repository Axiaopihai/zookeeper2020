package com.zooker.api.watch;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author zxq
 * 2020/5/12
 */
public class Service {

    private ZooKeeper zkClient;

    public static void main(String[] args) throws IOException, InterruptedException {
        Service service = new Service();
        //初始化zk客户端
        service.getZkClient();
        //注册信息
        service.register();
        //业务处理
        service.dothing();

    }

    private void getZkClient() throws IOException {
        //获取zk客户端
        String connection = "hadoop128:2181,hadoop129:2181,hadoop130:2181";
        zkClient = new ZooKeeper(connection, 4000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                // do nothing
            }
        });
    }

    private void register() {
        //注册信息
        try {
            String s = zkClient.create("/service/serviceId",
                    "serviceId".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void dothing() throws InterruptedException {
        //业务处理
        System.out.println("提供服务中。。。");
        Thread.sleep(Long.MAX_VALUE);
    }
}


