public class AbstractClass {
    abstract class Animal {
        abstract void eat();
        void sleep() {
            System.out.println("sleeping...");
        }
    }

    class Dog extends Animal {
        void eat() { // Must be implemented
            System.out.println("eating bread...");
        }
    }

    abstract class Mammal extends Animal {
        abstract void walk();
    }

}
