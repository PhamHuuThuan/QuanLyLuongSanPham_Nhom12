USE [master]
GO
/****** Object:  Database [QuanLyLuongSanPham-TPT]    Script Date: 12/13/2023 11:56:21 PM ******/
CREATE DATABASE [QuanLyLuongSanPham-TPT]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuanLyLuongSanPham-TPT', FILENAME = N'D:\QuanLyLuongSanPham-TPT.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuanLyLuongSanPham-TPT_log', FILENAME = N'D:\QuanLyLuongSanPham-TPT_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
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
ALTER DATABASE [QuanLyLuongSanPham-TPT] SET QUERY_STORE = OFF
GO
USE [QuanLyLuongSanPham-TPT]
GO
/****** Object:  Table [dbo].[BangChamCongCongNhan]    Script Date: 12/13/2023 11:56:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangChamCongCongNhan](
	[ngayChamCong] [date] NOT NULL,
	[gioVaoLam] [nvarchar](6) NOT NULL,
	[maPhanCong] [nchar](10) NOT NULL,
	[soLuongLam] [int] NOT NULL,
	[ghiChu] [nvarchar](100) NULL,
	[trangThai] [int] NOT NULL,
 CONSTRAINT [PK_BangChamCongCongNhan] PRIMARY KEY CLUSTERED 
(
	[ngayChamCong] ASC,
	[gioVaoLam] ASC,
	[maPhanCong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BangChamCongNhanVien]    Script Date: 12/13/2023 11:56:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangChamCongNhanVien](
	[phanCongNV] [char](7) NOT NULL,
	[ngayChamCong] [date] NOT NULL,
	[caLam] [int] NOT NULL,
	[diLam] [bit] NOT NULL,
	[coPhep] [bit] NOT NULL,
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
/****** Object:  Table [dbo].[BangLuongCongNhan]    Script Date: 12/13/2023 11:56:21 PM ******/
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
	[luongThang] [decimal](18, 2) NOT NULL,
	[luongCongDoan] [decimal](18, 2) NOT NULL,
 CONSTRAINT [PK_BangLuongCongNhan] PRIMARY KEY CLUSTERED 
