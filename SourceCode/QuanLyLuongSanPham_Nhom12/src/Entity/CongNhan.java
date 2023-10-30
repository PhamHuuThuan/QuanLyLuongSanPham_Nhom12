package Entity;

import java.util.Calendar;
import java.util.Date;

public class CongNhan {
	private String maCN;
	private String matKhau;
	private String hoTen;
	private Date ngaySinh;
	private boolean gioiTinh; // true:nam, false: nu
	private String sDT;
	private String email;
	private String diaChi;
	private String soCCCD;
	private Date ngayVaoLam;
	private String anhDaiDien;
	private String ghiChu;

	public CongNhan(String maCN, String matKhau, String hoTen, Date ngaySinh, boolean gioiTinh, String sDT,
			String email, String diaChi, String soCCCD, Date ngayVaoLam, String anhDaiDien,String ghiChu) {
		super();
		try {
			setMaCN(maCN);
			setMatKhau(matKhau);
			setHoTen(hoTen);
			setNgaySinh(ngaySinh);
			setGioiTinh(gioiTinh);
			setSDT(sDT);
			setEmail(email);
			setDiaChi(diaChi);
			setSoCCCD(soCCCD);
			setNgayVaoLam(ngayVaoLam);
			setAnhDaiDien(anhDaiDien);
			setGhiChu(ghiChu);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	

	public String getMaCN() {
		return maCN;
	}

	public void setMaCN(String maCN) throws Exception {
		if (maCN == null || maCN.trim().length() > 0) {
			throw new Exception("Mã công nhân không phép rỗng");
		}
		if (!maCN.matches("\\S+")) {
			throw new Exception("Mã công nhân không phép chưa khoảng trắng");
		} else if (!maCN.matches("^CN\\d{5}$")) {
			throw new Exception("Mã Công nhân phải định dạng CN12345");
		} else {

			this.maCN = maCN;
		}
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) throws Exception {
		if (matKhau == null || matKhau.trim().length() <= 0) {
			throw new Exception("Mật khẩu không được rỗng!");
		}
		if (!matKhau.matches("\\S+")) {
			throw new Exception("Mật khẩu không được chứa khoảng trắng!");
		} else if (!matKhau.matches(".*[0-9].*")) {
			throw new Exception("Mật khẩu phải chứa ít nhất một số!");
		} else if (!matKhau.matches(".*[a-zA-Z].*")) {
			throw new Exception("Mật khẩu phải chứa ít nhất một chữ cái!");
		} else if (!matKhau.matches(".*[@#$%^&+=].*")) {
			throw new Exception("Mật khẩu phải chứa ít nhất một kí tự đặc biệt!");
		} else {
			this.matKhau = matKhau;
		}
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) throws Exception {
		if (hoTen != null && hoTen.trim().length() > 0) {
			this.hoTen = hoTen;
		} else {
			throw new Exception("Họ tên không được rỗng!");
		}
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -18);
		java.util.Date eighteenYearsAgo = cal.getTime();
		if (!ngaySinh.before(eighteenYearsAgo)) {
			throw new Exception("Ngày sinh trước ngày hiện tại và phải đủ 18 tuổi!");
		} else {
			this.ngaySinh = ngaySinh;
		}
	}

	public boolean getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSDT() {
		return sDT;
	}

	public void setSDT(String sDT) throws Exception {
		if (sDT == null || sDT.trim().length() <= 0) {
			throw new Exception("Số điện thoại không được rỗng!");
		}
		if (!sDT.matches("\\S+")) {
			throw new Exception("Số điện thoại không được chứa khoảng trắng!");
		} else if (!sDT.matches("^(\\+84|84|0)\\d{9}$")) {
			throw new Exception("Số điện thoại bắt đầu bằng 0, 84, +84 và 9 số!");
		} else {
			this.sDT = sDT;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		if (email == null || email.trim().length() <= 0) {
			throw new Exception("Email không được rỗng!");
		}
		if (!email.matches("\\S+")) {
			throw new Exception("Email không được chứa khoảng trắng!");
		} else if (!email.matches("^([a-zA-Z0-9]){5,}@([a-zA-Z0-9])+\\.com$")) {
			throw new Exception("Email phải có dạng abcde@domain.com");
		} else {
			this.email = email;
		}
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoCCCD() {
		return soCCCD;
	}

	public void setSoCCCD(String soCCCD)throws Exception {
		if(soCCCD==null || soCCCD.trim().length()<=0) {
			throw new Exception("Căn cước công dân không được rỗng!");
		}
		if(!soCCCD.matches("\\S+")){
		    throw new Exception("Căn cước công dân không được chứa khoảng trắng!");
		}
		else if(!soCCCD.matches("^\\d{12}$")){
		    throw new Exception("Căn cước công dân dài 12 kí tự và chỉ chứa số");
		}
		else {
			this.soCCCD = soCCCD;
		}
	}

	public Date getNgayVaoLam() {
		return ngayVaoLam;
	}

	public void setNgayVaoLam(Date ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}

	public String getAnhDaiDien() {
		return anhDaiDien;
	}

	public void setAnhDaiDien(String anhDaiDien) {
		this.anhDaiDien = anhDaiDien;
	}
	
	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "CongNhan [maCN=" + maCN + ", matKhau=" + matKhau + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh
				+ ", gioiTinh=" + gioiTinh + ", sDT=" + sDT + ", email=" + email + ", diaChi=" + diaChi + ", soCCCD="
				+ soCCCD + ", ngayVaoLam=" + ngayVaoLam + ", anhDaiDien=" + anhDaiDien + ", ghiChu=" + ghiChu + "]";
	}

	
}
