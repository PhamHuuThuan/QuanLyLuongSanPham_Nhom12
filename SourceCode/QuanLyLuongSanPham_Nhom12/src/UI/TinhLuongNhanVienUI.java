package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
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
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;

public class TinhLuongNhanVienUI extends JPanel implements ActionListener, MouseListener{
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private RoundedButton btnTinhLuongALL, btnChamCong, btnChiTiet, btnXuat, btnFocus;
	private DefaultTableModel dtblModelNVPC, dtblModelNV;
	private JTable tblNVPC, tblNV;
	private JTableHeader tbhNVPC, tbhNV;
	private JTextField  txtPhuCap;
	private JComboBox cmbPhongBan;
	private JSpinner spnGioDen;
	private JTextField txtThucLanh;
	private JTextField txtNgay;
	private JTextField txtTangCa;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	/**
	 * Create the panel.
	 */
	public TinhLuongNhanVienUI(MainUI main) {
		this.main = main;
		
		//set gia tri cho jpanel Tinh Luong
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(bgColor);
		
		JPanel pnlTTTinhLuong = new JPanel();
		add(pnlTTTinhLuong, BorderLayout.CENTER);
		pnlTTTinhLuong.setLayout(new BorderLayout(0, 0));
		pnlTTTinhLuong.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pnlTTTinhLuong.setBackground(bgColor);
		
		//tao jpanel chua Title va Thong tin luong
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlNorth.setBackground(bgColor);
		pnlTTTinhLuong.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BorderLayout(0, 0));
		
		//Tao jpanel Title
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		pnlTitle.setBackground(bgColor);
		pnlNorth.add(pnlTitle, BorderLayout.NORTH);
		
		//Tiêu đề
		JLabel lblTitle = new JLabel("TÍNH LƯƠNG NHÂN VIÊN");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle.add(lblTitle);
		
		//Tạo hộp thoại Tính lương
		Box b = Box.createHorizontalBox();
		b.setBackground(bgColor);
		pnlNorth.add(b, BorderLayout.CENTER);
		
		// tạo jpanel chứa table nhân viên
		JPanel pnlBangNV = new JPanel();
		TitledBorder titleBorderTTNV = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Danh sách nhân viên");
		titleBorderTTNV.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNV.setBorder(BorderFactory.createCompoundBorder(titleBorderTTNV, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		pnlBangNV.setLayout(new BorderLayout());
		pnlBangNV.setBackground(bgColor);
		pnlBangNV.setPreferredSize(new Dimension(900, 300));
		b.add(pnlBangNV);
		b.add(Box.createHorizontalStrut(50));
		
		JPanel pnlPBvaTime = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlPBvaTime.setBackground(bgColor);
		pnlPBvaTime.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));
		pnlBangNV.add(pnlPBvaTime, BorderLayout.NORTH);
		
		JLabel lblPhongBan = new JLabel("Phòng ban:");
		pnlPBvaTime.add(lblPhongBan);
		lblPhongBan.setForeground(textColor);
		lblPhongBan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		cmbPhongBan = new JComboBox();
		pnlPBvaTime.add(cmbPhongBan);
		cmbPhongBan.setModel(new DefaultComboBoxModel(new String[] {"Nhân sự", "Kế toán"}));
		Border cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbPhongBan.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbPhongBan.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbPhongBan.setBackground(bgColor);
		cmbPhongBan.setForeground(textColor);
		cmbPhongBan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlPBvaTime.add(Box.createHorizontalStrut(30));
		
