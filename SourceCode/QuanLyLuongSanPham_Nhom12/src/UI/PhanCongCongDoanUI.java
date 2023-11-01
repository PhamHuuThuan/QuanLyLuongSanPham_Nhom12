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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
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
	private RoundedButton btnPhanCong, btnCapNhatPC, btnXoaPC, btnLuu, btnHuy, btnFocus;
	private DefaultTableModel dtblModelCDPC, dtblModelCD, dtblModelSP;
	private JTable tblCDPC, tblCD, tblSP;
	private JTableHeader tbhCDPC, tbhCD, tbhSP;
	private JTextField textField;
	private JTextField txtMaCD;
	private JTextField txtTenSP;
	private JTextField textField_3;
	private JTextField txtTenCD;
	private JTextField textField_1;

	private JFrame mainFrame;

	private JButton btnGetSP;
	private JTextField textField_4;
	private JTextField txtMaSP;

	/**
	 * Create the panel.
	 */
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
		pnlNorth.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnlNorth.setBackground(bgColor);
		pnlNhanVien.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BorderLayout(0, 0));

		// Tao jpanel Title
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
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
								"Danh s\u00E1ch C\u00F4ng nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null,
								new Color(0, 0, 0)),
						new EmptyBorder(10, 0, 0, 0)));
		pnlBangNV.setLayout(new BorderLayout());
		pnlBangNV.setBackground(bgColor);
		pnlBangNV.setPreferredSize(new Dimension(700, 300));
		b.add(pnlBangNV);
		b.add(Box.createHorizontalStrut(10));

		String cols[] = { "Mã CN", "Tên CN", "Ngày sinh", "Ngày vào làm" };
		dtblModelCD = new DefaultTableModel(cols, 0);
		tblCD = new JTable(dtblModelCD);

		tbhCD = new JTableHeader(tblCD.getColumnModel());
		tbhCD.setReorderingAllowed(false);
		tbhCD.setBackground(componentColor);
		tbhCD.setForeground(Color.WHITE);
		tbhCD.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblCD.setTableHeader(tbhCD);

		tblCD.setRowHeight(20);
		tblCD.getColumnModel().getColumn(0).setPreferredWidth(75);
		tblCD.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblCD.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblCD.getColumnModel().getColumn(3).setPreferredWidth(100);

		// Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrNV = new JScrollPane(tblCD, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNV.add(scrNV, BorderLayout.CENTER);

		// Tao jpanel Thong tin nhan vien
		JPanel pnThongTinNV = new JPanel();
		pnThongTinNV.setLayout(new BorderLayout());
		pnThongTinNV.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), ("Thông tin phân công"));
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnThongTinNV.setBorder(
				new CompoundBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(66, 66, 66)), "Th\u00F4ng tin ph\u00E2n c\u00F4ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 5, 10, 5)));
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

		textField = new JTextField();
		horizontalBox.add(textField);
		textField.setColumns(10);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_3);

		JLabel lblMaPhanCong = new JLabel("Mã Phân Công");
		horizontalBox.add(lblMaPhanCong);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_4);

		textField_3 = new JTextField();
		horizontalBox.add(textField_3);
		textField_3.setColumns(10);

		Component verticalStrut = Box.createVerticalStrut(20);
		pnlThongTinPhanCong.add(verticalStrut);

		Box horizontalBox_1 = Box.createHorizontalBox();
		pnlThongTinPhanCong.add(horizontalBox_1);

		JLabel lblMaCD = new JLabel("Mã CD");
		horizontalBox_1.add(lblMaCD);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_1);

		txtMaCD = new JTextField();
		horizontalBox_1.add(txtMaCD);
		txtMaCD.setColumns(10);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_5);

		JLabel lblTenCD = new JLabel("Tên CD");
		horizontalBox_1.add(lblTenCD);

		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_6);

		txtTenCD = new JTextField();
		horizontalBox_1.add(txtTenCD);
		txtTenCD.setColumns(10);

		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_7);

		btnGetSP = new JButton("Lấy Công Đoạn");
		horizontalBox_1.add(btnGetSP);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnlThongTinPhanCong.add(verticalStrut_1);

		Box horizontalBox_2 = Box.createHorizontalBox();
		pnlThongTinPhanCong.add(horizontalBox_2);

		JLabel lblTenSP = new JLabel("Tên SP");
		horizontalBox_2.add(lblTenSP);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_2);

		txtTenSP = new JTextField();
		horizontalBox_2.add(txtTenSP);
		txtTenSP.setColumns(10);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_10);
		
		JLabel lblMaSP = new JLabel("Mã SP");
		horizontalBox_2.add(lblMaSP);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_11);
		
		txtMaSP = new JTextField();
		horizontalBox_2.add(txtMaSP);
		txtMaSP.setColumns(10);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		pnlThongTinPhanCong.add(verticalStrut_2);

		Box horizontalBox_3 = Box.createHorizontalBox();
		pnlThongTinPhanCong.add(horizontalBox_3);

		JLabel lblNewLabel_1 = new JLabel("New label");
		horizontalBox_3.add(lblNewLabel_1);

		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_8);

		textField_1 = new JTextField();
		horizontalBox_3.add(textField_1);
		textField_1.setColumns(10);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_9);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		horizontalBox_3.add(lblNewLabel_2);
		
		textField_4 = new JTextField();
		horizontalBox_3.add(textField_4);
		textField_4.setColumns(10);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
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
		btnPhanCong.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlChucNang.add(btnPhanCong);
		pnlChucNang.add(Box.createHorizontalStrut(20));

		btnCapNhatPC = new RoundedButton("Sửa", null, 20, 0, 1.0f);
		btnCapNhatPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnCapNhatPC.setForeground(Color.WHITE);
		btnCapNhatPC.setBackground(Color.decode("#ffc107"));
		btnCapNhatPC.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnCapNhatPC.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlChucNang.add(btnCapNhatPC);
		pnlChucNang.add(Box.createHorizontalStrut(20));

		btnXoaPC = new RoundedButton("Xóa", null, 20, 0, 1.0f);
		btnXoaPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnXoaPC.setForeground(Color.WHITE);
		btnXoaPC.setBackground(Color.decode("#dc3545"));
		btnXoaPC.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoaPC.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlChucNang.add(btnXoaPC);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(30);
		pnlChucNang.add(horizontalStrut_12);
		
		
		btnLuu = new RoundedButton("Lưu", null, 20, 0, 0.6f);
		btnLuu.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setBackground(Color.decode("#28a745"));
		btnLuu.setIcon(new ImageScaler("/image/save_Icon.png", 24, 24).getScaledImageIcon());
		btnLuu.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlChucNang.add(btnLuu);
		pnlChucNang.add(Box.createHorizontalStrut(20));

		btnHuy = new RoundedButton("Hủy", null, 20, 0, 0.6f);
		btnHuy.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBackground(Color.decode("#ffc107"));
		btnHuy.setIcon(new ImageScaler("/image/cancelHD_Icon.png", 24, 24).getScaledImageIcon());
		btnHuy.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlChucNang.add(btnHuy);

		// tạo jpanel chứa table phân công công đoạn
		JPanel pnlBangNVPC = new JPanel();
		TitledBorder titleBorderTTNVPC = BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Danh sách phân công công đoạn");
		titleBorderTTNVPC.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNVPC.setBorder(
				new CompoundBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(66, 66, 66)),
						"Danh s\u00E1ch ph\u00E2n c\u00F4ng c\u00F4ng \u0111o\u1EA1n", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 0, 10, 0)));
		pnlBangNVPC.setLayout(new BoxLayout(pnlBangNVPC, BoxLayout.X_AXIS));
		pnlBangNVPC.setBackground(bgColor);
		pnlNhanVien.add(pnlBangNVPC, BorderLayout.CENTER);

		String colsPCNV[] = { "STT", "Mã PC", "Mã CN", "Tên CN", "Mã SP", "Tên SP", "Mã CD", "Tên CD", "Ngày PC",
				"Số Lượng Làm" };
		dtblModelCDPC = new DefaultTableModel(colsPCNV, 0);
		tblCDPC = new JTable(dtblModelCDPC);

		tbhCDPC = new JTableHeader(tblCDPC.getColumnModel());
		tbhCDPC.setReorderingAllowed(false);
		tbhCDPC.setBackground(componentColor);
		tbhCDPC.setForeground(Color.WHITE);
		tbhCDPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblCDPC.setTableHeader(tbhCDPC);
		tblCDPC.setRowHeight(20);
		tblCDPC.getColumnModel().getColumn(0).setPreferredWidth(45);
		tblCDPC.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblCDPC.getColumnModel().getColumn(2).setPreferredWidth(80);
		tblCDPC.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(80);
		tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(80);
		tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(110);
		tblCDPC.getColumnModel().getColumn(4).setPreferredWidth(90);

		// Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblCDPC, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNVPC.add(scrSP);

		pnlNorth.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

		btnPhanCong.addActionListener(this);
		btnCapNhatPC.addActionListener(this);
		btnGetSP.addActionListener(this);

		btnPhanCong.addMouseListener(this);
		btnCapNhatPC.addMouseListener(this);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		main.music.playSE(2);

		if (o == btnGetSP) {
			showJDialogSP();
		}

	}

	private void showJDialogSP() {
		JDialog listSp = new JDialog(mainFrame, "Danh sách Công đoạn", JDialog.ModalityType.APPLICATION_MODAL);
		listSp.setSize(1200, 500);
		listSp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		listSp.setLocationRelativeTo(null);

	
		String cols_cd[] = {"Mã CĐ", "Tên CĐ", "Thứ tự","Mã SP","Tên SP","Đơn giá", "Số lượng", "Tình trạng", "Ngày ht", "Ghi chú"};
		dtblModelCD = new DefaultTableModel(cols_cd, 0);
		tblCD = new JTable(dtblModelCD);

		 tbhCD = new JTableHeader(tblCD.getColumnModel());
		    tbhCD.setReorderingAllowed(false);
		    tbhCD.setBackground(componentColor);
		    tbhCD.setForeground(Color.WHITE);
		    tbhCD.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));

		    tblCD.setTableHeader(tbhCD);
		    tblCD.setRowHeight(20);
		    tblCD.getColumnModel().getColumn(0).setPreferredWidth(70);
		    tblCD.getColumnModel().getColumn(1).setPreferredWidth(100);
		    tblCD.getColumnModel().getColumn(2).setPreferredWidth(50);
		    tblCD.getColumnModel().getColumn(3).setPreferredWidth(90);
		    tblCD.getColumnModel().getColumn(4).setPreferredWidth(90);
		    tblCD.getColumnModel().getColumn(5).setPreferredWidth(90);
		    tblCD.getColumnModel().getColumn(6).setPreferredWidth(70);
		    tblCD.getColumnModel().getColumn(7).setPreferredWidth(60);
		    tblCD.getColumnModel().getColumn(8).setPreferredWidth(70);
		    tblCD.getColumnModel().getColumn(9).setPreferredWidth(90);

		    JScrollPane scrCD = new JScrollPane(tblCD, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		    JButton btnGetProduct = new JButton("Lấy Thông tin công đoạn");
		    btnGetProduct.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		           
		            listSp.dispose();
		        }
		    });

		    listSp.getContentPane().setLayout(new BorderLayout());
		    listSp.getContentPane().add(scrCD, BorderLayout.CENTER);
		    listSp.getContentPane().add(btnGetProduct, BorderLayout.SOUTH);



		listSp.setVisible(true);

	}

}
