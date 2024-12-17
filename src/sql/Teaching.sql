use Teaching

-- 删除旧表
if OBJECT_ID('Elective', 'U') is not null
	drop table Elective

if OBJECT_ID('Students', 'U') is not null
	drop table Students

if OBJECT_ID('Classes', 'U') is not null
	drop table Classes

if OBJECT_ID('Subjects', 'U') is not null
	drop table Subjects


-- 创建表
-- 班级表
create table Classes(
	Classid char(6) not null,
	classname char(30) not null,
	primary key (Classid)
)

-- 学生表
create table Students(
	Studentid char(11) not null,
	classid char(6) not null,
	studentname char(30) not null,
	gender bit not null,
	graduated bit not null,
	primary key (Studentid),
	foreign key (classid) references Classes(classid)
)

-- 课程表
create table Subjects(
	subjectid char(6) not null,
	subjectname char(30) not null,
	primary key (subjectid)
)

--选课表
create table Elective(
	Studentid char(11) not null,
	subjectid char(6) not null,
	grade int not null,
	primary key (Studentid, subjectid),
	foreign key (Studentid) references Students(Studentid),
	foreign key (subjectid) references Subjects(subjectid)
)

-- 插入数据
insert into Classes values
('SA02', '软工A2'),
('SA03', '软工A1'),
('SA01', '软工A1')

insert into Students values
('20150101002', 'SA02','张晓燕',0,0),
('20150101001', 'SA01', '王小三', 1, 0),
('20150101004', 'SA01', '李四', 1, 0)

insert into Subjects values
('Sub001', '大学英语'),
('Sub002', '程序设计基础'),
('Sub003', '数据库系统概论'),
('Sub004', '高等数学'),
('Sub005', '数据结构')

insert into Elective values
('20150101001', 'Sub001', 88),
('20150101001', 'Sub002', 93),
('20150101002', 'Sub001', 78),
('20150101002', 'Sub002', 75),
('20150101002', 'Sub003', 85)

-- 打印所有数据
select * from Students
select * from Classes
select * from Subjects
select * from Elective