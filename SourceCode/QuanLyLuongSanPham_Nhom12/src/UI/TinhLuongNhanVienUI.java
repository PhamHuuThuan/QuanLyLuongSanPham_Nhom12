package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomUI.CustomComboBoxUI;
import CustomUI.CustomListCellRenderer;
import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Dao.ChamCongNhanVien_Dao;
import Dao.PhanCongNhanVien_Dao;
import Dao.PhongBan_Dao;
import Dao.TinhLuongNhanVien_Dao;
import Entity.BangChamCongNhanVien;
import Entity.BangLuongNhanVien;
import Entity.BangPhanCongNhanVien;
import Entity.PhongBan;
import Util.SinhMaTuDong;
import Util.XuatChamCongForm;
import Util.XuatExcel;
import Util.XuatPDF;
import net.sf.jasperreports.engine.JRException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;

public class TinhLuongNhanVienUI extends JPanel implements ActionListener, MouseListener, ItemListener, ChangeListener{
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private RoundedButton btnTinhLuongALL, btnTinhLuong, btnChiTiet, btnXuat, btnFocus;
	private DefaultTableModel dtblModelLuongNV, dtblModelNV;
	private JTable tblLuongNV, tblNV;
	private JTableHeader tbhNVPC, tbhNV;
	private JTextField  txtPhuCap;
	private JComboBox cmbPhongBan;
	private JSpinner spnThangNam;
	private SpinnerModel spnModelThangNam;
	private Date timeDefault;
	private JTextField txtThucLanh;
	private JTextField txtNgay;
	private JTextField txtTangCa;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JLabel lblMessage;
	private ArrayList<BangPhanCongNhanVien> dsPhanCong = new ArrayList<>();
	private ArrayList<BangLuongNhanVien> dsLuong = new ArrayList<>();
	private TinhLuongNhanVien_Dao tinhLuong_Dao = new TinhLuongNhanVien_Dao();
	private PhongBan_Dao pb_Dao = new PhongBan_Dao();
	private ChamCongNhanVien_Dao ccnv_Dao = new ChamCongNhanVien_Dao();
	private PhanCongNhanVien_Dao pcnv_Dao = new PhanCongNhanVien_Dao();
	private SinhMaTuDong maTuDong = new SinhMaTuDong();
	private XuatPDF xuat = new XuatPDF();
	private XuatExcel xuatExcel = new XuatExcel();
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
		
		cmbPhongBan = new JComboBox<>();
		// Tạo một đối tượng DefaultComboBoxModel
		DefaultComboBoxModel modelPB = new DefaultComboBoxModel();
		modelPB.addElement(new PhongBan("PB00", "Tất cả", 0, ""));

		// Lấy danh sách tất cả các phòng ban
		ArrayList<PhongBan> listPB = pb_Dao.getAllPhongBan();

		// Thêm phòng ban vào model
		modelPB.addAll(listPB);
		    
