package com.taskmaster.task.service;

import com.taskmaster.task.model.Task;
import com.taskmaster.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task)
    {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask)
    {
       //get task from db
        //if not found , throw error
        //updating fields
        //save and return
        Task exisistingTask = taskRepository.findById(id).orElseThrow();
        exisistingTask.setStatus(updatedTask.isStatus());
        exisistingTask.setDescription(updatedTask.getDescription());
        exisistingTask.setTitle(updatedTask.getTitle());
        return taskRepository.save(exisistingTask);

    }

    //get task
    public Task getTaskById(Long id)
    {
        Task presentTask = taskRepository.findById(id).orElseThrow(() ->
                new RuntimeException("the requested task not found"+ id));

        return presentTask;
    }

    //get all task

    public List<Task> getAllTasks()
    {
        List<Task> tasks = taskRepository.findAll();

        return tasks;
    }

    public void deleteTask(Long id)
    {
        Task taskToDelete = taskRepository.findById(id).orElseThrow(() ->
                new RuntimeException("the requested task not found"+ id));

        taskRepository.delete(taskToDelete);
    }
}
