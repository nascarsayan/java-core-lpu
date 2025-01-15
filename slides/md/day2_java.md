---
theme: default
class: 'text-center'
transition: slide-left
title: Day 1 - Java Basics
mdc: true
---

Hello

---
layout: default
---

# Table of contents


<Toc maxDepth="1"></Toc>

---

# Hello World in Java

```java
public class HelloWorld {
  public static void main(String[] args) {
    System.out.println("Hello, world!");
  }
}
```

---

# Java Basics

## Data Type sizes

| Type | Size (in bytes) |
| --- | --- |
| byte | 1 |
| short | 2 |
| int | 4 |
| long | 8 |
| float | 4 |
| double | 8 |
| boolean | 1 |
| char | 2 |

---

## Signed vs Unsigned Types in Java

Java has two types of signed integers: `int` and `long`. The difference is that the size of a `long` is twice as large as an `int`.

---

# Range of values which can be be stored in a variable of a type

### Calculation (unsigned integer)

Suppose size of the type is N bits.
The total number of values that can be stored in a variable is 2<sup>N</sup>.

`0000000` -> 8 times (lowest)

The value is 0.

`1 1 1 1 1 1 1` -> 8 times (highest)

= 2<sup>8</sup> - 1
= 256 - 1 = 255

---

## Calculation (signed integer)

For 8 bits, there are 256 positions.
- 256/2 = 128
There is 0
128 <0> 127

The range is -128 to 127.

We had 8 bits.
2^(8) = 256
2^(8-1) = 128

-2^(8-1) to 2^(8-1) - 1 = -128 to 127.

For generic N bits,

- -(2^(N-1)) to 2^(N-1)-1

---

## Types and size in Rust (aside)

Just for comparison, here are the sizes of some types in Rust:

| Type | Size (in bytes) |
| --- | --- |
| u8 | 1 |
| i8 | 1 |
| u16 | 2 |
| i16 | 2 |
| u32 | 4 |
| i32 | 4 |
| u64 | 8 |
| i64 | 8 |
| f32 | 4 |
| f64 | 8 |
| bool | 1 |
| char | 2 |

---

## Unicode

Unicode is a way to represent characters in computers.

UTF-16 can have values from 2^0 to 2^16 - 1, which is 65,536. This means that it can represent 65,536 different characters.

All differect UTF encodings:
1. UTF-8: 7 bits per character (most common)
2. UTF-16: 16 bits per character (used by most modern systems)
3. UTF-32: 32 bits per character (used for Unicode code points)

---

## Unicode encoding

```java
public class HelloWorld {
    public static void main(String[] args) {
        char ch = 0;
//        System.out.println((int)(Character.MAX_VALUE));
        for (; ch <= Short.MAX_VALUE; ch++) {
            System.out.printf("%d = %c\n", (int)ch, ch);
        }
    }
}
```

---

