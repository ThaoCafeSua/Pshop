CREATE DATABASE DUAN1_PRO1041_NHOM7


use DUAN1_PRO1041_NHOM7

CREATE TABLE MauSac(
id int identity(1,1) primary key,
ten_mau_sac nvarchar(225) not null,
trang_thai bit not null
);
CREATE TABLE Size(
id int identity(1,1) primary key,
ten_size nvarchar(225) not null,
trang_thai bit not null
);

CREATE TABLE DanhMuc(
id int identity(1,1) primary key,
ten_danh_muc nvarchar(225) not null,
trang_thai bit not null
);
CREATE TABLE ChatLieu(
id int identity(1,1) primary key,
ten_chat_lieu nvarchar(225) not null,
trang_thai bit not null
);
CREATE TABLE ThuongHieu(
id int identity(1,1) primary key,
ten_thuong_hieu nvarchar(225) not null,
trang_thai bit not null
);

CREATE TABLE ChucVu(
id int identity(1,1) primary key,
ten_chuc_vu nvarchar(225) not null,
trang_thai bit not null
);

CREATE TABLE NhanVien(
id int identity(1,1) primary key,
id_chuc_vu int not null,
ten_nhan_vien nvarchar(225) not null,
ngay_sinh Date not null,
gioi_tinh bit not null,
dia_chi nvarchar(225) not null,
email varchar(50) not null,
so_dien_thoai varchar(15) not null,
tai_khoan varchar(50) not null,
mat_khau varchar(50) not null,
ngay_tao Date not null,
ngay_sua Date not null,
trang_thai bit not null
);
CREATE TABLE SanPham(
id int identity(1,1) primary key,
ten_san_pham nvarchar(225) not null,
id_thuong_hieu int not null,
id_chat_lieu int not null,
id_danh_muc int not null,
ngay_tao Date not null,
ngay_sua Date not null,
trang_thai bit not null
)
CREATE TABLE SanPhamChiTiet(
id int identity(1,1) primary key,
id_mau_sac int not null,
id_size int not null,
id_san_pham int not null,
so_luong int not null,
gia_ban float not null,
trang_thai bit not null
)
CREATE TABLE KhachHang(
id int identity(1,1) primary key,
ten_khach_hang nvarchar(225) not null,
ngay_sinh Date not null,
gioi_tinh bit not null,
dia_chi nvarchar(225) not null,
email varchar(225) not null,
so_dien_thoai varchar(15) not null,
ngay_tao Date not null,
ngay_sua Date not null,
trang_thai bit not null
)

CREATE TABLE PhuongThucThanhToan(
id int identity(1,1) primary key,
ten_phuong_thuc_thanh_toan nvarchar(225) not null,
ngay_tao Date not null,
ngay_sua Date not null,
trang_thai bit not null
)
CREATE TABLE Voucher(
id int identity(1,1) primary key,
ma_voucher varchar(10) not null,
ten_voucher nvarchar(225) not null,
ngay_bat_dau Date not null,
ngay_ket_thuc Date not null,
muc_giam varchar(50) not null,
hinh_thuc nvarchar(50) not null,
so_luong int not null,
ngay_tao Date not null,
ngay_sua Date not null,
trang_thai bit not null
)
CREATE TABLE HoaDon(
id int identity(1,1) primary key,
ma_hoa_don varchar(10) null,
id_khach_hang int  null,
id_nhan_vien int null,
id_voucher int  null,
id_pttt int  null,
tong_tien float null,
thanh_tien float null,
ngay_tao Date  null,
ngay_sua Date  null,
ngay_thanh_toan Date  null,
trang_thai bit null
)

CREATE TABLE HoaDonChiTiet(
id int identity(1,1) primary key,
id_hoa_don int not null,
id_ctsp int not null,
so_luong int not null,
gia_tien float not null,
trang_thai bit not null
)


ALTER TABLE NhanVien
ADD CONSTRAINT fk_id_chuc_vu FOREIGN KEY (id_chuc_vu) REFERENCES ChucVu(id)

