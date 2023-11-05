package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;

import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

public class TimKiemSanPhamUI extends JPanel implements ActionListener, MouseListener{
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField txtMaHD, txtMaSP, txtTenSP, txtDonGia;
	private RoundedButton btnTimKiem, btnXoaRong, btnXuat, btnFocus;
	private DefaultTableModel dtblModelSP;
	private JTable tblSP;
	private JTableHeader tbhSP;
	private JPanel pnlChucNang;
	
	private JTextField txtMaHDS;
	private JTextField txtMaSPS;
	private JTextField txtTenSPS;
	private JTextField txtDonGiaSan;
	private JTextField txtDonGiaTran;
	/**
	 * Create the panel.
	 */
	public TimKiemSanPhamUI(MainUI main) {
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
		pnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pnlNorth.setBackground(bgColor);
		pnlSanPham.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BorderLayout(0, 0));
		
		//Tao jpanel Title
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		pnlTitle.setBackground(bgColor);
		pnlNorth.add(pnlTitle, BorderLayout.NORTH);
		
		//Tiêu đề
		JLabel lblTitle = new JLabel(main.read_file_languages.getString("title_product_search"));
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
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("title_border_searchbox"));
		titleBorderTTSP.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlTimKiem.setBorder(BorderFactory.createCompoundBorder(titleBorderTTSP, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		b.add(pnlTimKiem);
		b.add(Box.createHorizontalStrut(50));
		
		Box b2 = Box.createHorizontalBox();
		pnlTimKiem.add(b2);
		
		JLabel lblMaHDS = new JLabel(main.read_file_languages.getString("lblMaHD") + ":");
		lblMaHDS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblMaHDS.setForeground(textColor);
		b2.add(lblMaHDS);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		b2.add(horizontalStrut);
		
		txtMaHDS = new JTextField();
		txtMaHDS.setColumns(10);
		txtMaHDS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaHDS.setForeground(textColor);
		txtMaHDS.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaHDS.setBackground(Color.WHITE);
		b2.add(txtMaHDS);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(30);
		b2.add(horizontalStrut_2);
		
		JLabel lblMaSPS = new JLabel(main.read_file_languages.getString("lblMaSPSearch") + ":");
		lblMaSPS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblMaSPS.setForeground(textColor);
		b2.add(lblMaSPS);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		b2.add(horizontalStrut_3);
		
		txtMaSPS = new JTextField();
		txtMaSPS.setColumns(10);
		txtMaSPS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaSPS.setForeground(textColor);
		txtMaSPS.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaSPS.setBackground(Color.WHITE);
		b2.add(txtMaSPS);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		pnlTimKiem.add(verticalStrut_2);
		
		Box b3 = Box.createHorizontalBox();
		pnlTimKiem.add(b3);
		
		JLabel lblTenSPS = new JLabel(main.read_file_languages.getString("lblTenSPSearch") + ":");
		lblTenSPS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblTenSPS.setForeground(textColor);
		b3.add(lblTenSPS);
		
		b3.add(Box.createHorizontalStrut(10));
		
		txtTenSPS = new JTextField();
		txtTenSPS.setColumns(10);
		txtTenSPS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenSPS.setForeground(textColor);
		txtTenSPS.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
								BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtTenSPS.setBackground(Color.WHITE);
		b3.add(txtTenSPS);
		
		Component horizontalStrut_2_1 = Box.createHorizontalStrut(10);
		b3.add(horizontalStrut_2_1);
		
		JLabel lblDVTS = new JLabel(main.read_file_languages.getString("lblSoLuong")+ " (>=):");
		lblDVTS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblDVTS.setForeground(textColor);
		b3.add(lblDVTS);
		
		b3.add(Box.createHorizontalStrut(10));
		
		SpinnerNumberModel modelSL1 = new SpinnerNumberModel(10, 1, 1000000, 100);
		JSpinner spnSoLuongS = new JSpinner(modelSL1);
		Border cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 0, 5, 0));
		spnSoLuongS.setBorder(cboBorder);
		spnSoLuongS.setBackground(bgColor);
		spnSoLuongS.setForeground(textColor);
		spnSoLuongS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 14F));
		b3.add(spnSoLuongS);
		
		pnlTimKiem.add(Box.createVerticalStrut(20));
		
		Box b4 = Box.createHorizontalBox();
		pnlTimKiem.add(b4);

		pnlTimKiem.add(Box.createVerticalStrut(20));
		
		JLabel lblDonGiaS = new JLabel(main.read_file_languages.getString("lblDonGia") + ":");
		lblDonGiaS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblDonGiaS.setForeground(textColor);
		b4.add(lblDonGiaS);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(10);
		b4.add(horizontalStrut_5);
		
		txtDonGiaSan = new JTextField();
		txtDonGiaSan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtDonGiaSan.setForeground(textColor);
		txtDonGiaSan.setColumns(10);
		txtDonGiaSan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
								BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtDonGiaSan.setBackground(bgColor);
		b4.add(txtDonGiaSan);
		
		Component horizontalStrut_3_2_1 = Box.createHorizontalStrut(10);
		b4.add(horizontalStrut_3_2_1);
		
		JLabel lblNoi = new JLabel("-"+ main.read_file_languages.getString("lblNoi") +"-");
		lblNoi.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		lblNoi.setForeground(textColor);
		b4.add(lblNoi);
		
		Component horizontalStrut_3_2 = Box.createHorizontalStrut(10);
		b4.add(horizontalStrut_3_2);
		
		txtDonGiaTran = new JTextField();
		txtDonGiaTran.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtDonGiaTran.setForeground(textColor);
		txtDonGiaTran.setColumns(10);
		txtDonGiaTran.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
								BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtDonGiaTran.setBackground(Color.WHITE);
		b4.add(txtDonGiaTran);
		
		JLabel lblVND2_2 = new JLabel("VNĐ");
		lblVND2_2.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblVND2_2.setForeground(textColor);
		b4.add(lblVND2_2);
	
		
		//Tao jpanel Thong tin hop dong
		JPanel pnlThongTinSP = new JPanel();
		pnlThongTinSP.setLayout(new BoxLayout(pnlThongTinSP, BoxLayout.Y_AXIS));
		pnlThongTinSP.setBackground(bgColor);
		titleBorderTTSP = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("info_product"));
		titleBorderTTSP.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlThongTinSP.setBorder(BorderFactory.createCompoundBorder(titleBorderTTSP, BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		b.add(pnlThongTinSP);
		
		// Tao box chua cac phan tu hang 1: maHD, ma SP, tenSP
		Box b1 = Box.createHorizontalBox();
		pnlThongTinSP.add(b1);
		
		JLabel lblMaHD = new JLabel(main.read_file_languages.getString("lblMaHD") + ":");
		lblMaHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblMaHD.setForeground(textColor);
		b1.add(lblMaHD);
		b1.add(Box.createHorizontalStrut(10));
		
		txtMaHD = new JTextField();
		txtMaHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaHD.setForeground(textColor);
		txtMaHD.setBackground(bgColor);
		txtMaHD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		b1.add(txtMaHD);
		b1.add(Box.createHorizontalStrut(30));
		
		JLabel lblMaSP = new JLabel(main.read_file_languages.getString("lblMaSP") + ":");
		lblMaSP.setForeground(textColor);
		lblMaSP.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b1.add(lblMaSP);
		b1.add(Box.createHorizontalStrut(10));
		
		txtMaSP = new JTextField();
		txtMaSP.setForeground(textColor);
		txtMaSP.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaSP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaSP.setBackground(bgColor);
		b1.add(txtMaSP);
		b1.add(Box.createHorizontalStrut(30));
		
		JLabel lblTenSP = new JLabel(main.read_file_languages.getString("lblTenSP") + ":");
		lblTenSP.setForeground(textColor);
		lblTenSP.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b1.add(lblTenSP);
		b1.add(Box.createHorizontalStrut(10));
		
		txtTenSP = new JTextField();
		txtTenSP.setForeground(textColor);
		txtTenSP.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenSP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtTenSP.setBackground(bgColor);
		b1.add(txtTenSP);
		
		pnlThongTinSP.add(Box.createVerticalStrut(40));
		
		//
		
		Box b5 = Box.createHorizontalBox();
		b5.setBackground(bgColor);
		pnlThongTinSP.add(b5);
		
		JLabel lblDVT = new JLabel(main.read_file_languages.getString("lblDVT") + ":");
		lblDVT.setForeground(textColor);
		lblDVT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b5.add(lblDVT);
		
		b5.add(Box.createHorizontalStrut(15));
		
		JComboBox cmbDVT = new JComboBox();
		cmbDVT.setModel(new DefaultComboBoxModel(new String[] {"Cái", "Bộ", "Đôi(Cặp)", "Hộp", "Gói", "M2", "Kg", "Lít"}));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbDVT.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbDVT.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbDVT.setBackground(bgColor);
		cmbDVT.setForeground(textColor);
		cmbDVT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b5.add(cmbDVT);

		b5.add(Box.createHorizontalStrut(40));
		
		JLabel lblDonGia = new JLabel(main.read_file_languages.getString("lblDonGia") + ":");
		lblDonGia.setForeground(textColor);
		lblDonGia.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b5.add(lblDonGia);
		
		b5.add(Box.createHorizontalStrut(15));
		
		txtDonGia = new JTextField();
		txtDonGia.setForeground(textColor);
		txtDonGia.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtDonGia.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtDonGia.setBackground(bgColor);
		txtDonGia.setColumns(7);
		b5.add(txtDonGia);
		
		JLabel lblVND2 = new JLabel("VNĐ");
		lblVND2.setForeground(textColor);
		lblVND2.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b5.add(lblVND2);
		
		b5.add(Box.createHorizontalStrut(40));
		
		JLabel lblSoLuong = new JLabel(main.read_file_languages.getString("lblSoLuong") + ":");
		lblSoLuong.setForeground(textColor);
		lblSoLuong.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b5.add(lblSoLuong);
		
		b5.add(Box.createHorizontalStrut(15));
		
		SpinnerNumberModel modelSL2 = new SpinnerNumberModel(10, 1, 1000000, 100);
		JSpinner spnSoLuong = new JSpinner(modelSL2);
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 0, 5, 0));
		spnSoLuong.setBorder(cboBorder);
		spnSoLuong.setBackground(bgColor);
		spnSoLuong.setForeground(textColor);
		spnSoLuong.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b5.add(spnSoLuong);
		b5.add(Box.createHorizontalStrut(75));
		
		pnlThongTinSP.add(Box.createVerticalStrut(20));
		
		Box b6 = Box.createHorizontalBox();
		pnlThongTinSP.add(b6);
		b6.setBackground(bgColor);
		JLabel lblYeuCau = new JLabel(main.read_file_languages.getString("lblYeuCau") + ":");
		lblYeuCau.setForeground(textColor);
		lblYeuCau.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b6.add(lblYeuCau);
		b6.add(Box.createHorizontalStrut(35));
		
		JTextArea txaYeuCau = new JTextArea();
		txaYeuCau.setRows(3);
		txaYeuCau.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txaYeuCau.setForeground(textColor);
		txaYeuCau.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		JScrollPane scrYC = new JScrollPane(txaYeuCau,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		b6.add(scrYC);
		
		//Khởi tạo jpanel chức năng chứa các button chức năng: thêm, sửa, xóa, lưu, hủy
		pnlChucNang = new JPanel();
		pnlTimKiem.add(pnlChucNang);
		pnlChucNang.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlChucNang.setBackground(bgColor);
		pnlChucNang.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		btnTimKiem = new RoundedButton(main.read_file_languages.getString("btnTimKiem"), null, 20, 0, 1.0f);
		btnTimKiem.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setBackground(Color.decode("#3B71CA"));
		btnTimKiem.setIcon(new ImageScaler("/image/searchwhite_icon.png", 24, 24).getScaledImageIcon());
		btnTimKiem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnTimKiem);
		pnlChucNang.add(Box.createHorizontalStrut(25));
		
		btnXoaRong = new RoundedButton(main.read_file_languages.getString("btnXoaRong"), null, 20, 0, 1.0f);
		btnXoaRong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setBackground(Color.decode("#ffc107"));
		btnXoaRong.setIcon(new ImageScaler("/image/refresh-arrow_white_icon.png", 24, 24).getScaledImageIcon());
		btnXoaRong.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoaRong);
		
		// tạo jpanel chứa table sản phẩm tìm được
		JPanel pnlBangSP = new JPanel();
		titleBorderTTSP = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("border_title_SP"));
		titleBorderTTSP.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangSP.setBorder(BorderFactory.createCompoundBorder(titleBorderTTSP, BorderFactory.createEmptyBorder(0, 10, 5, 10)));
		pnlBangSP.setBackground(bgColor);
		pnlSanPham.add(pnlBangSP, BorderLayout.CENTER);
		pnlBangSP.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlXuat = new JPanel();
		pnlXuat.setBackground(bgColor);
		pnlXuat.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlBangSP.add(pnlXuat, BorderLayout.NORTH);
		
		btnXuat = new RoundedButton(main.read_file_languages.getString("btn_XuatDS"), null, 5, 0, 1.0f);
		btnXuat.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setBackground(Color.decode("#28a745"));
		btnXuat.setIcon(new ImageScaler("/image/printer_icon.png", 20, 20).getScaledImageIcon());
		btnXuat.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		pnlXuat.add(btnXuat);
		
		String cols[] = {
				main.read_file_languages.getString("stt"),
				main.read_file_languages.getString("tbhMaHDSP"),
				main.read_file_languages.getString("lblMaSP"),
				main.read_file_languages.getString("lblTenSP"), 
				main.read_file_languages.getString("lblDVT"), 
				main.read_file_languages.getString("lblSoLuong"), 
				main.read_file_languages.getString("lblDonGia"), 
				main.read_file_languages.getString("lblYeuCau")};
		dtblModelSP = new DefaultTableModel(cols, 0);
		tblSP = new JTable(dtblModelSP);

		tbhSP = new JTableHeader(tblSP.getColumnModel());
		tbhSP.setReorderingAllowed(false);
		tbhSP.setBackground(componentColor);
		tbhSP.setForeground(Color.WHITE);
		tbhSP.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblSP.setTableHeader(tbhSP);
		
		tblSP.setRowHeight(20);
		tblSP.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblSP.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblSP.getColumnModel().getColumn(2).setPreferredWidth(200);
		tblSP.getColumnModel().getColumn(3).setPreferredWidth(175);
		tblSP.getColumnModel().getColumn(4).setPreferredWidth(175);
		tblSP.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblSP.getColumnModel().getColumn(6).setPreferredWidth(150);
		tblSP.getColumnModel().getColumn(7).setPreferredWidth(200);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm tìm được
		JScrollPane scrSP = new JScrollPane(tblSP,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangSP.add(scrSP, BorderLayout.CENTER);
		
		pnlNorth.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
		
		btnTimKiem.addActionListener(this);
		btnXoaRong.addActionListener(this);
		
		btnTimKiem.addMouseListener(this);
		btnXoaRong.addMouseListener(this);
		
		
		//Không thể chỉnh sửa txt
		setEditableForTextField(false);
		
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
		if(o == btnTimKiem) {
			setEditableForTextField(true);
			xoaRong();
			
		}
		if(o == btnXoaRong) {
			setEditableForTextField(true);
			
		}
	}
	private void setEditableForTextField(boolean edit) {
		if(edit == true) {
			txtMaHD.setEditable(true);
			txtMaSP.setEditable(true);
			txtTenSP.setEditable(true);
			txtDonGia.setEditable(true);
		}else {
			txtMaHD.setEditable(false);
			txtMaSP.setEditable(false);
			txtTenSP.setEditable(false);
			txtDonGia.setEditable(false);
		}
	}
	private void xoaRong() {
		txtMaHD.setText("");
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtDonGia.setText("");
	}
	//hiển thị border cho button được user nhấn
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
}
