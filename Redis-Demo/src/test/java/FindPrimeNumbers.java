import java.util.Random;

public class FindPrimeNumbers {

	public static Random rand = new Random(0);

	/**
	 * Arguments [thread_count] [coefficient] thread_count: number of thread
	 * coefficient: be ratio to the amount of task.
	 */
	public static void main(String[] args) throws Exception {
		// show help
		if (args.length > 0 && args[0].toLowerCase().equals("help")) {
			System.out.println("SUM, COUNT (B), TIME(s), SPEED(B/s)");
			return;
		}

		// get arguments
		int threadCount = args.length > 0 ? Integer.parseInt(args[0]) : 20;
		int coefficient = args.length > 1 ? Integer.parseInt(args[1]) : 1;

		// check numbers between 1b and 2b.
		long startNumber = 1_000_000_000L;
		long totalRange = 10_000_000 * coefficient;
		long interval = totalRange / threadCount;

		// define variables
		double[] results = new double[threadCount];
		Thread[] threads = new Thread[threadCount];
		for (int i = 0; i < threadCount; i++) {
			threads[i] = new Thread("Subthread-" + i) {
				@Override
				public void run() {
					int tid = Integer.parseInt(getName().split("-")[1]);
					long start = startNumber + interval * tid;
					long end = tid == threadCount - 1 ? startNumber + totalRange : startNumber + interval * tid + interval;
					int primeNo = 0;
					// System.out.printf("id: %d, start=%d, end=%d.\n", tid, start, end);
					for (long i = start; i < end; i++)
						if (isPrimeNumber(i))
							primeNo++;
					results[tid] = primeNo;
				}

				boolean isPrimeNumber(long n) {
					if (n < 2 || n % 2 == 0)
						return false;
					long m = (long) Math.sqrt(n) + 1;
					for (long i = 3; i < m; i += 2) 
						if (n % i == 0) 
							return false;
					return true;
				}

			};
		}

		// start all threads
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < threads.length; i++)
			threads[i].start();

		// wait threads to finish
		for (int i = 0; i < threads.length; i++)
			threads[i].join();
		long t2 = System.currentTimeMillis();

		// sum all results
		long total_sum = 0;
		for (int i = 0; i < threads.length; i++) {
			total_sum += results[i];
		}

		// format and output results
		double time = (t2 - t1) / 1000.0;
		double speed = totalRange / time /1000;
		System.out.printf("# of Primes=%d, total=%d, time=%6.2fs, score=%5.0f.\n", total_sum, totalRange, time, speed);
	}
}
