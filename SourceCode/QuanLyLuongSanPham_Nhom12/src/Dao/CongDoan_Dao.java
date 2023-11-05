package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.CongDoan;
import Entity.HopDong;
import Entity.SanPham;

public class CongDoan_Dao {
	//sửa hợp đồng trong csdl
		public boolean suaCongDoan(CongDoan cd) {
			ConnectDB.getInstance();
			PreparedStatement st = null;
			int n=0;
			try {
				Connection con = ConnectDB.getConnection();
				st = con.prepareStatement("UPDATE Cong Doan SET maSanPham=?, maCD=?, tenCD=?, "
						+ "thuTu=?, donGia=?, tinhTrang=?, soLuong=?,"
						+ "ngayHoanThanh=?");
				st.setString(1, cd.getMaSanPham());
				st.setString(2, cd.getMaCD());
				st.setString(3, cd.getTenCD());
				st.setDate(5, new java.sql.Date(cd.getNgayHoanThanh().getTime()));
				st.setInt(6, cd.getThuTu());
				st.setDouble(7, cd.getDonGia());
				st.setBoolean(8, cd.isTinhTrang());
				st.setInt(9, cd.getSoLuong());

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
				st = con.prepareStatement("insert into CongDoan values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
				st.setString(1, cd.getMaSanPham());
				st.setString(2, cd.getMaCD());
				st.setString(3, cd.getTenCD());
				st.setDate(5, new java.sql.Date(cd.getNgayHoanThanh().getTime()));
				st.setInt(6, cd.getThuTu());
				st.setDouble(7, cd.getDonGia());
				st.setBoolean(8, cd.isTinhTrang());
				st.setInt(9, cd.getSoLuong());
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
		            CongDoan cd = new CongDoan(getMaCDMax(), getMaCDMax(), 0, 0, 0, false, null, null);
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
		        st = con.prepareStatement("delete from CongDoan where maCongDoan = ?");
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
				public ArrayList<CongDoan> getCongDoanTheoSanPham(String maCD) {
				    ArrayList<CongDoan> list = new ArrayList<>();
				    ConnectDB.getInstance();
				    PreparedStatement st = null;
				    try {
				        Connection con = ConnectDB.getConnection();
				        st = con.prepareStatement("SELECT * FROM CongDoan WHERE maSP = ?");
				        st.setString(1, maCD);
				        ResultSet rs = st.executeQuery();
				        while (rs.next()) {
				            CongDoan cd = new CongDoan(rs.getString(1), new SanPham(rs.getString(2)), rs.getString(3), rs.getString(4),
				                    rs.getInt(5), rs.getString(6), rs.getDouble(7));
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
		            cd = new CongDoan(maCD, maCD, 0, 0, 0, false, null, null);
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
