CREATE DATABASE Training_management_system;
USE Training_management_system;
CREATE TABLE Courses (
	Course_id INT PRIMARY KEY,
    Course_name VARCHAR(50)
);
CREATE TABLE Topic(
	topic_id INT PRIMARY KEY,
    topic_name VARCHAR(50),
    Course_id INT,
    FOREIGN KEY (Course_id) REFERENCES Courses(Course_id)
    ON DELETE SET NULL
);

CREATE TABLE Trainer(
	trainer_id INT PRIMARY KEY,
    trainer_name VARCHAR(50)
);
CREATE TABLE Batch(
	batch_id INT PRIMARY KEY,
    start_date DATE,
    end_date DATE,
    Course_id INT,
    FOREIGN KEY (Course_id) REFERENCES Courses(Course_id)
);


CREATE TABLE Candidate(
	candidate_id INT PRIMARY KEY,
    candidate_name VARCHAR(50),
    email VARCHAR(50),
    phone_no VARCHAR(15) 
);
CREATE TABLE Assignment(
	assign_id INT PRIMARY KEY,
	assign_title VARCHAR(40),
    descrip VARCHAR(100),
    due_date DATE,
    batch_id INT,
    FOREIGN KEY(batch_id) REFERENCES Batch(batch_id)
    ON DELETE SET NULL
);


CREATE TABLE cand_status(
	candidate_id INT,
    batch_id INT,
    status VARCHAR(20) CHECK (status IN ('In Progress', 'Completed', 'Terminated')),
    PRIMARY KEY (candidate_id,batch_id),
    FOREIGN KEY(batch_id) REFERENCES Batch(batch_id)
    ON DELETE CASCADE,
    FOREIGN KEY (candidate_id) REFERENCES Candidate(candidate_id)
    ON DELETE CASCADE
);

CREATE TABLE submit(
	candidate_id INT,
    assign_id INT,
    submit_date DATE,
    score INT,
    PRIMARY KEY(candidate_id,assign_id),
    FOREIGN KEY (candidate_id) REFERENCES Candidate(candidate_id)
    ON DELETE CASCADE,
    FOREIGN KEY (assign_id) REFERENCES Assignment(assign_id)
    ON DELETE CASCADE
);
CREATE TABLE Trainer_batch(
	trainer_id INT,
    batch_id INT,
    PRIMARY KEY(trainer_id,batch_id),
    FOREIGN KEY(batch_id) REFERENCES Batch(batch_id)
    ON DELETE CASCADE,
    FOREIGN KEY(trainer_id) REFERENCES Trainer(trainer_id)
    ON DELETE CASCADE
);


RENAME TABLE Courses TO Course;
