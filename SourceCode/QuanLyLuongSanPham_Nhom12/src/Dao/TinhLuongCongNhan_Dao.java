package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ConnectDB.ConnectDB;
import Entity.BangChamCongCongNhan;
import Entity.BangLuongCongNhan;
import Entity.BangPhanCongCongDoan;
import Entity.CongDoan;
import Entity.CongNhan;
import Entity.SanPham;
import Util.SinhMaTuDong;

public class TinhLuongCongNhan_Dao {

	private SinhMaTuDong maTuDong = new SinhMaTuDong();
	public double luongCoBan, luongThang, luongCongDoan, thucLanh;

	// HÀM LẤY MÃ LƯƠNG CN LỚN NHẤT
	public String getMaBangLuongCNLonNhat() {
		String maBangLuongLonNhat = null;
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT MAX(maBangLuong) AS maBangLuongLonNhat FROM BangLuongCongNhan";

			st = con.prepareStatement(sql);
			rs = st.executeQuery();

			if (rs.next()) {
				maBangLuongLonNhat = rs.getString("maBangLuongLonNhat");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return maBangLuongLonNhat;
	}

	// Hàm lấy và tính và thêm
	public boolean tinhALL(int thang, int nam) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "DECLARE @luongCoBan DECIMAL(18, 2);\r\n"
					+ "DECLARE @thang INT;\r\n"
					+ "DECLARE @nam INT; \r\n"
					+ "\r\n"
					+ "SET @luongCoBan = 250000;\r\n"
					+ "SET @thang = ? ;\r\n"
					+ "SET @nam = ? ;\r\n"
					+ "\r\n"
					+ "MERGE INTO [dbo].[BangLuongCongNhan] AS target\r\n"
					+ "USING (\r\n"
					+ "    SELECT \r\n"
					+ "        'LC' + RIGHT('0000000' + CAST(ROW_NUMBER() OVER (ORDER BY cn.maCN) AS VARCHAR(7)), 7) AS maBangLuong,\r\n"
					+ "        cn.maCN,\r\n"
					+ "        SUM(soLuongLam) AS soLuongCongDoanLam,\r\n"
					+ "        SUM(CASE WHEN trangThai = 0 THEN 1 ELSE 0 END) AS soNgayLam,\r\n"
					+ "        SUM(CASE WHEN trangThai = 1 THEN 1 ELSE 0 END) AS soNgayPhep,\r\n"
					+ "        SUM(CASE WHEN trangThai = 2 THEN 1 ELSE 0 END) AS soNgayNghi,\r\n"
					+ "        CASE WHEN (SUM((@luongCoBan * (\r\n"
					+ "            (CASE WHEN trangThai = 0 THEN 1 ELSE 0 END) + \r\n"
					+ "            (CASE WHEN trangThai = 1 THEN 1 ELSE 0 END) - \r\n"
					+ "            (CASE WHEN trangThai = 2 THEN 1 ELSE 0 END)\r\n"
					+ "        )  + cd.donGia * soLuongLam ))) < 0 THEN 0\r\n"
					+ "        ELSE\r\n"
					+ "        (SUM((@luongCoBan * (\r\n"
					+ "            (CASE WHEN trangThai = 0 THEN 1 ELSE 0 END) + \r\n"
					+ "            (CASE WHEN trangThai = 1 THEN 1 ELSE 0 END) - \r\n"
					+ "            (CASE WHEN trangThai = 2 THEN 1 ELSE 0 END)\r\n"
					+ "        ) + cd.donGia * soLuongLam ))) END AS thucLanh,\r\n"
					+ "        CONCAT(@thang ,'/', @nam) AS thangNam,\r\n"
					+ "        CASE WHEN(SUM(@luongCoBan * (\r\n"
					+ "            (CASE WHEN trangThai = 0 THEN 1 ELSE 0 END) + \r\n"
					+ "            (CASE WHEN trangThai = 1 THEN 1 ELSE 0 END) - \r\n"
					+ "            (CASE WHEN trangThai = 2 THEN 1 ELSE 0 END)\r\n"
					+ "        ))) < 0 THEN 0\r\n"
					+ "        ELSE\r\n"
					+ "        (SUM(@luongCoBan * (\r\n"
					+ "            (CASE WHEN trangThai = 0 THEN 1 ELSE 0 END) + \r\n"
					+ "            (CASE WHEN trangThai = 1 THEN 1 ELSE 0 END) - \r\n"
					+ "            (CASE WHEN trangThai = 2 THEN 1 ELSE 0 END)\r\n"
					+ "        ))) END  AS luongThang,\r\n"
					+ "        SUM(cd.donGia * soLuongLam) AS luongCongDoan\r\n"
					+ "    FROM [dbo].[BangChamCongCongNhan] cccn \r\n"
					+ "    JOIN [dbo].[BangPhanCongCongDoan] pccn ON cccn.maPhanCong = pccn.maPhanCong\r\n"
					+ "    JOIN [dbo].[CongNhan] cn ON pccn.maCN = cn.maCN\r\n"
					+ "    JOIN [dbo].[CongDoan] cd ON pccn.maCongDoan = cd.maCD\r\n"
					+ "    WHERE MONTH(cccn.ngayChamCong) = @thang AND YEAR(cccn.ngayChamCong) = @nam\r\n"
					+ "    GROUP BY cn.maCN\r\n"
					+ ") AS source\r\n"
					+ "ON target.maBangLuong = source.maBangLuong\r\n"
					+ "WHEN MATCHED THEN\r\n"
					+ "    UPDATE SET\r\n"
					+ "        target.maCN = source.maCN,\r\n"
					+ "        target.soLuongCongDoanLam = source.soLuongCongDoanLam,\r\n"
					+ "        target.soNgayLam = source.soNgayLam,\r\n"
					+ "        target.soNgayNghi = source.soNgayNghi,\r\n"
					+ "        target.soNgayPhep = source.soNgayPhep,\r\n"
					+ "        target.thucLanh = source.thucLanh,\r\n"
					+ "        target.thangNam = source.thangNam,\r\n"
					+ "        target.luongThang = source.luongThang,\r\n"
					+ "        target.luongCongDoan = source.luongCongDoan\r\n"
					+ "WHEN NOT MATCHED THEN\r\n"
					+ "    INSERT (\r\n"
					+ "        maBangLuong,\r\n"
					+ "        maCN,\r\n"
					+ "        soLuongCongDoanLam,\r\n"
					+ "        soNgayLam,\r\n"
					+ "        soNgayNghi,\r\n"
					+ "        soNgayPhep,\r\n"
					+ "        thucLanh,\r\n"
					+ "        thangNam,\r\n"
					+ "        luongThang,\r\n"
					+ "        luongCongDoan\r\n"
					+ "    ) VALUES (\r\n"
					+ "        source.maBangLuong,\r\n"
					+ "        source.maCN,\r\n"
					+ "        source.soLuongCongDoanLam,\r\n"
					+ "        source.soNgayLam,\r\n"
					+ "        source.soNgayNghi,\r\n"
					+ "        source.soNgayPhep,\r\n"
					+ "        source.thucLanh,\r\n"
					+ "        source.thangNam,\r\n"
					+ "        source.luongThang,\r\n"
					+ "        source.luongCongDoan\r\n"
					+ "    );\r\n"
					+ "";
			st = conn.prepareStatement(querry);

			st.setInt(1, thang);
			st.setInt(2, nam);
			n = st.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return n > 0;
	}

