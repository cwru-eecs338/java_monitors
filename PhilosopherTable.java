
public class PhilosopherTable {

    private static enum State {
        HUNGRY("H"),
        THINKING("T"),
        EATING("E");

        public String str;
        private State(String str) {
            this.str = str;
        }
    }

    int seats;
    State[] state;

    public PhilosopherTable(int seats) {
        this.seats = seats;
        state = new State[seats];
        for(int i = 0; i < seats; i++) {
            state[i] = State.THINKING;
        }
        printStates();
    }

    public synchronized void pickup(int i)
    throws InterruptedException {
        setState(i, State.HUNGRY);
        test(i);
        while (state[i] != State.EATING) {
            this.wait();
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
            this.notifyAll();
        }
    }

    private int left(int i) {
        return (i + seats - 1) % seats;
    }

    private int right(int i) {
        return (i + 1) % seats;
    }

    private void setState(int i, State newState) {
        state[i] = newState;
        sanityCheck();
        printStates();
    }

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
