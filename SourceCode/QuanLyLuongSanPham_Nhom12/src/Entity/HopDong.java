package Entity;

import java.util.Date;

public class HopDong {
	private String maHD;
	private String maTenHD;
	private String tenKhachHang;
	private String nguoiDaiDien;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private double giaTriHD;
	private double tienCoc;
	private double donGia;
	private String thoaThuan;
	private boolean trangThai;
	
	
	public HopDong(String maHD, String maTenHD, String tenKhachHang, String nguoiDaiDien, Date ngayBatDau,
			Date ngayKetThuc, double giaTriHD, double tienCoc, double donGia, String thoaThuan, boolean trangThai) {
		super();
		this.maHD = maHD;
		this.maTenHD = maTenHD;
		this.tenKhachHang = tenKhachHang;
		this.nguoiDaiDien = nguoiDaiDien;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.giaTriHD = giaTriHD;
		this.tienCoc = tienCoc;
		this.donGia = donGia;
		this.thoaThuan = thoaThuan;
		this.trangThai = trangThai;
	}


	public String getMaHD() {
		return maHD;
	}


	public void setMaHD(String maHD) throws Exception{
		if(maHD==null || maHD.trim().length()<=0) {
			throw new Exception("Mã hợp đồng  không được rỗng!");
		}
		if(!maHD.matches("\\S+")){
		    throw new Exception("Mã hợp đồng  không được chứa khoảng trắng!");
		}
		else if(!maHD.matches("^SP\\d{5}$")){
		    throw new Exception("Mã hợp đồng  có dạng SP12345");
		}
		else {
			this.maHD = maHD;
		}
	}


	public String getMaTenHD() {
		return maTenHD;
	}


	public void setMaTenHD(String maTenHD) {
		this.maTenHD = maTenHD;
	}


	public String getTenKhachHang() {
		return tenKhachHang;
	}


	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}


	public String getNguoiDaiDien() {
		return nguoiDaiDien;
	}


	public void setNguoiDaiDien(String nguoiDaiDien) {
		this.nguoiDaiDien = nguoiDaiDien;
	}


	public Date getNgayBatDau() {
		return ngayBatDau;
	}


	public void setNgayBatDau(Date ngayBatDau) {
        Date ngayHienTai = new Date();
        if (ngayBatDau.compareTo(ngayHienTai) >= 0) {
            this.ngayBatDau = ngayBatDau;
        } else {
            System.out.println("Ngày bắt đầu không hợp lệ.");
        }
    }


	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}


	 public void setNgayKetThuc(Date ngayKetThuc) {
	        Date ngayHienTai = new Date();
	        if (ngayKetThuc.compareTo(ngayHienTai) >= 0) {
	            this.ngayKetThuc = ngayKetThuc;
	        } else {
	            System.out.println("Ngày kết thúc không hợp lệ.");
	        }
	    }


	public double getGiaTriHD() {
		return giaTriHD;
	}


	public void setGiaTriHD(double giaTriHD) {
		this.giaTriHD = giaTriHD;
	}


	public double getTienCoc() {
		return tienCoc;
	}


	public void setTienCoc(double tienCoc) throws Exception{
		if (tienCoc < 0) {
			throw new Exception("Số lượng phải lớn hơn 0!");
		}
		this.tienCoc = tienCoc;
	}


	public double getDonGia() {
		return donGia;
	}


	public void setDonGia(double donGia) throws Exception{
		if (donGia < 0) {
			throw new Exception("Số lượng phải lớn hơn 0!");
		}
		this.donGia = donGia;
	}


	public String getThoaThuan() {
		return thoaThuan;
	}


	public void setThoaThuan(String thoaThuan) {
		this.thoaThuan = thoaThuan;
	}


	public boolean isTrangThai() {
		return trangThai;
	}


	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}


	@Override
	public String toString() {
		return "HopDong [maHD=" + maHD + ", maTenHD=" + maTenHD + ", tenKhachHang=" + tenKhachHang + ", nguoiDaiDien="
				+ nguoiDaiDien + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", giaTriHD="
				+ giaTriHD + ", tienCoc=" + tienCoc + ", donGia=" + donGia + ", thoaThuan=" + thoaThuan + ", trangThai="
				+ trangThai + "]";
	}


	public HopDong() {
		super();
		// TODO Auto-generated constructor stub
	}


	public boolean matches(String string) {
		// TODO Auto-generated method stub
		return false;
	}

}