ALTER TABLE SanPham
ADD CONSTRAINT fk_id_danh_muc FOREIGN KEY (id_danh_muc) REFERENCES DanhMuc(id)

ALTER TABLE SanPham
ADD CONSTRAINT fk_id_thuong_hieu FOREIGN KEY (id_thuong_hieu) REFERENCES ThuongHieu(id)

ALTER TABLE SanPham
ADD CONSTRAINT fk_id_chat_lieu FOREIGN KEY (id_chat_lieu) REFERENCES ChatLieu(id)

ALTER TABLE SanPhamChiTiet
ADD CONSTRAINT fk_id_mau_sac FOREIGN KEY (id_mau_sac) REFERENCES MauSac(id)

ALTER TABLE SanPhamChiTiet
ADD CONSTRAINT fk_id_size FOREIGN KEY (id_size) REFERENCES Size(id)

ALTER TABLE SanPhamChiTiet
ADD CONSTRAINT fk_id_san_pham FOREIGN KEY (id_san_pham) REFERENCES SanPham(id)

ALTER TABLE HoaDon
ADD CONSTRAINT fk_id_khach_hang FOREIGN KEY (id_khach_hang) REFERENCES KhachHang(id)

ALTER TABLE HoaDon
ADD CONSTRAINT fk_id_nhan_vien FOREIGN KEY (id_nhan_vien) REFERENCES NhanVien(id)

ALTER TABLE HoaDon
ADD CONSTRAINT fk_id_voucher FOREIGN KEY (id_voucher) REFERENCES Voucher(id)

ALTER TABLE HoaDon
ADD CONSTRAINT fk_id_pttt FOREIGN KEY (id_pttt) REFERENCES PhuongThucThanhToan(id)

ALTER TABLE HoaDonChiTiet
ADD CONSTRAINT fk_id_hoa_don FOREIGN KEY (id_hoa_don) REFERENCES HoaDon(id)

ALTER TABLE HoaDonChiTiet
ADD CONSTRAINT fk_id_ctsp FOREIGN KEY (id_ctsp) REFERENCES SanPhamChiTiet(id)

INSERT INTO MauSac (ten_mau_sac, trang_thai) VALUES
('Red', 1),
('Blue', 1),
('Green', 1),
('Black', 1),
('White', 1),
('Yellow', 1),
('Pink', 1),
('Purple', 1),
('Orange', 1),
('Brown', 1);

-- Inserting data into Size
INSERT INTO Size (ten_size, trang_thai) VALUES
('Small', 1),
('Medium', 1),
('Large', 1),
('X-Large', 1),
('XX-Large', 1),
('38', 1),
('39', 1),
('40', 1),
('41', 1),
('42', 1);

-- Inserting data into DanhMuc
INSERT INTO DanhMuc (ten_danh_muc, trang_thai) VALUES
('Men', 1),
('Women', 1),
('Kids', 1),
('Sports', 1),
('Casual', 1),
('Formal', 1),
('Boots', 1),
('Sandals', 1),
('Slippers', 1),
('Sneakers', 1);

-- Inserting data into ChatLieu
INSERT INTO ChatLieu (ten_chat_lieu, trang_thai) VALUES
('Leather', 1),
('Canvas', 1),
('Synthetic', 1),
('Rubber', 1),
('Foam', 1),
('Mesh', 1),
('Suede', 1),
('Nylon', 1),
('Polyester', 1),
('Cotton', 1);

-- Inserting data into ThuongHieu
INSERT INTO ThuongHieu (ten_thuong_hieu, trang_thai) VALUES
('Nike', 1),
('Adidas', 1),
('Puma', 1),
('Reebok', 1),
('Under Armour', 1),
('New Balance', 1),
('Converse', 1),
('Vans', 1),
('ASICS', 1),
('Fila', 1);

select * from ChucVu
INSERT INTO ChucVu(ten_chuc_vu,trang_thai)
VALUES (N'Quản lý', 1),
	   (N'Nhân viên',0)

