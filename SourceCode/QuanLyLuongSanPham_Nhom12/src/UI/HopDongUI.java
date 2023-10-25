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

import org.jdesktop.swingx.*;

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JTextField;
<<<<<<< HEAD
import javax.swing.SpinnerNumberModel;
=======
<<<<<<< HEAD
=======
import javax.swing.SwingUtilities;
>>>>>>> 8c46c642b3806a9430e40c735952ef82a0f23f83

import org.jdesktop.swingx.JXDatePicker;
>>>>>>> main

import CustomUI.CustomComboBoxUI;
import CustomUI.CustomListCellRenderer;
import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Util.SoundPlay;
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
	private RoundedButton btnThemHD, btnSuaHD, btnXoaHD, btnThemSP, btnSuaSP, btnXoaSP, btnLuu, btnHuy, btnIn, btnFocus;
	private DefaultTableModel dtblModelHD, dtblModelSP;
	private JTable tblHD, tblSP;
	private JTableHeader tbhHD, tbhSP;
	private JPanel pnlChucNang;
	private JComboBox cmbTrangThai;
	private JXDatePicker dtpBatDau, dtpKetThuc;
	private JLabel lblGiaTriText, lblTienCocText;
	private XuatForm xf;
	private Font fontText;
	/**
	 * Create the panel.
	 */
	public HopDongUI(MainUI main) {
		this.main = main;
		xf = new XuatForm();
		fontText = main.roboto_regular.deriveFont(Font.PLAIN, 13F);
		
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
		Box b1 = Box.createHorizontalBox();
		pnlThongTinSP.add(b1);
		
		JLabel lblMaSP = new JLabel(main.read_file_languages.getString("lblMaSP") + ":");
		lblMaSP.setForeground(textColor);
		lblMaSP.setFont(fontText);
		b1.add(lblMaSP);
		b1.add(Box.createHorizontalStrut(5));
		
		txtMaSP = new JTextField();
		txtMaSP.setForeground(textColor);
		txtMaSP.setFont(fontText);
		txtMaSP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 10, 2, 10)));
		txtMaSP.setBackground(bgColor);
		b1.add(txtMaSP);
		b1.add(Box.createHorizontalStrut(10));
		
		JLabel lblTenSP = new JLabel(main.read_file_languages.getString("lblTenSP") + ":");
		lblTenSP.setForeground(textColor);
		lblTenSP.setFont(fontText);
		b1.add(lblTenSP);
		b1.add(Box.createHorizontalStrut(5));
		
		txtTenSP = new JTextField();
		txtTenSP.setForeground(textColor);
		txtTenSP.setFont(fontText);
		txtTenSP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 10, 2, 10)));
		txtTenSP.setBackground(bgColor);
		b1.add(txtTenSP);
		
		pnlThongTinSP.add(Box.createVerticalStrut(20));
		
		//Tao box chua thong tin hang 2: ngayBD, ngayKT, giatri, tiencoc
		
		Box b2 = Box.createHorizontalBox();
		b2.setBackground(bgColor);
		pnlThongTinSP.add(b2);
		
		JLabel lblDVT = new JLabel(main.read_file_languages.getString("lblDVT") + ":");
		lblDVT.setForeground(textColor);
		lblDVT.setFont(fontText);
		b2.add(lblDVT);
		
		b2.add(Box.createHorizontalStrut(5));
		
		JComboBox cmbDVT = new JComboBox();
		cmbDVT.setModel(new DefaultComboBoxModel(new String[] {"Cái", "Bộ", "Đôi(Cặp)", "Hộp", "Gói", "M2", "Kg", "Lít"}));
		Border cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbDVT.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 5, 0, 5));
		cmbDVT.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbDVT.setBackground(bgColor);
		cmbDVT.setForeground(textColor);
		cmbDVT.setFont(fontText);
		b2.add(cmbDVT);

		b2.add(Box.createHorizontalStrut(10));
		
		JLabel lblDonGia = new JLabel(main.read_file_languages.getString("lblDonGia") + ":");
		lblDonGia.setForeground(textColor);
		lblDonGia.setFont(fontText);
		b2.add(lblDonGia);
		
		b2.add(Box.createHorizontalStrut(5));
		
		txtDonGia = new JTextField();
		txtDonGia.setForeground(textColor);
		txtDonGia.setFont(fontText);
		txtDonGia.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtDonGia.setBackground(bgColor);
		b2.add(txtDonGia);
		
		JLabel lblVND2 = new JLabel("VNĐ");
		lblVND2.setForeground(textColor);
		lblVND2.setFont(fontText);
		b2.add(lblVND2);
		
		b2.add(Box.createHorizontalStrut(10));
		
		JLabel lblSoLuong = new JLabel(main.read_file_languages.getString("lblSoLuong") + ":");
		lblSoLuong.setForeground(textColor);
		lblSoLuong.setFont(fontText);
		b2.add(lblSoLuong);
		
		b2.add(Box.createHorizontalStrut(5));
		
		SpinnerNumberModel model = new SpinnerNumberModel(100, 1, 100000, 100);
		JSpinner spnSoLuong = new JSpinner(model);
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 0, 5, 0));
		spnSoLuong.setBorder(cboBorder);
		spnSoLuong.setBackground(bgColor);
		spnSoLuong.setForeground(textColor);
		spnSoLuong.setFont(fontText);
		b2.add(spnSoLuong);
		
		pnlThongTinSP.add(Box.createVerticalStrut(20));
		
		Box b3 = Box.createHorizontalBox();
		pnlThongTinSP.add(b3);
		b3.setBackground(bgColor);
		JLabel lblYeuCau = new JLabel(main.read_file_languages.getString("lblYeuCau") + ":");
		lblYeuCau.setForeground(textColor);
		lblYeuCau.setFont(fontText);
		b3.add(lblYeuCau);
		b3.add(Box.createHorizontalStrut(35));
		
		JTextArea txaYeuCau = new JTextArea();
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
		
		// tạo jpanel chứa table sản phẩm
		JPanel pnlBangSP = new JPanel();
		pnlBangSP.setLayout(new BoxLayout(pnlBangSP, BoxLayout.X_AXIS));
		pnlThongTinSP.add(pnlBangSP, BorderLayout.CENTER);
		String cols[] = {
				main.read_file_languages.getString("lblMaSP"),
				main.read_file_languages.getString("lblTenSP"), 
				main.read_file_languages.getString("lblSoLuong"), 
				main.read_file_languages.getString("lblDonGia")};
		dtblModelSP = new DefaultTableModel(cols, 4);
		tblSP = new JTable(dtblModelSP);
		
		tbhSP = new JTableHeader(tblSP.getColumnModel());
		tbhSP.setReorderingAllowed(false);
		tbhSP.setBackground(componentColor);
		tbhSP.setForeground(Color.WHITE);
		tbhSP.setFont(fontText);
		tblSP.setTableHeader(tbhSP);
		
		tblSP.setRowHeight(20);
		tblSP.setPreferredScrollableViewportSize(tblSP.getPreferredSize());
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
		Box b4 = Box.createHorizontalBox();
		pnThongTinHD.add(b4);
		
		JLabel lblMaHD = new JLabel(main.read_file_languages.getString("lblMaHD") + ":");
		lblMaHD.setFont(fontText);
		lblMaHD.setForeground(textColor);
		b4.add(lblMaHD);
		b4.add(Box.createHorizontalStrut(5));
		
		txtMaHD = new JTextField();
		txtMaHD.setFont(fontText);
		txtMaHD.setForeground(textColor);
		txtMaHD.setBackground(bgColor);
		txtMaHD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		b4.add(txtMaHD);
		b4.add(Box.createHorizontalStrut(10));
		
		JLabel lblTenHD = new JLabel(main.read_file_languages.getString("lblTenHD") + ":");
		lblTenHD.setForeground(textColor);
		lblTenHD.setFont(fontText);
		b4.add(lblTenHD);
		b4.add(Box.createHorizontalStrut(10));
		
		txtTenHD = new JTextField();
		txtTenHD.setForeground(textColor);
		txtTenHD.setFont(fontText);
		txtTenHD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtTenHD.setBackground(bgColor);
		b4.add(txtTenHD);
		b4.add(Box.createHorizontalStrut(30));
		
		JLabel lblTenKH = new JLabel(main.read_file_languages.getString("lblKH") + ":");
		lblTenKH.setForeground(textColor);
		lblTenKH.setFont(fontText);
		b4.add(lblTenKH);
		b4.add(Box.createHorizontalStrut(10));
		
		txtTenKH = new JTextField();
		txtTenKH.setForeground(textColor);
		txtTenKH.setFont(fontText);
		txtTenKH.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtTenKH.setBackground(bgColor);
		b4.add(txtTenKH);
		
		pnThongTinHD.add(Box.createVerticalStrut(20));
		
		//Tao box chua thong tin hang 2: ngayBD, ngayKT, giatri, tiencoc
		Box b5 = Box.createHorizontalBox();
		pnThongTinHD.add(b5);
		
		JLabel lblDaiDien = new JLabel(main.read_file_languages.getString("lblDaiDien") + ":");
		lblDaiDien.setForeground(textColor);
		lblDaiDien.setFont(fontText);
		b5.add(lblDaiDien);
		b5.add(Box.createHorizontalStrut(10));
		
		txtDaiDien = new JTextField();
		txtDaiDien.setForeground(textColor);
		txtDaiDien.setFont(fontText);
		txtDaiDien.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtDaiDien.setBackground(bgColor);
		b5.add(txtDaiDien);
		
		b5.add(Box.createHorizontalStrut(30));
		
		// ngay bat dau
		JLabel lblNgayBD = new JLabel(main.read_file_languages.getString("lblNgayBD") + ":");
		lblNgayBD.setForeground(textColor);
		lblNgayBD.setFont(fontText);
		b5.add(lblNgayBD);
		
		b5.add(Box.createHorizontalStrut(10));
		
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
		b5.add(dtpBatDau);
		
		b5.add(Box.createHorizontalStrut(30));
		
		// ngay ket thuc
		JLabel lblNgayKetThuc = new JLabel(main.read_file_languages.getString("lblNgayKT") + ":");
		lblNgayKetThuc.setForeground(textColor);
		lblNgayKetThuc.setFont(fontText);
		b5.add(lblNgayKetThuc);
		
		b5.add(Box.createHorizontalStrut(10));
		
		
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
		b5.add(dtpKetThuc);
		
		pnThongTinHD.add(Box.createVerticalStrut(20));
		
		//Tạo box chứa thông tin hàng 3: thỏa thuận, trạng thái, ghi chú
		Box b6 = Box.createHorizontalBox();
		pnThongTinHD.add(b6);
		
		JLabel lblGiaTri = new JLabel(main.read_file_languages.getString("lblGiaTri") + ":");
		lblGiaTri.setForeground(textColor);
		lblGiaTri.setFont(fontText);
		b6.add(lblGiaTri);
		b6.add(Box.createHorizontalStrut(10));
		
		txtGiaTri = new JTextField();
		txtGiaTri.setForeground(textColor);
		txtGiaTri.setFont(fontText);
		txtGiaTri.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtGiaTri.setBackground(bgColor);
		b6.add(txtGiaTri);
		
		JLabel lblVND1 = new JLabel("VNĐ");
		lblVND1.setForeground(textColor);
		lblVND1.setFont(fontText);
		b6.add(lblVND1);
		
		lblGiaTriText = new JLabel("");
		lblGiaTriText.setForeground(textColor);
		lblGiaTriText.setFont(fontText);
		b6.add(lblGiaTriText);

		b6.add(Box.createHorizontalStrut(30));
		
		JLabel lblTienCoc = new JLabel(main.read_file_languages.getString("lblTienCoc") + ":");
		lblTienCoc.setForeground(textColor);
		lblTienCoc.setFont(fontText);
		b6.add(lblTienCoc);
		
		b6.add(Box.createHorizontalStrut(10));
		
		txtTienCoc = new JTextField();
		txtTienCoc.setForeground(textColor);
		txtTienCoc.setFont(fontText);
		txtTienCoc.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtTienCoc.setBackground(bgColor);
		b6.add(txtTienCoc);
		
		JLabel lblVND3 = new JLabel("VNĐ");
		lblVND3.setForeground(textColor);
		lblVND3.setFont(fontText);
		b6.add(lblVND3);
		
		lblTienCocText = new JLabel("");
		lblTienCocText.setForeground(textColor);
		lblTienCocText.setFont(fontText);
		b6.add(lblTienCocText);
		b6.add(Box.createHorizontalStrut(30));
		
		JLabel lblThoaThuan = new JLabel(main.read_file_languages.getString("lblThoaThuan") + ":");
		lblThoaThuan.setForeground(Color.BLACK);
		lblThoaThuan.setFont(fontText);
		b6.add(lblThoaThuan);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		b6.add(horizontalStrut);
		
		txtThoaThuan = new JTextField();
		txtThoaThuan.setForeground(Color.BLACK);
		txtThoaThuan.setFont(fontText);
		txtThoaThuan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtThoaThuan.setBackground(Color.WHITE);
		b6.add(txtThoaThuan);
		
		Box b7 = Box.createHorizontalBox();
		pnThongTinHD.add(Box.createVerticalStrut(20));
		pnThongTinHD.add(b7);
		
		JLabel lblTrangThai = new JLabel(main.read_file_languages.getString("lblTrangThai") + ":");
		lblTrangThai.setForeground(Color.BLACK);
		lblTrangThai.setFont(fontText);
		b7.add(lblTrangThai);
		
		b7.add(Box.createHorizontalStrut(10));
		
		cmbTrangThai = new JComboBox();
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbTrangThai.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbTrangThai.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbTrangThai.addItem(main.read_file_languages.getString("lblTrangThai1"));
		b7.add(cmbTrangThai);
		cmbTrangThai.setBackground(bgColor);
		cmbTrangThai.setForeground(textColor);
		cmbTrangThai.setFont(fontText);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(30);
		b7.add(horizontalStrut_3);
		
		JLabel lblGhiChu = new JLabel(main.read_file_languages.getString("lblGhiChu") + ":");
		lblGhiChu.setForeground(textColor);
		lblGhiChu.setFont(fontText);
		b7.add(lblGhiChu);
		
		b7.add(Box.createHorizontalStrut(10));
		
		txtGhiChu = new JTextField();
		txtGhiChu.setForeground(textColor);
		txtGhiChu.setFont(fontText);
		txtGhiChu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		txtGhiChu.setBackground(bgColor);
		b7.add(txtGhiChu);
		
		pnThongTinHD.add(Box.createVerticalStrut(20));
		
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
		String colsHD[] = {main.read_file_languages.getString("lblMaHD"),
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
		tblHD.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblHD.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblHD.getColumnModel().getColumn(2).setPreferredWidth(175);
		tblHD.getColumnModel().getColumn(3).setPreferredWidth(175);
		tblHD.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblHD.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblHD.getColumnModel().getColumn(6).setPreferredWidth(200);
		tblHD.getColumnModel().getColumn(7).setPreferredWidth(100);
		tblHD.getColumnModel().getColumn(7).setPreferredWidth(150);
		
		//Tạo jscrollpane để tạo scroll cho bảng hợp đồng
		JScrollPane scrHD = new JScrollPane(tblHD,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangHD.add(scrHD);
		
		btnThemHD.addActionListener(this);
		btnSuaHD.addActionListener(this);
		btnXoaHD.addActionListener(this);
		btnIn.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		
		btnThemHD.addMouseListener(this);
		btnSuaHD.addMouseListener(this);
		btnXoaHD.addMouseListener(this);
		btnIn.addMouseListener(this);
		btnLuu.addMouseListener(this);
		btnHuy.addMouseListener(this);
		
		//Không thể thao tác với button lưu và hủy
		displayButtonSaveAndCancel(false);
		
		//Không thể chỉnh sửa txt
		setEditableForTextField(false);
		
		//Set giá trị mặc định để hiển thị
//		txtMaHD.setText("HD12345");
//		txtTenKH.setText("Nguyễn Văn Phong");
//		txtTenHD.setText("Hợp đồng hợp tác ABC");
//		txtDaiDien.setText("Phạm Hữu Thuận");
//		txtGiaTri.setText(formatMoneyText("10000000000"));
//		lblGiaTriText.setText("    (" + formatMoneyToText(Double.parseDouble(txtGiaTri.getText().replaceAll(",", ""))) + " VNĐ)     ");
//		txtTienCoc.setText(formatMoneyText("1000000000"));
//		lblTienCocText.setText("     (" + formatMoneyToText(Double.parseDouble(txtTienCoc.getText().replaceAll(",", ""))) + " VNĐ)");
//		txtThoaThuan.setText("Thỏa thuận giữa 2 bên bao gồm: điều 1, điều 2, điều 3,...");
	}
	@Override
	public void mouseClicked(MouseEvent e) {

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
		Object o = e.getSource();
		main.music.playSE(2);
		if(o == btnThemHD) {
			displayButtonSaveAndCancel(true);
			setEditableForTextField(true);
			xoaRong();	
		}
		if(o == btnSuaHD) {
			displayButtonSaveAndCancel(true);
			setEditableForTextField(true);
			
		}
		if(o == btnXoaHD) {
			
		}
		if(o == btnIn) {
			xuatHopDong();
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
	private void setEditableForTextField(boolean edit) {
		if(edit == true) {
			txtMaHD.setEditable(true);
			txtTenHD.setEditable(true);
			txtTenKH.setEditable(true);
			txtDaiDien.setEditable(true);
			dtpBatDau.setEditable(true);
			dtpKetThuc.setEditable(true);
			txtGiaTri.setEditable(true);
			txtTienCoc.setEditable(true);
			txtThoaThuan.setEditable(true);
			txtGhiChu.setEditable(true);
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
			cmbTrangThai.setEditable(false);
			txtGhiChu.setEditable(false);
		}
	}
	//Xóa toàn bộ dữ liệt trên bảng thông tin
	private void xoaRong() {
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
	public void xuatHopDong() {
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
	}
}
