-- create database QuanLySinhVien;
use QuanLySinhVien;

CREATE TABLE class (
    ClassID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ClassName VARCHAR(60) NOT NULL,
    StartDate DATETIME NOT NULL,
    Status BIT
);

CREATE TABLE student (
    StudentID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    StudentName VARCHAR(30) NOT NULL,
    Address VARCHAR(50),
    Phone VARCHAR(20),
    Status BIT,
    ClassID INT NOT NULL,
    FOREIGN KEY (ClassID)
        REFERENCES class (ClassID)
);

CREATE TABLE subject (
    SubID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SubName VARCHAR(30) NOT NULL,
    Credit TINYINT NOT NULL DEFAULT 1 CHECK (Credit >= 1),
    Status BIT DEFAULT 1
);

CREATE TABLE mark (
    MarkID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SubID INT NOT NULL,
    StudentID INT NOT NULL,
    Mark FLOAT DEFAULT 0 CHECK (Mark BETWEEN 0 AND 100),
    ExamTimes TINYINT DEFAULT 1,
    UNIQUE (SubID , StudentID),
    FOREIGN KEY (SubID)
        REFERENCES subject (SubID),
    FOREIGN KEY (StudentID)
        REFERENCES student (StudentID)
);

INSERT INTO class
VALUES (1, 'A1', '2008-12-20', 1);
INSERT INTO class
VALUES (2, 'A2', '2008-12-22', 1);
INSERT INTO class
VALUES (3, 'B3', current_date, 0);

INSERT INTO student (StudentName, Address, Phone, Status, ClassID)
VALUES ('Hung', 'Ha Noi', '0912113113', 1, 1);
INSERT INTO student (StudentName, Address, Status, ClassID)
VALUES ('Hoa', 'Hai phong', 1, 1);
INSERT INTO student (StudentName, Address, Phone, Status, ClassID)
VALUES ('Manh', 'HCM', '0123123123', 0, 2);

INSERT INTO subject
VALUES (1, 'CF', 5, 1),
 (2, 'C', 6, 1),
 (3, 'HDJ', 5, 1),
 (4, 'RDBMS', 10, 1);
 
INSERT INTO mark (SubID, StudentID, Mark, ExamTimes)
VALUES (1, 1, 8, 1),
 (1, 2, 10, 2),
 (2, 1, 12, 1);
 

select * from student where StudentName like "H%";

select * from class where month(StartDate) = 12;

select * from subject where Credit between 3 and 5;

update student set ClassID = 10 where StudentName = "Hung" and StudentID = 1;
select * from student;

select StudentName, SubName, Mark from ((subject inner join mark on subject.SubID = mark.SubID) inner join student on mark.StudentID = student.StudentID) order by Mark desc, StudentName asc;