package Entity;

import java.util.Date;

public class BangChamCongCongNhan {
	private Date ngayChamCong;
	private Date gioVaoLam;
	private BangPhanCongCongDoan phanCong;
	private  int soLuongLam ;
	private String ghiChu;
	
	
	
	
	
	@Override
	public String toString() {
		return "BangChamCongCongNhan [ngayChamCong=" + ngayChamCong + ", gioVaoLam=" + gioVaoLam + ", phanCong="
				+ phanCong + ", soLuongLam=" + soLuongLam + ", ghiChu=" + ghiChu + "]";
	}

	public BangChamCongCongNhan(Date ngayChamCong, Date gioVaoLam, BangPhanCongCongDoan phanCong, int soLuongLam,
			String ghiChu) {
		super();
		this.ngayChamCong = ngayChamCong;
		this.gioVaoLam = gioVaoLam;
		this.phanCong = phanCong;
		this.soLuongLam = soLuongLam;
		this.ghiChu = ghiChu;
	}
	
	public BangChamCongCongNhan() {
		super();
	}
	
	public Date getNgayChamCong() {
		return ngayChamCong;
	}
	public void setNgayChamCong(Date ngayChamCong) {
		this.ngayChamCong = ngayChamCong;
	}
	public Date getGioVaoLam() {
		return gioVaoLam;
	}
	public void setGioVaoLam(Date gioVaoLam) {
		this.gioVaoLam = gioVaoLam;
	}
	public BangPhanCongCongDoan getPhanCong() {
		return phanCong;
	}
	public void setPhanCong(BangPhanCongCongDoan phanCong) {
		this.phanCong = phanCong;
	}
	public int getSoLuongLam() {
		return soLuongLam;
	}
	public void setSoLuongLam(int soLuongLam) {
		this.soLuongLam = soLuongLam;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	
}
