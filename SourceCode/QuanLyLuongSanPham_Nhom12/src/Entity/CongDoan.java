package Entity;

import java.util.Date;

public class CongDoan {
	private String maCD;
	private String tenCD;
	private int thuTu;
	private int soLuong;
	private double donGia;

	private boolean tinhTrang;// true: đã hoàn thành | false: chưa hoàn thành
	private Date ngayHoanThanh;
	private SanPham sanPham;
	
	private int soLuongConLai;

	
	public CongDoan(String maCD, String tenCD, int thuTu, int soLuong, double donGia, boolean tinhTrang,
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
	

	public CongDoan(int soLuong, int soLuongConLai) {
		super();
		this.soLuong = soLuong;
		this.soLuongConLai = soLuongConLai;
	}

	public CongDoan(String maCD, String tenCD, int thuTu, int soLuong,int soLuongConLai ,double donGia, boolean tinhTrang,
			Date ngayHoanThanh, SanPham sanPham) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		this.thuTu = thuTu;
		this.soLuong = soLuong;
		this.soLuongConLai = soLuongConLai;
		this.donGia = donGia;
		this.tinhTrang = tinhTrang;
		this.ngayHoanThanh = ngayHoanThanh;
		this.sanPham = sanPham;
	}

	public CongDoan(String maCD, String tenCD, int soLuong, SanPham sanPham) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		this.soLuong = soLuong;
		this.sanPham = sanPham;
	}


	public CongDoan(String maCD) {
		super();
		this.maCD = maCD;
	}
	
	public CongDoan(String maCD, String tenCD) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
	}
	public CongDoan(String maCD, String tenCD, Integer thuTu) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		this.thuTu = thuTu;
	}

	public CongDoan(String maCD,String tenCD, Integer soLuong, Integer soLuongConLai) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		this.soLuong = soLuong;
		this.soLuongConLai = soLuongConLai;
	}
	
	
	
	

	public CongDoan(String maCD, String tenCD, int soLuong, SanPham sanPham, int soLuongConLai) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		this.soLuong = soLuong;
		this.sanPham = sanPham;
		this.soLuongConLai = soLuongConLai;
	}


	public CongDoan(String maCD, String tenCD, SanPham sanPham) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		this.sanPham = sanPham;
	}

	public int getSoLuongConLai() {
		return soLuongConLai;
	}

	public void setSoLuongConLai(int soLuongConLai) {
		this.soLuongConLai = soLuongConLai;
	}

	public String getMaCD() {
		return maCD;
	}

	public void setMaCD(String maCD) throws Exception {
		if (maCD == null || maCD.trim().length() <= 0) {
			throw new Exception("Mã công đoạn không được rỗng!");
		}
		if (!maCD.matches("\\S+")) {
			throw new Exception("Mã công đoạn không được chứa khoảng trắng!");
		} else if (!maCD.matches("^CD\\d{7}$")) {
			throw new Exception("Mã công đoạn có dạng CD1234567");
		} else {
			this.maCD = maCD;
		}
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

	public void setThuTu(int thuTu) throws Exception {
		if (thuTu < 0) {
			throw new Exception("Thứ tự phải lớn hơn 0!");
		}
		this.thuTu = thuTu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) throws Exception  {
		if(soLuong <0) {
			throw new Exception("Số lượng phải lớn hơn 0!");
		}
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
        Date ngayHienTai = new Date();
        if (ngayHoanThanh.compareTo(ngayHienTai) >= 0) {
            this.ngayHoanThanh = ngayHoanThanh;
        } else {
            System.out.println("Ngày hoàn thành không hợp lệ.");
        }
    }

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	@Override
	public String toString() {
		return "CongDoan [maCD=" + maCD + ", tenCD=" + tenCD + ", thuTu=" + thuTu + ", soLuong=" + soLuong + ", donGia="
				+ donGia + ", tinhTrang=" + tinhTrang + ", ngayHoanThanh=" + ngayHoanThanh + "]";
	}

	public CongDoan(String maCD, String tenCD, int thuTu, int soLuong, double donGia, boolean tinhTrang,
			Date ngayHoanThanh, int soLuongConLai , SanPham sanPham) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		this.thuTu = thuTu;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.tinhTrang = tinhTrang;
		this.ngayHoanThanh = ngayHoanThanh;
		this.soLuongConLai = soLuongConLai;
		this.sanPham = sanPham;
	}

	public CongDoan(SanPham sanPham) {
		super();
		this.sanPham = sanPham;
	}
	
	
}