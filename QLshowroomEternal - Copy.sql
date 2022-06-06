Create database QLShowRoomEternal;
use QLShowRoomEternal;

Create table NhanVien(
 MaNV nvarchar(50) primary key not null,
 HoTen nvarchar(50) not null ,
 GioiTinh bit not null,
 SoDT nvarchar(10) not null,
 Email nvarchar (50) not null,
 DiaChi nvarchar(50) not null,
 Luong float not null,
 ThuongHH float not null,
 GhiChu nvarchar(max),
 Hinh nvarchar(200) not null
  )
  
  Create table Userr(
  Username varchar(50) primary key not null,
  MaNV nvarchar(50) foreign key  references NhanVien(MaNV) not null ,
  HoTen  nvarchar(50) not null,
  MatKhau varchar(20) not null,
  Email varchar(50) not null,
  Sodt varchar(20) not null,
  VaiTro bit not null,
  Hinh varchar(100)

  )
  

create table TheLoai (
  MaTL int identity(1,1) primary key not null,
  Ten nvarchar(50) not null,
 
  )
  insert into TheLoai values (N'Ô tô');
    insert into TheLoai values (N'Sản Phẩm');

  Create table SanPham(
  MaSP nvarchar(50) primary key not null,
  TenSP nvarchar(50)   not null ,
  DonGia float not null,
  SoLuong float not null,
  Hang nvarchar(50) not null,
  XuatXu nvarchar(50) not null,
  MauSac nvarchar(50) not null,
  --MaTL int foreign key  references TheLoai(MaTL),
   PhanLoai nvarchar(50) not null,
  Hinh nvarchar(200) not null,
  GhiChu nvarchar(max) not null,
  QRImages nvarchar(100) null
 )
 

  Create table KhachHang(
  MaKH nvarchar(50) primary key not null,
  HoTen nvarchar(50) not null,
  GioiTinh bit not null,
  SoDT nvarchar(10) not null,
  NgaySinh date not null,
  DiaChi nvarchar(200) not null,
  Email nvarchar(200) not null,
  GhiChu nvarchar(max)
  )
  Create table GioHang(
  MaGH int identity primary key not null,
  --MaHDCT int foreign key  references ChiTietHoaDon(MaHDCT),
  MaSP nvarchar(50) references SanPham(MaSP),
  SoLuong float not null,
  DonGia float not null
   )

 
 Create table HoaDon (
 MaHD int identity primary key not null,
 MaGH int foreign key  references GioHang(MaGH) not null,
 TenKH nvarchar(50) ,
 MaNV nvarchar(50) foreign key  references  NhanVien(MaNV) not null,
 NgayLap date,
 TongTien float,
 QRCode nvarchar(100) null
 )
 
 
 
 
 /*  select * from HoaDon
    delete from HoaDon
	drop table GioHang
	drop table HoaDon
	delete from GioHang
   select * from GioHang
   */
  Create table HopDong(
  MaHopDong nvarchar(50) primary key not null,
  TenHopDong nvarchar(50) not null,
  MaSP nvarchar(50) references SanPham(MaSP),
  MaHD int references HoaDon(MaHD),
  MaKH nvarchar(50) references KhachHang(MaKH),
  MaNV nvarchar(50) references NhanVien(MaNV)
  )
  
 
  Create table ChamCong(
  ID int identity primary key,
  HoTen nvarchar(50),
  Ngay1 bit null,
  Ngay2 bit null,
 Ngay3 bit null,
 Ngay4 bit null,
 Ngay5 bit null,
 Ngay6 bit null,
 Ngay7 bit null,
 Ngay8 bit null,
 Ngay9 bit null,
 Ngay10 bit null,
 Ngay11 bit null,
 Ngay12 bit null,
 Ngay13 bit null,
 Ngay14 bit null,
 Ngay15 bit null,
 Ngay16 bit null,
  Ngay17 bit null,
 Ngay18 bit null,
 Ngay19 bit null,
 Ngay20 bit null,
 Ngay21 bit null,
 Ngay22 bit null,
 Ngay23 bit null,
 Ngay24 bit null,
 Ngay25 bit null,
 Ngay26 bit null,
  Ngay27 bit null,
 Ngay28 bit null,
 Ngay29 bit null,
 Ngay30 bit null,
 Ngay31 bit null

  )

  select * from ChamCong


  Insert into NhanVien values(N'NV001',N'Hoàng Văn Tú',1,N'0353294111',N'tuhvps17799@fpt.edu.vn',N'17 Quang Trung,Gò Vấp',1000,0.1,N'thưởng 10 đô',N'tu.jpg');
  Insert into NhanVien values
  (N'NV002',N'Dương Thanh Phương',1,N'0353294121',N'phuongdtps17651@fpt.edu.vn',N'17 Nguyễn Đức Thọ, Gò Vấp',1000,0.1,N'thưởng 10 đô',N'phuong.jpg'),
  (N'NV003',N'Nguyễn Thế Thọ',1,N'0353294165',N'thontps17709@fpt.edu.vn',N'1 Nguyễn  XX , Quận 1',1000,0.1,N'thưởng 1000000 đô',N'tho.jpg'),
  (N'NV004',N'Trần Văn Phú',0,N'0345674111',N'phutvps1779x@fpt.edu.vn',N'1 Nguyễn Ngọc Vân,Gò Vấp',1000,0.1,N'thưởng 10 đô',N'phu.jpg'),
  (N'NV005',N'Hoàng Văn A',1,N'0315474144',N'Ahvps17799@fpt.edu.vn',N'Nguyễn XX,Gò Vấp',1000,0.1,N'thưởng 10 đô',N'nam1.jpg'),
  (N'NV006',N'Nguyễn Thị Hoàng',0,N'0353254671',N'trps17799@fpt.edu.vn',N'17  Nguyễn Huệ, Quận 12',1000,0.1,N'thưởng 10 đô',N'nam2.jpg'),
  (N'NV007',N'Dương Quốc Huy',1,N'036784111',N'huy@gmail.com',N'Dương Thị Mười, quận 12',1000,0.1,N'thưởng 10 đô',N'nam3.jpg'),
  (N'NV008',N'Hồ Hoàng Phú',1,N'0353296521',N'phu@gmail.com',N'Lê Đức Thọ, Gò Vấp',1000,0.1,N'thưởng 10 đô',N'phu.jpg'),
  (N'NV009',N'Nguyễn Thị Kim Ngân',0,N'035324521',N'ngan@gmail.com',N'17 Quang Trung,Gò Vấp',1000,0.1,N'thưởng 10 đô',N'ngan.jpg'),
  (N'NV010',N'Trần Thị Kim Dung',0,N'031239875',N'dung@gmail.com',N'182 Dương Quảng Hàm, Quận 2',1000,0.1,N'thưởng 10 đô',N'nu1.jpg'),
  (N'NV011',N'Trần Đức Quang',1,N'0352347511',N'Quang@gmail.com',N'17 Quang Trung,Gò Vấp',1000,0.1,N'thưởng 10 đô',N'nu2.jpg');
  select * from NhanVien;

  Insert into Userr values ('TrungHT',N'NV001',N'Hoàng Tuấn Trung','trung123', 'trunghtps17777@fpt.edu.vn', '0123456789',1,N'trung.jpg'),
  ('TuHV',N'NV002',N'Hoàng Văn Tú','tu123', 'tuhvps17799@fpt.edu.vn', '0124567241',0,N'tu.jpg'),
  ('PhuongDT',N'NV003',N'Dương Thanh Phương','phuong123','phuongdtps17651@fpt.edu.vn', '0129876543',0,N'phuong.jpg');
  Insert into Userr values ('ThoNT',N'NV004',N'Nguyễn Thế Thọ','tho123','thont1761@fpt.edu.vn', '0129812343',0,N'tho.jpg');

  Select * from Userr;


  Insert into KhachHang values (N'KH001',N'Nguyễn Văn Cường',1, N'0123456789',N'05/29/2000',N'18 Nguyễn Văn Cừ, Quận 2',N'Cuong@gmail.com',N'Cuong');
  Insert into KhachHang values (N'KH002',N'Nguyễn Duy Tân',1, N'0789456123',N'10/08/2002',N'18 Xô Viết Nghệ Tĩnh, Quận 3',N'Tân@gmail.com',N'Tân');
