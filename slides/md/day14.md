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
    Press Space for next page <div class="i-carbon:arrow-right inline-block"></div>
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

Certainly! Below is an outline for a slide deck on **SOLID Principles in Java**, including the core meaning, intention, and code snippets. You can use this structure to create your slides.

---

### **Slide 1: Title Slide**
**Title:** SOLID Principles in Java  
**Subtitle:** Building Robust and Maintainable Software  
**Your Name/Date**

---

### **Slide 2: Introduction to SOLID Principles**
**Title:** What are SOLID Principles?  
**Content:**  
- SOLID is an acronym for five design principles intended to make software designs more understandable, flexible, and maintainable.  
- Introduced by Robert C. Martin (Uncle Bob).  
- Applicable to object-oriented programming (OOP) languages like Java.  

**SOLID Stands For:**  
1. **S**ingle Responsibility Principle (SRP)  
2. **O**pen/Closed Principle (OCP)  
3. **L**iskov Substitution Principle (LSP)  
4. **I**nterface Segregation Principle (ISP)  
5. **D**ependency Inversion Principle (DIP)  

---

### **Slide 3: Single Responsibility Principle (SRP)**
**Title:** Single Responsibility Principle (SRP)  
**Core Meaning:**  
- A class should have only one reason to change, meaning it should have only one job or responsibility.  

**Intention:**  
- Improves readability, maintainability, and testability.  
- Reduces coupling between classes.  

```mermaid
classDiagram
    class UserBad {
        +login()
        +sendEmail()
        +saveToDatabase()
    }
    
    class UserGood {
        +login()
    }
    class EmailService {
        +sendEmail()
    }
    class UserRepository {
        +saveToDatabase()
    }
    
    UserGood ..> EmailService : uses
    UserGood ..> UserRepository : uses
    
    note for UserBad "Bad: One class with multiple responsibilities"
    note for UserGood "Good: Responsibilities are separated"
```

**Code Snippet:**  
```java
// Bad Example: A class with multiple responsibilities
class User {
    void login() { /* Login logic */ }
    void sendEmail() { /* Email logic */ }
}

// Good Example: Separate responsibilities
class User {
    void login() { /* Login logic */ }
}

class EmailService {
    void sendEmail() { /* Email logic */ }
}
```

---

### **Slide 4: Open/Closed Principle (OCP)**
**Title:** Open/Closed Principle (OCP)  
**Core Meaning:**  
- Software entities (classes, modules, functions) should be open for extension but closed for modification.  

**Intention:**  
- Allows adding new features without altering existing code.  
- Promotes the use of abstractions and interfaces.  

```mermaid
classDiagram
    class Shape {
        <<interface>>
        +draw()
    }
    
    class Rectangle {
        +draw()
    }
    
    class Circle {
        +draw()
    }
    
    class Triangle {
        +draw()
    }
    
    Shape <|.. Rectangle
    Shape <|.. Circle
    Shape <|.. Triangle
    
    note for Shape "Open for extension"
    note for Rectangle "New shapes can be added\nwithout modifying existing code"
```

**Code Snippet:**  
```java
// Bad Example: Modifying existing class for new shapes
class Rectangle {
    void draw() { /* Draw rectangle */ }
}

// Good Example: Extending functionality without modifying existing code
interface Shape {
    void draw();
}

class Rectangle implements Shape {
    public void draw() { /* Draw rectangle */ }
}

class Circle implements Shape {
    public void draw() { /* Draw circle */ }
}
```

---

### **Slide 5: Liskov Substitution Principle (LSP)**
**Title:** Liskov Substitution Principle (LSP)  
**Core Meaning:**  
- Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.  

**Intention:**  
- Ensures that derived classes extend the base class without changing its behavior.  

```mermaid
classDiagram
    class Bird {
        +move()
    }
    
    class FlyingBird {
        +move()
        +fly()
    }
    
    class NonFlyingBird {
        +move()
        +walk()
    }
    
    class Eagle {
        +move()
        +fly()
    }
    
    class Ostrich {
        +move()
        +walk()
    }
    
    Bird <|-- FlyingBird
    Bird <|-- NonFlyingBird
    FlyingBird <|-- Eagle
    NonFlyingBird <|-- Ostrich
    
    note for Bird "Base class with common behavior"
    note for FlyingBird "Specialized behavior that\ndoesn't violate base class"
```

