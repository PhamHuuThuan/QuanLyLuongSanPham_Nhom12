package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.plaf.basic.CalendarHeaderHandler;
import org.jdesktop.swingx.plaf.basic.SpinningCalendarHeaderHandler;

import ConnectDB.ConnectDB;
import Entity.BangPhanCongNhanVien;
import Util.ConfigManager;
import Util.ImportFont;
import Util.LuuTru;
import Util.SoundPlay;

public class MainUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public Font roboto_light, roboto_bold, roboto_regular;
	private ImportFont impFont;
	public Color borderFocusColor;
	public JPanel pnlContent;
	public SoundPlay music = new SoundPlay();
	public LuuTru l = new LuuTru();
	public ConfigManager config = new ConfigManager("/config/config.properties");
	public ResourceBundle read_file_languages;
	public ResourceBundle read_file_themes = ResourceBundle.getBundle("config/themes/lightmode");
	public BangPhanCongNhanVien nv;
	private JLabel clockLabel;

	/**
	 * Create the frame.
	 */
	public MainUI(BangPhanCongNhanVien nv) {
		ImageIcon appIcon = new ImageIcon("assets/icon_logo.png");
		setIconImage(appIcon.getImage());
		this.nv = nv;
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		khoiTaoGiaTriDefault();
		getConfig();
		getFonts();
		setTitle("TPT_FUNITURE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		UIManager.put(CalendarHeaderHandler.uiControllerID, SpinningCalendarHeaderHandler.class.getName());
	}
	
	public void getConfig() {
		read_file_languages = l.getLanguageConfig(config.getLanguage());
		int value = config.getSoundSetting();
		music.music.setVolumeScale(value);
		music.se.setVolumeScale(value);
	}
	
}
