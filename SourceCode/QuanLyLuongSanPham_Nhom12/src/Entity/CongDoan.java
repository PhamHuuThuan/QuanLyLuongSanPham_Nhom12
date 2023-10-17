package Entity;

import java.sql.Blob;
import java.util.Date;

public class CongDoan {
	private String maCD;
	private String tenCD;
	private int thuTu;
	private int soLuong;
	private double donGia;
	private Boolean tinhTrang;
	private Date ngayHoanThanh;
	private SanPham sanPham;
	public String getMaCD() {
		return maCD;
	}
	public void setMaCD(String maCD) {
		this.maCD = maCD;
	}
	public String getTenCD() {
		return tenCD;
	}
	public void setTenCD(String tenCD) {
		this.tenCD = tenCD;
	}
	public int getThuTu() {
		return thuTu;
	}
	public void setThuTu(int thuTu) {
		this.thuTu = thuTu;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public Boolean getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(Boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public Date getNgayHoanThanh() {
		return ngayHoanThanh;
	}
	public void setNgayHoanThanh(Date ngayHoanThanh) {
		this.ngayHoanThanh = ngayHoanThanh;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public CongDoan(String maCD, String tenCD, int thuTu, int soLuong, double donGia, Boolean tinhTrang,
			Date ngayHoanThanh, SanPham sanPham) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		this.thuTu = thuTu;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.tinhTrang = tinhTrang;
		this.ngayHoanThanh = ngayHoanThanh;
		this.sanPham = sanPham;
	}
	public CongDoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CongDoan [maCD=" + maCD + ", tenCD=" + tenCD + ", thuTu=" + thuTu + ", soLuong=" + soLuong + ", donGia="
				+ donGia + ", tinhTrang=" + tinhTrang + ", ngayHoanThanh=" + ngayHoanThanh + "]";
	}
	
	
}
