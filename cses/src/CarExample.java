class Car {
    enum Color {
        Red,
        Green,
        Blue
    }

    String model;
    Color color;
    int seatCount;
    final static int wheelCount = 4;

    Car(String model, Color color, int seatCount) {
        this.model = model;
        this.color = color;
        this.seatCount = seatCount;
    }

}

public class CarExample {

    static int stat;
    int nonStat;

//    CarExample() {
//
//    }

    public void methodNonStat() {

    }

    public static void methodStat() {

    }

    public void nonStatic() {
        var x = stat; // this works
        var y = this.stat; // redundant this
        methodStat();
        this.methodStat(); // redundant this
        this.methodNonStat();
    }

    public static void staticMethod() {
        var x = stat; // this works
//        var y = this.stat; // this does not work
        methodStat();
//        this.methodStat(); // not work
//        this.methodNonStat();
    }

    public static void main(String[] args) {
        var bmw = new Car("BMW", Car.Color.Blue, 4);
        System.out.println(Car.wheelCount);

        var lambo = new Car("Lambo", Car.Color.Red, 6);
        System.out.println(Car.wheelCount);
//        Car.wheelCount = 10;

        var obj = new CarExample();
        var x = obj.nonStat;

        // this will not work:
        // this.xyz
    }
}