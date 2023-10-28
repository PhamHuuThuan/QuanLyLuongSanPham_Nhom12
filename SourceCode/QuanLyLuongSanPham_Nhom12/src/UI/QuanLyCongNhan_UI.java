package UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

public class QuanLyCongNhan_UI extends JPanel implements ActionListener, MouseListener {
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private Font fontText;
	private JTextField txtMaCN;
	private JTextField txtHoTen;
	private JTextField txtMatKhau;
	private JTextField txtNgaySinh;
	private JTextField txtSoDienThoai;
	private JTextField txtEmail;
	private JTextField txtSoCCCD;
	private JTextField txtDiaChi;
	private JTextField txtGhiChu;
	private RoundedButton btnThem, btnSua, btnXoa, btnLuu, btnHuy, btnIn;
	private DefaultTableModel dtblModel;
	private JTableHeader tbhCN;

	public QuanLyCongNhan_UI(MainUI main) {
		this.main = main;
		fontText = main.roboto_regular.deriveFont(Font.PLAIN, 16F);

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
		pnlBody.setLayout(new BoxLayout(pnlBody, BoxLayout.Y_AXIS));
		pnlContent.add(pnlBody, BorderLayout.CENTER);

		Box box_1 = Box.createHorizontalBox();
		box_1.setAlignmentY(0.5f);
		pnlBody.add(box_1);

		JLabel lblMaCN = new JLabel("Mã CN");
		lblMaCN.setForeground(textColor);
		lblMaCN.setFont(fontText);
		box_1.add(lblMaCN);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut);

