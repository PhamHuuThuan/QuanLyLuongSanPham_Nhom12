package Entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class NhanVien {
	private String maNV;
	private String matKhau;
	private String hoTen;
	private boolean gioiTinh;	// true: nam, false: nu
	private java.util.Date ngaySinh;
	private String sdt;
	private String email;
	private String cCCD;
	private String diaChi;
	private String hinhAnh;
	
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", matKhau=" + matKhau + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh
				+ ", ngaySinh=" + ngaySinh + ", sdt=" + sdt + ", email=" + email + ", cCCD=" + cCCD + ", diaChi="
				+ diaChi + ", hinhAnh=" + hinhAnh + "]";
	}
	//Khởi tạo đối tượng nhân viên đầy đủ tham số
	public NhanVien(String maNV, String matKhau, String hoTen, boolean gioiTinh, java.util.Date ngaySinh, String sdt,
			String email, String cCCD, String diaChi, String hinhAnh){
		super();
		try {
			setMaNV(maNV);
			setMatKhau(matKhau);
			setHoTen(hoTen);
			setGioiTinh(gioiTinh);
			setNgaySinh(ngaySinh);
			setSdt(sdt);
			setEmail(email);
			setcCCD(cCCD);
			setDiaChi(diaChi);
			setHinhAnh(hinhAnh);
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}
	// Khởi tạo đối tượng nhân viên mặc định (không có tham số)
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	//KHởi tạo nhân viên chỉ có mã NV
	public NhanVien(String maNV) {
		super();
		try {
			setMaNV(maNV);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) throws Exception {
		if(maNV==null || maNV.trim().length()<=0) {
			throw new Exception("Mã nhân viên không được rỗng!");
		}
		if(!maNV.matches("\\S+")){
		    throw new Exception("Mã nhân viên không được chứa khoảng trắng!");
		}
		else if(!maNV.matches("^NV\\d{5}$")){
		    throw new Exception("Mã nhân viên có dạng NV12345");
		}
		else {
			this.maNV = maNV;
		}
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) throws Exception{
		if(matKhau==null || matKhau.trim().length()<=0) {
			throw new Exception("Mật khẩu không được rỗng!");
		}
		if(!matKhau.matches("\\S+")){
		    throw new Exception("Mật khẩu không được chứa khoảng trắng!");
		}
		else if(!matKhau.matches(".*[0-9].*")){
		    throw new Exception("Mật khẩu phải chứa ít nhất một số!");
		}
		else if(!matKhau.matches(".*[a-zA-Z].*")){
		    throw new Exception("Mật khẩu phải chứa ít nhất một chữ cái!");
		}
		else if(!matKhau.matches(".*[@#$%^&+=].*")){
		    throw new Exception("Mật khẩu phải chứa ít nhất một kí tự đặc biệt!");
		}
		else {
			this.matKhau = matKhau;
		}
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) throws Exception {
		if(hoTen!=null && hoTen.trim().length()>0) {
			this.hoTen = hoTen;
		}
		else {
			throw new Exception("Họ tên không được rỗng!");
		}
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public java.util.Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(java.util.Date ngaySinh) throws Exception {
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -18); // trừ 18 năm kể từ hiện tại
        java.util.Date eighteenYearsAgo = cal.getTime();
		if(!ngaySinh.before(eighteenYearsAgo)) {
			throw new Exception("Ngày sinh trước ngày hiện tại và phải đủ 18 tuổi!");
		}
		else {
			this.ngaySinh = ngaySinh;
		}
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) throws Exception {
		if(sdt==null || sdt.trim().length()<=0) {
			throw new Exception("Số điện thoại không được rỗng!");
		}
		if(!sdt.matches("\\S+")){
		    throw new Exception("Số điện thoại không được chứa khoảng trắng!");
		}
		else if(!sdt.matches("^(\\+84|84|0)\\d{9}$")){
		    throw new Exception("Số điện thoại bắt đầu bằng 0, 84, +84 và 9 số!");
		}
		else {
			this.sdt = sdt;
		}
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws Exception {
		if(email==null || email.trim().length()<=0) {
			throw new Exception("Email không được rỗng!");
		}
		if(!email.matches("\\S+")){
		    throw new Exception("Email không được chứa khoảng trắng!");
		}
		else if(!email.matches("^([a-zA-Z0-9]){5,}@([a-zA-Z0-9])+\\.com$")){
		    throw new Exception("Email có dạng abcde@domain.com");
		}
		else {
			this.email = email;
		}
	}
	public String getcCCD() {
		return cCCD;
	}
	public void setcCCD(String cCCD) {
		if(cCCD==null || cCCD.trim().length()<=0) {
			throw new IllegalArgumentException("Căn cước công dân không được rỗng!");
		}
		if(!cCCD.matches("\\S+")){
		    throw new IllegalArgumentException("Căn cước công dân không được chứa khoảng trắng!");
		}
		else if(!cCCD.matches("^\\d{12}$")){
		    throw new IllegalArgumentException("Căn cước công dân dài 12 kí tự và chỉ chứa số");
		}
		else {
			this.cCCD = cCCD;
		}
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) throws Exception {
		this.diaChi = diaChi;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	
//	public static void main(String[] args){
//		NhanVien nv = new NhanVien("", "123456a@", "123", false, new java.util.Date(Date.UTC(103, 4, 14, 0, 0, 0)), "+84123456789", "", "", "", "");
//	}
}
