package UI;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TinhLuongCongNhan_UI extends JPanel {
	private MainUI main;

	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JXDatePicker dtbNgayChamCong;

	private DefaultTableModel dtblModelTLCN;
	private JTable tblDSTL;
	private JTableHeader tbhCN, tbhCNCC;
	private RoundedButton btnXuat;

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
				.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin t\u00EDnh l\u01B0\u01A1ng C\u00F4ng Nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 10, 0, 10)));
		pnlChamCong.add(pnlCCSelectAll, BorderLayout.CENTER);
		pnlCCSelectAll.setLayout(new BorderLayout(0, 0));
//		pnlCCSelectAll.setPreferredSize(new Dimension(200, 300));

		JPanel pnlCcAll = new JPanel();
		pnlCcAll.setBackground(new Color(255, 255, 255));
		pnlCCSelectAll.add(pnlCcAll, BorderLayout.NORTH);
		pnlCcAll.setLayout(new BoxLayout(pnlCcAll, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		pnlCcAll.add(panel);

		Box horizontalBox = Box.createHorizontalBox();
		panel.add(horizontalBox);

		JLabel lblNgayChamCong = new JLabel("Ngày Chấm");
		horizontalBox.add(lblNgayChamCong);

		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_7);

		dtbNgayChamCong = new JXDatePicker((Date) null);
		dtbNgayChamCong.setFormats(new SimpleDateFormat("MM/yyyy"));
		dtbNgayChamCong.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		dtbNgayChamCong.setLocale(new Locale("vi", "VN"));
		horizontalBox.add(dtbNgayChamCong);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(60);
		horizontalBox.add(horizontalStrut_1);
		
		JButton btnChamTatCa = new JButton("Tính lương tất cả");
		horizontalBox.add(btnChamTatCa);
		
		Component horizontalStrut = Box.createHorizontalStrut(30);
		horizontalBox.add(horizontalStrut);
		
		JButton btnXemChiTiet = new JButton("Xem chi tiết");
		horizontalBox.add(btnXemChiTiet);

		Box box_ChamCong = Box.createHorizontalBox();
		box_ChamCong.setBorder(new EmptyBorder(0, 0, 10, 0));
		pnlCcAll.add(box_ChamCong);


		JPanel pnlBangChamCong = new JPanel();
		pnlBangChamCong.setBackground(new Color(255, 255, 255));
		pnlBangChamCong
				.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch L\u01B0\u01A1ng C\u00F4ng nh\u00E2n \u0111\u00E3 t\u00EDnh", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(0, 0, 0, 0)));
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

		String colsTLCN[] = { "STT", "Mã lương", "Mã CN", "Họ Tên","Ngày Làm", "Ngày TL", "Lương tháng",
				"Lương cơ bản", "Thực lãnh", "Ghi chú" };
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
	}
}
