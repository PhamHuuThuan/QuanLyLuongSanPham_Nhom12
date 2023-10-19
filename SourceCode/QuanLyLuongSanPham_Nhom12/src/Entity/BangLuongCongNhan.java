package Entity;

import java.time.YearMonth;
import java.util.ArrayList;

public class BangLuongCongNhan {
	private String maBangLuong;
	private CongNhan congNhan;
	//bo sung trong class
	private ArrayList<BangChamCongCongNhan> danhSachChamCong;
	private int soLuongCongDoanLam;
	private int soNgayLam;
	private int soNgayNghi;
	private int soNgayPhep;
	private double thucLanh;
	private YearMonth thangNam;
	
	
	
	@Override
	public String toString() {
		return "BangLuongCongNhan [maBangLuong=" + maBangLuong + ", congNhan=" + congNhan + ", danhSachChamCong="
				+ danhSachChamCong + ", soLuongCongDoanLam=" + soLuongCongDoanLam + ", soNgayLam=" + soNgayLam
				+ ", soNgayNghi=" + soNgayNghi + ", soNgayPhep=" + soNgayPhep + ", thucLanh=" + thucLanh + ", thangNam="
				+ thangNam + "]";
	}
	public BangLuongCongNhan(String maBangLuong, CongNhan congNhan, ArrayList<BangChamCongCongNhan> danhSachChamCong,
			int soLuongCongDoanLam, int soNgayLam, int soNgayNghi, int soNgayPhep, double thucLanh,
			YearMonth thangNam) {
		super();
		this.maBangLuong = maBangLuong;
		this.congNhan = congNhan;
		this.danhSachChamCong = danhSachChamCong;
		this.soLuongCongDoanLam = soLuongCongDoanLam;
		this.soNgayLam = soNgayLam;
		this.soNgayNghi = soNgayNghi;
		this.soNgayPhep = soNgayPhep;
		this.thucLanh = thucLanh;
		this.thangNam = thangNam;
	}
	public BangLuongCongNhan() {
		super();
	}
	public String getMaBangLuong() {
		return maBangLuong;
	}
	public void setMaBangLuong(String maBangLuong) {
		this.maBangLuong = maBangLuong;
	}
	public CongNhan getCongNhan() {
		return congNhan;
	}
	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}
	public ArrayList<BangChamCongCongNhan> getDanhSachChamCong() {
		return danhSachChamCong;
	}
	public void setDanhSachChamCong(ArrayList<BangChamCongCongNhan> danhSachChamCong) {
		this.danhSachChamCong = danhSachChamCong;
	}
	public int getSoLuongCongDoanLam() {
		return soLuongCongDoanLam;
	}
	public void setSoLuongCongDoanLam(int soLuongCongDoanLam) {
		this.soLuongCongDoanLam = soLuongCongDoanLam;
	}
	public int getSoNgayLam() {
		return soNgayLam;
	}
	public void setSoNgayLam(int soNgayLam) {
		this.soNgayLam = soNgayLam;
	}
	public int getSoNgayNghi() {
		return soNgayNghi;
	}
	public void setSoNgayNghi(int soNgayNghi) {
		this.soNgayNghi = soNgayNghi;
	}
	public int getSoNgayPhep() {
		return soNgayPhep;
	}
	public void setSoNgayPhep(int soNgayPhep) {
		this.soNgayPhep = soNgayPhep;
	}
	public double getThucLanh() {
		return thucLanh;
	}
	public void setThucLanh(double thucLanh) {
		this.thucLanh = thucLanh;
	}
	public YearMonth getThangNam() {
		return thangNam;
	}
	public void setThangNam(YearMonth thangNam) {
		this.thangNam = thangNam;
	}
	
	

	
	

}
