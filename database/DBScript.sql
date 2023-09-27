USE [master]
GO
/****** Object:  Database [hehe1]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[AcademicRank]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[AccessLog]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[Appointments]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[Awards]    Script Date: 9/28/2023 00:00:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Awards](
	[id] [varchar](255) NOT NULL,
	[doctorId] [varchar](255) NULL,
	[description] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Banner]    Script Date: 9/28/2023 00:00:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Banner](
	[id] [int] NOT NULL,
	[title] [nvarchar](max) NULL,
	[description] [nvarchar](max) NULL,
	[image] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BannerDetails]    Script Date: 9/28/2023 00:00:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BannerDetails](
	[id] [int] NOT NULL,
	[number] [nvarchar](10) NULL,
	[information] [nvarchar](max) NULL,
	[bannerId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BillingHistory]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[Branch]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[CancelledRequest]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[Certificate]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[CertificateDoctor]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[CurriculumVitae]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[Department]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[Doctor]    Script Date: 9/28/2023 00:00:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Doctor](
	[id] [varchar](255) NOT NULL,
	[email] [varchar](255) NULL,
	[password] [nvarchar](255) NOT NULL,
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
/****** Object:  Table [dbo].[DoctorCertificates]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[DoctorService]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[DoctorServices]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[DoctorWorkingDay]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[DoctorWorkingSlot]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[Education]    Script Date: 9/28/2023 00:00:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Education](
	[id] [varchar](255) NOT NULL,
	[doctorId] [varchar](255) NULL,
	[description] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[EmployeeRole]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[Experience]    Script Date: 9/28/2023 00:00:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Experience](
	[id] [varchar](255) NOT NULL,
	[doctorId] [varchar](255) NULL,
	[description] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FamilyProfile]    Script Date: 9/28/2023 00:00:43 ******/
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
	[ownerId] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[profileId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NavigationItem]    Script Date: 9/28/2023 00:00:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NavigationItem](
	[id] [int] NOT NULL,
	[name] [nvarchar](255) NULL,
	[link] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[News]    Script Date: 9/28/2023 00:00:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[News](
	[id] [int] NOT NULL,
	[title] [nvarchar](max) NULL,
	[content] [nvarchar](max) NULL,
	[author] [varchar](255) NULL,
	[categoryId] [int] NULL,
	[createdAt] [datetime] NULL,
	[lastModified] [datetime] NULL,
	[viewCount] [int] NULL,
	[coverImage] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NewsCategory]    Script Date: 9/28/2023 00:00:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NewsCategory](
	[id] [int] NOT NULL,
	[name] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Province]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[Relationship]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[ResearchPapers]    Script Date: 9/28/2023 00:00:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ResearchPapers](
	[id] [varchar](255) NOT NULL,
	[doctorId] [varchar](255) NULL,
	[description] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Reviews]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[ServiceTag]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[User]    Script Date: 9/28/2023 00:00:43 ******/
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
/****** Object:  Table [dbo].[WorkingSlot]    Script Date: 9/28/2023 00:00:43 ******/
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
INSERT [dbo].[Appointments] ([id], [userId], [doctorId], [serviceId], [plannedAt], [status]) VALUES (1, N'1', N'1', 1, NULL, 2)
INSERT [dbo].[Appointments] ([id], [userId], [doctorId], [serviceId], [plannedAt], [status]) VALUES (2, N'1', N'1', 2, NULL, 2)
INSERT [dbo].[Appointments] ([id], [userId], [doctorId], [serviceId], [plannedAt], [status]) VALUES (3, N'3', N'2', 1, NULL, 2)
INSERT [dbo].[Appointments] ([id], [userId], [doctorId], [serviceId], [plannedAt], [status]) VALUES (4, N'4', N'1', 3, NULL, 1)
GO
INSERT [dbo].[Awards] ([id], [doctorId], [description]) VALUES (N'1', N'1', N'Travel award, ICBMT 2018')
INSERT [dbo].[Awards] ([id], [doctorId], [description]) VALUES (N'10', N'7', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"')
INSERT [dbo].[Awards] ([id], [doctorId], [description]) VALUES (N'11', N'8', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"')
INSERT [dbo].[Awards] ([id], [doctorId], [description]) VALUES (N'12', N'9', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"')
INSERT [dbo].[Awards] ([id], [doctorId], [description]) VALUES (N'13', N'10', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"')
INSERT [dbo].[Awards] ([id], [doctorId], [description]) VALUES (N'2', N'1', N'Travel award, ASBMT 2019')
INSERT [dbo].[Awards] ([id], [doctorId], [description]) VALUES (N'3', N'2', N'Travel award, ASBMT 2019')
INSERT [dbo].[Awards] ([id], [doctorId], [description]) VALUES (N'4', N'2', N'Travel award, ICBMT 2018')
INSERT [dbo].[Awards] ([id], [doctorId], [description]) VALUES (N'5', N'2', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"')
INSERT [dbo].[Awards] ([id], [doctorId], [description]) VALUES (N'6', N'3', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"')
INSERT [dbo].[Awards] ([id], [doctorId], [description]) VALUES (N'7', N'4', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"')
INSERT [dbo].[Awards] ([id], [doctorId], [description]) VALUES (N'8', N'5', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"')
INSERT [dbo].[Awards] ([id], [doctorId], [description]) VALUES (N'9', N'6', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"')
GO
INSERT [dbo].[Banner] ([id], [title], [description], [image]) VALUES (1, N'MediCare', N'là hệ thống bệnh viện lớn do MediGroup đầu tư phát triển nhằm đem lại chất lượng cuộc sống tốt hơn cho người dân ở khắp mọi miền tổ quốc', N'assets/client/images/knowledge-bg.png')
GO
INSERT [dbo].[BannerDetails] ([id], [number], [information], [bannerId]) VALUES (1, N'~10', N'năm kinh nghiệm', 1)
INSERT [dbo].[BannerDetails] ([id], [number], [information], [bannerId]) VALUES (2, N'02', N'bệnh viện được công nhận đạt chuẩn y tế toàn cầu JCI', 1)
INSERT [dbo].[BannerDetails] ([id], [number], [information], [bannerId]) VALUES (3, N'10', N'bệnh viện trên hệ thống ', 1)
INSERT [dbo].[BannerDetails] ([id], [number], [information], [bannerId]) VALUES (4, N'400+', N'bác sĩ, chuyên gia trong & ngoài nước', 1)
INSERT [dbo].[BannerDetails] ([id], [number], [information], [bannerId]) VALUES (5, N'100%', N'tỉ lệ bệnh nhân sống sót sau khi khám', 1)
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
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'1', N'doctor@gmail.com', N'123456', N'Nguyễn Thanh Liêm', 1, N'0123456789', 1, 1, 3000, N'Hà Nội', NULL, 0)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'10', N'doctor9@gmail.com', N'123456', N'Lê Thị My', 1, N'0123456789', NULL, 5, NULL, N'Hà Nội', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'2', N'doctor1@gmail.com', N'123456', N'Phạm Nhật An', 1, N'0123456789', 1, 2, NULL, N'Hà Nội', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'3', N'doctor2@gmail.com', N'123456', N'Phan Quỳnh Lan', 1, N'0123456789', NULL, 8, NULL, N'Hà Nội', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'4', N'doctor3@gmail.com', N'123456', N'Võ Thành Nhân', 2, N'0123456789', 1, 3, NULL, N'TP Hồ Chí Minh', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'5', N'doctor4@gmail.com', N'123456', N'Nguyễn Thanh Hưng', 3, N'0123456789', NULL, 4, NULL, N'Nha Trang', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'6', N'doctor5@gmail.com', N'123456', N'Thái Bằng', 3, N'0123456789', NULL, 7, NULL, N'Nha Trang', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'7', N'doctor6@gmail.com', N'123456', N'Nguyễn Thị Tuyết Minh', 1, N'0123456789', NULL, 6, NULL, N'Hà Nội', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'8', N'doctor7@gmail.com', N'123456', N'Đặng Thị Linh', 1, N'0123456789', NULL, 9, NULL, N'Hà Nội', NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status]) VALUES (N'9', N'doctor8@gmail.com', N'123456', N'Phạm Vũ Hiệp', 1, N'0123456789', NULL, 10, NULL, N'Hà Nội', NULL, NULL)
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
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'1', N'1', N'1981: Tốt nghiệp Đại học Y khoa Hà Nội')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'10', N'2', N'011: Tham gia khóa đào tạo về Kỹ thuật chẩn đoán trước sinh tại Đài Loan')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'11', N'2', N'2006: Đào tạo Chẩn đoán trước sinh tại Singapore')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'12', N'2', N'2006: Đào tạo Chẩn đoán trước sinh tại Singapore')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'13', N'3', N'2006: Đào tạo Chẩn đoán trước sinh tại Singapore')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'14', N'3', N'Đào tạo Phụ khoa vị thành niên, Nội tiết phụ khoa tại Úc')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'15', N'3', N'Đào tạo Chẩn đoán trước sinh và Y học bào thai tại Nhật')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'16', N'4', N'Đào tạo Chẩn đoán trước sinh và Y học bào thai tại Nhật')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'17', N'4', N'2008: Tham gia khóa đào tạo hỗ trợ sinh sản tại Thái Lan')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'18', N'4 ', N'993 - 1994: Chuyên khoa sâu sau đại học, Đại học Y Dược Franche-Compte, Pháp. Chứng chỉ AFSA (tương đương Thạc sĩ)')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'19', N'5', N'1981: Tốt nghiệp Đại học Y khoa Hà Nội')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'2', N'1', N'1981 - 1983: Bác sĩ nội trú bệnh viện Phụ sản Trung Ương')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'20', N'5', N'1981 - 1983: Bác sĩ nội trú bệnh viện Phụ sản Trung Ương

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'21', N'5', N'1993 - 1994: Chuyên khoa sâu sau đại học, Đại học Y Dược Franche-Compte, Pháp. Chứng chỉ AFSA (tương đương Thạc sĩ)')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'22', N'5', N'1999: Đào tạo chuyên ngành Phụ Sản tại Úc')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'23', N'5', N'Đào tạo Chẩn đoán trước sinh và Y học bào thai tại Nhật')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'24', N'5', N'Đào tạo Thai nghén có nguy cơ cao, phẫu thuật nội soi, siêu âm sản khoa, phẫu thuật sàn chậu tại Mỹ

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'25', N'5', N'2006: Đào tạo Chẩn đoán trước sinh tại Singapore

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'26', N'6', N'2006: Đào tạo Chẩn đoán trước sinh tại Singapore

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'27', N'6', N'2007: BS Chuyên khoa cấp 2, chuyên ngành Sản Phụ, Đại học Y khoa Hà Nội

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'28', N'6', N'2008: Tham gia khóa đào tạo hỗ trợ sinh sản tại Thái Lan

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'29', N'6', N'2009: Đào tạo Sàng lọc và chẩn đoán trước sinh tại Macao

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'3', N'1', N'1993 - 1994: Chuyên khoa sâu sau đại học, Đại học Y Dược Franche-Compte, Pháp. Chứng chỉ AFSA (tương đương Thạc sĩ)')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'30', N'7', N'2010: Nhận bằng Bác sĩ Cao cấp

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'31', N'7', N'2011: Tham gia khóa đào tạo về Kỹ thuật chẩn đoán trước sinh tại Đài Loan

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'32', N'7', N'Từ 2012 cho tới nay: tham gia nhiều khóa học

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'33', N'7', N'2007: BS Chuyên khoa cấp 2, chuyên ngành Sản Phụ, Đại học Y khoa Hà Nội

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'34', N'8', N'1981: Tốt nghiệp Đại học Y khoa Hà Nội')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'35', N'8', N'1981 - 1983: Bác sĩ nội trú bệnh viện Phụ sản Trung Ương

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'36', N'8', N'1993 - 1994: Chuyên khoa sâu sau đại học, Đại học Y Dược Franche-Compte, Pháp. Chứng chỉ AFSA (tương đương Thạc sĩ)

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'37', N'8', N'1999: Đào tạo chuyên ngành Phụ Sản tại Úc

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'38', N'9', N'Đào tạo Chẩn đoán trước sinh và Y học bào thai tại Nhật

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'39', N'9', N'Đào tạo Thai nghén có nguy cơ cao, phẫu thuật nội soi, siêu âm sản khoa, phẫu thuật sàn chậu tại Mỹ

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'4', N'1', N'1999: Đào tạo chuyên ngành Phụ Sản tại Úc')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'40', N'9', N'Đào tạo Phụ khoa vị thành niên, Nội tiết phụ khoa tại Úc

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'41', N'9', N'2006: Đào tạo Chẩn đoán trước sinh tại Singapore

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'42', N'10', N'2007: BS Chuyên khoa cấp 2, chuyên ngành Sản Phụ, Đại học Y khoa Hà Nội

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'43', N'10', N'2008: Tham gia khóa đào tạo hỗ trợ sinh sản tại Thái Lan

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'44', N'10', N'2009: Đào tạo Sàng lọc và chẩn đoán trước sinh tại Macao

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'5', N'1', N'Đào tạo Chẩn đoán trước sinh và Y học bào thai tại Nhật')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'6', N'1', N'Đào tạo Thai nghén có nguy cơ cao, phẫu thuật nội soi, siêu âm sản khoa, phẫu thuật sàn chậu tại Mỹ

