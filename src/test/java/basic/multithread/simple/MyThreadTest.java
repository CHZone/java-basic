package basic.multithread.simple;

public class MyThreadTest {
    public static void main(String[] args){
        System.out.println("主进程ID:"+Thread.currentThread().getId());
        MyThread thread1 = new MyThread("thread1");
        thread1.start();
        MyThread thread2 = new MyThread("thread2");
        thread2.run();
    }
}
