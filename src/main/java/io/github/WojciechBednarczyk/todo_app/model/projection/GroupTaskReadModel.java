package io.github.WojciechBednarczyk.todo_app.model.projection;

import io.github.WojciechBednarczyk.todo_app.model.Task;
import lombok.Getter;
import lombok.Setter;

public class GroupTaskReadModel {
    @Getter @Setter
    private String description;
    @Getter @Setter
    private boolean done;

    public GroupTaskReadModel(Task source){
        description=source.getDescription();
        done= source.isDone();
    }
}
