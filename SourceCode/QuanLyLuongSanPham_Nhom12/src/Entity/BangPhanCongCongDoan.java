package Entity;

import java.util.Date;

public class BangPhanCongCongDoan {
	private String maPhanCong;
	private CongNhan congNhan;
	private CongDoan congDoan;
	private Date ngayPhanCong;
	private int soLuongCanLam;
	// bo sung trong class
	private String ghiChu;
	
	private SanPham sanPham;
	
	
	public BangPhanCongCongDoan(String maPhanCong, CongNhan congNhan, CongDoan congDoan,Date ngayPhanCong,
			int soLuongCanLam, String ghiChu) {
		super();
		this.maPhanCong = maPhanCong;
		this.congNhan = congNhan;
		this.congDoan = congDoan;
		this.ngayPhanCong = ngayPhanCong;
		this.soLuongCanLam = soLuongCanLam;
		this.ghiChu = ghiChu;
	}
	
	public BangPhanCongCongDoan(String maPhanCong, CongNhan congNhan, CongDoan congDoan,SanPham sanPham,Date ngayPhanCong,
			int soLuongCanLam, String ghiChu) {
		super();
		this.maPhanCong = maPhanCong;
		this.congNhan = congNhan;
		this.congDoan = congDoan;
		this.sanPham = sanPham;
		this.ngayPhanCong = ngayPhanCong;
		this.soLuongCanLam = soLuongCanLam;
		this.ghiChu = ghiChu;
	}
	


	public BangPhanCongCongDoan(String maPhanCong ,SanPham sanPham, CongDoan congDoan) {
		super();
		this.maPhanCong = maPhanCong;
		this.sanPham = sanPham;
		this.congDoan = congDoan;
	}
	
	

	public BangPhanCongCongDoan(String maPhanCong, CongDoan congDoan) {
		super();
		this.maPhanCong = maPhanCong;
		this.congDoan = congDoan;
	}

	public BangPhanCongCongDoan(String maPhanCong) {
		super();
		this.maPhanCong = maPhanCong;
	}

	

	public BangPhanCongCongDoan(String maPhanCong, CongNhan congNhan) {
		super();
		this.maPhanCong = maPhanCong;
		this.congNhan = congNhan;
	}

	public BangPhanCongCongDoan(String maPhanCong, int soLuongCanLam) {
		super();
		this.maPhanCong = maPhanCong;
		this.soLuongCanLam = soLuongCanLam;
	}

	public BangPhanCongCongDoan(int soLuongCanLam) {
		super();
		this.soLuongCanLam = soLuongCanLam;
	}
	

	

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public BangPhanCongCongDoan() {
		super();
		
	}

	public String getMaPhanCong() {
		return maPhanCong;
	}

	public void setMaPhanCong(String maPhanCong) {
		this.maPhanCong = maPhanCong;
	}

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}

	public CongDoan getCongDoan() {
		return congDoan;
	}

	public void setCongDoan(CongDoan congDoan) {
		this.congDoan = congDoan;
	}

	public Date getNgayPhanCong() {
		return ngayPhanCong;
	}

	public void setNgayPhanCong(Date ngayPhanCong) {
		this.ngayPhanCong = ngayPhanCong;
	}

	public int getSoLuongCanLam() {
		return soLuongCanLam;
	}

	public void setSoLuongCanLam(int soLuongCanLam) {
		this.soLuongCanLam = soLuongCanLam;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	

}
