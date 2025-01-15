public class HelloWorld {
    public static void main(String[] args) {
        char ch = 0;
//        System.out.println((int)(Character.MAX_VALUE));
        for (; ch <= Short.MAX_VALUE; ch++) {
            System.out.printf("%d = %c\n", (int)ch, ch);
        }
    }
}