---
theme: default
class: 'text-center'
transition: slide-left
title: Day 7
exportFilename: Day_07.pdf
mdc: true
---

# Day 7

## There is no magic, every convenience is just an abstraction

<div class="pt-12">
  <span @click="$slidev.nav.next" class="px-2 py-1 rounded cursor-pointer" flex="~ justify-center items-center gap-2" hover="bg-white bg-opacity-10">
    Press Space for next page <div class="i-carbon:arrow-right inline-block"/>
  </span>
</div>

---
layout: default
---

## Table of contents

<Toc columns=3></Toc>

---

## Agenda

- lambdas and method references, custom sorting
- garbage collection
- trees
- self balancing trees (red-black tree)
- priority queue
- real world usage of interfaces

---

## Lambda Expressions

- Lambda expressions are used to define anonymous methods.
- Suppose some function call requires you to pass another function as an argument, you can use lambda expressions to define the function on the fly.
- Example: `Arrays.sort(arr, (a, b) -> b - a);` sorts the array in descending order.

```java
import java.util.Arrays;
public class Lambdas {
    public static void main(String[] args) {
        Integer[] arr = {1, 4, 2, 10, -2};
        Arrays.sort(arr, (a, b) -> b -a);
    }
}

```

---

## :: operator in Java 8

Learn more: https://stackoverflow.com/a/22245383

`::` operator in Java 8 is used to refer to a method reference. It is a reference to a method without invoking it. It is used to point the method by method reference.

```java
import java.util.Arrays;
public class Lambdas {
    public static int comparator(int a, int b) {
        return b - a;
    }
    public static void main(String[] args) {
        Integer[] arr = {1, 4, 2, 10, -2};
        Arrays.sort(arr, Lambdas::comparator);
    }
}
```

---

## Custom Sorting in Java (using Lambda)

We must provide our custom comparator function while sorting an array of custom class' objects.

**We want to sort a bunch øf students according to the following criteria:**
- Marks in descending order (Higher marks gets the upper hand)
- Income in ascending order (Lower income gets the upper hand)
- Age in descending order (Higher age gets the upper hand)

```java
import java.util.Arrays;

class Person {
    int age;
    int marks;
    int incomeUSD;

    Person(int age, int marks, int incomeUSD) {
        this.age = age;
        this.marks = marks;
        this.incomeUSD = incomeUSD;
    }

    @Override
    public String toString() {
        return String.format("marks = %d%%, income = %d USD, age = %d yrs", this.marks, this.incomeUSD, this.age);
    }
}

public class Lambdas {

    public static void main(String[] args) {
        Person[] students = new Person[4];
        students[0] = new Person(18, 90, 5000);
        students[1] = new Person(18, 90, 4000);
        students[2] = new Person(20, 90, 4000);
        students[3] = new Person(20, 88, 4000);

        Arrays.sort(students, (a, b) -> {
            if (a.marks != b.marks) return b.marks - a.marks;
            if (a.incomeUSD != b.incomeUSD) return a.incomeUSD - b.incomeUSD;
            return b.age - a.age;
        });
        for (var s: students) System.out.println(s);

        // Below will not work.
        // Arrays.sort(students);
        // for (var s: students) System.out.println(s);
    }
}
```

---

## Custom Sorting in Java (using Method reference)

```java
import java.util.Arrays;

class Person {
    int age;
    int marks;
    int incomeUSD;

    Person(int age, int marks, int incomeUSD) {
        this.age = age;
        this.marks = marks;
        this.incomeUSD = incomeUSD;
    }

    static int compare(Person a, Person b) {
        if (a.marks != b.marks) return b.marks - a.marks;
        if (a.incomeUSD != b.incomeUSD) return a.incomeUSD - b.incomeUSD;
        return b.age - a.age;
    }

    @Override
    public String toString() {
        return String.format("marks = %d%%, income = %d USD, age = %d yrs", this.marks, this.incomeUSD, this.age);
    }
}

public class Lambdas {

    public static void main(String[] args) {
        Person[] students = new Person[4];
        students[0] = new Person(18, 90, 5000);
        students[1] = new Person(18, 90, 4000);
        students[2] = new Person(20, 90, 4000);
        students[3] = new Person(20, 88, 4000);

        Arrays.sort(students, Person::compare);
        for (var s: students) System.out.println(s);

        // Below will not work.
        // Arrays.sort(students);
        // for (var s: students) System.out.println(s);
    }
}
```

---

## 


---

## Binary Tree

- A binary tree is a tree data structure in which each node has at most two children, which are referred to as the left child and the right child.
- A binary tree is a special case of a K-ary tree, where k is 2.
- The root of the tree is the first node in the tree.
- The height of a binary tree is the number of edges between the root and the deepest leaf node.