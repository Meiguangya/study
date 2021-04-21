package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author water33
 */
public class Main {

    private static String connnectString = "127.0.0.1:2181";
    private static int sessionTimeout = 2000;
    private static ZooKeeper zooKeeper;


    @Before
    public void testInit() throws IOException {
        zooKeeper = new ZooKeeper(connnectString,sessionTimeout,(watcher)->{
            System.out.println("watch start...");
            try {
                List<String> children = zooKeeper.getChildren("/", true);
                children.forEach(path->{
                    System.out.println(path);
                });
                System.out.println("watch end...");
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("zookeeper初始化成功");
    }

    @Test
    public void testCreateNode() throws KeeperException, InterruptedException {
        String path = zooKeeper.create("/book/math", "数学".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println(path);
    }

    @Test
    public void testWatch() throws KeeperException, InterruptedException {
        testCreateNode();
        while(true){

        }
    }

    @Test
    public void testExist() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/book", false);
        System.out.println(exists);
    }

}
