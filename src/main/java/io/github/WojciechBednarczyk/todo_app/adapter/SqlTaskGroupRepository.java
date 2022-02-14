package io.github.WojciechBednarczyk.todo_app.adapter;

import io.github.WojciechBednarczyk.todo_app.model.TaskGroup;
import io.github.WojciechBednarczyk.todo_app.model.TaskGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlTaskGroupRepository extends JpaRepository<TaskGroup,Integer>, TaskGroupRepository {

    @Override
    @Query("from TaskGroup g join fetch g.tasks")
    List<TaskGroup> findAll();

    @Override
    boolean existsByDoneIsFalseAndProject_Id(Integer projectId);
}
