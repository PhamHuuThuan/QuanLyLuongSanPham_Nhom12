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

	public CongDoan(String string, SanPham sanPham2, String string2, String string3, int i, String string4, double d) {
		super();

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

	public String getMaSanPham() {
		// TODO Auto-generated method stub
		return null;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getMaCDLonNhat() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
