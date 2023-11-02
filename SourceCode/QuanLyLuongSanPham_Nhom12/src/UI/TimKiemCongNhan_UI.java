package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Dao.CongNhan_Dao;
import Entity.CongNhan;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class TimKiemCongNhan_UI extends JPanel implements ActionListener, MouseListener {
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

	private RoundedButton btnTim, btnXoaRong, btnXuat;
	private DefaultTableModel dtbModelCN;
	private JTable tblCN;
	private JTableHeader tbhCN;
	private JTextField txtMaCN_dt;
	private JTextField txtHoTenCN_dt;
	private JTextField txtSoCCCD_dt;
	private JTextField txtEmail_dt;
	private JTextField txtSoCCCD;
	private JXDatePicker dpNgaySinh;
	private JCheckBox chkNam, chkNu;
	private JLabel lblAvatar_dt;
	
	private ArrayList<CongNhan> dsCN = new ArrayList<>();
	private CongNhan_Dao cn_dao = new CongNhan_Dao();
	

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
		pnlThongTinCanTim
				.setBorder(new CompoundBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"Th\u00F4ng tin c\u1EA7n t\u00ECm", TitledBorder.LEADING, TitledBorder.TOP, null,
								new Color(0, 0, 0)),
						new EmptyBorder(10, 20, 10, 20)));
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

		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		box_2.add(lblNgaySinh);

		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_9);

		dpNgaySinh = new JXDatePicker((Date) null);
		dpNgaySinh.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dpNgaySinh.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dpNgaySinh.setLocale(new Locale("vi", "VN"));
		box_2.add(dpNgaySinh);

		Component horizontalStrut_10 = Box.createHorizontalStrut(40);
		box_2.add(horizontalStrut_10);

		JLabel lblGioiTinh = new JLabel("Giới tính");
		box_2.add(lblGioiTinh);

		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_11);

		chkNam = new JCheckBox("Nam");
		chkNam.setBackground(new Color(255, 255, 255));
		box_2.add(chkNam);

		Component horizontalStrut_7_1 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_7_1);

		chkNu = new JCheckBox("Nữ");
		chkNu.setBackground(new Color(255, 255, 255));
		box_2.add(chkNu);

		Component verticalStrut_7 = Box.createVerticalStrut(30);
		pnlThongTinCanTim.add(verticalStrut_7);

		Box box_3 = Box.createHorizontalBox();
		pnlThongTinCanTim.add(box_3);

		JLabel lblSoDT = new JLabel("Số DT");
		box_3.add(lblSoDT);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_5);

		txtSoDT = new JTextField();
		box_3.add(txtSoDT);
		txtSoDT.setColumns(10);

		Component horizontalStrut_6 = Box.createHorizontalStrut(40);
		box_3.add(horizontalStrut_6);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		box_3.add(lblDiaChi);

		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_7);

		txtDiaChi = new JTextField();
		box_3.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		Component verticalStrut_2 = Box.createVerticalStrut(30);
		pnlThongTinCanTim.add(verticalStrut_2);

		Box box_4 = Box.createHorizontalBox();
		pnlThongTinCanTim.add(box_4);

		JLabel lblSoCCCD_search = new JLabel("Số CCCD");
		box_4.add(lblSoCCCD_search);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		box_4.add(horizontalStrut);

		txtSoCCCD = new JTextField();
		box_4.add(txtSoCCCD);
		txtSoCCCD.setColumns(10);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		box_4.add(horizontalStrut_1);

		Component verticalStrut_8 = Box.createVerticalStrut(20);
		pnlThongTinCanTim.add(verticalStrut_8);

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
		pnlThongTinCongNhan
				.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin C\u00F4ng Nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 20, 10, 20)));
		pnlBody.add(pnlThongTinCongNhan, BorderLayout.CENTER);
		pnlThongTinCongNhan.setLayout(new BoxLayout(pnlThongTinCongNhan, BoxLayout.X_AXIS));

		JPanel pnlAvatar = new JPanel();
		pnlAvatar.setBackground(new Color(255, 255, 255));
		pnlAvatar.setBorder(new EmptyBorder(30, 0, 0, 0));
		pnlThongTinCongNhan.add(pnlAvatar);

		lblAvatar_dt = new JLabel("");
		lblAvatar_dt.setIcon(new ImageScaler("/image/image_cn_df.jpg", 140, 140).getScaledImageIcon());
		pnlAvatar.add(lblAvatar_dt);
		
		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		pnlThongTinCongNhan.add(horizontalStrut_15);

		JPanel pnlInforDetail_1 = new JPanel();
		pnlInforDetail_1.setBackground(new Color(255, 255, 255));
		pnlInforDetail_1.setBorder(new EmptyBorder(0, 10, 20, 10));
		pnlThongTinCongNhan.add(pnlInforDetail_1);
		pnlInforDetail_1.setLayout(new BoxLayout(pnlInforDetail_1, BoxLayout.Y_AXIS));

		Box box_info_1 = Box.createHorizontalBox();
		pnlInforDetail_1.add(box_info_1);

		JLabel lblMaCN_dt = new JLabel("Mã CN");
		lblMaCN_dt.setFont(new Font("Segoe UI", Font.BOLD, 15));
		box_info_1.add(lblMaCN_dt);

		Component horizontalStrut_20 = Box.createHorizontalStrut(20);
		box_info_1.add(horizontalStrut_20);

		txtMaCN_dt = new JTextField("");
		txtMaCN_dt.setEditable(false);
		txtMaCN_dt.setForeground(Color.BLACK);
		txtMaCN_dt.setFont(null);
		txtMaCN_dt.setColumns(10);
		txtMaCN_dt.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaCN_dt.setBackground(Color.WHITE);
		box_info_1.add(txtMaCN_dt);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_1);

		Box box_info_2 = Box.createHorizontalBox();
		pnlInforDetail_1.add(box_info_2);

		JLabel lblSoCCCD = new JLabel("Số CCCD");
		lblSoCCCD.setFont(new Font("Segoe UI", Font.BOLD, 15));
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
		txtSoCCCD_dt.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		box_info_2.add(txtSoCCCD_dt);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_3);

		Box horizontalBox = Box.createHorizontalBox();
		pnlInforDetail_1.add(horizontalBox);

		JLabel lblSoDT_dt = new JLabel("Số DT");
		lblSoDT_dt.setFont(new Font("Segoe UI", Font.BOLD, 15));
		horizontalBox.add(lblSoDT_dt);

		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_12);

		txtSoDT_dt = new JTextField();
		txtSoDT_dt.setEditable(false);
		txtSoDT_dt.setForeground(Color.BLACK);
		txtSoDT_dt.setFont(null);
		txtSoDT_dt.setColumns(10);
		txtSoDT_dt.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtSoDT_dt.setBackground(Color.WHITE);
		horizontalBox.add(txtSoDT_dt);
		txtSoDT_dt.setColumns(10);

		Component verticalStrut_4 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_4);

		Box horizontalBox_2 = Box.createHorizontalBox();
		pnlInforDetail_1.add(horizontalBox_2);

		JLabel lblGioiTinh_dt = new JLabel("Giới tính");
		lblGioiTinh_dt.setFont(new Font("Segoe UI", Font.BOLD, 15));
		horizontalBox_2.add(lblGioiTinh_dt);

		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_13);

		txtGioiTinh_dt = new JTextField();
		txtGioiTinh_dt.setEditable(false);
		txtGioiTinh_dt.setForeground(Color.BLACK);
		txtGioiTinh_dt.setFont(null);
		txtGioiTinh_dt.setColumns(10);
		txtGioiTinh_dt.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtGioiTinh_dt.setBackground(Color.WHITE);
		horizontalBox_2.add(txtGioiTinh_dt);
		txtGioiTinh_dt.setColumns(10);

		Component verticalStrut_5 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_5);

		Box horizontalBox_3 = Box.createHorizontalBox();
		pnlInforDetail_1.add(horizontalBox_3);

		JLabel lblDiaChi_dt = new JLabel("Địa Chỉ");
		lblDiaChi_dt.setFont(new Font("Segoe UI", Font.BOLD, 15));
		horizontalBox_3.add(lblDiaChi_dt);

		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_14);

		txtDiaChi_dt = new JTextField();
		txtDiaChi_dt.setEditable(false);
		txtDiaChi_dt.setForeground(Color.BLACK);
		txtDiaChi_dt.setFont(null);
		txtDiaChi_dt.setColumns(10);
		txtDiaChi_dt.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtDiaChi_dt.setBackground(Color.WHITE);
		horizontalBox_3.add(txtDiaChi_dt);
		txtDiaChi_dt.setColumns(10);

		Component verticalStrut_5_2 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_5_2);

		Box horizontalBox_3_2 = Box.createHorizontalBox();
		pnlInforDetail_1.add(horizontalBox_3_2);

		JLabel lblGhiChu_dt = new JLabel("Ghi chú");
		lblGhiChu_dt.setFont(new Font("Segoe UI", Font.BOLD, 15));
		horizontalBox_3_2.add(lblGhiChu_dt);

		Component horizontalStrut_14_2 = Box.createHorizontalStrut(20);
		horizontalBox_3_2.add(horizontalStrut_14_2);

		txtGhiChu_dt = new JTextField();
		txtGhiChu_dt.setEditable(false);
		txtGhiChu_dt.setForeground(Color.BLACK);
		txtGhiChu_dt.setFont(null);
		txtGhiChu_dt.setColumns(10);
		txtGhiChu_dt.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtGhiChu_dt.setBackground(Color.WHITE);
		txtGhiChu_dt.setColumns(10);
		horizontalBox_3_2.add(txtGhiChu_dt);

		JPanel pnlInfoDetail_2 = new JPanel();
		pnlInfoDetail_2.setBackground(new Color(255, 255, 255));
		pnlInfoDetail_2.setBorder(new EmptyBorder(0, 5, 20, 0));
		pnlThongTinCongNhan.add(pnlInfoDetail_2);
		pnlInfoDetail_2.setLayout(new BoxLayout(pnlInfoDetail_2, BoxLayout.Y_AXIS));

		Box box_info_1_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(box_info_1_1);

		JLabel lblHoTenCN_dt = new JLabel("Họ Tên");
		lblHoTenCN_dt.setFont(new Font("Segoe UI", Font.BOLD, 15));
		box_info_1_1.add(lblHoTenCN_dt);

		Component horizontalStrut_2_1 = Box.createHorizontalStrut(20);
		box_info_1_1.add(horizontalStrut_2_1);

		txtHoTenCN_dt = new JTextField("");
		txtHoTenCN_dt.setEditable(false);
		txtHoTenCN_dt.setForeground(Color.BLACK);
		txtHoTenCN_dt.setFont(null);
		txtHoTenCN_dt.setColumns(10);
		txtHoTenCN_dt.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtHoTenCN_dt.setBackground(Color.WHITE);
		box_info_1_1.add(txtHoTenCN_dt);

		Component verticalStrut_1_1 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_1_1);

		Box box_info_2_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(box_info_2_1);

		JLabel lblEmail_dt = new JLabel("Email");
		lblEmail_dt.setFont(new Font("Segoe UI", Font.BOLD, 15));
		box_info_2_1.add(lblEmail_dt);

		Component horizontalStrut_12_1_1 = Box.createHorizontalStrut(20);
		box_info_2_1.add(horizontalStrut_12_1_1);

		txtEmail_dt = new JTextField();
		txtEmail_dt.setEditable(false);
		txtEmail_dt.setForeground(Color.BLACK);
		txtEmail_dt.setFont(null);
		txtEmail_dt.setColumns(10);
		txtEmail_dt.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtEmail_dt.setBackground(Color.WHITE);
		box_info_2_1.add(txtEmail_dt);

		Component verticalStrut_3_1 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_3_1);

		Box horizontalBox_4 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(horizontalBox_4);

		JLabel lblNgaySinh_dt = new JLabel("Ng Sinh");
		lblNgaySinh_dt.setFont(new Font("Segoe UI", Font.BOLD, 15));
		horizontalBox_4.add(lblNgaySinh_dt);

		Component horizontalStrut_12_1 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_12_1);

		txtNgaySinh_dt = new JTextField();
		txtNgaySinh_dt.setEditable(false);
		txtNgaySinh_dt.setForeground(Color.BLACK);
		txtNgaySinh_dt.setFont(null);
		txtNgaySinh_dt.setColumns(10);
		txtNgaySinh_dt.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtNgaySinh_dt.setBackground(Color.WHITE);
		txtNgaySinh_dt.setColumns(10);
		horizontalBox_4.add(txtNgaySinh_dt);

		Component verticalStrut_4_1 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_4_1);

		Box horizontalBox_2_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(horizontalBox_2_1);

		JLabel lblNgayVaoLam_dt = new JLabel("Ngày vào làm");
		lblNgayVaoLam_dt.setFont(new Font("Segoe UI", Font.BOLD, 15));
		horizontalBox_2_1.add(lblNgayVaoLam_dt);

		Component horizontalStrut_13_1 = Box.createHorizontalStrut(20);
		horizontalBox_2_1.add(horizontalStrut_13_1);

		txtNgayVaoLam_dt = new JTextField();
		txtNgayVaoLam_dt.setEditable(false);
		txtNgayVaoLam_dt.setForeground(Color.BLACK);
		txtNgayVaoLam_dt.setFont(null);
		txtNgayVaoLam_dt.setColumns(10);
		txtNgayVaoLam_dt.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtNgayVaoLam_dt.setBackground(Color.WHITE);
		txtNgayVaoLam_dt.setColumns(10);
		horizontalBox_2_1.add(txtNgayVaoLam_dt);

		Component verticalStrut_5_1 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_5_1);

		Box horizontalBox_3_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(horizontalBox_3_1);

		JLabel lblMatKhau_dt = new JLabel("Mật khẩu");
		lblMatKhau_dt.setFont(new Font("Segoe UI", Font.BOLD, 15));
		horizontalBox_3_1.add(lblMatKhau_dt);

		Component horizontalStrut_14_1 = Box.createHorizontalStrut(20);
		horizontalBox_3_1.add(horizontalStrut_14_1);

		txtMatKhau_dt = new JTextField();
		txtMatKhau_dt.setEditable(false);
		txtMatKhau_dt.setForeground(Color.BLACK);
		txtMatKhau_dt.setFont(null);
		txtMatKhau_dt.setColumns(10);
		txtMatKhau_dt.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
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

		Component horizontalStrut_8 = Box.createHorizontalStrut(10);
		pnlThongTinCongNhan.add(horizontalStrut_8);

		JPanel pnlTable = new JPanel();
		pnlTable.setBackground(new Color(255, 255, 255));
		pnlTable.setBorder(new CompoundBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch C\u00F4ng Nh\u00E2n \u0111\u00E3 t\u00ECm", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(0, 0, 0)), new EmptyBorder(0, 10, 0, 10)));
		pnlTable.setLayout(new BorderLayout(0, 0));

		String cols[] = { "Mã CN", "Họ tên", "Giới tính", "SĐT", "Email", "Địa chỉ", "Ngày vào làm", "Ghi chú" };
		dtbModelCN = new DefaultTableModel(cols, 0);
		tblCN = new JTable(dtbModelCN);

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

		// Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblCN, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlTable.add(scrSP);

		pnlTKCN.add(pnlTable, BorderLayout.CENTER);
		
		btnTim.addActionListener(this);
		btnXoaRong.addActionListener(this);
		
		tblCN.addMouseListener(this);
		
		
		setEditableForTextField(false);
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o==tblCN) {
			int item = tblCN.getSelectedRow();
			if(item != -1) {
				hienThiThongTinCN(item);
			}
		}

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
		if(o==btnTim) {
			timKiemCongNhan();
		}
		if(o==btnXoaRong) {
			xoaRong();
		}
	}

	// HÀM ĐỂ SET CÁC BUTTON EDITABLE
	private void setEditableForTextField(boolean active) {
		txtMaCN_dt.setEditable(active);
		txtHoTenCN_dt.setEditable(active);
		txtSoCCCD_dt.setEditable(active);
		txtEmail_dt.setEditable(active);
		txtSoDT_dt.setEditable(active);
		txtNgaySinh_dt.setEditable(active);
		txtGioiTinh_dt.setEditable(active);
		txtNgayVaoLam_dt.setEditable(active);
		txtDiaChi_dt.setEditable(active);
		txtMatKhau_dt.setEditable(active);
		txtGhiChu_dt.setEditable(active);
		
		
		chkNam.setSelected(true);
		chkNu.setSelected(true);
	}

	// HÀM XÓA RỖNG
	private void xoaRong() {
		txtMaCN.setText("");
		txtHoTenCN.setText("");
		txtSoCCCD.setText("");
		txtSoDT_dt.setText("");
		dpNgaySinh.setDate(null);
		chkNam.setSelected(true);
		chkNu.setSelected(true);
		txtSoDT.setText("");
		txtDiaChi.setText("");
		txtSoCCCD.setText("");
		
		txtMaCN_dt.setText("");
		txtHoTenCN_dt.setText("");
		txtSoCCCD_dt.setText("");
		txtEmail_dt.setText("");
		txtSoDT_dt.setText("");
		txtNgaySinh_dt.setText("");
		txtGioiTinh_dt.setText("");
		txtNgayVaoLam_dt.setText("");
		txtDiaChi_dt.setText("");
		txtMatKhau_dt.setText("");
		txtGhiChu_dt.setText("");
	}
	// HÀM HIỂN THỊ CÔNG NHÂN KHI CHỌN ROW Ở TABLE
	private void hienThiThongTinCN(int index) {
		txtMaCN_dt.setText(dsCN.get(index).getMaCN());
		txtHoTenCN_dt.setText(dsCN.get(index).getHoTen());
		txtSoCCCD_dt.setText(dsCN.get(index).getSoCCCD());
		txtEmail_dt.setText(dsCN.get(index).getEmail());
		txtSoDT_dt.setText(dsCN.get(index).getSDT());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateNgaySinhString = dateFormat.format(dsCN.get(index).getNgaySinh());
		txtNgaySinh_dt.setText(dateNgaySinhString);
		
		txtGioiTinh_dt.setText(dsCN.get(index).getGioiTinh() ? "Nam": "Nữ");
		String dateNgayVaoLamString = dateFormat.format(dsCN.get(index).getNgayVaoLam());
		txtNgayVaoLam_dt.setText(dateNgayVaoLamString);
		
		txtDiaChi_dt.setText(dsCN.get(index).getDiaChi());
		txtMatKhau_dt.setText(dsCN.get(index).getMatKhau());
		txtGhiChu_dt.setText(dsCN.get(index).getGhiChu());
		
		lblAvatar_dt.setIcon(new ImageScaler("/image/" + dsCN.get(index).getAnhDaiDien(), 140, 140).getScaledImageIcon());
		
	}
	
	

	// HÀM TÌM KIẾM CÔNG NHÂN
	private void timKiemCongNhan() {
		String maCN = txtMaCN.getText();
		String hoten = txtHoTenCN.getText();
		Date ngaySinh = dpNgaySinh.getDate();
		int nam = chkNam.isSelected() ? 1 : -1;
		int nu = chkNu.isSelected() ? 0 : -1;
		String sDT = txtSoDT.getText();
		String diaChi = txtDiaChi.getText();
		String soCCCD = txtSoCCCD.getText();
		
		dsCN = cn_dao.timKiemCongNhan(maCN, hoten, ngaySinh, nam, nu, sDT, diaChi, soCCCD);
		
		if(dsCN.size()!=0) {
			alertSuccess("Đã tìm thấy " + dsCN.size() + " Công nhân");
			themAllCongNhanVaoTable(dsCN);
		}else {
			alertNotification("Không tìm thấy công nhân");
			themAllCongNhanVaoTable(dsCN);
		}
	}

	// THÊM 1 CÔNG NHÂN VÀO TABLE
	private void themMotCongNhanVaoTable(CongNhan cn) {
		Object[] row = new Object[99];
		
		row[0] = dtbModelCN.getRowCount() +1;
		row[1] = cn.getMaCN();
		row[2] = cn.getHoTen();
		row[3] = cn.getGioiTinh() ? "Nam" : "Nữ";
		row[4] = cn.getSDT();
		row[5] = cn.getEmail();
		row[6] = cn.getNgayVaoLam();
		row[7] = cn.getGhiChu();
		dtbModelCN.addRow(row);
		
	}

	// THÊM TẤT CẢ CÔNG NHÂN VÀO TABLE
	private void themAllCongNhanVaoTable(ArrayList<CongNhan> listCn) {
		dtbModelCN.setRowCount(0);
		for(int i=0; i< dsCN.size();i++) {
			themMotCongNhanVaoTable(dsCN.get(i));
		}
	}

	
	
	
	
	
	// ALERT

	public int alertNotification(String textError) {
		main.music.playSE(3);
		String[] options = { "Cancel" };
		int result = JOptionPane.showOptionDialog(main, textError, "NOTIFICATION", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		return result;
	}

	public int alertSuccess(String textError) {
		main.music.playSE(1);
		String[] options = { "Cancel" };
		int result = JOptionPane.showOptionDialog(main, textError, "NOTIFICATION", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		return result;
	}

}
