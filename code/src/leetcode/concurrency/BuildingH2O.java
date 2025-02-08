package leetcode.concurrency;


import java.util.concurrent.Semaphore;

public class BuildingH2O {
    private static class H2O {

        private Semaphore hLock, oLock;
        private int hCount;
        public H2O() {
            hCount = 0;
            hLock = new Semaphore(2);
            oLock = new Semaphore(0);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hLock.acquire();
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            hCount++;
            if (hCount % 2 == 0) {
                oLock.release();
            }
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {

            oLock.acquire();
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            hLock.release(2);
        }
    }
}
