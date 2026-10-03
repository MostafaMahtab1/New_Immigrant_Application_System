-- Target the correct dockerized container database schema
USE immigrant_db;

-- 1. Check all saved Immigrant Primary Applicants
SELECT * FROM immigrant_entity;

-- 2. Check all saved Alien Relatives
SELECT * FROM alien_relative;

-- 3. View specific status tracks in the role pipeline
SELECT applicant_id, full_name, status FROM immigrant_entity;

-- 4. Find relatives linked to a specific applicant (e.g., John Doe)
-- SELECT * FROM alien_relative WHERE immigrant_id = 'A123456789';
