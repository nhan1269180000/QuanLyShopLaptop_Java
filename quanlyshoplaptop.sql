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
('HD01', '2022-01-01', '2022-01-06', '734 ???????ng 2/4 Ph?????ng V??nh Ph?????c', 'KH01', 'NV01', '???? thanh to??n'),
('HD02', '2022-01-03', '2022-01-15', '710 ???????ng 2/4 Ph?????ng V??nh Ph?????c', 'KH02', 'NV02', '??ang ti???n h??nh'),
('HD03', '2022-01-07', '2022-01-10', 'T??? 16 Tr?????ng Ph??c', 'KH04', 'NV03', '??ang ti???n h??nh'),
('HD04', '2022-01-07', '2022-01-07', 'T??? 16 Tr?????ng Ph??c', 'KH06', 'NV04', '???? thanh to??n'),
('HD05', '2022-01-07', '2022-01-07', 'Th??p B?? Ponagar', 'KH04', 'NV01', '??ang ti???n h??nh'),
('HD06', '2022-01-15', '2022-01-30', 'Vincom', 'KH05', 'NV02', '??ang ti???n h??nh'),
('HD07', '2022-01-11', '2022-01-17', 'Tr?????ng ?????i H???c Nha Trang', 'KH03', 'NV04', '??ang ti???n h??nh'),
('HD08', '2022-01-07', '2022-01-08', 'Tr?????ng Trung H???c Nguy???n Khuy???n', 'KH03', 'NV01', '???? thanh to??n');

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
('KH01', 'Tr???n Minh Tr???ng', 786669993, 'Nha Trang', 'trong', '202CB962AC59075B964B07152D234B70', 'User'),
('KH02', 'Nguy???n Ph????ng', 784358800, 'Nha Trang', 'phuong', '202CB962AC59075B964B07152D234B70', 'User'),
('KH03', 'D????ng T??ng L???c', 755356111, 'Nha Trang', 'luc', '202CB962AC59075B964B07152D234B70', 'User'),
('KH04', 'Tr????ng ????nh Khang', 123456789, 'Nha Trang', 'khang', '202CB962AC59075B964B07152D234B70', 'User'),
('KH05', 'Hu???nh ????nh Minh Tr??', 700123456, 'Nha Trang', 'tri', '202CB962AC59075B964B07152D234B70', 'User'),
('KH06', 'Cao Nguy???n H???i Duy', 700456123, 'Nha Trang', 'duy', '202CB962AC59075B964B07152D234B70', 'User');

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
('NV01', 'Nguy???n Th??nh Nh??n', 896499728, 'nhan', '202CB962AC59075B964B07152D234B70', 'Admin'),
('NV02', 'Nguy???n V??n Th???ch', 971770202, 'thach', '202CB962AC59075B964B07152D234B70', 'Admin'),
('NV03', 'Nguy???n Nh???t Tr?????ng', 768686861, 'truong', '202CB962AC59075B964B07152D234B70', 'Admin'),
('NV04', 'Nguy???n H??ng', 981158080, 'hung', '250CF8B51C773F3F8DC8B4BE867A9A02', 'Admin'),
('NV05', 'V?? Tu???n Anh', 123456789, 'anh', '250CF8B51C773F3F8DC8B4BE867A9A02', 'Admin'),
('NV06', 'Nguy???n Th??? Y???n Nhi', 773574392, 'yennhi', '250CF8B51C773F3F8DC8B4BE867A9A02', 'Admin');

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
('SP01', 'Lenovo IdeaPad Gaming 3 ', 'Lenovo IdeaPad Gaming 3 15IMH05 c?? hi???u n??ng ch??i game tuy???t v???i trong t???m gi?? khi s??? h???u b??? vi x??? l?? Intel Core i7 10750H c???c m???nh ??i c??ng card ????? h???a GTX 1650 Ti, gi??p b???n ch??i t???t h???u h???t m???i t???a game hi???n nay.', 'CPU:	Intel Core i7-10750H\nRAM:	8 GB, DDR4, 2933 MHz\nM??n h??nh:	15.6\", 1920 x 1080 Pixel, IPS, 120 Hz\n????? h???a:	NVIDIA GeForce GTX 1650Ti 4 GB \n??? c???ng:	SSD 512 GB\nH??? ??i???u h??nh:	Windows 10\nTr???ng l?????ng (kg):	2.2\nK??ch th?????c (mm):	359 x 249.6 x 24', 'Lenovo Ideapad Gaming 3.jpg', 25000000, 10, 'Lenovo', 'Gaming'),
('SP02', 'Asus TUF Gaming FX506LH', 'Asus TUF Gaming FX506LH HN002T thu???c d??ng laptop ch??i game Asus TUF, cho b???n s??? m???nh m???, b???n b??? kh??ng ch??? thi???t k??? b??? ngo??i m?? c??? hi???u n??ng b??n trong. V???i Asus TUF Gaming FX506, b???n s??? l?? m???t chi???n binh th???c s??? trong tr?? ch??i y??u th??ch', 'CPU:	Intel Core i5-10300H\nRAM:	8 GB, DDR4, 2933 MHz\nM??n h??nh:	15.6\", 1920 x 1080 Pixel, IPS, 144 Hz\n????? h???a:	NVIDIA GeForce GTX 1650 4 GB\n??? c???ng:	SSD 512 GB\nH??? ??i???u h??nh:	Windows 10\nTr???ng l?????ng (kg):	2.3\nK??ch th?????c (mm):	359 x 256 x 24.7', 'Asus TUF Gaming FX506LH.jpg', 21500000, 10, 'Asus', 'Gaming'),
('SP03', 'Asus VivoBook M413IA-EK481T', 'Tr???i nghi???m s??? kh??c bi???t v??? hi???u n??ng ?????n t??? b??? vi x??? l?? AMD Ryzen 7 4000 series, Asus VivoBook M413IA EK560T mang t???i t???c ????? ????ng kinh ng???c trong m???t thi???t k??? th???i trang v?? di ?????ng.', 'CPU:	AMD Ryzen 7-4700U\nRAM:	8 GB, DDR4, 3200 MHz\nM??n h??nh:	14.0\", 1920 x 1080 Pixel, IPS, 60 Hz\n????? h???a:	AMD Radeon Graphics\n??? c???ng:	SSD 1 TB\nH??? ??i???u h??nh:	Windows 10\nTr???ng l?????ng (kg):	1.45\nK??ch th?????c (mm):	325 x 213 x 16.2', 'Asus VivoBook M413IA-EK481T.jpg', 19000000, 15, 'Asus', 'Business'),
('SP04', 'HP Envy x360 13 ay0069AU', 'HP Envy x360 13 ay0069AU l?? chi???c laptop 2-in-1 xu???t s???c b???c nh???t hi???n nay khi kh??ng ch??? linh ho???t v???i m??n h??nh c???m ???ng v?? kh??? n??ng xoay 360 ????? m?? m??y c??n trang b??? c???u h??nh m???nh m??? ????ng kinh ng???c.', 'CPU:	AMD Ryzen 7-4700U\nRAM:	8 GB, DDR4, 3200 MHz\nM??n h??nh:	13.3\", 1920 x 1080 Pixels, IPS, 60 Hz\n????? h???a:	AMD Radeon Graphics\n??? c???ng:	SSD 256 GB\nH??? ??i???u h??nh:	Windows 10 Home\nTr???ng l?????ng (kg):	1.32kg\nK??ch th?????c (mm):	306.5 x 194.6 x 16.4', 'HP Envy x360 13 ay0069AU.jpg', 27000000, 10, 'HP', 'Home'),
('SP05', 'Lenovo Yoga Slim 7 14ITL05', 'Mang tr??n m??nh b??? vi x??? l?? Intel Core i7 th??? h??? th??? 11 Tiger Lake m???i nh???t, Lenovo Yoga Slim 7 14ITL05 x???ng ????ng l?? s??? l???a ch???n h??ng ?????u cho d??ng m??y t??nh nh??? g???n chuy??n d??nh cho c??ng vi???c, v???i thi???t k??? cao c???p v?? ????? b???o m???t ??ang tin c???y.', 'CPU:	Intel Core i7-1165G7\nRAM:	16 GB, DDR4, 3200 MHz\nM??n h??nh:	14.0\", 1920 x 1080 Pixel, IPS, 60 Hz\n????? h???a:	Intel Iris Xe Graphics\n??? c???ng:	SSD 512 GB\nH??? ??i???u h??nh:	Windows 11\nTr???ng l?????ng (kg):	1.36\nK??ch th?????c (mm):	320.5 x 208.5 x 14.9', 'Lenovo Yoga Slim 7 14ITL05.jpg', 30000000, 10, 'Lenovo', 'Business'),
('SP06', 'MSI Gaming GF65 10UE 286VN', 'Th?????ng th???c Ray Tracing v?? ki???n tr??c Ampere m???i nh???t c???a d??ng card r???i RTX 30 series trong m???c gi?? h???p d???n ch??a t???ng th???y, laptop MSI Gaming GF65 10UE 286VN s??? ????a tr???i nghi???m laptop gaming c???a b???n l??n m???t t???m cao m???i.', 'CPU:	Intel Core i5-10500H\nRAM:	16 GB, DDR4, 3200 MHz\nM??n h??nh:	15.6\", 1920 x 1080 Pixel, IPS, 144 Hz\n????? h???a:	NVIDIA GeForce RTX 3060 Max-Q 6GB\n??? c???ng:	SSD 512 GB\nH??? ??i???u h??nh:	Windows 10\nTr???ng l?????ng (kg):	1.86\nK??ch th?????c (mm):	359 x 254 x 21.7', 'MSI Gaming GF65 10UE 286VN.jpg', 28500000, 10, 'MSI', 'Gaming'),
('SP07', 'Dell Vostro V3510', 'Dell Vostro V3510 mang m???t thi???t k??? thanh l???ch, hi???u n??ng ???n ?????nh t??? chip Intel th??? h??? 11, chu???n laptop h???c t???p - v??n ph??ng, ?????m b???o ????p ???ng m???i t??c v??? quen thu???c ???????c y??u c???u.', 'CPU:	Intel Core i5-1135G7\nRAM:	8 GB, DDR4, 3200 MHz\nM??n h??nh:	15.6\", 1920 x 1080 Pixel, WVA, 60 Hz\n????? h???a:	NVIDIA GeForce MX350 2 GB\n??? c???ng:	SSD 512 GB\nH??? ??i???u h??nh:	Windows 11\nTr???ng l?????ng (kg):	1.699\nK??ch th?????c (mm):	358.5 x 235 x 18.9', 'Dell Vostro V3510.jpg', 22000000, 15, 'Dell', 'Home'),
('SP08', 'Acer Nitro Gaming AN515-57-57MX', 'Acer Nitro Gaming AN515-57-57MX l?? chi???c laptop gaming s??? d???ng c???u h??nh m???i nh???t v???i b??? vi x??? l?? Intel th??? h??? th??? 11 v?? card ????? h???a r???i RTX 30 series, mang ?????n hi???u su???t ch??i game v?????t tr???i, c??ng b???n th???ng tr??? th??? gi???i gaming.', 'CPU:	Intel Core i5-11400H\nRAM:	8 GB, DDR4, 3200 MHz\nM??n h??nh:	15.6\", 1920 x 1080 Pixel, IPS, 144 Hz\n????? h???a:	NVIDIA GeForce RTX 3050Ti 4 GB\n??? c???ng:	SSD 512 GB\nH??? ??i???u h??nh:	Windows 10\nTr???ng l?????ng (kg):	2.2\nK??ch th?????c (mm):	363.4 x 255 x 23.9', 'Acer Nitro Gaming AN515-57-57MX.jpg', 26500000, 10, 'Acer', 'Gaming'),
('SP09', 'Dell Inspiron N3501B', 'Dell Inspiron N3501B v???i ????? b???n ?????c tr??ng c???a Dell, trang b??? c???u h??nh m???i nh???t t??? b??? vi x??? l?? Intel th??? h??? th??? 11, ch???c ch???n s??? l?? s???n ph???m ????ng tin c???y trong c??ng vi???c d??nh cho b???n.', 'CPU:	Intel Core i5-1135G7\nRAM:	4 GB, DDR4, 2666 MHz\nM??n h??nh:	15.6\", 1920 x 1080 Pixel, WVA, 60 Hz\n????? h???a:	Intel Iris Xe Graphics\n??? c???ng:	SSD 512 GB\nH??? ??i???u h??nh:	Windows 10\nTr???ng l?????ng (kg):	1.96\nK??ch th?????c (mm):	363.96 x 249 x 19.9', 'Dell Inspiron N3501B.jpg', 22000000, 15, 'Dell', 'Home'),
('SP10', 'MSI Prestige 15 A11SC 037VN', '????? ng?????i d??ng c?? ???????c ph????ng ti???n l??m vi???c hi???u qu??? nh???t, nh??ng v???n ph???i thi???t k??? tinh x???o v?? th??? hi???n ?????ng c???p, MSI ???? t???o ra MSI Prestige 15 A11SC 037VN, m???u laptop m???ng nh??? nh??ng v???n v?? c??ng m???nh m???.', 'CPU:	Intel Core i7-1185G7\nRAM:	16 GB, DDR4, 3200 MHz\nM??n h??nh:	15.6\", 1920 x 1080 Pixel, IPS, 60 Hz\n????? h???a:	NVIDIA GeForce GTX 1650 Max-Q 4 GB \n??? c???ng:	SSD 512 GB\nH??? ??i???u h??nh:	Windows 10\nTr???ng l?????ng (kg):	1.693\nK??ch th?????c (mm):	356.8 x 233.7 x 16.9', 'MSI Prestige 15 A11SC 037VN.jpg', 22000000, 15, 'MSI', 'Business');

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
