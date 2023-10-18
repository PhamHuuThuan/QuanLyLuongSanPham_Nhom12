package Util;

public class XuatHopDongForm {
	private String maHD;
	private String tenHD;
	private String tenKH;
	private String tenNguoiDD;
	private String thoaThuan;
	private String ngayBD;
	private String ngayKT;
	private String giaTri;
	private String tienCoc;
	private String time;

	public XuatHopDongForm(String time, String maHD, String tenHD, String tenKH, String tenNguoiDD, String thoaThuan, String ngayBD,
			String ngayKT, String giaTri, String tienCoc) {
		super();
		this.time = time;
		this.maHD = maHD;
		this.tenHD = tenHD;
		this.tenKH = tenKH;
		this.tenNguoiDD = tenNguoiDD;
		this.thoaThuan = thoaThuan;
		this.ngayBD = ngayBD;
		this.ngayKT = ngayKT;
		this.giaTri = giaTri;
		this.tienCoc = tienCoc;
	}
	
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getTenHD() {
		return tenHD;
	}
	public void setTenHD(String tenHD) {
		this.tenHD = tenHD;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getTenNguoiDD() {
		return tenNguoiDD;
	}
	public void setTenNguoiDD(String tenNguoiDD) {
		this.tenNguoiDD = tenNguoiDD;
	}
	public String getThoaThuan() {
		return thoaThuan;
	}
	public void setThoaThuan(String thoaThuan) {
		this.thoaThuan = thoaThuan;
	}
	public String getNgayBD() {
		return ngayBD;
	}
	public void setNgayBD(String ngayBD) {
		this.ngayBD = ngayBD;
	}
	public String getNgayKT() {
		return ngayKT;
	}
	public void setNgayKT(String ngayKT) {
		this.ngayKT = ngayKT;
	}
	public String getGiaTri() {
		return giaTri;
	}
	public void setGiaTri(String giaTri) {
		this.giaTri = giaTri;
	}
	public String getTienCoc() {
		return tienCoc;
	}
	public void setTienCoc(String tienCoc) {
		this.tienCoc = tienCoc;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	
	
	@Override
	public String toString() {
		return "XuatHopDong [maHD=" + maHD + ", tenHD=" + tenHD + ", tenKH=" + tenKH + ", tenNguoiDD=" + tenNguoiDD
				+ ", thoaThuan=" + thoaThuan + ", ngayBD=" + ngayBD + ", ngayKT=" + ngayKT + ", giaTri=" + giaTri
				+ ", tienCoc=" + tienCoc + ", time=" + time + "]";
	}

}
