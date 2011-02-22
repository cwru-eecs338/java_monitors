
public class PhilosopherTable {

    /*
     * Enumeration for the
     * states of the philosophers.
     */
    private static enum State {
        HUNGRY("H"),
        THINKING("T"),
        EATING("E");

        public String str;
        private State(String str) {
            this.str = str;
        }
    }

    // Number of seats at the table
    int seats;

    // States of the philosophers
    State[] state;

    /*
     * Initialize the table and
     * philosopher states.
     */
    public PhilosopherTable(int seats) {
        this.seats = seats;
        state = new State[seats];
        for(int i = 0; i < seats; i++) {
            state[i] = State.THINKING;
        }
        printStates();
    }

    /*
     * The following methods are taken
     * almost exactly from the lecture
     * slides.
     */

    public synchronized void pickup(int i)
    throws InterruptedException {
        setState(i, State.HUNGRY);
        test(i);
        while (state[i] != State.EATING) {
            this.wait();
            // Recheck condition in loop,
            // since we might have been notified
            // when we were still hungry
        }
    }

    public synchronized void putdown(int i) {
        setState(i, State.THINKING);
        test(right(i));
        test(left(i));
    }

    private synchronized void test(int i) {
        if (state[left(i)] != State.EATING &&
            state[right(i)] != State.EATING &&
            state[i] == State.HUNGRY)
        {
            setState(i, State.EATING);
            // Wake up all waiting threads
            this.notifyAll();
        }
    }

    /*
     * Abstract away the left/right
     * philosopher at the table.
     */

    private int left(int i) {
        return (i + seats - 1) % seats;
    }

    private int right(int i) {
        return (i + 1) % seats;
    }

    /*
     * Manages setting the state of
     * a philosopher, so that we can
     * make sure it's legal, and print
     * the new state.
     */
    private void setState(int i, State newState) {
        state[i] = newState;
        sanityCheck();
        printStates();
    }

    /*
     * Print a row representing the
     * states of the philosophers.
     */
    private void printStates() {
        // Print states
        for(int p = 0; p < seats; p++) {
            switch (state[p]) {
                case EATING:
                    System.out.printf("|%s| ", state[p].str);
                    break;
                case THINKING:
                case HUNGRY:
                    if (state[right(p)] == State.EATING) {
                        System.out.printf(" %s  ", state[p].str);
                    } else {
                        System.out.printf(" %s |", state[p].str);
                    }
                    break;
            }
        }
        System.out.println();
    }

    /*
     * Just make sure we don't have two
     * consecutive philosophers eating.
     */
    private void sanityCheck() {
        for(int i = 0; i < seats; i++) {
            if(state[i] == State.EATING &&
               (state[ left(i)] == State.EATING ||
                state[right(i)] == State.EATING))
            {
                throw new IllegalStateException();
            }
        }
    }

}
