package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.BangPhanCongNhanVien;
import Entity.NhanVien;
import Entity.PhongBan;

public class PhanCongNhanVien_Dao {
	//tìm kiếm phân công nhân viên phù hợp
	public BangPhanCongNhanVien timPCNhanVien(String maNV) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT * FROM BangPhanCongNhanVien WHERE maNhanVien = ?");
	        st.setString(1, maNV);
	        rs = st.executeQuery();
	        while (rs.next()) {
	        	BangPhanCongNhanVien pcnv = new BangPhanCongNhanVien(rs.getString("maPhanCong"), new NhanVien(rs.getString("maNhanVien")), new PhongBan(rs.getString("maPhongBan")), rs.getString("chucVu"), new java.util.Date(rs.getDate("ngayCongTac").getTime()), rs.getString("ghiChu"));
	            return pcnv;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	        } catch (SQLException e2) {
	            e2.printStackTrace();
	        }
	    }
	    return new BangPhanCongNhanVien();
	}
	//get ds pc theo nhân viên
	public ArrayList<BangPhanCongNhanVien> getPhanCong(ArrayList<NhanVien> nhanVienList) {
	    ArrayList<BangPhanCongNhanVien> phanCongList = new ArrayList<>();
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        for (NhanVien nv : nhanVienList) {
	            st = con.prepareStatement("SELECT * FROM BangPhanCongNhanVien WHERE maNhanVien = ?");
	            st.setString(1, nv.getMaNV());
	            rs = st.executeQuery();

	            if (rs.next()) {
	                // Tạo đối tượng BangPhanCongNhanVien từ kết quả truy vấn và thêm vào danh sách
	                BangPhanCongNhanVien pcnv = new BangPhanCongNhanVien(rs.getString("maPhanCong"), nv, new PhongBan_Dao().timPhongBanTheoMa(rs.getString("maPhongBan")), rs.getString("chucVu"), new java.util.Date(rs.getDate("ngayCongTac").getTime()), rs.getString("ghiChu"));
	                // Cập nhật các thuộc tính cho đối tượng BangPhanCongNhanVien từ kết quả truy vấn
	                phanCongList.add(pcnv);
	            } else {
	                // Nếu nhân viên không có phân công, thêm một đối tượng BangPhanCongNhanVien rỗng vào danh sách
	                phanCongList.add(new BangPhanCongNhanVien());
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	        } catch (SQLException e2) {
	            e2.printStackTrace();
	        }
	    }

	    return phanCongList;
	}
}
