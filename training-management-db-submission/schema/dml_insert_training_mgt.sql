INSERT INTO Courses VALUES (1, 'Python Programming');
INSERT INTO Courses VALUES (2, 'Data Science');

INSERT INTO Topic VALUES (101, 'Variables and Data Types', 1);
INSERT INTO Topic VALUES (102, 'Loops and Functions', 1);
INSERT INTO Topic VALUES (103, 'Machine Learning Intro', 2);

INSERT INTO Trainer VALUES (201, 'John Mathew');
INSERT INTO Trainer VALUES (202, 'Alice Thomas');

INSERT INTO Batch VALUES (301, '2025-07-01', '2025-08-15', 1);
INSERT INTO Batch VALUES (302, '2025-08-01', '2025-09-30', 2);

INSERT INTO Candidate VALUES (401, 'Riya Johnson', 'riya@gmail.com', '9876543210');
INSERT INTO Candidate VALUES (402, 'Arjun Menon', 'arjun@gmail.com', '9876501234');

INSERT INTO Assignment VALUES (501, 'Basics Quiz', 'Covers data types and variables', '2025-07-10', 301);
INSERT INTO Assignment VALUES (503, 'Moderate Quiz', 'functions', '2025-07-13', 301);
INSERT INTO Assignment VALUES (502, 'ML Mini Project', 'Basic project on regression', '2025-08-20', 302);

INSERT INTO cand_status VALUES (401, 301, 'In Progress');
INSERT INTO cand_status VALUES (402, 301, 'In Progress');
INSERT INTO cand_status VALUES (401, 302, 'Completed');

INSERT INTO submit VALUES (401, 501, '2025-07-09', 85);
INSERT INTO submit VALUES (402, 501, '2025-07-10', 90);
INSERT INTO submit VALUES (401, 502, '2025-08-19', 95);
INSERT INTO submit VALUES (401, 503, '2025-07-13', 92);

INSERT INTO Trainer_batch VALUES (201, 301);
INSERT INTO Trainer_batch VALUES (202, 302);
INSERT INTO Trainer_batch VALUES (201, 302);
