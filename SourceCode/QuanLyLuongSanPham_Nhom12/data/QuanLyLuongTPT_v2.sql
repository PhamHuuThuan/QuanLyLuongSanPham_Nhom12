USE [master]
GO
/****** Object:  Database [QuanLyLuongSanPham-TPT]    Script Date: 11/2/2023 6:50:52 PM ******/
CREATE DATABASE [QuanLyLuongSanPham-TPT]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuanLyLuongSanPham-TPT', FILENAME = N'D:\database_TPT\QuanLyLuongSanPham-TPT.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuanLyLuongSanPham-TPT_log', FILENAME = N'D:\database_TPT\QuanLyLuongSanPham-TPT_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyLuongSanPham-TPT].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'QuanLyLuongSanPham-TPT', N'ON'
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET QUERY_STORE = OFF
GO
USE [QuanLyLuongSanPham-TPT]
GO
/****** Object:  Table [dbo].[BangChamCongCongNhan]    Script Date: 11/2/2023 6:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangChamCongCongNhan](
	[ngayChamCong] [date] NOT NULL,
	[gioVaoLam] [smalldatetime] NOT NULL,
	[maPhanCong] [nchar](10) NOT NULL,
	[soLuongLam] [int] NOT NULL,
	[ghiChu] [nvarchar](100) NULL,
 CONSTRAINT [PK_BangChamCongCongNhan] PRIMARY KEY CLUSTERED 
(
	[ngayChamCong] ASC,
	[gioVaoLam] ASC,
	[maPhanCong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BangChamCongNhanVien]    Script Date: 11/2/2023 6:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangChamCongNhanVien](
	[phanCongNV] [char](7) NOT NULL,
	[ngayChamCong] [date] NOT NULL,
	[caLam] [int] NOT NULL,
	[trangThai] [int] NOT NULL,
	[gioDen] [nvarchar](5) NULL,
	[gioTangCa] [float] NULL,
	[ghiChu] [nvarchar](100) NULL,
 CONSTRAINT [PK_BangChamCongNhanVien] PRIMARY KEY CLUSTERED 
(
	[phanCongNV] ASC,
	[ngayChamCong] ASC,
	[caLam] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BangLuongCongNhan]    Script Date: 11/2/2023 6:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangLuongCongNhan](
	[maBangLuong] [char](9) NOT NULL,
	[maCN] [char](7) NOT NULL,
	[soLuongCongDoanLam] [int] NOT NULL,
	[soNgayLam] [int] NOT NULL,
	[soNgayNghi] [int] NOT NULL,
	[soNgayPhep] [int] NOT NULL,
	[thucLanh] [decimal](18, 2) NULL,
	[thangNam] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_BangLuongCongNhan] PRIMARY KEY CLUSTERED 
(
	[maBangLuong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BangLuongNhanVien]    Script Date: 11/2/2023 6:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangLuongNhanVien](
	[maBangLuong] [char](9) NOT NULL,
	[maNhanVien] [char](7) NOT NULL,
	[soNgayLam] [float] NOT NULL,
	[soNgayNghi] [float] NOT NULL,
	[soNgayPhep] [float] NOT NULL,
	[luongThang] [decimal](18, 2) NOT NULL,
	[luongTangCa] [decimal](18, 2) NOT NULL,
	[phuCap] [decimal](18, 2) NOT NULL,
	[thucLanh] [decimal](18, 2) NOT NULL,
	[ghiChu] [nvarchar](100) NULL,
	[thangNam] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_BangLuongNhanVien] PRIMARY KEY CLUSTERED 
(
	[maBangLuong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BangPhanCongCongDoan]    Script Date: 11/2/2023 6:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangPhanCongCongDoan](
	[maPhanCong] [nchar](10) NOT NULL,
	[maCN] [char](7) NOT NULL,
	[maCongDoan] [char](9) NOT NULL,
	[ngayPhanCong] [date]NOT NULL,
	[soLuongCanLam] [int] NOT NULL,
	[ghiChu] [nvarchar](1000) NULL,
 CONSTRAINT [PK_BangPhanCongCongDoan] PRIMARY KEY CLUSTERED 
(
	[maPhanCong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BangPhanCongNhanVien]    Script Date: 11/2/2023 6:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangPhanCongNhanVien](
	[maPhanCong] [char](7) NOT NULL,
	[maNhanVien] [char](7) NOT NULL,
	[maPhongBan] [char](4) NULL,
	[chucVu] [nvarchar](30) NULL,
	[ngayCongTac] [date] NOT NULL,
	[ghiChu] [nvarchar](100) NULL,
 CONSTRAINT [PK_BangPhanCongNhanVien] PRIMARY KEY CLUSTERED 
(
	[maPhanCong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CongDoan]    Script Date: 11/2/2023 6:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongDoan](
	[maCD] [char](9) NOT NULL,
	[tenCD] [nvarchar](50) NOT NULL,
	[thuTu] [int] NOT NULL,
	[donGia] [decimal](18, 2) NOT NULL,
	[tinhTrang] [bit] NULL,
	[ngayHoanThanh] [date] NOT NULL,
	[maSP] [char](9) NOT NULL,
	[soLuong] [int] NOT NULL,
 CONSTRAINT [PK_CongDoan] PRIMARY KEY CLUSTERED 
(
	[maCD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CongNhan]    Script Date: 11/2/2023 6:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongNhan](
	[maCN] [char](7) NOT NULL,
	[matKhau] [nvarchar](30) NOT NULL,
	[hoTen] [nvarchar](50) NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[gioiTinh] [bit] NOT NULL,
	[sDT] [nvarchar](12) NOT NULL,
	[email] [varchar](254) NOT NULL,
	[diaChi] [nvarchar](50) NOT NULL,
	[soCCCD] [varchar](12) NOT NULL,
	[ngayVaoLam] [date] NOT NULL,
	[anhDaiDien] [nvarchar](128) NULL,
	[ghiChu] [nvarchar](80) NULL,
 CONSTRAINT [PK_CongNhan] PRIMARY KEY CLUSTERED 
(
	[maCN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HopDong]    Script Date: 11/2/2023 6:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HopDong](
	[maHD] [char](7) NOT NULL,
	[tenHD] [nvarchar](50) NOT NULL,
	[tenKH] [nvarchar](50) NOT NULL,
	[maNguoiDaiDien] [char](7) NOT NULL,
	[ngayBatDau] [date] NOT NULL,
	[ngayKetThuc] [date] NOT NULL,
	[giaTriHopDong] [decimal](18, 2) NOT NULL,
	[tienCoc] [decimal](18, 2) NULL,
	[thoaThuan] [nvarchar](100) NULL,
	[trangThai] [bit] NOT NULL,
	[ghiChu] [nvarchar](100) NULL,
 CONSTRAINT [PK_HopDong] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 11/2/2023 6:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [char](7) NOT NULL,
	[matKhau] [nvarchar](30) NOT NULL,
	[hoTen] [nvarchar](50) NOT NULL,
	[gioiTinh] [bit] NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[sDT] [nvarchar](12) NOT NULL,
	[email] [varchar](254) NULL,
	[soCCCD] [varchar](12) NOT NULL,
	[diaChi] [nvarchar](50) NOT NULL,
	[anhDaiDien] [nvarchar](128) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhongBan]    Script Date: 11/2/2023 6:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhongBan](
	[maPB] [char](4) NOT NULL,
	[tenPB] [nvarchar](50) NOT NULL,
	[soLuongNV] [int] NOT NULL,
	[moTa] [nvarchar](100) NULL,
 CONSTRAINT [PK_PhongBan] PRIMARY KEY CLUSTERED 
(
	[maPB] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 11/2/2023 6:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[maSP] [char](9) NOT NULL,
	[maHopDong] [char](7) NOT NULL,
	[tenSP] [nvarchar](50) NOT NULL,
	[donViTinh] [nvarchar](30) NOT NULL,
	[soLuong] [int] NOT NULL,
	[yeuCau] [nvarchar](128) NULL,
	[donGia] [decimal](18, 2) NOT NULL,
 CONSTRAINT [PK_SanPham] PRIMARY KEY CLUSTERED 
(
	[maSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[BangChamCongCongNhan]  WITH CHECK ADD  CONSTRAINT [FK_BangChamCongCongNhan_BangPhanCongCongDoan] FOREIGN KEY([maPhanCong])
REFERENCES [dbo].[BangPhanCongCongDoan] ([maPhanCong])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[BangChamCongCongNhan] CHECK CONSTRAINT [FK_BangChamCongCongNhan_BangPhanCongCongDoan]
GO
ALTER TABLE [dbo].[BangChamCongNhanVien]  WITH CHECK ADD  CONSTRAINT [FK_BangChamCongNhanVien_BangPhanCongNhanVien] FOREIGN KEY([phanCongNV])
REFERENCES [dbo].[BangPhanCongNhanVien] ([maPhanCong])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[BangChamCongNhanVien] CHECK CONSTRAINT [FK_BangChamCongNhanVien_BangPhanCongNhanVien]
GO
ALTER TABLE [dbo].[BangLuongCongNhan]  WITH CHECK ADD  CONSTRAINT [FK_BangLuongCongNhan_CongNhan] FOREIGN KEY([maCN])
REFERENCES [dbo].[CongNhan] ([maCN])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[BangLuongCongNhan] CHECK CONSTRAINT [FK_BangLuongCongNhan_CongNhan]
GO
ALTER TABLE [dbo].[BangLuongNhanVien]  WITH CHECK ADD  CONSTRAINT [FK_BangLuongNhanVien_NhanVien] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNV])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[BangLuongNhanVien] CHECK CONSTRAINT [FK_BangLuongNhanVien_NhanVien]
GO
ALTER TABLE [dbo].[BangPhanCongCongDoan]  WITH CHECK ADD  CONSTRAINT [FK_BangPhanCongCongDoan_CongDoan] FOREIGN KEY([maCongDoan])
REFERENCES [dbo].[CongDoan] ([maCD])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[BangPhanCongCongDoan] CHECK CONSTRAINT [FK_BangPhanCongCongDoan_CongDoan]
GO
ALTER TABLE [dbo].[BangPhanCongCongDoan]  WITH CHECK ADD  CONSTRAINT [FK_BangPhanCongCongDoan_CongNhan] FOREIGN KEY([maCN])
REFERENCES [dbo].[CongNhan] ([maCN])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[BangPhanCongCongDoan] CHECK CONSTRAINT [FK_BangPhanCongCongDoan_CongNhan]
GO
ALTER TABLE [dbo].[BangPhanCongNhanVien]  WITH CHECK ADD  CONSTRAINT [FK_BangPhanCongNhanVien_NhanVien] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNV])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[BangPhanCongNhanVien] CHECK CONSTRAINT [FK_BangPhanCongNhanVien_NhanVien]
GO
ALTER TABLE [dbo].[BangPhanCongNhanVien]  WITH CHECK ADD  CONSTRAINT [FK_BangPhanCongNhanVien_PhongBan] FOREIGN KEY([maPhongBan])
REFERENCES [dbo].[PhongBan] ([maPB])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[BangPhanCongNhanVien] CHECK CONSTRAINT [FK_BangPhanCongNhanVien_PhongBan]
GO
ALTER TABLE [dbo].[CongDoan]  WITH CHECK ADD  CONSTRAINT [FK_CongDoan_SanPham] FOREIGN KEY([maSP])
REFERENCES [dbo].[SanPham] ([maSP])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CongDoan] CHECK CONSTRAINT [FK_CongDoan_SanPham]
GO
ALTER TABLE [dbo].[HopDong]  WITH CHECK ADD  CONSTRAINT [FK_HopDong_NhanVien] FOREIGN KEY([maNguoiDaiDien])
REFERENCES [dbo].[NhanVien] ([maNV])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HopDong] CHECK CONSTRAINT [FK_HopDong_NhanVien]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_SanPham_HopDong] FOREIGN KEY([maHopDong])
REFERENCES [dbo].[HopDong] ([maHD])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_SanPham_HopDong]
GO
USE [master]
GO
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET  READ_WRITE 
GO
