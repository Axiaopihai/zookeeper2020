package com.zooker.api.watch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zxq
 * 2020/5/12
 */
public class ServiceClient {

    private ZooKeeper zkClient;
    private ArrayList<String> services = new ArrayList<String>();

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        ServiceClient serviceClient = new ServiceClient();
        //初始化客户端
        serviceClient.getZkClient();
        //从zookeeper中获取服务列表
        serviceClient.getServices();
        //业务处理
        serviceClient.dothing();

    }

    private void getZkClient() throws IOException {
        //获取zk客户端
        String connection = "hadoop128:2181,hadoop129:2181,hadoop130:2181";
        zkClient = new ZooKeeper(connection, 4000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //监控节点变化后的重新获取服务列表
                try {
                    getServices();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getServices() throws KeeperException, InterruptedException {
        //获取服务器列表
        List<String> children = zkClient.getChildren("/service", true);
        for (String child : children) {
            System.out.println(child);
            services.add(child);
        }
    }

    private void dothing() throws InterruptedException {
        //业务处理
        for (String service : services) {
            System.out.println("可应用的服务："+service);
            Thread.sleep(Long.MAX_VALUE);
        }
    }


}
