package Entity;

import java.util.Date;

public class CongDoan {
	private String maCD;
	private String tenCD;
	private int thuTu;
	private int soLuong;
	private double donGia;
	private boolean tinhTrang;
	private Date ngayHoanThanh;
	private SanPham sanPham;

	

	@Override
	public String toString() {
		return "CongDoan [maCD=" + maCD + ", tenCD=" + tenCD + ", thuTu=" + thuTu + ", soLuong=" + soLuong + ", donGia="
				+ donGia + ", tinhTrang=" + tinhTrang + ", ngayHoanThanh=" + ngayHoanThanh + "]";
	}

	public CongDoan(String maCD, String tenCD, int thuTu, int soLuong, double donGia, boolean tinhTrang,
			Date ngayHoanThanh, SanPham sanPham) {
		super();
		try {
			setMaCD(maCD);
			setTenCD(tenCD);
			setThuTu(thuTu);
			setSoLuong(soLuong);
			setDonGia(donGia);
			setTinhTrang(tinhTrang);
			setNgayHoanThanh(ngayHoanThanh);
			setSanPham(sanPham);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	public CongDoan() {
		super();
		
	}

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

	public boolean isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
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
	

}








