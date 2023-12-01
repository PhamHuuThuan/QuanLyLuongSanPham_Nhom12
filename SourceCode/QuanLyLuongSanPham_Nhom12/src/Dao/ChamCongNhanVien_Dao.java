package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.BangChamCongNhanVien;
import Entity.BangPhanCongNhanVien;
import Entity.NhanVien;

public class ChamCongNhanVien_Dao {
	//get ds chưa chấm công theo phòng ban và ngày
	public ArrayList<BangPhanCongNhanVien> getDSChuaChamCong(String maPhongBan, java.util.Date ngay) {
		ArrayList<BangPhanCongNhanVien> phanCongList = new ArrayList<>();
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        String sql = " SELECT * FROM BangPhanCongNhanVien pcnv "
	        		+ "    JOIN NhanVien nv"
	        		+ "    ON pcnv.maNhanVien = nv.maNV"
	        		+ "	   WHERE pcnv.maPhanCong NOT IN "
	        		+ "    (SELECT bccnv.phanCongNV"
	        		+ "	   FROM BangChamCongNhanVien bccnv"
	        		+ "	   WHERE bccnv.ngayChamCong = ?) "	// ds chưa chấm trong ngày đó
	        		+ "	   AND (pcnv.maPhongBan = ? OR ? IS NULL)"	//được phân công vào pb
	        		+ "	   AND pcnv.ngayCongTac <= ?"; //ngày công tác trước ngày chấm thì hiển thị
	 
	        st = con.prepareStatement(sql);
	        st.setDate(1, new java.sql.Date(ngay.getTime()));
	        st.setString(2, maPhongBan);
	        st.setString(3, maPhongBan);
	        st.setDate(4, new java.sql.Date(ngay.getTime()));
	        rs = st.executeQuery();

	        while (rs.next()) {
	            NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("matKhau"), rs.getString("hoTen"),
	                    rs.getBoolean("gioiTinh"), new java.util.Date(rs.getDate("ngaySinh").getTime()), rs.getString("sDT"), rs.getString("email"),
	                    rs.getString("soCCCD"), rs.getString("diaChi"), rs.getString("anhDaiDien"));
	            BangPhanCongNhanVien pcnv = new BangPhanCongNhanVien(rs.getString("maPhanCong"), nv, new PhongBan_Dao().timPhongBanTheoMa(rs.getString("maPhongBan")), rs.getString("chucVu"), new java.util.Date(rs.getDate("ngayCongTac").getTime()), rs.getString("ghiChu"));
	            phanCongList.add(pcnv);
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
	public ArrayList<BangChamCongNhanVien> getDSDaChamCong(String maPhongBan, java.util.Date ngay) {
	    ArrayList<BangChamCongNhanVien> chamCongList = new ArrayList<>();
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT * FROM BangChamCongNhanVien bccnv "
	                + "JOIN BangPhanCongNhanVien pcnv "
	                + "ON bccnv.phanCongNV = pcnv.maPhanCong "
	                + "JOIN NhanVien nv "
	                + "ON pcnv.maNhanVien = nv.maNV "
	                + "WHERE bccnv.ngayChamCong = ? "
	                + "AND (pcnv.maPhongBan = ? OR ? IS NULL)";

	        st = con.prepareStatement(sql);
	        st.setDate(1, new java.sql.Date(ngay.getTime()));
	        st.setString(2, maPhongBan);
	        st.setString(3, maPhongBan);
	        rs = st.executeQuery();

	        while (rs.next()) {
	            BangChamCongNhanVien bccnv = new BangChamCongNhanVien(new PhanCongNhanVien_Dao().getPhanCongTheoMa(rs.getString("phanCongNV")), 
	            		new java.util.Date(rs.getDate("ngayChamCong").getTime()), rs.getInt("caLam"), rs.getBoolean("diLam"), rs.getBoolean("coPhep"), rs.getString("gioDen"), 
	            		rs.getFloat("gioTangCa"), rs.getString("ghiChu"));
	            chamCongList.add(bccnv);
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
	    return chamCongList;
	}
// thêm chấm công nhân viên
	    public boolean themBangChamCongNhanVien(BangChamCongNhanVien bccnv) {
	        ConnectDB.getInstance();
	        PreparedStatement st = null;
	        int n = 0;
	        try {
	            Connection con = ConnectDB.getConnection();
	            String sql = "INSERT INTO BangChamCongNhanVien (phanCongNV, ngayChamCong, caLam, diLam, coPhep, gioDen, gioTangCa, ghiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	            st = con.prepareStatement(sql);
	            st.setString(1, bccnv.getPhanCong().getMaPhanCong());
	            st.setDate(2, new java.sql.Date(bccnv.getNgayChamCong().getTime()));
	            st.setInt(3, bccnv.getCaLam());
	            st.setBoolean(4, bccnv.isDiLam());
	            st.setBoolean(5, bccnv.isCoPhep());
	            st.setString(6, bccnv.getGioDen());
	            st.setFloat(7, bccnv.getGioTangCa());
	            st.setString(8, bccnv.getGhiChu());
	            n = st.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (st != null) st.close();
	            } catch (SQLException e2) {
	                e2.printStackTrace();
	            }
	        }
	        return n>0;
	    }
	    //sửa chấm công nhân viên
	    public boolean suaBangChamCongNhanVien(BangChamCongNhanVien bccnv) {
	        ConnectDB.getInstance();
	        PreparedStatement st = null;
	        int n = 0;
	        try {
	            Connection con = ConnectDB.getConnection();
	            String sql = "UPDATE BangChamCongNhanVien SET caLam = ?, diLam = ?, coPhep = ?, gioDen = ?, gioTangCa = ?, ghiChu = ? WHERE phanCongNV = ? AND ngayChamCong = ?";
	            st = con.prepareStatement(sql);
	            st.setInt(1, bccnv.getCaLam());
	            st.setBoolean(2, bccnv.isDiLam());
	            st.setBoolean(3, bccnv.isCoPhep());
	            st.setString(4, bccnv.getGioDen());
	            st.setFloat(5, bccnv.getGioTangCa());
	            st.setString(6, bccnv.getGhiChu());
	            st.setString(7, bccnv.getPhanCong().getMaPhanCong());
	            st.setDate(8, new java.sql.Date(bccnv.getNgayChamCong().getTime()));
	            n = st.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (st != null) st.close();
	            } catch (SQLException e2) {
	                e2.printStackTrace();
	            }
	        }
	        return n > 0;
	    }
// xóa chấm công
	    public boolean xoaBangChamCongNhanVien(String maPhanCong, java.util.Date ngayChamCong) {
	        ConnectDB.getInstance();
	        PreparedStatement st = null;
	        int n = 0;
	        try {
	            Connection con = ConnectDB.getConnection();
	            String sql = "DELETE FROM BangChamCongNhanVien WHERE phanCongNV = ? AND ngayChamCong = ?";
	            st = con.prepareStatement(sql);
	            st.setString(1, maPhanCong);
	            st.setDate(2, new java.sql.Date(ngayChamCong.getTime()));
	            n = st.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (st != null) st.close();
	            } catch (SQLException e2) {
	                e2.printStackTrace();
	            }
	        }
	        return n > 0;
	    }
