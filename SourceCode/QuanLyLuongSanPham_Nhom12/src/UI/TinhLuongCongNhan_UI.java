package UI;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Dao.TinhLuongCongNhan_Dao;
import Entity.BangChamCongCongNhan;
import Entity.BangLuongCongNhan;
import Util.SinhMaTuDong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.GridLayout;

public class TinhLuongCongNhan_UI extends JPanel implements ActionListener, MouseListener {
	private MainUI main;
	private JFrame mainFrame;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JXDatePicker dtbNgayTinhLuong;

	private DefaultTableModel dtblModelTLCN, dtblModelCTCN;
	private JComboBox<String> cmbFilter;
	private JTable tblDSTL, tblCTCN;
	private JTableHeader tbhCNCC, tbhCTCN;
	private RoundedButton btnXuat;
	private RoundedButton btnTinhLuongTatCa, btnXemChiTiet;
	private TinhLuongCongNhan_Dao tlcn_dao = new TinhLuongCongNhan_Dao();
	private ArrayList<BangLuongCongNhan> dsBLCN = new ArrayList<>();

	private SinhMaTuDong maTuDong = new SinhMaTuDong();

	private int thang, nam;
	private JPanel pnlContent = new JPanel();

	private JTextField txtMaCN;
	private JTextField txtTenCN;
	private JTextField txtSoNgayLam;
	private JTextField txtTongLuongThang;
	private JTextField txtTongLuongCongDoan;
	private JTextField txtThucLanh;
	private JTextField txtLuongCoBan;

	public TinhLuongCongNhan_UI(MainUI main) {
		this.main = main;
		setLayout(new BorderLayout(0, 0));

		JPanel pnlTinhLuongCN = new JPanel();
		add(pnlTinhLuongCN, BorderLayout.CENTER);
		pnlTinhLuongCN.setLayout(new BorderLayout(0, 0));

		JPanel pnlBody = new JPanel();
		pnlTinhLuongCN.add(pnlBody, BorderLayout.NORTH);
		pnlBody.setLayout(new BorderLayout(0, 0));

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(new EmptyBorder(10, 0, 0, 0));
		pnlTitle.setBackground(new Color(255, 255, 255));
		pnlBody.add(pnlTitle, BorderLayout.NORTH);
		pnlTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTitle = new JLabel("TÍNH LƯƠNG CÔNG NHÂN");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTitle.add(lblTitle);

		JPanel pnlChamCong = new JPanel();
		pnlBody.add(pnlChamCong, BorderLayout.SOUTH);
		pnlChamCong.setLayout(new BorderLayout(0, 0));

		JPanel pnlCCSelectAll = new JPanel();
		pnlCCSelectAll.setBackground(new Color(255, 255, 255));
		pnlCCSelectAll
				.setBorder(
						new CompoundBorder(
								new TitledBorder(
										new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
												new Color(160, 160, 160)),
										"Th\u00F4ng tin t\u00EDnh l\u01B0\u01A1ng C\u00F4ng Nh\u00E2n",
										TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
								new EmptyBorder(0, 10, 0, 10)));
		pnlChamCong.add(pnlCCSelectAll, BorderLayout.CENTER);
		pnlCCSelectAll.setLayout(new BorderLayout(0, 0));
//		pnlCCSelectAll.setPreferredSize(new Dimension(200, 300));

		JPanel pnlCcAll = new JPanel();
		pnlCcAll.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnlCcAll.setBackground(new Color(255, 255, 255));
		pnlCCSelectAll.add(pnlCcAll, BorderLayout.NORTH);
		pnlCcAll.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlTL = new JPanel();
		pnlTL.setBorder(new EmptyBorder(0, 30, 0, 30));
		pnlTL.setBackground(new Color(255, 255, 255));
		pnlCcAll.add(pnlTL);
		pnlTL.setLayout(new BoxLayout(pnlTL, BoxLayout.Y_AXIS));

		Box horizontalBox = Box.createHorizontalBox();
		pnlTL.add(horizontalBox);

		JLabel lblThangNam = new JLabel("Tháng / Năm");
		horizontalBox.add(lblThangNam);

		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_7);

