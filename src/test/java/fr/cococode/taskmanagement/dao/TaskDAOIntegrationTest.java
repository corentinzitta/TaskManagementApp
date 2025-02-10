package fr.cococode.taskmanagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import fr.cococode.taskmanagement.model.Task;
import fr.cococode.taskmanagement.model.TaskStatus;

/*
 * @DataJpaTest :  Utilisé pour tester les applications JPA. 
 * 	Il remplace la source de données de l'application en configurant et exécutant une DB en mémoire, 
 *	par défaut, il scanne les classes de type @Entity (Task) et les repositories implémentant
 *  Spring Data JPA (TaskRepository).
 * 
 * @Import : Utilisé pour inclure des beans spécifiques qui ne sont pas 
 * 	pris automatiquement par le scan de composants (component scanning).
 * 
 * Dans notre cas, nous avons utilisé @Import car @DataJpaTest procure un contexte Spring limité
 * 	par seulement les beans liés à JPA (repositories, EntityManager, etc...). Notre classe TaskDAOImpl
 * 	n'est pas inclus automatiquement par @DataJpaTest. Sans ce @Import, l'attribut @Autowired private TaskDAO taskDAO 
 *  produirait une erreur car Spring ne saurait pas quelle implémentation utiliser.
 * 
 * @Autowired : Injection automatique de dépendance. Le Spring Framework va chercher le bean 
 * 	du contexte d’application dont le type est applicable à ce qui est annoté @Autowired (constructeur, méthode ou attribut).
 */

@DataJpaTest
@Import(TaskDAOImpl.class)
public class TaskDAOIntegrationTest {
	@Autowired
	private TaskDAO taskDAO;

	@Test
	void testFindAll() {
		List<Task> tasks = taskDAO.findAll();

		assertFalse(tasks.isEmpty(), "should find initial tasks");
	}

	@Test
	void testSaveAndFindById() {
		Task newTask = new Task();

		newTask.setTitle("New test task");
		newTask.setDescription("Test description");
		newTask.setStatus(TaskStatus.TODO);

		Task savedTask = taskDAO.save(newTask);

		Optional<Task> foundTask = taskDAO.findById(savedTask.getId());

		assertTrue(foundTask.isPresent());
		assertEquals("New test task", foundTask.get().getTitle());
	}

}
