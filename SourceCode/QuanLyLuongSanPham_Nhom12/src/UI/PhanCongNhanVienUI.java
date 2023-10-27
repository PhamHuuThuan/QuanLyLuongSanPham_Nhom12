package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
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

public class PhanCongNhanVienUI extends JPanel implements ActionListener, MouseListener{
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private RoundedButton btnPhanCong, btnCapNhatPC, btnXoaPC, btnLuu, btnHuy, btnFocus;
	private DefaultTableModel dtblModelNVPC, dtblModelNV;
	private JTable tblNVPC, tblNV;
	private JTableHeader tbhNVPC, tbhNV;
	private JTextField  txtMaNV, txtTenNV, txtSDT, txtGhiChu,txtChucVu;
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
		
		//tao jpanel chua Title va Thong tin san pham
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
		JLabel lblTitle = new JLabel("PHÂN CÔNG NHÂN VIÊN");
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
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Danh sách chưa phân công");
		titleBorderTTNV.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNV.setBorder(BorderFactory.createCompoundBorder(titleBorderTTNV, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		pnlBangNV.setLayout(new BorderLayout());
		pnlBangNV.setBackground(bgColor);
		pnlBangNV.setPreferredSize(new Dimension(700, 300));
		b.add(pnlBangNV);
		b.add(Box.createHorizontalStrut(50));
		
		String cols[] = {"Mã NV", "Họ tên", "Giới tính", "SĐT", "Địa chỉ"};
		dtblModelNV = new DefaultTableModel(cols, 0);
		tblNV = new JTable(dtblModelNV);

		tbhNV = new JTableHeader(tblNV.getColumnModel());
		tbhNV.setReorderingAllowed(false);
		tbhNV.setBackground(componentColor);
		tbhNV.setForeground(Color.WHITE);
		tbhNV.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblNV.setTableHeader(tbhNV);
		
		tblNV.setRowHeight(20);
		tblNV.getColumnModel().getColumn(0).setPreferredWidth(75);
		tblNV.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblNV.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblNV.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblNV.getColumnModel().getColumn(4).setPreferredWidth(200);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrNV = new JScrollPane(tblNV,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNV.add(scrNV, BorderLayout.CENTER);
		
		
		//Tao jpanel Thong tin nhan vien
		JPanel pnThongTinNV = new JPanel();
		pnThongTinNV.setLayout(new BorderLayout());
		pnThongTinNV.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Thông tin phân công");
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnThongTinNV.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(10, 30, 10, 30)));
		pnThongTinNV.setPreferredSize(new Dimension(700, 300));
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
		
		JPanel pnlB1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB1.setBackground(bgColor);
		pnlTTRight.add(pnlB1);
		
		JLabel lblTenNV = new JLabel("Họ tên:");
		lblTenNV.setForeground(textColor);
		lblTenNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB1.add(lblTenNV);
		
		txtTenNV = new JTextField();
		txtTenNV.setColumns(8);
		txtTenNV.setForeground(textColor);
		txtTenNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtTenNV.setBackground(bgColor);
		pnlB1.add(txtTenNV);
		pnlB1.add(Box.createHorizontalStrut(20));
		
		JLabel lblSDT = new JLabel("SĐT:");
		pnlB1.add(lblSDT);
		lblSDT.setForeground(textColor);
		lblSDT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtSDT = new JTextField();
		pnlB1.add(txtSDT);
		txtSDT.setColumns(8);
		txtSDT.setForeground(textColor);
		txtSDT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtSDT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtSDT.setBackground(bgColor);
		
		JPanel pnlB2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB2.setBackground(bgColor);
		pnlTTRight.add(pnlB2);
		
		JLabel lblPhongBan = new JLabel("Phòng ban:");
		lblPhongBan.setForeground(textColor);
		lblPhongBan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB2.add(lblPhongBan);
		
		JComboBox cmbPhongBan = new JComboBox();
		cmbPhongBan.setModel(new DefaultComboBoxModel(new String[] {"Nhân sự", "Kế toán"}));
		Border cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 10, 0, 5));
		cmbPhongBan.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cmbPhongBan.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbPhongBan.setBackground(bgColor);
		cmbPhongBan.setForeground(textColor);
		cmbPhongBan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		cmbPhongBan.setPreferredSize(txtTenNV.getPreferredScrollableViewportSize());
		pnlB2.add(cmbPhongBan);
		pnlB2.add(Box.createHorizontalStrut(20));
		
		JLabel lblChucVu = new JLabel("Chức vụ:");
		pnlB2.add(lblChucVu);
		lblChucVu.setForeground(textColor);
		lblChucVu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtChucVu = new JTextField();
		txtChucVu.setColumns(8);
		pnlB2.add(txtChucVu);
		txtChucVu.setForeground(textColor);
		txtChucVu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtChucVu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
								BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtChucVu.setBackground(Color.WHITE);
		
		JPanel pnlB3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB3.setBackground(bgColor);
		pnlTTRight.add(pnlB3);
		
		JLabel lblNgayVL = new JLabel("Ngày VL:");
		pnlB3.add(lblNgayVL);
		lblNgayVL.setForeground(textColor);
		lblNgayVL.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		JXDatePicker dtbNgayVL = new JXDatePicker((Date) null);
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
		Component horizontalStrut = Box.createHorizontalStrut(20);
		pnlB3.add(horizontalStrut);
		
		JLabel lblGhiChu = new JLabel("Ghi chú:");
		lblGhiChu.setForeground(textColor);
		lblGhiChu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB3.add(lblGhiChu);
		
		txtGhiChu = new JTextField();
		txtGhiChu.setColumns(8);
		txtGhiChu.setForeground(textColor);
		txtGhiChu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtGhiChu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtGhiChu.setBackground(bgColor);
		pnlB3.add(txtGhiChu);
		
		lblTenNV.setPreferredSize(lblPhongBan.getPreferredSize());
		lblNgayVL.setPreferredSize(lblPhongBan.getPreferredSize());
		
		lblSDT.setPreferredSize(lblChucVu.getPreferredSize());
		lblGhiChu.setPreferredSize(lblChucVu.getPreferredSize());
		
		
		//Khởi tạo jpanel chức năng chứa các button chức năng
		JPanel pnlChucNang = new JPanel(new FlowLayout());
		pnlChucNang.setBackground(bgColor);
		pnlTTRight.add(pnlChucNang);
		
		btnPhanCong = new RoundedButton("Phân công", null, 20, 0, 1.0f);
		btnPhanCong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnPhanCong.setForeground(Color.WHITE);
		btnPhanCong.setBackground(Color.decode("#3B71CA"));
		btnPhanCong.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnPhanCong.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnPhanCong);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnCapNhatPC = new RoundedButton("Cập Nhật", null, 20, 0, 1.0f);
		btnCapNhatPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnCapNhatPC.setForeground(Color.WHITE);
		btnCapNhatPC.setBackground(Color.decode("#ffc107"));
		btnCapNhatPC.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnCapNhatPC.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnCapNhatPC);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnXoaPC = new RoundedButton("Xóa", null, 20, 0, 1.0f);
		btnXoaPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnXoaPC.setForeground(Color.WHITE);
		btnXoaPC.setBackground(Color.decode("#dc3545"));
		btnXoaPC.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoaPC.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoaPC);
		
		// tạo jpanel chứa table phân công nhân viên
		JPanel pnlBangNVPC = new JPanel();
		TitledBorder titleBorderTTNVPC = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Danh sách phân công nhân viên");
		titleBorderTTNVPC.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNVPC.setBorder(BorderFactory.createCompoundBorder(titleBorderTTNVPC, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		pnlBangNVPC.setLayout(new BoxLayout(pnlBangNVPC, BoxLayout.X_AXIS));
		pnlBangNVPC.setBackground(bgColor);
		pnlNhanVien.add(pnlBangNVPC, BorderLayout.CENTER);
		
		String colsPCNV[] = {"Mã NV", "Họ tên", "Giới tính", "SĐT", "Địa chỉ", "Phòng ban", "Chức vụ", "Ngày công tác", "Ghi chú"};
		dtblModelNVPC = new DefaultTableModel(colsPCNV, 0);
		tblNVPC = new JTable(dtblModelNVPC);

		tbhNVPC = new JTableHeader(tblNVPC.getColumnModel());
		tbhNVPC.setReorderingAllowed(false);
		tbhNVPC.setBackground(componentColor);
		tbhNVPC.setForeground(Color.WHITE);
		tbhNVPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblNVPC.setTableHeader(tbhNVPC);
		
		tblNVPC.setRowHeight(20);
		tblNVPC.getColumnModel().getColumn(0).setPreferredWidth(75);
		tblNVPC.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblNVPC.getColumnModel().getColumn(2).setPreferredWidth(75);
		tblNVPC.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblNVPC.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblNVPC.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblNVPC.getColumnModel().getColumn(6).setPreferredWidth(100);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblNVPC,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNVPC.add(scrSP);
		
		pnlNorth.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		
		btnPhanCong.addActionListener(this);
		btnCapNhatPC.addActionListener(this);
		
		btnPhanCong.addMouseListener(this);
		btnCapNhatPC.addMouseListener(this);
		
		
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
		if(o == btnPhanCong) {
			
		}
		if(o == btnCapNhatPC) {

			
		}
	}
}
