package Entity;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class BangChamCongNhanVien {
	private BangPhanCongNhanVien phanCong;
	private Date ngayChamCong;
	private int caLam; // 1: nua ngay, 2: ca ngay
	private boolean diLam;
	private boolean coPhep;
	private String gioDen;
	private float gioTangCa;
	private String ghiChu;
	
	//Khởi tạo đối tượng bangchamcongnhanvien đầy đủ tham số
	public BangChamCongNhanVien(BangPhanCongNhanVien phanCong, Date ngayChamCong, int caLam, boolean diLam, boolean coPhep,
			String gioDen, float gioTangCa, String ghiChu) {
		super();
		try {
			setPhanCong(phanCong);
			setNgayChamCong(ngayChamCong);
			setCaLam(caLam);
			setDiLam(diLam);
			setCoPhep(coPhep);
			setGioDen(gioDen);
			setGioTangCa(gioTangCa);
			setGhiChu(ghiChu);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//Khởi tạo đối tượng bangchamcongnhanvien mặc định không có tham số
	public BangChamCongNhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BangPhanCongNhanVien getPhanCong() {
		return phanCong;
	}
	public void setPhanCong(BangPhanCongNhanVien phanCong) {
		this.phanCong = phanCong;
	}
	public Date getNgayChamCong() {
		return ngayChamCong;
	}
	public void setNgayChamCong(Date ngayChamCong) throws Exception {
		Calendar cal = Calendar.getInstance();
        java.util.Date now = cal.getTime();
		if(ngayChamCong.after(now)) {
			throw new Exception("Ngày chấm công từ ngày hiện tại về trước!");
		}
		else {
			this.ngayChamCong = ngayChamCong;
		}
	}
	public int getCaLam() {
		return caLam;
	}
	public void setCaLam(int caLam) {
		this.caLam = caLam;
	}
	public boolean isDiLam() {
		return diLam;
	}
	public void setDiLam(boolean diLam) {
		this.diLam = diLam;
	}
	public boolean isCoPhep() {
		return coPhep;
	}
	public void setCoPhep(boolean coPhep) {
		this.coPhep = coPhep;
	}
	public String getGioDen() {
		return gioDen;
	}
	public void setGioDen(String gioDen) {
		this.gioDen = gioDen;
	}
	public float getGioTangCa() {
		return gioTangCa;
	}
	public void setGioTangCa(float gioTangCa) throws Exception {
		if(gioTangCa < 0) {
			throw new Exception("Số giờ tăng ca phải lớn hơn hoặc bằng 0!");
		}else {
			this.gioTangCa = gioTangCa;
		}
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	@Override
	public String toString() {
		return "BangChamCongNhanVien [phanCong=" + phanCong + ", ngayChamCong=" + ngayChamCong + ", caLam=" + caLam
				+ ", diLam=" + diLam + ", coPhep=" + coPhep + ", gioDen=" + gioDen + ", gioTangCa=" + gioTangCa
				+ ", ghiChu=" + ghiChu + "]";
	}
}
