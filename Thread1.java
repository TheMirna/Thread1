package hr.fer.oop.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Thread1 {
		
	
	public static class Producer implements Runnable{
		private BlockingQueue<Integer> queue;
		private int size;
		
		public Producer(BlockingQueue<Integer> queue, int size){
			this.queue = queue;
			this.size = size;
		}
			
			@Override
			public void run(){
				
				for(int i = 0; i < 10; i++){
					try{
						int number = (int)(Math.random() * 1000);
						queue.put(number);
						System.out.println("Stavljam broj: " + number);
						
						Thread.sleep((int)Math.random() * 1000);
					} catch (InterruptedException ex){
						System.out.println("Interrupted!");
					}
				}
			}
		}
	
		private static class Consumer implements Runnable{
			private BlockingQueue<Integer> queue;
			private int size;
			
			public Consumer(BlockingQueue<Integer> queue, int size){
				this.queue = queue;
				this.size = size;
			}
			
			
			@Override
			public void run(){
				for(int i = 0; i < 10; i++){
					try{
						System.out.println("Uzimam element: " + queue.take());
					} catch (InterruptedException ex){
						System.out.println("Interrupted!");
					}
				}
			}
		}
	
	public static void main(String[] args){
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1);
		Thread producer = new Thread(new Producer(queue, 1));
		Thread consumer = new Thread(new Consumer(queue, 1));
		
		producer.start();
		consumer.start();
		
	}

}
