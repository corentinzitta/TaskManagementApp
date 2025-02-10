package fr.cococode.taskmanagement.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.cococode.taskmanagement.dto.TaskDTO;
import fr.cococode.taskmanagement.model.TaskStatus;
import fr.cococode.taskmanagement.service.TaskService;

/*
 * (Il était également possible d'utiliser @SpringBootTest à la place de @WebMvcTest).
 * 
 * => L'avantage de @WebMvcTest est d'être plus léger que @SpringBootTest qui auto-configure
 * 	tout, @WebMvcTest ne va configurer que la partie MVC d'un controller en particulier en
 *  le fournissant par argument (TaskController.class)
 * 
 * 	En utilisant le stéréotype @WebMvcTest, un attribut 'MockMvc' est nécessaire. Il est accompagné d'un
 * 	autre attribut de couche de service (TaskService ici) utilisant l'annotation @MockitoBean.
 * 
 * @MockitoBean (ex MockBean) :  Utilisé pour mocker la réponse de la couche de service 
 * 	sans appeler réellement le service
 * 
 * Un mock est un objet qui permet de simuler le comportement d'un autre objet concret de façon maitrisée.
 *  
 */

@WebMvcTest(TaskController.class)
public class TaskControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private TaskService taskService;

	@Autowired
	private ObjectMapper objectMapper;

	private TaskDTO sampleTaskDTO;

	@BeforeEach
	void setUp() {
		sampleTaskDTO = new TaskDTO();

		sampleTaskDTO.setId(1L);
		sampleTaskDTO.setTitle("Sample Task");
		sampleTaskDTO.setDescription("Sample Description");
		sampleTaskDTO.setStatus("TODO");
	}

	@Test
	void testGetAllTasks() throws Exception {
		when(taskService.getAllTasks()).thenReturn(Arrays.asList(sampleTaskDTO));

		mockMvc.perform(get("/api/tasks"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].title").value("Sample Task"));

	}

	@Test
	void testCreateTask() throws Exception {
		when(taskService.createTask(any(TaskDTO.class))).thenReturn(sampleTaskDTO);

		mockMvc.perform(post("/api/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(sampleTaskDTO)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.title").value("Sample Task"));
	}

	@Test
	void testCreateInvalidTask() throws Exception {
		TaskDTO invalidTaskDTO = new TaskDTO();
		invalidTaskDTO.setTitle(""); // Invalid title

		mockMvc.perform(post("/api/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidTaskDTO)))
				.andExpect(status().isBadRequest());
	}

}
