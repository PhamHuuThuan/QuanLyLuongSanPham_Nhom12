package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.CustomComboBoxUI;
import CustomUI.CustomListCellRenderer;
import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Dao.ChamCongNhanVien_Dao;
import Dao.PhanCongNhanVien_Dao;
import Dao.PhongBan_Dao;
import Entity.BangChamCongNhanVien;
import Entity.BangPhanCongNhanVien;
import Entity.PhongBan;
import Util.XuatExcel;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Component;

public class ChamCongNhanVienUI extends JPanel implements ActionListener, MouseListener, ItemListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private RoundedButton btnChamCongALL, btnChamCong, btnChamLai, btnXuat,  btnXoa, btnFocus;
	private DefaultTableModel dtblModelDSCC, dtblModelDSPC;
	private JTable tblDSCC, tblDSPC;
	private JTableHeader tbhNVPC, tbhNV;
	private JTextField  txtGhiChu;
	private JComboBox<PhongBan> cmbPhongBan;
	private JXDatePicker dtbNgayCC;
	private JComboBox cmbCaLam, cmbTrangThai;
	private JSpinner spnGioDen, spnTangCa;
	private SpinnerModel spnModelGioDen;
	private JTextField txtMaNV;
	private JLabel lblMessage;
	private PhanCongNhanVien_Dao pcnv_Dao = new PhanCongNhanVien_Dao();
	private ChamCongNhanVien_Dao ccnv_Dao = new ChamCongNhanVien_Dao();
	private PhongBan_Dao pb_Dao = new PhongBan_Dao();
	private Date timeDefault;
	private ArrayList<BangPhanCongNhanVien> dsChuaCC = new ArrayList<>();
	private ArrayList<BangChamCongNhanVien> dsChamCong = new ArrayList<>();
	private XuatExcel xuatExcel = new XuatExcel();
	/**
	 * Create the panel.
	 */
	public ChamCongNhanVienUI(MainUI main) {
		this.main = main;
		
		//set gia tri cho jpanel SanPham
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(bgColor);
		
		JPanel pnlNhanVien = new JPanel();
		add(pnlNhanVien, BorderLayout.CENTER);
		pnlNhanVien.setLayout(new BorderLayout(0, 0));
		pnlNhanVien.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pnlNhanVien.setBackground(bgColor);
		
		//tao jpanel chua Title va Thong tin san pham
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlNorth.setBackground(bgColor);
		pnlNhanVien.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BorderLayout(0, 0));
		
		//Tao jpanel Title
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		pnlTitle.setBackground(bgColor);
		pnlNorth.add(pnlTitle, BorderLayout.NORTH);
		
		//Tiêu đề
		JLabel lblTitle = new JLabel( main.read_file_languages.getString("text_header_CCNV"));
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle.add(lblTitle);
		
		//Tạo hộp thoại phân công
		Box b = Box.createHorizontalBox();
		b.setBackground(bgColor);
		pnlNorth.add(b, BorderLayout.CENTER);
		
		// tạo jpanel chứa table nhân viên
		JPanel pnlBangNV = new JPanel();
		TitledBorder titleBorderTTNV = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("text_border_DSNV"));
		titleBorderTTNV.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNV.setBorder(BorderFactory.createCompoundBorder(titleBorderTTNV, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		pnlBangNV.setLayout(new BorderLayout());
		pnlBangNV.setBackground(bgColor);
		pnlBangNV.setPreferredSize(new Dimension(1000, 300));
		b.add(pnlBangNV);
		b.add(Box.createHorizontalStrut(50));
		
		JPanel pnlPBvaTime = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlPBvaTime.setBackground(bgColor);
		pnlPBvaTime.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));
		pnlBangNV.add(pnlPBvaTime, BorderLayout.NORTH);
		
		JLabel lblPhongBan = new JLabel( main.read_file_languages.getString("lblPhongBan1") + ":");
		pnlPBvaTime.add(lblPhongBan);
		lblPhongBan.setForeground(textColor);
		lblPhongBan.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		cmbPhongBan = new JComboBox<>();
		// Tạo một đối tượng DefaultComboBoxModel
		DefaultComboBoxModel modelPB = new DefaultComboBoxModel();
		modelPB.addElement(new PhongBan("PB00", main.read_file_languages.getString("lblALL"), 0, ""));

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
		pnlPBvaTime.add(Box.createHorizontalStrut(20));
		
		JLabel lblNgayCham = new JLabel(main.read_file_languages.getString("lblNgayCham") + ":" );
		pnlPBvaTime.add(lblNgayCham);
		lblNgayCham.setForeground(textColor);
		lblNgayCham.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		dtbNgayCC = new JXDatePicker(new Date());
		pnlPBvaTime.add(dtbNgayCC);
		pnlPBvaTime.add(Box.createHorizontalStrut(20));
		dtbNgayCC.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtbNgayCC.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dtbNgayCC.setLocale(new Locale("vi", "VN"));	// set thoi gian local la VN
		dtbNgayCC.getEditor().setBackground(bgColor);
		dtbNgayCC.getEditor().setForeground(textColor);
		dtbNgayCC.getMonthView().setUpperBound(new Date());
		JButton btnDateNVL = (JButton) dtbNgayCC.getComponent(1);
		btnDateNVL.setIcon(new ImageScaler("/image/calendar_icon.png", 18, 18).getScaledImageIcon());
		btnDateNVL.setBackground(bgColor);
		btnDateNVL.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		cmbPhongBan.setPreferredSize(dtbNgayCC.getPreferredSize());
		
		btnChamCongALL = new RoundedButton(main.read_file_languages.getString("btnChamALL"), null, 20, 0, 1.0f);
		btnChamCongALL.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnChamCongALL.setForeground(Color.WHITE);
		btnChamCongALL.setBackground(Color.decode("#28a745"));
		btnChamCongALL.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnChamCongALL.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlPBvaTime.add(btnChamCongALL);
		
		String cols[] = {main.read_file_languages.getString("stt"),
				main.read_file_languages.getString("lblMaNV"), 
				main.read_file_languages.getString("lblHoTen"), 
				main.read_file_languages.getString("lblPhongBan1"), 
				main.read_file_languages.getString("lblChucVu")};
		dtblModelDSPC = new DefaultTableModel(cols, 0);
		tblDSPC = new JTable(dtblModelDSPC);

		tbhNV = new JTableHeader(tblDSPC.getColumnModel());
		tbhNV.setReorderingAllowed(false);
		tbhNV.setBackground(componentColor);
		tbhNV.setForeground(Color.WHITE);
		tbhNV.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblDSPC.setTableHeader(tbhNV);
		tblDSPC.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 14F));
		
		tblDSPC.setRowHeight(20);
		tblDSPC.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblDSPC.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblDSPC.getColumnModel().getColumn(2).setPreferredWidth(275);
		tblDSPC.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblDSPC.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		//chỉnh trái phải của dữ liệu trong bảng
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tblDSPC.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrNV = new JScrollPane(tblDSPC,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNV.add(scrNV, BorderLayout.CENTER);
		
		
		//Tao jpanel Thong tin cham cong
		JPanel pnThongTinCC = new JPanel();
		pnThongTinCC.setLayout(new BorderLayout());
		pnThongTinCC.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("text_border_TTCC"));
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnThongTinCC.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(10, 30, 10, 30)));
		pnThongTinCC.setPreferredSize(new Dimension(600, 300));
		b.add(pnThongTinCC);
		
		JPanel pnlAnhDaiDien = new JPanel();
		pnlAnhDaiDien.setLayout(new BoxLayout(pnlAnhDaiDien, BoxLayout.Y_AXIS));
		pnlAnhDaiDien.setBackground(bgColor);
		pnThongTinCC.add(pnlAnhDaiDien, BorderLayout.WEST);
		pnlAnhDaiDien.add(Box.createVerticalStrut(100));
		
		
		JPanel pnlTTRight = new JPanel();
		pnlTTRight.setLayout(new BoxLayout(pnlTTRight, BoxLayout.Y_AXIS));
		pnlTTRight.setBackground(bgColor);
		pnThongTinCC.add(pnlTTRight, BorderLayout.CENTER);
		
		JPanel pnlB1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB1.setBackground(bgColor);
		pnlTTRight.add(pnlB1);
		
		JLabel lblCaLam = new JLabel(main.read_file_languages.getString("lblCaLam") + ":");
		lblCaLam.setForeground(textColor);
		lblCaLam.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB1.add(lblCaLam);
		
		cmbCaLam = new JComboBox<>();
		cmbCaLam.setModel(new DefaultComboBoxModel(new String[] {"Cả ngày (8h)","Nửa ngày (4h)"}));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbCaLam.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbCaLam.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbCaLam.setBackground(bgColor);
		cmbCaLam.setForeground(textColor);
		cmbCaLam.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB1.add(cmbCaLam);
		pnlB1.add(Box.createHorizontalStrut(20));
		
		JLabel lblTrangThai = new JLabel(main.read_file_languages.getString("lblTrangThai") + ":");
		pnlB1.add(lblTrangThai);
		lblTrangThai.setForeground(textColor);
		lblTrangThai.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		cmbTrangThai = new JComboBox<>();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Đúng giờ", "Trễ", "Nghỉ 0 phép", "Nghỉ phép"}));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbTrangThai.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbTrangThai.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbTrangThai.setBackground(bgColor);
		cmbTrangThai.setForeground(textColor);
		cmbTrangThai.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlB1.add(cmbTrangThai);
		
		JPanel pnlB2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB2.setBackground(bgColor);
		pnlTTRight.add(pnlB2);
		
		JLabel lblGioDen = new JLabel(main.read_file_languages.getString("lblGioDen") + ":");
		pnlB2.add(lblGioDen);
		lblGioDen.setForeground(textColor);
		lblGioDen.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 10, 0, 5));
		
        // Tạo một Calendar để thiết lập thời gian
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 6); // Thiết lập giờ là 7
        calendar.set(Calendar.MINUTE, 0); // Thiết lập phút là 0
        Date startDate = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 18); // Giờ kết thúc
        Date endDate = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 7); // Giờ hiện tại
        timeDefault = calendar.getTime();

        // Tạo JSpinner với SpinnerDateModel và giá trị mặc định đã thiết lập
        spnModelGioDen = new SpinnerDateModel(timeDefault, null, null, Calendar.HOUR_OF_DAY);
        spnGioDen = new JSpinner(spnModelGioDen);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spnGioDen, "HH:mm");
        spnGioDen.setEditor(editor);
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10));
		spnGioDen.setBorder(cboBorder);
		spnGioDen.setBackground(bgColor);
		spnGioDen.setForeground(textColor);
		spnGioDen.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		spnGioDen.setPreferredSize(cmbCaLam.getPreferredSize());
        pnlB2.add(spnGioDen);
		pnlB2.add(Box.createHorizontalStrut(20));
		
		JLabel lblTangCa = new JLabel(main.read_file_languages.getString("lblTangCa") + ":");
		pnlB2.add(lblTangCa);
		lblTangCa.setForeground(textColor);
		lblTangCa.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 8, 4);
		spnTangCa = new JSpinner(model);
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10));
		spnTangCa.setBorder(cboBorder);
		spnTangCa.setBackground(bgColor);
		spnTangCa.setForeground(textColor);
		spnTangCa.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		spnTangCa.setPreferredSize(cmbTrangThai.getPreferredSize());
		pnlB2.add(spnTangCa);
		
		JLabel lblGio = new JLabel(main.read_file_languages.getString("lblHour"));
		pnlB2.add(lblGio);
		lblGio.setForeground(textColor);
		lblGio.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		JPanel pnlB4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB4.setBackground(bgColor);
		pnlTTRight.add(pnlB4);
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 10, 0, 5));
		
		JLabel lblMaNV = new JLabel(main.read_file_languages.getString("lblMaNV") + ":");
		lblMaNV.setForeground(textColor);
		lblMaNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		lblMaNV.setPreferredSize(lblGioDen.getPreferredSize());
		pnlB4.add(lblMaNV);
		
		txtMaNV = new JTextField();
		txtMaNV.setForeground(textColor);
		txtMaNV.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtMaNV.setPreferredSize(cmbCaLam.getPreferredSize());
		txtMaNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtMaNV.setBackground(Color.WHITE);
		txtMaNV.setEditable(false);
		pnlB4.add(txtMaNV);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		pnlB4.add(horizontalStrut);
		
		JLabel lblGhiChu = new JLabel(main.read_file_languages.getString("lblGhiChu") + ":");
		pnlB4.add(lblGhiChu);
		lblGhiChu.setPreferredSize(lblTrangThai.getPreferredSize());
		lblGhiChu.setForeground(textColor);
		lblGhiChu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		txtGhiChu = new JTextField();
		txtGhiChu.setPreferredSize(cmbCaLam.getPreferredSize());
		txtGhiChu.setForeground(textColor);
		txtGhiChu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtGhiChu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtGhiChu.setBackground(bgColor);
		pnlB4.add(txtGhiChu);
		
		lblTangCa.setPreferredSize(lblTrangThai.getPreferredSize());
		lblCaLam.setPreferredSize(lblGioDen.getPreferredSize());
		
		JPanel pnlMessage = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlMessage.setBackground(bgColor);
		pnlTTRight.add(pnlMessage);
		pnlMessage.add(lblMessage = new JLabel(" "));
		lblMessage.setForeground(Color.decode("#dc3545"));
		lblMessage.setFont(main.roboto_regular.deriveFont(Font.ITALIC, 14F));
		
		//Khởi tạo jpanel chức năng chứa các button chức năng
		JPanel pnlChucNang = new JPanel(new FlowLayout());
		pnlChucNang.setBackground(bgColor);
		pnlTTRight.add(pnlChucNang);
		
		btnChamCong = new RoundedButton(main.read_file_languages.getString("btnChamCong"), null, 20, 0, 1.0f);
		btnChamCong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnChamCong.setForeground(Color.WHITE);
		btnChamCong.setBackground(Color.decode("#3B71CA"));
		btnChamCong.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnChamCong.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnChamCong);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnChamLai = new RoundedButton(main.read_file_languages.getString("btnSua"), null, 20, 0, 1.0f);
		btnChamLai.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnChamLai.setForeground(Color.WHITE);
		btnChamLai.setBackground(Color.decode("#ffc107"));
		btnChamLai.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnChamLai.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnChamLai);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnXoa = new RoundedButton(main.read_file_languages.getString("btnXoa"), null, 20, 0, 1.0f);
		btnXoa.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(Color.decode("#dc3545"));
		btnXoa.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoa);
		
		// tạo jpanel chứa table phân công nhân viên
		JPanel pnlBangChamCong = new JPanel();
		TitledBorder titleBorderTTNVPC = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("text_border_DSCC"));
		titleBorderTTNVPC.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangChamCong.setBorder(BorderFactory.createCompoundBorder(titleBorderTTNVPC, BorderFactory.createEmptyBorder(0, 20, 10, 20)));
		pnlBangChamCong.setLayout(new BorderLayout());
		pnlBangChamCong.setBackground(bgColor);
		pnlNhanVien.add(pnlBangChamCong, BorderLayout.CENTER);
		
		JPanel pnlXuat = new JPanel();
		pnlXuat.setBackground(bgColor);
		pnlXuat.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlBangChamCong.add(pnlXuat, BorderLayout.NORTH);
		
		btnXuat = new RoundedButton(main.read_file_languages.getString("btn_XuatDS"), null, 5, 0, 1.0f);
		btnXuat.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setBackground(Color.decode("#28a745"));
		btnXuat.setIcon(new ImageScaler("/image/printer_icon.png", 20, 20).getScaledImageIcon());
		btnXuat.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		pnlXuat.add(btnXuat);
		
		String colsPCNV[] = {main.read_file_languages.getString("stt"),
				main.read_file_languages.getString("lblMaNV"), 
				main.read_file_languages.getString("lblHoTen"),
				main.read_file_languages.getString("lblPhongBan1"), 
				main.read_file_languages.getString("lblNgayCham"), 
				main.read_file_languages.getString("lblCaLam"), 
				main.read_file_languages.getString("lblTrangThai"), 
				main.read_file_languages.getString("lblGioDen"), 
				main.read_file_languages.getString("lblTangCa"), 
				main.read_file_languages.getString("lblGhiChu")};
		dtblModelDSCC = new DefaultTableModel(colsPCNV, 0);
		tblDSCC = new JTable(dtblModelDSCC);

		tbhNVPC = new JTableHeader(tblDSCC.getColumnModel());
		tbhNVPC.setReorderingAllowed(false);
		tbhNVPC.setBackground(componentColor);
		tbhNVPC.setForeground(Color.WHITE);
		tbhNVPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblDSCC.setTableHeader(tbhNVPC);
		tblDSCC.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 14F));
		
		//chỉnh kích thước coloumn
		tblDSCC.setRowHeight(20);
		tblDSCC.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblDSCC.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblDSCC.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(5).setPreferredWidth(75);
		tblDSCC.getColumnModel().getColumn(6).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(7).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(8).setPreferredWidth(100);
		tblDSCC.getColumnModel().getColumn(9).setPreferredWidth(100);
		
		//trái phải của dữ liệu
		tblDSCC.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblDSCC.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		
		tblDSCC.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		tblDSCC.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		tblDSCC.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblDSCC,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangChamCong.add(scrSP, BorderLayout.CENTER);
		
		btnChamCongALL.addActionListener(this);
		btnChamCong.addActionListener(this);
		btnChamLai.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXuat.addActionListener(this);
		dtbNgayCC.addActionListener(this);
		
		btnChamCongALL.addMouseListener(this);
		btnChamCong.addMouseListener(this);
		btnChamLai.addMouseListener(this);
		btnXoa.addMouseListener(this);
		btnXuat.addMouseListener(this);
		tblDSCC.addMouseListener(this);
		tblDSPC.addMouseListener(this);
		
		cmbPhongBan.addItemListener(this);
		
		//Set giá trị mặc định để hiển thị
		uploadDataToTable();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o == tblDSCC) {
			tblDSPC.clearSelection();
			hienThiChamCong(tblDSCC.getSelectedRow());
		}
		if(o == tblDSPC) {
			tblDSCC.clearSelection();
			xoaRongTTCC();
			txtMaNV.setText(tblDSPC.getValueAt(tblDSPC.getSelectedRow(), 1).toString());
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
		main.music.playSE(2);
		Object o = e.getSource();
		if(o == btnChamCong) {
			chamCongNV();
		}
		if(o == btnChamLai) {
			suaChamCongNV();
		}
		if(o == btnChamCongALL) {
			chamTatCa();
		}
		if(o == btnXoa) {
			xoaChamCong();
		}
		if(o == dtbNgayCC) {
			if(dtbNgayCC.getDate()!=null) {
				uploadDataToTable();
			}else {
				setTextError("Chỉ có thể chấm công từ ngày hiện tại về trước!");
			}
		}
		if(o == btnXuat) {
			xuatDSChamCongNhanVienExcel();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getSource();
		if(o == cmbPhongBan) {
			uploadDataToTable();
		}
	}
	//thêm một phân công vào bảng chưa chấm công
	private void themPCNhanVienVaoBangCCC(BangPhanCongNhanVien pcnv) {
	    Object[] row = new Object[10];
	    row[0] = dtblModelDSPC.getRowCount() + 1;  // STT
	    row[1] = pcnv.getNhanVien().getMaNV();  // Mã NV
	    row[2] = pcnv.getNhanVien().getHoTen();  // Họ tên
	    row[3] = pcnv.getPhongBan().getTenPhongBan(); // Phòng ban
	    row[4] = pcnv.getChucVu();  // Chức vụ
	    
	    dtblModelDSPC.addRow(row);
	}
	//thêm một ds chưa chấm công vào bảng
	private void themTatCaPCNhanVienVaoBangCCC(ArrayList<BangPhanCongNhanVien> list) {
		dtblModelDSPC.setRowCount(0);
	    for (BangPhanCongNhanVien pcnv : list) {
	        themPCNhanVienVaoBangCCC(pcnv);
	    }
	}
	//thêm một phân công vào bảng chấm công
	private void themCCNhanVienVaoBangChamCong(BangChamCongNhanVien ccnv) {
	    Object[] row = new Object[10];
	    row[0] = dtblModelDSCC.getRowCount() + 1;  // STT
	    row[1] = ccnv.getPhanCong().getNhanVien().getMaNV();  // Mã NV
	    row[2] = ccnv.getPhanCong().getNhanVien().getHoTen();  // Họ tên
	    row[3] = ccnv.getPhanCong().getPhongBan().getTenPhongBan(); // Phòng ban
	    row[4] = new SimpleDateFormat("dd-MM-yyyy").format(ccnv.getNgayChamCong());  // Chức vụ
	    row[5] = ccnv.getCaLam()==1?"Nửa ngày":"Cả ngày";
	    row[6] = cmbTrangThai.getItemAt(ccnv.getTrangThai()).toString();
	    row[7] = ccnv.getGioDen();
	    row[8] = ccnv.getGioTangCa() + " giờ";
	    row[9] = ccnv.getGhiChu();
	    dtblModelDSCC.addRow(row);
	}
	//thêm một ds chấm công vào bảng
	private void themTatCaCCNhanVienVaoBangChamCong(ArrayList<BangChamCongNhanVien> list) {
		dtblModelDSCC.setRowCount(0);
	    for (BangChamCongNhanVien ccnv : list) {
	        themCCNhanVienVaoBangChamCong(ccnv);
	    }
	}
	// thông báo lỗi
	private void setTextError(String message) {
		main.music.playSE(3);
		lblMessage.setText(message);
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
	//xóa thông tin trên giao diện
	private void xoaRongTTCC() {
		cmbCaLam.setSelectedIndex(0);
		spnModelGioDen.setValue(timeDefault);
		spnTangCa.setValue(0);
		txtMaNV.setText("");
		txtGhiChu.setText("");
		lblMessage.setText("");
	}
	//chấm công tất cả
	private void chamTatCa() {
		if(dsChuaCC.size()>0) {
			Date ngayCham = dtbNgayCC.getDate();
			int caLam = cmbCaLam.getSelectedIndex()==0?2:1;
			int trangThai = cmbTrangThai.getSelectedIndex();
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			String gioDen = format.format(spnModelGioDen.getValue());
			float tangCa = Float.parseFloat(spnTangCa.getValue().toString());
			String ghiChu = txtGhiChu.getText();
			for(BangPhanCongNhanVien pcnv : dsChuaCC) {
				BangChamCongNhanVien bccnv = new BangChamCongNhanVien(pcnv, ngayCham, caLam, trangThai, gioDen, tangCa, ghiChu);
				if(ccnv_Dao.themBangChamCongNhanVien(bccnv)) {
					
				}else {
					setTextError("Chấm công thất bại!" + pcnv.getNhanVien().getMaNV());
				}
			}
			uploadDataToTable();
			lblMessage.setText("Hoàn tất chấm công tất cả nhân viên!");
		}else {
			lblMessage.setText("Đã chấm công tất cả nhân viên!");
		}
	}
	//chấm công 1 nhân viên
	private void chamCongNV() {
		if(tblDSPC.getSelectedRow()!=-1) {
			Date ngayCham = dtbNgayCC.getDate();
			int caLam = cmbCaLam.getSelectedIndex()==0?2:1;
			int trangThai = cmbTrangThai.getSelectedIndex();
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			String gioDen = format.format(spnModelGioDen.getValue());
			float tangCa = Float.parseFloat(spnTangCa.getValue().toString());
			String ghiChu = txtGhiChu.getText();
			BangPhanCongNhanVien pcnv = dsChuaCC.get(tblDSPC.getSelectedRow());
			BangChamCongNhanVien bccnv = new BangChamCongNhanVien(pcnv, ngayCham, caLam, trangThai, gioDen, tangCa, ghiChu);
			if(ccnv_Dao.themBangChamCongNhanVien(bccnv)) {
				lblMessage.setText("Chấm công thành công!");
			}else {
				setTextError("Chấm công thất bại!" + pcnv.getNhanVien().getMaNV());
			}
			uploadDataToTable();
		}else if(dsChuaCC.size()<=0){
			setTextError("Đã chấm công tất cả nhân viên!");
		}
		else {
			setTextError("Bạn cần chọn nhân viên để chấm công!");
		}
	}
	// sửa chấm công 1 nhân viên
	private void suaChamCongNV() {
		if(tblDSCC.getSelectedRow()!=-1) {
			Date ngayCham = dtbNgayCC.getDate();
			int caLam = cmbCaLam.getSelectedIndex()==0?2:1;
			int trangThai = cmbTrangThai.getSelectedIndex();
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			String gioDen = format.format(spnModelGioDen.getValue());
			float tangCa = Float.parseFloat(spnTangCa.getValue().toString());
			String ghiChu = txtGhiChu.getText();
			BangPhanCongNhanVien pcnv = dsChamCong.get(tblDSCC.getSelectedRow()).getPhanCong();
			BangChamCongNhanVien bccnv = new BangChamCongNhanVien(pcnv, ngayCham, caLam, trangThai, gioDen, tangCa, ghiChu);
			if(ccnv_Dao.suaBangChamCongNhanVien(bccnv)) {
				lblMessage.setText("Sửa chấm công thành công!");
			}else {
				setTextError("Sửa chấm công thất bại!" + pcnv.getNhanVien().getMaNV());
			}
			uploadDataToTable();
		}
		else {
			setTextError("Bạn cần chọn nhân viên cần sửa chấm công!");
		}
	}
	//xóa chấm công
	private void xoaChamCong() {
		if(tblDSCC.getSelectedRow()!=-1) {
			if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa chấm công này?", "Cảnh báo", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				BangChamCongNhanVien ccnv = dsChamCong.get(tblDSCC.getSelectedRow());
				if(ccnv_Dao.xoaBangChamCongNhanVien(ccnv.getPhanCong().getMaPhanCong(), ccnv.getNgayChamCong())) {
					lblMessage.setText("Xóa chấm công thành công!");
				}else {
					setTextError("Xóa chấm công thất bại!" + ccnv.getPhanCong().getNhanVien().getMaNV());
				}
				uploadDataToTable();
			}
		}
		else {
			setTextError("Bạn cần chọn nhân viên cần xóa chấm công!");
		}
	}
	//cập nhật lại 2 table
	private void uploadDataToTable() {
		if(cmbPhongBan.getSelectedIndex()!=0) {
			PhongBan pb = (PhongBan) cmbPhongBan.getSelectedItem();
			dsChuaCC = ccnv_Dao.getDSChuaChamCong(pb.getMaPhongBan(), dtbNgayCC.getDate());
			dsChamCong = ccnv_Dao.getDSDaChamCong(pb.getMaPhongBan(), dtbNgayCC.getDate());
		}else {
			dsChuaCC = ccnv_Dao.getDSChuaChamCong(null, dtbNgayCC.getDate());
			dsChamCong = ccnv_Dao.getDSDaChamCong(null, dtbNgayCC.getDate());
		}
		themTatCaPCNhanVienVaoBangCCC(dsChuaCC);
		themTatCaCCNhanVienVaoBangChamCong(dsChamCong);
	}
	//hiển thị dữ liệu chấm công lên bảng thông tin
	private void hienThiChamCong(int index) {
		BangChamCongNhanVien ccnv = dsChamCong.get(index);
		cmbCaLam.setSelectedIndex(ccnv.getCaLam()-1);
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date(2000, 01, 01, 07, 00);
        try {
			date = formatter.parse(ccnv.getGioDen());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spnModelGioDen.setValue(date);
		spnTangCa.setValue(ccnv.getGioTangCa());
		txtMaNV.setText(ccnv.getPhanCong().getNhanVien().getMaNV());
		txtGhiChu.setText(ccnv.getGhiChu());
	}
	//xuất thông tin ds chấm công nhân viên ra excel
	private void xuatDSChamCongNhanVienExcel() {
		if(dsChamCong.size()>0) {
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Chỉ cho phép chọn thư mục
	        int option = fileChooser.showSaveDialog(null);
	        if(option == JFileChooser.APPROVE_OPTION){
	           File file = fileChooser.getSelectedFile();
	           String saveDir = file.getAbsolutePath(); // Đây là thư mục mà người dùng đã chọn
	           try {
	        	   String filePath = saveDir + File.separator + "DanhSachChamCong-" + new SimpleDateFormat("dd-MM-yyyy").format(dtbNgayCC.getDate()) + ".xlsx";
					xuatExcel.writeExcelTTCC(dsChamCong, filePath);
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