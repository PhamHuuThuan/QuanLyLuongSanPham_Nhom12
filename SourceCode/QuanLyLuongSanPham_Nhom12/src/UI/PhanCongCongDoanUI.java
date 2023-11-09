package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.CustomComboBoxUI;
import CustomUI.CustomListCellRenderer;
import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Dao.PhanCongCongDoan_Dao;
import Entity.BangPhanCongCongDoan;
import Entity.CongDoan;
import Entity.CongNhan;
import Util.SinhMaTuDong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;

public class PhanCongCongDoanUI extends JPanel implements ActionListener, MouseListener {

	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private RoundedButton btnPhanCong, btnCapNhatPC, btnXoaPC, btnLuu, btnHuy;
	private DefaultTableModel dtblModelPCCD, dtblModelCD, dtblModelSP, dtblModelCNCPC;
	private JTable tblCDPC, tblCD, tblSP, tblCNCPC;
	private JTableHeader tbhCDPC, tbhCD, tbhSP, tbhCNCPC;
	private JTextField txtMaCD;
	private JTextField txtMaSP;
	private JTextField txtMaPCCD;
	private JTextField txtTenCD;
	private JTextField txtSoLuongLam;

	private JFrame mainFrame;

	private JButton btnGetSP;
	private JTextField txtTenSp;

	private JXDatePicker dpNgayPhanCong;

	private PhanCongCongDoan_Dao pccd_dao = new PhanCongCongDoan_Dao();
	private ArrayList<CongDoan> dsCD = new ArrayList<>();

	private PhanCongCongDoan_Dao cncpc_dao = new PhanCongCongDoan_Dao();
	private ArrayList<CongNhan> dsCNcpc = new ArrayList<>();

	private PhanCongCongDoan_Dao pccd_main_dao = new PhanCongCongDoan_Dao();
	private ArrayList<BangPhanCongCongDoan> dspccd = new ArrayList<>();

	private int selectedRowIndex = -1;
	private JTextField txtMaCN;
	private JTextField txtTenCN;
	private JTextField txtGhiChu;

	private boolean isThemPCCD = false;
	private JTextField txtTongSoLuong;
	private JButton btnGetProduct;
	private JTextField txtSLChuaPC;

	public PhanCongCongDoanUI(MainUI main) {
		this.main = main;

		// set gia tri cho jpanel SanPham
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(bgColor);

		JPanel pnlNhanVien = new JPanel();
		add(pnlNhanVien, BorderLayout.CENTER);
		pnlNhanVien.setLayout(new BorderLayout(0, 0));
		pnlNhanVien.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlNhanVien.setBackground(bgColor);

		// tao jpanel chua Title va Thong tin cong doan
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(new EmptyBorder(0, 0, 10, 0));
		pnlNorth.setBackground(bgColor);
		pnlNhanVien.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BorderLayout(0, 0));

		// Tao jpanel Title
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
		pnlTitle.setBackground(bgColor);
		pnlNorth.add(pnlTitle, BorderLayout.NORTH);

		// Tiêu đề
		JLabel lblTitle = new JLabel("PHÂN CÔNG CÔNG ĐOẠN");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle.add(lblTitle);

		// Tạo hộp thoại phân công
		Box b = Box.createHorizontalBox();
		b.setBackground(bgColor);
		pnlNorth.add(b, BorderLayout.CENTER);