INSERT INTO NhanVien (id_chuc_vu, ten_nhan_vien, ngay_sinh, gioi_tinh, dia_chi, email, so_dien_thoai, tai_khoan, mat_khau, ngay_tao, ngay_sua, trang_thai) VALUES
(1, N'Phạm Khánh Duy', '2004-06-06', 1, N'123 Đường A, Quận 1, TP. HCM', 'nguyenvana@example.com', '0912345678', 'abc', '123', GETDATE(), GETDATE(), 1),
(2, N'Đàm Việt Hà', '1991-02-02', 0, N'456 Đường B, Quận 2, TP. HCM', 'tranthib@example.com', '0912345679', 'a', '1', GETDATE(), GETDATE(), 1),
(1, N'Lê Văn C', '1992-03-03', 1, N'789 Đường C, Quận 3, TP. HCM', 'levanc@example.com', '0912345680', 'levanc', 'password123', GETDATE(), GETDATE(), 1),
(2, N'Phạm Thị D', '1993-04-04', 0, N'123 Đường D, Quận 4, TP. HCM', 'phamthid@example.com', '0912345681', 'phamthid', 'password123', GETDATE(), GETDATE(), 1),
(2, N'Hoàng Văn E', '1994-05-05', 1, N'456 Đường E, Quận 5, TP. HCM', 'hoangvane@example.com', '0912345682', 'hoangvane', 'password123', GETDATE(), GETDATE(), 1),
(2, N'Đặng Thị F', '1995-06-06', 0, N'789 Đường F, Quận 6, TP. HCM', 'dangthif@example.com', '0912345683', 'dangthif', 'password123', GETDATE(), GETDATE(), 1),
(1, N'Bùi Văn G', '1996-07-07', 1, N'123 Đường G, Quận 7, TP. HCM', 'buivang@example.com', '0912345684', 'buivang', 'password123', GETDATE(), GETDATE(), 1),
(2, N'Võ Thị H', '1997-08-08', 0, N'456 Đường H, Quận 8, TP. HCM', 'vothih@example.com', '0912345685', 'vothih', 'password123', GETDATE(), GETDATE(), 1),
(2, N'Ngô Văn I', '1998-09-09', 1, N'789 Đường I, Quận 9, TP. HCM', 'ngovani@example.com', '0912345686', 'ngovani', 'password123', GETDATE(), GETDATE(), 1),
(2, N'Dương Thị J', '1999-10-10', 0, N'123 Đường J, Quận 10, TP. HCM', 'duongthij@example.com', '0912345687', 'duongthij', 'password123', GETDATE(), GETDATE(), 1);

-- Inserting data into SanPham
INSERT INTO SanPham (ten_san_pham,id_thuong_hieu,id_chat_lieu,id_danh_muc, ngay_tao, ngay_sua, trang_thai) VALUES
('Nike Air Max',1,1,1,  GETDATE(), GETDATE(), 1),
('Adidas Ultraboost',2,2,2, GETDATE(), GETDATE(), 1),
('Puma Suede Classic',3,3,3, GETDATE(), GETDATE(), 1),
('Reebok Classic Leather',4,4,4, GETDATE(), GETDATE(), 1),
('Under Armour HOVR Phantom',5,5,5, GETDATE(), GETDATE(), 1),
('New Balance 574',6,6,6, GETDATE(), GETDATE(), 1),
('Converse Chuck Taylor All Star',7,7,7, GETDATE(), GETDATE(), 1),
('Vans Old Skool',8,8,8, GETDATE(), GETDATE(), 1),
('ASICS Gel-Kayano',9,9,9, GETDATE(), GETDATE(), 1),
('Fila Disruptor',10,10,10, GETDATE(), GETDATE(), 1);


-- Inserting data into SanPhamChiTiet
INSERT INTO SanPhamChiTiet (id_mau_sac, id_size, id_san_pham, so_luong, gia_ban, trang_thai) VALUES
(1, 1, 1, 100, 100.000, 1),
(2, 2, 2, 150, 130.000, 1),
(3, 3, 3, 200, 90.000, 1),
(4, 4, 4, 120, 125.000, 1),
(5, 5, 5, 180, 140.000, 1),
(6, 6, 6, 220, 80.000, 1),
(7, 7, 7, 160, 120.000, 1),
(8, 8, 8, 140, 100.000, 1),
(9, 9, 9, 130, 100.000, 1),
(10, 10,10, 110, 200.000, 1);


