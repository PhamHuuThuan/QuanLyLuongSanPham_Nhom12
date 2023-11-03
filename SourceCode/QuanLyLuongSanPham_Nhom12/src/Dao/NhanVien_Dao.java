package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.lowagie.text.List;

import ConnectDB.ConnectDB;
import Entity.NhanVien;

public class NhanVien_Dao {
	//sửa hợp đồng trong csdl
		public boolean suaThongTinNhanVien(NhanVien nv) {
			ConnectDB.getInstance();
			PreparedStatement st = null;
			int n=0;
			try {
				Connection con = ConnectDB.getConnection();
				st = con.prepareStatement("UPDATE NhanVien SET matKhau=?, hoTen=?, gioiTinh=?, "
						+ "ngaySinh=?, sDT=?, email=?, soCCCD=?,"
						+ "diaChi=?, anhDaiDien=? where maNV=?");
				st.setString(1, nv.getMatKhau());
				st.setString(2, nv.getHoTen());
				st.setBoolean(3, nv.isGioiTinh());
				st.setDate(4, new Date(nv.getNgaySinh().getTime()));
				st.setString(5, nv.getSdt());
				st.setString(6, nv.getEmail());
				st.setString(7,nv.getcCCD());
				st.setString(8,nv.getDiaChi());
				st.setString(9,nv.getHinhAnh());
				st.setString(10,nv.getMaNV());
				n = st.executeUpdate();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					st.close();
				} catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			return n>0;
		}
		// Thêm nhân viên vào csdl
		public boolean themNhanVien(NhanVien nv) {
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    int n = 0;
		    try {
		        Connection con = ConnectDB.getConnection();
		        st = con.prepareStatement("INSERT INTO NhanVien VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		        st.setString(1, nv.getMaNV());
		        st.setString(2, nv.getMatKhau());
		        st.setString(3, nv.getHoTen());
		        st.setBoolean(4, nv.isGioiTinh());
		        st.setDate(5, new java.sql.Date(nv.getNgaySinh().getTime()));
		        st.setString(6, nv.getSdt());
		        st.setString(7, nv.getEmail());
		        st.setString(8,nv.getcCCD());
		        st.setString(9,nv.getDiaChi());
		        st.setString(10,nv.getHinhAnh());
		        n = st.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            st.close();
		        } catch (SQLException e2) {
		            e2.printStackTrace();
		        }
		    }
		    return n > 0;
		}

