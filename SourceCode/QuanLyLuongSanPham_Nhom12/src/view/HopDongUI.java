package view;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

import CustomUI.CustomComboBoxUI;
import CustomUI.CustomListCellRenderer;
import CustomUI.ImageScaler;
import CustomUI.RoundedButton;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComboBox;
import java.awt.FlowLayout;

public class HopDongUI extends JPanel implements ActionListener, MouseListener{
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField txtMaHD;
	private JTextField txtTenHD;
	private JTextField txtTenKH;
	private JTextField txtDaiDien;
	private JTextField txtGiaTri;
	private JTextField txtTienCoc;
	private JTextField txtThoaThuan;
	private JTextField txtAGhiChu;
	private RoundedButton btnThem, btnSua, btnXoa, btnLuu, btnHuy;
	private DefaultTableModel tblModel;
	private JTable tblHD;
	private JTableHeader tblHead;
	private JPanel pnChucNang;
	/**
	 * Create the panel.
	 */
	public HopDongUI(MainUI main) {
		this.main = main;
		
		setLayout(new BorderLayout(0, 0));
		setBackground(bgColor);
		
		JPanel pnNorth = new JPanel();
		pnNorth.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		pnNorth.setBackground(bgColor);
		add(pnNorth, BorderLayout.NORTH);
		pnNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(bgColor);
		pnNorth.add(pnTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("QUẢN LÝ HỢP ĐỒNG");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnTitle.add(lblTitle);
		
		JPanel pnThongTinHD = new JPanel();
		pnThongTinHD.setLayout(new BoxLayout(pnThongTinHD, BoxLayout.Y_AXIS));
		pnThongTinHD.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Thông tin hợp đồng");
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnThongTinHD.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(0, 50, 10, 50)));
		pnNorth.add(pnThongTinHD, BorderLayout.CENTER);
		
		Box b1 = Box.createHorizontalBox();
		pnThongTinHD.add(b1);
		
		JLabel lblMaHD = new JLabel("Mã HĐ:");
		lblMaHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblMaHD.setForeground(textColor);
		b1.add(lblMaHD);
		b1.add(Box.createHorizontalStrut(10));
		
		txtMaHD = new JTextField();
		txtMaHD.setEditable(false);
		txtMaHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaHD.setForeground(textColor);
		txtMaHD.setBackground(bgColor);
		txtMaHD.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
		b1.add(txtMaHD);
		b1.add(Box.createHorizontalStrut(30));
		
		JLabel lblTenHD = new JLabel("Tên HĐ:");
		lblTenHD.setForeground(textColor);
		lblTenHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b1.add(lblTenHD);
		b1.add(Box.createHorizontalStrut(10));
		
		txtTenHD = new JTextField();
		txtTenHD.setEditable(false);
		txtTenHD.setForeground(textColor);
		txtTenHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenHD.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
		txtTenHD.setBackground(bgColor);
		b1.add(txtTenHD);
		b1.add(Box.createHorizontalStrut(30));
		
		JLabel lblTenKH = new JLabel("Khách hàng:");
		lblTenKH.setForeground(textColor);
		lblTenKH.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b1.add(lblTenKH);
		b1.add(Box.createHorizontalStrut(10));
		
		txtTenKH = new JTextField();
		txtTenKH.setEditable(false);
		txtTenKH.setForeground(textColor);
		txtTenKH.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenKH.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
		txtTenKH.setBackground(bgColor);
		b1.add(txtTenKH);
		b1.add(Box.createHorizontalStrut(30));
		
		JLabel lblDaiDien = new JLabel("Người đại diện:");
		lblDaiDien.setForeground(textColor);
		lblDaiDien.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b1.add(lblDaiDien);
		b1.add(Box.createHorizontalStrut(10));
		
		txtDaiDien = new JTextField();
		txtDaiDien.setEditable(false);
		txtDaiDien.setForeground(textColor);
		txtDaiDien.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtDaiDien.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
		txtDaiDien.setBackground(bgColor);
		b1.add(txtDaiDien);
		
		pnThongTinHD.add(Box.createVerticalStrut(10));
		
