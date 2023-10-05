GO

CREATE TABLE [dbo].[AdminSidebarMenu](

	[name] [nvarchar](50) NULL,

	[link] [varchar](500) NULL,

	[icon] [nvarchar](50) NULL

) ON [PRIMARY]

GO

INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Trang chủ', N'/admin-employee-home', N'fa fa-dashboard')

INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Nhân viên', N'/admin-list-employee', N'fa fa-user')

INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Bác sĩ', N'/admin-doctors', N'fa fa-user-md')

INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Người dùng', N'/admin-list-user', N'fa fa-wheelchair')

INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Đơn đặt khám', N'/admin-list-appointments', N'fa fa-calendar')

INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Đánh giá', N'/admin-list-review', NULL)

INSERT [dbo].[AdminSidebarMenu] ([name], [link], [icon]) VALUES (N'Tin tức', N'/admin-list-news', NULL)

GO
/****** Object:  Table [dbo].[TitleTable]    Script Date: 04/10/2023 8:39:09 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TitleTable](
	[titleTableEmployee] [nvarchar](50) NULL,
	[titleTableDoctor] [nvarchar](50) NULL,
	[titleTableUser] [nvarchar](50) NULL,
	[titleTableAppointments] [nvarchar](50) NULL,
	[titleTableReviews] [nvarchar](50) NULL,
	[titleTableNews] [nvarchar](50) NULL
) ON [PRIMARY]
GO
INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'ID', N'ID', N'ID', N'ID', N'ID', N'ID')

INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'Email', NULL, N'Ảnh', N'Tên người dùng', N'Tên người dùng', N'Tiêu đề')

INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'Mật khẩu', NULL, N'Email', N'Tên bác sĩ', N'Tên bác sĩ', N'Nội dung')

INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'Chi nhánh', NULL, N'Tên', N'Tên dịch vụ', N'ID đơn hàng', N'Tác giả')

INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'Tên', NULL, N'Ngày sinh', N'Thời gian tạo', N'Điểm đánh giá', N'Phân loại')

INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'Ngày sinh', NULL, N'Giới tính', N'Trạng thái', N'Nội dung', N'Ngày tạo')

INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'Giới tính', NULL, N'Địa chỉ', N'Hành động', N'Ngày tạo', N'Lần thay đổi cuối')

INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'Địa chỉ', NULL, N'Tỉnh', NULL, N'Hành động', N'Số người xem')

INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'Nơi làm việc', NULL, N'Số CCCD', NULL, NULL, N'Ảnh')

INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'Tỉnh', NULL, N'Số BHYT', NULL, NULL, N'Hành động')

INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'Điện thoại', NULL, N'Dân tộc', NULL, NULL, NULL)

INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'Dân tộc', NULL, N'Điện Thoại', NULL, NULL, NULL)

INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'Quyền', NULL, N'Ngày tạo', NULL, NULL, NULL)

INSERT [dbo].[TitleTable] ([titleTableEmployee], [titleTableDoctor], [titleTableUser], [titleTableAppointments], [titleTableReviews], [titleTableNews]) VALUES (N'Ngày tạo', NULL, N'Hành động', NULL, NULL, NULL)

GO