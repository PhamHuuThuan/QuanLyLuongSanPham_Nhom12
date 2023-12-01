package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import ConnectDB.ConnectDB;
import Entity.BangChamCongCongNhan;
import Entity.BangPhanCongCongDoan;
import Entity.CongDoan;
import Entity.CongNhan;
import Entity.SanPham;

public class ChamCongCongNhan_Dao {
	// HÀM LẤY TẤT CẢ SẢN PHẨM ĐÃ ĐƯỢC PHÂN CÔNG
	public ArrayList<BangPhanCongCongDoan> getALLSanPham() {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<BangPhanCongCongDoan> listSP = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT cd.maSP, sp.tenSP ,pccd.maCongDoan, "
					+ "cd.tenCD, cd.thuTu, pccd.maPhanCong FROM [dbo].[BangPhanCongCongDoan] "
					+ "pccd JOIN [dbo].[CongNhan] cn ON pccd.maCN = cn.maCN "
					+ "JOIN [dbo].[CongDoan] cd ON pccd.maCongDoan  = cd.maCD "
					+ "JOIN [dbo].[SanPham] sp ON cd.maSP = sp.maSP "
					+ "GROUP BY cd.maSP, sp.tenSP, pccd.maCongDoan, cd.tenCD, cd.thuTu, pccd.maPhanCong";
			st = conn.prepareStatement(querry);
			rs = st.executeQuery();

			while (rs.next()) {
				SanPham sp = new SanPham(rs.getString("maSp"), rs.getString("tenSP"));
				CongDoan cd = new CongDoan(rs.getString("maCongDoan"), rs.getString("tenCD"), rs.getInt("thuTu"));
				BangPhanCongCongDoan pccd_1 = new BangPhanCongCongDoan(rs.getString("maPhanCong"), sp, cd);

				listSP.add(pccd_1);

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

		return listSP;
	}

	// HÀM LẤY TẤT CẢ CÔNG NHÂN ĐÃ ĐƯỢC PHÂN CÔNG THỎA SP VÀ CD TRÊN
	public ArrayList<CongNhan> getALLCongNhan(String maSP, String maCongDoan) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<CongNhan> listCN = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "WITH DanhSachCongNhan AS (\r\n"
					+ "    SELECT\r\n"
					+ "        pccd.maPhanCong,\r\n"
					+ "        pccd.soLuongCanLam,\r\n"
					+ "        COALESCE(SUM(cccn.soLuongLam), 0) AS soLuongDaCham\r\n"
					+ "    FROM [dbo].[BangPhanCongCongDoan] pccd\r\n"
					+ "    LEFT JOIN [dbo].[BangChamCongCongNhan] cccn ON pccd.maPhanCong = cccn.maPhanCong\r\n"
					+ "	WHERE pccd.soLuongCanLam - COALESCE(cccn.soLuongLam, 0) <> 0\r\n"
					+ "	GROUP BY pccd.maPhanCong, pccd.soLuongCanLam\r\n"
					+ ")\r\n"
					+ "\r\n"
					+ "SELECT DISTINCT\r\n"
					+ "    pccd.*,\r\n"
					+ "	cn.*,\r\n"
					+ "	cd.*,\r\n"
					+ "	sp.*,\r\n"
					+ "    cv.soLuongCanlam AS soLuongDaPhan,\r\n"
					+ "    cv.soLuongDaCham,\r\n"
					+ "    cv.soLuongCanLam - cv.soLuongDaCham AS soLuongChuaCham\r\n"
					+ "FROM [dbo].[BangPhanCongCongDoan] pccd\r\n"
					+ "JOIN DanhSachCongNhan cv ON pccd.maPhanCong = cv.maPhanCong\r\n"
					+ "JOIN [dbo].[CongNhan] cn ON pccd.maCN = cn.maCN\r\n"
					+ "JOIN [dbo].[CongDoan] cd ON pccd.maCongDoan = cd.maCD\r\n"
					+ "JOIN [dbo].[SanPham] sp ON cd.maSP = sp.maSP "
					+ "WHERE cd.maSP LIKE ? AND pccd.maCongDoan LIKE ? ";
			st = conn.prepareStatement(querry);

			st.setString(1, "%" + maSP + "%");
			st.setString(2, "%" + maCongDoan + "%");

			rs = st.executeQuery();

			while (rs.next()) {
				BangPhanCongCongDoan pccd = new BangPhanCongCongDoan(rs.getString("maPhanCong"),rs.getInt("soLuongDaPhan"));
				BangChamCongCongNhan cccn = new BangChamCongCongNhan(rs.getInt("soLuongDaCham"),rs.getInt("soLuongChuaCham"));
				CongNhan cn_new = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"), 
						rs.getDate("ngaySinh"), pccd, cccn);

				listCN.add(cn_new);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					rs.close();
				if (rs != null)
					st.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return listCN;
	}

