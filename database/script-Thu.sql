-----------------------employee----------------------
--thêm các cột sau cho bảng Employee

	--theo thầy giáo nhắc

	ALTER TABLE Employee ADD CreateBy int;

	ALTER TABLE Employee ADD modifyAt datetime;

	ALTER TABLE Employee ADD modifyBy int;

	--thêm cột xóa

	ALTER TABLE Employee ADD isDelete bit;

--xóa dữ liệu trong newscategory (2 tiêu đề hiển thị bảng employee: quyền và ngày tạo)

delete NewsCategory where id=152

delete NewsCategory where id=153

--thêm dữ liệu cho newscategory (để hiển thị thêm cho employee khi bấm vào xem chi tiết)

INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (198, N'Test category', NULL, NULL, NULL, 7)

INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (199, N'MoreTitleTableEmployee', NULL, NULL, NULL, 18)

INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (200, N'Quyền', 199, NULL, NULL, NULL)

INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (201, N'Ngày tạo', 205, NULL, NULL, NULL)

INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (202, N'Người tạo', 205, NULL, NULL, NULL)

INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (203, N'Ngày chỉnh sửa', 205, NULL, NULL, NULL)

INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (204, N'Người chỉnh sửa', 205, NULL, NULL, NULL)

INSERT [dbo].[NewsCategory] ([id], [name], [parentId], [href], [icon], [locateId]) VALUES (205, N'LoadMoreTitle', NULL, NULL, NULL, 18)

-----------------User-----------------21/10/2023

ALTER TABLE [User] ADD createBy int;

ALTER TABLE [User] ADD modifyAt datetime;

ALTER TABLE [User] ADD modifyBy int;

-----------------Appointment----------21/10/2023

ALTER TABLE appointments ADD createBy int;

ALTER TABLE appointments ADD modifyAt datetime;

ALTER TABLE appointments ADD modifyBy int;

-----------------Review---------------21/10/2023

ALTER TABLE reviews ADD createBy int;

ALTER TABLE reviews ADD modifyAt datetime;

ALTER TABLE reviews ADD modifyBy int;


-------------------------------------23/10/2023

delete NewsCategory where id=166