//get ds chấm công của nhân viên theo maNV và thangNam
	    public ArrayList<BangChamCongNhanVien> getDSChamCongNhanVien(String maNV, String thangNam) {
	        ArrayList<BangChamCongNhanVien> list = new ArrayList<>();
	        ConnectDB.getInstance();
	        PreparedStatement st = null;
	        ResultSet rs = null;
	        try {
	            Connection con = ConnectDB.getConnection();
	            String sql = "SELECT * FROM BangChamCongNhanVien bccnv "
	                        + "JOIN BangPhanCongNhanVien pcnv "
	                        + "ON bccnv.phanCongNV = pcnv.maPhanCong "
	                        + "WHERE pcnv.maNhanVien = ? "
	                        + "AND MONTH(bccnv.ngayChamCong) = MONTH(CONVERT(datetime, '01/' + ?, 103)) "
	                        + "AND YEAR(bccnv.ngayChamCong) = YEAR(CONVERT(datetime, '01/' + ?, 103))";

	            st = con.prepareStatement(sql);
	            st.setString(1, maNV);
	            st.setString(2, thangNam);
	            st.setString(3, thangNam);
	            rs = st.executeQuery();

	            while (rs.next()) {
	                // Tạo đối tượng BangChamCongNhanVien từ kết quả truy vấn
		            BangChamCongNhanVien bccnv = new BangChamCongNhanVien(new BangPhanCongNhanVien(rs.getString("phanCongNV")), 
		            		new java.util.Date(rs.getDate("ngayChamCong").getTime()), rs.getInt("caLam"), rs.getBoolean("diLam"), rs.getBoolean("coPhep"), rs.getString("gioDen"), 
		            		rs.getFloat("gioTangCa"), rs.getString("ghiChu"));
	                list.add(bccnv);
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
	        return list;
	    }

}
