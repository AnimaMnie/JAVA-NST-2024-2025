package org.example.projectmanager.controller;

import org.example.projectmanager.entity.Tasks;
import org.example.projectmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping
    public Tasks createTask(@RequestBody Tasks task) {
        return taskRepository.save(task);
    }

    @GetMapping("/{id}")
    public Tasks getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id).orElse(null);
    }
}