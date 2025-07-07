# Training Management Database

## Description
The **Training Management System (TMS)** is a database-driven platform designed to manage training operations efficiently. It organizes **courses**, **topics**, **trainers**, **batches**, **candidates**, and **assignments** in a structured way. Candidates can enroll in multiple batches, each with a status like *In Progress*, *Completed*, or *Terminated*. Trainers can be assigned to multiple batches, and each batch can have several assignments with tracked submissions and scores.

This system is designed to manage courses, topics, trainers, batches, candidates, assignments, and their submissions effectively.

## Assumptions
- Each **course** can have multiple **topics**.
- A **batch** is linked to a single **course**.
- A **trainer** can be assigned to multiple batches, and a **batch** can have multiple trainers.
- A **candidate** can enroll in multiple batches, each with an individual status (`In Progress`, `Completed`, `Terminated`).
- Each **batch** can have multiple **assignments**, which all enrolled candidates must submit.
- **Submissions** record submission date and score per candidate per assignment.

## Instructions
- All SQL files are in `schema` and `assignments`.
- ER diagram image is stored in `er-diagram`.
