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
	
	

	@Override
	public String toString() {
		return "BangPhanCongCongDoan [maPhanCong=" + maPhanCong + ", congNhan=" + congNhan + ", congDoan=" + congDoan
				+ ", ngayPhanCong=" + ngayPhanCong + ", soLuongCanLam=" + soLuongCanLam + ", ghiChu=" + ghiChu + "]";
	}

	public BangPhanCongCongDoan(String maPhanCong, CongNhan congNhan, CongDoan congDoan, Date ngayPhanCong,
			int soLuongCanLam, String ghiChu) {
		super();
		try {
			setMaPhanCong(maPhanCong);
			setCongNhan(congNhan);
			setCongDoan(congDoan);
			setNgayPhanCong(ngayPhanCong);
			setSoLuongCanLam(soLuongCanLam);
			setGhiChu(ghiChu);
		} catch (Exception e) {
			// TODO: handle exception
		}

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
