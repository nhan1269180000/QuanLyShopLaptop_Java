-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 08, 2022 at 07:33 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlyshoplaptop`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHD` varchar(8) NOT NULL,
  `MaSP` varchar(8) NOT NULL,
  `DonGia` int(50) NOT NULL,
  `SoLuong` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MaHD`, `MaSP`, `DonGia`, `SoLuong`) VALUES
('HD01', 'SP01', 19000000, 1),
('HD01', 'SP02', 21500000, 1),
('HD01', 'SP05', 30000000, 2),
('HD02', 'SP01', 25000000, 1),
('HD02', 'SP02', 21500000, 1),
('HD02', 'SP03', 19000000, 1),
('HD02', 'SP07', 22000000, 2),
('HD03', 'SP02', 21500000, 1),
('HD03', 'SP04', 27000000, 1),
('HD03', 'SP06', 28500000, 1),
('HD03', 'SP07', 22000000, 1),
('HD04', 'SP01', 25000000, 1),
('HD04', 'SP03', 19000000, 1),
('HD04', 'SP04', 27000000, 1),
('HD05', 'SP01', 25000000, 1),
('HD05', 'SP02', 21500000, 1),
('HD06', 'SP05', 30000000, 1),
('HD06', 'SP07', 22000000, 1),
('HD07', 'SP03', 19000000, 1),
('HD07', 'SP06', 28500000, 1),
('HD08', 'SP03', 19000000, 2),
('HD08', 'SP06', 28500000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hangsx`
--

CREATE TABLE `hangsx` (
  `MaHSX` varchar(15) NOT NULL,
  `TenHSX` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hangsx`
--

INSERT INTO `hangsx` (`MaHSX`, `TenHSX`) VALUES
('Acer', 'Acer'),
('Asus', 'Asus'),
('Dell', 'Dell'),
('HP', 'HP'),
('Lenovo', 'Lenovo'),
('MSI', 'MSI');

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` varchar(8) NOT NULL,
  `NgayDat` varchar(20) NOT NULL,
  `NgayGiao` varchar(20) NOT NULL,
  `DiaChiGiao` varchar(300) NOT NULL,
  `MaKH` varchar(8) NOT NULL,
  `MaNV` varchar(8) NOT NULL,
  `TinhTrang` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `NgayDat`, `NgayGiao`, `DiaChiGiao`, `MaKH`, `MaNV`, `TinhTrang`) VALUES
('HD01', '2022-01-01', '2022-01-06', '734 đường 2/4 Phường Vĩnh Phước', 'KH01', 'NV01', 'Đã thanh toán'),
('HD02', '2022-01-03', '2022-01-15', '710 đường 2/4 Phường Vĩnh Phước', 'KH02', 'NV02', 'Đang tiến hành'),
('HD03', '2022-01-07', '2022-01-10', 'Tổ 16 Trường Phúc', 'KH04', 'NV03', 'Đang tiến hành'),
('HD04', '2022-01-07', '2022-01-07', 'Tổ 16 Trường Phúc', 'KH06', 'NV04', 'Đã thanh toán'),
('HD05', '2022-01-07', '2022-01-07', 'Tháp Bà Ponagar', 'KH04', 'NV01', 'Đang tiến hành'),
('HD06', '2022-01-15', '2022-01-30', 'Vincom', 'KH05', 'NV02', 'Đang tiến hành'),
('HD07', '2022-01-11', '2022-01-17', 'Trường Đại Học Nha Trang', 'KH03', 'NV04', 'Đang tiến hành'),
('HD08', '2022-01-07', '2022-01-08', 'Trường Trung Học Nguyễn Khuyến', 'KH03', 'NV01', 'Đã thanh toán');

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` varchar(8) NOT NULL,
  `HoTen` varchar(50) NOT NULL,
  `SDT` int(15) NOT NULL,
  `DiaChi` varchar(100) NOT NULL,
  `TenDN` varchar(50) NOT NULL,
  `MatKhau` varchar(64) NOT NULL,
  `Quyen` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `HoTen`, `SDT`, `DiaChi`, `TenDN`, `MatKhau`, `Quyen`) VALUES
('KH01', 'Trần Minh Trọng', 786669993, 'Nha Trang', 'trong', '202CB962AC59075B964B07152D234B70', 'User'),
('KH02', 'Nguyễn Phương', 784358800, 'Nha Trang', 'phuong', '202CB962AC59075B964B07152D234B70', 'User'),
('KH03', 'Dương Tăng Lực', 755356111, 'Nha Trang', 'luc', '202CB962AC59075B964B07152D234B70', 'User'),
('KH04', 'Trương Đình Khang', 123456789, 'Nha Trang', 'khang', '202CB962AC59075B964B07152D234B70', 'User'),
('KH05', 'Huỳnh Đình Minh Trí', 700123456, 'Nha Trang', 'tri', '202CB962AC59075B964B07152D234B70', 'User'),
('KH06', 'Cao Nguyễn Hải Duy', 700456123, 'Nha Trang', 'duy', '202CB962AC59075B964B07152D234B70', 'User');

-- --------------------------------------------------------

--
-- Table structure for table `loaisp`
--

CREATE TABLE `loaisp` (
  `MaLSP` varchar(20) NOT NULL,
  `TenLSP` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loaisp`
