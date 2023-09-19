/*

USE master;
GO
ALTER DATABASE hehe1 SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
GO
DROP DATABASE hehe1;

*/

USE [master]
GO
/****** Object:  Database [hehe1]    Script Date: 9/20/2023 04:59:38 ******/
CREATE DATABASE [hehe1]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'hehe1', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\hehe1.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'hehe1_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\hehe1_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [hehe1] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [hehe1].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [hehe1] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [hehe1] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [hehe1] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [hehe1] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [hehe1] SET ARITHABORT OFF 
GO
ALTER DATABASE [hehe1] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [hehe1] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [hehe1] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [hehe1] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [hehe1] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [hehe1] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [hehe1] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [hehe1] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [hehe1] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [hehe1] SET  ENABLE_BROKER 
GO
ALTER DATABASE [hehe1] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [hehe1] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [hehe1] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [hehe1] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [hehe1] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [hehe1] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [hehe1] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [hehe1] SET RECOVERY FULL 
GO
ALTER DATABASE [hehe1] SET  MULTI_USER 
GO
ALTER DATABASE [hehe1] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [hehe1] SET DB_CHAINING OFF 
GO
ALTER DATABASE [hehe1] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [hehe1] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [hehe1] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [hehe1] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'hehe1', N'ON'
GO
ALTER DATABASE [hehe1] SET QUERY_STORE = OFF
GO
USE [hehe1]
GO
/****** Object:  Table [dbo].[AcademicRank]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AcademicRank](
	[id] [int] NOT NULL,
	[name] [nvarchar](255) NULL,
	[wage] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AccessLog]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccessLog](
	[id] [int] NOT NULL,
	[ipAddress] [varchar](15) NULL,
	[accessTime] [timestamp] NOT NULL,
	[accessCount] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Appointments]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Appointments](
	[id] [int] NOT NULL,
	[userId] [varchar](255) NULL,
	[doctorId] [varchar](255) NULL,
	[serviceId] [int] NULL,
	[plannedAt] [time](7) NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BillingHistory]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BillingHistory](
	[id] [int] NOT NULL,
	[appointmentId] [int] NULL,
	[totalCash] [float] NULL,
	[userId] [varchar](255) NULL,
	[doctorId] [varchar](255) NULL,
	[createdAt] [time](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Branch]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Branch](
	[id] [int] NOT NULL,
	[name] [nvarchar](255) NULL,
	[description] [nvarchar](max) NULL,
	[locateId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CancelledRequest]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CancelledRequest](
	[id] [int] NOT NULL,
	[appointmentId] [int] NULL,
	[totalRefund] [float] NULL,
	[userId] [varchar](255) NULL,
	[doctorId] [varchar](255) NULL,
	[cancelledAt] [time](7) NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Certificate]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Certificate](
	[id] [int] NOT NULL,
	[name] [nvarchar](255) NULL,
	[wage] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CertificateDoctor]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CertificateDoctor](
	[certId] [int] NOT NULL,
	[doctorId] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[certId] ASC,
	[doctorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CurriculumVitae]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CurriculumVitae](
	[id] [int] NOT NULL,
	[introduce] [nvarchar](max) NULL,
	[education] [nvarchar](max) NULL,
	[workHistory] [nvarchar](max) NULL,
	[startYear] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Department]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Department](
	[id] [int] NOT NULL,
	[name] [nvarchar](255) NULL,
	[description] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Doctor]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Doctor](
	[id] [varchar](255) NOT NULL,
	[email] [varchar](255) NULL,
	[displayName] [nvarchar](255) NULL,
	[branchId] [int] NULL,
	[phone] [varchar](10) NULL,
	[ARId] [int] NULL,
	[CVId] [int] NULL,
	[salary] [float] NULL,
	[workplace] [nvarchar](255) NULL,
	[profilePicture] [nvarchar](255) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoctorCertificates]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DoctorCertificates](
	[DoctorId] [int] NOT NULL,
	[Certificates] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[DoctorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoctorService]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DoctorService](
	[doctorId] [varchar](255) NOT NULL,
	[serviceId] [int] NOT NULL,
	[price] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[doctorId] ASC,
	[serviceId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoctorServices]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DoctorServices](
	[DoctorId] [int] NOT NULL,
	[Services] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[DoctorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoctorWorkingDay]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DoctorWorkingDay](
	[dayOfWeek] [int] NOT NULL,
	[doctorId] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[dayOfWeek] ASC,
	[doctorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoctorWorkingSlot]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DoctorWorkingSlot](
	[slotId] [int] NOT NULL,
	[doctorId] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[slotId] ASC,
	[doctorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[id] [int] NOT NULL,
	[email] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[branchId] [int] NULL,
	[name] [nvarchar](255) NULL,
	[birthDate] [date] NULL,
	[gender] [int] NULL,
	[address] [nvarchar](255) NULL,
	[workplace] [nvarchar](255) NULL,
	[provinceId] [int] NULL,
	[phone] [varchar](10) NULL,
	[ethnic] [nvarchar](255) NULL,
	[roleId] [int] NULL,
	[createAt] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EmployeeRole]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EmployeeRole](
	[id] [int] NOT NULL,
	[role] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FamilyProfile]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FamilyProfile](
	[profileId] [int] NOT NULL,
	[email] [varchar](255) NULL,
	[name] [nvarchar](255) NULL,
	[birthDate] [date] NULL,
	[gender] [int] NULL,
	[address] [nvarchar](255) NULL,
	[provinceId] [int] NULL,
	[identity] [varchar](15) NULL,
	[medicalId] [varchar](15) NULL,
	[ethnic] [nvarchar](255) NULL,
	[phone] [varchar](10) NULL,
	[profilePicture] [varchar](255) NULL,
	[createdAt] [datetime] NULL,
	[relationId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[profileId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[News]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[News](
	[id] [int] NOT NULL,
	[title] [nvarchar](1500) NULL,
	[content] [nvarchar](MAX) NULL,
	[author] [varchar](255) NULL,
	[categoryId] [int] NULL,
	[createdAt] [time](7) NULL,
	[lastModified] [time](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NewsCategory]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NewsCategory](
	[id] [int] NOT NULL,
	[name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Province]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Province](
	[id] [int] NOT NULL,
	[name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Relationship]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Relationship](
	[id] [int] NOT NULL,
	[relation] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Reviews]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Reviews](
	[id] [int] NOT NULL,
	[userId] [varchar](255) NULL,
	[doctorId] [varchar](255) NULL,
	[appointmentId] [int] NULL,
	[rating] [float] NULL,
	[reviewContent] [nvarchar](1500) NULL,
	[createdAt] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ServiceTag]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ServiceTag](
	[id] [int] NOT NULL,
	[nametag] [nvarchar](255) NULL,
	[description] [nvarchar](max) NULL,
	[departmentId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[id] [varchar](255) NOT NULL,
	[email] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[name] [nvarchar](255) NULL,
	[birthDate] [date] NULL,
	[gender] [int] NULL,
	[address] [nvarchar](255) NULL,
	[provinceId] [int] NULL,
	[identity] [varchar](15) NULL,
	[medicalId] [varchar](15) NULL,
	[ethnic] [nvarchar](255) NULL,
	[phone] [varchar](10) NULL,
	[profilePicture] [varchar](255) NULL,
	[createdAt] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[WorkingSlot]    Script Date: 9/20/2023 04:59:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WorkingSlot](
	[id] [int] NOT NULL,
	[startTime] [time](7) NULL,
	[endTime] [time](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[AcademicRank] ([id], [name], [wage]) VALUES (1, N'Giáo sư', NULL)
INSERT [dbo].[AcademicRank] ([id], [name], [wage]) VALUES (2, N'Phó giáo sư', NULL)
GO
INSERT [dbo].[Branch] ([id], [name], [description], [locateId]) VALUES (1, N'Vinmec Hà Nội', NULL, 1)
INSERT [dbo].[Branch] ([id], [name], [description], [locateId]) VALUES (2, N'Vinmec TP Hồ Chí Minh', NULL, 2)
INSERT [dbo].[Branch] ([id], [name], [description], [locateId]) VALUES (3, N'Vinmec Nha Trang', NULL, 3)
GO
INSERT [dbo].[Certificate] ([id], [name], [wage]) VALUES (1, N'Bác sĩ ', NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage]) VALUES (2, N'Bác sĩ chuyên khoa II', NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage]) VALUES (3, N'Thạc sĩ', NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage]) VALUES (4, N'Bác sĩ cao cấp ', NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage]) VALUES (5, N'Bác sĩ nội trú', NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage]) VALUES (6, N'Tiến sĩ', NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage]) VALUES (7, N'Bác sĩ chuyên khoa I', NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage]) VALUES (8, N'Cử nhân', NULL)
GO
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (1, N'4')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (1, N'9')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (2, N'10')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (3, N'8')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (3, N'9')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (5, N'8')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (6, N'4')
GO
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear]) VALUES (1, N'Giáo sư Hussein Kassem được đào tạo chuyên sâu về can thiệp tim mạch nâng cao tại các Trung tâm Tim mạch hàng đầu thế giới như Hệ thống Y khoa Cleveland Clinic và Bệnh viện Henry Ford Michigan Hoa Kỳ. Với sự nghiệp 25 năm trong lĩnh vực Can thiệp tim mạch & Nội tim mạch của mình, Giáo sư Hussein Kassem đã thực hiện thành công hơn 4000 ca can thiệp động mạch vành phức tạp, 1000 ca can thiệp bệnh động mạch ngoại biên và hàng trăm ca can thiệp tim cấu trúc và van hai lá. Với những thành tựu chuyên môn ưu tú, ông không chỉ giữ vị trí Giám đốc Trung tâm Tim mạch tại các bệnh viện uy tín tại Ai Cập và UAE, mà còn là thành viên tích cực của Hiệp hội Tim mạch can thiệp Hoa kỳ (SCAI), Trường môn tim mạch Hoa kỳ (ACC) và Hiệp hội Tim mạch Châu Âu (ESC). Ông đã cống hiến hơn 80 bài báo, sách, tạp chí về lĩnh vực Can thiệp – Nội tim mạch cho nền y học thế giới.', NULL, N'2007 - 2012: Giảng viên khoa Tim mạch, Đại học Cairo,', 2007)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear]) VALUES (2, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2015)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear]) VALUES (3, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2014)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear]) VALUES (4, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2016)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear]) VALUES (5, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2012)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear]) VALUES (6, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2019)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear]) VALUES (7, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2014)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear]) VALUES (8, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2011)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear]) VALUES (9, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2015)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear]) VALUES (10, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2015)
GO
INSERT [dbo].[Department] ([id], [name], [description]) VALUES (1, N'Nội', N'Khoa chuyên về chẩn đoán, điều trị và quản lý các bệnh lý nội tiết và lâm sàng trong cơ thể. Đây là nơi tập trung điều trị các bệnh ảnh hưởng đến các cơ quan và hệ thống nội tiết của cơ thể.')
INSERT [dbo].[Department] ([id], [name], [description]) VALUES (2, N'Ngoại', N'Khoa chuyên về phẫu thuật và điều trị các vấn đề y tế cần can thiệp từ bên ngoài cơ thể, bao gồm cả phẫu thuật cấp cứu và các ca phẫu thuật lập kế hoạch như phẫu thuật chỉnh hình.')
INSERT [dbo].[Department] ([id], [name], [description]) VALUES (3, N'Nhi', N'Khoa tập trung vào sức khỏe của trẻ em và thiếu niên, bao gồm cả chăm sóc cho trẻ từ lúc mới sinh đến tuổi vị thành niên. Điều này bao gồm kiểm tra sức khỏe định kỳ, điều trị các bệnh lý trẻ em, và tư vấn về chăm sóc sức khỏe cho gia đình.')
INSERT [dbo].[Department] ([id], [name], [description]) VALUES (4, N'Sản', N'Khoa quản lý và chăm sóc cho sức khỏe của phụ nữ trong quá trình mang thai, sanh, và sau sinh. Các chuyên gia trong khoa này cung cấp chăm sóc cho bà bầu và bé mới sinh, và điều trị các vấn đề phụ khoa của phụ nữ.')
GO
INSERT [dbo].[Doctor] ([id], [email], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'1', N'doctor@gmail.com', N'Nguyễn Thanh Liêm', 1, N'0123456789', 1, 1, 3000, N'Hà Nội', NULL, 0)
INSERT [dbo].[Doctor] ([id], [email], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'10', N'doctor9@gmail.com', N'Lê Thị My', 1, N'0123456789', NULL, 5, NULL, N'Hà Nội', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'2', N'doctor1@gmail.com', N'Phạm Nhật An', 1, N'0123456789', 1, 2, NULL, N'Hà Nội', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'3', N'doctor2@gmail.com', N'Phan Quỳnh Lan', 1, N'0123456789', NULL, 8, NULL, N'Hà Nội', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'4', N'doctor3@gmail.com', N'Võ Thành Nhân', 2, N'0123456789', 1, 3, NULL, N'TP Hồ Chí Minh', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'5', N'doctor4@gmail.com', N'Nguyễn Thanh Hưng', 3, N'0123456789', NULL, 4, NULL, N'Nha Trang', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'6', N'doctor5@gmail.com', N'Thái Bằng', 3, N'0123456789', NULL, 7, NULL, N'Nha Trang', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'7', N'doctor6@gmail.com', N'Nguyễn Thị Tuyết Minh', 1, N'0123456789', NULL, 6, NULL, N'Hà Nội', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'8', N'doctor7@gmail.com', N'Đặng Thị Linh', 1, N'0123456789', NULL, 9, NULL, N'Hà Nội', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'9', N'doctor8@gmail.com', N'Phạm Vũ Hiệp', 1, N'0123456789', NULL, 10, NULL, N'Hà Nội', NULL, NULL)
GO
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates]) VALUES (1, NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates]) VALUES (2, NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates]) VALUES (3, NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates]) VALUES (4, N'[{"name":"Bác sĩ "},{"name":"Tiến sĩ"}]')
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates]) VALUES (5, NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates]) VALUES (6, NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates]) VALUES (7, NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates]) VALUES (8, N'[{"name":"Bác sĩ nội trú"},{"name":"Thạc sĩ"}]')
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates]) VALUES (9, N'[{"name":"Bác sĩ "},{"name":"Thạc sĩ"}]')
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates]) VALUES (10, N'[{"name":"Bác sĩ chuyên khoa II"}]')
GO
INSERT [dbo].[DoctorService] ([doctorId], [serviceId], [price]) VALUES (N'10', 12, NULL)
INSERT [dbo].[DoctorService] ([doctorId], [serviceId], [price]) VALUES (N'4', 2, NULL)
INSERT [dbo].[DoctorService] ([doctorId], [serviceId], [price]) VALUES (N'7', 2, NULL)
INSERT [dbo].[DoctorService] ([doctorId], [serviceId], [price]) VALUES (N'8', 2, NULL)
INSERT [dbo].[DoctorService] ([doctorId], [serviceId], [price]) VALUES (N'9', 5, NULL)
INSERT [dbo].[DoctorService] ([doctorId], [serviceId], [price]) VALUES (N'9', 12, NULL)
GO
INSERT [dbo].[DoctorServices] ([DoctorId], [Services]) VALUES (1, NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services]) VALUES (2, NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services]) VALUES (3, NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services]) VALUES (4, N'[{"name":"Tim mạch"}]')
INSERT [dbo].[DoctorServices] ([DoctorId], [Services]) VALUES (5, NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services]) VALUES (6, NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services]) VALUES (7, N'[{"name":"Tim mạch"}]')
INSERT [dbo].[DoctorServices] ([DoctorId], [Services]) VALUES (8, N'[{"name":"Tim mạch"}]')
INSERT [dbo].[DoctorServices] ([DoctorId], [Services]) VALUES (9, N'[{"name":"Bệnh lý tuyến giáp"},{"name":"Nội tiết"}]')
INSERT [dbo].[DoctorServices] ([DoctorId], [Services]) VALUES (10, N'[{"name":"Bệnh lý tuyến giáp"}]')
GO
INSERT [dbo].[Province] ([id], [name]) VALUES (1, N'Hà Nội')
INSERT [dbo].[Province] ([id], [name]) VALUES (2, N'TP Hồ Chí Minh')
INSERT [dbo].[Province] ([id], [name]) VALUES (3, N'Nha Trang')
GO
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (1, N'Nội tổng quát', NULL, 1)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (2, N'Tim mạch', N'', 1)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (3, N'Tiêu hóa', NULL, 1)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (4, N'Truyền nhiễm', NULL, 1)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (5, N'Nội tiết', NULL, 1)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (6, N'Hen - Dị ứng miễn dịch', NULL, 1)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (7, N'Thần kinh', NULL, 1)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (8, N'Hô hấp', NULL, 1)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (9, N'Tiết niệu', NULL, 1)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (10, N'Đa khoa', NULL, 1)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (11, N'Dinh dưỡng', NULL, 1)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (12, N'Bệnh lý tuyến giáp', NULL, 1)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (13, N'Ngoại chấn thương chỉnh hình', NULL, 2)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (14, N'Phẫu thuật chỉnh hình', NULL, 2)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (15, N'Ngoại lồng ngực', NULL, 2)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (16, N'Nhi', NULL, 3)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (17, N'Sơ sinh', NULL, 3)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId]) VALUES (18, N'Sản phụ khoa', NULL, 4)
GO
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'1', N'nguyenthitubinh133@gmail.com', N'7150a81293c5344e082eb96f59145503fe7f31593a60f1bc333c9bb4b19a3d975de9de8ae56a6f86cdd76d1dcbd0644c', N'Nguyễn Tú Bình', CAST(N'2003-04-14' AS Date), 1, N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, N'Kinh', N'0358219546', NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'2', N'nguyenvietthu03@gmail.com', N'7150a81293c5344e082eb96f59145503fe7f31593a60f1bc333c9bb4b19a3d975de9de8ae56a6f86cdd76d1dcbd0644c', N'Nguyễn Việt Thu', CAST(N'2003-05-05' AS Date), 1, N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, N'Kinh', N'0358219546', NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'3', N'user', N'7150a81293c5344e082eb96f59145503fe7f31593a60f1bc333c9bb4b19a3d975de9de8ae56a6f86cdd76d1dcbd0644c', N'Nguyễn Tú Bình', CAST(N'2003-05-06' AS Date), 0, N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, NULL, N'0123456789', NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'4', N'gnaohuv55', N'19772918d6eb06a2c8ee321cbfd0bd4636aaf86111d5a7da5f1e5161ec433006768900227b40aa933dbc6673cefd6730', N'hehe', CAST(N'2003-05-05' AS Date), 0, N'hehe', 1, N'0', NULL, NULL, N'0123456789', N'hehe', NULL)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Doctor__AB6E6164639C3730]    Script Date: 9/20/2023 04:59:38 ******/
ALTER TABLE [dbo].[Doctor] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Employee__AB6E6164F0F0D2F8]    Script Date: 9/20/2023 04:59:38 ******/
ALTER TABLE [dbo].[Employee] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__FamilyPr__AB6E6164D19DDA1F]    Script Date: 9/20/2023 04:59:38 ******/
ALTER TABLE [dbo].[FamilyProfile] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__User__AB6E61640D1D7384]    Script Date: 9/20/2023 04:59:38 ******/
ALTER TABLE [dbo].[User] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Appointments]  WITH CHECK ADD FOREIGN KEY([doctorId])
REFERENCES [dbo].[Doctor] ([id])
GO
ALTER TABLE [dbo].[Appointments]  WITH CHECK ADD FOREIGN KEY([serviceId])
REFERENCES [dbo].[ServiceTag] ([id])
GO
ALTER TABLE [dbo].[Appointments]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[BillingHistory]  WITH CHECK ADD FOREIGN KEY([appointmentId])
REFERENCES [dbo].[Appointments] ([id])
GO
ALTER TABLE [dbo].[BillingHistory]  WITH CHECK ADD FOREIGN KEY([doctorId])
REFERENCES [dbo].[Doctor] ([id])
GO
ALTER TABLE [dbo].[BillingHistory]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[Branch]  WITH CHECK ADD FOREIGN KEY([locateId])
REFERENCES [dbo].[Province] ([id])
GO
ALTER TABLE [dbo].[CancelledRequest]  WITH CHECK ADD FOREIGN KEY([appointmentId])
REFERENCES [dbo].[Appointments] ([id])
GO
ALTER TABLE [dbo].[CancelledRequest]  WITH CHECK ADD FOREIGN KEY([doctorId])
REFERENCES [dbo].[Doctor] ([id])
GO
ALTER TABLE [dbo].[CancelledRequest]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[CertificateDoctor]  WITH CHECK ADD FOREIGN KEY([certId])
REFERENCES [dbo].[Certificate] ([id])
GO
ALTER TABLE [dbo].[CertificateDoctor]  WITH CHECK ADD FOREIGN KEY([doctorId])
REFERENCES [dbo].[Doctor] ([id])
GO
ALTER TABLE [dbo].[Doctor]  WITH CHECK ADD FOREIGN KEY([ARId])
REFERENCES [dbo].[AcademicRank] ([id])
GO
ALTER TABLE [dbo].[Doctor]  WITH CHECK ADD FOREIGN KEY([branchId])
REFERENCES [dbo].[Branch] ([id])
GO
ALTER TABLE [dbo].[Doctor]  WITH CHECK ADD FOREIGN KEY([CVId])
REFERENCES [dbo].[CurriculumVitae] ([id])
GO
ALTER TABLE [dbo].[DoctorService]  WITH CHECK ADD FOREIGN KEY([doctorId])
REFERENCES [dbo].[Doctor] ([id])
GO
ALTER TABLE [dbo].[DoctorService]  WITH CHECK ADD FOREIGN KEY([serviceId])
REFERENCES [dbo].[ServiceTag] ([id])
GO
ALTER TABLE [dbo].[DoctorWorkingDay]  WITH CHECK ADD FOREIGN KEY([doctorId])
REFERENCES [dbo].[Doctor] ([id])
GO
ALTER TABLE [dbo].[DoctorWorkingSlot]  WITH CHECK ADD FOREIGN KEY([doctorId])
REFERENCES [dbo].[Doctor] ([id])
GO
ALTER TABLE [dbo].[DoctorWorkingSlot]  WITH CHECK ADD FOREIGN KEY([slotId])
REFERENCES [dbo].[WorkingSlot] ([id])
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD FOREIGN KEY([branchId])
REFERENCES [dbo].[Branch] ([id])
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD FOREIGN KEY([provinceId])
REFERENCES [dbo].[Province] ([id])
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD FOREIGN KEY([roleId])
REFERENCES [dbo].[EmployeeRole] ([id])
GO
ALTER TABLE [dbo].[FamilyProfile]  WITH CHECK ADD FOREIGN KEY([provinceId])
REFERENCES [dbo].[Province] ([id])
GO
ALTER TABLE [dbo].[FamilyProfile]  WITH CHECK ADD FOREIGN KEY([relationId])
REFERENCES [dbo].[Relationship] ([id])
GO
ALTER TABLE [dbo].[News]  WITH CHECK ADD FOREIGN KEY([author])
REFERENCES [dbo].[Employee] ([email])
GO
ALTER TABLE [dbo].[News]  WITH CHECK ADD FOREIGN KEY([categoryId])
REFERENCES [dbo].[NewsCategory] ([id])
GO
ALTER TABLE [dbo].[Reviews]  WITH CHECK ADD FOREIGN KEY([appointmentId])
REFERENCES [dbo].[Appointments] ([id])
GO
ALTER TABLE [dbo].[Reviews]  WITH CHECK ADD FOREIGN KEY([doctorId])
REFERENCES [dbo].[Doctor] ([id])
GO
ALTER TABLE [dbo].[Reviews]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[ServiceTag]  WITH CHECK ADD FOREIGN KEY([departmentId])
REFERENCES [dbo].[Department] ([id])
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD FOREIGN KEY([provinceId])
REFERENCES [dbo].[Province] ([id])
GO
ALTER TABLE [dbo].[Reviews]  WITH CHECK ADD CHECK  (([rating]<=(5)))
GO
USE [master]
GO
ALTER DATABASE [hehe1] SET  READ_WRITE 
GO
