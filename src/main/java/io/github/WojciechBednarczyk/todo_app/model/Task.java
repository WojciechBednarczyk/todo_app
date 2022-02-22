package io.github.WojciechBednarczyk.todo_app.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    @Getter @Setter
    private boolean done;
    @NotBlank(message = "Tasks's description must not be null!")
    @Getter @Setter
    private String description;
    @Getter @Setter
    private LocalDateTime deadline;
    @Embedded
    private Audit audit = new Audit();
    @ManyToOne
    @JoinColumn(name = "task_group_id")
    @Getter(AccessLevel.PACKAGE) @Setter(AccessLevel.PACKAGE)
    private TaskGroup group;
    public Task() {
    }

    public Task(String description, LocalDateTime deadline){
        this.description=description;
        this.deadline=deadline;
    }

    public void updateFrom(final Task source){
        description = source.description;
        done = source.done;
        deadline = source.deadline;
        group = source.group;
    }

}
