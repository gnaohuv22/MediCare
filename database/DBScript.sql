USE [master]
GO
/****** Object:  Database [hehe1]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[AcademicRank]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[AccessLog]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[AdminSidebarMenu]    Script Date: 10/9/2023 02:13:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AdminSidebarMenu](
	[name] [nvarchar](50) NULL,
	[link] [varchar](500) NULL,
	[icon] [nvarchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Appointments]    Script Date: 10/9/2023 02:13:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Appointments](
	[id] [int] NOT NULL,
	[userId] [varchar](255) NULL,
	[doctorId] [varchar](255) NULL,
	[serviceId] [int] NULL,
	[plannedAt] [datetime] NULL,
	[status] [int] NULL,
	[branchId] [int] NULL,
	[createdAt] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Awards]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[Banner]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[BannerDetails]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[BillingHistory]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[Branch]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[CancelledRequest]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[Certificate]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[CertificateDoctor]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[CurriculumVitae]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[Department]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[Doctor]    Script Date: 10/9/2023 02:13:57 ******/
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
	[birthDate] [date] NULL,
	[gender] [int] NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoctorCertificates]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[DoctorService]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[DoctorServices]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[DoctorWorkingDay]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[DoctorWorkingSlot]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[Education]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[Employee]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[EmployeeRole]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[Experience]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[FamilyProfile]    Script Date: 10/9/2023 02:13:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FamilyProfile](
	[profileId] [int] IDENTITY(1,1) NOT NULL,
	[email] [nvarchar](255) NULL,
	[name] [nvarchar](255) NULL,
	[birthDate] [date] NULL,
	[gender] [nvarchar](50) NULL,
	[address] [nvarchar](255) NULL,
	[provinceId] [int] NULL,
	[identity] [nvarchar](255) NULL,
	[medicalId] [nvarchar](255) NULL,
	[ethnic] [nvarchar](255) NULL,
	[phone] [nvarchar](255) NULL,
	[profilePicture] [nvarchar](255) NULL,
	[createdAt] [datetime] NULL,
	[relationId] [int] NULL,
	[ownerId] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[profileId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[News]    Script Date: 10/9/2023 02:13:57 ******/
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
	[subtitle] [nvarchar](max) NULL,
	[slug] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NewsCategory]    Script Date: 10/9/2023 02:13:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NewsCategory](
	[id] [int] NOT NULL,
	[name] [nvarchar](max) NULL,
	[parentId] [int] NULL,
	[slug] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Province]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[Relationship]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[ResearchPapers]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[Reviews]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[ServiceTag]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[SubLevelMenu]    Script Date: 10/9/2023 02:13:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SubLevelMenu](
	[id] [int] NOT NULL,
	[content] [nvarchar](max) NULL,
	[parentId] [int] NULL,
	[locateId] [int] NULL,
	[href] [nvarchar](max) NULL,
	[icon] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TopLevelMenu]    Script Date: 10/9/2023 02:13:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TopLevelMenu](
	[id] [int] NOT NULL,
	[name] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 10/9/2023 02:13:57 ******/
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
/****** Object:  Table [dbo].[WorkingSlot]    Script Date: 10/9/2023 02:13:57 ******/
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
INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Trang chủ', N'/admin-employee-home', N'fa fa-dashboard')
INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Nhân viên', N'/admin-list-employee', N'fa fa-user')
INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Bác sĩ', N'/admin-doctors', N'fa fa-user-md')
INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Người dùng', N'/admin-list-user', N'fa fa-wheelchair')
INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Đơn đặt khám', N'/admin-list-appointments', N'fa fa-calendar')
INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Đánh giá', N'/admin-list-review', NULL)
INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Tin tức', N'/admin-list-news', NULL)
GO
INSERT [dbo].[Appointments] ([id], [userId], [doctorId], [serviceId], [plannedAt], [status], [branchId], [createdAt]) VALUES (1, N'1', N'1', 1, NULL, 2, NULL, NULL)
INSERT [dbo].[Appointments] ([id], [userId], [doctorId], [serviceId], [plannedAt], [status], [branchId], [createdAt]) VALUES (2, N'1', N'1', 2, NULL, 2, NULL, NULL)
INSERT [dbo].[Appointments] ([id], [userId], [doctorId], [serviceId], [plannedAt], [status], [branchId], [createdAt]) VALUES (3, N'3', N'2', 1, NULL, 2, NULL, NULL)
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
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (1, N'1')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (1, N'4')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (1, N'9')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (2, N'10')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (3, N'1')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (3, N'8')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (3, N'9')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (5, N'8')
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId]) VALUES (6, N'1')
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
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'1', N'doctor@gmail.com', N'123456', N'Nguyễn Thanh Liêm', 1, N'0123456789', 1, 1, 3000, N'Hà Nội', N'../assets/img/doctor-03.jpg', 1, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'10', N'doctor9@gmail.com', N'123456', N'Lê Thị My', 1, N'0123456789', NULL, 5, NULL, N'Hà Nội', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'2', N'doctor1@gmail.com', N'123456', N'Phạm Nhật An', 1, N'0123456789', 1, 2, NULL, N'Hà Nội', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'3', N'doctor2@gmail.com', N'123456', N'Phan Quỳnh Lan', 1, N'0123456789', NULL, 8, NULL, N'Hà Nội', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'4', N'doctor3@gmail.com', N'123456', N'Võ Thành Nhân', 2, N'0123456789', 1, 3, NULL, N'TP Hồ Chí Minh', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'5', N'doctor4@gmail.com', N'123456', N'Nguyễn Thanh Hưng', 3, N'0123456789', NULL, 4, NULL, N'Nha Trang', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'6', N'doctor5@gmail.com', N'123456', N'Thái Bằng', 3, N'0123456789', NULL, 7, NULL, N'Nha Trang', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'7', N'doctor6@gmail.com', N'123456', N'Nguyễn Thị Tuyết Minh', 1, N'0123456789', NULL, 6, NULL, N'Hà Nội', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'8', N'doctor7@gmail.com', N'123456', N'Đặng Thị Linh', 1, N'0123456789', NULL, 9, NULL, N'Hà Nội', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'9', N'doctor8@gmail.com', N'123456', N'Phạm Vũ Hiệp', 1, N'0123456789', NULL, 10, NULL, N'Hà Nội', NULL, NULL, NULL, NULL, NULL)
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
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt]) VALUES (3, N'binhntthe176420@fpt.edu.vn', N'3ebed938b5e29f48fa550e6cb116147d2b66f067dabd28fa567b0991380573c9177372accc3528b7d80ce9134556ea59', 1, N'Vu Huy Hoang', CAST(N'1900-01-01' AS Date), 0, N'hehe', N'VN', 1, N'0916432148', N'VN', 1, CAST(N'2023-09-24T05:10:17.993' AS DateTime))
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
SET IDENTITY_INSERT [dbo].[FamilyProfile] ON 

INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [relationId], [ownerId]) VALUES (1, N'bingdemo@example.com', N'Bing Demo', CAST(N'2000-01-01' AS Date), N'1', N'123 Bing Street', 1, N'1234567890', N'MED1234567', N'Ethnicity', N'1234567890', N'hehe', CAST(N'2023-10-07T17:12:05.030' AS DateTime), NULL, N'10')
SET IDENTITY_INSERT [dbo].[FamilyProfile] OFF
GO
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug]) VALUES (1, N'hehe', N'hehe', N'hoangmeo1905@gmail.com', 1, CAST(N'2023-09-26T00:00:00.000' AS DateTime), CAST(N'2023-09-26T00:00:00.000' AS DateTime), 464, N'https://cdn.youmed.vn/tin-tuc/wp-content/uploads/2023/08/di-angesic.jpg?width=300', NULL, N'hehe')
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug]) VALUES (2, N'test', N'quỷ kiếm darkin', N'hoangmeo1905@gmail.com', 2, CAST(N'2021-06-15T00:00:00.000' AS DateTime), CAST(N'2021-09-29T00:00:00.000' AS DateTime), 9166, N'https://cdn.youmed.vn/tin-tuc/wp-content/uploads/2023/08/diaprid-4.jpg?width=300', NULL, N'test')
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug]) VALUES (3, N'top 4 người tui luôn tin tưởng', N'jack j97 trịnh trần phương tuấn meo meo', N'hoangvhhe176169@fpt.edu.vn', 2, CAST(N'2018-01-01T00:00:00.000' AS DateTime), CAST(N'2018-01-01T00:00:00.000' AS DateTime), 1000497, N'https://i.ytimg.com/vi/c_9F8m9rMvI/maxresdefault.jpg', NULL, N'top-4-nguoi-tui-luon-tin-tuong')
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug]) VALUES (4, N'không ngờ lại gặp phải thằng liều', N'<h1>1. Tầm quan trọng của tiêm vắc-xin đúng lịch</h1>
<p>Theo khuyến cáo của các chuyên gia y tế, ngay cả trong thời điểm có dịch bệnh, phụ huynh vẫn nên đưa con đi tiêm vắc xin đúng lịch. Việc trì hoãn lịch tiêm có thể làm tăng nguy cơ mắc bệnh đã được loại trừ hoặc khiến các bệnh truyền nhiễm, bội nhiễm như cúm, sởi, thủy đậu, viêm họng, viêm phổi...trở nên nặng hơn và khó điều trị hơn.</p>

<p>Trong tình hình dịch bệnh hiện nay, việc tiêm vaccine đúng thời điểm và đúng lịch là rất quan trọng và cần thiết để kịp thời phòng được bệnh đã tiêm và để không nhầm lẫn với bệnh khác, không gây lo lắng cho gia đình và cộng đồng. Nếu trong thời điểm này trẻ bị ho hay sốt các phụ huynh sẽ rất hoảng sợ không biết triệu chứng này do Covid-19 hay do bệnh khác vì một số triệu chứng của nhiễm Covid-19 giống như cúm hoặc các bệnh về đường hô hấp khác</p>

<p>Trong vòng 5 năm đầu đời trẻ mới xây dựng được hệ miễn dịch hoàn thiện vì vậy tiêm chủng đầy đủ là một cách tăng cường sức đề kháng hiệu quả với trẻ. Cha mẹ cần lưu ý khi đưa trẻ đi tiêm phòng tuân thủ đúng những khuyến cáo của Tổ chức Y tế thế giới WHO và Bộ y tế như đeo khẩu trang đúng cách, rửa tay thường xuyên bằng dung dịch sát khuẩn. Ngoài ra các phụ huynh luôn cập nhật thông tin đúng đủ về chủng Covid -19 này để bảo vệ bản thân và gia đình khỏi Virus Corona.</p>

<p>Những khu vực không có dịch các mẹ vẫn có thể cho bé đi tiêm phòng theo lịch tiêm chủng phù hợp với lứa tuổi, nhưng tránh những nơi tụ tập đông người, nên đặt lịch hẹn trước tránh không phải chờ đợi và đến cơ sở y tế tin tưởng cơ sở vật chất đảm bảo.</p>

<h1>2. Những vắc xin không thể trì hoãn tiêm phòng</h1>
<p>Trong đó có 4 loại vắc xin mẹ bắt buộc phải tiêm để đảm bảo sức khỏe cho con:</p>
<ul>
    <li>Vắc-xin viêm gan B: Sau khi trẻ sơ sinh ra đời, liều vắc-xin viêm gan B đầu tiên được tiêm cho trẻ sơ sinh.</li>
    <li>Tiêm vắc xin BCG: Khi trẻ được 28 ngày tuổi, bé sẽ được tiêm vắc xin BCG để phòng bệnh lao.</li>
    <li>Ngoài ra còn có hai loại vắc-xin phòng bệnh dại và tiêm phòng độc tố uốn ván cần được tiêm đúng lịch vì 2 vaccine này khi cần tiêm là không thể trì hoãn được.</li>
</ul>
<p>Tất nhiên, nếu trẻ có tiền sử tiếp xúc với người nghi ngờ hoặc đã nhiễm virus Covid-19, bạn cần cho trẻ cách ly trong 14 ngày. Trong vòng 14 ngày, nếu trẻ bị sốt, bạn nên ngừng tiêm phòng.</p>

<h1>Tiêm phòng</h1>
<p>Để đảm bảo sức khỏe cho bé, cha mẹ không nên trì hoãn tiêm vì dịch bệnh</p>

<h1>3. Những loại vắc xin phụ huynh có thể trì hoãn tạm thời</h1>
<p>Vì hiện tại đang là thời điểm đặc biệt, để tránh nguy cơ lây nhiễm chéo, mẹ không nên cho trẻ đến những nơi đông người và có thể hoãn lịch tiêm chủng đối với một số loại vắc xin.</p>

<p>Vắc-xin viêm não mô cầu AC. Bệnh viêm não do não mô cầu có thể trì hoãn tạm thời vì hiện tại không phải vụ dịch và theo quy luật 3 năm mới có dịch 1 lần. Trẻ 2 tuổi bắt đầu được tiêm và cứ 3 năm nhắc lại một lần</p>
<p>Thương hàn: bệnh này lây qua đường ăn uống, nếu giữ vệ sinh ăn chín uống nước đảm bảo là có thể hạn chế được bệnh này. Vaccin này nhắc lại 3 năm 1 lần</p>
<p>Viêm gan A: Bệnh lây qua đường ăn uống nên trong giai đoạn này có thể tạm hoãn</p>
<p>Vắc-xin HPV: Vaccin phòng ung thư cổ tử cung tuổi tiêm lý tưởng từ 9 đến 13 tuổi, như vậy việc trì hoãn tạm thời trong thời điểm này không ảnh hưởng đến trẻ</p>

<p>Nếu phụ huynh không chắc chắn về loại vắc-xin có thể được hoãn lại, hãy tham khảo ý kiến của sở y tế hoặc các trung tâm phòng chống dịch bệnh tại địa phương.</p>', N'binhntthe176420@fpt.edu.vn', 3, CAST(N'2023-01-01T00:00:00.000' AS DateTime), CAST(N'2023-01-01T00:00:00.000' AS DateTime), 61477, N'data:image/jpeg;base64,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', N'Bài viết được tư vấn chuyên môn bởi Bác sĩ chuyên khoa II Quách Nguyễn Thu Thủy - Trung tâm Nhi - Bệnh viện Đa khoa Quốc tế Vinmec Times City

Trong thời điểm dịch bệnh Covid-19 vẫn đang diễn ra phức tạp, nhiều cha mẹ băn khoăn không biết có nên đưa con đến bệnh viện để tiêm phòng không. Loại vắc xin nào có thể được trì hoãn lịch tiêm, loại vắc xin nào không thể?', N'khong-ngo-lai-gap-phai-thang-lieu')
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug]) VALUES (5, N'chồn xanh lè', N'tuyển tập Doraemon ', N'hoangmeo1905@gmail.com', 1, CAST(N'2023-01-01T00:00:00.000' AS DateTime), CAST(N'2023-01-01T00:00:00.000' AS DateTime), 9199, N'https://th.bing.com/th/id/OIP.8zV250gDALoI7JIetba5BwAAAA?pid=ImgDet&rs=1', NULL, N'chon-xanh-le')
GO
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (1, N'Hoạt động bệnh viện', NULL, N'hoat-dong-benh-vien')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (2, N'Thành tựu chuyên môn', NULL, N'thanh-tuu-chuyen-mon')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (3, N'Câu chuyện khách hàng', NULL, N'cau-chuyen-khach-hang')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (4, N'Thông tin sức khoẻ', NULL, N'thong-tin-suc-khoe')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (5, N'Hoạt động đào tạo', NULL, N'hoat-dong-dao-tao')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (6, N'Hỏi đáp bác sĩ', NULL, N'hoi-dap-bac-si')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (7, N'Hợp tác quốc tế', NULL, N'hop-tac-quoc-te')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (8, N'Sản phụ khoa và Hỗ trợ sinh sản', 4, N'san-phu-khoa-va-ho-tro-sinh-san')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (9, N'Nhi', 4, N'nhi')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (10, N'Sức khoẻ tổng quát', 4, N'suc-khoe-tong-quat')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (11, N'Tế bào gốc - Công nghệ gen', 4, N'te-bao-goc-cong-nghe-gen')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (12, N'Dịch Covid 19', 4, N'dich-covid-19')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (13, N'Dinh dưỡng', 4, N'dinh-duong')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (14, N'Sống khoẻ', 4, N'song-khoe')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (15, N'Làm đẹp', 4, N'lam-dep')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (16, N'Thông tin dược', 4, N'thong-tin-duoc')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (17, N'Tim mạch', 5, N'tim-mach')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (18, N'Ung bướu - Xạ trị', 5, N'ung-buou-xa-tri')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (19, N'Tiêu hoá - Gan mật', 5, N'tieu-hoa-gan-mat')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (20, N'Sản phụ khoa và Hỗ trợ sinh sản', 5, N'san-phu-khoa-va-ho-tro-sinh-san')
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [slug]) VALUES (21, N'Cơ xương khớp', 5, N'co-xuong-khop')
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
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (1, N'Trang chủ', NULL, 1, N'user-home', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (2, N'Chi nhánh', NULL, 1, N'#', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (3, N'Dịch vụ', NULL, 1, N'#', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (4, N'Bác sĩ', NULL, 1, N'user-list-all-doctor', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (5, N'Tin tức', NULL, 1, N'user-news', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (6, N'Chi nhánh Hà Nội', 2, NULL, N'user-branch-detail?branchId=1', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (7, N'Chi nhánh Nha Trang', 2, NULL, N'user-branch-detail?branchId=2', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (8, N'Chi nhánh TP HCM', 2, NULL, N'user-branch-detail?branchId=3', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (9, N'Nội tổng quát', 3, NULL, N'user-service-detail?serviceId=1', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (10, N'Tim mạch', 3, NULL, N'user-service-detail?serviceId=2', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (11, N'Tiêu hoá', 3, NULL, N'user-service-detail?serviceId=3', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (12, N'Truyền nhiễm', 3, NULL, N'user-service-detail?serviceId=4', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (13, N'Nội tiết', 3, NULL, N'user-service-detail?serviceId=5', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (14, N'Hen - Dị ứng miễn dịch', 3, NULL, N'user-service-detail?serviceId=6', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (15, N'Thần kinh', 3, NULL, N'user-service-detail?serviceId=7', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (16, N'Hô hấp', 3, NULL, N'user-service-detail?serviceId=8', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (17, N'Tiết niệu', 3, NULL, N'user-service-detail?serviceId=9', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (18, N'Đa khoa', 3, NULL, N'user-service-detail?serviceId=10', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (19, N'Dinh dưỡng', 3, NULL, N'user-service-detail?serviceId=11', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (20, N'Bệnh lý tuyến giáp', 3, NULL, N'user-service-detail?serviceId=12', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (21, N'Ngoại chấn thương chỉnh hình', 3, NULL, N'user-service-detail?serviceId=13', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (22, N'Phẫu thuật chỉnh hình', 3, NULL, N'user-service-detail?serviceId=14', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (23, N'Ngoại lồng ngực', 3, NULL, N'user-service-detail?serviceId=15', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (24, N'Nhi', 3, NULL, N'user-service-detail?serviceId=16', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (25, N'Sơ sinh', 3, NULL, N'user-service-detail?serviceId=17', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (26, N'Sản phụ khoa', 3, NULL, N'user-service-detail?serviceId=18', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (27, N'FPT University, Hoa Lac, Thach That, Ha Noi', NULL, 4, NULL, N'assets/client/images/map-icon.png')
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (28, N'medicare1733@gmail.com', NULL, 4, NULL, N'assets/client/images/mail-icon.png')
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (29, N'(+84) 123 456 789', NULL, 4, NULL, N'assets/client/images/call-icon.png')
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (30, N'Wikipedia', NULL, 5, N'https://www.wikipedia.org/', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (31, N'YouMed', NULL, 5, N'https://youmed.vn/', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (32, N'VinMec', NULL, 5, N'https://www.vinmec.com/', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (33, N'Facebook', NULL, 6, N'https://www.facebook.com/', N'assets/client/images/fb-icon.png')
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (34, N'Instagram', NULL, 6, N'https://www.instagram.com/', N'assets/client/images/instagram-icon.png')
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (35, N'LinkedIn', NULL, 6, N'https://www.linkedin.com/', N'assets/client/images/linkedin-icon.png')
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (36, N'Chỉnh sửa hồ sơ', NULL, 8, N'edit-user-profile', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (37, N'Danh sách cuộc hẹn', NULL, 8, N'user-appointment', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (38, N'Lịch sử thanh toán', NULL, 8, N'user-payment-history', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (39, N'Đăng xuất', NULL, 8, N'user-logout', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (40, N'Hồ sơ', NULL, 9, N'user-profile', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (41, N'Danh sách cuộc hẹn', NULL, 9, N'user-appointment', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (42, N'Lịch sử thanh toán', NULL, 9, N'user-payment-history', NULL)
INSERT [dbo].[SubLevelMenu] ([id], [content], [parentId], [locateId], [href], [icon]) VALUES (43, N'Đăng xuất', NULL, 9, N'user-logout', NULL)
GO
INSERT [dbo].[TopLevelMenu] ([id], [name]) VALUES (1, N'Navigation Bar')
INSERT [dbo].[TopLevelMenu] ([id], [name]) VALUES (2, N'Header Banner')
INSERT [dbo].[TopLevelMenu] ([id], [name]) VALUES (3, N'Main Banner')
INSERT [dbo].[TopLevelMenu] ([id], [name]) VALUES (4, N'Liên hệ với chúng tôi')
INSERT [dbo].[TopLevelMenu] ([id], [name]) VALUES (5, N'Tham khảo')
INSERT [dbo].[TopLevelMenu] ([id], [name]) VALUES (6, N'Kết nối với chúng tôi')
INSERT [dbo].[TopLevelMenu] ([id], [name]) VALUES (7, N'News Category')
INSERT [dbo].[TopLevelMenu] ([id], [name]) VALUES (8, N'User Menu')
INSERT [dbo].[TopLevelMenu] ([id], [name]) VALUES (9, N'Profile Sidebar')
GO
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'1', N'nguyenthitubinh133@gmail.com', N'7150a81293c5344e082eb96f59145503fe7f31593a60f1bc333c9bb4b19a3d975de9de8ae56a6f86cdd76d1dcbd0644c', N'Nguyễn Tú Bình', CAST(N'2003-04-14' AS Date), 1, N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, N'Kinh', N'0358219546', N'assets/client/images/doctor-thumb-01.jpg', NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'10', N'bingdemo@example.com', N'19772918d6eb06a2c8ee321cbfd0bd4636aaf86111d5a7da5f1e5161ec433006768900227b40aa933dbc6673cefd6730', N'Bing Demo', CAST(N'2000-01-01' AS Date), 1, N'123 Bing Street', 1, N'1234567890', N'MED1234567', N'Ethnicity', N'1234567890', N'hehe', CAST(N'2023-10-07T17:12:05.030' AS DateTime))
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'2', N'nguyenvietthu03@gmail.com', N'7150a81293c5344e082eb96f59145503fe7f31593a60f1bc333c9bb4b19a3d975de9de8ae56a6f86cdd76d1dcbd0644c', N'Nguyễn Việt Thu', CAST(N'2003-05-05' AS Date), 1, N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, N'Kinh', N'0358219546', N'assets/client/images/client-img.png', NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'3', N'user', N'7150a81293c5344e082eb96f59145503fe7f31593a60f1bc333c9bb4b19a3d975de9de8ae56a6f86cdd76d1dcbd0644c', N'Nguyễn Tú Bình', CAST(N'2003-05-06' AS Date), 0, N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, NULL, N'0123456789', N'assets/client/images/doctor-thumb-02.jpg', NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'4', N'gnaohuv55', N'19772918d6eb06a2c8ee321cbfd0bd4636aaf86111d5a7da5f1e5161ec433006768900227b40aa933dbc6673cefd6730', N'hehe', CAST(N'2003-05-05' AS Date), 0, N'hehe', 1, N'0', NULL, NULL, N'0123456789', N'assets/client/images/client-img.png', NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'5', N'hoangvhhe176169@fpt.edu.vn', N'19772918d6eb06a2c8ee321cbfd0bd4636aaf86111d5a7da5f1e5161ec433006768900227b40aa933dbc6673cefd6730', N'hoang vu', CAST(N'2003-05-05' AS Date), 1, N'hehe', 1, N'0123', NULL, NULL, NULL, N'assets/client/images/client-img.png', NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) VALUES (N'6', N'hoangmeo1905@gmail.com', N'0b9d7555f10edc1d3d60cac4b9bffde49162ee19f96ac3580b1e99fa8d17c5456f44ff4e4b1b518c099e808300e8e5c8', N'Vu Hoang', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-30T23:07:33.550' AS DateTime))
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Doctor__AB6E616463FC06DE]    Script Date: 10/9/2023 02:13:58 ******/
ALTER TABLE [dbo].[Doctor] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Employee__AB6E6164F9454319]    Script Date: 10/9/2023 02:13:58 ******/
ALTER TABLE [dbo].[Employee] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__User__AB6E61649308F237]    Script Date: 10/9/2023 02:13:58 ******/
ALTER TABLE [dbo].[User] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Appointments]  WITH CHECK ADD FOREIGN KEY([branchId])
REFERENCES [dbo].[Branch] ([id])
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
ALTER TABLE [dbo].[SubLevelMenu]  WITH CHECK ADD FOREIGN KEY([locateId])
REFERENCES [dbo].[TopLevelMenu] ([id])
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD FOREIGN KEY([provinceId])
REFERENCES [dbo].[Province] ([id])
GO
ALTER TABLE [dbo].[Reviews]  WITH CHECK ADD CHECK  (([rating]<=(5)))
GO
/****** Object:  Trigger [dbo].[trg_InsertUser]    Script Date: 10/9/2023 02:13:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[trg_InsertUser]
ON [hehe1].[dbo].[User]
AFTER INSERT
AS
BEGIN
    INSERT INTO [hehe1].[dbo].[FamilyProfile] 
    (
        [email],
        [name],
        [birthDate],
        [gender],
        [address],
        [provinceId],
        [identity],
        [medicalId],
        [ethnic],
        [phone],
        [profilePicture],
        [createdAt],
        [relationId],
        [ownerId]
    )
    SELECT 
        i.[email], 
        i.[name], 
        i.[birthDate], 
        i.[gender], 
        i.[address], 
        i.[provinceId], 
        i.[identity], 
        i.[medicalId], 
        i.[ethnic], 
        i.[phone], 
        i.[profilePicture], 
        i.[createdAt], 
        NULL, -- Assuming relationId is not known at this point
        i.[id] -- ownerId is the same as User's id
    FROM inserted i;
END;
GO
ALTER TABLE [dbo].[User] ENABLE TRIGGER [trg_InsertUser]
GO
USE [master]
GO
ALTER DATABASE [hehe1] SET  READ_WRITE 
GO