**Code Snippet:**  
```java
// Bad Example: Violating LSP
class Bird {
    void fly() { /* Fly logic */ }
}

class Ostrich extends Bird {
    void fly() {
        throw new UnsupportedOperationException("Ostrich can't fly");
    }
}

// Good Example: Adhering to LSP
class Bird {
    // Common bird behavior
}

class FlyingBird extends Bird {
    void fly() { /* Fly logic */ }
}

class Ostrich extends Bird {
    // Ostrich-specific behavior
}
```

---

### **Slide 6: Interface Segregation Principle (ISP)**
**Title:** Interface Segregation Principle (ISP)  
**Core Meaning:**  
- Clients should not be forced to depend on interfaces they do not use.  

**Intention:**  
- Prevents "fat" interfaces and promotes smaller, more specific interfaces.  

```mermaid
classDiagram
    class Worker {
        <<interface>>
        +work()
        +eat()
        +sleep()
    }
    
    class Workable {
        <<interface>>
        +work()
    }
    
    class Eatable {
        <<interface>>
        +eat()
    }
    
    class Sleepable {
        <<interface>>
        +sleep()
    }
    
    class Robot {
        +work()
    }
    
    class Human {
        +work()
        +eat()
        +sleep()
    }
    
    Worker <|.. Human : Bad
    Workable <|.. Robot : Good
    Eatable <|.. Human : Good
    Sleepable <|.. Human : Good
    
    note for Worker "Bad: Fat interface"
    note for Workable "Good: Segregated interfaces"
```

**Code Snippet:**  
```java
// Bad Example: A single large interface
interface Worker {
    void work();
    void eat();
}

class Engineer implements Worker {
    public void work() { /* Work logic */ }
    public void eat() { /* Eat logic */ }
}

// Good Example: Segregated interfaces
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Engineer implements Workable {
    public void work() { /* Work logic */ }
}
```

---

### **Slide 7: Dependency Inversion Principle (DIP)**
**Title:** Dependency Inversion Principle (DIP)  
**Core Meaning:**  
- High-level modules should not depend on low-level modules. Both should depend on abstractions.  
- Abstractions should not depend on details. Details should depend on abstractions.  

**Intention:**  
- Reduces coupling between modules.  
- Promotes the use of dependency injection.  

```mermaid
classDiagram
    class NotificationService {
        -messageService
        +sendNotification()
    }
    
    class MessageService {
        <<interface>>
        +sendMessage()
    }
    
    class EmailService {
        +sendMessage()
    }
    
    class SMSService {
        +sendMessage()
    }
    
    NotificationService --> MessageService : depends on abstraction
    MessageService <|.. EmailService : implements
    MessageService <|.. SMSService : implements
    
    note for NotificationService "High-level module"
    note for MessageService "Abstraction"
    note for EmailService "Low-level module"
```

**Code Snippet:**  
```java
// Bad Example: High-level module depends on low-level module
class EmailService {
    void sendEmail() { /* Email logic */ }
}

class Notification {
    private EmailService emailService = new EmailService();
    void sendNotification() {
        emailService.sendEmail();
    }
}

// Good Example: Both depend on abstraction
interface MessageService {
    void sendMessage();
}

class EmailService implements MessageService {
    public void sendMessage() { /* Email logic */ }
}

class Notification {
    private MessageService messageService;
    Notification(MessageService messageService) {
        this.messageService = messageService;
    }
    void sendNotification() {
        messageService.sendMessage();
    }
}
```

---

### **Slide 8: Benefits of SOLID Principles**
**Title:** Why Follow SOLID Principles?  
**Content:**  
- **Maintainability:** Easier to understand and modify code.  
- **Scalability:** Simplifies adding new features.  
- **Testability:** Encourages modular and decoupled code.  
- **Reusability:** Promotes reusable components.  
- **Flexibility:** Reduces the risk of breaking existing functionality.  

