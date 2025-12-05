List how many complete years each staff member has been with the school as of October 1, 2017, and sort the result by last name and first name. (27 rows) 
SELECT TOP 27
  StfFirstName,
  StfLastName,
  DATEDIFF(YEAR, DateHired, '2017-10-01') AS years_complete
FROM Staff;

Show me a list of staff members, their salaries, and a proposed 7 percent bonus for each staff member. (27 rows) 
SELECT TOP 27
  StfFirstName, 
  StfLastName,
  Salary,
  0.07 * Salary AS bonus
FROM Staff;

Give me a list of staff members and show them in descending order of salary. (27 rows). 
SELECT TOP 27
  StfFirstName,
  StfLastName,
  Salary
FROM Staff
ORDER BY Salary DESC;

Can you give me a staff member phone list? (27 rows). 
SELECT TOP 27
  StfFirstName,
  StfLastName,
  StfPhoneNumber
FROM Staff;

List the names of all our students and order them by the cities they live in. (18 rows). 
SELECT TOP 18
  StudFirstName,
  StudLastName,
  StudCity
FROM Students
ORDER BY StudCity;

Show me an alphabetical list of all the staff members and their salaries if they make between $40,000 and $50,000 a year. (14 rows)
SELECT TOP 14
  StfFirstName,
  StfLastName,
  Salary
FROM Staff
WHERE Salary BETWEEN 40000 AND 50000;

Show me a list of students whose last name is ‘Kennedy’ or who live in Seattle. (4 rows)
SELECT TOP 4
  StudFirstName,
  StudLastName,
  StudCity
FROM Students
WHERE StudLastName = 'Kennedy'
   OR StudCity = 'Seattle';

Show me which staff members use a post office box as their address. (3 rows).
SELECT TOP 3
  StfFirstName,
  StfLastName,
  StfStreetAddress
FROM Staff
WHERE StfStreetAddress LIKE '%Box%';

Can you show me which students live outside of the Pacific Northwest? (5 rows). 
SELECT TOP 5
  StudFirstName,
  StudLastName,
  StudState
FROM Students
WHERE StudState NOT IN ('ID', 'OR', 'WA');

List all the subjects that have a subject code starting ‘MUS’. (4 rows). 
SELECT TOP 4
  SubjectName,
  SubjectCode
FROM Subjects
WHERE SubjectCode LIKE 'MUS%';

Produce a list of the ID numbers all the Associate Professors who are employed full time. (4 rows). 
SELECT TOP 4
  StaffID,
  Title,
  Tenured
FROM Faculty
WHERE Title = 'Associate Professor'
  AND Status = 'Full Time';

List the subjects taught on Wednesday. SELECT DISTINCT
SELECT DISTINCT TOP 4
  SubjectName
FROM Subjects
WHERE SubjectID IN (
  SELECT SubjectID
  FROM Classes
  WHERE WednesdaySchedule > 0
);

Show me the students and teachers who have the same first name. 
SELECT
  StudentID,
  StudFirstName,
  StudLastName,
  StaffID,
  StfFirstName,
  StfLastName
FROM Students, Staff
WHERE StudFirstName = StfFirstName;

Display buildings and all the classrooms in each building. (47 rows).
SELECT TOP 47
  c_r.BuildingCode,
  b.BuildingName,
  ClassRoomID
FROM Class_Rooms AS c_r,
  Buildings AS b
  WHERE c_r.BuildingCode = b.BuildingCode;

List students and all the classes in which they are currently enrolled. (50 rows).
SELECT TOP 50
  s.StudentID,
  StudFirstName,
  StudLastName,
  ClassID
FROM 
  Student_Schedules AS s_s, 
  Students AS s
WHERE
  s_s.StudentID = s.StudentID
  AND ClassStatus IN(
    SELECT ClassStatus
    FROM Student_Class_Status
    WHERE ClassStatusDescription = 'Enrolled'
    );
