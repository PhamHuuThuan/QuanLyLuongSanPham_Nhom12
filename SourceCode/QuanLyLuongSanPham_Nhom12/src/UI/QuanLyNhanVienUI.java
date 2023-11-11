package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Dao.NhanVien_Dao;
import Entity.NhanVien;
import Util.SinhMaTuDong;

import java.awt.FlowLayout;
import javax.swing.JRadioButton;

public class QuanLyNhanVienUI extends JPanel implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField txtMaNV, txtMatKhau, txtTenNV, txtSDT, txtEmail;
	private RoundedButton btnThem, btnSua, btnXoa, btnLuu, btnHuy, btnFocus, btnChonAnh;
	private JRadioButton radNam, radNu;
	private ButtonGroup grpGioiTinh;
	private DefaultTableModel dtblModel;
	private JTable tblNV;
	private JTableHeader tbhNV;
	private JPanel pnlChucNang;
	private JXDatePicker dtpNgaySinh;
	private JTextField txtCCCD;
	private JTextField txtDiaChi; 
	private Font fontText;
	private JLabel lblAvatar;
	private JLabel lblMessage;
	private NhanVien_Dao nv_Dao = new NhanVien_Dao();
	private ArrayList<NhanVien> dsNV = new ArrayList<>();
	private boolean isThem = false;
	/**
	 * Create the panel.
	 */
	public QuanLyNhanVienUI(MainUI main) {
		this.main = main;
		fontText = main.roboto_regular.deriveFont(Font.PLAIN, 16F);
		
		//set gia tri cho jpanel NhanVien
		setLayout(new BorderLayout(0, 0));
		setBackground(bgColor);
		
		//tao jpanel chua Title va Thong tin NV
		JPanel pnNorth = new JPanel();
		pnNorth.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		pnNorth.setBackground(bgColor);
		add(pnNorth, BorderLayout.NORTH);
		pnNorth.setLayout(new BorderLayout(0, 0));
		
		//Tao jpanel Title
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(bgColor);
		pnNorth.add(pnTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel(main.read_file_languages.getString("text_header_QLNV"));
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnTitle.add(lblTitle);
		
		//Tao jpanel Thong tin nhan vien
		JPanel pnlThongTin = new JPanel();
		pnlThongTin.setBackground(bgColor);
		pnlThongTin.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnlThongTin.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("text_border_TTNV"));
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlThongTin.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		pnNorth.add(pnlThongTin, BorderLayout.CENTER);
		
		JPanel pnlAnhDD = new JPanel();
		pnlAnhDD.setLayout(new BoxLayout(pnlAnhDD, BoxLayout.Y_AXIS));
		pnlAnhDD.setBackground(bgColor);
		pnlThongTin.add(pnlAnhDD);
		pnlThongTin.add(Box.createHorizontalStrut(30));
		
		lblAvatar = new JLabel("");
		lblAvatar.setIcon(new ImageScaler("/image/employee.png", 150, 150).getScaledImageIcon());
		lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
		pnlAnhDD.add(lblAvatar, BorderLayout.CENTER);
		
		btnChonAnh = new RoundedButton(main.read_file_languages.getString("btnChonAnh"), null, 5, 0, 1f);
		btnChonAnh.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnChonAnh.setForeground(Color.WHITE);
		btnChonAnh.setBackground(componentColor);
		btnChonAnh.setIcon(new ImageScaler("/image/add-image.png", 16, 16).getScaledImageIcon());
		btnChonAnh.setBorder(BorderFactory.createEmptyBorder(5, 35, 5, 35));
		Dimension size = btnChonAnh.getPreferredSize();
		btnChonAnh.setMinimumSize(size);
		btnChonAnh.setMaximumSize(size);
		btnChonAnh.setPreferredSize(size);
		pnlAnhDD.add(btnChonAnh);
		
		JPanel pnThongTinNV = new JPanel();
		pnThongTinNV.setLayout(new BoxLayout(pnThongTinNV, BoxLayout.Y_AXIS));
		pnThongTinNV.setBackground(bgColor);
		pnlThongTin.add(pnThongTinNV);
		
		// Tao box chua cac phan tu
		JPanel pnlB1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB1.setBackground(bgColor);
		pnThongTinNV.add(pnlB1);
		
		JLabel lblMaNV = new JLabel(main.read_file_languages.getString("lblMaNV") +":");
		lblMaNV.setFont(fontText);
		lblMaNV.setForeground(textColor);
		pnlB1.add(lblMaNV);
		pnlB1.add(Box.createHorizontalStrut(5));
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(fontText);
		txtMaNV.setForeground(textColor);
		txtMaNV.setBackground(bgColor);
		txtMaNV.setColumns(5);
		txtMaNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		pnlB1.add(txtMaNV);
		pnlB1.add(Box.createHorizontalStrut(20));
		
		JLabel lblTenHD = new JLabel(main.read_file_languages.getString("text_heading_password") + ":");
		lblTenHD.setForeground(textColor);
		lblTenHD.setFont(fontText);
		pnlB1.add(lblTenHD);
		pnlB1.add(Box.createHorizontalStrut(5));
		
		txtMatKhau = new JTextField();
		txtMatKhau.setForeground(textColor);
		txtMatKhau.setFont(fontText);
		txtMatKhau.setColumns(10);
		txtMatKhau.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtMatKhau.setBackground(bgColor);
		pnlB1.add(txtMatKhau);
		pnlB1.add(Box.createHorizontalStrut(20));
		
		JLabel lblTenKH = new JLabel(main.read_file_languages.getString("lblHoTen") + ":");
		lblTenKH.setForeground(textColor);
		lblTenKH.setFont(fontText);
		pnlB1.add(lblTenKH);
		pnlB1.add(Box.createHorizontalStrut(5));
		
		txtTenNV = new JTextField();
		txtTenNV.setForeground(textColor);
		txtTenNV.setFont(fontText);
		txtTenNV.setColumns(15);
		txtTenNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtTenNV.setBackground(bgColor);
		pnlB1.add(txtTenNV);
		
		pnlB1.add(Box.createHorizontalStrut(20));
		
		JLabel lblGT = new JLabel(main.read_file_languages.getString("lblGioiTinh") + ":");
		lblGT.setForeground(textColor);
		lblGT.setFont(fontText);
		pnlB1.add(lblGT);
		pnlB1.add(Box.createHorizontalStrut(5));
		
		radNam = new JRadioButton(main.read_file_languages.getString("rad_Nam"));
		radNam.setSelected(true);
		radNam.setBackground(bgColor);
		radNam.setForeground(textColor);
		radNam.setFont(fontText);
		pnlB1.add(radNam);
		
		radNu = new JRadioButton(main.read_file_languages.getString("rad_Nu"));
		radNu.setBackground(bgColor);
		radNu.setForeground(textColor);
		radNu.setFont(fontText);
		pnlB1.add(radNu);
		
		grpGioiTinh = new ButtonGroup();
		grpGioiTinh.add(radNam);
		grpGioiTinh.add(radNu);
		
		pnThongTinNV.add(Box.createVerticalStrut(20));
		
		//Tao box chua thong tin
		JPanel pnlB2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB2.setBackground(bgColor);
		pnThongTinNV.add(pnlB2);
	
		JLabel lblNgaySinh = new JLabel(main.read_file_languages.getString("lblNgaySinh") + ":");
		lblNgaySinh.setForeground(textColor);
		lblNgaySinh.setFont(fontText);
		pnlB2.add(lblNgaySinh);
		
		pnlB2.add(Box.createHorizontalStrut(5));
		
		dtpNgaySinh = new JXDatePicker(new Date(100, 0, 1));
		dtpNgaySinh.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtpNgaySinh.setFont(fontText);
		dtpNgaySinh.setBackground(bgColor);
		dtpNgaySinh.setForeground(textColor);
		dtpNgaySinh.setLocale(new Locale("vi", "VN"));	// set thoi gian local la VN
		JButton btnDateBD = (JButton) dtpNgaySinh.getComponent(1);
		btnDateBD.setIcon(new ImageScaler("/image/calendar_icon.png", 18, 18).getScaledImageIcon());
		btnDateBD.setBackground(bgColor);
		btnDateBD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		dtpNgaySinh.getEditor().setBackground(bgColor);
		dtpNgaySinh.getEditor().setForeground(textColor);
		dtpNgaySinh.getMonthView().setZoomable(true);
		pnlB2.add(dtpNgaySinh);
		
		pnlB2.add(Box.createHorizontalStrut(20));
		
		JLabel lblSDT = new JLabel(main.read_file_languages.getString("lblSDT") + ":");
		lblSDT.setForeground(textColor);
		lblSDT.setFont(fontText);
		pnlB2.add(lblSDT);
		
		pnlB2.add(Box.createHorizontalStrut(5));
		
		txtSDT = new JTextField();
		txtSDT.setForeground(textColor);
		txtSDT.setFont(fontText);
		txtSDT.setColumns(10);
		txtSDT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtSDT.setBackground(bgColor);
		pnlB2.add(txtSDT);

		pnlB2.add(Box.createHorizontalStrut(20));
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(textColor);
		lblEmail.setFont(fontText);
		pnlB2.add(lblEmail);
		
		pnlB2.add(Box.createHorizontalStrut(5));
		
		txtEmail = new JTextField();
		txtEmail.setForeground(textColor);
		txtEmail.setFont(fontText);
		txtEmail.setColumns(12);
		txtEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtEmail.setBackground(bgColor);
		pnlB2.add(txtEmail);
		
		pnlB2.add(Box.createHorizontalStrut(20));
		
		JLabel lblCCCD = new JLabel(main.read_file_languages.getString("lblCCCD") + ":");
		lblCCCD.setForeground(Color.BLACK);
		lblCCCD.setFont(fontText);
		pnlB2.add(lblCCCD);

		pnlB2.add(Box.createHorizontalStrut(5));
		
		txtCCCD = new JTextField();
		txtCCCD.setForeground(Color.BLACK);
		txtCCCD.setFont(fontText);
		txtCCCD.setColumns(10);
		txtCCCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 

						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtCCCD.setBackground(Color.WHITE);
		pnlB2.add(txtCCCD);
		
		pnThongTinNV.add(Box.createVerticalStrut(20));
		
		//Tạo box chứa thông tin
		JPanel pnlB3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB3.setBackground(bgColor);
		pnThongTinNV.add(pnlB3);
		
		JLabel lblDiaChi = new JLabel(main.read_file_languages.getString("lblDiaChi") + ":");
		lblDiaChi.setForeground(Color.BLACK);
		lblDiaChi.setFont(fontText);
		pnlB3.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setForeground(Color.BLACK);
		txtDiaChi.setFont(fontText);
		txtDiaChi.setColumns(30);
		txtDiaChi.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 


								BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtDiaChi.setBackground(Color.WHITE);
		pnlB3.add(txtDiaChi);
		pnlB3.add(Box.createHorizontalStrut(20));
		
		JPanel pnlMessage = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlMessage.setBackground(bgColor);
		pnlB3.add(pnlMessage);
		pnlMessage.add(lblMessage = new JLabel());
		lblMessage.setForeground(Color.decode("#dc3545"));
		lblMessage.setFont(fontText.deriveFont(Font.ITALIC));
	
		pnThongTinNV.add(Box.createVerticalStrut(10));
		
		//Khởi tạo jpanel chức năng chứa các button chức năng: thêm, sửa, xóa, lưu, hủy
		pnlChucNang = new JPanel();
		pnNorth.add(pnlChucNang, BorderLayout.SOUTH);
		pnlChucNang.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlChucNang.setBackground(bgColor);
		pnlChucNang.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		btnThem = new RoundedButton(main.read_file_languages.getString("btnThem"), null, 20, 0, 1.0f);
		btnThem.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(Color.decode("#3B71CA"));
		btnThem.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnThem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnThem);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnSua = new RoundedButton(main.read_file_languages.getString("btnSua"), null, 20, 0, 1.0f);
		btnSua.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnSua.setForeground(Color.WHITE);
		btnSua.setBackground(Color.decode("#ffc107"));
		btnSua.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnSua.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnSua);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnXoa = new RoundedButton(main.read_file_languages.getString("btnXoa"), null, 20, 0, 1.0f);
		btnXoa.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(Color.decode("#dc3545"));
		btnXoa.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoa);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		pnlChucNang.add(Box.createHorizontalStrut(200));
		
		btnLuu = new RoundedButton(main.read_file_languages.getString("btnLuu"), null, 20, 0, 0.6f);
		btnLuu.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setBackground(Color.decode("#28a745"));
		btnLuu.setIcon(new ImageScaler("/image/save_Icon.png", 24, 24).getScaledImageIcon());
		btnLuu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnLuu);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnHuy = new RoundedButton(main.read_file_languages.getString("btnHuy"), null, 20, 0, 0.6f);
		btnHuy.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBackground(Color.decode("#ffc107"));
		btnHuy.setIcon(new ImageScaler("/image/cancelHD_Icon.png", 24, 24).getScaledImageIcon());
		btnHuy.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnHuy);
		
		// tạo jpanel chứa table nhân viên
		JPanel pnlBangNV = new JPanel();
		pnlBangNV.setLayout(new BoxLayout(pnlBangNV, BoxLayout.X_AXIS));
		add(pnlBangNV, BorderLayout.CENTER);
		String cols[] = {main.read_file_languages.getString("stt"),
				main.read_file_languages.getString("lblMaNV"), 
				main.read_file_languages.getString("lblHoTen"), 
				main.read_file_languages.getString("lblGioiTinh"),
				main.read_file_languages.getString("lblNgaySinh"), 
				main.read_file_languages.getString("lblSDT"), 
				"Email", 
				main.read_file_languages.getString("lblCCCD"),
				main.read_file_languages.getString("lblDiaChi")};
		dtblModel = new DefaultTableModel(cols, 0);
		tblNV = new JTable(dtblModel);
		tblNV.setFont(fontText.deriveFont(14F));
		
		tbhNV = new JTableHeader(tblNV.getColumnModel());
		tbhNV.setReorderingAllowed(false);
		tbhNV.setBackground(componentColor);
		tbhNV.setForeground(Color.WHITE);
		tbhNV.setFont(fontText);
		tblNV.setTableHeader(tbhNV);
		
		//set kích thước cho cột
		tblNV.setRowHeight(20);
		tblNV.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblNV.getColumnModel().getColumn(1).setPreferredWidth(75);
		tblNV.getColumnModel().getColumn(2).setPreferredWidth(250);
		tblNV.getColumnModel().getColumn(3).setPreferredWidth(75);
		tblNV.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblNV.getColumnModel().getColumn(5).setPreferredWidth(100);
		tblNV.getColumnModel().getColumn(6).setPreferredWidth(200);
		tblNV.getColumnModel().getColumn(7).setPreferredWidth(150);
		tblNV.getColumnModel().getColumn(8).setPreferredWidth(300);
		
		//chỉnh trái phải của dữ liệu trong bảng
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tblNV.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		tblNV.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tblNV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblNV.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tblNV.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		tblNV.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		
		//Tạo jscrollpane để tạo scroll cho bảng nhân viên
		JScrollPane scrHD = new JScrollPane(tblNV,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNV.add(scrHD);
		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		btnChonAnh.addActionListener(this);
		
		btnThem.addMouseListener(this);
		btnSua.addMouseListener(this);
		btnXoa.addMouseListener(this);
		btnLuu.addMouseListener(this);
		btnHuy.addMouseListener(this);
		tblNV.addMouseListener(this);
		
		//Không thể thao tác với button lưu và hủy
		displayButtonSaveAndCancel(false);
		
		//Không thể chỉnh sửa txt
		setEditableForTextField(false);
		
		//Set giá trị mặc định để hiển thị
		txtTenNV.setText("Phạm Hữu Thuận");
		txtMatKhau.setText("12345abc@");
		txtSDT.setText("0326635356");
		txtCCCD.setText("012345678912");
		txtEmail.setText("huuthuan1405@gmail.com");
		txtDiaChi.setText("Quang Trung, phường 10, Gò Vấp");
		
		//get du lieu len
		xoaRong();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == tblNV && btnFocus!=btnThem && btnFocus!=btnSua) {
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
		Object o = e.getSource();
		main.music.playSE(2);
		if(o == btnThem) {
			displayButtonSaveAndCancel(true);
			setEditableForTextField(true);
			isThem = true;
			xoaRong();
			
		}
		if(o == btnSua) {
			if(tblNV.getSelectedRow()!=-1) {
				displayButtonSaveAndCancel(true);
				setEditableForTextField(true);
				isThem = false;
			}else {
				setTextError("Bạn chưa chọn nhân viên cần chỉnh sửa!!!");
			}
		}
		if(o == btnXoa) {
			xoaNhanVien();
		}
		if(o == btnLuu) {
			if(isThem==true)
				themNhanVien();
			else
				suaNhanVien();
			
		}
		if(o == btnHuy) {
			if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát? Toàn bộ thông tin thay đổi sẽ mất", "Cảnh báo", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				xoaRong();
				displayButtonSaveAndCancel(false);
				setEditableForTextField(false);
			}
		}
		if(o==btnChonAnh) {
            String newFilePath = getStringPathAvatar();
            if(newFilePath!=null) {
            	String filePath = newFilePath;
                lblAvatar.setIcon(new ImageScaler(filePath, 150, 150).getScaledImageAvatar());
                btnChonAnh.setText(filePath);
            }
		}
	}
	//Thay đổi hiển thị và thao tác button
	private void displayButtonSaveAndCancel(boolean display) {
		if(display == true) {
			btnLuu.setEnabled(true);
			btnLuu.setAlpha(1f);
			btnHuy.setEnabled(true);
			btnHuy.setAlpha(1f);
			btnChonAnh.setEnabled(true);
			btnChonAnh.setAlpha(1f);
			
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
			btnChonAnh.setEnabled(false);
			btnChonAnh.setAlpha(0.6f);
			
			btnThem.setEnabled(true);
			btnThem.setAlpha(1f);
			btnSua.setEnabled(true);
			btnSua.setAlpha(1f);
			btnXoa.setEnabled(true);
			btnXoa.setAlpha(1f);
		}
	}
	//Cho phép và không cho phép chỉnh sửa thông tin
	private void setEditableForTextField(boolean edit) {
		if(edit == true) {
			txtMatKhau.setEditable(true);
			txtTenNV.setEditable(true);
			txtTenNV.requestFocus();
			dtpNgaySinh.setEditable(true);
			txtSDT.setEditable(true);
			txtCCCD.setEditable(true);
			txtEmail.setEditable(true);
			txtDiaChi.setEditable(true);
			radNu.setEnabled(true);
			radNam.setEnabled(true);
		}else {
			txtMaNV.setEditable(false);
			txtMatKhau.setEditable(false);
			txtTenNV.setEditable(false);
			dtpNgaySinh.setEditable(false);
			txtSDT.setEditable(false);
			txtCCCD.setEditable(false);
			txtEmail.setEditable(false);
			txtDiaChi.setEditable(false);
			radNu.setEnabled(false);
			radNam.setEnabled(false);
		}
	}
	//Xóa rỗng thông tin trên giao diện
	private void xoaRong() {
		dsNV = nv_Dao.getAllNhanVien();
		themTatCaNhanVienVaoBang(dsNV);
		txtMaNV.setText(new SinhMaTuDong().sinhMaNV());
		txtMatKhau.setText("abc123@");
		txtTenNV.setText("");
		dtpNgaySinh.setDate(new Date());
		txtSDT.setText("");
		txtEmail.setText("");
		txtCCCD.setText("");
		txtDiaChi.setText("");
		lblAvatar.setIcon(new ImageScaler("/image/employee.png", 150, 150).getScaledImageIcon());
		btnChonAnh.setText(main.read_file_languages.getString("btnChonAnh"));
		radNam.setSelected(true);
		dtpNgaySinh.setDate(new Date(100, 0, 1));
		lblMessage.setText("");
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
			if(o == btnThem || o == btnSua || o == btnXoa) {
				btnFocus = (RoundedButton) o;
				btnFocus.setFocusButton(main.borderFocusColor, 3);
			}
		}else {
			btnFocus = (RoundedButton) o;
			btnFocus.setFocusButton(main.borderFocusColor, 3);
		}
	}
	//Hàm get dữ liệu trên txt ra đối tượng nhân viên
	private NhanVien convertDataToNhanVien() {
		String maNV = txtMaNV.getText();
		String tenNV = txtTenNV.getText();
		String matKhau = txtMatKhau.getText();
		boolean gioiTinh = radNam.isSelected()?true:false;
		Date ngaySinh = dtpNgaySinh.getDate();
		String sdt = txtSDT.getText();
		String email = txtEmail.getText();
		String diaChi = txtDiaChi.getText();
		String cccd = txtCCCD.getText();
		String hinhAnh = btnChonAnh.getText();
		
		return new NhanVien(maNV, matKhau, tenNV, gioiTinh, ngaySinh, sdt, email, cccd, diaChi, hinhAnh);
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
		if(dsNV.get(index).getHinhAnh()!=null && dsNV.get(index).getHinhAnh().trim().length()>0)
			lblAvatar.setIcon(new ImageScaler(dsNV.get(index).getHinhAnh(), 150, 150).getScaledImageAvatar());
		else
			lblAvatar.setIcon(new ImageScaler("/image/employee.png", 150, 150).getScaledImageIcon());
		radNu.setSelected(dsNV.get(index).isGioiTinh()?false:true);
		radNam.setSelected(dsNV.get(index).isGioiTinh());
		
		String dateString = (String) dtblModel.getValueAt(index, 4); // Lấy chuỗi ngày từ bảng
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
		    java.util.Date date = formatter.parse(dateString); // Chuyển đổi chuỗi thành java.util.Date
		    dtpNgaySinh.setDate(date); // Đặt giá trị cho JXDatePicker
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		btnChonAnh.setText(dsNV.get(index).getHinhAnh());
	}
	//open file anh
	private String getStringPathAvatar() {
        JFileChooser fileChooser = new JFileChooser();
        // Đặt thư mục hiện tại
        fileChooser.setCurrentDirectory(new File("/path/to/directory"));
        
        // Lấy danh sách các định dạng ảnh được hỗ trợ
        String[] suffices = ImageIO.getReaderFileSuffixes();
        
        // Thêm bộ lọc cho mỗi định dạng ảnh
        for (int i = 0; i < suffices.length; i++) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(suffices[i] + " files", suffices[i]);
            fileChooser.addChoosableFileFilter(filter);
        }

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }
	private boolean validDataNV() {
		String maNV = txtMaNV.getText();
		String tenNV = txtTenNV.getText();
		String matKhau = txtMatKhau.getText();
		Date ngaySinh = dtpNgaySinh.getDate();
		String sdt = txtSDT.getText();
		String email = txtEmail.getText();
		String cccd = txtCCCD.getText();
		
		if(!maNV.matches("\\S+") || !maNV.matches("^NV\\d{5}$")) {
			setTextError("Mã nhân viên phải có dạng: NV12345!");
			return false;
		}
		if(!matKhau.matches("\\S+") || !matKhau.matches(".*[0-9].*") || !matKhau.matches(".*[a-zA-Z].*") || !matKhau.matches(".*[@#$%^&+=].*")) {
			setTextError("Mật khẩu ít nhất 6 kí tự, bao gồm: chữ cái, số, kí tự đặc biệt!");
			txtMatKhau.selectAll();
			txtMatKhau.requestFocus();
			return false;
		}
		if(tenNV==null && tenNV.trim().length()<=0) {
			setTextError("Tên nhân viên không được để trống!");
			txtTenNV.selectAll();
			txtTenNV.requestFocus();
			return false;
		}
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -18); // trừ 18 năm kể từ hiện tại
        Date eighteenYearsAgo = cal.getTime();
		if(ngaySinh.compareTo(eighteenYearsAgo) > 0) {
			setTextError("Nhân viên phải đủ 18 tuổi trở lên!");
			return false;
		}
		if(!sdt.matches("^(\\+84|84|0)\\d{9}$")) {
			setTextError("Số điện thoại không được rỗng và bắt đầu 0, 84!");
			txtSDT.selectAll();
			txtSDT.requestFocus();
			return false;
		}
		if(!email.matches("^([a-zA-Z0-9]){5,}@([a-zA-Z0-9])+\\.com$")) {
			setTextError("Email phải có dạng abcde@domain.com!");
			txtEmail.selectAll();
			txtEmail.requestFocus();
			return false;
		}
		if(!cccd.matches("^\\d{12}$")) {
			setTextError("Căn cước công dân chỉ gồm 12 chữ số!");
			txtCCCD.selectAll();
			txtCCCD.requestFocus();
			return false;
		}
		return true;
	}
	//Thêm nhân viên từ giao diện vào csdl
	private void themNhanVien() {
		if(validDataNV()==true) {
			NhanVien nvNew = convertDataToNhanVien();
			if(nvNew != null) {
				nvNew.setHinhAnh(main.l.copyFileAvatar(btnChonAnh.getText(), nvNew.getMaNV()));
				if(nv_Dao.themNhanVien(nvNew)) {
					xoaRong();
					lblMessage.setText("Thêm nhân viên thành công!");
					displayButtonSaveAndCancel(false);
					setEditableForTextField(false);
				}else {
					setTextError("Thêm thất bại! Trùng mã!");
				}
			}else {
				setTextError("Thêm thất bại! Có lỗi xảy ra!");
			}
		}
	}
	// sửa một nhân viên được chọn
	private void suaNhanVien() {
		if(tblNV.getSelectedRow()!=-1) {
			if(validDataNV()==true) {
				NhanVien nvNew = convertDataToNhanVien();
				if(nvNew != null) {
					nvNew.setHinhAnh(main.l.copyFileAvatar(btnChonAnh.getText(), nvNew.getMaNV()));
					if(nv_Dao.suaThongTinNhanVien(nvNew)) {
						displayButtonSaveAndCancel(false);
						setEditableForTextField(false);
						xoaRong();
						lblMessage.setText("Sửa thành công!");
					}else {
						setTextError("Sửa thất bại! Không tìm thấy nhân viên!");
					}
				}else {
					setTextError("Sửa thất bại! Lỗi không xác định!");
				}
			}
		}else {
			setTextError("Bạn cần chọn nhân viên muốn sửa!");
		}
	}
	//Xóa nhân viên được chọn
	private void xoaNhanVien() {
		if(tblNV.getSelectedRow()!=-1) {
			String maNV = txtMaNV.getText();
			if(maNV != null) {
				if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa nhân viên đã chọn?", "Cảnh báo!", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					if(nv_Dao.xoaNhanVien(maNV)) {
						main.l.xoaFileAvatar(maNV);
						xoaRong();
						lblMessage.setText("Xóa thành công!");
					}else {
						setTextError("Xóa thất bại! Không tìm thấy nhân viên!");
					}
				}
			}else {
				setTextError("Xóa thất bại! Có lỗi xảy ra!");
			}
		}else {
			setTextError("Bạn cần chọn nhân viên muốn xóa!");
		}
	}
	// thông báo lỗi
	private void setTextError(String message) {
		main.music.playSE(3);
		lblMessage.setText(message);
	}
	//get danh sách nhân viên từ csdl lên table
	private void getDataToTable() {
		dsNV = nv_Dao.getAllNhanVien();
		themTatCaNhanVienVaoBang(dsNV);
	}
	//thêm một nhân viên vào table 
	private void themNhanVienVaoBang(NhanVien nv) {
	    Object[] row = new Object[10];
	    row[0] = dtblModel.getRowCount() + 1;  // STT
	    row[1] = nv.getMaNV();  // Mã NV
	    row[2] = nv.getHoTen();  // Họ tên
	    row[3] = nv.isGioiTinh() ? "Nam" : "Nữ";  // Giới tính
	    row[4] = new SimpleDateFormat("dd-MM-yyyy").format(nv.getNgaySinh());  // Ngày sinh
	    row[5] = nv.getSdt();  // SDT
	    row[6] = nv.getEmail();  // Email
	    row[7] = nv.getcCCD();  // CCCD
	    row[8] = nv.getDiaChi();  // Địa chỉ
	    
	    dtblModel.addRow(row);
	}
	//thêm một ds nhân viên vào bảng
	private void themTatCaNhanVienVaoBang(ArrayList<NhanVien> list) {
		dtblModel.setRowCount(0); //Xóa hết thông tin trên bảng
	    for (NhanVien nv : list) {
	        themNhanVienVaoBang(nv);
	    }
	}
}