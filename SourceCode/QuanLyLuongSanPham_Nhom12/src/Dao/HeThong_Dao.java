package Dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConnectDB.ConnectDB;

public class HeThong_Dao {
	public boolean BackUp(String fileName){
		int n = 0;
		ConnectDB.getInstance();	
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "USE master; BACKUP DATABASE [QuanLyLuongSanPham-TPT] TO DISK = 'D:\\backup\\" + fileName + "'";
			System.out.println(sql);
	        PreparedStatement stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return false;
		}
	    // Kiểm tra xem file sao lưu có tồn tại hay không
	    File file = new File("D:\\backup\\" + fileName);
	    return file.exists();
	}
	public boolean Restore(String fileName){
	    ConnectDB.getInstance();    
	    try {
	        Connection con = ConnectDB.getConnection();
	        String sql = "USE master; ALTER DATABASE [QuanLyLuongSanPham-TPT] SET SINGLE_USER WITH ROLLBACK IMMEDIATE; RESTORE DATABASE [QuanLyLuongSanPham-TPT] FROM DISK = '" + fileName + "'; ALTER DATABASE [QuanLyLuongSanPham-TPT] SET MULTI_USER";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }

	    // Kiểm tra xem file sao lưu có tồn tại hay không
	    File file = new File(fileName);
	    return file.exists();
	}
}
