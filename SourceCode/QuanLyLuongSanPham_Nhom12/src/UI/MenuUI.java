package UI;

import javax.swing.JPanel;
import javax.swing.UIManager;

import CustomUI.ImageScaler;
import CustomUI.RoundedBorder;
import Util.ConvertTime;
import Util.LuuTru;
import Util.SoundPlay;
import groovyjarjarantlr.Utils;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MenuUI extends JPanel implements ActionListener, MouseListener{
	private MainUI main;
	private JMenuItem mniTrangChu, mniPhongBan, mniHopDong, mniQuanLySP, mniTimKiemSP, mniQLCD, mniPCCD, mniTimKiemCD, mniQuanLyCN, mniTimKiemCN, mniChamCongCN,
	mniTinhLuongCN, mniQuanLyNV, mniTimKiem, mniPCNV, mniChamCongNV, mniTinhLuong, mniThongKe, mniCaiDat;
	private JMenu mnSanPham, mnCongDoan, mnCongNhan, mnNhanVien;
	
	public String pathFileLanguage;
	public String pathFileTheme;
	private LuuTru l;
	private JPanel pnlControl;
	private JLabel lblHello;
	private JLabel lblNameUser;
	private JButton btnControlAccout;
	
	
	private Component horizontalStrut;
	private JPanel pnlLeftBox;
	private JLabel lblLogo;
	private Component horizontalStrut_1;
	private JPanel pnlClock;
	private JLabel lblTime,lblDate;
	private Thread clock;
	
	
	public MenuUI(MainUI main) {
		this.main = main;
		l = new LuuTru();
		main.music = new SoundPlay();
		
		
		main.pnlContent.add(new TrangChu_UI(), BorderLayout.CENTER);
		

		pathFileLanguage = l.readFile("src/config/languages/selectedLanguage.txt");
		pathFileTheme = l.readFile("src/config/themes/selectedTheme.txt");

		ResourceBundle read_file_themes = ResourceBundle.getBundle(pathFileTheme);
		
		setBackground(Color.decode(read_file_themes.getString("color_main")));
		setLayout(new BorderLayout(0, 0));
		setUIManagerColor();
		createGUI();
		
		//thêm sự kiện cho các button
		mniTrangChu.addActionListener(this);
		mniPhongBan.addActionListener(this);
		mniHopDong.addActionListener(this);
		mnSanPham.addActionListener(this);
		mniQuanLySP.addActionListener(this);
		mniTimKiemSP.addActionListener(this);
		mniQLCD.addActionListener(this);
		mniPCCD.addActionListener(this);
		mnCongNhan.addActionListener(this);
		mniQuanLyCN.addActionListener(this);
		mniTimKiemCN.addActionListener(this);
		mniChamCongCN.addActionListener(this);
		mniTinhLuongCN.addActionListener(this);
		mnNhanVien.addActionListener(this);
		mniQuanLyNV.addActionListener(this);
		mniTimKiem.addActionListener(this);
		mniPCNV.addActionListener(this);
		mniChamCongNV.addActionListener(this);
		mniTinhLuong.addActionListener(this);
		mniThongKe.addActionListener(this);
		mniCaiDat.addActionListener(this);
		btnControlAccout.addActionListener(this);
		
		mnSanPham.addMouseListener(this);
		mnCongNhan.addMouseListener(this);
		mnNhanVien.addMouseListener(this);
		
	}
	//Tạo giao diện menu
	public void createGUI() {
		
		
		ResourceBundle read_file_languages = ResourceBundle.getBundle(pathFileLanguage);
		ResourceBundle read_file_themes = ResourceBundle.getBundle(pathFileTheme);

		JPanel pnlHead = new JPanel();
		add(pnlHead, BorderLayout.NORTH);
		pnlHead.setBackground(Color.decode(read_file_themes.getString("color_main_sw")));
		pnlHead.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBackground(Color.decode(read_file_themes.getString("color_main")));
		pnlHead.add(pnlTitle, BorderLayout.CENTER);
		pnlTitle.setLayout(new GridLayout(0, 3, 0, 0));
		
		pnlLeftBox = new JPanel();
		pnlLeftBox.setBackground(new Color(255, 255, 255));
		pnlTitle.add(pnlLeftBox);
		pnlLeftBox.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageScaler("/image/logo_v3_color-no-bgr-342e993c.png",40,40).getScaledImageIcon());
		lblLogo.setBorder(new RoundedBorder(Color.decode(read_file_themes.getString("color_main_sw")), 5, 2));
		pnlLeftBox.add(lblLogo);
		
		horizontalStrut_1 = Box.createHorizontalStrut(30);
		pnlLeftBox.add(horizontalStrut_1);
		
		pnlClock = new JPanel();
		pnlClock.setBackground(Color.decode(read_file_themes.getString("color_main")));
		pnlLeftBox.add(pnlClock);
		pnlClock.setLayout(new GridLayout(2, 1, 0, 0));
		
		lblDate = new JLabel("?");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(main.roboto_bold.deriveFont(Font.PLAIN, 19F));
		lblDate.setForeground(Color.decode("#b22323"));
		pnlClock.add(lblDate);
		
		lblTime = new JLabel("?");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(main.roboto_bold.deriveFont(Font.BOLD, 20F));
		lblTime.setForeground(Color.decode("#b22323"));
		pnlClock.add(lblTime);
		
		clock();
		
		
		JLabel lblHeader = new JLabel(read_file_languages.getString("text_heading_title_menu"));
		lblHeader.setFont(main.roboto_bold.deriveFont(Font.BOLD, 21F));
		lblHeader.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		pnlTitle.add(lblHeader);
		
		pnlControl = new JPanel();
		pnlTitle.add(pnlControl);
		pnlControl.setBackground(Color.decode(read_file_themes.getString("color_main")));
		pnlControl.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		lblHello = new JLabel("Xin chào, ");
		lblHello.setFont(main.roboto_bold.deriveFont(Font.PLAIN, 20F));
		pnlControl.add(lblHello);
		
		lblNameUser = new JLabel("Nguyễn Văn Phong");
		lblNameUser.setFont(main.roboto_bold.deriveFont(Font.BOLD, 20F));
		pnlControl.add(lblNameUser);
		
		horizontalStrut = Box.createHorizontalStrut(10);
		pnlControl.add(horizontalStrut);
		
		btnControlAccout = new JButton("");
		btnControlAccout.setBorder(null);
		btnControlAccout.setBackground(null);
		btnControlAccout.setOpaque(false);
		btnControlAccout.setIcon(new ImageScaler("/image/icon_control_account.png", 40, 40).getScaledImageIcon());
		pnlControl.add(btnControlAccout);
		
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(Color.decode(read_file_themes.getString("color_main")));
		pnlMenu.setLayout(new BorderLayout());
		pnlHead.add(pnlMenu,BorderLayout.SOUTH);
		
		JMenuBar mnuBar = new JMenuBar();
		mnuBar.setLayout(new GridLayout(1, 9));
		mnuBar.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mnuBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pnlMenu.add(mnuBar, BorderLayout.CENTER);
		
		mniTrangChu = new JMenuItem(read_file_languages.getString("text_home"));
		mniTrangChu.setFont(this.main.roboto_regular.deriveFont(Font.BOLD,20F));
		mniTrangChu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.decode(read_file_themes.getString("color_main_sw"))), 
							BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnuBar.add(mniTrangChu);
		
		mniPhongBan = new JMenuItem(read_file_languages.getString("text_department"));
		mniPhongBan.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		mniPhongBan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnuBar.add(mniPhongBan);
		
		mniHopDong = new JMenuItem(read_file_languages.getString("text_contract"));
		mniHopDong.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		mniHopDong.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnuBar.add(mniHopDong);
		
		mnSanPham = new JMenu(read_file_languages.getString("text_product"));
		mnSanPham.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		mnSanPham.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnSanPham.getPopupMenu().setPopupSize(new Dimension(192, 113));
		mnSanPham.getPopupMenu().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#424242")));
		mnuBar.add(mnSanPham);
		
		mniQuanLySP = new JMenuItem(read_file_languages.getString("text_manage"));
		mniQuanLySP.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniQuanLySP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnSanPham.add(mniQuanLySP);
		
		mniTimKiemSP = new JMenuItem(read_file_languages.getString("text_search"));
		mniTimKiemSP.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniTimKiemSP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnSanPham.add(mniTimKiemSP);
		