		JLabel lblNgayThang = new JLabel("Thời gian tính:");
		pnlPBvaTime.add(lblNgayThang);
		lblNgayThang.setForeground(textColor);
		lblNgayThang.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
        // Tạo một SpinnerDateModel
        Calendar calendar = Calendar.getInstance();
        Date initDate = calendar.getTime();
        Date latestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -2);
        Date earliestDate = calendar.getTime();
       // Giới hạn trên là ngày hiện tại
        SpinnerDateModel model = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.MONTH);
        
     // Tạo một JSpinner với model đã tạo
        JSpinner spnThangNam = new JSpinner(model);

        // Đặt định dạng hiển thị cho JSpinner
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spnThangNam, "MM/yyyy");
        spnThangNam.setEditor(editor);
        
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10));
		spnThangNam.setBorder(cboBorder);
		spnThangNam.setBackground(bgColor);
		spnThangNam.setForeground(textColor);
		spnThangNam.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		spnThangNam.setPreferredSize(cmbPhongBan.getPreferredSize());
        
        pnlPBvaTime.add(spnThangNam);
        pnlPBvaTime.add(Box.createHorizontalStrut(30));
		
		btnTinhLuongALL = new RoundedButton("Chấm tất cả", null, 20, 0, 1.0f);
		btnTinhLuongALL.setText("Tính lương tất cả");
		btnTinhLuongALL.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnTinhLuongALL.setForeground(Color.WHITE);
		btnTinhLuongALL.setBackground(Color.decode("#28a745"));
		btnTinhLuongALL.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnTinhLuongALL.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlPBvaTime.add(btnTinhLuongALL);
		
		String cols[] = {"STT", "Mã NV", "Họ tên", "Phòng ban", "Chức vụ"};
		dtblModelNV = new DefaultTableModel(cols, 0);
		tblNV = new JTable(dtblModelNV);

		tbhNV = new JTableHeader(tblNV.getColumnModel());
		tbhNV.setReorderingAllowed(false);
		tbhNV.setBackground(componentColor);
		tbhNV.setForeground(Color.WHITE);
		tbhNV.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblNV.setTableHeader(tbhNV);
		
		tblNV.setRowHeight(20);
		tblNV.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblNV.getColumnModel().getColumn(1).setPreferredWidth(75);
		tblNV.getColumnModel().getColumn(2).setPreferredWidth(150);
		tblNV.getColumnModel().getColumn(3).setPreferredWidth(150);
		tblNV.getColumnModel().getColumn(4).setPreferredWidth(150);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrNV = new JScrollPane(tblNV,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNV.add(scrNV, BorderLayout.CENTER);
		
		
		//Tao jpanel Thong tin cham cong
		JPanel pnThongTinNV = new JPanel();
		pnThongTinNV.setLayout(new BorderLayout());
		pnThongTinNV.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Thông tin lương");
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnThongTinNV.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(10, 30, 10, 30)));
		pnThongTinNV.setPreferredSize(new Dimension(600, 300));
		b.add(pnThongTinNV);
		
		
		JPanel pnlTTRight = new JPanel();
		pnlTTRight.setLayout(new BoxLayout(pnlTTRight, BoxLayout.Y_AXIS));
		pnlTTRight.setBackground(bgColor);
		pnThongTinNV.add(pnlTTRight, BorderLayout.CENTER);
		
		JPanel pnlB1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB1.setBackground(bgColor);
		pnlTTRight.add(pnlB1);
		
		JLabel lblMaNV = new JLabel("Mã NV:");
		lblMaNV.setForeground(textColor);
		lblMaNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB1.add(lblMaNV);
		
		txtMaNV = new JTextField();
		txtMaNV.setForeground(textColor);
		txtMaNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaNV.setColumns(8);
		txtMaNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtMaNV.setBackground(Color.WHITE);
		pnlB1.add(txtMaNV);
		pnlB1.add(Box.createHorizontalStrut(20));
		
		JLabel lblTenNV = new JLabel("Tên NV:");
		pnlB1.add(lblTenNV);
		lblTenNV.setForeground(textColor);
		lblTenNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));

		txtTenNV = new JTextField();
		txtTenNV.setColumns(8);
		txtTenNV.setForeground(Color.BLACK);
		txtTenNV.setForeground(textColor);
		txtTenNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtTenNV.setBackground(Color.WHITE);
		pnlB1.add(txtTenNV);
		
		JPanel pnlB2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB2.setBackground(bgColor);
		pnlTTRight.add(pnlB2);
		
		JLabel lblCongLam = new JLabel("Số ngày:");
		pnlB2.add(lblCongLam);
		lblCongLam.setForeground(textColor);
		lblCongLam.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtNgay = new JTextField();
		txtNgay.setForeground(textColor);
		txtNgay.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtNgay.setColumns(2);
		txtNgay.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtNgay.setBackground(Color.WHITE);
		pnlB2.add(txtNgay);
		
		JLabel lblNgay = new JLabel("ngày");
		lblNgay.setForeground(textColor);
		lblNgay.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB2.add(lblNgay);
		
		pnlB2.add(Box.createHorizontalStrut(67));
		
		JLabel lblPhuCap = new JLabel("Phụ cấp:");
		pnlB2.add(lblPhuCap);
		lblPhuCap.setForeground(textColor);
		lblPhuCap.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtPhuCap = new JTextField();
		pnlB2.add(txtPhuCap);
		txtPhuCap.setColumns(8);
		txtPhuCap.setForeground(textColor);
		txtPhuCap.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtPhuCap.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtPhuCap.setBackground(bgColor);
		
		JPanel pnlB4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB4.setBackground(bgColor);
		pnlTTRight.add(pnlB4);
		
		JLabel lblTangCa = new JLabel("Tăng ca:");
		pnlB4.add(lblTangCa);
		lblTangCa.setForeground(textColor);
		lblTangCa.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtTangCa = new JTextField();
		pnlB4.add(txtTangCa);
		txtTangCa.setForeground(textColor);
		txtTangCa.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTangCa.setColumns(2);
		txtTangCa.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtTangCa.setBackground(Color.WHITE);
		
		JLabel lblGio = new JLabel("giờ");
		pnlB4.add(lblGio);
		lblGio.setForeground(textColor);
		lblGio.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		pnlB4.add(Box.createHorizontalStrut(30));
		
		JLabel lblThucLanh = new JLabel("Thực lãnh:");
		lblThucLanh.setForeground(Color.decode("#17a2b8"));
		lblThucLanh.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		pnlB4.add(lblThucLanh);
		
		txtThucLanh = new JTextField();
		txtThucLanh.setForeground(textColor);
		txtThucLanh.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		txtThucLanh.setColumns(8);
		txtThucLanh.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtThucLanh.setBackground(Color.WHITE);
		pnlB4.add(txtThucLanh);
		
		JLabel lblVND = new JLabel("VNĐ");
		lblVND.setForeground(textColor);
		lblVND.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB4.add(lblVND);
		
		lblTenNV.setPreferredSize(lblThucLanh.getPreferredSize());
		lblMaNV.setPreferredSize(lblTangCa.getPreferredSize());
		lblPhuCap.setPreferredSize(lblThucLanh.getPreferredSize());
		
		//Khởi tạo jpanel chức năng chứa các button chức năng
		JPanel pnlChucNang = new JPanel(new FlowLayout());
		pnlChucNang.setBackground(bgColor);
		pnlTTRight.add(pnlChucNang);
		
		btnChamCong = new RoundedButton("Phân công", null, 20, 0, 1.0f);
		btnChamCong.setText("Tính lương");
		btnChamCong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnChamCong.setForeground(Color.WHITE);
		btnChamCong.setBackground(Color.decode("#3B71CA"));
		btnChamCong.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnChamCong.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnChamCong);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnChiTiet = new RoundedButton("Cập Nhật", null, 20, 0, 1.0f);
		btnChiTiet.setText("Xem chi tiết");
		btnChiTiet.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnChiTiet.setForeground(Color.WHITE);
		btnChiTiet.setBackground(Color.decode("#ffc107"));
		btnChiTiet.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnChiTiet.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnChiTiet);
		
		// tạo jpanel chứa table phân công nhân viên
		JPanel pnlDSLuong = new JPanel();
		TitledBorder titleBorderTTNVPC = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Danh sách lương nhân viên");
		titleBorderTTNVPC.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlDSLuong.setBorder(BorderFactory.createCompoundBorder(titleBorderTTNVPC, BorderFactory.createEmptyBorder(0, 20, 10, 20)));
		pnlDSLuong.setLayout(new BorderLayout());
		pnlDSLuong.setBackground(bgColor);
		pnlTTTinhLuong.add(pnlDSLuong, BorderLayout.CENTER);
		
		JPanel pnlXuat = new JPanel();
		pnlXuat.setBackground(bgColor);
		pnlXuat.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlDSLuong.add(pnlXuat, BorderLayout.NORTH);
		
		btnXuat = new RoundedButton("Xuất DS", null, 5, 0, 1.0f);
		btnXuat.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setBackground(Color.decode("#28a745"));
		btnXuat.setIcon(new ImageScaler("/image/printer_icon.png", 20, 20).getScaledImageIcon());
		btnXuat.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		pnlXuat.add(btnXuat);
		
		String colsPCNV[] = {"STT", "Mã NV", "Họ tên", "Chức vụ", "Làm", "Nghỉ", "Phép", "Lương tháng", "Tăng ca", "Phụ cấp", "Thực lãnh"};
		dtblModelNVPC = new DefaultTableModel(colsPCNV, 0);
		tblNVPC = new JTable(dtblModelNVPC);

		tbhNVPC = new JTableHeader(tblNVPC.getColumnModel());
		tbhNVPC.setReorderingAllowed(false);
		tbhNVPC.setBackground(componentColor);
		tbhNVPC.setForeground(Color.WHITE);
		tbhNVPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblNVPC.setTableHeader(tbhNVPC);
		
		tblNVPC.setRowHeight(20);
		tblNVPC.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblNVPC.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblNVPC.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblNVPC.getColumnModel().getColumn(3).setPreferredWidth(75);
		tblNVPC.getColumnModel().getColumn(4).setPreferredWidth(50);
		tblNVPC.getColumnModel().getColumn(5).setPreferredWidth(50);
		tblNVPC.getColumnModel().getColumn(6).setPreferredWidth(50);
		tblNVPC.getColumnModel().getColumn(7).setPreferredWidth(100);
		tblNVPC.getColumnModel().getColumn(8).setPreferredWidth(100);
		tblNVPC.getColumnModel().getColumn(9).setPreferredWidth(100);
		tblNVPC.getColumnModel().getColumn(10).setPreferredWidth(100);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblNVPC,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlDSLuong.add(scrSP, BorderLayout.CENTER);
		
		btnChamCong.addActionListener(this);
		btnChiTiet.addActionListener(this);
		
		btnChamCong.addMouseListener(this);
		btnChiTiet.addMouseListener(this);
		
		
		//Set giá trị mặc định để hiển thị
		
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
		if(o == btnChamCong) {
			
		}
		if(o == btnChiTiet) {

			
		}
	}
}
