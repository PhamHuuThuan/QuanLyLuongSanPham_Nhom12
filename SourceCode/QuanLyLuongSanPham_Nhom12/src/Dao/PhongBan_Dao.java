package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ConnectDB.ConnectDB;
import Entity.PhongBan;

public class PhongBan_Dao {

    public boolean suaPhongBan(PhongBan pb) {
        ConnectDB.getInstance();
        PreparedStatement st = null;
        int n = 0;

        try (Connection con = ConnectDB.getConnection()) {
            st = con.prepareStatement("UPDATE PhongBan SET tenPB=?, soLuongNV=?, moTa=? WHERE maPB=?");
            st.setString(1, pb.getTenPhongBan());
            st.setInt(2, pb.getsoLuongNV());
            st.setString(3, pb.getMoTa());
            st.setString(4, pb.getMaPB());

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

    public boolean themPhongBan(PhongBan pb) {
        ConnectDB.getInstance();
        PreparedStatement st = null;
        int n = 0;

        try (Connection con = ConnectDB.getConnection()) {
            st = con.prepareStatement("INSERT INTO PhongBan VALUES (?, ?, ?, ?)");
            st.setString(1, pb.getMaPB());
            st.setString(2, pb.getTenPhongBan());
            st.setInt(3, pb.getsoLuongNV());
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

    public ArrayList<PhongBan> getAllPhongBan() {
        ConnectDB.getInstance();
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<PhongBan> listPB = new ArrayList<>();

        try (Connection con = ConnectDB.getConnection()) {
            st = con.prepareStatement("SELECT * FROM PhongBan");
            rs = st.executeQuery();

            while (rs.next()) {
                PhongBan pb = new PhongBan(rs.getString("maPB"), rs.getString("tenPb"),
                        rs.getInt("soLuongNV"), rs.getString("moTa"));

                listPB.add(pb);
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

        return listPB;
    }

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


}
