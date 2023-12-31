package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.CustomComboBoxUI;
import CustomUI.CustomListCellRenderer;
import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Dao.NhanVien_Dao;
import Dao.PhanCongNhanVien_Dao;
import Dao.PhongBan_Dao;
import Entity.BangPhanCongNhanVien;
import Entity.NhanVien;
import Entity.PhongBan;
import Util.XuatExcel;
import Util.XuatPDF;
import net.sf.jasperreports.engine.JRException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Box;
import javax.swing.JTextField;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Dimension;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class TimKiemNhanVienUI extends JPanel implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private RoundedButton btnTim, btnXoaRong, btnIn, btnXuat, btnFocus;
	private DefaultTableModel dtbModelNV;
	private JTable tblNV;
	private JTableHeader tbhNV;
	private JPanel pnlChucNang;
	private JTextField txtMaNVS, txtMaNV, txtMatKhau, txtTenNV, txtSDT, txtEmail, txtCCCD, txtDiaChi, txtGhiChu;
	private JTextField txtTenNVS;
	private JTextField txtSDTS;
	private JTextField txtCCCDS;
	private JTextField txtDiaChiS;
	private JRadioButton rbtnNam, rbtnNu;
	private JCheckBox chkNamS, chkNuS;
	private JXDatePicker dtpNgaySinh, dtpNgaySinhS;
	private JTextField txtChucVu;
	private JComboBox<PhongBan> cmbPhongBan, cmbPhongBanS;
	private ButtonGroup grpGioiTinh;
	private ArrayList<NhanVien> dsNV = new ArrayList<>();
	private JLabel lblAnh, lblMessage;
	private PhongBan_Dao pb_Dao = new PhongBan_Dao();
	private NhanVien_Dao nv_Dao = new NhanVien_Dao();
	private PhanCongNhanVien_Dao pcnv_Dao = new PhanCongNhanVien_Dao();
	private ArrayList<BangPhanCongNhanVien> dsPCNV = new ArrayList<>();
	private XuatPDF xuatPDF = new XuatPDF();
	private XuatExcel xuatExcel = new XuatExcel();
	/**
	 * Create the panel.
	 */
	public TimKiemNhanVienUI(MainUI main) {
		this.main = main;
		
		//set gia tri cho jpanel SanPham
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		setBackground(bgColor);
		
		JPanel pnlNhanVien = new JPanel();
		add(pnlNhanVien, BorderLayout.CENTER);
		pnlNhanVien.setLayout(new BorderLayout(0, 0));
		pnlNhanVien.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pnlNhanVien.setBackground(bgColor);
		
		//tao jpanel chua Title va Thong tin nhân viên
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
		pnlNorth.setBackground(bgColor);
		pnlNhanVien.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BorderLayout(0, 0));
		
		//Tao jpanel Title
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		pnlTitle.setBackground(bgColor);
		pnlNorth.add(pnlTitle, BorderLayout.NORTH);
		
		//Tiêu đề
		JLabel lblTitle = new JLabel(main.read_file_languages.getString("text_header_TKNV"));
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
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("text_border_search"));
		titleBorderTTSP.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlTimKiem.setBorder(BorderFactory.createCompoundBorder(titleBorderTTSP, BorderFactory.createEmptyBorder(10, 20, 0, 20)));
		pnlTimKiem.setPreferredSize(new Dimension(500, 320));
		pnlNorth.add(pnlTimKiem, BorderLayout.WEST);
		b.add(Box.createHorizontalStrut(30));
		
		JPanel pnlB1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB1.setBackground(bgColor);
		pnlTimKiem.add(pnlB1);
		
		JLabel lblMaNVS = new JLabel(main.read_file_languages.getString("lblMaNV") +":");
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
		
		JLabel lblTenS = new JLabel(main.read_file_languages.getString("lblHoTen") + ":");
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
		
		JLabel lblGioiTinhS = new JLabel(main.read_file_languages.getString("lblGioiTinh") + ":");
		lblGioiTinhS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblGioiTinhS.setForeground(textColor);
		pnlB2.add(lblGioiTinhS);
		
		chkNamS = new JCheckBox(main.read_file_languages.getString("rad_Nam"));
		chkNamS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		chkNamS.setForeground(textColor);
		chkNamS.setBackground(bgColor);
		pnlB2.add(chkNamS);
		pnlB2.add(Box.createHorizontalStrut(5));
		
		chkNuS = new JCheckBox(main.read_file_languages.getString("rad_Nu"));
		chkNuS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		chkNuS.setForeground(textColor);
		chkNuS.setBackground(bgColor);
		pnlB2.add(chkNuS);
		
		pnlB2.add(Box.createHorizontalStrut(15));
		
		JLabel lblNgaySinhS = new JLabel(main.read_file_languages.getString("lblNgaySinh") + ":");
		lblNgaySinhS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblNgaySinhS.setForeground(textColor);
		pnlB2.add(lblNgaySinhS);
		
		dtpNgaySinhS = new JXDatePicker(new Date());
		dtpNgaySinhS.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtpNgaySinhS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dtpNgaySinhS.setBackground(bgColor);
		dtpNgaySinhS.setForeground(textColor);
		dtpNgaySinhS.setLocale(new Locale("vi", "VN"));	// set thoi gian local la VN
		dtpNgaySinhS.getMonthView().setZoomable(true);
		JButton btnDateNSS = (JButton) dtpNgaySinhS.getComponent(1);
		btnDateNSS.setIcon(new ImageScaler("/image/calendar_icon.png", 18, 18).getScaledImageIcon());
		btnDateNSS.setBackground(bgColor);
		btnDateNSS.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		dtpNgaySinhS.getEditor().setBackground(bgColor);
		dtpNgaySinhS.getEditor().setForeground(textColor);
		pnlB2.add(dtpNgaySinhS);
		
		pnlTimKiem.add(Box.createVerticalStrut(10));
		
		JPanel pnlB3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB3.setBackground(bgColor);
		pnlTimKiem.add(pnlB3);
		
		JLabel lblSDTS = new JLabel(main.read_file_languages.getString("lblSDT") + ":");
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
		
		JLabel lblPBS = new JLabel(main.read_file_languages.getString("lblPhongBan") + ":");
		lblPBS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblPBS.setForeground(textColor);
		pnlB3.add(lblPBS);
		
		cmbPhongBanS = new JComboBox();

		// Tạo một đối tượng DefaultComboBoxModel
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(new PhongBan("PB00", main.read_file_languages.getString("lblALL"), 0, ""));

		// Lấy danh sách tất cả các phòng ban
		ArrayList<PhongBan> listPB = pb_Dao.getAllPhongBan();

		// Thêm từng phòng ban vào model
		for (PhongBan pb : listPB) {
		    model.addElement(pb);
		}

		// Đặt model cho JComboBox
		cmbPhongBanS.setModel(model);
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
		
		JLabel lblCCCDS = new JLabel(main.read_file_languages.getString("lblCCCD") + ":");
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
		
		JLabel lblDiaChiS = new JLabel(main.read_file_languages.getString("lblDiaChi") + ":");
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
		
		JPanel pnlMessage = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlMessage.setBackground(bgColor);
		pnlTimKiem.add(pnlMessage);
		pnlMessage.add(lblMessage = new JLabel(""));
		lblMessage.setForeground(Color.decode("#dc3545"));
		lblMessage.setFont(main.roboto_regular.deriveFont(Font.ITALIC, 14F));
		
		//Tao jpanel Thong tin nhan vien
		JPanel pnThongTinNV = new JPanel();
		pnThongTinNV.setLayout(new BorderLayout());
		pnThongTinNV.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("text_border_TTNV"));
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnThongTinNV.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(20, 10, 10, 10)));
		b.add(pnThongTinNV);
		
		JPanel pnlAnhDaiDien = new JPanel();
		pnlAnhDaiDien.setLayout(new BoxLayout(pnlAnhDaiDien, BoxLayout.Y_AXIS));
		pnlAnhDaiDien.setBackground(bgColor);
		pnThongTinNV.add(pnlAnhDaiDien, BorderLayout.WEST);
		
		lblAnh = new JLabel("");
		lblAnh.setIcon(new ImageScaler("/image/employee.png", 150, 150).getScaledImageIcon());
		lblAnh.setBorder(BorderFactory.createLineBorder(componentColor));
		lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
		pnlAnhDaiDien.add(lblAnh, BorderLayout.CENTER);
		
		txtMaNV = new JTextField();
		txtMaNV.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaNV.setColumns(5);
		pnlAnhDaiDien.add(txtMaNV, BorderLayout.SOUTH);
		pnlAnhDaiDien.add(Box.createVerticalStrut(50));
		txtMaNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaNV.setForeground(textColor);
		txtMaNV.setBackground(bgColor);
		txtMaNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		
		btnIn = new RoundedButton("", null, 5, 0, 1.0f);
		btnIn.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnIn.setForeground(Color.WHITE);
		btnIn.setBackground(Color.decode("#17a2b8"));
		btnIn.setIcon(new ImageScaler("/image/printer_icon.png", 24, 24).getScaledImageIcon());
		btnIn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pnlAnhDaiDien.add(btnIn);
		
		
		JPanel pnlTTRight = new JPanel();
		pnlTTRight.setLayout(new BoxLayout(pnlTTRight, BoxLayout.Y_AXIS));
		pnlTTRight.setBackground(bgColor);
		pnThongTinNV.add(pnlTTRight, BorderLayout.CENTER);
		
		JPanel pnlB5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB5.setBackground(bgColor);
		pnlTTRight.add(pnlB5);
		
		JLabel lblTenNV = new JLabel(main.read_file_languages.getString("lblHoTen") + ":");
		lblTenNV.setForeground(textColor);
		lblTenNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB5.add(lblTenNV);
		
		txtTenNV = new JTextField();
		txtTenNV.setColumns(12);
		txtTenNV.setForeground(textColor);
		txtTenNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 0)));
		txtTenNV.setBackground(bgColor);
		pnlB5.add(txtTenNV);
		pnlB5.add(Box.createHorizontalStrut(5));
		
		JLabel lblTenHD = new JLabel(main.read_file_languages.getString("text_heading_password") + ":");
		pnlB5.add(lblTenHD);
		lblTenHD.setForeground(textColor);
		lblTenHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtMatKhau = new JTextField();
		pnlB5.add(txtMatKhau);
		pnlB5.add(Box.createHorizontalStrut(5));
		txtMatKhau.setColumns(8);
		txtMatKhau.setForeground(textColor);
		txtMatKhau.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMatKhau.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 0)));
		txtMatKhau.setBackground(bgColor);
		
		JLabel lblGioiTinh = new JLabel(main.read_file_languages.getString("lblGioiTinh") + ":");
		pnlB5.add(lblGioiTinh);
		lblGioiTinh.setForeground(textColor);
		lblGioiTinh.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		rbtnNam = new JRadioButton(main.read_file_languages.getString("rad_Nam"));
		rbtnNam.setForeground(textColor);
		rbtnNam.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		rbtnNam.setBackground(Color.WHITE);
		pnlB5.add(rbtnNam);
		
		rbtnNu = new JRadioButton(main.read_file_languages.getString("rad_Nu"));
		rbtnNu.setForeground(textColor);
		rbtnNu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		rbtnNu.setBackground(Color.WHITE);
		pnlB5.add(rbtnNu);
		
		grpGioiTinh = new ButtonGroup();
		grpGioiTinh.add(rbtnNam);
		grpGioiTinh.add(rbtnNu);
		
		JPanel pnlB6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB6.setBackground(bgColor);
		pnlTTRight.add(pnlB6);
		
		JLabel lblNgaySinh = new JLabel(main.read_file_languages.getString("lblNgaySinh") + ":");
		lblNgaySinh.setForeground(textColor);
		lblNgaySinh.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB6.add(lblNgaySinh);
		
		dtpNgaySinh = new JXDatePicker(new Date());
		dtpNgaySinh.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtpNgaySinh.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dtpNgaySinh.setBackground(bgColor);
		dtpNgaySinh.setForeground(textColor);
		dtpNgaySinh.setLocale(new Locale("vi", "VN"));	// set thoi gian local la VN
		JButton btnDateNS = (JButton) dtpNgaySinh.getComponent(1);
		btnDateNS.setIcon(new ImageScaler("/image/calendar_icon.png", 18, 18).getScaledImageIcon());
		btnDateNS.setBackground(bgColor);
		btnDateNS.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		dtpNgaySinh.getEditor().setBackground(bgColor);
		dtpNgaySinh.getEditor().setForeground(textColor);
		pnlB6.add(dtpNgaySinh);
		pnlB6.add(Box.createHorizontalStrut(10));
						
		JLabel lblSDT = new JLabel(main.read_file_languages.getString("lblSDT") + ":");
		pnlB6.add(lblSDT);
		lblSDT.setForeground(textColor);
		lblSDT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtSDT = new JTextField();
		txtSDT.setColumns(7);
		pnlB6.add(txtSDT);
		txtSDT.setForeground(textColor);
		txtSDT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtSDT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtSDT.setBackground(bgColor);
		
		pnlB6.add(Box.createHorizontalStrut(5));
		
		JLabel lblEmail = new JLabel("Email:");
		pnlB6.add(lblEmail);
		lblEmail.setForeground(textColor);
		lblEmail.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtEmail = new JTextField();
		txtEmail.setColumns(14);
		pnlB6.add(txtEmail);
		txtEmail.setForeground(textColor);
		txtEmail.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtEmail.setBackground(bgColor);
		
		JPanel pnlB7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB7.setBackground(bgColor);
		pnlTTRight.add(pnlB7);
		
		JLabel lblDiaChi = new JLabel(main.read_file_languages.getString("lblDiaChi") + ":");
		pnlB7.add(lblDiaChi);
		lblDiaChi.setForeground(textColor);
		lblDiaChi.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtDiaChi = new JTextField();
		txtDiaChi.setForeground(textColor);
		txtDiaChi.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtDiaChi.setColumns(15);
		txtDiaChi.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 

						BorderFactory.createEmptyBorder(5, 0, 5, 0)));
		txtDiaChi.setBackground(bgColor);
		pnlB7.add(txtDiaChi);
		Component horizontalStrut = Box.createHorizontalStrut(5);
		pnlB7.add(horizontalStrut);
		
		JLabel lblCCCD = new JLabel(main.read_file_languages.getString("lblCCCD") + ":");
		pnlB7.add(lblCCCD);
		lblCCCD.setForeground(textColor);
		lblCCCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtCCCD = new JTextField();
		txtCCCD.setColumns(8);
		pnlB7.add(txtCCCD);
		txtCCCD.setForeground(textColor);
		txtCCCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtCCCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtCCCD.setBackground(bgColor);
		
		JLabel lblPhongBan = new JLabel(main.read_file_languages.getString("lblPhongBan") + ":");
		lblPhongBan.setForeground(textColor);
		lblPhongBan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB7.add(lblPhongBan);
		
		// Tạo một đối tượng DefaultComboBoxModel
		DefaultComboBoxModel modelPB = new DefaultComboBoxModel<>();
		modelPB.addElement(new PhongBan("PB00", main.read_file_languages.getString("lblChuaPC"), 0, ""));
		modelPB.addAll(listPB);
		
		cmbPhongBan = new JComboBox();
		cmbPhongBan.setModel(modelPB);
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbPhongBan.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbPhongBanS.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbPhongBan.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbPhongBan.setBackground(bgColor);
		cmbPhongBan.setForeground(textColor);
		cmbPhongBan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB7.add(cmbPhongBan);
		pnlB7.add(Box.createHorizontalStrut(10));
		
		JPanel pnlB8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB8.setBackground(bgColor);
		pnlTTRight.add(pnlB8);
		
		JLabel lblChucVu = new JLabel(main.read_file_languages.getString("lblChucVu") + ":");
		pnlB8.add(lblChucVu);
		lblChucVu.setForeground(textColor);
		lblChucVu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtChucVu = new JTextField();
		txtChucVu.setColumns(10);
		pnlB8.add(txtChucVu);
		txtChucVu.setForeground(textColor);
		txtChucVu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtChucVu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 

								BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtChucVu.setBackground(Color.WHITE);
		
		JLabel lblGhiChu = new JLabel(main.read_file_languages.getString("lblGhiChu") + ":");
		lblGhiChu.setForeground(textColor);
		lblGhiChu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB8.add(lblGhiChu);
		
		txtGhiChu = new JTextField();
		txtGhiChu.setColumns(24);
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
		
		btnTim = new RoundedButton(main.read_file_languages.getString("btnTim"), null, 20, 0, 1.0f);
		btnTim.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnTim.setForeground(Color.WHITE);
		btnTim.setBackground(Color.decode("#3B71CA"));
		btnTim.setIcon(new ImageScaler("/image/searchwhite_icon.png", 24, 24).getScaledImageIcon());
		btnTim.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnTim);
		pnlChucNang.add(Box.createHorizontalStrut(25));
		
		btnXoaRong = new RoundedButton(main.read_file_languages.getString("btnXoaRong"), null, 20, 0, 1.0f);
		btnXoaRong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setBackground(Color.decode("#ffc107"));
		btnXoaRong.setIcon(new ImageScaler("/image/refresh-arrow_white_icon.png", 24, 24).getScaledImageIcon());
		btnXoaRong.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoaRong);
		
		// tạo jpanel chứa table sản phẩm
		JPanel pnlBangNV = new JPanel();
		titleBorderTTSP = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("text_border_DSNV"));
		titleBorderTTSP.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNV.setBorder(BorderFactory.createCompoundBorder(titleBorderTTSP, BorderFactory.createEmptyBorder(0, 20, 5, 20)));
		pnlBangNV.setLayout(new BorderLayout(0, 0));
		pnlBangNV.setBackground(bgColor);
		pnlNhanVien.add(pnlBangNV, BorderLayout.CENTER);
		
		JPanel pnlXuat = new JPanel();
		pnlXuat.setBackground(bgColor);
		pnlXuat.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlBangNV.add(pnlXuat, BorderLayout.NORTH);
		
		btnXuat = new RoundedButton(main.read_file_languages.getString("btn_XuatDS"), null, 5, 0, 1.0f);
		btnXuat.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setBackground(Color.decode("#28a745"));
		btnXuat.setIcon(new ImageScaler("/image/printer_icon.png", 20, 20).getScaledImageIcon());
		btnXuat.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		pnlXuat.add(btnXuat);
		
		String cols[] = {main.read_file_languages.getString("stt"),
				main.read_file_languages.getString("lblMaNV"), 
				main.read_file_languages.getString("lblHoTen"), 
				main.read_file_languages.getString("lblGioiTinh"), 
				main.read_file_languages.getString("lblSDT"), 
				main.read_file_languages.getString("lblDiaChi"), 
				main.read_file_languages.getString("lblPhongBan"), 
				main.read_file_languages.getString("lblChucVu"), 
				main.read_file_languages.getString("lblNgayCT")};
		dtbModelNV = new DefaultTableModel(cols, 0);
		tblNV = new JTable(dtbModelNV);

		tbhNV = new JTableHeader(tblNV.getColumnModel());
		tbhNV.setReorderingAllowed(false);
		tbhNV.setBackground(componentColor);
		tbhNV.setForeground(Color.WHITE);
		tbhNV.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblNV.setTableHeader(tbhNV);
		tblNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 14F));
		
		tblNV.setRowHeight(20);
		tblNV.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblNV.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblNV.getColumnModel().getColumn(2).setPreferredWidth(175);
		tblNV.getColumnModel().getColumn(3).setPreferredWidth(50);
		tblNV.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblNV.getColumnModel().getColumn(5).setPreferredWidth(200);
		tblNV.getColumnModel().getColumn(6).setPreferredWidth(100);
		tblNV.getColumnModel().getColumn(7).setPreferredWidth(100);
		tblNV.getColumnModel().getColumn(8).setPreferredWidth(100);
		
		//chỉnh trái phải của dữ liệu trong bảng
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tblNV.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tblNV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblNV.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tblNV.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblNV,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNV.add(scrSP);
		
		pnlNorth.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		
		//Ngăn chỉnh sửa thông tin trong bảng thông tin
		setEditableForTextField(false);
		
		//Thêm sự kiện cho các component
		btnTim.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnXuat.addActionListener(this);
		btnIn.addActionListener(this);
		
		btnTim.addMouseListener(this);
		btnXoaRong.addMouseListener(this);
		btnXuat.addMouseListener(this);
		btnIn.addMouseListener(this);
		
		tblNV.addMouseListener(this);
		
		//Xóa rỗng thông tin
		xoaRong();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == tblNV) {
			int index = tblNV.getSelectedRow();
			if(index != -1) {
				hienThiThongTinNV(index);
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		if (o instanceof RoundedButton) {
			setBorderForFocusButton(o);
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
		lblMessage.setText("");
		main.music.playSE(2);
		Object o = e.getSource();
		if(o == btnTim) {
			timKiemNhanVien();
		}
		if(o == btnXoaRong) {
			xoaRong();
		}
		if(o == btnIn) {
			xuatNhanVien();
		}
		if(o == btnXuat) {
			xuatDSNhanVienExcel();
		}
	}
	//Cho phép hoặc không cho phép chỉnh sửa thông tin
	private void setEditableForTextField(boolean edit) {
		if(edit == true) {
			txtMaNV.setEditable(true);
			txtMatKhau.setEditable(true);
			txtTenNV.setEditable(true);
			dtpNgaySinh.setEditable(true);
			txtSDT.setEditable(true);
			txtCCCD.setEditable(true);
			txtEmail.setEditable(true);
			txtDiaChi.setEditable(true);
			rbtnNu.setEnabled(true);
			txtChucVu.setEditable(true);
			txtGhiChu.setEditable(false);
		}else {
			txtMaNV.setEditable(false);
			txtMatKhau.setEditable(false);
			txtTenNV.setEditable(false);
			dtpNgaySinh.setEditable(false);
			txtSDT.setEditable(false);
			txtCCCD.setEditable(false);
			txtEmail.setEditable(false);
			txtDiaChi.setEditable(false);
			rbtnNu.setEnabled(false);
			rbtnNam.setEnabled(false);
			txtChucVu.setEditable(false);
			txtGhiChu.setEditable(false);
		}
	}
	//Xóa rỗng thông tin trên giao diện
	private void xoaRong() {
		dtbModelNV.setRowCount(0);
		dsNV.removeAll(dsNV);
		dsPCNV.removeAll(dsPCNV);
		txtMaNV.setText("");
		txtTenNV.setText("");
		txtMatKhau.setText("");
		txtSDT.setText("");
		txtCCCD.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtChucVu.setText("");
		txtGhiChu.setText("");
		dtpNgaySinh.setDate(null);
		rbtnNam.setSelected(true);
		cmbPhongBan.setSelectedIndex(0);
		lblAnh.setIcon(new ImageScaler("/image/employee.png", 150, 150).getScaledImageIcon());
		
		txtMaNVS.setText("");
		txtTenNVS.setText("");
		txtSDTS.setText("");
		txtCCCDS.setText("");
		txtDiaChiS.setText("");
		cmbPhongBanS.setSelectedIndex(0);
		dtpNgaySinhS.setDate(null);
		chkNamS.setSelected(true);
		chkNuS.setSelected(true);
	}
	//hiển thị border cho button được user nhấn
	private void setBorderForFocusButton(Object o) {
		if(btnFocus!=null && btnFocus!=o) {
			btnFocus.setFocusButton(null, 0);
		}
		if(btnFocus==null) {
			btnFocus = (RoundedButton) o;
			btnFocus.setFocusButton(main.borderFocusColor, 3);
		}
		else {
			btnFocus = (RoundedButton) o;
			btnFocus.setFocusButton(main.borderFocusColor, 3);
		}
	}
	//Hiển thị nhân viên được chọn từ table lên bảng thông tin
	private void hienThiThongTinNV(int index) {
		txtMaNV.setText(dsNV.get(index).getMaNV());
		txtMatKhau.setText(dsNV.get(index).getMatKhau());
		txtTenNV.setText(dsNV.get(index).getHoTen());
		txtSDT.setText(dsNV.get(index).getSdt());
		txtEmail.setText(dsNV.get(index).getEmail());
		txtCCCD.setText(dsNV.get(index).getcCCD());
		txtDiaChi.setText(dsNV.get(index).getDiaChi());
		rbtnNu.setSelected(dsNV.get(index).isGioiTinh()?false:true);
		rbtnNam.setSelected(dsNV.get(index).isGioiTinh());
		dtpNgaySinh.setDate(dsNV.get(index).getNgaySinh()); // Đặt giá trị cho JXDatePicker
	
		
		if(dsPCNV.get(index).getMaPhanCong()==null) { // Nếu chưa được phân công
			cmbPhongBan.setSelectedIndex(0);
			txtChucVu.setText("");
			txtGhiChu.setText("");
		}else { // Nếu đã được phân công
			for(int i = 0; i < cmbPhongBan.getItemCount(); i++) {
				if(cmbPhongBan.getItemAt(i).getMaPhongBan().equals(dsPCNV.get(index).getPhongBan().getMaPhongBan())) {
					cmbPhongBan.setSelectedIndex(i);
					break;
				}
			}
			txtChucVu.setText(dsPCNV.get(index).getChucVu());
			txtGhiChu.setText(dsPCNV.get(index).getGhiChu());
		}
		if(dsNV.get(index).getHinhAnh()==null || dsNV.get(index).getHinhAnh().trim().length()<=0)
			lblAnh.setIcon(new ImageScaler("/image/employee.png", 150, 150).getScaledImageIcon()); //hiển thị ảnh mặc định
		else
			lblAnh.setIcon(new ImageScaler(dsNV.get(index).getHinhAnh(), 150, 150).getScaledImageAvatar()); //hiển thị ảnh của nv
	}
	// thông báo lỗi
	private void setTextError(String message) {
		main.music.playSE(3);
		lblMessage.setText(message);
	}
	//Tìm kiếm nhân viên theo tiêu chí
	private void timKiemNhanVien() {
	    String maNV = txtMaNVS.getText();
	    String tenNV = txtTenNVS.getText();
	    String sdt = txtSDTS.getText();
	    String cccd = txtCCCDS.getText();
	    String diaChi = txtDiaChiS.getText();
	    PhongBan phongBan = (PhongBan) cmbPhongBanS.getSelectedItem();
	    Date ngaySinh = dtpNgaySinhS.getDate();
	    int isNam = chkNamS.isSelected()?1:-1;
	    int isNu = chkNuS.isSelected()?0:-1;
	    
	    dsNV = nv_Dao.timNhanVien(maNV, tenNV, isNam, isNu, sdt, cccd, diaChi, ngaySinh, phongBan.getMaPhongBan()); //Get ds nhân viên tìm được
	    if(dsNV.size()!=0) { //Nếu tìm được nhân viên phù hợp
	    	dsPCNV = pcnv_Dao.getPhanCong(dsNV);
	    	lblMessage.setText("Tìm thấy " + dsNV.size() + " nhân viên." );
	    	themTatCaNhanVienVaoBang(dsNV);
	    }else {
	    	setTextError("Không tìm thấy nhân viên nào phù hợp!");
	    }
	    
	}
	//thêm một nhân viên vào table 
	private void themNhanVienVaoBang(NhanVien nv, BangPhanCongNhanVien pcnv) {
	    Object[] row = new Object[10];
	    row[0] = dtbModelNV.getRowCount() + 1;  // STT
	    row[1] = nv.getMaNV();  // Mã NV
	    row[2] = nv.getHoTen();  // Họ tên
	    row[3] = nv.isGioiTinh() ? "Nam" : "Nữ";  // Giới tính
	    row[4] = nv.getSdt();  // SDT
	    row[5] = nv.getDiaChi();  // Địa chỉ
	    if(pcnv.getMaPhanCong()==null) {
		    row[6] = "";  // Phòng ban
		    row[7] = "";  // chức vụ
		    row[8] = "";  // 
	    }else {
		    row[6] = pcnv.getPhongBan().getTenPhongBan();  // Phòng ban
		    row[7] = pcnv.getChucVu();  // chức vụ
		    row[8] = new SimpleDateFormat("dd-MM-yyyy").format(pcnv.getNgayCongTac());
	    }
	    dtbModelNV.addRow(row);
	}
	//thêm một ds nhân viên vào bảng
	private void themTatCaNhanVienVaoBang(ArrayList<NhanVien> list) {
		dtbModelNV.setRowCount(0);
	    for (int i = 0; i < dsNV.size(); i++) {
	        themNhanVienVaoBang(dsNV.get(i), dsPCNV.get(i));
	    }
	}
	//xuất thông tin chi tiết 1 nhân viên
	private void xuatNhanVien() {
		int index = tblNV.getSelectedRow();
		if(index!=-1) {
			try {
				xuatPDF.xuatThongTinNhanVien(dsNV.get(index), dsPCNV.get(index));
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			setTextError("Bạn phải chọn nhân viên muốn xuất!");
		}
	}
	//xuất thông tin ds nhân viên ra excel
	private void xuatDSNhanVienExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Chỉ cho phép chọn thư mục
        int option = fileChooser.showSaveDialog(null);
        if(option == JFileChooser.APPROVE_OPTION){
           File file = fileChooser.getSelectedFile();
           String saveDir = file.getAbsolutePath(); // Đây là thư mục mà người dùng đã chọn
           try {
        	   String filePath = saveDir + File.separator + "DanhSachNhanVien" + new SimpleDateFormat("ddMMyyHHmmss").format(new Date()) + ".xlsx";
				xuatExcel.writeExcelTTPC(dsPCNV, dsNV, filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else{
           setTextError("Phải chọn thư mục lưu file!");
        }
	}
}