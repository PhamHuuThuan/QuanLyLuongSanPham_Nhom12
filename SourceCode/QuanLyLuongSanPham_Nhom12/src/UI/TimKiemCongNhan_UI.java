package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class TimKiemCongNhan_UI extends JPanel {
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField txtMaCN;
	private JTextField txtHoTenCN;
	private JTextField txtSoDT;
	private JTextField txtDiaChi;
	private JTextField txtSoDT_dt;
	private JTextField txtGioiTinh_dt;
	private JTextField txtDiaChi_dt;
	private JTextField txtNgaySinh_dt;
	private JTextField txtNgayVaoLam_dt;
	private JTextField txtMatKhau_dt;
	private JTextField txtGhiChu_dt;
	
	private RoundedButton btnTim, btnXoaRong,btnXuat;
	private DefaultTableModel dtbModelCN;
	private JTableHeader tbhCN;
	private JTextField txtMaCN_dt;
	private JTextField txtHoTen_dt;
	private JTextField txtSoCCCD_dt;
	private JTextField txtEmail_dt;

	public TimKiemCongNhan_UI(MainUI main) {
		this.main = main;
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTKCN = new JPanel();
		add(pnlTKCN, BorderLayout.NORTH);
		pnlTKCN.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBody = new JPanel();
		pnlTKCN.add(pnlBody, BorderLayout.NORTH);
		pnlBody.setLayout(new BorderLayout(0, 0));
		
		
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnlTitle.setBackground(new Color(255, 255, 255));
		pnlBody.add(pnlTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("TÌM KIẾM CÔNG NHÂN");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 21F));
		pnlTitle.add(lblTitle);
		
		JPanel pnlThongTinCanTim = new JPanel();
		pnlThongTinCanTim.setBackground(new Color(255, 255, 255));
		pnlThongTinCanTim.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin c\u1EA7n t\u00ECm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 20, 10, 20)));
		pnlBody.add(pnlThongTinCanTim, BorderLayout.WEST);
		pnlThongTinCanTim.setLayout(new BoxLayout(pnlThongTinCanTim, BoxLayout.Y_AXIS));
		
		Box box_1 = Box.createHorizontalBox();
		pnlThongTinCanTim.add(box_1);
		
		JLabel lblMaCN = new JLabel("Mã CN");
		box_1.add(lblMaCN);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_2);
		
		txtMaCN = new JTextField();
		box_1.add(txtMaCN);
		txtMaCN.setColumns(10);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(40);
		box_1.add(horizontalStrut_3);
		
		JLabel lblTenCN = new JLabel("Họ Tên");
		box_1.add(lblTenCN);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_4);
		
		txtHoTenCN = new JTextField();
		box_1.add(txtHoTenCN);
		txtHoTenCN.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(30);
		pnlThongTinCanTim.add(verticalStrut);
		
		Box box_2 = Box.createHorizontalBox();
		pnlThongTinCanTim.add(box_2);
		
		JLabel lblSoDT = new JLabel("Số DT");
		box_2.add(lblSoDT);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_5);
		
		txtSoDT = new JTextField();
		box_2.add(txtSoDT);
		txtSoDT.setColumns(10);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(40);
		box_2.add(horizontalStrut_6);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		box_2.add(lblDiaChi);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_7);
		
		txtDiaChi = new JTextField();
		box_2.add(txtDiaChi);
		txtDiaChi.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(30);
		pnlThongTinCanTim.add(verticalStrut_2);
		
		Box box_3 = Box.createHorizontalBox();
		pnlThongTinCanTim.add(box_3);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		box_3.add(lblNgaySinh);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_9);
		
		JXDatePicker dpNgaySinh = new JXDatePicker((Date) null);
		dpNgaySinh.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dpNgaySinh.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dpNgaySinh.setLocale(new Locale("vi", "VN"));
		box_3.add(dpNgaySinh);
		
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(40);
		box_3.add(horizontalStrut_10);
		
		JLabel lblGioiTinh = new JLabel("Giới tính");
		box_3.add(lblGioiTinh);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_11);
		
		JRadioButton rdbNam = new JRadioButton("nam");
		rdbNam.setSelected(true);
		rdbNam.setForeground(Color.BLACK);
		rdbNam.setFont(null);
		rdbNam.setBackground(Color.WHITE);
		box_3.add(rdbNam);
		
		Component horizontalStrut_7_1 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_7_1);
		
		JRadioButton rdbNu = new JRadioButton("nữ");
		rdbNu.setForeground(Color.BLACK);
		rdbNu.setBackground(Color.WHITE);
		box_3.add(rdbNu);
		
		Component verticalStrut_7 = Box.createVerticalStrut(40);
		pnlThongTinCanTim.add(verticalStrut_7);
		
		JPanel pnlControl = new JPanel();
		pnlControl.setBackground(new Color(255, 255, 255));
		pnlThongTinCanTim.add(pnlControl);
		
		
		btnTim = new RoundedButton("Thêm", null, 20, 0, 1.0f);
		
		btnTim.setText("Tìm kiếm");
		btnTim.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnTim.setForeground(Color.WHITE);
		btnTim.setBackground(Color.decode("#3B71CA"));
		btnTim.setIcon(new ImageScaler("/image/searchwhite_icon.png", 22, 22).getScaledImageIcon());
		btnTim.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlControl.add(btnTim);
		pnlControl.add(Box.createHorizontalStrut(25));
		
		btnXoaRong = new RoundedButton("Sửa", null, 20, 0, 1.0f);
		btnXoaRong.setText("Xóa rỗng");
		btnXoaRong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setBackground(Color.decode("#ffc107"));
		btnXoaRong.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnXoaRong.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlControl.add(btnXoaRong);
		
		JPanel pnlThongTinCongNhan = new JPanel();
		pnlThongTinCongNhan.setBackground(new Color(255, 255, 255));
		pnlThongTinCongNhan.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin C\u00F4ng Nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 0, 10, 10)));
		pnlBody.add(pnlThongTinCongNhan, BorderLayout.CENTER);
		pnlThongTinCongNhan.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel pnlAvatar = new JPanel();
		pnlAvatar.setBackground(new Color(255, 255, 255));
		pnlAvatar.setBorder(new EmptyBorder(30, 0, 0, 0));
		pnlThongTinCongNhan.add(pnlAvatar);
		
		JLabel lblAvatar_dt = new JLabel("");
		lblAvatar_dt.setIcon(new ImageScaler("/image/image_cn_df.jpg", 150, 150).getScaledImageIcon());
		pnlAvatar.add(lblAvatar_dt);
		
		JPanel pnlInforDetail_1 = new JPanel();
		pnlInforDetail_1.setBackground(new Color(255, 255, 255));
		pnlInforDetail_1.setBorder(new EmptyBorder(0, 10, 20, 10));
		pnlThongTinCongNhan.add(pnlInforDetail_1);
		pnlInforDetail_1.setLayout(new BoxLayout(pnlInforDetail_1, BoxLayout.Y_AXIS));
		
		Box box_info_1 = Box.createHorizontalBox();
		pnlInforDetail_1.add(box_info_1);
		
		JLabel lblMaCN_dt = new JLabel("Mã CN");
		lblMaCN_dt.setForeground(Color.BLACK);
		lblMaCN_dt.setFont(null);
		box_info_1.add(lblMaCN_dt);
		
		Component horizontalStrut_20 = Box.createHorizontalStrut(20);
		box_info_1.add(horizontalStrut_20);
		
		txtMaCN_dt = new JTextField("");
		txtMaCN_dt.setEditable(false);
		txtMaCN_dt.setForeground(Color.BLACK);
		txtMaCN_dt.setFont(null);
		txtMaCN_dt.setColumns(10);
		txtMaCN_dt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaCN_dt.setBackground(Color.WHITE);
		box_info_1.add(txtMaCN_dt);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_1);
		
		Box box_info_2 = Box.createHorizontalBox();
		pnlInforDetail_1.add(box_info_2);
		
		JLabel lblSoCCCD = new JLabel("Số CCCD");
		lblSoCCCD.setForeground(Color.BLACK);
		lblSoCCCD.setFont(null);
		box_info_2.add(lblSoCCCD);
		
		Component horizontalStrut_14_1_2 = Box.createHorizontalStrut(20);
		box_info_2.add(horizontalStrut_14_1_2);
		
		txtSoCCCD_dt = new JTextField();
		txtSoCCCD_dt.setText("");
		txtSoCCCD_dt.setEditable(false);
		txtSoCCCD_dt.setForeground(Color.BLACK);
		txtSoCCCD_dt.setFont(null);
		txtSoCCCD_dt.setColumns(10);
		txtSoCCCD_dt.setBackground(Color.WHITE);
		txtSoCCCD_dt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		box_info_2.add(txtSoCCCD_dt);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_3);
		
		Box horizontalBox = Box.createHorizontalBox();
		pnlInforDetail_1.add(horizontalBox);
		
		JLabel lblSoDT_dt = new JLabel("Số DT");
		horizontalBox.add(lblSoDT_dt);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_12);
		
		txtSoDT_dt = new JTextField();
		txtSoDT_dt.setEditable(false);
		txtSoDT_dt.setForeground(Color.BLACK);
		txtSoDT_dt.setFont(null);
		txtSoDT_dt.setColumns(10);
		txtSoDT_dt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtSoDT_dt.setBackground(Color.WHITE);
		horizontalBox.add(txtSoDT_dt);
		txtSoDT_dt.setColumns(10);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_4);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		pnlInforDetail_1.add(horizontalBox_2);
		
		JLabel lblGioiTinh_dt = new JLabel("Giới tính");
		horizontalBox_2.add(lblGioiTinh_dt);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_13);
		
		txtGioiTinh_dt = new JTextField();
		txtGioiTinh_dt.setEditable(false);
		txtGioiTinh_dt.setForeground(Color.BLACK);
		txtGioiTinh_dt.setFont(null);
		txtGioiTinh_dt.setColumns(10);
		txtGioiTinh_dt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtGioiTinh_dt.setBackground(Color.WHITE);
		horizontalBox_2.add(txtGioiTinh_dt);
		txtGioiTinh_dt.setColumns(10);
		
		Component verticalStrut_5 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_5);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		pnlInforDetail_1.add(horizontalBox_3);
		
		JLabel lblDiaChi_dt = new JLabel("Địa Chỉ");
		horizontalBox_3.add(lblDiaChi_dt);
		
		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_14);
		
		txtDiaChi_dt = new JTextField();
		txtDiaChi_dt.setEditable(false);
		txtDiaChi_dt.setForeground(Color.BLACK);
		txtDiaChi_dt.setFont(null);
		txtDiaChi_dt.setColumns(10);
		txtDiaChi_dt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtDiaChi_dt.setBackground(Color.WHITE);
		horizontalBox_3.add(txtDiaChi_dt);
		txtDiaChi_dt.setColumns(10);
		
		Component verticalStrut_5_2 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_5_2);
		
		Box horizontalBox_3_2 = Box.createHorizontalBox();
		pnlInforDetail_1.add(horizontalBox_3_2);
		
		JLabel lblGhiChu_dt = new JLabel("Ghi chú");
		horizontalBox_3_2.add(lblGhiChu_dt);
		
		Component horizontalStrut_14_2 = Box.createHorizontalStrut(20);
		horizontalBox_3_2.add(horizontalStrut_14_2);
		
		txtGhiChu_dt = new JTextField();
		txtGhiChu_dt.setEditable(false);
		txtGhiChu_dt.setForeground(Color.BLACK);
		txtGhiChu_dt.setFont(null);
		txtGhiChu_dt.setColumns(10);
		txtGhiChu_dt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtGhiChu_dt.setBackground(Color.WHITE);
		txtGhiChu_dt.setColumns(10);
		horizontalBox_3_2.add(txtGhiChu_dt);
		
		
		JPanel pnlInfoDetail_2 = new JPanel();
		pnlInfoDetail_2.setBackground(new Color(255, 255, 255));
		pnlInfoDetail_2.setBorder(new EmptyBorder(0, 10, 20, 10));
		pnlThongTinCongNhan.add(pnlInfoDetail_2);
		pnlInfoDetail_2.setLayout(new BoxLayout(pnlInfoDetail_2, BoxLayout.Y_AXIS));
		
		Box box_info_1_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(box_info_1_1);
		
		JLabel lblHoTen = new JLabel("Họ Tên");
		lblHoTen.setForeground(Color.BLACK);
		lblHoTen.setFont(null);
		box_info_1_1.add(lblHoTen);
		
		Component horizontalStrut_2_1 = Box.createHorizontalStrut(20);
		box_info_1_1.add(horizontalStrut_2_1);
		
		txtHoTen_dt = new JTextField("");
		txtHoTen_dt.setEditable(false);
		txtHoTen_dt.setForeground(Color.BLACK);
		txtHoTen_dt.setFont(null);
		txtHoTen_dt.setColumns(10);
		txtHoTen_dt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
										BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtHoTen_dt.setBackground(Color.WHITE);
		box_info_1_1.add(txtHoTen_dt);
		
		Component verticalStrut_1_1 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_1_1);
		
		Box box_info_2_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(box_info_2_1);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(null);
		box_info_2_1.add(lblEmail);
		
		Component horizontalStrut_12_1_1 = Box.createHorizontalStrut(20);
		box_info_2_1.add(horizontalStrut_12_1_1);
		
		txtEmail_dt = new JTextField();
		txtEmail_dt.setEditable(false);
		txtEmail_dt.setForeground(Color.BLACK);
		txtEmail_dt.setFont(null);
		txtEmail_dt.setColumns(10);
		txtEmail_dt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
										BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtEmail_dt.setBackground(Color.WHITE);
		box_info_2_1.add(txtEmail_dt);
		
		Component verticalStrut_3_1 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_3_1);
		
		Box horizontalBox_4 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(horizontalBox_4);
		
		JLabel lblNgaySinh_dt = new JLabel("Ng Sinh");
		horizontalBox_4.add(lblNgaySinh_dt);
		
		Component horizontalStrut_12_1 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_12_1);
		
		txtNgaySinh_dt = new JTextField();
		txtNgaySinh_dt.setEditable(false);
		txtNgaySinh_dt.setForeground(Color.BLACK);
		txtNgaySinh_dt.setFont(null);
		txtNgaySinh_dt.setColumns(10);
		txtNgaySinh_dt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtNgaySinh_dt.setBackground(Color.WHITE);
		txtNgaySinh_dt.setColumns(10);
		horizontalBox_4.add(txtNgaySinh_dt);
		
		Component verticalStrut_4_1 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_4_1);
		
		Box horizontalBox_2_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(horizontalBox_2_1);
		
		JLabel lblNgayVaoLam_dt = new JLabel("Ngày vào làm");
		horizontalBox_2_1.add(lblNgayVaoLam_dt);
		
		Component horizontalStrut_13_1 = Box.createHorizontalStrut(20);
		horizontalBox_2_1.add(horizontalStrut_13_1);
		
		txtNgayVaoLam_dt = new JTextField();
		txtNgayVaoLam_dt.setEditable(false);
		txtNgayVaoLam_dt.setForeground(Color.BLACK);
		txtNgayVaoLam_dt.setFont(null);
		txtNgayVaoLam_dt.setColumns(10);
		txtNgayVaoLam_dt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtNgayVaoLam_dt.setBackground(Color.WHITE);
		txtNgayVaoLam_dt.setColumns(10);
		horizontalBox_2_1.add(txtNgayVaoLam_dt);
		
		Component verticalStrut_5_1 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_5_1);
		
		Box horizontalBox_3_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(horizontalBox_3_1);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu");
		horizontalBox_3_1.add(lblMatKhau);
		
		Component horizontalStrut_14_1 = Box.createHorizontalStrut(20);
		horizontalBox_3_1.add(horizontalStrut_14_1);
		
		txtMatKhau_dt = new JTextField();
		txtMatKhau_dt.setEditable(false);
		txtMatKhau_dt.setForeground(Color.BLACK);
		txtMatKhau_dt.setFont(null);
		txtMatKhau_dt.setColumns(10);
		txtMatKhau_dt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMatKhau_dt.setBackground(Color.WHITE);
		txtMatKhau_dt.setColumns(10);
		horizontalBox_3_1.add(txtMatKhau_dt);
		
		Component verticalStrut_6 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_6);
		
		Box horizontalBox_3_1_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(horizontalBox_3_1_1);
		
		JLabel lblNewLabel_11_1_1 = new JLabel(".");
		horizontalBox_3_1_1.add(lblNewLabel_11_1_1);
		
		
		JPanel pnlTable = new JPanel();
		pnlTable.setBackground(new Color(255, 255, 255));
		pnlTable.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch C\u00F4ng Nh\u00E2n \u0111\u00E3 t\u00ECm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(0, 10, 0, 10)));
		pnlTable.setLayout(new BorderLayout(0, 0));
		
		String cols[] = {"Mã CN", "Họ tên", "Giới tính", "SĐT","Emai", "Địa chỉ",  "Ngày vào làm", "Ghi chú"};
		dtbModelCN = new DefaultTableModel(cols, 0);
		JTable tblCN = new JTable(dtbModelCN);
		
		JPanel pnlXuat = new JPanel();
		pnlXuat.setBackground(Color.WHITE);
		pnlTable.add(pnlXuat, BorderLayout.NORTH);
		pnlXuat.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnXuat = new RoundedButton(main.read_file_languages.getString("btn_XuatDS"), null, 5, 0, 1.0f);
		btnXuat.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setBackground(Color.decode("#28a745"));
		btnXuat.setIcon(new ImageScaler("/image/printer_icon.png", 20, 20).getScaledImageIcon());
		btnXuat.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		pnlXuat.add(btnXuat);

		tbhCN = new JTableHeader(tblCN.getColumnModel());
		tbhCN.setReorderingAllowed(false);
		tbhCN.setBackground(componentColor);
		tbhCN.setForeground(Color.WHITE);
		tbhCN.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblCN.setTableHeader(tbhCN);
		
		tblCN.setRowHeight(20);
		tblCN.getColumnModel().getColumn(0).setPreferredWidth(75);
		tblCN.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblCN.getColumnModel().getColumn(2).setPreferredWidth(75);
		tblCN.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblCN.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblCN.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblCN.getColumnModel().getColumn(6).setPreferredWidth(100);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblCN,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlTable.add(scrSP);
		

		pnlTKCN.add(pnlTable, BorderLayout.CENTER);
		
		
		
		
		
	}
}
