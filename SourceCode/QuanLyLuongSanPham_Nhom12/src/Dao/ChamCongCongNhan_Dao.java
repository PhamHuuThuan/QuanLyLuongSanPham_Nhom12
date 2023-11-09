package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
					+ "cd.tenCD, cd.thuTu FROM [dbo].[BangPhanCongCongDoan] "
					+ "pccd JOIN [dbo].[CongNhan] cn ON pccd.maCN = cn.maCN "
					+ "JOIN [dbo].[CongDoan] cd ON pccd.maCongDoan  = cd.maCD "
					+ "JOIN [dbo].[SanPham] sp ON cd.maSP = sp.maSP "
					+ "GROUP BY cd.maSP, sp.tenSP, pccd.maCongDoan, cd.tenCD, cd.thuTu;";
			st = conn.prepareStatement(querry);
			rs = st.executeQuery();

			while (rs.next()) {
				SanPham sp = new SanPham(rs.getString("maSp"), rs.getString("tenSP"));
				CongDoan cd = new CongDoan(rs.getString("maCongDoan"), rs.getString("tenCD"), rs.getInt("thuTu"));
				BangPhanCongCongDoan pccd_1 = new BangPhanCongCongDoan(sp, cd);

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
		public ArrayList<CongNhan> getALLCongNhan(String maSP,String maCongDoan) {
			ConnectDB.getInstance();
			PreparedStatement st = null;
			ResultSet rs = null;
			ArrayList<CongNhan> listCN = new ArrayList<>();

			try {
				Connection conn = ConnectDB.getConnection();
				String querry = "SELECT cn.maCN , cn.hoTen, cn.ngaySinh FROM [dbo].[BangPhanCongCongDoan] "
						+ "pccd JOIN [dbo].[CongNhan] cn ON pccd.maCN = cn.maCN "
						+ "JOIN [dbo].[CongDoan] cd ON pccd.maCongDoan  = cd.maCD JOIN [dbo].[SanPham] sp ON cd.maSP = sp.maSP "
						+ "WHERE cd.maSP LIKE ? AND pccd.maCongDoan LIKE ?";
				st = conn.prepareStatement(querry);
				
				st.setString(1, "%" + maSP + "%");
				st.setString(2, "%" + maCongDoan + "%");
				
				rs = st.executeQuery();

				while (rs.next()) {
					CongNhan cn_new = new CongNhan(
							rs.getString("maCN"),
							rs.getString("hoTen"),
							rs.getDate("ngaySinh")
							);

					listCN.add(cn_new);
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
		
		// HÀM CHẤM CHÂM CÔNG CÔNG NHÂN ĐÃ PHÂN CÔNG
	
		
		
		
}









