/*

USE master;
GO
ALTER DATABASE hehe1 SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
GO
DROP DATABASE hehe1;
GO
CREATE DATABASE hehe1;
GO
USE hehe1;
GO

*/

create table Province (
	id int primary key,
	name nvarchar(255)
);

create table Branch ( --chi nhanh benh vien
	id int primary key,
	name nvarchar(255),
	description text,
	locateId int,
	foreign key (locateId) references Province (id)

);

create table [User] (
	id varchar(255) primary key,
	email varchar(255) unique,
	password varchar(255),
	name nvarchar(255),
    birthDate date,
    gender int,
    address nvarchar(255),
	provinceId int,
	[identity] varchar(15),
	medicalId varchar(15),
	ethnic nvarchar(255),
    phone varchar(10),
	profilePicture varchar(255),
    createdAt datetime,
	foreign key (provinceId) references Province(id)
);

create table EmployeeRole (
	id int primary key,
	role nvarchar(255)
);

create table Employee (
	id int primary key,
	email varchar(255) unique,
	password varchar(255),
	branchId int,
	name nvarchar(255),
	birthDate date,
	gender int,
	address nvarchar(255),
	workplace nvarchar(255),
	provinceId int,
	phone varchar(10),
	ethnic nvarchar(255),
	roleId int,
	createAt datetime
	foreign key (roleId) references EmployeeRole(id),
	foreign key (branchId) references Branch(id),
	foreign key (provinceId) references Province(id)
);

create table [Certificate] (
	id int primary key,
	name nvarchar(255),
	wage float
);

create table AcademicRank (
	id int primary key,
	name nvarchar(255),
	wage float
);

create table CurriculumVitae (
	id int primary key,
	introduce text,
	education text,
	workHistory text,
	startYear int
);

create table NewsCategory (
	id int primary key,
	name nvarchar(255)
);

create table News (
	id int primary key,
	title nvarchar(1500),
	content text,
	author varchar(255),
	categoryId int,
	createdAt time,
	lastModified time
	foreign key (categoryId) references NewsCategory (id),
	foreign key (author) references Employee(email)
);

create table Doctor (
	id varchar(255) primary key,
	email varchar(255) unique,
	displayName nvarchar(255),
	password varchar(255),
	branchId int,
	phone varchar(10),
	ARId int,
	CVId int,
	salary float,
	workplace nvarchar(255),
	profilePicture nvarchar(255),
	status bit,
	foreign key (ARId) references AcademicRank(id),
	foreign key (CVId) references CurriculumVitae(id),
	foreign key (branchId) references Branch(id)
);

create table WorkingSlot ( --du kien 15 phut moi period
	id int primary key,
	startTime time,
	endTime time
);

create table DoctorWorkingSlot (
	slotId int,
	doctorId varchar(255),
	primary key (slotId, doctorId),
	foreign key (slotId) references WorkingSlot(id),
	foreign key (doctorId) references Doctor(id)
);

create table DoctorWorkingDay (
	dayOfWeek int,
	doctorId varchar(255),
	primary key (dayOfWeek, doctorId),
	foreign key (doctorId) references Doctor(id)
);

create table Department ( --day la khoa
	id int primary key,
	name nvarchar(255),
	description text
);

create table ServiceTag ( --day la chuyen khoa
	id int primary key,
	nametag nvarchar(255),
	description text,
	departmentId int,
	foreign key (departmentId) references Department(id)
);

create table DoctorService (
	doctorId varchar(255),
	serviceId int,
	price float
	primary key (doctorId, serviceId),
	foreign key (doctorId) references Doctor(id),
	foreign key (serviceId) references ServiceTag(id)
);

create table CertificateDoctor (
	certId int,
	doctorId varchar(255),
	primary key (certId, doctorId),
	foreign key (certId) references Certificate(id),
	foreign key (doctorId) references Doctor(id)
);

create table Relationship (
	id int primary key,
	relation nvarchar(255)
);

create table FamilyProfile ( 
	profileId int primary key,
	email varchar(255) unique,
	password varchar(255),
	name nvarchar(255),
    birthDate date,
    gender int,
    address nvarchar(255),
	provinceId int,
	[identity] varchar(15),
	medicalId varchar(15),
	ethnic nvarchar(255),
    phone varchar(10),
	profilePicture varchar(255),
    createdAt datetime,
	relationId int,
	foreign key (provinceId) references Province(id),
	foreign key (relationId) references Relationship(id)
);

create table Appointments (
	id int primary key,
	userId varchar(255),
	doctorId varchar(255),
	serviceId int,
	plannedAt time,
	status int, --0: requested, 1: accepted, 2: completed, 3: rejected/cancelled
	foreign key (serviceId) references ServiceTag(id),
	foreign key (userId) references [User](id),
	foreign key (doctorId) references Doctor (id)
);

create table Reviews (
	id int primary key,
	userId varchar(255),
	doctorId varchar(255),
	appointmentId int,
	rating float check(rating <= 5),
	reviewContent nvarchar(1500),
	createdAt datetime,
	foreign key (userId) references [User](id),
	foreign key (doctorId) references Doctor(id),
	foreign key (appointmentId) references Appointments(id)
);

create table BillingHistory (
	id int primary key,
	appointmentId int, 
	totalCash float,
	userId varchar(255),
	doctorId varchar(255),
	createdAt time,
	foreign key (appointmentId) references appointments(id),
	foreign key (userId) references [User](id),
	foreign key (doctorId) references Doctor(id)
)
/*
create table BonusSalary (
	
);
*/

create table CancelledRequest (
	id int primary key,
	appointmentId int, 
	totalRefund float,
	userId varchar(255),
	doctorId varchar(255),
	cancelledAt time,
	status int, --0: requested, 1: accepted, 2: completed, 3: rejected/cancelled
	foreign key (appointmentId) references appointments(id),
	foreign key (userId) references [User](id),
	foreign key (doctorId) references Doctor(id)
)


create table AccessLog (
	id int primary key,
	ipAddress varchar(15),
	accessTime timestamp,
	accessCount int
);
