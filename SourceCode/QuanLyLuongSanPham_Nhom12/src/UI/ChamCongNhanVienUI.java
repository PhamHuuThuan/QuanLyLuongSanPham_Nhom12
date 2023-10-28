package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Component;

public class ChamCongNhanVienUI extends JPanel implements ActionListener, MouseListener{
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private RoundedButton btnChamCongALL, btnChamCong, btnChamLai, btnXoa, btnFocus;
	private DefaultTableModel dtblModelNVPC, dtblModelNV;
	private JTable tblNVPC, tblNV;
	private JTableHeader tbhNVPC, tbhNV;
	private JTextField  txtGhiChu;
	private JComboBox cmbPhongBan, cmbCaLam, cmbTrangThai;
	private JSpinner spnGioDen, spnTangCa;
	private JTextField txtMaNV;
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
		JLabel lblTitle = new JLabel("CHẤM CÔNG NHÂN VIÊN");
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
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Danh sách nhân viên");
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
		
		JLabel lblNgayCham = new JLabel("Ngày Chấm:");
		pnlPBvaTime.add(lblNgayCham);
		lblNgayCham.setForeground(textColor);
		lblNgayCham.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		JXDatePicker dtbNgayVL = new JXDatePicker((Date) null);
		pnlPBvaTime.add(dtbNgayVL);
		pnlPBvaTime.add(Box.createHorizontalStrut(30));
		dtbNgayVL.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dtbNgayVL.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dtbNgayVL.setLocale(new Locale("vi", "VN"));	// set thoi gian local la VN
		dtbNgayVL.getEditor().setBackground(bgColor);
		dtbNgayVL.getEditor().setForeground(textColor);
		JButton btnDateNVL = (JButton) dtbNgayVL.getComponent(1);
		btnDateNVL.setIcon(new ImageScaler("/image/calendar_icon.png", 18, 18).getScaledImageIcon());
		btnDateNVL.setBackground(bgColor);
		btnDateNVL.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		cmbPhongBan.setPreferredSize(dtbNgayVL.getPreferredSize());
		
		btnChamCongALL = new RoundedButton("Chấm tất cả", null, 20, 0, 1.0f);
		btnChamCongALL.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnChamCongALL.setForeground(Color.WHITE);
		btnChamCongALL.setBackground(Color.decode("#28a745"));
		btnChamCongALL.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnChamCongALL.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlPBvaTime.add(btnChamCongALL);
		
		String cols[] = {"Mã NV", "Họ tên", "Phòng ban", "Chức vụ"};
		dtblModelNV = new DefaultTableModel(cols, 0);
		tblNV = new JTable(dtblModelNV);

		tbhNV = new JTableHeader(tblNV.getColumnModel());
		tbhNV.setReorderingAllowed(false);
		tbhNV.setBackground(componentColor);
		tbhNV.setForeground(Color.WHITE);
		tbhNV.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblNV.setTableHeader(tbhNV);
		
