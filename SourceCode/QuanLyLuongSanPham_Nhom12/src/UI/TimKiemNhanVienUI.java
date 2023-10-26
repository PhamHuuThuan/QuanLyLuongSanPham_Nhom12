package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.CustomComboBoxUI;
import CustomUI.CustomListCellRenderer;
import CustomUI.ImageScaler;
import CustomUI.RoundedButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;

import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class TimKiemNhanVienUI extends JPanel implements ActionListener, MouseListener{
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField txtMaHD, txtMaSP, txtTenSP, txtDonGia;
	private RoundedButton btnTim, btnXoaRong, btnFocus;
	private DefaultTableModel dtblModelSP;
	private JTable tblSP;
	private JTableHeader tbhSP;
	private JPanel pnlChucNang;
	private JTextField txtMaNVS, txtMaNV, txtMatKhau, txtTenNV, txtSDT, txtEmail, txtCCCD, txtDiaChi, txtGhiChu;
	private JTextField txtTenNVS;
	private JTextField txtSDTS;
	private JTextField txtCCCDS;
	private JTextField txtDiaChiS;
	private JXDatePicker dtpNgaySinh, dtpNgaySinhS;
	private JTextField txtChucVu;
	/**
	 * Create the panel.
	 */
	public TimKiemNhanVienUI(MainUI main) {
		this.main = main;
		
		//set gia tri cho jpanel SanPham
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(bgColor);
		
		JPanel pnlSanPham = new JPanel();
		add(pnlSanPham, BorderLayout.CENTER);
		pnlSanPham.setLayout(new BorderLayout(0, 0));
		pnlSanPham.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pnlSanPham.setBackground(bgColor);
		
		//tao jpanel chua Title va Thong tin san pham
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlNorth.setBackground(bgColor);
		pnlSanPham.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BorderLayout(0, 0));
		
		//Tao jpanel Title
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		pnlTitle.setBackground(bgColor);
		pnlNorth.add(pnlTitle, BorderLayout.NORTH);
		
		//Tiêu đề
		JLabel lblTitle = new JLabel("TÌM KIẾM NHÂN VIÊN");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle.add(lblTitle);
		
		//Tạo hộp thoại tìm kiếm
		Box b = Box.createHorizontalBox();
		b.setBackground(bgColor);
		pnlNorth.add(b, BorderLayout.CENTER);
		
		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setLayout(new BoxLayout(pnlTimKiem, BoxLayout.Y_AXIS));
		pnlTimKiem.setBackground(bgColor);
		TitledBorder titleBorderTTSP = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Tìm kiếm");
		titleBorderTTSP.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlTimKiem.setBorder(BorderFactory.createCompoundBorder(titleBorderTTSP, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		pnlTimKiem.setPreferredSize(new Dimension(500, 320));
		pnlNorth.add(pnlTimKiem, BorderLayout.WEST);
		b.add(Box.createHorizontalStrut(30));
		
		JPanel pnlB1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB1.setBackground(bgColor);
		pnlTimKiem.add(pnlB1);
		
		JLabel lblMaNVS = new JLabel("Mã NV:");
		lblMaNVS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblMaNVS.setForeground(textColor);
		pnlB1.add(lblMaNVS);
		
		txtMaNVS = new JTextField();
		txtMaNVS.setColumns(8);
		txtMaNVS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaNVS.setForeground(textColor);
		txtMaNVS.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
						BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtMaNVS.setBackground(Color.WHITE);
		pnlB1.add(txtMaNVS);
		
		pnlB1.add(Box.createHorizontalStrut(15));
		
		JLabel lblTenS = new JLabel("Họ tên:");
		lblTenS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblTenS.setForeground(textColor);
		pnlB1.add(lblTenS);
		
		txtTenNVS = new JTextField();
		txtTenNVS.setColumns(8);
		txtTenNVS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenNVS.setForeground(textColor);
		txtTenNVS.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
						BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtTenNVS.setBackground(Color.WHITE);
		pnlB1.add(txtTenNVS);
		
		pnlTimKiem.add(Box.createVerticalStrut(10));
		
		JPanel pnlB2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB2.setBackground(bgColor);
		pnlTimKiem.add(pnlB2);
		
		JLabel lblGioiTinhS = new JLabel("Giới tính:");
		lblGioiTinhS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblGioiTinhS.setForeground(textColor);
		pnlB2.add(lblGioiTinhS);
		
		JRadioButton rbtnNamS = new JRadioButton("Nam");
		rbtnNamS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		rbtnNamS.setForeground(textColor);
		rbtnNamS.setBackground(bgColor);
		pnlB2.add(rbtnNamS);
		pnlB2.add(Box.createHorizontalStrut(5));
		
		JRadioButton rbtnNuS = new JRadioButton("Nữ");
		rbtnNuS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		rbtnNuS.setForeground(textColor);
		rbtnNuS.setBackground(bgColor);
		pnlB2.add(rbtnNuS);
		
		pnlB2.add(Box.createHorizontalStrut(15));
		
		JLabel lblNgaySinhS = new JLabel("Ngày sinh:");
		lblNgaySinhS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblNgaySinhS.setForeground(textColor);
		pnlB2.add(lblNgaySinhS);
		
		dtpNgaySinhS = new JXDatePicker(new Date());
		dtpNgaySinhS.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtpNgaySinhS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dtpNgaySinhS.setBackground(bgColor);
		dtpNgaySinhS.setForeground(textColor);
		dtpNgaySinhS.setLocale(new Locale("vi", "VN"));	// set thoi gian local la VN
		JButton btnDateBD = (JButton) dtpNgaySinhS.getComponent(1);
		btnDateBD.setIcon(new ImageScaler("/image/calendar_icon.png", 18, 18).getScaledImageIcon());
		btnDateBD.setBackground(bgColor);
		btnDateBD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		dtpNgaySinhS.getEditor().setBackground(bgColor);
		dtpNgaySinhS.getEditor().setForeground(textColor);
		pnlB2.add(dtpNgaySinhS);
		
		pnlTimKiem.add(Box.createVerticalStrut(10));
		
		JPanel pnlB3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB3.setBackground(bgColor);
		pnlTimKiem.add(pnlB3);
		
		JLabel lblSDTS = new JLabel("SĐT:");
		lblSDTS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblSDTS.setForeground(textColor);
		pnlB3.add(lblSDTS);
		
		txtSDTS = new JTextField();
		txtSDTS.setColumns(8);
		txtSDTS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtSDTS.setForeground(textColor);
		txtSDTS.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
								BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtSDTS.setBackground(Color.WHITE);
		pnlB3.add(txtSDTS);
		
		pnlB3.add(Box.createHorizontalStrut(15));
		
		JLabel lblPBS = new JLabel("Phòng ban:");
		lblPBS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblPBS.setForeground(textColor);
		pnlB3.add(lblPBS);
		
		JComboBox cmbPhongBanS = new JComboBox();
		cmbPhongBanS.setModel(new DefaultComboBoxModel(new String[] {"Nhân sự", "Kế toán"}));
		Border cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbPhongBanS.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbPhongBanS.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbPhongBanS.setBackground(bgColor);
		cmbPhongBanS.setForeground(textColor);
		cmbPhongBanS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		cmbPhongBanS.setPreferredSize(new Dimension(120, 30));
		pnlB3.add(cmbPhongBanS);
		
		pnlTimKiem.add(Box.createVerticalStrut(10));
		
		JPanel pnlB4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB4.setBackground(bgColor);
		pnlTimKiem.add(pnlB4);

		pnlTimKiem.add(Box.createVerticalStrut(20));
		
		JLabel lblCCCDS = new JLabel("CCCD:");
		lblCCCDS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblCCCDS.setForeground(textColor);
		pnlB4.add(lblCCCDS);
		
		txtCCCDS = new JTextField();
		txtCCCDS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtCCCDS.setForeground(textColor);
		txtCCCDS.setColumns(8);
		txtCCCDS.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
								BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtCCCDS.setBackground(bgColor);
		pnlB4.add(txtCCCDS);
		pnlB4.add(Box.createHorizontalStrut(15));
		
		JLabel lblDiaChiS = new JLabel("Địa chỉ:");
		lblDiaChiS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblDiaChiS.setForeground(textColor);
		pnlB4.add(lblDiaChiS);
		
		txtDiaChiS = new JTextField();
		txtDiaChiS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtDiaChiS.setForeground(textColor);
		txtDiaChiS.setColumns(8);
		txtDiaChiS.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
								BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtDiaChiS.setBackground(Color.WHITE);
		pnlB4.add(txtDiaChiS);

		lblMaNVS.setPreferredSize(lblGioiTinhS.getPreferredSize());
		lblSDTS.setPreferredSize(lblGioiTinhS.getPreferredSize());
		lblCCCDS.setPreferredSize(lblGioiTinhS.getPreferredSize());
	
		lblTenS.setPreferredSize(lblPBS.getPreferredSize());
		lblNgaySinhS.setPreferredSize(lblNgaySinhS.getPreferredSize());
		lblDiaChiS.setPreferredSize(lblPBS.getPreferredSize());
		
		//Tao jpanel Thong tin nhan vien
				JPanel pnThongTinNV = new JPanel();
				pnThongTinNV.setLayout(new BorderLayout());
				pnThongTinNV.setBackground(bgColor);
				TitledBorder titleBorder = BorderFactory.createTitledBorder(
		                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Thông tin nhân viên");
				titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
				pnThongTinNV.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(20, 50, 20, 50)));
				b.add(pnThongTinNV);
				
				JPanel pnlAnhDaiDien = new JPanel();
				pnlAnhDaiDien.setLayout(new BoxLayout(pnlAnhDaiDien, BoxLayout.Y_AXIS));
				pnlAnhDaiDien.setBackground(bgColor);
				pnThongTinNV.add(pnlAnhDaiDien, BorderLayout.WEST);
				
				JLabel lblAnh = new JLabel("");
				lblAnh.setPreferredSize(new Dimension(75, 125));
				lblAnh.setIcon(new ImageScaler("/image/team_icon.png", 75, 125).getScaledImageIcon());
				lblAnh.setBorder(BorderFactory.createLineBorder(componentColor));
				pnlAnhDaiDien.add(lblAnh, BorderLayout.CENTER);
				
				txtMaNV = new JTextField();
				txtMaNV.setHorizontalAlignment(SwingConstants.CENTER);
				txtMaNV.setColumns(5);
				pnlAnhDaiDien.add(txtMaNV, BorderLayout.SOUTH);
				pnlAnhDaiDien.add(Box.createVerticalStrut(100));
				txtMaNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				txtMaNV.setForeground(textColor);
				txtMaNV.setBackground(bgColor);
				txtMaNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
				
				
				JPanel pnlTTRight = new JPanel();
				pnlTTRight.setLayout(new BoxLayout(pnlTTRight, BoxLayout.Y_AXIS));
				pnlTTRight.setBackground(bgColor);
				pnThongTinNV.add(pnlTTRight, BorderLayout.CENTER);
				
				JPanel pnlB5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnlB5.setBackground(bgColor);
				pnlTTRight.add(pnlB5);
				
				JLabel lblTenNV = new JLabel("Họ tên:");
				lblTenNV.setForeground(textColor);
				lblTenNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				pnlB5.add(lblTenNV);
				
				txtTenNV = new JTextField();
				txtTenNV.setColumns(8);
				txtTenNV.setForeground(textColor);
				txtTenNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				txtTenNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
				txtTenNV.setBackground(bgColor);
				pnlB5.add(txtTenNV);
				pnlB5.add(Box.createHorizontalStrut(20));
				
				JLabel lblTenHD = new JLabel("Mật khẩu:");
				pnlB5.add(lblTenHD);
				lblTenHD.setForeground(textColor);
				lblTenHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				
				txtMatKhau = new JTextField();
				pnlB5.add(txtMatKhau);
				pnlB5.add(Box.createHorizontalStrut(20));
				txtMatKhau.setColumns(8);
				txtMatKhau.setForeground(textColor);
				txtMatKhau.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				txtMatKhau.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
				txtMatKhau.setBackground(bgColor);
				
				JLabel lblGioiTinh = new JLabel("Giới tính:");
				pnlB5.add(lblGioiTinh);
				lblGioiTinh.setForeground(textColor);
				lblGioiTinh.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				
				JRadioButton rbtnNamS_1 = new JRadioButton("Nam");
				rbtnNamS_1.setForeground(textColor);
				rbtnNamS_1.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				rbtnNamS_1.setBackground(Color.WHITE);
				pnlB5.add(rbtnNamS_1);
				pnlB5.add(Box.createHorizontalStrut(5));
				
				JRadioButton rbtnNuS_1 = new JRadioButton("Nữ");
				rbtnNuS_1.setForeground(textColor);
				rbtnNuS_1.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				rbtnNuS_1.setBackground(Color.WHITE);
				pnlB5.add(rbtnNuS_1);
				
				btnDateBD.setIcon(new ImageScaler("/image/calendar_icon.png", 18, 18).getScaledImageIcon());
				btnDateBD.setBackground(bgColor);
				btnDateBD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
						BorderFactory.createEmptyBorder(5, 5, 5, 5)));
				
				JPanel pnlB6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnlB6.setBackground(bgColor);
				pnlTTRight.add(pnlB6);
				
				JLabel lblNgaySinh = new JLabel("Ngày sinh:");
				lblNgaySinh.setForeground(textColor);
				lblNgaySinh.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				pnlB6.add(lblNgaySinh);
				
				dtpNgaySinh = new JXDatePicker(new Date());
				dtpNgaySinh.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
				dtpNgaySinh.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				dtpNgaySinh.setBackground(bgColor);
				dtpNgaySinh.setForeground(textColor);
				dtpNgaySinh.setLocale(new Locale("vi", "VN"));	// set thoi gian local la VN
				JButton btnDateSN = (JButton) dtpNgaySinh.getComponent(1);
				dtpNgaySinh.getEditor().setBackground(bgColor);
				dtpNgaySinh.getEditor().setForeground(textColor);
				pnlB6.add(dtpNgaySinh);
				pnlB6.add(Box.createHorizontalStrut(20));
								
				JLabel lblSDT = new JLabel("SĐT:");
				pnlB6.add(lblSDT);
				lblSDT.setForeground(textColor);
				lblSDT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				
				txtSDT = new JTextField();
				txtSDT.setColumns(8);
				pnlB6.add(txtSDT);
				txtSDT.setForeground(textColor);
				txtSDT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				txtSDT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
				txtSDT.setBackground(bgColor);
				
				pnlB6.add(Box.createHorizontalStrut(20));
				
				JLabel lblEmail = new JLabel("Email:");
				pnlB6.add(lblEmail);
				lblEmail.setForeground(textColor);
				lblEmail.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				
				txtEmail = new JTextField();
				txtEmail.setColumns(8);
				pnlB6.add(txtEmail);
				txtEmail.setForeground(textColor);
				txtEmail.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				txtEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
				txtEmail.setBackground(bgColor);
				
				JPanel pnlB7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnlB7.setBackground(bgColor);
				pnlTTRight.add(pnlB7);
				
				JLabel lblPhongBan = new JLabel("Phòng ban:");
				lblPhongBan.setForeground(textColor);
				lblPhongBan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				pnlB7.add(lblPhongBan);
				
				JComboBox cmbPhongBan = new JComboBox();
				cmbPhongBan.setModel(new DefaultComboBoxModel(new String[] {"Nhân sự", "Kế toán"}));
				cmbPhongBan.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
				cmbPhongBan.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
				cmbPhongBan.setBackground(bgColor);
				cmbPhongBan.setForeground(textColor);
				cmbPhongBan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				pnlB7.add(cmbPhongBan);
				pnlB7.add(Box.createHorizontalStrut(20));
				
				JLabel lblChucVu = new JLabel("Chức vụ:");
				pnlB7.add(lblChucVu);
				lblChucVu.setForeground(textColor);
				lblChucVu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				
				txtChucVu = new JTextField();
				txtChucVu.setColumns(8);
				pnlB7.add(txtChucVu);
				pnlB7.add(Box.createHorizontalStrut(20));
				txtChucVu.setForeground(textColor);
				txtChucVu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				txtChucVu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
										BorderFactory.createEmptyBorder(5, 10, 5, 10)));
				txtChucVu.setBackground(Color.WHITE);
				
				JLabel lblNgayVL = new JLabel("Ngày VL:");
				pnlB7.add(lblNgayVL);
				lblNgayVL.setForeground(textColor);
				lblNgayVL.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				
				JXDatePicker txtNgayVL = new JXDatePicker((Date) null);
				pnlB7.add(txtNgayVL);
				txtNgayVL.getEditor().setForeground(Color.BLACK);
				txtNgayVL.getEditor().setBackground(Color.WHITE);
				txtNgayVL.setLocale(new Locale("vi", "VN"));
				txtNgayVL.setForeground(Color.BLACK);
				txtNgayVL.setFont(null);
				txtNgayVL.setBackground(Color.WHITE);
				
				JPanel pnlB8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnlB8.setBackground(bgColor);
				pnlTTRight.add(pnlB8);
				
				JLabel lblGhiChu = new JLabel("Ghi chú:");
				lblGhiChu.setForeground(textColor);
				lblGhiChu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				pnlB8.add(lblGhiChu);
				
				txtGhiChu = new JTextField();
				txtGhiChu.setColumns(40);
				txtGhiChu.setForeground(textColor);
				txtGhiChu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
				txtGhiChu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
				txtGhiChu.setBackground(bgColor);
				pnlB8.add(txtGhiChu);
		
		//Khởi tạo jpanel chức năng chứa các button chức năng: thêm, sửa, xóa, lưu, hủy
		pnlChucNang = new JPanel();
		pnlTimKiem.add(pnlChucNang);
		pnlChucNang.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlChucNang.setBackground(bgColor);
		pnlChucNang.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		btnTim = new RoundedButton("Thêm", null, 20, 0, 1.0f);
		btnTim.setText("Tìm kiếm");
		btnTim.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnTim.setForeground(Color.WHITE);
		btnTim.setBackground(Color.decode("#3B71CA"));
		btnTim.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnTim.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnTim);
		pnlChucNang.add(Box.createHorizontalStrut(25));
		
		btnXoaRong = new RoundedButton("Sửa", null, 20, 0, 1.0f);
		btnXoaRong.setText("Xóa rỗng");
		btnXoaRong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setBackground(Color.decode("#ffc107"));
		btnXoaRong.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnXoaRong.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoaRong);
		
		// tạo jpanel chứa table sản phẩm
		JPanel pnlBangNV = new JPanel();
		titleBorderTTSP = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Danh sách nhân viên");
		titleBorderTTSP.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNV.setBorder(BorderFactory.createCompoundBorder(titleBorderTTSP, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		pnlBangNV.setLayout(new BoxLayout(pnlBangNV, BoxLayout.X_AXIS));
		pnlBangNV.setBackground(bgColor);
		pnlSanPham.add(pnlBangNV, BorderLayout.CENTER);
		
		String cols[] = {"Mã NV", "Họ tên", "Giới tính", "SĐT", "Địa chỉ", "Phòng ban", "Chức vụ", "Ngày công tác"};
		dtblModelSP = new DefaultTableModel(cols, 0);
		tblSP = new JTable(dtblModelSP);

		tbhSP = new JTableHeader(tblSP.getColumnModel());
		tbhSP.setReorderingAllowed(false);
		tbhSP.setBackground(componentColor);
		tbhSP.setForeground(Color.WHITE);
		tbhSP.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblSP.setTableHeader(tbhSP);
		
		tblSP.setRowHeight(20);
		tblSP.getColumnModel().getColumn(0).setPreferredWidth(75);
		tblSP.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblSP.getColumnModel().getColumn(2).setPreferredWidth(75);
		tblSP.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblSP.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblSP.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblSP.getColumnModel().getColumn(6).setPreferredWidth(100);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblSP,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNV.add(scrSP);
		
		pnlNorth.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		
		btnTim.addActionListener(this);
		btnXoaRong.addActionListener(this);
		
		btnTim.addMouseListener(this);
		btnXoaRong.addMouseListener(this);
		
		
		//Set giá trị mặc định để hiển thị
		
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
		if(o == btnTim) {
			
		}
		if(o == btnXoaRong) {

			
		}
	}
}
