--Basic Queries 
--1. Write a query to list the names of all employees.
SELECT 
    fname,
    minit,
    lname
FROM 
    employee; 
--2. Write a query to list the names of all female employees.
SELECT 
    fname,
    minit,
    lname
FROM 
    employee
WHERE 
    sex = 'F';
--3. Write a query to list the names of all employees along with their department names.
SELECT 
    fname, 
    minit, 
    lname, 
    dname
FROM 
    employee,
    department
WHERE 
    employee.dno = department.dnumber; 
--4. Write a query to list the names of all departments along with their manager’s social security number.
SELECT 
    dname, 
    mgrssn
FROM 
    department;
--Aggregate Functions 
--5. Write a query to find the average salary of employees in each department.
SELECT 
    dname,
    AVG(salary) AS avg_salary
FROM 
    department, 
    employee
WHERE 
    department.dnumber = employee.dno
GROUP BY 
    dname; 
--Subqueries and Joins 
--6. Write a query to list the names of employees who don’t work on any projects.
SELECT 
    fname,
    minit,
    lname
FROM 
    employee
WHERE 
    ssn NOT IN (
    SELECT 
        essn
    FROM 
        works_on
    ); 
--7. Write a query to list the names of all employees and their dependents.
SELECT 
    fname,
    minit,
    lname,
    dependent_name
FROM 
    employee,
    dependent
WHERE 
    ssn = essn;       
--8. Write a query to list the names of employees who are also managers of departments.
SELECT 
    fname,
    minit,
    lname
FROM
    employee,
    department
WHERE   
    ssn = mgrssn;
--9. Write a query to find the names of all employees who work on every project.
SELECT 
    fname, 
    minit, 
    lname
FROM 
    employee AS e
WHERE 
    NOT EXISTS (
    SELECT 
        pnumber
    FROM 
        project AS p
    WHERE 
        NOT EXISTS (
        SELECT 
            w.essn
        FROM 
            works_on AS w
        WHERE 
            w.essn = e.ssn AND 
            w.pno = p.pnumber
        )
    );
--10. Write a query to find the names of employees with the same supervisor as the employee with social security number ‘123456789’.
SELECT 
    fname,
    minit,
    lname
FROM
    employee
WHERE superssn IN (
    SELECT 
        superssn
    FROM 
        employee
    WHERE 
        ssn = '123456789'
    );
--Complex Queries 
--11. Write a query to retrieve the names of all employees in department 5 who work more than 10 hours per week on the ‘ProductX’ project.
SELECT 
    fname,
    minit,
    lname
FROM 
    employee
WHERE dno IN (
    SELECT 
        dnumber
    FROM 
        department
    WHERE
        dnumber = 5
    ) 
    AND ssn IN (
    SELECT 
        essn
    FROM 
        works_on
    WHERE
        hours > 10
        AND pno IN (
            SELECT 
                pno
            FROM 
                project
            WHERE
                pname = 'ProductX'
        )
    );
--12. Write a query to list the names of all employees who have a dependent with the same first name as themselves.
SELECT 
    e.fname,
    e.minit,
    e.lname
FROM
    employee AS e
    INNER JOIN dependent AS d ON e.ssn = d.essn
WHERE 
    e.fname = d.dependent_name; 
--13. Write a query to find the names of all employees who are directly supervised by ‘Franklin Wong’.
SELECT 
    fname,
    minit,
    lname
FROM
    employee
WHERE superssn IN (
    SELECT 
        ssn
    FROM
        employee
    WHERE 
        fname = 'Frank'
        AND lname = 'Wong' 
    );
--14. Write a query to list, for each project, the project name and the total hours per week spent on that project by all employees. 
SELECT 
    pname,
    SUM(hours) AS total_hours
FROM works_on INNER JOIN project ON project.pnumber = works_on.pno
GROUP BY 
    pname;
--15. Write a query to retrieve the names of all employees who work on every project.
SELECT 
    fname,
    minit,
    lname
FROM
    employee AS e
WHERE 
    NOT EXISTS (
        SELECT 
            pnumber
        FROM 
            project AS p
        WHERE
            NOT EXISTS (
                SELECT 
                    essn
                FROM 
                    works_on AS w
                WHERE 
                    e.ssn = w.essn
                    AND p.pnumber = w.pno
            )  
        );
--16. Write a query to retrieve the names of all employees who do not work on any project.
SELECT 
    fname,
    minit,
    lname
FROM 
    employee
WHERE 
    ssn NOT IN (
    SELECT 
        essn
    FROM 
        works_on
    ); 
--Advanced Queries 
--17. Write a query to retrieve the average salary of all female employees.
SELECT
    fname,
    minit,
    lname,
    AVG(salary) AS avg_salary_female
FROM 
    employee
WHERE 
    sex = 'F'
GROUP BY   
    fname,
    minit,
    lname;
--18. Write a query to find the names and addresses of all employees who work on at least one project located in Houston but whose department has no location in Houston.
SELECT
    fname,
    minit,
    lname,
    address
FROM 
    employee
WHERE
    ssn IN (
        SELECT 
            essn
        FROM 
            works_on
        WHERE 
            pno IN (
                SELECT 
                    pnumber
                FROM
                    project
                WHERE
                    plocation = 'Houston'
            )
    )
    AND dno NOT IN (
        SELECT 
            dnumber
        FROM 
            dept_locations
        WHERE
            dlocation = 'Houston'
    );
--19. Write a query to list the last names of all department managers who have no dependents.
SELECT 
    lname
FROM 
    employee, 
    department 
WHERE 
    ssn = mgrssn
    AND ssn NOT IN (
        SELECT 
            essn
        FROM 
            dependent
    );
--20. Write a query to retrieve, for each department whose average employee salary is more than 30000, the department name and the number of employees working for that department.
SELECT
    dname,
    COUNT(ssn) AS num_employee
FROM 
    employee INNER JOIN department ON employee.dno = department.dnumber
GROUP BY 
    dname
HAVING 
    AVG(salary) > 30000; 