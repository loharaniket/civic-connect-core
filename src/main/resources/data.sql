
-- dist_admin
--     private Long id;
--     private String distName;
--     private String distState;
--     private String adminName;
--     private String adminSurname;
--     private String adminEmail;
--     private String adminPassword;

-- INSERT INTO dist_admin(dist_name,dist_state,admin_name,admin_surname,admin_email,admin_password,roles)
-- VALUES ('Kolhapur','Maharastra','Deepak','Lohar','deepaklohar1972@gmail.com','$2a$10$MySmioX/u0niioN19G6qhelAw3LD54cITfmEuGuAbEIkDIoBwUaQe',"SUPER_ADMIN");

INSERT INTO dist_admin(dist_name,dist_state,admin_name,admin_surname,admin_email,admin_password)
VALUES ('Kolhapur','Maharastra','Deepak','Lohar','deepaklohar1972@gmail.com','$2a$10$MySmioX/u0niioN19G6qhelAw3LD54cITfmEuGuAbEIkDIoBwUaQe');

-- INSERT INTO DEPARTMENT (department)
-- VALUES ('Public Works'),
-- ('Water Supply'),
-- ('Drainage'),
-- ('Street Light');

INSERT INTO department(dept_name,dist_admin_id) VALUES ('Water Supply',1),
('Street Light',1);

-- dept_admin
    -- private Long id;
    -- private String firstName;
    -- private String lastName;
    -- private String email;
    -- private String password;
    -- private Long distAdminId;
    -- private Long deptId;

INSERT INTO dept_admin (first_name, last_name,email,password,dist_admin_id,dept_id) 
VALUES ('Saniya','Lohar','saniyalohar2005@gmail.com','$2a$10$MySmioX/u0niioN19G6qhelAw3LD54cITfmEuGuAbEIkDIoBwUaQe',1,1);