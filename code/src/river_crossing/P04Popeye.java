package river_crossing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class P04Popeye {
    // State represents the items present in the Left-Hand Side of the river.
    // The State on the Right-Hand Side can be calculated by
    // checking which items are not present in the LHS.
    private static class State {
        // n is the total number of people.
        final static int n = 5;
        // timeTaken[i] is time taken by ith person.
        static int[] timeTaken;

        // isPresent[i] is true if ith person is present in LHS of river.
        boolean[] isPresent;

        // remainingTime is the remaining time.
        int remainingTime = 30;

        // boatPresent specifies if the boat is present in the LHS of river.
        boolean boatPresent;
        State() {
            timeTaken = new int[n];
            timeTaken[0] = 1;
            timeTaken[1] = 3;
            timeTaken[2] = 6;
            timeTaken[3] = 8;
            timeTaken[4] = 12;
            isPresent = new boolean[n];
            for (int i = 0; i < n; i++) {
                isPresent[i] = true;
            }
            boatPresent = true;
        }

        State(State o) {
            this.isPresent = o.isPresent.clone();
            this.boatPresent = o.boatPresent;
            this.remainingTime = o.remainingTime;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true; // Same reference
            if (obj == null || getClass() != obj.getClass()) return false; // Null or different class
            State other = (State) obj;
            for (int i = 0; i < n; i++) {
                if (isPresent[i] != other.isPresent[i]) return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(isPresent);
        }

        @Override
        public String toString() {
            StringBuilder lhs = new StringBuilder();
            StringBuilder rhs = new StringBuilder();

            for (int i = 0; i < n; i++) {
                if (isPresent[i]) {
                    lhs.append(timeTaken[i]).append("s ");
                } else {
                    rhs.append(timeTaken[i]).append("s ");
                }
            }

            return lhs.toString().trim() + " | " + rhs.toString().trim();
        }

        ArrayList<State> next() {
            ArrayList<State> states = new ArrayList<>();
            // either a single person moves to the other side
            // or both the persons move.
            // boatPresent and isPresent[i] -> switchable.
            // not(boatPresent) and not(isPresent[i]) -> switchable.
            for (int i1 = 0; i1 < n; i1++) {
                if (isPresent[i1] != boatPresent) continue;
                var s2 = new State(this);
                s2.isPresent[i1] = !s2.isPresent[i1];
                s2.boatPresent = !s2.boatPresent;
                s2.remainingTime -= timeTaken[i1];
                // just the person at i1 index crosses the river.
                states.addLast(s2);
                for (int i2 = i1+1; i2 < n; i2++) {
                    if (isPresent[i2] != boatPresent) continue;
                    var s3 = new State(s2);
                    s3.isPresent[i2] = !s3.isPresent[i2];
                    // if person at i2 is slower than person at i1,
                    // more time is taken to cross the river.
                    if (timeTaken[i2] > timeTaken[i1])
                        s3.remainingTime -= (timeTaken[i2] - timeTaken[i1]);
                    // persons at i1 and i2 cross the river.
                    states.addLast(s3);
                }
            }
            return states;
        }

        // Checks if current state is valid or not.
        boolean isValid() {
            return remainingTime >= 0;
        }

        // return if this is the final state.
        boolean isFinalState() {
            for (int i = 0; i < n; i++) {
                if (isPresent[i]) return false;
            }
            return true;
        }
    }

    private final HashSet<State> visited = new HashSet<>();

    boolean recurse(State state, ArrayList<State> path) {
        visited.add(state);
        if (state.isFinalState()) return true;
        var nextStates = state.next();
        for (var nextState : nextStates) {
            if (visited.contains(nextState)) continue;
            if (!nextState.isValid()) continue;
            // Add new move.
            path.addLast(nextState);
            boolean done = recurse(nextState, path);
            if (done) return true;
            // Reset the move.
            path.removeLast();
        }
        return false;
    }

    public static void main(String[] args) {
        var initialState = new State();
        var path = new ArrayList<State>();
        path.addLast(initialState);
        (new P04Popeye()).recurse(initialState, path);
        for (var state: path) {
            System.out.println(state);
        }
    }
}
