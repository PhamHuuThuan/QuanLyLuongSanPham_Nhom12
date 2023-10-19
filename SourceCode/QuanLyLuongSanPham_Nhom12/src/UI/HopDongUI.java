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

import CustomUI.CustomComboBoxUI;
import CustomUI.CustomListCellRenderer;
import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Util.Sound;
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
	private JTextField txtMaHD, txtTenHD, txtTenKH, txtDaiDien, txtGiaTri, txtTienCoc, txtThoaThuan, txtGhiChu;
	private RoundedButton btnThem, btnSua, btnXoa, btnLuu, btnHuy, btnIn,btnFocus;
	private DefaultTableModel dtblModel;
	private JTable tblHD;
	private JTableHeader tbhHD;
	private JPanel pnlChucNang;
	private JComboBox cmbTrangThai;
	private JXDatePicker dtpBatDau, dtpKetThuc;
	private JLabel lblGiaTriText, lblTienCocText;
	private XuatForm xf;
	private SoundPlay music = new SoundPlay();
	/**
	 * Create the panel.
	 */
	public HopDongUI(MainUI main) {
		this.main = main;
		xf = new XuatForm();
		
		//set gia tri cho jpanel HopDong
		setLayout(new BorderLayout(0, 0));
		setBackground(bgColor);
		
		//tao jpanel chua Title va Thong tin HD
		JPanel pnNorth = new JPanel();
		pnNorth.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		pnNorth.setBackground(bgColor);
		add(pnNorth, BorderLayout.NORTH);
		pnNorth.setLayout(new BorderLayout(0, 0));
		
		//Tao jpanel Title
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(bgColor);
		pnNorth.add(pnTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("QUẢN LÝ HỢP ĐỒNG");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnTitle.add(lblTitle);
		
		//Tao jpanel Thong tin hop dong
		JPanel pnThongTinHD = new JPanel();
		pnThongTinHD.setLayout(new BoxLayout(pnThongTinHD, BoxLayout.Y_AXIS));
		pnThongTinHD.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Thông tin hợp đồng");
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnThongTinHD.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		pnNorth.add(pnThongTinHD, BorderLayout.CENTER);
		
		// Tao box chua cac phan tu hang 1: maHD, tenHD, tenKH, nguoiDaiDien
		Box b1 = Box.createHorizontalBox();
		pnThongTinHD.add(b1);
		
		JLabel lblMaHD = new JLabel("Mã HĐ:");
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
		
		JLabel lblTenHD = new JLabel("Tên HĐ:");
		lblTenHD.setForeground(textColor);
		lblTenHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b1.add(lblTenHD);
		b1.add(Box.createHorizontalStrut(10));
		
		txtTenHD = new JTextField();
		txtTenHD.setForeground(textColor);
		txtTenHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenHD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtTenHD.setBackground(bgColor);
		b1.add(txtTenHD);
		b1.add(Box.createHorizontalStrut(30));
		
		JLabel lblTenKH = new JLabel("Khách hàng:");
		lblTenKH.setForeground(textColor);
		lblTenKH.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b1.add(lblTenKH);
		b1.add(Box.createHorizontalStrut(10));
		
		txtTenKH = new JTextField();
		txtTenKH.setForeground(textColor);
		txtTenKH.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenKH.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtTenKH.setBackground(bgColor);
		b1.add(txtTenKH);
		b1.add(Box.createHorizontalStrut(30));
		
		JLabel lblDaiDien = new JLabel("Người đại diện:");
		lblDaiDien.setForeground(textColor);
		lblDaiDien.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b1.add(lblDaiDien);
		b1.add(Box.createHorizontalStrut(10));
		
		txtDaiDien = new JTextField();
		txtDaiDien.setForeground(textColor);
		txtDaiDien.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtDaiDien.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtDaiDien.setBackground(bgColor);
		b1.add(txtDaiDien);
		
		pnThongTinHD.add(Box.createVerticalStrut(20));
		
		//Tao box chua thong tin hang 2: ngayBD, ngayKT, giatri, tiencoc
		Box b2 = Box.createHorizontalBox();
		pnThongTinHD.add(b2);
		
		// ngay bat dau
		JLabel lblNgayBD = new JLabel("Ngày bắt đầu:");
		lblNgayBD.setForeground(textColor);
		lblNgayBD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblNgayBD);
		
		b2.add(Box.createHorizontalStrut(10));
		
		dtpBatDau = new JXDatePicker(new Date());
		dtpBatDau.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtpBatDau.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
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
		b2.add(dtpBatDau);
		
		b2.add(Box.createHorizontalStrut(30));
		
		// ngay ket thuc
		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc");
		lblNgayKetThuc.setForeground(textColor);
		lblNgayKetThuc.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblNgayKetThuc);
		
		b2.add(Box.createHorizontalStrut(10));
		
		
		dtpKetThuc = new JXDatePicker(new Date());
		dtpKetThuc.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtpKetThuc.getEditor().setForeground(textColor);
		dtpKetThuc.getEditor().setBackground(bgColor);
		dtpKetThuc.setLocale(new Locale("vi", "VN"));
		dtpKetThuc.setForeground(textColor);
		dtpKetThuc.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dtpKetThuc.setBackground(bgColor);
		JButton btnDateKT = (JButton) dtpKetThuc.getComponent(1);
		btnDateKT.setIcon(new ImageScaler("/image/calendarfinish_icon.png", 18, 18).getScaledImageIcon());
		btnDateKT.setBackground(bgColor);
		btnDateKT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		b2.add(dtpKetThuc);
		
		b2.add(Box.createHorizontalStrut(30));
		
		JLabel lblGiaTri = new JLabel("Giá trị:");
		lblGiaTri.setForeground(textColor);
		lblGiaTri.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblGiaTri);
		
		b2.add(Box.createHorizontalStrut(10));
		
		txtGiaTri = new JTextField();
		txtGiaTri.setForeground(textColor);
		txtGiaTri.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtGiaTri.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtGiaTri.setBackground(bgColor);
		b2.add(txtGiaTri);
		
		JLabel lblVND1 = new JLabel("VNĐ");
		lblVND1.setForeground(textColor);
		lblVND1.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblVND1);
		
		lblGiaTriText = new JLabel("");
		lblGiaTriText.setForeground(textColor);
		lblGiaTriText.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblGiaTriText);

		b2.add(Box.createHorizontalStrut(30));
		
		JLabel lblTienCoc = new JLabel("Tiền cọc:");
		lblTienCoc.setForeground(textColor);
		lblTienCoc.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblTienCoc);
		
		b2.add(Box.createHorizontalStrut(10));
		
		txtTienCoc = new JTextField();
		txtTienCoc.setForeground(textColor);
		txtTienCoc.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTienCoc.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtTienCoc.setBackground(bgColor);
		b2.add(txtTienCoc);
		
		JLabel lblVND2 = new JLabel("VNĐ");
		lblVND2.setForeground(textColor);
		lblVND2.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblVND2);
		
		lblTienCocText = new JLabel("");
		lblTienCocText.setForeground(textColor);
		lblTienCocText.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblTienCocText);
		
		pnThongTinHD.add(Box.createVerticalStrut(20));
		
		//Tạo box chứa thông tin hàng 3: thỏa thuận, trạng thái, ghi chú
		Box b3 = Box.createHorizontalBox();
		pnThongTinHD.add(b3);
		
		JLabel lblThoaThuan = new JLabel("Thỏa thuận:");
		lblThoaThuan.setForeground(Color.BLACK);
		lblThoaThuan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b3.add(lblThoaThuan);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		b3.add(horizontalStrut);
		
		txtThoaThuan = new JTextField();
		txtThoaThuan.setForeground(Color.BLACK);
		txtThoaThuan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtThoaThuan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtThoaThuan.setBackground(Color.WHITE);
		b3.add(txtThoaThuan);
		
		b3.add(Box.createHorizontalStrut(30));
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setForeground(Color.BLACK);
		lblTrangThai.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b3.add(lblTrangThai);
		
		b3.add(Box.createHorizontalStrut(10));
		
		cmbTrangThai = new JComboBox();
		Border cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbTrangThai.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbTrangThai.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbTrangThai.addItem("Đang thực hiện");
		b3.add(cmbTrangThai);
		cmbTrangThai.setBackground(bgColor);
		cmbTrangThai.setForeground(textColor);
		cmbTrangThai.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(30);
		b3.add(horizontalStrut_3);
		
		JLabel lblGhiChu = new JLabel("Ghi chú:");
		lblGhiChu.setForeground(textColor);
		lblGhiChu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b3.add(lblGhiChu);
		
		b3.add(Box.createHorizontalStrut(10));
		
		txtGhiChu = new JTextField();
		txtGhiChu.setForeground(textColor);
		txtGhiChu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtGhiChu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtGhiChu.setBackground(bgColor);
		b3.add(txtGhiChu);
		
		pnThongTinHD.add(Box.createVerticalStrut(10));
		
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
		
		// tạo jpanel chứa table hợp đồng
		JPanel pnlBangHD = new JPanel();
		pnlBangHD.setLayout(new BoxLayout(pnlBangHD, BoxLayout.X_AXIS));
		add(pnlBangHD, BorderLayout.CENTER);
		String cols[] = {"Mã HĐ", "Tên HĐ", "Khách hàng", "Người đại diện", "Ngày bắt đầu", "Ngày kết thúc", "Giá trị", "Trạng thái", "Ghi chú"};
		dtblModel = new DefaultTableModel(cols, 0);
		tblHD = new JTable(dtblModel);
		
		tbhHD = new JTableHeader(tblHD.getColumnModel());
		tbhHD.setReorderingAllowed(false);
		tbhHD.setBackground(componentColor);
		tbhHD.setForeground(Color.WHITE);
		tbhHD.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
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
		txtMaHD.setText("HD12345");
		txtTenKH.setText("Nguyễn Văn Phong");
		txtTenHD.setText("Hợp đồng hợp tác ABC");
		txtDaiDien.setText("Phạm Hữu Thuận");
		txtGiaTri.setText(formatMoneyText("10000000000"));
		lblGiaTriText.setText("    (" + formatMoneyToText(Double.parseDouble(txtGiaTri.getText().replaceAll(",", ""))) + " VNĐ)     ");
		txtTienCoc.setText(formatMoneyText("1000000000"));
		lblTienCocText.setText("     (" + formatMoneyToText(Double.parseDouble(txtTienCoc.getText().replaceAll(",", ""))) + " VNĐ)");
		txtThoaThuan.setText("Thỏa thuận giữa 2 bên bao gồm: điều 1, điều 2, điều 3,...");
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
		music.playSE(2);
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
		else if(btnFocus == btnThem || btnFocus == btnSua) {
			if(o == btnHuy || o == btnLuu) {
				btnFocus = (RoundedButton) o;
				btnFocus.setFocusButton(main.borderFocusColor, 3);
			}
		}else if(btnFocus == btnLuu || btnFocus == btnHuy) {
			if(o == btnThem || o == btnSua || o == btnXoa || o == btnIn) {
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
			music.playSE(1);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
