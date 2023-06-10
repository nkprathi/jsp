show databases;
use student_record;
create table Student(id int,name varchar(50),Location varchar(50));
insert into Student(id,name,Location) values (115,'Prem','Chennai'),(112,'Lisa','Salem'),(120,'Gopika','Coimbatore'),(108,'Rahul','Trichy'),(122,'Radha','Salem'),(95,'Krupa','Chennai'),(101,'Prathi','Coimbatore'),(102,'Naresh','Chennai'),(103,'Karpagam','Coimbatore');
select * from Student;
select * from Student where id=122;
