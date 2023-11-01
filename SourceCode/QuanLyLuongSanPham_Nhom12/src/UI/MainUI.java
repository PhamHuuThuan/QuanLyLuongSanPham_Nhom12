package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import ConnectDB.ConnectDB;
import Entity.NhanVien;
import Util.ImportFont;
import Util.LuuTru;
import Util.SoundPlay;

public class MainUI extends JFrame {

	private JPanel contentPane;
	public Font roboto_light, roboto_bold, roboto_regular;
	private ImportFont impFont;
	public Color borderFocusColor;
	public JPanel pnlContent;
	public SoundPlay music;
	public LuuTru l = new LuuTru();
	public ResourceBundle read_file_languages = ResourceBundle.getBundle(l.readFile("src/config/languages/selectedLanguage.txt"));
	public ResourceBundle read_file_themes = ResourceBundle.getBundle(l.readFile("src/config/languages/selectedLanguage.txt"));
	public NhanVien nv;
	private JLabel clockLabel;

	/**
	 * Create the frame.
	 */
	public MainUI(NhanVien nv) {
		ImageIcon appIcon = new ImageIcon("assets/logo_v1.png");
		setIconImage(appIcon.getImage());
		this.nv = nv;
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		khoiTaoGiaTriDefault();
		getFonts();
		setTitle("TPT_FUNITURE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		
		
		
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setBounds(0, 0, 1500, 800);
		add(new MenuUI(this), BorderLayout.NORTH);
		add(pnlContent, BorderLayout.CENTER);
		
	}
	// import font chữ Roboto để sử dụng
	public void getFonts() {
		impFont = new ImportFont();
		roboto_light = impFont.importFontFromFile("/fonts/Roboto-Light.ttf");
		roboto_bold = impFont.importFontFromFile("/fonts/Roboto-Bold.ttf");
		roboto_regular = impFont.importFontFromFile("/fonts/Roboto-Regular.ttf");
	}
	//khởi tạo một số giá trị mặc định cần thiết cho ứng dụng
	public void khoiTaoGiaTriDefault() {
		borderFocusColor = new Color(0, 0, 255, 64);  // set màu mặc định cho border khi được focus
		pnlContent = new JPanel(new BorderLayout()); // Phần jpanel chứa các giao diện chức năng
		music = new SoundPlay(); // Khởi tạo âm thanh ứng dụng
	}
	
}
