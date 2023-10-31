package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.CongNhan;

public class CongNhan_Dao {
	public boolean themCongNhan(CongNhan cn) {
		ConnectDB.getInstance();	
		PreparedStatement st=null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String query = "INSERT INTO CongNhan VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			st = conn.prepareStatement(query);
			st.setString(1, cn.getMaCN());
			st.setString(2, cn.getMatKhau());
			st.setString(3, cn.getHoTen());
			st.setDate(4, new java.sql.Date(cn.getNgaySinh().getTime()));
			st.setBoolean(5, cn.getGioiTinh());
			st.setString(6, cn.getSDT());
			st.setString(7, cn.getEmail());
			st.setString(8, cn.getDiaChi());
			st.setString(9, cn.getSoCCCD());
			st.setDate(10, new java.sql.Date(cn.getNgayVaoLam().getTime()));
			st.setString(11, cn.getAnhDaiDien());
			st.setString(12, cn.getGhiChu());
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
	public ArrayList<CongNhan> getAllCongNhan(){
		ArrayList<CongNhan> listCN = new ArrayList<>();
		ConnectDB.getInstance();
	    PreparedStatement st = null;
	    try {
	    	Connection conn = ConnectDB.getConnection();
	    	String querry= "SELECT * FROM CongNhan";
	    	st = conn.prepareStatement(querry);
	    	ResultSet rs = st.executeQuery();
	    	while(rs.next()) {
	    		CongNhan cn = new CongNhan(
	    				rs.getString(1),
	    				rs.getString(2),
	    				rs.getString(3),
	    				new java.util.Date(rs.getDate(4).getTime()),
	    				rs.getBoolean(5),
	    				rs.getString(6),
	    				rs.getString(7),
	    				rs.getString(8),
	    				rs.getString(9),
	    				new java.util.Date(rs.getDate(10).getTime()),
	    				rs.getString(11),
	    				rs.getString(12)
	    				);
	    		listCN.add(cn);
	    	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
	            st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
	    
	    return listCN;
	}
	
	
	
}


















