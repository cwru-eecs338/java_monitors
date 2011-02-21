import java.lang.Thread;

public class PhilosopherThread extends Thread {
    
    PhilosopherTable table;
    int philosopher;

    public PhilosopherThread(PhilosopherTable table, int philosopher) {
        this.table = table;
        this.philosopher = philosopher;
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            try {
                think();
                table.pickup(philosopher);
                eat();
                table.putdown(philosopher);
            } catch (InterruptedException ie) {
                break;
            }
        }
    }

    private void think()
    throws InterruptedException {
        // Think for some time
        random_sleep(500);
    }

    private void eat()
    throws InterruptedException {
        // Eat for some time
        random_sleep(250);
    }

    private void random_sleep(int max)
    throws InterruptedException {
        // Eat for some time
        long milliseconds = (long)(max*Math.random());
        Thread.sleep(milliseconds);
    }

}
