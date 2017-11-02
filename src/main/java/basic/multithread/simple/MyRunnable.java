package basic.multithread.simple;

public class MyRunnable implements Runnable {
    public MyRunnable(){

    }
    @Override
    public void run() {
        System.out.println("子进程ID："+Thread.currentThread().getId());
    }
}
