package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JSpinner;

public class QuanLySanPhamUI extends JPanel implements ActionListener, MouseListener{
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField txtMaHD, txtMaSP, txtTenSP, txtDonGia;
	private RoundedButton btnThem, btnSua, btnXoa, btnLuu, btnHuy, btnIn,btnFocus;
	private DefaultTableModel dtblModelSP, dtblModelHD;
	private JTable tblSP, tblHD;
	private JTableHeader tbhSP, tbhHD;
	private JPanel pnlChucNang;
	/**
	 * Create the panel.
	 */
	public QuanLySanPhamUI(MainUI main) {
		this.main = main;
		
		//set gia tri cho jpanel SanPham
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(bgColor);
		
		JPanel pnlSanPham = new JPanel();
		add(pnlSanPham, BorderLayout.CENTER);
		pnlSanPham.setLayout(new BorderLayout(0, 0));
		pnlSanPham.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pnlSanPham.setBackground(bgColor);
		
		//tao jpanel chua Title va Thong tin san pham
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlNorth.setBackground(bgColor);
		pnlSanPham.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BorderLayout(0, 0));
		
		//Tao jpanel Title
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBackground(bgColor);
		pnlNorth.add(pnlTitle, BorderLayout.NORTH);
		
		//Tiêu đề
		JLabel lblTitle = new JLabel(main.read_file_languages.getString("title_product"));
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle.add(lblTitle);
		
