package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import net.sf.jasperreports.engine.JRException;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import javax.swing.JRadioButton;

public class QuanLyNhanVienUI extends JPanel implements ActionListener, MouseListener{
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField txtMaNV, txtMatKhau, txtTenKH, txtGiaTri, txtEmail, txtGhiChu;
	private RoundedButton btnThem, btnSua, btnXoa, btnLuu, btnHuy, btnIn,btnFocus;
	private DefaultTableModel dtblModel;
	private JTable tblHD;
	private JTableHeader tbhHD;
	private JPanel pnlChucNang;
	private JXDatePicker dtpNgaySinh;
	private JTextField txtCCCD;
	private JTextField txtDiaChi;
	private Font fontText;
	/**
	 * Create the panel.
	 */
	public QuanLyNhanVienUI(MainUI main) {
		this.main = main;
		fontText = main.roboto_regular.deriveFont(Font.PLAIN, 16F);
		
		//set gia tri cho jpanel NhanVien
		setLayout(new BorderLayout(0, 0));
		setBackground(bgColor);
		
		//tao jpanel chua Title va Thong tin NV
		JPanel pnNorth = new JPanel();
		pnNorth.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		pnNorth.setBackground(bgColor);
		add(pnNorth, BorderLayout.NORTH);
		pnNorth.setLayout(new BorderLayout(0, 0));
		
		//Tao jpanel Title
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(bgColor);
		pnNorth.add(pnTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnTitle.add(lblTitle);
		
		//Tao jpanel Thong tin nhan vien
		JPanel pnThongTinNV = new JPanel();
		pnThongTinNV.setLayout(new BoxLayout(pnThongTinNV, BoxLayout.Y_AXIS));
		pnThongTinNV.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Thông tin nhân viên");
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnThongTinNV.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		pnNorth.add(pnThongTinNV, BorderLayout.CENTER);
		
		// Tao box chua cac phan tu
		Box b1 = Box.createHorizontalBox();
		pnThongTinNV.add(b1);
		
		JLabel lblMaNV = new JLabel("Mã NV:");
		lblMaNV.setFont(fontText);
		lblMaNV.setForeground(textColor);
		b1.add(lblMaNV);
		b1.add(Box.createHorizontalStrut(10));
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(fontText);
		txtMaNV.setForeground(textColor);
		txtMaNV.setBackground(bgColor);
		txtMaNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		b1.add(txtMaNV);
		b1.add(Box.createHorizontalStrut(30));
		
		JLabel lblTenHD = new JLabel("Mật khẩu:");
		lblTenHD.setForeground(textColor);
		lblTenHD.setFont(fontText);
		b1.add(lblTenHD);
		b1.add(Box.createHorizontalStrut(10));
		
		txtMatKhau = new JTextField();
		txtMatKhau.setForeground(textColor);
		txtMatKhau.setFont(fontText);
		txtMatKhau.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMatKhau.setBackground(bgColor);
		b1.add(txtMatKhau);
		b1.add(Box.createHorizontalStrut(30));
		
		JLabel lblTenKH = new JLabel("Họ tên:");
		lblTenKH.setForeground(textColor);
		lblTenKH.setFont(fontText);
		b1.add(lblTenKH);
		b1.add(Box.createHorizontalStrut(10));
		
		txtTenKH = new JTextField();
		txtTenKH.setForeground(textColor);
		txtTenKH.setFont(fontText);
		txtTenKH.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtTenKH.setBackground(bgColor);
		b1.add(txtTenKH);
		b1.add(Box.createHorizontalStrut(30));
		
		JLabel lblDaiDien = new JLabel("Giới tính:");
		lblDaiDien.setForeground(textColor);
		lblDaiDien.setFont(fontText);
		b1.add(lblDaiDien);
		b1.add(Box.createHorizontalStrut(10));
		
		JRadioButton radNam = new JRadioButton("Nam");
		radNam.setSelected(true);
		radNam.setBackground(bgColor);
		radNam.setForeground(textColor);
		radNam.setFont(fontText);
		b1.add(radNam);
		
		JRadioButton radNu = new JRadioButton("Nữ");
		radNu.setBackground(bgColor);
		radNu.setForeground(textColor);
		radNu.setFont(fontText);
		b1.add(radNu);
		
		pnThongTinNV.add(Box.createVerticalStrut(20));
		
		//Tao box chua thong tin
		Box b2 = Box.createHorizontalBox();
		pnThongTinNV.add(b2);
	
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setForeground(textColor);
		lblNgaySinh.setFont(fontText);
		b2.add(lblNgaySinh);
		
		b2.add(Box.createHorizontalStrut(10));
		
		dtpNgaySinh = new JXDatePicker(new Date());
		dtpNgaySinh.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtpNgaySinh.setFont(fontText);
		dtpNgaySinh.setBackground(bgColor);
		dtpNgaySinh.setForeground(textColor);
		dtpNgaySinh.setLocale(new Locale("vi", "VN"));	// set thoi gian local la VN
		JButton btnDateBD = (JButton) dtpNgaySinh.getComponent(1);
		btnDateBD.setIcon(new ImageScaler("/image/calendar_icon.png", 18, 18).getScaledImageIcon());
		btnDateBD.setBackground(bgColor);
		btnDateBD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		dtpNgaySinh.getEditor().setBackground(bgColor);
		dtpNgaySinh.getEditor().setForeground(textColor);
		b2.add(dtpNgaySinh);
		
		b2.add(Box.createHorizontalStrut(30));
		
		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setForeground(textColor);
		lblSDT.setFont(fontText);
		b2.add(lblSDT);
		
		b2.add(Box.createHorizontalStrut(10));
		
		txtGiaTri = new JTextField();
		txtGiaTri.setForeground(textColor);
		txtGiaTri.setFont(fontText);
		txtGiaTri.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtGiaTri.setBackground(bgColor);
		b2.add(txtGiaTri);

		b2.add(Box.createHorizontalStrut(30));
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(textColor);
		lblEmail.setFont(fontText);
		b2.add(lblEmail);
		
		b2.add(Box.createHorizontalStrut(10));
		
		txtEmail = new JTextField();
		txtEmail.setForeground(textColor);
		txtEmail.setFont(fontText);
		txtEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtEmail.setBackground(bgColor);
		b2.add(txtEmail);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(30);
		b2.add(horizontalStrut_2);
		
		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setForeground(Color.BLACK);
		lblCCCD.setFont(fontText);
		b2.add(lblCCCD);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		b2.add(horizontalStrut_1);
		
		txtCCCD = new JTextField();
		txtCCCD.setForeground(Color.BLACK);
		txtCCCD.setFont(fontText);
		txtCCCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 

						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtCCCD.setBackground(Color.WHITE);
		b2.add(txtCCCD);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(30);
		b2.add(horizontalStrut_4);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setForeground(Color.BLACK);
		lblDiaChi.setFont(fontText);
		b2.add(lblDiaChi);
		
		Component horizontalStrut_1_1 = Box.createHorizontalStrut(10);
		b2.add(horizontalStrut_1_1);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setForeground(Color.BLACK);
		txtDiaChi.setFont(fontText);
		txtDiaChi.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 


								BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtDiaChi.setBackground(Color.WHITE);
		b2.add(txtDiaChi);
		
		pnThongTinNV.add(Box.createVerticalStrut(20));
		
		//Tạo box chứa thông tin
		Box b3 = Box.createHorizontalBox();
		pnThongTinNV.add(b3);
		
		JLabel lblHinhAnh = new JLabel("Hình ảnh:");
		lblHinhAnh.setForeground(Color.BLACK);
		lblHinhAnh.setFont(fontText);
		b3.add(lblHinhAnh);
		
//		JFileChooser fileAnh = new JFileChooser();
//		b3.add(fileAnh);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		b3.add(horizontalStrut);
		
		RoundedButton btnChonAnh = new RoundedButton("Image", null, 5, 0, 1f);
		btnChonAnh.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnChonAnh.setForeground(Color.WHITE);
		btnChonAnh.setBackground(componentColor);
		btnChonAnh.setIcon(new ImageScaler("/image/add-image.png", 16, 16).getScaledImageIcon());
		b3.add(btnChonAnh);
		
		b3.add(Box.createHorizontalStrut(30));
		Border cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JLabel lblGhiChu = new JLabel("Ghi chú:");
		lblGhiChu.setForeground(textColor);
		lblGhiChu.setFont(fontText);
		b3.add(lblGhiChu);
		
		b3.add(Box.createHorizontalStrut(10));
		
		txtGhiChu = new JTextField();
		txtGhiChu.setForeground(textColor);
		txtGhiChu.setFont(fontText);
		txtGhiChu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtGhiChu.setBackground(bgColor);
		b3.add(txtGhiChu);
		
		pnThongTinNV.add(Box.createVerticalStrut(10));
		
		//Khởi tạo jpanel chức năng chứa các button chức năng: thêm, sửa, xóa, lưu, hủy
		pnlChucNang = new JPanel();
		pnNorth.add(pnlChucNang, BorderLayout.SOUTH);
		pnlChucNang.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlChucNang.setBackground(bgColor);
		pnlChucNang.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		btnThem = new RoundedButton("Thêm", null, 20, 0, 1.0f);
		btnThem.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(Color.decode("#3B71CA"));
		btnThem.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnThem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnThem);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnSua = new RoundedButton("Sửa", null, 20, 0, 1.0f);
		btnSua.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnSua.setForeground(Color.WHITE);
		btnSua.setBackground(Color.decode("#ffc107"));
		btnSua.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnSua.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnSua);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnXoa = new RoundedButton("Xóa", null, 20, 0, 1.0f);
		btnXoa.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(Color.decode("#dc3545"));
		btnXoa.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoa);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnIn = new RoundedButton("Xuất", null, 20, 0, 1.0f);
		btnIn.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnIn.setForeground(Color.WHITE);
		btnIn.setBackground(Color.decode("#17a2b8"));
		btnIn.setIcon(new ImageScaler("/image/printer_icon.png", 24, 24).getScaledImageIcon());
		btnIn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnIn);
		
		pnlChucNang.add(Box.createHorizontalStrut(200));
		
		btnLuu = new RoundedButton("Lưu", null, 20, 0, 0.6f);
		btnLuu.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setBackground(Color.decode("#28a745"));
		btnLuu.setIcon(new ImageScaler("/image/save_Icon.png", 24, 24).getScaledImageIcon());
		btnLuu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnLuu);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnHuy = new RoundedButton("Hủy", null, 20, 0, 0.6f);
		btnHuy.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBackground(Color.decode("#ffc107"));
		btnHuy.setIcon(new ImageScaler("/image/cancelHD_Icon.png", 24, 24).getScaledImageIcon());
		btnHuy.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnHuy);
		
		// tạo jpanel chứa table nhân viên
		JPanel pnlBangNV = new JPanel();
		pnlBangNV.setLayout(new BoxLayout(pnlBangNV, BoxLayout.X_AXIS));
		add(pnlBangNV, BorderLayout.CENTER);
		String cols[] = {"Mã NV", "Họ tên", "Giới tính", "Ngày sinh", "SDT", "Email", "CCCD", "Địa chỉ", "Ghi chú"};
		dtblModel = new DefaultTableModel(cols, 0);
		tblHD = new JTable(dtblModel);
		
		tbhHD = new JTableHeader(tblHD.getColumnModel());
		tbhHD.setReorderingAllowed(false);
		tbhHD.setBackground(componentColor);
		tbhHD.setForeground(Color.WHITE);
		tbhHD.setFont(fontText);
		tblHD.setTableHeader(tbhHD);
		
		tblHD.setRowHeight(20);
		tblHD.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblHD.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblHD.getColumnModel().getColumn(2).setPreferredWidth(175);
		tblHD.getColumnModel().getColumn(3).setPreferredWidth(175);
		tblHD.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblHD.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblHD.getColumnModel().getColumn(6).setPreferredWidth(200);
		tblHD.getColumnModel().getColumn(7).setPreferredWidth(100);
		tblHD.getColumnModel().getColumn(7).setPreferredWidth(150);
		
		//Tạo jscrollpane để tạo scroll cho bảng nhân viên
		JScrollPane scrHD = new JScrollPane(tblHD,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNV.add(scrHD);
		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnIn.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		
		btnThem.addMouseListener(this);
		btnSua.addMouseListener(this);
		btnXoa.addMouseListener(this);
		btnIn.addMouseListener(this);
		btnLuu.addMouseListener(this);
		btnHuy.addMouseListener(this);
		
		//Không thể thao tác với button lưu và hủy
		displayButtonSaveAndCancel(false);
		
		//Không thể chỉnh sửa txt
		setEditableForTextField(false);
		
		//Set giá trị mặc định để hiển thị
		txtMaNV.setText("NV12345");
		txtTenKH.setText("Nguyễn Văn Phong");
		txtMatKhau.setText("12345abc@");
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {

	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		if (o instanceof RoundedButton) {
	    }
	}
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == btnThem) {
			displayButtonSaveAndCancel(true);
			setEditableForTextField(true);
			xoaRong();
			
		}
		if(o == btnSua) {
			displayButtonSaveAndCancel(true);
			setEditableForTextField(true);
			
		}
		if(o == btnXoa) {
			
		}
		if(o == btnIn) {
		}
		if(o == btnLuu) {
			displayButtonSaveAndCancel(false);
			setEditableForTextField(false);
			
		}
		if(o == btnHuy) {
			displayButtonSaveAndCancel(false);
			setEditableForTextField(false);
			
		}
	}
	private void displayButtonSaveAndCancel(boolean display) {
		if(display == true) {
			btnLuu.setEnabled(true);
			btnLuu.setAlpha(1f);
			btnHuy.setEnabled(true);
			btnHuy.setAlpha(1f);
			
			btnThem.setEnabled(false);
			btnThem.setAlpha(0.6f);
			btnSua.setEnabled(false);
			btnSua.setAlpha(0.6f);
			btnXoa.setEnabled(false);
			btnXoa.setAlpha(0.6f);
			btnIn.setEnabled(false);
			btnIn.setAlpha(0.6f);
			
		}else {
			btnLuu.setEnabled(false);
			btnLuu.setAlpha(0.6f);
			btnHuy.setEnabled(false);
			btnHuy.setAlpha(0.6f);
			
			btnThem.setEnabled(true);
			btnThem.setAlpha(1f);
			btnSua.setEnabled(true);
			btnSua.setAlpha(1f);
			btnXoa.setEnabled(true);
			btnXoa.setAlpha(1f);
			btnIn.setEnabled(true);
			btnIn.setAlpha(1f);
		}
	}
	private void setEditableForTextField(boolean edit) {
		if(edit == true) {
			txtMaNV.setEditable(true);
			txtMatKhau.setEditable(true);
			txtTenKH.setEditable(true);
			dtpNgaySinh.setEditable(true);
			txtGiaTri.setEditable(true);
			txtEmail.setEditable(true);
			txtGhiChu.setEditable(true);
		}else {
			txtMaNV.setEditable(false);
			txtMatKhau.setEditable(false);
			txtTenKH.setEditable(false);
			dtpNgaySinh.setEditable(false);
			txtGiaTri.setEditable(false);
			txtEmail.setEditable(false);
			txtGhiChu.setEditable(false);
		}
	}
	private void xoaRong() {
		txtMaNV.setText("");
		txtMatKhau.setText("");
		txtTenKH.setText("");
		dtpNgaySinh.setDate(new Date());
		txtGiaTri.setText("");
		txtEmail.setText("");
		txtGhiChu.setText("");
	}
}
