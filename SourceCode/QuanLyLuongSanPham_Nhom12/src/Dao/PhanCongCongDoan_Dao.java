package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.BangPhanCongCongDoan;
import Entity.CongDoan;
import Entity.CongNhan;
import Entity.SanPham;

public class PhanCongCongDoan_Dao {
	// HÀM LẤY MÃ PHÂN CÔNG CÔNG ĐOẠN LỚN NHÂT
	public String getMaPCCDMax() {
		String maPCCDMax = null;
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT MAX(maPhanCong) AS maPhanCongLonNhat FROM BangPhanCongCongDoan";
			st = conn.prepareStatement(querry);

			rs = st.executeQuery();

			if (rs.next()) {
				maPCCDMax = rs.getString("maPhanCongLonNhat");
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
		return maPCCDMax;

	}

	// HÀM LẤY DỮ LIỆU ALL CÔNG ĐOẠN
	public ArrayList<CongDoan> getAllCongDoan() {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<CongDoan> listCD = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT * FROM CongDoan cd JOIN SanPham sp ON cd.maSP = sp.maSP WHERE tinhTrang LIKE 0;";
			st = conn.prepareStatement(querry);
			rs = st.executeQuery();

			while (rs.next()) {
				CongDoan cd = new CongDoan(rs.getString("maCD"), rs.getString("tenCD"), rs.getInt("thuTu"),
						rs.getInt("soLuong"), rs.getDouble("donGia"), rs.getBoolean("tinhTrang"),
						rs.getDate("ngayHoanThanh"), new SanPham(rs.getString("maSP"), rs.getString("tenSP")));
				listCD.add(cd);
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

	// HÀM LẤY DANH SÁCH CÔNG NHÂN CHƯA PHÂN CÔNG
	public ArrayList<CongNhan> getAllCongNhanChuaPhanCong() {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<CongNhan> listCN = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT cn.maCN, cn.hoTen, cn.ngaySinh, cn.ngayVaoLam FROM CongNhan cn WHERE NOT EXISTS (SELECT 1 FROM "
					+ "BangPhanCongCongDoan pccd WHERE pccd.maCN = cn.maCN)";
			st = conn.prepareStatement(querry);
			rs = st.executeQuery();

			while (rs.next()) {
				CongNhan cnNew = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"), rs.getDate("ngaySinh"),
						rs.getDate("ngayVaoLam"));
				listCN.add(cnNew);
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
		return listCN;
	}

	// HÀM LẤY DANH SÁCH PHÂN CÔNG CÔNG ĐOẠN
	public ArrayList<BangPhanCongCongDoan> getAllPhanCongCongDoan() {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<BangPhanCongCongDoan> listPCCD = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT * FROM BangPhanCongCongDoan pccd  "
					+ "JOIN CongDoan cd ON pccd.maCongDoan =  cd.maCD " + "JOIN CongNhan cn ON pccd.maCN = cn.maCN "
					+ "JOIN SanPham sp ON cd.maSP = sp.maSP";
			st = conn.prepareStatement(querry);
			rs = st.executeQuery();

			while (rs.next()) {
				CongNhan congNhan = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"));
				CongDoan congDoan = new CongDoan(rs.getString("maCD"), rs.getString("tenCD"));
				SanPham sanPham = new SanPham(rs.getString("maSP"), rs.getString("tenSP"));

				BangPhanCongCongDoan cdNew = new BangPhanCongCongDoan(rs.getString("maPhanCong"), congNhan, congDoan,
						sanPham, rs.getDate("ngayPhanCong"), rs.getInt("soLuongCanLam"), rs.getString("ghiChu"));

				listPCCD.add(cdNew);
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
		return listPCCD;
	}

	// HÀM THÊM PHÂN CÔNG CÔNG ĐOẠN
	public boolean themPCCD(BangPhanCongCongDoan pccd) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "INSERT INTO BangPhanCongCongDoan VALUES(?,?,?,?,?,?)";
			st = conn.prepareStatement(querry);
			st.setString(1, pccd.getMaPhanCong());
			st.setString(2, pccd.getCongNhan().getMaCN());
			st.setString(3, pccd.getCongDoan().getMaCD());
			st.setDate(4, new java.sql.Date(pccd.getNgayPhanCong().getTime()));
			st.setInt(5, pccd.getSoLuongCanLam());
			st.setString(6, pccd.getGhiChu());

			n = st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return n > 0;
	}
	// HÀM SỬA PHÂN CÔNG CÔNG ĐOẠN
	public boolean suaPCCD(BangPhanCongCongDoan pccd) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n=0;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "UPDATE BangPhanCongCongDoan SET "
					+ "maCN =?, maCongDoan=?, soLuongCanLam=?,ghiChu=? WHERE maPhanCong= ?";
			st = conn.prepareStatement(querry);
			st.setString(1, pccd.getCongNhan().getMaCN());
			st.setString(2, pccd.getCongDoan().getMaCD());
			st.setString(3, String.valueOf(Integer.valueOf(pccd.getSoLuongCanLam())));
			st.setString(4, pccd.getGhiChu());
			
			st.setString(5, pccd.getMaPhanCong());
			
			n = st.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return n>0;
	}
	// HÀM XÓA 1 CÔNG ĐOẠN
	public boolean xoaPCCD(String maPhanCong) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n=0;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "DELETE FROM BangPhanCongCongDoan WHERE maPhanCong= ?";
			st = conn.prepareStatement(querry);
			st.setString(1, maPhanCong);
			
			n = st.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return n>0;
	}
	

}