Insert into KhachHang values (N'KH003',N'Hoàng Anh Tuấn',1, N'0147852369',N'01/2/2002',N'19 Lê Duẩn, Quận 1',N'Tuan@gmail.com',N'Tan');
Insert into KhachHang values (N'KH004',N'Lê Duy Thái',1, N'0369852147',N'02/16/2002',N'20 Phan Văn Trị, Quận Gò Vấp',N'Thai@gmail.com',N'Thai');
Insert into KhachHang values (N'KH005',N'Phạm Minh Luân',1, N'0258741369',N'03/19/2002',N'21 Xa Lộ Hà Nội, Quận Thủ Đức',N'Luan@gmail.com',N'Luan');
Insert into KhachHang values (N'KH006',N'Nguyễn Thanh Ngân',0, N'0159784236',N'05/27/1996',N'22 Điện Biên Phủ, Quận 2',N'Ngan@gmail.com',N'Ngan');
Insert into KhachHang values (N'KH007',N'Nguyễn Đăng Khoa',1, N'0369852147',N'08/28/2001',N'23 Đào Duy Từ, Quận Phú Nhuận',N'Khoa@gmail.com',N'Khoa');
Insert into KhachHang values (N'KH008',N'Nguyễn Thị Ngọc',0, N'0852741963',N'07/16/1998',N'24 Nguyễn Thị Minh Khai, Quận 1',N'Ngoc@gmail.com',N'Ngoc');
Insert into KhachHang values (N'KH009',N'Nguyễn Mai Thảo',0, N'0852741963',N'05/30/1999',N'25 Phạm Văn Đồng, Quận Thủ Đức',N'Thao@gmail.com',N'Thao');
Insert into KhachHang values (N'KH010',N'Nguyễn Thị Mai',0, N'0159874236',N'12/16/1997',N'26 Bà Huyên Thanh Quan, Quận 3',N'Mai@gmail.com',N'Mai');
  select * from KhachHang;


  Insert into SanPham values (N'SP001', N'Mercedes GLC 300', 45990, 5, N'Mercedes', N'Đức', N'Black pink', N'Ô tô', N'mercedesglc300.jpg', N'Mercedes GLC giá 5 tỏi',N'5864635046.jpg');
  Insert into SanPham values (N'SP002', N'Honda Civic Hatchback', 19900, 5, N'Honda', N'Nhật Bản', N'White',  N'Ô tô', N'hondacivichatchback.jpg', N'Honda Civic Hatchback giá xịn',N'7787580672.jpg');
  Insert into SanPham values (N'SP003', N'BMW M3 Gets All-Wheel', 59990, 5, N'BMW', N'Đức', N'black - blue',  N'Ô tô', N'bmwm3.jpg', N'BMW giá xịn',N'5661838413.jpg');
  Insert into SanPham values (N'SP004', N'BMW 507 Wallpapes', 34590, 5, N'BMW', N'Đức', N'Blue',  N'Ô tô', N'bmw507.jpg', N'BMW giá xịn',N'7773568161.jpg');
  select * from SanPham
  delete from SanPham
  Insert into SanPham values (N'SP005', N'Mercedes-Benz E 350', 44790, 5, N'Mercedes', N'Đức', N'Yellow',  N'Ô tô', N'mercedes3350.jpg', N'Mercedes giá xịn',N'5487467426.jpg');
  Insert into SanPham values (N'SP006', N'BMW X7', 90000, 3, N'BMW', N'Đức', N'Grey', N'Ô tô', N'bmwx7.jpg', N'BMW giá xịn',N'1638534644.jpg');
  Insert into SanPham values (N'SP007', N'Vinfast Lux A2.0', 49900,5, N'Vinfast', N'Việt Nam', N'Black',  N'Ô tô', N'vinluxa2_0.jpg', N'Vinfast hàng Việt chất lượng Đông Lào',N'2585763395.jpg');
  Insert into SanPham values (N'SP008', N'Vinfast Fadil ', 19900,5, N'Vinfast', N'Việt Nam', N'White',  N'Ô tô', N'vinfadil.jpg', N'Vinfast hàng Việt chất lượng Đông Lào',N'9424085538.jpg');

	Insert into SanPham values (N'SP009', N'Mazda CX5', 39900, 5, N'Mazda', N'Nhật Bản', N'White-Black', N'Ô tô', N'mazdacx5.jpg', N'Mazda giá xịn',N'4286391596.jpg');
	Insert into SanPham values (N'SP010', N'Mercedes C300', 45990, 5, N'Mercedes', N'Đức', N'White-Black',  N'Ô tô', N'mecC300.jpg', N'Mercedes giá xịn',N'5892106893.jpg');
	Insert into SanPham values (N'SP011', N'BMW 330e m Spost', 23990, 5, N'BMW', N'Đức', N'White-Black',  N'Ô tô', N'bmw330e.jpg', N'BMW giá xịn',N'2962218423.jpg');
	Insert into SanPham values (N'SP012', N'Suzuki Jimny', 23990, 5, N'Suzukijimny',N'Nhật Bản', N'White-Black',  N'Ô tô', N'suzukijimny.jpg', N'Suzuki jimny giá xịn',N'0429384135.jpg');
	Insert into SanPham values (N'SP013', N'Nissan Juke Acenta', 11990, 5, N'Nissan', N'Nhật Bản', N'White-Black',  N'Ô tô', N'nissanjuke.jpg', N'Nissan giá xịn',N'2335585437.jpg');
	Insert into SanPham values (N'SP014', N'Suzuki S-Presso 1.0', 23990, 5, N'Suzuki', N'Nhật Bản', N'White-Black', N'Ô tô', N'suzukis_presso.jpg', N'Suzuki giá xịn',N'2186618923.jpg');
	Insert into SanPham values (N'SP015', N'Toyota 7 chổ', 9999, 3, N'Toyota', N'Nhật Bản', N'White-Black',  N'Ô tô', N'toyota7cho.jpg', N'Toyota giá xịn',N'8453777745.jpg');
	select * from SanPham
