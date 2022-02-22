package io.github.WojciechBednarczyk.todo_app.model.projection;

import io.github.WojciechBednarczyk.todo_app.model.TaskGroup;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupWriteModel {
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Set<GroupTaskWriteModel> tasks;

    public TaskGroup toGroup() {
        var result = new TaskGroup();
        result.setDescription(description);
        result.setTasks(tasks.stream()
                .map(GroupTaskWriteModel::toTask)
                .collect(Collectors.toSet()));
        return result;
    }
}