List the faculty staff and the subject each teaches. (110 rows).
SELECT TOP 110
  s.StaffID,
  StfFirstName,
  StfLastName,
  SubjectName
FROM
  Faculty_Subjects AS f_sub,
  Staff AS s,
  Subjects AS sub
WHERE 
  f_sub.StaffID = s.StaffID
  AND f_sub.SubjectID = sub.SubjectID;
Show me the students who have a grade of 85 or better in art and who also have a grade of 85 or better in any computer course. (1 row).
SELECT TOP 1
  s.StudentID,
  StudFirstName,
  StudLastName,
  Grade
FROM Students AS s INNER JOIN Student_Schedules AS s_s ON s.StudentID = s_s.StudentID
WHERE Grade >= 85
  AND ClassID IN (
    SELECT ClassID
    FROM Classes
    WHERE SubjectID IN (
      SELECT SubjectID
      FROM Subjects
      WHERE CategoryID IN (
        SELECT CategoryID
        FROM Categories
        WHERE CategoryDescription IN ('Art', 'Computer Science')
        )
      )
    );
Show me classes that have no students enrolled. (Hint: You need only enrolled rows from Student_Classes, not completed or withdrew.) (118 rows).
SELECT TOP 118
  ClassID,
  ClassStatus
FROM Student_Schedules
WHERE ClassStatus NOT IN (
  SELECT ClassStatus
  FROM Student_Class_Status
  WHERE ClassStatusDescription = 'Enrolled'
  );
Display subjects with no faculty assigned. (1 row). 
SELECT TOP 1 SubjectName
FROM Subjects
WHERE SubjectID NOT IN (
  SELECT SubjectID
  FROM Faculty_Subjects
  );
SELECT TOP 2
  StudentID,
  StudFirstName,
  StudLastName
FROM Students
WHERE StudentID IN (
    SELECT StudentID
    FROM Student_Schedules
    WHERE ClassStatus NOT IN (
      SELECT ClassStatus
      FROM Student_Class_Status
      WHERE ClassStatusDescription = 'Enrolled'
    )
);
SELECT TOP 135
  s.StaffID,
  Title,
  StfFirstName,
  StfLastname,
  ClassID
FROM Faculty AS f INNER JOIN Staff s ON f.StaffID = s.StaffID INNER JOIN Faculty_Classes AS f_c ON s.StaffID = f_c.StaffID;
WITH Art_Faculty AS (SELECT 
  s.StaffID,
  StfFirstName,
  StfLastName,
  SubjectID,
  ProficiencyRating
FROM Staff AS s INNER JOIN Faculty_Subjects AS f_sub ON s.StaffID = f_sub.StaffID
WHERE ProficiencyRating >= 9
  AND SubjectID IN (
  SELECT SubjectID
  FROM Subjects
  WHERE CategoryID IN (
    SELECT CategoryID
	FROM Categories
	WHERE CategoryDescription = 'Art'
	)
  )
), Art_Student AS (
SELECT
  s_s.StudentID,
  StudFirstName,
  StudLastName,
  s_s.ClassID,
  SubjectID,
  Grade
FROM Student_Schedules AS s_s INNER JOIN Classes AS c ON s_s.ClassID = c.ClassID INNER JOIN Students AS s ON s_s.StudentID = s.StudentID
WHERE Grade >= 85 
  AND SubjectID IN (
  SELECT SubjectID
  FROM Subjects
  WHERE CategoryID IN (
    SELECT CategoryID
	FROM Categories
	WHERE CategoryDescription = 'Art'
	)
  )
)
SELECT TOP 12
  StudentID,
  StudFirstName,
  StudLastName,
  a_s.SubjectID,
  Grade,
  StaffID,
  StfFirstName,
  StfLastname,
  ProficiencyRating
FROM Art_Faculty AS a_f LEFT JOIN Art_Student AS a_s ON a_f.SubjectID = a_s.SubjectID;