package leetcode.concurrency;

import java.util.concurrent.Semaphore;

public class FooBarAlternative {
    private static class FooBar {
        private final int n;
        private final Semaphore fooLock, barLock;

        public FooBar(int n) {
            this.n = n;
            fooLock = new Semaphore(1);
            barLock = new Semaphore(0);
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                fooLock.acquire();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                barLock.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                barLock.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                fooLock.release();
            }
        }
    }
}
