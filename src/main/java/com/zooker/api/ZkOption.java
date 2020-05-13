package com.zooker.api;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author zxq
 * 2020/5/12
 */
public class ZkOption {

    public static void main(String[] args) throws IOException {
        //获取zk客户端
        ZooKeeper zkClient = ZkClient.getZkClient();
        //创建节点
        //acl访问权限
        try {
            zkClient.create("/sanguo",
                    "yingjie".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取节点的值,子节点，权限等
        try {
            byte[] data = zkClient.getData("/sanguo", false, null);
            System.out.println(new String(data));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //判断节点是否存在
        try {
            Stat exists = zkClient.exists("/sanguo", false);
            System.out.println(exists);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
