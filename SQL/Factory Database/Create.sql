-- Create database
CREATE DATABASE PDM_Lab04;

-- Use the database
USE PDM_Lab04;

-- Create table 'department'
CREATE TABLE department (
  dname VARCHAR(15) NOT NULL,
  dnumber DECIMAL(4, 0) NOT NULL,
  mgrssn CHAR(9) NOT NULL,
  mgrstartdate DATE NULL,
  PRIMARY KEY (dnumber),
  UNIQUE (dname)
);

-- Create table 'dependent'
CREATE TABLE dependent (
  essn CHAR(9) NOT NULL,
  dependent_name VARCHAR(15) NOT NULL,
  sex CHAR(1) NULL,
  bdate DATE NULL,
  relationship VARCHAR(8) NULL,
  PRIMARY KEY (essn, dependent_name)
);

-- Create table 'dept_locations'
CREATE TABLE dept_locations (
  dnumber DECIMAL(4, 0) NOT NULL,
  dlocation VARCHAR(15) NOT NULL,
  PRIMARY KEY (dnumber, dlocation)
);

-- Create table 'employee'
CREATE TABLE employee (
  fname VARCHAR(15) NOT NULL,
  minit VARCHAR(1) NULL,
  lname VARCHAR(15) NOT NULL,
  ssn CHAR(9) NOT NULL,
  bdate DATE NULL,
  address VARCHAR(30) NULL,
  sex CHAR(1) NULL,
  salary DECIMAL(10, 2) NULL,
  superssn CHAR(9) NULL,
  dno DECIMAL(4, 0) NULL,
  PRIMARY KEY (ssn)
);

-- Create table 'project'
CREATE TABLE project (
  pname VARCHAR(15) NOT NULL,
  pnumber DECIMAL(4, 0) NOT NULL,
  plocation VARCHAR(15) NULL,
  dnum DECIMAL(4, 0) NOT NULL,
  PRIMARY KEY (pnumber),
  UNIQUE (pname)
);

-- Create table 'works_on'
CREATE TABLE works_on (
  essn CHAR(9) NOT NULL,
  pno DECIMAL(4, 0) NOT NULL,
  hours DECIMAL(4, 1) NULL,
  PRIMARY KEY (essn, pno)
);

-- Add foreign keys
ALTER TABLE department 
ADD CONSTRAINT FK_department_employee 
FOREIGN KEY (mgrssn) REFERENCES employee(ssn);

ALTER TABLE dependent 
ADD CONSTRAINT FK_dependent_employee 
FOREIGN KEY (essn) REFERENCES employee(ssn);

ALTER TABLE dept_locations 
ADD CONSTRAINT FK_dept_locations_department 
FOREIGN KEY (dnumber) REFERENCES department(dnumber);

ALTER TABLE employee 
ADD CONSTRAINT FK_employee_employee 
FOREIGN KEY (superssn) REFERENCES employee(ssn);

ALTER TABLE works_on 
ADD CONSTRAINT FK_works_on_employee 
FOREIGN KEY (essn) REFERENCES employee(ssn);

ALTER TABLE works_on 
ADD CONSTRAINT FK_works_on_project 
FOREIGN KEY (pno) REFERENCES project(pnumber);
