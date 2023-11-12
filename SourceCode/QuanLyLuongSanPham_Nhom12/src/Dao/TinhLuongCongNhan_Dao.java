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
	public double luongCoBan,luongThang,luongCongDoan,thucLanh;

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
                luongCoBan = 150000;
                luongThang = luongCoBan * (soNgayLam + soNgayPhep - soNgayNghi);
                luongCongDoan = donGia * soLuongCongDoanLam;
                thucLanh = luongThang + luongCongDoan;
                
                boolean isExisted = checkCongNhanExist(maCN,thang,nam);
                if (isExisted) {
                    // Nếu công nhân đã tồn tại, thực hiện UPDATE
                    String querryUpdate = "UPDATE BangLuongCongNhan "
                            + "SET soLuongCongDoanLam=?, soNgayLam=?, soNgayNghi=?, soNgayPhep=?, thucLanh=?, "
                            + "luongThang = ?, luongCongDoan = ? "
                            + "WHERE maCN=? AND thangNam=?";
                    
                    PreparedStatement st_update = conn.prepareStatement(querryUpdate);
                    st_update.setInt(1, soLuongCongDoanLam);
                    st_update.setInt(2, soNgayLam);
                    st_update.setInt(3, soNgayNghi);
                    st_update.setInt(4, soNgayPhep);
                    st_update.setDouble(5, thucLanh);
                    st_update.setDouble(6, luongThang);
                    st_update.setDouble(7, luongCongDoan);
                    st_update.setString(8, maCN);
                    st_update.setString(9, thang+"/"+nam);
                    
                    n = st_update.executeUpdate();
                } else {
                    // Nếu công nhân chưa tồn tại, thực hiện INSERT
                    String querryInsert = "INSERT INTO BangLuongCongNhan "
                            + "(maBangLuong, maCN, soLuongCongDoanLam, soNgayLam, soNgayNghi, soNgayPhep, thucLanh, thangNam, "
                            + "luongThang, luongCongDoan) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)";
                    
                    PreparedStatement st_is = conn.prepareStatement(querryInsert);
                    st_is.setString(1, maTuDong.sinhMaBLCN());
                    st_is.setString(2, maCN);
                    st_is.setInt(3, soLuongCongDoanLam);
                    st_is.setInt(4, soNgayLam);
                    st_is.setInt(5, soNgayNghi);
                    st_is.setInt(6, soNgayPhep);
                    st_is.setDouble(7, thucLanh);
                    st_is.setString(8, thang+"/"+nam);
                    st_is.setDouble(9, luongThang);
                    st_is.setDouble(10, luongCongDoan);

                    n = st_is.executeUpdate();
                    
                    listCC.removeAll(listCC);
                }
                

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
	// HÀM CHECK ĐÃ TỒN TẠI CÔNG NHÂN TRONG BẢNG TINH LƯƠNG
	private boolean checkCongNhanExist(String maCN, int thang, int nam) throws SQLException {
	    String querryCheck = "SELECT COUNT(*) FROM BangLuongCongNhan WHERE maCN=? AND thangNam=?";
	    Connection conn = ConnectDB.getConnection();
	    PreparedStatement st_check = conn.prepareStatement(querryCheck);
	    st_check.setString(1, maCN);
	    st_check.setString(2, thang+"/"+nam);
	    
	    ResultSet rs_check = st_check.executeQuery();
	    rs_check.next();
	    int count = rs_check.getInt(1);
	    
	    return count > 0;
	}
	// HÀM GET DATA BẢNG LƯƠNG 
	public ArrayList<BangLuongCongNhan> getAllBLCN(){
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<BangLuongCongNhan> listBLCN = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT *  FROM [QuanLyLuongSanPham-TPT].[dbo].[BangLuongCongNhan] blcn\r\n"
					+ "JOIN [dbo].[CongNhan] cn ON blcn.maCN = cn.maCN";
			st = conn.prepareStatement(querry);
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				CongNhan cn = new CongNhan(rs.getString("maCN"));
				BangChamCongCongNhan cccn = new BangChamCongCongNhan(rs.getInt("soLuongCongDoanLam"));
				BangLuongCongNhan blcn = new BangLuongCongNhan(
						rs.getString("maBangLuong"),
						cn,
						cccn,
						rs.getInt("soNgayLam"),
						rs.getInt("soNgayNghi"),
						rs.getInt("soNgayPhep"),
						rs.getDouble("luongThang"),
						rs.getDouble("luongCongDoan"),
						rs.getDouble("thucLanh"),
						rs.getString("thangNam")
						);
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
	
	
	
}











