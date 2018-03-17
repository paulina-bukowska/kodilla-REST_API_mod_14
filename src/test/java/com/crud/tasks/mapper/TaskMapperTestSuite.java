package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(10L, "Test_title", "Test_content");

        //When
        Task returnedTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals("Test_title", returnedTask.getTitle());
        assertEquals("Test_content", returnedTask.getContent());
        assertNotNull(returnedTask.getId());
//        assertEquals(10L, returnedTask.getId());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(5L, "Test_title", "Test_content");

        //When
        TaskDto returnedTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals("Test_title", returnedTaskDto.getTitle());
        assertEquals("Test_content", returnedTaskDto.getContent());
        assertNotNull(returnedTaskDto.getId());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Test_title1", "Test_content1"));
        taskList.add(new Task(2L, "Test_title2", "Test_content2"));
        taskList.add(new Task(3L, "Test_title3", "Test_content3"));

        //When
        List<TaskDto> taskListDto = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(3, taskListDto.size());
        assertEquals("Test_title2", taskListDto.get(1).getTitle());
        assertEquals("Test_content3", taskListDto.get(2).getContent());
    }
}
