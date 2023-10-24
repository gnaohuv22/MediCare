USE [master]
GO
/****** Object:  Database [hehe1]    Script Date: 24/10/2023 2:06:46 pm ******/
CREATE DATABASE [hehe1]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'hehe1', FILENAME = N'D:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\hehe1.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'hehe1_log', FILENAME = N'D:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\hehe1_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [hehe1] SET COMPATIBILITY_LEVEL = 160
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
ALTER DATABASE [hehe1] SET AUTO_CLOSE ON 
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
ALTER DATABASE [hehe1] SET RECOVERY SIMPLE 
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
ALTER DATABASE [hehe1] SET QUERY_STORE = ON
GO
ALTER DATABASE [hehe1] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [hehe1]
GO
/****** Object:  Table [dbo].[AcademicRank]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AcademicRank](
	[id] [int] NOT NULL,
	[name] [nvarchar](255) NULL,
	[wage] [float] NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AccessLog]    Script Date: 24/10/2023 2:06:46 pm ******/
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
/****** Object:  Table [dbo].[Appointments]    Script Date: 24/10/2023 2:06:46 pm ******/
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
	[symptoms] [nvarchar](max) NULL,
	[profileId] [int] NULL,
	[createBy] [int] NULL,
	[modifyAt] [datetime] NULL,
	[modifyBy] [int] NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Awards]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Awards](
	[id] [varchar](255) NOT NULL,
	[doctorId] [varchar](255) NULL,
	[description] [nvarchar](max) NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Banner]    Script Date: 24/10/2023 2:06:46 pm ******/
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
/****** Object:  Table [dbo].[BannerDetails]    Script Date: 24/10/2023 2:06:46 pm ******/
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
/****** Object:  Table [dbo].[BillingHistory]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BillingHistory](
	[id] [int] NOT NULL,
	[appointmentId] [int] NULL,
	[totalCash] [float] NULL,
	[createdAt] [time](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Branch]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Branch](
	[id] [int] NOT NULL,
	[name] [nvarchar](255) NULL,
	[description] [nvarchar](max) NULL,
	[locateId] [int] NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CancelledRequest]    Script Date: 24/10/2023 2:06:46 pm ******/
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
/****** Object:  Table [dbo].[Certificate]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Certificate](
	[id] [int] NOT NULL,
	[name] [nvarchar](255) NULL,
	[wage] [float] NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CertificateDoctor]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CertificateDoctor](
	[certId] [int] NOT NULL,
	[doctorId] [varchar](255) NOT NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[certId] ASC,
	[doctorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CurriculumVitae]    Script Date: 24/10/2023 2:06:46 pm ******/
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
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Department]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Department](
	[id] [int] NOT NULL,
	[name] [nvarchar](255) NULL,
	[description] [nvarchar](max) NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Doctor]    Script Date: 24/10/2023 2:06:46 pm ******/
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
/****** Object:  Table [dbo].[DoctorCertificates]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DoctorCertificates](
	[DoctorId] [int] NOT NULL,
	[Certificates] [nvarchar](max) NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[DoctorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoctorSchedule]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DoctorSchedule](
	[id] [bigint] NOT NULL,
	[DoctorID] [varchar](255) NULL,
	[WorkDate] [date] NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoctorService]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DoctorService](
	[doctorId] [varchar](255) NOT NULL,
	[serviceId] [int] NOT NULL,
	[price] [float] NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[doctorId] ASC,
	[serviceId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoctorServices]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DoctorServices](
	[DoctorId] [int] NOT NULL,
	[Services] [nvarchar](max) NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[DoctorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoctorWorkingDay]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DoctorWorkingDay](
	[dayOfWeek] [int] NOT NULL,
	[doctorId] [varchar](255) NOT NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[dayOfWeek] ASC,
	[doctorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoctorWorkingSlot]    Script Date: 24/10/2023 2:06:46 pm ******/
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
/****** Object:  Table [dbo].[Education]    Script Date: 24/10/2023 2:06:46 pm ******/
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
/****** Object:  Table [dbo].[Employee]    Script Date: 24/10/2023 2:06:46 pm ******/
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
	[CreateBy] [int] NULL,
	[modifyAt] [datetime] NULL,
	[modifyBy] [int] NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EmployeeRole]    Script Date: 24/10/2023 2:06:46 pm ******/
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
/****** Object:  Table [dbo].[Experience]    Script Date: 24/10/2023 2:06:46 pm ******/
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
/****** Object:  Table [dbo].[FamilyProfile]    Script Date: 24/10/2023 2:06:46 pm ******/
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
	[ownerId] [varchar](255) NULL,
	[relationId] [int] NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[profileId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[News]    Script Date: 24/10/2023 2:06:46 pm ******/
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
	[type] [int] NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NewsCategory]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NewsCategory](
	[id] [int] NOT NULL,
	[name] [nvarchar](max) NULL,
	[parentId] [int] NULL,
	[href] [nvarchar](max) NULL,
	[icon] [nvarchar](max) NULL,
	[locateId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Province]    Script Date: 24/10/2023 2:06:46 pm ******/
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
/****** Object:  Table [dbo].[Relationship]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Relationship](
	[id] [int] NOT NULL,
	[name] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ResearchPapers]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ResearchPapers](
	[id] [varchar](255) NOT NULL,
	[doctorId] [varchar](255) NULL,
	[description] [nvarchar](max) NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Reviews]    Script Date: 24/10/2023 2:06:46 pm ******/
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
	[createBy] [int] NULL,
	[modifyAt] [datetime] NULL,
	[modifyBy] [int] NULL,
	[isDelete] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ScheduleDetail]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ScheduleDetail](
	[id] [bigint] NOT NULL,
	[ScheduleID] [bigint] NULL,
	[SlotID] [int] NULL,
	[SlotStatus] [varchar](255) NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ServiceTag]    Script Date: 24/10/2023 2:06:46 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ServiceTag](
	[id] [int] NOT NULL,
	[nametag] [nvarchar](255) NULL,
	[description] [nvarchar](max) NULL,
	[departmentId] [int] NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 24/10/2023 2:06:46 pm ******/
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
	[createBy] [int] NULL,
	[modifyAt] [datetime] NULL,
	[modifyBy] [int] NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[WorkingSlot]    Script Date: 24/10/2023 2:06:46 pm ******/
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
INSERT [dbo].[AcademicRank] ([id], [name], [wage], [isDelete]) VALUES (1, N'Giáo sư', NULL, NULL)
INSERT [dbo].[AcademicRank] ([id], [name], [wage], [isDelete]) VALUES (2, N'Phó giáo sư', NULL, NULL)
GO
INSERT [dbo].[Appointments] ([id], [userId], [doctorId], [serviceId], [plannedAt], [status], [branchId], [createdAt], [symptoms], [profileId], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (1, N'1', N'1', 1, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Appointments] ([id], [userId], [doctorId], [serviceId], [plannedAt], [status], [branchId], [createdAt], [symptoms], [profileId], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (2, N'1', N'1', 2, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Appointments] ([id], [userId], [doctorId], [serviceId], [plannedAt], [status], [branchId], [createdAt], [symptoms], [profileId], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (3, N'3', N'2', 1, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Appointments] ([id], [userId], [doctorId], [serviceId], [plannedAt], [status], [branchId], [createdAt], [symptoms], [profileId], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (4, N'10', N'2', 2, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Appointments] ([id], [userId], [doctorId], [serviceId], [plannedAt], [status], [branchId], [createdAt], [symptoms], [profileId], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (5, N'10', N'1', 1, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Appointments] ([id], [userId], [doctorId], [serviceId], [plannedAt], [status], [branchId], [createdAt], [symptoms], [profileId], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (6, N'10', N'8', 2, CAST(N'2023-10-11T10:00:00.000' AS DateTime), 1, 1, CAST(N'2023-10-11T23:19:18.987' AS DateTime), N'heehe', 19, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[Awards] ([id], [doctorId], [description], [isDelete]) VALUES (N'1', N'1', N'Travel award, ICBMT 2018', NULL)
INSERT [dbo].[Awards] ([id], [doctorId], [description], [isDelete]) VALUES (N'10', N'7', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"', NULL)
INSERT [dbo].[Awards] ([id], [doctorId], [description], [isDelete]) VALUES (N'11', N'8', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"', NULL)
INSERT [dbo].[Awards] ([id], [doctorId], [description], [isDelete]) VALUES (N'12', N'9', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"', NULL)
INSERT [dbo].[Awards] ([id], [doctorId], [description], [isDelete]) VALUES (N'13', N'10', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"', NULL)
INSERT [dbo].[Awards] ([id], [doctorId], [description], [isDelete]) VALUES (N'2', N'1', N'Travel award, ASBMT 2019', NULL)
INSERT [dbo].[Awards] ([id], [doctorId], [description], [isDelete]) VALUES (N'3', N'2', N'Travel award, ASBMT 2019', NULL)
INSERT [dbo].[Awards] ([id], [doctorId], [description], [isDelete]) VALUES (N'4', N'2', N'Travel award, ICBMT 2018', NULL)
INSERT [dbo].[Awards] ([id], [doctorId], [description], [isDelete]) VALUES (N'5', N'2', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"', NULL)
INSERT [dbo].[Awards] ([id], [doctorId], [description], [isDelete]) VALUES (N'6', N'3', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"', NULL)
INSERT [dbo].[Awards] ([id], [doctorId], [description], [isDelete]) VALUES (N'7', N'4', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"', NULL)
INSERT [dbo].[Awards] ([id], [doctorId], [description], [isDelete]) VALUES (N'8', N'5', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"', NULL)
INSERT [dbo].[Awards] ([id], [doctorId], [description], [isDelete]) VALUES (N'9', N'6', N'Huy chương "Vì sự nghiệp chăm sóc sức khỏe Nhân dân"', NULL)
GO
INSERT [dbo].[Banner] ([id], [title], [description], [image]) VALUES (1, N'MediCare', N'là hệ thống bệnh viện lớn do MediGroup đầu tư phát triển nhằm đem lại chất lượng cuộc sống tốt hơn cho người dân ở khắp mọi miền tổ quốc', N'assets/client/images/knowledge-bg.png')
GO
INSERT [dbo].[BannerDetails] ([id], [number], [information], [bannerId]) VALUES (1, N'~10', N'năm kinh nghiệm', 1)
INSERT [dbo].[BannerDetails] ([id], [number], [information], [bannerId]) VALUES (2, N'02', N'bệnh viện được công nhận đạt chuẩn y tế toàn cầu JCI', 1)
INSERT [dbo].[BannerDetails] ([id], [number], [information], [bannerId]) VALUES (3, N'10', N'bệnh viện trên hệ thống ', 1)
INSERT [dbo].[BannerDetails] ([id], [number], [information], [bannerId]) VALUES (4, N'400+', N'bác sĩ, chuyên gia trong & ngoài nước', 1)
INSERT [dbo].[BannerDetails] ([id], [number], [information], [bannerId]) VALUES (5, N'100%', N'tỉ lệ bệnh nhân sống sót sau khi khám', 1)
GO
INSERT [dbo].[BillingHistory] ([id], [appointmentId], [totalCash], [createdAt]) VALUES (1, 4, 1000000, NULL)
INSERT [dbo].[BillingHistory] ([id], [appointmentId], [totalCash], [createdAt]) VALUES (2, 5, 30000000, NULL)
GO
INSERT [dbo].[Branch] ([id], [name], [description], [locateId], [isDelete]) VALUES (1, N'Vinmec Hà Nội', NULL, 1, NULL)
INSERT [dbo].[Branch] ([id], [name], [description], [locateId], [isDelete]) VALUES (2, N'Vinmec TP Hồ Chí Minh', NULL, 2, NULL)
INSERT [dbo].[Branch] ([id], [name], [description], [locateId], [isDelete]) VALUES (3, N'Vinmec Nha Trang', NULL, 3, NULL)
GO
INSERT [dbo].[Certificate] ([id], [name], [wage], [isDelete]) VALUES (1, N'Bác sĩ ', NULL, NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage], [isDelete]) VALUES (2, N'Bác sĩ chuyên khoa II', NULL, NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage], [isDelete]) VALUES (3, N'Thạc sĩ', NULL, NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage], [isDelete]) VALUES (4, N'Bác sĩ cao cấp ', NULL, NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage], [isDelete]) VALUES (5, N'Bác sĩ nội trú', NULL, NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage], [isDelete]) VALUES (6, N'Tiến sĩ', NULL, NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage], [isDelete]) VALUES (7, N'Bác sĩ chuyên khoa I', NULL, NULL)
INSERT [dbo].[Certificate] ([id], [name], [wage], [isDelete]) VALUES (8, N'Cử nhân', NULL, NULL)
GO
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (1, N'1', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (1, N'10', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (1, N'11', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (1, N'4', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (1, N'9', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (2, N'10', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (2, N'12', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (2, N'7', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (3, N'1', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (3, N'10', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (3, N'11', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (3, N'8', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (3, N'9', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (4, N'10', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (4, N'12', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (4, N'7', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (5, N'11', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (5, N'8', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (6, N'1', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (6, N'10', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (6, N'12', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (6, N'4', NULL)
INSERT [dbo].[CertificateDoctor] ([certId], [doctorId], [isDelete]) VALUES (6, N'7', NULL)
GO
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear], [isDelete]) VALUES (1, N'Giáo sư Hussein Kassem được đào tạo chuyên sâu về can thiệp tim mạch nâng cao tại các Trung tâm Tim mạch hàng đầu thế giới như Hệ thống Y khoa Cleveland Clinic và Bệnh viện Henry Ford Michigan Hoa Kỳ. Với sự nghiệp 25 năm trong lĩnh vực Can thiệp tim mạch & Nội tim mạch của mình, Giáo sư Hussein Kassem đã thực hiện thành công hơn 4000 ca can thiệp động mạch vành phức tạp, 1000 ca can thiệp bệnh động mạch ngoại biên và hàng trăm ca can thiệp tim cấu trúc và van hai lá. Với những thành tựu chuyên môn ưu tú, ông không chỉ giữ vị trí Giám đốc Trung tâm Tim mạch tại các bệnh viện uy tín tại Ai Cập và UAE, mà còn là thành viên tích cực của Hiệp hội Tim mạch can thiệp Hoa kỳ (SCAI), Trường môn tim mạch Hoa kỳ (ACC) và Hiệp hội Tim mạch Châu Âu (ESC). Ông đã cống hiến hơn 80 bài báo, sách, tạp chí về lĩnh vực Can thiệp – Nội tim mạch cho nền y học thế giới.', NULL, N'2007 - 2012: Giảng viên khoa Tim mạch, Đại học Cairo,', 2007, NULL)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear], [isDelete]) VALUES (2, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2015, NULL)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear], [isDelete]) VALUES (3, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2014, NULL)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear], [isDelete]) VALUES (4, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2016, NULL)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear], [isDelete]) VALUES (5, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2012, NULL)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear], [isDelete]) VALUES (6, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2019, NULL)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear], [isDelete]) VALUES (7, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2014, NULL)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear], [isDelete]) VALUES (8, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2011, NULL)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear], [isDelete]) VALUES (9, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2015, NULL)
INSERT [dbo].[CurriculumVitae] ([id], [introduce], [education], [workHistory], [startYear], [isDelete]) VALUES (10, N'Giáo sư Hussein Kassem gia nhập Bệnh viện Vinmec Times City ở vai trò Giám đốc Trung tâm Tim mạch từ tháng 5 năm 2023. Với nền tảng vững chắc sẵn có của Vinmec Times City về Tim mạch trong những năm vừa qua, bao gồm được được Trường môn tim mạch Hoa kỳ (ACC) công nhận là Trung tâm xuất sắc về Tim mạch đầu tiên tại Đông Nam Á, là thành viên chính thức trong Hệ thống kết nối của Cleveland Clinic - Hệ thống Y khoa đứng đầu nước Mỹ về Tim mạch trong 28 năm liên tiếp, Trung tâm Tim mạch Vinmec Times City sẽ có cơ hội phát triển lên những tầm cao mới về chuyên môn lâm sàng và nghiên cứu học thuật dưới sự dẫn dắt của Giáo sư Hussein Kassem.', NULL, N'02/2015 - 08/2017: Trưởng khoa Tim mạch, Bệnh viện Quốc tế As-Salam, Cairo, Hệ thống Y tế Alameda', 2015, NULL)
GO
INSERT [dbo].[Department] ([id], [name], [description], [isDelete]) VALUES (1, N'Nội', N'Khoa chuyên về chẩn đoán, điều trị và quản lý các bệnh lý nội tiết và lâm sàng trong cơ thể. Đây là nơi tập trung điều trị các bệnh ảnh hưởng đến các cơ quan và hệ thống nội tiết của cơ thể.', NULL)
INSERT [dbo].[Department] ([id], [name], [description], [isDelete]) VALUES (2, N'Ngoại', N'Khoa chuyên về phẫu thuật và điều trị các vấn đề y tế cần can thiệp từ bên ngoài cơ thể, bao gồm cả phẫu thuật cấp cứu và các ca phẫu thuật lập kế hoạch như phẫu thuật chỉnh hình.', NULL)
INSERT [dbo].[Department] ([id], [name], [description], [isDelete]) VALUES (3, N'Nhi', N'Khoa tập trung vào sức khỏe của trẻ em và thiếu niên, bao gồm cả chăm sóc cho trẻ từ lúc mới sinh đến tuổi vị thành niên. Điều này bao gồm kiểm tra sức khỏe định kỳ, điều trị các bệnh lý trẻ em, và tư vấn về chăm sóc sức khỏe cho gia đình.', NULL)
INSERT [dbo].[Department] ([id], [name], [description], [isDelete]) VALUES (4, N'Sản', N'Khoa quản lý và chăm sóc cho sức khỏe của phụ nữ trong quá trình mang thai, sanh, và sau sinh. Các chuyên gia trong khoa này cung cấp chăm sóc cho bà bầu và bé mới sinh, và điều trị các vấn đề phụ khoa của phụ nữ.', NULL)
GO
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'1', N'doctor@gmail.com', N'123456', N'Nguyễn Thanh Liêm', 1, N'0123456789', 1, 1, 3000, N'Hà Nội', N'../assets/img/doctor-03.jpg', 1, NULL, NULL, 1)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'10', N'doctor9@gmail.com', N'123456', N'Lê Thị An', 2, N'0123456789', 2, NULL, 3000, N'TP Hồ Chí Minh', N'../assets/img/doctor-03.jpg', 1, CAST(N'2023-10-14' AS Date), 1, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'11', N'doctor11@gmail.com', N'194d8aadbc1c73b19449739942929860d61be5bbdc188051176175f6795e4ad2be1f9a7bf5bdfb6c5211748c4dc480b1', N'Lương Thị Phê', 1, N'0916432148', 1, 1, 70000, N'Hà Nội', N'../assets/img/doctor-03.jpg', 1, CAST(N'2023-10-12' AS Date), 2, 0)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'12', N'doctor12@gmail.com', N'8f57c01950d8e46b0a034cf125c81d77197d9a0f4dc1335cbe157b3967bbb89f6b8e07aaa9ce8e54cc9b70fd4cc945a9', N'Lê Thị Phương', 1, N'0916432148', 1, 1, 70000, N'Hà Nội', N'../assets/img/doctor-03.jpg', 1, CAST(N'2023-10-12' AS Date), 1, 0)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'2', N'doctor1@gmail.com', N'123456', N'Phạm Nhật An', 1, N'0123456789', 1, 2, NULL, N'Hà Nội', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'3', N'doctor2@gmail.com', N'123456', N'Phan Quỳnh Lan', 1, N'0123456789', NULL, 8, NULL, N'Hà Nội', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'4', N'doctor3@gmail.com', N'123456', N'Võ Thành Nhân', 2, N'0123456789', 1, 3, NULL, N'TP Hồ Chí Minh', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'5', N'doctor4@gmail.com', N'123456', N'Nguyễn Thanh Hưng', 3, N'0123456789', NULL, 4, NULL, N'Nha Trang', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'6', N'doctor5@gmail.com', N'123456', N'Thái Bằng', 3, N'0123456789', NULL, 7, NULL, N'Nha Trang', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'7', N'doctor6@gmail.com', N'123456', N'Nguyễn Thị Tuyết Minh', 1, N'0123456789', 1, NULL, 3, N'Hà Nội', N'../assets/img/doctor-03.jpg', 1, CAST(N'2023-10-06' AS Date), 1, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'8', N'doctor7@gmail.com', N'123456', N'Đặng Thị Linh', 1, N'0123456789', NULL, 9, NULL, N'Hà Nội', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Doctor] ([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) VALUES (N'9', N'doctor8@gmail.com', N'123456', N'Phạm Vũ Hiệp', 1, N'0123456789', NULL, 10, NULL, N'Hà Nội', NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates], [isDelete]) VALUES (1, NULL, NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates], [isDelete]) VALUES (2, NULL, NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates], [isDelete]) VALUES (3, NULL, NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates], [isDelete]) VALUES (4, N'[{"name":"Bác sĩ "},{"name":"Tiến sĩ"}]', NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates], [isDelete]) VALUES (5, NULL, NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates], [isDelete]) VALUES (6, NULL, NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates], [isDelete]) VALUES (7, NULL, NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates], [isDelete]) VALUES (8, N'[{"name":"Bác sĩ nội trú"},{"name":"Thạc sĩ"}]', NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates], [isDelete]) VALUES (9, N'[{"name":"Bác sĩ "},{"name":"Thạc sĩ"}]', NULL)
INSERT [dbo].[DoctorCertificates] ([DoctorId], [Certificates], [isDelete]) VALUES (10, N'[{"name":"Bác sĩ chuyên khoa II"}]', NULL)
GO
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (1, N'1', CAST(N'2023-10-03' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (2, N'1', CAST(N'2023-10-04' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (3, N'1', CAST(N'2023-10-05' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (4, N'1', CAST(N'2023-10-06' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (5, N'1', CAST(N'2023-10-07' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (6, N'2', CAST(N'2023-10-03' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (7, N'2', CAST(N'2023-10-04' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (8, N'2', CAST(N'2023-10-05' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (9, N'2', CAST(N'2023-10-06' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (10, N'2', CAST(N'2023-10-07' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (11, N'3', CAST(N'2023-10-03' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (12, N'3', CAST(N'2023-10-04' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (13, N'3', CAST(N'2023-10-05' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (14, N'3', CAST(N'2023-10-06' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (15, N'3', CAST(N'2023-10-07' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (16, N'4', CAST(N'2023-10-03' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (17, N'4', CAST(N'2023-10-04' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (18, N'4', CAST(N'2023-10-05' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (19, N'4', CAST(N'2023-10-06' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (20, N'4', CAST(N'2023-10-07' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (21, N'5', CAST(N'2023-10-03' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (22, N'5', CAST(N'2023-10-04' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (23, N'5', CAST(N'2023-10-05' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (24, N'5', CAST(N'2023-10-06' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (25, N'5', CAST(N'2023-10-07' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (26, N'6', CAST(N'2023-10-03' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (27, N'6', CAST(N'2023-10-04' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (28, N'6', CAST(N'2023-10-05' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (29, N'6', CAST(N'2023-10-06' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (30, N'6', CAST(N'2023-10-07' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (31, N'6', CAST(N'2023-10-08' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (32, N'7', CAST(N'2023-10-05' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (33, N'7', CAST(N'2023-10-06' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (34, N'7', CAST(N'2023-10-07' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (35, N'7', CAST(N'2023-10-08' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (36, N'7', CAST(N'2023-10-09' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (37, N'7', CAST(N'2023-10-10' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (38, N'8', CAST(N'2023-10-05' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (39, N'8', CAST(N'2023-10-06' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (40, N'8', CAST(N'2023-10-07' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (41, N'8', CAST(N'2023-10-08' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (42, N'8', CAST(N'2023-10-09' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (43, N'8', CAST(N'2023-10-10' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (44, N'8', CAST(N'2023-10-11' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (45, N'9', CAST(N'2023-10-05' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (46, N'9', CAST(N'2023-10-06' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (47, N'9', CAST(N'2023-10-07' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (48, N'9', CAST(N'2023-10-08' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (49, N'9', CAST(N'2023-10-09' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (50, N'9', CAST(N'2023-10-10' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (51, N'10', CAST(N'2023-10-05' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (52, N'10', CAST(N'2023-10-06' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (53, N'10', CAST(N'2023-10-07' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (54, N'10', CAST(N'2023-10-08' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (55, N'10', CAST(N'2023-10-09' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (56, N'10', CAST(N'2023-10-10' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (57, N'1', CAST(N'2023-10-10' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (58, N'1', CAST(N'2023-10-11' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (59, N'1', CAST(N'2023-10-12' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (60, N'1', CAST(N'2023-10-13' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (61, N'2', CAST(N'2023-10-10' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (62, N'2', CAST(N'2023-10-11' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (63, N'2', CAST(N'2023-10-12' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (64, N'1', CAST(N'2023-10-14' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (65, N'2', CAST(N'2023-10-13' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (66, N'3', CAST(N'2023-10-10' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (67, N'3', CAST(N'2023-10-11' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (68, N'3', CAST(N'2023-10-12' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (69, N'3', CAST(N'2023-10-13' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (70, N'4', CAST(N'2023-10-10' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (71, N'5', CAST(N'2023-10-10' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (72, N'6', CAST(N'2023-10-10' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (73, N'7', CAST(N'2023-10-11' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (74, N'8', CAST(N'2023-10-11' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (75, N'9', CAST(N'2023-10-11' AS Date), NULL)
INSERT [dbo].[DoctorSchedule] ([id], [DoctorID], [WorkDate], [isDelete]) VALUES (76, N'10', CAST(N'2023-10-11' AS Date), NULL)
GO
INSERT [dbo].[DoctorService] ([doctorId], [serviceId], [price], [isDelete]) VALUES (N'10', 12, NULL, NULL)
INSERT [dbo].[DoctorService] ([doctorId], [serviceId], [price], [isDelete]) VALUES (N'4', 2, NULL, NULL)
INSERT [dbo].[DoctorService] ([doctorId], [serviceId], [price], [isDelete]) VALUES (N'7', 2, NULL, NULL)
INSERT [dbo].[DoctorService] ([doctorId], [serviceId], [price], [isDelete]) VALUES (N'8', 2, NULL, NULL)
INSERT [dbo].[DoctorService] ([doctorId], [serviceId], [price], [isDelete]) VALUES (N'9', 5, NULL, NULL)
INSERT [dbo].[DoctorService] ([doctorId], [serviceId], [price], [isDelete]) VALUES (N'9', 12, NULL, NULL)
GO
INSERT [dbo].[DoctorServices] ([DoctorId], [Services], [isDelete]) VALUES (1, NULL, NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services], [isDelete]) VALUES (2, NULL, NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services], [isDelete]) VALUES (3, NULL, NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services], [isDelete]) VALUES (4, N'[{"name":"Tim mạch"}]', NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services], [isDelete]) VALUES (5, NULL, NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services], [isDelete]) VALUES (6, NULL, NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services], [isDelete]) VALUES (7, N'[{"name":"Tim mạch"}]', NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services], [isDelete]) VALUES (8, N'[{"name":"Tim mạch"}]', NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services], [isDelete]) VALUES (9, N'[{"name":"Bệnh lý tuyến giáp"},{"name":"Nội tiết"}]', NULL)
INSERT [dbo].[DoctorServices] ([DoctorId], [Services], [isDelete]) VALUES (10, N'[{"name":"Bệnh lý tuyến giáp"}]', NULL)
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
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt], [CreateBy], [modifyAt], [modifyBy], [isDelete]) VALUES (1, N'hoangvhhe176169@fpt.edu.vn', NULL, 2, N'Hoang Vu', CAST(N'2003-05-05' AS Date), 0, N'ViN', N'VN', 1, N'9999999999', N'VN', 8, CAST(N'2003-05-05T00:00:00.000' AS DateTime), NULL, CAST(N'2023-10-23T13:16:54.343' AS DateTime), 4, 0)
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt], [CreateBy], [modifyAt], [modifyBy], [isDelete]) VALUES (2, N'hoangmeo1905@gmail.com', N'1a60d4c40feacbfb6d97850f3e9ebdc46392708ac7d3b9ebc0da2379a46efc97dafe25de04248a3d124237f9c5ae4eae', 1, N'Vu Huy Hoang', CAST(N'1900-01-01' AS Date), 1, N'53, Group 11, Minh Xuan District', N'VN', 1, N'0916432148', N'VN', 1, CAST(N'2023-09-24T05:09:31.477' AS DateTime), NULL, NULL, NULL, 0)
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt], [CreateBy], [modifyAt], [modifyBy], [isDelete]) VALUES (4, N'thunvhe176252@fpt.edu.vn', N'911d585a04628ded62fd8a41e6126437b441ffbc74e352a47999e9f2461316a1f2d2d1194d5075f603bd2bc966dc8420', 1, N'Thu', CAST(N'2002-01-01' AS Date), 0, N'VN', N'Ha Noi', 1, N'0123456789', N'VN', 2, CAST(N'2023-09-24T05:10:17.993' AS DateTime), NULL, NULL, NULL, 0)
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt], [CreateBy], [modifyAt], [modifyBy], [isDelete]) VALUES (5, N'123@abc.com', NULL, 1, N'Nguyen Viet Thu', CAST(N'2023-10-18' AS Date), 1, N'Hà Nội', N'Hà Nội', 1, N'9999999999', N'Kinh', 3, CAST(N'2023-10-18T15:34:02.657' AS DateTime), NULL, CAST(N'2023-10-23T17:00:42.243' AS DateTime), 4, 0)
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt], [CreateBy], [modifyAt], [modifyBy], [isDelete]) VALUES (6, N'binhntthe176420@fpt.edu.vn', NULL, 1, N'Vũ Thị Bình', CAST(N'2003-07-18' AS Date), 1, N'Hà Nội', N'Hà Nội', 1, N'2222222222', N'Kinh', 5, CAST(N'2023-10-18T17:01:11.913' AS DateTime), NULL, CAST(N'2023-10-23T13:21:02.497' AS DateTime), 4, 0)
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt], [CreateBy], [modifyAt], [modifyBy], [isDelete]) VALUES (7, N'chungvnhe170688@fpt.edu.vn', N'1ffaf6ce5325b9ee967309ef46402e87ec2f3eb4e7a2e60e47b708576233a18503a079f45f7d12ddae6cef8f1ca91e75', 1, N'Vũ Ngọc Chung', CAST(N'2003-07-18' AS Date), 0, N'Hà Nội', N'Hà Nội', 1, N'2222222222', N'Kinh', 2, CAST(N'2023-10-18T17:05:20.740' AS DateTime), NULL, CAST(N'2023-10-23T17:08:08.770' AS DateTime), 4, 0)
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt], [CreateBy], [modifyAt], [modifyBy], [isDelete]) VALUES (8, N'test', N'test', 1, N'test', CAST(N'2000-01-01' AS Date), 0, N'test', N'test', 1, N'test', N'test', 1, CAST(N'2023-10-18T17:05:20.740' AS DateTime), 1, CAST(N'2023-10-18T17:05:20.740' AS DateTime), 1, 1)
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt], [CreateBy], [modifyAt], [modifyBy], [isDelete]) VALUES (9, N'username@domain.com', N'76ef8a819a64349e5ac05493b4f2a8c34519442b08390959b84d01e645bce6e5f0785ee7d3cd6d3d6bbb46c5150dc5b4', 2, N'Binh', CAST(N'2023-10-21' AS Date), 1, N'Hồ Chí Minh', N'Hồ Chí Minh', 2, N'9999999999', N'Kinh', 7, CAST(N'2023-10-21T13:55:16.680' AS DateTime), 4, NULL, NULL, 0)
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt], [CreateBy], [modifyAt], [modifyBy], [isDelete]) VALUES (10, N'1234@abc.com', N'8646f3b6a4ebf9e1705bb33be67ded2c7212cde2e7241e4b93ebeadec0468a397ae7d75f970d9c17688e869ef6b3e3af', 3, N'Binh', CAST(N'2023-10-23' AS Date), 1, N'Hồ Chí Minh', N'Hồ Chí Minh', 3, N'9999999999', N'Kinh', 4, CAST(N'2023-10-23T21:17:32.023' AS DateTime), 4, NULL, NULL, 0)
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt], [CreateBy], [modifyAt], [modifyBy], [isDelete]) VALUES (11, N'12345@abc.com', N'd44ff56912d1399edf210d85a2b2a2b2bf9916c3398e8a5b55ba5a4eda93a008143a29614de89801829c2c359ad8ec4b', 3, N'Binh', CAST(N'2023-10-23' AS Date), 1, N'Hồ Chí Minh', N'Hồ Chí Minh', 3, N'0123456788', N'Kinh', 3, CAST(N'2023-10-23T21:22:56.083' AS DateTime), 4, NULL, NULL, 0)
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt], [CreateBy], [modifyAt], [modifyBy], [isDelete]) VALUES (12, N'123456@abc.com', N'a01542ca16e8df2913b7071db6f633050dc964cf4c082f45e27c3fae38d5a5bb3c872546390f87dc3707a98a9e33390b', 3, N'Binh', CAST(N'2023-10-23' AS Date), 1, N'Hồ Chí Minh', N'Hồ Chí Minh', 3, N'0123456788', N'Kinh', 3, CAST(N'2023-10-23T21:24:50.757' AS DateTime), 4, NULL, NULL, 0)
INSERT [dbo].[Employee] ([id], [email], [password], [branchId], [name], [birthDate], [gender], [address], [workplace], [provinceId], [phone], [ethnic], [roleId], [createAt], [CreateBy], [modifyAt], [modifyBy], [isDelete]) VALUES (13, N'12346@abc.com', N'f1ccbc9c2acf12332644c3d666806da11fe0b91fd8c31ad293445ad2360f13bac3080eb4537b0eb34f0156929b5fdc97', 3, N'Binh', CAST(N'2023-10-23' AS Date), 1, N'Hồ Chí Minh', N'Hồ Chí Minh', 3, N'0123456788', N'Kinh', 3, CAST(N'2023-10-23T21:25:07.193' AS DateTime), 4, CAST(N'2023-10-24T13:05:22.530' AS DateTime), 4, 0)
GO
INSERT [dbo].[EmployeeRole] ([id], [role]) VALUES (1, N'non-Admin')
INSERT [dbo].[EmployeeRole] ([id], [role]) VALUES (2, N'Admin')
INSERT [dbo].[EmployeeRole] ([id], [role]) VALUES (3, N'Xử lí nhân viên')
INSERT [dbo].[EmployeeRole] ([id], [role]) VALUES (4, N'Xử lí người dùng')
INSERT [dbo].[EmployeeRole] ([id], [role]) VALUES (5, N'Xử lí đơn hàng')
INSERT [dbo].[EmployeeRole] ([id], [role]) VALUES (6, N'Xử lí đánh giá')
INSERT [dbo].[EmployeeRole] ([id], [role]) VALUES (7, N'Xử lí tin tức')
INSERT [dbo].[EmployeeRole] ([id], [role]) VALUES (8, N'Xử lí bác sĩ')
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

INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (2, N'binhntthe176420@fpt.edu.vn4', N'bslkj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-29T15:41:15.863' AS DateTime), N'11', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (3, N'user1@gma.sg', N'biaed', CAST(N'2000-05-02' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-09-29T15:53:14.337' AS DateTime), N'12', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (4, N'emial', N'ge', CAST(N'2000-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-09-30T22:20:53.960' AS DateTime), N'13', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (5, N'nk', N'r', CAST(N'5555-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-09-30T22:28:59.837' AS DateTime), N'14', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (6, N'binhntthe176420@fpt.edu.vn5', N'bnir', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-30T22:32:16.807' AS DateTime), N'15', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (7, N'buanv@gmail.po', N'anvpoe ', CAST(N'2022-01-01' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T09:59:36.030' AS DateTime), N'16', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (8, N'binh@gmail.com', N'3', CAST(N'2020-02-02' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:11:16.590' AS DateTime), N'17', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (9, N'binh@gamai.com', N'ag', CAST(N'2020-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:12:04.447' AS DateTime), N'18', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (10, N'binh@mg.com', N'aodfn', CAST(N'2020-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:12:41.213' AS DateTime), N'19', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (11, N'bioan@gam.com', N'fsd', CAST(N'2020-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:13:45.413' AS DateTime), N'20', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (12, N'af@gmail.com', N'fafs', CAST(N'2020-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:16:21.813' AS DateTime), N'21', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (13, N'vano@gm.co', N'05', CAST(N'2020-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:18:54.220' AS DateTime), N'22', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (14, N'gu', N'gưer', CAST(N'2000-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:23:08.107' AS DateTime), N'23', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (15, N'nbah@ga.com', N'falf', CAST(N'2000-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'0123456789', NULL, CAST(N'2023-10-08T13:16:11.257' AS DateTime), N'24', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (16, N'binhnguyen103@gmail.com', N'binh ngueyn', CAST(N'2000-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-09-28T17:13:48.923' AS DateTime), N'7', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (17, N'binhntthe176420@fpt.edu.vn1', N'f', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-28T17:21:38.983' AS DateTime), N'8', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (18, N'binhntthe176420@fpt.edu.vn2', N'b', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-28T17:24:23.820' AS DateTime), N'9', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (19, N'hoangmeo1905@gmail.com', N'Vu Hoang', CAST(N'2003-05-05' AS Date), N'1', NULL, NULL, NULL, NULL, NULL, N'0916432148', NULL, CAST(N'2023-10-11T23:12:49.853' AS DateTime), N'42', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (20, N'binh@gmail.com', N'Nguyễn Ngọc Cường', CAST(N'2003-05-05' AS Date), N'1', NULL, NULL, NULL, NULL, NULL, N'0111222333', NULL, NULL, N'10', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (21, N'hoangvu@gnail.com', N'Vũ Huy Hoàng', CAST(N'2000-01-01' AS Date), N'0', NULL, NULL, NULL, NULL, NULL, N'0235566142', NULL, NULL, N'10', NULL, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (26, N'nguyenthitubinh133@gmail.com', N'Nguyễn Tú Bình', CAST(N'2003-04-14' AS Date), N'1', N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, N'Kinh', N'0358219546', N'assets/client/images/doctor-thumb-01.jpg', NULL, N'1', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (27, N'bingdemo@example.com', N'Bing Demo', CAST(N'2000-01-01' AS Date), N'1', N'123 Bing Street', 1, N'1234567890', N'MED1234567', N'Ethnicity', N'1234567890', N'hehe', CAST(N'2023-10-07T17:12:05.030' AS DateTime), N'10', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (28, N'binhntthe176420@fpt.edu.vn4', N'bslkj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-29T15:41:15.863' AS DateTime), N'11', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (29, N'user1@gma.sg', N'biaed', CAST(N'2000-05-02' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-09-29T15:53:14.337' AS DateTime), N'12', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (30, N'emial', N'ge', CAST(N'2000-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-09-30T22:20:53.960' AS DateTime), N'13', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (31, N'nk', N'r', CAST(N'5555-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-09-30T22:28:59.837' AS DateTime), N'14', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (32, N'binhntthe176420@fpt.edu.vn5', N'bnir', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-30T22:32:16.807' AS DateTime), N'15', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (33, N'buanv@gmail.po', N'anvpoe ', CAST(N'2022-01-01' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T09:59:36.030' AS DateTime), N'16', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (34, N'binh@gmail.com', N'3', CAST(N'2020-02-02' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:11:16.590' AS DateTime), N'17', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (35, N'binh@gamai.com', N'ag', CAST(N'2020-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:12:04.447' AS DateTime), N'18', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (36, N'binh@mg.com', N'aodfn', CAST(N'2020-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:12:41.213' AS DateTime), N'19', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (37, N'nguyenvietthu03@gmail.com', N'Nguyễn Việt Thu', CAST(N'2003-05-05' AS Date), N'1', N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, N'Kinh', N'0358219546', N'assets/client/images/client-img.png', NULL, N'2', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (38, N'bioan@gam.com', N'fsd', CAST(N'2020-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:13:45.413' AS DateTime), N'20', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (39, N'af@gmail.com', N'fafs', CAST(N'2020-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:16:21.813' AS DateTime), N'21', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (40, N'vano@gm.co', N'05', CAST(N'2020-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:18:54.220' AS DateTime), N'22', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (41, N'gu', N'gưer', CAST(N'2000-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:23:08.107' AS DateTime), N'23', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (42, N'nbah@ga.com', N'falf', CAST(N'2000-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'0123456789', NULL, CAST(N'2023-10-08T13:16:11.257' AS DateTime), N'24', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (43, N'user', N'Nguyễn Tú Bình', CAST(N'2003-05-06' AS Date), N'0', N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, NULL, N'0123456789', N'assets/client/images/doctor-thumb-02.jpg', NULL, N'3', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (44, N'gnaohuv55', N'hehe', CAST(N'2003-05-05' AS Date), N'0', N'hehe', 1, N'0', NULL, NULL, N'0123456789', N'assets/client/images/client-img.png', NULL, N'4', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (45, N'hoangvhhe176169@fpt.edu.vn', N'hoang vu', CAST(N'2003-05-05' AS Date), N'1', N'hehe', 1, N'0123', NULL, NULL, NULL, N'assets/client/images/client-img.png', NULL, N'5', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (46, N'hoangmeo1905@gmail.com', N'Vu Hoang', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-30T23:07:33.550' AS DateTime), N'6', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (47, N'binhnguyen103@gmail.com', N'binh ngueyn', CAST(N'2000-05-05' AS Date), N'0', N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-09-28T17:13:48.923' AS DateTime), N'7', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (48, N'binhntthe176420@fpt.edu.vn1', N'f', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-28T17:21:38.983' AS DateTime), N'8', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (49, N'binhntthe176420@fpt.edu.vn2', N'b', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-28T17:24:23.820' AS DateTime), N'9', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (50, N'vietthu002@gmail.com', N'Nguyễn Việt Thu', CAST(N'2000-01-01' AS Date), N'1', N'Hà Nội', 1, N'111111111111', N'1111111111', N'Kinh', N'9999999999', NULL, CAST(N'2023-10-19T16:06:16.320' AS DateTime), N'34', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (51, N'vietthu222@gmail.com', N'Nguyễn Việt Thu', CAST(N'2000-01-01' AS Date), N'1', N'Hà Nội', 1, N'111111111111', N'1111111111', N'Kinh', N'9999999999', NULL, CAST(N'2023-10-19T16:08:47.167' AS DateTime), N'36', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (52, N'vietthuuon@gmail.com', N'Nguyễn Việt Thu', CAST(N'2000-01-01' AS Date), N'1', N'Hà Nội', 1, N'111111111111', N'1111111111', N'Kinh', N'9999999999', NULL, CAST(N'2023-10-19T16:11:44.473' AS DateTime), N'37', 0, NULL)
INSERT [dbo].[FamilyProfile] ([profileId], [email], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [ownerId], [relationId], [isDelete]) VALUES (53, N'hieptran.pa@gmail.com', N'Trần Hiệp', CAST(N'2023-10-19' AS Date), N'1', N'Hà Nội', 1, N'012345678910', N'1111111111', N'Kinh', N'9999999999', NULL, CAST(N'2023-10-19T16:17:30.673' AS DateTime), N'38', 0, NULL)
SET IDENTITY_INSERT [dbo].[FamilyProfile] OFF
GO
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug], [type], [isDelete]) VALUES (1, N'Navigation Bar', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL)
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug], [type], [isDelete]) VALUES (2, N'Header Banner', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL)
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug], [type], [isDelete]) VALUES (3, N'Main Banner', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL)
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug], [type], [isDelete]) VALUES (4, N'Liên hệ với chúng tôi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL)
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug], [type], [isDelete]) VALUES (5, N'Tham khảo', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL)
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug], [type], [isDelete]) VALUES (6, N'Kết nối với chúng tôi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL)
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug], [type], [isDelete]) VALUES (7, N'News Category', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL)
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug], [type], [isDelete]) VALUES (8, N'User Menu', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL)
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug], [type], [isDelete]) VALUES (9, N'Profile Sidebar', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL)
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug], [type], [isDelete]) VALUES (10, N'News Sidebar', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL)
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug], [type], [isDelete]) VALUES (18, N'TitleTable', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL)
INSERT [dbo].[News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle], [slug], [type], [isDelete]) VALUES (19, N'AdminHeader', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (1, N'Hoạt động bệnh viện', 55, N'hoat-dong-benh-vien', NULL, 10)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (2, N'Thành tựu chuyên môn', 55, N'thanh-tuu-chuyen-mon', NULL, 10)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (3, N'Câu chuyện khách hàng', 55, N'cau-chuyen-khach-hang', NULL, 10)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (4, N'Thông tin sức khoẻ', 55, N'thong-tin-suc-khoe', NULL, 10)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (5, N'Hoạt động đào tạo', 55, N'hoat-dong-dao-tao', NULL, 10)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (6, N'Hỏi đáp bác sĩ', 55, N'hoi-dap-bac-si', NULL, 10)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (7, N'Hợp tác quốc tế', 55, N'hop-tac-quoc-te', NULL, 10)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (8, N'Sản phụ khoa và Hỗ trợ sinh sản', 4, N'san-phu-khoa-va-ho-tro-sinh-san', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (9, N'Nhi', 4, N'nhi', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (10, N'Sức khoẻ tổng quát', 4, N'suc-khoe-tong-quat', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (11, N'Tế bào gốc - Công nghệ gen', 4, N'te-bao-goc-cong-nghe-gen', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (12, N'Dịch Covid 19', 4, N'dich-covid-19', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (13, N'Dinh dưỡng', 4, N'dinh-duong', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (14, N'Sống khoẻ', 4, N'song-khoe', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (15, N'Làm đẹp', 4, N'lam-dep', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (16, N'Thông tin dược', 4, N'thong-tin-duoc', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (17, N'Tim mạch', 5, N'tim-mach', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (18, N'Ung bướu - Xạ trị', 5, N'ung-buou-xa-tri', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (19, N'Tiêu hoá - Gan mật', 5, N'tieu-hoa-gan-mat', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (20, N'Sản phụ khoa và Hỗ trợ sinh sản', 5, N'san-phu-khoa-va-ho-tro-sinh-san', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (21, N'Cơ xương khớp', 5, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (22, N'Menu', NULL, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (29, N'(+84) 123 456 789', NULL, NULL, N'assets/client/images/call-icon.png', 4)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (30, N'Wikipedia', NULL, N'https://www.wikipedia.org/', NULL, 5)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (31, N'YouMed', NULL, N'https://youmed.vn/', NULL, 5)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (32, N'VinMec', NULL, N'https://www.vinmec.com/', NULL, 5)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (33, N'Facebook', NULL, N'https://www.facebook.com/', N'assets/client/images/fb-icon.png', 6)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (34, N'Instagram', NULL, N'https://www.instagram.com/', N'assets/client/images/instagram-icon.png', 6)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (35, N'LinkedIn', NULL, N'https://www.linkedin.com/', N'assets/client/images/linkedin-icon.png', 6)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (36, N'Chỉnh sửa hồ sơ', NULL, N'edit-user-profile', NULL, 8)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (37, N'Danh sách cuộc hẹn', NULL, N'user-appointment', NULL, 8)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (38, N'Lịch sử thanh toán', NULL, N'user-payment-history', NULL, 8)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (39, N'Đăng xuất', NULL, N'user-logout', NULL, 8)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (40, N'Hồ sơ', NULL, N'user-profile', NULL, 9)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (41, N'Danh sách cuộc hẹn', NULL, N'user-appointment', NULL, 9)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (42, N'Lịch sử thanh toán', NULL, N'user-payment-history', NULL, 9)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (43, N'Đăng xuất', NULL, N'user-logout', NULL, 9)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (51, N'Trang chủ', NULL, N'user-home', NULL, 1)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (52, N'Chi nhánh', NULL, N'#', NULL, 1)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (53, N'Dịch vụ', NULL, N'#', NULL, 1)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (54, N'Bác sĩ', NULL, N'user-list-all-doctor', NULL, 1)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (55, N'Tin tức', NULL, N'news', NULL, 1)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (56, N'Chi nhánh Hà Nội', 52, N'user-branch-detail?branchId=1', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (57, N'Chi nhánh Nha Trang', 52, N'user-branch-detail?branchId=2', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (58, N'Chi nhánh TP HCM', 52, N'user-branch-detail?branchId=3', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (59, N'Nội tổng quát', 53, N'user-service-detail?serviceId=1', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (60, N'Tim mạch', 53, N'user-service-detail?serviceId=2', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (61, N'Tiêu hoá', 53, N'user-service-detail?serviceId=3', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (62, N'Truyền nhiễm', 53, N'user-service-detail?serviceId=4', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (63, N'Nội tiết', 53, N'user-service-detail?serviceId=5', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (64, N'Hen - Dị ứng miễn dịch', 53, N'user-service-detail?serviceId=6', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (65, N'Thần kinh', 53, N'user-service-detail?serviceId=7', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (66, N'Hô hấp', 53, N'user-service-detail?serviceId=8', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (67, N'Tiết niệu', 53, N'user-service-detail?serviceId=9', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (68, N'Đa khoa', 53, N'user-service-detail?serviceId=10', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (69, N'Dinh dưỡng', 53, N'user-service-detail?serviceId=11', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (70, N'Bệnh lý tuyến giáp', 53, N'user-service-detail?serviceId=12', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (71, N'Ngoại chấn thương chỉnh hình', 53, N'user-service-detail?serviceId=13', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (72, N'Phẫu thuật chỉnh hình', 53, N'user-service-detail?serviceId=14', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (73, N'Ngoại lồng ngực', 53, N'user-service-detail?serviceId=15', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (74, N'Nhi', 53, N'user-service-detail?serviceId=16', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (75, N'Sơ sinh', 53, N'user-service-detail?serviceId=17', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (76, N'Sản phụ khoa', 53, N'user-service-detail?serviceId=18', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (77, N'FPT University, Hoa Lac, Thach That, Ha Noi', NULL, NULL, N'assets/client/images/map-icon.png', 4)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (78, N'medicare1733@gmail.com', NULL, NULL, N'assets/client/images/mail-icon.png', 4)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (136, N'titleTableEmployee', NULL, NULL, NULL, 18)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (137, N'titleTableUser', NULL, NULL, NULL, 18)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (138, N'titleTableAppointments', NULL, NULL, NULL, 18)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (139, N'titleTableReviews', NULL, NULL, NULL, 18)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (140, N'titleTableNews', NULL, NULL, NULL, 18)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (141, N'ID', 136, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (142, N'Email', 136, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (143, N'Chi nhánh', 136, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (144, N'Tên', 136, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (145, N'Ngày sinh', 136, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (146, N'Giới tính', 136, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (147, N'Địa chỉ', 136, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (148, N'Nơi làm việc', 136, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (149, N'Tỉnh', 136, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (150, N'Điện thoại', 136, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (151, N'Dân tộc', 136, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (154, N'ID', 137, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (155, N'Ảnh', 137, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (156, N'Email', 137, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (157, N'Tên', 137, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (158, N'Ngày sinh', 137, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (159, N'Giới tính', 137, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (160, N'Địa chỉ', 137, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (161, N'Tỉnh', 137, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (162, N'Số CCCD', 137, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (163, N'Số BHYT', 137, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (164, N'Dân tộc', 137, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (165, N'Điện Thoại', 137, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (167, N'ID', 138, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (168, N'Tên người dùng', 138, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (169, N'Tên bác sĩ', 138, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (170, N'Tên dịch vụ', 138, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (171, N'Thời gian tạo', 138, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (172, N'Trạng thái', 138, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (173, N'ID', 139, NULL, NULL, NULL)
GO
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (174, N'Tên người dùng', 139, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (175, N'Tên bác sĩ', 139, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (176, N'ID đơn hàng', 139, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (177, N'Điểm đánh giá', 139, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (178, N'Nội dung', 139, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (179, N'Ngày tạo', 139, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (180, N'ID', 140, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (181, N'Tiêu đề', 140, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (182, N'Nội dung', 140, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (183, N'Tác giả', 140, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (184, N'Phân loại', 140, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (185, N'Ngày tạo', 140, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (186, N'Lần thay đổi cuối', 140, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (187, N'Số người xem', 140, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (188, N'Ảnh', 140, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (189, N'AdminSidebarMenu', NULL, NULL, NULL, 19)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (190, N'Trang chủ', 189, N'admin-employee-home', N'fa fa-dashboard', NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (191, N'Nhân viên', 189, N'admin-list-employee', N'fa fa-user', NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (192, N'Bác sĩ', 189, N'admin-doctors', N'fa fa-user-md', NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (193, N'Người dùng', 189, N'admin-list-user', N'fa fa-wheelchair', NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (194, N'Đơn đặt khám', 189, N'admin-list-appointments', N'fa fa-calendar', NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (195, N'Đánh giá', 189, N'admin-list-review', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (196, N'Tin tức', 189, N'admin-list-news', NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (197, N'subtitle', 140, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (198, N'Test category', NULL, NULL, NULL, 7)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (199, N'MoreTitleTableEmployee', NULL, NULL, NULL, 18)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (200, N'Quyền', 199, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (201, N'Ngày tạo', 205, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (202, N'Người tạo', 205, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (203, N'Ngày chỉnh sửa', 205, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (204, N'Người chỉnh sửa', 205, NULL, NULL, NULL)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (205, N'LoadMoreTitle', NULL, NULL, NULL, 18)
INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (1007, N'Đặt khám', NULL, N'user-book-appointment', NULL, 1)
GO
INSERT [dbo].[Province] ([id], [name]) VALUES (1, N'Hà Nội')
INSERT [dbo].[Province] ([id], [name]) VALUES (2, N'TP Hồ Chí Minh')
INSERT [dbo].[Province] ([id], [name]) VALUES (3, N'Nha Trang')
GO
INSERT [dbo].[Relationship] ([id], [name]) VALUES (0, N'Tôi')
INSERT [dbo].[Relationship] ([id], [name]) VALUES (1, N'Bố')
INSERT [dbo].[Relationship] ([id], [name]) VALUES (2, N'Mẹ')
INSERT [dbo].[Relationship] ([id], [name]) VALUES (3, N'Vợ')
INSERT [dbo].[Relationship] ([id], [name]) VALUES (4, N'Chồng')
INSERT [dbo].[Relationship] ([id], [name]) VALUES (5, N'Con')
INSERT [dbo].[Relationship] ([id], [name]) VALUES (6, N'Khác')
GO
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description], [isDelete]) VALUES (N'1', N'1', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

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
', NULL)
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description], [isDelete]) VALUES (N'10', N'10', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

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
', NULL)
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description], [isDelete]) VALUES (N'2', N'2', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

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
', NULL)
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description], [isDelete]) VALUES (N'3', N'3', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

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
', NULL)
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description], [isDelete]) VALUES (N'4', N'4', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

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
', NULL)
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description], [isDelete]) VALUES (N'5', N'5', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

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
', NULL)
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description], [isDelete]) VALUES (N'6', N'6', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

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
', NULL)
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description], [isDelete]) VALUES (N'7', N'7', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

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
', NULL)
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description], [isDelete]) VALUES (N'8', N'8', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

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
', NULL)
INSERT [dbo].[ResearchPapers] ([id], [doctorId], [description], [isDelete]) VALUES (N'9', N'9', N'<p>Bác sĩ Cao cấp Nguyễn Thị Tân Sinh đã tham gia nhiều nghiên cứu khoa học trên các lĩnh vực, trên 30 đề tài đã được công bố trên các tạp chí trong và ngoài nước, trong đó có:</p>

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
', NULL)
GO
INSERT [dbo].[Reviews] ([id], [userId], [doctorId], [appointmentId], [rating], [reviewContent], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (1, N'1', N'1', 1, 4, N'I’ve been using this medical service for over a year now and I’m extremely satisfied. The staff is always friendly and professional, and they go out of their way to make sure I’m comfortable. The facilities are clean and well-maintained. I highly recommend them!', CAST(N'2023-09-24T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[Reviews] ([id], [userId], [doctorId], [appointmentId], [rating], [reviewContent], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (2, N'2', N'1', 2, 5, N'The level of care and attention I received was exceptional. The doctors and nurses were very knowledgeable and took the time to explain everything to me in a way that I could understand. I felt well taken care of throughout my treatment.', CAST(N'2023-09-24T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[Reviews] ([id], [userId], [doctorId], [appointmentId], [rating], [reviewContent], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (3, N'3', N'2', 3, 4, N'I was really impressed with the efficiency of the service. From booking an appointment to getting my results, everything was smooth and quick. This is exactly what you need when you’re not feeling well.', CAST(N'2022-09-22T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (1, 1, 1, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (2, 1, 2, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (3, 1, 3, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (4, 1, 4, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (5, 1, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (6, 1, 6, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (7, 1, 7, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (8, 1, 8, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (9, 1, 9, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (10, 1, 10, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (11, 1, 11, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (12, 1, 12, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (13, 1, 13, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (14, 1, 14, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (15, 1, 15, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (16, 1, 16, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (17, 1, 17, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (18, 1, 18, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (19, 1, 19, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (20, 2, 1, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (21, 2, 2, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (22, 2, 3, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (23, 2, 4, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (24, 2, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (25, 3, 1, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (26, 3, 2, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (27, 3, 3, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (28, 3, 4, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (29, 3, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (30, 4, 1, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (31, 4, 2, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (32, 4, 3, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (33, 4, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (34, 4, 6, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (35, 4, 9, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (36, 5, 1, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (37, 5, 2, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (38, 5, 9, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (39, 5, 10, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (40, 5, 11, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (41, 6, 1, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (42, 6, 2, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (43, 7, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (44, 9, 6, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (45, 9, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (46, 10, 2, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (47, 10, 3, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (48, 11, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (49, 12, 6, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (50, 13, 8, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (51, 14, 8, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (52, 15, 6, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (53, 16, 8, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (54, 17, 6, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (55, 18, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (56, 19, 6, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (57, 20, 8, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (58, 21, 9, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (59, 22, 10, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (60, 23, 15, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (61, 24, 1, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (62, 25, 3, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (63, 26, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (64, 27, 2, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (65, 28, 6, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (66, 29, 8, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (67, 30, 2, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (68, 31, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (69, 32, 6, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (70, 33, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (71, 34, 6, N'0', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (72, 35, 9, N'0', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (73, 36, 4, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (74, 37, 2, N'0', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (75, 38, 9, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (76, 39, 10, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (77, 40, 2, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (78, 41, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (79, 42, 6, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (80, 43, 7, N'0', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (81, 44, 6, N'0', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (82, 45, 9, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (83, 46, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (84, 47, 6, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (85, 48, 9, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (86, 49, 7, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (87, 50, 2, N'0', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (88, 51, 6, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (89, 52, 4, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (90, 53, 4, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (91, 54, 7, N'0', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (92, 55, 6, N'0', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (93, 56, 4, N'0', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (94, 56, 8, N'0', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (95, 8, 1, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (96, 8, 2, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (97, 8, 5, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (98, 8, 6, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (99, 57, 3, N'1', NULL)
INSERT [dbo].[ScheduleDetail] ([id], [ScheduleID], [SlotID], [SlotStatus], [isDelete]) VALUES (100, 57, 2, N'1', NULL)
GO
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (1, N'Nội tổng quát', NULL, 1, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (2, N'Tim mạch', N'', 1, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (3, N'Tiêu hóa', NULL, 1, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (4, N'Truyền nhiễm', NULL, 1, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (5, N'Nội tiết', NULL, 1, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (6, N'Hen - Dị ứng miễn dịch', NULL, 1, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (7, N'Thần kinh', NULL, 1, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (8, N'Hô hấp', NULL, 1, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (9, N'Tiết niệu', NULL, 1, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (10, N'Đa khoa', NULL, 1, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (11, N'Dinh dưỡng', NULL, 1, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (12, N'Bệnh lý tuyến giáp', NULL, 1, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (13, N'Ngoại chấn thương chỉnh hình', NULL, 2, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (14, N'Phẫu thuật chỉnh hình', NULL, 2, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (15, N'Ngoại lồng ngực', NULL, 2, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (16, N'Nhi', NULL, 3, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (17, N'Sơ sinh', NULL, 3, NULL)
INSERT [dbo].[ServiceTag] ([id], [nametag], [description], [departmentId], [isDelete]) VALUES (18, N'Sản phụ khoa', NULL, 4, NULL)
GO
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'1', N'nguyenthitubinh133@gmail.com', NULL, N'Nguyễn Tú Bình', CAST(N'2003-04-14' AS Date), 1, N'Thạch Thất - Hà Nội', 2, N'012345678910', N'1111111111', N'Kinh', N'0358219546', N'assets/client/images/doctor-thumb-01.jpg', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'10', N'bingdemo@example.com', N'19772918d6eb06a2c8ee321cbfd0bd4636aaf86111d5a7da5f1e5161ec433006768900227b40aa933dbc6673cefd6730', N'Bing Demo', CAST(N'2000-01-01' AS Date), 1, N'123 Bing Street', 1, N'1234567890', N'MED1234567', N'Ethnicity', N'1234567890', N'hehe', CAST(N'2023-10-07T17:12:05.030' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'11', N'binhntthe176420@fpt.edu.vn4', N'a24d4e0cff7be0fadf8e4b73e021cb8138e99c9cb4617deb1718c0854d036e75914c6339ce304438b007640efe23e761', N'bslkj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-29T15:41:15.863' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'12', N'user1@gma.sg', N'f8ec3144cca85bd3f625aec3b333e346adb7f640a29b434a6f121ecbe63806f6cecbfb856a1df2c66f4b0e12a70c7509', N'biaed', CAST(N'2000-05-02' AS Date), 0, N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-09-29T15:53:14.337' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'13', N'emial', N'971e8e841fb971f63941073604d56b5b6543b594648244719059d71e9cd71ed954893dd5fc7eb2790d440dbc21a09e23', N'ge', CAST(N'2000-05-05' AS Date), 0, N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-09-30T22:20:53.960' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'14', N'nk', N'51b12ebe34aefa7f398e0de46dc5b65d2294fe98453bb6b6014dc56be0bd6516b7f270611f01bf5cc6cee3b954dc7c03', N'r', CAST(N'5555-05-05' AS Date), 0, N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-09-30T22:28:59.837' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'15', N'binhntthe176420@fpt.edu.vn5', N'69cccee7f8dd581388129c9a1c12aad17edede806528153038d6bffc592187a5c57caba32e2be717f81cf82ff1ea8617', N'bnir', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-30T22:32:16.807' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'16', N'buanv@gmail.po', N'f4fe2afbf0f1e8be7065addc1319ae7d3a3c0447fbeac8fb0bf29bb6511350c0baaae0f7065df6e5bb739b3ca3514e6b', N'anvpoe ', CAST(N'2022-01-01' AS Date), 0, N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T09:59:36.030' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'17', N'binh@gmail.com', N'3e7d1afb06ac40840b43141270381cbc22522780b5881b22e3b06c6da71733c990d271807a6eda8f92e6af05c237a942', N'3', CAST(N'2020-02-02' AS Date), 0, N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:11:16.590' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'18', N'binh@gamai.com', N'c5f83546e0c0d7fe7d24c5c1dab76de1a4fd18cb23686a18923f26ff2c8a5f1b7d4eeae4bb0051e6b63f344885f3ac25', N'ag', CAST(N'2020-05-05' AS Date), 0, N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:12:04.447' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'19', N'binh@mg.com', N'e4047e4e6fabab60a916c639c47ef692dd0ad558599eaa5428cc86eb54dc2e0668e0443406c642ea2b031fea7935808e', N'aodfn', CAST(N'2020-05-05' AS Date), 0, N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:12:41.213' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'2', N'nguyenvietthu03@gmail.com', N'7150a81293c5344e082eb96f59145503fe7f31593a60f1bc333c9bb4b19a3d975de9de8ae56a6f86cdd76d1dcbd0644c', N'Nguyễn Việt Thu', CAST(N'2003-05-05' AS Date), 1, N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, N'Kinh', N'0358219546', N'assets/client/images/client-img.png', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'20', N'bioan@gam.com', N'b471573877655a707698c436a88b1187229c7e46231c1760df39a6ab7ef92e5a281fe514a581a68a2be338eafc9543fc', N'fsd', CAST(N'2020-05-05' AS Date), 0, N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:13:45.413' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'21', N'af@gmail.com', N'3fb40679c5ad6e66030fb8a3065f209274491d167a5ca75c7f5ad519978a944e44520085da87eadbf84c86b3880f9f5f', N'fafs', CAST(N'2020-05-05' AS Date), 0, N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:16:21.813' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'22', N'vano@gm.co', N'66b4f82d45640eb0e49c614df879da58417225de7b3d5d1572752ad6d61469edf1ebf831e45475d9ec72b23f1ef7d1f3', N'05', CAST(N'2020-05-05' AS Date), 0, N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:18:54.220' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'23', N'gu', N'4eeda8d802e4b0786d189efc4b660ffdb67f66605913d27e6bf575e1411009dfc7b2bfda0e89b8667f7224820397915d', N'gưer', CAST(N'2000-05-05' AS Date), 0, N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-10-08T10:23:08.107' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'24', N'nbah@ga.com', N'78df9dedc86f54f9253024bf198442b1302e94495ba54435f8b01a3b36d0a7db2a5da6f896112031a704567ab39be232', N'falf', CAST(N'2000-05-05' AS Date), 0, N'', NULL, NULL, NULL, NULL, N'0123456789', NULL, CAST(N'2023-10-08T13:16:11.257' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'25', N'binhnguyentu@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-10-10T08:03:25.273' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'26', N'test@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-10-10T08:04:19.073' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'27', N'test1@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-10-10T08:12:07.423' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'28', N'test2@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-10-10T08:18:42.530' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'29', N'test3@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-10-10T08:21:47.497' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'3', N'user', N'7150a81293c5344e082eb96f59145503fe7f31593a60f1bc333c9bb4b19a3d975de9de8ae56a6f86cdd76d1dcbd0644c', N'Nguyễn Tú Bình', CAST(N'2003-05-06' AS Date), 0, N'Thạch Thất - Hà Nội', 1, N'0123456789', NULL, NULL, N'0123456789', N'assets/client/images/doctor-thumb-02.jpg', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'30', N'test4@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-10-10T08:33:07.117' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'31', N'test5@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-10-10T08:43:52.627' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'32', N'test6@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-10-10T08:52:42.127' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'33', N'email.@ga.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-10-10T10:01:39.063' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'35', N'binhnguyen@gmaiul.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-10-10T11:45:51.463' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'37', N'vietthuuon@gmail.com', N'98f02ff1ef8ac9b19b3218c089d03b99b88eae686b3b641fc6e81f2559dfe4deb24cb90e2e6c8aa400c9d9b496a9db37', N'Nguyễn Việt Thu', CAST(N'2000-01-01' AS Date), 1, N'Hà Nội', 1, N'111111111111', N'1111111111', N'Kinh', N'9999999999', NULL, CAST(N'2023-10-19T16:11:44.473' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'38', N'hieptran.pa@gmail.com', N'6d6ee73a1818d3fcb30ed22a61cebbc3bdd25f763772978806b9290f0c5b1d906ae34dc0d12b2233eca55b7cb55fe476', N'Trần Hiệp', CAST(N'2023-10-19' AS Date), 1, N'Hà Nội', 1, N'012345678910', N'1111111111', N'Kinh', N'9999999999', NULL, CAST(N'2023-10-19T16:17:30.673' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'4', N'gnaohuv55', N'19772918d6eb06a2c8ee321cbfd0bd4636aaf86111d5a7da5f1e5161ec433006768900227b40aa933dbc6673cefd6730', N'hehe', CAST(N'2003-05-05' AS Date), 0, N'hehe', 1, N'0', NULL, NULL, N'0123456789', N'assets/client/images/client-img.png', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'40', N'bingdemo@example.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'41', N'bingdemo@example.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'42', N'hoangmeo1905@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-10-11T23:12:49.843' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'5', N'hoangvhhe176169@fpt.edu.vn', N'19772918d6eb06a2c8ee321cbfd0bd4636aaf86111d5a7da5f1e5161ec433006768900227b40aa933dbc6673cefd6730', N'hoang vu', CAST(N'2003-05-05' AS Date), 1, N'hehe', 1, N'0123', NULL, NULL, NULL, N'assets/client/images/client-img.png', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'6', N'hoangmeo1905@gmail.com', N'0b9d7555f10edc1d3d60cac4b9bffde49162ee19f96ac3580b1e99fa8d17c5456f44ff4e4b1b518c099e808300e8e5c8', N'Vu Hoang', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-30T23:07:33.550' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'7', N'binhnguyen103@gmail.com', N'3d126370830d19e72f0c9b083c875a8f32c03f04e70e295e7abb163995dda0ce488bac854e3e6e878f0c3c4cc36a0776', N'binh ngueyn', CAST(N'2000-05-05' AS Date), 0, N'', NULL, NULL, NULL, NULL, N'', NULL, CAST(N'2023-09-28T17:13:48.923' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'8', N'binhntthe176420@fpt.edu.vn1', N'6a84a48b5dc547f1c903cc809af61425bdcf2e14f88f23bab616589c78ecdc509ea015cb8502d754baf846b5c7e0b991', N'f', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-28T17:21:38.983' AS DateTime), NULL, NULL, NULL, NULL)
INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt], [createBy], [modifyAt], [modifyBy], [isDelete]) VALUES (N'9', N'binhntthe176420@fpt.edu.vn2', N'2c734d18013ec90752a3e4b29c4d3e01fe48438435487df8388fcfeb70d23ded4cebe335595936bdf054ee8e48f2ae83', N'b', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2023-09-28T17:24:23.820' AS DateTime), NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (1, CAST(N'07:30:00' AS Time), CAST(N'08:00:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (2, CAST(N'08:00:00' AS Time), CAST(N'08:30:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (3, CAST(N'08:30:00' AS Time), CAST(N'09:00:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (4, CAST(N'09:00:00' AS Time), CAST(N'09:30:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (5, CAST(N'09:30:00' AS Time), CAST(N'10:00:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (6, CAST(N'10:00:00' AS Time), CAST(N'10:30:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (7, CAST(N'10:30:00' AS Time), CAST(N'11:00:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (8, CAST(N'11:00:00' AS Time), CAST(N'11:30:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (9, CAST(N'11:30:00' AS Time), CAST(N'12:00:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (10, CAST(N'12:00:00' AS Time), CAST(N'12:30:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (11, CAST(N'12:30:00' AS Time), CAST(N'13:00:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (12, CAST(N'13:00:00' AS Time), CAST(N'13:30:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (13, CAST(N'13:30:00' AS Time), CAST(N'14:00:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (14, CAST(N'14:00:00' AS Time), CAST(N'14:30:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (15, CAST(N'14:30:00' AS Time), CAST(N'15:00:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (16, CAST(N'15:00:00' AS Time), CAST(N'15:30:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (17, CAST(N'15:30:00' AS Time), CAST(N'16:00:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (18, CAST(N'16:00:00' AS Time), CAST(N'16:30:00' AS Time))
INSERT [dbo].[WorkingSlot] ([id], [startTime], [endTime]) VALUES (19, CAST(N'16:30:00' AS Time), CAST(N'17:00:00' AS Time))
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Employee__AB6E616408F68420]    Script Date: 24/10/2023 2:06:47 pm ******/
ALTER TABLE [dbo].[Employee] ADD UNIQUE NONCLUSTERED 
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
ALTER TABLE [dbo].[Appointments]  WITH CHECK ADD FOREIGN KEY([profileId])
REFERENCES [dbo].[FamilyProfile] ([profileId])
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
ALTER TABLE [dbo].[DoctorSchedule]  WITH CHECK ADD FOREIGN KEY([DoctorID])
REFERENCES [dbo].[Doctor] ([id])
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
ALTER TABLE [dbo].[FamilyProfile]  WITH CHECK ADD FOREIGN KEY([relationId])
REFERENCES [dbo].[Relationship] ([id])
GO
ALTER TABLE [dbo].[News]  WITH CHECK ADD FOREIGN KEY([author])
REFERENCES [dbo].[Employee] ([email])
GO
ALTER TABLE [dbo].[News]  WITH CHECK ADD FOREIGN KEY([categoryId])
REFERENCES [dbo].[NewsCategory] ([id])
GO
ALTER TABLE [dbo].[NewsCategory]  WITH CHECK ADD FOREIGN KEY([locateId])
REFERENCES [dbo].[News] ([id])
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
ALTER TABLE [dbo].[ScheduleDetail]  WITH CHECK ADD FOREIGN KEY([ScheduleID])
REFERENCES [dbo].[DoctorSchedule] ([id])
GO
ALTER TABLE [dbo].[ScheduleDetail]  WITH CHECK ADD FOREIGN KEY([SlotID])
REFERENCES [dbo].[WorkingSlot] ([id])
GO
ALTER TABLE [dbo].[ServiceTag]  WITH CHECK ADD FOREIGN KEY([departmentId])
REFERENCES [dbo].[Department] ([id])
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD FOREIGN KEY([provinceId])
REFERENCES [dbo].[Province] ([id])
GO
ALTER TABLE [dbo].[Reviews]  WITH CHECK ADD CHECK  (([rating]<=(5)))
GO
/****** Object:  Trigger [dbo].[trg_InsertUser]    Script Date: 24/10/2023 2:06:47 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[trg_InsertUser]
ON [hehe1].[dbo].[User]
AFTER INSERT
AS
BEGIN
    -- Check if the inserted record has a non-null password
    IF EXISTS (SELECT 1 FROM inserted WHERE [password] IS NOT NULL)
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
            0, -- Set RelationId to 0
            i.[id] -- ownerId is the same as User's id
        FROM inserted i;
    END;
END;
GO
ALTER TABLE [dbo].[User] ENABLE TRIGGER [trg_InsertUser]
GO
USE [master]
GO
ALTER DATABASE [hehe1] SET  READ_WRITE 
GO
