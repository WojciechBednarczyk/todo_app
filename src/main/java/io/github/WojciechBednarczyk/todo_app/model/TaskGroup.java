package io.github.WojciechBednarczyk.todo_app.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "task_groups")
@NoArgsConstructor
public class TaskGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    @NotBlank(message = "Task group's description must not be empty")
    @Getter @Setter
    private String description;
    @Getter @Setter
    private boolean done;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    @Getter @Setter
    private Set<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @Getter @Setter
    private Project project;


}
