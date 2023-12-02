package UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Dao.CongNhan_Dao;
import Entity.CongNhan;
import Util.SinhMaTuDong;

import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class QuanLyCongNhan_UI extends JPanel implements ActionListener, MouseListener {
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private Font fontText;
	private RoundedButton btnThem, btnSua, btnXoa, btnLuu, btnHuy;
	private JButton btnEditAvatar;
	private JLabel lblAvatar;
	private JPanel pnlAvatar;
	private DefaultTableModel dtblModel;
	private JTableHeader tbhCN;
	private JTextField txtMaCN;
	private JTextField txtHoTen;
	private JTextField txtMatKhau;
	private JTextField txtSoDT;
	private JTextField txtEmail;
	private JTextField txtSoCCCD;
	private JTextField txtDiaChi;
	private JTextField txtGhiChu;
	private boolean isThemCongNhan = false;
	private JComboBox<String> cmbGioiTinh;
	private boolean gioiTinhCheck = true;

	private CongNhan_Dao cn_dao = new CongNhan_Dao();

	private String pathNameAvatar = "image_cn_df.jpg";

	private JXDatePicker dpNgayVaoLam, dpNgaySinh;
	private JTable tblCN;

	private ArrayList<CongNhan> dsCN = new ArrayList<>();

	public QuanLyCongNhan_UI(MainUI main) {
		this.main = main;
		fontText = main.roboto_regular.deriveFont(Font.PLAIN, 16F);
		pathNameAvatar = "image_cn_df.jpg";

		setLayout(new BorderLayout(0, 0));
		setBackground(bgColor);

		JPanel pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(10, 30, 0, 30));
		pnlContent.setBackground(new Color(255, 255, 255));
		add(pnlContent, BorderLayout.NORTH);
		pnlContent.setLayout(new BorderLayout(0, 0));

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(null);
		pnlTitle.setBackground(new Color(255, 255, 255));
		pnlContent.add(pnlTitle, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("QUẢN LÝ CÔNG NHÂN");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle.add(lblTitle);

		JPanel pnlBody = new JPanel();
		pnlBody.setBackground(bgColor);

		TitledBorder titleBorder = BorderFactory
				.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Thông tin Công Nhân");
		pnlBody.setBorder(
				BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBody.setLayout(new BoxLayout(pnlBody, BoxLayout.X_AXIS));
		pnlContent.add(pnlBody, BorderLayout.CENTER);

		pnlAvatar = new JPanel();
		pnlAvatar.setBackground(new Color(255, 255, 255));
		pnlAvatar.setBorder(null);
		pnlBody.add(pnlAvatar);
		pnlAvatar.setLayout(new BorderLayout(0, 10));

		// button anh dai dien

		btnEditAvatar = new JButton("image_cn_df.jpg");
		pnlAvatar.add(btnEditAvatar, BorderLayout.SOUTH);
		lblAvatar = new JLabel("");
		lblAvatar.setIcon(new ImageScaler("/image/" + pathNameAvatar, 150, 150).getScaledImageIcon());
		lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
		pnlAvatar.add(lblAvatar, BorderLayout.CENTER);

		Component horizontalStrut = Box.createHorizontalStrut(40);
		pnlBody.add(horizontalStrut);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		pnlBody.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		Box box_1 = Box.createHorizontalBox();
		box_1.setAlignmentY(0.5f);
		panel_1.add(box_1);

		JLabel lblMaCN = new JLabel("Mã CN");
		lblMaCN.setForeground(Color.BLACK);
		lblMaCN.setFont(null);
		box_1.add(lblMaCN);

		Component horizontalStrut_20 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_20);

		txtMaCN = new JTextField("");
		txtMaCN.setForeground(Color.BLACK);
		txtMaCN.setFont(null);
		txtMaCN.setColumns(10);
		txtMaCN.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),

						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaCN.setBackground(Color.WHITE);
		box_1.add(txtMaCN);

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_1_1);

		JLabel lblHoTen = new JLabel("Họ Tên");
		lblHoTen.setForeground(Color.BLACK);
		lblHoTen.setFont(null);
		box_1.add(lblHoTen);

		Component horizontalStrut_2_1 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_2_1);

		txtHoTen = new JTextField("");
		txtHoTen.setForeground(Color.BLACK);
		txtHoTen.setFont(null);
		txtHoTen.setColumns(10);
		txtHoTen.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),

						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtHoTen.setBackground(Color.WHITE);
		box_1.add(txtHoTen);

		Component horizontalStrut_3_1 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_3_1);

		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setForeground(Color.BLACK);
		lblMatKhau.setFont(null);
		box_1.add(lblMatKhau);

		Component horizontalStrut_4_1 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_4_1);

		txtMatKhau = new JTextField();
		txtMatKhau.setForeground(Color.BLACK);
		txtMatKhau.setFont(null);
		txtMatKhau.setColumns(10);
		txtMatKhau.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),

						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMatKhau.setBackground(Color.WHITE);
		box_1.add(txtMatKhau);

		Component horizontalStrut_5_1 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_5_1);

		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setForeground(Color.BLACK);
		lblGioiTinh.setFont(null);
		box_1.add(lblGioiTinh);

		Component horizontalStrut_6_1 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_6_1);

		cmbGioiTinh = new JComboBox<>();
		cmbGioiTinh.setPreferredSize(new Dimension(70, 10));
		cmbGioiTinh.addItem("Nam");
		cmbGioiTinh.addItem("Nữ");
		box_1.add(cmbGioiTinh);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_2);

		Box box_2 = Box.createHorizontalBox();
		panel_1.add(box_2);

		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		lblNgaySinh.setForeground(Color.BLACK);
		lblNgaySinh.setFont(null);
		box_2.add(lblNgaySinh);

		Component horizontalStrut_8_1 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_8_1);

		dpNgaySinh = new JXDatePicker(new Date(100,0,1));
		dpNgaySinh.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dpNgaySinh.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dpNgaySinh.setLocale(new Locale("vi", "VN"));
		dpNgaySinh.getMonthView().setZoomable(true);

		box_2.add(dpNgaySinh);

		Component horizontalStrut_9_1 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_9_1);

		JLabel lblSoDienThoai = new JLabel("Số ĐT");
		lblSoDienThoai.setForeground(Color.BLACK);
		lblSoDienThoai.setFont(null);
		box_2.add(lblSoDienThoai);

		Component horizontalStrut_10_1 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_10_1);

		txtSoDT = new JTextField();
		txtSoDT.setForeground(Color.BLACK);
		txtSoDT.setFont(null);
		txtSoDT.setColumns(10);
		txtSoDT.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),

						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		box_2.add(txtSoDT);

		Component horizontalStrut_11_1 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_11_1);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(null);
		box_2.add(lblEmail);

		Component horizontalStrut_12_1 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_12_1);

		txtEmail = new JTextField();
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setFont(null);
		txtEmail.setColumns(10);
		txtEmail.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),

						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtEmail.setBackground(Color.WHITE);
		box_2.add(txtEmail);

		Component horizontalStrut_13_1 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_13_1);

		JLabel lblSoCCCD = new JLabel("Số CCCD");
		lblSoCCCD.setForeground(Color.BLACK);
		lblSoCCCD.setFont(null);
		box_2.add(lblSoCCCD);

		Component horizontalStrut_14_1 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_14_1);

		txtSoCCCD = new JTextField();
		txtSoCCCD.setForeground(Color.BLACK);
		txtSoCCCD.setFont(null);
		txtSoCCCD.setColumns(10);
		txtSoCCCD.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),

						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		box_2.add(txtSoCCCD);

		Component verticalStrut_1_1 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_1_1);

		Box box_3 = Box.createHorizontalBox();
		panel_1.add(box_3);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setForeground(Color.BLACK);
		lblDiaChi.setFont(null);
		box_3.add(lblDiaChi);

		Component horizontalStrut_15_1 = Box.createHorizontalStrut(30);
		box_3.add(horizontalStrut_15_1);

		txtDiaChi = new JTextField();
		txtDiaChi.setForeground(Color.BLACK);
		txtDiaChi.setFont(null);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),

						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtDiaChi.setBackground(Color.WHITE);
		box_3.add(txtDiaChi);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_1);

		JLabel lblNgayVaoLam = new JLabel("Ngày Vào Làm");
		box_3.add(lblNgayVaoLam);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_2);

		dpNgayVaoLam = new JXDatePicker(new Date());

		dpNgayVaoLam.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dpNgayVaoLam.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dpNgayVaoLam.setLocale(new Locale("vi", "VN"));
		
		dpNgayVaoLam.setEnabled(false);
		dpNgayVaoLam.setEnabled(false);

		box_3.add(dpNgayVaoLam);

		Component horizontalStrut_16_1 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_16_1);

		JLabel lblGhiChu = new JLabel("Ghi chú");
		lblGhiChu.setForeground(Color.BLACK);
		lblGhiChu.setFont(null);
		box_3.add(lblGhiChu);

		Component horizontalStrut_18_1 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_18_1);

		txtGhiChu = new JTextField();
		txtGhiChu.setForeground(Color.BLACK);
		txtGhiChu.setFont(null);
		txtGhiChu.setColumns(10);
		txtGhiChu.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),

						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtGhiChu.setBackground(Color.WHITE);
		box_3.add(txtGhiChu);

		JPanel pnlControl = new JPanel();
		pnlContent.add(pnlControl, BorderLayout.SOUTH);
		pnlControl.setBackground(bgColor);
		pnlControl.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		btnThem = new RoundedButton("Thêm", null, 20, 0, 1.0f);
		btnThem.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(Color.decode("#3B71CA"));
		btnThem.setIcon(new ImageScaler("/image/icon_add_row.png", 24, 24).getScaledImageIcon());
		btnThem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlControl.add(btnThem);
		pnlControl.add(Box.createHorizontalStrut(50));

		btnSua = new RoundedButton("Sửa", null, 20, 0, 1.0f);
		btnSua.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnSua.setForeground(Color.WHITE);
		btnSua.setBackground(Color.decode("#ffc107"));
		btnSua.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnSua.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlControl.add(btnSua);
		pnlControl.add(Box.createHorizontalStrut(50));

		btnXoa = new RoundedButton("Xóa", null, 20, 0, 1.0f);
		btnXoa.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(Color.decode("#dc3545"));
		btnXoa.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlControl.add(btnXoa);
		
		pnlControl.add(Box.createHorizontalStrut(200));

		btnLuu = new RoundedButton("Lưu", null, 20, 0, 0.6f);
		btnLuu.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setBackground(Color.decode("#28a745"));
		btnLuu.setIcon(new ImageScaler("/image/save_Icon.png", 24, 24).getScaledImageIcon());
		btnLuu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlControl.add(btnLuu);
		pnlControl.add(Box.createHorizontalStrut(50));

		btnHuy = new RoundedButton("Hủy", null, 20, 0, 0.6f);
		btnHuy.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBackground(Color.decode("#ffc107"));
		btnHuy.setIcon(new ImageScaler("/image/cancelHD_Icon.png", 24, 24).getScaledImageIcon());
		btnHuy.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlControl.add(btnHuy);

		JPanel pnlTable = new JPanel();
		pnlTable.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch to\u00E0n b\u1ED9 c\u00F4ng nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 5)));
		pnlTable.setBackground(new Color(255, 255, 255));
		add(pnlTable, BorderLayout.CENTER);

		String cols[] = { "STT", "Mã CN", "Họ Tên", "Giới tính", "Ngày sinh", "SDT", "Email", "Địa chỉ", "CCCD",
				"Ngày vào làm", "Ghi chú" };
		dtblModel = new DefaultTableModel(cols, 0);
		tblCN = new JTable(dtblModel);

		tbhCN = new JTableHeader(tblCN.getColumnModel());
		tbhCN.setReorderingAllowed(false);
		tbhCN.setBackground(componentColor);
		tbhCN.setForeground(Color.WHITE);
		tbhCN.setFont(fontText);
		tblCN.setTableHeader(tbhCN);

		tblCN.setRowHeight(25);
		tblCN.getColumnModel().getColumn(0).setPreferredWidth(10);
		tblCN.getColumnModel().getColumn(1).setPreferredWidth(70);
		tblCN.getColumnModel().getColumn(2).setPreferredWidth(90);
		tblCN.getColumnModel().getColumn(3).setPreferredWidth(30);
		tblCN.getColumnModel().getColumn(4).setPreferredWidth(60);
		tblCN.getColumnModel().getColumn(5).setPreferredWidth(50);
		tblCN.getColumnModel().getColumn(6).setPreferredWidth(50);
		tblCN.getColumnModel().getColumn(7).setPreferredWidth(70);
		tblCN.getColumnModel().getColumn(8).setPreferredWidth(50);
		tblCN.getColumnModel().getColumn(9).setPreferredWidth(90);
		tblCN.getColumnModel().getColumn(10).setPreferredWidth(90);
