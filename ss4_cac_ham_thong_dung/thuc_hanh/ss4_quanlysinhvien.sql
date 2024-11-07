use quanlysinhvien;

-- Hiển thị số lượng sinh viên ở từng nơi
select Address, count(StudentID) from student group by Address;

-- Tính điểm trung bình các môn học của mỗi học viên
select student.StudentID, student.StudentName, avg(Mark) as "Average Mark" from student join mark on student.StudentID = mark.StudentID group by student.StudentID;

-- Hiển thị những bạn học viên co điểm trung bình các môn học lớn hơn 15
select StudentName, avg(Mark) from student join mark on student.StudentID = mark.StudentID group by student.StudentID having avg(Mark) > 15;

-- Hiển thị thông tin các học viên có điểm trung bình lớn nhất.
select StudentName, avg(Mark) from student join mark on student.StudentID = mark.StudentID group by student.StudentID having avg(Mark) >= all (select avg(Mark) from mark group by StudentID);
