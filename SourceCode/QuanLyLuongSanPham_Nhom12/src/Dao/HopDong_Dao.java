package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ConnectDB.ConnectDB;
import Entity.HopDong;
import Entity.NhanVien;

public class HopDong_Dao {
	//sửa hợp đồng trong csdl
	public boolean suaHopDong(HopDong hd) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n=0;
		try {
			Connection con = ConnectDB.getConnection();
			st = con.prepareStatement("UPDATE HopDong SET tenHD=?, tenKH=?, maNguoiDaiDien=?, "
					+ "ngayBatDau=?, ngayKetThuc=?, giaTriHopDong=?, tienCoc=?,"
					+ "thoaThuan=?, trangThai=?, ghiChu=? where MaHD=?");
			st.setString(1, hd.getTenHD());
			st.setString(2, hd.getTenKhachHang());
			st.setString(3, hd.getNguoiDaiDien().getMaNV());
			st.setDate(4, new java.sql.Date(hd.getNgayBatDau().getTime()));
			st.setDate(5, new java.sql.Date(hd.getNgayKetThuc().getTime()));
			st.setDouble(6, hd.getGiaTriHD());
			st.setDouble(7, hd.getTienCoc());
			st.setString(8, hd.getThoaThuan());
			st.setBoolean(9, hd.isTrangThai());
			st.setString(10, hd.getGhiChu());
			st.setString(11, hd.getMaHD());
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
	//thêm hợp đồng trong csdl
	public boolean themHopDong(HopDong hd) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n=0;
		try {
			Connection con = ConnectDB.getConnection();
			st = con.prepareStatement("insert into HopDong values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			st.setString(1, hd.getMaHD());
			st.setString(2, hd.getTenHD());
			st.setString(3, hd.getTenKhachHang());
			st.setString(4, hd.getNguoiDaiDien().getMaNV());
			st.setDate(5, new java.sql.Date(hd.getNgayBatDau().getTime()));
			st.setDate(6, new java.sql.Date(hd.getNgayKetThuc().getTime()));
			st.setDouble(7, hd.getGiaTriHD());
			st.setDouble(8, hd.getTienCoc());
			st.setString(9, hd.getThoaThuan());
			st.setBoolean(10, hd.isTrangThai());
			st.setString(11, hd.getGhiChu());
			n = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n>0;
	}
	//get all hợp đồng trong csdl
		public ArrayList<HopDong> getAllHopDong() {
		    ArrayList<HopDong> list = new ArrayList<>();
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    try {
		        Connection con = ConnectDB.getConnection();
		        st = con.prepareStatement("select * from HopDong");
		        ResultSet rs = st.executeQuery();
		        while (rs.next()) {
		            HopDong hd = new HopDong(rs.getString(1), rs.getString(2), rs.getString(3), new NhanVien_Dao().timNhanVienTheoMaNV(rs.getString(4)),
		            		new java.util.Date(rs.getDate(5).getTime()), new java.util.Date(rs.getDate(6).getTime()),
		            		rs.getDouble(7), rs.getDouble(8), rs.getString(9), rs.getBoolean(10), rs.getString(11));
		            list.add(hd);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            st.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return list;
		}
	//get all hợp đồng theo người đại diện trong csdl
	public ArrayList<HopDong> getAllHopDongTheoNguoiDaiDien(String maNguoiDaiDien) {
	    ArrayList<HopDong> list = new ArrayList<>();
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("select * from HopDong where maNguoiDaiDien = ?");
	        st.setString(1, maNguoiDaiDien);
	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	            HopDong hd = new HopDong(rs.getString(1), rs.getString(2), rs.getString(3), new NhanVien_Dao().timNhanVienTheoMaNV(rs.getString(4)),
	            		new java.util.Date(rs.getDate(5).getTime()), new java.util.Date(rs.getDate(6).getTime()),
	            		rs.getDouble(7), rs.getDouble(8), rs.getString(9), rs.getBoolean(10), rs.getString(11));
	            list.add(hd);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return list;
	}
	//xóa hợp đồng trong csdl
	public boolean xoaHopDong(String maHD) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    int n = 0;
	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("delete from HopDong where MaHD = ?");
	        st.setString(1, maHD);
	        n = st.executeUpdate();
	    } catch (SQLException e) {
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
	//get mã max trong csdl
	public String getMaHDMax() {
	    String maHDMax = null;
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT MAX(maHD) AS MaHDMax FROM HopDong");
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            maHDMax = rs.getString("MaHDMax");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (st != null) {
	                st.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return maHDMax;
	}
	public HopDong getHopDongTheoMa(String maHD) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    HopDong hd = null;
	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT * FROM HopDong WHERE MaHD = ?");
	        st.setString(1, maHD);
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            hd = new HopDong(rs.getString(1), rs.getString(2), rs.getString(3), new NhanVien(rs.getString(4)),
	                    new java.util.Date(rs.getDate(5).getTime()), new java.util.Date(rs.getDate(6).getTime()),
	                    rs.getDouble(7), rs.getDouble(8), rs.getString(9), rs.getBoolean(10), rs.getString(11));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (st != null) {
	                st.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return hd;
	}
	// Lấy danh sách tổng giá trị hợp đồng từng tháng của một năm cụ thể
	public Map<Integer, Double> layTongGiaTriHopDongTheoThang(int nam) {
	    Map<Integer, Double> tongGiaTriHopDongTheoThang = new HashMap<>();
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT MONTH(ngayBatDau) as Thang, SUM(giaTriHopDong) as TongGiaTri "
	                    + "FROM HopDong "
	                    + "WHERE YEAR(ngayBatDau) = ? "
	                    + "GROUP BY MONTH(ngayBatDau) "
	                    + "ORDER BY Thang";

	        st = con.prepareStatement(sql);
	        st.setInt(1, nam);
	        rs = st.executeQuery();

	        while (rs.next()) {
	            int thang = rs.getInt("Thang");
	            double tongGiaTri = rs.getDouble("TongGiaTri");
	            // Lưu tháng và tổng giá trị hợp đồng vào Map
	            tongGiaTriHopDongTheoThang.put(thang, tongGiaTri);
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
	    return tongGiaTriHopDongTheoThang;
	}

}