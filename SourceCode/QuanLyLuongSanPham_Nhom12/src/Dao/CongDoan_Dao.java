package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import ConnectDB.ConnectDB;
import Entity.CongDoan;
import Entity.HopDong;
import Entity.SanPham;

public class CongDoan_Dao {
	//sửa hợp đồng trong csdl
		public boolean suaCongDoan(CongDoan cd) {
			ConnectDB.getInstance();
			System.out.println("check cd: " + cd);
			PreparedStatement st = null;
			int n=0;
			try {
				Connection con = ConnectDB.getConnection();
				st = con.prepareStatement("UPDATE CongDoan SET tenCD=?, thuTu=?, "
						+ "donGia=?, tinhTrang=?, ngayHoanThanh=?, maSanPham=?,"
						+ "soLuong=? WHERE maCD=?");
				st.setString(1, cd.getTenCD());
				st.setInt(2, cd.getThuTu());
				st.setDouble(3, cd.getDonGia());
				st.setBoolean(4, cd.isTinhTrang());
				st.setDate(5, new java.sql.Date(cd.getNgayHoanThanh().getTime()));
				st.setString(6, cd.getSanPham().getMaSP());
				st.setInt(7, cd.getSoLuong());
				st.setString(8, cd.getMaCD());

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
		public boolean themCongDoan(CongDoan cd) {
			ConnectDB.getInstance();
			PreparedStatement st = null;
			int n=0;
			try {
				Connection con = ConnectDB.getConnection();
				st = con.prepareStatement("insert into CongDoan values(?, ?, ?, ?, ?, ?, ?, ?)");
				st.setString(1, cd.getMaCD());
				st.setString(2, cd.getTenCD());
				st.setInt(3, cd.getThuTu());
				st.setDouble(4, cd.getDonGia());
				st.setBoolean(5, cd.isTinhTrang());
				st.setDate(6, new java.sql.Date(cd.getNgayHoanThanh().getTime()));
				st.setString(7, cd.getSanPham().getMaSP());
				st.setInt(8, cd.getSoLuong());
				
				
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
		//get all công đoạn  trong csdl
		public ArrayList<CongDoan> getAllCongDoan() {
		    ArrayList<CongDoan> list = new ArrayList<>();
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    try {
		        Connection con = ConnectDB.getConnection();
		        st = con.prepareStatement("select * from CongDoan");
		        ResultSet rs = st.executeQuery();
		        while (rs.next()) {
		        	String maCd = rs.getString("maCD");
		        	String tenCd = rs.getString("tenCD");
		        	int thuTu = rs.getInt("thuTu");
		        	Double donGia = rs.getDouble("donGia");
		        	Boolean tinhTrang = rs.getBoolean("tinhTrang");
		        	Date ngayHT = rs.getDate("ngayHoanThanh");
		        	String maSP = rs.getString("maSP");
		        	int soLuong = rs.getInt("soLuong");
		            CongDoan cd = new CongDoan(maCd, tenCd, thuTu, soLuong, donGia, tinhTrang, ngayHT, new SanPham(maSP));
		            list.add(cd);
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
		//get cong doan theo ma san pham
		//get all công đoạn  trong csdl
		public ArrayList<CongDoan> getAllCongDoanTheoMaSP() {
		    ArrayList<CongDoan> list = new ArrayList<>();
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    try {
		        Connection con = ConnectDB.getConnection();
		        st = con.prepareStatement("select * from CongDoan");
		        ResultSet rs = st.executeQuery();
		        while (rs.next()) {
		        	String maCd = rs.getString("maCD");
		        	String tenCd = rs.getString("tenCD");
		        	int thuTu = rs.getInt("thuTu");
		        	Double donGia = rs.getDouble("donGia");
		        	Boolean tinhTrang = rs.getBoolean("tinhTrang");
		        	Date ngayHT = rs.getDate("ngayHoanThanh");
		        	String maSP = rs.getString("maSP");
		        	int soLuong = rs.getInt("soLuong");
		            CongDoan cd = new CongDoan(maCd, tenCd, thuTu, soLuong, donGia, tinhTrang, ngayHT, new SanPham(maSP));
		            list.add(cd);
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
		public boolean xoaCongDoan(String maCD) {
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    int n = 0;
		    try {
		        Connection con = ConnectDB.getConnection();
		        st = con.prepareStatement("delete from CongDoan where maCD = ?");
		        st.setString(1, maCD);
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
		public String getMaCDMax() {
		    String maCDMax = null;
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    try {
		        Connection con = ConnectDB.getConnection();
		        st = con.prepareStatement("SELECT MAX(maCD) AS MaCDMax FROM CongDoan");
		        ResultSet rs = st.executeQuery();
		        if (rs.next()) {
		            maCDMax = rs.getString("MaCDMax");
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
		    return maCDMax;
		}
		//get mã công đoạnlớn nhất
		public String getMaCDLonNhat() {
		    String maCDLonNhat = null;
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    try {
		        Connection con = ConnectDB.getConnection();
		        st = con.prepareStatement("SELECT MAX(MaCD) AS MaCDLonNhat FROM CongDoan");
		        ResultSet rs = st.executeQuery();
		        if (rs.next()) {
		            maCDLonNhat = rs.getString("MaCDLonNhat");
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
		    return maCDLonNhat;
		}
		//get ds sản phẩm của hợp đồng
		public ArrayList<CongDoan> getCongDoanTheoSanPham(String maSP) {
		    ArrayList<CongDoan> list = new ArrayList<>();
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    try {
		        Connection con = ConnectDB.getConnection();
		        st = con.prepareStatement("SELECT * FROM CongDoan WHERE maSP = ?");
		        st.setString(1, maSP);
		        ResultSet rs = st.executeQuery();
		        while (rs.next()) {
		        	String maCd = rs.getString("maCD");
		        	String tenCd = rs.getString("tenCD");
		        	int thuTu = rs.getInt("thuTu");
		        	Double donGia = rs.getDouble("donGia");
		        	Boolean tinhTrang = rs.getBoolean("tinhTrang");
		        	Date ngayHT = rs.getDate("ngayHoanThanh");
		        	String maSp = rs.getString("maSP");
		        	int soLuong = rs.getInt("soLuong");
		            CongDoan cd = new CongDoan(maCd, tenCd, thuTu, soLuong, soLuong, tinhTrang, ngayHT, new SanPham(maSP));
		            list.add(cd);
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
		public CongDoan getCongDoanTheoMa(String maCD) {
		    ConnectDB.getInstance();
		    PreparedStatement st = null;
		    CongDoan cd = null;
		    try {
		        Connection con = ConnectDB.getConnection();
		        st = con.prepareStatement("SELECT * FROM HopDong WHERE MaHD = ?");
		        st.setString(1, maCD);
		        ResultSet rs = st.executeQuery();
		        if (rs.next()) {
		        	String maCd = rs.getString("maCD");
		        	String tenCd = rs.getString("tenCD");
		        	int thuTu = rs.getInt("thuTu");
		        	Double donGia = rs.getDouble("donGia");
		        	Boolean tinhTrang = rs.getBoolean("tinhTrang");
		        	Date ngayHT = rs.getDate("ngayHoanThanh");
		        	String maSP = rs.getString("maSanPham");
		        	int soLuong = rs.getInt("soLuong");
		            cd = new CongDoan(maCd, tenCd, thuTu, soLuong, soLuong, tinhTrang, ngayHT, new SanPham(maSP));
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
		    return cd;
		}

}
