package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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

public class ChamCongCongNhan_UI extends JPanel {
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField txtCongDoan;
	private JTextField textField_1;
	private JTextField txtGioDen;
	private JTextField txtSoLuongLam;
	private JTextField txtGhiChu;
	private JTextField txtPhongBan;
	private JTextField txtNgayCham;
	
	private DefaultTableModel dtblModelCN,dtblModelCNCC;
	private JTable tblDSCC,tblCN;
	private JTableHeader tbhCN,tbhCNCC;
	private RoundedButton btnXuat,btnChamCong,btnChamLai,btnXoa ;
	
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
		
		Box box_ChamCong = Box.createHorizontalBox();
		pnlCcAll.add(box_ChamCong);
		
		JLabel lblSlPhongBan = new JLabel("Phòng ban");
		box_ChamCong.add(lblSlPhongBan);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		box_ChamCong.add(horizontalStrut_9);
		
		txtPhongBan = new JTextField();
		box_ChamCong.add(txtPhongBan);
		txtPhongBan.setColumns(10);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		box_ChamCong.add(horizontalStrut_10);
		
		JLabel lblNgayCham = new JLabel("Ngày chấm");
		box_ChamCong.add(lblNgayCham);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		box_ChamCong.add(horizontalStrut_11);
		
		txtNgayCham = new JTextField();
		box_ChamCong.add(txtNgayCham);
		txtNgayCham.setColumns(10);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		box_ChamCong.add(horizontalStrut_12);

		
		JButton btnChamTatCa = new JButton("Chấm tất cả");
		box_ChamCong.add(btnChamTatCa);
		
		String cols[] = {"Mã CN", "Họ tên", "Ngày sinh", "Công đoạn"};
		dtblModelCN = new DefaultTableModel(cols, 0);
		
		tblCN = new JTable(dtblModelCN);
		
		tbhCN = new JTableHeader(tblCN.getColumnModel());
		tbhCN.setReorderingAllowed(false);
		tbhCN.setBackground(componentColor);
		tbhCN.setForeground(Color.WHITE);
		tbhCN.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblCN.setTableHeader(tbhCN);
		
		tblCN.setRowHeight(15);
		tblCN.getColumnModel().getColumn(0).setPreferredWidth(75);
		tblCN.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblCN.getColumnModel().getColumn(2).setPreferredWidth(120);
		tblCN.getColumnModel().getColumn(3).setPreferredWidth(120);
		
		JScrollPane scrNV = new JScrollPane(tblCN, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrNV.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));
		
		pnlCCSelectAll.add(scrNV, BorderLayout.CENTER);
		
		JPanel pnlCCDetail = new JPanel();
		pnlCCDetail.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin ch\u1EA5m c\u00F4ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(20, 10, 0, 10)));
		pnlChamCong.add(pnlCCDetail, BorderLayout.EAST);
		pnlCCDetail.setLayout(new BoxLayout(pnlCCDetail, BoxLayout.Y_AXIS));
		
		Box box_r1 = Box.createHorizontalBox();
		pnlCCDetail.add(box_r1);
		
		JLabel lblCongDoan = new JLabel("Công đoạn");
		box_r1.add(lblCongDoan);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		box_r1.add(horizontalStrut);
		
		txtCongDoan = new JTextField();
		txtCongDoan.setEditable(false);
		box_r1.add(txtCongDoan);
		txtCongDoan.setColumns(10);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		box_r1.add(horizontalStrut_1);
		
		JLabel lblTrangThai = new JLabel("Trạng thái");
		box_r1.add(lblTrangThai);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		box_r1.add(horizontalStrut_2);
		
		textField_1 = new JTextField();
		box_r1.add(textField_1);
		textField_1.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(40);
		pnlCCDetail.add(verticalStrut);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		pnlCCDetail.add(horizontalBox_1);
		
		JLabel lblGioDen = new JLabel("Giờ đến");
		horizontalBox_1.add(lblGioDen);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_3);
		
		txtGioDen = new JTextField();
		horizontalBox_1.add(txtGioDen);
		txtGioDen.setColumns(10);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_4);
		
		JLabel lblSoLuongLam = new JLabel("SL làm");
		horizontalBox_1.add(lblSoLuongLam);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_5);
		
		txtSoLuongLam = new JTextField();
		horizontalBox_1.add(txtSoLuongLam);
		txtSoLuongLam.setColumns(10);
		
		Component verticalStrut_1 = Box.createVerticalStrut(40);
		pnlCCDetail.add(verticalStrut_1);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		pnlCCDetail.add(horizontalBox_2);
		
		JLabel lblGhiChu = new JLabel("Nghỉ phép");
		horizontalBox_2.add(lblGhiChu);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_6);
		
		txtGhiChu = new JTextField();
		horizontalBox_2.add(txtGhiChu);
		txtGhiChu.setColumns(10);
		
		JPanel pnlControl = new JPanel();
		pnlControl.setBorder(new EmptyBorder(20, 0, 10, 0));
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
		
		String colsPCNV[] = {"Mã NV", "Họ tên", "Phòng ban", "Ngày", "Ca làm", "Trạng thái", "Phép", "Giờ đến", "Tăng ca", "Ghi chú"};
		dtblModelCNCC = new DefaultTableModel(colsPCNV, 0);
		tblDSCC = new JTable(dtblModelCNCC);

		tbhCNCC = new JTableHeader(tblDSCC.getColumnModel());
		tbhCNCC.setReorderingAllowed(false);
		tbhCNCC.setBackground(componentColor);
		tbhCNCC.setForeground(Color.WHITE);
		tbhCNCC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblDSCC.setTableHeader(tbhCNCC);
		
		tblDSCC.setRowHeight(20);
		tblDSCC.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblDSCC.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(4).setPreferredWidth(75);
		tblDSCC.getColumnModel().getColumn(5).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(6).setPreferredWidth(50);
		tblDSCC.getColumnModel().getColumn(7).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(8).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(8).setPreferredWidth(100);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblDSCC,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangChamCong.add(scrSP);
		
	}
}
