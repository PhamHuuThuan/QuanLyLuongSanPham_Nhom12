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
import Util.SinhMaTuDong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Component;

public class PhanCongNhanVienUI extends JPanel implements ActionListener, MouseListener, ItemListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private RoundedButton btnPhanCong, btnCapNhatPC, btnXoaPC, btnFocus;
	private DefaultTableModel dtblModelNVPC, dtblModelNV;
	private JTable tblNVPC, tblNV;
	private JTableHeader tbhNVPC, tbhNV;
	private JTextField  txtMaNV, txtTenNV, txtSDT, txtGhiChu;
	private JXDatePicker dtbNgayVL;
	private JComboBox<PhongBan> cmbPhongBan, cmbPhongBanS;
	private JComboBox<String> cmbChucVu;
	private NhanVien_Dao nv_Dao = new NhanVien_Dao();
	private PhongBan_Dao pb_Dao = new PhongBan_Dao();
	private PhanCongNhanVien_Dao pcnv_Dao = new PhanCongNhanVien_Dao();
	private ArrayList<NhanVien> dsNV = new ArrayList<>();
	private ArrayList<BangPhanCongNhanVien> dsPCNV = new ArrayList<>();
	private JLabel lblMessage;
	private JLabel lblAvatar;
	/**
	 * Create the panel.
	 */
	public PhanCongNhanVienUI(MainUI main) {
		this.main = main;
		
		//set gia tri cho jpanel SanPham
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(bgColor);
		
		JPanel pnlNhanVien = new JPanel();
		add(pnlNhanVien, BorderLayout.CENTER);
		pnlNhanVien.setLayout(new BorderLayout(0, 0));
		pnlNhanVien.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pnlNhanVien.setBackground(bgColor);
		
		//tao jpanel chua Title va Thong tin nhan vien
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlNorth.setBackground(bgColor);
		pnlNhanVien.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BorderLayout(0, 0));
		
		//Tao jpanel Title
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		pnlTitle.setBackground(bgColor);
		pnlNorth.add(pnlTitle, BorderLayout.NORTH);
		
		//Tiêu đề
		JLabel lblTitle = new JLabel(main.read_file_languages.getString("text_header_PCNV"));
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle.add(lblTitle);
		
		//Tạo hộp thoại phân công
		Box b = Box.createHorizontalBox();
		b.setBackground(bgColor);
		pnlNorth.add(b, BorderLayout.CENTER);
		
		// tạo jpanel chứa table nhân viên
		JPanel pnlBangNV = new JPanel();
		TitledBorder titleBorderTTNV = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("text_border_DSCPC"));
		titleBorderTTNV.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNV.setBorder(BorderFactory.createCompoundBorder(titleBorderTTNV, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		pnlBangNV.setLayout(new BorderLayout());
		pnlBangNV.setBackground(bgColor);
		pnlBangNV.setPreferredSize(new Dimension(700, 300));
		b.add(pnlBangNV);
		b.add(Box.createHorizontalStrut(50));
		
		String cols[] = {main.read_file_languages.getString("stt"),
				main.read_file_languages.getString("lblMaNV"), 
				main.read_file_languages.getString("lblHoTen"), 
				main.read_file_languages.getString("lblGioiTinh"), 
				main.read_file_languages.getString("lblSDT"), 
				main.read_file_languages.getString("lblDiaChi")};
		dtblModelNV = new DefaultTableModel(cols, 0);
		tblNV = new JTable(dtblModelNV);

		tbhNV = new JTableHeader(tblNV.getColumnModel());
		tbhNV.setReorderingAllowed(false);
		tbhNV.setBackground(componentColor);
		tbhNV.setForeground(Color.WHITE);
		tbhNV.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblNV.setTableHeader(tbhNV);
		
		tblNV.setRowHeight(20);
		tblNV.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblNV.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblNV.getColumnModel().getColumn(2).setPreferredWidth(150);
		tblNV.getColumnModel().getColumn(3).setPreferredWidth(75);
		tblNV.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblNV.getColumnModel().getColumn(5).setPreferredWidth(150);
		
		//chỉnh trái phải của dữ liệu trong bảng
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tblNV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblNV.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tblNV.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		
		//Tạo jscrollpane để tạo scroll cho bảng nhân viên chưa phân công
		JScrollPane scrNV = new JScrollPane(tblNV,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNV.add(scrNV, BorderLayout.CENTER);
		
		
		//Tao jpanel Thong tin phân công nhân viên
		JPanel pnThongTinNV = new JPanel();
		pnThongTinNV.setLayout(new BorderLayout());
		pnThongTinNV.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("text_border_TTPC"));
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnThongTinNV.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(10, 30, 10, 30)));
		pnThongTinNV.setPreferredSize(new Dimension(700, 300));
		b.add(pnThongTinNV);
		
		JPanel pnlAnhDaiDien = new JPanel();
		pnlAnhDaiDien.setLayout(new BoxLayout(pnlAnhDaiDien, BoxLayout.Y_AXIS));
		pnlAnhDaiDien.setBackground(bgColor);
		pnThongTinNV.add(pnlAnhDaiDien, BorderLayout.WEST);
		
		lblAvatar = new JLabel("");
		lblAvatar.setPreferredSize(new Dimension(75, 75));
		lblAvatar.setIcon(new ImageScaler("/image/employee.png", 75, 75).getScaledImageIcon());
		lblAvatar.setBorder(BorderFactory.createLineBorder(componentColor));
		pnlAnhDaiDien.add(lblAvatar, BorderLayout.CENTER);
		
		txtMaNV = new JTextField();
		txtMaNV.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaNV.setColumns(5);
		pnlAnhDaiDien.add(txtMaNV, BorderLayout.SOUTH);
		pnlAnhDaiDien.add(Box.createVerticalStrut(125));
		txtMaNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaNV.setForeground(textColor);
		txtMaNV.setBackground(bgColor);
		txtMaNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtMaNV.setEditable(false);
		
		
		JPanel pnlTTRight = new JPanel();
		pnlTTRight.setLayout(new BoxLayout(pnlTTRight, BoxLayout.Y_AXIS));
		pnlTTRight.setBackground(bgColor);
		pnThongTinNV.add(pnlTTRight, BorderLayout.CENTER);
		
		JPanel pnlB1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB1.setBackground(bgColor);
		pnlTTRight.add(pnlB1);
		
		JLabel lblTenNV = new JLabel(main.read_file_languages.getString("lblHoTen") + ":");
		lblTenNV.setForeground(textColor);
		lblTenNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB1.add(lblTenNV);
		
		txtTenNV = new JTextField();
		txtTenNV.setColumns(14);
		txtTenNV.setForeground(textColor);
		txtTenNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 0, 5, 0)));
		txtTenNV.setBackground(bgColor);
		txtTenNV.setEditable(false);
		pnlB1.add(txtTenNV);
		pnlB1.add(Box.createHorizontalStrut(10));
		
		JLabel lblSDT = new JLabel(main.read_file_languages.getString("lblSDT") + ":");
		pnlB1.add(lblSDT);
		lblSDT.setForeground(textColor);
		lblSDT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtSDT = new JTextField();
		pnlB1.add(txtSDT);
		txtSDT.setColumns(8);
		txtSDT.setForeground(textColor);
		txtSDT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtSDT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtSDT.setBackground(bgColor);
		txtSDT.setEditable(false);
		
		JPanel pnlB2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB2.setBackground(bgColor);
		pnlTTRight.add(pnlB2);
		
		JLabel lblPhongBan = new JLabel(main.read_file_languages.getString("lblPhongBan1") +":");
		lblPhongBan.setForeground(textColor);
		lblPhongBan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB2.add(lblPhongBan);
		
		cmbPhongBan = new JComboBox();
		// Tạo một đối tượng DefaultComboBoxModel
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(new PhongBan("PB00", main.read_file_languages.getString("lblChuaPC"), 0, ""));

		// Lấy danh sách tất cả các phòng ban
		ArrayList<PhongBan> listPB = pb_Dao.getAllPhongBan();

		// Thêm phòng ban vào model
		model.addAll(listPB);
		    
		// Đặt model cho JComboBox
		cmbPhongBan.setModel(model);
		Border 	cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbPhongBan.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbPhongBan.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbPhongBan.setBackground(bgColor);
		cmbPhongBan.setForeground(textColor);
		cmbPhongBan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		cmbPhongBan.setPreferredSize(txtTenNV.getPreferredScrollableViewportSize());
		pnlB2.add(cmbPhongBan);
		pnlB2.add(Box.createHorizontalStrut(10));
		
		JLabel lblChucVu = new JLabel(main.read_file_languages.getString("lblChucVu") +":");
		pnlB2.add(lblChucVu);
		lblChucVu.setForeground(textColor);
		lblChucVu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		String chucVu[] = {" ","Quản lý", "Nhân viên", "Thực tập sinh"};
		cmbChucVu = new JComboBox<String>(chucVu);
		pnlB2.add(cmbChucVu);
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbChucVu.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbChucVu.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbChucVu.setBackground(bgColor);
		cmbPhongBan.setForeground(textColor);
		cmbChucVu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		cmbChucVu.setPreferredSize(txtSDT.getPreferredSize());
		
		JPanel pnlB3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB3.setBackground(bgColor);
		pnlTTRight.add(pnlB3);
		
		JLabel lblNgayVL = new JLabel(main.read_file_languages.getString("lblNgayCT") + ":");
		pnlB3.add(lblNgayVL);
		lblNgayVL.setForeground(textColor);
		lblNgayVL.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		dtbNgayVL = new JXDatePicker((Date) null);
		pnlB3.add(dtbNgayVL);
		dtbNgayVL.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtbNgayVL.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dtbNgayVL.setLocale(new Locale("vi", "VN"));	// set thoi gian local la VN
		dtbNgayVL.getEditor().setBackground(bgColor);
		dtbNgayVL.getEditor().setForeground(textColor);
		dtbNgayVL.setPreferredSize(txtTenNV.getPreferredScrollableViewportSize());
		JButton btnDateNVL = (JButton) dtbNgayVL.getComponent(1);
		btnDateNVL.setIcon(new ImageScaler("/image/calendar_icon.png", 18, 18).getScaledImageIcon());
		btnDateNVL.setBackground(bgColor);
		btnDateNVL.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		Component horizontalStrut = Box.createHorizontalStrut(10);
		pnlB3.add(horizontalStrut);
		
		JLabel lblGhiChu = new JLabel(main.read_file_languages.getString("lblGhiChu") + ":");
		lblGhiChu.setForeground(textColor);
		lblGhiChu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB3.add(lblGhiChu);
		
		txtGhiChu = new JTextField();
		txtGhiChu.setColumns(8);
		txtGhiChu.setForeground(textColor);
		txtGhiChu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtGhiChu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtGhiChu.setBackground(bgColor);
		pnlB3.add(txtGhiChu);
		
		lblTenNV.setPreferredSize(lblPhongBan.getPreferredSize());
		lblNgayVL.setPreferredSize(lblPhongBan.getPreferredSize());
		
		lblSDT.setPreferredSize(lblChucVu.getPreferredSize());
		lblGhiChu.setPreferredSize(lblChucVu.getPreferredSize());
		
		JPanel pnlMessage = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlMessage.setBackground(bgColor);
		pnlTTRight.add(pnlMessage);
		pnlMessage.add(lblMessage = new JLabel());
		lblMessage.setForeground(Color.decode("#dc3545"));
		lblMessage.setFont(main.roboto_regular.deriveFont(Font.ITALIC, 16F));
		
		//Khởi tạo jpanel chức năng chứa các button chức năng
		JPanel pnlChucNang = new JPanel(new FlowLayout());
		pnlChucNang.setBackground(bgColor);
		pnlTTRight.add(pnlChucNang);
		
		btnPhanCong = new RoundedButton(main.read_file_languages.getString("btnPhanCong"), null, 20, 0, 1.0f);
		btnPhanCong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnPhanCong.setForeground(Color.WHITE);
		btnPhanCong.setBackground(Color.decode("#3B71CA"));
		btnPhanCong.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnPhanCong.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnPhanCong);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnCapNhatPC = new RoundedButton(main.read_file_languages.getString("btnSua"), null, 20, 0, 1.0f);
		btnCapNhatPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnCapNhatPC.setForeground(Color.WHITE);
		btnCapNhatPC.setBackground(Color.decode("#ffc107"));
		btnCapNhatPC.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnCapNhatPC.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnCapNhatPC);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnXoaPC = new RoundedButton(main.read_file_languages.getString("btnXoa"), null, 20, 0, 1.0f);
		btnXoaPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnXoaPC.setForeground(Color.WHITE);
		btnXoaPC.setBackground(Color.decode("#dc3545"));
		btnXoaPC.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoaPC.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoaPC);
		
		// tạo jpanel chứa table phân công nhân viên
		JPanel pnlBangNVPC = new JPanel();
		TitledBorder titleBorderTTNVPC = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("title_border_DSPCNV"));
		titleBorderTTNVPC.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNVPC.setBorder(BorderFactory.createCompoundBorder(titleBorderTTNVPC, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		pnlBangNVPC.setLayout(new BorderLayout());
		pnlBangNVPC.setBackground(bgColor);
		pnlNhanVien.add(pnlBangNVPC, BorderLayout.CENTER);
		
		JPanel pnlLocPC = new JPanel();
		pnlLocPC.setBackground(bgColor);
		pnlLocPC.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnlBangNVPC.add(pnlLocPC, BorderLayout.NORTH);
		
		cmbPhongBanS = new JComboBox();
		// Tạo một đối tượng DefaultComboBoxModel
		DefaultComboBoxModel modelS = new DefaultComboBoxModel();
		modelS.addElement(new PhongBan("PB00", main.read_file_languages.getString("lblALL"), 0, ""));

		// Lấy danh sách tất cả các phòng ban
		ArrayList<PhongBan> listPBS = pb_Dao.getAllPhongBan();

		// Thêm phòng ban vào model
		modelS.addAll(listPB);
		    
		// Đặt model cho JComboBox
		cmbPhongBanS.setModel(modelS);
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbPhongBanS.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbPhongBanS.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbPhongBanS.setBackground(bgColor);
		cmbPhongBanS.setForeground(textColor);
		cmbPhongBanS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		cmbPhongBanS.setPreferredSize(txtTenNV.getPreferredScrollableViewportSize());
		pnlLocPC.add(cmbPhongBanS);
		
		String colsPCNV[] = {main.read_file_languages.getString("stt"),
				main.read_file_languages.getString("lblMaNV"), 
				main.read_file_languages.getString("lblHoTen"), 
				main.read_file_languages.getString("lblGioiTinh"), 
				main.read_file_languages.getString("lblSDT"), 
				main.read_file_languages.getString("lblDiaChi"),
				main.read_file_languages.getString("lblPhongBan1"), 
				main.read_file_languages.getString("lblChucVu"), 
				main.read_file_languages.getString("lblNgayCT"), 
				main.read_file_languages.getString("lblGhiChu")};
		dtblModelNVPC = new DefaultTableModel(colsPCNV, 0);
		tblNVPC = new JTable(dtblModelNVPC);

		tbhNVPC = new JTableHeader(tblNVPC.getColumnModel());
		tbhNVPC.setReorderingAllowed(false);
		tbhNVPC.setBackground(componentColor);
		tbhNVPC.setForeground(Color.WHITE);
		tbhNVPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblNVPC.setTableHeader(tbhNVPC);
		tblNVPC.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 14F));
		
		//size của column
		tblNVPC.setRowHeight(20);
		tblNVPC.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblNVPC.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblNVPC.getColumnModel().getColumn(2).setPreferredWidth(200);
		tblNVPC.getColumnModel().getColumn(3).setPreferredWidth(50);
		tblNVPC.getColumnModel().getColumn(4).setPreferredWidth(75);
		tblNVPC.getColumnModel().getColumn(5).setPreferredWidth(250);
		tblNVPC.getColumnModel().getColumn(6).setPreferredWidth(100);
		tblNVPC.getColumnModel().getColumn(7).setPreferredWidth(100);
		
		//chỉnh trái phải các cột
		tblNVPC.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblNVPC.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		
		tblNVPC.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblNVPC,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNVPC.add(scrSP, BorderLayout.CENTER);
		
		pnlNorth.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		
		btnPhanCong.addActionListener(this);
		btnCapNhatPC.addActionListener(this);
		btnXoaPC.addActionListener(this);
		
		btnPhanCong.addMouseListener(this);
		btnCapNhatPC.addMouseListener(this);
		btnXoaPC.addMouseListener(this);
		
		tblNV.addMouseListener(this);
		tblNVPC.addMouseListener(this);
		
		cmbPhongBan.addItemListener(this);
		cmbPhongBanS.addItemListener(this);
		cmbChucVu.addItemListener(this);
		
		
		//get dữ liệu từ csdl
		dsNV = nv_Dao.timNhanVienChuaPhanCong();
		themTatCaNhanVienVaoBangChuaPC(dsNV);
		dsPCNV = pcnv_Dao.getAllPhanCong();
		themTatCaPCNhanVienVaoBangPC(dsPCNV);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o instanceof JTable) {
			hienThiThongTinNV(o);
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
		Object o = e.getSource();
		if(o == btnPhanCong) {
			phanCongNhanVien();
		}
		if(o == btnCapNhatPC) {
			capNhatPhanCongNhanVien();
		}
		if(o == btnXoaPC) {
			xoaPhanCong();
		}
	}
	//thêm một nhân viên vào table 
	private void themNhanVienVaoBangChuaPC(NhanVien nv) {
	    Object[] row = new Object[10];
	    row[0] = dtblModelNV.getRowCount() + 1;  // STT
	    row[1] = nv.getMaNV();  // Mã NV
	    row[2] = nv.getHoTen();  // Họ tên
	    row[3] = nv.isGioiTinh() ? "Nam" : "Nữ";  // Giới tính
	    row[4] = nv.getSdt();  // SDT
	    row[5] = nv.getDiaChi();  // Địa chỉ
	    
	    dtblModelNV.addRow(row);
	}
	//thêm một ds nhân viên vào bảng
	private void themTatCaNhanVienVaoBangChuaPC(ArrayList<NhanVien> list) {
		dtblModelNV.setRowCount(0);
	    for (NhanVien nv : list) {
	        themNhanVienVaoBangChuaPC(nv);
	    }
	}
	//thêm một nhân viên phân công vào table 
	private void themPCNhanVienVaoBangPC(BangPhanCongNhanVien pcnv) {
	    Object[] row = new Object[10];
	    row[0] = dtblModelNVPC.getRowCount() + 1;  // STT
	    row[1] = pcnv.getNhanVien().getMaNV();  // Mã NV
	    row[2] = pcnv.getNhanVien().getHoTen();  // Họ tên
	    row[3] = pcnv.getNhanVien().isGioiTinh() ? "Nam" : "Nữ";  // Giới tính
	    row[4] = pcnv.getNhanVien().getSdt();  // SDT
	    row[5] = pcnv.getNhanVien().getDiaChi();  // Địa chỉ
	    row[6] = pcnv.getPhongBan().getTenPhongBan();  // phòng ban
	    row[7] = pcnv.getChucVu();  //chức vụ
	    row[8] = new SimpleDateFormat("dd-MM-yyyy").format(pcnv.getNgayCongTac());  // ngày công tác
	    row[9] = pcnv.getGhiChu();  //ghi chú
	    
	    dtblModelNVPC.addRow(row);
	}
	//thêm một ds phân công nhân viên vào bảng
	private void themTatCaPCNhanVienVaoBangPC(ArrayList<BangPhanCongNhanVien> list) {
		dtblModelNVPC.setRowCount(0);
	    for (BangPhanCongNhanVien pcnv : list) {
	        themPCNhanVienVaoBangPC(pcnv);
	    }
	}
	//hiển thị thông tin chọn từ bảng lên giao diện
	private void hienThiThongTinNV(Object o) {
		if(o==tblNV) {
			tblNVPC.clearSelection();
			int index = tblNV.getSelectedRow();
			txtMaNV.setText(tblNV.getValueAt(index, 1).toString());
			txtTenNV.setText(tblNV.getValueAt(index, 2).toString());
			txtSDT.setText(tblNV.getValueAt(index, 4).toString());

			if(dsNV.get(index).getHinhAnh()==null || dsNV.get(index).getHinhAnh().trim().length()<=0)
				lblAvatar.setIcon(new ImageScaler("/image/employee.png", 75, 75).getScaledImageIcon());
			else
				lblAvatar.setIcon(new ImageScaler(dsNV.get(index).getHinhAnh(), 75, 75).getScaledImageAvatar());
			cmbChucVu.setSelectedIndex(0);
			txtGhiChu.setText("");
			dtbNgayVL.setDate(pcnv_Dao.timPCNhanVien(tblNV.getValueAt(index, 1).toString()).getNgayCongTac()); 
			if(dtbNgayVL.getDate()!=null)
				dtbNgayVL.setEditable(false);
			else
				dtbNgayVL.setEditable(true);
			cmbPhongBan.setSelectedIndex(0);
		
		}
		else if(o==tblNVPC) {
			tblNV.clearSelection();
			int index = tblNVPC.getSelectedRow();
			txtMaNV.setText(tblNVPC.getValueAt(index, 1).toString());
			txtTenNV.setText(tblNVPC.getValueAt(index, 2).toString());
			txtSDT.setText(tblNVPC.getValueAt(index, 4).toString());
			
			for(int i = 0; i < cmbPhongBan.getItemCount(); i++) {
				if(cmbPhongBan.getItemAt(i).getMaPhongBan().equals(dsPCNV.get(index).getPhongBan().getMaPhongBan())) {
					cmbPhongBan.setSelectedIndex(i);
					break;
				}
			}
			for(int i = 0; i < cmbChucVu.getItemCount(); i++) {
				if(cmbChucVu.getItemAt(i).equals(dsPCNV.get(index).getChucVu())) {
					cmbChucVu.setSelectedIndex(i);
					break;
				}
			}
			txtGhiChu.setText(dsPCNV.get(index).getGhiChu());
			dtbNgayVL.setDate(dsPCNV.get(index).getNgayCongTac()); 
			dtbNgayVL.setEditable(false);
			System.out.println();
			if(dsPCNV.get(index).getNhanVien().getHinhAnh()==null || dsPCNV.get(index).getNhanVien().getHinhAnh().trim().length()<=0)
				lblAvatar.setIcon(new ImageScaler("/image/employee.png", 75, 75).getScaledImageIcon());
			else
				lblAvatar.setIcon(new ImageScaler(dsPCNV.get(index).getNhanVien().getHinhAnh(), 75, 75).getScaledImageAvatar());
		}
	}
	//chuyển dữ liệu nhập vào thành object phân công
	private BangPhanCongNhanVien convertDataToPhanCong() {
		PhongBan pb;
		Date ngayVL;
		String chucVu;
		if(cmbPhongBan.getSelectedIndex()!=0)
			pb = (PhongBan) cmbPhongBan.getSelectedItem();
		else {
			setTextError("Bạn chưa chọn phòng ban!");
			return null;
		}
		if(cmbChucVu.getSelectedIndex()!=0)
			chucVu = (String) cmbChucVu.getSelectedItem();
		else {
			setTextError("Bạn chưa chọn chức vụ!");
			return null;
		}
		if(dtbNgayVL.getDate()!=null)
			ngayVL = dtbNgayVL.getDate();
		else {
			setTextError("Bạn chưa chọn ngày vào làm!");
			return null;
		}
		String ghiChu = txtGhiChu.getText();
		if(tblNV.getSelectedRow()!=-1) {
			BangPhanCongNhanVien pcnv = pcnv_Dao.kiemTraPhanCong(dsNV.get(tblNV.getSelectedRow()).getMaNV());
			if(pcnv==null) {
				return new BangPhanCongNhanVien(new SinhMaTuDong().sinhMaPCNV(), dsNV.get(tblNV.getSelectedRow()), pb, chucVu, ngayVL, ghiChu);
			}else {
				pcnv.setPhongBan(pb);
				pcnv.setChucVu(chucVu);
				pcnv.setGhiChu(ghiChu);
				return pcnv;
			}
		}
		else
			return new BangPhanCongNhanVien(dsPCNV.get(tblNVPC.getSelectedRow()).getMaPhanCong(),dsPCNV.get(tblNVPC.getSelectedRow()).getNhanVien(), pb, chucVu, ngayVL, ghiChu);
	}
	// thông báo lỗi
	private void setTextError(String message) {
		main.music.playSE(3);
		lblMessage.setText(message);
	}
	//phân công nhân viên
	private void phanCongNhanVien() {
		if(tblNV.getSelectedRow()!=-1) {
			BangPhanCongNhanVien pcnv = convertDataToPhanCong();
			if(pcnv!=null) {
				if(!pcnv.getMaPhanCong().equals(new SinhMaTuDong().sinhMaPCNV())) { //đã từng được phân công
					if(pcnv_Dao.capNhatPhanCong(pcnv)) {
						dsNV = nv_Dao.timNhanVienChuaPhanCong();
						themTatCaNhanVienVaoBangChuaPC(dsNV);
						dsPCNV = pcnv_Dao.getAllPhanCong(); // thêm vào bảng mới
						themTatCaPCNhanVienVaoBangPC(dsPCNV);
						lblMessage.setText("Thêm thành công!");
					}else {
						setTextError("Phân công thất bại đã xảy ra lỗi!");
					}
				}else { // phân công mới
					if(pcnv_Dao.luuPhanCong(pcnv)) {
						dsNV = nv_Dao.timNhanVienChuaPhanCong();
						themTatCaNhanVienVaoBangChuaPC(dsNV);
						dsPCNV = pcnv_Dao.getAllPhanCong(); // thêm vào bảng mới
						themTatCaPCNhanVienVaoBangPC(dsPCNV);
						lblMessage.setText("Thêm thành công!");
					}else {
						setTextError("Phân công thất bại đã xảy ra lỗi!");
					}
				}
			}
		}else {
			setTextError("Bạn phải chọn nhân viên cần phân công!");
		}
	}
	//cập nhật phân công nhân viên
	private void capNhatPhanCongNhanVien() {
		if(tblNVPC.getSelectedRow()!=-1) {
			BangPhanCongNhanVien pcnv = convertDataToPhanCong();
			if(pcnv!=null) {
				if(pcnv_Dao.capNhatPhanCong(pcnv)) {
					dsPCNV = pcnv_Dao.getAllPhanCong();
					themTatCaPCNhanVienVaoBangPC(dsPCNV);
					lblMessage.setText("Cập nhật thành công!");
				}else {
					setTextError("Cập nhật phân công thất bại đã xảy ra lỗi!");
				}
			}
		}else {
			setTextError("Bạn phải chọn nhân viên cần sửa phân công!");
		}
	}
	//xóa phân công
	private void xoaPhanCong() {
		if(tblNVPC.getSelectedRow()!=-1) {
			if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa phân công này?", "Cảnh báo", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				if(pcnv_Dao.xoaThongTinPhanCong(dsPCNV.get(tblNVPC.getSelectedRow()))) {
					dsPCNV = pcnv_Dao.getAllPhanCong();
					themTatCaPCNhanVienVaoBangPC(dsPCNV);
					dsNV = nv_Dao.timNhanVienChuaPhanCong();
					themTatCaNhanVienVaoBangChuaPC(dsNV);
					lblMessage.setText("Xóa thành công!");
				}else {
					setTextError("Xóa phân công thất bại đã xảy ra lỗi!");
				}
			}
		}else {
			setTextError("Bạn phải chọn nhân viên cần xóa phân công!");
		}
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
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getSource();
		if(o == cmbPhongBanS) {
			PhongBan pb = (PhongBan)cmbPhongBanS.getSelectedItem();
			if(cmbPhongBanS.getSelectedIndex()!=0) {
				dsPCNV = pcnv_Dao.getDSPhanCong(pb.getMaPhongBan(), null);
			}else if(cmbPhongBanS.getSelectedIndex()==0) {
				dsPCNV = pcnv_Dao.getDSPhanCong(null, null);
			}
			themTatCaPCNhanVienVaoBangPC(dsPCNV);
		}
	}
}