		dtbNgayTinhLuong = new JXDatePicker(new Date());
		dtbNgayTinhLuong.setFormats(new SimpleDateFormat("MM/yyyy"));
		dtbNgayTinhLuong.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dtbNgayTinhLuong.setLocale(new Locale("vi", "VN"));
		dtbNgayTinhLuong.getMonthView().setUpperBound(new Date());

		horizontalBox.add(dtbNgayTinhLuong);

		Component verticalStrut = Box.createVerticalStrut(20);
		pnlTL.add(verticalStrut);

		Box horizontalBox_1 = Box.createHorizontalBox();
		pnlTL.add(horizontalBox_1);

		btnTinhLuongTatCa = new RoundedButton("Tính lương tất cả", (Color) null, 10, 0, 1.0f);
		btnTinhLuongTatCa.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnTinhLuongTatCa.setForeground(Color.WHITE);
		btnTinhLuongTatCa.setBackground(Color.decode("#3B71CA"));
		btnTinhLuongTatCa.setIcon(new ImageScaler("/image/icon_get_all.png", 24, 24).getScaledImageIcon());
		btnTinhLuongTatCa.setBorder(new EmptyBorder(5, 10, 5, 10));
		horizontalBox_1.add(btnTinhLuongTatCa);

		Component horizontalStrut = Box.createHorizontalStrut(30);
		horizontalBox_1.add(horizontalStrut);

		btnXemChiTiet = new RoundedButton("Xem chi tiết", (Color) null, 10, 0, 1.0f);
		btnXemChiTiet.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnXemChiTiet.setForeground(Color.WHITE);
		btnXemChiTiet.setBackground(Color.decode("#424242"));
		btnXemChiTiet.setIcon(new ImageScaler("/image/icon_add_cd.png", 24, 24).getScaledImageIcon());
		btnXemChiTiet.setBorder(new EmptyBorder(5, 10, 5, 10));

		btnXemChiTiet.setEnabled(false);
		
		horizontalBox_1.add(btnXemChiTiet);

		JPanel pnlGhiChu = new JPanel();
		pnlGhiChu.setBackground(new Color(255, 255, 255));
		pnlCcAll.add(pnlGhiChu);

		JTextArea txtrLngCBn = new JTextArea();
		txtrLngCBn.setEditable(false);
		txtrLngCBn.setFont(new Font("Arial", Font.BOLD, 16));
		txtrLngCBn.setTabSize(6);
		txtrLngCBn.setText(
				"Lương cơ bản: 250.000\r\nLương tháng: lương cơ bản * (làm + phép - nghỉ)\r\nLương CĐ : Đơn giá CĐ * SL CĐ đã làm\r\nThực lãnh: lương tháng + lương CĐ");
		pnlGhiChu.add(txtrLngCBn);

		JPanel pnlBangChamCong = new JPanel();
		pnlBangChamCong.setBackground(new Color(255, 255, 255));
		pnlBangChamCong
				.setBorder(new CompoundBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"Danh s\u00E1ch L\u01B0\u01A1ng C\u00F4ng nh\u00E2n \u0111\u00E3 t\u00EDnh",
								TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
						new EmptyBorder(0, 0, 0, 0)));
		pnlTinhLuongCN.add(pnlBangChamCong, BorderLayout.CENTER);
		pnlBangChamCong.setLayout(new BorderLayout(0, 0));

		JPanel pnlXuat = new JPanel();
		pnlXuat.setBorder(new EmptyBorder(5, 10, 5, 10));
		pnlXuat.setBackground(bgColor);
		pnlBangChamCong.add(pnlXuat, BorderLayout.NORTH);
		pnlXuat.setLayout(new BorderLayout(0, 0));

