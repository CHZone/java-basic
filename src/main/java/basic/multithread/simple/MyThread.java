package basic.multithread.simple;

public class MyThread extends Thread{

    private String name;
    public MyThread(String name){
        this.name = name;
    }


    public void run(){
        System.out.println("name:"+name+"子进程"+Thread.currentThread().getId());
    }
}
