package com.taskmaster.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmaster.task.controller.TaskController;
import com.taskmaster.task.exception.TaskNotFoundException;
import com.taskmaster.task.model.Task;
import com.taskmaster.task.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TaskController.class)
public class taskControllertest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testCreateTask() throws Exception {
        Task task = new Task(1L, "Simple task", "This is a simple task");

        when(taskService.createTask(any(Task.class))).thenReturn(task); // ✅ fix

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Simple task"))
                .andExpect(jsonPath("$.description").value("This is a simple task"));
    }



    @Test
    public void testUserGetbyID() throws Exception{
    Task task = new Task (2L , "GetTask" , "Task to test get");

    when(taskService.getTaskById(2L)).thenReturn(task);

    mockMvc.perform(get("/tasks/2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(2))
            .andExpect(jsonPath("$.title").value("GetTask"));
    }

    @Test
    public void testUserNotFound() throws Exception {

        when(taskService.getTaskById(999L))
                .thenThrow(new TaskNotFoundException("Task not found"));

        mockMvc.perform(get("/tasks/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Task not found")) ;
    }
}
