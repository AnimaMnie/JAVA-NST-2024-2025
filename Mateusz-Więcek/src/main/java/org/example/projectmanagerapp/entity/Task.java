package org.example.projectmanagerapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.projectmanagerapp.enums.PriorityLevel;
import org.example.projectmanagerapp.enums.TaskType;
import org.example.projectmanagerapp.enums.LowPriority;
import org.example.projectmanagerapp.enums.MediumPriority;
import org.example.projectmanagerapp.enums.HighPriority;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_type", nullable = false)
    private TaskType taskType;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Transient  // To pole NIE JEST zapisywane w bazie, ale może być używane w kodzie
    private PriorityLevel priorityLevel;

    public Task(String title, String description, TaskType taskType, Project project) {
        this.title = title;
        this.description = description;
        this.taskType = taskType;
        this.project = project;
        setPriority(taskType);
    }

    public void setPriority(TaskType taskType) {
        switch (taskType) {
            case LOW -> this.priorityLevel = new LowPriority();
            case MEDIUM -> this.priorityLevel = new MediumPriority();
            case HIGH -> this.priorityLevel = new HighPriority();
        }
    }

    public String getPriority() {
        return priorityLevel.getPriority();
    }
}
