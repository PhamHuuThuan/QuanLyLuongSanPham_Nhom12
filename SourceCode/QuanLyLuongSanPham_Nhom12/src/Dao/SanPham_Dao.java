package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.HopDong;
import Entity.SanPham;

public class SanPham_Dao {
	//sửa sản phẩm
	public boolean suaSanPham(SanPham sp) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n=0;
		try {
			Connection con = ConnectDB.getConnection();
			st = con.prepareStatement("UPDATE SanPham SET maHopDong=?, tenSP=?, donViTinh=?, "
					+ "soLuong=?, yeuCau=?, donGia=? where maSP=?");
			st.setString(1, sp.getMaHopDong().getMaHD());
			st.setString(2, sp.getTenSP());
			st.setString(3, sp.getDonViTinh());
			st.setInt(4, sp.getSoLuong());
			st.setString(5, sp.getYeuCau());
			st.setDouble(6, sp.getDonGia());
			st.setString(7, sp.getMaSP());
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
	//thêm sản phẩm
	public boolean themSanPham(SanPham sp) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    int n = 0;
	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("INSERT INTO SanPham VALUES(?, ?, ?, ?, ?, ?, ?)");
	        st.setString(1, sp.getMaSP());
	        st.setString(2, sp.getMaHopDong().getMaHD());
	        st.setString(3, sp.getTenSP());
	        st.setString(4, sp.getDonViTinh());
	        st.setInt(5, sp.getSoLuong());
	        st.setString(6, sp.getYeuCau());
	        st.setDouble(7, sp.getDonGia());
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
	//xóa sản phẩm
	public boolean xoaSanPham(String maSP) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    int n = 0;
	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("DELETE FROM SanPham WHERE maSP=?");
	        st.setString(1, maSP);
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
	//get ds sản phẩm của hợp đồng
	public ArrayList<SanPham> getSanPhamTheoHopDong(String maHD) {
	    ArrayList<SanPham> list = new ArrayList<>();
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT * FROM SanPham WHERE maHopDong = ?");
	        st.setString(1, maHD);
	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	            SanPham sp = new SanPham(rs.getString(1), new HopDong(rs.getString(2)), rs.getString(3), rs.getString(4),
	                    rs.getInt(5), rs.getString(6), rs.getDouble(7));
	            list.add(sp);
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
	//get mã sản phẩm lớn nhất
	public String getMaSPLonNhat() {
	    String maSPLonNhat = null;
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT MAX(MaSP) AS MaSPLonNhat FROM SanPham");
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            maSPLonNhat = rs.getString("MaSPLonNhat");
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
	    return maSPLonNhat;
	}
	//tính tổng tiền của các sản phẩm theo maHD
	public double tinhTongTien(String maHD) {
	    double tongTien = 0;
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT SUM(soLuong * donGia) AS TongTien FROM SanPham WHERE maHopDong = ?");
	        st.setString(1, maHD);
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            tongTien = rs.getDouble("TongTien");
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
	    return tongTien;
	}
	//get sản phẩm theo mã
	public SanPham getSanPhamTheoMa(String maSP) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    SanPham sp = null;
	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT * FROM SanPham WHERE MaSP = ?");
	        st.setString(1, maSP);
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            sp = new SanPham(rs.getString(1), new HopDong(rs.getString(2)), rs.getString(3), rs.getString(4),
	                    rs.getInt(5), rs.getString(6), rs.getDouble(7));
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
	    return sp;
	}

}