//		mnCongDoan = new JMenu(read_file_languages.getString("text_stage"));
//		mnCongDoan.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
//		mnCongDoan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.decode(read_file_themes.getString("color_main_sw"))), 
//				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
//		mnCongDoan.getPopupMenu().setPopupSize(new Dimension(168, 113));
//		mnCongDoan.getPopupMenu().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#424242")));
//		mnuBar.add(mnCongDoan);
		
		mniQLCD = new JMenuItem(read_file_languages.getString("text_manage_CD"));
		mniQLCD.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniQLCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnSanPham.add(mniQLCD);
		
		mniPCCD = new JMenuItem(read_file_languages.getString("text_assignment"));
		mniPCCD.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniPCCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		
//		mniTimKiemCD = new JMenuItem(read_file_languages.getString("text_search"));
//		mniTimKiemCD.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
//		mniTimKiemCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
//				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
//		mnCongDoan.add(mniTimKiemCD);
		
		mnCongNhan = new JMenu(read_file_languages.getString("text_worder"));
		mnCongNhan.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		mnCongNhan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnCongNhan.getPopupMenu().setPopupSize(new Dimension(192, 188));
		mnCongNhan.getPopupMenu().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#424242")));
		mnuBar.add(mnCongNhan);
		
		mniQuanLyCN = new JMenuItem(read_file_languages.getString("text_manage"));
		mniQuanLyCN.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniQuanLyCN.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnCongNhan.add(mniQuanLyCN);
		
		mniTimKiemCN = new JMenuItem(read_file_languages.getString("text_search"));
		mniTimKiemCN.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniTimKiemCN.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnCongNhan.add(mniTimKiemCN);
		mnCongNhan.add(mniPCCD);
		
		mniChamCongCN = new JMenuItem(read_file_languages.getString("text_timekeeping"));
		mniChamCongCN.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniChamCongCN.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnCongNhan.add(mniChamCongCN);
		
		mniTinhLuongCN = new JMenuItem(read_file_languages.getString("text_payroll"));
		mniTinhLuongCN.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniTinhLuongCN.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnCongNhan.add(mniTinhLuongCN);
		
		mnNhanVien = new JMenu(read_file_languages.getString("text_staff"));
		mnNhanVien.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		mnNhanVien.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnNhanVien.getPopupMenu().setPopupSize(new Dimension(192, 188));
		mnNhanVien.getPopupMenu().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#424242")));
		mnuBar.add(mnNhanVien);
		
		mniQuanLyNV = new JMenuItem(read_file_languages.getString("text_manage"));
		mniQuanLyNV.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniQuanLyNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnNhanVien.add(mniQuanLyNV);
		
		mniTimKiem = new JMenuItem(read_file_languages.getString("text_search"));
		mniTimKiem.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniTimKiem.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnNhanVien.add(mniTimKiem);
		
		mniPCNV = new JMenuItem(read_file_languages.getString("text_assignment"));
		mniPCNV.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniPCNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnNhanVien.add(mniPCNV);
		
		mniChamCongNV = new JMenuItem(read_file_languages.getString("text_timekeeping"));
		mniChamCongNV.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniChamCongNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnNhanVien.add(mniChamCongNV);
		
		mniTinhLuong = new JMenuItem(read_file_languages.getString("text_payroll"));
		mniTinhLuong.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniTinhLuong.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnNhanVien.add(mniTinhLuong);
		
		mniThongKe = new JMenuItem(read_file_languages.getString("text_statistical"));
		mniThongKe.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		mniThongKe.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnuBar.add(mniThongKe);
		
		mniCaiDat = new JMenuItem(read_file_languages.getString("text_orther"));
		mniCaiDat.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		mniCaiDat.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnuBar.add(mniCaiDat);
		
		setMenuColorDefault();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o==mnSanPham) {
			setMenuColorDefault();
			mnSanPham.setOpaque(true);
			mnSanPham.setBackground(Color.decode("#424242"));
			mnSanPham.setForeground(Color.WHITE);
			mnSanPham.setIcon(new ImageScaler("/image/package_icon(1).png", 24, 24).getScaledImageIcon());
		}
		if(o==mnCongDoan) {
			setMenuColorDefault();
			mnCongDoan.setOpaque(true);
			mnCongDoan.setBackground(Color.decode("#424242"));
			mnCongDoan.setForeground(Color.WHITE);
			mnCongDoan.setIcon(new ImageScaler("/image/timeline_icon(1).png", 24, 24).getScaledImageIcon());
		}
		if(o==mnCongNhan) {
			setMenuColorDefault();
			mnCongNhan.setOpaque(true);
			mnCongNhan.setBackground(Color.decode("#424242"));
			mnCongNhan.setForeground(Color.WHITE);
			mnCongNhan.setIcon(new ImageScaler("/image/construction-and-tools_icon(1).png", 24, 24).getScaledImageIcon());
		}
		if(o==mnNhanVien) {
			setMenuColorDefault();
			mnNhanVien.setOpaque(true);
			mnNhanVien.setBackground(Color.decode("#424242"));
			mnNhanVien.setForeground(Color.WHITE);
			mnNhanVien.setIcon(new ImageScaler("/image/team_icon(1).png", 24, 24).getScaledImageIcon());
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
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
		if(o==btnControlAccout) {
			setMenuColorDefault();
			
			main.pnlContent.removeAll(); // Remove all nội dung
			main.pnlContent.add(new TrangCaNhan(), BorderLayout.CENTER); // thêm giao diện trang ca nhan vào
			main.validate(); // cập nhật lại
		}
		
		
		if(o==mniTrangChu) {
			setMenuColorDefault();
			mniTrangChu.setBackground(Color.decode("#424242"));
			mniTrangChu.setForeground(Color.WHITE);
			mniTrangChu.setIcon(new ImageScaler("/image/home_icon(1).png", 24, 24).getScaledImageIcon());
			
			main.pnlContent.removeAll(); // Remove all nội dung
			main.pnlContent.add(new TrangChu_UI(), BorderLayout.CENTER); // thêm giao diện trang chur vào
			main.validate(); // cập nhật lại
		}
		if(o==mniPhongBan) {
			setMenuColorDefault();
			mniPhongBan.setBackground(Color.decode("#424242"));
			mniPhongBan.setForeground(Color.WHITE);
			mniPhongBan.setIcon(new ImageScaler("/image/door-open_icon(1).png", 24, 24).getScaledImageIcon());
			
			main.pnlContent.removeAll(); // Remove all nội dung
			main.pnlContent.add(new PhongBan_UI(main), BorderLayout.CENTER); // thêm giao diện phong ban vào
			main.validate(); // cập nhật lại
		}
		if(o==mniHopDong) {
			setMenuColorDefault();
			mniHopDong.setBackground(Color.decode("#424242"));
			mniHopDong.setForeground(Color.WHITE);
			mniHopDong.setIcon(new ImageScaler("/image/contract_icon(1).png", 24, 24).getScaledImageIcon());
			
			main.pnlContent.removeAll(); // Remove all nội dung
			main.pnlContent.add(new HopDongUI(main), BorderLayout.CENTER); // thêm giao diện hợp đồng vào
			main.validate(); // cập nhật lại
		}
		if(o==mniQuanLySP) {
			setMenuColorDefault();
			mnSanPham.setBackground(Color.decode("#424242"));
			mnSanPham.setForeground(Color.WHITE);
			mnSanPham.setIcon(new ImageScaler("/image/package_icon(1).png", 24, 24).getScaledImageIcon());
			
			main.pnlContent.removeAll(); // Remove all nội dung
			main.pnlContent.add(new QuanLySanPhamUI(main), BorderLayout.CENTER);// thêm giao diện sản phẩm vào
			main.validate(); // cập nhật lại
		}
		if(o==mniTimKiemSP) {
			setMenuColorDefault();
			mnSanPham.setBackground(Color.decode("#424242"));
			mnSanPham.setForeground(Color.WHITE);
			mnSanPham.setIcon(new ImageScaler("/image/package_icon(1).png", 24, 24).getScaledImageIcon());
			
			main.pnlContent.removeAll(); // Remove all nội dung
			main.pnlContent.add(new TimKiemSanPhamUI(main), BorderLayout.CENTER);// thêm giao diện tìm kiếm sản phẩm vào
			main.validate(); // cập nhật lại	
		}
		if(o==mniQLCD) {
			setMenuColorDefault();
			mnSanPham.setBackground(Color.decode("#424242"));
			mnSanPham.setForeground(Color.WHITE);
			mnSanPham.setIcon(new ImageScaler("/image/package_icon(1).png", 24, 24).getScaledImageIcon());
			
			main.pnlContent.removeAll(); // Remove all nội dung
//			main.pnlContent.add(new QuanLyCongDoanUI(main), BorderLayout.CENTER);// thêm giao diện quản lí công đoạn vào
			main.validate(); // cập nhật lại	
		}
		if(o==mnCongNhan) {
					
				}
		if(o==mniQuanLyNV) {
			setMenuColorDefault();
			mnNhanVien.setBackground(Color.decode("#424242"));
			mnNhanVien.setForeground(Color.WHITE);
			mnNhanVien.setIcon(new ImageScaler("/image/team_icon(1).png", 24, 24).getScaledImageIcon());
			
			main.pnlContent.removeAll(); // Remove all nội dung
			main.pnlContent.add(new QuanLyNhanVienUI(main), BorderLayout.CENTER);// thêm giao diện nhân viên vào
			main.validate(); // cập nhật lại
		}
		if(o==mniTimKiem) {
			setMenuColorDefault();
			mnNhanVien.setBackground(Color.decode("#424242"));
			mnNhanVien.setForeground(Color.WHITE);
			mnNhanVien.setIcon(new ImageScaler("/image/team_icon(1).png", 24, 24).getScaledImageIcon());
			
			main.pnlContent.removeAll(); // Remove all nội dung
			main.pnlContent.add(new TimKiemNhanVienUI(main), BorderLayout.CENTER);// thêm giao diện tìm nhân viên vào
			main.validate(); // cập nhật lại
		}
		if(o==mniPCNV) {
			setMenuColorDefault();
			mnNhanVien.setBackground(Color.decode("#424242"));
			mnNhanVien.setForeground(Color.WHITE);
			mnNhanVien.setIcon(new ImageScaler("/image/team_icon(1).png", 24, 24).getScaledImageIcon());
			
			main.pnlContent.removeAll(); // Remove all nội dung
			main.pnlContent.add(new PhanCongNhanVienUI(main), BorderLayout.CENTER);// thêm giao diện phân công nhân viên vào
			main.validate(); // cập nhật lại
		}
		if(o==mniChamCongNV) {
			setMenuColorDefault();
			mnNhanVien.setBackground(Color.decode("#424242"));
			mnNhanVien.setForeground(Color.WHITE);
			mnNhanVien.setIcon(new ImageScaler("/image/team_icon(1).png", 24, 24).getScaledImageIcon());
			
			main.pnlContent.removeAll(); // Remove all nội dung
			main.pnlContent.add(new ChamCongNhanVienUI(main), BorderLayout.CENTER);// thêm giao diện chấm công nhân viên vào
			main.validate(); // cập nhật lại
		}
		if(o==mniThongKe) {
			setMenuColorDefault();
			mniThongKe.setBackground(Color.decode("#424242"));
			mniThongKe.setForeground(Color.WHITE);
			mniThongKe.setIcon(new ImageScaler("/image/trend_icon(1).png", 24, 24).getScaledImageIcon());

		}
		if(o==mniCaiDat) {
			setMenuColorDefault();
			mniCaiDat.setBackground(Color.decode("#424242"));
			mniCaiDat.setForeground(Color.WHITE);
			mniCaiDat.setIcon(new ImageScaler("/image/cogwheel_icon(1).png", 24, 24).getScaledImageIcon());
		}
	}
	//set màu của menu ở trạng thái mặc định
	public void setMenuColorDefault() {
		
		ResourceBundle read_file_themes = ResourceBundle.getBundle(pathFileTheme);
		
		mniTrangChu.setIcon(new ImageScaler("/image/home_icon.png", 24, 24).getScaledImageIcon());
		mniTrangChu.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniTrangChu.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniPhongBan.setIcon(new ImageScaler("/image/door-open_icon.png", 24, 24).getScaledImageIcon());
		mniPhongBan.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniPhongBan.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniHopDong.setIcon(new ImageScaler("/image/contract_icon.png", 24, 24).getScaledImageIcon());
		mniHopDong.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniHopDong.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mnSanPham.setIcon(new ImageScaler("/image/package_icon.png", 24, 24).getScaledImageIcon());
		mnSanPham.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mnSanPham.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniTimKiemSP.setIcon(new ImageScaler("/image/magnifying-glass_icon.png", 24, 24).getScaledImageIcon());
		mniTimKiemSP.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniTimKiemSP.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniQuanLySP.setIcon(new ImageScaler("/image/add-package_icon.png", 24, 24).getScaledImageIcon());
		mniQuanLySP.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniQuanLySP.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
//		mnCongDoan.setIcon(new ImageScaler("/image/timeline_icon.png", 24, 24).getScaledImageIcon());
//		mnCongDoan.setBackground(Color.decode(read_file_themes.getString("color_main")));
//		mnCongDoan.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
//		
//		mniTimKiemCD.setIcon(new ImageScaler("/image/magnifying-glass_icon.png", 24, 24).getScaledImageIcon());
//		mniTimKiemCD.setBackground(Color.decode(read_file_themes.getString("color_main")));
//		mniTimKiemCD.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniQLCD.setIcon(new ImageScaler("/image/add-package_icon.png", 24, 24).getScaledImageIcon());
		mniQLCD.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniQLCD.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniPCCD.setIcon(new ImageScaler("/image/phancong_icon.png", 24, 24).getScaledImageIcon());
		mniPCCD.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniPCCD.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mnCongNhan.setIcon(new ImageScaler("/image/construction-and-tools_icon.png", 24, 24).getScaledImageIcon());
		mnCongNhan.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mnCongNhan.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniQuanLyCN.setIcon(new ImageScaler("/image/add-package_icon.png", 24, 24).getScaledImageIcon());
		mniQuanLyCN.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniQuanLyCN.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniTimKiemCN.setIcon(new ImageScaler("/image/magnifying-glass_icon.png", 24, 24).getScaledImageIcon());
		mniTimKiemCN.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniTimKiemCN.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniChamCongCN.setIcon(new ImageScaler("/image/chamcong_icon.png", 24, 24).getScaledImageIcon());
		mniChamCongCN.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniChamCongCN.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniTinhLuongCN.setIcon(new ImageScaler("/image/tinhluong_icon.png", 24, 24).getScaledImageIcon());
		mniTinhLuongCN.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniTinhLuongCN.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mnNhanVien.setIcon(new ImageScaler("/image/team_icon.png", 24, 24).getScaledImageIcon());
		mnNhanVien.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mnNhanVien.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniQuanLyNV.setIcon(new ImageScaler("/image/add-package_icon.png", 24, 24).getScaledImageIcon());
		mniQuanLyNV.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniQuanLyNV.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniTimKiem.setIcon(new ImageScaler("/image/magnifying-glass_icon.png", 24, 24).getScaledImageIcon());
		mniTimKiem.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniTimKiem.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniPCNV.setIcon(new ImageScaler("/image/phancong_icon.png", 24, 24).getScaledImageIcon());
		mniPCNV.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniPCNV.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniChamCongNV.setIcon(new ImageScaler("/image/chamcong_icon.png", 24, 24).getScaledImageIcon());
		mniChamCongNV.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniChamCongNV.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniTinhLuong.setIcon(new ImageScaler("/image/tinhluong_icon.png", 24, 24).getScaledImageIcon());
		mniTinhLuong.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniTinhLuong.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniThongKe.setIcon(new ImageScaler("/image/trend_icon.png", 24, 24).getScaledImageIcon());
		mniThongKe.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniThongKe.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniCaiDat.setIcon(new ImageScaler("/image/cogwheel_icon.png", 24, 24).getScaledImageIcon());
		mniCaiDat.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniCaiDat.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
	}
	public void setUIManagerColor() {
		ResourceBundle read_file_themes = ResourceBundle.getBundle(pathFileTheme);
		
		UIManager.put("Menu.selectionBackground", Color.decode(read_file_themes.getString("color_main_sw")));
		UIManager.put("Menu.selectionForeground", Color.decode(read_file_themes.getString("color_main")));
		UIManager.put("Button.focus", new Color(0,0,0,0));
//		UIManager.put("MenuItem.selectionBackground", Color.decode("#ffe6cc"));
	}
	
	public void clock() {
        clock = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    try {
                        LocalDateTime currentTime = LocalDateTime.now();
                        int day = currentTime.getDayOfMonth();
                        int month = currentTime.getMonthValue();
                        int year = currentTime.getYear();
                        int hour = currentTime.getHour();
                        int minute = currentTime.getMinute();
                        int second = currentTime.getSecond();
                        lblTime.setText(ConvertTime.convertLocalTimeToString(LocalTime.of(hour, minute,second)));
                        lblDate.setText(String.format("%s-%s-%d", day < 10 ? "0" + day : day,
                                        month < 10 ? "0" + month : month, year));
                        sleep(1000);
                    } catch (InterruptedException e) {
                        System.err.println(e);
                    }
                }
            }
	};

        clock.start();
    }

}