		//Danh sách hợp đồng
		JPanel pnlDSHD = new JPanel();
		pnlDSHD.setLayout(new BorderLayout());
		pnlDSHD.setBackground(bgColor);
		TitledBorder titleBorderDSHD = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("border_title_HD"));
		pnlDSHD.setBorder(BorderFactory.createCompoundBorder(titleBorderDSHD, BorderFactory.createEmptyBorder(10, 5, 5, 5)));
		add(pnlDSHD, BorderLayout.WEST);
		
		//Bảng hợp đồng
		String colsHDName[] = {main.read_file_languages.getString("tbhMaHDSP"), 
				main.read_file_languages.getString("lblTenHD"), 
				main.read_file_languages.getString("lblKH")};
		dtblModelHD = new DefaultTableModel(colsHDName, 0);
		tblHD = new JTable(dtblModelHD);
		
		tbhHD = new JTableHeader(tblHD.getColumnModel());
		tbhHD.setReorderingAllowed(false);
		tbhHD.setBackground(componentColor);
		tbhHD.setForeground(Color.WHITE);
		tbhHD.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblHD.setTableHeader(tbhHD);
		
		tblHD.setRowHeight(20);
		tblHD.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblHD.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblHD.getColumnModel().getColumn(2).setPreferredWidth(175);
		
		JScrollPane scrHD = new JScrollPane(tblHD,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlDSHD.add(scrHD, BorderLayout.CENTER);
		
		//Tao jpanel Thong tin san pham
		JPanel pnlThongTinSP = new JPanel();
		pnlThongTinSP.setLayout(new BoxLayout(pnlThongTinSP, BoxLayout.Y_AXIS));
		pnlThongTinSP.setBackground(bgColor);
		TitledBorder titleBorderTTSP = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("info_product"));
		titleBorderTTSP.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlThongTinSP.setBorder(BorderFactory.createCompoundBorder(titleBorderTTSP, BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		pnlNorth.add(pnlThongTinSP, BorderLayout.CENTER);
		
		// Tao box chua cac phan tu hang 1: maHD, ma SP, tenSP
		Box b1 = Box.createHorizontalBox();
		pnlThongTinSP.add(b1);
		
		JLabel lblMaHD = new JLabel(main.read_file_languages.getString("lblMaHD") + ":");
		lblMaHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblMaHD.setForeground(textColor);
		b1.add(lblMaHD);
		b1.add(Box.createHorizontalStrut(10));
		
		txtMaHD = new JTextField();
		txtMaHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaHD.setForeground(textColor);
		txtMaHD.setBackground(bgColor);
		txtMaHD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		b1.add(txtMaHD);
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
		
		JLabel lblTenSP = new JLabel(main.read_file_languages.getString("lblTenSP") + ":");
		lblTenSP.setForeground(textColor);
		lblTenSP.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b1.add(lblTenSP);
		b1.add(Box.createHorizontalStrut(10));
		
		txtTenSP = new JTextField();
		txtTenSP.setForeground(textColor);
		txtTenSP.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenSP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtTenSP.setBackground(bgColor);
		b1.add(txtTenSP);
		
		pnlThongTinSP.add(Box.createVerticalStrut(20));
		
		//Tao box chua thong tin hang 2: ngayBD, ngayKT, giatri, tiencoc
		
		Box b2 = Box.createHorizontalBox();
		b2.setBackground(bgColor);
		pnlThongTinSP.add(b2);
		
		JLabel lblDVT = new JLabel(main.read_file_languages.getString("lblDVT") + ":");
		lblDVT.setForeground(textColor);
		lblDVT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblDVT);
		
		b2.add(Box.createHorizontalStrut(15));
		
		JComboBox cmbDVT = new JComboBox();
		cmbDVT.setModel(new DefaultComboBoxModel(new String[] {"Cái", "Bộ", "Đôi(Cặp)", "Hộp", "Gói", "M2", "Kg", "Lít"}));
		Border cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbDVT.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbDVT.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbDVT.setBackground(bgColor);
		cmbDVT.setForeground(textColor);
		cmbDVT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(cmbDVT);

		b2.add(Box.createHorizontalStrut(40));
		
		JLabel lblDonGia = new JLabel(main.read_file_languages.getString("lblDonGia") + ":");
		lblDonGia.setForeground(textColor);
		lblDonGia.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblDonGia);
		
		b2.add(Box.createHorizontalStrut(15));
		
		txtDonGia = new JTextField();
		txtDonGia.setForeground(textColor);
		txtDonGia.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtDonGia.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtDonGia.setBackground(bgColor);
		txtDonGia.setColumns(7);
		b2.add(txtDonGia);
		
		JLabel lblVND2 = new JLabel("VNĐ");
		lblVND2.setForeground(textColor);
		lblVND2.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblVND2);
		
		b2.add(Box.createHorizontalStrut(40));
		
		JLabel lblSoLuong = new JLabel(main.read_file_languages.getString("lblSoLuong") + ":");
		lblSoLuong.setForeground(textColor);
		lblSoLuong.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblSoLuong);
		
		b2.add(Box.createHorizontalStrut(15));
		
		SpinnerNumberModel model = new SpinnerNumberModel(10, 1, 1000000, 100);
		JSpinner spnSoLuong = new JSpinner(model);
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 0, 5, 0));
		spnSoLuong.setBorder(cboBorder);
		spnSoLuong.setBackground(bgColor);
		spnSoLuong.setForeground(textColor);
		spnSoLuong.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(spnSoLuong);
		b2.add(Box.createHorizontalStrut(75));
		
		pnlThongTinSP.add(Box.createVerticalStrut(20));
		
		Box b3 = Box.createHorizontalBox();
		pnlThongTinSP.add(b3);
		b3.setBackground(bgColor);
		JLabel lblYeuCau = new JLabel(main.read_file_languages.getString("lblYeuCau") + ":");
		lblYeuCau.setForeground(textColor);
		lblYeuCau.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b3.add(lblYeuCau);
		b3.add(Box.createHorizontalStrut(35));
		
		JTextArea txaYeuCau = new JTextArea();
		txaYeuCau.setRows(3);
		txaYeuCau.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txaYeuCau.setForeground(textColor);
		txaYeuCau.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		JScrollPane scrYC = new JScrollPane(txaYeuCau,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		b3.add(scrYC);
		
		//Khởi tạo jpanel chức năng chứa các button chức năng: thêm, sửa, xóa, lưu, hủy
		pnlChucNang = new JPanel();
		pnlNorth.add(pnlChucNang, BorderLayout.SOUTH);
		pnlChucNang.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlChucNang.setBackground(bgColor);
		pnlChucNang.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		btnThem = new RoundedButton(main.read_file_languages.getString("btnThem"), null, 20, 0, 1.0f);
		btnThem.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(Color.decode("#3B71CA"));
		btnThem.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnThem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnThem);
		pnlChucNang.add(Box.createHorizontalStrut(25));
		
		btnSua = new RoundedButton(main.read_file_languages.getString("btnSua"), null, 20, 0, 1.0f);
		btnSua.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnSua.setForeground(Color.WHITE);
		btnSua.setBackground(Color.decode("#ffc107"));
		btnSua.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnSua.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnSua);
		pnlChucNang.add(Box.createHorizontalStrut(25));
		
		btnXoa = new RoundedButton(main.read_file_languages.getString("btnXoa"), null, 20, 0, 1.0f);
		btnXoa.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(Color.decode("#dc3545"));
		btnXoa.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoa);
		pnlChucNang.add(Box.createHorizontalStrut(25));
		
		btnIn = new RoundedButton(main.read_file_languages.getString("btnIn"), null, 20, 0, 1.0f);
		btnIn.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnIn.setForeground(Color.WHITE);
		btnIn.setBackground(Color.decode("#17a2b8"));
		btnIn.setIcon(new ImageScaler("/image/printer_icon.png", 24, 24).getScaledImageIcon());
		btnIn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnIn);
		
		pnlChucNang.add(Box.createHorizontalStrut(100));
		
		btnLuu = new RoundedButton(main.read_file_languages.getString("btnLuu"), null, 20, 0, 0.6f);
		btnLuu.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setBackground(Color.decode("#28a745"));
		btnLuu.setIcon(new ImageScaler("/image/save_Icon.png", 24, 24).getScaledImageIcon());
		btnLuu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnLuu);
		pnlChucNang.add(Box.createHorizontalStrut(25));
		
		btnHuy = new RoundedButton(main.read_file_languages.getString("btnHuy"), null, 20, 0, 0.6f);
		btnHuy.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBackground(Color.decode("#ffc107"));
		btnHuy.setIcon(new ImageScaler("/image/cancelHD_Icon.png", 24, 24).getScaledImageIcon());
		btnHuy.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnHuy);
		
		// tạo jpanel chứa table sản phẩm
		JPanel pnlBangSP = new JPanel();
		pnlBangSP.setLayout(new BoxLayout(pnlBangSP, BoxLayout.X_AXIS));
		pnlSanPham.add(pnlBangSP, BorderLayout.CENTER);
		String cols[] = {main.read_file_languages.getString("tbhMaHDSP"),
				main.read_file_languages.getString("lblMaSP"),
				main.read_file_languages.getString("lblTenSP"), 
				main.read_file_languages.getString("lblDVT"), 
				main.read_file_languages.getString("lblSoLuong"), 
				main.read_file_languages.getString("lblDonGia"), 
				main.read_file_languages.getString("lblYeuCau")};
		dtblModelSP = new DefaultTableModel(cols, 0);
		tblSP = new JTable(dtblModelSP);
		
		tbhSP = new JTableHeader(tblSP.getColumnModel());
		tbhSP.setReorderingAllowed(false);
		tbhSP.setBackground(componentColor);
		tbhSP.setForeground(Color.WHITE);
		tbhSP.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblSP.setTableHeader(tbhSP);
		
		tblSP.setRowHeight(20);
		tblSP.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblSP.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblSP.getColumnModel().getColumn(2).setPreferredWidth(175);
		tblSP.getColumnModel().getColumn(3).setPreferredWidth(175);
		tblSP.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblSP.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblSP.getColumnModel().getColumn(6).setPreferredWidth(200);
		
		//Tạo jscrollpane để tạo scroll cho bảng hợp đồng
		JScrollPane scrSP = new JScrollPane(tblSP,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangSP.add(scrSP);
		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnIn.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		
		btnThem.addMouseListener(this);
		btnSua.addMouseListener(this);
		btnXoa.addMouseListener(this);
		btnIn.addMouseListener(this);
		btnLuu.addMouseListener(this);
		btnHuy.addMouseListener(this);
		
		//Không thể thao tác với button lưu và hủy
		displayButtonSaveAndCancel(false);
		
		//Không thể chỉnh sửa txt
		setEditableForTextField(false);
		
		//Set giá trị mặc định để hiển thị
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {

	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		if (o instanceof RoundedButton) {
			setBorderForFocusButton(o);
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
		main.music.playSE(2);
		if(o == btnThem) {
			displayButtonSaveAndCancel(true);
			setEditableForTextField(true);
			xoaRong();
			
		}
		if(o == btnSua) {
			displayButtonSaveAndCancel(true);
			setEditableForTextField(true);
			
		}
		if(o == btnXoa) {
			
		}
		if(o == btnIn) {
		}
		if(o == btnLuu) {
			displayButtonSaveAndCancel(false);
			setEditableForTextField(false);
			
		}
		if(o == btnHuy) {
			displayButtonSaveAndCancel(false);
			setEditableForTextField(false);
			
		}
	}
	private void displayButtonSaveAndCancel(boolean display) {
		if(display == true) {
			btnLuu.setEnabled(true);
			btnLuu.setAlpha(1f);
			btnHuy.setEnabled(true);
			btnHuy.setAlpha(1f);
			
			btnThem.setEnabled(false);
			btnThem.setAlpha(0.6f);
			btnSua.setEnabled(false);
			btnSua.setAlpha(0.6f);
			btnXoa.setEnabled(false);
			btnXoa.setAlpha(0.6f);
			btnIn.setEnabled(false);
			btnIn.setAlpha(0.6f);
			
		}else {
			btnLuu.setEnabled(false);
			btnLuu.setAlpha(0.6f);
			btnHuy.setEnabled(false);
			btnHuy.setAlpha(0.6f);
			
			btnThem.setEnabled(true);
			btnThem.setAlpha(1f);
			btnSua.setEnabled(true);
			btnSua.setAlpha(1f);
			btnXoa.setEnabled(true);
			btnXoa.setAlpha(1f);
			btnIn.setEnabled(true);
			btnIn.setAlpha(1f);
		}
	}
	private void setEditableForTextField(boolean edit) {
		if(edit == true) {
			txtMaHD.setEditable(true);
			txtMaSP.setEditable(true);
			txtTenSP.setEditable(true);
			txtDonGia.setEditable(true);
		}else {
			txtMaHD.setEditable(false);
			txtMaSP.setEditable(false);
			txtTenSP.setEditable(false);
			txtDonGia.setEditable(false);
		}
	}
	private void xoaRong() {
		txtMaHD.setText("");
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtDonGia.setText("");
	}
	//hiển thị border cho button được user nhấn
	//format lai giá trị và tiền cọc
	private String formatMoneyText(String money) {
	    try {
	        double value = Double.parseDouble(money.replaceAll("\\.", ""));
	        DecimalFormat formatter = new DecimalFormat("#,###");
	        return formatter.format(value);
	    } catch (NumberFormatException ex) {
	        // handle exception here
	    }
	    return null;
	}
	//chuyển đổi sang dạng chữ từ trong khoảng trăm - tỷ
	private String formatMoneyToText(Double money) {
		String text = "";
        try {
            if (money < 100) {
            	text = String.valueOf(money);
            } else if (money < 1000) {
            	text = money / 100 + " trăm";
            }else if (money < 1000000) {
            	text = money / 1000 + " nghìn";
            } else if (money < 1000000000) {
            	text = money / 1000000 + " triệu";
            } else {
            	text = money / 1000000000 + " tỷ";
            }
        } catch (NumberFormatException ex) {
            // handle exception here
        }
        return text;
    }
	//hiển thị border cho button được user nhấn
	private void setBorderForFocusButton(Object o) {
		if(btnFocus!=null && btnFocus!=o) {
			btnFocus.setFocusButton(null, 0);
		}
		if(btnFocus==null) {
			btnFocus = (RoundedButton) o;
			btnFocus.setFocusButton(main.borderFocusColor, 3);
		}
		else if(btnFocus == btnThem || btnFocus == btnSua) {
			if(o == btnHuy || o == btnLuu) {
				btnFocus = (RoundedButton) o;
				btnFocus.setFocusButton(main.borderFocusColor, 3);
			}
		}else if(btnFocus == btnLuu || btnFocus == btnHuy) {
			if(o == btnThem || o == btnSua || o == btnXoa || o == btnIn) {
				btnFocus = (RoundedButton) o;
				btnFocus.setFocusButton(main.borderFocusColor, 3);
			}
		}else {
			btnFocus = (RoundedButton) o;
			btnFocus.setFocusButton(main.borderFocusColor, 3);
		}
	}
}
