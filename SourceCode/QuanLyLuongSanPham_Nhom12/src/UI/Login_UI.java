package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.border.MatteBorder;

import CustomUI.RoundedButton;
import Dao.PhanCongNhanVien_Dao;
import Entity.BangPhanCongNhanVien;
import Util.ConfigManager;
import Util.LuuTru;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class Login_UI extends JFrame implements ItemListener{

	private static MainUI main;
	private JPanel contentPane;
	private JPanel panel_right;
	private JPanel panel_in_right;
	private JLabel text_heading_login;
	private JPanel panel_input_user;
	private JLabel text_heading_user; 
	private JTextField input_user;
	private JPanel panel_input_password;
	private JLabel text_heading_password;
	private JPasswordField input_password;
	private JComboBox<String> combox_languages;
	private JCheckBox checkbox_remember_user;
	private RoundedButton button_login;
	public LuuTru l = new LuuTru();
	public ConfigManager config = new ConfigManager("/config/config.properties");
	public ResourceBundle read_file_languages = l.getLanguageConfig(config.getLanguage());

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_UI frame = new Login_UI(main);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void ClosePage() {
//		this.dispose();
		System.exit(0);
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

		panel_right = new JPanel();
		panel_right.setBackground(Color.decode("#424242"));
		panel_right.setBounds(571, 0, 540, 456);
		contentPane.add(panel_right);
		panel_right.setLayout(null);

		JButton button_close_page = new JButton("×");
		button_close_page.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClosePage();
			}
		});
		button_close_page.setBackground(Color.decode("#424242"));
		button_close_page.setForeground(Color.decode("#ffffff"));
		button_close_page.setFont(new Font("Tahoma", Font.BOLD, 35));
		button_close_page.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 255, 255)));
		button_close_page.setBounds(493, 0, 45, 45);
		button_close_page.setMargin(new Insets(3, 3, 3, 3));
		panel_right.add(button_close_page);

		panel_in_right = new JPanel();
		panel_in_right.setForeground(new Color(255, 255, 255));
		panel_in_right.setBackground(Color.decode("#424242"));
		panel_in_right.setBounds(29, 31, 488, 414);
		panel_right.add(panel_in_right);
		panel_in_right.setLayout(null);

		text_heading_login = new JLabel("ĐĂNG NHẬP");
		text_heading_login.setHorizontalAlignment(SwingConstants.CENTER);
		text_heading_login.setForeground(Color.WHITE);
		text_heading_login.setFont(new Font("Segoe UI", Font.BOLD, 22));
		text_heading_login.setBounds(163, 0, 182, 31);
		panel_in_right.add(text_heading_login);

		panel_input_user = new JPanel();
		panel_input_user.setBounds(10, 64, 439, 86);
		panel_input_user.setBackground(Color.decode("#424242"));
		panel_in_right.add(panel_input_user);
		panel_input_user.setLayout(null);

		text_heading_user = new JLabel("TÀI KHOẢN");
		text_heading_user.setForeground(new Color(255, 255, 255));
		text_heading_user.setFont(new Font("Segoe UI", Font.BOLD, 19));
		text_heading_user.setBounds(12, 0, 214, 38);
		panel_input_user.add(text_heading_user);

		input_user = new JTextField();
		input_user.requestFocusInWindow();
		input_user.setHorizontalAlignment(SwingConstants.LEFT);
		input_user.setForeground(new Color(255, 255, 255));
		input_user.setBackground(Color.decode("#515151"));
		input_user.setCaretColor(Color.WHITE);
		input_user.setBounds(12, 38, 427, 35);
		input_user.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		panel_input_user.add(input_user);
		input_user.setFont(new Font("Tahoma", Font.PLAIN, 18));
		input_user.setColumns(10);
		input_user.setText(config.getUsername());

		panel_input_password = new JPanel();
		panel_input_password.setLayout(null);
		panel_input_password.setBackground(new Color(66, 66, 66));
		panel_input_password.setBounds(10, 157, 439, 86);
		panel_in_right.add(panel_input_password);

		text_heading_password = new JLabel("MẬT KHẨU");
		text_heading_password.setForeground(Color.WHITE);
		text_heading_password.setFont(new Font("Tahoma", Font.BOLD, 18));
		text_heading_password.setBounds(12, 0, 207, 38);
		panel_input_password.add(text_heading_password);

		input_password = new JPasswordField();
		input_password.setCaretColor(Color.WHITE);
		input_password.setHorizontalAlignment(SwingConstants.LEFT);
		input_password.setForeground(Color.WHITE);
		input_password.setFont(new Font("Tahoma", Font.PLAIN, 18));
		input_password.setColumns(10);
		input_password.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		input_password.setBackground(new Color(81, 81, 81));
		input_password.setBounds(12, 38, 424, 35);
		panel_input_password.add(input_password);
		input_password.setText(config.getPassword());

		button_login = new RoundedButton("ĐĂNG NHẬP", null, 15, 0, 2f);
		button_login.setFont(new Font("Segoe UI", Font.BOLD, 18));
		button_login.setBounds(20, 282, 429, 35);
		panel_in_right.add(button_login);

		checkbox_remember_user = new JCheckBox("Nhớ tài khoản");
		checkbox_remember_user.setSelected(config.getRememberAccount());
		checkbox_remember_user.setBackground(Color.decode("#424242"));
		checkbox_remember_user.setForeground(Color.WHITE);
		checkbox_remember_user.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkbox_remember_user.setBounds(300, 250, 150, 21);
		panel_in_right.add(checkbox_remember_user);

		combox_languages = new JComboBox<>();
		combox_languages.addItem("Vietnamese");
		combox_languages.addItem("English");
		combox_languages.setBounds(20, 353, 150, 31);
		combox_languages.setSelectedIndex(config.getLanguage());
		panel_in_right.add(combox_languages);
		

		JComboBox<String> combox_theme = new JComboBox<>();
		combox_theme.addItem("Lightmode");
		combox_theme.addItem("Darkmode");
		combox_theme.setBounds(334, 353, 115, 31);
		panel_in_right.add(combox_theme);

		button_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				config.setRememberAccount(checkbox_remember_user.isSelected(), input_user.getText(), input_password.getText());
