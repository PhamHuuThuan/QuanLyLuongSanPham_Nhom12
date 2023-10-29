package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class TimKiemCongNhan_UI extends JPanel {
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField textField;
	private JTextField txtMaCN;
	private JTextField txtHoTenCN;
	private JTextField txtSoDT;
	private JTextField txtDiaChi;
	private JTextField textField_6;
	private JTextField txtNgaySinh;
	private JTextField txtGioiTinh;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_1;
	private JTextField textField_17;
	
	private RoundedButton btnTim, btnXoaRong,btnXuat;
	private DefaultTableModel dtbModelCN;
	private JTableHeader tbhCN;

	public TimKiemCongNhan_UI(MainUI main) {
		this.main = main;
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTKCN = new JPanel();
		add(pnlTKCN, BorderLayout.NORTH);
		pnlTKCN.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBody = new JPanel();
		pnlTKCN.add(pnlBody, BorderLayout.NORTH);
		pnlBody.setLayout(new BorderLayout(0, 0));
		
		
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnlTitle.setBackground(new Color(255, 255, 255));
		pnlBody.add(pnlTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("TÌM KIẾM CÔNG NHÂN");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 21F));
		pnlTitle.add(lblTitle);
		
		JPanel pnlThongTinCanTim = new JPanel();
		pnlThongTinCanTim.setBackground(new Color(255, 255, 255));
		pnlThongTinCanTim.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin c\u1EA7n t\u00ECm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 20, 10, 20)));
		pnlBody.add(pnlThongTinCanTim, BorderLayout.WEST);
		pnlThongTinCanTim.setLayout(new BoxLayout(pnlThongTinCanTim, BoxLayout.Y_AXIS));
		
		Box box_1 = Box.createHorizontalBox();
		pnlThongTinCanTim.add(box_1);
		
		JLabel lblMaCN = new JLabel("Mã CN");
		box_1.add(lblMaCN);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_2);
		
		txtMaCN = new JTextField();
		box_1.add(txtMaCN);
		txtMaCN.setColumns(10);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(40);
		box_1.add(horizontalStrut_3);
		
		JLabel lblTenCN = new JLabel("Họ Tên");
		box_1.add(lblTenCN);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_4);
		
		txtHoTenCN = new JTextField();
		box_1.add(txtHoTenCN);
		txtHoTenCN.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(30);
		pnlThongTinCanTim.add(verticalStrut);
		
		Box box_2 = Box.createHorizontalBox();
		pnlThongTinCanTim.add(box_2);
		
		JLabel lblSoDT = new JLabel("Số DT");
		box_2.add(lblSoDT);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_5);
		
		txtSoDT = new JTextField();
		box_2.add(txtSoDT);
		txtSoDT.setColumns(10);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(40);
		box_2.add(horizontalStrut_6);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		box_2.add(lblDiaChi);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_7);
		
		txtDiaChi = new JTextField();
		box_2.add(txtDiaChi);
		txtDiaChi.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(30);
		pnlThongTinCanTim.add(verticalStrut_2);
		
		Box box_3 = Box.createHorizontalBox();
		pnlThongTinCanTim.add(box_3);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		box_3.add(lblNgaySinh);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_9);
		
		txtNgaySinh = new JTextField();
		box_3.add(txtNgaySinh);
		txtNgaySinh.setColumns(10);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(40);
		box_3.add(horizontalStrut_10);
		
		JLabel lblGioiTinh = new JLabel("New label");
		box_3.add(lblGioiTinh);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		box_3.add(horizontalStrut_11);
		
		txtGioiTinh = new JTextField();
		box_3.add(txtGioiTinh);
		txtGioiTinh.setColumns(10);
		
		Component verticalStrut_7 = Box.createVerticalStrut(40);
		pnlThongTinCanTim.add(verticalStrut_7);
		
		JPanel pnlControl = new JPanel();
		pnlControl.setBackground(new Color(255, 255, 255));
		pnlThongTinCanTim.add(pnlControl);
		
		
		btnTim = new RoundedButton("Thêm", null, 20, 0, 1.0f);
		
		btnTim.setText("Tìm kiếm");
		btnTim.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnTim.setForeground(Color.WHITE);
		btnTim.setBackground(Color.decode("#3B71CA"));
		btnTim.setIcon(new ImageScaler("/image/searchwhite_icon.png", 22, 22).getScaledImageIcon());
		btnTim.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlControl.add(btnTim);
		pnlControl.add(Box.createHorizontalStrut(25));
		
		btnXoaRong = new RoundedButton("Sửa", null, 20, 0, 1.0f);
		btnXoaRong.setText("Xóa rỗng");
		btnXoaRong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setBackground(Color.decode("#ffc107"));
		btnXoaRong.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnXoaRong.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlControl.add(btnXoaRong);
		
		JPanel pnlThongTinCongNhan = new JPanel();
		pnlThongTinCongNhan.setBackground(new Color(255, 255, 255));
		pnlThongTinCongNhan.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin C\u00F4ng Nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 0, 10, 10)));
		pnlBody.add(pnlThongTinCongNhan, BorderLayout.CENTER);
		pnlThongTinCongNhan.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel pnlAvatar = new JPanel();
		pnlAvatar.setBackground(new Color(255, 255, 255));
		pnlAvatar.setBorder(new EmptyBorder(30, 0, 0, 0));
		pnlThongTinCongNhan.add(pnlAvatar);
		
		JLabel lblAvatar = new JLabel("");
		lblAvatar.setIcon(new ImageScaler("/image/image_cn_df.jpg", 150, 150).getScaledImageIcon());
		pnlAvatar.add(lblAvatar);
		
		JPanel pnlInforDetail_1 = new JPanel();
		pnlInforDetail_1.setBackground(new Color(255, 255, 255));
		pnlInforDetail_1.setBorder(new EmptyBorder(0, 10, 20, 10));
		pnlThongTinCongNhan.add(pnlInforDetail_1);
		pnlInforDetail_1.setLayout(new BoxLayout(pnlInforDetail_1, BoxLayout.Y_AXIS));
		
		Box box_info_1 = Box.createHorizontalBox();
		pnlInforDetail_1.add(box_info_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		box_info_1.add(lblNewLabel_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		box_info_1.add(horizontalStrut);
		
		textField = new JTextField();
		box_info_1.add(textField);
		textField.setColumns(10);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_1);
		
		Box box_info_2 = Box.createHorizontalBox();
		pnlInforDetail_1.add(box_info_2);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		box_info_2.add(lblNewLabel_7);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		box_info_2.add(horizontalStrut_8);
		
		textField_6 = new JTextField();
		box_info_2.add(textField_6);
		textField_6.setColumns(10);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_3);
		
		Box horizontalBox = Box.createHorizontalBox();
		pnlInforDetail_1.add(horizontalBox);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		horizontalBox.add(lblNewLabel_9);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_12);
		
		textField_9 = new JTextField();
		horizontalBox.add(textField_9);
		textField_9.setColumns(10);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_4);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		pnlInforDetail_1.add(horizontalBox_2);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		horizontalBox_2.add(lblNewLabel_10);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_13);
		
		textField_10 = new JTextField();
		horizontalBox_2.add(textField_10);
		textField_10.setColumns(10);
		
		Component verticalStrut_5 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_5);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		pnlInforDetail_1.add(horizontalBox_3);
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		horizontalBox_3.add(lblNewLabel_11);
		
		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_14);
		
		textField_11 = new JTextField();
		horizontalBox_3.add(textField_11);
		textField_11.setColumns(10);
		
		Component verticalStrut_5_2 = Box.createVerticalStrut(20);
		pnlInforDetail_1.add(verticalStrut_5_2);
		
		Box horizontalBox_3_2 = Box.createHorizontalBox();
		pnlInforDetail_1.add(horizontalBox_3_2);
		
		JLabel lblNewLabel_11_2 = new JLabel("New label");
		horizontalBox_3_2.add(lblNewLabel_11_2);
		
		Component horizontalStrut_14_2 = Box.createHorizontalStrut(20);
		horizontalBox_3_2.add(horizontalStrut_14_2);
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		horizontalBox_3_2.add(textField_17);
		
		
		JPanel pnlInfoDetail_2 = new JPanel();
		pnlInfoDetail_2.setBackground(new Color(255, 255, 255));
		pnlInfoDetail_2.setBorder(new EmptyBorder(0, 10, 20, 10));
		pnlThongTinCongNhan.add(pnlInfoDetail_2);
		pnlInfoDetail_2.setLayout(new BoxLayout(pnlInfoDetail_2, BoxLayout.Y_AXIS));
		
		Box box_info_1_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(box_info_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New label");
		box_info_1_1.add(lblNewLabel_1_1);
		
		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		box_info_1_1.add(horizontalStrut_15);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		box_info_1_1.add(textField_12);
		
		Component verticalStrut_1_1 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_1_1);
		
		Box box_info_2_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(box_info_2_1);
		
		JLabel lblNewLabel_7_1 = new JLabel("New label");
		box_info_2_1.add(lblNewLabel_7_1);
		
		Component horizontalStrut_8_1 = Box.createHorizontalStrut(20);
		box_info_2_1.add(horizontalStrut_8_1);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		box_info_2_1.add(textField_13);
		
		Component verticalStrut_3_1 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_3_1);
		
		Box horizontalBox_4 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(horizontalBox_4);
		
		JLabel lblNewLabel_9_1 = new JLabel("New label");
		horizontalBox_4.add(lblNewLabel_9_1);
		
		Component horizontalStrut_12_1 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_12_1);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		horizontalBox_4.add(textField_14);
		
		Component verticalStrut_4_1 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_4_1);
		
		Box horizontalBox_2_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(horizontalBox_2_1);
		
		JLabel lblNewLabel_10_1 = new JLabel("New label");
		horizontalBox_2_1.add(lblNewLabel_10_1);
		
		Component horizontalStrut_13_1 = Box.createHorizontalStrut(20);
		horizontalBox_2_1.add(horizontalStrut_13_1);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		horizontalBox_2_1.add(textField_15);
		
		Component verticalStrut_5_1 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_5_1);
		
		Box horizontalBox_3_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(horizontalBox_3_1);
		
		JLabel lblNewLabel_11_1 = new JLabel("New label");
		horizontalBox_3_1.add(lblNewLabel_11_1);
		
		Component horizontalStrut_14_1 = Box.createHorizontalStrut(20);
		horizontalBox_3_1.add(horizontalStrut_14_1);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		horizontalBox_3_1.add(textField_16);
		
		Component verticalStrut_6 = Box.createVerticalStrut(20);
		pnlInfoDetail_2.add(verticalStrut_6);
		
		Box horizontalBox_3_1_1 = Box.createHorizontalBox();
		pnlInfoDetail_2.add(horizontalBox_3_1_1);
		
		JLabel lblNewLabel_11_1_1 = new JLabel("New label");
		horizontalBox_3_1_1.add(lblNewLabel_11_1_1);
		
		Component horizontalStrut_14_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_3_1_1.add(horizontalStrut_14_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		horizontalBox_3_1_1.add(textField_1);
		
		
		JPanel pnlTable = new JPanel();
		pnlTable.setBackground(new Color(255, 255, 255));
		pnlTable.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch C\u00F4ng Nh\u00E2n \u0111\u00E3 t\u00ECm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(0, 10, 0, 10)));
		pnlTable.setLayout(new BorderLayout(0, 0));
		
		String cols[] = {"Mã CN", "Họ tên", "Giới tính", "SĐT","Emai", "Địa chỉ",  "Ngày vào làm", "Ghi chú"};
		dtbModelCN = new DefaultTableModel(cols, 0);
		JTable tblCN = new JTable(dtbModelCN);
		
		JPanel pnlXuat = new JPanel();
		pnlXuat.setBackground(Color.WHITE);
		pnlTable.add(pnlXuat, BorderLayout.NORTH);
		pnlXuat.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnXuat = new RoundedButton(main.read_file_languages.getString("btn_XuatDS"), null, 5, 0, 1.0f);
		btnXuat.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setBackground(Color.decode("#28a745"));
		btnXuat.setIcon(new ImageScaler("/image/printer_icon.png", 20, 20).getScaledImageIcon());
		btnXuat.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		pnlXuat.add(btnXuat);

		tbhCN = new JTableHeader(tblCN.getColumnModel());
		tbhCN.setReorderingAllowed(false);
		tbhCN.setBackground(componentColor);
		tbhCN.setForeground(Color.WHITE);
		tbhCN.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblCN.setTableHeader(tbhCN);
		
		tblCN.setRowHeight(20);
		tblCN.getColumnModel().getColumn(0).setPreferredWidth(75);
		tblCN.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblCN.getColumnModel().getColumn(2).setPreferredWidth(75);
		tblCN.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblCN.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblCN.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblCN.getColumnModel().getColumn(6).setPreferredWidth(100);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblCN,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlTable.add(scrSP);
		

		pnlTKCN.add(pnlTable, BorderLayout.CENTER);
		
		
		
		
		
	}
}
