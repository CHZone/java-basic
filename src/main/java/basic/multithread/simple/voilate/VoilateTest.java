package basic.multithread.simple.voilate;

public class VoilateTest {
	public static volatile int race = 0;
	public static void increase(){
		race++;
	}
	
	public static final int THREAD_COUNT = 20;
	public static void main(String[] args){
		Thread[] threads = new Thread[20];
		for(int i = 0; i < THREAD_COUNT; i++){
			threads[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for(int i = 0; i < 10000; i++){
						increase();
					}
				}
			});
			threads[i].start();
		}
		
		while(Thread.activeCount() > 1){
			Thread.yield();
		}
		System.out.println(race);
	}
	
	
}
