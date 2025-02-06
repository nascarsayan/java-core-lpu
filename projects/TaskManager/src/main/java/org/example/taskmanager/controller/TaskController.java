package org.example.taskmanager.controller;

import org.example.taskmanager.model.Task;
import org.example.taskmanager.repository.TaskJdbcRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskJdbcRepository repository;

    public TaskController(TaskJdbcRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(repository.create(task));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            return ResponseEntity.ok(repository.findByUserId(userId));
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        repository.update(task);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        repository.delete(id);
        return ResponseEntity.ok().build();
    }
} 