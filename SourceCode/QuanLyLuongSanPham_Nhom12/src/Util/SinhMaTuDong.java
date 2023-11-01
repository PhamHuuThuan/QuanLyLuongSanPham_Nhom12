package Util;

import Dao.HopDong_Dao;
import Dao.NhanVien_Dao;
import Dao.PhanCongNhanVien_Dao;
import Dao.SanPham_Dao;

public class SinhMaTuDong {
	public String sinhMaHD() {
	    String maNew = "HD";
	    String maPre = new HopDong_Dao().getMaHDMax();
	    if (maPre == null || maPre.length() < 5) {
	        maNew += "00001";
	    } else {
	        try {
	            int ma = Integer.parseInt(maPre.substring(2)) + 1;
	            maNew += String.format("%05d", ma);
	        } catch (NumberFormatException e) {
	            // Xử lý ngoại lệ ở đây
	        }
	    }
	    return maNew;
	}
	public String sinhMaSP() {
	    String maNew = "SP";
	    String maPre = new SanPham_Dao().getMaSPLonNhat();
	    if (maPre == null || maPre.length() < 7) {
	        maNew += "0000001";
	    } else {
	        try {
	            int ma = Integer.parseInt(maPre.substring(2)) + 1;
	            maNew += String.format("%07d", ma);
	        } catch (NumberFormatException e) {
	            // Xử lý ngoại lệ ở đây
	        }
	    }
	    return maNew;
	}
	public String sinhMaNV() {
	    String maNew = "NV";
	    String maPre = new NhanVien_Dao().getMaNhanVienLonNhat();
	    if (maPre == null || maPre.length() < 5) {
	        maNew += "00001";
	    } else {
	        try {
	            int ma = Integer.parseInt(maPre.substring(2)) + 1;
	            maNew += String.format("%05d", ma);
	        } catch (NumberFormatException e) {
	            // Xử lý ngoại lệ ở đây
	        }
	    }
	    return maNew;
	}
	public String sinhMaPCNV() {
	    String maNew = "PC";
	    String maPre = new PhanCongNhanVien_Dao().getMaPCLonNhat();
	    if (maPre == null || maPre.length() < 5) {
	        maNew += "00001";
	    } else {
	        try {
	            int ma = Integer.parseInt(maPre.substring(2)) + 1;
	            maNew += String.format("%05d", ma);
	        } catch (NumberFormatException e) {
	            // Xử lý ngoại lệ ở đây
	        }
	    }
	    return maNew;
	}
}
