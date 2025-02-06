package org.example.taskmanager.repository;

import org.example.taskmanager.model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TaskJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Task> taskMapper = (rs, rowNum) -> {
        Task task = new Task();
        task.setId(rs.getLong("id"));
        task.setTitle(rs.getString("title"));
        task.setDescription(rs.getString("description"));
        task.setStatus(rs.getString("status"));
        task.setCategory(rs.getString("category"));
        task.setDueDate(rs.getDate("due_date").toLocalDate().toString());
        task.setUserId(rs.getLong("user_id"));
        return task;
    };

    public TaskJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Task create(Task task) {
        String sql = "INSERT INTO task (title, description, status, category, due_date, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getStatus());
            ps.setString(4, task.getCategory());
            ps.setDate(5, java.sql.Date.valueOf(task.getDueDate()));
            ps.setLong(6, task.getUserId());
            return ps;
        }, keyHolder);

        task.setId(keyHolder.getKey().longValue());
        return task;
    }

    public List<Task> findAll() {
        return jdbcTemplate.query("SELECT * FROM task", taskMapper);
    }

    public Task findById(Long id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM task WHERE id = ?",
            taskMapper,
            id
        );
    }

    public List<Task> findByUserId(Long userId) {
        return jdbcTemplate.query(
            "SELECT * FROM task WHERE user_id = ?",
            taskMapper,
            userId
        );
    }

    public void update(Task task) {
        jdbcTemplate.update(
            "UPDATE task SET title=?, description=?, status=?, category=?, due_date=?, user_id=? WHERE id=?",
            task.getTitle(),
            task.getDescription(),
            task.getStatus(),
            task.getCategory(),
            java.sql.Date.valueOf(task.getDueDate()),
            task.getUserId(),
            task.getId()
        );
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM task WHERE id = ?", id);
    }
} 