		// Xóa nhân viên khỏi csdl
		public boolean xoaNhanVien(String maNV) {
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    int n = 0;
		    try {
		        Connection con = ConnectDB.getConnection();
		        st = con.prepareStatement("DELETE FROM NhanVien WHERE maNV = ?");
		        st.setString(1, maNV);
		        n = st.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            st.close();
		        } catch (SQLException e2) {
		            e2.printStackTrace();
		        }
		    }
		    return n > 0;
		}

		// Lấy tất cả nhân viên từ csdl
		public ArrayList<NhanVien> getAllNhanVien() {
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    ResultSet rs = null;
		    ArrayList<NhanVien> listNV = new ArrayList<>();
		    
		    try {
		        Connection con = ConnectDB.getConnection();
		        st = con.prepareStatement("SELECT * FROM NhanVien");
		        rs = st.executeQuery();

		        while(rs.next()) {
		            NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("matKhau"), rs.getString("hoTen"),
		            		rs.getBoolean("gioiTinh"), new java.util.Date(rs.getDate("ngaySinh").getTime()), rs.getString("sDT"), rs.getString("email"),
		            		rs.getString("soCCCD"), rs.getString("diaChi"), rs.getString("anhDaiDien"));
		            
		            listNV.add(nv);
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            rs.close();
		            st.close();
		        } catch (SQLException e2) {
		            e2.printStackTrace();
		        }
		    }
		    
		    return listNV;
		}
		public String getMaNhanVienLonNhat() {
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    ResultSet rs = null;
		    String maNhanVienLonNhat = null;

		    try {
		        Connection con = ConnectDB.getConnection();
		        st = con.prepareStatement("SELECT MAX(maNV) AS MaNVMax FROM NhanVien");
		        rs = st.executeQuery();

		        if (rs.next()) {
		            maNhanVienLonNhat = rs.getString("MaNVMax");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            rs.close();
		            st.close();
		        } catch (SQLException e2) {
		            e2.printStackTrace();
		        }
		    }

		    return maNhanVienLonNhat;
		}
		//tìm kiếm danh sách nhân viên phù hợp
		public ArrayList<NhanVien> timNhanVien(String maNV, String hoTen, int nam, int nu, String sdt, String cCCD, String diaChi, java.util.Date ngaySinh, String maPhongBan) {
		    ArrayList<NhanVien> list = new ArrayList<>();
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    ResultSet rs = null;
		    try {
		        Connection con = ConnectDB.getConnection();
		        String query = "  SELECT * FROM NhanVien nv LEFT JOIN BangPhanCongNhanVien pcnv ON nv.maNV = pcnv.maNhanVien "
		        		+ "  WHERE nv.maNV LIKE ?"
		        		+ "  AND nv.hoTen LIKE ? "
		        		+ "  AND nv.gioiTinh in (?,?) "
		        		+ "  AND nv.sDT LIKE ?"
		        		+ "  AND nv.soCCCD LIKE ?"
		        		+ "  AND nv.diaChi LIKE ?";
		        if (ngaySinh != null) {
		            query += " AND nv.ngaySinh = ?";
		        }
		        if(!maPhongBan.equals("PB00")) {
		        	query += " AND pcnv.maPhongBan = '" + maPhongBan +"'";
		        }
		        st = con.prepareStatement(query);
		        st.setString(1, "%" + maNV + "%");
		        st.setString(2, "%" + hoTen + "%");
		        st.setInt(3, nam);
		        st.setInt(4, nu);
		        st.setString(5, "%" + sdt + "%");
		        st.setString(6, "%" + cCCD + "%");
		        st.setString(7, "%" + diaChi + "%");
		        if(ngaySinh != null)
		        	st.setDate(8, new Date(ngaySinh.getTime()));
		        rs = st.executeQuery();
		        while (rs.next()) {
		        	 NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("matKhau"), rs.getString("hoTen"),
			            		rs.getBoolean("gioiTinh"), new java.util.Date(rs.getDate("ngaySinh").getTime()), rs.getString("sDT"), rs.getString("email"),
			            		rs.getString("soCCCD"), rs.getString("diaChi"), rs.getString("anhDaiDien"));
		            // Cập nhật các thuộc tính cho đối tượng NhanVien từ kết quả truy vấn
		            list.add(nv);
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
		//tìm nhân viên chưa phân công
		public ArrayList<NhanVien> timNhanVienChuaPhanCong() {
		    ArrayList<NhanVien> list = new ArrayList<>();
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    ResultSet rs = null;
		    try {
		        Connection con = ConnectDB.getConnection();
		        String query = "SELECT distinct * FROM NhanVien nv LEFT JOIN BangPhanCongNhanVien pcnv ON nv.maNV = pcnv.maNhanVien "
		                + "  WHERE pcnv.maPhongBan IS NULL OR pcnv.chucVu IS NULL";
		        st = con.prepareStatement(query);
		        rs = st.executeQuery();
		        while (rs.next()) {
		            NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("matKhau"), rs.getString("hoTen"),
		                    rs.getBoolean("gioiTinh"), new java.util.Date(rs.getDate("ngaySinh").getTime()), rs.getString("sDT"), rs.getString("email"),
		                    rs.getString("soCCCD"), rs.getString("diaChi"), rs.getString("anhDaiDien"));
		            list.add(nv);
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
//tìm nhân viên theo mã
		public NhanVien timNhanVienTheoMaNV(String maNV) {
		    NhanVien nv = null;
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    ResultSet rs = null;
		    try {
		        Connection con = ConnectDB.getConnection();
		        String query = "SELECT * FROM NhanVien WHERE maNV = ?";

		        st = con.prepareStatement(query);
		        st.setString(1, maNV);
		        rs = st.executeQuery();

		        if (rs.next()) {
		            nv = new NhanVien(rs.getString("maNV"), rs.getString("matKhau"), rs.getString("hoTen"),
		                    rs.getBoolean("gioiTinh"), new java.util.Date(rs.getDate("ngaySinh").getTime()), rs.getString("sDT"), rs.getString("email"),
		                    rs.getString("soCCCD"), rs.getString("diaChi"), rs.getString("anhDaiDien"));
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
		    return nv;
		}
}
