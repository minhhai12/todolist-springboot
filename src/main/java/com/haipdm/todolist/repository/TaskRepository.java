package com.haipdm.todolist.repository;


import com.haipdm.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value = "SELECT * FROM task WHERE task.status = 0 and task.flag_delete=0", nativeQuery = true)
    public List<Task> findListUnfinished();

    @Query(value = "SELECT * FROM task WHERE task.status = 1 and task.flag_delete=0", nativeQuery = true)
    public List<Task> findListFinished();
}
