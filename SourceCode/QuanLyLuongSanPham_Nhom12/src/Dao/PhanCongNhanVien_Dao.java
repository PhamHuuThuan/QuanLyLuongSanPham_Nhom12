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
	//get ma phan cong lon nhat
	public String getMaPCLonNhat() {
	    String maPCLonNhat = null;
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT MAX(maPhanCong) as maPhanCong FROM BangPhanCongNhanVien");
	        rs = st.executeQuery();

	        if (rs.next()) {
	            maPCLonNhat = rs.getString("maPhanCong");
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

	    return maPCLonNhat;
	}
	//get ds phan cong theo phong ban
	public ArrayList<BangPhanCongNhanVien> getDSPhanCongTheoPhongBan(String maPhongBan) {
	    ArrayList<BangPhanCongNhanVien> phanCongList = new ArrayList<>();
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT * FROM BangPhanCongNhanVien pcnv JOIN NhanVien nv ON pcnv.maNhanVien = nv.maNV WHERE pcnv.maPhongBan = ? AND pcnv.chucVu IS NOT NULL");
	        st.setString(1, maPhongBan);
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

	public ArrayList<BangPhanCongNhanVien> getAllPhanCong() {
	    ArrayList<BangPhanCongNhanVien> phanCongList = new ArrayList<>();
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT * FROM BangPhanCongNhanVien pcnv Join NhanVien nv on pcnv.maNhanVien = nv.maNV WHERE pcnv.chucVu IS NOT NULL");
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

	//thêm phân công vào csdl
	public boolean luuPhanCong(BangPhanCongNhanVien pcnv) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    int n = 0;
	    try {
	        Connection con = ConnectDB.getConnection();
	        String query = "INSERT INTO BangPhanCongNhanVien (maPhanCong, maNhanVien, maPhongBan, chucVu, ngayCongTac, ghiChu) VALUES (?, ?, ?, ?, ?, ?)";
	        st = con.prepareStatement(query);
	        st.setString(1, pcnv.getMaPhanCong());
	        st.setString(2, pcnv.getNhanVien().getMaNV());
	        st.setString(3, pcnv.getPhongBan().getMaPhongBan());
	        st.setString(4, pcnv.getChucVu());
	        st.setDate(5, new java.sql.Date(pcnv.getNgayCongTac().getTime()));
	        st.setString(6, pcnv.getGhiChu());

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
	//cập nhật phân công
	public boolean capNhatPhanCong(BangPhanCongNhanVien pcnv) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    int n = 0;
	    try {
	        Connection con = ConnectDB.getConnection();
	        String query = "UPDATE BangPhanCongNhanVien SET maNhanVien = ?, maPhongBan = ?, chucVu = ?, ngayCongTac = ?, ghiChu = ? WHERE maPhanCong = ?";
	        st = con.prepareStatement(query);
	        st.setString(1, pcnv.getNhanVien().getMaNV());
	        st.setString(2, pcnv.getPhongBan().getMaPhongBan());
	        st.setString(3, pcnv.getChucVu());
	        st.setDate(4, new java.sql.Date(pcnv.getNgayCongTac().getTime()));
	        st.setString(5, pcnv.getGhiChu());
	        st.setString(6, pcnv.getMaPhanCong());

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
	//Xóa phân công
	public boolean xoaThongTinPhanCong(String maPhanCong) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    int n = 0;
	    try {
	        Connection con = ConnectDB.getConnection();
	        String query = "UPDATE BangPhanCongNhanVien SET maPhongBan = NULL, chucVu = NULL, ghiChu = NULL WHERE maPhanCong = ?";
	        st = con.prepareStatement(query);
	        st.setString(1, maPhanCong);

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
	//kiểm tra xem tồn tại mã phân công hay chưa
	public BangPhanCongNhanVien kiemTraPhanCong(String maNV) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    BangPhanCongNhanVien pcnv = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        String query = "SELECT * FROM BangPhanCongNhanVien WHERE maNhanVien = ?";
	        st = con.prepareStatement(query);
	        st.setString(1, maNV);
	        rs = st.executeQuery();

	        if (rs.next()) {
	            pcnv = new BangPhanCongNhanVien(rs.getString("maPhanCong"), new NhanVien(rs.getString("maNhanVien")), new PhongBan_Dao().timPhongBanTheoMa(rs.getString("maPhongBan")), rs.getString("chucVu"), new java.util.Date(rs.getDate("ngayCongTac").getTime()), rs.getString("ghiChu"));
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

	    return pcnv;
	}
	//get phân công theo phòng ban và chức vụ
	public ArrayList<BangPhanCongNhanVien> getDSPhanCong(String maPhongBan, String chucVu) {
	    ArrayList<BangPhanCongNhanVien> phanCongList = new ArrayList<>();
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT * FROM BangPhanCongNhanVien pcnv JOIN NhanVien nv ON pcnv.maNhanVien = nv.maNV WHERE (pcnv.maPhongBan = ? OR ? IS NULL) AND (pcnv.chucVu = ? OR ? IS NULL)";
	        st = con.prepareStatement(sql);
	        st.setString(1, maPhongBan);
	        st.setString(2, maPhongBan);
	        st.setString(3, chucVu);
	        st.setString(4, chucVu);
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
//get phân công theo mã phân công
	public BangPhanCongNhanVien getPhanCongTheoMa(String maPhanCong) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    BangPhanCongNhanVien pcnv = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT * FROM BangPhanCongNhanVien pcnv JOIN NhanVien nv ON pcnv.maNhanVien = nv.maNV WHERE pcnv.maPhanCong = ?");
	        st.setString(1, maPhanCong);
	        rs = st.executeQuery();

	        if (rs.next()) {
	            NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("matKhau"), rs.getString("hoTen"),
	                    rs.getBoolean("gioiTinh"), new java.util.Date(rs.getDate("ngaySinh").getTime()), rs.getString("sDT"), rs.getString("email"),
	                    rs.getString("soCCCD"), rs.getString("diaChi"), rs.getString("anhDaiDien"));
	            pcnv = new BangPhanCongNhanVien(rs.getString("maPhanCong"), nv, new PhongBan_Dao().timPhongBanTheoMa(rs.getString("maPhongBan")), rs.getString("chucVu"), new java.util.Date(rs.getDate("ngayCongTac").getTime()), rs.getString("ghiChu"));
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
	    return pcnv;
	}
	//kiểm tra login
	public BangPhanCongNhanVien kiemTraDangNhap(String maNV, String matKhau) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    BangPhanCongNhanVien pcnv = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT * FROM NhanVien, BangPhanCongNhanVien WHERE NhanVien.maNV = BangPhanCongNhanVien.maNhanVien AND NhanVien.maNV = ? AND NhanVien.matKhau = ?");
	        st.setString(1, maNV);
	        st.setString(2, matKhau);
	        rs = st.executeQuery();

	        if (rs.next()) {
	            // Tạo đối tượng BangPhanCongNhanVien từ kết quả truy vấn
	            NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("matKhau"), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), rs.getDate("ngaySinh"), rs.getString("sDT"), rs.getString("email"), rs.getString("soCCCD"), rs.getString("diaChi"), rs.getString("anhDaiDien"));
	            pcnv = new BangPhanCongNhanVien(rs.getString("maPhanCong"), nv, new PhongBan_Dao().timPhongBanTheoMa(rs.getString("maPhongBan")), rs.getString("chucVu"), new java.util.Date(rs.getDate("ngayCongTac").getTime()), rs.getString("ghiChu"));
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

	    return pcnv;
	}


}