Insert into SanPham values (N'SP016', N'DECAL DÁN XE Ô TÔ', 100, 5, N'Eternal showroom', N'Việt Nam', N'White', N'Phụ tùng', N'decal.jpg', N'decal cờ VN',N'4346821777.jpg');
Insert into SanPham values (N'SP017', N'NƯỚC HOA Ô TÔ ', 200, 100, N'Eternal showroom', N'Việt Nam', N'White', N'Phụ tùng', N'nuochoa.jpg', N'Nước hoa cao cấp',N'1141718866.jpg');
Insert into SanPham values (N'SP018', N'TÚI ĐỰNG ĐỒ ', 400, 1000, N'Eternal showroom', N'Việt Nam', N'Black', N'Phụ tùng', N'tuidungdo.jpg', N'Túi đựng đồ cao cấp',N'8844513740.jpg');
Insert into SanPham values (N'SP019', N'GỐI TỰA ĐẦU Ô TÔ ', 350, 100, N'Eternal showroom', N'Việt Nam', N'White',N'Phụ tùng', N'goituadau.jpg', N'Gối vjp pro ',N'7568186653.jpg');
Insert into SanPham values (N'SP020', N'GIÁ ĐỠ ĐIỆN THOẠI Ô TÔ ', 250, 100, N'Eternal showroom', N'Việt Nam', N'Black', N'Phụ tùng', N'giadodt.jpg', N'Rớt đt hoàn tiền',N'3838851877.jpg');

	Insert into SanPham values (N'SP021', N'BỌC DA VÔ LĂNG', 500, 255, N'Luxury', N'ITALY', N'BLACK', N'Phụ tùng', N'bocda.jpg', N'BỌC DA VÔ LĂNG CAO CẤP',N'8573787311.jpg');
	Insert into SanPham values (N'SP022', N'CẢM BIẾN ÁP SUẤT LỐP FOBO', 600, 155, N'Luxury', N'Đức', N'Gray', N'Phụ tùng', N'cambien.jpg', N'CẢM BIẾN ÁP SUẤT LỐP FOBO CAO CẤP',N'7690329316.jpg');
	Insert into SanPham values (N'SP023', N'CAMMERA HÀNH TRÌNH', 355, 275, N'Sports', N'Nhật Bản', N'BLACK', N'Phụ tùng', N'camera.jpg', N'CAMMERA HÀNH TRÌNH',N'7161149866.jpg');
	Insert into SanPham values (N'SP024', N'Thảm lót sàn Mercedes-Benz GLA', 700, 257, N'Luxury', N'Hàn Quốc', N'Yellow',N'Phụ tùng', N'thamlotmercedes.jpg', N'Thảm lót sàn Mercedes-Benz GLA CAO CẤP',N'1274842498.jpg');