-- Inserting data into KhachHang
INSERT INTO KhachHang (ten_khach_hang, ngay_sinh, gioi_tinh, dia_chi, email, so_dien_thoai, ngay_tao, ngay_sua, trang_thai) VALUES
(N'Nguyễn Thị Hồng', '1985-04-15', 0, N'123 Đường A, Quận 1, TP. HCM', 'hong.nguyen@example.com', '0912345678', GETDATE(), GETDATE(), 1),
(N'Trần Văn Minh', '1987-05-20', 1, N'456 Đường B, Quận 2, TP. HCM', 'minh.tran@example.com', '0912345679', GETDATE(), GETDATE(), 1),
(N'Lê Thị Lan', '1990-06-25', 0, N'789 Đường C, Quận 3, TP. HCM', 'lan.le@example.com', '0912345680', GETDATE(), GETDATE(), 1),
(N'Phạm Quốc Bảo', '1992-07-30', 1, N'123 Đường D, Quận 4, TP. HCM', 'bao.pham@example.com', '0912345681', GETDATE(), GETDATE(), 1),
(N'Vũ Thị Hạnh', '1989-08-05', 0, N'456 Đường E, Quận 5, TP. HCM', 'hanh.vu@example.com', '0912345682', GETDATE(), GETDATE(), 1),
(N'Ngô Văn Hưng', '1984-09-10', 1, N'789 Đường F, Quận 6, TP. HCM', 'hung.ngo@example.com', '0912345683', GETDATE(), GETDATE(), 1),
(N'Bùi Thị Thanh', '1988-10-15', 0, N'123 Đường G, Quận 7, TP. HCM', 'thanh.bui@example.com', '0912345684', GETDATE(), GETDATE(), 1),
(N'Hoàng Văn An', '1991-11-20', 1, N'456 Đường H, Quận 8, TP. HCM', 'an.hoang@example.com', '0912345685', GETDATE(), GETDATE(), 1),
(N'Đỗ Thị Hoa', '1986-12-25', 0, N'789 Đường I, Quận 9, TP. HCM', 'hoa.do@example.com', '0912345686', GETDATE(), GETDATE(), 1),
(N'Đinh Văn Kiên', '1983-01-01', 1, N'123 Đường J, Quận 10, TP. HCM', 'kien.dinh@example.com', '0912345687', GETDATE(), GETDATE(), 1);
-- Inserting data into PhuongThucThanhToan
INSERT INTO PhuongThucThanhToan (ten_phuong_thuc_thanh_toan, ngay_tao, ngay_sua, trang_thai) VALUES
(N'Thanh toán bằng tiền mặt', GETDATE(), GETDATE(), 1),
(N'Thanh toán bằng tài khoản', GETDATE(), GETDATE(), 1);
-- Inserting data into Voucher
INSERT INTO Voucher (ma_voucher, ten_voucher, ngay_bat_dau, ngay_ket_thuc, muc_giam, hinh_thuc, so_luong, ngay_tao, ngay_sua, trang_thai)
VALUES
    ('V001', N'Voucher 1', '2024-07-01', '2024-07-31', '10%', N'Phần trăm', 100, '2024-07-01', '2024-07-01', 1),
    ('V002', N'Voucher 2', '2024-07-05', '2024-08-05', '20%', N'Phần trăm', 50, '2024-07-05', '2024-07-05', 1),
    ('V003', N'Voucher 3', '2024-07-10', '2024-08-10', '15%', N'Phần trăm', 75, '2024-07-10', '2024-07-10', 1),
    ('V004', N'Voucher 4', '2024-07-15', '2024-08-15', '30000 VND', N'Số tiền', 200, '2024-07-15', '2024-07-15', 1),
    ('V005', N'Voucher 5', '2024-07-20', '2024-08-20', '25000 VND', N'Số tiền', 150, '2024-07-20', '2024-07-20', 1),
    ('V006', N'Voucher 6', '2024-07-25', '2024-08-25', '50%', N'Phần trăm', 300, '2024-07-25', '2024-07-25', 1),
    ('V007', N'Voucher 7', '2024-07-30', '2024-08-30', '40%', N'Phần trăm', 80, '2024-07-30', '2024-07-30', 1),
    ('V008', N'Voucher 8', '2024-08-01', '2024-08-31', '20000 VND', N'Số tiền', 120, '2024-08-01', '2024-08-01', 1),
    ('V009', N'Voucher 9', '2024-08-05', '2024-09-05', '10000 VND', N'Số tiền', 90, '2024-08-05', '2024-08-05', 1),
    ('V010', N'Voucher 10', '2024-08-10', '2024-09-10', '35%', N'Phần trăm', 180, '2024-08-10', '2024-08-10', 1);-- Inserting data into HoaDon