		txtMaCN = new JTextField("CN0003");
		txtMaCN.setForeground(textColor);
		txtMaCN.setFont(fontText);
		txtMaCN.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaCN.setBackground(bgColor);
		box_1.add(txtMaCN);
		txtMaCN.setColumns(10);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_1);

		JLabel lblHoTen = new JLabel("Họ Tên");
		lblHoTen.setForeground(textColor);
		lblHoTen.setFont(fontText);
		box_1.add(lblHoTen);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_2);

		txtHoTen = new JTextField("Nguyễn Văn Phong");
		txtHoTen.setForeground(textColor);
		txtHoTen.setFont(fontText);
		txtHoTen.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtHoTen.setBackground(bgColor);
		box_1.add(txtHoTen);
		txtHoTen.setColumns(10);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_3);

		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setForeground(textColor);
		lblMatKhau.setFont(fontText);
		box_1.add(lblMatKhau);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_4);

		txtMatKhau = new JTextField();
		txtMatKhau.setForeground(textColor);
		txtMatKhau.setFont(fontText);
		txtMatKhau.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMatKhau.setBackground(bgColor);
		box_1.add(txtMatKhau);
		txtMatKhau.setColumns(10);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_5);

		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setForeground(textColor);
		lblGioiTinh.setFont(fontText);
		box_1.add(lblGioiTinh);

		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_6);

		JRadioButton rdbNam = new JRadioButton("nam");
		rdbNam.setSelected(true);
		rdbNam.setBackground(bgColor);
		rdbNam.setForeground(textColor);
		rdbNam.setFont(fontText);
		box_1.add(rdbNam);

		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_7);

		JRadioButton rdbNu = new JRadioButton("nữ");
		rdbNu.setBackground(bgColor);
		rdbNu.setForeground(textColor);
		box_1.add(rdbNu);

		Component verticalStrut = Box.createVerticalStrut(20);
		pnlBody.add(verticalStrut);

		Box box_2 = Box.createHorizontalBox();
		pnlBody.add(box_2);

		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		lblNgaySinh.setForeground(textColor);
		lblNgaySinh.setFont(fontText);
		box_2.add(lblNgaySinh);

		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_8);

		txtNgaySinh = new JTextField();
		txtNgaySinh.setForeground(textColor);
		txtNgaySinh.setFont(fontText);
		txtNgaySinh.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtNgaySinh.setBackground(bgColor);
		box_2.add(txtNgaySinh);
		txtNgaySinh.setColumns(10);

		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_9);

		JLabel lblSoDienThoai = new JLabel("Số ĐT");
		lblSoDienThoai.setForeground(textColor);
		lblSoDienThoai.setFont(fontText);
		box_2.add(lblSoDienThoai);

		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_10);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setForeground(textColor);
		txtSoDienThoai.setFont(fontText);
		txtSoDienThoai.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaCN.setBackground(bgColor);
		box_2.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);

		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_11);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(textColor);
		lblEmail.setFont(fontText);
		box_2.add(lblEmail);

		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_12);

		txtEmail = new JTextField();
		txtEmail.setForeground(textColor);
		txtEmail.setFont(fontText);
		txtEmail.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtEmail.setBackground(bgColor);
		box_2.add(txtEmail);
		txtEmail.setColumns(10);

		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_13);

		JLabel lblSoCCCD = new JLabel("Số CCCD");
		lblSoCCCD.setForeground(textColor);
		lblSoCCCD.setFont(fontText);
		box_2.add(lblSoCCCD);

		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_14);

		txtSoCCCD = new JTextField();
		txtSoCCCD.setForeground(textColor);
		txtSoCCCD.setFont(fontText);
		txtSoCCCD.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaCN.setBackground(bgColor);
		box_2.add(txtSoCCCD);
		txtSoCCCD.setColumns(10);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnlBody.add(verticalStrut_1);

		Box box_3 = Box.createHorizontalBox();
		pnlBody.add(box_3);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setForeground(textColor);
		lblDiaChi.setFont(fontText);
		box_3.add(lblDiaChi);

		Component horizontalStrut_15 = Box.createHorizontalStrut(30);
		box_3.add(horizontalStrut_15);

		txtDiaChi = new JTextField();
		txtDiaChi.setForeground(textColor);
		txtDiaChi.setFont(fontText);
		txtDiaChi.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtDiaChi.setBackground(bgColor);
		box_3.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		Component horizontalStrut_16 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_16);

		JLabel lblHinhDaiDien = new JLabel("Hình đại diện");
		lblHinhDaiDien.setForeground(textColor);
		lblHinhDaiDien.setFont(fontText);
		box_3.add(lblHinhDaiDien);

		Component horizontalStrut_17 = Box.createHorizontalStrut(40);
		box_3.add(horizontalStrut_17);

		RoundedButton btnChonAnh = new RoundedButton("Image", null, 5, 0, 1f);
		btnChonAnh.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnChonAnh.setForeground(Color.WHITE);
		btnChonAnh.setBackground(componentColor);
		btnChonAnh.setIcon(new ImageScaler("/image/add-image.png", 16, 16).getScaledImageIcon());
		box_3.add(btnChonAnh);

		Component horizontalStrut_19 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_19);

		JLabel lblGhiChu = new JLabel("Ghi chú");
		lblGhiChu.setForeground(textColor);
		lblGhiChu.setFont(fontText);
		box_3.add(lblGhiChu);

		Component horizontalStrut_18 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_18);

		txtGhiChu = new JTextField();
		txtGhiChu.setForeground(textColor);
		txtGhiChu.setFont(fontText);
		txtGhiChu.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtGhiChu.setBackground(bgColor);
		box_3.add(txtGhiChu);
		txtGhiChu.setColumns(10);

		JPanel pnlControl = new JPanel();
		pnlContent.add(pnlControl, BorderLayout.SOUTH);
		pnlControl.setBackground(bgColor);
		pnlControl.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		btnThem = new RoundedButton("Thêm", null, 20, 0, 1.0f);
		btnThem.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(Color.decode("#3B71CA"));
		btnThem.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
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
		pnlControl.add(Box.createHorizontalStrut(50));

		btnIn = new RoundedButton("Xuất", null, 20, 0, 1.0f);
		btnIn.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnIn.setForeground(Color.WHITE);
		btnIn.setBackground(Color.decode("#17a2b8"));
		btnIn.setIcon(new ImageScaler("/image/printer_icon.png", 24, 24).getScaledImageIcon());
		btnIn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlControl.add(btnIn);

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
		pnlTable.setBackground(new Color(255, 255, 255));
		add(pnlTable, BorderLayout.CENTER);

		String cols[] = { "Mã CN", "Họ tên", "Giới tính", "Ngày sinh", "SDT", "Email", "CCCD", "Địa chỉ","Ngày vào làm", "Ghi chú" };
		dtblModel = new DefaultTableModel(cols, 0);
		JTable tblCN = new JTable(dtblModel);

		tbhCN = new JTableHeader(tblCN.getColumnModel());
		tbhCN.setReorderingAllowed(false);
		tbhCN.setBackground(componentColor);
		tbhCN.setForeground(Color.WHITE);
		tbhCN.setFont(fontText);
		tblCN.setTableHeader(tbhCN);

		tblCN.setRowHeight(20);
		tblCN.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblCN.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblCN.getColumnModel().getColumn(2).setPreferredWidth(175);
		tblCN.getColumnModel().getColumn(3).setPreferredWidth(175);
		tblCN.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblCN.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblCN.getColumnModel().getColumn(6).setPreferredWidth(200);
		tblCN.getColumnModel().getColumn(7).setPreferredWidth(100);
		tblCN.getColumnModel().getColumn(8).setPreferredWidth(150);
		pnlTable.setLayout(new BorderLayout(0, 0));

		// Tạo jscrollpane để tạo scroll cho bảng nhân viên
		JScrollPane scrHD = new JScrollPane(tblCN, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlTable.add(scrHD);

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

		displayButtonSaveAndCancel(false);

		setEditableForTextField(false);
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
		if (o == btnThem) {
			displayButtonSaveAndCancel(true);
			setEditableForTextField(true);
			xoaRong();

		}
		if (o == btnSua) {
			displayButtonSaveAndCancel(true);
			setEditableForTextField(true);

		}
		if (o == btnXoa) {

		}
		if (o == btnIn) {
		}
		if (o == btnLuu) {
			displayButtonSaveAndCancel(false);
			setEditableForTextField(false);

		}
		if (o == btnHuy) {
			displayButtonSaveAndCancel(false);
			setEditableForTextField(false);

		}
	}

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
			btnIn.setEnabled(false);
			btnIn.setAlpha(0.6f);

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
			btnIn.setEnabled(true);
			btnIn.setAlpha(1f);
		}
	}

	private void setEditableForTextField(boolean edit) {
		if (edit == true) {
			txtMaCN.setEditable(true);
			txtHoTen.setEditable(true);
			txtMatKhau.setEditable(true);
			txtNgaySinh.setEditable(true);
			txtDiaChi.setEditable(true);
			txtEmail.setEditable(true);
			txtGhiChu.setEditable(true);
		} else {
			txtMaCN.setEditable(false);
			txtHoTen.setEditable(false);
			txtMatKhau.setEditable(false);
			txtNgaySinh.setEditable(false);
			txtDiaChi.setEditable(false);
			txtEmail.setEditable(false);
			txtGhiChu.setEditable(false);
		}
	}

	private void xoaRong() {
		txtMaCN.setText("");
		txtHoTen.setText("");
		txtMatKhau.setText("");
		txtNgaySinh.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtGhiChu.setText("");
	}

}