--

INSERT INTO `loaisp` (`MaLSP`, `TenLSP`) VALUES
('Business', 'Business Laptop'),
('Gaming', 'Gaming Laptop'),
('Home', 'Home Laptop');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` varchar(8) NOT NULL,
  `HoTen` varchar(50) NOT NULL,
  `SDT` int(15) NOT NULL,
  `TenDN` varchar(50) NOT NULL,
  `MatKhau` varchar(64) NOT NULL,
  `Quyen` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `HoTen`, `SDT`, `TenDN`, `MatKhau`, `Quyen`) VALUES
('NV01', 'Nguyễn Thành Nhân', 896499728, 'nhan', '202CB962AC59075B964B07152D234B70', 'Admin'),
('NV02', 'Nguyễn Văn Thạch', 971770202, 'thach', '202CB962AC59075B964B07152D234B70', 'Admin'),
('NV03', 'Nguyễn Nhật Trường', 768686861, 'truong', '202CB962AC59075B964B07152D234B70', 'Admin'),
('NV04', 'Nguyễn Hưng', 981158080, 'hung', '250CF8B51C773F3F8DC8B4BE867A9A02', 'Admin'),
('NV05', 'Võ Tuấn Anh', 123456789, 'anh', '250CF8B51C773F3F8DC8B4BE867A9A02', 'Admin'),
('NV06', 'Nguyễn Thị Yến Nhi', 773574392, 'yennhi', '250CF8B51C773F3F8DC8B4BE867A9A02', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` varchar(8) NOT NULL,
  `TenSP` varchar(80) NOT NULL,
  `MoTa` varchar(300) NOT NULL,
  `ThongSo` varchar(300) NOT NULL,
  `AnhSP` varchar(200) NOT NULL,
  `DonGia` int(50) NOT NULL,
  `SoLuong` int(50) NOT NULL,
  `MaHSX` varchar(15) NOT NULL,
  `MaLSP` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`MaSP`, `TenSP`, `MoTa`, `ThongSo`, `AnhSP`, `DonGia`, `SoLuong`, `MaHSX`, `MaLSP`) VALUES
