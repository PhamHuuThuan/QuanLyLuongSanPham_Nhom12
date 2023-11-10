package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ConnectDB.ConnectDB;
import Entity.BangLuongNhanVien;
import Entity.BangPhanCongNhanVien;
import Entity.NhanVien;

public class TinhLuongNhanVien_Dao {
	//Lấy danh sách chưa được tính lương theo phòng ban và tháng năm
	public ArrayList<BangPhanCongNhanVien> getDSChuaTinhLuong(String maPhongBan, String thangNam) {
	    ArrayList<BangPhanCongNhanVien> phanCongList = new ArrayList<>();
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT * FROM BangPhanCongNhanVien pcnv "
	                    + "JOIN NhanVien nv "
	                    + "ON pcnv.maNhanVien = nv.maNV "
	                    + "WHERE pcnv.maNhanVien NOT IN "
	                    + "(SELECT blnv.maNhanVien "
	                    + "FROM BangLuongNhanVien blnv "
	                    + "WHERE blnv.thangNam = ?) "
	                    + "AND (pcnv.maPhongBan = ? OR ? IS NULL)";

	        st = con.prepareStatement(sql);
	        st.setString(1, thangNam);
	        st.setString(2, maPhongBan);
	        st.setString(3, maPhongBan);
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
// lấy danh sách bảng lương theo mã phòng ban và tháng năm
	public ArrayList<BangLuongNhanVien> getDSTinhLuong(String maPhongBan, String thangNam) {
	    ArrayList<BangLuongNhanVien> luongList = new ArrayList<>();
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT * FROM BangLuongNhanVien blnv "
	                    + "JOIN NhanVien nv "
	                    + "ON blnv.maNhanVien = nv.maNV "
	                    + "JOIN BangPhanCongNhanVien pcnv "
	                    + "ON pcnv.maNhanVien = nv.maNV "
	                    + "WHERE blnv.thangNam = ? "
	                    + "AND (pcnv.maPhongBan = ? OR ? IS NULL)";

	        st = con.prepareStatement(sql);
	        st.setString(1, thangNam);
	        st.setString(2, maPhongBan);
	        st.setString(3, maPhongBan);
	        rs = st.executeQuery();

	        while (rs.next()) {
	            NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("matKhau"), rs.getString("hoTen"),
	                    rs.getBoolean("gioiTinh"), new java.util.Date(rs.getDate("ngaySinh").getTime()), rs.getString("sDT"), rs.getString("email"),
	                    rs.getString("soCCCD"), rs.getString("diaChi"), rs.getString("anhDaiDien"));
	            String str = rs.getString("thangNam");
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
	            YearMonth ym = YearMonth.parse(str, formatter);
	            BangLuongNhanVien blnv = new BangLuongNhanVien(rs.getString("maBangLuong"), nv, rs.getFloat("soNgayLam"), rs.getFloat("soNgayNghi"),
	            		rs.getFloat("soNgayPhep"), rs.getDouble("luongThang"), rs.getDouble("luongTangCa"), rs.getDouble("phuCap"), rs.getDouble("thucLanh"), 
	            		ym, rs.getString("ghiChu"));
	            luongList.add(blnv);
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
	    return luongList;
	}
	//thêm mới bảng lương vào csdl
	public boolean themBangLuongNhanVien(BangLuongNhanVien blnv) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    int n = 0;
	    try {
	        Connection con = ConnectDB.getConnection();
	        String sql = "INSERT INTO BangLuongNhanVien (maBangLuong, maNhanVien, soNgayLam, soNgayNghi, soNgayPhep, luongThang, luongTangCa, phuCap, thucLanh, thangNam, ghiChu) "
	                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        st = con.prepareStatement(sql);
	        st.setString(1, blnv.getMaBangLuong());
	        st.setString(2, blnv.getNhanVien().getMaNV());
	        st.setFloat(3, blnv.getNgayLam());
	        st.setFloat(4, blnv.getNgayNghi());
	        st.setFloat(5, blnv.getNgayNghiPhep());
	        st.setDouble(6, blnv.getLuongThang());
	        st.setDouble(7, blnv.getLuongTangCa());
	        st.setDouble(8, blnv.getPhuCap());
	        st.setDouble(9, blnv.getThucLanh());
	        st.setString(10, DateTimeFormatter.ofPattern("MM/yyyy").format(blnv.getThangNam()));
	        st.setString(11, blnv.getGhiChu());

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
	//lấy mã bảng lương lớn nhất từ csdl
	public String getMaBangLuongLonNhat() {
	    String maBangLuongLonNhat = null;
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT MAX(maBangLuong) AS maBangLuongLonNhat FROM BangLuongNhanVien";

	        st = con.prepareStatement(sql);
	        rs = st.executeQuery();

	        if (rs.next()) {
	            maBangLuongLonNhat = rs.getString("maBangLuongLonNhat");
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

	    return maBangLuongLonNhat;
	}
	// Danh sách top 5 nhân viên lương cao nhất
	public List<String[]> layTop5NhanVienLuongCaoNhat(String thangNam) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    List<String[]> danhSachNhanVien = new ArrayList<>();
	    try {
	        Connection con = ConnectDB.getConnection();
	        String query = "SELECT TOP 5 NhanVien.HoTen, BangLuongNhanVien.thucLanh FROM NhanVien INNER JOIN BangLuongNhanVien ON NhanVien.maNV = BangLuongNhanVien.maNhanVien WHERE BangLuongNhanVien.thangNam = ? ORDER BY BangLuongNhanVien.thucLanh DESC";
	        st = con.prepareStatement(query);
	        st.setString(1, thangNam);
	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	            String hoTen = rs.getString("HoTen");
	            double thucLanh = rs.getDouble("thucLanh");
	            // Lưu tên nhân viên và thực lãnh vào danh sách
	            danhSachNhanVien.add(new String[] {hoTen, String.valueOf(thucLanh)});
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
	    return danhSachNhanVien;
	}

	// Lấy tên phòng ban và lương trung bình từ csdl và lưu vào Map
	public Map<String, Double> layTenPhongBanVaLuongTrungBinh(String thangNam) {
	    Map<String, Double> luongTrungBinhTheoPhongBan = new HashMap<>();
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT pb.tenPB, AVG(blnv.thucLanh) as LuongTrungBinh "
	                    + "FROM PhongBan pb "
	                    + "JOIN BangPhanCongNhanVien pcnv ON pb.maPB = pcnv.maPhongBan "
	                    + "JOIN BangLuongNhanVien blnv ON pcnv.maNhanVien = blnv.maNhanVien "
	                    + "WHERE blnv.thangNam = ? "
	                    + "GROUP BY pb.tenPB "
	                    + "ORDER BY pb.tenPB";

	        st = con.prepareStatement(sql);
	        st.setString(1, thangNam);
	        rs = st.executeQuery();

	        while (rs.next()) {
	            String tenPhongBan = rs.getString("tenPB");
	            double luongTrungBinh = rs.getDouble("LuongTrungBinh");
	            // Lưu tên phòng ban và lương trung bình vào Map
	            luongTrungBinhTheoPhongBan.put(tenPhongBan, luongTrungBinh);
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
	    return luongTrungBinhTheoPhongBan;
	}


}
