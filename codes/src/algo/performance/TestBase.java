package algo.performance;

public class TestBase {
    //测试父类构造函数
    static class Father{
        public Father(){
            System.out.print("father1");
        }
        public Father(int i){
            System.out.print("father2");
        }
    }
    static class Child extends Father{

    }
    //接口和抽象类的异同

    public static void main(String[] args){
        Father child = new Child();
    }
}
