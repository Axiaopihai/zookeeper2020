package com.zooker.api;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author zxq
 * 2020/5/12
 */
public class ZkClient {

    public static ZooKeeper getZkClient() throws IOException {
        //connection配置方式与zookeeper集群中server的配置方式须相同，均为主机名或均为ip
        String connection = "hadoop128:2181,hadoop129:2181,hadoop130:2181";
        return new ZooKeeper(connection, 4000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
            }
        });
    }

}
