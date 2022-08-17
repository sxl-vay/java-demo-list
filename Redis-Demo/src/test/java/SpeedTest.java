import lombok.var;

import java.util.Random;

public class SpeedTest {
	public static Random rand =  new Random(0);
	/**
	* Arguments [thread_count] [coefficient]
	*      thread_count: number of thread
	*      coefficient:  be ratio to the amount of task.
	*/
	public static void main(String[] args) throws Exception {
		// show help
		if(args.length > 0 && args[0].toLowerCase().equals("help"))
		{
			System.out.println("SUM, COUNT (B), TIME(s), SPEED(B/s)");
			return;
		}
		
		// get arguments
		int threadCount = args.length > 0 ? Integer.parseInt(args[0]) : 1;
		int coefficient = args.length > 1 ? Integer.parseInt(args[1]) : 1;
		
		// define variables
		double[] results = new double[threadCount]; 
		long[] counts = new long[threadCount];
		Thread[] threads = new Thread[threadCount];
		for (int i = 0; i < threadCount; i++) {
			threads[i] = new Thread("Subthread-" + i) {
				@Override
				public void run() {
					int tid = Integer.parseInt(getName().split("-")[1]);
					counts[tid] = 1048576L * (1024 + rand.nextInt(2)) * coefficient;
					results[tid] = get_sum(0, counts[tid]);
				}
				
				double get_sum(long start, long end) {
					double result = 0;
					for(long i = start; i < end; i ++)
						result += i;
					return result;
				}
			};
		}

		// start all threads
		var t1 = System.currentTimeMillis();
		for (int i = 0; i < threads.length; i++) 
			threads[i].start();

		// wait threads to finish
		for (int i = 0; i < threads.length; i++) 
			threads[i].join();
		var t2 = System.currentTimeMillis();
		
		// sum all results
		double total_sum = 0;
		double total_count = 0;
		for (int i = 0; i < threads.length; i++){
			total_sum += results[i];
			total_count += counts[i];
		}
		
		// format and output results
		total_count /= 1E9; // in Billions
		double time = (t2 - t1)/1000.0;
		var speed = total_count / time;
		System.out.printf("%8.3E, %8.3f, %8.3f, %8.3f.\n", total_sum, total_count, time, speed);
	}	
}