//		cmbFilter = new JComboBox<>();
//		cmbFilter.addItem("Tất cả");
//		cmbFilter.addItem("Tháng/Năm");
//		pnlXuat.add(cmbFilter, BorderLayout.WEST);

		btnXuat = new RoundedButton("Xuất DS", null, 5, 0, 1.0f);
		btnXuat.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setBackground(Color.decode("#28a745"));
		btnXuat.setIcon(new ImageScaler("/image/printer_icon.png", 20, 20).getScaledImageIcon());
		btnXuat.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		pnlXuat.add(btnXuat, BorderLayout.EAST);

		String colsTLCN[] = { "#", "Tháng/Năm", "Mã CN", "Tên", "Làm", "Nghỉ Phép", "Nghỉ", "SL Làm",
				"Lương tháng", "Lương CĐ", "Thực lãnh" };
		dtblModelTLCN = new DefaultTableModel(colsTLCN, 0);
		tblDSTL = new JTable(dtblModelTLCN);

		tbhCNCC = new JTableHeader(tblDSTL.getColumnModel());
		tbhCNCC.setReorderingAllowed(false);
		tbhCNCC.setBackground(componentColor);
		tbhCNCC.setForeground(Color.WHITE);
		tbhCNCC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblDSTL.setTableHeader(tbhCNCC);

		tblDSTL.setRowHeight(30);
		tblDSTL.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblDSTL.getColumnModel().getColumn(1).setPreferredWidth(70);
		tblDSTL.getColumnModel().getColumn(2).setPreferredWidth(60);
		tblDSTL.getColumnModel().getColumn(3).setPreferredWidth(80);
		tblDSTL.getColumnModel().getColumn(4).setPreferredWidth(70);
		tblDSTL.getColumnModel().getColumn(5).setPreferredWidth(70);
		tblDSTL.getColumnModel().getColumn(6).setPreferredWidth(70);
		tblDSTL.getColumnModel().getColumn(7).setPreferredWidth(80);
		tblDSTL.getColumnModel().getColumn(8).setPreferredWidth(150);
		tblDSTL.getColumnModel().getColumn(9).setPreferredWidth(150);
		tblDSTL.getColumnModel().getColumn(10).setPreferredWidth(150);

		// Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblDSTL, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangChamCong.add(scrSP);

		btnTinhLuongTatCa.addActionListener(this);
		btnXemChiTiet.addActionListener(this);
		btnXuat.addActionListener(this);
//		cmbFilter.addActionListener(this);

		tblDSTL.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		main.music.playSE(2);
		if (o == tblDSTL) {
			int index = tblDSTL.getSelectedRow();
			if (index != -1) {
				btnXemChiTiet.setEnabled(true);
			}
		}
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
		Object o = e.getSource();
		main.music.playSE(2);
		if (o == btnTinhLuongTatCa) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dtbNgayTinhLuong.getDate());
			thang = calendar.get(Calendar.MONTH) + 1;
			nam = calendar.get(Calendar.YEAR);
			if (tlcn_dao.tinhALL(thang, nam)) {
				getDataTLCNLenBang();
				alertSuccess("Tính lương thành công");
			} else {
				String text = String.format("Tính lương thất bại! Do không có danh sách nào trong tháng %s năm %s",
						thang, nam);
				alertNotification(text);
			}
		}

		if (o == btnXemChiTiet) {
			int index = tblDSTL.getSelectedRow();
			if (index != -1) {
				showJDialogSP();
			} else {
				alertNotification("Hãy chọn 1 công nhân cần xem chi tiết lương");
			}
		}

