package UI;

import javax.swing.JPanel;
import javax.swing.UIManager;

import CustomUI.ImageScaler;
import CustomUI.RoundedBorder;
import Util.LuuTru;

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
	private JMenuItem mniTrangChu, mniPhongBan, mniHopDong, mniQuanLySP, mniTimKiemSP, mniQLCD, mniPCCD, mniTimKiemCD, mniQuanLyCN, mniTimKiemCN, mniChamCongCN,
	mniTinhLuongCN, mniQuanLyNV, mniTimKiem, mniPCNV, mniChamCongNV, mniTinhLuong, mniThongKe, mniCaiDat;
	private JMenu mnSanPham, mnCongDoan, mnCongNhan, mnNhanVien;
	
	public String pathFileLanguage;
	public String pathFileTheme;
	private LuuTru l;
	
	public MenuUI(MainUI main) {
		this.main = main;
		l = new LuuTru();
		pathFileLanguage = l.readFile("src/config/languages/selectedLanguage.txt");
		pathFileTheme = l.readFile("src/config/themes/selectedTheme.txt");

		ResourceBundle read_file_themes = ResourceBundle.getBundle(pathFileTheme);
		
		setBackground(Color.decode(read_file_themes.getString("color_main")));
//		setBackground(Color.RED);
		setLayout(new BorderLayout(0, 0));
		setUIManagerColor();
		createGUI();
		
		mniTrangChu.addActionListener(this);
		mniTrangChu.addActionListener(this); 
		mniPhongBan.addActionListener(this);
		mniHopDong.addActionListener(this);
		mniQuanLySP.addActionListener(this);
		mniTimKiemSP.addActionListener(this);
		mniQLCD.addActionListener(this);
		mniPCCD.addActionListener(this);
		mniTimKiemCD.addActionListener(this);
		mniQuanLyCN.addActionListener(this);
		mniTimKiemCN.addActionListener(this);
		mniChamCongCN.addActionListener(this);
		mniTinhLuongCN.addActionListener(this);
		mniQuanLyNV.addActionListener(this);
		mniTimKiem.addActionListener(this);
		mniPCNV.addActionListener(this);
		mniChamCongNV.addActionListener(this);
		mniTinhLuong.addActionListener(this);
		mniThongKe.addActionListener(this);
		mniCaiDat.addActionListener(this);
		
		mnSanPham.addMouseListener(this);
		mnCongDoan.addMouseListener(this);
		mnCongNhan.addMouseListener(this);
		mnNhanVien.addMouseListener(this);
		
	}
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
		pnlTitle.setLayout(new BoxLayout(pnlTitle, BoxLayout.X_AXIS));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageScaler("/image/logo_v3_color-no-bgr-342e993c.png", 48, 48).getScaledImageIcon());
		lblLogo.setBorder(new RoundedBorder(Color.decode(read_file_themes.getString("color_main_sw")), 5, 2));
		pnlTitle.add(lblLogo);
		
		Component horizontalBox = Box.createHorizontalStrut(20);
		pnlTitle.add(horizontalBox);
		
		JLabel lblHeader = new JLabel(read_file_languages.getString("text_heading_title_menu"));
		lblHeader.setFont(main.roboto_bold.deriveFont(Font.BOLD, 26F));
		lblHeader.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		pnlTitle.add(lblHeader);
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(Color.decode(read_file_themes.getString("color_main")));
		pnlMenu.setLayout(new BorderLayout());
		pnlHead.add(pnlMenu,BorderLayout.SOUTH);
		
		JMenuBar mnuBar = new JMenuBar();
		mnuBar.setLayout(new GridLayout(1, 9));
		mnuBar.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mnuBar.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
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
		mnSanPham.getPopupMenu().setPopupSize(new Dimension(168, 75));
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
		
		mnCongDoan = new JMenu(read_file_languages.getString("text_stage"));
		mnCongDoan.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		mnCongDoan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnCongDoan.getPopupMenu().setPopupSize(new Dimension(168, 113));
		mnCongDoan.getPopupMenu().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#424242")));
		mnuBar.add(mnCongDoan);
		
		mniQLCD = new JMenuItem(read_file_languages.getString("text_manage"));
		mniQLCD.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniQLCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnCongDoan.add(mniQLCD);
		
		mniPCCD = new JMenuItem(read_file_languages.getString("text_assignment"));
		mniPCCD.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniPCCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnCongDoan.add(mniPCCD);
		
		mniTimKiemCD = new JMenuItem(read_file_languages.getString("text_search"));
		mniTimKiemCD.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 16F));
		mniTimKiemCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnCongDoan.add(mniTimKiemCD);
		
		mnCongNhan = new JMenu(read_file_languages.getString("text_worder"));
		mnCongNhan.setFont(this.main.roboto_regular.deriveFont(Font.BOLD, 20F));
		mnCongNhan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(read_file_themes.getString("color_main_sw"))), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		mnCongNhan.getPopupMenu().setPopupSize(new Dimension(168, 150));
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
		mnNhanVien.getPopupMenu().setPopupSize(new Dimension(168, 188));
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
		if(o==mniTrangChu) {
			setMenuColorDefault();
			mniTrangChu.setBackground(Color.decode("#424242"));
			mniTrangChu.setForeground(Color.WHITE);
			mniTrangChu.setIcon(new ImageScaler("/image/home_icon(1).png", 24, 24).getScaledImageIcon());
		}
		if(o==mniPhongBan) {
			setMenuColorDefault();
			mniPhongBan.setBackground(Color.decode("#424242"));
			mniPhongBan.setForeground(Color.WHITE);
			mniPhongBan.setIcon(new ImageScaler("/image/door-open_icon(1).png", 24, 24).getScaledImageIcon());
		}
		if(o==mniHopDong) {
			setMenuColorDefault();
			mniHopDong.setBackground(Color.decode("#424242"));
			mniHopDong.setForeground(Color.WHITE);
			mniHopDong.setIcon(new ImageScaler("/image/contract_icon(1).png", 24, 24).getScaledImageIcon());
			
			main.add(new HopDongUI(main), BorderLayout.CENTER);
			main.validate();
		}
		if(o==mnSanPham) {
			
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
		
		mnCongDoan.setIcon(new ImageScaler("/image/timeline_icon.png", 24, 24).getScaledImageIcon());
		mnCongDoan.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mnCongDoan.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
		mniTimKiemCD.setIcon(new ImageScaler("/image/magnifying-glass_icon.png", 24, 24).getScaledImageIcon());
		mniTimKiemCD.setBackground(Color.decode(read_file_themes.getString("color_main")));
		mniTimKiemCD.setForeground(Color.decode(read_file_themes.getString("color_main_sw")));
		
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
		
		UIManager.put("Menu.selectionBackground", Color.decode(read_file_themes.getString("color_main")));
		UIManager.put("Menu.selectionForeground", Color.decode(read_file_themes.getString("color_main_sw")));
		
//		UIManager.put("MenuItem.selectionBackground", Color.decode("#ffe6cc"));
	}
}
