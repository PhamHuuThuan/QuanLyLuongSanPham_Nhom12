package Entity;

import java.time.YearMonth;
import java.util.ArrayList;

public class BangLuongNhanVien {
	private String maBangLuong;
	private NhanVien nhanVien;
	private ArrayList<BangChamCongNhanVien> dsChamCong;
	private float ngayLam;
	private float ngayNghi;
	private float ngayNghiPhep;
	private double luongThang;
	private double luongTangCa;
	private double phuCap;
	private double thucLanh;
	private YearMonth thangNam;
	private String ghiChu;
	
	//Khởi tạo đối tượng bangluongnhanvien đầy đủ tham số
	public BangLuongNhanVien(String maBangLuong, NhanVien nhanVien, ArrayList<BangChamCongNhanVien> dsChamCong,
			float ngayLam, float ngayNghi, float ngayNghiPhep, double luongThang, double luongTangCa, double phuCap,
			double thucLanh, YearMonth thangNam, String ghiChu) {
		super();
		try {
			setMaBangLuong(maBangLuong);
			setNhanVien(nhanVien);
			setDsChamCong(dsChamCong);
			setNgayLam(ngayLam);
			setNgayNghi(ngayNghi);
			setNgayNghiPhep(ngayNghiPhep);
			setLuongThang(luongThang);
			setLuongTangCa(luongTangCa);
			setPhuCap(phuCap);
			setThucLanh(thucLanh);
			setThangNam(thangNam);
			setGhiChu(ghiChu);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//Khởi tạo đối tượng bangluongnhanvien mặc định không có tham số
	public BangLuongNhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaBangLuong() {
		return maBangLuong;
	}
	public void setMaBangLuong(String maBangLuong) throws Exception {
		if(maBangLuong==null || maBangLuong.trim().length()<=0) {
			throw new Exception("Mã bảng lương không được rỗng!");
		}
		if(!maBangLuong.matches("\\S+")){
		    throw new Exception("Mã bảng lương không được chứa khoảng trắng!");
		}
		else if(!maBangLuong.matches("^PB\\d{2}$")){
		    throw new Exception("Mã bảng lương có dạng LN1234567");
		}
		else {
			this.maBangLuong = maBangLuong;
		}
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public ArrayList<BangChamCongNhanVien> getDsChamCong() {
		return dsChamCong;
	}
	public void setDsChamCong(ArrayList<BangChamCongNhanVien> dsChamCong) {
		this.dsChamCong = dsChamCong;
	}
	public float getNgayLam() {
		return ngayLam;
	}
	public void setNgayLam(float ngayLam) throws Exception {
		if(ngayLam<0) {
			throw new Exception("Số ngày làm hơn hoặc bằng 0!");
		}else {
			this.ngayLam = ngayLam;
		}
	}
	public float getNgayNghi() {
		return ngayNghi;
	}
	public void setNgayNghi(float ngayNghi) throws Exception {
		if(ngayNghi<0) {
			throw new Exception("Số ngày nghỉ làm hơn hoặc bằng 0!");
		}else {
			this.ngayNghi = ngayNghi;
		}
	}
	public float getNgayNghiPhep() {
		return ngayNghiPhep;
	}
	public void setNgayNghiPhep(float ngayNghiPhep) throws Exception {
		if(ngayNghiPhep<0) {
			throw new Exception("Số ngày nghỉ làm có phép hơn hoặc bằng 0!");
		}else {
			this.ngayNghiPhep = ngayNghiPhep;
		}
	}
	public double getLuongThang() {
		return luongThang;
	}
	private void setLuongThang(double luongThang) {
		this.luongThang = luongThang;
	}
	public double getLuongTangCa() {
		return luongTangCa;
	}
	private void setLuongTangCa(double luongTangCa) {
		this.luongTangCa = luongTangCa;
	}
	public double getPhuCap() {
		return phuCap;
	}
	private void setPhuCap(double phuCap) {
		this.phuCap = phuCap;
	}
	public double getThucLanh() {
		return thucLanh;
	}
	private void setThucLanh(double thucLanh) {
		this.thucLanh = thucLanh;
	}
	public YearMonth getThangNam() {
		return thangNam;
	}
	public void setThangNam(YearMonth thangNam) throws Exception {
		YearMonth now = YearMonth.now();
        if (thangNam.isAfter(now)) {
            throw new Exception("Tháng năm phải trước tháng năm hiện tại!");
        } else {
            this.thangNam = thangNam;
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
		return "BangLuongNhanVien [maBangLuong=" + maBangLuong + ", nhanVien=" + nhanVien + ", dsChamCong=" + dsChamCong
				+ ", ngayLam=" + ngayLam + ", ngayNghi=" + ngayNghi + ", ngayNghiPhep=" + ngayNghiPhep + ", luongThang="
				+ luongThang + ", luongTangCa=" + luongTangCa + ", phuCap=" + phuCap + ", thucLanh=" + thucLanh
				+ ", thangNam=" + thangNam + ", ghiChu=" + ghiChu + "]";
	}
}
