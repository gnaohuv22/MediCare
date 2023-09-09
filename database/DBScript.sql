/*

use master;
drop database hehe1;
create database hehe1;
use hehe1;

*/

create table [User] (
	username varchar(255) primary key,
	password varchar(255),
	firstName nvarchar(255),
    lastName nvarchar(255),
    birthDate date,
    gender int,
    address nvarchar(255),
    email nvarchar(255),
    phone varchar(10),
	profilePicture varchar(255),
    createdAt datetime
);

create table Admin (
	username varchar(255) primary key,
	password varchar(255)
);

create table [Certificate] (
	certId int primary key,
	name nvarchar(255)
);

create table AcademicRank (
	ARId int primary key,
	name nvarchar(255)
);

create table Employee (
	employeeId int primary key,
	username varchar(255) unique,
	password varchar(255),
	firstName nvarchar(255),
	lastName nvarchar(255),
	email varchar(255),
	phone varchar(10),
	certId int,
	ARId int,
	speciality nvarchar(255),
	salary float,
	profilePicture nvarchar(255)
	foreign key (certId) references Certificate(certId),
	foreign key (ARId) references AcademicRank(ARId)
);

create table [plan] (
	planId int primary key,
	name nvarchar(255),
	description nvarchar(750),
	price float
);

create table appointments (
	appointmentId int primary key,
	userId varchar(255),
	doctorId varchar(255),
	planId int,
	plannedAt time,
	accepted bit,
	completed bit,
	foreign key (planId) references [plan](planId),
	foreign key (userId) references [User](username),
	foreign key (doctorId) references Employee (username)
);

create table reviews (
	reviewId int primary key,
	userId varchar(255),
	doctorId varchar(255),
	rating float check(rating <= 5),
	reviewContent nvarchar(1500),
	createdAt datetime,
	foreign key (userId) references [User](username),
	foreign key (doctorId) references Employee(username)
);

create table billingHistory (
	billingId int primary key,
	appointmentId int, 
	totalCash float,
	userId varchar(255),
	doctorId varchar(255),
	createdAt time,
	foreign key (appointmentId) references appointments(appointmentId),
	foreign key (userId) references [User](username),
	foreign key (doctorId) references Employee(username)
)

create table salaryBaseOnCert (
	certId int primary key,
	name nvarchar(255),
	associateSalary float
	foreign key (certId) references Certificate(certId)
)

--salary base on kinh nghiem

create table cancelledRequest (
	cancelId int primary key,
	appointmentId int, 
	totalRefund float,
	userId varchar(255),
	doctorId varchar(255),
	cancelledAt time,
	foreign key (appointmentId) references appointments(appointmentId),
	foreign key (userId) references [User](username),
	foreign key (doctorId) references Employee(username)
)
