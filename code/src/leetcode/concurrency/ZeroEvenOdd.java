package leetcode.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;


public class ZeroEvenOdd {
    private int n, currEven, currOdd, zeroCount;
    private final Semaphore zero, odd, even;

    public ZeroEvenOdd(int n) {
        this.n = n;
        currOdd = 1;
        currEven = 2;
        zeroCount = 0;
        zero = new Semaphore(1);
        odd = new Semaphore(0);
        even = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (zeroCount < n) {
            zero.acquire();
            printNumber.accept(0);
            zeroCount++;
            if (zeroCount % 2 == 1) {
                // odd number
                odd.release();
            } else {
                // even number
                even.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (currEven <= n) {
            even.acquire();
            printNumber.accept(currEven);
            currEven += 2;
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (currOdd <= n) {
            odd.acquire();
            printNumber.accept(currOdd);
            currOdd += 2;
            zero.release();
        }
    }
}

