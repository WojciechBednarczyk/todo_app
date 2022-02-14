package io.github.WojciechBednarczyk.todo_app.logic;

import io.github.WojciechBednarczyk.todo_app.model.Task;
import io.github.WojciechBednarczyk.todo_app.model.TaskGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TempService {

    @Autowired
    void temp(TaskGroupRepository repository){
        repository.findAll().stream()
                .flatMap(taskGroup -> taskGroup.getTasks().stream())
                .map(Task::getDescription)
                .collect(Collectors.toList());
    }
}