	// HÀM CHECK ĐÃ TỒN TẠI CÔNG NHÂN TRONG BẢNG TÍNH LƯƠNG
	private boolean checkCongNhanExist(String maCN, int thang, int nam) throws SQLException {
		String querryCheck = "SELECT COUNT(*) FROM BangLuongCongNhan WHERE maCN=? AND thangNam=?";
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st_check = conn.prepareStatement(querryCheck);
		st_check.setString(1, maCN);
		st_check.setString(2, thang + "/" + nam);

		ResultSet rs_check = st_check.executeQuery();
		rs_check.next();
		int count = rs_check.getInt(1);

		return count > 0;
	}

	// HÀM GET DATA BẢNG LƯƠNG
	public ArrayList<BangLuongCongNhan> getAllBLCN() {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<BangLuongCongNhan> listBLCN = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT *  FROM [QuanLyLuongSanPham-TPT].[dbo].[BangLuongCongNhan] blcn\r\n"
					+ "JOIN [dbo].[CongNhan] cn ON blcn.maCN = cn.maCN ";
			st = conn.prepareStatement(querry);

			rs = st.executeQuery();

			while (rs.next()) {
				CongNhan cn = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"));
				BangChamCongCongNhan cccn = new BangChamCongCongNhan(rs.getInt("soLuongCongDoanLam"));
				BangLuongCongNhan blcn = new BangLuongCongNhan(rs.getString("maBangLuong"), cn, cccn,
						rs.getInt("soNgayLam"), rs.getInt("soNgayNghi"), rs.getInt("soNgayPhep"),
						rs.getDouble("luongThang"), rs.getDouble("luongCongDoan"), rs.getDouble("thucLanh"),
						rs.getString("thangNam"));
				listBLCN.add(blcn);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listBLCN;

	}

	// HÀM GET DATA BẢNG LƯƠNG THEO THANG NAM
	public ArrayList<BangLuongCongNhan> getAllBLCN_TN(int thang, int nam) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<BangLuongCongNhan> listBLCN = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT *  FROM [QuanLyLuongSanPham-TPT].[dbo].[BangLuongCongNhan] blcn\r\n"
					+ "JOIN [dbo].[CongNhan] cn ON blcn.maCN = cn.maCN "
					+ " WHERE thangNam = ?";
			st = conn.prepareStatement(querry);
			st.setString(1, thang+"/"+nam);

			rs = st.executeQuery();

			while (rs.next()) {
				CongNhan cn = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"));
				BangChamCongCongNhan cccn = new BangChamCongCongNhan(rs.getInt("soLuongCongDoanLam"));
				BangLuongCongNhan blcn = new BangLuongCongNhan(rs.getString("maBangLuong"), cn, cccn,
						rs.getInt("soNgayLam"), rs.getInt("soNgayNghi"), rs.getInt("soNgayPhep"),
						rs.getDouble("luongThang"), rs.getDouble("luongCongDoan"), rs.getDouble("thucLanh"),
						rs.getString("thangNam"));
				listBLCN.add(blcn);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listBLCN;

	}
	// HÀM GET THÔNG TIN VÀ ALL CÔNG ĐOẠN CHI TIẾT TÍNH LƯƠNG
	public List<BangLuongCongNhan> getAllCD_CTL(String maBangLuong) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<BangLuongCongNhan> listCD = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT cccn.ngayChamCong,cccn.gioVaoLam, cd.maCD, cd.tenCD, cd.thuTu, sp.maSP, sp.tenSP , cccn.soLuongLam \r\n"
					+ "FROM [dbo].[BangLuongCongNhan] blcn\r\n"
					+ "JOIN [dbo].[CongNhan] cn ON cn.maCN = blcn.maCN \r\n"
					+ "JOIN [dbo].[BangPhanCongCongDoan] pccd ON blcn.maCN = pccd.maCN\r\n"
					+ "JOIN [dbo].[CongDoan] cd ON cd.maCD = pccd.maCongDoan\r\n"
					+ "JOIN [dbo].[SanPham] sp ON sp.maSP = cd.maSP\r\n"
					+ "JOIN [dbo].[BangChamCongCongNhan] cccn ON cccn.maPhanCong = pccd.maPhanCong\r\n"
					+ "WHERE maBangLuong = ? ";
			st = conn.prepareStatement(querry);
			st.setString(1, maBangLuong);

			rs = st.executeQuery();

			while (rs.next()) {
				SanPham sp = new SanPham(rs.getString("maSP"), rs.getString("tenSP"));
				CongDoan cd = new CongDoan(rs.getString("maCD"), rs.getString("tenCD"),rs.getInt("thuTu"), sp);
				BangPhanCongCongDoan pccd = new BangPhanCongCongDoan(cd);
				BangChamCongCongNhan cccn = new BangChamCongCongNhan(
						rs.getDate("ngayChamCong"),
						rs.getString("gioVaoLam"),
						pccd,
						rs.getInt("soLuongLam"));
				BangLuongCongNhan blcn = new BangLuongCongNhan(cccn);
				listCD.add(blcn);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listCD;

	}

}
