
-- District Admin
INSERT INTO dist_admin(dist_name,dist_state,admin_name,admin_surname,admin_email,admin_password,role)
VALUES ('Kolhapur','Maharastra','Deepak','Lohar','deepaklohar1972@gmail.com','$2a$10$MySmioX/u0niioN19G6qhelAw3LD54cITfmEuGuAbEIkDIoBwUaQe','SUPER_ADMIN');

-- Department
INSERT INTO department(dept_name,dist_admin_id) VALUES ('Water Supply',1),
('Street Light',1);

-- Department Admin
INSERT INTO dept_admin (first_name, last_name,email,password,dist_admin_id,dept_id,role) 
VALUES ('Saniya','Lohar','saniyalohar2005@gmail.com','$2a$10$MySmioX/u0niioN19G6qhelAw3LD54cITfmEuGuAbEIkDIoBwUaQe',1,1,'ADMIN');

-- users
INSERT INTO users (user_name, user_surname, user_email, password, role, dist_id) VALUES 
('Amit', 'Sharma', 'amit.sharma@gmail.com', '$2a$10$MySmioX/u0niioN19G6qhelAw3LD54cITfmEuGuAbEIkDIoBwUaQe', 'USER', 1),
('Priya', 'Patel', 'priya.patel@gmail.com', '$2a$10$MySmioX/u0niioN19G6qhelAw3LD54cITfmEuGuAbEIkDIoBwUaQe', 'USER', 2);

-- issue
INSERT INTO issues (title, description, auther_id, status, dept_id, dist_id, created_at) VALUES 
('Massive pothole on Main Street', 'Large pothole causing severe traffic slowdowns and vehicle damage.', 1, 'PENDING', 1, 1, '2026-02-18 08:30:00'),
('Streetlights not working', 'Entire row of streetlights is out near the public park, causing safety concerns.', 1, 'PENDING', 1, 1, '2026-02-17 19:15:00'),
('Garbage overflow at Market', 'The community bins have not been cleared for three days. Foul smell spreading.', 1, 'PENDING', 1, 1, '2026-02-16 10:00:00'),
('Irregular water supply', 'No water supply for the last 48 hours in the residential blocks of Sector 4.', 2, 'PENDING', 2, 1, '2026-02-19 07:45:00'),
('Blocked stormwater drain', 'Drain is completely choked with plastic, causing waterlogging during unseasonal rain.', 2, 'PENDING', 2, 1, '2026-02-10 14:20:00'),
('Broken pavement near school', 'Footpath tiles are broken and missing, dangerous for school children walking by.', 2, 'PENDING', 1, 1, '2026-02-18 11:10:00'),
('Illegal dumping of debris', 'Construction waste has been dumped overnight blocking half the access road.', 2, 'PENDING', 1, 1, '2026-02-19 09:05:00'),
('Contaminated drinking water', 'Tap water is appearing muddy and has a strange chemical odor.', 2, 'PENDING', 2, 1, '2026-02-19 13:30:00'),
('Dead tree branch hazard', 'A massive dry branch is hanging dangerously over the pedestrian walkway.', 2, 'PENDING', 2, 1, '2026-02-12 16:40:00'),
('Stray animal menace', 'Pack of aggressive stray dogs chasing two-wheelers at night near the station.', 2, 'PENDING', 2, 1, '2026-02-18 22:50:00');