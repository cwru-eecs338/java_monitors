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
        // This code is executed when
        // the thread is started...
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
        // Think for some time...
        // "What doth life?"
        random_sleep(500);
    }

    private void eat()
    throws InterruptedException {
        // Eat for some time
        random_sleep(250);
    }

    private void random_sleep(int max)
    throws InterruptedException {
        long milliseconds = (long)(max*Math.random());
        Thread.sleep(milliseconds);
    }

}
