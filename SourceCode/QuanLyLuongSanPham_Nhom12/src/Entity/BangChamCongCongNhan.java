package Entity;

import java.util.Calendar;
import java.util.Date;

import javax.swing.Timer;

public class BangChamCongCongNhan {
	private Date ngayChamCong;
	// sua class thanh Timer
	private Timer gioVaoLam;
	private BangPhanCongCongDoan phanCong;
	private  int soLuongLam ;
	private String ghiChu;
	
	public BangChamCongCongNhan(Date ngayChamCong, Timer gioVaoLam, BangPhanCongCongDoan phanCong, int soLuongLam,
			String ghiChu){
		super();
		try {
			setNgayChamCong(ngayChamCong);
			setGioVaoLam(gioVaoLam);
			setPhanCong(phanCong);
			setSoLuongLam(soLuongLam);
			setGhiChu(ghiChu);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public BangChamCongCongNhan() {
		super();
	}
	
	public Date getNgayChamCong() {
		return ngayChamCong;
	}
	public void setNgayChamCong(Date ngayChamCong) throws Exception {
		Calendar cal = Calendar.getInstance();
        java.util.Date now = cal.getTime();
		if(ngayChamCong.after(now)) {
			throw new Exception("Ngày chấm công từ ngày hiện tại về trước");
		}
		else {
			this.ngayChamCong = ngayChamCong;
		}
	}
	public Timer getGioVaoLam() {
		return gioVaoLam;
	}
	public void setGioVaoLam(Timer gioVaoLam) {
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
	
	
	@Override
	public String toString() {
		return "BangChamCongCongNhan [ngayChamCong=" + ngayChamCong + ", gioVaoLam=" + gioVaoLam + ", phanCong="
				+ phanCong + ", soLuongLam=" + soLuongLam + ", ghiChu=" + ghiChu + "]";
	}
	
}