	// HÀM LẤY TẤT CẢ THÔNG TIN CHẤM CÔNG (CHI TIẾT)
	public ArrayList<BangChamCongCongNhan> getALLCCCN() {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<BangChamCongCongNhan> listCCCN = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT * FROM [dbo].[BangChamCongCongNhan] cccn JOIN [dbo].[BangPhanCongCongDoan] pccd ON cccn.maPhanCong = pccd.maPhanCong\r\n"
					+ "JOIN [dbo].[CongNhan] cn ON pccd.maCN = cn.maCN JOIN [dbo].[CongDoan] cd ON pccd.maCongDoan = cd.maCD\r\n"
					+ "JOIN [dbo].[SanPham] sp ON cd.maSP = sp.maSP";
			st = conn.prepareStatement(querry);
			rs = st.executeQuery();

			while (rs.next()) {
				CongDoan cd = new CongDoan(rs.getString("maCD"),rs.getString("tenCD"));
				BangPhanCongCongDoan pccd = new BangPhanCongCongDoan(rs.getString("maPhanCong"), rs.getInt("soLuongCanLam") );
				CongNhan cn = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"));
				SanPham sp  = new SanPham(rs.getString("maSP"),rs.getString("tenSP"));
		
				
				BangChamCongCongNhan cccn_new = new BangChamCongCongNhan(
						rs.getDate("ngayChamCong"),
						rs.getString("gioVaoLam"),
						pccd,
						rs.getInt("soLuongLam"),
						rs.getString("ghiChu"),
						rs.getInt("trangThai"),
						cn,
						cd,
						sp
						);

				listCCCN.add(cccn_new);

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

		return listCCCN;
	}
	

	// HÀM CHẤM 1 CÔNG NHÂN ĐÃ PHÂN CÔNG
	public boolean chamCongNhan(BangChamCongCongNhan cccn) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "INSERT INTO BangChamCongCongNhan "+
			"(ngayChamCong ,gioVaoLam, maPhanCong, soLuongLam, ghiChu, trangThai) VALUES (?,?,?,?,?,?)";
			st = conn.prepareStatement(querry);
			st.setDate(1, new java.sql.Date(cccn.getNgayChamCong().getTime()));
			st.setString(2, cccn.getGioVaoLam());
			st.setString(3, cccn.getPhanCong().getMaPhanCong());
			st.setInt(4, cccn.getSoLuongLam());
			st.setString(5, cccn.getGhiChu());
			st.setInt(6, cccn.getTrangThai());

			n = st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return n > 0;

	}
	// HÀM CẬP NHẬT CHẤM CÔNG CÔNG NHÂN ĐÃ CHẤM CÔNG
	public boolean capNhatCCCN(BangChamCongCongNhan cccn) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "UPDATE [dbo].[BangChamCongCongNhan]\r\n"
					+ "   SET \r\n"
					+ "      [soLuongLam] = ? \r\n"
					+ "      ,[ghiChu] = ? \r\n"
					+ "      ,[trangThai] = ? \r\n"
					+ "  WHERE [ngayChamCong] = ? AND "
					+ "[gioVaoLam] = ? AND "
					+ "[maPhanCong]= ? ";
			st = conn.prepareStatement(querry);
			st.setInt(1, cccn.getSoLuongLam());
			st.setString(2, cccn.getGhiChu());
			st.setInt(3, cccn.getTrangThai());
			st.setDate(4, new java.sql.Date(cccn.getNgayChamCong().getTime()));
			st.setString(5, cccn.getGioVaoLam());
			st.setString(6, cccn.getPhanCong().getMaPhanCong());
			n = st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}
	// HÀM XÓA CHẤM CÔNG CÔNG NHÂN ĐÃ CHẤM CÔNG
	public boolean xoaCCCN(java.util.Date ngayChamCong, String gioVaoLam, String maPC) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "DELETE FROM [dbo].[BangChamCongCongNhan] "
					+ "WHERE [ngayChamCong] = ? AND "
					+ "[gioVaoLam] = ? AND "
					+ "[maPhanCong]= ? ";;
			st = conn.prepareStatement(querry);
			st.setDate(1, new java.sql.Date(ngayChamCong.getTime()));
			st.setString(2, gioVaoLam);
			st.setString(3, maPC);
			n = st.executeUpdate();
					
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return n >0;
		
	}
	
	
	

}

















