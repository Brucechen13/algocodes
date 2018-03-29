package skill.process;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试线程
 * A,B两个线程
 * 交替输出
 */
public class ThreadTest {

    static class A implements Runnable{

        private String msg;

        public A(String msg){
            this.msg = msg;
        }

        @Override
        public void run() {
            System.out.println(msg);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Thread t1 = new Thread(new A("t1"));
        t1.start();
        try {
            t1.join();

            System.out.println("end");
            ExecutorService threadPool = Executors.newFixedThreadPool(3);
            threadPool.execute(new A("t2"));
            //threadPool.shutdown();
            threadPool.awaitTermination(10000, TimeUnit.DAYS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("end2");

    }
}
