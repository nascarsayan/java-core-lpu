# Task Manager Application Documentation

## Overview
This application is a task management system built with a modern, decoupled architecture. The frontend uses vanilla JavaScript with Preact (via CDN), while the backend is powered by Spring Boot with H2 database support.

## Architecture

### Component Diagram
```mermaid
graph TD
    A[Frontend App] --> B[TaskStorageInterface]
    B --> C[IndexedDBStorage]
    B --> D[RestApiClient]
    D --> E[Spring Boot Backend]
    E --> F[H2 Database]
    
    style A fill:#f9f,stroke:#333
    style B fill:#ff9,stroke:#333
    style C fill:#9f9,stroke:#333
    style D fill:#9f9,stroke:#333
    style E fill:#99f,stroke:#333
    style F fill:#f99,stroke:#333
```

### Frontend Data Flow
```mermaid
sequenceDiagram
    participant UI as TaskList/TaskForm
    participant Context as TaskContext
    participant Service as TaskService
    participant Storage as Storage Implementation
    
    UI->>Context: User Action
    Context->>Service: Call Method
    Service->>Storage: Execute Operation
    Storage-->>Service: Return Result
    Service-->>Context: Update State
    Context-->>UI: Re-render
```

## Frontend Architecture

### No Build Tools Required
The frontend is built without any build tools, using:
- ES Modules for code organization
- CDN-delivered dependencies
- Import maps for dependency management

Reference the import map configuration:

```html
<!--9:17:src/main/resources/static/index.html-->
  <script type="importmap">
    {
        "imports": {
            "preact": "https://esm.sh/preact@10.23.1",
            "htm/preact": "https://esm.sh/htm@3.1.1/preact?external=preact",
            "preact/hooks": "https://esm.sh/preact@10.23.1/hooks"
        }
    }
    </script>
```


### Storage Interface Pattern
The application uses an abstract storage interface that allows switching between:
1. IndexedDB (client-side storage)
2. REST API (server communication)

The interface definition:

```js
// 1:22:src/main/resources/static/js/services/TaskStorageInterface.js
// Interface for Task Storage implementations
export class TaskStorageInterface {
  async createTask(task) {
    throw new Error('Not implemented');
  }

  async getAllTasks() {
    throw new Error('Not implemented');
  }

  async updateTask(id, task) {
    throw new Error('Not implemented');
  }

  async deleteTask(id) {
    throw new Error('Not implemented');
  }

  async getTasksByUserId(userId) {
    throw new Error('Not implemented');
  }
}
```


### Storage Implementation Selection
Storage implementation can be switched in the taskService configuration:

```js
// 1:9:src/main/resources/static/js/services/taskService.js
import { IndexedDBStorage } from './IndexedDBStorage.js';
import { RestApiClient } from './RestApiClient.js';

// Choose which implementation to use
const storage = new IndexedDBStorage();
// const storage = new RestApiClient();

// Export the storage implementation as the taskService
export const taskService = storage;
```


## Backend Architecture

### Database Layer
- H2 Database with both JPA and JDBC support
- Raw SQL queries via JdbcTemplate
- Schema defined in schema.sql

Database schema:

```sql
-- 1:9:src/main/resources/schema.sql
CREATE TABLE IF NOT EXISTS task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    status VARCHAR(20),
    category VARCHAR(50),
    due_date DATE,
    user_id BIGINT
); 
```


### API Endpoints

| Endpoint                 | Method | Description       |
| ------------------------ | ------ | ----------------- |
| `/api/tasks`             | GET    | Get all tasks     |
| `/api/tasks`             | POST   | Create new task   |
| `/api/tasks/{id}`        | PUT    | Update task       |
| `/api/tasks/{id}`        | DELETE | Delete task       |
| `/api/tasks?userId={id}` | GET    | Get tasks by user |

## Component Interactions

### Frontend Components
```mermaid
graph TD
    A[App] --> B[TaskProvider]
    B --> C[TaskForm]
    B --> D[TaskList]
    B --> E[TaskContext]
    E --> F[TaskService]
    F --> G[Storage Implementation]
```

### Backend Components
```mermaid
graph TD
    A[TaskController] --> B[TaskJdbcRepository]
    B --> C[JdbcTemplate]
    C --> D[H2 Database]
    
    style A fill:#99f,stroke:#333
    style B fill:#f9f,stroke:#333
    style C fill:#ff9,stroke:#333
    style D fill:#f99,stroke:#333
```

## Key Features
1. Decoupled frontend and backend
2. Switchable storage implementations
3. No build tools required for frontend
4. Raw SQL support via JdbcTemplate
5. Modern component-based UI with Preact
6. Category-based task filtering
7. Real-time UI updates

## Configuration
The application can be configured through:

```yaml
# 1:15:src/main/resources/application.yaml
spring:
  application:
    name: TaskManager
  datasource:
    url: jdbc:h2:file:./h2
    username: user
    password: pass
    driver-class-name: org.h2.Driver
  h2:
    console:
      enabled: true
      path: /h2-console
  sql:
    init:
      mode: always
```


This architecture allows for:
- Easy switching between storage implementations
- Independent frontend development
- Simple deployment (single JAR)
- Flexible database access (JPA/JDBC)
