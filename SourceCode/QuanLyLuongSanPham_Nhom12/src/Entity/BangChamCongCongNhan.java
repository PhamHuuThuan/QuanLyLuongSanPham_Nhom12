package Entity;

<<<<<<< HEAD
import java.util.Date;

public class BangChamCongCongNhan {
	private Date ngayChamCong;
	private Date gioVaoLam;
=======
import java.util.Calendar;
import java.util.Date;

import javax.swing.Timer;

public class BangChamCongCongNhan {
	private Date ngayChamCong;
	// sua class thanh Timer
	private Timer gioVaoLam;
>>>>>>> main
	private BangPhanCongCongDoan phanCong;
	private  int soLuongLam ;
	private String ghiChu;
	
<<<<<<< HEAD
	
	
	
	
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
=======
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
		
>>>>>>> main
	}
	
	public BangChamCongCongNhan() {
		super();
	}
	
	public Date getNgayChamCong() {
		return ngayChamCong;
	}
<<<<<<< HEAD
	public void setNgayChamCong(Date ngayChamCong) {
		this.ngayChamCong = ngayChamCong;
	}
	public Date getGioVaoLam() {
		return gioVaoLam;
	}
	public void setGioVaoLam(Date gioVaoLam) {
=======
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
>>>>>>> main
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
	
	
<<<<<<< HEAD
=======
	@Override
	public String toString() {
		return "BangChamCongCongNhan [ngayChamCong=" + ngayChamCong + ", gioVaoLam=" + gioVaoLam + ", phanCong="
				+ phanCong + ", soLuongLam=" + soLuongLam + ", ghiChu=" + ghiChu + "]";
	}
	
>>>>>>> main
}
