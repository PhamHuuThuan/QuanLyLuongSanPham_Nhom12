package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomUI.CustomComboBoxUI;
import CustomUI.CustomListCellRenderer;
import CustomUI.ImageScaler;
import CustomUI.RoundedButton;

public class TimKiemCongDoanUI extends JPanel implements ActionListener, MouseListener{
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField txtMaCD, txtMaSP, txtTenCD, txtDonGia;
	private RoundedButton btnTimKiem, btnXoaRong, btnXuat, btnFocus;
	private DefaultTableModel dtblModelCD;
	private JTable tblCD;
	private JTableHeader tbhCD;
	private JPanel pnlChucNang;
	
	private JTextField txtMaHDS;
	private JTextField txtMaSPS;
	private JTextField txtTenSPS;
	private JTextField txtDonGiaSan;
	private JTextField txtDonGiaTran;
	/**
	 * Create the panel.
	 */
	public TimKiemCongDoanUI(MainUI main) {
		this.main = main;
		
		//set gia tri cho jpanel Cong Doan
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(bgColor);
		
		JPanel pnlCongDoan = new JPanel();
		add(pnlCongDoan, BorderLayout.CENTER);
		pnlCongDoan.setLayout(new BorderLayout(0, 0));
		pnlCongDoan.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pnlCongDoan.setBackground(bgColor);
		
		//tao jpanel chua Title va Thong tin cong doan
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pnlNorth.setBackground(bgColor);
		pnlCongDoan.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BorderLayout(0, 0));
		
		//Tao jpanel Title
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		pnlTitle.setBackground(bgColor);
		pnlNorth.add(pnlTitle, BorderLayout.NORTH);
		
		//Tiêu đề
		JLabel lblTitle = new JLabel(main.read_file_languages.getString("title_product_search"));
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle.add(lblTitle);
		
