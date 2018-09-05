package algo.other;

public class YieldTest extends Thread{
    public void run(){
        Thread.yield();
        System.out.println("run1");
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run2");
    }
    public static void main(String[] args){
        Thread t1 = new YieldTest();
        Thread t2 = new YieldTest();
        t1.start();
        t2.start();
    }

}
