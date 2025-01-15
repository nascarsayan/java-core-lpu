import java.util.ArrayList;
import java.util.Collections;

public class Sample {

    public static void main(String[] args) {
        // Upper limit for the primes we want to check for.
        int maxVal = 30;

        // create a static sized (31) length arraylist of boolean with default value of true.
        // We dont need to initialize the values if we use arraylist.
        var isPrime = new ArrayList<Boolean>(Collections.nCopies(maxVal+1, true));

        // c++ equivalent.
        // auto x = vector<bool>(maxVal+1, true);

        for (int num = 2; num <= maxVal; num++) {
            if (!isPrime.get(num)) continue;
            for (int multiple = num*2;
                 multiple <= maxVal; multiple += num) {
                isPrime.set(multiple, false);
            }
        }

        // Printing primes
        for (int num = 2; num <= maxVal; num++) {
            if (isPrime.get(num)) System.out.println(num);
        }

    }
}