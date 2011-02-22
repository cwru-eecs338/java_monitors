
public class DiningPhilosophers {

    public static final int PHILOSOPHERS = 20;
    public static final long DURATION = 5000/*ms*/;

    /*
     * Two optional arguments:
     * 1st argument: number of philosophers
     * 2nd argument: duration in seconds
     */
    public static void main(String[] args)
    throws InterruptedException {

        // Parse arguments
        int philosophers = PHILOSOPHERS;
        long duration = DURATION;
        try {
            philosophers = Integer.parseInt(args[0]);
            duration = 1000*Integer.parseInt(args[1]);
        } catch (Exception e) { }

        // Set the table
        PhilosopherTable table = new PhilosopherTable(philosophers);

        // Create/Start a thread
        // for each philosopher
        PhilosopherThread[] threads = new PhilosopherThread[philosophers];
        for(int i = 0; i < philosophers; i++) {
            threads[i] = new PhilosopherThread(table, i);
            threads[i].start();
        }

        // Wait for them to have profound
        // epistemological insights, etc.
        try {
            Thread.sleep(duration);
        } catch(Exception e) {
            e.printStackTrace();
        }

        // Time's up...
        for(int i = 0; i < philosophers; i++) {
            threads[i].interrupt();
            threads[i].join();
        }
    }

}