/*
Insert into SanPham values (N'SP026', N'BỌC DA VÔ LĂNG', 500, 25, N'Luxury', N'ITALY', N'BLACK', N'SẢN PHẨM', N'bocdavolang.jpg', N'BỌC DA VÔ LĂNG CAO CẤP')
Insert into SanPham values (N'SP027', N'Camera hành trình VEZO 4K 360', 515, 5, N'Luxury', N'Đức', N'BLACK', N'SẢN PHẨM', N'cameravezo4k.jpg', N'Camera hành trình VEZO 4K 360');
Insert into SanPham values (N'SP028', N'Mâm BMW cacbon', 900, 5, N'BMW', N'Đức', N'White', N'SẢN PHẨM', N'mambmw.jpg', N'Mâm xịn');
Insert into SanPham values (N'SP029', N'Lốp Michelin', 190, 5, N'Luxury', N'Đức', N'BLACK', N'SẢN PHẨM', N'lopmichelin.jpg', N'Lốp ngon');
Insert into SanPham values (N'SP030', N'Vô lăng CBX', 500, 5, N'Luxury', N'Đức', N'BLACK-Yellow', N'SẢN PHẨM', N'volang.jpg', N'Vô lăng ngon');
*/
Select * from SanPham;


 

  Insert into HopDong values (N'MHD001',N'Hợp đồng mua bán xe ô tô',N'SP001',N'B001',N'KH001',N'NV001');
  Insert into HopDong values (N'MHD002',N'Hợp đồng mua bán xe ô tô',N'SP002',N'B002',N'KH002',N'NV005');
  Insert into HopDong values (N'MHD003',N'Hợp đồng mua bán xe ô tô',N'SP003',N'B003',N'KH003',N'NV002');
  Insert into HopDong values (N'MHD004',N'Hợp đồng mua bán xe ô tô',N'SP004',N'B004',N'KH004',N'NV004');
  Insert into HopDong values (N'MHD005',N'Hợp đồng mua bán xe ô tô',N'SP005',N'B005',N'KH005',N'NV009');
  Insert into HopDong values (N'MHD006',N'Hợp đồng mua bán xe ô tô',N'SP010',N'B007',N'KH007',N'NV001');
  Insert into HopDong values (N'MHD007',N'Hợp đồng mua bán xe ô tô',N'SP011',N'B008',N'KH008',N'NV008');
  Insert into HopDong values (N'MHD008',N'Hợp đồng mua bán xe ô tô',N'SP012',N'B009',N'KH009',N'NV003');
  Insert into HopDong values (N'MHD009',N'Hợp đồng mua bán xe ô tô',N'SP013',N'B010',N'KH010',N'NV004');
  Insert into HopDong values (N'MHD010',N'Hợp đồng mua bán xe ô tô',N'SP015',N'B011',N'KH010',N'NV007');
  select * from HopDong;
