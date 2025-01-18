import java.util.Scanner;

class A {

}

class B {

}

//class C extends A, B {
//
//}

class Animal {
    void eat() {
        System.out.println("eating...");
    }
}

class Dog extends Animal {
    void eat() {
        System.out.println("eating bread...");
    }
}

class Cat extends Animal {
    void eat() {
        System.out.println("eating fish...");
    }
}

public class Overriding {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();

        Scanner sc = new Scanner(System.in);
        var str = sc.next();

        Animal a = null;
        if (str.charAt(0) == 'a') {
            a = new Animal();
        } else if (str.charAt(0) == 'd') {
            a = new Dog();
        } else if (str.charAt(0) == 'c') {
            a = new Cat();
        }

        a.eat();

//        Dog b = new Animal(); // this won't work.
    }
}
