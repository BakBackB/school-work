CREATE TABLE Staff (
    StaffID INT PRIMARY KEY,
    StfFirstName VARCHAR(100) NOT NULL,
    StfLastName VARCHAR(100) NOT NULL,
    StfStreetAddress VARCHAR(100),
    StfCity VARCHAR(100),
    StfState VARCHAR(100),
    StfZipCode VARCHAR(100),
    StfAreaCode VARCHAR(10),
    StfPhoneNumber VARCHAR(10) UNIQUE,
    Salary DECIMAL(10,2) CHECK(Salary >= 0),
    DateHired DATE,
    Position VARCHAR(100)
);

CREATE TABLE Faculty (
    StaffID INT PRIMARY KEY,
    Title VARCHAR(100) NOT NULL,
    Status VARCHAR(100) NOT NULL,
    Tenured BIT DEFAULT 0,
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)
);

CREATE TABLE Departments (
    DepartmentID INT PRIMARY KEY,
    DeptName VARCHAR(100) NOT NULL,
    DeptChair INT FOREIGN KEY REFERENCES Faculty(StaffID)
);

CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY,
    CategoryDescription VARCHAR(100) DEFAULT 'None',
    DeparmentID INT FOREIGN KEY REFERENCES Departments(DepartmentID)
);

CREATE TABLE Faculty_Categories (
    StaffID INT,
    CategoryID INT,
    PRIMARY KEY (StaffID, CategoryID),
    FOREIGN KEY (StaffID) REFERENCES Faculty(StaffID),
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

CREATE TABLE Subjects (
    SubjectID INT PRIMARY KEY,
    SubjectCode VARCHAR(100) UNIQUE,
    SubjectName VARCHAR(100) NOT NULL,
    SubjectPreReq VARCHAR(100) DEFAULT 'None',
    SubjectDescription VARCHAR(100) DEFAULT 'None',
    CategoryID INT FOREIGN KEY REFERENCES Categories(CategoryID)
);

CREATE TABLE Faculty_Subjects (
    StaffID INT,
    SubjectID INT,
    ProficiencyRating INT,
    PRIMARY KEY (StaffID, SubjectID),
    FOREIGN KEY (StaffID) REFERENCES Faculty(StaffID),
    FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID)
);

CREATE TABLE Buildings (
    BuildingCode VARCHAR(100) PRIMARY KEY,
    BuildingName VARCHAR(100) UNIQUE,
    NumberOfFloors INT CHECK(NumberOfFloors > 0),
    ElevatorAccess BIT CHECK(ElevatorAccess IN (0,1)),
    SiteParkingAvailable BIT CHECK(SiteParkingAvailable IN (0,1))
);

CREATE TABLE Classrooms (
    ClassRoomID INT PRIMARY KEY,
    PhoneAvailable BIT CHECK(PhoneAvailable IN (0,1)),
    BuildingCode VARCHAR(100) FOREIGN KEY REFERENCES Buildings(BuildingCode)
);

CREATE TABLE Classes (
    ClassID INT PRIMARY KEY,
    Credits INT DEFAULT 3,
    StartDate DATE,
    StartTime TIME,
    Duration INT,
    MondaySchedule VARCHAR(100),
    TuesdaySchedule VARCHAR(100),
    WednesdaySchedule VARCHAR(100),
    ThursdaySchedule VARCHAR(100),
    FridaySchedule VARCHAR(100),
    SaturdaySchedule VARCHAR(100),
    SubjectID INT FOREIGN KEY REFERENCES Subjects(SubjectID),
    ClassRoomID INT FOREIGN KEY REFERENCES Classrooms(ClassRoomID)
);

CREATE TABLE Faculty_Classes (
    StaffID INT,
    ClassID INT,
    PRIMARY KEY (StaffID, ClassID),
    FOREIGN KEY (StaffID) REFERENCES Faculty(StaffID),
    FOREIGN KEY (ClassID) REFERENCES Classes(ClassID)
);

CREATE TABLE Student_Class_Status (
    CategoryID INT PRIMARY KEY,
    CategoryDescription VARCHAR(100) DEFAULT 'None'
);

CREATE TABLE Majors (
    MajorID INT PRIMARY KEY,
    Major VARCHAR(100) NOT NULL
);

CREATE TABLE Students (
    StudentID INT PRIMARY KEY,
    StudFirstName VARCHAR(100) NOT NULL,
    StudLastName VARCHAR(100) NOT NULL,
    StudStreetAddress VARCHAR(100),
    StudCity VARCHAR(100),
    StudState VARCHAR(100),
    StudZipCode VARCHAR(100),
    StudAreaCode VARCHAR(10),
    StudPhoneNumber VARCHAR(10) UNIQUE,
    StudGPA DECIMAL(3,2) CHECK(StudGPA > 0),
    StudMajor INT NOT NULL,
    FOREIGN KEY (StudMajor) REFERENCES Majors(MajorID)
);

CREATE TABLE Student_Schedules (
    ClassID INT,
    StudentID INT,
    Grade VARCHAR(5),
    PRIMARY KEY (ClassID, StudentID),
    FOREIGN KEY (ClassID) REFERENCES Classes(ClassID),
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID),
    ClassStatus INT FOREIGN KEY REFERENCES Student_Class_Status(CategoryID)
);

