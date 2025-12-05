CREATE TABLE Account (
	user_ID INT PRIMARY KEY,
	username VARCHAR(20) NOT NULL,
	password VARCHAR(20) NOT NULL
);

CREATE TABLE Patient(
	patient_ID INT PRIMARY KEY,
	fullname VARCHAR(100),
	email VARCHAR(100) UNIQUE NOT NULL,
);

CREATE TABLE Specialist(
	specialist_ID INT PRIMARY KEY,
	fullname VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	phone INT,
	user_ID INT FOREIGN KEY REFERENCES Account(user_ID),
	patient_ID INT FOREIGN KEY REFERENCES Patient(patient_ID)
);

CREATE TABLE Healing_Information(
	ID INT PRIMARY KEY,
	place VARCHAR(100),
	date DATE,
	extra_information VARCHAR(100),
	Description VARCHAR(100),
	fee INT CHECK(fee>=0),
	specialist_ID INT FOREIGN KEY REFERENCES Specialist(specialist_ID),
	patient_ID INT FOREIGN KEY REFERENCES Patient(patient_ID)
);

CREATE TABLE Symptom(
	symptom_ID INT PRIMARY KEY,
	description VARCHAR(100),
	name VARCHAR(100) NOT NULL
);

CREATE TABLE Test(
	test_ID INT PRIMARY KEY,
	number_question INT,
	total INT
);

CREATE TABLE Answer_Set(
	answerset_ID INT PRIMARY KEY,
	number_ans INT
);

CREATE TABLE Question(
	question_ID INT PRIMARY KEY,
	title VARCHAR(100) NOT NULL,
	test_ID INT FOREIGN KEY REFERENCES Test(test_ID),
	answerset_ID INT FOREIGN KEY REFERENCES Answer_Set(answerset_ID)
);

CREATE TABLE Answer(
	answer_ID INT PRIMARY KEY,
	title VARCHAR(100) NOT NULL,
	weight INT CHECK(weight>0),
	answerset_ID INT FOREIGN KEY REFERENCES Answer_Set(answerset_ID)
);

CREATE TABLE Disease(
	disease_ID INT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(100),
	test_ID INT FOREIGN KEY REFERENCES Test(test_ID)
);

CREATE TABLE Result(
	result_ID INT PRIMARY KEY,
	weight INT CHECK(weight>0),
	description VARCHAR(100),
	patient_ID INT FOREIGN KEY REFERENCES Patient(patient_ID),
	test_ID INT FOREIGN KEY REFERENCES Test(test_ID)
);

CREATE TABLE Solution(
	solution_ID INT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	type INT UNIQUE,
	benefit VARCHAR(100),
	symptom_ID INT FOREIGN KEY REFERENCES Symptom(symptom_ID)
);

CREATE TABLE Symptom_Disease(
	symptom_ID INT,
	disease_ID INT,
	PRIMARY KEY(symptom_ID, disease_ID),
	FOREIGN KEY(symptom_ID) REFERENCES Disease(disease_ID),
	FOREIGN KEY(disease_ID) REFERENCES Symptom(symptom_ID)
);

CREATE TABLE Disease_Result(
	disease_ID INT,
	result_ID INT,
	PRIMARY KEY(disease_ID, result_ID),
	FOREIGN KEY(disease_ID) REFERENCES Result(result_ID),
	FOREIGN KEY(result_ID) REFERENCES Disease(disease_ID),
);

CREATE TABLE Result_Solution(
	result_ID INT,
	solution_ID INT,
	PRIMARY KEY(result_ID, solution_ID),
	FOREIGN KEY(result_ID) REFERENCES Solution(solution_ID),
	FOREIGN KEY(solution_ID) REFERENCES Result(result_ID)
);