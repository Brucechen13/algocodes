package algo.depthfirst;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    private static Runnable blockRunner = () -> {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("one round:" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    private static ScheduledExecutorService scheduledExecutorService =
            Executors.newScheduledThreadPool(2);

    public static void main(String ... args) {


        scheduledExecutorService
                .scheduleAtFixedRate(blockRunner, 0, 100, TimeUnit.MILLISECONDS);

    }
}
