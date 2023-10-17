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

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class Login_UI extends JFrame {

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

	public String pathFileLanguage = "config/languages/vietnamese";
	public String pathFileTheme = "config/themes/lightmode";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_UI frame = new Login_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void ClosePage() {
		this.dispose();
	}

	public Login_UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 456);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon gifAnimation = new ImageIcon("assets/logo_v1.png");
		JLabel widthtLogo = new JLabel(gifAnimation);
		widthtLogo.setBounds(0, 0, 411, 456);

		JPanel panel_logo = new JPanel();
		panel_logo.setBounds(0, 0, 412, 456);
		panel_logo.setLayout(null);
		panel_logo.add(widthtLogo);
		contentPane.add(panel_logo);

		panel_right = new JPanel();
		panel_right.setBackground(Color.decode("#424242"));
		panel_right.setBounds(411, 0, 540, 456);
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
		text_heading_login.setForeground(new Color(255, 255, 255));
		text_heading_login.setFont(new Font("Tahoma", Font.BOLD, 25));
		text_heading_login.setBounds(163, 0, 182, 31);
		panel_in_right.add(text_heading_login);

		panel_input_user = new JPanel();
		panel_input_user.setBounds(10, 64, 439, 86);
		panel_input_user.setBackground(Color.decode("#424242"));
		panel_in_right.add(panel_input_user);
		panel_input_user.setLayout(null);

		text_heading_user = new JLabel("TÀI KHOẢN");
		text_heading_user.setForeground(new Color(255, 255, 255));
		text_heading_user.setFont(new Font("Tahoma", Font.BOLD, 18));
		text_heading_user.setBounds(12, 0, 214, 38);
		panel_input_user.add(text_heading_user);

		input_user = new JTextField();
		input_user.requestFocusInWindow();
		input_user.setHorizontalAlignment(SwingConstants.CENTER);
		input_user.setForeground(new Color(255, 255, 255));
		input_user.setBackground(Color.decode("#515151"));
		input_user.setCaretColor(Color.WHITE);
		input_user.setBounds(12, 38, 427, 35);
		input_user.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		panel_input_user.add(input_user);
		input_user.setFont(new Font("Tahoma", Font.PLAIN, 18));
		input_user.setColumns(10);

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
		input_password.setHorizontalAlignment(SwingConstants.CENTER);
		input_password.setForeground(Color.WHITE);
		input_password.setFont(new Font("Tahoma", Font.PLAIN, 18));
		input_password.setColumns(10);
		input_password.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		input_password.setBackground(new Color(81, 81, 81));
		input_password.setBounds(12, 38, 424, 35);
		panel_input_password.add(input_password);

		JButton button_login = new JButton("ĐĂNG NHẬP");
		button_login.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_login.setBounds(20, 282, 429, 35);
		panel_in_right.add(button_login);

		JCheckBox checkbox_remember_user = new JCheckBox("Nhớ tài khoản");
		checkbox_remember_user.setSelected(true);
		checkbox_remember_user.setBackground(Color.decode("#424242"));
		checkbox_remember_user.setForeground(Color.WHITE);
		checkbox_remember_user.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkbox_remember_user.setBounds(346, 250, 103, 21);
		panel_in_right.add(checkbox_remember_user);

		JComboBox<String> combox_languages = new JComboBox<>();
		combox_languages.addItem("Vietnamese");
		combox_languages.addItem("English");
		combox_languages.setBounds(20, 353, 150, 31);
		panel_in_right.add(combox_languages);

		combox_languages.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				String selectLG = (String) combox_languages.getSelectedItem();
				if (selectLG.equals("English")) {
					pathFileLanguage = "config/languages/english";
				} else {
					pathFileLanguage = "config/languages/vietnamese";
				}

			}
		});
		

		JComboBox<String> combox_theme = new JComboBox<>();
		combox_theme.addItem("Lightmode");
		combox_theme.addItem("Darkmode");
		combox_theme.setBounds(334, 353, 115, 31);
		panel_in_right.add(combox_theme);

		combox_theme.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				String selectT = (String) combox_theme.getSelectedItem();
				if (selectT.equals("Darkmode")) {
					pathFileTheme = "config/themes/darkmode";
				} else {
					pathFileTheme = "config/themes/lightmode";
				}

			}
		});

		button_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FileWriter writer = new FileWriter("src/config/languages/selectedLanguage.txt");
					writer.write(pathFileLanguage);
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				};
				try {
					FileWriter writer = new FileWriter("src/config/themes/selectedTheme.txt");
					writer.write(pathFileTheme);
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
				openViewHome();
			}
		});
	}

	private void openViewHome() {
		this.dispose();
		MainUI goPageMenu = new MainUI();
//		ViewHome goPageMenu =  new ViewHome();
		goPageMenu.setVisible(true);
	}
}
