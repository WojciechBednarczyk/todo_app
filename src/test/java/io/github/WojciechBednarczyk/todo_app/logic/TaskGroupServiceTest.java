package io.github.WojciechBednarczyk.todo_app.logic;

import io.github.WojciechBednarczyk.todo_app.model.TaskGroup;
import io.github.WojciechBednarczyk.todo_app.model.TaskGroupRepository;
import io.github.WojciechBednarczyk.todo_app.model.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskGroupServiceTest {

    @Test
    @DisplayName("should throw IllegalStateException when there are undone tasks in group")
    void toggleGroup_undoneTasksExist_throwsIllegalStateException(){

        TaskRepository mockTaskRepository = mock(TaskRepository.class);
        when(mockTaskRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(true);


        var toTest = new TaskGroupService(null,mockTaskRepository);

        var exception = catchThrowable(() -> toTest.toggleGroup(0));

        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("has undone tasks");
    }

    @Test
    @DisplayName("should throw IllegalArgumentException when group with given id was not found")
    void toggleGroup_noGroupWithGivenId_throwsIllegarArgumentException(){

        TaskRepository mockTaskRepository = mock(TaskRepository.class);
        when(mockTaskRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(false);

        TaskGroupRepository mockTaskGroupRepository = mock(TaskGroupRepository.class);
        when(mockTaskGroupRepository.findById(anyInt())).thenReturn(Optional.empty());

        var toTest = new TaskGroupService(mockTaskGroupRepository,mockTaskRepository);

        var exception = catchThrowable(() -> toTest.toggleGroup(0));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id not found");
    }

    @Test
    @DisplayName("should toggle group")
    void toggleGroup_worsAsExpected(){

        TaskRepository mockTaskRepository = mock(TaskRepository.class);
        when(mockTaskRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(false);

        var group = new TaskGroup();
        var beforeToggle = group.isDone();

        TaskGroupRepository mockTaskGroupRepository = mock(TaskGroupRepository.class);
        when(mockTaskGroupRepository.findById(anyInt())).thenReturn(Optional.of(group));

        var toTest = new TaskGroupService(mockTaskGroupRepository,mockTaskRepository);

        toTest.toggleGroup(0);

        assertThat(group.isDone()).isEqualTo(!beforeToggle);
    }
}
