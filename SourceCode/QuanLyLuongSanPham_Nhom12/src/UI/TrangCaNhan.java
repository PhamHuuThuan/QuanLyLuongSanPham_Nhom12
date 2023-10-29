package UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;

import java.awt.Panel;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class TrangCaNhan extends JPanel implements ActionListener {
	private MainUI main;
	private Color componentColor = Color.decode("#424242");
	private Font fontText;
	private JFrame frameEditTrangCaNhan;
	private JTextField txtName;
	private JTextField txtMa;
	private JComboBox<String> cmbGioiTinh;
	private JTextField jdcNgaySinh;
	private JTextField txtSoDt;
	private JTextField txtEmail;
	private JTextField txtSoCCCD;
	private JTextField txtNgayVaoLam;
	private RoundedButton btnLogOut, btnEditInfo, btnSave, btnCannelEdit;

	public TrangCaNhan(MainUI main) {
		this.main = main;
		fontText = main.roboto_regular.deriveFont(Font.PLAIN, 16F);

		setLayout(new BorderLayout(0, 0));

		JPanel pnlTitile = new JPanel();
		pnlTitile.setBorder(new EmptyBorder(5, 0, 10, 0));
		pnlTitile.setBackground(new Color(255, 255, 255));
		add(pnlTitile, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("TRANG CÁ NHÂN");
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitile.add(lblTitle);

		JPanel pnlBodyTrangCaNhan = new JPanel();
		pnlBodyTrangCaNhan.setBackground(new Color(255, 255, 255));
		pnlBodyTrangCaNhan.setBorder(new EmptyBorder(0, 20, 0, 20));
		add(pnlBodyTrangCaNhan);
		pnlBodyTrangCaNhan.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel pnlIfnormation = new JPanel();
		pnlIfnormation.setBackground(new Color(255, 255, 255));
		pnlBodyTrangCaNhan.add(pnlIfnormation);

		TitledBorder titleBorder = BorderFactory
				.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Thông tin cá nhân");
		pnlIfnormation.setBorder(
				BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 19F));
		pnlIfnormation.setLayout(new BoxLayout(pnlIfnormation, BoxLayout.X_AXIS));

		JPanel pnlBox_left = new JPanel();
		pnlBox_left.setBackground(new Color(255, 255, 255));
		pnlIfnormation.add(pnlBox_left);
		pnlBox_left.setLayout(new GridLayout(10, 1, 0, 0));

		JLabel lblName = new JLabel("Họ Tên");
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblName);

		JLabel lblMa = new JLabel("Mã CN");
		lblMa.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblMa);

		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblGioiTinh);

		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		lblNgaySinh.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblNgaySinh);

		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblSoDienThoai);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblEmail);

		JLabel lblSoCCCD = new JLabel("SỐ CCCD");
		lblSoCCCD.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblSoCCCD);

		JLabel lblNgayVaoLam = new JLabel("Ngày vào làm");
		lblNgayVaoLam.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnlBox_left.add(lblNgayVaoLam);

		JPanel pngBox_right = new JPanel();
		pngBox_right.setBackground(new Color(255, 255, 255));
		pnlIfnormation.add(pngBox_right);
		pngBox_right.setLayout(new GridLayout(10, 2, 0, 0));

		txtName = new JTextField("Nguyễn Văn Phong");
		pngBox_right.add(txtName);
		txtName.setColumns(10);

		txtMa = new JTextField("CN00004");
		pngBox_right.add(txtMa);
		txtMa.setColumns(10);

		cmbGioiTinh = new JComboBox<>();
		cmbGioiTinh.addItem("nam");
		cmbGioiTinh.addItem("nữ");
		pngBox_right.add(cmbGioiTinh);

		jdcNgaySinh = new JTextField("12/02/2000");
		pngBox_right.add(jdcNgaySinh);

		txtSoDt = new JTextField("0992888832");
		pngBox_right.add(txtSoDt);
		txtSoDt.setColumns(10);

		txtEmail = new JTextField("abcd_admin@gmail.com");
		pngBox_right.add(txtEmail);
		txtEmail.setColumns(10);

		txtSoCCCD = new JTextField("123981230982");
		pngBox_right.add(txtSoCCCD);
		txtSoCCCD.setColumns(10);

		txtNgayVaoLam = new JTextField("12/09/2018");
		pngBox_right.add(txtNgayVaoLam);
		txtNgayVaoLam.setColumns(10);

		JPanel pnlControl = new JPanel();
		pnlBodyTrangCaNhan.add(pnlControl);
		pnlControl.setLayout(new BorderLayout(0, 0));

		JPanel pnlBoxInfor = new JPanel();
		pnlBoxInfor.setBackground(new Color(255, 255, 255));
		pnlBoxInfor.setBorder(new EmptyBorder(10, 30, 20, 30));
		pnlControl.add(pnlBoxInfor);
		pnlBoxInfor.setLayout(new BorderLayout(0, 10));

		JLabel lblAvatar = new JLabel("");
		lblAvatar.setIcon(new ImageScaler("/image/image_account.jpg", 340, 340).getScaledImageIcon());
		lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
		pnlBoxInfor.add(lblAvatar);

		Panel panel_1 = new Panel();
		pnlBoxInfor.add(panel_1, BorderLayout.SOUTH);

		btnEditInfo = new RoundedButton("Cập nhật thông tin cá nhân", null, 15, 0, 2f);
		btnEditInfo.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnEditInfo.setForeground(Color.decode("#FFFFFF"));
		btnEditInfo.setBackground(Color.decode("#424242"));
		btnEditInfo.setIcon(new ImageScaler("/image/icon_edit.png", 25, 25).getScaledImageIcon());
		btnEditInfo.setVisible(true);
		panel_1.add(btnEditInfo);

		btnSave = new RoundedButton("Lưu", null, 15, 0, 2f);
		btnSave.setVisible(false);
		panel_1.add(btnSave);

		btnCannelEdit = new RoundedButton("Hủy", null, 15, 0, 2f);
		btnCannelEdit.setVisible(false);
		panel_1.add(btnCannelEdit);
		
		EditTrangCaNhan(false);

		Panel pnlHandle = new Panel();
		pnlHandle.setBackground(new Color(255, 255, 255));
		pnlControl.add(pnlHandle, BorderLayout.SOUTH);
		FlowLayout fl_pnlHandle = new FlowLayout(FlowLayout.CENTER, 90, 10);
		fl_pnlHandle.setAlignOnBaseline(true);
		pnlHandle.setLayout(fl_pnlHandle);

		JLabel lblVersion = new JLabel("Version 2.1.0");
		lblVersion.setFont(main.roboto_regular.deriveFont(Font.ITALIC, 17F));
		pnlHandle.add(lblVersion);

		btnLogOut = new RoundedButton("ĐĂNG XUẤT", null, 15, 0, 2f);
		btnLogOut.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setBackground(new Color(186, 81, 69));
		btnLogOut.setIcon(new ImageScaler("/image/icon_logout.png", 30, 30).getScaledImageIcon());

		pnlHandle.add(btnLogOut);

		btnLogOut.addActionListener(this);
		btnEditInfo.addActionListener(this);
		btnCannelEdit.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		main.music.playSE(2);
		if (o == btnLogOut) {
			int result = JOptionPane.showConfirmDialog(main, "Bạn có muốn đăng xuất khỏi hệ thống?",
					"ĐĂNG XUẤT HỆ THỐNG", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				main.setVisible(false);
				Login_UI loginUi = new Login_UI(main);
				loginUi.setVisible(true);
			} else if (result == JOptionPane.NO_OPTION) {

			}
		}
		if (o == btnEditInfo) {
			btnEditInfo.setVisible(false);
			btnSave.setVisible(true);
			btnCannelEdit.setVisible(true);
			EditTrangCaNhan(true);
		}
		if (o == btnCannelEdit) {
			btnEditInfo.setVisible(true);
			btnSave.setVisible(false);
			btnCannelEdit.setVisible(false);
			EditTrangCaNhan(false);
		}

	}
	public void EditTrangCaNhan(boolean editer) {
		if (editer == true) {
			txtName.setEditable(true);
			txtMa.setEditable(true);
			cmbGioiTinh.setEnabled(true);
			jdcNgaySinh.setEditable(true);
			txtSoDt.setEditable(true);
			txtEmail.setEditable(true);
			txtSoCCCD.setEditable(true);
			txtNgayVaoLam.setEditable(true);
		} else {
			txtName.setEditable(false);
			txtMa.setEditable(false);
			cmbGioiTinh.setEnabled(false);
			jdcNgaySinh.setEditable(false);
			txtSoDt.setEditable(false);
			txtEmail.setEditable(false);
			txtSoCCCD.setEditable(false);
			txtNgayVaoLam.setEditable(false);
		}
	}

}
