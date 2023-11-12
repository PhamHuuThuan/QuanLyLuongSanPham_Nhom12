package Entity;


import java.util.Calendar;
import java.util.Date;


public class BangChamCongCongNhan {
	private Date ngayChamCong;
	// sua class thanh String
	private String gioVaoLam;
	private BangPhanCongCongDoan phanCong;
	private  int soLuongLam ;
	private String ghiChu;
	private Integer trangThai;
	private int soLuongChuaCham;
	
	private CongNhan congNhan;
	private CongDoan congDoan;
	private SanPham sanPham;
	
	public BangChamCongCongNhan(Date ngayChamCong, String gioVaoLam, BangPhanCongCongDoan phanCong, int soLuongLam,
			String ghiChu, Integer trangThai) {
		super();
		this.ngayChamCong = ngayChamCong;
		this.gioVaoLam = gioVaoLam;
		this.phanCong = phanCong;
		this.soLuongLam = soLuongLam;
		this.ghiChu = ghiChu;
		this.trangThai = trangThai;
	}





	public BangChamCongCongNhan(BangPhanCongCongDoan phanCong, int soLuongLam, int trangThai) {
		super();
		this.phanCong = phanCong;
		this.soLuongLam = soLuongLam;
		this.trangThai = trangThai;
	}







	public BangChamCongCongNhan() {
		super();
	}
	


	public BangChamCongCongNhan(int soLuongLam, int soLuongChuaCham) {
		super();
		this.soLuongLam = soLuongLam;
		this.soLuongChuaCham = soLuongChuaCham;
	}


	public BangChamCongCongNhan(Date ngayChamCong, String gioVaoLam, BangPhanCongCongDoan phanCong, int soLuongLam,
			String ghiChu, Integer trangThai, CongNhan congNhan, CongDoan congDoan, SanPham sanPham) {
		super();
		this.ngayChamCong = ngayChamCong;
		this.gioVaoLam = gioVaoLam;
		this.phanCong = phanCong;
		this.soLuongLam = soLuongLam;
		this.ghiChu = ghiChu;
		this.trangThai = trangThai;
		this.congNhan = congNhan;
		this.congDoan = congDoan;
		this.sanPham = sanPham;
	}
	
	
	
	
	


	public int getSoLuongChuaCham() {
		return soLuongChuaCham;
	}


	public void setSoLuongChuaCham(int soLuongChuaCham) {
		this.soLuongChuaCham = soLuongChuaCham;
	}


	public CongNhan getCongNhan() {
		return congNhan;
	}


	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}


	public CongDoan getCongDoan() {
		return congDoan;
	}


	public void setCongDoan(CongDoan congDoan) {
		this.congDoan = congDoan;
	}


	public SanPham getSanPham() {
		return sanPham;
	}


	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}


	public Integer getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Integer trangThai) {
		this.trangThai = trangThai;
	}
	
	public Date getNgayChamCong() {
		return ngayChamCong;
	}
	public void setNgayChamCong(Date ngayChamCong) throws Exception {
		Calendar cal = Calendar.getInstance();
        java.util.Date now = cal.getTime();
		if(ngayChamCong.after(now)) {
			throw new Exception("Ngày chấm công từ ngày hiện tại về trước");
		}
		else {
			this.ngayChamCong = ngayChamCong;
		}
	}
	public String getGioVaoLam() {
		return gioVaoLam;
	}
	public void setGioVaoLam(String gioVaoLam) {

		this.gioVaoLam = gioVaoLam;
	}
	public BangPhanCongCongDoan getPhanCong() {
		return phanCong;
	}
	public void setPhanCong(BangPhanCongCongDoan phanCong) {
		this.phanCong = phanCong;
	}
	public int getSoLuongLam() {
		return soLuongLam;
	}
	public void setSoLuongLam(int soLuongLam) throws Exception{
		if(soLuongLam <0) {
			throw new Exception("Số lượng làm phải lớn hơn 0");
		}else {
			this.soLuongLam = soLuongLam;
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
		return "BangChamCongCongNhan [ngayChamCong=" + ngayChamCong + ", gioVaoLam=" + gioVaoLam + ", phanCong="
				+ phanCong + ", soLuongLam=" + soLuongLam + ", ghiChu=" + ghiChu + "]";
	}
}
