package algo.performance;

import java.util.Vector;

public class TestBase {
    public final static int OUTOFMEMORY = 200000000;

    private String oom;

    private int length;

    StringBuffer tempOOM = new StringBuffer();

    public TestBase(int leng) {
        this.length = leng;

        int i = 0;
        while (i < leng) {
            i++;
            try {
                tempOOM.append("a");
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                break;
            }
            if(i%1000000 == 0){
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        this.oom = tempOOM.toString();

    }

    public String getOom() {
        return oom;
    }

    public int getLength() {
        return length;
    }

    public static void main(String[] args) {
        TestBase javaHeapTest = new TestBase(OUTOFMEMORY);
        System.out.println(javaHeapTest.getOom().length());
    }

}
