package io.github.WojciechBednarczyk.todo_app.adapter;

import io.github.WojciechBednarczyk.todo_app.model.Project;
import io.github.WojciechBednarczyk.todo_app.model.ProjectRepository;
import io.github.WojciechBednarczyk.todo_app.model.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlProjectRepository extends JpaRepository<Project,Integer>, ProjectRepository {

    @Override
    @Query("from Project p join fetch p.steps")
    List<Project> findAll();


}
