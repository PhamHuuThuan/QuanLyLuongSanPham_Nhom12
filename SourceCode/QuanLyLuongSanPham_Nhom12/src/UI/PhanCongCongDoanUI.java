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

public class PhanCongCongDoanUI extends JPanel implements ActionListener, MouseListener{
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
		// TODO Auto-generated method stub
		
	}
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private RoundedButton btnPhanCong, btnCapNhatPC, btnXoaPC, btnLuu, btnHuy, btnFocus;
	private DefaultTableModel dtblModelCDPC, dtblModelCD;
	private JTable tblCDPC, tblCD;
	private JTableHeader tbhCDPC, tbhCD;
	private JTextField  txtMaCD, txtTenCD, txtSoLuong, txtDonGia, txtMaPC,txtNgayPC,txtSoLuongLam;
	/**
	 * Create the panel.
	 */
	public PhanCongCongDoanUI(MainUI main) {
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
		
		//tao jpanel chua Title va Thong tin cong doan
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
		JLabel lblTitle = new JLabel("PHÂN CÔNG CÔNG ĐOẠN");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle.add(lblTitle);
		
		//Tạo hộp thoại phân công
		Box b = Box.createHorizontalBox();
		b.setBackground(bgColor);
		pnlNorth.add(b, BorderLayout.CENTER);
		
		// tạo jpanel chứa table cong doan
		JPanel pnlBangNV = new JPanel();
		TitledBorder titleBorderTTNV = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), ("Danh sách chưa phân công"));
		titleBorderTTNV.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNV.setBorder(BorderFactory.createCompoundBorder(titleBorderTTNV, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		pnlBangNV.setLayout(new BorderLayout());
		pnlBangNV.setBackground(bgColor);
		pnlBangNV.setPreferredSize(new Dimension(700, 300));
		b.add(pnlBangNV);
		b.add(Box.createHorizontalStrut(50));
		
		String cols[] = {"Mã CD", "Tên CD", "Số lượng", "Đơn giá"};
		dtblModelCD = new DefaultTableModel(cols, 0);
		tblCD = new JTable(dtblModelCD);

		tbhCD = new JTableHeader(tblCD.getColumnModel());
		tbhCD.setReorderingAllowed(false);
		tbhCD.setBackground(componentColor);
		tbhCD.setForeground(Color.WHITE);
		tbhCD.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblCD.setTableHeader(tbhCD);
		
		tblCD.setRowHeight(20);
		tblCD.getColumnModel().getColumn(0).setPreferredWidth(75);
		tblCD.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblCD.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblCD.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrNV = new JScrollPane(tblCD,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNV.add(scrNV, BorderLayout.CENTER);
		
		
		//Tao jpanel Thong tin nhan vien
		JPanel pnThongTinNV = new JPanel();
		pnThongTinNV.setLayout(new BorderLayout());
		pnThongTinNV.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), ("Thông tin phân công"));
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnThongTinNV.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(10, 30, 10, 30)));
		pnThongTinNV.setPreferredSize(new Dimension(700, 300));
		b.add(pnThongTinNV);
		
		JPanel pnlAnhDaiDien = new JPanel();
		pnlAnhDaiDien.setLayout(new BoxLayout(pnlAnhDaiDien, BoxLayout.Y_AXIS));
		pnlAnhDaiDien.setBackground(bgColor);
		pnThongTinNV.add(pnlAnhDaiDien, BorderLayout.WEST);
		
		
		txtMaCD = new JTextField();
		txtMaCD.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaCD.setColumns(5);
		pnlAnhDaiDien.add(txtMaCD, BorderLayout.SOUTH);
		pnlAnhDaiDien.add(Box.createVerticalStrut(100));
		txtMaCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaCD.setForeground(textColor);
		txtMaCD.setBackground(bgColor);
		txtMaCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		
		
		JPanel pnlTTRight = new JPanel();
		pnlTTRight.setLayout(new BoxLayout(pnlTTRight, BoxLayout.Y_AXIS));
		pnlTTRight.setBackground(bgColor);
		pnThongTinNV.add(pnlTTRight, BorderLayout.CENTER);
		
		JPanel pnlB1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB1.setBackground(bgColor);
		pnlTTRight.add(pnlB1);
		
		JLabel lblMaCD = new JLabel("Mã CD:");
		lblMaCD.setForeground(textColor);
		lblMaCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB1.add(lblMaCD);
		
		txtMaCD = new JTextField();
		txtMaCD.setColumns(8);
		txtMaCD.setForeground(textColor);
		txtMaCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaCD.setBackground(bgColor);
		pnlB1.add(txtMaCD);
		pnlB1.add(Box.createHorizontalStrut(20));
		
		JLabel lblMaPC = new JLabel("Mã PC:");
		lblMaPC.setForeground(textColor);
		lblMaPC.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB1.add(lblMaPC);
		
		txtMaPC = new JTextField();
		txtMaPC.setColumns(8);
		txtMaPC.setForeground(textColor);
		txtMaPC.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaPC.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaPC.setBackground(bgColor);
		pnlB1.add(txtMaPC);
		pnlB1.add(Box.createHorizontalStrut(20));
		
		JPanel pnlB2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB2.setBackground(bgColor);
		pnlTTRight.add(pnlB2);
		JLabel lblTenCD = new JLabel("Tên CD:");
		lblTenCD.setForeground(textColor);
		lblTenCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB2.add(lblTenCD);
		
		txtTenCD = new JTextField();
		txtTenCD.setColumns(8);
		txtTenCD.setForeground(textColor);
		txtTenCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaPC.setBackground(bgColor);
		pnlB2.add(txtTenCD);
		pnlB2.add(Box.createHorizontalStrut(20));
		
		JLabel lblSoLuongLam= new JLabel("Số Lượng làm:");
		lblSoLuongLam.setForeground(textColor);
		lblSoLuongLam.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB2.add(lblSoLuongLam);
		
		txtSoLuongLam = new JTextField();
		txtSoLuongLam.setColumns(8);
		pnlB2.add(txtSoLuongLam);
		txtSoLuongLam.setForeground(textColor);
		txtSoLuongLam.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtSoLuongLam.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 

								BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtSoLuongLam.setBackground(Color.WHITE);
		
		JPanel pnlB3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB3.setBackground(bgColor);
		pnlTTRight.add(pnlB3);
		
		JLabel lblNgayPC = new JLabel("Ngày PCông:");
		pnlB3.add(lblNgayPC);
		lblNgayPC.setForeground(textColor);
		lblNgayPC.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		JXDatePicker dtbNgayPC = new JXDatePicker((Date) null);
		pnlB3.add(dtbNgayPC);
		dtbNgayPC.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtbNgayPC.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dtbNgayPC.setLocale(new Locale("vi", "VN"));	// set thoi gian local la VN
		dtbNgayPC.getEditor().setBackground(bgColor);
		dtbNgayPC.getEditor().setForeground(textColor);
		dtbNgayPC.setPreferredSize(txtTenCD.getPreferredScrollableViewportSize());
		JButton btnDateNVL = (JButton) dtbNgayPC.getComponent(1);

		btnDateNVL.setBackground(bgColor);
		btnDateNVL.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		Component horizontalStrut = Box.createHorizontalStrut(20);
		pnlB3.add(horizontalStrut);
		
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
		
		// tạo jpanel chứa table phân công công đoạn
		JPanel pnlBangNVPC = new JPanel();
		TitledBorder titleBorderTTNVPC = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Danh sách phân công công đoạn");
		titleBorderTTNVPC.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNVPC.setBorder(BorderFactory.createCompoundBorder(titleBorderTTNVPC, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		pnlBangNVPC.setLayout(new BoxLayout(pnlBangNVPC, BoxLayout.X_AXIS));
		pnlBangNVPC.setBackground(bgColor);
		pnlNhanVien.add(pnlBangNVPC, BorderLayout.CENTER);
		
		String colsPCNV[] = {"Mã CD", " Mã CN", "Mã PC", "Ngày PC", "Số Lượng"};
		dtblModelCDPC = new DefaultTableModel(colsPCNV, 0);
		tblCDPC = new JTable(dtblModelCDPC);

		tbhCDPC = new JTableHeader(tblCDPC.getColumnModel());
		tbhCDPC.setReorderingAllowed(false);
		tbhCDPC.setBackground(componentColor);
		tbhCDPC.setForeground(Color.WHITE);
		tbhCDPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblCDPC.setTableHeader(tbhCDPC);
		
			tblCDPC.setRowHeight(20);
			tblCDPC.getColumnModel().getColumn(0).setPreferredWidth(75);
			tblCDPC.getColumnModel().getColumn(1).setPreferredWidth(100);
			tblCDPC.getColumnModel().getColumn(2).setPreferredWidth(75);
			tblCDPC.getColumnModel().getColumn(3).setPreferredWidth(100);
			tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(150);
	;
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblCDPC,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNVPC.add(scrSP);
		
		pnlNorth.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		
		btnPhanCong.addActionListener(this);
		btnCapNhatPC.addActionListener(this);
		
		btnPhanCong.addMouseListener(this);
		btnCapNhatPC.addMouseListener(this);
		
		
	}
	
}
