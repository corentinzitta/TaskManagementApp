-- Clear existing data (if any)
DELETE FROM tasks;

-- Reset the sequence if it exists
ALTER TABLE tasks ALTER COLUMN id RESTART WITH 1;

-- Insert sample tasks
INSERT INTO tasks (title, description, status, created_at, updated_at) 
VALUES 
    ('Complete project documentation', 
     'Write comprehensive documentation for the REST API project', 
     'TODO', 
     CURRENT_TIMESTAMP(), 
     CURRENT_TIMESTAMP());

INSERT INTO tasks (title, description, status, created_at, updated_at) 
VALUES 
    ('Implement user authentication', 
     'Add JWT-based authentication to secure the API endpoints', 
     'IN_PROGRESS', 
     CURRENT_TIMESTAMP(), 
     CURRENT_TIMESTAMP());

INSERT INTO tasks (title, description, status, created_at, updated_at) 
VALUES 
    ('Write unit tests', 
     'Achieve 80% test coverage for all service classes', 
     'TODO', 
     CURRENT_TIMESTAMP(), 
     CURRENT_TIMESTAMP());

INSERT INTO tasks (title, description, status, created_at, updated_at) 
VALUES 
    ('Setup CI/CD pipeline', 
     'Configure GitHub Actions for automated testing and deployment', 
     'TODO', 
     CURRENT_TIMESTAMP(), 
     CURRENT_TIMESTAMP());

INSERT INTO tasks (title, description, status, created_at, updated_at) 
VALUES 
    ('Code review implementation', 
     'Review and refactor code based on best practices', 
     'COMPLETED', 
     CURRENT_TIMESTAMP(), 
     CURRENT_TIMESTAMP());