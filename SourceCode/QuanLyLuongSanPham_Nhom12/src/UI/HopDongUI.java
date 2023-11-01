package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.CustomComboBoxUI;
import CustomUI.CustomListCellRenderer;
import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Dao.HopDong_Dao;
import Dao.SanPham_Dao;
import Entity.HopDong;
import Entity.NhanVien;
import Entity.SanPham;
import Util.SinhMaTuDong;
import Util.XuatForm;
import Util.XuatHopDongForm;
import net.sf.jasperreports.engine.JRException;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComboBox;
import java.awt.FlowLayout;

public class HopDongUI extends JPanel implements ActionListener, MouseListener{
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField txtMaHD, txtTenHD, txtTenKH, txtDaiDien, txtGiaTri, txtTienCoc, txtThoaThuan, txtGhi, txtMaSP, txtTenSP, txtDonGia, txtGhiChu;
	private RoundedButton btnThemHD, btnSuaHD, btnXoaHD, btnThemSP, btnSuaSP, btnXoaSP, btnLuu, btnHuy, btnIn, btnFocus, btnXoaRong;
	private DefaultTableModel dtblModelHD, dtblModelSP;
	private JTable tblHD, tblSP;
	private JTableHeader tbhHD, tbhSP;
	private JPanel pnlChucNang;
	private JComboBox cmbTrangThai, cmbDVT;
	private JTextArea txaYeuCau;
	private JXDatePicker dtpBatDau, dtpKetThuc;
	private JLabel lblGiaTriText, lblTienCocText, lblMessage;
	private JSpinner spnSoLuong;
	private SpinnerNumberModel modelSPN;
	private XuatForm xf;
	private Font fontText;
	private HopDong_Dao hd_Dao = new HopDong_Dao();
	private SanPham_Dao sp_Dao = new SanPham_Dao();
	private boolean isThem = false;
	private ArrayList<HopDong> dsHD = new ArrayList<>();
	private ArrayList<SanPham> dsSP = new ArrayList<>();
	/**
	 * Create the panel.
	 */
	public HopDongUI(MainUI main) {
		this.main = main;
		xf = new XuatForm();
		fontText = main.roboto_regular.deriveFont(Font.PLAIN, 14F);
		
		//set gia tri cho jpanel HopDong
		setLayout(new BorderLayout(0, 0));
		setBackground(bgColor);
		
		//tạo jpanel north
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlNorth.setBackground(bgColor);
		add(pnlNorth, BorderLayout.NORTH);
		
		//Tao jpanel Thong tin san pham
		JPanel pnlThongTinSP = new JPanel();
		pnlThongTinSP.setLayout(new BoxLayout(pnlThongTinSP, BoxLayout.Y_AXIS));
		pnlThongTinSP.setBackground(bgColor);
		TitledBorder titleBorderTTSP = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("title_border_DSSP"));
		titleBorderTTSP.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 14F));
		pnlNorth.setLayout(new BorderLayout(0, 0));
		pnlThongTinSP.setBorder(BorderFactory.createCompoundBorder(titleBorderTTSP, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		pnlThongTinSP.setSize(new Dimension(400, 300));
		pnlNorth.add(pnlThongTinSP, BorderLayout.EAST);
		
		// Tao box chua cac phan tu hang 1: maHD, ma SP, tenSP
		JPanel pnlB1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB1.setBackground(bgColor);
		pnlThongTinSP.add(pnlB1);
		
		JLabel lblMaSP = new JLabel(main.read_file_languages.getString("lblMaSP") + ":");
		lblMaSP.setForeground(textColor);
		lblMaSP.setFont(fontText);
		pnlB1.add(lblMaSP);
		pnlB1.add(Box.createHorizontalStrut(5));
		
		txtMaSP = new JTextField();
		txtMaSP.setForeground(textColor);
		txtMaSP.setFont(fontText);
		txtMaSP.setColumns(7);
		txtMaSP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 10, 2, 10)));
		txtMaSP.setBackground(bgColor);
		pnlB1.add(txtMaSP);
		pnlB1.add(Box.createHorizontalStrut(10));
		
		JLabel lblTenSP = new JLabel(main.read_file_languages.getString("lblTenSP") + ":");
		lblTenSP.setForeground(textColor);
		lblTenSP.setFont(fontText);
		pnlB1.add(lblTenSP);
		pnlB1.add(Box.createHorizontalStrut(5));
		
		txtTenSP = new JTextField();
		txtTenSP.setForeground(textColor);
		txtTenSP.setFont(fontText);
		txtTenSP.setColumns(10);
		txtTenSP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 10, 2, 10)));
		txtTenSP.setBackground(bgColor);
		pnlB1.add(txtTenSP);
		
		pnlThongTinSP.add(Box.createVerticalStrut(20));
		
		//Tao box chua thong tin san pham
		
		JPanel pnlB2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB2.setBackground(bgColor);
		pnlThongTinSP.add(pnlB2);
		
		JLabel lblDVT = new JLabel(main.read_file_languages.getString("lblDVT") + ":");
		lblDVT.setForeground(textColor);
		lblDVT.setFont(fontText);
		pnlB2.add(lblDVT);
		
		cmbDVT = new JComboBox();
		cmbDVT.setModel(new DefaultComboBoxModel(new String[] {"Cái", "Bộ", "Đôi(Cặp)", "Hộp", "Gói", "M2", "Kg", "Lít"}));
		Border cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbDVT.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbDVT.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbDVT.setBackground(bgColor);
		cmbDVT.setForeground(textColor);
		cmbDVT.setFont(fontText);
		pnlB2.add(cmbDVT);

		pnlB2.add(Box.createHorizontalStrut(10));
		
		JLabel lblDonGia = new JLabel(main.read_file_languages.getString("lblDonGia") + ":");
		lblDonGia.setForeground(textColor);
		lblDonGia.setFont(fontText);
		pnlB2.add(lblDonGia);
		
		txtDonGia = new JTextField();
		txtDonGia.setForeground(textColor);
		txtDonGia.setFont(fontText);
		txtDonGia.setColumns(6);
		txtDonGia.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtDonGia.setBackground(bgColor);
		pnlB2.add(txtDonGia);
		
		JLabel lblVND2 = new JLabel("VNĐ");
		lblVND2.setForeground(textColor);
		lblVND2.setFont(fontText);
		pnlB2.add(lblVND2);
		
		pnlB2.add(Box.createHorizontalStrut(10));
		
		JLabel lblSoLuong = new JLabel(main.read_file_languages.getString("lblSoLuong") + ":");
		lblSoLuong.setForeground(textColor);
		lblSoLuong.setFont(fontText);
		pnlB2.add(lblSoLuong);
		
		modelSPN = new SpinnerNumberModel(100, 1, 100000, 100);
		spnSoLuong = new JSpinner(modelSPN);
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 0, 5, 0));
		spnSoLuong.setBorder(cboBorder);
		spnSoLuong.setBackground(bgColor);
		spnSoLuong.setForeground(textColor);
		spnSoLuong.setFont(fontText.deriveFont(12F));
		pnlB2.add(spnSoLuong);
		
		pnlThongTinSP.add(Box.createVerticalStrut(20));
		
		Box b3 = Box.createHorizontalBox();
		pnlThongTinSP.add(b3);
		b3.setBackground(bgColor);
		JLabel lblYeuCau = new JLabel(main.read_file_languages.getString("lblYeuCau") + ":");
		lblYeuCau.setForeground(textColor);
		lblYeuCau.setFont(fontText);
		b3.add(lblYeuCau);
		b3.add(Box.createHorizontalStrut(35));
		
		txaYeuCau = new JTextArea();
		txaYeuCau.setRows(3);
		txaYeuCau.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txaYeuCau.setForeground(textColor);
		txaYeuCau.setFont(fontText);
		JScrollPane scrYC = new JScrollPane(txaYeuCau, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		b3.add(scrYC);
		pnlThongTinSP.add(Box.createVerticalStrut(10));
		
		Box b8 = Box.createHorizontalBox();
		pnlThongTinSP.add(b8);
		pnlThongTinSP.add(Box.createVerticalStrut(10));
		
		//cac button chuc nang cho san pham
		
		btnThemSP = new RoundedButton(main.read_file_languages.getString("btnThem"), null, 15, 0, 1.0f);
		btnThemSP.setFont(fontText);
		btnThemSP.setForeground(Color.WHITE);
		btnThemSP.setBackground(Color.decode("#3B71CA"));
		btnThemSP.setIcon(new ImageScaler("/image/addHopDong_icon.png", 20, 20).getScaledImageIcon());
		btnThemSP.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		b8.add(btnThemSP);
		b8.add(Box.createHorizontalStrut(25));
		
		btnSuaSP = new RoundedButton(main.read_file_languages.getString("btnSua"), null, 15, 0, 1.0f);
		btnSuaSP.setFont(fontText);
		btnSuaSP.setForeground(Color.WHITE);
		btnSuaSP.setBackground(Color.decode("#ffc107"));
		btnSuaSP.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 20, 20).getScaledImageIcon());
		btnSuaSP.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		b8.add(btnSuaSP);
		b8.add(Box.createHorizontalStrut(25));
		
		btnXoaSP = new RoundedButton(main.read_file_languages.getString("btnXoa"), null, 15, 0, 1.0f);
		btnXoaSP.setFont(fontText);
		btnXoaSP.setForeground(Color.WHITE);
		btnXoaSP.setBackground(Color.decode("#dc3545"));
		btnXoaSP.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 20, 20).getScaledImageIcon());
		btnXoaSP.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		b8.add(btnXoaSP);
		b8.add(Box.createHorizontalStrut(25));
		
		btnXoaRong = new RoundedButton("", null, 15, 0, 1.0f);
		btnXoaRong.setFont(fontText);
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setBackground(Color.decode("#17a2b8"));
		btnXoaRong.setIcon(new ImageScaler("/image/refresh-arrow_white_icon.png", 20, 20).getScaledImageIcon());
		btnXoaRong.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		b8.add(btnXoaRong);
		
		// tạo jpanel chứa table sản phẩm
		JPanel pnlBangSP = new JPanel();
		pnlBangSP.setLayout(new BoxLayout(pnlBangSP, BoxLayout.X_AXIS));
		pnlThongTinSP.add(pnlBangSP, BorderLayout.CENTER);
		String cols[] = {
				main.read_file_languages.getString("stt"),
				main.read_file_languages.getString("lblMaSP"),
				main.read_file_languages.getString("lblTenSP"), 
				main.read_file_languages.getString("lblSoLuong"), 
				main.read_file_languages.getString("lblDonGia")};
		dtblModelSP = new DefaultTableModel(cols, 0);
		tblSP = new JTable(dtblModelSP);
		
		tbhSP = new JTableHeader(tblSP.getColumnModel());
		tbhSP.setReorderingAllowed(false);
		tbhSP.setBackground(componentColor);
		tbhSP.setForeground(Color.WHITE);
		tbhSP.setFont(fontText);
		tblSP.setTableHeader(tbhSP);
		
		tblSP.setRowHeight(20);
		tblSP.setPreferredScrollableViewportSize(tblSP.getPreferredSize());
		tblSP.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblSP.getColumnModel().getColumn(1).setPreferredWidth(70);
		tblSP.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblSP.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblSP.setFillsViewportHeight(true);
		
		//Tạo jscrollpane để tạo scroll cho bảng hợp đồng
		JScrollPane scrSP = new JScrollPane(tblSP,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrSP.setPreferredSize(new Dimension(500, tblSP.getRowHeight() * 5));
		pnlBangSP.add(scrSP);
		
		
		//tao jpanel chua Title va Thong tin HD
		JPanel pnlHopDong = new JPanel();
		pnlHopDong.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		pnlHopDong.setBackground(bgColor);
		pnlNorth.add(pnlHopDong, BorderLayout.CENTER);
		pnlHopDong.setLayout(new BorderLayout(0, 0));
		
		//Tao jpanel Title
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(bgColor);
		pnlNorth.add(pnTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel(main.read_file_languages.getString("title_contract"));
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 18F));
		pnTitle.add(lblTitle);
		
		//Tao jpanel Thong tin hop dong
		JPanel pnThongTinHD = new JPanel();
		pnThongTinHD.setLayout(new BoxLayout(pnThongTinHD, BoxLayout.Y_AXIS));
		pnThongTinHD.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("info_contract"));
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 14F));
		pnThongTinHD.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(10, 30, 0, 30)));
		pnlHopDong.add(pnThongTinHD, BorderLayout.CENTER);
		
		// Tao box chua cac phan tu hang 1: maHD, tenHD, tenKH, nguoiDaiDien
		JPanel pnlB4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB4.setBackground(bgColor);
		pnThongTinHD.add(pnlB4);
		
		JLabel lblMaHD = new JLabel(main.read_file_languages.getString("lblMaHD") + ":");
		lblMaHD.setFont(fontText);
		lblMaHD.setForeground(textColor);
		pnlB4.add(lblMaHD);
		
		txtMaHD = new JTextField();
		txtMaHD.setFont(fontText);
		txtMaHD.setForeground(textColor);
		txtMaHD.setBackground(bgColor);
		txtMaHD.setColumns(10);
		txtMaHD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		pnlB4.add(txtMaHD);
		pnlB4.add(Box.createHorizontalStrut(10));
		
		JLabel lblTenHD = new JLabel(main.read_file_languages.getString("lblTenHD") + ":");
		lblTenHD.setForeground(textColor);
		lblTenHD.setFont(fontText);
		pnlB4.add(lblTenHD);
		
		txtTenHD = new JTextField();
		txtTenHD.setForeground(textColor);
		txtTenHD.setFont(fontText);
		txtTenHD.setColumns(12);
		txtTenHD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtTenHD.setBackground(bgColor);
		pnlB4.add(txtTenHD);
		pnlB4.add(Box.createHorizontalStrut(10));
		
		JLabel lblTenKH = new JLabel(main.read_file_languages.getString("lblKH") + ":");
		lblTenKH.setForeground(textColor);
		lblTenKH.setFont(fontText);
		pnlB4.add(lblTenKH);
		
		txtTenKH = new JTextField();
		txtTenKH.setForeground(textColor);
		txtTenKH.setFont(fontText);
		txtTenKH.setColumns(12);
		txtTenKH.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtTenKH.setBackground(bgColor);
		pnlB4.add(txtTenKH);
		
		pnThongTinHD.add(Box.createVerticalStrut(10));
		
		//Tao box chua thong tin hang 2: ngayBD, ngayKT, giatri, tiencoc
		JPanel pnlB5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB5.setBackground(bgColor);
		pnThongTinHD.add(pnlB5);
		
		JLabel lblDaiDien = new JLabel(main.read_file_languages.getString("lblDaiDien") + ":");
		lblDaiDien.setForeground(textColor);
		lblDaiDien.setFont(fontText);
		pnlB5.add(lblDaiDien);
		
		txtDaiDien = new JTextField();
		txtDaiDien.setForeground(textColor);
		txtDaiDien.setFont(fontText);
		txtDaiDien.setColumns(10);
		txtDaiDien.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtDaiDien.setBackground(bgColor);
		pnlB5.add(txtDaiDien);
		pnlB5.add(Box.createHorizontalStrut(20));
		
		// ngay bat dau
		JLabel lblNgayBD = new JLabel(main.read_file_languages.getString("lblNgayBD") + ":");
		lblNgayBD.setForeground(textColor);
		lblNgayBD.setFont(fontText);
		pnlB5.add(lblNgayBD);
		
		dtpBatDau = new JXDatePicker(new Date());
		dtpBatDau.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtpBatDau.setFont(fontText);
		dtpBatDau.setBackground(bgColor);
		dtpBatDau.setForeground(textColor);
		dtpBatDau.setLocale(new Locale("vi", "VN"));	// set thoi gian local la VN
		JButton btnDateBD = (JButton) dtpBatDau.getComponent(1);
		btnDateBD.setIcon(new ImageScaler("/image/calendar_icon.png", 18, 18).getScaledImageIcon());
		btnDateBD.setBackground(bgColor);
		btnDateBD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		dtpBatDau.getEditor().setBackground(bgColor);
		dtpBatDau.getEditor().setForeground(textColor);
		pnlB5.add(dtpBatDau);
		
		pnlB5.add(Box.createHorizontalStrut(20));
		
		// ngay ket thuc
		JLabel lblNgayKetThuc = new JLabel(main.read_file_languages.getString("lblNgayKT") + ":");
		lblNgayKetThuc.setForeground(textColor);
		lblNgayKetThuc.setFont(fontText);
		pnlB5.add(lblNgayKetThuc);
		
		dtpKetThuc = new JXDatePicker(new Date());
		dtpKetThuc.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtpKetThuc.getEditor().setForeground(textColor);
		dtpKetThuc.getEditor().setBackground(bgColor);
		dtpKetThuc.setLocale(new Locale("vi", "VN"));
		dtpKetThuc.setForeground(textColor);
		dtpKetThuc.setFont(fontText);
		dtpKetThuc.setBackground(bgColor);
		JButton btnDateKT = (JButton) dtpKetThuc.getComponent(1);
		btnDateKT.setIcon(new ImageScaler("/image/calendarfinish_icon.png", 18, 18).getScaledImageIcon());
		btnDateKT.setBackground(bgColor);
		btnDateKT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		pnlB5.add(dtpKetThuc);
		
		pnThongTinHD.add(Box.createVerticalStrut(20));
		
		//Tạo box chứa thông tin hàng 3: thỏa thuận, trạng thái, ghi chú
		JPanel pnlB6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB6.setBackground(bgColor);
		pnThongTinHD.add(pnlB6);
		
		JLabel lblGiaTri = new JLabel(main.read_file_languages.getString("lblGiaTri") + ":");
		lblGiaTri.setForeground(textColor);
		lblGiaTri.setFont(fontText);
		pnlB6.add(lblGiaTri);
		
		txtGiaTri = new JTextField();
		txtGiaTri.setForeground(textColor);
		txtGiaTri.setFont(fontText);
		txtGiaTri.setColumns(10);
		txtGiaTri.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtGiaTri.setBackground(bgColor);
		pnlB6.add(txtGiaTri);
		
		JLabel lblVND1 = new JLabel("VNĐ");
		lblVND1.setForeground(textColor);
		lblVND1.setFont(fontText);
		pnlB6.add(lblVND1);
		
		lblGiaTriText = new JLabel("");
		lblGiaTriText.setForeground(textColor);
		lblGiaTriText.setFont(fontText);
		pnlB6.add(lblGiaTriText);

		pnlB6.add(Box.createHorizontalStrut(20));
		
		JLabel lblTienCoc = new JLabel(main.read_file_languages.getString("lblTienCoc") + ":");
		lblTienCoc.setForeground(textColor);
		lblTienCoc.setFont(fontText);
		pnlB6.add(lblTienCoc);
		
		txtTienCoc = new JTextField();
		txtTienCoc.setForeground(textColor);
		txtTienCoc.setFont(fontText);
		txtTienCoc.setColumns(12);
		txtTienCoc.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtTienCoc.setBackground(bgColor);
		pnlB6.add(txtTienCoc);
		
		JLabel lblVND3 = new JLabel("VNĐ");
		lblVND3.setForeground(textColor);
		lblVND3.setFont(fontText);
		pnlB6.add(lblVND3);
		
		lblTienCocText = new JLabel("");
		lblTienCocText.setForeground(textColor);
		lblTienCocText.setFont(fontText);
		pnlB6.add(lblTienCocText);
		pnlB6.add(Box.createHorizontalStrut(10));
		
		JLabel lblThoaThuan = new JLabel(main.read_file_languages.getString("lblThoaThuan") + ":");
		lblThoaThuan.setForeground(Color.BLACK);
		lblThoaThuan.setFont(fontText);
		pnlB6.add(lblThoaThuan);
		
		txtThoaThuan = new JTextField();
		txtThoaThuan.setForeground(Color.BLACK);
		txtThoaThuan.setFont(fontText);
		txtThoaThuan.setColumns(12);
		txtThoaThuan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtThoaThuan.setBackground(Color.WHITE);
		pnlB6.add(txtThoaThuan);
		
		JPanel pnlB7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB7.setBackground(bgColor);
		pnThongTinHD.add(Box.createVerticalStrut(20));
		pnThongTinHD.add(pnlB7);
		
		JLabel lblTrangThai = new JLabel(main.read_file_languages.getString("lblTrangThai") + ":");
		lblTrangThai.setForeground(Color.BLACK);
		lblTrangThai.setFont(fontText);
		pnlB7.add(lblTrangThai);
		
		pnlB7.add(Box.createHorizontalStrut(10));
		
		cmbTrangThai = new JComboBox();
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbTrangThai.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbTrangThai.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbTrangThai.addItem(main.read_file_languages.getString("lblTrangThai1"));
		cmbTrangThai.addItem("Hoàn thành");
		pnlB7.add(cmbTrangThai);
		cmbTrangThai.setBackground(bgColor);
		cmbTrangThai.setForeground(textColor);
		cmbTrangThai.setFont(fontText);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(30);
		pnlB7.add(horizontalStrut_3);
		
		JLabel lblGhiChu = new JLabel(main.read_file_languages.getString("lblGhiChu") + ":");
		lblGhiChu.setForeground(textColor);
		lblGhiChu.setFont(fontText);
		pnlB7.add(lblGhiChu);
		
		pnlB7.add(Box.createHorizontalStrut(10));
		
		txtGhiChu = new JTextField();
		txtGhiChu.setForeground(textColor);
		txtGhiChu.setFont(fontText);
		txtGhiChu.setColumns(34);
		txtGhiChu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtGhiChu.setBackground(bgColor);
		pnlB7.add(txtGhiChu);
		
		pnThongTinHD.add(Box.createVerticalStrut(10));
		
		JPanel pnlMessage = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlMessage.setBackground(bgColor);
		pnThongTinHD.add(pnlMessage);
		pnlMessage.add(lblMessage = new JLabel());
		lblMessage.setForeground(Color.decode("#dc3545"));
		lblMessage.setFont(fontText.deriveFont(Font.ITALIC));
		
		pnThongTinHD.add(Box.createVerticalStrut(10));
		
		//Khởi tạo jpanel chức năng chứa các button chức năng: thêm, sửa, xóa, xuất, lưu, hủy
		pnlChucNang = new JPanel();
		pnlHopDong.add(pnlChucNang, BorderLayout.SOUTH);
		pnlChucNang.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlChucNang.setBackground(bgColor);
		pnlChucNang.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		
		btnThemHD = new RoundedButton(main.read_file_languages.getString("btnThem"), null, 15, 0, 1.0f);
		btnThemHD.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnThemHD.setForeground(Color.WHITE);
		btnThemHD.setBackground(Color.decode("#3B71CA"));
		btnThemHD.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnThemHD.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnThemHD);
		pnlChucNang.add(Box.createHorizontalStrut(20));
		
		btnSuaHD = new RoundedButton(main.read_file_languages.getString("btnSua"), null, 15, 0, 1.0f);
		btnSuaHD.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnSuaHD.setForeground(Color.WHITE);
		btnSuaHD.setBackground(Color.decode("#ffc107"));
		btnSuaHD.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnSuaHD.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnSuaHD);
		pnlChucNang.add(Box.createHorizontalStrut(20));
		
		btnXoaHD = new RoundedButton(main.read_file_languages.getString("btnXoa"), null, 15, 0, 1.0f);
		btnXoaHD.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnXoaHD.setForeground(Color.WHITE);
		btnXoaHD.setBackground(Color.decode("#dc3545"));
		btnXoaHD.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoaHD.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoaHD);
		pnlChucNang.add(Box.createHorizontalStrut(20));
		
		btnIn = new RoundedButton(main.read_file_languages.getString("btnIn"), null, 15, 0, 1.0f);
		btnIn.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnIn.setForeground(Color.WHITE);
		btnIn.setBackground(Color.decode("#17a2b8"));
		btnIn.setIcon(new ImageScaler("/image/printer_icon.png", 24, 24).getScaledImageIcon());
		btnIn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnIn);
		
		pnlChucNang.add(Box.createHorizontalStrut(100));
		
		btnLuu = new RoundedButton(main.read_file_languages.getString("btnLuu"), null, 15, 0, 0.6f);
		btnLuu.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setBackground(Color.decode("#28a745"));
		btnLuu.setIcon(new ImageScaler("/image/save_Icon.png", 24, 24).getScaledImageIcon());
		btnLuu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnLuu);
		pnlChucNang.add(Box.createHorizontalStrut(20));
		
		btnHuy = new RoundedButton(main.read_file_languages.getString("btnHuy"), null, 15, 0, 0.6f);
		btnHuy.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBackground(Color.decode("#ffc107"));
		btnHuy.setIcon(new ImageScaler("/image/cancelHD_Icon.png", 24, 24).getScaledImageIcon());
		btnHuy.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnHuy);
		
		// tạo jpanel chứa table hợp đồng
		JPanel pnlBangHD = new JPanel();
		pnlBangHD.setLayout(new BoxLayout(pnlBangHD, BoxLayout.X_AXIS));
		add(pnlBangHD, BorderLayout.CENTER);
		String colsHD[] = {main.read_file_languages.getString("stt"),
				main.read_file_languages.getString("lblMaHD"),
				main.read_file_languages.getString("lblTenHD"), 
				main.read_file_languages.getString("lblKH"), 
				main.read_file_languages.getString("lblDaiDien"), 
				main.read_file_languages.getString("lblNgayBD"), 
				main.read_file_languages.getString("lblNgayKT"), 
				main.read_file_languages.getString("lblGiaTri"), 
				main.read_file_languages.getString("lblTrangThai"), 
				main.read_file_languages.getString("lblGhiChu")};
		dtblModelHD = new DefaultTableModel(colsHD, 0);
		tblHD = new JTable(dtblModelHD);
		
		tbhHD = new JTableHeader(tblHD.getColumnModel());
		tbhHD.setReorderingAllowed(false);
		tbhHD.setBackground(componentColor);
		tbhHD.setForeground(Color.WHITE);
		tbhHD.setFont(fontText);
		tblHD.setTableHeader(tbhHD);
		
		tblHD.setRowHeight(20);
		tblHD.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblHD.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblHD.getColumnModel().getColumn(2).setPreferredWidth(200);
		tblHD.getColumnModel().getColumn(3).setPreferredWidth(175);
		tblHD.getColumnModel().getColumn(4).setPreferredWidth(175);
		tblHD.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblHD.getColumnModel().getColumn(6).setPreferredWidth(150);
		tblHD.getColumnModel().getColumn(7).setPreferredWidth(200);
		tblHD.getColumnModel().getColumn(8).setPreferredWidth(100);
		tblHD.getColumnModel().getColumn(9).setPreferredWidth(150);
		
		//Tạo jscrollpane để tạo scroll cho bảng hợp đồng
		JScrollPane scrHD = new JScrollPane(tblHD,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangHD.add(scrHD);
		
		//add sự kiện cho các component
		btnThemHD.addActionListener(this);
		btnSuaHD.addActionListener(this);
		btnXoaHD.addActionListener(this);
		btnIn.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		btnThemSP.addActionListener(this);
		btnSuaSP.addActionListener(this);
		btnXoaSP.addActionListener(this);
		btnXoaRong.addActionListener(this);
		
		btnThemHD.addMouseListener(this);
		btnSuaHD.addMouseListener(this);
		btnXoaHD.addMouseListener(this);
		btnIn.addMouseListener(this);
		btnLuu.addMouseListener(this);
		btnHuy.addMouseListener(this);
		tblHD.addMouseListener(this);
		tblSP.addMouseListener(this);
		
		//Không thể thao tác với button lưu và hủy
		displayButtonSaveAndCancel(false);
		
		//Không thể chỉnh sửa txt
		setEditableForTextField(false);
		
		//get danh sách hợp đồng từ cơ sở dữ liệu
		getDataToTable();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == tblHD) {
			int index = tblHD.getSelectedRow();
			if(index != -1  && btnFocus!=btnThemHD && btnFocus!=btnSuaHD) {
				hienThiThongTinHD(index);
				dsSP = sp_Dao.getSanPhamTheoHopDong(txtMaHD.getText());
				themTatCaSanPhamVaoBang(dsSP);
			}
		}
		if(e.getSource() == tblSP) {
			int index = tblSP.getSelectedRow();
			if(index != -1) {
				hienThiThongTinSP(index);
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
		Object o = e.getSource();
		main.music.playSE(2);
		if(o == btnThemHD) {
			displayButtonSaveAndCancel(true);
			setEditableForTextField(true);
			xoaRong();	
			txtMaHD.setText(new SinhMaTuDong().sinhMaHD());
			txtDaiDien.setText(main.nv.getHoTen());
			xoaRongSP();
			isThem = true;
		}
		if(o == btnSuaHD) {
			if(tblHD.getSelectedRow()!=-1) {
				displayButtonSaveAndCancel(true);
				setEditableForTextField(true);
				xoaRongSP();
				isThem = false;
			}else {
				setTextError("Bạn chưa chọn hợp đồng cần chỉnh sửa!!!");
			}
		}
		if(o == btnXoaHD) {
			if(tblHD.getSelectedRow()!=-1) {
				xoaHopDong();
				xoaRong();
			}else {
				setTextError("Bạn chưa chọn hợp đồng cần xóa!!!");
			}
		}
		if(o == btnIn) {
			xuatHopDong();
		}
		if(o == btnLuu) {
			if(isThem == true) {
				themHopDong();
			}else {
				suaHopDong();
			}
		}
		if(o == btnHuy) {
			displayButtonSaveAndCancel(false);
			setEditableForTextField(false);
			if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát? Toàn bộ thông tin thay đổi sẽ mất", "Cảnh báo", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				if(isThem == true)
					xoaHopDongTamThoi();
				xoaRong();
			}
		}
		if(o == btnThemSP) {
			themSanpham();
		}
		if(o == btnSuaSP) {
			suaSanPham();
		}
		if(o == btnXoaSP) {
			xoaSanPham();
		}
		if(o == btnXoaRong) {
			xoaRongSP();
		}
	}
	//thay đổi hiển thị button
	private void displayButtonSaveAndCancel(boolean display) {
		if(display == true) {
			btnLuu.setEnabled(true);
			btnLuu.setAlpha(1f);
			btnHuy.setEnabled(true);
			btnHuy.setAlpha(1f);
			btnThemSP.setEnabled(true);
			btnThemSP.setAlpha(1f);
			btnSuaSP.setEnabled(true);
			btnSuaSP.setAlpha(1f);
			btnXoaSP.setEnabled(true);
			btnXoaSP.setAlpha(1f);
			btnXoaRong.setEnabled(true);
			btnXoaRong.setAlpha(1f);
			
			btnThemHD.setEnabled(false);
			btnThemHD.setAlpha(0.6f);
			btnSuaHD.setEnabled(false);
			btnSuaHD.setAlpha(0.6f);
			btnXoaHD.setEnabled(false);
			btnXoaHD.setAlpha(0.6f);
			btnIn.setEnabled(false);
			btnIn.setAlpha(0.6f);
			
		}else {
			btnLuu.setEnabled(false);
			btnLuu.setAlpha(0.6f);
			btnHuy.setEnabled(false);
			btnHuy.setAlpha(0.6f);
			btnThemSP.setEnabled(false);
			btnThemSP.setAlpha(0.6f);
			btnSuaSP.setEnabled(false);
			btnSuaSP.setAlpha(0.6f);
			btnXoaSP.setEnabled(false);
			btnXoaSP.setAlpha(0.6f);
			btnXoaRong.setEnabled(false);
			btnXoaRong.setAlpha(0.6f);
			
			btnThemHD.setEnabled(true);
			btnThemHD.setAlpha(1f);
			btnSuaHD.setEnabled(true);
			btnSuaHD.setAlpha(1f);
			btnXoaHD.setEnabled(true);
			btnXoaHD.setAlpha(1f);
			btnIn.setEnabled(true);
			btnIn.setAlpha(1f);
		}
	}
	//cho phép hoặc ngăn user chỉnh sửa thông tin
	private void setEditableForTextField(boolean edit) {
		if(edit == true) {
			txtTenHD.setEditable(true);
			txtTenKH.setEditable(true);
			txtDaiDien.setEditable(true);
			dtpBatDau.setEditable(true);
			dtpKetThuc.setEditable(true);
			txtGiaTri.setEditable(true);
			txtTienCoc.setEditable(true);
			txtThoaThuan.setEditable(true);
			txtGhiChu.setEditable(true);
			
			txtTenSP.setEditable(true);
			txtDonGia.setEditable(true);
			txaYeuCau.setEditable(true);

		}else {
			txtMaHD.setEditable(false);
			txtTenHD.setEditable(false);
			txtTenKH.setEditable(false);
			txtDaiDien.setEditable(false);
			dtpBatDau.setEditable(false);
			dtpKetThuc.setEditable(false);
			txtGiaTri.setEditable(false);
			txtTienCoc.setEditable(false);
			txtThoaThuan.setEditable(false);
			txtGhiChu.setEditable(false);
			
			txtMaSP.setEditable(false);
			txtTenSP.setEditable(false);
			txtDonGia.setEditable(false);
			txaYeuCau.setEditable(false);
		}
	}
	//Xóa toàn bộ dữ liệt trên bảng thông tin
	private void xoaRong() {
		dsHD = hd_Dao.getAllHopDong();
		dtblModelHD.setRowCount(0);
		themTatCaHopDongVaoBang(dsHD);
		dsSP.removeAll(dsSP);
		txtMaHD.setText("");
		txtTenHD.setText("");
		txtTenKH.setText("");
		txtDaiDien.setText("");
		dtpBatDau.setDate(new Date());
		dtpKetThuc.setDate(new Date());
		txtGiaTri.setText("");
		txtTienCoc.setText("");
		txtThoaThuan.setText("");
		cmbTrangThai.setSelectedIndex(0);
		txtGhiChu.setText("");
		lblGiaTriText.setText("");
		lblTienCocText.setText("");
		
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtDonGia.setText("");
		txaYeuCau.setText("");
		cmbDVT.setSelectedIndex(0);
	}
	//xóa rỗng thông tin sản phẩm
	private void xoaRongSP() {
		themTatCaSanPhamVaoBang(dsSP);
		
		txtMaSP.setText(new SinhMaTuDong().sinhMaSP());
		txtTenSP.setText("");
		txtDonGia.setText("");
		txaYeuCau.setText("");
		cmbDVT.setSelectedIndex(0);
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
		else if(btnFocus == btnThemHD || btnFocus == btnSuaHD) {
			if(o == btnHuy || o == btnLuu) {
				btnFocus = (RoundedButton) o;
				btnFocus.setFocusButton(main.borderFocusColor, 3);
			}
		}else if(btnFocus == btnLuu || btnFocus == btnHuy) {
			if(o == btnThemHD || o == btnSuaHD || o == btnXoaHD || o == btnIn) {
				btnFocus = (RoundedButton) o;
				btnFocus.setFocusButton(main.borderFocusColor, 3);
			}
		}else {
			btnFocus = (RoundedButton) o;
			btnFocus.setFocusButton(main.borderFocusColor, 3);
		}
	}
	//format lai giá trị và tiền cọc
	private String formatMoneyText(String money) {
	    try {
	        double value = Double.parseDouble(money.replaceAll("\\.", ""));
	        DecimalFormat formatter = new DecimalFormat("#,###");
	        return formatter.format(value);
	    } catch (NumberFormatException ex) {
	        // handle exception here
	    }
	    return null;
	}
	//chuyển đổi sang dạng chữ từ trong khoảng trăm - tỷ
	private String formatMoneyToText(Double money) {
		String text = "";
        try {
            if (money < 100) {
            	text = String.valueOf(money);
            } else if (money < 1000) {
            	text = money / 100 + " trăm";
            }else if (money < 1000000) {
            	text = money / 1000 + " nghìn";
            } else if (money < 1000000000) {
            	text = money / 1000000 + " triệu";
            } else {
            	text = money / 1000000000 + " tỷ";
            }
        } catch (NumberFormatException ex) {
            // handle exception here
        }
        return text;
    }
	private void xuatHopDong() {
		if(tblHD.getSelectedRow()!=-1) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String giaTriHD = txtGiaTri.getText() + lblGiaTriText.getText();
			String tienCocHD = txtTienCoc.getText() + lblTienCocText.getText();
			XuatHopDongForm hd = new XuatHopDongForm("Gò Vấp, ngày "+ LocalDate.now().getDayOfMonth() + " tháng " + LocalDate.now().getMonthValue() + " năm " + LocalDate.now().getYear(), 
					txtMaHD.getText(), 
					txtTenHD.getText(), 
					txtTenKH.getText(), 
					txtDaiDien.getText(), 
					txtThoaThuan.getText(),
					formatter.format(dtpBatDau.getDate()), 
					formatter.format(dtpKetThuc.getDate()), 
					giaTriHD, 
					tienCocHD);
			try {
				xf.xuatHD(hd);
				main.music.playSE(1);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			setTextError("Bạn phải chọn hợp đồng để xuất!");
		}
	}
	//Hàm get dữ liệu trên txt ra đối tượng hợp đồng
	private HopDong convertDataToHopDong() {
		String maHD = txtMaHD.getText();
		String tenHD = txtTenHD.getText();
		String tenKH = txtTenKH.getText();
		NhanVien nguoiDD = main.nv;
		Date ngayBD = dtpBatDau.getDate();
		Date ngayKT = dtpKetThuc.getDate();
		Double giaTri = Double.parseDouble(txtGiaTri.getText().replace(",", ""));
		Double tienCoc = Double.parseDouble(txtTienCoc.getText().replace(",", ""));
		String thoaThuan = txtThoaThuan.getText();
		String ghiChu = txtGhiChu.getText();
		boolean trangThai = (cmbTrangThai.getSelectedItem().equals("Hoàn thành"))?true:false;
		return new HopDong(maHD, tenHD, tenKH, nguoiDD, ngayBD, ngayKT, giaTri, tienCoc, thoaThuan, trangThai, ghiChu);
	}
	//Thêm hợp đồng từ giao diện vào csdl
	private void themHopDong() {
		if(validDataHD()==true) {
			HopDong hdNew = convertDataToHopDong();
			if(hdNew != null) {
				if(sp_Dao.getSanPhamTheoHopDong(hdNew.getMaHD()).size()>=1) {
					if(hd_Dao.getHopDongTheoMa(hdNew.getMaHD())!=null) {
						themHopDongVaoBang(hdNew);
						dsHD.add(hdNew);
						lblMessage.setText("Thêm thành công!");
						displayButtonSaveAndCancel(false);
						setEditableForTextField(false);
					}else {
						setTextError("Thêm thất bại! Trùng mã!");
					}
				}else {
					setTextError("Phải có ít nhất 1 sản phẩm trong hợp đồng!");
				}
			}else {
				setTextError("Thêm thất bại! Có lỗi xảy ra!");
			}
		}
	}
	// sửa một hợp đồng được chọn
	private void suaHopDong() {
		if(validDataHD()==true) {
			HopDong hdNew = convertDataToHopDong();
			if(hdNew != null) {
				if(hd_Dao.suaHopDong(hdNew)) {
					lblMessage.setText("Sửa thành công!");
					displayButtonSaveAndCancel(false);
					setEditableForTextField(false);
					xoaRong();
				}else {
					setTextError("Sửa thất bại! Không tìm thấy hợp đồng!");
				}
			}
		}
	}
	//get dữ liệu từ csdl lên table
	private void getDataToTable() {
		dsHD = hd_Dao.getAllHopDong();
		themTatCaHopDongVaoBang(dsHD);
	}
	//thêm một hợp đồng vào table 
	private void themHopDongVaoBang(HopDong hd) {
	    String[] row = new String[10];
	    row[0] = String.valueOf(dtblModelHD.getRowCount() + 1);
	    row[1] = hd.getMaHD();
	    row[2] = hd.getTenHD();
	    row[3] = hd.getTenKhachHang();
	    row[4] = hd.getNguoiDaiDien().getMaNV();
	    row[5] = new SimpleDateFormat("dd-MM-yyyy").format(hd.getNgayBatDau());
	    row[6] = new SimpleDateFormat("dd-MM-yyyy").format(hd.getNgayKetThuc());
	    row[7] = new DecimalFormat("#,###").format(hd.getGiaTriHD());
	    row[8] = hd.isTrangThai() ? "Hoàn thành" : "Đang thực hiện";
	    row[9] = hd.getGhiChu();
	    dtblModelHD.addRow(row);
	}
	//thêm một ds hợp đồng vào bảng
	private void themTatCaHopDongVaoBang(ArrayList<HopDong> list) {
		dtblModelHD.setRowCount(0);
	    for (HopDong hd : list) {
	        themHopDongVaoBang(hd);
	    }
	}
	//Xóa hợp đồng được chọn
	private void xoaHopDong() {
		String maHD = txtMaHD.getText();
		if(maHD != null) {
			if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa hợp đồng đã chọn?", "Cảnh báo!", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				if(hd_Dao.xoaHopDong(maHD)) {
					lblMessage.setText("Xóa thành công!");
					dsHD = hd_Dao.getAllHopDong();
					xoaRong();
				}else {
					setTextError("Xóa thất bại! Không tìm thấy hợp đồng!");
				}
			}
		}else {
			setTextError("Xóa thất bại! Có lỗi xảy ra!");
		}
	}
	//Hiển thị hợp đồng được chọn từ table lên bảng thông tin
	private void hienThiThongTinHD(int index) {
		txtMaHD.setText(dsHD.get(index).getMaHD());
		txtTenHD.setText(dsHD.get(index).getTenHD());
		txtTenKH.setText(dsHD.get(index).getTenKhachHang());
		txtDaiDien.setText(dsHD.get(index).getNguoiDaiDien().getHoTen());
		
		String dateString = (String) dtblModelHD.getValueAt(index, 5); // Lấy chuỗi ngày từ bảng
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
		    java.util.Date date = formatter.parse(dateString); // Chuyển đổi chuỗi thành java.util.Date
		    dtpBatDau.setDate(date); // Đặt giá trị cho JXDatePicker
		} catch (ParseException e) {
		    e.printStackTrace();
		}

		dateString = (String) dtblModelHD.getValueAt(index, 6); // Lấy chuỗi ngày từ bảng
		try {
		    java.util.Date date = formatter.parse(dateString); // Chuyển đổi chuỗi thành java.util.Date
		    dtpKetThuc.setDate(date); // Đặt giá trị cho JXDatePicker
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		txtGiaTri.setText(dtblModelHD.getValueAt(index, 7).toString());
		txtTienCoc.setText(new DecimalFormat("#,###").format(dsHD.get(index).getTienCoc()));
		txtThoaThuan.setText(dsHD.get(index).getThoaThuan());
		cmbTrangThai.setSelectedIndex(dsHD.get(index).isTrangThai()?1:0);
		txtGhiChu.setText(dsHD.get(index).getGhiChu());
	}
	//kiểm tra dữ liệu người dùng nhập vào có đúng không
	private boolean validDataHD() {
		String tenHD = txtTenHD.getText();
		String tenKH = txtTenKH.getText();
		NhanVien nguoiDD = main.nv;
		Date ngayBD = dtpBatDau.getDate();
		Date ngayKT = dtpKetThuc.getDate();
		String giaTri = txtGiaTri.getText().replace(",", "");
		String tienCoc = txtTienCoc.getText().replace(",", "");
		
		if(tenHD==null || tenHD.trim().length()<=0) {
			setTextError("Tên hợp đồng không được rỗng");
			return false;
		}
		if(tenKH==null || tenKH.trim().length()<=0) {
			setTextError("Tên khách hàng không được rỗng");
			return false;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date todayMidnight = cal.getTime();
		if(ngayBD.compareTo(todayMidnight) < 0) {
			setTextError("Ngày bắt đầu phải từ ngày hiện tại về sau");
			return false;
		}
		if(ngayKT.compareTo(ngayBD) < 0) {
			setTextError("Ngày kết thúc phải từ sau ngày bắt đầu");
			return false;
		}
		if(giaTri.matches("\\d+")==false || Double.parseDouble(giaTri)<0) {
			setTextError("Giá trị có định dạng #,### hoặc chỉ gồm số và >= 0");
			return false;
		}
		if(tienCoc.matches("\\d+")==false && Double.parseDouble(tienCoc)<0 || Double.parseDouble(tienCoc)>Double.parseDouble(giaTri)) {
			setTextError("Tiền cọc có định dạng #,### và Giá trị HĐ >= Tiền cọc >= 0");
			return false;
		}
		return true;
	}
	//thêm một sản phẩm vào table 
	private void themSanPhamVaoBang(SanPham sp) {
	    String[] row = new String[10];
	    row[0] = String.valueOf(dtblModelSP.getRowCount() + 1);
	    row[1] = sp.getMaSP();
	    row[2] = sp.getTenSP();
	    row[3] = String.valueOf(sp.getSoLuong());
	    row[4] = new DecimalFormat("#,###").format(sp.getDonGia());
	    dtblModelSP.addRow(row);
	}
	//thêm một ds hợp đồng vào bảng
	private void themTatCaSanPhamVaoBang(ArrayList<SanPham> list) {
		dtblModelSP.setRowCount(0);
	    for (SanPham sp : list) {
	        themSanPhamVaoBang(sp);
	    }
	}
	//kiểm tra dữ liệu người dùng nhập vào có đúng không
	private boolean validDataSP() {
		String tenSP = txtTenSP.getText();
		int soLuong = Integer.parseInt(spnSoLuong.getValue().toString());
		String donGia = txtDonGia.getText().replace(",", "");
		
		if(tenSP==null || tenSP.trim().length()<=0) {
			setTextError("Tên sản phẩm không được rỗng");
			return false;
		}
		if(soLuong < 0) {
			setTextError("Số lượng sản phẩm phải lớn hơn 0");
			return false;
		}if(donGia.matches("\\d+")==false && Double.parseDouble(donGia)<0) {
			setTextError("Đơn giá sản phẩm có định dạng #,### hoặc chỉ gồm số và >= 0");
			return false;
		}
		double tongTienSP = sp_Dao.tinhTongTien(txtMaHD.getText()) + soLuong*Double.parseDouble(donGia);
		double giaTriHD =  Double.parseDouble(txtGiaTri.getText().replace(",", ""));
		if(tongTienSP > giaTriHD) {
			setTextError("Tổng tiền của tất cả sản phẩm không được vượt quá giá trị hợp đồng: "+tongTienSP + " > " + giaTriHD);
			return false;
		}
		return true;
	}
	//chuyển dữ liệu thành đối tượng sản phẩm
	private SanPham convertDataToSanPham() {
		String maSP = txtMaSP.getText();
		String tenSP = txtTenSP.getText();
		String donVT = cmbDVT.getSelectedItem().toString();
		int soLuong = Integer.parseInt(spnSoLuong.getValue().toString());
		String donGia = txtDonGia.getText().replace(",", "");
		String yeuCau = txaYeuCau.getText();
		
		return new SanPham(maSP, new HopDong(txtMaHD.getText()), tenSP, donVT, soLuong, yeuCau, Double.parseDouble(donGia));
	}
	//Thêm sản phẩm từ giao diện vào csdl
	private void themSanpham() {
		if(hd_Dao.getHopDongTheoMa(txtMaHD.getText())!=null) {
			if(validDataSP()==true) {
				SanPham spNew = convertDataToSanPham();
				if(spNew != null) {
					if(sp_Dao.themSanPham(spNew)) {
						dsSP.add(spNew);
						themTatCaSanPhamVaoBang(dsSP);
						lblMessage.setText("Thêm thành công sản phẩm!");
						xoaRongSP();
					}else {
						setTextError("Thêm sản phẩm thất bại! Trùng mã!");
					}
				}else {
					setTextError("Thêm sản phẩm thất bại! Có lỗi xảy ra!");
				}
			}
		}else {
			themHDTamThoi();
		}
	}
	// sửa một sản phẩm được chọn
	private void suaSanPham() {
		if(tblSP.getSelectedRow()!=-1) {
			if(validDataSP()==true) {
				SanPham spNew = convertDataToSanPham();
				if(spNew != null) {
					if(sp_Dao.suaSanPham(spNew)) {
						dsSP.set(tblSP.getSelectedRow(), spNew);
						themTatCaSanPhamVaoBang(dsSP);
						lblMessage.setText("Sửa thành công sản phẩm!");
						xoaRongSP();
					}else {
						setTextError("Sửa sản phẩm thất bại! Không tìm thấy trong csdl!");
					}
				}else {
					setTextError("Sửa sản phẩm thất bại! Có lỗi xảy ra!");
				}
			}
		}else {
			setTextError("Bạn cần chọn một sản phẩm cần sửa!");
		}
	}
	//get dữ liệu từ csdl lên table
	private void getDataToTableSP() {
		dsSP = sp_Dao.getSanPhamTheoHopDong(txtMaHD.getText());
		themTatCaSanPhamVaoBang(dsSP);
	}

	//Xóa sản phẩm được chọn
	private void xoaSanPham() {
		int index = tblSP.getSelectedRow();
		if(index != -1 && sp_Dao.getSanPhamTheoHopDong(txtMaHD.getText()).size()>1) {
			if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sản phẩm đã chọn?", "Cảnh báo!", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				if(sp_Dao.xoaSanPham(tblSP.getValueAt(index, 1).toString())==true) {
					dsSP = sp_Dao.getSanPhamTheoHopDong(txtMaHD.getText());
					themTatCaSanPhamVaoBang(dsSP);
					lblMessage.setText("Xóa thành công sản phẩm!");
					xoaRongSP();
				}else {
					setTextError("Xóa thất bại! Không tìm thấy sản phẩm trong csdl!");
				}
			}
		}else {
			setTextError("Bạn cần chọn sản phẩm muốn xóa và không thể xóa toàn bộ sản phẩm");
		}
	}
	//Hiển thị sản phẩm được chọn từ table lên bảng thông tin
	private void hienThiThongTinSP(int index) {
		txtMaSP.setText(dsSP.get(index).getMaSP());
		txtTenSP.setText(dsSP.get(index).getTenSP());
		txtDonGia.setText(new DecimalFormat("#,###").format(dsSP.get(index).getDonGia()));
		modelSPN.setValue(dsSP.get(index).getSoLuong());
		for(int i = 0; i < cmbDVT.getItemCount(); i++) {
			if(cmbDVT.getItemAt(index).equals(dsSP.get(index).getDonViTinh())) {
				cmbDVT.setSelectedIndex(i);
				break;
			}
		}
	}
	//Thêm tạm thời hợp đồng vào csdl
	private void themHDTamThoi() {
		if(validDataHD()==true) {
			HopDong hdNew = convertDataToHopDong();
			if(hdNew != null) {
				if(hd_Dao.themHopDong(hdNew)) {
					return;
				}else {
					setTextError("Trùng mã!");
				}
			}else {
				setTextError("Có lỗi xảy ra!");
			}
		}
	}
	//xóa hợp đồng thạm thời
	private void xoaHopDongTamThoi() {
		String maHD = txtMaHD.getText();
		if(maHD != null) {
				if(hd_Dao.xoaHopDong(maHD)) {
				}
		}else {
			setTextError("Có lỗi xảy ra!");
		}
	}
	private void setTextError(String message) {
		main.music.playSE(3);
		lblMessage.setText(message);
	}
}