('SP01', 'Lenovo IdeaPad Gaming 3 ', 'Lenovo IdeaPad Gaming 3 15IMH05 có hiệu năng chơi game tuyệt vời trong tầm giá khi sở hữu bộ vi xử lý Intel Core i7 10750H cực mạnh đi cùng card đồ họa GTX 1650 Ti, giúp bạn chơi tốt hầu hết mọi tựa game hiện nay.', 'CPU:	Intel Core i7-10750H\nRAM:	8 GB, DDR4, 2933 MHz\nMàn hình:	15.6\", 1920 x 1080 Pixel, IPS, 120 Hz\nĐồ họa:	NVIDIA GeForce GTX 1650Ti 4 GB \nỔ cứng:	SSD 512 GB\nHệ điều hành:	Windows 10\nTrọng lượng (kg):	2.2\nKích thước (mm):	359 x 249.6 x 24', 'Lenovo Ideapad Gaming 3.jpg', 25000000, 10, 'Lenovo', 'Gaming'),
('SP02', 'Asus TUF Gaming FX506LH', 'Asus TUF Gaming FX506LH HN002T thuộc dòng laptop chơi game Asus TUF, cho bạn sự mạnh mẽ, bền bỉ không chỉ thiết kế bề ngoài mà cả hiệu năng bên trong. Với Asus TUF Gaming FX506, bạn sẽ là một chiến binh thực sự trong trò chơi yêu thích', 'CPU:	Intel Core i5-10300H\nRAM:	8 GB, DDR4, 2933 MHz\nMàn hình:	15.6\", 1920 x 1080 Pixel, IPS, 144 Hz\nĐồ họa:	NVIDIA GeForce GTX 1650 4 GB\nỔ cứng:	SSD 512 GB\nHệ điều hành:	Windows 10\nTrọng lượng (kg):	2.3\nKích thước (mm):	359 x 256 x 24.7', 'Asus TUF Gaming FX506LH.jpg', 21500000, 10, 'Asus', 'Gaming'),
('SP03', 'Asus VivoBook M413IA-EK481T', 'Trải nghiệm sự khác biệt về hiệu năng đến từ bộ vi xử lý AMD Ryzen 7 4000 series, Asus VivoBook M413IA EK560T mang tới tốc độ đáng kinh ngạc trong một thiết kế thời trang và di động.', 'CPU:	AMD Ryzen 7-4700U\nRAM:	8 GB, DDR4, 3200 MHz\nMàn hình:	14.0\", 1920 x 1080 Pixel, IPS, 60 Hz\nĐồ họa:	AMD Radeon Graphics\nỔ cứng:	SSD 1 TB\nHệ điều hành:	Windows 10\nTrọng lượng (kg):	1.45\nKích thước (mm):	325 x 213 x 16.2', 'Asus VivoBook M413IA-EK481T.jpg', 19000000, 15, 'Asus', 'Business'),
('SP04', 'HP Envy x360 13 ay0069AU', 'HP Envy x360 13 ay0069AU là chiếc laptop 2-in-1 xuất sắc bậc nhất hiện nay khi không chỉ linh hoạt với màn hình cảm ứng và khả năng xoay 360 độ mà máy còn trang bị cấu hình mạnh mẽ đáng kinh ngạc.', 'CPU:	AMD Ryzen 7-4700U\nRAM:	8 GB, DDR4, 3200 MHz\nMàn hình:	13.3\", 1920 x 1080 Pixels, IPS, 60 Hz\nĐồ họa:	AMD Radeon Graphics\nỔ cứng:	SSD 256 GB\nHệ điều hành:	Windows 10 Home\nTrọng lượng (kg):	1.32kg\nKích thước (mm):	306.5 x 194.6 x 16.4', 'HP Envy x360 13 ay0069AU.jpg', 27000000, 10, 'HP', 'Home'),
('SP05', 'Lenovo Yoga Slim 7 14ITL05', 'Mang trên mình bộ vi xử lý Intel Core i7 thế hệ thứ 11 Tiger Lake mới nhất, Lenovo Yoga Slim 7 14ITL05 xứng đáng là sự lựa chọn hàng đầu cho dòng máy tính nhỏ gọn chuyên dành cho công việc, với thiết kế cao cấp và độ bảo mật đang tin cậy.', 'CPU:	Intel Core i7-1165G7\nRAM:	16 GB, DDR4, 3200 MHz\nMàn hình:	14.0\", 1920 x 1080 Pixel, IPS, 60 Hz\nĐồ họa:	Intel Iris Xe Graphics\nỔ cứng:	SSD 512 GB\nHệ điều hành:	Windows 11\nTrọng lượng (kg):	1.36\nKích thước (mm):	320.5 x 208.5 x 14.9', 'Lenovo Yoga Slim 7 14ITL05.jpg', 30000000, 10, 'Lenovo', 'Business'),
('SP06', 'MSI Gaming GF65 10UE 286VN', 'Thưởng thức Ray Tracing và kiến trúc Ampere mới nhất của dòng card rời RTX 30 series trong mức giá hấp dẫn chưa từng thấy, laptop MSI Gaming GF65 10UE 286VN sẽ đưa trải nghiệm laptop gaming của bạn lên một tầm cao mới.', 'CPU:	Intel Core i5-10500H\nRAM:	16 GB, DDR4, 3200 MHz\nMàn hình:	15.6\", 1920 x 1080 Pixel, IPS, 144 Hz\nĐồ họa:	NVIDIA GeForce RTX 3060 Max-Q 6GB\nỔ cứng:	SSD 512 GB\nHệ điều hành:	Windows 10\nTrọng lượng (kg):	1.86\nKích thước (mm):	359 x 254 x 21.7', 'MSI Gaming GF65 10UE 286VN.jpg', 28500000, 10, 'MSI', 'Gaming'),
('SP07', 'Dell Vostro V3510', 'Dell Vostro V3510 mang một thiết kế thanh lịch, hiệu năng ổn định từ chip Intel thế hệ 11, chuẩn laptop học tập - văn phòng, đảm bảo đáp ứng mọi tác vụ quen thuộc được yêu cầu.', 'CPU:	Intel Core i5-1135G7\nRAM:	8 GB, DDR4, 3200 MHz\nMàn hình:	15.6\", 1920 x 1080 Pixel, WVA, 60 Hz\nĐồ họa:	NVIDIA GeForce MX350 2 GB\nỔ cứng:	SSD 512 GB\nHệ điều hành:	Windows 11\nTrọng lượng (kg):	1.699\nKích thước (mm):	358.5 x 235 x 18.9', 'Dell Vostro V3510.jpg', 22000000, 15, 'Dell', 'Home'),
('SP08', 'Acer Nitro Gaming AN515-57-57MX', 'Acer Nitro Gaming AN515-57-57MX là chiếc laptop gaming sử dụng cấu hình mới nhất với bộ vi xử lý Intel thế hệ thứ 11 và card đồ họa rời RTX 30 series, mang đến hiệu suất chơi game vượt trội, cùng bạn thống trị thế giới gaming.', 'CPU:	Intel Core i5-11400H\nRAM:	8 GB, DDR4, 3200 MHz\nMàn hình:	15.6\", 1920 x 1080 Pixel, IPS, 144 Hz\nĐồ họa:	NVIDIA GeForce RTX 3050Ti 4 GB\nỔ cứng:	SSD 512 GB\nHệ điều hành:	Windows 10\nTrọng lượng (kg):	2.2\nKích thước (mm):	363.4 x 255 x 23.9', 'Acer Nitro Gaming AN515-57-57MX.jpg', 26500000, 10, 'Acer', 'Gaming'),
('SP09', 'Dell Inspiron N3501B', 'Dell Inspiron N3501B với độ bền đặc trưng của Dell, trang bị cấu hình mới nhất từ bộ vi xử lý Intel thế hệ thứ 11, chắc chắn sẽ là sản phẩm đáng tin cậy trong công việc dành cho bạn.', 'CPU:	Intel Core i5-1135G7\nRAM:	4 GB, DDR4, 2666 MHz\nMàn hình:	15.6\", 1920 x 1080 Pixel, WVA, 60 Hz\nĐồ họa:	Intel Iris Xe Graphics\nỔ cứng:	SSD 512 GB\nHệ điều hành:	Windows 10\nTrọng lượng (kg):	1.96\nKích thước (mm):	363.96 x 249 x 19.9', 'Dell Inspiron N3501B.jpg', 22000000, 15, 'Dell', 'Home'),
('SP10', 'MSI Prestige 15 A11SC 037VN', 'Để người dùng có được phương tiện làm việc hiệu quả nhất, nhưng vẫn phải thiết kế tinh xảo và thể hiện đẳng cấp, MSI đã tạo ra MSI Prestige 15 A11SC 037VN, mẫu laptop mỏng nhẹ nhưng vẫn vô cùng mạnh mẽ.', 'CPU:	Intel Core i7-1185G7\nRAM:	16 GB, DDR4, 3200 MHz\nMàn hình:	15.6\", 1920 x 1080 Pixel, IPS, 60 Hz\nĐồ họa:	NVIDIA GeForce GTX 1650 Max-Q 4 GB \nỔ cứng:	SSD 512 GB\nHệ điều hành:	Windows 10\nTrọng lượng (kg):	1.693\nKích thước (mm):	356.8 x 233.7 x 16.9', 'MSI Prestige 15 A11SC 037VN.jpg', 22000000, 15, 'MSI', 'Business');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`MaHD`,`MaSP`),
  ADD KEY `FK_ChiTietHoaDon_SanPham` (`MaSP`);

--
-- Indexes for table `hangsx`
--
ALTER TABLE `hangsx`
  ADD PRIMARY KEY (`MaHSX`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `FK_HoaDon_KhachHang` (`MaKH`),
  ADD KEY `FK_HoaDon_NhanVien` (`MaNV`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Indexes for table `loaisp`
--
ALTER TABLE `loaisp`
  ADD PRIMARY KEY (`MaLSP`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`),
  ADD KEY `FK_SanPham_HangSX` (`MaHSX`),
  ADD KEY `FK_SanPham_LoaiSP` (`MaLSP`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `FK_ChiTietHoaDon_HoaDon` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_ChiTietHoaDon_SanPham` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `FK_HoaDon_KhachHang` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_HoaDon_NhanVien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK_SanPham_HangSX` FOREIGN KEY (`MaHSX`) REFERENCES `hangsx` (`MaHSX`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_SanPham_LoaiSP` FOREIGN KEY (`MaLSP`) REFERENCES `loaisp` (`MaLSP`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
