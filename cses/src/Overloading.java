public class Overloading {
    int sum(int a, int b) {
        return a + b;
    }

    int sum(int a, int b, int c) {
        return a + b + c;
    }

    double sum(double a, double b) {
        return (a + b) * 2;
    }

//    int sum(double a, double b) {
//        return (int)(a + b);
//    }

    public static void main(String[] args) {
        var s = new Overloading();
        System.out.println(s.sum(10.0, (int)20)); // 30
        System.out.println(s.sum(10, 20, 50)); // 60
        System.out.println(s.sum(10.5, 20.5)); // 31.0
    }
}