		//Tạo hộp thoại tìm kiếm
		Box b = Box.createHorizontalBox();
		b.setBackground(bgColor);
		pnlNorth.add(b, BorderLayout.CENTER);
		
		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setLayout(new BoxLayout(pnlTimKiem, BoxLayout.Y_AXIS));
		pnlTimKiem.setBackground(bgColor);
		TitledBorder titleBorderTTCD = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("title_border_searchbox"));
		titleBorderTTCD.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlTimKiem.setBorder(BorderFactory.createCompoundBorder(titleBorderTTCD, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		b.add(pnlTimKiem);
		b.add(Box.createHorizontalStrut(50));
		
		Box b2 = Box.createHorizontalBox();
		pnlTimKiem.add(b2);
		
		JLabel lblMaCD = new JLabel(main.read_file_languages.getString("lblMaCD") + ":");
		lblMaCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblMaCD.setForeground(textColor);
		b2.add(lblMaCD);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		b2.add(horizontalStrut);
		
		txtMaHDS = new JTextField();
		txtMaHDS.setColumns(10);
		txtMaHDS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaHDS.setForeground(textColor);
		txtMaHDS.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 

						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaHDS.setBackground(Color.WHITE);
		b2.add(txtMaHDS);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(30);
		b2.add(horizontalStrut_2);
		
		JLabel lblMaSPS = new JLabel(main.read_file_languages.getString("lblMaSPSearch") + ":");
		lblMaSPS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblMaSPS.setForeground(textColor);
		b2.add(lblMaSPS);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		b2.add(horizontalStrut_3);
		
		txtMaSPS = new JTextField();
		txtMaSPS.setColumns(10);
		txtMaSPS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaSPS.setForeground(textColor);
		txtMaSPS.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 

						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaSPS.setBackground(Color.WHITE);
		b2.add(txtMaSPS);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		pnlTimKiem.add(verticalStrut_2);
		
		Box b3 = Box.createHorizontalBox();
		pnlTimKiem.add(b3);
		
		JLabel lblTenSPS = new JLabel(main.read_file_languages.getString("lblTenSPSearch") + ":");
		lblTenSPS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblTenSPS.setForeground(textColor);
		b3.add(lblTenSPS);
		
		b3.add(Box.createHorizontalStrut(10));
		
		txtTenSPS = new JTextField();
		txtTenSPS.setColumns(10);
		txtTenSPS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenSPS.setForeground(textColor);
		txtTenSPS.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 


								BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtTenSPS.setBackground(Color.WHITE);
		b3.add(txtTenSPS);
		
		Component horizontalStrut_2_1 = Box.createHorizontalStrut(10);
		b3.add(horizontalStrut_2_1);
		
		JLabel lblDVTS = new JLabel(main.read_file_languages.getString("lblSoLuong")+ " (>=):");
		lblDVTS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblDVTS.setForeground(textColor);
		b3.add(lblDVTS);
		
		b3.add(Box.createHorizontalStrut(10));
		
		SpinnerNumberModel modelSL1 = new SpinnerNumberModel(10, 1, 1000000, 100);
		JSpinner spnSoLuongS = new JSpinner(modelSL1);
		Border cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 0, 5, 0));
		spnSoLuongS.setBorder(cboBorder);
		spnSoLuongS.setBackground(bgColor);
		spnSoLuongS.setForeground(textColor);
		spnSoLuongS.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 14F));
		b3.add(spnSoLuongS);
		
		pnlTimKiem.add(Box.createVerticalStrut(20));
		
		
		//Tao jpanel Thong tin hop dong
		JPanel pnlThongTinCD = new JPanel();
		pnlThongTinCD.setLayout(new BoxLayout(pnlThongTinCD, BoxLayout.Y_AXIS));
		pnlThongTinCD.setBackground(bgColor);
		titleBorderTTCD = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("info_product"));
		titleBorderTTCD.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlThongTinCD.setBorder(BorderFactory.createCompoundBorder(titleBorderTTCD, BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		b.add(pnlThongTinCD);
		
		// Tao box chua cac phan tu hang 1: maCD, ma SP, tenCD
		Box b1 = Box.createHorizontalBox();
		pnlThongTinCD.add(b1);
		
		JLabel lblMaHD = new JLabel(main.read_file_languages.getString("lblMaCD") + ":");
		lblMaHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblMaHD.setForeground(textColor);
		b1.add(lblMaHD);
		b1.add(Box.createHorizontalStrut(10));
		
		txtMaCD = new JTextField();
		txtMaCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaCD.setForeground(textColor);
		txtMaCD.setBackground(bgColor);
		txtMaCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		b1.add(txtMaCD);
		b1.add(Box.createHorizontalStrut(30));
		
		JLabel lblMaSP = new JLabel(main.read_file_languages.getString("lblMaSP") + ":");
		lblMaSP.setForeground(textColor);
		lblMaSP.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b1.add(lblMaSP);
		b1.add(Box.createHorizontalStrut(10));
		
		txtMaSP = new JTextField();
		txtMaSP.setForeground(textColor);
		txtMaSP.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaSP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaSP.setBackground(bgColor);
		b1.add(txtMaSP);
		b1.add(Box.createHorizontalStrut(30));
		
		JLabel lblTenSP = new JLabel(main.read_file_languages.getString("lblTenCD") + ":");
		lblTenSP.setForeground(textColor);
		lblTenSP.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b1.add(lblTenSP);
		b1.add(Box.createHorizontalStrut(10));
		
		txtTenCD = new JTextField();
		txtTenCD.setForeground(textColor);
		txtTenCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtTenCD.setBackground(bgColor);
		b1.add(txtTenCD);
		
		pnlThongTinCD.add(Box.createVerticalStrut(40));
		
		//Khởi tạo jpanel chức năng chứa các button chức năng: thêm, sửa, xóa, lưu, hủy
		pnlChucNang = new JPanel();
		pnlTimKiem.add(pnlChucNang);
		pnlChucNang.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlChucNang.setBackground(bgColor);
		pnlChucNang.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		btnTimKiem = new RoundedButton(main.read_file_languages.getString("btnTimKiem"), null, 20, 0, 1.0f);
		btnTimKiem.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setBackground(Color.decode("#3B71CA"));
		btnTimKiem.setIcon(new ImageScaler("/image/searchwhite_icon.png", 24, 24).getScaledImageIcon());
		btnTimKiem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnTimKiem);
		pnlChucNang.add(Box.createHorizontalStrut(25));
		
		btnXoaRong = new RoundedButton(main.read_file_languages.getString("btnXoaRong"), null, 20, 0, 1.0f);
		btnXoaRong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setBackground(Color.decode("#ffc107"));
		btnXoaRong.setIcon(new ImageScaler("/image/refresh-arrow_white_icon.png", 24, 24).getScaledImageIcon());
		btnXoaRong.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoaRong);
		
		// tạo jpanel chứa table cong doan
		JPanel pnlBangCD = new JPanel();
		titleBorderTTCD = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("border_title_CD"));
		titleBorderTTCD.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangCD.setBorder(BorderFactory.createCompoundBorder(titleBorderTTCD, BorderFactory.createEmptyBorder(0, 10, 5, 10)));
		pnlBangCD.setBackground(bgColor);
		pnlCongDoan.add(pnlBangCD, BorderLayout.CENTER);
		pnlBangCD.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlXuat = new JPanel();
		pnlXuat.setBackground(bgColor);
		pnlXuat.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlBangCD.add(pnlXuat, BorderLayout.NORTH);
		
		btnXuat = new RoundedButton(main.read_file_languages.getString("btn_XuatDS"), null, 5, 0, 1.0f);
		btnXuat.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setBackground(Color.decode("#28a745"));
		btnXuat.setIcon(new ImageScaler("/image/printer_icon.png", 20, 20).getScaledImageIcon());
		btnXuat.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		pnlXuat.add(btnXuat);
		
		String cols[] = {main.read_file_languages.getString("tbhMaHDSP"),
				main.read_file_languages.getString("lblMaCD"),
				main.read_file_languages.getString("lblTenCD"), 
				main.read_file_languages.getString("lblTenSP"), 
				};
		dtblModelCD = new DefaultTableModel(cols, 0);
		tblCD = new JTable(dtblModelCD);

		tbhCD = new JTableHeader(tblCD.getColumnModel());
		tbhCD.setReorderingAllowed(false);
		tbhCD.setBackground(componentColor);
		tbhCD.setForeground(Color.WHITE);
		tbhCD.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblCD.setTableHeader(tbhCD);
		
		tblCD.setRowHeight(20);
		tblCD.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblCD.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblCD.getColumnModel().getColumn(2).setPreferredWidth(175);
		tblCD.getColumnModel().getColumn(3).setPreferredWidth(175);
		
		//Tạo jscrollpane để tạo scroll cho bảng cong doan
		JScrollPane scrSP = new JScrollPane(tblCD,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangCD.add(scrSP, BorderLayout.CENTER);
		
		pnlNorth.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
		
		btnTimKiem.addActionListener(this);
		btnXoaRong.addActionListener(this);
		
		btnTimKiem.addMouseListener(this);
		btnXoaRong.addMouseListener(this);
		
		
		//Không thể chỉnh sửa txt
		setEditableForTextField(false);
		
		//Set giá trị mặc định để hiển thị
		
	}
	private void add(JPanel pnlCongDoan, String center) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {

	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		if (o instanceof RoundedButton) {
	    }
	}
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == btnTimKiem) {
			setEditableForTextField(true);
			xoaRong();
			
		}
		if(o == btnXoaRong) {
			setEditableForTextField(true);
			
		}
	}
	private void setEditableForTextField(boolean edit) {
		if(edit == true) {
			txtMaCD.setEditable(true);
			txtMaSP.setEditable(true);
			txtTenCD.setEditable(true);
			txtDonGia.setEditable(true);
		}else {
			txtMaCD.setEditable(false);
			txtMaSP.setEditable(false);
			txtTenCD.setEditable(false);
			txtDonGia.setEditable(false);
		}
	}
	private void xoaRong() {
		txtMaCD.setText("");
		txtMaSP.setText("");
		txtTenCD.setText("");
		txtDonGia.setText("");
	}
	
}

