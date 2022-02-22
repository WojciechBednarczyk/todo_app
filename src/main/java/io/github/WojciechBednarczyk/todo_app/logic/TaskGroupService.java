package io.github.WojciechBednarczyk.todo_app.logic;

import io.github.WojciechBednarczyk.todo_app.TaskConfigurationProperties;
import io.github.WojciechBednarczyk.todo_app.model.Task;
import io.github.WojciechBednarczyk.todo_app.model.TaskGroup;
import io.github.WojciechBednarczyk.todo_app.model.TaskGroupRepository;
import io.github.WojciechBednarczyk.todo_app.model.TaskRepository;
import io.github.WojciechBednarczyk.todo_app.model.projection.GroupReadModel;
import io.github.WojciechBednarczyk.todo_app.model.projection.GroupWriteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequestScope
public class TaskGroupService {

    private TaskGroupRepository repository;
    private TaskRepository taskRepository;
    private TaskConfigurationProperties config;

    public TaskGroupService(TaskGroupRepository repository,TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository=taskRepository;
    }

    public GroupReadModel createGroup(GroupWriteModel source){
        TaskGroup result = repository.save(source.toGroup());
        return new GroupReadModel(result);
    }

    public List<GroupReadModel> readAll(){
        return repository.findAll()
                .stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public void toggleGroup(int groupId){
        if (taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)){
            throw new IllegalStateException("Group has undone tasks. Done all the tasks first");
        }
        TaskGroup result = repository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Task group with given id not found"));
        result.setDone(!result.isDone());
    }
}
