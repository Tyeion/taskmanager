package com.taskmaster.task.repository;

import com.taskmaster.task.model.Task;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
