/*

use master;
drop database hehe1;
create database hehe1;
use hehe1;

*/

create table [User] (
	id varchar(255) primary key,
	email varchar(255) unique,
	password varchar(255),
	name nvarchar(255),
    birthDate date,
    gender int,
    address nvarchar(255),
	[identity] varchar(15),
	medicalId varchar(15),
	ethnic nvarchar(255),
    phone varchar(10),
	profilePicture varchar(255),
    createdAt datetime
);

create table Role (
	id int primary key,
	role nvarchar(255)
);

create table Admin (
	email varchar(255) primary key,
	password varchar(255),
	roleId int,
	displayName nvarchar(255),
	foreign key (roleId) references [Role] (id)
);

create table [Certificate] (
	id int primary key,
	name nvarchar(255)
);

create table AcademicRank (
	id int primary key,
	name nvarchar(255)
);

create table CurriculumVitae (
	id int primary key,
	introduce text,
	education text,
	workHistory text,
	startYear int
);

create table Category (
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
	foreign key (categoryId) references Category (id),
	foreign key (author) references Admin(email)
);

create table Speciality (
	id int primary key,
	speciality varchar(255)
);

create table Specialist (
	id varchar(255) primary key,
	email varchar(255) unique,
	displayName nvarchar(255),
	specialityId int,
	phone varchar(10),
	certId int,
	ARId int,
	CVId int,
	salary float,
	workplace nvarchar(255),
	profilePicture nvarchar(255),
	duty nvarchar(255)
	foreign key (email) references [Admin](email),
	foreign key (certId) references [Certificate] (id),
	foreign key (ARId) references AcademicRank(id),
	foreign key (CVId) references CurriculumVitae(id),
	foreign key (specialityId) references Speciality(id)
);

create table [Plan] (
	planId int primary key,
	name nvarchar(255),
	description text,
	price float
);

create table PlanSpecialist (
	planId int,
	specialistId varchar(255),
	primary key (planId, specialistId),
	foreign key (planId) references [Plan](planId),
	foreign key (specialistId) references Specialist(id)
);

create table Appointments (
	appointmentId int primary key,
	userId varchar(255),
	specialistId varchar(255),
	planId int,
	plannedAt time,
	accepted bit,
	completed bit,
	foreign key (planId) references [plan](planId),
	foreign key (userId) references [User](id),
	foreign key (specialistId) references Specialist (id)
);

create table Reviews (
	reviewId int primary key,
	userId varchar(255),
	specialistId varchar(255),
	rating float check(rating <= 5),
	reviewContent nvarchar(1500),
	createdAt datetime,
	foreign key (userId) references [User](id),
	foreign key (specialistId) references Specialist(id)
);

create table billingHistory (
	billingId int primary key,
	appointmentId int, 
	totalCash float,
	userId varchar(255),
	specialistId varchar(255),
	createdAt time,
	foreign key (appointmentId) references appointments(appointmentId),
	foreign key (userId) references [User](id),
	foreign key (specialistId) references Specialist(id)
)

create table SalaryCert (
	certId int primary key,
	name nvarchar(255),
	associateSalary float
	foreign key (certId) references Certificate(id)
)

/*
create table BonusSalary (
	
);
*/

create table CancelledRequest (
	cancelId int primary key,
	appointmentId int, 
	totalRefund float,
	userId varchar(255),
	specialistId varchar(255),
	cancelledAt time,
	foreign key (appointmentId) references appointments(appointmentId),
	foreign key (userId) references [User](id),
	foreign key (specialistId) references Specialist(id)
)

create table SpecialistSchedule (
	scheduleId int primary key,
	specialistId varchar(255),
	busyDate date,
	description text,
	foreign key (specialistId) references Specialist(id)
)