INSERT INTO HoaDon (id_khach_hang, id_nhan_vien, id_voucher, id_pttt, tong_tien, thanh_tien, ngay_tao, ngay_sua, ngay_thanh_toan, trang_thai) VALUES
(1, 1, 1, 1, 1000000, 900000, GETDATE(), GETDATE(), GETDATE(), 1),
(2, 2, 2, 1, 2000000, 1600000, GETDATE(), GETDATE(), GETDATE(), 1),
(3, 3, 3, 1, 3000000, 2100000, GETDATE(), GETDATE(), GETDATE(), 1),
(4, 4, 4, 1, 4000000, 2400000, GETDATE(), GETDATE(), GETDATE(), 1),
(5, 5, 5, 1, 5000000, 2500000, GETDATE(), GETDATE(), GETDATE(), 1),
(6, 6, 6, 2, 6000000, 3000000, GETDATE(), GETDATE(), GETDATE(), 1),
(7, 7, 7, 2, 7000000, 3500000, GETDATE(), GETDATE(), GETDATE(), 1),
(8, 8, 8, 2, 8000000, 4000000, GETDATE(), GETDATE(), GETDATE(), 1),
(9, 9, 9, 1, 9000000, 4500000, GETDATE(), GETDATE(), GETDATE(), 1),
(10, 10, 10, 1, 10000000, 5000000, GETDATE(), GETDATE(), GETDATE(), 1);

-- Inserting data into HoaDonChiTiet
INSERT INTO HoaDonChiTiet (id_hoa_don, id_ctsp, so_luong, gia_tien, trang_thai)
VALUES 
(1, 1, 2, 100.000, 1),
(2, 2, 1, 150.000, 1),
(3, 3, 3, 200.000, 1),
(4, 4, 1, 120.000, 1),
(5, 5, 2, 180.000, 1),
(6, 6, 1, 160.000, 1),
(7, 7, 4, 250.000, 1),
(8, 8, 2, 130.000, 1),
(9, 9, 1, 110.000, 1),
(10, 10, 3, 140.000, 1);

select * from NhanVien
select * from KhachHang
select * from PhuongThucThanhToan
select * from HoaDon
select * from HoaDonChiTiet
select * from SanPham
select * from SanPhamChiTiet
select * from ChatLieu
select * from MauSac
select * from Size
select * from ThuongHieu
select * from DanhMuc
select * from ChucVu
select * from Voucher

select * from HoaDon
SELECT tong_tien from HoaDon where id = ?
SELECT id,ma_hoa_don,id_khach_hang,id_nhan_vien,id_voucher,id_pttt,tong_tien,thanh_tien,ngay_tao,ngay_sua,ngay_thanh_toan,trang_thai FROM HoaDon 
UPDATE HoaDon SET id_khach_hang=?,id_voucher=?,id_pttt= ? ,tong_tien = ?,thanh_tien=?,ngay_sua=GETDATE(),ngay_thanh_toan = GETDATE(), trang_thai = 0 WHERE id= ?
SELECT id,ma_voucher,ten_voucher,ngay_bat_dau,ngay_ket_thuc,muc_giam,hinh_thuc,so_luong,ngay_tao,ngay_sua,trang_thai FROM Voucher WHERE ma_voucher = N'V001'