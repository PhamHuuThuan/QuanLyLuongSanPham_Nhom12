package UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;

public class QuanLyCongDoanUI extends JPanel implements ActionListener, MouseListener{

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
		// TODO Auto-generated method stub
		
	}
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField txtMaSP, txtMaCD, txtTenCD, txtThuTu, txtTinhTrang, txtSoLuong,txtDonGia, txtNgayHT;
	private RoundedButton btnThem, btnSua, btnXoa, btnLuu, btnHuy, btnIn,btnFocus;
	private DefaultTableModel dtblModelCD, dtblModelSP;
	private JTable tblCD, tblSP;
	private JXDatePicker dtpNgayHT;
	private JTableHeader tbhCD, tbhSP;
	private JPanel pnlChucNang; 
	private JTextArea txaTinhTrang;
	private JSpinner spnSoLuong;
	
	public QuanLyCongDoanUI(MainUI main) {
		this.main = main;
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(bgColor);
		
		JPanel pnlCongDoan = new JPanel();
		add(pnlCongDoan, BorderLayout.CENTER);
		pnlCongDoan.setBackground(bgColor);
		pnlCongDoan.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlNorth.setBackground(bgColor);
		pnlCongDoan.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BorderLayout(0, 0));
		
		//Tao jpanel Title
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBackground(bgColor);
		pnlNorth.add(pnlTitle, BorderLayout.NORTH);
		
		//Tiêu đề
		JLabel lblTitle = new JLabel("QUẢN LÝ CÔNG ĐOẠN");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle.add(lblTitle);
		
		//Danh sách Cong Doan
		JPanel pnlDSSP = new JPanel();
		pnlDSSP.setLayout(new BorderLayout());
		pnlDSSP.setBackground(bgColor);
		TitledBorder titleBorderDSHD = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), ("Thông tin sản phẩm"));
		pnlDSSP.setBorder(BorderFactory.createCompoundBorder(titleBorderDSHD, BorderFactory.createEmptyBorder(10, 5, 5, 5)));
		add(pnlDSSP, BorderLayout.WEST);
		
		//Bảng Sản phâmr
		String colsSPName[] = {main.read_file_languages.getString("lblMaSP"), 
				main.read_file_languages.getString("lblTenSP"), 
				main.read_file_languages.getString("lblSoLuong"),
				main.read_file_languages.getString("lblDonGia")};
		dtblModelSP = new DefaultTableModel(colsSPName, 0);
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
		
		JScrollPane scrHD = new JScrollPane(tblSP,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlDSSP.add(scrHD, BorderLayout.CENTER);
		
		//Tao jpanel Thong tin công đoạn
		JPanel pnlThongTinCD = new JPanel();
		pnlThongTinCD.setLayout(new BoxLayout(pnlThongTinCD, BoxLayout.Y_AXIS));
		pnlThongTinCD.setBackground(bgColor);
		TitledBorder titleBorderTTSP = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), ("Thông tin công đoạn"));
		titleBorderTTSP.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlThongTinCD.setBorder(BorderFactory.createCompoundBorder(titleBorderTTSP, BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		pnlNorth.add(pnlThongTinCD, BorderLayout.CENTER);
		
		// Tao box chua cac phan tu hang 1
		Box b1 = Box.createHorizontalBox();
		pnlThongTinCD.add(b1);
	
		JLabel lblMaSP = new JLabel("Mã SP");
		lblMaSP.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblMaSP.setForeground(textColor);
		b1.add(lblMaSP);
		b1.add(Box.createHorizontalStrut(10));
		
		txtMaSP = new JTextField();
		txtMaSP.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaSP.setForeground(textColor);
		txtMaSP.setBackground(bgColor);
		txtMaSP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		b1.add(txtMaSP);
		b1.add(Box.createHorizontalStrut(30));
		
	
		JLabel lblMaCD = new JLabel("Mã CD:");
		lblMaCD.setForeground(textColor);
		lblMaCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b1.add(lblMaCD);
		b1.add(Box.createHorizontalStrut(10));
		
		txtMaCD = new JTextField();
		txtMaCD.setForeground(textColor);
		txtMaCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtMaCD.setBackground(bgColor);
		b1.add(txtMaCD);
		b1.add(Box.createHorizontalStrut(30));
		
		JLabel lblTenCD = new JLabel("Tên CD:");
		lblTenCD.setForeground(textColor);
		lblTenCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b1.add(lblTenCD);
		b1.add(Box.createHorizontalStrut(10));
		
		txtTenCD = new JTextField();
		txtTenCD.setForeground(textColor);
		txtTenCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenCD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtTenCD.setBackground(bgColor);
		b1.add(txtTenCD);
		
		pnlThongTinCD.add(Box.createVerticalStrut(20));
		
		//Tao box chua thong tin hang 2: 
		
		Box b2 = Box.createHorizontalBox();
		b2.setBackground(bgColor);
		pnlThongTinCD.add(b2);
		
		JLabel lblThuTu = new JLabel("Thứ tự: ");
		lblThuTu.setForeground(textColor);
		lblThuTu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblThuTu);
		b2.add(Box.createHorizontalStrut(10));
		
		txtThuTu = new JTextField();
		txtThuTu.setForeground(textColor);
		txtThuTu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtThuTu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtThuTu.setBackground(bgColor);
		b2.add(txtThuTu);
		
		b2.add(Box.createHorizontalStrut(15));
		
		JLabel lblDonGia = new JLabel("Đơn giá: ");
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
		JLabel lblSoLuong = new JLabel("Số lượng: ");
		lblSoLuong.setForeground(textColor);
		lblSoLuong.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(lblSoLuong);
		
		b2.add(Box.createHorizontalStrut(15));
		
		SpinnerNumberModel model = new SpinnerNumberModel(10, 1, 1000000, 100);
		spnSoLuong = new JSpinner(model);
		CompoundBorder cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 0, 5, 0));
		spnSoLuong.setBorder(cboBorder);
		spnSoLuong.setBackground(bgColor);
		spnSoLuong.setForeground(textColor);
		spnSoLuong.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b2.add(spnSoLuong);
		b2.add(Box.createHorizontalStrut(75));
		
		pnlThongTinCD.add(Box.createVerticalStrut(20));
		
		Box b3 = Box.createHorizontalBox();
		pnlThongTinCD.add(b3);
		b3.setBackground(bgColor);
		
		JLabel lblTinhTrang = new JLabel("Tình trạng:");
		lblTinhTrang.setForeground(textColor);
		lblTinhTrang.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b3.add(lblTinhTrang);
		b3.add(Box.createHorizontalStrut(35));
		
		txaTinhTrang = new JTextArea();
		txaTinhTrang.setRows(3);
		txaTinhTrang.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txaTinhTrang.setForeground(textColor);
		txaTinhTrang.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		JScrollPane scrYC = new JScrollPane(txaTinhTrang,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		b3.add(scrYC);
		
		//Tao box chua thong tin hang 4: 
		Box b4 = Box.createHorizontalBox();
		pnlThongTinCD.add(b3);
		JLabel lblNgayHT = new JLabel("Ngày hoàn thành:");
		lblNgayHT.setForeground(textColor);
		lblNgayHT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b4.add(lblNgayHT);
		b4.add(Box.createHorizontalStrut(10));
		dtpNgayHT = new JXDatePicker(new Date());
		dtpNgayHT.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtpNgayHT.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dtpNgayHT.setBackground(bgColor);
		dtpNgayHT.setForeground(textColor);
		dtpNgayHT.setLocale(new Locale("vi", "VN")); // set thoi gian local la VN
		JButton btnDateBD = (JButton) dtpNgayHT.getComponent(1);
		btnDateBD.setIcon(new ImageScaler("/image/calendar_icon.png", 18, 18).getScaledImageIcon());
		btnDateBD.setBackground(bgColor);
		btnDateBD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
		BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		dtpNgayHT.getEditor().setBackground(bgColor);
		dtpNgayHT.getEditor().setForeground(textColor);
		b3.add(dtpNgayHT);
		b3.add(Box.createHorizontalStrut(30));

		
		pnlChucNang = new JPanel();
		pnlNorth.add(pnlChucNang, BorderLayout.SOUTH);
		pnlChucNang.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlChucNang.setBackground(bgColor);
		pnlChucNang.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		btnThem = new RoundedButton("Thêm", null, 20, 0, 1.0f);
		btnThem.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(Color.decode("#3B71CA"));
		btnThem.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnThem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnThem);
		pnlChucNang.add(Box.createHorizontalStrut(75));
		btnSua = new RoundedButton("Sửa", null, 20, 0, 1.0f);
		btnSua.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnSua.setForeground(Color.WHITE);
		btnSua.setBackground(Color.decode("#ffc107"));
		btnSua.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnSua.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnSua);
		pnlChucNang.add(Box.createHorizontalStrut(75));
		btnXoa = new RoundedButton("Xóa", null, 20, 0, 1.0f);
		btnXoa.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(Color.decode("#dc3545"));
		btnXoa.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoa);
		pnlChucNang.add(Box.createHorizontalStrut(100));
		btnLuu = new RoundedButton("Lưu", null, 20, 0, 0.6f);
		btnLuu.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setBackground(Color.decode("#28a745"));
		btnLuu.setIcon(new ImageScaler("/image/save_Icon.png", 24, 24).getScaledImageIcon());
		btnLuu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnLuu);
		pnlChucNang.add(Box.createHorizontalStrut(75));
		btnHuy = new RoundedButton("Hủy", null, 20, 0, 0.6f);
		btnHuy.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBackground(Color.decode("#ffc107"));
		btnHuy.setIcon(new ImageScaler("/image/cancelHD_Icon.png", 24, 24).getScaledImageIcon());
		btnHuy.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnHuy);
		
		JPanel pnlBangCD = new JPanel();
		pnlBangCD.setLayout(new BorderLayout());
		pnlCongDoan.add(pnlBangCD, BorderLayout.CENTER);
		String cols[] = {"Mã CĐ", "Tên CĐ", "Thứ tự ", "Đơn giá ", "Số lượng ", "Tình trạng", "Ngày hoàn thành ", "Ghi chú"};
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
		tblCD.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblCD.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblCD.getColumnModel().getColumn(6).setPreferredWidth(200);
		tblCD.getColumnModel().getColumn(7).setPreferredWidth(100);
		// Tạo JScrollPane sau khi đã gắn tblCD với DefaultTableModel
		JScrollPane scrCD = new JScrollPane(tblCD, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangCD.add(scrCD, BorderLayout.CENTER);
		// Tiếp theo, bạn có thể thêm các ActionListener và MouseListener như bạn đã làm trước đó.
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		btnThem.addMouseListener(this);
		btnSua.addMouseListener(this);
		btnXoa.addMouseListener(this);
		btnLuu.addMouseListener(this);
		btnHuy.addMouseListener(this);
	}

}
