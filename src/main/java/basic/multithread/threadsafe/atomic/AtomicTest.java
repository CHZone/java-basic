package basic.multithread.threadsafe.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
	private static AtomicInteger race = new AtomicInteger(0);
	
	public  static void increase() {
		race.getAndIncrement();
	}
	
	private static final int THREAD_COUNT = 20;
	
	public  static void main(String[] args){
		Thread[] threads = new Thread[THREAD_COUNT];
		for (int i = 0; i < THREAD_COUNT; i++){
			threads[i] = new Thread(new Runnable() {
				public void run() {
					for(int i = 0; i < 10000; i++){
						increase();
					}
				}
			});
			threads[i].start();
		}
		
		while (Thread.activeCount() > 1){
			Thread.yield();
		}
		System.out.println(race);
	}
	
}
