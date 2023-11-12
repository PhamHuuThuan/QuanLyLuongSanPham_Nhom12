package UI;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;
import Dao.TinhLuongCongNhan_Dao;
import Entity.BangChamCongCongNhan;
import Util.SinhMaTuDong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JXDatePicker dtbNgayTinhLuong;

	private DefaultTableModel dtblModelTLCN, dtbModelCN;
	private JTable tblDSTL, tblCN;
	private JTableHeader tbhCN, tbhCNCC;
	private RoundedButton btnXuat;
	private RoundedButton btnTinhLuongTatCa, btnXemChiTiet;
	private ArrayList<BangChamCongCongNhan> dsCCCN = new ArrayList<>();
	private TinhLuongCongNhan_Dao tlcn_dao = new TinhLuongCongNhan_Dao();
	private SinhMaTuDong maTuDong = new SinhMaTuDong();

	private int ngayLam;
	private int ngayNghi;
	private int ngayNghiPhep;

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
		horizontalBox_1.add(btnXemChiTiet);

		JPanel pnlGhiChu = new JPanel();
		pnlGhiChu.setBackground(new Color(255, 255, 255));
		pnlCcAll.add(pnlGhiChu);

		JTextArea txtrLngCBn = new JTextArea();
		txtrLngCBn.setEditable(false);
		txtrLngCBn.setFont(new Font("Arial", Font.BOLD, 16));
		txtrLngCBn.setTabSize(6);
		txtrLngCBn.setText(
				"Lương cơ bản: 150.000\r\nLương tháng: lương cơ bản * (làm + phép - nghỉ)\r\nLương CĐ : Đơn giá CĐ * SL CĐ đã làm\r\nThực lãnh: lương tháng + lương CĐ");
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

		String colsTLCN[] = { "STT", "Mã lương", "Mã CN", "Họ Tên", "Ngày Làm", "Ngày TL", "Lương tháng", "Lương CĐ",
				"Thực lãnh", "Ghi chú" };
		dtblModelTLCN = new DefaultTableModel(colsTLCN, 0);
		tblDSTL = new JTable(dtblModelTLCN);

		tbhCNCC = new JTableHeader(tblDSTL.getColumnModel());
		tbhCNCC.setReorderingAllowed(false);
		tbhCNCC.setBackground(componentColor);
		tbhCNCC.setForeground(Color.WHITE);
		tbhCNCC.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		tblDSTL.setTableHeader(tbhCNCC);

		tblDSTL.setRowHeight(30);
		tblDSTL.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblDSTL.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblDSTL.getColumnModel().getColumn(2).setPreferredWidth(80);
		tblDSTL.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblDSTL.getColumnModel().getColumn(4).setPreferredWidth(30);
		tblDSTL.getColumnModel().getColumn(5).setPreferredWidth(80);
		tblDSTL.getColumnModel().getColumn(6).setPreferredWidth(110);
		tblDSTL.getColumnModel().getColumn(7).setPreferredWidth(110);
		tblDSTL.getColumnModel().getColumn(8).setPreferredWidth(110);
		tblDSTL.getColumnModel().getColumn(9).setPreferredWidth(80);

		// Tạo jscrollpane để tạo scroll cho bảng sản phẩm
		JScrollPane scrSP = new JScrollPane(tblDSTL, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangChamCong.add(scrSP);

		btnTinhLuongTatCa.addActionListener(this);
		btnXemChiTiet.addActionListener(this);
		btnXuat.addActionListener(this);

	}

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
		Object o = e.getSource();
		main.music.playSE(2);
		if (o == btnTinhLuongTatCa) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dtbNgayTinhLuong.getDate());
			int thang = calendar.get(Calendar.MONTH) + 1;
			int nam = calendar.get(Calendar.YEAR);
			if (tlcn_dao.tinhALL(thang, nam)) {
				alertSuccess("Tính lương thành công");
			} else {
				alertNotification("Tính lương thất bại");
			}
		}

	}

	// HÀM LẤY DANH SÁCH CÔNG NHÂN ĐÃ CHẤM THEO THÁNG NĂM
	public void tinhLuongALL() {

	}

	// HÀM TÍNH LƯƠNG
//	public void tinhLuong(ArrayList<BangChamCongCongNhan> list) {
//		for (BangChamCongCongNhan value : list) {
//			String maPC = value.getPhanCong().getCongNhan().getMaCN();
//			int soLuongLam = value.getSoLuongLam();
//			System.out.println(maPC + "--" + soLuongLam);
//		}
//		System.out.println(thang +"/"+nam);
//		dsCCCN = tlcn_dao.getDSChamCong(thang, nam);
//		tinhLuong(dsCCCN);
//		System.out.println(maTuDong.sinhMaBLCN());
//	}

	
	
	
	
	
	
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
