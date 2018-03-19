package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static java.util.Optional.ofNullable;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        Long id = 13L;
        TaskDto taskDto = new TaskDto(13L, "Test_title", "Test_content");
        Task task = new Task(13L, "Test_title", "Test_content");

        when(service.getTask(id)).thenReturn(ofNullable(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        try {
            mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON).param("taskId", "13"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(13)))
                    .andExpect(jsonPath("$.title", is("Test_title")))
                    .andExpect(jsonPath("$.content", is("Test_content")));
        } catch (TaskNotFoundException e) {
        }
    }

//    @Test(expected = TaskNotFoundException.class)
//    public void shouldThrowException() throws Exception {
//        // Given
//        Long id = 13L;
//        TaskDto taskDto = new TaskDto(13L, "Test_title", "Test_content");
//        Task task = new Task(13L, "Test_title", "Test_content");
//        TaskNotFoundException error = new TaskNotFoundException();
//
//        when(service.getTask(id)).thenReturn(error);
//        when(taskMapper.mapToTaskDto(task)).thenReturn(error);
//
//        // When & Then
//        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON).param("taskId", "14"));
//    }

    @Test
    public void shouldFetchTaskList() throws Exception {
        //Given
        List<TaskDto> taskListDto = new ArrayList<>();
        taskListDto.add(new TaskDto(13L, "Test_title", "Test_content"));

        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(13L, "Test_title", "Test_content"));

        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskListDto);

        // When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(13)))
                .andExpect(jsonPath("$[0].title", is("Test_title")))
                .andExpect(jsonPath("$[0].content", is("Test_content")));
    }

    @Test
    public void testCreateTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(9L, "Test_title", "Test_content");
        Task task = new Task(9L, "Test_title", "Test_content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonTask = gson.toJson(task);

        // When & Then
        mockMvc.perform(post("/v1/task/createTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(jsonTask))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(9L, "Update_title", "Update_content");
        Task task = new Task(9L, "Update_title", "Update_content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonTaskDto = gson.toJson(taskDto);

        // When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(jsonTaskDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(9)))
                .andExpect(jsonPath("$.title", is("Update_title")))
                .andExpect(jsonPath("$.content", is("Update_content")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given

        // When & Then
        mockMvc.perform(delete("/v1/task/deleteTask").contentType(MediaType.APPLICATION_JSON).param("taskId", "5"))
                .andExpect(status().isOk());
    }
}