		// Đặt model cho JComboBox
		cmbPhongBan.setModel(modelPB);
		pnlPBvaTime.add(cmbPhongBan);
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
        calendar.add(Calendar.MONTH, -1);
        timeDefault = calendar.getTime();
        Date latestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -1);
        Date earliestDate = calendar.getTime();
       // Giới hạn trên là ngày hiện tại
       spnModelThangNam = new SpinnerDateModel(timeDefault, earliestDate, latestDate, Calendar.MONTH);
        
     // Tạo một JSpinner với model đã tạo
        spnThangNam = new JSpinner(spnModelThangNam);

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
		tblNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 14F));
		
		tblNV.setRowHeight(20);
		tblNV.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblNV.getColumnModel().getColumn(1).setPreferredWidth(75);
		tblNV.getColumnModel().getColumn(2).setPreferredWidth(250);
		tblNV.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblNV.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		//chỉnh trái phải của dữ liệu trong bảng
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tblNV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		
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
		pnThongTinNV.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(10, 20, 10, 10)));
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
		txtMaNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 0, 5, 0)));
		txtMaNV.setBackground(Color.WHITE);
		pnlB1.add(txtMaNV);
		pnlB1.add(Box.createHorizontalStrut(10));
		
		JLabel lblTenNV = new JLabel("Tên NV:");
		pnlB1.add(lblTenNV);
		lblTenNV.setForeground(textColor);
		lblTenNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));

		txtTenNV = new JTextField();
		txtTenNV.setColumns(12);
		txtTenNV.setForeground(Color.BLACK);
		txtTenNV.setForeground(textColor);
		txtTenNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTenNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 0, 5, 0)));
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
		txtNgay.setColumns(8);
		txtNgay.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 0, 5, 0)));
		txtNgay.setBackground(Color.WHITE);
		pnlB2.add(txtNgay);
		
		pnlB2.add(Box.createHorizontalStrut(15));
		
		JLabel lblPhuCap = new JLabel("Phụ cấp:");
		pnlB2.add(lblPhuCap);
		lblPhuCap.setForeground(textColor);
		lblPhuCap.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtPhuCap = new JTextField();
		pnlB2.add(txtPhuCap);
		txtPhuCap.setColumns(8);
		txtPhuCap.setForeground(textColor);
		txtPhuCap.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtPhuCap.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 0, 5, 0)));
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
		txtTangCa.setColumns(8);
		txtTangCa.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 0, 5, 0)));
		txtTangCa.setBackground(Color.WHITE);
		
		pnlB4.add(Box.createHorizontalStrut(10));
		
		JLabel lblThucLanh = new JLabel("Thực lãnh:");
		lblThucLanh.setForeground(Color.decode("#dc3545"));
		lblThucLanh.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		pnlB4.add(lblThucLanh);
		
		txtThucLanh = new JTextField();
		txtThucLanh.setForeground(textColor);
		txtThucLanh.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		txtThucLanh.setColumns(8);
		txtThucLanh.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 0, 5, 0)));
		txtThucLanh.setBackground(Color.WHITE);
		pnlB4.add(txtThucLanh);
		
		lblTenNV.setPreferredSize(lblThucLanh.getPreferredSize());
		lblMaNV.setPreferredSize(lblTangCa.getPreferredSize());
		lblPhuCap.setPreferredSize(lblThucLanh.getPreferredSize());
		
		JPanel pnlMessage = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlMessage.setBackground(bgColor);
		pnlTTRight.add(pnlMessage);
		pnlMessage.add(lblMessage = new JLabel(""));
		lblMessage.setForeground(Color.decode("#dc3545"));
		lblMessage.setFont(main.roboto_regular.deriveFont(Font.ITALIC, 14F));
		
		//Khởi tạo jpanel chức năng chứa các button chức năng
		JPanel pnlChucNang = new JPanel(new FlowLayout());
		pnlChucNang.setBackground(bgColor);
		pnlTTRight.add(pnlChucNang);
		
		btnTinhLuong = new RoundedButton("Phân công", null, 20, 0, 1.0f);
		btnTinhLuong.setText("Tính lương");
		btnTinhLuong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnTinhLuong.setForeground(Color.WHITE);
		btnTinhLuong.setBackground(Color.decode("#3B71CA"));
		btnTinhLuong.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnTinhLuong.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnTinhLuong);
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
		
		String colsPCNV[] = {"STT", "Mã NV", "Họ tên", "Làm", "Nghỉ", "Phép", "Lương tháng", "Tăng ca", "Phụ cấp", "Thực lãnh"};
		dtblModelLuongNV = new DefaultTableModel(colsPCNV, 0);
		tblLuongNV = new JTable(dtblModelLuongNV);

		tbhNVPC = new JTableHeader(tblLuongNV.getColumnModel());
		tbhNVPC.setReorderingAllowed(false);
		tbhNVPC.setBackground(componentColor);
		tbhNVPC.setForeground(Color.WHITE);
		tbhNVPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblLuongNV.setTableHeader(tbhNVPC);
		tblLuongNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 14F));
		
		//kích thước column
		tblLuongNV.setRowHeight(20);
		tblLuongNV.getColumnModel().getColumn(0).setPreferredWidth(25);
		tblLuongNV.getColumnModel().getColumn(1).setPreferredWidth(40);
		tblLuongNV.getColumnModel().getColumn(2).setPreferredWidth(200);
		tblLuongNV.getColumnModel().getColumn(3).setPreferredWidth(40);
		tblLuongNV.getColumnModel().getColumn(4).setPreferredWidth(40);
		tblLuongNV.getColumnModel().getColumn(5).setPreferredWidth(40);
		tblLuongNV.getColumnModel().getColumn(6).setPreferredWidth(100);
		tblLuongNV.getColumnModel().getColumn(7).setPreferredWidth(100);
		tblLuongNV.getColumnModel().getColumn(8).setPreferredWidth(100);
		tblLuongNV.getColumnModel().getColumn(9).setPreferredWidth(100);
		
		//trái phải dữ liệu
		tblLuongNV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		
		tblLuongNV.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tblLuongNV.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		tblLuongNV.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		tblLuongNV.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		tblLuongNV.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		tblLuongNV.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		tblLuongNV.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblLuongNV,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlDSLuong.add(scrSP, BorderLayout.CENTER);
		
		btnTinhLuong.addActionListener(this);
		btnTinhLuongALL.addActionListener(this);
		btnChiTiet.addActionListener(this);
		btnXuat.addActionListener(this);
		
		btnTinhLuong.addMouseListener(this);
		btnTinhLuongALL.addMouseListener(this);
		btnChiTiet.addMouseListener(this);
		btnXuat.addMouseListener(this);
		tblLuongNV.addMouseListener(this);
		tblNV.addMouseListener(this);
		
		spnThangNam.addChangeListener(this);
		cmbPhongBan.addItemListener(this);
		
		//Set giá trị mặc định để hiển thị
		uploadDataToTable();
		xoaRong();
		setEditData(false);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o == tblNV) {
			tblLuongNV.clearSelection();
			xoaRong();
			txtMaNV.setText(tblNV.getValueAt(tblNV.getSelectedRow(), 1).toString());
			txtTenNV.setText(tblNV.getValueAt(tblNV.getSelectedRow(), 2).toString());
		}
		if(o == tblLuongNV) {
			tblNV.clearSelection();
			xoaRong();
			hienThiThongTinLuong();
		}
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
		lblMessage.setText("");
		Object o = e.getSource();
		if(o == btnTinhLuong) {
			tinhLuongNVDuocChon();
		}
		if(o == btnTinhLuongALL) {
			tinhLuongALLNhanVien();
		}
		if(o == btnChiTiet) {
			xuatChiTietLuong();
		}
		if(o == btnXuat) {
			xuatDSLuong();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() ==cmbPhongBan) {
			uploadDataToTable();
		}
		
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == spnThangNam) {
			uploadDataToTable();
		}
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
		else {
			btnFocus = (RoundedButton) o;
			btnFocus.setFocusButton(main.borderFocusColor, 3);
		}
	}
	//thêm một chưa tính lương vào bảng chưa chấm công
	private void themPCNhanVienVaoBangNV(BangPhanCongNhanVien pcnv) {
	    Object[] row = new Object[10];
	    row[0] = dtblModelNV.getRowCount() + 1;  // STT
	    row[1] = pcnv.getNhanVien().getMaNV();  // Mã NV
	    row[2] = pcnv.getNhanVien().getHoTen();  // Họ tên
	    row[3] = pcnv.getPhongBan().getTenPhongBan(); // Phòng ban
	    row[4] = pcnv.getChucVu();  // Chức vụ
	    
	    dtblModelNV.addRow(row);
	}
	//thêm một ds chưa tính lương vào bảng
	private void themTatCaPCNhanVienVaoBangNV(ArrayList<BangPhanCongNhanVien> list) {
		dtblModelNV.setRowCount(0);
	    for (BangPhanCongNhanVien pcnv : list) {
	        themPCNhanVienVaoBangNV(pcnv);
	    }
	}
	//thêm một chưa tính lương vào bảng chưa chấm công
	private void themLuongNVVaoBangLuong(BangLuongNhanVien blnv) {
	    Object[] row = new Object[10];
	    row[0] = dtblModelLuongNV.getRowCount() + 1;  // STT
	    row[1] = blnv.getNhanVien().getMaNV();  // Mã NV
	    row[2] = blnv.getNhanVien().getHoTen();  // Họ tên
	    row[3] = blnv.getNgayLam() + " ngày"; // Số ngày làm
	    row[4] = blnv.getNgayNghi()+ " ngày"; // Số ngày nghỉ k phép
	    row[5] = blnv.getNgayNghiPhep()+ " ngày"; // Số ngày nghỉ có phép
	    row[6] = new DecimalFormat("#,###").format(blnv.getLuongThang()) + " VNĐ"; // Lương tháng
	    row[7] = new DecimalFormat("#,###").format(blnv.getLuongTangCa()) + " VNĐ"; // tăng ca
	    row[8] = new DecimalFormat("#,###").format(blnv.getPhuCap()) + " VNĐ";  // Phụ cấp
	    row[9] = new DecimalFormat("#,###").format(blnv.getThucLanh()) + " VNĐ";  // Thực lãnh
	    
	    dtblModelLuongNV.addRow(row);
	}
	//thêm một ds chưa tính lương vào bảng
	private void themTatCaLuongNVVaoBangLuong(ArrayList<BangLuongNhanVien> list) {
		dtblModelLuongNV.setRowCount(0);
	    for (BangLuongNhanVien blnv : list) {
	        themLuongNVVaoBangLuong(blnv);
	    }
	}
	//Get thang Nam từ giao diện thành chuỗi
	private String thangNamString() {
		int month = ((Date) spnModelThangNam.getValue()).getMonth()+1; //lấy tháng từ spinner tháng bắt đầu từ 0 
		int year = ((Date) spnModelThangNam.getValue()).getYear()+1900; //lấy năm từ spinner năm bắt đầu từ 1900
		String thangNam = (month<10)?("0"+month):(month)+"";
		thangNam += "/" + (year);
		return thangNam;
	}
	//cập nhật lại 2 table
	private void uploadDataToTable() {
		String thangNam = thangNamString();
		if(cmbPhongBan.getSelectedIndex()!=0) {
			PhongBan pb = (PhongBan) cmbPhongBan.getSelectedItem();
			dsPhanCong = tinhLuong_Dao.getDSChuaTinhLuong(pb.getMaPhongBan(), thangNam);
			dsLuong = tinhLuong_Dao.getDSTinhLuong(pb.getMaPhongBan(), thangNam);
		}else {
			dsPhanCong = tinhLuong_Dao.getDSChuaTinhLuong(null, thangNam);
			dsLuong = tinhLuong_Dao.getDSTinhLuong(null, thangNam);
		}
		themTatCaPCNhanVienVaoBangNV(dsPhanCong);
		themTatCaLuongNVVaoBangLuong(dsLuong);
	}
	//xóa rỗng thông tin trên giao diện
	private void xoaRong() {
		txtMaNV.setText("");
		txtTenNV.setText("");
		txtNgay.setText("0");
		txtPhuCap.setText("0");
		txtTangCa.setText("0");
		txtThucLanh.setText("0");
	}
	//không cho phép chỉnh sửa dữ liệu
	private void setEditData(boolean edit) {
		if(edit == false) {
			txtMaNV.setEditable(false);
			txtTenNV.setEditable(false);
			txtNgay.setEditable(false);
			txtPhuCap.setEditable(false);
			txtTangCa.setEditable(false);
			txtThucLanh.setEditable(false);
		}
	}
	//hiển thị thông tin từ bảng lương lên
	private void hienThiThongTinLuong() {
		int index = tblLuongNV.getSelectedRow();
		txtMaNV.setText(tblLuongNV.getValueAt(index, 1).toString());
		txtTenNV.setText(tblLuongNV.getValueAt(index, 2).toString());
		txtNgay.setText(tblLuongNV.getValueAt(index, 3).toString());
		txtPhuCap.setText(tblLuongNV.getValueAt(index, 8).toString());
		txtTangCa.setText(tblLuongNV.getValueAt(index, 7).toString());
		txtThucLanh.setText(tblLuongNV.getValueAt(index, 9).toString());
	}
	//tính lương cho một nhân viên
	private void tinhLuongMotNhanVien(BangPhanCongNhanVien pcnv, String thangNam) {
		ArrayList<BangChamCongNhanVien> dscc = new ArrayList<>();
		dscc = ccnv_Dao.getDSChamCongNhanVien(pcnv.getNhanVien().getMaNV(), thangNam);
		
		String maBL = maTuDong.sinhMaBLNV();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth ym = YearMonth.parse(thangNam, formatter);
		BangLuongNhanVien blnv = new BangLuongNhanVien(maBL, pcnv.getNhanVien(), dscc, ym, null);
		blnv.tinhLuongNhanVien(pcnv);
		
		if(tinhLuong_Dao.themBangLuongNhanVien(blnv)) {
			lblMessage.setText("Tính lương thành công!!!");
			uploadDataToTable();
		}else {
			setTextError("Tính lương thất bại!!! Đã xảy ra lỗi!!!");
		}	
	}
	//tính lương tất cả nhân viên
	private void tinhLuongALLNhanVien() {
		if(dsPhanCong.size()>0) {
			String thangNam = thangNamString();
			for(BangPhanCongNhanVien pcnv : dsPhanCong) {
				tinhLuongMotNhanVien(pcnv, thangNam);
			}
			lblMessage.setText("Tính lương tất cả nhân viên thành công!!!");
		}else {
			setTextError("Không còn nhân viên nào cần tính lương!!!");
		}
	}
	//tính lương nhân viên được chọn
	private void tinhLuongNVDuocChon() {
		int index = tblNV.getSelectedRow();
		if(index!=-1) {
			String thangNam = thangNamString();
			tinhLuongMotNhanVien(dsPhanCong.get(index), thangNam);
		}else {
			setTextError("Bạn cần chọn nhân viên muốn tính lương!!!");
		}
	}
	// thông báo lỗi
	private void setTextError(String message) {
		main.music.playSE(3);
		lblMessage.setText(message);
	}
	//xuất chi tiết lương của một nhân viên
	private void xuatChiTietLuong() {
		int index = tblLuongNV.getSelectedRow();
		if(index!=-1) {			
			BangLuongNhanVien blnv = dsLuong.get(index);
			ArrayList<BangChamCongNhanVien> dscc = new ArrayList<>();
			dscc = ccnv_Dao.getDSChamCongNhanVien(blnv.getNhanVien().getMaNV(), DateTimeFormatter.ofPattern("MM/yyyy").format(blnv.getThangNam()));
			try {
				xuat.xuatChiTietLuong(dscc, blnv, pcnv_Dao.timPCNhanVien(blnv.getNhanVien().getMaNV()));
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			setTextError("Bạn cần chọn nhân viên muốn xem chi tiết lương!!!");
		}
	}
	//xuất danh sách lương ra excel
	private void xuatDSLuong() {
		if(dsLuong.size()>0) {
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Chỉ cho phép chọn thư mục
	        int option = fileChooser.showSaveDialog(null);
	        if(option == JFileChooser.APPROVE_OPTION){
	           File file = fileChooser.getSelectedFile();
	           String saveDir = file.getAbsolutePath(); // Đây là thư mục mà người dùng đã chọn
	           try {
	        	   String filePath = saveDir + File.separator + "BangLuongThang-" + thangNamString().replaceAll("/", "-") + ".xlsx";
					xuatExcel.writeExcelTTLuong(dsLuong, filePath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }else{
	           setTextError("Phải chọn thư mục lưu file!");
	        }
		}else{
			setTextError("Không có thông tin nào để xuất!");
		}
	}
}
