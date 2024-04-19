package demo;

/**
 * @author sr
 * * @date Create at 20:08 2024/4/18
 */
public class ThreadDemo {
    public static void main(String[] args) {
        MyThread mt = new MyThread();
        mt.setName("mt");
        //启动线程，不代表run方法中的代码会立即执行
        mt.start();
        while(1 > 0){
            System.out.print("asdf");
        }

    }
}
