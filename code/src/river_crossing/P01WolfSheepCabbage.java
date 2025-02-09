package river_crossing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

// P01 ->

public class P01WolfSheepCabbage {
    // State represents the items present in the Left-Hand Side of the river.
    // The State on the Right-Hand Side can be calculated by
    // checking which items are not present in the LHS.
    private static class State {
        boolean manPresent;
        // Wolf, sheep, cabbage.
        boolean[] wsc;
        State() {
            manPresent = true;
            wsc = new boolean[3];
            for (int i = 0; i < 3; i++) {
                wsc[i] = true;
            }
        }

        State(State o) {
            this.manPresent = o.manPresent;
            this.wsc = o.wsc.clone();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true; // Same reference
            if (obj == null || getClass() != obj.getClass()) return false; // Null or different class
            State other = (State) obj;
            return manPresent == other.manPresent &&
                    Objects.equals(wsc[0], other.wsc[0]) &&
                    Objects.equals(wsc[1], other.wsc[1]) &&
                    Objects.equals(wsc[2], other.wsc[2]);
        }

        @Override
        public int hashCode() {
            return Objects.hash(manPresent, wsc[0], wsc[1], wsc[2]);
        }

        // Get next states from current state.
        ArrayList<State> next() {
            ArrayList<State> states = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                // Either the man takes some object with him
                // while going to the other side of the river.
                var nextState = new State(this);
                nextState.wsc[i] = !nextState.wsc[i];
                nextState.manPresent = !nextState.manPresent;
                states.addLast(nextState);
            }
            // Or the man does not take any object with him.
            var nextState = new State(this);
            nextState.manPresent = !nextState.manPresent;
            states.addLast(nextState);
            return states;
        }

        // Checks if current state is valid or not.
        boolean isValid() {
            if (wsc[0] == wsc[1] && wsc[0] != manPresent) return false;
            if (wsc[1] == wsc[2] && wsc[1] != manPresent) return false;
            return true;
        }

        // return if this is the final state.
        boolean isFinalState() {
            return !wsc[0] && !wsc[1] && !wsc[2];
        }

//        @Override
//        public String toString() {
//            return String.format("Man:%b | Wolf:%b | Sheep:%b | Cabbage:%b\n", manPresent, wsc[0], wsc[1], wsc[2]);
//        }

        @Override
        public String toString() {
            StringBuilder lhs = new StringBuilder("");
            StringBuilder rhs = new StringBuilder("");

            // Add man to the appropriate side.
            if (manPresent) {
                lhs.append("Man ");
            } else {
                rhs.append("Man ");
            }

            // Add wolf, sheep, and cabbage to the appropriate sides.
            String[] names = {"Wolf", "Sheep", "Cabbage"};
            for (int i = 0; i < 3; i++) {
                if (wsc[i]) {
                    lhs.append(names[i]).append(" ");
                } else {
                    rhs.append(names[i]).append(" ");
                }
            }

            return lhs.toString().trim() + " | " + rhs.toString().trim();
        }
    }

    private final HashSet<State> visited = new HashSet<>();

    private boolean recurse(State state, ArrayList<State> path) {
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
        (new P01WolfSheepCabbage()).recurse(initialState, path);
        for (var state: path) {
            System.out.println(state);
        }
    }
}
