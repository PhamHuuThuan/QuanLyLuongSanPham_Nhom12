package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.BangChamCongCongNhan;
import Entity.BangLuongCongNhan;
import Entity.BangPhanCongCongDoan;
import Entity.CongNhan;
import Util.SinhMaTuDong;

public class TinhLuongCongNhan_Dao {
	// HÀM LẤY DS CHƯA TÍNH LƯƠNG THEO THÁNG NĂM
	
	private SinhMaTuDong maTuDong = new SinhMaTuDong();
	
	
	public ArrayList<BangChamCongCongNhan> getDSChamCong(int thang, int nam) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<BangChamCongCongNhan> listCC = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT cn.maCN, cccn.soLuongLam, pccd.maPhanCong, cccn.trangThai FROM [dbo].[BangChamCongCongNhan] cccn\r\n"
					+ "JOIN [dbo].[BangPhanCongCongDoan] pccd ON cccn.maPhanCong = pccd.maPhanCong\r\n"
					+ "JOIN [dbo].[CongNhan] cn ON pccd.maCN = cn.maCN\r\n"
					+ "JOIN [dbo].[CongDoan] cd ON pccd.maCongDoan = cd.maCD\r\n"
					+ "JOIN [dbo].[SanPham] sp ON cd.maSP = sp.maSP \r\n"
					+ "WHERE  MONTH(cccn.ngayChamCong) = ? AND YEAR(cccn.ngayChamCong) = ?\r\n"
					+ "GROUP BY cn.maCN, cccn.soLuongLam ,pccd.maPhanCong";
			st = conn.prepareStatement(querry);

			st.setInt(1, thang);
			st.setInt(2, nam);

			rs = st.executeQuery();
			while (rs.next()) {
				CongNhan cn = new CongNhan(rs.getString("maCN"));
				BangPhanCongCongDoan pccd = new BangPhanCongCongDoan(rs.getString("maPhanCong"), cn);
				BangChamCongCongNhan cccn_new = new BangChamCongCongNhan(pccd, rs.getInt("soLuongLam"), rs.getInt("trangThai"));
				listCC.add(cccn_new);
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
		return listCC;
	}

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

	// HÀM THÊM BẢNG LƯƠNG
	public boolean themBangLuong(BangLuongCongNhan blcn) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "INSERT INTO BangLuongCongNhan" + "(maBangLuong, maCN, soLuongCongDoanLam, soNgayLam,"
					+ "soNgayNghi, soNgayPhep, thucLanh, thangNam ) " + "VALUES (?,?,?,?,?,?,?,?)";
			st = conn.prepareStatement(querry);
			st.setString(1, blcn.getMaBangLuong());
			st.setString(2, blcn.getCongNhan().getMaCN());
			st.setInt(3, blcn.getSoLuongCongDoanLam());
			st.setInt(4, blcn.getSoNgayLam());
			st.setInt(5, blcn.getSoNgayNghi());
			st.setInt(6, blcn.getSoNgayPhep());
			st.setDouble(7, blcn.getThucLanh());
			st.setString(8, DateTimeFormatter.ofPattern("MM/yyyy").format(blcn.getThangNam()));

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

	// Hàm lấy và tính và thêm
	public boolean tinhALL(int thang, int nam) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<BangChamCongCongNhan> listCC = new ArrayList<>();
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT cn.maCN, cccn.soLuongLam, pccd.maPhanCong, cccn.trangThai, cd.donGia\r\n"
					+ "FROM [dbo].[BangChamCongCongNhan] cccn\r\n"
					+ "JOIN [dbo].[BangPhanCongCongDoan] pccd ON cccn.maPhanCong = pccd.maPhanCong\r\n"
					+ "JOIN [dbo].[CongNhan] cn ON pccd.maCN = cn.maCN\r\n"
					+ "JOIN [dbo].[CongDoan] cd ON pccd.maCongDoan = cd.maCD\r\n"
					+ "JOIN [dbo].[SanPham] sp ON cd.maSP = sp.maSP \r\n"
					+ "WHERE  MONTH(cccn.ngayChamCong) = ? AND YEAR(cccn.ngayChamCong) = ?\r\n"
					+ "GROUP BY cn.maCN, cccn.soLuongLam ,pccd.maPhanCong, cccn.trangThai, cd.donGia";
			st = conn.prepareStatement(querry);

			st.setInt(1, thang);
			st.setInt(2, nam);
			rs = st.executeQuery();

			while (rs.next()) {
				String maCN = rs.getString("maCN");
				int soLuongLam = rs.getInt("soLuongLam");
				int trangThai = rs.getInt("trangThai");
				double donGia = rs.getDouble("donGia");
				
				CongNhan cn = new CongNhan(maCN);
				BangPhanCongCongDoan pccd = new BangPhanCongCongDoan(rs.getString("maPhanCong"), cn);
				BangChamCongCongNhan cccn_new = new BangChamCongCongNhan(pccd, soLuongLam, trangThai);
				listCC.add(cccn_new);

				int soNgayLam = 0;
				int soNgayNghi = 0;
				int soNgayPhep = 0;

				for (BangChamCongCongNhan chamCong : listCC) {
				    int trangThai_2 = chamCong.getTrangThai();

				    switch (trangThai_2) {
				        case 0:
				            soNgayLam += 1;
				            break;
				        case 1:
				            soNgayPhep += 1;
				            break;
				        case 2:
				            soNgayNghi += 1;
				            break;
				    }
				}
				
				int soLuongCongDoanLam = soLuongLam;
                double luongCoBan = 150000;
                double luongThang = luongCoBan * (soNgayLam + soNgayPhep - soNgayNghi);
                double luongCongDoan = donGia * soLuongCongDoanLam;
                double thucLanh = luongThang + luongCongDoan;
                
                String querryInsert = "INSERT INTO BangLuongCongNhan " +
                        "(maBangLuong, maCN, soLuongCongDoanLam, soNgayLam, soNgayNghi, soNgayPhep, thucLanh, thangNam) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                
                PreparedStatement st_is = conn.prepareStatement(querryInsert);
                st_is.setString(1, maTuDong.sinhMaBLCN());
                st_is.setString(2, maCN);
                st_is.setInt(3, soLuongCongDoanLam);
                st_is.setInt(4, soNgayLam);
                st_is.setInt(5, soNgayNghi);
                st_is.setInt(6, soNgayPhep);
                st_is.setDouble(7, thucLanh);
                st_is.setString(8, thang+"/"+nam); 
                
                n = st_is.executeUpdate();
                
                listCC.removeAll(listCC);

			}

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

}
