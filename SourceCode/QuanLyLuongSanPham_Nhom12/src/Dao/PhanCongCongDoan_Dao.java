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
	public ArrayList<CongDoan> getAllCongDoan(String maSP) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<CongDoan> listCD = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "WITH CongDoanWithConLai AS ( \r\n"
					+ "	SELECT cd.maCD, cd.tenCD, cd.soLuong, cd.maSP, cd.tinhTrang, cd.donGia, cd.thuTu, cd.ngayHoanThanh,\r\n"
					+ "	cd.soLuong - COALESCE(SUM(pccd.soLuongCanLam), 0) AS soLuongConLai \r\n"
					+ "	FROM [dbo].[CongDoan] cd LEFT JOIN BangPhanCongCongDoan pccd ON pccd.maCongDoan = cd.maCD \r\n"
					+ "	LEFT JOIN CongNhan cn ON pccd.maCN = cn.maCN \r\n"
					+ "	LEFT JOIN SanPham sp ON cd.maSP = sp.maSP\r\n"
					+ "	GROUP BY cd.maCD, cd.tenCD, cd.soLuong, cd.maSP, cd.tinhTrang, cd.ngayHoanThanh, cd.donGia, cd.thuTu \r\n"
					+ ")\r\n"
					+ "SELECT CongDoanWithConLai.*, sp.tenSP FROM  \r\n"
					+ "CongDoanWithConLai LEFT JOIN SanPham sp ON CongDoanWithConLai.maSP = sp.maSP \r\n"
					+ "WHERE (CongDoanWithConLai.tinhTrang LIKE '0' OR CongDoanWithConLai.tinhTrang IS NULL) \r\n"
					+ "AND CongDoanWithConLai.soLuongConLai > 0 AND CongDoanWithConLai.maSP LIKE ?";
			st = conn.prepareStatement(querry);
			
			st.setString(1, "%"+ maSP + "%");
			
			rs = st.executeQuery();

			while (rs.next()) {
				CongDoan cd = new CongDoan(rs.getString("maCD"), rs.getString("tenCD"), rs.getInt("thuTu"),
						rs.getInt("soLuong"),rs.getInt("soLuongConLai"), rs.getDouble("donGia"), rs.getBoolean("tinhTrang"),
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
			String querry = "SELECT * FROM CongNhan cn WHERE NOT EXISTS (SELECT 1 FROM "
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
	// HÀM LẤY DANH SÁCH SẢN PHẨM ĐÃ CÓ CÔNG ĐOẠN
	public ArrayList<SanPham> getAllSPDaCoCongDoan(){
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<SanPham> listSPDaCoCongDoan = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT DISTINCT cd.maSP, sp.tenSP, cd.tinhTrang "
					+ "FROM [dbo].[CongDoan] cd JOIN [dbo].[SanPham] sp ON cd.maSP = sp.maSP "
					+ "WHERE cd.tinhTrang <> 1 ";
			st = conn.prepareStatement(querry);
			rs = st.executeQuery();
			
			while(rs.next()) {
				SanPham sp = new SanPham(rs.getString("maSP"),rs.getString("tenSP"));
				listSPDaCoCongDoan.add(sp);
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
		return listSPDaCoCongDoan;
	}
	

	// HÀM LẤY DANH SÁCH PHÂN CÔNG CÔNG ĐOẠN
	public ArrayList<BangPhanCongCongDoan> getAllPhanCongCongDoan() {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<BangPhanCongCongDoan> listPCCD = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "WITH CongDoanWithConLai AS (\r\n"
					+ "    SELECT \r\n"
					+ "        cd.maCD,cd.tenCD, cd.soLuong, cd.maSP, pccd.maPhanCong,\r\n"
					+ "		pccd.ngayPhanCong, pccd.soLuongCanLam, pccd.ghiChu,\r\n"
					+ "		cn.maCN, cn.hoTen, \r\n"
					+ "        cd.soLuong - COALESCE(SUM(pccd.soLuongCanLam), 0) AS soLuongConLai\r\n"
					+ "    FROM \r\n"
					+ "        CongDoan cd\r\n"
					+ "        LEFT JOIN BangPhanCongCongDoan pccd ON pccd.maCongDoan = cd.maCD\r\n"
					+ "        LEFT JOIN CongNhan cn ON pccd.maCN = cn.maCN \r\n"
					+ "        LEFT JOIN SanPham sp ON cd.maSP = sp.maSP\r\n"
					+ "    GROUP BY \r\n"
					+ "        cd.maCD, cd.tenCD, cd.soLuong, cd.maSP, pccd.maPhanCong, \r\n"
					+ "		pccd.ngayPhanCong, pccd.soLuongCanLam, pccd.ghiChu,\r\n"
					+ "		cn.maCN, cn.hoTen\r\n"
					+ ")\r\n"
					+ "SELECT \r\n"
					+ "    CongDoanWithConLai.*, sp.*\r\n"
					+ "FROM \r\n"
					+ "    CongDoanWithConLai LEFT JOIN SanPham sp ON CongDoanWithConLai.maSP = sp.maSP\r\n"
					+ "WHERE \r\n"
					+ "    CongDoanWithConLai.maPhanCong IS NOT NULL";
			st = conn.prepareStatement(querry);
			rs = st.executeQuery();

			while (rs.next()) {
				CongNhan congNhan = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"));
				CongDoan congDoan = new CongDoan(rs.getString("maCD"), rs.getString("tenCD"), rs.getInt("soLuong"), rs.getInt("soLuongConLai"));
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
	// HÀM CHECK XEM NẾU ĐÃ CHẤM CÔNG THÌ KHÔNG XOÁ PHÂN CÔNG ẤY ĐƯỢC
	public boolean checkPhanCongDaTonTaiOChamCong(String maPhanCong) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean n = false;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT * FROM [dbo].[BangPhanCongCongDoan] pccd JOIN [dbo].[BangChamCongCongNhan] cccn "
					+ "ON pccd.maPhanCong = cccn.maPhanCong "
					+ "WHERE pccd.maPhanCong LIKE ?";
			st = conn.prepareStatement(querry);
			st.setString(1,"%"+ maPhanCong + "%");
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				n = true;
			}
			
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
		return n;
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
	
//	// HÀM LẤY TỔNG SỐ LƯỢNG VÀ TỔNG SỐ LƯỢNG
//	public ArrayList<BangPhanCongCongDoan> getSLAndSLConLai( String maCD, String maSP ){
//		ArrayList<CongDoan> listCD = new ArrayList<>();
//		ConnectDB.getInstance();
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		try {
//			Connection conn = ConnectDB.getConnection();
//			String querry = "WITH CongDoanWithConLai AS (\r\n"
//					+ "    SELECT \r\n"
//					+ "        cd.maCD, cd.soLuong, cd.maSP,\r\n"
//					+ "        cd.soLuong - COALESCE(SUM(pccd.soLuongCanLam), 0) AS soLuongConLai\r\n"
//					+ "    FROM \r\n"
//					+ "        CongDoan cd\r\n"
//					+ "        LEFT JOIN BangPhanCongCongDoan pccd ON pccd.maCongDoan = cd.maCD\r\n"
//					+ "        LEFT JOIN CongNhan cn ON pccd.maCN = cn.maCN \r\n"
//					+ "        LEFT JOIN SanPham sp ON cd.maSP = sp.maSP\r\n"
//					+ "    GROUP BY \r\n"
//					+ "        cd.maCD, cd.soLuong, cd.maSP\r\n"
//					+ ")\r\n"
//					+ "SELECT \r\n"
//					+ "    CongDoanWithConLai.*\r\n"
//					+ "FROM \r\n"
//					+ "    CongDoanWithConLai LEFT JOIN SanPham sp ON CongDoanWithConLai.maSP = sp.maSP\r\n"
//					+ "WHERE \r\n"
//					+ "    CongDoanWithConLai.maCD LIKE ? AND  CongDoanWithConLai.maSP LIKE ?";
//			
//			st = conn.prepareStatement(querry);
//			
//			st.setString(1, "%" + maCD + "%");
//			st.setString(2, "%" + maSP + "%");
//			
//			rs  = st.executeQuery();
//			while(rs.next()) {
//				CongDoan cd = new CongDoan(
//						rs.getString("soLuong"),
//						rs.getString("soLuongConLai")
//						);
//				listCD.add(cd);
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally {
//			try {
//				if(st !=null) rs.close();
//				if(rs != null) st.close();
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//		
//		return listCD;
//	}
	
	

}