//		tblCN.getColumnModel().getColumn(11).setPreferredWidth(90);
		pnlTable.setLayout(new BorderLayout(0, 0));

		// Tạo jscrollpane để tạo scroll cho bảng công nhân
		JScrollPane scrHD = new JScrollPane(tblCN, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlTable.add(scrHD);

		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		cmbGioiTinh.addActionListener(this);

		btnEditAvatar.addActionListener(this);

		btnThem.addMouseListener(this);
		btnSua.addMouseListener(this);
		btnXoa.addMouseListener(this);
		btnLuu.addMouseListener(this);
		btnHuy.addMouseListener(this);
		tblCN.addMouseListener(this);

		displayButtonSaveAndCancel(false);

		setEditableForTextField(false);

		 xoaRong();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == tblCN) {
			int index = tblCN.getSelectedRow();
			if (index != -1) {
				displayButtonSaveAndCancel(false);
				setEditableForTextField(false);
				hienThiThongTinCongNhan(index);
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
		main.music.playSE(2);
		if (o == btnEditAvatar) {
			String namePathAvatar = getPathNameAvatar();
			lblAvatar.setIcon(new ImageScaler("/image/" + namePathAvatar, 150, 150).getScaledImageIcon());
			btnEditAvatar.setText(namePathAvatar);
		}

		if (o == cmbGioiTinh) {
			String selectGT = (String) cmbGioiTinh.getSelectedItem();
			if (selectGT.equals("Nam")) {
				gioiTinhCheck = true;
			} else if (selectGT.equals("Nữ")) {
				gioiTinhCheck = false;
			}
		}

		if (o == btnThem) {
			displayButtonSaveAndCancel(true);
			setEditableForTextField(true);
			xoaRong();
			dpNgayVaoLam.setDate(new Date());
			isThemCongNhan = true;

		}
		if (o == btnSua) {

			isThemCongNhan = false;
			if (tblCN.getSelectedRow() != -1) {
				displayButtonSaveAndCancel(true);
				setEditableForTextField(true);
			} else {
				alertNotification("Cần chọn 1 Công nhân để sửa");
			}

		}
		if (o == btnXoa) {
			if (tblCN.getSelectedRow() != -1) {
				xoaCongNhan();
			} else {
				alertNotification("Chưa chọn Công nhân cần xóa");
			}

		}
		if (o == btnLuu) {
			if (isThemCongNhan == true) {
				themCongNhan();
			} else {
				suaCongNhan();
			}
		}
		if (o == btnHuy) {
			displayButtonSaveAndCancel(false);
			setEditableForTextField(false);
		}
	}
	//HÀM DISPLAY BUTTON SAVE VÀ CANCEL
	private void displayButtonSaveAndCancel(boolean display) {
		if (display == true) {
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

		} else {
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
	// HÀM SET EDIT CÁC JTEXT
	private void setEditableForTextField(boolean edit) {
		if (edit == true) {
			txtMaCN.setEditable(false);
			txtHoTen.setEditable(true);
			txtMatKhau.setEditable(true);

			dpNgaySinh.setEditable(true);
			dpNgaySinh.setEnabled(true);

			txtDiaChi.setEditable(true);
			txtEmail.setEditable(true);

			txtGhiChu.setEditable(true);
			cmbGioiTinh.setEnabled(edit);

			btnEditAvatar.setEnabled(true);

		} else {
			txtMaCN.setEditable(false);
			txtHoTen.setEditable(false);
			txtMatKhau.setEditable(false);

			dpNgaySinh.setEditable(false);
			dpNgaySinh.setEnabled(false);

			txtDiaChi.setEditable(false);
			txtEmail.setEditable(false);


			txtGhiChu.setEditable(false);

			cmbGioiTinh.setEnabled(false);

			btnEditAvatar.setEnabled(false);
		}
	}

	
	// HÀM XÓA RỖNG CÁC TRƯỜNG
	private void xoaRong() {
		dsCN = cn_dao.getAllCongNhan();
		themAllCongNhanVaoBang(dsCN);
		txtMaCN.setText(new SinhMaTuDong().sinhMaCN());
		txtHoTen.setText("");
		txtMatKhau.setText("");
		dpNgaySinh.setDate(new Date(100,0,1));
		txtDiaChi.setText("");
		txtSoDT.setText("");
		txtSoCCCD.setText("");
		txtEmail.setText("");
		txtGhiChu.setText("");
		lblAvatar.setIcon(new ImageScaler("/image/" + "image_cn_df.jpg", 150, 150).getScaledImageIcon());
		btnEditAvatar.setText("Ảnh đại diện");
	}

	// HÀM ĐỂ GET TÊN ẢNH ĐÃ CHỌN
	private String getPathNameAvatar() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png", "gif"));

		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			java.io.File selectedFile = fileChooser.getSelectedFile();
			String filePathAvatar = selectedFile.getName();
			pathNameAvatar = filePathAvatar;
			repaint();
			revalidate();
		}
		return pathNameAvatar;
	}

	// HÀM CHUYỂN ĐỔI DATA CÔNG NHÂN
	private CongNhan convertDataToCongNhan() {
		String maCN = txtMaCN.getText();
		String matKhau = txtMatKhau.getText();
		String hoten = txtHoTen.getText();
		Date ngaySinh = dpNgaySinh.getDate();
		Boolean gioiTinh = gioiTinhCheck;
		String sdt = txtSoDT.getText();
		String email = txtEmail.getText();
		String diaChi = txtDiaChi.getText();
		String scccd = txtSoCCCD.getText();
		Date ngayVaoLam = dpNgayVaoLam.getDate();
		String anhDaiDien = pathNameAvatar;
		String ghiChu = txtGhiChu.getText();

		return new CongNhan(maCN, matKhau, hoten, ngaySinh, gioiTinh, sdt, email, diaChi, scccd, ngayVaoLam, anhDaiDien,
				ghiChu);
	}

	// THEM CONG NHAN
	private void themCongNhan() {
		if (validCongNhan() == true) {
			CongNhan cnNew = convertDataToCongNhan();
			if (cnNew != null) {
				if (cn_dao.themCongNhan(cnNew)) {
					themCongNhanVaoBang(cnNew);
					xoaRong();
					alertSuccess("Thêm công nhân thành công");
				} else {
					alertNotification("Thêm công nhân thất bại, do mã CN đã tồn tại");
				}
			} else {
				alertNotification("Thêm thất bại! Lỗi");
			}
		}
	}

	// HÀM GET TẤT CẢ DATA VÀ THÊM VÀO BẢNG
	private void getDataToTable() {
		dsCN = cn_dao.getAllCongNhan();
		themAllCongNhanVaoBang(dsCN);
	}

	// HÀM THÊM CÔNG NHÂN VÀO BẢNG
	private void themCongNhanVaoBang(CongNhan cn) {
		String[] row = new String[14];
		row[0] = String.valueOf(dtblModel.getRowCount() + 1); //STT
		row[1] = cn.getMaCN();
		row[2] = cn.getHoTen();
		row[3] = cn.getGioiTinh() ? "Nam" : "Nữ"; //true:nam, false: nu
		row[4] = new SimpleDateFormat("dd-MM-YYYY").format(cn.getNgaySinh());
		row[5] = cn.getSDT();
		row[6] = cn.getEmail();
		row[7] = cn.getDiaChi();
		row[8] = cn.getSoCCCD();
		row[9] = new SimpleDateFormat("dd-MM-YYYY").format(cn.getNgayVaoLam());
		row[10] = cn.getGhiChu();
		dtblModel.addRow(row);

	}

	// HIỆN THỊ THÔNG TIN CÔNG NHÂN
	private void hienThiThongTinCongNhan(int index) {
		txtMaCN.setText(dsCN.get(index).getMaCN());
		txtHoTen.setText(dsCN.get(index).getHoTen());
		txtSoDT.setText(dsCN.get(index).getSDT());
		txtEmail.setText(dsCN.get(index).getEmail());
		txtSoCCCD.setText(dsCN.get(index).getSoCCCD());
		txtDiaChi.setText(dsCN.get(index).getDiaChi());
		txtGhiChu.setText(dsCN.get(index).getGhiChu());
		cmbGioiTinh.setSelectedIndex(dsCN.get(index).getGioiTinh() ? 0 : 1);
		txtMatKhau.setText(dsCN.get(index).getMatKhau());

//		btnEditAvatar.setText(dsCN.get(index).getAnhDaiDien());
//		System.out.println(dsCN.get(index).getAnhDaiDien());
		
		
		if (dsCN.get(index).getAnhDaiDien() == null) {
			lblAvatar.setIcon(new ImageScaler("image_cn_df.jpg", 150, 150).getScaledImageIcon());
		} else {
			lblAvatar.setIcon(
					new ImageScaler("/image/" + dsCN.get(index).getAnhDaiDien(), 150, 150).getScaledImageIcon());
		}

		String dateString = (String) dtblModel.getValueAt(index, 4);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			java.util.Date date = formatter.parse(dateString);
			dpNgaySinh.setDate(date);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		dateString = (String) dtblModel.getValueAt(index, 9);
		try {
			java.util.Date date = formatter.parse(dateString);
			dpNgayVaoLam.setDate(date);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// HÀM RÀNG BUỘC CÁC TRƯỜNG CÔNG NHÂN
	private boolean validCongNhan() {
		String maCN = txtMaCN.getText();
		String tenCN = txtHoTen.getText();
		String mk = txtMatKhau.getText();
		Date ngaySinh = dpNgaySinh.getDate();
		String sdt = txtSoDT.getText();
		String email = txtEmail.getText();
		String scccd = txtSoCCCD.getText();
		Date ngayVaoLam = dpNgayVaoLam.getDate();

//		if (pathNameAvatar.equals("image_cn_df.jpg")) {
//			alertNotification("Ảnh đại diện là bắt buộc ");
//			return false;
//		}

		if (maCN == null || maCN.trim().length() <= 0) {
			alertNotification("Trường nhập Mã Công Nhân là bắt buộc");
			return false;
		} else if (!Pattern.matches("CN\\d{5}", maCN)) {
			alertNotification("Trường nhập Mã Công Nhân không đúng định dạng (CNXXXXX, X:0-1)");
			return false;
		}

		if (tenCN == null || tenCN.trim().length() <= 0) {
			alertNotification("Trường nhập Tên Công Nhân là bắt buộc");
			return false;
		}else if(Pattern.matches(".*\\d.*", tenCN)){
			alertNotification("Trường nhập Tên Công Nhân không phép chứa số");
			return false;
		}

		if (mk == null || mk.trim().length() <= 0) {
			alertNotification("Trường nhập Mật khẩu không được để trống");
			return false;
		} else if (mk.length() < 6) {
			alertNotification("Trường nhập Mật khẩu phải lớn hơn 6 kí tự");
			return false;
		}

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -18);
		java.util.Date eighteenYearsAgo = cal.getTime();
		if (!ngaySinh.before(eighteenYearsAgo)) {
			alertNotification("Trường chọn Ngày trước sinh phải là ngày hiện tại và trên 18 tuổi");
			return false;
		}

		if (sdt == null || sdt.trim().length() <= 0) {
			alertNotification("Trường nhập Số điện thoại không được để trống");
			return false;
		}else if (!Pattern.matches("^(\\+84|0)[1-9]\\d{8}$", sdt)) {
			alertNotification("Trường nhập Số điện thoại không đúng định dạng (bắt đầu bằng +84/0 + 9 số)");
			return false;
		}

		if (email == null || email.trim().length() <= 0) {
			alertNotification("Trường nhập Email không được để trống");
			return false;
		}else if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
			alertNotification("Trường nhập Email không đúng định dạng");
			return false;
		}

		if (scccd == null || scccd.trim().length() <= 0) {
			alertNotification("Trường nhập Số căn cước không được để trống");
			return false;
		}else if (!Pattern.matches("^\\d{12}$", scccd)) {
			alertNotification("Trường nhập Số căn cước không đúng định dạng (12 số)");
			return false;
		}
		
//		Date ngayHienTai = new Date();
//		if (ngayVaoLam.before(ngayHienTai)||ngayVaoLam.equals(ngayHienTai)) {
//			alertNotification("Ngày vào làm phải bằng hoặc sau ngày hiện tại");
//			return false;
//		}

		return true;
	}

	// SUA CONG NHAN
	private void suaCongNhan() {
		if (validCongNhan() == true) {
			CongNhan cnNew = convertDataToCongNhan();
			if (cnNew != null) {
				if (cn_dao.suaCongNhan(cnNew)) {
					xoaRong();
					displayButtonSaveAndCancel(false);
					setEditableForTextField(false);
					alertSuccess("Sửa thành công");
					dtblModel.fireTableDataChanged();
				} else {
					alertNotification("Sửa thất bại! Công nhân không tồn tại");
				}
			}
		}
	}

	// XOA CONG NHAN
	public void xoaCongNhan() {
		String maHD = txtMaCN.getText();
		if (maHD != null) {
			String message = String.format("Quyết định xóa công nhân có mã %s", maHD);
			main.music.playSE(3);
			int result = JOptionPane.showConfirmDialog(this, message, "NOTIFICATION", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				if (cn_dao.xoaCongNhan(maHD)) {
					xoaRong();
					alertSuccess("Xóa thành công");
					dtblModel.fireTableDataChanged();
				} else {
					alertNotification("Xóa không thành công! Không tìm thấy công nhân cần xóa");
				}
			}
		} else {
			alertNotification("Đã có lỗi xảy ra");
		}
	}

	// HÀM THÊM TẤT CẢ CÔNG NHÂN VÀO BẢNG
	private void themAllCongNhanVaoBang(ArrayList<CongNhan> list) {
		dtblModel.setRowCount(0);
		for (CongNhan cn : list) {
			themCongNhanVaoBang(cn);
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
