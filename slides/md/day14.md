---
theme: default
class: 'text-center'
transition: slide-left
title: Day 14
exportFilename: Day_14.pdf
mdc: true
---

# Day 14


<div class="pt-13">
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

- Foundational Concepts of OOP
- SOLID Principles
- Composition Over Inheritance


---

### **1. Foundational Concepts of OOP**
Before diving into advanced topics, ensure you have a solid grasp of the four core principles of OOP:

1. **Encapsulation**:
   - Bundling data (attributes) and methods (functions) that operate on the data into a single unit (class).
   - Restricting direct access to some of an object's components (using access modifiers like `private`, `protected`, etc.).

2. **Abstraction**:
   - Hiding complex implementation details and exposing only the necessary features.
   - Achieved through abstract classes and interfaces.

3. **Inheritance**:
   - Creating new classes (child classes) from existing ones (parent classes) to promote code reuse.
   - Understand the difference between single and multiple inheritance (if supported by the language).

4. **Polymorphism**:
   - The ability of objects to take on multiple forms (e.g., method overriding and method overloading).
   - Enables flexibility and dynamic behavior in code.

---

### **2. SOLID Principles**

[Full slide](../llm-outputs/OOPs/SOLID.md)