---

### **Slide 9: Conclusion**
**Title:** Key Takeaways  
**Content:**  
- SOLID principles are guidelines for writing clean, maintainable, and scalable code.  
- They help in reducing technical debt and improving software quality.  
- Applying SOLID principles requires practice and thoughtful design.  

**Call to Action:**  
- Start applying SOLID principles in your Java projects today!  

---

### **Slide 10: Q&A**
**Title:** Questions?  
**Content:**  
- Open the floor for questions and discussions.  

---

This slide deck provides a comprehensive overview of SOLID principles with practical Java examples. You can customize it further based on your audience and presentation style. Let me know if you need additional details!

---

User: is solid enforced from day 1? or, as we make code changes, we understand it's better to break things down into smaller chunks.

---

Great question! **SOLID principles** are not necessarily enforced from day 1, and they don't have to be. In fact, it's often more practical and realistic to **refactor code over time** as you gain a deeper understanding of the system and its requirements. Let’s break this down:

---

### **Slide 11: When to Apply SOLID Principles?**
**Title:** Is SOLID Enforced from Day 1?  
**Content:**  
- **Not always enforced from day 1:**  
  - Early in a project, the focus is often on delivering functionality quickly.  
  - Over-engineering upfront can lead to unnecessary complexity.  
- **Refactor as you go:**  
  - As the codebase grows, you’ll identify areas where SOLID principles can improve maintainability and scalability.  
  - Refactoring is a natural part of the software development lifecycle.  

---

### **Slide 12: Why Not Enforce SOLID from Day 1?**
**Title:** Why Delay Applying SOLID Principles?  
**Content:**  
1. **Unclear Requirements:**  
   - Early in a project, requirements may not be fully understood.  
   - Applying SOLID too early might lead to incorrect abstractions.  
2. **Premature Optimization:**  
   - Over-engineering before understanding the problem can waste time.  
   - Focus on solving the problem first, then refine the design.  
3. **Iterative Development:**  
   - Agile and iterative development encourages evolving the design as the system grows.  

---

### **Slide 13: When to Refactor for SOLID?**
**Title:** Signs It’s Time to Apply SOLID Principles  
**Content:**  
- **Code Duplication:**  
  - Repeated logic indicates a need for better abstraction (SRP, OCP).  
- **Rigid Design:**  
  - Difficulty adding new features without breaking existing ones (OCP, DIP).  
- **Fragile Code:**  
  - Changes in one part of the system break unrelated parts (SRP, LSP).  
- **Large Classes/Interfaces:**  
  - Classes or interfaces doing too much (SRP, ISP).  
- **Tight Coupling:**  
  - High dependency between classes makes testing and maintenance hard (DIP).  

---

### **Slide 14: Refactoring Example**
**Title:** Refactoring to SOLID Over Time  
**Content:**  
- **Initial Code (Monolithic):**  
  ```java
  class Report {
      void generateReport() { /* Generate report */ }
      void saveToFile() { /* Save to file */ }
      void printReport() { /* Print report */ }
  }
  ```  
- **Refactored Code (SOLID-Compliant):**  
  ```java
  interface ReportGenerator {
      void generate();
  }

  interface ReportSaver {
      void save();
  }

  interface ReportPrinter {
      void print();
  }

  class PDFReport implements ReportGenerator, ReportSaver {
      public void generate() { /* Generate PDF */ }
      public void save() { /* Save PDF */ }
  }

  class Printer implements ReportPrinter {
      public void print() { /* Print report */ }
  }
  ```  

---

### **Slide 15: Balancing SOLID and Practicality**
**Title:** Balancing SOLID with Real-World Constraints  
**Content:**  
- **Start Simple:**  
  - Begin with a working solution, even if it’s not perfectly SOLID.  
- **Refactor Incrementally:**  
  - Apply SOLID principles as the codebase grows and pain points emerge.  
- **Avoid Over-Engineering:**  
  - Don’t force SOLID where it doesn’t add value.  