')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'7', N'1', N'Đào tạo Phụ khoa vị thành niên, Nội tiết phụ khoa tại Úc')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'8', N'1', N'2006: Đào tạo Chẩn đoán trước sinh tại Singapore')
INSERT [dbo].[Education] ([id], [doctorId], [description]) VALUES (N'9', N'1', N'011: Tham gia khóa đào tạo về Kỹ thuật chẩn đoán trước sinh tại Đài Loan')
GO
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt]) VALUES (1, N'hoangvhhe176169@fpt.edu.vn', N'4e3b0cec4ec3e98ffb1341d8753c757e9b864316d4170fb8c77f82b474ab4c497a6149ae926c8baf4123ec5d280efda4', 1, N'Hoang Vu', CAST(N'2003-05-05' AS Date), 0, N'ViN', N'VN', 1, N'0123456789', N'VN', 1, CAST(N'2003-05-05T00:00:00.000' AS DateTime))
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt]) VALUES (2, N'hoangmeo1905@gmail.com', N'1a60d4c40feacbfb6d97850f3e9ebdc46392708ac7d3b9ebc0da2379a46efc97dafe25de04248a3d124237f9c5ae4eae', 1, N'Vu Huy Hoang', CAST(N'1900-01-01' AS Date), 1, N'53, Group 11, Minh Xuan District', N'VN', 1, N'0916432148', N'VN', 1, CAST(N'2023-09-24T05:09:31.477' AS DateTime))
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt]) VALUES (3, N'binhntthe176420@fpt.edu.vn', N'3ebed938b5e29f48fa550e6cb116147d2b66f067dabd28fa567b0991380573c9177372accc3528b7d80ce9134556ea59', 1, N'Vu Huy Hoang', CAST(N'1900-01-01' AS Date), 1, N'hehe', N'VN', 1, N'0916432148', N'VN', 1, CAST(N'2023-09-24T05:10:17.993' AS DateTime))
GO
INSERT [dbo].[EmployeeRole] ([id], [role]) VALUES (1, N'non-Admin')
GO
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'1', N'1', N'Từ 1984 - 2013: Nguyên Phó Trưởng khoa, Khoa Phụ sản, Bệnh viện Bạch Mai')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'10', N'2', N'2007 - 2008: Giảng viên lâm sàng, đơn vị Huyết học và Ung bướu, khoa Nội tổng hợp, Bệnh viện đại học quốc gia Seoul, Hàn Quốc')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'11', N'2', N'2003 - 2006: Bác sĩ Nội trú, khoa Nội tổng hợp, Bệnh viện đại học Inha, Incheon, Hàn Quốc')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'12', N'2', N'2002 - 2003: Thực tập tại bệnh viện đại học Inha, Incheon, Hàn Quốc')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'13', N'3', N'2002 - 2003: Thực tập tại bệnh viện đại học Inha, Incheon, Hàn Quốc')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'14', N'3', N'2003 - 2006: Bác sĩ Nội trú, khoa Nội tổng hợp, Bệnh viện đại học Inha, Incheon, Hàn Quốc')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'15', N'4', N'2011 - 2014: Phó giáo sư, đơn vị Huyết học và Ung bướu, khoa Nội tổng hợp, Đại học Y Inha, Incheon, Hàn Quốc')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'16', N'4', N'2014 - 2017: Phó giáo sư, đơn vị Huyết học và Ung bướu, khoa Nội tổng hợp, Đại học Y Inha, Incheon, Hàn Quốc')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'17', N'4', N'2017 - 2019: Khoa Ung bướu - Huyết học, Bệnh viện Đa khoa Quốc tế Vinmec Times City')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'18', N'4', N'2019 - Đến nay: Trưởng Đơn nguyên Phòng khám Ung bướu Huyết học kiêm Phụ trách Khoa Ung bướu Huyết học - Trung tâm Ung bướu Vinmec - Penn - Bệnh viện Đa khoa Quốc tế Vinmec Times City.')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'19', N'5', N'Từ 1984 - 2013: Nguyên Phó Trưởng khoa, Khoa Phụ sản, Bệnh viện Bạch Mai')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'2', N'1', N'Từ 1997 - 2000: Bác sĩ khoa Phụ Sản - Bệnh viện Việt Úc, Bệnh viện Việt Pháp Hà Nội ')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'20', N'5', N'Từ 1997 - 2000: Bác sĩ khoa Phụ Sản - Bệnh viện Việt Úc, Bệnh viện Việt Pháp Hà Nội ')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'21', N'5', N'Từ 2013 - 2/2023: Phó Giám đốc Trung tâm, kiêm Trưởng khoa Phụ khoa;Chủ tịch Hội đồng Chẩn đoán trước sinh; Thành viên Hội đồng chuyên môn Vinmec, Trung tâm Sức khỏe Phụ nữ, Bệnh viện Đa khoa Quốc tế Vinmec Times City')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'22', N'6', N'Từ 2013 - 2/2023: Phó Giám đốc Trung tâm, kiêm Trưởng khoa Phụ khoa;Chủ tịch Hội đồng Chẩn đoán trước sinh; Thành viên Hội đồng chuyên môn Vinmec, Trung tâm Sức khỏe Phụ nữ, Bệnh viện Đa khoa Quốc tế Vinmec Times City')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'23', N'6', N'Từ 2013 - 2/2023: Phó Giám đốc Trung tâm, kiêm Trưởng khoa Phụ khoa;Chủ tịch Hội đồng Chẩn đoán trước sinh; Thành viên Hội đồng chuyên môn Vinmec, Trung tâm Sức khỏe Phụ nữ, Bệnh viện Đa khoa Quốc tế Vinmec Times City')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'24', N'6', N'Từ 1997 - 2000: Bác sĩ khoa Phụ Sản - Bệnh viện Việt Úc, Bệnh viện Việt Pháp Hà Nội ')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'25', N'7', N'Từ 1997 - 2000: Bác sĩ khoa Phụ Sản - Bệnh viện Việt Úc, Bệnh viện Việt Pháp Hà Nội ')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'26', N'8', N'Từ 1997 - 2000: Bác sĩ khoa Phụ Sản - Bệnh viện Việt Úc, Bệnh viện Việt Pháp Hà Nội ')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'27', N'8', N'Từ 2013 - 2/2023: Phó Giám đốc Trung tâm, kiêm Trưởng khoa Phụ khoa;Chủ tịch Hội đồng Chẩn đoán trước sinh; Thành viên Hội đồng chuyên môn Vinmec, Trung tâm Sức khỏe Phụ nữ, Bệnh viện Đa khoa Quốc tế Vinmec Times City')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'28', N'8', N'Từ 2013 - 2/2023: Phó Giám đốc Trung tâm, kiêm Trưởng khoa Phụ khoa;Chủ tịch Hội đồng Chẩn đoán trước sinh; Thành viên Hội đồng chuyên môn Vinmec, Trung tâm Sức khỏe Phụ nữ, Bệnh viện Đa khoa Quốc tế Vinmec Times City')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'29', N'9', N'Từ 2013 - 2/2023: Phó Giám đốc Trung tâm, kiêm Trưởng khoa Phụ khoa;Chủ tịch Hội đồng Chẩn đoán trước sinh; Thành viên Hội đồng chuyên môn Vinmec, Trung tâm Sức khỏe Phụ nữ, Bệnh viện Đa khoa Quốc tế Vinmec Times City')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'3', N'1', N'Từ 2013 - 2/2023: Phó Giám đốc Trung tâm, kiêm Trưởng khoa Phụ khoa;Chủ tịch Hội đồng Chẩn đoán trước sinh; Thành viên Hội đồng chuyên môn Vinmec, Trung tâm Sức khỏe Phụ nữ, Bệnh viện Đa khoa Quốc tế Vinmec Times City')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'30', N'9', N'Từ 1997 - 2000: Bác sĩ khoa Phụ Sản - Bệnh viện Việt Úc, Bệnh viện Việt Pháp Hà Nội ')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'31', N'9', N'Từ 1997 - 2000: Bác sĩ khoa Phụ Sản - Bệnh viện Việt Úc, Bệnh viện Việt Pháp Hà Nội ')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'32', N'10', N'Từ 1997 - 2000: Bác sĩ khoa Phụ Sản - Bệnh viện Việt Úc, Bệnh viện Việt Pháp Hà Nội ')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'33', N'10', N'Từ 2013 - 2/2023: Phó Giám đốc Trung tâm, kiêm Trưởng khoa Phụ khoa;Chủ tịch Hội đồng Chẩn đoán trước sinh; Thành viên Hội đồng chuyên môn Vinmec, Trung tâm Sức khỏe Phụ nữ, Bệnh viện Đa khoa Quốc tế Vinmec Times City')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'4', N'1', N'Từ 2/2023 - nay: Bác sĩ Sản phụ khoa, Trung tâm Sức khỏe Phụ nữ, Bệnh viện Đa khoa Quốc tế Vinmec Times City ')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'5', N'2', N'2019 - Đến nay: Trưởng Đơn nguyên Phòng khám Ung bướu Huyết học kiêm Phụ trách Khoa Ung bướu Huyết học - Trung tâm Ung bướu Vinmec - Penn - Bệnh viện Đa khoa Quốc tế Vinmec Times City.')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'6', N'2', N'2017 - 2019: Khoa Ung bướu - Huyết học, Bệnh viện Đa khoa Quốc tế Vinmec Times City')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'7', N'2', N'2014 - 2017: Phó giáo sư, đơn vị Huyết học và Ung bướu, khoa Nội tổng hợp, Đại học Y Inha, Incheon, Hàn Quốc')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'8', N'2', N'2011 - 2014: Phó giáo sư, đơn vị Huyết học và Ung bướu, khoa Nội tổng hợp, Đại học Y Inha, Incheon, Hàn Quốc')
INSERT [dbo].[Experience] ([id], [doctorId], [description]) VALUES (N'9', N'2', N'2008 - 2011: Giảng viên toàn thời gian, đơn vị Huyết học và Ung bướu, khoa Nội tổng hợp, Đại học Y Inha, Incheon, Hàn Quốc')
GO
INSERT [dbo].[NavigationItem] ([id], [name], [link]) VALUES (1, N'Home', N'user-home')
INSERT [dbo].[NavigationItem] ([id], [name], [link]) VALUES (2, N'Branches', N'user-branches')
INSERT [dbo].[NavigationItem] ([id], [name], [link]) VALUES (3, N'Services', N'user-service')
INSERT [dbo].[NavigationItem] ([id], [name], [link]) VALUES (4, N'Doctors', N'user-list-all-doctor')
INSERT [dbo].[NavigationItem] ([id], [name], [link]) VALUES (5, N'News', N'user-news')
GO
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage]) VALUES (1, N'hehe', N'hehe', N'hoangmeo1905@gmail.com', 1, CAST(N'2023-09-26T00:00:00.000' AS DateTime), CAST(N'2023-09-26T00:00:00.000' AS DateTime), 464, N'https://cdn.youmed.vn/tin-tuc/wp-content/uploads/2023/08/di-angesic.jpg?width=300')
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage]) VALUES (2, N'test', N'quỷ kiếm darkin', N'hoangmeo1905@gmail.com', 2, CAST(N'2021-06-15T00:00:00.000' AS DateTime), CAST(N'2021-09-29T00:00:00.000' AS DateTime), 9166, N'https://cdn.youmed.vn/tin-tuc/wp-content/uploads/2023/08/diaprid-4.jpg?width=300')
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage]) VALUES (3, N'top 4 người tui luôn tin tưởng', N'jack j97 trịnh trần phương tuấn meo meo', N'hoangvhhe176169@fpt.edu.vn', 2, CAST(N'2018-01-01T00:00:00.000' AS DateTime), CAST(N'2018-01-01T00:00:00.000' AS DateTime), 1000497, N'https://i.ytimg.com/vi/c_9F8m9rMvI/maxresdefault.jpg')
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage]) VALUES (4, N'không ngờ lại gặp phải thằng liều', N'thí hậu king', N'binhntthe176420@fpt.edu.vn', 3, CAST(N'2023-01-01T00:00:00.000' AS DateTime), CAST(N'2023-01-01T00:00:00.000' AS DateTime), 61477, N'data:image/jpeg;base64,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')
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage]) VALUES (5, N'chồn xanh lè', N'tuyển tập Doraemon ', N'hoangmeo1905@gmail.com', 1, CAST(N'2023-01-01T00:00:00.000' AS DateTime), CAST(N'2023-01-01T00:00:00.000' AS DateTime), 9199, N'https://statics.voz.tech/data/avatars/o/1860/1860210.jpg?1675217460')
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage]) VALUES (6, N'leader team 6 mất ngủ', N'lần thứ 11 trong tháng 9 này', N'hoangvhhe176169@fpt.edu.vn', 5, CAST(N'2023-01-01T00:00:00.000' AS DateTime), CAST(N'2023-01-01T00:00:00.000' AS DateTime), 8175, N'https://img-cdn.xemgame.com/2022/04/06/Thanh-nien-noi-dao-ly-choi-game-va-cai-ket.png')
GO
INSERT [dbo].[NewsCategory] ([id], [name]) VALUES (1, N'hehe')
INSERT [dbo].[NewsCategory] ([id], [name]) VALUES (2, N'hehehe')
INSERT [dbo].[NewsCategory] ([id], [name]) VALUES (3, N'ehehehe')
INSERT [dbo].[NewsCategory] ([id], [name]) VALUES (4, N'eheheheehehehe')
INSERT [dbo].[NewsCategory] ([id], [name]) VALUES (5, N'vcl')
GO
INSERT [dbo].[Province] ([id], [name]) VALUES (1, N'Hà Nội')
INSERT [dbo].[Province] ([id], [name]) VALUES (2, N'TP Hồ Chí Minh')
INSERT [dbo].[Province] ([id], [name]) VALUES (3, N'Nha Trang')
GO
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description]) VALUES (N'1', N'1', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

<ul>
    <li>Tổng kết tác dụng của Blastolysin trên các bệnh tế bào nuôi. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 1985</li>
    <li>Nghiên cứu tỉ lệ mắc bệnh và kết quả điều trị viêm đường sinh dục do Chlamydia trachomatis. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 2001</li>
    <li>Sử dụng kỹ thuật phân tử để chẩn đoán sớm hai bệnh thần kinh cơ di truyền phổ biến: loạn dưỡng cơ Duchenne và thoái hóa cơ tủy. Đề tài cấp Bộ</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các công bố quốc tế gần đây:</p>

<ul>
    <li>Can Autologous Adipose-Derived Mesenchymal Stem Cell Transplantation Improve Sexual Function in People with Sexual Functional Deficiency? Stem Cell Reviews and Reports, 17, pages2153–2163 (2021)</li>
    <li>“Adipose-derived mesenchymal stem cell therapy for the management of female sexual dysfunction: Literature reviews and study design of a clinical trial”. Front Cell Dev Biol. 2022; 10: 956274</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các sách, tài liệu đã viết:</p>

<ul>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về: Sàng lọc trước sinh, Quản lý thai kỳ, Đái tháo đường thai kỳ, Sàng lọc sơ sinh, Sàng lọc bệnh lý tuyến vú, (2020)</li>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về Lạc nội mạc tử cung, Buồng trứng đa nang (2021)</li>
    <!-- Thêm các mục khác tương tự -->
</ul>
')
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description]) VALUES (N'10', N'10', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

<ul>
    <li>Tổng kết tác dụng của Blastolysin trên các bệnh tế bào nuôi. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 1985</li>
    <li>Nghiên cứu tỉ lệ mắc bệnh và kết quả điều trị viêm đường sinh dục do Chlamydia trachomatis. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 2001</li>
    <li>Sử dụng kỹ thuật phân tử để chẩn đoán sớm hai bệnh thần kinh cơ di truyền phổ biến: loạn dưỡng cơ Duchenne và thoái hóa cơ tủy. Đề tài cấp Bộ</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các công bố quốc tế gần đây:</p>

<ul>
    <li>Can Autologous Adipose-Derived Mesenchymal Stem Cell Transplantation Improve Sexual Function in People with Sexual Functional Deficiency? Stem Cell Reviews and Reports, 17, pages2153–2163 (2021)</li>
    <li>“Adipose-derived mesenchymal stem cell therapy for the management of female sexual dysfunction: Literature reviews and study design of a clinical trial”. Front Cell Dev Biol. 2022; 10: 956274</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các sách, tài liệu đã viết:</p>

<ul>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về: Sàng lọc trước sinh, Quản lý thai kỳ, Đái tháo đường thai kỳ, Sàng lọc sơ sinh, Sàng lọc bệnh lý tuyến vú, (2020)</li>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về Lạc nội mạc tử cung, Buồng trứng đa nang (2021)</li>
    <!-- Thêm các mục khác tương tự -->
</ul>
')
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description]) VALUES (N'2', N'2', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

<ul>
    <li>Tổng kết tác dụng của Blastolysin trên các bệnh tế bào nuôi. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 1985</li>
    <li>Nghiên cứu tỉ lệ mắc bệnh và kết quả điều trị viêm đường sinh dục do Chlamydia trachomatis. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 2001</li>
    <li>Sử dụng kỹ thuật phân tử để chẩn đoán sớm hai bệnh thần kinh cơ di truyền phổ biến: loạn dưỡng cơ Duchenne và thoái hóa cơ tủy. Đề tài cấp Bộ</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các công bố quốc tế gần đây:</p>

<ul>
    <li>Can Autologous Adipose-Derived Mesenchymal Stem Cell Transplantation Improve Sexual Function in People with Sexual Functional Deficiency? Stem Cell Reviews and Reports, 17, pages2153–2163 (2021)</li>
    <li>“Adipose-derived mesenchymal stem cell therapy for the management of female sexual dysfunction: Literature reviews and study design of a clinical trial”. Front Cell Dev Biol. 2022; 10: 956274</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các sách, tài liệu đã viết:</p>

<ul>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về: Sàng lọc trước sinh, Quản lý thai kỳ, Đái tháo đường thai kỳ, Sàng lọc sơ sinh, Sàng lọc bệnh lý tuyến vú, (2020)</li>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về Lạc nội mạc tử cung, Buồng trứng đa nang (2021)</li>
    <!-- Thêm các mục khác tương tự -->
</ul>
')
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description]) VALUES (N'3', N'3', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

<ul>
    <li>Tổng kết tác dụng của Blastolysin trên các bệnh tế bào nuôi. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 1985</li>
    <li>Nghiên cứu tỉ lệ mắc bệnh và kết quả điều trị viêm đường sinh dục do Chlamydia trachomatis. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 2001</li>
    <li>Sử dụng kỹ thuật phân tử để chẩn đoán sớm hai bệnh thần kinh cơ di truyền phổ biến: loạn dưỡng cơ Duchenne và thoái hóa cơ tủy. Đề tài cấp Bộ</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các công bố quốc tế gần đây:</p>

<ul>
    <li>Can Autologous Adipose-Derived Mesenchymal Stem Cell Transplantation Improve Sexual Function in People with Sexual Functional Deficiency? Stem Cell Reviews and Reports, 17, pages2153–2163 (2021)</li>
    <li>“Adipose-derived mesenchymal stem cell therapy for the management of female sexual dysfunction: Literature reviews and study design of a clinical trial”. Front Cell Dev Biol. 2022; 10: 956274</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các sách, tài liệu đã viết:</p>

<ul>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về: Sàng lọc trước sinh, Quản lý thai kỳ, Đái tháo đường thai kỳ, Sàng lọc sơ sinh, Sàng lọc bệnh lý tuyến vú, (2020)</li>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về Lạc nội mạc tử cung, Buồng trứng đa nang (2021)</li>
    <!-- Thêm các mục khác tương tự -->
</ul>
')
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description]) VALUES (N'4', N'4', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

<ul>
    <li>Tổng kết tác dụng của Blastolysin trên các bệnh tế bào nuôi. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 1985</li>
    <li>Nghiên cứu tỉ lệ mắc bệnh và kết quả điều trị viêm đường sinh dục do Chlamydia trachomatis. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 2001</li>
    <li>Sử dụng kỹ thuật phân tử để chẩn đoán sớm hai bệnh thần kinh cơ di truyền phổ biến: loạn dưỡng cơ Duchenne và thoái hóa cơ tủy. Đề tài cấp Bộ</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các công bố quốc tế gần đây:</p>

<ul>
    <li>Can Autologous Adipose-Derived Mesenchymal Stem Cell Transplantation Improve Sexual Function in People with Sexual Functional Deficiency? Stem Cell Reviews and Reports, 17, pages2153–2163 (2021)</li>
    <li>“Adipose-derived mesenchymal stem cell therapy for the management of female sexual dysfunction: Literature reviews and study design of a clinical trial”. Front Cell Dev Biol. 2022; 10: 956274</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các sách, tài liệu đã viết:</p>

<ul>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về: Sàng lọc trước sinh, Quản lý thai kỳ, Đái tháo đường thai kỳ, Sàng lọc sơ sinh, Sàng lọc bệnh lý tuyến vú, (2020)</li>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về Lạc nội mạc tử cung, Buồng trứng đa nang (2021)</li>
    <!-- Thêm các mục khác tương tự -->
</ul>
')
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description]) VALUES (N'5', N'5', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

<ul>
    <li>Tổng kết tác dụng của Blastolysin trên các bệnh tế bào nuôi. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 1985</li>
    <li>Nghiên cứu tỉ lệ mắc bệnh và kết quả điều trị viêm đường sinh dục do Chlamydia trachomatis. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 2001</li>
    <li>Sử dụng kỹ thuật phân tử để chẩn đoán sớm hai bệnh thần kinh cơ di truyền phổ biến: loạn dưỡng cơ Duchenne và thoái hóa cơ tủy. Đề tài cấp Bộ</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các công bố quốc tế gần đây:</p>

<ul>
    <li>Can Autologous Adipose-Derived Mesenchymal Stem Cell Transplantation Improve Sexual Function in People with Sexual Functional Deficiency? Stem Cell Reviews and Reports, 17, pages2153–2163 (2021)</li>
    <li>“Adipose-derived mesenchymal stem cell therapy for the management of female sexual dysfunction: Literature reviews and study design of a clinical trial”. Front Cell Dev Biol. 2022; 10: 956274</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các sách, tài liệu đã viết:</p>

<ul>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về: Sàng lọc trước sinh, Quản lý thai kỳ, Đái tháo đường thai kỳ, Sàng lọc sơ sinh, Sàng lọc bệnh lý tuyến vú, (2020)</li>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về Lạc nội mạc tử cung, Buồng trứng đa nang (2021)</li>
    <!-- Thêm các mục khác tương tự -->
</ul>
')
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description]) VALUES (N'6', N'6', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

<ul>
    <li>Tổng kết tác dụng của Blastolysin trên các bệnh tế bào nuôi. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 1985</li>
    <li>Nghiên cứu tỉ lệ mắc bệnh và kết quả điều trị viêm đường sinh dục do Chlamydia trachomatis. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 2001</li>
    <li>Sử dụng kỹ thuật phân tử để chẩn đoán sớm hai bệnh thần kinh cơ di truyền phổ biến: loạn dưỡng cơ Duchenne và thoái hóa cơ tủy. Đề tài cấp Bộ</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các công bố quốc tế gần đây:</p>

<ul>
    <li>Can Autologous Adipose-Derived Mesenchymal Stem Cell Transplantation Improve Sexual Function in People with Sexual Functional Deficiency? Stem Cell Reviews and Reports, 17, pages2153–2163 (2021)</li>
    <li>“Adipose-derived mesenchymal stem cell therapy for the management of female sexual dysfunction: Literature reviews and study design of a clinical trial”. Front Cell Dev Biol. 2022; 10: 956274</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các sách, tài liệu đã viết:</p>

<ul>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về: Sàng lọc trước sinh, Quản lý thai kỳ, Đái tháo đường thai kỳ, Sàng lọc sơ sinh, Sàng lọc bệnh lý tuyến vú, (2020)</li>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về Lạc nội mạc tử cung, Buồng trứng đa nang (2021)</li>
    <!-- Thêm các mục khác tương tự -->
</ul>
')
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description]) VALUES (N'7', N'7', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

<ul>
    <li>Tổng kết tác dụng của Blastolysin trên các bệnh tế bào nuôi. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 1985</li>
    <li>Nghiên cứu tỉ lệ mắc bệnh và kết quả điều trị viêm đường sinh dục do Chlamydia trachomatis. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 2001</li>
    <li>Sử dụng kỹ thuật phân tử để chẩn đoán sớm hai bệnh thần kinh cơ di truyền phổ biến: loạn dưỡng cơ Duchenne và thoái hóa cơ tủy. Đề tài cấp Bộ</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các công bố quốc tế gần đây:</p>

<ul>
    <li>Can Autologous Adipose-Derived Mesenchymal Stem Cell Transplantation Improve Sexual Function in People with Sexual Functional Deficiency? Stem Cell Reviews and Reports, 17, pages2153–2163 (2021)</li>
    <li>“Adipose-derived mesenchymal stem cell therapy for the management of female sexual dysfunction: Literature reviews and study design of a clinical trial”. Front Cell Dev Biol. 2022; 10: 956274</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các sách, tài liệu đã viết:</p>

<ul>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về: Sàng lọc trước sinh, Quản lý thai kỳ, Đái tháo đường thai kỳ, Sàng lọc sơ sinh, Sàng lọc bệnh lý tuyến vú, (2020)</li>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về Lạc nội mạc tử cung, Buồng trứng đa nang (2021)</li>
    <!-- Thêm các mục khác tương tự -->
</ul>
')
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description]) VALUES (N'8', N'8', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

<ul>
    <li>Tổng kết tác dụng của Blastolysin trên các bệnh tế bào nuôi. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 1985</li>
    <li>Nghiên cứu tỉ lệ mắc bệnh và kết quả điều trị viêm đường sinh dục do Chlamydia trachomatis. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 2001</li>
    <li>Sử dụng kỹ thuật phân tử để chẩn đoán sớm hai bệnh thần kinh cơ di truyền phổ biến: loạn dưỡng cơ Duchenne và thoái hóa cơ tủy. Đề tài cấp Bộ</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các công bố quốc tế gần đây:</p>

<ul>
    <li>Can Autologous Adipose-Derived Mesenchymal Stem Cell Transplantation Improve Sexual Function in People with Sexual Functional Deficiency? Stem Cell Reviews and Reports, 17, pages2153–2163 (2021)</li>
    <li>“Adipose-derived mesenchymal stem cell therapy for the management of female sexual dysfunction: Literature reviews and study design of a clinical trial”. Front Cell Dev Biol. 2022; 10: 956274</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các sách, tài liệu đã viết:</p>

<ul>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về: Sàng lọc trước sinh, Quản lý thai kỳ, Đái tháo đường thai kỳ, Sàng lọc sơ sinh, Sàng lọc bệnh lý tuyến vú, (2020)</li>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về Lạc nội mạc tử cung, Buồng trứng đa nang (2021)</li>
    <!-- Thêm các mục khác tương tự -->
</ul>
')
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description]) VALUES (N'9', N'9', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

<ul>
    <li>Tổng kết tác dụng của Blastolysin trên các bệnh tế bào nuôi. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 1985</li>
    <li>Nghiên cứu tỉ lệ mắc bệnh và kết quả điều trị viêm đường sinh dục do Chlamydia trachomatis. Kỷ yếu Công trình nghiên cứu khoa học Bệnh viện Bạch Mai 2001</li>
    <li>Sử dụng kỹ thuật phân tử để chẩn đoán sớm hai bệnh thần kinh cơ di truyền phổ biến: loạn dưỡng cơ Duchenne và thoái hóa cơ tủy. Đề tài cấp Bộ</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các công bố quốc tế gần đây:</p>

<ul>
    <li>Can Autologous Adipose-Derived Mesenchymal Stem Cell Transplantation Improve Sexual Function in People with Sexual Functional Deficiency? Stem Cell Reviews and Reports, 17, pages2153–2163 (2021)</li>
    <li>“Adipose-derived mesenchymal stem cell therapy for the management of female sexual dysfunction: Literature reviews and study design of a clinical trial”. Front Cell Dev Biol. 2022; 10: 956274</li>
    <!-- Thêm các mục khác tương tự -->
</ul>

<p>Các sách, tài liệu đã viết:</p>

<ul>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về: Sàng lọc trước sinh, Quản lý thai kỳ, Đái tháo đường thai kỳ, Sàng lọc sơ sinh, Sàng lọc bệnh lý tuyến vú, (2020)</li>
    <li>Đồng biên soạn sách, tài liệu giảng dạy về Lạc nội mạc tử cung, Buồng trứng đa nang (2021)</li>
    <!-- Thêm các mục khác tương tự -->
</ul>
')
GO
INSERT [dbo].[Reviews] ([id], [userId], [doctorId], [appointmentId], [rating], [reviewContent], [createdAt]) VALUES (1, N'1', N'1', 1, 4, N'I’ve been using this medical service for over a year now and I’m extremely satisfied. The staff is always friendly and professional, and they go out of their way to make sure I’m comfortable. The facilities are clean and well-maintained. I highly recommend them!', CAST(N'2023-09-24T00:00:00.000' AS DateTime))
INSERT [dbo].[Reviews] ([id], [userId], [doctorId], [appointmentId], [rating], [reviewContent], [createdAt]) VALUES (2, N'2', N'1', 2, 5, N'The level of care and attention I received was exceptional. The doctors and nurses were very knowledgeable and took the time to explain everything to me in a way that I could understand. I felt well taken care of throughout my treatment.', CAST(N'2023-09-24T00:00:00.000' AS DateTime))
INSERT [dbo].[Reviews] ([id], [userId], [doctorId], [appointmentId], [rating], [reviewContent], [createdAt]) VALUES (3, N'3', N'2', 3, 4, N'I was really impressed with the efficiency of the service. From booking an appointment to getting my results, everything was smooth and quick. This is exactly what you need when you’re not feeling well.', CAST(N'2022-09-22T00:00:00.000' AS DateTime))
INSERT [dbo].[Reviews] ([id], [userId], [doctorId], [appointmentId], [rating], [reviewContent], [createdAt]) VALUES (4, N'3', N'1', 4, 5, NULL, CAST(N'2003-05-05T00:00:00.000' AS DateTime))
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
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'1', N'nguyenthitubinh133@gmail.com', N'7150a81293c5344e082eb96f59145503fe7f31593a60f1bc333c9bb4b19a3d975de9de8ae56a6f86cdd76d1dcbd0644c', N'Nguyễn Tú Bình', CAST(N'2003-04-14' AS Date), 1, N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, N'Kinh', N'0358219546', N'assets/client/images/doctor-thumb-01.jpg', NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'2', N'nguyenvietthu03@gmail.com', N'7150a81293c5344e082eb96f59145503fe7f31593a60f1bc333c9bb4b19a3d975de9de8ae56a6f86cdd76d1dcbd0644c', N'Nguyễn Việt Thu', CAST(N'2003-05-05' AS Date), 1, N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, N'Kinh', N'0358219546', N'assets/client/images/client-img.png', NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'3', N'user', N'7150a81293c5344e082eb96f59145503fe7f31593a60f1bc333c9bb4b19a3d975de9de8ae56a6f86cdd76d1dcbd0644c', N'Nguyễn Tú Bình', CAST(N'2003-05-06' AS Date), 0, N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, NULL, N'0123456789', N'assets/client/images/doctor-thumb-02.jpg', NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'4', N'gnaohuv55', N'19772918d6eb06a2c8ee321cbfd0bd4636aaf86111d5a7da5f1e5161ec433006768900227b40aa933dbc6673cefd6730', N'hehe', CAST(N'2003-05-05' AS Date), 0, N'hehe', 1, N'0', NULL, NULL, N'0123456789', N'assets/client/images/client-img.png', NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'5', N'hoangvhhe176169@fpt.edu.vn', N'19772918d6eb06a2c8ee321cbfd0bd4636aaf86111d5a7da5f1e5161ec433006768900227b40aa933dbc6673cefd6730', N'hoang vu', CAST(N'2003-05-05' AS Date), 1, N'hehe', 1, N'0123', NULL, NULL, NULL, N'assets/client/images/client-img.png', NULL)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Doctor__AB6E6164F508D8D6]    Script Date: 9/28/2023 00:00:44 ******/
ALTER TABLE [dbo].[Doctor] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Employee__AB6E616423FCE671]    Script Date: 9/28/2023 00:00:44 ******/
ALTER TABLE [dbo].[Employee] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__FamilyPr__AB6E616429269EA7]    Script Date: 9/28/2023 00:00:44 ******/
ALTER TABLE [dbo].[FamilyProfile] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__User__AB6E6164C942E4C2]    Script Date: 9/28/2023 00:00:44 ******/
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
ALTER TABLE [dbo].[Awards]  WITH CHECK ADD FOREIGN KEY([doctorId])
REFERENCES [dbo].[Doctor] ([id])
GO
ALTER TABLE [dbo].[BannerDetails]  WITH CHECK ADD FOREIGN KEY([bannerId])
REFERENCES [dbo].[Banner] ([id])
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
ALTER TABLE [dbo].[Education]  WITH CHECK ADD FOREIGN KEY([doctorId])
REFERENCES [dbo].[Doctor] ([id])
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
ALTER TABLE [dbo].[Experience]  WITH CHECK ADD FOREIGN KEY([doctorId])
REFERENCES [dbo].[Doctor] ([id])
GO
ALTER TABLE [dbo].[FamilyProfile]  WITH CHECK ADD FOREIGN KEY([provinceId])
REFERENCES [dbo].[Province] ([id])
GO
ALTER TABLE [dbo].[FamilyProfile]  WITH CHECK ADD FOREIGN KEY([relationId])
REFERENCES [dbo].[Relationship] ([id])
GO
ALTER TABLE [dbo].[FamilyProfile]  WITH CHECK ADD  CONSTRAINT [FK_FamilyProfile_User] FOREIGN KEY([ownerId])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[FamilyProfile] CHECK CONSTRAINT [FK_FamilyProfile_User]
GO
ALTER TABLE [dbo].[News]  WITH CHECK ADD FOREIGN KEY([author])
REFERENCES [dbo].[Employee] ([email])
GO
ALTER TABLE [dbo].[News]  WITH CHECK ADD FOREIGN KEY([categoryId])
REFERENCES [dbo].[NewsCategory] ([id])
GO
ALTER TABLE [dbo].[ResearchPapers]  WITH CHECK ADD FOREIGN KEY([doctorId])
REFERENCES [dbo].[Doctor] ([id])
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
