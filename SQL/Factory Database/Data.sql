DELETE FROM employee;
INSERT INTO employee VALUES ('James', 'E', 'Borg',
        '888665555', '10-NOV-27', 'Houston,TX', 'M', 55000, null, null);
INSERT INTO employee VALUES ('Frank', 'T', 'Wong',
        '333445555', '08-DEC-45', 'Houston,TX', 'M', 40000, '888665555', null);
INSERT INTO employee VALUES ('Jennifer', 'S', 'Wallace',
        '987654321', '20-JUN-31', 'Bellaire,TX', 'F', 43000, '888665555', null);

DELETE FROM department;
INSERT INTO department VALUES ('Research', 5, '333445555', '22-MAY-78');
INSERT INTO department VALUES ('Administration', 4, '987654321', '01-JAN-85');
INSERT INTO department VALUES ('Headquarters', 1, '888665555', '19-JUN-71');

UPDATE employee SET DNO = 5 WHERE ssn = '333445555';
UPDATE employee SET DNO = 4 WHERE ssn = '987654321';
UPDATE employee SET DNO = 1 WHERE ssn = '888665555';

INSERT INTO employee VALUES ('John', 'B', 'Smith',
        '123456789', '09-Jan-55', 'Houston,TX', 'M', 30000, '333445555', 5);
INSERT INTO employee VALUES ('Alicia', 'J', 'Zelaya',
        '999887777', '19-JUL-58', 'Spring,TX', 'F', 25000, '987654321', 4);
INSERT INTO employee VALUES ('Ramesh', 'K', 'Narayan',
        '666884444', '15-SEP-52', 'Humble,TX', 'M', 38000, '333445555', 5);
INSERT INTO employee VALUES ('Joyce', 'A', 'English',
        '453453453', '31-JUL-62', 'Houston, TX', 'F', 25000, '333445555', 5);
INSERT INTO employee VALUES ('Ahmad', 'V', 'Jabbar',
        '987987987', '29-MAR-59', 'Houston,TX', 'M', 25000, '987654321', 4);

DELETE FROM project;
INSERT INTO project VALUES ('ProductX', 1, 'Bellaire',  5);
INSERT INTO project VALUES ('ProductY', 2, 'Sugarland', 5);
INSERT INTO project VALUES ('ProductZ', 3, 'Houston', 5);
INSERT INTO project VALUES ('Computerization', 10, 'Stafford', 4);
INSERT INTO project VALUES ('Reorganization', 20, 'Houston', 1);
INSERT INTO project VALUES ('Newbenefits', 30,  'Stafford', 4);

DELETE FROM dept_locations;
INSERT INTO dept_locations VALUES (1, 'Houston');
INSERT INTO dept_locations VALUES (4, 'Stafford');
INSERT INTO dept_locations VALUES (5, 'Bellaire');
INSERT INTO dept_locations VALUES (5, 'Sugarland');
INSERT INTO dept_locations VALUES (5, 'Houston');

DELETE from dependent;
INSERT INTO dependent VALUES ('333445555','Alice','F','05-APR-76','Daughter');
INSERT INTO dependent VALUES ('333445555','Theodore','M','25-OCT-73','Son');
INSERT INTO dependent VALUES ('333445555','Joy','F','03-MAY-48','Spouse');
INSERT INTO dependent VALUES ('987654321','Abner','M','29-FEB-32','Spouse');
INSERT INTO dependent VALUES ('123456789','Michael','M','01-JAN-78','Son');
INSERT INTO dependent VALUES ('123456789','Alice','F', '31-DEC-78','Daughter');
INSERT INTO dependent VALUES ('123456789','Elizabeth','F','05-MAY-57','Spouse');

DELETE FROM works_on;
INSERT INTO works_on VALUES ('123456789', 1,  32.5);
INSERT INTO works_on VALUES ('123456789', 2,  7.5);
INSERT INTO works_on VALUES ('666884444', 3,  40.0);
INSERT INTO works_on VALUES ('453453453', 1,  20.0);
INSERT INTO works_on VALUES ('453453453', 2,  20.0);
INSERT INTO works_on VALUES ('333445555', 2,  10.0);
INSERT INTO works_on VALUES ('333445555', 3,  10.0);
INSERT INTO works_on VALUES ('333445555', 10, 10.0);
INSERT INTO works_on VALUES ('333445555', 20, 10.0);
INSERT INTO works_on VALUES ('999887777', 30, 30.0);
INSERT INTO works_on VALUES ('999887777', 10, 10.0);
INSERT INTO works_on VALUES ('987987987', 10, 35.0);
INSERT INTO works_on VALUES ('987987987', 30, 5.0);
INSERT INTO works_on VALUES ('987654321', 30, 20.0);
INSERT INTO works_on VALUES ('987654321', 20, 15.0);
INSERT INTO works_on VALUES ('888665555', 20, null);