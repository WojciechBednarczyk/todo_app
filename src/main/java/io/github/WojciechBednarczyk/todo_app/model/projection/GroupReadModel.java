package io.github.WojciechBednarczyk.todo_app.model.projection;

import io.github.WojciechBednarczyk.todo_app.model.Task;
import io.github.WojciechBednarczyk.todo_app.model.TaskGroup;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupReadModel {
    @Getter @Setter
    private String description;
    /*
    * Deadline from the latest task in group.
    */
    @Getter @Setter
    private LocalDateTime deadline;
    @Getter @Setter
    private Set<GroupTaskReadModel> tasks;

    public GroupReadModel(TaskGroup source){
        description = source.getDescription();
        source.getTasks().stream()
                .map(Task::getDeadline)
                .max(LocalDateTime::compareTo)
                .ifPresent(date -> deadline = date);
        tasks = source.getTasks().stream()
                .map(GroupTaskReadModel::new)
                .collect(Collectors.toSet());
    }
}
