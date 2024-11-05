create database demo;
use demo;

create table jame_account(
	user_name varchar(50) primary key,
    password varchar(50)
);

create table class(
	class_id int primary key auto_increment,
    class_name varchar(50)
);

create table student(
	student_id int primary key auto_increment,
    student_name varchar(50),
    student_birthday date,
    student_gender bit,
    student_email varchar(50),
    student_point double,
    user_name varchar(50),
    class_id int,
    foreign key(user_name) references jame_account(user_name),
    foreign key(class_id) references class(class_id)
);

create table instructor(
	instructor_id int primary key auto_increment,
	instructor_name varchar(50),
    instructor_birthday date,
	instructor_salary double
);

create table instructor_class(
	class_id int,
    instructor_id int,
    primary key(class_id,instructor_id),
    foreign key(class_id) references class(class_id),
    foreign key(instructor_id) references instructor(instructor_id)
);

insert into class (class_name) values ('c1121g1'), ('c1221g1'),('a0821i1'),('a0921i1');

insert into jame_account(user_name,password)
 values('cunn','12345'),('chunglh','12345'),('hoanhh','12345'),('dungd','12345'),('huynhtd','12345'),
 ('hainm','12345'),('namtv','12345'),('hieuvm','12345'),('kynx','12345'),('vulm','12345'),('anv','12345'),('bnv','12345');


insert into instructor(instructor_name,instructor_birthday, instructor_salary)
 values('tran van chanh','1985-02-03',100),('dang chi trung','1985-02-03',200),('nguyen vu thanh tien','1985-02-03',300);

 
 insert into student(student_name,student_birthday, student_gender,student_point, class_id,user_name) 
 values ('nguyen ngoc cu','1981-12-12',1,8,1,'cunn'),('le hai chung','1981-12-12',1,5,1,'chunglh'),
 ('hoang huu hoan','1990-12-12',1,6,2,'hoanhh'),('dau dung','1987-12-12',1,8,1,'dungd'),
 ('ta dinh huynh','1981-12-12',1,7,2,'huynhtd'),('nguyen minh hai','1987-12-12',1,9,1,'hainm'),
 ('tran van nam','1989-12-12',1,4,2,'namtv'),('vo minh hieu','1981-12-12',1,3,1,'hieuvm'),
 ('le xuan ky','1981-12-12',1,7,2,'kynx'),('le minh vu','1981-12-12',1,7,1,'vulm'),
 ('nguyen van a','1981-12-12',1,8,null,'anv'),('tran van b','1981-12-12',1,5,null,'bnv');

 insert into instructor_class(class_id,instructor_id) values (1,1),(1,2),(2,1),(2,2),(3,1),(3,2);
 

 
-- CÂU HỎI TÌM HIỂU:
-- 1. Câu lệnh JOIN trong MySQL sử dụng để làm gì? 
-- JOIN là phép kết nối dữ liệu từ nhiều bảng lại với nhau, nối 2 bảng, 3 bảng.. với nhau. 
-- Khi cần truy vấn các cột dữ liệu từ nhiều bảng khác nhau để trả về trong cùng một tập kết quả. 2 bảng kết nối được với nhau khi có 1 trường chung giữa 2 bảng này
-- Cú pháp: SELECT column_name(s) FROM table1 INNER JOIN table2 ON table1.column_name = table2.column_name;
-- 2. Các loại JOIN trong MySQL? Phân biệt sự khác nhau của chúng?
-- INNER JOIN (Hoặc JOIN): Trả về tất cả các hàng khi có ít nhất một giá trị ở cả hai bảng
-- LEFT OUTER JOIN (Hoặc LEFT JOIN): Trả lại tất cả các dòng từ bảng bên trái, và các dòng đúng với điều kiện từ bảng bên phải
-- RIGHT OUTER JOIN (Hoặc RIGHT JOIN): Trả lại tất cả các hàng từ bảng bên phải, và các dòng thỏa mãn điều kiện từ bảng bên trái
-- FULL OUTER JOIN (Hoặc OUTER JOIN): Trả về tất cả các dòng đúng với 1 trong các bảng
-- 3. Câu lệnh WHERE được sử dụng với mục đích gì?
-- Sử dụng để chỉ định điều kiện khi lấy dữ liệu từ một bảng hoặc nối nhiều bảng với nhau. Nếu điều kiện được thỏa mãn thì nó chỉ trả về những giá trị cụ thể trong bảng.
-- Sử dụng để lọc các bản ghi và chỉ lấy những bản ghi phù hợp với yêu cầu hoặc thực sự cần thiết.
-- WHERE không đi một mình mà thường được sử dụng kết hợp với lệnh SELECT, lệnh UPDATE, lệnh DELETE và rất nhiều lệnh khác.
-- 4. Ý nghĩa sử dụng của câu lệnh GROUP BY, HAVING?
-- Câu lệnh GROUP BY nhóm các hàng có cùng giá trị thành các hàng tóm tắt, chẳng hạn như "tìm số lượng khách hàng ở mỗi quốc gia"
-- Câu lệnh GROUP BY thường được sử dụng với các hàm tổng hợp (COUNT(), MAX(), MIN(), SUM(), AVG()) để nhóm tập kết quả theo một hoặc nhiều cột.
-- Mệnh đề HAVING đã được thêm vào SQL vì từ khóa WHERE không thể được sử dụng với các hàm tổng hợp.
-- Cú pháp: SELECT column_name(s)
-- 			FROM table_name
-- 			WHERE condition
-- 			GROUP BY column_name(s)
-- 			HAVING condition
-- 			ORDER BY column_name(s);
-- 5. Ý nghĩa sử dụng của câu lệnh ORDER BY?
-- Từ khóa ORDER BY được sử dụng để sắp xếp tập kết quả theo thứ tự tăng dần hoặc giảm dần.
-- Cú pháp: SELECT column1, column2, ...
-- 			FROM table_name
-- 			ORDER BY column1, column2, ... ASC|DESC;

-- BÀI TẬP:

select * from student;
-- 1. Lấy ra thông tin tất cả học viên có lớp học và tên lớp mà các học viên đó đang theo học.
select * from student inner join class on class.class_id = student.class_id order by student_id;

-- 2. Lấy ra thông tin tất cả học viên (bao gồm có và chưa có lớp) và tên lớp (nếu có) mà các học viên đó đang theo học.
select * from student left join class on class.class_id = student.class_id order by student_id;

-- 3. Lấy thông tin của các học viên tên “Hai” và 'Huynh’.
select * from student where student_name like "% hai" or  student_name like "% huynh";

-- 4. Lấy ra thông tin học viên có điểm lớn hơn 5 .
select * from student where student_point > 5;

-- 5. Lấy ra thông tin học viên có họ là “nguyen”
select * from student where  student_name like "nguyen %";

-- 6. Thông kế số lượng học sinh theo từng loại điểm.
select student_point as Point, count(student_id) as Count from student group by student_point;

-- 7. Thông kế số lượng học sinh theo điểm và điểm phải lớn hơn 5
select student_point as Point, count(student_id) as Count from student group by student_point having student_point > 5;

-- 8. Thông kế số lượng học sinh theo điểm lớn hơn 5 và chỉ hiện thị với số lượng >= 2
select student_point as Point, count(student_id) as Count from student group by student_point having student_point > 5 and count(student_id) >= 2;

-- 9. Lấy ra danh sách học viên của lớp c1121g1 và sắp xếp tên học viên theo alphabet.
select * from student left join class on student.class_id = class.class_id where class_name = "c1121g1" order by substring_index(student_name, " ", -1) asc;

