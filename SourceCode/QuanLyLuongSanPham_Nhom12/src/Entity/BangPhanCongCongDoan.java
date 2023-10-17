package Entity;

import java.util.Date;

public class BangPhanCongCongDoan {
	private String maPhanCong;
	private CongNhan congNhan;
	private  CongDoan congDoanh;
	private Date ngayPhanCong;
	private int soLuongCanLam;
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
	public CongDoan getCongDoanh() {
		return congDoanh;
	}
	public void setCongDoanh(CongDoan congDoanh) {
		this.congDoanh = congDoanh;
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
	public BangPhanCongCongDoan(String maPhanCong, CongNhan congNhan, CongDoan congDoanh, Date ngayPhanCong,
			int soLuongCanLam) {
		super();
		this.maPhanCong = maPhanCong;
		this.congNhan = congNhan;
		this.congDoanh = congDoanh;
		this.ngayPhanCong = ngayPhanCong;
		this.soLuongCanLam = soLuongCanLam;
	}
	public BangPhanCongCongDoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BangPhanCongCongDoan [maPhanCong=" + maPhanCong + ", congDoanh=" + congDoanh + ", ngayPhanCong="
				+ ngayPhanCong + ", soLuongCanLam=" + soLuongCanLam + "]";
	}
	
	
}
