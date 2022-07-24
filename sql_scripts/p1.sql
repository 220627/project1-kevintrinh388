-- REIMBURSEMENT STATUS, 1:Pending, 2:Approved, 3:Denied
CREATE TABLE ers_reimbursement_status(
	reimb_status_id serial PRIMARY KEY,
	reimb_status TEXT
);
INSERT INTO ers_reimbursement_status (reimb_status) 
VALUES ('Pending'), ('Approved'), ('Denied');
SELECT * FROM ers_reimbursement_status;
DROP TABLE ers_reimbursement_status;


-- REIMBURSEMENT TYPE, 1:Lodging, 2:Travel, 3:Food, 4:Other
CREATE TABLE ers_reimbursement_type(
	reimb_type_id serial PRIMARY KEY,
	reimb_type TEXT
);
INSERT INTO ers_reimbursement_type (reimb_type)
VALUES ('Lodging'), ('Travel'), ('Food'), ('Other');
SELECT * FROM ers_reimbursement_type;
DROP TABLE ers_reimbursement_type;


-- USER ROLES, 1:Employee, 2:Finance Manager
CREATE TABLE ers_user_roles(
	ers_user_role_id serial PRIMARY KEY,
	user_role TEXT
);
INSERT INTO ers_user_roles (user_role)
VALUES ('Employee'), ('Finance Manager');
SELECT * FROM ers_user_roles;
DROP TABLE ers_user_roles;


-- USERS
CREATE TABLE ers_users(
	ers_users_id serial PRIMARY KEY,
	ers_username TEXT UNIQUE,
	ers_password TEXT,
	user_first_name TEXT,
	user_last_name TEXT,
	user_email TEXT UNIQUE,
	user_role_id int REFERENCES ers_user_roles(ers_user_role_id)
);
INSERT INTO ers_users (ers_username,ers_password,user_first_name,user_last_name,user_email,user_role_id)
VALUES ('test123','pass123','TesterFirst','TesterLast','tester@gmail.com',1);
SELECT * FROM ers_users;
DROP TABLE ers_users;


-- REIMBURSEMENT
CREATE TABLE ers_reimbursement(
	reimb_id serial PRIMARY KEY,
	reimb_amount int,
	reimb_submitted timestamp,
	reimb_author int REFERENCES ers_users(ers_users_id),
	reimb_status_id int REFERENCES ers_reimbursement_status(reimb_status_id),
	reimb_type_id int REFERENCES ers_reimbursement_type(reimb_type_id)
);
INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_author, reimb_status_id, reimb_type_id)
VALUES (5000, '1999-01-08 04:05:06', 1,1,1);
SELECT * FROM ers_reimbursement;





