---
theme: default
class: 'text-center'
transition: slide-left
title: Day 5
exportFilename: Day_05.pdf
mdc: true
---

# Day 5

# There is no magic, every convenience is just an abstraction

<div class="pt-12">
  <span @click="$slidev.nav.next" class="px-2 py-1 rounded cursor-pointer" flex="~ justify-center items-center gap-2" hover="bg-white bg-opacity-10">
    Press Space for next page <div class="i-carbon:arrow-right inline-block"/>
  </span>
</div>

---
layout: default
---

# Table of contents

<Toc columns=3></Toc>

---

# Counting Towers

```java
import java.util.HashMap;
import java.util.Scanner;

public class CountingTowers {

    static HashMap<Long, TotalWays> dp = new HashMap<>();
    static long mod = 1000000007;

    // TotalWays is the count of ttotal number of ways we can
    // constuct a tower of height n.
    // width 1 ends with length 1,1. 🟩🟥
    // width 2 ends with 2. 🟩🟩
    static class TotalWays {
        long width1;
        long width2;

        TotalWays(long width1, long width2) {
            this.width1 = width1;
            this.width2 = width2;
        }

        @Override
        public String toString() {
            return "width1: " + width1 + " width2: " + width2;
        }
    }

    static TotalWays solve(long n) {
        if (n==1) return new TotalWays(1, 1);

        // memoize
        if (dp.containsKey(n)) {
            return dp.get(n);
        }

        // Ways in which we can solve n-1 height tower.
        var prev = solve(n-1);

        /*
          🟥🟥  🟥🟦      🟥🟦
          🟥🟥  🟥🟦      🟪🟦
        */
        var withWidth1 = (prev.width1 * 4 + prev.width2) % mod;

        /*
          🟥🟦      🟥🟦  🟥🟥  🟥🟦  🟥🟥
          🟥🟪      🟩🟪  🟩🟪  🟩🟩  🟩🟩
        */
        var withWidth2 = (prev.width1 + prev.width2 * 2) % mod;
        var res = new TotalWays(withWidth1, withWidth2);
        dp.put(n, res);
        return res;
    }

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var testcases = sc.nextLong();
        for (long i = 0; i < testcases; i++) {
            var height = sc.nextLong();
            var res = solve(height);
            System.out.println((res.width1 + res.width2) % mod);
        }
    }
}
```

---

## Agenda

- Java APIs (STL, syntax and more) (part 1)

---

## Namespace

- It's a group.
- In Java, the first level of group is the package.

---

## Access modifier

- Access modifiers: `public`, `protected`, `private`, `default`


Here's a breakdown of Java's access modifiers and their visibility levels:

| Modifier | Class | Package | Subclass | World |
|----------|--------|----------|-----------|--------|
| private | ✓ | ✗ | ✗ | ✗ |
| default (no modifier) | ✓ | ✓ | ✗ | ✗ |
| protected | ✓ | ✓ | ✓ | ✗ |
| public | ✓ | ✓ | ✓ | ✓ |

---

## Object Oriented Programming

- There is blueprint called class.
- From each blueprint, we can create concrete stuff called objects.

---

## Static / final keyword

Attributes which are associated with the class.

```java
class Car {
    enum Color {
        Red,
        Green,
        Blue
    }

    Color color;
    int seatCount;
    final static int wheelCount = 4;

    Car(Color color, int seatCount) {
        this.color = color;
        this.seatCount = seatCount;
    }

}

public class CarExample {

    public static void main(String[] args) {
        var c = new Car(Car.Color.Blue, 4);
        System.out.println(Car.wheelCount);
//        Car.wheelCount = 10;
    }
}
```

---

## Static / Non-static

```java
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
```
