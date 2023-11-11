package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Dao.ChamCongCongNhan_Dao;
import Entity.BangChamCongCongNhan;
import Entity.BangPhanCongCongDoan;
import Entity.CongNhan;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class ChamCongCongNhan_UI extends JPanel implements ActionListener, MouseListener {
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;

	private JTextField txtMaSP_dt, txtMaCN_dt, txtTenCongNhan_dt, txtTenSanPham;
	private JTextField txtSoLuongLam_dt, txtGhiChu_dt, txtTenCongDoan;
	private JComboBox<String> cmbTrangThai;
	private JXDatePicker dtbNgayChamCong;

	private DefaultTableModel dtblModelCN, dtblModelCNCC, dtblModelSP;
	private JTable tblDSCC, tblCN, tblSP;
	private JTableHeader tbhCN, tbhCNCC, tbhSP;
	private RoundedButton btnXuat, btnCham, btnSua, btnXoa, btnLuu, btnHuy;
	private JTextField txtMaCD_dt;
	private JTextField txtMaCD;
	private JTextField txtMaSP;

	private JSpinner spnGioDen;

	private JFrame mainFrame;
	private RoundedButton btnGetSP, btnChamTatCa, btnGetOutSP;

	private int selectedRowIndex = -1;

	private ChamCongCongNhan_Dao cccn_dao = new ChamCongCongNhan_Dao();
	private ArrayList<BangPhanCongCongDoan> dspccd_sp = new ArrayList<>();
	private ArrayList<BangChamCongCongNhan> dscccn = new ArrayList<>();

	private ArrayList<CongNhan> dspccd_cn = new ArrayList<>();

	private String valueMaPhanCong;
	private Integer soLuongDaPhan;
	private Boolean isLuu = false;

	public ChamCongCongNhan_UI(MainUI main) {
		this.main = main;
		setLayout(new BorderLayout(0, 0));

		JPanel pnlChamCongCN = new JPanel();
		pnlChamCongCN.setBackground(new Color(255, 255, 255));
		add(pnlChamCongCN, BorderLayout.CENTER);
		pnlChamCongCN.setLayout(new BorderLayout(0, 0));

		JPanel pnlBody = new JPanel();
		pnlChamCongCN.add(pnlBody, BorderLayout.NORTH);
		pnlBody.setLayout(new BorderLayout(0, 0));

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnlTitle.setBackground(new Color(255, 255, 255));
		pnlBody.add(pnlTitle, BorderLayout.NORTH);
		pnlTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTitle = new JLabel("CHẤM CÔNG CÔNG NHÂN");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTitle.add(lblTitle);

		JPanel pnlChamCong = new JPanel();
		pnlChamCong.setBackground(new Color(255, 255, 255));
		pnlChamCong.setBorder(new EmptyBorder(0, 5, 0, 5));
		pnlBody.add(pnlChamCong, BorderLayout.SOUTH);
		pnlChamCong.setLayout(new BorderLayout(0, 0));

		JPanel pnlCCSelectAll = new JPanel();
		pnlCCSelectAll.setBackground(new Color(255, 255, 255));
		pnlCCSelectAll
				.setBorder(new CompoundBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"Danh s\u00E1ch C\u00F4ng Nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null,
								new Color(0, 0, 0)),
						new EmptyBorder(10, 7, 0, 7)));
		pnlChamCong.add(pnlCCSelectAll, BorderLayout.CENTER);
		pnlCCSelectAll.setLayout(new BorderLayout(0, 0));
		pnlCCSelectAll.setPreferredSize(new Dimension(200, 300));

		JPanel pnlCcAll = new JPanel();
		pnlCcAll.setBackground(new Color(255, 255, 255));
		pnlCCSelectAll.add(pnlCcAll, BorderLayout.NORTH);
		pnlCcAll.setLayout(new BoxLayout(pnlCcAll, BoxLayout.Y_AXIS));

		Box horizontalBox = Box.createHorizontalBox();
		pnlCcAll.add(horizontalBox);

		JLabel lblNgayChamCong = new JLabel("Ngày Chấm");
		horizontalBox.add(lblNgayChamCong);

		Component horizontalStrut_7 = Box.createHorizontalStrut(10);
		horizontalBox.add(horizontalStrut_7);

		dtbNgayChamCong = new JXDatePicker(new Date());
		dtbNgayChamCong.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtbNgayChamCong.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dtbNgayChamCong.setLocale(new Locale("vi", "VN"));
		dtbNgayChamCong.getMonthView().setUpperBound(new Date());
		horizontalBox.add(dtbNgayChamCong);

		Component horizontalStrut_8 = Box.createHorizontalStrut(15);
		horizontalBox.add(horizontalStrut_8);

		JLabel lblMaSanPham = new JLabel("Mã SP");
		horizontalBox.add(lblMaSanPham);

		Component horizontalStrut_13 = Box.createHorizontalStrut(10);
		horizontalBox.add(horizontalStrut_13);

		txtMaSP = new JTextField();
		txtMaSP.setEditable(false);
		horizontalBox.add(txtMaSP);
		txtMaSP.setColumns(10);

		Component horizontalStrut_14 = Box.createHorizontalStrut(15);
		horizontalBox.add(horizontalStrut_14);

		JLabel lblTenSanPham = new JLabel("Tên SP");
		horizontalBox.add(lblTenSanPham);

		Component horizontalStrut_18 = Box.createHorizontalStrut(10);
		horizontalBox.add(horizontalStrut_18);

		txtTenSanPham = new JTextField("");
		txtTenSanPham.setEditable(false);
		horizontalBox.add(txtTenSanPham);
		txtTenSanPham.setColumns(10);

		Component horizontalStrut_21 = Box.createHorizontalStrut(15);
		horizontalBox.add(horizontalStrut_21);

		btnGetSP = new RoundedButton("Lấy sản phẩm", null, 10, 0, 1.0f);
		btnGetSP.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnGetSP.setForeground(Color.WHITE);
		btnGetSP.setBackground(Color.decode("#424242"));
		btnGetSP.setIcon(new ImageScaler("/image/icon_add_cd.png", 24, 24).getScaledImageIcon());
		btnGetSP.setBorder(new EmptyBorder(5, 10, 5, 10));
		horizontalBox.add(btnGetSP);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		pnlCcAll.add(verticalStrut_3);

		Box box_ChamCong = Box.createHorizontalBox();
		box_ChamCong.setBackground(new Color(255, 255, 255));
		box_ChamCong.setBorder(new EmptyBorder(0, 0, 10, 0));
		pnlCcAll.add(box_ChamCong);

		JLabel lblChonMaCongDoan = new JLabel("Mã công Đoạn");
		box_ChamCong.add(lblChonMaCongDoan);

		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		box_ChamCong.add(horizontalStrut_9);

		txtMaCD = new JTextField();
		txtMaCD.setEditable(false);
		box_ChamCong.add(txtMaCD);
		txtMaCD.setColumns(10);

		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		box_ChamCong.add(horizontalStrut_10);

		JLabel lblTenCongDoan = new JLabel("Tên công đoạn");
		box_ChamCong.add(lblTenCongDoan);

		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		box_ChamCong.add(horizontalStrut_11);

		txtTenCongDoan = new JTextField("");
		txtTenCongDoan.setEditable(false);
		box_ChamCong.add(txtTenCongDoan);
		txtTenCongDoan.setColumns(10);

		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		box_ChamCong.add(horizontalStrut_12);

		
		btnChamTatCa = new RoundedButton("Chấm tất cả", null, 10, 0, 1.0f);
		btnChamTatCa.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnChamTatCa.setForeground(Color.WHITE);
		btnChamTatCa.setBackground(Color.decode("#424242"));
		btnChamTatCa.setIcon(new ImageScaler("/image/icon_get_all.png", 24, 24).getScaledImageIcon());
		btnChamTatCa.setBorder(new EmptyBorder(5, 10, 5, 10));
		
		btnChamTatCa.setEnabled(false);
		box_ChamCong.add(btnChamTatCa);

		String cols[] = { "STT", "Mã CN", "Họ tên", "Ngày sinh", "SL đã phân" };
		dtblModelCN = new DefaultTableModel(cols, 0);

		tblCN = new JTable(dtblModelCN);

		tbhCN = new JTableHeader(tblCN.getColumnModel());
		tbhCN.setReorderingAllowed(false);
		tbhCN.setBackground(componentColor);
		tbhCN.setForeground(Color.WHITE);
		tbhCN.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblCN.setTableHeader(tbhCN);

		tblCN.setRowHeight(30);
		tblCN.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblCN.getColumnModel().getColumn(1).setPreferredWidth(120);
		tblCN.getColumnModel().getColumn(2).setPreferredWidth(150);
		tblCN.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblCN.getColumnModel().getColumn(4).setPreferredWidth(80);

		JScrollPane scrNV = new JScrollPane(tblCN, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrNV.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));

		pnlCCSelectAll.add(scrNV, BorderLayout.CENTER);

		JPanel pnlCCDetail = new JPanel();
		pnlCCDetail.setBackground(new Color(255, 255, 255));
		pnlCCDetail
				.setBorder(new CompoundBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"Th\u00F4ng tin ch\u1EA5m c\u00F4ng", TitledBorder.LEADING, TitledBorder.TOP, null,
								new Color(0, 0, 0)),
						new EmptyBorder(20, 10, 0, 10)));
		pnlChamCong.add(pnlCCDetail, BorderLayout.EAST);
		pnlCCDetail.setLayout(new BoxLayout(pnlCCDetail, BoxLayout.Y_AXIS));

		Box box_r0 = Box.createHorizontalBox();
		pnlCCDetail.add(box_r0);

		JLabel lblMaCN = new JLabel("Mã CN");
		box_r0.add(lblMaCN);

		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		box_r0.add(horizontalStrut_15);

		txtMaCN_dt = new JTextField();
		txtMaCN_dt.setEditable(false);
		box_r0.add(txtMaCN_dt);
		txtMaCN_dt.setColumns(10);

		Component horizontalStrut_16 = Box.createHorizontalStrut(20);
		box_r0.add(horizontalStrut_16);

		JLabel lblTenCN = new JLabel("Tên CN");
		box_r0.add(lblTenCN);

		Component horizontalStrut_17 = Box.createHorizontalStrut(20);
		box_r0.add(horizontalStrut_17);

		txtTenCongNhan_dt = new JTextField();
		txtTenCongNhan_dt.setEditable(false);
		box_r0.add(txtTenCongNhan_dt);
		txtTenCongNhan_dt.setColumns(10);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		pnlCCDetail.add(verticalStrut_2);

		Box box_r1 = Box.createHorizontalBox();
		pnlCCDetail.add(box_r1);

		JLabel lblTTSanPham = new JLabel("Mã SP");
		box_r1.add(lblTTSanPham);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		box_r1.add(horizontalStrut);

		txtMaSP_dt = new JTextField();
		txtMaSP_dt.setEditable(false);
		box_r1.add(txtMaSP_dt);
		txtMaSP_dt.setColumns(10);

		Component horizontalStrut_19 = Box.createHorizontalStrut(20);
		box_r1.add(horizontalStrut_19);

		JLabel lblTTMaCongDoan = new JLabel("Mã CĐ");
		box_r1.add(lblTTMaCongDoan);

		Component horizontalStrut_20 = Box.createHorizontalStrut(20);
		box_r1.add(horizontalStrut_20);

		txtMaCD_dt = new JTextField();
		txtMaCD_dt.setEditable(false);
		box_r1.add(txtMaCD_dt);
		txtMaCD_dt.setColumns(10);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		box_r1.add(horizontalStrut_1);

		JLabel lblTrangThai = new JLabel("Trạng thái");
		box_r1.add(lblTrangThai);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		box_r1.add(horizontalStrut_2);

		cmbTrangThai = new JComboBox<>();
		cmbTrangThai.addItem("Làm đầy đủ");
		cmbTrangThai.addItem("Nghỉ phép");
		cmbTrangThai.addItem("Nghỉ");
		box_r1.add(cmbTrangThai);

		Component verticalStrut = Box.createVerticalStrut(20);
		pnlCCDetail.add(verticalStrut);

		Box horizontalBox_1 = Box.createHorizontalBox();
		pnlCCDetail.add(horizontalBox_1);

		JLabel lblGioDen = new JLabel("Giờ Vào Làm");
		horizontalBox_1.add(lblGioDen);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_3);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 0);

		spnGioDen = new JSpinner(new SpinnerDateModel(cal.getTime(), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spnGioDen, "hh' : 'mm ");
		spnGioDen.setEditor(timeEditor);
		horizontalBox_1.add(spnGioDen);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_4);

		JLabel lblSoLuongLam = new JLabel("Số lượng làm được");
		horizontalBox_1.add(lblSoLuongLam);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_5);

		txtSoLuongLam_dt = new JTextField();
		horizontalBox_1.add(txtSoLuongLam_dt);
		txtSoLuongLam_dt.setColumns(10);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnlCCDetail.add(verticalStrut_1);

		Box horizontalBox_2 = Box.createHorizontalBox();
		pnlCCDetail.add(horizontalBox_2);

		JLabel lblGhiChu = new JLabel("Ghi chú");
		horizontalBox_2.add(lblGhiChu);

		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_6);

		txtGhiChu_dt = new JTextField();
		horizontalBox_2.add(txtGhiChu_dt);
		txtGhiChu_dt.setColumns(10);

		JPanel pnlControl = new JPanel();
		pnlControl.setBackground(new Color(255, 255, 255));
		pnlControl.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnlCCDetail.add(pnlControl);

		btnCham = new RoundedButton("Chấm công", null, 20, 0, 1.0f);
		btnCham.setText("Chấm công");
		btnCham.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnCham.setForeground(Color.WHITE);
		btnCham.setBackground(Color.decode("#3B71CA"));
		btnCham.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnCham.setBorder(new EmptyBorder(5, 10, 5, 10));
		pnlControl.add(btnCham);
		pnlControl.add(Box.createHorizontalStrut(20));

		btnSua = new RoundedButton("Cập Nhật", null, 20, 0, 1.0f);
		btnSua.setText("Sửa");
		btnSua.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnSua.setForeground(Color.WHITE);
		btnSua.setBackground(Color.decode("#ffc107"));
		btnSua.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnSua.setBorder(new EmptyBorder(5, 10, 5, 10));
		pnlControl.add(btnSua);
		pnlControl.add(Box.createHorizontalStrut(20));

		btnXoa = new RoundedButton("Xóa", null, 20, 0, 1.0f);
		btnXoa.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(Color.decode("#dc3545"));
		btnXoa.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoa.setBorder(new EmptyBorder(5, 10, 5, 10));
		pnlControl.add(btnXoa);

		Component horizontalStrut_22 = Box.createHorizontalStrut(50);
		pnlControl.add(horizontalStrut_22);

		btnLuu = new RoundedButton("Lưu", null, 20, 0, 0.6f);
		btnLuu.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setBackground(Color.decode("#28a745"));
		btnLuu.setIcon(new ImageScaler("/image/save_Icon.png", 24, 24).getScaledImageIcon());
		btnLuu.setBorder(new EmptyBorder(5, 10, 5, 10));
		pnlControl.add(btnLuu);
		pnlControl.add(Box.createHorizontalStrut(20));

		btnHuy = new RoundedButton("Hủy", null, 20, 0, 0.6f);
		btnHuy.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBackground(Color.decode("#ffc107"));
		btnHuy.setIcon(new ImageScaler("/image/cancelHD_Icon.png", 24, 24).getScaledImageIcon());
		btnHuy.setBorder(new EmptyBorder(5, 10, 5, 10));
		pnlControl.add(btnHuy);

		JPanel pnlBangChamCong = new JPanel();
		pnlBangChamCong.setBackground(new Color(255, 255, 255));
		pnlBangChamCong
				.setBorder(
						new CompoundBorder(
								new TitledBorder(
										new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
												new Color(160, 160, 160)),
										"Danh s\u00E1ch C\u00F4ng nh\u00E2n \u0111\u00E3 ch\u1EA5m",
										TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
								new EmptyBorder(0, 8, 0, 8)));
		pnlChamCongCN.add(pnlBangChamCong, BorderLayout.CENTER);
		pnlBangChamCong.setLayout(new BorderLayout(0, 0));

		JPanel pnlXuat = new JPanel();
		pnlXuat.setBackground(bgColor);
		pnlXuat.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlBangChamCong.add(pnlXuat, BorderLayout.NORTH);

		btnXuat = new RoundedButton("Xuất DS", null, 5, 0, 1.0f);
		btnXuat.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setBackground(Color.decode("#28a745"));
		btnXuat.setIcon(new ImageScaler("/image/printer_icon.png", 20, 20).getScaledImageIcon());
		btnXuat.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		pnlXuat.add(btnXuat);

		String colsPCNV[] = { "STT", "Mã CN", "Tên CN", "Mã SP", "Tên SP", "Mã CĐ", "Tên CĐ", "Ngày Chấm", "Trạng thái",
				"Giờ vào làm", "SL đã làm", "Ghi chú" };
		dtblModelCNCC = new DefaultTableModel(colsPCNV, 0);
		tblDSCC = new JTable(dtblModelCNCC);

		tbhCNCC = new JTableHeader(tblDSCC.getColumnModel());
		tbhCNCC.setReorderingAllowed(false);
		tbhCNCC.setBackground(componentColor);
		tbhCNCC.setForeground(Color.WHITE);
		tbhCNCC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblDSCC.setTableHeader(tbhCNCC);

		tblDSCC.setRowHeight(30);
		tblDSCC.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblDSCC.getColumnModel().getColumn(1).setPreferredWidth(60);
		tblDSCC.getColumnModel().getColumn(2).setPreferredWidth(80);
		tblDSCC.getColumnModel().getColumn(3).setPreferredWidth(70);
		tblDSCC.getColumnModel().getColumn(4).setPreferredWidth(80);
		tblDSCC.getColumnModel().getColumn(5).setPreferredWidth(60);
		tblDSCC.getColumnModel().getColumn(6).setPreferredWidth(70);
		tblDSCC.getColumnModel().getColumn(7).setPreferredWidth(90);
		tblDSCC.getColumnModel().getColumn(8).setPreferredWidth(80);
		tblDSCC.getColumnModel().getColumn(9).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(10).setPreferredWidth(60);
		tblDSCC.getColumnModel().getColumn(11).setPreferredWidth(90);

		getDataCCCNLenBang();

		// Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblDSCC, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangChamCong.add(scrSP);

		btnGetSP.addActionListener(this);
		btnChamTatCa.addActionListener(this);

		btnCham.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);

		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);

		cmbTrangThai.addActionListener(this);

		tblCN.addMouseListener(this);
		tblDSCC.addMouseListener(this);

		displayButtonSaveAndCancel(false);
		setEditableForTextField(false);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		main.music.playSE(2);

		if (o == tblCN) {
			int index = tblCN.getSelectedRow();
			if (index != -1) {
				hienthiThongTinPC(index);
			}
		}
		if (o == tblDSCC) {
			int index_2 = tblDSCC.getSelectedRow();
			if (index_2 != -1) {
				hienThiCCCNChiTiet(index_2);
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

		if (o == btnGetSP) {
			showJDialogSP();
		}
		if (o == btnCham) {
			isLuu = true;
			displayButtonSaveAndCancel(true);
			setEditableForTextField(true);
		}
		if (o == btnSua) {
			isLuu = false;
		}
		if (o == btnXoa) {

		}
		if (o == btnLuu) {
			if (isLuu) {
				chamCongCongNhan();
			}

		}
		if (o == btnHuy) {
			displayButtonSaveAndCancel(false);
			setEditableForTextField(false);
		}
		if (o == cmbTrangThai) {
			String selectTT = cmbTrangThai.getSelectedItem().toString();
			if (selectTT.equals("Nghỉ phép") || selectTT.equals("Nghỉ")) {
				txtSoLuongLam_dt.setText("0");
				txtSoLuongLam_dt.setEditable(false);
				spnGioDen.setEnabled(false);
			} else {
				txtSoLuongLam_dt.setText("");
				txtSoLuongLam_dt.setEditable(true);
				spnGioDen.setEnabled(true);
			}
		}

	}

	// HÀM DISPLAY BUTTON SAVE VÀ CANCEL
	private void displayButtonSaveAndCancel(boolean display) {
		if (display == true) {
			btnLuu.setEnabled(true);
			btnLuu.setAlpha(1f);
			btnHuy.setEnabled(true);
			btnHuy.setAlpha(1f);

			btnCham.setEnabled(false);
			btnCham.setAlpha(0.6f);
			btnSua.setEnabled(false);
			btnSua.setAlpha(0.6f);
			btnXoa.setEnabled(false);
			btnXoa.setAlpha(0.6f);

		} else {
			btnLuu.setEnabled(false);
			btnLuu.setAlpha(0.6f);
			btnHuy.setEnabled(false);
			btnHuy.setAlpha(0.6f);

			btnCham.setEnabled(true);
			btnCham.setAlpha(1f);
			btnSua.setEnabled(true);
			btnSua.setAlpha(1f);
			btnXoa.setEnabled(true);
			btnXoa.setAlpha(1f);
		}
	}

	// HÀM SET EDIT CÁC JTEXT
	private void setEditableForTextField(boolean edit) {
		if (edit == true) {
			spnGioDen.setEnabled(true);
			txtSoLuongLam_dt.setEditable(true);
			txtGhiChu_dt.setEditable(true);
			cmbTrangThai.setEnabled(true);

			spnGioDen.setEnabled(true);

		} else {
			spnGioDen.setEnabled(false);
			txtSoLuongLam_dt.setEditable(false);
			txtGhiChu_dt.setEditable(false);
			cmbTrangThai.setEnabled(false);
			spnGioDen.setEnabled(false);
		}
	}

	private void showJDialogSP() {
		JDialog listSP = new JDialog(mainFrame, "DANH SÁCH SẢN PHẨM ĐÃ ĐƯỢC PHÂN CÔNG",
				JDialog.ModalityType.APPLICATION_MODAL);
		listSP.setSize(800, 500);
		listSP.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		listSP.setLocationRelativeTo(null);

		String cols_cd[] = { "STT", "Mã SP", "Tên SP", "Mã CĐ", "Tên CĐ", "Thứ tự" };
		dtblModelSP = new DefaultTableModel(cols_cd, 0);
		tblSP = new JTable(dtblModelSP);

		tbhSP = new JTableHeader(tblSP.getColumnModel());
		tbhSP.setReorderingAllowed(false);
		tbhSP.setBackground(componentColor);
		tbhSP.setForeground(Color.WHITE);
		tbhSP.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));

		tblSP.setTableHeader(tbhSP);
		tblSP.setRowHeight(30);
		tblSP.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblSP.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblSP.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblSP.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblSP.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblSP.getColumnModel().getColumn(5).setPreferredWidth(100);

		getDataSPLenBang();

		JScrollPane scrCD = new JScrollPane(tblSP, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		tblSP.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					main.music.playSE(2);
					selectedRowIndex = tblSP.getSelectedRow();
					btnGetOutSP.setEnabled(true);

				}
			}
		});
		// BUTTON LẤY THÔNG TIN SẢN PHẨM
		
		btnGetOutSP = new RoundedButton("Lấy Thông tin Sản phẩm", null, 10, 0, 1.0f);
		btnGetOutSP.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnGetOutSP.setForeground(Color.WHITE);
		btnGetOutSP.setBackground(Color.decode("#424242"));
		btnGetOutSP.setIcon(new ImageScaler("/image/icon_add_cd.png", 24, 24).getScaledImageIcon());
		btnGetOutSP.setBorder(new EmptyBorder(5, 10, 5, 10));
		
		btnGetOutSP.setEnabled(false);
		
		btnGetOutSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.music.playSE(2);
				btnChamTatCa.setEnabled(true);
				if (selectedRowIndex != -1) {

					String maSP = dspccd_sp.get(selectedRowIndex).getSanPham().getMaSP();
					String tenSP = dspccd_sp.get(selectedRowIndex).getSanPham().getTenSP();
					String maCD = dspccd_sp.get(selectedRowIndex).getCongDoan().getMaCD();
					String tenCD = dspccd_sp.get(selectedRowIndex).getCongDoan().getTenCD();

					valueMaPhanCong = dspccd_sp.get(selectedRowIndex).getMaPhanCong();

					txtMaSP.setText(maSP);
					txtTenSanPham.setText(tenSP);
					txtMaCD.setText(maCD);
					txtTenCongDoan.setText(tenCD);

					getDataCNLenBang();

				}
				listSP.dispose();
			}
		});

		listSP.getContentPane().setLayout(new BorderLayout());
		listSP.getContentPane().add(scrCD, BorderLayout.CENTER);
		listSP.getContentPane().add(btnGetOutSP, BorderLayout.SOUTH);

		listSP.setVisible(true);

	}

	// HÀM LẤY DỮ LIỆU SP LÊN BẢNG
	public void getDataSPLenBang() {
		dspccd_sp = cccn_dao.getALLSanPham();
		themAllSPVaoBang(dspccd_sp);
	}

	// HÀM THÊM TẤT CẢ SP LÊN BẢNG
	public void themAllSPVaoBang(ArrayList<BangPhanCongCongDoan> listSP) {
		dtblModelSP.setRowCount(0);
		for (BangPhanCongCongDoan pccd_sp : listSP) {
			themSPVaoBang(pccd_sp);
		}
	}

	// HÀM THÊM SẢN PHẨM VÀO TỪNG CỘT
	public void themSPVaoBang(BangPhanCongCongDoan pccd_sp) {
		String[] row = new String[50];
		row[0] = String.valueOf(dtblModelSP.getRowCount() + 1);
		row[1] = pccd_sp.getSanPham().getMaSP();
		row[2] = pccd_sp.getSanPham().getTenSP();
		row[3] = pccd_sp.getCongDoan().getMaCD();
		row[4] = pccd_sp.getCongDoan().getTenCD();
		row[5] = String.valueOf(Integer.valueOf(pccd_sp.getCongDoan().getThuTu()));

		dtblModelSP.addRow(row);
	}

	/* HÀM LẤY DATA CÔNG NHÂN */
	// HÀM LẤY DỮ LIỆU CN LÊN BẢNG
	public void getDataCNLenBang() {
		String maSP = txtMaSP.getText();
		String maCD = txtMaCD.getText();

		dspccd_cn = cccn_dao.getALLCongNhan(maSP, maCD);
		themAllCNVaoBang(dspccd_cn);
	}

	// HÀM THÊM TẤT CẢ CN LÊN BẢNG
	public void themAllCNVaoBang(ArrayList<CongNhan> listCN) {
		dtblModelCN.setRowCount(0);
		for (CongNhan pccd_cn : listCN) {
			themCNVaoBang(pccd_cn);
		}
	}

	// HÀM THÊM SẢN PHẨM VÀO TỪNG CỘT
	public void themCNVaoBang(CongNhan pccd_cn) {
		String[] row = new String[50];
		row[0] = String.valueOf(dtblModelCN.getRowCount() + 1);
		row[1] = pccd_cn.getMaCN();
		row[2] = pccd_cn.getHoTen();
		row[3] = new SimpleDateFormat("dd-MM-yyy").format(pccd_cn.getNgaySinh());
		row[4] = String.valueOf(Integer.valueOf(pccd_cn.getPhanCongCongDoan().getSoLuongCanLam()));

		dtblModelCN.addRow(row);
	}

	public void hienthiThongTinPC(int index) {
		txtMaCN_dt.setText(dspccd_cn.get(index).getMaCN());
		txtTenCongNhan_dt.setText(dspccd_cn.get(index).getHoTen());

		soLuongDaPhan = dspccd_cn.get(index).getPhanCongCongDoan().getSoLuongCanLam();

		txtMaSP_dt.setText(dspccd_sp.get(index).getSanPham().getMaSP());
		txtMaCD_dt.setText(dspccd_sp.get(index).getCongDoan().getMaCD());

	}

	/* HÀM CHÂM CÔNG CÔNG NHÂN */
	// HÀM CONVERT DATA CHAM CONG CONG NHAN
	public BangChamCongCongNhan convertDataCCCN() {
		Date ngayChamCong = dtbNgayChamCong.getDate();

		SimpleDateFormat format = new SimpleDateFormat("HH:mm ");
		String gioVaoLam = format.format(spnGioDen.getValue());

		BangPhanCongCongDoan pc = new BangPhanCongCongDoan(valueMaPhanCong);
		Integer soLuongLam = Integer.parseInt(txtSoLuongLam_dt.getText());
		String ghiChu = txtGhiChu_dt.getText();

		Integer trangThai = cmbTrangThai.getSelectedIndex();

		return new BangChamCongCongNhan(ngayChamCong, gioVaoLam, pc, soLuongLam, ghiChu, trangThai);
	}

	// HÀM VALID CCCN
	public boolean validCCCN() {
		if (txtMaCN_dt.getText().isEmpty() || txtTenCongNhan_dt.getText().isEmpty()) {
			alertNotification("Phải chọn thông tin Công Nhân cần chấm (bên trái)");
			return false;
		}
		String soLuongLamDuoc = txtSoLuongLam_dt.getText();
		try {
			int slld = Integer.parseInt(soLuongLamDuoc);
			if (slld > Integer.valueOf(soLuongDaPhan)) {
				alertNotification("Số lượng làm được phải nhỏ hơn số lượng đã phân");
				return false;
			} else if (slld < 0) {
				alertNotification("Số lượng làm được phải lớn hơn hoặc bằng 0");
				return false;
			}
		} catch (Exception e) {
			alertNotification("Số lượng làm được phải là số nguyên");
			return false;
		}

		return true;
	}

	// HÀM CHẤM CÔNG CÔNG NHÂN
	public void chamCongCongNhan() {
		if (validCCCN()) {
			BangChamCongCongNhan cccn_new = convertDataCCCN();
			if (cccn_new != null) {
				if (cccn_dao.chamCongNhan(cccn_new)) {
					getDataCCCNLenBang();
					alertSuccess("Chấm công thành công");
				} else {
					alertNotification("Chấm công thất bại! ...");
				}
			} else {
				alertNotification("LỖI không mong muốn");
			}
		}
	}

	// HÀM LẤY DỮ LIỆU CCCN LÊN BẢNG
	private void getDataCCCNLenBang() {
		dscccn = cccn_dao.getALLCCCN();
		themALLCCCNVaoBang(dscccn);
	}

	// THÊM TẤT CẢ CCCN VÀO BẢNG
	private void themALLCCCNVaoBang(ArrayList<BangChamCongCongNhan> listCCCN) {
		dtblModelCNCC.setRowCount(0);
		for (BangChamCongCongNhan cccn : listCCCN) {
			themDataCCCNVaoTable(cccn);
		}
	}

	// HÀM THÊM CHẤM CÔNG CÔNG NHÂN VÀO BẢNG
	private void themDataCCCNVaoTable(BangChamCongCongNhan cccn) {
		String[] row = new String[30];
		row[0] = String.valueOf(dtblModelCNCC.getRowCount() + 1);
		row[1] = cccn.getCongNhan().getMaCN();
		row[2] = cccn.getCongNhan().getHoTen();
		row[3] = cccn.getSanPham().getMaSP();
		row[4] = cccn.getSanPham().getTenSP();
		row[5] = cccn.getCongDoan().getMaCD();
		row[6] = cccn.getCongDoan().getTenCD();
		row[7] = String.valueOf(cccn.getNgayChamCong());
		row[8] = cmbTrangThai.getItemAt(cccn.getTrangThai());
		row[9] = cccn.getGioVaoLam();
		row[10] = String.valueOf(Integer.valueOf(cccn.getSoLuongLam()));
		row[11] = cccn.getGhiChu();

		dtblModelCNCC.addRow(row);

	}

	// HÀM HIỂN THỊ THÔNG TIN CHI TIẾT CHẤM CÔNG CÔNG NHÂN
	public void hienThiCCCNChiTiet(int index) {
		txtMaCN_dt.setText(dscccn.get(index).getCongNhan().getMaCN());
		txtTenCongNhan_dt.setText(dscccn.get(index).getCongNhan().getHoTen());

		txtMaSP_dt.setText(dscccn.get(index).getSanPham().getMaSP());
		txtMaCD_dt.setText(dscccn.get(index).getCongDoan().getMaCD());

		cmbTrangThai.setSelectedIndex(dscccn.get(index).getTrangThai());

		DateFormat format = new SimpleDateFormat("HH:mm");
		Date date = new Date(2000, 01, 01, 07, 00);
		try {
			date = format.parse(dscccn.get(index).getGioVaoLam());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spnGioDen.setValue(date);

		txtSoLuongLam_dt.setText(String.valueOf(Integer.valueOf(dscccn.get(index).getSoLuongLam())));

		txtGhiChu_dt.setText(dscccn.get(index).getGhiChu());

		displayButtonSaveAndCancel(false);
		setEditableForTextField(false);

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
