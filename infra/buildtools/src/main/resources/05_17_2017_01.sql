DROP TABLE ROLES;
DROP TABLE EMPLOYEEROLE;
DROP TABLE CONTACTINFO;
DROP TABLE EMPLOYEE;

CREATE TABLE EMPLOYEE (
	ID BIGSERIAL,
	LAST_NAME VARCHAR(255) NOT NULL,
	FIRST_NAME VARCHAR(255) NOT NULL,
	MIDDLE_NAME VARCHAR(255) default NULL,
	SUFFIX VARCHAR(255) default NULL,
	TITLE VARCHAR(255) default NULL,
	STREET VARCHAR(255) default NULL,
	BARANGAY VARCHAR(255) default NULL,
	CITY VARCHAR(255) default NULL,
	ZIPCODE VARCHAR(255) default NULL,
	COUNTRY VARCHAR(255) default NULL,
	BIRTHDAY DATE,
	GWA FLOAT(6),
	HIRE_DATE DATE,
	EMPLOYED BOOLEAN,
	PRIMARY KEY (ID)
);

CREATE TABLE ROLES (
	ID BIGSERIAL,
	ROLE_CODE VARCHAR(255) NOT NULL,
	ROLE_NAME VARCHAR(255) NOT NULL,
	PRIMARY KEY (ID, ROLE_CODE)
);

CREATE TABLE EMPLOYEEROLE (
	EMPLOYEE_ID BIGINT NOT NULL,
	ROLE_ID BIGINT NOT NULL,
	PRIMARY KEY (EMPLOYEE_ID, ROLE_ID),
	FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE(ID)
);

CREATE TABLE CONTACTINFO (
	EMPLOYEE_ID BIGINT NOT NULL,
	CONTACT_INFO_TYPE VARCHAR(255) NOT NULL,
	CONTACT_INFORMATION VARCHAR(255) NOT NULL,
	FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE(ID)
);


INSERT INTO employee (last_name, first_name, middle_name, suffix, title, street, barangay, city, country, zipcode, birthday, gwa, hire_date, employed)
	VALUES ('Cleese', 'John', 'Marwood', 'Sr', 'Sir', '120', 'BF', 'Makati', 'Philippines', '1200A', '1999-06-06', '1.99', '2000-12-31', '1');

INSERT INTO employee (last_name, first_name, middle_name, suffix, title, street, barangay, city, country, zipcode, birthday, gwa, hire_date, employed)
	VALUES ('Bataller', 'Joshua', 'Rex', ' ', ' ', '101', 'Isla', 'Quezon City', 'Philippines', '1400', '1990-06-06', '1.877', '9999-12-31', '0');

INSERT INTO employee (last_name, first_name, middle_name, suffix, title, street, barangay, city, country, zipcode, birthday, gwa, hire_date, employed)
	VALUES ('Bastille', 'Bella', 'Donna', ' ', 'Ms.', '1-A', 'Palanan', 'Pasig City', 'Philippines', '1122', '1991-01-01', '1.2466', '2012-11-28', '1');

INSERT INTO employee (last_name, first_name, middle_name, suffix, title, street, barangay, city, country, zipcode, birthday, gwa, hire_date, employed)
	VALUES ('Bond', 'James', 'Bond', 'Junior', 'Dr.', '222', 'Westminster', 'London', '1111', 'United Kingdom', '1978-01-31', '1.97', '2003-01-30', '1');		

INSERT INTO employee (last_name, first_name, middle_name, suffix, title, street, barangay, city, country, zipcode, birthday, gwa, hire_date, employed)
	VALUES ('Nash', 'Stephen', 'John', ' ', ' ', '133 Saskatchewan', 'Regina', 'Johannesburg', '1544', 'South Africa', '1974-02-07', '2.334', '2000-09-30', '1');			

INSERT INTO employee (last_name, first_name, middle_name, suffix, title, street, barangay, city, country, zipcode, birthday, gwa, hire_date, employed)
	VALUES ('Messi', 'Lionel', 'Andres', ' ', 'Football God', '10 Oro Street', 'Rosario', 'Buenos Aires', '2341', 'Argentina', '1987-06-24', '1.441', '1998-01-31', '1');

INSERT INTO employee (last_name, first_name, middle_name, suffix, title, street, barangay, city, country, zipcode, birthday, gwa, hire_date, employed)
	VALUES ('Nadal', 'Rafael', 'Parera', 'y', 'Prof.', '186 Balearic Street', 'Manacor', 'Barcelona', '7721', 'Spain', '1986-06-03', '1.97', '2004-04-21', '1');					


INSERT INTO roles(role_code, role_name) VALUES ('DEV', 'Developer');
INSERT INTO roles(role_code, role_name) VALUES ('QA', 'Quality Assurance');
INSERT INTO roles(role_code, role_name) VALUES ('TES', 'Tester');
INSERT INTO roles(role_code, role_name) VALUES ('MGR', 'Manager');
INSERT INTO roles(role_code, role_name) VALUES ('MGA', 'Assistant Manager');
INSERT INTO roles(role_code, role_name) VALUES ('TEN', 'Tennis Player');
INSERT INTO roles(role_code, role_name) VALUES ('FBL', 'Football Player');
INSERT INTO roles(role_code, role_name) VALUES ('BSK', 'Basketball Player');

INSERT INTO EMPLOYEEROLE VALUES ('1','2');
INSERT INTO EMPLOYEEROLE VALUES ('5','8');
INSERT INTO EMPLOYEEROLE VALUES ('6','7');
INSERT INTO EMPLOYEEROLE VALUES ('7','6');