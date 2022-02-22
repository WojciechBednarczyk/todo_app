package io.github.WojciechBednarczyk.todo_app.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Getter @Setter
    @NotBlank(message = "Project's description must not be empty")
    private String description;
    @OneToMany(mappedBy = "project")
    @Getter(AccessLevel.PACKAGE) @Setter(AccessLevel.PACKAGE)
    private Set<TaskGroup> groups;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    @Getter @Setter
    private Set<ProjectStep> steps;
}
