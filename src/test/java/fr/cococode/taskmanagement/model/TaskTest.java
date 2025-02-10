package fr.cococode.taskmanagement.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/*
 * Validator : (Vient de Spring Validation Framework) Interface utilisée pour valider les contraintes sur les données.
 * 	L'interface Validator définit les fonctionnalités d'un valideur.
 * 
 * 	Pour valider les contraintes sur les données d'un bean, il faut obtenir une instance de l'interface Validator, 
 * 	utiliser cette instance pour valider les données d'un bean. Les éventuelles erreurs détectées par cette validation sont retournées 
 * 	sous la forme d'un Set d'objets de type ConstraintViolation.
 */

public class TaskTest {

	private Validator validator;

	@BeforeEach
	void setUp() {
		// obtenir une instance de fabrique
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		// obtenir une instance de type Validator
		validator = factory.getValidator();
	}

	@Test
	void testValidTask() {
		Task task = new Task();

		task.setTitle("Valid task");
		task.setDescription("that's a new task !");
		task.setStatus(TaskStatus.TODO);

		Set<ConstraintViolation<Task>> violations = validator.validate(task);
		assertTrue(violations.isEmpty(), "Task should be valid");
	}

	@Test
	void testInvalidtask() {
		Task task = new Task();

		task.setTitle(""); // violation : empty title
		task.setDescription("that's a new invalid task !");
		task.setStatus(TaskStatus.TODO);

		Set<ConstraintViolation<Task>> violations = validator.validate(task);

		assertFalse(violations.isEmpty(), "Task should be invalid");
		assertEquals(1, violations.size());
		assertEquals("Title is required", violations.iterator().next().getMessage());
	}

}
