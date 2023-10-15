package customView;

import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class MenuUI extends JPanel implements ActionListener, MouseListener{
	private MainUI main;
	private JMenuItem menuTrangChu, menuPhongBan, menuHopDong, menuQuanLySP, menuTimKiemSP, menuQLCD, menuPCCD,menuTimKiemCD, menuQuanLyCN, menuTimKiemCN, menuChamCongCN,
	menuTinhLuongCN, menuQuanLyNV, menuTimKiem, menuPCNV, menuChamCongNV, menuTinhLuong, menuThongKe, menuCaiDat;
	private JMenu menuSanPham, menuCongDoan, menuCongNhan, menuNhanVien;
	
	public String pathFileLanguage;
	public String pathFileTheme;

	private void readPathFileLanguage() {
		try {
			FileReader reader = new FileReader("src/config/languages/selectedLanguage.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			pathFileLanguage = bufferedReader.readLine();
			bufferedReader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	private void readPathFileTheme() {
		try {
			FileReader reader = new FileReader("src/config/themes/selectedTheme.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			pathFileTheme = bufferedReader.readLine();
			bufferedReader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	

	

	
	public MenuUI(MainUI main) {
		this.main = main;
		readPathFileLanguage();
		readPathFileTheme();

		ResourceBundle read_file_themes = ResourceBundle.getBundle(pathFileTheme);
		
		setBackground(Color.decode(read_file_themes.getString("color_main")));
//		setBackground(Color.RED);
		setLayout(new BorderLayout(0, 0));
		setUIManagerColor();
		readPathFileTheme();
		
		createGUI();
		
		menuTrangChu.addActionListener(this);
		menuTrangChu.addActionListener(this); 
		menuPhongBan.addActionListener(this);
		menuHopDong.addActionListener(this);
		menuQuanLySP.addActionListener(this);
		menuTimKiemSP.addActionListener(this);
		menuQLCD.addActionListener(this);
		menuPCCD.addActionListener(this);
		menuTimKiemCD.addActionListener(this);
		menuQuanLyCN.addActionListener(this);
		menuTimKiemCN.addActionListener(this);
		menuChamCongCN.addActionListener(this);
		menuTinhLuongCN.addActionListener(this);
		menuQuanLyNV.addActionListener(this);
		menuTimKiem.addActionListener(this);
		menuPCNV.addActionListener(this);
		menuChamCongNV.addActionListener(this);
		menuTinhLuong.addActionListener(this);
		menuThongKe.addActionListener(this);
		menuCaiDat.addActionListener(this);
		
		menuSanPham.addMouseListener(this);
		menuCongDoan.addMouseListener(this);
		menuCongNhan.addMouseListener(this);
		menuNhanVien.addMouseListener(this);
		
	}
	public void createGUI() {
		
		
		ResourceBundle read_file_languages = ResourceBundle.getBundle(pathFileLanguage);
		ResourceBundle read_file_themes = ResourceBundle.getBundle(pathFileTheme);

		JPanel pnHead = new JPanel();
		add(pnHead, BorderLayout.NORTH);
		pnHead.setBackground(Color.decode(read_file_themes.getString("color_main_sw")));
		pnHead.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(Color.decode(read_file_themes.getString("color_main")));
		pnHead.add(pnTitle, BorderLayout.CENTER);
		pnTitle.setLayout(new BoxLayout(pnTitle, BoxLayout.X_AXIS));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageScaler("/image/logo_v3_color-no-bgr-342e993c.png", 48, 48).getScaledImageIcon());
		lblLogo.setBorder(new RoundedBorder(Color.decode(read_file_themes.getString("color_main_sw")), 5, 2));
		pnTitle.add(lblLogo);
		
		Component horizontalBox = Box.createHorizontalStrut(20);
		pnTitle.add(horizontalBox);
		
		JLabel lblHeader = new JLabel(read_file_languages.getString("text_heading_title_menu"));
		lblHeader.setFont(main.roboto_bold.deriveFont(Font.BOLD, 26F));
		lblHeader.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		pnTitle.add(lblHeader);
		
		JPanel pnMenu = new JPanel();
		pnMenu.setBackground(Color.decode(read_file_themes.getString("color_main")));
		pnMenu.setLayout(new BorderLayout());
		pnHead.add(pnMenu,BorderLayout.SOUTH);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new GridLayout(1, 9));
		menuBar.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuBar.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		pnMenu.add(menuBar, BorderLayout.CENTER);
		
		menuTrangChu = new JMenuItem(read_file_languages.getString("text_home"));
		menuTrangChu.setFont(this.main.roboto_regular.deriveFont(Font.BOLD,20F));
		menuTrangChu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.decode(read_file_themes.getString("color_main_sw"))), 
							BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuBar.add(menuTrangChu);
		
		menuPhongBan = new JMenuItem(read_file_languages.getString("text_department"));
		menuPhongBan.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		menuPhongBan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuBar.add(menuPhongBan);
		
		menuHopDong = new JMenuItem(read_file_languages.getString("text_contract"));
		menuHopDong.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		menuHopDong.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuBar.add(menuHopDong);
		
		menuSanPham = new JMenu(read_file_languages.getString("text_product"));
		menuSanPham.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		menuSanPham.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuSanPham.getPopupMenu().setPopupSize(new Dimension(168, 75));
		menuSanPham.getPopupMenu().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#424242")));
		menuBar.add(menuSanPham);
		
		menuQuanLySP = new JMenuItem(read_file_languages.getString("text_manage"));
		menuQuanLySP.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuQuanLySP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuSanPham.add(menuQuanLySP);
		
		menuTimKiemSP = new JMenuItem(read_file_languages.getString("text_search"));
		menuTimKiemSP.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuTimKiemSP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuSanPham.add(menuTimKiemSP);
		
		menuCongDoan = new JMenu(read_file_languages.getString("text_stage"));
		menuCongDoan.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		menuCongDoan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuCongDoan.getPopupMenu().setPopupSize(new Dimension(168, 113));
		menuCongDoan.getPopupMenu().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#424242")));
		menuBar.add(menuCongDoan);
		
		menuQLCD = new JMenuItem(read_file_languages.getString("text_manage"));
		menuQLCD.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuQLCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuCongDoan.add(menuQLCD);
		
		menuPCCD = new JMenuItem(read_file_languages.getString("text_assignment"));
		menuPCCD.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuPCCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuCongDoan.add(menuPCCD);
		
		menuTimKiemCD = new JMenuItem(read_file_languages.getString("text_search"));
		menuTimKiemCD.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuTimKiemCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuCongDoan.add(menuTimKiemCD);
		
		menuCongNhan = new JMenu(read_file_languages.getString("text_worder"));
		menuCongNhan.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		menuCongNhan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuCongNhan.getPopupMenu().setPopupSize(new Dimension(168, 150));
		menuCongNhan.getPopupMenu().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#424242")));
		menuBar.add(menuCongNhan);
		
		menuQuanLyCN = new JMenuItem(read_file_languages.getString("text_manage"));
		menuQuanLyCN.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuQuanLyCN.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuCongNhan.add(menuQuanLyCN);
		
		menuTimKiemCN = new JMenuItem(read_file_languages.getString("text_search"));
		menuTimKiemCN.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuTimKiemCN.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuCongNhan.add(menuTimKiemCN);
		
		menuChamCongCN = new JMenuItem(read_file_languages.getString("text_timekeeping"));
		menuChamCongCN.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuChamCongCN.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuCongNhan.add(menuChamCongCN);
		
		menuTinhLuongCN = new JMenuItem(read_file_languages.getString("text_payroll"));
		menuTinhLuongCN.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuTinhLuongCN.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuCongNhan.add(menuTinhLuongCN);
		
		menuNhanVien = new JMenu(read_file_languages.getString("text_staff"));
		menuNhanVien.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		menuNhanVien.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuNhanVien.getPopupMenu().setPopupSize(new Dimension(168, 188));
		menuNhanVien.getPopupMenu().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#424242")));
		menuBar.add(menuNhanVien);
		
		menuQuanLyNV = new JMenuItem(read_file_languages.getString("text_manage"));
		menuQuanLyNV.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuQuanLyNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuNhanVien.add(menuQuanLyNV);
		
		menuTimKiem = new JMenuItem(read_file_languages.getString("text_search"));
		menuTimKiem.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuTimKiem.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuNhanVien.add(menuTimKiem);
		
		menuPCNV = new JMenuItem(read_file_languages.getString("text_assignment"));
		menuPCNV.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuPCNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuNhanVien.add(menuPCNV);
		
		menuChamCongNV = new JMenuItem(read_file_languages.getString("text_timekeeping"));
		menuChamCongNV.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuChamCongNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuNhanVien.add(menuChamCongNV);
		
		menuTinhLuong = new JMenuItem(read_file_languages.getString("text_payroll"));
		menuTinhLuong.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		menuTinhLuong.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuNhanVien.add(menuTinhLuong);
		
		menuThongKe = new JMenuItem(read_file_languages.getString("text_statistical"));
		menuThongKe.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		menuThongKe.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuBar.add(menuThongKe);
		
		menuCaiDat = new JMenuItem(read_file_languages.getString("text_orther"));
		menuCaiDat.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		menuCaiDat.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		menuBar.add(menuCaiDat);
		
		setMenuColorDefault();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o==menuSanPham) {
			setMenuColorDefault();
			menuSanPham.setOpaque(true);
			menuSanPham.setBackground(Color.decode("#424242"));
			menuSanPham.setForeground(Color.WHITE);
			menuSanPham.setIcon(new ImageScaler("/image/package_icon(1).png", 24, 24).getScaledImageIcon());
		}
		if(o==menuCongDoan) {
			setMenuColorDefault();
			menuCongDoan.setOpaque(true);
			menuCongDoan.setBackground(Color.decode("#424242"));
			menuCongDoan.setForeground(Color.WHITE);
			menuCongDoan.setIcon(new ImageScaler("/image/timeline_icon(1).png", 24, 24).getScaledImageIcon());
		}
		if(o==menuCongNhan) {
			setMenuColorDefault();
			menuCongNhan.setOpaque(true);
			menuCongNhan.setBackground(Color.decode("#424242"));
			menuCongNhan.setForeground(Color.WHITE);
			menuCongNhan.setIcon(new ImageScaler("/image/construction-and-tools_icon(1).png", 24, 24).getScaledImageIcon());
		}
		if(o==menuNhanVien) {
			setMenuColorDefault();
			menuNhanVien.setOpaque(true);
			menuNhanVien.setBackground(Color.decode("#424242"));
			menuNhanVien.setForeground(Color.WHITE);
			menuNhanVien.setIcon(new ImageScaler("/image/team_icon(1).png", 24, 24).getScaledImageIcon());
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
		if(o==menuTrangChu) {
			setMenuColorDefault();
			menuTrangChu.setBackground(Color.decode("#424242"));
			menuTrangChu.setForeground(Color.WHITE);
			menuTrangChu.setIcon(new ImageScaler("/image/home_icon(1).png", 24, 24).getScaledImageIcon());
		}
		if(o==menuPhongBan) {
			setMenuColorDefault();
			menuPhongBan.setBackground(Color.decode("#424242"));
			menuPhongBan.setForeground(Color.WHITE);
			menuPhongBan.setIcon(new ImageScaler("/image/door-open_icon(1).png", 24, 24).getScaledImageIcon());
		}
		if(o==menuHopDong) {
			setMenuColorDefault();
			menuHopDong.setBackground(Color.decode("#424242"));
			menuHopDong.setForeground(Color.WHITE);
			menuHopDong.setIcon(new ImageScaler("/image/contract_icon(1).png", 24, 24).getScaledImageIcon());
		}
		if(o==menuSanPham) {
			
		}
		if(o==menuThongKe) {
			setMenuColorDefault();
			menuThongKe.setBackground(Color.decode("#424242"));
			menuThongKe.setForeground(Color.WHITE);
			menuThongKe.setIcon(new ImageScaler("/image/trend_icon(1).png", 24, 24).getScaledImageIcon());
		}
		if(o==menuCaiDat) {
			setMenuColorDefault();
			menuCaiDat.setBackground(Color.decode("#424242"));
			menuCaiDat.setForeground(Color.WHITE);
			menuCaiDat.setIcon(new ImageScaler("/image/cogwheel_icon(1).png", 24, 24).getScaledImageIcon());
		}
	}
	public void setMenuColorDefault() {
		
		ResourceBundle read_file_themes = ResourceBundle.getBundle(pathFileTheme);
		
		menuTrangChu.setIcon(new ImageScaler("/image/home_icon.png", 24, 24).getScaledImageIcon());
		menuTrangChu.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuTrangChu.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuPhongBan.setIcon(new ImageScaler("/image/door-open_icon.png", 24, 24).getScaledImageIcon());
		menuPhongBan.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuPhongBan.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuHopDong.setIcon(new ImageScaler("/image/contract_icon.png", 24, 24).getScaledImageIcon());
		menuHopDong.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuHopDong.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuSanPham.setIcon(new ImageScaler("/image/package_icon.png", 24, 24).getScaledImageIcon());
		menuSanPham.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuSanPham.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuTimKiemSP.setIcon(new ImageScaler("/image/magnifying-glass_icon.png", 24, 24).getScaledImageIcon());
		menuTimKiemSP.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuTimKiemSP.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuQuanLySP.setIcon(new ImageScaler("/image/add-package_icon.png", 24, 24).getScaledImageIcon());
		menuQuanLySP.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuQuanLySP.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuCongDoan.setIcon(new ImageScaler("/image/timeline_icon.png", 24, 24).getScaledImageIcon());
		menuCongDoan.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuCongDoan.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuTimKiemCD.setIcon(new ImageScaler("/image/magnifying-glass_icon.png", 24, 24).getScaledImageIcon());
		menuTimKiemCD.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuTimKiemCD.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuQLCD.setIcon(new ImageScaler("/image/add-package_icon.png", 24, 24).getScaledImageIcon());
		menuQLCD.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuQLCD.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuPCCD.setIcon(new ImageScaler("/image/phancong_icon.png", 24, 24).getScaledImageIcon());
		menuPCCD.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuPCCD.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuCongNhan.setIcon(new ImageScaler("/image/construction-and-tools_icon.png", 24, 24).getScaledImageIcon());
		menuCongNhan.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuCongNhan.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuQuanLyCN.setIcon(new ImageScaler("/image/add-package_icon.png", 24, 24).getScaledImageIcon());
		menuQuanLyCN.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuQuanLyCN.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuTimKiemCN.setIcon(new ImageScaler("/image/magnifying-glass_icon.png", 24, 24).getScaledImageIcon());
		menuTimKiemCN.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuTimKiemCN.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuChamCongCN.setIcon(new ImageScaler("/image/chamcong_icon.png", 24, 24).getScaledImageIcon());
		menuChamCongCN.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuChamCongCN.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuTinhLuongCN.setIcon(new ImageScaler("/image/tinhluong_icon.png", 24, 24).getScaledImageIcon());
		menuTinhLuongCN.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuTinhLuongCN.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuNhanVien.setIcon(new ImageScaler("/image/team_icon.png", 24, 24).getScaledImageIcon());
		menuNhanVien.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuNhanVien.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuQuanLyNV.setIcon(new ImageScaler("/image/add-package_icon.png", 24, 24).getScaledImageIcon());
		menuQuanLyNV.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuQuanLyNV.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuTimKiem.setIcon(new ImageScaler("/image/magnifying-glass_icon.png", 24, 24).getScaledImageIcon());
		menuTimKiem.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuTimKiem.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuPCNV.setIcon(new ImageScaler("/image/phancong_icon.png", 24, 24).getScaledImageIcon());
		menuPCNV.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuPCNV.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuChamCongNV.setIcon(new ImageScaler("/image/chamcong_icon.png", 24, 24).getScaledImageIcon());
		menuChamCongNV.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuChamCongNV.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuTinhLuong.setIcon(new ImageScaler("/image/tinhluong_icon.png", 24, 24).getScaledImageIcon());
		menuTinhLuong.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuTinhLuong.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuThongKe.setIcon(new ImageScaler("/image/trend_icon.png", 24, 24).getScaledImageIcon());
		menuThongKe.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuThongKe.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		menuCaiDat.setIcon(new ImageScaler("/image/cogwheel_icon.png", 24, 24).getScaledImageIcon());
		menuCaiDat.setBackground(Color.decode(read_file_themes.getString("color_main")));
		menuCaiDat.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
	}
	public void setUIManagerColor() {
		ResourceBundle read_file_themes = ResourceBundle.getBundle(pathFileTheme);
		
		UIManager.put("Menu.selectionBackground", Color.decode(read_file_themes.getString("color_main")));
		UIManager.put("Menu.selectionForeground", Color.decode(read_file_themes.getString("color_main_sw")));
		
//		UIManager.put("MenuItem.selectionBackground", Color.decode("#ffe6cc"));
	}
}
