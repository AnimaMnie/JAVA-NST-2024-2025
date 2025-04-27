package org.example.projectmanagerapp.entity;

import org.example.projectmanagerapp.priority.PriorityLevel;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskType taskType;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Transient
    private PriorityLevel priorityLevel;

    public Task(String title, String description, TaskType taskType, Project project) {
        this.title = title;
        this.description = description;
        this.taskType = taskType;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() { return priorityLevel != null ? priorityLevel.getPriority() : "UNDEFINED"; }

    public void setPriorityLevel(PriorityLevel priorityLevel) { this.priorityLevel = priorityLevel; }
}