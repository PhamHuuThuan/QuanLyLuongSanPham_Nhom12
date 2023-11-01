package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.PhongBan;

public class PhongBan_Dao {
	//get all phòng ban từ csdl
	public ArrayList<PhongBan> getAllPhongBan() {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    ArrayList<PhongBan> listPB = new ArrayList<>();
	    
	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT * FROM PhongBan");
	        rs = st.executeQuery();

	        while(rs.next()) {
	            PhongBan pb = new PhongBan(rs.getString("maPB"), rs.getString("tenPB"), rs.getInt("soLuongNV"), rs.getString("moTa"));
	            
	            listPB.add(pb);
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
	    
	    return listPB;
	}
	public PhongBan timPhongBanTheoMa(String maPB) {
	    ConnectDB.getInstance();
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    PhongBan pb = null;

	    try {
	        Connection con = ConnectDB.getConnection();
	        st = con.prepareStatement("SELECT * FROM PhongBan WHERE maPB = ?");
	        st.setString(1, maPB);
	        rs = st.executeQuery();

	        if (rs.next()) {
	            pb = new PhongBan(rs.getString("maPB"), rs.getString("tenPB"), rs.getInt("soLuongNV"), rs.getString("moTa"));
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

	    return pb;
	}

}
