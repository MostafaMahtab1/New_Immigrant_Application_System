-- Dummy data for testing purposes
-- Clear tables
DELETE FROM alien_relative;
DELETE FROM immigrant_entity;

-- Insert immigrants
INSERT INTO immigrant_entity
(applicant_id, full_name, dob, address, email, phone, status)
VALUES
('A123456789', 'John Alexander Doe', '1987-03-15', '123 Main Street, Springfield, IL 62704', 'john.doe@email.com', '555-123-4567', 'PENDING_REVIEW'),
('A987654321', 'Maria Sanchez', '1990-08-10', '456 Oak Ave, Austin, TX', 'maria.sanchez@example.com', '555-555-0101', 'IN_REVIEW'),
('A543216789', 'David Chen', '1985-01-04', '12 Pine St, San Francisco, CA', 'david.chen@example.com', '555-999-2222', 'COMPLETED');

-- Insert alien relatives (no id field)
INSERT INTO alien_relative
(full_name, dob, address, email, phone, immigrant_id)
VALUES
('Alice Doe', '2010-06-12', '123 Main Street, Springfield, IL 62704', 'alice.doe@email.com', '555-123-4568', 'A123456789'),
('Bob Sanchez', '2012-11-23', '456 Oak Ave, Austin, TX', 'bob.sanchez@example.com', '555-555-0102', 'A987654321');