		// tạo jpanel chứa table cong doan
		JPanel pnlBangNV = new JPanel();
		TitledBorder titleBorderTTNV = BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), ("Danh sách chưa phân công"));
		titleBorderTTNV.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNV
				.setBorder(new CompoundBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"Danh s\u00E1ch C\u00F4ng nh\u00E2n ch\u01B0a ph\u00E2n c\u00F4ng",
								TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
						new EmptyBorder(10, 0, 0, 0)));
		pnlBangNV.setLayout(new BorderLayout());
		pnlBangNV.setBackground(bgColor);
		pnlBangNV.setPreferredSize(new Dimension(700, 300));
		b.add(pnlBangNV);
		b.add(Box.createHorizontalStrut(10));

		String cols[] = { "STT", "Mã CN", "Tên CN", "Ngày sinh", "Ngày vào làm" };
		dtblModelCNCPC = new DefaultTableModel(cols, 0);
		tblCNCPC = new JTable(dtblModelCNCPC);

		tbhCNCPC = new JTableHeader(tblCNCPC.getColumnModel());
		tbhCNCPC.setReorderingAllowed(false);
		tbhCNCPC.setBackground(componentColor);
		tbhCNCPC.setForeground(Color.WHITE);
		tbhCNCPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblCNCPC.setTableHeader(tbhCNCPC);

		tblCNCPC.setRowHeight(25);
		tblCNCPC.getColumnModel().getColumn(0).setPreferredWidth(45);
		tblCNCPC.getColumnModel().getColumn(1).setPreferredWidth(75);
		tblCNCPC.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblCNCPC.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblCNCPC.getColumnModel().getColumn(4).setPreferredWidth(100);

		getDataLenTable();
		
		tblCNCPC.setEnabled(false);

		// Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrCNCPC = new JScrollPane(tblCNCPC, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNV.add(scrCNCPC, BorderLayout.CENTER);

		// Tao jpanel Thong tin nhan vien
		JPanel pnThongTinNV = new JPanel();
		pnThongTinNV.setLayout(new BorderLayout());
		pnThongTinNV.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), ("Thông tin phân công"));
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnThongTinNV.setBorder(new CompoundBorder(new TitledBorder(
				new MatteBorder(1, 1, 1, 1, (Color) new Color(66, 66, 66)), "Th\u00F4ng tin ph\u00E2n c\u00F4ng",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 5, 10, 5)));
		pnThongTinNV.setPreferredSize(new Dimension(700, 300));
		b.add(pnThongTinNV);

		JPanel pnlTTRight = new JPanel();
		pnlTTRight.setLayout(new BoxLayout(pnlTTRight, BoxLayout.Y_AXIS));
		pnlTTRight.setBackground(bgColor);
		pnThongTinNV.add(pnlTTRight, BorderLayout.CENTER);

		JPanel pnlThongTinPhanCong = new JPanel();
		pnlThongTinPhanCong.setBackground(new Color(255, 255, 255));
		pnlThongTinPhanCong.setBorder(new EmptyBorder(0, 10, 0, 10));
		pnlTTRight.add(pnlThongTinPhanCong);
		pnlThongTinPhanCong.setLayout(new BoxLayout(pnlThongTinPhanCong, BoxLayout.Y_AXIS));

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlThongTinPhanCong.add(horizontalBox);

		JLabel lblNgayPhanCong = new JLabel("Ngày Phân Công");
		horizontalBox.add(lblNgayPhanCong);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);

		dpNgayPhanCong = new JXDatePicker(new Date());
		dpNgayPhanCong.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dpNgayPhanCong.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dpNgayPhanCong.setLocale(new Locale("vi", "VN"));
		horizontalBox.add(dpNgayPhanCong);

		dpNgayPhanCong.setEnabled(false);
		dpNgayPhanCong.setEnabled(false);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_3);

		JLabel lblMaPhanCong = new JLabel("Mã Phân Công");
		horizontalBox.add(lblMaPhanCong);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_4);

		txtMaPCCD = new JTextField();
		txtMaPCCD.setEditable(false);
		horizontalBox.add(txtMaPCCD);
		txtMaPCCD.setColumns(10);

		Component verticalStrut_4 = Box.createVerticalStrut(10);
		pnlThongTinPhanCong.add(verticalStrut_4);

		Box horizontalBox_4 = Box.createHorizontalBox();
		pnlThongTinPhanCong.add(horizontalBox_4);

		JLabel lblMaCN = new JLabel("Mã CN");
		horizontalBox_4.add(lblMaCN);

		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_13);

		txtMaCN = new JTextField();
		txtMaCN.setEditable(false);
		horizontalBox_4.add(txtMaCN);
		txtMaCN.setColumns(10);

		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_14);

		JLabel lblTenCN = new JLabel("Tên CN");
		horizontalBox_4.add(lblTenCN);

		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_15);

		txtTenCN = new JTextField();
		txtTenCN.setEditable(false);
		horizontalBox_4.add(txtTenCN);
		txtTenCN.setColumns(10);

		Component verticalStrut = Box.createVerticalStrut(10);
		pnlThongTinPhanCong.add(verticalStrut);

		Box horizontalBox_1 = Box.createHorizontalBox();
		pnlThongTinPhanCong.add(horizontalBox_1);

		JLabel lblMaCD = new JLabel("Mã CD");
		horizontalBox_1.add(lblMaCD);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_1);

		txtMaCD = new JTextField();
		txtMaCD.setEditable(false);
		horizontalBox_1.add(txtMaCD);
		txtMaCD.setColumns(10);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_5);

		JLabel lblTenCD = new JLabel("Tên CD");
		horizontalBox_1.add(lblTenCD);

		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_6);

		txtTenCD = new JTextField();
		txtTenCD.setEditable(false);
		horizontalBox_1.add(txtTenCD);
		txtTenCD.setColumns(10);

		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_7);

		btnGetSP = new JButton("Lấy Công Đoạn");
		horizontalBox_1.add(btnGetSP);

		Component verticalStrut_1 = Box.createVerticalStrut(10);
		pnlThongTinPhanCong.add(verticalStrut_1);

		Box horizontalBox_2 = Box.createHorizontalBox();
		pnlThongTinPhanCong.add(horizontalBox_2);

		JLabel lblMaSP = new JLabel("Mã SP");
		horizontalBox_2.add(lblMaSP);

		Component horizontalStrut_2 = Box.createHorizontalStrut(15);
		horizontalBox_2.add(horizontalStrut_2);

		txtMaSP = new JTextField();
		txtMaSP.setEditable(false);
		horizontalBox_2.add(txtMaSP);
		txtMaSP.setColumns(10);

		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_10);

		JLabel lblTenSP = new JLabel("Tên SP");
		horizontalBox_2.add(lblTenSP);

		Component horizontalStrut_11 = Box.createHorizontalStrut(15);
		horizontalBox_2.add(horizontalStrut_11);

		txtTenSp = new JTextField();
		txtTenSp.setEditable(false);
		horizontalBox_2.add(txtTenSp);
		txtTenSp.setColumns(10);
		
		Component horizontalStrut_19 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_19);
		
		JLabel lblSLChuaPC = new JLabel("SL chưa PC");
		horizontalBox_2.add(lblSLChuaPC);
		
		Component horizontalStrut_20 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_20);
		
		txtSLChuaPC = new JTextField();
		txtSLChuaPC.setEditable(false);
		horizontalBox_2.add(txtSLChuaPC);
		txtSLChuaPC.setColumns(10);

		Component horizontalStrut_17 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_17);

		JLabel lblTongSoLuong = new JLabel("Tổng Số Lượng");
		horizontalBox_2.add(lblTongSoLuong);

		Component horizontalStrut_18 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_18);

		txtTongSoLuong = new JTextField();
		txtTongSoLuong.setEditable(false);
		horizontalBox_2.add(txtTongSoLuong);
		txtTongSoLuong.setColumns(10);

		Component verticalStrut_2 = Box.createVerticalStrut(10);
		pnlThongTinPhanCong.add(verticalStrut_2);

		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setBorder(new CompoundBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(192, 192, 192)),
				new EmptyBorder(10, 0, 0, 0)));
		pnlThongTinPhanCong.add(horizontalBox_3);

		JLabel lblSoLuongLam = new JLabel("Số lượng làm");
		horizontalBox_3.add(lblSoLuongLam);

		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_8);

		txtSoLuongLam = new JTextField("0");
		horizontalBox_3.add(txtSoLuongLam);
		txtSoLuongLam.setColumns(10);

		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_9);

		JLabel lblGhiChu = new JLabel("Ghi Chú");
		horizontalBox_3.add(lblGhiChu);

		Component horizontalStrut_16 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_16);

		txtGhiChu = new JTextField();
		horizontalBox_3.add(txtGhiChu);
		txtGhiChu.setColumns(10);

		Component verticalStrut_3 = Box.createVerticalStrut(10);
		pnlThongTinPhanCong.add(verticalStrut_3);

		// Khởi tạo jpanel chức năng chứa các button chức năng
		FlowLayout fl_pnlChucNang = new FlowLayout();
		fl_pnlChucNang.setHgap(0);
		JPanel pnlChucNang = new JPanel(fl_pnlChucNang);
		pnlChucNang.setBackground(bgColor);
		pnlTTRight.add(pnlChucNang);

		btnPhanCong = new RoundedButton("Phân công", null, 20, 0, 1.0f);
		btnPhanCong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnPhanCong.setForeground(Color.WHITE);
		btnPhanCong.setBackground(Color.decode("#3B71CA"));
		btnPhanCong.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnPhanCong.setBorder(new EmptyBorder(5, 10, 5, 10));
		pnlChucNang.add(btnPhanCong);
		pnlChucNang.add(Box.createHorizontalStrut(20));

		btnCapNhatPC = new RoundedButton("Sửa", null, 20, 0, 1.0f);
		btnCapNhatPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnCapNhatPC.setForeground(Color.WHITE);
		btnCapNhatPC.setBackground(Color.decode("#ffc107"));
		btnCapNhatPC.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnCapNhatPC.setBorder(new EmptyBorder(5, 10, 5, 10));
		pnlChucNang.add(btnCapNhatPC);
		pnlChucNang.add(Box.createHorizontalStrut(20));

		btnXoaPC = new RoundedButton("Xóa", null, 20, 0, 1.0f);
		btnXoaPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnXoaPC.setForeground(Color.WHITE);
		btnXoaPC.setBackground(Color.decode("#dc3545"));
		btnXoaPC.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoaPC.setBorder(new EmptyBorder(5, 10, 5, 10));
		pnlChucNang.add(btnXoaPC);

		Component horizontalStrut_12 = Box.createHorizontalStrut(30);
		pnlChucNang.add(horizontalStrut_12);

		btnLuu = new RoundedButton("Lưu", null, 20, 0, 0.6f);
		btnLuu.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setBackground(Color.decode("#28a745"));
		btnLuu.setIcon(new ImageScaler("/image/save_Icon.png", 24, 24).getScaledImageIcon());
		btnLuu.setBorder(new EmptyBorder(5, 10, 5, 10));
		pnlChucNang.add(btnLuu);
		pnlChucNang.add(Box.createHorizontalStrut(20));

		btnHuy = new RoundedButton("Hủy", null, 20, 0, 0.6f);
		btnHuy.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBackground(Color.decode("#ffc107"));
		btnHuy.setIcon(new ImageScaler("/image/cancelHD_Icon.png", 24, 24).getScaledImageIcon());
		btnHuy.setBorder(new EmptyBorder(5, 10, 5, 10));
		pnlChucNang.add(btnHuy);

		// tạo jpanel chứa table phân công công đoạn
		JPanel pnlBangNVPC = new JPanel();
		TitledBorder titleBorderTTNVPC = BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Danh sách phân công công đoạn");
		titleBorderTTNVPC.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNVPC.setBorder(
				new CompoundBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(66, 66, 66)),
						"Danh s\u00E1ch ph\u00E2n c\u00F4ng c\u00F4ng \u0111o\u1EA1n", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 0, 3, 0)));
		pnlBangNVPC.setLayout(new BoxLayout(pnlBangNVPC, BoxLayout.X_AXIS));
		pnlBangNVPC.setBackground(bgColor);
		pnlNhanVien.add(pnlBangNVPC, BorderLayout.CENTER);

		String colsPCNV[] = { "STT", "Mã PC", "Mã CN", "Tên CN", "Mã CD", "Tên CD", "Mã SP", "Tên SP", "Ngày PC",
				"Số Lượng CLam", "Ghi chú" };
		dtblModelPCCD = new DefaultTableModel(colsPCNV, 0);
		tblCDPC = new JTable(dtblModelPCCD);

		tbhCDPC = new JTableHeader(tblCDPC.getColumnModel());
		tbhCDPC.setReorderingAllowed(false);
		tbhCDPC.setBackground(componentColor);
		tbhCDPC.setForeground(Color.WHITE);
		tbhCDPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));

		tblCDPC.setTableHeader(tbhCDPC);
		tblCDPC.setRowHeight(20);
		tblCDPC.getColumnModel().getColumn(0).setPreferredWidth(10);
		tblCDPC.getColumnModel().getColumn(1).setPreferredWidth(70);
		tblCDPC.getColumnModel().getColumn(2).setPreferredWidth(60);
		tblCDPC.getColumnModel().getColumn(3).setPreferredWidth(80);
		tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(50);
		tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(80);
		tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(60);
		tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(80);
		tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(90);
		tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(70);
		tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(110);

		getDataPCCDLenBang();

		// Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblCDPC, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNVPC.add(scrSP);

		pnlNorth.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

		txtMaPCCD.setText(new SinhMaTuDong().sinhMaPCCD());

		btnPhanCong.addActionListener(this);
		btnCapNhatPC.addActionListener(this);
		btnXoaPC.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLuu.addActionListener(this);

		btnGetSP.addActionListener(this);

		tblCNCPC.addMouseListener(this);
		tblCDPC.addMouseListener(this);

		displayButtonSaveAndCancel(false);
		setEditableForTextField(false);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
		if (o == tblCNCPC) {
			int index = tblCNCPC.getSelectedRow();
			if (index != -1) {
				hienThiThongTinCongNhan(index);
				main.music.playSE(2);
			}
		}
		if (o == tblCDPC) {
			int index = tblCDPC.getSelectedRow();
			if (index != -1) {
				main.music.playSE(2);
				hienThithongTinPCCD(index);
				displayButtonSaveAndCancel(false);
				setEditableForTextField(false);
				tblCNCPC.clearSelection();
				tblCNCPC.setEnabled(false);
			}
		}

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
		main.music.playSE(2);

		if (o == btnPhanCong) {
			isThemPCCD = true;

			displayButtonSaveAndCancel(true);
			setEditableForTextField(true);
			xoaRong();
			txtMaPCCD.setText(new SinhMaTuDong().sinhMaPCCD());
			tblCDPC.clearSelection();
			tblCNCPC.setEnabled(true);
		}
		if (o == btnCapNhatPC) {
			isThemPCCD = false;
			if (tblCDPC.getSelectedRow() != -1) {
				displayButtonSaveAndCancel(true);
				setEditableForTextField(true);
				tblCNCPC.setEnabled(true);
			} else {
				alertNotification("Cần chọn 1 Phân Công để cập nhật!");
			}

		}
		if (o == btnXoaPC) {
			if (tblCDPC.getSelectedRow() != -1) {
				xoaPCCD();
				displayButtonSaveAndCancel(false);
				setEditableForTextField(false);
			} else {
				alertNotification("Cần chọn 1 Phân Công để xóa!");
			}

		}

		if (o == btnLuu) {
			if (isThemPCCD == true) {
				themPCCD();
			} else {
				capNhatPCCD();
			}

		}
		if (o == btnHuy) {
			displayButtonSaveAndCancel(false);
			setEditableForTextField(false);
			xoaRong();
		}

		if (o == btnGetSP) {
			showJDialogSP();
		}

	}

	// HÀM DISPLAY BUTTON SAVE VÀ CANCEL
	private void displayButtonSaveAndCancel(boolean display) {
		if (display == true) {
			btnLuu.setEnabled(true);
			btnLuu.setAlpha(1f);
			btnHuy.setEnabled(true);
			btnHuy.setAlpha(1f);

			btnPhanCong.setEnabled(false);
			btnPhanCong.setAlpha(0.6f);
			btnCapNhatPC.setEnabled(false);
			btnCapNhatPC.setAlpha(0.6f);
			btnXoaPC.setEnabled(false);
			btnXoaPC.setAlpha(0.6f);

		} else {
			btnLuu.setEnabled(false);
			btnLuu.setAlpha(0.6f);
			btnHuy.setEnabled(false);
			btnHuy.setAlpha(0.6f);

			btnPhanCong.setEnabled(true);
			btnPhanCong.setAlpha(1f);
			btnCapNhatPC.setEnabled(true);
			btnCapNhatPC.setAlpha(1f);
			btnXoaPC.setEnabled(true);
			btnXoaPC.setAlpha(1f);
		}
	}

	// HÀM SET EDIT CÁC JTEXT
	private void setEditableForTextField(boolean edit) {
		if (edit == true) {
			txtSoLuongLam.setEditable(true);
			txtGhiChu.setEditable(true);
			btnGetSP.setEnabled(true);
		} else {
			txtSoLuongLam.setEditable(false);
			txtGhiChu.setEditable(false);
			btnGetSP.setEnabled(false);
		}
	}

	// HÀM MỞ MODAL LẤY DANH SÁCH CÔNG ĐOẠN
	private void showJDialogSP() {
		JDialog listCD = new JDialog(mainFrame, "Danh sách Công đoạn chưa hoàn thành",
				JDialog.ModalityType.APPLICATION_MODAL);
		listCD.setSize(1200, 500);
		listCD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		listCD.setLocationRelativeTo(null);

		String cols_cd[] = { "STT", "Mã CĐ", "Tên CĐ", "Thứ tự", "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Tình trạng",
				"Ngày HT" };
		dtblModelCD = new DefaultTableModel(cols_cd, 0);
		tblCD = new JTable(dtblModelCD);

		tbhCD = new JTableHeader(tblCD.getColumnModel());
		tbhCD.setReorderingAllowed(false);
		tbhCD.setBackground(componentColor);
		tbhCD.setForeground(Color.WHITE);
		tbhCD.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));

		tblCD.setTableHeader(tbhCD);
		tblCD.setRowHeight(30);
		tblCD.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblCD.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblCD.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblCD.getColumnModel().getColumn(3).setPreferredWidth(40);
		tblCD.getColumnModel().getColumn(4).setPreferredWidth(80);
		tblCD.getColumnModel().getColumn(5).setPreferredWidth(100);
		tblCD.getColumnModel().getColumn(6).setPreferredWidth(90);
		tblCD.getColumnModel().getColumn(7).setPreferredWidth(30);
		tblCD.getColumnModel().getColumn(8).setPreferredWidth(90);
		tblCD.getColumnModel().getColumn(9).setPreferredWidth(90);

		JScrollPane scrCD = new JScrollPane(tblCD, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		getDataCongDoanLenTable();

		tblCD.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					main.music.playSE(2);
					selectedRowIndex = tblCD.getSelectedRow();
					btnGetProduct.setEnabled(true);
				}
			}
		});
		// BUTTON LẤY THÔNG TIN CÔNG ĐOẠN
		btnGetProduct = new JButton("Lấy Thông tin công đoạn");
		btnGetProduct.setEnabled(false);
		btnGetProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.music.playSE(2);
				if (selectedRowIndex != -1) {
					
					String maCD = dtblModelCD.getValueAt(selectedRowIndex, 1).toString();
					String tenCD = dtblModelCD.getValueAt(selectedRowIndex, 2).toString();
					String maSP = dtblModelCD.getValueAt(selectedRowIndex, 4).toString();
					String tenSP = dtblModelCD.getValueAt(selectedRowIndex, 5).toString();
					String tongSL = dtblModelCD.getValueAt(selectedRowIndex, 7).toString();

					txtMaCD.setText(maCD);
					txtTenCD.setText(tenCD);
					txtMaSP.setText(maSP);
					txtTenSp.setText(tenSP);
					txtTongSoLuong.setText(tongSL);

				}
				listCD.dispose();
			}
		});

		listCD.getContentPane().setLayout(new BorderLayout());
		listCD.getContentPane().add(scrCD, BorderLayout.CENTER);
		listCD.getContentPane().add(btnGetProduct, BorderLayout.SOUTH);

		listCD.setVisible(true);

	}

	// HÀM XÓA RỖNG
	public void xoaRong() {
		txtMaCD.setText("");
		txtTenCD.setText("");
		txtMaCN.setText("");
		txtTenCN.setText("");
		txtMaSP.setText("");
		txtTenSp.setText("");
		txtSoLuongLam.setText("0");
		txtGhiChu.setText("");
	}

	// HÀM LẤY DỮ LIỆU CÔNG ĐOẠN LÊN BẢNG
	private void getDataCongDoanLenTable() {
		dsCD = pccd_dao.getAllCongDoan();
		themAllCongDoanVaoBang(dsCD);
	}

	// THÊM TẤT CẢ CÔNG ĐOẠN LÊN BẢNG
	private void themAllCongDoanVaoBang(ArrayList<CongDoan> listCD) {
		dtblModelCD.setRowCount(0);
		for (CongDoan cd : listCD) {
			themCongDoanVaoBang(cd);
		}
	}

	// THÊM CÔNG ĐOẠN VÀO BẢNG TỪNG CỘT
	private void themCongDoanVaoBang(CongDoan cd) {
		String[] row = new String[15];
		row[0] = String.valueOf(dtblModelCD.getRowCount() + 1);
		row[1] = cd.getMaCD();
		row[2] = cd.getTenCD();
		row[3] = String.valueOf(Integer.valueOf(cd.getThuTu()));
		row[4] = cd.getSanPham().getMaSP();
		row[5] = cd.getSanPham().getTenSP();
		row[6] = String.valueOf(Double.valueOf(cd.getDonGia()));
		row[7] = String.valueOf(Integer.valueOf(cd.getSoLuong()));
		row[8] = cd.isTinhTrang() ? "Đã HT" : "Chưa HT";
		row[9] = new SimpleDateFormat("dd-MM-yyyy").format(cd.getNgayHoanThanh());
		dtblModelCD.addRow(row);
	}

	/* CÁC HÀM LẤY CÔNG NHÂN CẦN PHÂN CÔNG */

	// HÀM LẤY DỮ LIỆU CÔNG NHÂN LÊN BẢNG
	public void getDataLenTable() {
		dsCNcpc = cncpc_dao.getAllCongNhanChuaPhanCong();
		themAllCongNhanVaoBang(dsCNcpc);
	}

	// HÀM THÊM ALL CÔNG NHÂN VÀO BẢNG
	public void themAllCongNhanVaoBang(ArrayList<CongNhan> listCN) {
		dtblModelCNCPC.setRowCount(0);
		for (CongNhan cn : listCN) {
			themCongNhanVaoBang(cn);
		}
	}

	// HÀM THÊM CÔNG NHÂN VÀO BẢNG TỪNG CỘT
	public void themCongNhanVaoBang(CongNhan cn) {
		String[] row = new String[99];
		row[0] = String.valueOf(dtblModelCNCPC.getRowCount() + 1);
		row[1] = cn.getMaCN();
		row[2] = cn.getHoTen();
		row[3] = new SimpleDateFormat("dd-MM-yyyy").format(cn.getNgaySinh());
		row[4] = new SimpleDateFormat("dd-MM-yyyy").format(cn.getNgayVaoLam());
		dtblModelCNCPC.addRow(row);
	}

	// HÀM HIỂN THỊ THÔNG TIN CÔNG NHÂN
	public void hienThiThongTinCongNhan(int index) {
		txtMaCN.setText(dsCNcpc.get(index).getMaCN());
		txtTenCN.setText(dsCNcpc.get(index).getHoTen());
	}

	/* CÁC HÀM LẤY PHÂN CÔNG CÔNG ĐOẠN */

	// HÀM LẤY DỮ LIỆU LÊN BẢNG
	public void getDataPCCDLenBang() {
		dspccd = pccd_main_dao.getAllPhanCongCongDoan();
		themAllPCCDVaoBang(dspccd);
	}

	// HÀM THÊM TẤT CẢ PCCD VÀO BẢNG
	public void themAllPCCDVaoBang(ArrayList<BangPhanCongCongDoan> listPCCD) {
		dtblModelPCCD.setRowCount(0);
		for (BangPhanCongCongDoan pccd : listPCCD) {
			themPhanCongCongDoanVaoBang(pccd);
		}
	}

	// HÀM THÊM PHÂN CÔNG CÔNG ĐOẠN VÀO BẢNG TỪNG CỘT
	public void themPhanCongCongDoanVaoBang(BangPhanCongCongDoan pccd) {
		String[] row = new String[99];
		row[0] = String.valueOf(dtblModelPCCD.getRowCount() + 1);
		row[1] = pccd.getMaPhanCong();
		row[2] = pccd.getCongNhan().getMaCN();
		row[3] = pccd.getCongNhan().getHoTen();
		row[4] = pccd.getCongDoan().getMaCD();
		row[5] = pccd.getCongDoan().getTenCD();
		row[6] = pccd.getSanPham().getMaSP();
		row[7] = pccd.getSanPham().getTenSP();
		row[8] = new SimpleDateFormat("dd-MM-yyyy").format(pccd.getNgayPhanCong());
		row[9] = String.valueOf(Integer.valueOf(pccd.getSoLuongCanLam()));
		row[10] = pccd.getGhiChu();

		dtblModelPCCD.addRow(row);
	}

	// HÀM HIỂN THỊ THÔNG TIN ĐÃ PHÂN CÔNG
	public void hienThithongTinPCCD(int index) {
		dpNgayPhanCong.setDate(dspccd.get(index).getNgayPhanCong());
		txtMaPCCD.setText(dspccd.get(index).getMaPhanCong());
		txtMaCN.setText(dspccd.get(index).getCongNhan().getMaCN());
		txtTenCN.setText(dspccd.get(index).getCongNhan().getHoTen());
		txtMaCD.setText(dspccd.get(index).getCongDoan().getMaCD());
		txtTenCD.setText(dspccd.get(index).getCongDoan().getTenCD());
		txtMaSP.setText(dspccd.get(index).getSanPham().getMaSP());
		txtTenSp.setText(dspccd.get(index).getSanPham().getTenSP());
		
		
		String tongSL = String.valueOf(dspccd.get(index).getCongDoan().getSoLuong());
		txtTongSoLuong.setText(tongSL);

		txtSoLuongLam.setText(String.valueOf(dspccd.get(index).getSoLuongCanLam()));
		txtGhiChu.setText(dspccd.get(index).getGhiChu());
	}

	/* CÁC HÀM PHÂN CÔNG CÔNG ĐOẠN */

	// HÀM CONVERT DATA
	public BangPhanCongCongDoan convertDataPCCD() {
		String maPC = txtMaPCCD.getText();

		String maCNString = txtMaCN.getText();
		CongNhan CN = new CongNhan(maCNString);

		String maCDString = txtMaCD.getText();
		CongDoan CD = new CongDoan(maCDString);

		Date ngayPC = dpNgayPhanCong.getDate();
		Integer soLuongLam = Integer.parseInt(txtSoLuongLam.getText());
		String ghiChu = txtGhiChu.getText();

		return new BangPhanCongCongDoan(maPC, CN, CD, ngayPC, soLuongLam, ghiChu);

	}

	// HÀM THÊM PHÂN CÔNG CÔNG ĐOẠN
	public void themPCCD() {
		if (validPCCD() == true) {
			BangPhanCongCongDoan pccdNew = convertDataPCCD();
			if (pccdNew != null) {
				if (pccd_main_dao.themPCCD(pccdNew)) {
					getDataPCCDLenBang();
					getDataLenTable();
					alertSuccess("Phân công thành công");
					txtMaPCCD.setText(new SinhMaTuDong().sinhMaPCCD());
					xoaRong();
				} else {
					alertNotification("Phân công thất bại! Do đã tồn tại mã PC");
				}
			} else {
				alertNotification("Lỗi đã xảy ra");
			}
		}
	}

	// HÀM VALID THÊM PHÂN CÔNG
	public boolean validPCCD() {

		if (txtMaCD.getText().isEmpty() || txtTenCD.getText().isEmpty()) {
			alertNotification("Vui lòng ấn  Lấy Công đoạn  để chọn công đoạn");
			return false;
		}
		if (txtMaCN.getText().isEmpty() || txtTenCN.getText().isEmpty()) {
			alertNotification("Vui lòng chọn Công Nhân cần phân công");
			return false;
		}

		String soLuongLam = txtSoLuongLam.getText();
		try {
			int sll = Integer.parseInt(soLuongLam);
			if (sll > Integer.parseInt(txtTongSoLuong.getText())) {
				alertNotification("Số lượng làm phải nhỏ hơn hoặc bằng Tổng số lượng công đoạn");
				return false;
			} else if (sll <= 0) {
				alertNotification("Số lượng làm phải lớn hơn 0");
				return false;

			}
		} catch (Exception e) {
			alertNotification("Số lượng làm phải là số nguyên");
			return false;
		}

		return true;
	}

	// HÀM CẬP NHẬT PHÂN CÔNG CÔNG ĐOẠN
	public void capNhatPCCD() {
		if (validPCCD() == true) {
			BangPhanCongCongDoan pccdNew = convertDataPCCD();
			if (pccdNew != null) {
				if (pccd_dao.suaPCCD(pccdNew)) {
					alertSuccess("Cập nhật thành công");
					getDataPCCDLenBang();
				} else {
					alertNotification("Cập nhật thất bại do Phân công không tồn tại");
				}
			}
		}
	}

	// HÀM XOÁ PHÂN CÔNG CÔNG ĐOẠN
	public void xoaPCCD() {
		String maPhanCong = txtMaPCCD.getText();
		if (maPhanCong != null) {
			String message = String.format("Quyết định xóa Phân công có mã %s", maPhanCong);
			main.music.playSE(3);
			int result = JOptionPane.showConfirmDialog(this, message, "NOTIFICATION", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				if (pccd_dao.xoaPCCD(maPhanCong)) {
					xoaRong();
					getDataPCCDLenBang();
					getDataLenTable();
					alertSuccess("Xóa thành công");
				} else {
					alertNotification("Xóa không thành công! Không tìm thấy phân công cần xóa");
				}
			}
		}
	}

	// ALERT

	public int alertNotification(String textError) {
		main.music.playSE(3);
		String[] options = { "Cancel" };
		int result = JOptionPane.showOptionDialog(main, textError, "NOTIFICATION", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		return result;
	}

	public int alertSuccess(String textError) {
		main.music.playSE(1);
		String[] options = { "Cancel" };
		int result = JOptionPane.showOptionDialog(main, textError, "NOTIFICATION", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		return result;
	}

}
