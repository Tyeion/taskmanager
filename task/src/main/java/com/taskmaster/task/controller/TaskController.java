package com.taskmaster.task.controller;

import com.taskmaster.task.model.Task;
import com.taskmaster.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    @Autowired
    private TaskService taskService;

    @PostMapping
    public  ResponseEntity<Task> createTask(@RequestBody Task task)
    {
        Task createdTask= taskService.createTask(task);
        return new ResponseEntity<>(createdTask , HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id)
    {
        Task getTask=taskService.getTaskById(id);

        return ResponseEntity.ok(getTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask()
    {
        List <Task> tasks = taskService.getAllTasks();

        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task>  updateTask(@RequestBody Task task, @PathVariable Long id)
    {
        Task updatedTask = taskService.updateTask(id , task);
         return new ResponseEntity<>(updatedTask , HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
