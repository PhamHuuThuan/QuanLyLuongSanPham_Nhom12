package Entity;

public class PhongBan {
	private String maPB;
	private String tenPhongBan;
	private int soNhanVien;
	private String moTa;
	
	//Khởi tạo đối tượng phòng ban đầy đủ tham số
	public PhongBan(String maPB, String tenPhongBan, int soNhanVien, String moTa) {
		super();
		try {
			setMaPhongBan(maPB);
			setTenPhongBan(tenPhongBan);
			setSoNhanVien(soNhanVien);
			setMoTa(moTa);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//Khởi tạo đối tượng phòng mặc định không có tham số
	public PhongBan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaPB() {
		return maPB;
	}
	public void setMaPhongBan(String maPhongBan) throws Exception {
		if(maPhongBan==null || maPhongBan.trim().length()<=0) {
			throw new Exception("Mã phòng ban không được rỗng!");
		}
		if(!maPhongBan.matches("\\S+")){
		    throw new Exception("Mã phòng ban không được chứa khoảng trắng!");
		}
		else if(!maPhongBan.matches("^PB\\d{2}$")){
		    throw new Exception("Mã phòng ban có dạng PB12");
		}
		else {
			this.maPB = maPhongBan;
		}
	}
	public String getTenPhongBan() {
		return tenPhongBan;
	}
	public void setTenPhongBan(String tenPhongBan) throws Exception {
		if(tenPhongBan!=null && tenPhongBan.trim().length()>0) {
			this.tenPhongBan = tenPhongBan;
		}
		else {
			throw new Exception("Tên phòng ban không được rỗng!");
		}
	}
	public int getsoLuongNV() {
		return soNhanVien;
	}
	public void setSoNhanVien(int soNhanVien) throws Exception {
		if(soNhanVien<0) {
			throw new Exception("Số nhân viên phải lớn hơn hoặc bằng 0!");
		}else {
			this.soNhanVien = soNhanVien;
		}
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	@Override
	public String toString() {
		return "PhongBan [maPhongBan=" + maPB + ", tenPhongBan=" + tenPhongBan + ", soNhanVien=" + soNhanVien
				+ ", moTa=" + moTa + "]";
	}
}
