---
theme: default
class: 'text-center'
transition: slide-left
title: Day 4
exportFilename: Day_04.pdf
mdc: true
---

### Day 4

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

# Agenda

- Install exercism CLI by following the steps.
- Solve a few exercises on [Exercism](https://exercism.org/tracks/java) to get acquainted with a few java concepts.
- Exercism is too involved installation, can be done after class too, so shifting to CSES.

---

## Weird Algorithm

1. We need to use long, not int, because the value of N can keep on increasing to large values.
2. Make sure the class name does not have any special characters (else might get invalid source name error).

```java
import java.util.Scanner;

public class Weird {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var n = sc.nextLong();
        while (n > 1) {
            System.out.print(n + " ");
            if (n % 2 == 0) n /= 2;
            else n = n*3+1;
        }
        System.out.println(n);
    }
}
```

## Missing Number (method 1)

- Sort the numbers and find the missing number.

```java
import java.util.Arrays;
import java.util.Scanner;

public class MissingNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n - 1; i++) {
            arr[i] = sc.nextInt();
        }
        arr[n-1] = 0;
        Arrays.sort(arr);
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[i-1] + 1) {
                System.out.println(arr[i-1]+1);
                return;
            }
        }
        System.out.println(n);
    }
}
```

## Missing Number (method 2)

```java
import java.util.Scanner;

public class MissingNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var n = sc.nextLong();
        long sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += sc.nextLong();
        }

        long sigmaN = (n * (n+1)) / 2;
        System.out.println(sigmaN - sum);
    }
}
```

---

2* 10^5 = 200000
1000 ~ 2^10
log(10^3) ~ log(2^10) ~ 10

log(200000) ~ 20 (= 17)

n log n = 20 * 10^5 * 2 = 4 * 10^6

---

## Repetitions

```java
import java.util.Scanner;

public class Repetitions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var s = sc.next();
        s += "X";
        int res = 1, curr = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i-1)) {
                res = Math.max(res, curr);
                curr = 1;
                continue;
            }
            curr++;
        }
        System.out.println(res);
    }
}
```

## Linked List

```java
class Node {
    int val;
    Node next;
    
    Node(int _val) {
        val = _val;
    }
    
}

public class LinkedList {
    public static void main(String[] args) {
        var head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        
        var curr = head;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
        
    }
}
```

## Doubly Linked List

```java
class Node {
    int val;
    Node next, prev;

    Node(int _val, Node prev) {
        val = _val;
        this.prev = prev;
    }

}

public class LinkedList {
    public static void main(String[] args) {
        var head = new Node(10, null);
        head.next = new Node(20, head);
        var last = head.next.next = new Node(30, head.next);

        var curr = last;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.prev;
        }

    }
}
```

## Prefix Tree (Trie)

```java
import java.util.ArrayList;

public class PrefixTree {
    static class Node {
        char ch;
        ArrayList<Node> children;
        boolean ends;
        // count of number of elements which start with the given prefix.
        int count;

        Node(char ch) {
            this.ch = ch;
            children = new ArrayList<>();
            count = 1;
        }
    }

    public static void main(String[] args) {
        var start = new Node('@');
        String[] arr = {
                "abcd",
                "abc",
                "abxy",
                "abdf",
                "ab",
                "acd"
        };

        for (var el: arr) {
            var curr = start;
            for (var ch: el.toCharArray()) {
                // check if curr contains ch or not.
                Node next = null;
                for (var child: curr.children) {
                    if (child.ch == ch) {
                        next = child;
                        child.count++;
                    }
                }
                // if it does not, then add it.
                if (next == null) {
                    next = new Node(ch);
                    curr.children.add(next);
                }
                curr = next;
                if (curr.ch == 'b') {
                    System.out.println(curr.count);
                    System.out.println(el);
                }
            }
            curr.ends = true;
        }

        // How many strings start with ab; = 5
        var target = "ab";
        var res = 0;
        var curr = start;
        for (var ch: target.toCharArray()) {
            Node next = null;
            for (var child: curr.children) {
                if (child.ch == ch) next = child;
            }
            if (next == null) {
                curr = null;
                break;
            }
            curr = next;
        }
        if (curr == null) System.out.println(0);
        else System.out.println(curr.count);
    }
}
```