import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // read a int from console
        int n = sc.nextInt();
        // flush the extra newline at the end of the int
        sc.nextLine();
        // read the next line
        String s = sc.nextLine();
        System.out.printf("Number is : ++%d++ String is ++%s++\n", n, s);
        // close the scanner
        sc.close();
    }
}