//		if (o == cmbFilter) {
//			if (cmbFilter.getSelectedItem().equals("Tất cả")) {
//				getDataTLCNLenBang();
//			} else if (cmbFilter.getSelectedItem().equals("Tháng/Năm")) {
//				getDataTLCNLenBangTN();
//			}
//		}

	}

	private void showJDialogSP() {
		JDialog listCTCN = new JDialog(mainFrame, "Chi tiết công nhân", JDialog.ModalityType.APPLICATION_MODAL);
		listCTCN.setSize(1100, 700);
		listCTCN.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		listCTCN.setLocationRelativeTo(null);

		// -----------------------------------------------------------------------------------------------------------------
		pnlContent.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlBoxInfo = new JPanel();
			pnlBoxInfo.setBackground(new Color(255, 255, 255));
			pnlBoxInfo.setBorder(new EmptyBorder(10, 30, 10, 30));
			pnlContent.add(pnlBoxInfo, BorderLayout.NORTH);
			pnlBoxInfo.setLayout(new BoxLayout(pnlBoxInfo, BoxLayout.Y_AXIS));
			{
				Box horizontalBox = Box.createHorizontalBox();
				pnlBoxInfo.add(horizontalBox);
				{
					JLabel lblTitle = new JLabel("CHI TIẾT LƯƠNG CÔNG NHÂN ");
					lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
					horizontalBox.add(lblTitle);
				}
				{
					JLabel lblNgayThangTinhLuong = new JLabel("??/????");
					lblNgayThangTinhLuong.setFont(new Font("Tahoma", Font.BOLD, 18));
					horizontalBox.add(lblNgayThangTinhLuong);
				}
			}
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				pnlBoxInfo.add(verticalStrut);
			}
			{
				JPanel pnlInformaion = new JPanel();
				pnlInformaion.setBackground(new Color(255, 255, 255));
				pnlBoxInfo.add(pnlInformaion);
				pnlInformaion.setLayout(new GridLayout(0, 2, 50, 10));
				{
					JPanel pnlBox1 = new JPanel();
					pnlBox1.setBackground(new Color(255, 255, 255));
					pnlInformaion.add(pnlBox1);
					pnlBox1.setLayout(new BoxLayout(pnlBox1, BoxLayout.X_AXIS));
					{
						JLabel lblMaCN = new JLabel("Mã Công Nhân: ");
						lblMaCN.setFont(new Font("Tahoma", Font.BOLD, 15));
						pnlBox1.add(lblMaCN);
					}
					{
						Component horizontalStrut = Box.createHorizontalStrut(20);
						pnlBox1.add(horizontalStrut);
					}
					{
						txtMaCN = new JTextField();
						txtMaCN.setEnabled(false);
						pnlBox1.add(txtMaCN);
						txtMaCN.setColumns(10);
					}
				}
				{
					JPanel pnlBox2 = new JPanel();
					pnlBox2.setBackground(new Color(255, 255, 255));
					pnlInformaion.add(pnlBox2);
					pnlBox2.setLayout(new BoxLayout(pnlBox2, BoxLayout.X_AXIS));
					{
						JLabel lblTenCN = new JLabel("Tên Công Nhân: ");
						lblTenCN.setFont(new Font("Tahoma", Font.BOLD, 15));
						pnlBox2.add(lblTenCN);
					}
					{
						Component horizontalStrut = Box.createHorizontalStrut(20);
						pnlBox2.add(horizontalStrut);
					}
					{
						txtTenCN = new JTextField();
						txtTenCN.setEnabled(false);
						txtTenCN.setColumns(10);
						pnlBox2.add(txtTenCN);
					}
				}
				{
					JPanel pnlBox3 = new JPanel();
					pnlBox3.setBackground(new Color(255, 255, 255));
					pnlInformaion.add(pnlBox3);
					pnlBox3.setLayout(new BoxLayout(pnlBox3, BoxLayout.X_AXIS));
					{
						JLabel lblTongSoNgayLam = new JLabel("Tổng Số Ngày Làm : ");
						lblTongSoNgayLam.setFont(new Font("Tahoma", Font.BOLD, 15));
						pnlBox3.add(lblTongSoNgayLam);
					}
					{
						Component horizontalStrut = Box.createHorizontalStrut(20);
						pnlBox3.add(horizontalStrut);
					}
					{
						txtSoNgayLam = new JTextField();
						txtSoNgayLam.setEnabled(false);
						txtSoNgayLam.setColumns(10);
						pnlBox3.add(txtSoNgayLam);
					}
				}
				{
					JPanel pnlBox4 = new JPanel();
					pnlBox4.setBackground(new Color(255, 255, 255));
					pnlInformaion.add(pnlBox4);
					pnlBox4.setLayout(new BoxLayout(pnlBox4, BoxLayout.X_AXIS));
					{
						JLabel lblTongLuongThang = new JLabel("Tổng Lương Tháng :");
						lblTongLuongThang.setFont(new Font("Tahoma", Font.BOLD, 15));
						pnlBox4.add(lblTongLuongThang);
					}
					{
						Component horizontalStrut = Box.createHorizontalStrut(20);
						pnlBox4.add(horizontalStrut);
					}
					{
						txtTongLuongThang = new JTextField();
						txtTongLuongThang.setEnabled(false);
						txtTongLuongThang.setColumns(10);
						pnlBox4.add(txtTongLuongThang);
					}
				}
				{
					JPanel pnlBox5 = new JPanel();
					pnlBox5.setBackground(new Color(255, 255, 255));
					pnlInformaion.add(pnlBox5);
					pnlBox5.setLayout(new BoxLayout(pnlBox5, BoxLayout.X_AXIS));
					{
						JLabel lblTongLuongCongDoan = new JLabel("Tổng Lương Công Đoạn : ");
						lblTongLuongCongDoan.setFont(new Font("Tahoma", Font.BOLD, 15));
						pnlBox5.add(lblTongLuongCongDoan);
					}
					{
						Component horizontalStrut = Box.createHorizontalStrut(20);
						pnlBox5.add(horizontalStrut);
					}
					{
						txtTongLuongCongDoan = new JTextField();
						txtTongLuongCongDoan.setEnabled(false);
						txtTongLuongCongDoan.setColumns(10);
						pnlBox5.add(txtTongLuongCongDoan);
					}
				}
				{
					JPanel pnlBox6 = new JPanel();
					pnlBox6.setBackground(new Color(255, 255, 255));
					pnlInformaion.add(pnlBox6);
					pnlBox6.setLayout(new BoxLayout(pnlBox6, BoxLayout.X_AXIS));
					{
						JLabel lblThcLnh = new JLabel("Thực Lãnh : ");
						lblThcLnh.setFont(new Font("Tahoma", Font.BOLD, 15));
						pnlBox6.add(lblThcLnh);
					}
					{
						Component horizontalStrut = Box.createHorizontalStrut(20);
						pnlBox6.add(horizontalStrut);
					}
					{
						txtThucLanh = new JTextField();
						txtThucLanh.setEnabled(false);
						txtThucLanh.setColumns(10);
						pnlBox6.add(txtThucLanh);
					}
				}
				{
					JPanel pnlBox7 = new JPanel();
					pnlBox7.setBackground(new Color(255, 255, 255));
					pnlInformaion.add(pnlBox7);
					pnlBox7.setLayout(new BoxLayout(pnlBox7, BoxLayout.X_AXIS));
					{
						JLabel lblLuongCoBan = new JLabel("Lương Cơ Bản : ");
						lblLuongCoBan.setFont(new Font("Tahoma", Font.BOLD, 15));
						pnlBox7.add(lblLuongCoBan);
					}
					{
						Component horizontalStrut = Box.createHorizontalStrut(20);
						pnlBox7.add(horizontalStrut);
					}
					{
						txtLuongCoBan = new JTextField();
						txtLuongCoBan.setFont(new Font("Tahoma", Font.ITALIC, 12));
						txtLuongCoBan.setText("250,000 VND");
						txtLuongCoBan.setEnabled(false);
						txtLuongCoBan.setColumns(10);
						pnlBox7.add(txtLuongCoBan);
					}
				}
				{
					JPanel pnlBox8 = new JPanel();
					pnlBox8.setBackground(new Color(255, 255, 255));
					pnlInformaion.add(pnlBox8);
					pnlBox8.setLayout(new GridLayout(0, 3, 0, 0));
					{
						Component horizontalStrut = Box.createHorizontalStrut(20);
						pnlBox8.add(horizontalStrut);
					}
					{
						JButton btnXuatPDF = new JButton("Xuất PDF");
						pnlBox8.add(btnXuatPDF);
					}
				}
			}
		}
		{
			JPanel pnlTableLuong = new JPanel();
			pnlTableLuong.setBackground(new Color(255, 255, 255));
			pnlTableLuong
					.setBorder(new CompoundBorder(
							new TitledBorder(
									new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
											new Color(160, 160, 160)),
									"Danh S\u00E1ch C\u00E1c C\u00F4ng \u0110o\u1EA1n \u0110\u00E3 L\u00E0m",
									TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
							new EmptyBorder(10, 10, 10, 20)));
			pnlContent.add(pnlTableLuong, BorderLayout.CENTER);
			{
				pnlTableLuong.setBorder(new CompoundBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"Danh S\u00E1ch C\u00E1c C\u00F4ng \u0110o\u1EA1n \u0110\u00E3 L\u00E0m",
								TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
						new EmptyBorder(10, 10, 10, 20)));

				pnlContent.add(pnlTableLuong, BorderLayout.CENTER);
				{
					String cols_cd[] = { "STT", "Mã SP", "Tên SP", "Mã CĐ", "Tên CĐ", "Thứ tự" };
					dtblModelCTCN = new DefaultTableModel(cols_cd, 0);
					tblCTCN = new JTable(dtblModelCTCN);

					tbhCTCN = new JTableHeader(tblCTCN.getColumnModel());
					tbhCTCN.setReorderingAllowed(false);
					tbhCTCN.setBackground(componentColor);
					tbhCTCN.setForeground(Color.WHITE);
					tbhCTCN.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));

					tblCTCN.setTableHeader(tbhCTCN);
					tblCTCN.setRowHeight(30);
					tblCTCN.getColumnModel().getColumn(0).setPreferredWidth(30);
					tblCTCN.getColumnModel().getColumn(1).setPreferredWidth(100);
					tblCTCN.getColumnModel().getColumn(2).setPreferredWidth(100);
					tblCTCN.getColumnModel().getColumn(3).setPreferredWidth(100);
					tblCTCN.getColumnModel().getColumn(4).setPreferredWidth(100);
					tblCTCN.getColumnModel().getColumn(5).setPreferredWidth(100);
					pnlTableLuong.setLayout(new BorderLayout(0, 0));

					JScrollPane scrCD = new JScrollPane(tblCTCN, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
							JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					pnlTableLuong.add(scrCD);
				}
			}
		}

		listCTCN.getContentPane().setLayout(new BorderLayout());
		listCTCN.getContentPane().add(pnlContent, BorderLayout.NORTH);

		listCTCN.setVisible(true);
	}

	private void getDataTLCNLenBang() {
		dsBLCN = tlcn_dao.getAllBLCN_TN(thang, nam);
		themAllTLCNVaoBang(dsBLCN);
	}

