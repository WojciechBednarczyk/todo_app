package io.github.WojciechBednarczyk.todo_app.model.projection;

import io.github.WojciechBednarczyk.todo_app.model.Task;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class GroupTaskWriteModel {
    @Getter @Setter
    private String description;
    @Getter @Setter
    private LocalDateTime deadline;

    public Task toTask(){
        return new Task(description, deadline);
    }
}
