input to bolt.new

---

- Backend in Java Spring Boot with gradle packaging (in Kotlin DSL). The backend code needs to be completed. Add comments as TODOs instead of writing the full code.
- Frontend in Preact with no build tools. Use only cdn packages. The frontend should be serve-able using a simple http server like python -m http.server or npx serve.
- Create clear interface for backend to be used by frontend.
IndexedDB implements that interface. Also, a stubbed js client to the backend implements it.
- The backend should have 2-3 entities and 1-2 relationships. The frontend should have a simple CRUD interface for these entities.
- The spring boot app should serve the frontend code as static files.

Technology:
- html, css, js without any build tools like vite / webpack.
- no typescript.
- no package manager like npm / yarn / bun.
- Use only cdn packages, with simple index.html, index.css, index.js , and a few other js files.
- Use preact with No-Build Workflows (Whilst build tools like Webpack, Rollup, and Vite are incredibly powerful and useful, Preact fully supports building applications without them.)
- The frontend should be servable using a simple http server like python -m http.server or npx serve.

---

Sample preact code with import maps

```html
<!DOCTYPE html>
<html>
  <head>
    <script type="importmap">
      {
        "imports": {
          "preact": "https://esm.sh/preact@10.23.1",
          "htm/preact": "https://esm.sh/htm@3.1.1/preact?external=preact"
        }
      }
    </script>
  </head>
  <body>
    <div id="app"></div>

    <script type="module">
      import { render } from 'preact';
      import { html } from 'htm/preact';

      export function App() {
        return html`<h1>Hello, World!</h1>`;
      }

      render(html`<${App} />`, document.getElementById('app'));
    </script>
  </body>
</html>
```

<!-- <The app specification goes here> -->

---- ### 1. **Task Manager App**
Entities: User, Task
Relationships: A User has many Tasks
Functionality: Users create, edit, and delete tasks with a due date and status.


-----------

Here are **10 project ideas** where the backend is stubbed out, and the frontend is built using **React + Bolt.new**, with Java as the backend:  

---

### 1. **Task Manager App**  
   - **Entities**: `User`, `Task`  
   - **Relationships**: A `User` has many `Tasks`  
   - **Functionality**: Users create, edit, and delete tasks with a due date and status.  

### 2. **Book Lending System**  
   - **Entities**: `User`, `Book`  
   - **Relationships**: A `User` can borrow multiple `Books`  
   - **Functionality**: Users can borrow and return books with availability tracking.  

### 3. **Event Ticketing System**  
   - **Entities**: `User`, `Event`  
   - **Relationships**: A `User` can purchase tickets for multiple `Events`  
   - **Functionality**: Users browse events and buy tickets.  

### 4. **Simple Inventory System**  
   - **Entities**: `Product`, `Category`  
   - **Relationships**: A `Category` has many `Products`  
   - **Functionality**: CRUD for products and categories. Products have stock levels.  

### 5. **Student Course Registration**  
   - **Entities**: `Student`, `Course`  
   - **Relationships**: A `Student` can enroll in multiple `Courses`  
   - **Functionality**: Students can register/drop courses. Courses have a capacity limit.  

### 6. **Movie Review System**  
   - **Entities**: `User`, `Movie`, `Review`  
   - **Relationships**: A `User` can leave multiple `Reviews` for `Movies`  
   - **Functionality**: Users review movies with ratings and comments.  

### 7. **Appointment Booking System**  
   - **Entities**: `User`, `Appointment`  
   - **Relationships**: A `User` can have multiple `Appointments`  
   - **Functionality**: Users book and manage appointments with a date/time field.  

### 8. **Online Polling System**  
   - **Entities**: `Poll`, `Option`, `Vote`  
   - **Relationships**: A `Poll` has multiple `Options`, and a `User` can cast one `Vote` per `Poll`  
   - **Functionality**: Users create polls and vote on them.  

### 9. **Expense Tracker**  
   - **Entities**: `User`, `Expense`, `Category`  
   - **Relationships**: A `User` has multiple `Expenses`, and each `Expense` belongs to a `Category`  
   - **Functionality**: Users track expenses, filter by category, and see total spending.  

### 10. **E-commerce Cart System**  
   - **Entities**: `User`, `Product`, `CartItem`  
   - **Relationships**: A `User` can have multiple `CartItems`, and each `CartItem` belongs to a `Product`  
   - **Functionality**: Users add/remove items from their cart, with quantity management.  

---

Each project will have:  
✅ **React frontend** (generated using Bolt.new)  
✅ **Stubbed Java backend** (REST API contract defined, implementation missing)  
✅ **IndexedDB/In-memory storage** for the initial working UI  

Would you like me to generate code stubs for any specific project(s)?