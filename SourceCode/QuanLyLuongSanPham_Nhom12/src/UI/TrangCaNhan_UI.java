package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Dao.NhanVien_Dao;
import Entity.NhanVien;

import java.awt.Panel;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class TrangCaNhan_UI extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainUI main;
	private Color componentColor = Color.decode("#424242");
	private Font fontText;
	private JFrame frameEditTrangCaNhan;
	private JTextField txtName;
	private JTextField txtMa;
	private JComboBox<String> cmbGioiTinh, cmbChucVu;
	private JXDatePicker dpNgaySinh, dpNgayVaoLam;
	private JTextField txtSoDt;
	private JTextField txtEmail;
	private JTextField txtSoCCCD;
	private JTextField txtNgayVaoLam;
	private RoundedButton btnLogOut, btnEditInfo, btnSave, btnCannelEdit;
	private boolean gioiTinhCheck = true;
	private JTextField txtDiaChi;
	private NhanVien_Dao nv_dao = new NhanVien_Dao();

	public TrangCaNhan_UI(MainUI main) {
		this.main = main;
		fontText = main.roboto_regular.deriveFont(Font.PLAIN, 16F);

		setLayout(new BorderLayout(0, 0));

		JPanel pnlTitile = new JPanel();
		pnlTitile.setBorder(new EmptyBorder(5, 0, 10, 0));
		pnlTitile.setBackground(new Color(255, 255, 255));
		add(pnlTitile, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("TRANG CÁ NHÂN");
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitile.add(lblTitle);

		JPanel pnlBodyTrangCaNhan = new JPanel();
		pnlBodyTrangCaNhan.setBackground(new Color(255, 255, 255));
		pnlBodyTrangCaNhan.setBorder(new EmptyBorder(0, 20, 0, 20));
		add(pnlBodyTrangCaNhan);
		pnlBodyTrangCaNhan.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel pnlIfnormation = new JPanel();
		pnlIfnormation.setBackground(new Color(255, 255, 255));
		pnlBodyTrangCaNhan.add(pnlIfnormation);

		TitledBorder titleBorder = BorderFactory
				.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Thông tin cá nhân");
		pnlIfnormation.setBorder(
				BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 19F));
		pnlIfnormation.setLayout(new BoxLayout(pnlIfnormation, BoxLayout.X_AXIS));

		JPanel pnlBox_left = new JPanel();
		pnlBox_left.setBackground(new Color(255, 255, 255));
		pnlIfnormation.add(pnlBox_left);
		pnlBox_left.setLayout(new GridLayout(11, 1, 0, 0));

		JLabel lblName = new JLabel("Họ Tên");
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblName);

		JLabel lblMa = new JLabel("Mã NV");
		lblMa.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblMa);

		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblGioiTinh);

		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		lblNgaySinh.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblNgaySinh);

		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblSoDienThoai);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblEmail);

		JLabel lblSoCCCD = new JLabel("SỐ CCCD");
		lblSoCCCD.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblSoCCCD);

		JLabel lblNgayVaoLam = new JLabel("Ngày vào làm");
		lblNgayVaoLam.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblNgayVaoLam);

		JLabel lblChucVu = new JLabel("Chức vụ");
		lblChucVu.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblChucVu);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblDiaChi);

		JPanel pngBox_right = new JPanel();
		pngBox_right.setBackground(new Color(255, 255, 255));
		pnlIfnormation.add(pngBox_right);
		pngBox_right.setLayout(new GridLayout(11, 2, 0, 0));

		txtName = new JTextField();
		pngBox_right.add(txtName);
		txtName.setColumns(10);

		txtMa = new JTextField();
		pngBox_right.add(txtMa);

		txtMa.setEditable(false);
		txtMa.setColumns(10);

		cmbGioiTinh = new JComboBox<>();
		cmbGioiTinh.addItem("nam");
		cmbGioiTinh.addItem("nữ");
		pngBox_right.add(cmbGioiTinh);

		dpNgaySinh = new JXDatePicker(new Date(100, 0, 1));
		dpNgaySinh.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dpNgaySinh.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dpNgaySinh.setLocale(new Locale("vi", "VN"));
		dpNgaySinh.getMonthView().setZoomable(true);

		pngBox_right.add(dpNgaySinh);

		txtSoDt = new JTextField();
		pngBox_right.add(txtSoDt);
		txtSoDt.setColumns(10);

		txtEmail = new JTextField();
		pngBox_right.add(txtEmail);
		txtEmail.setColumns(10);

		txtSoCCCD = new JTextField();
		pngBox_right.add(txtSoCCCD);
		txtSoCCCD.setColumns(10);

		dpNgayVaoLam = new JXDatePicker(new Date(100, 0, 1));
		dpNgayVaoLam.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dpNgayVaoLam.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dpNgayVaoLam.setLocale(new Locale("vi", "VN"));
		dpNgayVaoLam.getMonthView().setZoomable(true);

		dpNgayVaoLam.setEditable(false);
		dpNgayVaoLam.setEnabled(false);

		pngBox_right.add(dpNgayVaoLam);

		cmbChucVu = new JComboBox<>();
		cmbChucVu.addItem("Quản lý");
		cmbChucVu.addItem("Nhân viên");
		cmbChucVu.setEditable(false);
		cmbChucVu.setEnabled(false);
		pngBox_right.add(cmbChucVu);

		txtDiaChi = new JTextField();
		pngBox_right.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		JPanel pnlControl = new JPanel();
		pnlBodyTrangCaNhan.add(pnlControl);
		pnlControl.setLayout(new BorderLayout(0, 0));

		JPanel pnlBoxInfor = new JPanel();
		pnlBoxInfor.setBackground(new Color(255, 255, 255));
		pnlBoxInfor.setBorder(new EmptyBorder(10, 30, 20, 30));
		pnlControl.add(pnlBoxInfor);
		pnlBoxInfor.setLayout(new BorderLayout(0, 10));

		JLabel lblAvatar = new JLabel("");
		lblAvatar.setIcon(new ImageScaler("/image/image_account.jpg", 340, 340).getScaledImageIcon());
		lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
		pnlBoxInfor.add(lblAvatar);

		Panel panel_1 = new Panel();
		pnlBoxInfor.add(panel_1, BorderLayout.SOUTH);

		btnEditInfo = new RoundedButton("Cập nhật thông tin cá nhân", null, 15, 0, 2f);
		btnEditInfo.setOpaque(false);
		btnEditInfo.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnEditInfo.setForeground(Color.decode("#FFFFFF"));
		btnEditInfo.setBackground(Color.decode("#424242"));
		btnEditInfo.setIcon(new ImageScaler("/image/icon_edit.png", 25, 25).getScaledImageIcon());
		btnEditInfo.setVisible(true);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(btnEditInfo);

		btnSave = new RoundedButton("Lưu", null, 15, 0, 2f);
		btnSave.setVisible(false);
		panel_1.add(btnSave);

		btnCannelEdit = new RoundedButton("Hủy", null, 15, 0, 2f);
		btnCannelEdit.setVisible(false);
		panel_1.add(btnCannelEdit);

		EditTrangCaNhan(false);

		Panel pnlHandle = new Panel();
		pnlHandle.setBackground(new Color(255, 255, 255));
		pnlControl.add(pnlHandle, BorderLayout.SOUTH);
		pnlHandle.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 5));

		JLabel lblVersion = new JLabel("Version 2.1.0");
		lblVersion.setFont(main.roboto_regular.deriveFont(Font.ITALIC, 17F));
		pnlHandle.add(lblVersion);

		btnLogOut = new RoundedButton("ĐĂNG XUẤT", null, 15, 0, 2f);
		btnLogOut.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setBackground(new Color(186, 81, 69));
		btnLogOut.setIcon(new ImageScaler("/image/icon_logout.png", 30, 30).getScaledImageIcon());
		btnLogOut.setVisible(true);

		pnlHandle.add(btnLogOut);

		btnLogOut.addActionListener(this);
		btnEditInfo.addActionListener(this);
		btnCannelEdit.addActionListener(this);
		cmbGioiTinh.addActionListener(this);
		btnSave.addActionListener(this);

		lblAvatar.setIcon(new ImageScaler(main.nv.getNhanVien().getHinhAnh(), 340, 340).getScaledImageAvatar());

		txtName.setText(main.nv.getNhanVien().getHoTen());
		txtMa.setText(main.nv.getNhanVien().getMaNV());
		cmbGioiTinh.setSelectedItem(main.nv.getNhanVien().isGioiTinh() ? 0 : 1);

		dpNgaySinh.setDate(main.nv.getNhanVien().getNgaySinh());
		txtSoDt.setText(main.nv.getNhanVien().getSdt());
		txtEmail.setText(main.nv.getNhanVien().getEmail());
		txtSoCCCD.setText(main.nv.getNhanVien().getcCCD());
		dpNgayVaoLam.setDate(main.nv.getNgayCongTac());

		cmbChucVu.setSelectedItem(main.nv.getChucVu());

		txtDiaChi.setText(main.nv.getNhanVien().getDiaChi());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		main.music.playSE(2);
		if (o == btnLogOut) {
			main.music.playSE(3);
			int result = JOptionPane.showConfirmDialog(main, "Bạn có muốn đăng xuất khỏi hệ thống?",
					"ĐĂNG XUẤT HỆ THỐNG", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				main.dispose();
				Login_UI loginUi = new Login_UI(main);
				loginUi.setVisible(true);
			} else if (result == JOptionPane.NO_OPTION) {

			}
		}
		if (o == btnEditInfo) {
			btnEditInfo.setVisible(false);
			btnSave.setVisible(true);
			btnCannelEdit.setVisible(true);
			EditTrangCaNhan(true);
		}
		if (o == btnCannelEdit) {
			btnEditInfo.setVisible(true);
			btnSave.setVisible(false);
			btnCannelEdit.setVisible(false);
			EditTrangCaNhan(false);
		}
		if (o == cmbGioiTinh) {
			String selectGT = (String) cmbGioiTinh.getSelectedItem();
			if (selectGT.equals("Nam")) {
				gioiTinhCheck = true;
			} else if (selectGT.equals("Nữ")) {
				gioiTinhCheck = false;
			}
		}
		if(o==btnSave) {
			capNhatTT();
		}

	}

	public void EditTrangCaNhan(boolean editer) {
		if (editer == true) {
			txtName.setEditable(true);
			cmbGioiTinh.setEnabled(true);
			dpNgaySinh.setEditable(true);
			dpNgaySinh.setEnabled(true);
			txtSoDt.setEditable(true);
			txtEmail.setEditable(true);
			txtSoCCCD.setEditable(true);
			txtDiaChi.setEditable(true);
		} else {
			txtName.setEditable(false);

			cmbGioiTinh.setEnabled(false);
			dpNgaySinh.setEditable(false);
			dpNgaySinh.setEnabled(false);
			txtSoDt.setEditable(false);
			txtEmail.setEditable(false);
			txtSoCCCD.setEditable(false);
			txtDiaChi.setEditable(false);
		}
	}

	// hàm convert data trang ca nhan
	private NhanVien convertDataToNhanVien() {
		String maNV = txtMa.getText();
		String hoTen = txtName.getText();
		Boolean gioiTinh = gioiTinhCheck;
		Date ngaySinh = dpNgaySinh.getDate();
		String sdt = txtSoDt.getText();
		String email = txtEmail.getText();
		String soCCCD = txtSoCCCD.getText();
		String diaChi = txtDiaChi.getText();
		
		String mk =main.nv.getNhanVien().getMatKhau();
		String hinhAnh = main.nv.getNhanVien().getHinhAnh();
		return new NhanVien(maNV, mk ,hoTen, gioiTinh, ngaySinh, sdt, email, soCCCD, diaChi, hinhAnh);
	}
	// HÀM CẬP NHẬT THÔNG TIN
	private void capNhatTT() {
		if(validDataNV()) {
			NhanVien nvNew = convertDataToNhanVien();
			if(nvNew!=null) {
				if(nv_dao.suaThongTinNhanVien(nvNew)) {
					alertSuccess("Cập nhật thành công");
				}else {
					alertNotification("Cập nhật thất bại! Không tồn tại Nhân Viên");
				}
			}
		}
	}
	
	// HÀM VALID CẬP NHẬT
	private boolean validDataNV() {
		String maNV = txtMa.getText();
		String hoTen = txtName.getText();
		Date ngaySinh = dpNgaySinh.getDate();
		String sdt = txtSoDt.getText();
		String email = txtEmail.getText();
		String soCCCD = txtSoCCCD.getText();

		if (!maNV.matches("\\S+") || !maNV.matches("^NV\\d{5}$")) {
			alertNotification("Mã nhân viên phải có dạng: NV12345!");
			return false;
		}
		
		if (hoTen == null && hoTen.trim().length() <= 0) {
			alertNotification("Tên nhân viên không được để trống!");
			txtName.selectAll();
			txtName.requestFocus();
			return false;
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -18); // trừ 18 năm kể từ hiện tại
		Date eighteenYearsAgo = cal.getTime();
		if (ngaySinh.compareTo(eighteenYearsAgo) > 0) {
			alertNotification("Nhân viên phải đủ 18 tuổi trở lên!");
			return false;
		}
		if (!sdt.matches("^(\\+84|84|0)\\d{9}$")) {
			alertNotification("Số điện thoại không được rỗng và bắt đầu 0, 84!");
			txtSoDt.selectAll();
			txtSoDt.requestFocus();
			return false;
		}
		if (!email.matches("^([a-zA-Z0-9]){5,}@([a-zA-Z0-9])+\\.com$")) {
			alertNotification("Email phải có dạng abcde@domain.com!");
			txtEmail.selectAll();
			txtEmail.requestFocus();
			return false;
		}
		if (!soCCCD.matches("^\\d{12}$")) {
			alertNotification("Căn cước công dân chỉ gồm 12 chữ số!");
			txtSoCCCD.selectAll();
			txtSoCCCD.requestFocus();
			return false;
		}
		return true;
	}

	// ALERT

	public int alertNotification(String textError) {
		main.music.playSE(3);
		String[] options = { "Cancel" };
		int result = JOptionPane.showOptionDialog(main, textError, "NOTIFICATION", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		return result;
	}

	public int alertSuccess(String textError) {
		main.music.playSE(1);
		String[] options = { "Cancel" };
		int result = JOptionPane.showOptionDialog(main, textError, "NOTIFICATION", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		return result;
	}

}
