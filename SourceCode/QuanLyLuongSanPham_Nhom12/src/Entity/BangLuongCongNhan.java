package Entity;

import java.time.YearMonth;
import java.util.ArrayList;

public class BangLuongCongNhan {
	private String maBangLuong;
	private CongNhan congNhan;
	private BangChamCongCongNhan chamCongCongNhan;
	private int soNgayLam;
	private int soNgayNghi;
	private int soNgayPhep;
	private double luongThang;
	private double luongCongDoan;
	private double thucLanh;
	private String thangNam;
	
	
	public BangLuongCongNhan(String maBangLuong, CongNhan congNhan, BangChamCongCongNhan chamCongCongNhan, int soNgayLam,
			double luongThang, double luongCongDoan, double thucLanh, String thangNam) {
		super();
		this.maBangLuong = maBangLuong;
		this.congNhan = congNhan;
		this.chamCongCongNhan = chamCongCongNhan;
		this.soNgayLam = soNgayLam;
		this.luongThang = luongThang;
		this.luongCongDoan = luongCongDoan;
		this.thucLanh = thucLanh;
		this.thangNam = thangNam;
	}
	public BangLuongCongNhan(String maBangLuong, CongNhan congNhan, BangChamCongCongNhan chamCongCongNhan,
			int soNgayLam, int soNgayNghi, int soNgayPhep, double luongThang, double luongCongDoan, double thucLanh,
			String thangNam) {
		super();
		this.maBangLuong = maBangLuong;
		this.congNhan = congNhan;
		this.chamCongCongNhan = chamCongCongNhan;
		this.soNgayLam = soNgayLam;
		this.soNgayNghi = soNgayNghi;
		this.soNgayPhep = soNgayPhep;
		this.luongThang = luongThang;
		this.luongCongDoan = luongCongDoan;
		this.thucLanh = thucLanh;
		this.thangNam = thangNam;
	}
	
	
	public BangLuongCongNhan(BangChamCongCongNhan chamCongCongNhan) {
		super();
		this.chamCongCongNhan = chamCongCongNhan;
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
	public BangChamCongCongNhan getChamCongCongNhan() {
		return chamCongCongNhan;
	}
	public void setChamCongCongNhan(BangChamCongCongNhan chamCongCongNhan) {
		this.chamCongCongNhan = chamCongCongNhan;
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
	public double getLuongThang() {
		return luongThang;
	}
	public void setLuongThang(double luongThang) {
		this.luongThang = luongThang;
	}
	public double getLuongCongDoan() {
		return luongCongDoan;
	}
	public void setLuongCongDoan(double luongCongDoan) {
		this.luongCongDoan = luongCongDoan;
	}
	public double getThucLanh() {
		return thucLanh;
	}
	public void setThucLanh(double thucLanh) {
		this.thucLanh = thucLanh;
	}
	public String getThangNam() {
		return thangNam;
	}
	public void setThangNam(String thangNam) {
		this.thangNam = thangNam;
	}
	@Override
	public String toString() {
		return "BangLuongCongNhan [maBangLuong=" + maBangLuong + ", congNhan=" + congNhan + ", chamCongCongNhan="
				+ chamCongCongNhan + ", soNgayLam=" + soNgayLam + ", soNgayNghi=" + soNgayNghi + ", soNgayPhep="
				+ soNgayPhep + ", luongThang=" + luongThang + ", luongCongDoan=" + luongCongDoan + ", thucLanh="
				+ thucLanh + ", thangNam=" + thangNam + "]";
	}
	
	

}