//	private void getDataTLCNLenBangTN() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(dtbNgayTinhLuong.getDate());
//		thang = calendar.get(Calendar.MONTH) + 1;
//		nam = calendar.get(Calendar.YEAR);
//
//		dsBLCN = tlcn_dao.getAllBLCN_TN(thang, nam);
//		themAllTLCNVaoBang(dsBLCN);
//	}

	// HÀM THÊM TẤT CẢ TLCN VÀO BẢNG
	private void themAllTLCNVaoBang(ArrayList<BangLuongCongNhan> listLCN) {
		dtblModelTLCN.setRowCount(0);
		for (BangLuongCongNhan tlcn : listLCN) {
			themDataBLCNVaoBang(tlcn);
		}
	}

	// HÀM THÊM BLCN VÀO BẢNG
	private void themDataBLCNVaoBang(BangLuongCongNhan blcn) {
		String[] row = new String[99];
		row[0] = String.valueOf(dtblModelTLCN.getRowCount() + 1);
		row[1] = blcn.getThangNam();
		row[2] = blcn.getCongNhan().getMaCN();
		row[3] = blcn.getCongNhan().getHoTen();
		row[4] = String.valueOf(blcn.getSoNgayLam() + " ngày");
		row[5] = String.valueOf(blcn.getSoNgayPhep() + " ngày");
		row[6] = String.valueOf(blcn.getSoNgayNghi() + " ngày");
		row[7] = String.valueOf(blcn.getChamCongCongNhan().getSoLuongLam());

		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		row[8] = String.valueOf(decimalFormat.format(blcn.getLuongThang()) + " VND");

		row[9] = String.valueOf(decimalFormat.format(blcn.getLuongCongDoan()) + " VND");
		row[10] = String.valueOf(decimalFormat.format(blcn.getThucLanh()) + " VND");

		dtblModelTLCN.addRow(row);
	}

	// ALERT
	public int alertNotification(String textError) {
		main.music.playSE(3);
		String[] options = { "Cancel" };
		int result = JOptionPane.showOptionDialog(main, textError, "NOTIFICATION", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		return result;
	}

	public int alertSuccess(String textError) {
		main.music.playSE(1);
		String[] options = { "Cancel" };
		int result = JOptionPane.showOptionDialog(main, textError, "NOTIFICATION", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		return result;
	}

}