- **Team Collaboration:**  
  - Ensure the team understands SOLID principles and agrees on when to apply them.  

---

### **Slide 16: Conclusion**
**Title:** Key Takeaways  
**Content:**  
- SOLID principles are **guidelines**, not strict rules to be enforced from day 1.  
- Refactoring toward SOLID is a natural part of evolving a codebase.  
- Balance practicality with good design to avoid over-engineering.  

**Call to Action:**  
- Start small, deliver value, and refine your design as you go!  

---

This approach ensures that SOLID principles are applied **when they provide the most value**, rather than forcing them prematurely. Let me know if you'd like to dive deeper into any specific principle or refactoring technique!

---

## Class Relationship Types

[reference](../llm-outputs/OOPs/ClassRelationships.md)

```mermaid
classDiagram
    %% Inheritance Example
    class Animal {
        +makeSound()
        +move()
    }
    class Dog {
        +makeSound()
        +move()
        +wagTail()
    }
    Animal <|-- Dog : is a (inherits)
    
    %% Composition Example
    class Car {
        -engine: Engine
        +start()
        +stop()
    }
    class Engine {
        -cylinders: int
        +turnOn()
        +turnOff()
    }
    Car *-- Engine : has a (owns)
    
    %% Aggregation Example
    class University {
        -name: String
        +addDepartment()
    }
    class Department {
        -name: String
        +addProfessor()
    }
    University o-- Department : has a (contains)
    
    %% Association Example
    class Student {
        -name: String
        +enroll()
    }
    class Course {
        -title: String
        +addStudent()
    }
    Student --> Course : uses (independent)
    
    %% Implementation/Realization Example
    class Playable {
        <<interface>>
        +play()
        +pause()
    }
    class MusicPlayer {
        +play()
        +pause()
        +addSong()
    }
    Playable <|.. MusicPlayer : implements (contracts)
    
    %% Dependency Example
    class Order {
        +checkout()
    }
    class PaymentProcessor {
        +process()
    }
    Order ..> PaymentProcessor : uses (temporary)

```

---

## Composition Over Inheritance

I'll explain Composition Over Inheritance using both programming and real-world examples, and create a diagram to illustrate the concepts.


```mermaid
classDiagram
    class Vehicle {
        +start()
        +stop()
    }
    class Engine {
        +start()
        +stop()
        +injectFuel()
    }
    class Car {
        -engine
        +start()
        +stop()
    }
    class ElectricCar {
        -motor
        +start()
        +stop()
    }
    class Motor {
        +start()
        +stop()
        +consumePower()
    }

    Vehicle <|-- Car : Inheritance
    Vehicle <|-- ElectricCar : Inheritance
    Car o-- Engine : Composition
    ElectricCar o-- Motor : Composition

```

Composition Over Inheritance is a fundamental design principle in object-oriented programming that suggests you should favor composing objects together rather than using inheritance hierarchies. Let me break this down:

1. The Core Concept:
- Instead of inheriting behaviors from parent classes, objects should contain instances of other classes that provide the desired functionality
- This creates a "has-a" relationship rather than an "is-a" relationship

2. Real-World Examples:

    Example 1: Vehicle System
    - Inheritance Approach:
      ```java
      abstract class Vehicle {
          public abstract void move();
      }

      class Car extends Vehicle {
          @Override
          public void move() {
              // Car-specific movement logic
          }
      }
      ```

    - Composition Approach:
      ```java
      class Engine {
          public void run() {
              // Engine running logic
          }
      }

      class Car {
          private Engine engine;
          
          public Car() {
              this.engine = new Engine();
          }
          
          public void move() {
              engine.run();
          }
      }
      ```

    Real-Life Analogy: Think about building a car
    - Inheritance would be like saying "a car is a type of vehicle"
    - Composition is like saying "a car has an engine, has wheels, has seats"

    Example 2: Computer Assembly
    - Instead of inheriting from a "Computer" class, a laptop is composed of:
      - CPU
      - RAM
      - Storage
      - Display
      - Keyboard

