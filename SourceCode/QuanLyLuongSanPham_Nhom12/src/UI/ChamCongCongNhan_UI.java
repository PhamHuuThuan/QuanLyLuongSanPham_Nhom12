package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;

public class ChamCongCongNhan_UI extends JPanel {
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	
	
	private JTextField txtTTSanPham,txtMaCN,txtTenCongNhan,txtTenSanPham;
	private JTextField txtSoLuongLam,txtGhiChu,txtTenCongDoan;
	private JComboBox<String> cmbChonMaCongDoan,cmbTrangThai,cmbMaSanPham;
	private JXDatePicker dtbNgayChamCong;
	
	private DefaultTableModel dtblModelCN,dtblModelCNCC;
	private JTable tblDSCC,tblCN;
	private JTableHeader tbhCN,tbhCNCC;
	private RoundedButton btnXuat,btnChamCong,btnChamLai,btnXoa ;
	private JTextField txtTTMaCongDoan;
	
	public ChamCongCongNhan_UI(MainUI main) {
		this.main = main;
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlChamCongCN = new JPanel();
		add(pnlChamCongCN, BorderLayout.NORTH);
		pnlChamCongCN.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBody = new JPanel();
		pnlChamCongCN.add(pnlBody, BorderLayout.NORTH);
		pnlBody.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTitle = new JPanel();
		pnlBody.add(pnlTitle, BorderLayout.NORTH);
		pnlTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTitle = new JLabel("CHẤM CÔNG CÔNG NHÂN");
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTitle.add(lblTitle);
		
		JPanel pnlChamCong = new JPanel();
		pnlBody.add(pnlChamCong, BorderLayout.SOUTH);
		pnlChamCong.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlCCSelectAll = new JPanel();
		pnlCCSelectAll.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch C\u00F4ng Nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 10, 0, 10)));
		pnlChamCong.add(pnlCCSelectAll, BorderLayout.CENTER);
		pnlCCSelectAll.setLayout(new BorderLayout(0, 0));
		pnlCCSelectAll.setPreferredSize(new Dimension(200, 300));
		
		JPanel pnlCcAll = new JPanel();
		pnlCCSelectAll.add(pnlCcAll, BorderLayout.NORTH);
		pnlCcAll.setLayout(new BoxLayout(pnlCcAll, BoxLayout.Y_AXIS));
		
		Box horizontalBox = Box.createHorizontalBox();
		pnlCcAll.add(horizontalBox);
		
		JLabel lblNgayChamCong = new JLabel("Ngày Chấm");
		horizontalBox.add(lblNgayChamCong);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_7);
		
		dtbNgayChamCong = new JXDatePicker((Date) null);
		dtbNgayChamCong.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtbNgayChamCong.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dtbNgayChamCong.setLocale(new Locale("vi", "VN"));
		horizontalBox.add(dtbNgayChamCong);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_8);
		
		JLabel lblMaSanPham = new JLabel("Mã Sản Phẩm");
		horizontalBox.add(lblMaSanPham);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_13);
		
		cmbMaSanPham = new JComboBox<>();
		cmbMaSanPham.addItem("SP00001");
		cmbMaSanPham.addItem("SP00002");
		horizontalBox.add(cmbMaSanPham);
		
		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_14);
		
		JLabel lblTenSanPham = new JLabel("Tên SP");
		horizontalBox.add(lblTenSanPham);
		
		Component horizontalStrut_18 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_18);
		
		txtTenSanPham = new JTextField("Ghế nhựa GP1");
		txtTenSanPham.setEditable(false);
		horizontalBox.add(txtTenSanPham);
		txtTenSanPham.setColumns(10);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		pnlCcAll.add(verticalStrut_3);
		
		Box box_ChamCong = Box.createHorizontalBox();
		box_ChamCong.setBorder(new EmptyBorder(0, 0, 10, 0));
		pnlCcAll.add(box_ChamCong);
		
		JLabel lblChonMaCongDoan = new JLabel("Mã công Đoạn");
		box_ChamCong.add(lblChonMaCongDoan);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		box_ChamCong.add(horizontalStrut_9);
		
		cmbChonMaCongDoan = new JComboBox<>();
		cmbChonMaCongDoan.addItem("CD00001");
		cmbChonMaCongDoan.addItem("CD00002");
		box_ChamCong.add(cmbChonMaCongDoan);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		box_ChamCong.add(horizontalStrut_10);
		
		JLabel lblTenCongDoan = new JLabel("Tên công đoạn");
		box_ChamCong.add(lblTenCongDoan);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		box_ChamCong.add(horizontalStrut_11);
		
		txtTenCongDoan = new JTextField("Gia Công");
		txtTenCongDoan.setEditable(false);
		box_ChamCong.add(txtTenCongDoan);
		txtTenCongDoan.setColumns(10);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		box_ChamCong.add(horizontalStrut_12);

		
		JButton btnChamTatCa = new JButton("Lấy danh sách");
		box_ChamCong.add(btnChamTatCa);
		
		String cols[] = {"STT","Mã CN", "Họ tên", "Ngày sinh"};
		dtblModelCN = new DefaultTableModel(cols, 0);
		
		tblCN = new JTable(dtblModelCN);
		
		tbhCN = new JTableHeader(tblCN.getColumnModel());
		tbhCN.setReorderingAllowed(false);
		tbhCN.setBackground(componentColor);
		tbhCN.setForeground(Color.WHITE);
		tbhCN.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblCN.setTableHeader(tbhCN);
		
		tblCN.setRowHeight(15);
		tblCN.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblCN.getColumnModel().getColumn(1).setPreferredWidth(120);
		tblCN.getColumnModel().getColumn(2).setPreferredWidth(150);
		tblCN.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		JScrollPane scrNV = new JScrollPane(tblCN, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrNV.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));
		
		pnlCCSelectAll.add(scrNV, BorderLayout.CENTER);
		
		JPanel pnlCCDetail = new JPanel();
		pnlCCDetail.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin ch\u1EA5m c\u00F4ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(20, 10, 0, 10)));
		pnlChamCong.add(pnlCCDetail, BorderLayout.EAST);
		pnlCCDetail.setLayout(new BoxLayout(pnlCCDetail, BoxLayout.Y_AXIS));
		
		Box box_r0 = Box.createHorizontalBox();
		pnlCCDetail.add(box_r0);
		
		JLabel lblMaCN = new JLabel("Mã CN");
		box_r0.add(lblMaCN);
		
		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		box_r0.add(horizontalStrut_15);
		
		txtMaCN = new JTextField();
		txtMaCN.setText("CN00001");
		txtMaCN.setEditable(false);
		box_r0.add(txtMaCN);
		txtMaCN.setColumns(10);
		
		Component horizontalStrut_16 = Box.createHorizontalStrut(20);
		box_r0.add(horizontalStrut_16);
		
		JLabel lblTenCN = new JLabel("Tên CN");
		box_r0.add(lblTenCN);
		
		Component horizontalStrut_17 = Box.createHorizontalStrut(20);
		box_r0.add(horizontalStrut_17);
		
		txtTenCongNhan = new JTextField();
		txtTenCongNhan.setText("Nguyễn Văn Phong");
		txtTenCongNhan.setEditable(false);
		box_r0.add(txtTenCongNhan);
		txtTenCongNhan.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		pnlCCDetail.add(verticalStrut_2);
		
		Box box_r1 = Box.createHorizontalBox();
		pnlCCDetail.add(box_r1);
		
		JLabel lblTTSanPham = new JLabel("Sản phẩm");
		box_r1.add(lblTTSanPham);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		box_r1.add(horizontalStrut);
		
		txtTTSanPham = new JTextField();
		txtTTSanPham.setText("SP00001");
		txtTTSanPham.setEditable(false);
		box_r1.add(txtTTSanPham);
		txtTTSanPham.setColumns(10);
		
		Component horizontalStrut_19 = Box.createHorizontalStrut(20);
		box_r1.add(horizontalStrut_19);
		
		JLabel lblTTMaCongDoan = new JLabel("Mã CĐ");
		box_r1.add(lblTTMaCongDoan);
		
		Component horizontalStrut_20 = Box.createHorizontalStrut(20);
		box_r1.add(horizontalStrut_20);
		
		txtTTMaCongDoan = new JTextField();
		txtTTMaCongDoan.setText("CD00001");
		txtTTMaCongDoan.setEditable(false);
		box_r1.add(txtTTMaCongDoan);
		txtTTMaCongDoan.setColumns(10);
		
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
		
		JLabel lblGioDen = new JLabel("Giờ đến");
		horizontalBox_1.add(lblGioDen);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_3);
		
		JSpinner spnGioDen = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spnGioDen, "hh' : ' mm");
        spnGioDen.setEditor(timeEditor);
		horizontalBox_1.add(spnGioDen);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_4);
		
		JLabel lblSoLuongLam = new JLabel("SL làm");
		horizontalBox_1.add(lblSoLuongLam);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_5);
		
		txtSoLuongLam = new JTextField();
		horizontalBox_1.add(txtSoLuongLam);
		txtSoLuongLam.setColumns(10);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnlCCDetail.add(verticalStrut_1);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		pnlCCDetail.add(horizontalBox_2);
		
		JLabel lblGhiChu = new JLabel("Ghi chú");
		horizontalBox_2.add(lblGhiChu);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_6);
		
		txtGhiChu = new JTextField();
		horizontalBox_2.add(txtGhiChu);
		txtGhiChu.setColumns(10);
		
		JPanel pnlControl = new JPanel();
		pnlControl.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnlCCDetail.add(pnlControl);
		
		btnChamCong = new RoundedButton("Phân công", null, 20, 0, 1.0f);
		btnChamCong.setText("Chấm công");
		btnChamCong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnChamCong.setForeground(Color.WHITE);
		btnChamCong.setBackground(Color.decode("#3B71CA"));
		btnChamCong.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnChamCong.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlControl.add(btnChamCong);
		pnlControl.add(Box.createHorizontalStrut(50));
		
		btnChamLai = new RoundedButton("Cập Nhật", null, 20, 0, 1.0f);
		btnChamLai.setText("Chấm lại");
		btnChamLai.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnChamLai.setForeground(Color.WHITE);
		btnChamLai.setBackground(Color.decode("#ffc107"));
		btnChamLai.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnChamLai.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlControl.add(btnChamLai);
		pnlControl.add(Box.createHorizontalStrut(50));
		
		btnXoa = new RoundedButton("Xóa", null, 20, 0, 1.0f);
		btnXoa.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(Color.decode("#dc3545"));
		btnXoa.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlControl.add(btnXoa);
		
		
		
		
		
		
		JPanel pnlBangChamCong = new JPanel();
		pnlBangChamCong.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch C\u00F4ng nh\u00E2n \u0111\u00E3 ch\u1EA5m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 0, 0, 0)));
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
		
		String colsPCNV[] = {"STT","Mã CN", "Họ tên", "Công đoạn","Sản Phẩm", "Ngày Chấm","Trạng thái","Giờ đến", "SL làm","Ghi chú"};
		dtblModelCNCC = new DefaultTableModel(colsPCNV, 0);
		tblDSCC = new JTable(dtblModelCNCC);

		tbhCNCC = new JTableHeader(tblDSCC.getColumnModel());
		tbhCNCC.setReorderingAllowed(false);
		tbhCNCC.setBackground(componentColor);
		tbhCNCC.setForeground(Color.WHITE);
		tbhCNCC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblDSCC.setTableHeader(tbhCNCC);
		
		tblDSCC.setRowHeight(20);
		tblDSCC.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblDSCC.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblDSCC.getColumnModel().getColumn(2).setPreferredWidth(80);
		tblDSCC.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(5).setPreferredWidth(80);
		tblDSCC.getColumnModel().getColumn(6).setPreferredWidth(80);
		tblDSCC.getColumnModel().getColumn(7).setPreferredWidth(50);
		tblDSCC.getColumnModel().getColumn(8).setPreferredWidth(70);
		tblDSCC.getColumnModel().getColumn(9).setPreferredWidth(90);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblDSCC,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangChamCong.add(scrSP);
		
	}
}
