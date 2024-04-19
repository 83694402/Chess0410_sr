package demo;

/**
 * @author sr
 * * @date Create at 20:05 2024/4/18
 */
public class MyThread extends Thread{
    @Override
    public void run(){
        //线程体，run方法中的代码就是启动该线程需要执行的代码
        while(true){
            System.out.print(Thread.currentThread().getName()+"正在执行");
        }
    }
}
