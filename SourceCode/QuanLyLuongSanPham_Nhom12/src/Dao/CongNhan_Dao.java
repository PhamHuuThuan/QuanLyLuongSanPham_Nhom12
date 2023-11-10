package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.CongNhan;

public class CongNhan_Dao {

	// GET MA CONG NHAN TU DONG LON NHAT
	public String getMaCongNhanTuDong() {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		String maCNLonNhat = null;

		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT MAX(maCN) AS maCNMax FROM CongNhan";
			st = conn.prepareStatement(querry);
			rs = st.executeQuery();

			if (rs.next()) {
				maCNLonNhat = rs.getString("maCNMax");
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				st.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return maCNLonNhat;
	}

	// THEM CONG NHAN
	public boolean themCongNhan(CongNhan cn) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String query = "INSERT INTO CongNhan VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			st = conn.prepareStatement(query);
			st.setString(1, cn.getMaCN());
			st.setString(2, cn.getMatKhau());
			st.setString(3, cn.getHoTen());
			st.setDate(4, new java.sql.Date(cn.getNgaySinh().getTime()));
			st.setBoolean(5, cn.getGioiTinh());
			st.setString(6, cn.getSDT());
			st.setString(7, cn.getEmail());
			st.setString(8, cn.getDiaChi());
			st.setString(9, cn.getSoCCCD());
			st.setDate(10, new java.sql.Date(cn.getNgayVaoLam().getTime()));
			st.setString(11, cn.getAnhDaiDien());
			st.setString(12, cn.getGhiChu());
			n = st.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	// GET TẤT CẢ CÁC CÔNG NHÂN
	public ArrayList<CongNhan> getAllCongNhan() {
		ArrayList<CongNhan> listCN = new ArrayList<>();
		ConnectDB.getInstance();
		PreparedStatement st = null;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT * FROM CongNhan";
			st = conn.prepareStatement(querry);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				CongNhan cn = new CongNhan(rs.getString(1), rs.getString(2), rs.getString(3),
						new java.util.Date(rs.getDate(4).getTime()), rs.getBoolean(5), rs.getString(6), rs.getString(7),
						rs.getString(8), rs.getString(9), new java.util.Date(rs.getDate(10).getTime()),
						rs.getString(11), rs.getString(12));
				listCN.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
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

	// SUA CONG NHAN
	public boolean suaCongNhan(CongNhan cn) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "UPDATE CongNhan SET " + " matKhau=?, hoTen=?," + " ngaySinh=?, gioiTinh=?,"
					+ " sDT=?, email=?, diaChi=?, soCCCD=?," + " ngayVaoLam=?, anhDaiDien=?, ghiChu=? WHERE maCN=?";
			st = conn.prepareStatement(querry);
			st.setString(1, cn.getMatKhau());
			st.setString(2, cn.getHoTen());
			st.setDate(3, new java.sql.Date(cn.getNgaySinh().getTime()));
			st.setBoolean(4, cn.getGioiTinh());
			st.setString(5, cn.getSDT());
			st.setString(6, cn.getEmail());
			st.setString(7, cn.getDiaChi());
			st.setString(8, cn.getSoCCCD());
			st.setDate(9, new java.sql.Date(cn.getNgayVaoLam().getTime()));
			st.setString(10, cn.getAnhDaiDien());
			st.setString(11, cn.getGhiChu());
			st.setString(12, cn.getMaCN());

			n = st.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	// XOA CONG NHAN
	public boolean xoaCongNhan(String maCN) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "DELETE FROM CongNhan WHERE maCN=?";
			st = conn.prepareStatement(querry);
			st.setString(1, maCN);
			n = st.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return n > 0;
	}

	// TÌM KIẾM CÔNG NHÂN
	public ArrayList<CongNhan> timKiemCongNhan(
			String maCN, String hoten, java.util.Date ngaySinh,
			int nam, int nu, String sDT, String diaChi,
			String soCCCD){
		ArrayList<CongNhan> listCN = new ArrayList<>();
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT * FROM CongNhan WHERE "
					+ "maCN LIKE ? "
					+ "AND hoTen LIKE ? "
					+ "AND gioiTinh IN (?,?) "
					+ "AND sDT LIKE ? "
					+ "AND diaChi LIKE ? "
					+ "AND SoCCCD LIKE ? "
					;
			if(ngaySinh !=null) {
				querry  = querry + "AND ngaySinh LIKE ? ";
			}
			st = conn.prepareStatement(querry);
			
			st.setString(1, "%" + maCN + "%");
			st.setString(2, "%" + hoten + "%");
			st.setInt(3, nam);
			st.setInt(4, nu);
			st.setString(5, "%" + sDT +"%");
			st.setString(6, "%"+ diaChi +"%");
			st.setString(7, "%" + soCCCD + "%");
			
			if(ngaySinh !=null) {
				st.setDate(8, new java.sql.Date(ngaySinh.getTime()));
			}
			
			rs  = st.executeQuery();
			while(rs.next()) {
				CongNhan cn = new CongNhan(
						rs.getString("maCN"),
						rs.getString("matKhau"),
						rs.getString("hoten"),
						new java.util.Date(rs.getDate("ngaySinh").getTime()),
						rs.getBoolean("gioiTinh"),
						rs.getString("sDT"),
						rs.getString("email"),
						rs.getString("diaChi"),
						rs.getString("soCCCD"),
						new java.util.Date(rs.getDate("ngayVaoLam").getTime()),
						rs.getString("anhDaiDien"),
						rs.getString("ghiChu")
						);
				listCN.add(cn);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(st !=null) rs.close();
				if(rs != null) st.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return listCN;
	}
	// Lấy số lượng công nhân từ csdl
	public int laySoLuongCongNhan() {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    int soLuongCongNhan = 0;
	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT COUNT(*) AS SoLuong FROM CongNhan");
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            soLuongCongNhan = rs.getInt("SoLuong");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (st != null) st.close();
	        } catch (SQLException e2) {
	            e2.printStackTrace();
	        }
	    }
	    return soLuongCongNhan;
	}

}
