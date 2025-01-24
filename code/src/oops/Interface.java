package oops;

public class Interface {

    class Organism {
        int lifespan;
    }

    interface Animal {
        void eat();
        void sleep();
    }

    interface Dangerous {
        void attack();
        void sleep();
    }

    class Dog extends Organism implements Animal, Dangerous {
        public void eat() {
            System.out.println("eating bread...");
        }
        public void sleep() {
            System.out.println("sleeping...");
        }

        public void attack() {
            System.out.println("attack...");
            var z = this.lifespan;
        }
    }
}
