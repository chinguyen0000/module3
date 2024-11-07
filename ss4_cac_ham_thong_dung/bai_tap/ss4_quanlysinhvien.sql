use QuanLySinhVien;
-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.
select * from subject where Credit = (select max(Credit) from subject);

-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
select subject.*, Mark from subject left join mark on mark.SubID = subject.SubID where Mark = (select max(Mark) from mark);

-- Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần
select student.*, avg(Mark) as "Average Mark" from student left join mark on student.StudentID = mark.StudentID group by student.StudentID order by "Average Mark" desc;