//				try {
//					FileWriter writer = new FileWriter("src/config/languages/selectedLanguage.txt");
//					writer.write(pathFileLanguage);
//					writer.close();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				};
//				try {
//					FileWriter writer = new FileWriter("src/config/themes/selectedTheme.txt");
//					writer.write(pathFileTheme);
//					writer.close();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
				//kiểm tra đăng nhập
				checkLogin();
			}
		});
		
		combox_languages.addItemListener(this);
		checkbox_remember_user.addItemListener(this);
		
		setTextLanguage();

	}
	//kiểm tra thông tin nhân viên trong csdl
	private void checkLogin() {
		String maNV = input_user.getText();
		String matKhau = input_password.getText();
		BangPhanCongNhanVien pcnv = new PhanCongNhanVien_Dao().kiemTraDangNhap(maNV, matKhau);
		if(pcnv!=null) {
			if(pcnv.getChucVu().equals("Quản lý")) {
				this.dispose();
				new MainUI(pcnv).setVisible(true);
			}else if(pcnv.getChucVu().equals("Nhân viên")) {
				this.dispose();
				new MainUI(pcnv).setVisible(true);
			}else {
				//thực tập sinh
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == combox_languages) {
			config.setLanguage(combox_languages.getSelectedIndex());
			setTextLanguage();
		}
		if(e.getSource() == checkbox_remember_user) {
			config.setRememberAccount(checkbox_remember_user.isSelected(), input_user.getText(), input_password.getText());
		}
	}
	public void setTextLanguage() {
		read_file_languages = l.getLanguageConfig(config.getLanguage());
		text_heading_login.setText(read_file_languages.getString("text_heading_login"));
		text_heading_user.setText(read_file_languages.getString("text_heading_user"));
		text_heading_password.setText(read_file_languages.getString("text_heading_password"));
		checkbox_remember_user.setText(read_file_languages.getString("checkbox_remember_user"));
		button_login.setText(read_file_languages.getString("text_heading_login"));
	}
}
