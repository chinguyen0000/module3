-- create database student_management;
use student_management;

CREATE TABLE Class (
    class_id INT PRIMARY KEY,
    class_name VARCHAR(50)
);

CREATE TABLE Teacher (
    teacher_id INT PRIMARY KEY,
    teacher_name VARCHAR(50),
    teacher_age INT,
    teacher_country VARCHAR(50)
);