		Box b2 = Box.createHorizontalBox();
		pnThongTinHD.add(b2);
		
		// ngay bat dau
		JLabel lblNgayBD = new JLabel("Ngày bắt đầu:");
		lblNgayBD.setForeground(textColor);
		lblNgayBD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblNgayBD);
		
		b2.add(Box.createHorizontalStrut(10));
		
		JXDatePicker dateBatDau = new JXDatePicker(new Date());
		dateBatDau.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dateBatDau.setEditable(false);
		dateBatDau.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dateBatDau.setBackground(bgColor);
		dateBatDau.setForeground(textColor);
		dateBatDau.setLocale(new Locale("vi", "VN"));
		JButton btnDateBD = (JButton) dateBatDau.getComponent(1);
		btnDateBD.setIcon(new ImageScaler("/image/calendar_icon.png", 18, 18).getScaledImageIcon());
		btnDateBD.setBackground(bgColor);
		btnDateBD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		dateBatDau.getEditor().setBackground(bgColor);
		dateBatDau.getEditor().setForeground(textColor);
		b2.add(dateBatDau);
		
		b2.add(Box.createHorizontalStrut(30));
		
		// ngay ket thuc
		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc");
		lblNgayKetThuc.setForeground(textColor);
		lblNgayKetThuc.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblNgayKetThuc);
		
		b2.add(Box.createHorizontalStrut(10));
		
		
		JXDatePicker dateKetThuc = new JXDatePicker(new Date());
		dateKetThuc.setEditable(false);
		dateKetThuc.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dateKetThuc.getEditor().setForeground(textColor);
		dateKetThuc.getEditor().setBackground(bgColor);
		dateKetThuc.setLocale(new Locale("vi", "VN"));
		dateKetThuc.setForeground(textColor);
		dateKetThuc.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dateKetThuc.setBackground(bgColor);
		JButton btnDateKT = (JButton) dateKetThuc.getComponent(1);
		btnDateKT.setIcon(new ImageScaler("/image/calendarfinish_icon.png", 18, 18).getScaledImageIcon());
		btnDateKT.setBackground(bgColor);
		btnDateKT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		b2.add(dateKetThuc);
		
		b2.add(Box.createHorizontalStrut(30));
		
		JLabel lblGiaTri = new JLabel("Giá trị:");
		lblGiaTri.setForeground(textColor);
		lblGiaTri.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblGiaTri);
		
		b2.add(Box.createHorizontalStrut(10));
		
		txtGiaTri = new JTextField();
		txtGiaTri.setForeground(textColor);
		txtGiaTri.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtGiaTri.setEditable(false);
		txtGiaTri.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
		txtGiaTri.setBackground(bgColor);
		b2.add(txtGiaTri);

		b2.add(Box.createHorizontalStrut(30));
		
		JLabel lblTienCoc = new JLabel("Tiền cọc:");
		lblTienCoc.setForeground(textColor);
		lblTienCoc.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblTienCoc);
		
		b2.add(Box.createHorizontalStrut(10));
		
		txtTienCoc = new JTextField();
		txtTienCoc.setForeground(textColor);
		txtTienCoc.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTienCoc.setEditable(false);
		txtTienCoc.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
		txtTienCoc.setBackground(bgColor);
		b2.add(txtTienCoc);
		
		pnThongTinHD.add(Box.createVerticalStrut(10));
		
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
		txtThoaThuan.setEditable(false);
		txtThoaThuan.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
		txtThoaThuan.setBackground(Color.WHITE);
		b3.add(txtThoaThuan);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(30);
		b3.add(horizontalStrut_1);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setForeground(Color.BLACK);
		lblTrangThai.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b3.add(lblTrangThai);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		b3.add(horizontalStrut_2);
		
		JComboBox cboTrangThai = new JComboBox();
		Border cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cboTrangThai.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cboTrangThai.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cboTrangThai.addItem("Đang thực hiện");
		cboTrangThai.addItem("Hoàn thành");
		b3.add(cboTrangThai);
		cboTrangThai.setBackground(bgColor);
		cboTrangThai.setForeground(textColor);
		cboTrangThai.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(30);
		b3.add(horizontalStrut_3);
		
		JLabel lblGhiChu = new JLabel("Ghi chú:");
		lblGhiChu.setForeground(textColor);
		lblGhiChu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b3.add(lblGhiChu);
		
		b3.add(Box.createHorizontalStrut(10));
		
		txtAGhiChu = new JTextField();
		txtAGhiChu.setForeground(textColor);
		txtAGhiChu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtAGhiChu.setEditable(false);
		txtAGhiChu.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
		txtAGhiChu.setBackground(bgColor);
		b3.add(txtAGhiChu);
		
		pnThongTinHD.add(Box.createVerticalStrut(10));
		
		pnChucNang = new JPanel();
		pnNorth.add(pnChucNang, BorderLayout.SOUTH);
		pnChucNang.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnChucNang.setBackground(bgColor);
		pnChucNang.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		btnThem = new RoundedButton("Thêm", Color.BLACK, 20, 0, 1.0f);
		btnThem.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(Color.decode("#3B71CA"));
		btnThem.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnThem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnChucNang.add(btnThem);
		pnChucNang.add(Box.createHorizontalStrut(75));
		
		btnSua = new RoundedButton("Sửa", Color.BLACK, 20, 0, 1.0f);
		btnSua.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnSua.setForeground(Color.WHITE);
		btnSua.setBackground(Color.decode("#ffc107"));
		btnSua.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnSua.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnChucNang.add(btnSua);
		pnChucNang.add(Box.createHorizontalStrut(75));
		
		btnXoa = new RoundedButton("Xóa", Color.BLACK, 20, 0, 1.0f);
		btnXoa.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(Color.decode("#dc3545"));
		btnXoa.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnChucNang.add(btnXoa);
		
		pnChucNang.add(Box.createHorizontalStrut(100));
		
		btnLuu = new RoundedButton("Lưu", Color.BLACK, 20, 0, 0.6f);
		btnLuu.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setBackground(Color.decode("#28a745"));
		btnLuu.setIcon(new ImageScaler("/image/save_Icon.png", 24, 24).getScaledImageIcon());
		btnLuu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnChucNang.add(btnLuu);
		pnChucNang.add(Box.createHorizontalStrut(75));
		
		btnHuy = new RoundedButton("Hủy", Color.BLACK, 20, 0, 0.6f);
		btnHuy.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBackground(Color.decode("#ffc107"));
		btnHuy.setIcon(new ImageScaler("/image/cancelHD_Icon.png", 24, 24).getScaledImageIcon());
		btnHuy.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		displayButtonSaveAndCancel(false);
		pnChucNang.add(btnHuy);
		
		JPanel pnBangHD = new JPanel();
		pnBangHD.setLayout(new BoxLayout(pnBangHD, BoxLayout.X_AXIS));
		add(pnBangHD, BorderLayout.CENTER);
		String cols[] = {"Mã HĐ", "Tên HĐ", "Khách hàng", "Người đại diện", "Ngày bắt đầu", "Ngày kết thúc", "Giá trị", "Trạng thái", "Ghi chú"};
		tblModel = new DefaultTableModel(cols, 0);
		tblHD = new JTable(tblModel);
		
		tblHead = new JTableHeader(tblHD.getColumnModel());
		tblHead.setReorderingAllowed(false);
		tblHead.setBackground(componentColor);
		tblHead.setForeground(Color.WHITE);
		tblHead.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblHD.setTableHeader(tblHead);
		
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
		
		JScrollPane tblScroll = new JScrollPane(tblHD,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnBangHD.add(tblScroll);
		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
	}
	public void displayButtonSaveAndCancel(boolean display) {
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
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == btnThem) {
			displayButtonSaveAndCancel(true);
			
		}
		if(o == btnSua) {
			displayButtonSaveAndCancel(true);
			
		}
		if(o == btnXoa) {
			
		}
		if(o == btnLuu) {
			displayButtonSaveAndCancel(false);
			
		}
		if(o == btnHuy) {
			displayButtonSaveAndCancel(false);
			
		}
	}
}
