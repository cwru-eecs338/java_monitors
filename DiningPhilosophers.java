
public class DiningPhilosophers {

    public static final int PHILOSOPHERS = 20;
    public static final long DURATION = 5000/*ms*/;

    public static void main(String[] args)
    throws InterruptedException {

        // Set the table
        PhilosopherTable table = new PhilosopherTable(PHILOSOPHERS);

        // Create/Start a thread
        // for each philosopher
        PhilosopherThread[] threads = new PhilosopherThread[PHILOSOPHERS];
        for(int i = 0; i < PHILOSOPHERS; i++) {
            threads[i] = new PhilosopherThread(table, i);
            threads[i].start();
        }

        // Wait for them to have profound
        // epistemological insights, etc.
        try {
            Thread.sleep(DURATION);
        } catch(Exception e) {
            e.printStackTrace();
        }

        // Time's up...
        for(int i = 0; i < PHILOSOPHERS; i++) {
            threads[i].interrupt();
            threads[i].join();
        }
    }

}
