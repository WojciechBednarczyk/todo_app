package io.github.WojciechBednarczyk.todo_app.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "project_steps")
public class ProjectStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter(AccessLevel.PACKAGE)
    private int id;
    @Getter @Setter
    @NotBlank(message = "Project step's description must not be empty")
    private String description;
    @Getter @Setter
    private int daysToDeadline;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
