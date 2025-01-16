---
theme: default
class: 'text-center'
transition: slide-left
title: Day 3
mdc: true
---

### Day 3

<div class="pt-12">
  <span @click="$slidev.nav.next" class="px-2 py-1 rounded cursor-pointer" flex="~ justify-center items-center gap-2" hover="bg-white bg-opacity-10">
    Press Space for next page <div class="i-carbon:arrow-right inline-block"/>
  </span>
</div>

---
layout: default
---

# Table of contents


<Toc maxDepth="1"></Toc>

---

### Agenda

- Compilation and Running process
- Compiled vs Interpreted languages
- On top of JVM what languages are popular and used where?
- Packaging / modules in Java (vs other programming languages)

---

## Compilation and running process

Computer needs assembly code to be run on the CPU.
Assembly language is a low level language that can be directly understood by the computer.
Different levels of abstration were built with the pass of time.

Newer languages with higher levels of abstration gradually came to the market.

- Golang is beginner friendly syntax, and we are getting higher traction in backend code development using this language.
- Rust is very efficient, but it's not a beginner-friendly. Memory-safe language compared to C/C++.
- Swift is used by Apple for creating apps. It can also be used to write backend code. It's one of the most elegant languages.
- Kotlin syntax is similar to Swift (some features were influenced from Swift). It runs on JVM.

---

## Compiled languages

- A code in C / C++ / Golang / Rust
- Compiler will take the code as input and output a binary.
- This binary can **only** be executed on 
   + the same OS
   + the same CPU architecture.
  OS : Mac, Linux, Windows, BSD, Android, IOS, Arduino, etc.
  CPU: Raspberry Pi, Intel, AMD, ARM, ARM64, etc. AARCH.
  We cannot run a binary meant to be run on (CPU C1, OS O1) on a different machine (C2, O2).

---

## Interpreted languages

- A code
- Interpreter always requires a 2-step process.
- First step: Src Code -> Byte Code.
  This is done by compiler.
- Second step: Byte Code -> Machine Code.
  This is done by interpreter. (JIT compiler / JVM)
  This causes the program to run on any machine that has an interpreter.

---

## How a Java code is ultimately executed by the device.

Java is neither completely compiled nor interpreted. It's a mix of both.
- Java code is compiled to bytecode using `javac`.
- Bytecode is then interpreted by JVM (JIT compiler) which in turn generates machine code for the CPU.
- Typically compiled / half-compiled languages are faster than interpreted languages. The whole reason being that the compiler can efficiently using the stack in function calls.

---

