package Entity;

import java.util.Date;

public class BangPhanCongNhanVien {
	private String maPhanCong;
	private NhanVien nhanVien;
	private PhongBan phongBan;
	private String chucVu;		// Quan li, nhan vien, thuc tap
	private Date ngayCongTac;
	private String ghiChu;
	
	//Khởi tạo đối tượng bangphancongnhanvien đầy đủ tham số
	public BangPhanCongNhanVien(String maPhanCong, NhanVien nhanVien, PhongBan phongBan, String chucVu, Date ngayCongTac, String ghiChu) {
		super();
		try {
			setMaPhanCong(maPhanCong);
			setNhanVien(nhanVien);
			setPhongBan(phongBan);
			setChucVu(chucVu);
			setNgayCongTac(ngayCongTac);
			setGhiChu(ghiChu);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//Khởi tạo đối tượng bangphancongnhanvien mặc định không có tham số
	public BangPhanCongNhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaPhanCong() {
		return maPhanCong;
	}
	public void setMaPhanCong(String maPhanCong) throws Exception {
		if(maPhanCong==null || maPhanCong.trim().length()<=0) {
			throw new Exception("Mã phân công không được rỗng!");
		}
		if(!maPhanCong.matches("\\S+")){
		    throw new Exception("Mã phân công không được chứa khoảng trắng!");
		}
		else if(!maPhanCong.matches("^PC\\d{5}$")){
		    throw new Exception("Mã phân công có dạng PC12345");
		}
		else {
			this.maPhanCong = maPhanCong;
		}
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public PhongBan getPhongBan() {
		return phongBan;
	}
	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public Date getNgayCongTac() {
		return ngayCongTac;
	}
	public void setNgayCongTac(Date ngayCongTac) {
		this.ngayCongTac = ngayCongTac;
	}
	@Override
	public String toString() {
		return "BangPhanCongNhanVien [maPhanCong=" + maPhanCong + ", nhanVien=" + nhanVien + ", phongBan=" + phongBan
				+ ", chucVu=" + chucVu + ", ngayCongTac=" + ngayCongTac + ", ghiChu=" + ghiChu + "]";
	}
}