		tblNV.setRowHeight(20);
		tblNV.getColumnModel().getColumn(0).setPreferredWidth(75);
		tblNV.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblNV.getColumnModel().getColumn(2).setPreferredWidth(150);
		tblNV.getColumnModel().getColumn(3).setPreferredWidth(150);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrNV = new JScrollPane(tblNV,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNV.add(scrNV, BorderLayout.CENTER);
		
		
		//Tao jpanel Thong tin cham cong
		JPanel pnThongTinNV = new JPanel();
		pnThongTinNV.setLayout(new BorderLayout());
		pnThongTinNV.setBackground(bgColor);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Thông tin chấm công");
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnThongTinNV.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(10, 30, 10, 30)));
		pnThongTinNV.setPreferredSize(new Dimension(600, 300));
		b.add(pnThongTinNV);
		
		JPanel pnlAnhDaiDien = new JPanel();
		pnlAnhDaiDien.setLayout(new BoxLayout(pnlAnhDaiDien, BoxLayout.Y_AXIS));
		pnlAnhDaiDien.setBackground(bgColor);
		pnThongTinNV.add(pnlAnhDaiDien, BorderLayout.WEST);
		pnlAnhDaiDien.add(Box.createVerticalStrut(100));
		
		
		JPanel pnlTTRight = new JPanel();
		pnlTTRight.setLayout(new BoxLayout(pnlTTRight, BoxLayout.Y_AXIS));
		pnlTTRight.setBackground(bgColor);
		pnThongTinNV.add(pnlTTRight, BorderLayout.CENTER);
		
		JPanel pnlB1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB1.setBackground(bgColor);
		pnlTTRight.add(pnlB1);
		
		JLabel lblCaLam = new JLabel("Ca làm:");
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
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		pnlB1.add(lblTrangThai);
		lblTrangThai.setForeground(textColor);
		lblTrangThai.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		cmbTrangThai = new JComboBox<>();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Đúng giờ", "Trễ", "Nghỉ", "Nghỉ phép"}));
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
		
		JLabel lblGioDen = new JLabel("Giờ đến:");
		pnlB2.add(lblGioDen);
		lblGioDen.setForeground(textColor);
		lblGioDen.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 10, 0, 5));
		
        // Tạo một Calendar để thiết lập thời gian
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7); // Thiết lập giờ là 7
        calendar.set(Calendar.MINUTE, 0); // Thiết lập phút là 0
        Date defaultTime = calendar.getTime(); // Lấy thời gian từ Calendar

        // Tạo JSpinner với SpinnerDateModel và giá trị mặc định đã thiết lập
        spnGioDen = new JSpinner(new SpinnerDateModel(defaultTime, null, null, Calendar.HOUR_OF_DAY));
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
		
		JLabel lblTangCa = new JLabel("Tăng ca:");
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
		
		JLabel lblGio = new JLabel("giờ");
		pnlB2.add(lblGio);
		lblGio.setForeground(textColor);
		lblGio.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		JPanel pnlB4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB4.setBackground(bgColor);
		pnlTTRight.add(pnlB4);
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 10, 0, 5));
		
		JLabel lblMaNV = new JLabel("Mã NV:");
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
		pnlB4.add(txtMaNV);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		pnlB4.add(horizontalStrut);
		
		JLabel lblGhiChu = new JLabel("Ghi chú:");
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
		
		//Khởi tạo jpanel chức năng chứa các button chức năng
		JPanel pnlChucNang = new JPanel(new FlowLayout());
		pnlChucNang.setBackground(bgColor);
		pnlTTRight.add(pnlChucNang);
		
		btnChamCong = new RoundedButton("Phân công", null, 20, 0, 1.0f);
		btnChamCong.setText("Chấm công");
		btnChamCong.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnChamCong.setForeground(Color.WHITE);
		btnChamCong.setBackground(Color.decode("#3B71CA"));
		btnChamCong.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnChamCong.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnChamCong);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnChamLai = new RoundedButton("Cập Nhật", null, 20, 0, 1.0f);
		btnChamLai.setText("Chấm lại");
		btnChamLai.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnChamLai.setForeground(Color.WHITE);
		btnChamLai.setBackground(Color.decode("#ffc107"));
		btnChamLai.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnChamLai.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnChamLai);
		pnlChucNang.add(Box.createHorizontalStrut(50));
		
		btnXoa = new RoundedButton("Xóa", null, 20, 0, 1.0f);
		btnXoa.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(Color.decode("#dc3545"));
		btnXoa.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoa);
		
		// tạo jpanel chứa table phân công nhân viên
		JPanel pnlBangNVPC = new JPanel();
		TitledBorder titleBorderTTNVPC = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Danh sách chấm công");
		titleBorderTTNVPC.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlBangNVPC.setBorder(BorderFactory.createCompoundBorder(titleBorderTTNVPC, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		pnlBangNVPC.setLayout(new BoxLayout(pnlBangNVPC, BoxLayout.X_AXIS));
		pnlBangNVPC.setBackground(bgColor);
		pnlNhanVien.add(pnlBangNVPC, BorderLayout.CENTER);
		
		String colsPCNV[] = {"Mã NV", "Họ tên", "Phòng ban", "Ngày", "Ca làm", "Trạng thái", "Phép", "Giờ đến", "Tăng ca", "Ghi chú"};
		dtblModelNVPC = new DefaultTableModel(colsPCNV, 0);
		tblNVPC = new JTable(dtblModelNVPC);

		tbhNVPC = new JTableHeader(tblNVPC.getColumnModel());
		tbhNVPC.setReorderingAllowed(false);
		tbhNVPC.setBackground(componentColor);
		tbhNVPC.setForeground(Color.WHITE);
		tbhNVPC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblNVPC.setTableHeader(tbhNVPC);
		
		tblNVPC.setRowHeight(20);
		tblNVPC.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblNVPC.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblNVPC.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblNVPC.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblNVPC.getColumnModel().getColumn(4).setPreferredWidth(75);
		tblNVPC.getColumnModel().getColumn(5).setPreferredWidth(100);
		tblNVPC.getColumnModel().getColumn(6).setPreferredWidth(50);
		tblNVPC.getColumnModel().getColumn(7).setPreferredWidth(100);
		tblNVPC.getColumnModel().getColumn(8).setPreferredWidth(100);
		tblNVPC.getColumnModel().getColumn(8).setPreferredWidth(100);
		
		//Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblNVPC,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangNVPC.add(scrSP);
		
		btnChamCong.addActionListener(this);
		btnChamLai.addActionListener(this);
		
		btnChamCong.addMouseListener(this);
		btnChamLai.addMouseListener(this);
		
		
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
		if(o == btnChamLai) {

			
		}
	}
}