(
	[maBangLuong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BangLuongNhanVien]    Script Date: 12/13/2023 11:56:21 PM ******/
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
/****** Object:  Table [dbo].[BangPhanCongCongDoan]    Script Date: 12/13/2023 11:56:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangPhanCongCongDoan](
	[maPhanCong] [nchar](10) NOT NULL,
	[maCN] [char](7) NOT NULL,
	[maCongDoan] [char](9) NOT NULL,
	[ngayPhanCong] [date] NOT NULL,
	[soLuongCanLam] [int] NOT NULL,
	[ghiChu] [nvarchar](1000) NULL,
 CONSTRAINT [PK_BangPhanCongCongDoan] PRIMARY KEY CLUSTERED 
(
	[maPhanCong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BangPhanCongNhanVien]    Script Date: 12/13/2023 11:56:21 PM ******/
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
/****** Object:  Table [dbo].[CongDoan]    Script Date: 12/13/2023 11:56:21 PM ******/
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
/****** Object:  Table [dbo].[CongNhan]    Script Date: 12/13/2023 11:56:21 PM ******/
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
/****** Object:  Table [dbo].[HopDong]    Script Date: 12/13/2023 11:56:21 PM ******/
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
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/13/2023 11:56:21 PM ******/
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
/****** Object:  Table [dbo].[PhongBan]    Script Date: 12/13/2023 11:56:21 PM ******/
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
/****** Object:  Table [dbo].[SanPham]    Script Date: 12/13/2023 11:56:21 PM ******/
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
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-10-06' AS Date), N'08:00 ', N'PCD0000010', 64, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-10-13' AS Date), N'07:00 ', N'PCD0000008', 3, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-11-04' AS Date), N'08:00 ', N'PCD0000001', 20, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-11-04' AS Date), N'08:00 ', N'PCD0000009', 10, N'1', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-11-09' AS Date), N'08:00 ', N'PCD0000006', 2, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-11-11' AS Date), N'08:00 ', N'PCD0000006', 90, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-11-12' AS Date), N'07:00 ', N'PCD0000006', 16, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-11-12' AS Date), N'08:00 ', N'PCD0000005', 22, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-11-12' AS Date), N'09:00 ', N'PCD0000004', 6, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-11-13' AS Date), N'07:00 ', N'PCD0000005', 21, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-11-13' AS Date), N'08:00 ', N'PCD0000006', 26, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-11-13' AS Date), N'09:00 ', N'PCD0000007', 19, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-11-30' AS Date), N'08:00 ', N'PCD0000006', 8, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-01' AS Date), N'08:00 ', N'PCD0000009', 0, N'', 1)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-02' AS Date), N'08:00 ', N'PCD0000004', 0, N'', 2)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-02' AS Date), N'08:00 ', N'PCD0000006', 1, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-02' AS Date), N'08:00 ', N'PCD0000008', 2, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-02' AS Date), N'08:00 ', N'PCD0000009', 5, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-03' AS Date), N'08:00 ', N'PCD0000004', 0, N'', 2)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-04' AS Date), N'08:00 ', N'PCD0000004', 44, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-05' AS Date), N'08:00 ', N'PCD0000004', 0, N'', 1)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-05' AS Date), N'08:00 ', N'PCD0000009', 4, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-06' AS Date), N'08:00 ', N'PCD0000004', 12, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-06' AS Date), N'08:00 ', N'PCD0000009', 0, N'', 2)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-07' AS Date), N'08:00 ', N'PCD0000004', 77, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-08' AS Date), N'08:00 ', N'PCD0000009', 12, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-09' AS Date), N'08:00 ', N'PCD0000001', 77, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-09' AS Date), N'08:00 ', N'PCD0000002', 0, N'', 2)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-09' AS Date), N'08:00 ', N'PCD0000009', 2, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-11' AS Date), N'08:00 ', N'PCD0000010', 18, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-12' AS Date), N'08:00 ', N'PCD0000010', 80, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-13' AS Date), N'08:00 ', N'PCD0000008', 12, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-13' AS Date), N'08:00 ', N'PCD0000010', 22, N'', 0)
INSERT [dbo].[BangChamCongCongNhan] ([ngayChamCong], [gioVaoLam], [maPhanCong], [soLuongLam], [ghiChu], [trangThai]) VALUES (CAST(N'2023-12-13' AS Date), N'08:00 ', N'PCD0000011', 10, N'', 0)
GO
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-04-19' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-04-20' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-04-21' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-04-22' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-05-08' AS Date), 2, 0, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-02' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-03' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-04' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-05' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-06' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-07' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-08' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-09' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-10' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-11' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-12' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-13' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-14' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-15' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-16' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-17' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-18' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-19' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-20' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-21' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-22' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-24' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-25' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-27' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-28' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-08-31' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-02' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-03' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-04' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-05' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-06' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-08' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-11' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-12' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-13' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-16' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-18' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-19' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-20' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-21' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-22' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-24' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-27' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-28' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-29' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-09-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-02' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-03' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-16' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-17' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-18' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-19' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-20' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-21' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-22' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-24' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-25' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-27' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-28' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-10-31' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-11-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-11-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00001', CAST(N'2023-12-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00002', CAST(N'2023-10-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00002', CAST(N'2023-10-25' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00002', CAST(N'2023-10-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00002', CAST(N'2023-11-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00002', CAST(N'2023-11-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00002', CAST(N'2023-12-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00003', CAST(N'2023-10-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00003', CAST(N'2023-10-25' AS Date), 2, 0, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00003', CAST(N'2023-10-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00003', CAST(N'2023-11-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00003', CAST(N'2023-11-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00003', CAST(N'2023-12-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00004', CAST(N'2023-10-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00004', CAST(N'2023-10-25' AS Date), 2, 0, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00004', CAST(N'2023-10-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00004', CAST(N'2023-11-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00004', CAST(N'2023-11-30' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00004', CAST(N'2023-12-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00005', CAST(N'2023-10-23' AS Date), 2, 0, 0, N'07:00', 0, N'')
GO
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00005', CAST(N'2023-10-25' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00005', CAST(N'2023-10-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00005', CAST(N'2023-10-27' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00005', CAST(N'2023-10-28' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00005', CAST(N'2023-10-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00005', CAST(N'2023-10-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00005', CAST(N'2023-10-31' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00005', CAST(N'2023-11-29' AS Date), 2, 0, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00005', CAST(N'2023-11-30' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00005', CAST(N'2023-12-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00006', CAST(N'2023-10-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00006', CAST(N'2023-10-25' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00006', CAST(N'2023-10-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00006', CAST(N'2023-10-27' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00006', CAST(N'2023-10-28' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00006', CAST(N'2023-10-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00006', CAST(N'2023-10-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00006', CAST(N'2023-10-31' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00006', CAST(N'2023-11-29' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00006', CAST(N'2023-11-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00006', CAST(N'2023-12-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00007', CAST(N'2023-10-23' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00007', CAST(N'2023-10-25' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00007', CAST(N'2023-10-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00007', CAST(N'2023-10-27' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00007', CAST(N'2023-10-28' AS Date), 2, 0, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00007', CAST(N'2023-10-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00007', CAST(N'2023-10-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00007', CAST(N'2023-10-31' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00007', CAST(N'2023-11-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00007', CAST(N'2023-11-30' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00007', CAST(N'2023-12-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00008', CAST(N'2023-10-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00008', CAST(N'2023-10-25' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00008', CAST(N'2023-10-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00008', CAST(N'2023-10-27' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00008', CAST(N'2023-10-28' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00008', CAST(N'2023-10-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00008', CAST(N'2023-10-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00008', CAST(N'2023-10-31' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00008', CAST(N'2023-11-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00008', CAST(N'2023-11-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00008', CAST(N'2023-12-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-15' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-16' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-17' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-18' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-19' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-20' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-21' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-22' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-24' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-25' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-27' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-28' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-08-31' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-02' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-03' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-04' AS Date), 2, 0, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-05' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-06' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-08' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-11' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-12' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-13' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-16' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-18' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-19' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-20' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-21' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-22' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-24' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-27' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-28' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-09-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-02' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-03' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-16' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-17' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-18' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-19' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-20' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-21' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-22' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-24' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-28' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00009', CAST(N'2023-10-31' AS Date), 2, 1, 0, N'07:00', 0, N'')
GO
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-04' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-05' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-06' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-07' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-08' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-09' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-10' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-11' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-12' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-13' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-14' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-15' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-16' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-17' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-18' AS Date), 2, 0, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-19' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-20' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-21' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-22' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-24' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-25' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-27' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-28' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-08-31' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-02' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-03' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-04' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-05' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-06' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-08' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-11' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-12' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-13' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-16' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-18' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-19' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-20' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-21' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-22' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-24' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-27' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-28' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-29' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-09-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-01' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-02' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-03' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-16' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-17' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-18' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-19' AS Date), 2, 0, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-20' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-21' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-22' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-23' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-24' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-26' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-28' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-29' AS Date), 2, 0, 1, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-30' AS Date), 2, 1, 0, N'07:00', 0, N'')
INSERT [dbo].[BangChamCongNhanVien] ([phanCongNV], [ngayChamCong], [caLam], [diLam], [coPhep], [gioDen], [gioTangCa], [ghiChu]) VALUES (N'PC00010', CAST(N'2023-10-31' AS Date), 2, 1, 0, N'07:00', 0, N'')
GO
INSERT [dbo].[BangLuongCongNhan] ([maBangLuong], [maCN], [soLuongCongDoanLam], [soNgayLam], [soNgayNghi], [soNgayPhep], [thucLanh], [thangNam], [luongThang], [luongCongDoan]) VALUES (N'LC0000001', N'CN00004', 6, 1, 0, 0, CAST(430000.00 AS Decimal(18, 2)), N'11/2023', CAST(250000.00 AS Decimal(18, 2)), CAST(180000.00 AS Decimal(18, 2)))
INSERT [dbo].[BangLuongCongNhan] ([maBangLuong], [maCN], [soLuongCongDoanLam], [soNgayLam], [soNgayNghi], [soNgayPhep], [thucLanh], [thangNam], [luongThang], [luongCongDoan]) VALUES (N'LC0000002', N'CN00006', 43, 2, 0, 0, CAST(672000.00 AS Decimal(18, 2)), N'11/2023', CAST(500000.00 AS Decimal(18, 2)), CAST(172000.00 AS Decimal(18, 2)))
INSERT [dbo].[BangLuongCongNhan] ([maBangLuong], [maCN], [soLuongCongDoanLam], [soNgayLam], [soNgayNghi], [soNgayPhep], [thucLanh], [thangNam], [luongThang], [luongCongDoan]) VALUES (N'LC0000003', N'CN00009', 20, 1, 0, 0, CAST(330000.00 AS Decimal(18, 2)), N'11/2023', CAST(250000.00 AS Decimal(18, 2)), CAST(80000.00 AS Decimal(18, 2)))
INSERT [dbo].[BangLuongCongNhan] ([maBangLuong], [maCN], [soLuongCongDoanLam], [soNgayLam], [soNgayNghi], [soNgayPhep], [thucLanh], [thangNam], [luongThang], [luongCongDoan]) VALUES (N'LC0000004', N'CN00013', 10, 1, 0, 0, CAST(310000.00 AS Decimal(18, 2)), N'11/2023', CAST(250000.00 AS Decimal(18, 2)), CAST(60000.00 AS Decimal(18, 2)))
INSERT [dbo].[BangLuongCongNhan] ([maBangLuong], [maCN], [soLuongCongDoanLam], [soNgayLam], [soNgayNghi], [soNgayPhep], [thucLanh], [thangNam], [luongThang], [luongCongDoan]) VALUES (N'LC0000005', N'CN00014', 142, 5, 0, 0, CAST(1392000.00 AS Decimal(18, 2)), N'11/2023', CAST(1250000.00 AS Decimal(18, 2)), CAST(142000.00 AS Decimal(18, 2)))
INSERT [dbo].[BangLuongCongNhan] ([maBangLuong], [maCN], [soLuongCongDoanLam], [soNgayLam], [soNgayNghi], [soNgayPhep], [thucLanh], [thangNam], [luongThang], [luongCongDoan]) VALUES (N'LC0000006', N'CN00014', 1, 1, 0, 0, CAST(251000.00 AS Decimal(18, 2)), N'12/2023', CAST(250000.00 AS Decimal(18, 2)), CAST(1000.00 AS Decimal(18, 2)))
INSERT [dbo].[BangLuongCongNhan] ([maBangLuong], [maCN], [soLuongCongDoanLam], [soNgayLam], [soNgayNghi], [soNgayPhep], [thucLanh], [thangNam], [luongThang], [luongCongDoan]) VALUES (N'LC0000007', N'CN00015', 120, 3, 0, 0, CAST(1470000.00 AS Decimal(18, 2)), N'12/2023', CAST(750000.00 AS Decimal(18, 2)), CAST(720000.00 AS Decimal(18, 2)))
INSERT [dbo].[BangLuongCongNhan] ([maBangLuong], [maCN], [soLuongCongDoanLam], [soNgayLam], [soNgayNghi], [soNgayPhep], [thucLanh], [thangNam], [luongThang], [luongCongDoan]) VALUES (N'LC0000008', N'CN00016', 10, 1, 0, 0, CAST(260000.00 AS Decimal(18, 2)), N'12/2023', CAST(250000.00 AS Decimal(18, 2)), CAST(10000.00 AS Decimal(18, 2)))
GO
INSERT [dbo].[BangLuongNhanVien] ([maBangLuong], [maNhanVien], [soNgayLam], [soNgayNghi], [soNgayPhep], [luongThang], [luongTangCa], [phuCap], [thucLanh], [ghiChu], [thangNam]) VALUES (N'LN0000001', N'NV00001', 30, 0, 1, CAST(10333333.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(10333333.00 AS Decimal(18, 2)), NULL, N'08/2023')
INSERT [dbo].[BangLuongNhanVien] ([maBangLuong], [maNhanVien], [soNgayLam], [soNgayNghi], [soNgayPhep], [luongThang], [luongTangCa], [phuCap], [thucLanh], [ghiChu], [thangNam]) VALUES (N'LN0000002', N'NV00014', 16, 0, 1, CAST(5666666.50 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(5666666.50 AS Decimal(18, 2)), NULL, N'08/2023')
INSERT [dbo].[BangLuongNhanVien] ([maBangLuong], [maNhanVien], [soNgayLam], [soNgayNghi], [soNgayPhep], [luongThang], [luongTangCa], [phuCap], [thucLanh], [ghiChu], [thangNam]) VALUES (N'LN0000003', N'NV00013', 26, 1, 1, CAST(6066666.50 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(6066666.50 AS Decimal(18, 2)), NULL, N'08/2023')
INSERT [dbo].[BangLuongNhanVien] ([maBangLuong], [maNhanVien], [soNgayLam], [soNgayNghi], [soNgayPhep], [luongThang], [luongTangCa], [phuCap], [thucLanh], [ghiChu], [thangNam]) VALUES (N'LN0000004', N'NV00001', 22, 0, 1, CAST(7666666.50 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(7666666.50 AS Decimal(18, 2)), NULL, N'09/2023')
INSERT [dbo].[BangLuongNhanVien] ([maBangLuong], [maNhanVien], [soNgayLam], [soNgayNghi], [soNgayPhep], [luongThang], [luongTangCa], [phuCap], [thucLanh], [ghiChu], [thangNam]) VALUES (N'LN0000005', N'NV00014', 21, 1, 1, CAST(7000000.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(7000000.00 AS Decimal(18, 2)), NULL, N'09/2023')
INSERT [dbo].[BangLuongNhanVien] ([maBangLuong], [maNhanVien], [soNgayLam], [soNgayNghi], [soNgayPhep], [luongThang], [luongTangCa], [phuCap], [thucLanh], [ghiChu], [thangNam]) VALUES (N'LN0000006', N'NV00013', 21, 0, 2, CAST(5366666.50 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(5366666.50 AS Decimal(18, 2)), NULL, N'09/2023')
INSERT [dbo].[BangLuongNhanVien] ([maBangLuong], [maNhanVien], [soNgayLam], [soNgayNghi], [soNgayPhep], [luongThang], [luongTangCa], [phuCap], [thucLanh], [ghiChu], [thangNam]) VALUES (N'LN0000007', N'NV00009', 1, 0, 0, CAST(133333.34 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(133333.34 AS Decimal(18, 2)), NULL, N'11/2023')
INSERT [dbo].[BangLuongNhanVien] ([maBangLuong], [maNhanVien], [soNgayLam], [soNgayNghi], [soNgayPhep], [luongThang], [luongTangCa], [phuCap], [thucLanh], [ghiChu], [thangNam]) VALUES (N'LN0000008', N'NV00010', 1, 0, 0, CAST(133333.34 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(133333.34 AS Decimal(18, 2)), NULL, N'11/2023')
INSERT [dbo].[BangLuongNhanVien] ([maBangLuong], [maNhanVien], [soNgayLam], [soNgayNghi], [soNgayPhep], [luongThang], [luongTangCa], [phuCap], [thucLanh], [ghiChu], [thangNam]) VALUES (N'LN0000009', N'NV00001', 1, 0, 0, CAST(333333.34 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(333333.34 AS Decimal(18, 2)), NULL, N'10/2023')
GO
INSERT [dbo].[BangPhanCongCongDoan] ([maPhanCong], [maCN], [maCongDoan], [ngayPhanCong], [soLuongCanLam], [ghiChu]) VALUES (N'PCD0000001', N'CN00009', N'CD0000002', CAST(N'2023-12-01' AS Date), 100, N'')
INSERT [dbo].[BangPhanCongCongDoan] ([maPhanCong], [maCN], [maCongDoan], [ngayPhanCong], [soLuongCanLam], [ghiChu]) VALUES (N'PCD0000002', N'CN00010', N'CD0000004', CAST(N'2023-12-01' AS Date), 90, N'')
INSERT [dbo].[BangPhanCongCongDoan] ([maPhanCong], [maCN], [maCongDoan], [ngayPhanCong], [soLuongCanLam], [ghiChu]) VALUES (N'PCD0000003', N'CN00007', N'CD0000002', CAST(N'2023-12-02' AS Date), 80, N'')
INSERT [dbo].[BangPhanCongCongDoan] ([maPhanCong], [maCN], [maCongDoan], [ngayPhanCong], [soLuongCanLam], [ghiChu]) VALUES (N'PCD0000004', N'CN00004', N'CD0000006', CAST(N'2023-12-02' AS Date), 210, N'')
INSERT [dbo].[BangPhanCongCongDoan] ([maPhanCong], [maCN], [maCongDoan], [ngayPhanCong], [soLuongCanLam], [ghiChu]) VALUES (N'PCD0000005', N'CN00006', N'CD0000002', CAST(N'2023-12-05' AS Date), 30, N'')
INSERT [dbo].[BangPhanCongCongDoan] ([maPhanCong], [maCN], [maCongDoan], [ngayPhanCong], [soLuongCanLam], [ghiChu]) VALUES (N'PCD0000006', N'CN00014', N'CD0000001', CAST(N'2023-12-05' AS Date), 200, N'')
INSERT [dbo].[BangPhanCongCongDoan] ([maPhanCong], [maCN], [maCongDoan], [ngayPhanCong], [soLuongCanLam], [ghiChu]) VALUES (N'PCD0000008', N'CN00008', N'CD0000002', CAST(N'2023-12-05' AS Date), 12, N'')
INSERT [dbo].[BangPhanCongCongDoan] ([maPhanCong], [maCN], [maCongDoan], [ngayPhanCong], [soLuongCanLam], [ghiChu]) VALUES (N'PCD0000009', N'CN00013', N'CD0000003', CAST(N'2023-12-05' AS Date), 28, N'')
INSERT [dbo].[BangPhanCongCongDoan] ([maPhanCong], [maCN], [maCongDoan], [ngayPhanCong], [soLuongCanLam], [ghiChu]) VALUES (N'PCD0000010', N'CN00015', N'CD0000003', CAST(N'2023-12-13' AS Date), 200, N'')
INSERT [dbo].[BangPhanCongCongDoan] ([maPhanCong], [maCN], [maCongDoan], [ngayPhanCong], [soLuongCanLam], [ghiChu]) VALUES (N'PCD0000011', N'CN00016', N'CD0000001', CAST(N'2023-12-13' AS Date), 20, N'')
GO
INSERT [dbo].[BangPhanCongNhanVien] ([maPhanCong], [maNhanVien], [maPhongBan], [chucVu], [ngayCongTac], [ghiChu]) VALUES (N'PC00001', N'NV00001', N'PB01', N'Quản lý', CAST(N'2023-01-01' AS Date), N'')
INSERT [dbo].[BangPhanCongNhanVien] ([maPhanCong], [maNhanVien], [maPhongBan], [chucVu], [ngayCongTac], [ghiChu]) VALUES (N'PC00002', N'NV00002', N'PB04', N'Nhân viên', CAST(N'2023-11-01' AS Date), N'')
INSERT [dbo].[BangPhanCongNhanVien] ([maPhanCong], [maNhanVien], [maPhongBan], [chucVu], [ngayCongTac], [ghiChu]) VALUES (N'PC00003', N'NV00004', N'PB04', N'Nhân viên', CAST(N'2023-11-02' AS Date), N'')
INSERT [dbo].[BangPhanCongNhanVien] ([maPhanCong], [maNhanVien], [maPhongBan], [chucVu], [ngayCongTac], [ghiChu]) VALUES (N'PC00004', N'NV00005', N'PB02', N'Nhân viên', CAST(N'2023-11-05' AS Date), N'')
INSERT [dbo].[BangPhanCongNhanVien] ([maPhanCong], [maNhanVien], [maPhongBan], [chucVu], [ngayCongTac], [ghiChu]) VALUES (N'PC00005', N'NV00007', N'PB02', N'Quản lý', CAST(N'2023-10-25' AS Date), N'')
INSERT [dbo].[BangPhanCongNhanVien] ([maPhanCong], [maNhanVien], [maPhongBan], [chucVu], [ngayCongTac], [ghiChu]) VALUES (N'PC00006', N'NV00008', N'PB04', N'Quản lý', CAST(N'2023-10-25' AS Date), N'')
INSERT [dbo].[BangPhanCongNhanVien] ([maPhanCong], [maNhanVien], [maPhongBan], [chucVu], [ngayCongTac], [ghiChu]) VALUES (N'PC00007', N'NV00009', N'PB04', N'Thực tập sinh', CAST(N'2023-10-25' AS Date), N'')
INSERT [dbo].[BangPhanCongNhanVien] ([maPhanCong], [maNhanVien], [maPhongBan], [chucVu], [ngayCongTac], [ghiChu]) VALUES (N'PC00008', N'NV00010', N'PB01', N'Thực tập sinh', CAST(N'2023-10-25' AS Date), N'')
INSERT [dbo].[BangPhanCongNhanVien] ([maPhanCong], [maNhanVien], [maPhongBan], [chucVu], [ngayCongTac], [ghiChu]) VALUES (N'PC00009', N'NV00014', N'PB05', N'Quản lý', CAST(N'2023-08-15' AS Date), N'')
INSERT [dbo].[BangPhanCongNhanVien] ([maPhanCong], [maNhanVien], [maPhongBan], [chucVu], [ngayCongTac], [ghiChu]) VALUES (N'PC00010', N'NV00013', N'PB06', N'Nhân viên', CAST(N'2023-08-04' AS Date), N'')
GO
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000001', N'Ép phun', 3, CAST(1000.00 AS Decimal(18, 2)), 0, CAST(N'2023-11-30' AS Date), N'SP0000001', 300)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000002', N'Lắp ráp', 4, CAST(4000.00 AS Decimal(18, 2)), 0, CAST(N'2023-11-30' AS Date), N'SP0000002', 250)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000003', N'Nghiệm thu', 6, CAST(6000.00 AS Decimal(18, 2)), 0, CAST(N'2023-11-30' AS Date), N'SP0000003', 318)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000004', N'Lựa chọn vật liệu', 1, CAST(3000.00 AS Decimal(18, 2)), 0, CAST(N'2023-12-14' AS Date), N'SP0000004', 580)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000005', N'Gia Công', 2, CAST(7000.00 AS Decimal(18, 2)), 0, CAST(N'2024-11-30' AS Date), N'SP0000003', 222)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000006', N'Lựa chọn vật liệu', 1, CAST(30000.00 AS Decimal(18, 2)), 0, CAST(N'2023-11-30' AS Date), N'SP0000001', 210)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000007', N'Lắp ráp', 4, CAST(12000.00 AS Decimal(18, 2)), 0, CAST(N'2023-11-24' AS Date), N'SP0000007', 160)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000008', N'Gia Công', 2, CAST(8000.00 AS Decimal(18, 2)), 0, CAST(N'2024-01-28' AS Date), N'SP0000001', 210)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000009', N'Lắp ráp', 4, CAST(40000.00 AS Decimal(18, 2)), 0, CAST(N'2023-12-14' AS Date), N'SP0000001', 110)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000010', N'Lựa chọn vật liệu', 1, CAST(2000.00 AS Decimal(18, 2)), 0, CAST(N'2023-12-13' AS Date), N'SP0000002', 210)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000011', N'Ép phun 2', 3, CAST(1970.00 AS Decimal(18, 2)), 0, CAST(N'2023-12-21' AS Date), N'SP0000002', 60)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000012', N'Lựa chọn vật liệu', 2, CAST(1200.00 AS Decimal(18, 2)), 0, CAST(N'2023-12-06' AS Date), N'SP0000003', 60)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000013', N'Lựa chọn vật liệu', 1, CAST(1200.00 AS Decimal(18, 2)), 0, CAST(N'2024-02-10' AS Date), N'SP0000007', 33)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000014', N'Phun Sơn 2 màu', 3, CAST(1800.00 AS Decimal(18, 2)), 0, CAST(N'2023-12-07' AS Date), N'SP0000007', 67)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000015', N'Lựa chọn vật liệu', 1, CAST(3000.00 AS Decimal(18, 2)), 0, CAST(N'2024-02-15' AS Date), N'SP0000008', 110)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000016', N'Nghiệm thu', 5, CAST(2000.00 AS Decimal(18, 2)), 0, CAST(N'2024-01-20' AS Date), N'SP0000008', 30)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000017', N'Hoàn thiện cuối', 2, CAST(980.00 AS Decimal(18, 2)), 0, CAST(N'2023-12-31' AS Date), N'SP0000008', 130)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000018', N'Nghiệm thu', 5, CAST(1100.00 AS Decimal(18, 2)), 0, CAST(N'2024-02-17' AS Date), N'SP0000008', 110)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000019', N'Lựa chọn vậy liệu', 1, CAST(890.00 AS Decimal(18, 2)), 0, CAST(N'2024-01-20' AS Date), N'SP0000012', 110)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000020', N'Đóng khung', 2, CAST(1900.00 AS Decimal(18, 2)), 0, CAST(N'2024-02-10' AS Date), N'SP0000012', 110)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000021', N'Vận chuển kho', 3, CAST(3000.00 AS Decimal(18, 2)), 0, CAST(N'2024-01-14' AS Date), N'SP0000012', 110)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000022', N'Hình thành khung', 4, CAST(8900.00 AS Decimal(18, 2)), 0, CAST(N'2024-01-07' AS Date), N'SP0000012', 110)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000023', N'Lựa chọn vật liệu', 1, CAST(3000.00 AS Decimal(18, 2)), 0, CAST(N'2023-12-08' AS Date), N'SP0000013', 270)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000024', N'Nung nhiệt độ nhẹ', 2, CAST(8000.00 AS Decimal(18, 2)), 0, CAST(N'2024-01-07' AS Date), N'SP0000013', 470)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000025', N'Lựa chọn giá', 1, CAST(9900.00 AS Decimal(18, 2)), 0, CAST(N'2023-12-17' AS Date), N'SP0000014', 70)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000026', N'Đóng khung 2', 2, CAST(8000.00 AS Decimal(18, 2)), 0, CAST(N'2024-02-03' AS Date), N'SP0000014', 87)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000027', N'Lựa chọn vật tư', 1, CAST(8900.00 AS Decimal(18, 2)), 0, CAST(N'2024-01-14' AS Date), N'SP0000015', 287)
INSERT [dbo].[CongDoan] ([maCD], [tenCD], [thuTu], [donGia], [tinhTrang], [ngayHoanThanh], [maSP], [soLuong]) VALUES (N'CD0000028', N'Xếp bó chân', 2, CAST(9000.00 AS Decimal(18, 2)), 0, CAST(N'2024-01-13' AS Date), N'SP0000015', 287)
GO
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00001', N'111111', N'Nguyễn Văn Phong', CAST(N'2003-01-10' AS Date), 1, N'0987866788', N'phong@gmail.com', N'33 Nguyễn Văn Bảo, TP HCM', N'123324567678', CAST(N'2023-11-06' AS Date), N'image_cn_df.jpg', N'')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00002', N'111111', N'Nguyễn Văn Hùng', CAST(N'2001-12-09' AS Date), 0, N'0987828647', N'hung@gmail.com', N'Tri Phương, TP. Đà Nẵn', N'123322237999', CAST(N'2023-11-06' AS Date), N'i42.jpg', N'dân tộc')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00003', N'111111', N'Lương Thị Hoa', CAST(N'1999-11-11' AS Date), 1, N'0987776666', N'hoaLuong@gmail.com', N'TP. Hà nội', N'898767564343', CAST(N'2023-11-06' AS Date), N'i28.jpg', N'')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00004', N'111111', N'Vũ Nam Văn', CAST(N'1982-01-02' AS Date), 1, N'0911091877', N'vanvu1982@gmail.com', N'12 Nguyễn Tri Phương, Thái Nguyên ', N'898722222346', CAST(N'2023-11-06' AS Date), N'i37.jpg', N'')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00006', N'111111', N'Đặng Thuận', CAST(N'2001-04-17' AS Date), 0, N'0813455232', N'notepad@gmail.com', N'221 Nguyễn Minh Khai, Hà Tây', N'234412322421', CAST(N'2023-11-06' AS Date), N'i40.jpg', N'')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00007', N'111111', N'Hảo Hùng Hải', CAST(N'1996-11-09' AS Date), 1, N'0987866788', N'abcHungHai@gmail.com', N'quận Gò Vấp, TP. HCM', N'123321232346', CAST(N'2023-11-06' AS Date), N'image_cn_df.jpg', N'dan toc')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00008', N'111111', N'Bảy Ba Rúa', CAST(N'2000-01-04' AS Date), 0, N'0914567822', N'ruarua111@gmail.com', N'Dương Quảng Hàm', N'123321321244', CAST(N'2023-11-06' AS Date), N'image_cn_df.jpg', N'')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00009', N'123456', N'Nguyễn Văn Định', CAST(N'1999-12-01' AS Date), 1, N'0997899765', N'nguyen@gmail.com', N'12, nguyen van bảo', N'123123123123', CAST(N'2023-11-10' AS Date), N'i20.jpg', N'')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00010', N'123123', N'Trần Định Cứ', CAST(N'1999-11-19' AS Date), 1, N'0988766786', N'cunguyencute@gmail.com', N'Hoàng Định Lợi, TP.HCM', N'123543645674', CAST(N'2023-11-12' AS Date), N'i42.jpg', N'')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00011', N'123123', N'Xuân Sắc Nhai', CAST(N'1998-09-19' AS Date), 0, N'0887655656', N'nhaiTienTi@gmai.com', N'12 Hoàng Văn Tin, quận Gò Vấp, tp.HCM', N'786567999723', CAST(N'2023-11-12' AS Date), N'i42.jpg', N'')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00012', N'123123123', N'Đăng Duy Diệu', CAST(N'2000-01-01' AS Date), 1, N'0911876545', N'abc@gmail.com', N'', N'123123123123', CAST(N'2023-12-02' AS Date), N'image_cn_df.jpg', N'')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00013', N'111111', N'Nguyễn Văn Tường', CAST(N'2000-01-01' AS Date), 1, N'0886212356', N'nnguyenDang@gmail.com', N'Đăng Văn Trương, Hà Đông, Hà Nội', N'324534567767', CAST(N'2023-12-05' AS Date), N'i29.jpg', N'')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00014', N'1111111', N'Đăng Hải Chung', CAST(N'2000-01-01' AS Date), 1, N'0986524534', N'chungnguyenhuy@gmail.com', N'', N'123455334233', CAST(N'2023-12-05' AS Date), N'i42.jpg', N'')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00015', N'111111', N'Hồ Như Trọng', CAST(N'2000-01-01' AS Date), 1, N'0987823454', N'trongYeu@gmail.com', N'', N'123123456678', CAST(N'2023-12-13' AS Date), N'i37.jpg', N'')
INSERT [dbo].[CongNhan] ([maCN], [matKhau], [hoTen], [ngaySinh], [gioiTinh], [sDT], [email], [diaChi], [soCCCD], [ngayVaoLam], [anhDaiDien], [ghiChu]) VALUES (N'CN00016', N'12345a@', N'Nguyễn Hữu Tình', CAST(N'1997-03-22' AS Date), 1, N'0112233444', N'huutinh@gmail.com', N'Quận 12, TP HCM', N'064223344556', CAST(N'2023-12-13' AS Date), N'i42.jpg', N'')
GO
INSERT [dbo].[HopDong] ([maHD], [tenHD], [tenKH], [maNguoiDaiDien], [ngayBatDau], [ngayKetThuc], [giaTriHopDong], [tienCoc], [thoaThuan], [trangThai], [ghiChu]) VALUES (N'HD00001', N'Hợp tác kinh doanh', N'Nguyễn Văn Trọng', N'NV00001', CAST(N'2023-10-31' AS Date), CAST(N'2023-11-23' AS Date), CAST(20000000000.00 AS Decimal(18, 2)), CAST(10000000.00 AS Decimal(18, 2)), N'', 0, N'')
INSERT [dbo].[HopDong] ([maHD], [tenHD], [tenKH], [maNguoiDaiDien], [ngayBatDau], [ngayKetThuc], [giaTriHopDong], [tienCoc], [thoaThuan], [trangThai], [ghiChu]) VALUES (N'HD00002', N'Hợp đồng liên doanh', N'Công ty Đức Đạt', N'NV00001', CAST(N'2023-11-06' AS Date), CAST(N'2024-02-22' AS Date), CAST(100000000.00 AS Decimal(18, 2)), CAST(50000000.00 AS Decimal(18, 2)), N'', 0, N'')
INSERT [dbo].[HopDong] ([maHD], [tenHD], [tenKH], [maNguoiDaiDien], [ngayBatDau], [ngayKetThuc], [giaTriHopDong], [tienCoc], [thoaThuan], [trangThai], [ghiChu]) VALUES (N'HD00003', N'Hợp đồng hợp tác 102', N'Công ty CP Á Châu', N'NV00001', CAST(N'2023-11-05' AS Date), CAST(N'2023-11-23' AS Date), CAST(5000000000.00 AS Decimal(18, 2)), CAST(1000000000.00 AS Decimal(18, 2)), N'', 0, N'')
INSERT [dbo].[HopDong] ([maHD], [tenHD], [tenKH], [maNguoiDaiDien], [ngayBatDau], [ngayKetThuc], [giaTriHopDong], [tienCoc], [thoaThuan], [trangThai], [ghiChu]) VALUES (N'HD00004', N'Hợp tác quốc tế', N'Công ty Toàn Cầu', N'NV00001', CAST(N'2023-11-05' AS Date), CAST(N'2023-11-05' AS Date), CAST(50000000000.00 AS Decimal(18, 2)), CAST(2000000000.00 AS Decimal(18, 2)), N'', 0, N'')
INSERT [dbo].[HopDong] ([maHD], [tenHD], [tenKH], [maNguoiDaiDien], [ngayBatDau], [ngayKetThuc], [giaTriHopDong], [tienCoc], [thoaThuan], [trangThai], [ghiChu]) VALUES (N'HD00005', N'Hợp đồng gia công', N'Công ty SBC', N'NV00001', CAST(N'2023-11-13' AS Date), CAST(N'2024-06-12' AS Date), CAST(20000000000.00 AS Decimal(18, 2)), CAST(1000000000.00 AS Decimal(18, 2)), N'', 0, N'')
INSERT [dbo].[HopDong] ([maHD], [tenHD], [tenKH], [maNguoiDaiDien], [ngayBatDau], [ngayKetThuc], [giaTriHopDong], [tienCoc], [thoaThuan], [trangThai], [ghiChu]) VALUES (N'HD00007', N'HOA MAI', N'Nguyễn Văn Phong', N'NV00001', CAST(N'2023-12-02' AS Date), CAST(N'2024-02-18' AS Date), CAST(140000000.00 AS Decimal(18, 2)), CAST(50000000.00 AS Decimal(18, 2)), N'', 0, N'')
GO
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00001', N'123456a@', N'Phạm Hữu Thuận', 1, CAST(N'2003-05-14' AS Date), N'0356009858', N'huuthuan140503@gmail.com', N'064123123322', N'Chư Sê, Gia Lai', N'res\avatar\NV00001\steward.png')
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00002', N'abc123@', N'Nguyễn Thành Tâm', 1, CAST(N'2003-12-24' AS Date), N'0987756444', N'tamoc@gmail.com', N'012233445566', N'Thủ Đức, TP HCM', N'res\avatar\NV00002\waiter.png')
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00003', N'abc123@', N'Phạm Thành Tân', 1, CAST(N'2000-01-01' AS Date), N'0111111111', N'thanhtan@gmail.com', N'011223344551', N'Gò Vấp, TP HCM', N'res\avatar\NV00003\security.png')
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00004', N'abc123@', N'Nguyễn Văn Phong', 1, CAST(N'2000-01-01' AS Date), N'0998877665', N'vanphong@gmail.com', N'011223322331', N'Gò Vấp, TP HCM', N'res\avatar\NV00004\waiter.png')
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00005', N'abc123@', N'Nguyễn Thị Quỳnh Như', 0, CAST(N'1998-02-19' AS Date), N'0998877665', N'quynhnhu@gmail.com', N'061122332255', N'Hóc Môn, TP HCM', N'res\avatar\NV00005\maid.png')
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00006', N'abc123@', N'Lê Hoàng Quân', 1, CAST(N'1996-01-15' AS Date), N'0860312789', N'quanhoang@gmail.com', N'064860312789', N'Rạch Giá, Kiên Giang', N'res\avatar\NV00006\waiter.png')
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00007', N'abc123@', N'Phạm Thị Như Duyên', 0, CAST(N'1997-11-11' AS Date), N'0229672194', N'nhuduyen@gmail.com', N'064229672194', N'Bến Lức, Long An', N'res\avatar\NV00007\staff (1).png')
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00008', N'abc123@', N'Nguyễn Trần Nhật Linh', 1, CAST(N'1999-04-17' AS Date), N'0114182777', N'nhatling@gmail.com', N'064114182777', N'TP Dĩ An, Bình Dương', N'res\avatar\NV00008\receptionist.png')
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00009', N'abc123@', N'Đặng Quốc Quân', 1, CAST(N'1993-02-19' AS Date), N'0952815367', N'quocquan@gmail.com', N'064952815367', N'Quận 1, TP HCM', NULL)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00010', N'abc123@', N'Đinh Thị Thùy', 0, CAST(N'1997-06-12' AS Date), N'0854830669', N'thuydinh@gmail.com', N'064854830669', N'Quận Gò Vấp, TP HCM', NULL)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00011', N'abc123@', N'Cao Đoan Hồng Ngọc', 0, CAST(N'2000-03-22' AS Date), N'0621846071', N'hongngoc@gmail.com', N'064621846071', N'Chư Sê, Gia Lai', N'res\avatar\NV00011\staff.png')
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00012', N'abc123@', N'Mai Thị Kiều Oanh', 0, CAST(N'2000-01-01' AS Date), N'0299290344', N'kieuoanh@gmail.com', N'064299290344', N'Biên Hòa, Đồng Nai', N'res\avatar\NV00012\staff (1).png')
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00013', N'abc123@', N'Nguyễn Thị Lan Phương', 0, CAST(N'1999-09-13' AS Date), N'0289253505', N'lanphuong@gmail.com', N'064289253505', N'Quận 12, TP HCM', N'res\avatar\NV00013\staff.png')
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [hoTen], [gioiTinh], [ngaySinh], [sDT], [email], [soCCCD], [diaChi], [anhDaiDien]) VALUES (N'NV00014', N'abc123@', N'Nguyễn Thị Trúc Ly', 0, CAST(N'1997-01-28' AS Date), N'0998866333', N'trucly@gmail.com', N'026338993322', N'Cần Giuộc, Long An', N'res\avatar\NV00014\maid.png')
GO
INSERT [dbo].[PhongBan] ([maPB], [tenPB], [soLuongNV], [moTa]) VALUES (N'PB01', N'Nhân sự', 2, NULL)
INSERT [dbo].[PhongBan] ([maPB], [tenPB], [soLuongNV], [moTa]) VALUES (N'PB02', N'Tài chính', 2, NULL)
INSERT [dbo].[PhongBan] ([maPB], [tenPB], [soLuongNV], [moTa]) VALUES (N'PB03', N'Quản lý', 0, NULL)
INSERT [dbo].[PhongBan] ([maPB], [tenPB], [soLuongNV], [moTa]) VALUES (N'PB04', N'Thiết kế', 4, NULL)
INSERT [dbo].[PhongBan] ([maPB], [tenPB], [soLuongNV], [moTa]) VALUES (N'PB05', N'Phát triển', 1, NULL)
INSERT [dbo].[PhongBan] ([maPB], [tenPB], [soLuongNV], [moTa]) VALUES (N'PB06', N'Makerting', 1, NULL)
GO
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000001', N'HD00001', N'Ghế văn phòng', N'Cái', 300, N'', CAST(100000.00 AS Decimal(18, 2)))
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000002', N'HD00002', N'Bàn văn phòng', N'Cái', 50, N'', CAST(500000.00 AS Decimal(18, 2)))
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000003', N'HD00002', N'Kệ treo đồ', N'Cái', 250, N'', CAST(200000.00 AS Decimal(18, 2)))
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000004', N'HD00003', N'Ghế nhựa 321', N'Cái', 200, N'', CAST(50000.00 AS Decimal(18, 2)))
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000005', N'HD00003', N'Bàn nhựa 333', N'Cái', 100, N'', CAST(333000.00 AS Decimal(18, 2)))
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000006', N'HD00004', N'Ghế giám đốc', N'Cái', 500, N'', CAST(10000000.00 AS Decimal(18, 2)))
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000007', N'HD00004', N'Bàn VIP', N'Cái', 300, N'', CAST(50000000.00 AS Decimal(18, 2)))
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000008', N'HD00005', N'Bàn dài', N'Cái', 100, N'', CAST(2000000.00 AS Decimal(18, 2)))
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000009', N'HD00005', N'Ghế văn phòng', N'Cái', 300, N'', CAST(500000.00 AS Decimal(18, 2)))
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000011', N'HD00007', N'Bàn dài', N'Bộ', 140, N'đinh loại 3', CAST(10000.00 AS Decimal(18, 2)))
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000012', N'HD00001', N'Bàn MEXIO', N'Cái', 110, N'sơn màu vàng', CAST(4000.00 AS Decimal(18, 2)))
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000013', N'HD00001', N'Bàn Lục Giác', N'Cái', 211, N'', CAST(8000.00 AS Decimal(18, 2)))
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000014', N'HD00002', N'Ghế Trẻ em', N'Cái', 48, N'cỡ 3 tuổi', CAST(2690.00 AS Decimal(18, 2)))
INSERT [dbo].[SanPham] ([maSP], [maHopDong], [tenSP], [donViTinh], [soLuong], [yeuCau], [donGia]) VALUES (N'SP0000015', N'HD00005', N'Gác chân', N'Đôi(Cặp)', 148, N'', CAST(4500.00 AS Decimal(18, 2)))
GO
ALTER TABLE [dbo].[BangChamCongCongNhan] ADD  DEFAULT ((0)) FOR [trangThai]
GO
ALTER TABLE [dbo].[BangChamCongNhanVien] ADD  CONSTRAINT [DF_BangChamCongNhanVien_diLam]  DEFAULT ((0)) FOR [diLam]
GO
ALTER TABLE [dbo].[BangChamCongNhanVien] ADD  CONSTRAINT [DF_BangChamCongNhanVien_coPhep]  DEFAULT ((0)) FOR [coPhep]
GO
ALTER TABLE [dbo].[BangLuongCongNhan] ADD  DEFAULT ((0.0)) FOR [luongThang]
GO
ALTER TABLE [dbo].[BangLuongCongNhan] ADD  DEFAULT ((0.0)) FOR [luongCongDoan]
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
