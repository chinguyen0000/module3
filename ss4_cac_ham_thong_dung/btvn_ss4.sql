use demo;

-- 1. Hiện thị danh sách các lớp có học viên theo học và số lượng học viên của mỗi lớp.
select class_name, count(student_id) as numbers from class inner join student on class.class_id = student.class_id group by student.class_id;
-- 2. Tính điểm lớn nhất của mỗi các lớp.

-- 3. Tình điểm trung bình của từng lớp.
-- 4. Lấy ra toàn bộ tên và ngày sinh các instructor và student ở CodeGym (sử dụng UNION).
-- 5. Lấy ra top 3 học viên có điểm cao nhất của trung tâm.
select student_id, student_name, student_point from student where student_id%2=1 order by student_point desc limit 3;
-- 6. Lấy ra các học viên có điểm số là cao nhất của trung tâm.
select * from student where student_point = (select max(student_point) from student);