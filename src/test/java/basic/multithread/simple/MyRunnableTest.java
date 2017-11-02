package basic.multithread.simple;

public class MyRunnableTest {
    public static void main(String[] args){
        System.out.println("主线程ID:"+Thread.currentThread().getId());
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
