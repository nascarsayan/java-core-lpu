package leetcode.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    private final Semaphore diningLimit;
    private final Semaphore[] forks;
    private final static int n = 5;
    public DiningPhilosophers() {
        diningLimit = new Semaphore(n-1);
        forks = new Semaphore[n];
        for (int i = 0; i < n; i++) {
            forks[i] = new Semaphore(1);
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int l = philosopher, r = (philosopher+1) % n;
        if (l == 0) {
            l = r;
            r = 0;
        }
        diningLimit.acquire();
        try {
            forks[l].acquire();
            pickLeftFork.run();
            forks[r].acquire();
            pickRightFork.run();

            eat.run();

            putLeftFork.run();
            forks[l].release();
            putRightFork.run();
            forks[r].release();
        } finally {
            diningLimit.release();
        }
    }

    private static class Main {
        static class Action {
            int philosopherId;
            int forkAction; // 1: left, 2: right
            int operation;  // 1: pick, 2: put, 3: eat

            Action(int philosopherId, int forkAction, int operation) {
                this.philosopherId = philosopherId;
                this.forkAction = forkAction;
                this.operation = operation;
            }

            @Override
            public String toString() {
                return String.format("[%d,%d,%d]", philosopherId, forkAction, operation);
            }
        }

        static List<Action> actions = new ArrayList<>();

        public static void main(String[] args) throws Exception {
            int n = 1; // number of times each philosopher eats
            DiningPhilosophers dp = new DiningPhilosophers();

            // Create runnables for each action
            Runnable[][] runnables = new Runnable[5][5];
            for (int i = 0; i < 5; i++) {
                final int phil = i;

                // Pick left fork
                runnables[i][0] = () -> {
                    synchronized(actions) {
                        actions.add(new Action(phil, 1, 1));
                    }
                };

                // Pick right fork
                runnables[i][1] = () -> {
                    synchronized(actions) {
                        actions.add(new Action(phil, 2, 1));
                    }
                };

                // Eat
                runnables[i][2] = () -> {
                    synchronized(actions) {
                        actions.add(new Action(phil, 0, 3));
                    }
                };

                // Put left fork
                runnables[i][3] = () -> {
                    synchronized(actions) {
                        actions.add(new Action(phil, 1, 2));
                    }
                };

                // Put right fork
                runnables[i][4] = () -> {
                    synchronized(actions) {
                        actions.add(new Action(phil, 2, 2));
                    }
                };
            }

            // Create and start philosopher threads
            CompletableFuture<?>[] futures = new CompletableFuture[5];
            for (int i = 0; i < 5; i++) {
                final int phil = i;
                futures[i] = CompletableFuture.runAsync(() -> {
                    for (int j = 0; j < n; j++) {
                        try {
                            dp.wantsToEat(phil,
                                    runnables[phil][0],  // pickLeftFork
                                    runnables[phil][1],  // pickRightFork
                                    runnables[phil][2],  // eat
                                    runnables[phil][3],  // putLeftFork
                                    runnables[phil][4]   // putRightFork
                            );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            // Wait for all philosophers to finish
            CompletableFuture.allOf(futures).join();

            // Print results
            System.out.println(actions);

            // Verify no deadlocks occurred
            System.out.println("Total actions: " + actions.size());
            System.out.println("Expected actions: " + (n * 5 * 5)); // 5 philosophers * 5 actions * n times
        }
    }
}

