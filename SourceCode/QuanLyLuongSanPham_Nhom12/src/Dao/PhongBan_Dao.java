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
	//Tìm phòng ban theo mã
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
	//Sửa phòng ban
	public boolean suaPhongBan(PhongBan pb) {
        ConnectDB.getInstance();
        PreparedStatement st = null;
        int n = 0;

        try (Connection con = ConnectDB.getConnection()) {
            st = con.prepareStatement("UPDATE PhongBan SET tenPB=?, soLuongNV=?, moTa=? WHERE maPB=?");
            st.setString(1, pb.getTenPhongBan());
            st.setInt(2, pb.getSoNhanVien());
            st.setString(3, pb.getMoTa());
            st.setString(4, pb.getMaPhongBan());

            n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return n > 0;
    }
	//Thêm một phòng ban vào csdl
    public boolean themPhongBan(PhongBan pb) {
        ConnectDB.getInstance();
        PreparedStatement st = null;
        int n = 0;

        try (Connection con = ConnectDB.getConnection()) {
            st = con.prepareStatement("INSERT INTO PhongBan VALUES (?, ?, ?, ?)");
            st.setString(1, pb.getMaPhongBan());
            st.setString(2, pb.getTenPhongBan());
            st.setInt(3, pb.getSoNhanVien());
            st.setString(4, pb.getMoTa());
            n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return n > 0;
    }
    //Xóa phòng ban theo maPB
    public static boolean xoaPhongBan(String maPB) {
        ConnectDB.getInstance();
        PreparedStatement st = null;
        int n = 0;

        try (Connection con = ConnectDB.getConnection()) {
            st = con.prepareStatement("DELETE FROM PhongBan WHERE maPB = ?");
            st.setString(1, maPB);
            n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return n > 0;
    }
    //Lấy mã phòng ban lớn nhất từ csdl
    public String getMaPhongBanLonNhat() {
        ConnectDB.getInstance();
        PreparedStatement st = null;
        ResultSet rs = null;
        String maPhongBanLonNhat = null;

        try (Connection con = ConnectDB.getConnection()) {
            st = con.prepareStatement("SELECT MAX(maPhongBan) AS MaPhongBanMax FROM PhongBan");
            rs = st.executeQuery();

            if (rs.next()) {
                maPhongBanLonNhat = rs.getString("MaPhongBanMax");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return maPhongBanLonNhat;
    }
  //get mã sản phẩm lớn nhất
  	public String getMaPBLonNhat() {
  	    String maPBLonNhat = null;
  	    ConnectDB.getInstance();
  	    PreparedStatement st = null;
  	    try {
  	        Connection con = ConnectDB.getConnection();
  	        st = con.prepareStatement("SELECT MAX(MaSP) AS MaPBLonNhat FROM PhongBan");
  	        ResultSet rs = st.executeQuery();
  	        if (rs.next()) {
  	            maPBLonNhat = rs.getString("MaPBLonNhat");
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
  	    return maPBLonNhat;
  	}

}

