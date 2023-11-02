package Entity;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class BangChamCongNhanVien {
	private BangPhanCongNhanVien phanCong;
	private Date ngayChamCong;
	private int caLam; // 1: nua ngay, 2: ca ngay
	private int trangThai; // 0: đúng giờ, 1: trễ, 2: nghỉ, 3: nghỉ phép
	private String gioDen;
	private float gioTangCa;
	private String ghiChu;
	
	//Khởi tạo đối tượng bangchamcongnhanvien đầy đủ tham số
	public BangChamCongNhanVien(BangPhanCongNhanVien phanCong, Date ngayChamCong, int caLam, int trangThai,
			String gioDen, float gioTangCa, String ghiChu) {
		super();
		try {
			setPhanCong(phanCong);
			setNgayChamCong(ngayChamCong);
			setCaLam(caLam);
			setTrangThai(trangThai);
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
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
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
				+ ", trangThai=" + trangThai + ", gioDen=" + gioDen + ", gioTangCa=" + gioTangCa + ", ghiChu=" + ghiChu
				+ "]";
	}
}
