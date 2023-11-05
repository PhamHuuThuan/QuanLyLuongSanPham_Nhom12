package Util;

import Dao.CongNhan_Dao;
import Dao.HopDong_Dao;
import Dao.NhanVien_Dao;
import Dao.PhanCongCongDoan_Dao;
import Dao.PhanCongNhanVien_Dao;
import Dao.SanPham_Dao;

public class SinhMaTuDong {
	//sinh ma hop dong  +1
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
	// sinh ma san pham +1
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
	//sinh ma nhan vien +1
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
	public String sinhMaCN() {
	    String maNew = "CN";
	    String maPre = new CongNhan_Dao().getMaCongNhanTuDong();
	    if (maPre == null || maPre.length() < 5) {
	        maNew += "00001";
	    } else {
	        try {
	            int ma = Integer.parseInt(maPre.substring(2)) + 1;
	            maNew += String.format("%05d", ma);
	        } catch (NumberFormatException e) {
	            // Xử lý ngoại lệ
	        }
	    }
	    return maNew;
	}
	//sinh ma phan cong
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
	// HÀM SINH MÃ PHÂN CÔNG CÔNG ĐOẠN
		public String sinhMaPCCD() {
		    String maNew = "PCD";
		    String maPre = new PhanCongCongDoan_Dao().getMaPCCDMax();
		    if (maPre == null || maPre.length() < 7) {
		        maNew += "0000001";
		    } else {
		        try {
		            int ma = Integer.parseInt(maPre.substring(3)) + 1;
		            maNew += String.format("%07d", ma);
		        } catch (NumberFormatException e) {
		        	// Xử lý ngoại lệ ở đây
		        }
		    }
		    return maNew;
		}
}
