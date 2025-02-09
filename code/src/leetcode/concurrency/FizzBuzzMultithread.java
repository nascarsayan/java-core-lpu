package leetcode.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzzMultithread {
    private static class FizzBuzz {
        private int n;
        private Semaphore sf, sb, sfb, snum;

        public FizzBuzz(int n) {
            this.n = n;
            sf = new Semaphore(0);
            sb = new Semaphore(0);
            sfb = new Semaphore(0);
            snum = new Semaphore(1);
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for (int i = 3; i <= n; i += 3) {
                if (i % 5 == 0) continue;
                sf.acquire();
                printFizz.run();
                if ((i+1) % 5 == 0) sb.release();
                else snum.release();
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for (int i = 5; i <= n; i += 5) {
                if (i % 3 == 0) continue;
                sb.acquire();
                printBuzz.run();
                if ((i+1) % 3 == 0) sf.release();
                else snum.release();
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for (int i = 15; i <= n; i += 15) {
                sfb.acquire();
                printFizzBuzz.run();
                snum.release();
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i ++) {
                if (i % 3 == 0 || i % 5 == 0) continue;
                snum.acquire();
                printNumber.accept(i);
                if ((i+1) % 15 == 0) sfb.release();
                else if ((i+1) % 3 == 0) sf.release();
                else if ((i+1) % 5 == 0) sb.release();
                else snum.release();
            }
        }

        public static void main(String[] args) {
            int n = 15;  // Test with numbers up to 15
            FizzBuzz fizzBuzz = new FizzBuzz(n);

            // Create threads for each function
            Thread threadFizz = new Thread(() -> {
                try {
                    fizzBuzz.fizz(() -> System.out.println("fizz"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread threadBuzz = new Thread(() -> {
                try {
                    fizzBuzz.buzz(() -> System.out.println("buzz"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread threadFizzBuzz = new Thread(() -> {
                try {
                    fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread threadNumber = new Thread(() -> {
                try {
                    fizzBuzz.number(x -> System.out.println(x));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // Start all threads
            threadFizz.start();
            threadBuzz.start();
            threadFizzBuzz.start();
            threadNumber.start();

            // Wait for all threads to complete
            try {
                threadFizz.join();
                threadBuzz.join();
                threadFizzBuzz.join();
                threadNumber.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