3. Advantages of Composition:

    a) Flexibility
    - Easy to change parts without affecting the whole system
    - Can swap implementations at runtime
    - Less rigid than inheritance hierarchies

    b) Better Encapsulation
    - Internal details can be hidden
    - Changes to composed objects don't necessarily affect the containing object

    c) Looser Coupling
    - Components can be developed independently
    - Easier to test and maintain

4. When to Use Each:

    Use Inheritance When:
    - There's a true "is-a" relationship
    - You want to share code among several related classes
    - The parent class is designed for extension

    Use Composition When:
    - You need more flexibility
    - You want to reuse code across unrelated classes
    - The relationship between classes might change
    - You need to change behavior at runtime

5. Practical Example:
    ```java
    // Composition example for a music player

    // AudioPlayer class to manage playing music
    class AudioPlayer {
        private Equalizer equalizer;
        private Display display;
        private Storage storage;

        public AudioPlayer() {
            this.equalizer = new Equalizer();
            this.display = new Display();
            this.storage = new Storage();
        }

        public void playMusic() {
            Audio audio = storage.getAudio();
            String processedAudio = equalizer.process(audio);
            display.showPlaying(audio.getMetadata());
        }
    }
    ```

    This is more flexible than inheriting from a generic "MediaPlayer" class because:
    - You can easily swap out the equalizer implementation
    - The display can be changed without affecting other components
    - Storage can be changed from local to cloud without changing the AudioPlayer class

    Remember: "Favor composition over inheritance" doesn't mean "never use inheritance." It means composition should be your first choice, and inheritance should be used when it makes the most sense for your specific use case.

---

## ER Diagram

- Entity : The encapsulation of the data, consisting of multiple attributes.
- Relationship: The connections between the entities, defining how they interact.

Implementaion:

1. Entity:
    - The entity has a bunch of attributes. Each attribute represents a column in the database table.
    - We can have multiple instances of an entity, each instance represents a row in the database table.

    User entity:

    | Attribute      | Data Type | Description                |
    |----------------|-----------|----------------------------|
    | user_id        | INT       | Unique identifier for user |
    | name           | VARCHAR   | User's name                |
    | email          | VARCHAR   | User's email address       |
    | last_login     | TIMESTAMP | Last login timestamp       |

    | User_id | Email           | Last_login          | Name  |
    |---------|-----------------|-----------------------|-------|
    | 1       | john@example.com | 2022-01-01 12:00:00 | John Smith |
    | 2       | jack@hello.com | 2022-01-02 10:00:00 | Jack Reacher |

    Post entity:

    | Attribute      | Data Type | Description                |
    |----------------|-----------|----------------------------|
    | id        | INT       | Unique identifier for post |
    | title          | VARCHAR   | Title of the post          |
    | content        | TEXT      | Content of the post        |
    | created_at     | TIMESTAMP | Timestamp when the post was created |

    | id | Title           | Content          | Created_at          |
    |---------|-----------------|------------------|---------------------|
    | 1      | First Post      | This is my first post | 2022-01-01 12:00:00 |
    | 2       | Hello World     | Hello, this is Jack | 2022-01-02 10:00:00 |

2. Relation

    - The relationship between entities is defined by the foreign key constraint.
    - The foreign key in one entity refers to the primary key in another entity.

    User-Post relationship:

    | User_id | Post_id |
    |---------|---------|
    | 1       | 1       |
    | 1       | 2       |
    | 2       | 3       |

    In this example, the User-Post relationship table connects the User entity with the Post entity. Each row represents a relationship between a user and a post.

    This is must in a many to many relationship. Because there is no way we can store the relationship in the entities themselves. (If we can't use arrays).

    But in case of 1-many or 1-1 relationship, it's possible to store the relationship in the entities themselves. We store the foreign key in the entity which is on the many side.

    ```mermaid
    erDiagram
        USER {
            int id
            string name
            string email
        }
        POST {
            int id
            string title
            string content
            int user_id
        }
        USER ||--o{ POST : writes
    ```

---

## SQL

[sql console](../../code/src/sql/console.sql)
