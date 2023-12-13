package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.border.MatteBorder;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Dao.NhanVien_Dao;
import Dao.PhanCongNhanVien_Dao;
import Entity.BangPhanCongNhanVien;
import Entity.NhanVien;
import Util.ConfigManager;
import Util.LuuTru;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class Login_UI extends JFrame implements ItemListener, MouseListener, ActionListener {

	private static MainUI main;
	private JPanel contentPane;
	private JPanel pnlRight;
	private JPanel pnlInRight;
	private JLabel lblTextHeadingLogin;
	private JPanel pnlInputUser;
	private JLabel lblTextHeadingUser;
	private JPanel pnlInputPassword;
	private JLabel lblTextHeadingPassword;
	private RoundedButton btnTogglePassword;
	private JComboBox<String> cmbLanguages;
	private JCheckBox chkRememberUser;
	private RoundedButton btnLogin;
	public LuuTru l = new LuuTru();
	public ConfigManager config = new ConfigManager("/config/config.properties");
	public ResourceBundle read_file_languages = l.getLanguageConfig(config.getLanguage());

	private boolean isLoading = false;
	private boolean isPasswordVisible = false;

	public BangPhanCongNhanVien pcnv;
	private ArrayList<NhanVien> dsnv = new ArrayList<>();
	private NhanVien_Dao nv_dao = new NhanVien_Dao();
	private JTextField txtInputUser;
	private JPanel pnlInPass;
	private JPasswordField txtInputPassword;
	
	// HÀM ĐÓNG PAGE
	private void ClosePage() {
		this.dispose();
	}

	public Login_UI(MainUI main) {
		this.main = main;
		ImageIcon appIcon = new ImageIcon("assets/icon_logo.png");
		setIconImage(appIcon.getImage());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1111, 456);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon gifAnimation = new ImageIcon("assets/logo_intro.png");
		JLabel widthtLogo = new JLabel(gifAnimation);
		widthtLogo.setBounds(0, 0, 570, 456);

		JPanel panel_logo = new JPanel();
		panel_logo.setBounds(0, 0, 571, 456);
		panel_logo.setLayout(null);
		panel_logo.add(widthtLogo);
		contentPane.add(panel_logo);

		pnlRight = new JPanel();
		pnlRight.setBackground(Color.decode("#424242"));
		pnlRight.setBounds(571, 0, 540, 456);
		contentPane.add(pnlRight);
		pnlRight.setLayout(null);

		JButton btnClosePage = new JButton("×");
		btnClosePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClosePage();
			}
		});
		btnClosePage.setBackground(Color.decode("#424242"));
		btnClosePage.setForeground(Color.decode("#ffffff"));
		btnClosePage.setFont(new Font("Tahoma", Font.BOLD, 35));
		btnClosePage.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 255, 255)));
		btnClosePage.setBounds(493, 0, 45, 45);
		btnClosePage.setMargin(new Insets(3, 3, 3, 3));
		pnlRight.add(btnClosePage);

		pnlInRight = new JPanel();
		pnlInRight.setForeground(new Color(255, 255, 255));
		pnlInRight.setBackground(Color.decode("#424242"));
		pnlInRight.setBounds(29, 31, 488, 414);
		pnlRight.add(pnlInRight);
		pnlInRight.setLayout(null);

		lblTextHeadingLogin = new JLabel("ĐĂNG NHẬP");
		lblTextHeadingLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextHeadingLogin.setForeground(Color.WHITE);
		lblTextHeadingLogin.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTextHeadingLogin.setBounds(163, 0, 182, 31);
		pnlInRight.add(lblTextHeadingLogin);

		pnlInputUser = new JPanel();
		pnlInputUser.setBounds(10, 64, 439, 86);
		pnlInputUser.setBackground(Color.decode("#424242"));
		pnlInRight.add(pnlInputUser);
		pnlInputUser.setLayout(null);

		lblTextHeadingUser = new JLabel("TÀI KHOẢN");
		lblTextHeadingUser.setForeground(new Color(255, 255, 255));
		lblTextHeadingUser.setFont(new Font("Segoe UI", Font.BOLD, 19));
		lblTextHeadingUser.setBounds(12, 0, 214, 38);
		pnlInputUser.add(lblTextHeadingUser);

		pnlInputPassword = new JPanel();
		pnlInputPassword.setLayout(null);
		pnlInputPassword.setBackground(new Color(66, 66, 66));
		pnlInputPassword.setBounds(10, 157, 439, 86);
		pnlInRight.add(pnlInputPassword);

		lblTextHeadingPassword = new JLabel("MẬT KHẨU");
		lblTextHeadingPassword.setForeground(Color.WHITE);
		lblTextHeadingPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTextHeadingPassword.setBounds(12, 0, 207, 38);
		pnlInputPassword.add(lblTextHeadingPassword);

		btnLogin = new RoundedButton("ĐĂNG NHẬP", null, 15, 0, 2f);
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnLogin.setIcon(new ImageScaler("/image/icon_login.png", 35, 33).getScaledImageIcon());
		btnLogin.setBounds(20, 282, 429, 35);
		pnlInRight.add(btnLogin);

		chkRememberUser = new JCheckBox("Nhớ tài khoản");
		chkRememberUser.setHorizontalAlignment(SwingConstants.RIGHT);
		chkRememberUser.setSelected(config.getRememberAccount());
		chkRememberUser.setBackground(Color.decode("#424242"));
		chkRememberUser.setForeground(Color.WHITE);
		chkRememberUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chkRememberUser.setBounds(300, 250, 150, 21);
		pnlInRight.add(chkRememberUser);

		cmbLanguages = new JComboBox<>();
		cmbLanguages.addItem("Vietnamese");
		cmbLanguages.addItem("English");
		cmbLanguages.setBounds(20, 353, 150, 31);
		cmbLanguages.setSelectedIndex(config.getLanguage());
		pnlInRight.add(cmbLanguages);

		JComboBox<String> cmbTheme = new JComboBox<>();
		cmbTheme.addItem("Lightmode");
		cmbTheme.addItem("Darkmode");
		cmbTheme.setBounds(334, 353, 115, 31);
		pnlInRight.add(cmbTheme);

		cmbLanguages.addItemListener(this);
		chkRememberUser.addItemListener(this);

		setTextLanguage();

		JPanel pnlInMa = new JPanel();
		pnlInMa.setBackground(new Color(81, 81, 81));
		pnlInMa.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		pnlInMa.setBounds(12, 38, 427, 37);
		pnlInputUser.add(pnlInMa);
		pnlInMa.setLayout(null);

		txtInputUser = new JTextField();
		txtInputUser.setHorizontalAlignment(SwingConstants.LEFT);
		txtInputUser.setForeground(Color.WHITE);
		txtInputUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtInputUser.setColumns(10);
		txtInputUser.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 255, 255)));
		txtInputUser.setCaretColor(Color.WHITE);
		txtInputUser.setBackground(new Color(81, 81, 81));
		txtInputUser.setBounds(10, 0, 417, 36);
		txtInputUser.setText(config.getUsername());
		pnlInMa.add(txtInputUser);

		pnlInPass = new JPanel();
		pnlInPass.setBackground(new Color(81, 81, 81));
		pnlInPass.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		pnlInPass.setBounds(12, 37, 427, 34);
		pnlInputPassword.add(pnlInPass);
		pnlInPass.setLayout(null);

		txtInputPassword = new JPasswordField();
		txtInputPassword.setBounds(10, 0, 371, 32);
		txtInputPassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtInputPassword.setForeground(Color.WHITE);
		txtInputPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtInputPassword.setColumns(10);
		txtInputPassword.setCaretColor(Color.WHITE);
		txtInputPassword.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 255, 255)));
		txtInputPassword.setBackground(new Color(81, 81, 81));
		txtInputPassword.setText(config.getPassword());
		pnlInPass.add(txtInputPassword);

		btnTogglePassword = new RoundedButton("", null, 0, 0, 0f);
		btnTogglePassword.setBounds(377, 0, 50, 33);
		pnlInPass.add(btnTogglePassword);
		btnTogglePassword.setBackground(new Color(81, 81, 81));
		btnTogglePassword.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		btnTogglePassword.setIcon(new ImageScaler("/image/icon_show_password.png", 35, 23).getScaledImageIcon());
		
		btnTogglePassword.addActionListener(this);
		btnLogin.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnTogglePassword) {
			if (!isPasswordVisible) {
				isPasswordVisible = true;
				txtInputPassword.setEchoChar((char) 0);
				btnTogglePassword.setIcon(new ImageScaler("/image/icon_hide_password.png", 35, 23).getScaledImageIcon());
			} else {
				isPasswordVisible = false;
				txtInputPassword.setEchoChar('•');
				btnTogglePassword.setIcon(new ImageScaler("/image/icon_show_password.png", 35, 23).getScaledImageIcon());
			}
		};
		if(o==btnLogin) {
			config.setRememberAccount(chkRememberUser.isSelected(), txtInputUser.getText(),
					txtInputPassword.getText());

			if (checkLogin()) {
				new MainUI(pcnv).music.playSE(1);
				new MainUI(pcnv).setVisible(true);
				ClosePage();
			}
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
	// HÀM KIỂM TRA MÃ NV CÓ TỒN TẠI
	private static boolean contrainsMaNV(ArrayList<NhanVien> dsnv, String maNV, String matKhau) {
		for (NhanVien nv : dsnv) {
			if (nv.getMaNV().equals(maNV) && nv.getMatKhau().equals(matKhau)) {
				return true;
			}
		}
		return false;

	}

	// kiểm tra thông tin nhân viên trong csdl
	private boolean checkLogin() {
		String maNV = txtInputUser.getText();
		String matKhau = txtInputPassword.getText();

		pcnv = new PhanCongNhanVien_Dao().kiemTraDangNhap(maNV, matKhau);

		if (Pattern.matches("^NV\\d{5}$", maNV)) {
			dsnv = nv_dao.getAllNhanVien();

			if (!contrainsMaNV(dsnv, maNV, matKhau)) {
				alertNotification("Mã nhân viên hoặc mật khẩu không chính xác");
				return false;
			}

			if (pcnv != null) {
				if (pcnv.getChucVu().equals("Quản lý")) {
					return true;
				} else if (pcnv.getChucVu().equals("Nhân viên")) {
					return true;
				} else if (pcnv.getChucVu().equals("Thực tập sinh")) {
					alertNotification("Tài khoản chưa phân công hoặc không có quyền đăng nhập");
					return false;
				}
			} else {
				alertNotification("Tài khoản không tồn tại");
				return false;
			}
		} else {
			alertNotification("Mã NV không đúng định dạng");
			return false;
		}
		return true;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbLanguages) {
			config.setLanguage(cmbLanguages.getSelectedIndex());
			setTextLanguage();
		}
		if (e.getSource() == chkRememberUser) {
			config.setRememberAccount(chkRememberUser.isSelected(), txtInputUser.getText(), txtInputPassword.getText());
		}
	}

	// HÀM SET CÁC TRƯỜNG NGÔN NGỮ
	public void setTextLanguage() {
		read_file_languages = l.getLanguageConfig(config.getLanguage());
		lblTextHeadingLogin.setText(read_file_languages.getString("text_heading_login"));
		lblTextHeadingUser.setText(read_file_languages.getString("text_heading_user"));
		lblTextHeadingPassword.setText(read_file_languages.getString("text_heading_password"));
		chkRememberUser.setText(read_file_languages.getString("checkbox_remember_user"));
		btnLogin.setText(read_file_languages.getString("text_heading_login"));
	}

	// HÀM ALERT
	public int alertNotification(String textError) {
		String[] options = { "Cancel" };
		int result = JOptionPane.showOptionDialog(main, textError, "NOTIFICATION", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		return result;
	}

}
