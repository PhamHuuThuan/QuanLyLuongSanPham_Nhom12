package Entity;

public class SanPham {
	private String maSP;
	private HopDong maHopDong;
	private String tenSP;
	private String donViTinh;
	private int soLuong;
	private String yeuCau;
	private double donGia;
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) throws Exception{
		if(maSP==null || maSP.trim().length()<=0) {
			throw new Exception("Mã sản phẩm  không được rỗng!");
		}
		if(!maSP.matches("\\S+")){
		    throw new Exception("Mã sản phẩm không được chứa khoảng trắng!");
		}
		else if(!maSP.matches("^SP\\d{7}$")){
		    throw new Exception("Mã sản phẩm có dạng SP1234567");
		}
		else {
			this.maSP = maSP;
		}
	}
	public HopDong getMaHopDong() {
		return maHopDong;
	}
	public void setMaHopDong(HopDong maHopDong) throws Exception{
		this.maHopDong = maHopDong;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) throws Exception{
		if (soLuong < 0) {
			throw new Exception("Số lượng phải lớn hơn 0!");
		}
		this.soLuong = soLuong;
	}

	public String getYeuCau() {
		return yeuCau;
	}
	public void setYeuCau(String yeuCau) {
		this.yeuCau = yeuCau;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public SanPham(String maSP, HopDong maHopDong, String tenSP, String donViTinh, int soLuong, String yeuCau,
			double donGia) {
		super();
		this.maSP = maSP;
		this.maHopDong = maHopDong;
		this.tenSP = tenSP;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
		this.yeuCau = yeuCau;
		this.donGia = donGia;
	}
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", donViTinh=" + donViTinh + ", soLuong=" + soLuong
				+ ", yeuCau=" + yeuCau + ", donGia=" + donGia + "]";
	}
	
	
}
