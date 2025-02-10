package fr.cococode.taskmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.cococode.taskmanagement.dao.TaskDAO;
import fr.cococode.taskmanagement.dto.TaskDTO;
import fr.cococode.taskmanagement.exception.TaskNotFoundException;
import fr.cococode.taskmanagement.model.Task;
import fr.cococode.taskmanagement.model.TaskStatus;

/*
 * @ExtendWith(MockitoExtension.class) : Cette annotation intègre Mockito avec JUnit 5. Il informe JUnit d'activer 
 * 	les fonctionnalités spécifiques de Mockito et les annotations comme @Mock, @InjectMocks, et @Captor.
 * 
 * 	En utilisant MockitoExtension, on a pas besoin d'initialiser les mocks de façon manuelle. 
 * 	L'extension s'occupe de créer et d'injecter les instances de mock.
 * 
 * @Mock : Cette annotation créé une instance de mock d'une classe ou interface spécifiée. Ici, taskDAO est mocké.
 * 
 * @InjectMocks : Cette annotation injecte les mocks marqués avec @Mock dans la classe testée. 
 */

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

	@Mock
	private TaskDAO taskDAO;

	@InjectMocks
	private TaskService taskService;

	private Task sampleTask;
	private TaskDTO sampleTaskDTO;

	@BeforeEach
	void setUp() {
		sampleTask = new Task();

		sampleTask.setId(1L);
		sampleTask.setTitle("Sample Task");
		sampleTask.setDescription("Sample Description");
		sampleTask.setStatus(TaskStatus.TODO);

		sampleTaskDTO = new TaskDTO();
		sampleTaskDTO.setTitle("Sample Task");
		sampleTaskDTO.setDescription("Sample Description");
		sampleTaskDTO.setStatus("TODO");
	}

	@Test
	void testGetAllTasks() {
		Mockito.when(taskDAO.findAll()).thenReturn(Arrays.asList(sampleTask));

		List<TaskDTO> tasks = taskService.getAllTasks();

		assertNotNull(tasks);
		assertEquals(1, tasks.size());
		verify(taskDAO).findAll();
	}

	@Test
	void testGetTaskById() {
		Mockito.when(taskDAO.findById(1L)).thenReturn(Optional.of(sampleTask));

		TaskDTO taskDTO = taskService.getTaskById(1L);

		assertNotNull(taskDTO);
		assertEquals("Sample Task", taskDTO.getTitle());
	}

	@Test
	void testGetTaskByIdNotFound() {
		when(taskDAO.findById(1L)).thenReturn(Optional.empty());

		assertThrows(TaskNotFoundException.class, () -> {
			taskService.getTaskById(1L);
		});
	}

	@Test
	void testCreateTask() {
		when(taskDAO.save(any(Task.class))).thenReturn(sampleTask);

		TaskDTO createdTask = taskService.createTask(sampleTaskDTO);

		assertNotNull(createdTask);
		assertEquals("Sample Task", createdTask.getTitle());
		verify(taskDAO).save(any(Task.class));
	}

}
