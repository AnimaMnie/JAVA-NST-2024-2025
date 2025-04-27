package com.example.projectmanagerapp.entity;

import com.example.projectmanagerapp.service.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    @Transient
    private Priority task_type;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public void generatePriority(){
        int randomNum = (int)(Math.random() * 4);
        switch (randomNum) {
            case 1:
                this.task_type = new HighPriority().getPriority();
                break;
            case 2:
                this.task_type = new MediumPriority().getPriority();
                break;
            default:
                this.task_type = new LowPriority().getPriority();
        }
    }
}
