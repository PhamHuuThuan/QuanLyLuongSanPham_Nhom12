package Util;

public class XuatChamCongForm {
	private String ngayCham;
	private String caLam;
	private String trangThai;
	private String gioDen;
	private String tangCa;
	private String ghiChu;
	public XuatChamCongForm(String ngayCham, String caLam, String trangThai, String gioDen, String tangCa,
			String ghiChu) {
		super();
		this.ngayCham = ngayCham;
		this.caLam = caLam;
		this.trangThai = trangThai;
		this.gioDen = gioDen;
		this.tangCa = tangCa;
		this.ghiChu = ghiChu;
	}
	public String getNgayCham() {
		return ngayCham;
	}
	public void setNgayCham(String ngayCham) {
		this.ngayCham = ngayCham;
	}
	public String getCaLam() {
		return caLam;
	}
	public void setCaLam(String caLam) {
		this.caLam = caLam;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public String getGioDen() {
		return gioDen;
	}
	public void setGioDen(String gioDen) {
		this.gioDen = gioDen;
	}
	public String getTangCa() {
		return tangCa;
	}
	public void setTangCa(String tangCa) {
		this.tangCa = tangCa;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	@Override
	public String toString() {
		return "XuatChamCongForm [ngayCham=" + ngayCham + ", caLam=" + caLam + ", trangThai=" + trangThai + ", gioDen="
				+ gioDen + ", tangCa=" + tangCa + ", ghiChu=" + ghiChu + "]";
	}
}
