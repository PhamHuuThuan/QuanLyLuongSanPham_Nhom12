package UI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomUI.ImageScaler;
import CustomUI.RoundedButton;

import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PhongBan_UI extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField txtMaPhongBan, txtTenPb, txtSoNv, txtMota;
	private Font fontText;
	private RoundedButton btnThem, btnSua, btnXoa, btnLuu, btnHuy;
	private JTable tblPb;
	private JTableHeader tbhPb;

	private DefaultTableModel tabModel;

	public PhongBan_UI(MainUI main) {
		this.main = main;
		fontText = main.roboto_regular.deriveFont(Font.PLAIN, 16F);

//		set layout cho Phong Ban
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(bgColor);

		JPanel pnlPhongBan = new JPanel();
		pnlPhongBan.setBorder(new EmptyBorder(0, 50, 0, 50));
		add(pnlPhongBan, BorderLayout.NORTH);
		pnlPhongBan.setLayout(new BorderLayout(0, 0));

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(null);
		pnlTitle.setBackground(new Color(255, 255, 255));
		pnlPhongBan.add(pnlTitle, BorderLayout.NORTH);
		pnlPhongBan.setBackground(new Color(255, 255, 255));
		pnlTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTitle = new JLabel("QUẢN LÝ PHÒNG BAN");
		lblTitle.setBackground(bgColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle.add(lblTitle);

		JPanel pnlThongTinPhongBan = new JPanel();
		pnlThongTinPhongBan
				.setBorder(new CompoundBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"Th\u00F4ng tin ph\u00F2ng ban", TitledBorder.LEADING, TitledBorder.TOP, null,
								new Color(0, 0, 0)),
						new EmptyBorder(20, 50, 20, 50)));

		TitledBorder titleBorder = BorderFactory
				.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Thông tin phòng ban");
		titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlThongTinPhongBan.setBorder(
				BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		pnlThongTinPhongBan.setBackground(new Color(255, 255, 255));
		pnlPhongBan.add(pnlThongTinPhongBan);
		pnlThongTinPhongBan.setLayout(new BoxLayout(pnlThongTinPhongBan, BoxLayout.Y_AXIS));

		Box box_1 = Box.createHorizontalBox();
		box_1.setBorder(null);
		pnlThongTinPhongBan.add(box_1);

		JLabel lblMaPb = new JLabel("Mã Phòng Ban");
		box_1.add(lblMaPb);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut);

		txtMaPhongBan = new JTextField();
		txtMaPhongBan.setFont(fontText);
		txtMaPhongBan.setForeground(textColor);
		txtMaPhongBan.setBackground(bgColor);
		txtMaPhongBan.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		box_1.add(txtMaPhongBan);
		txtMaPhongBan.setColumns(10);

		Component horizontalStrut_1 = Box.createHorizontalStrut(50);
		box_1.add(horizontalStrut_1);

		JLabel lblTenPb = new JLabel("Tên Phòng Ban");
		box_1.add(lblTenPb);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		box_1.add(horizontalStrut_2);

		txtTenPb = new JTextField();
		txtTenPb.setForeground(Color.BLACK);
		txtTenPb.setFont(fontText);
		txtTenPb.setBackground(bgColor);
		txtTenPb.setColumns(10);
		txtTenPb.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		;
		box_1.add(txtTenPb);
		txtTenPb.setColumns(10);

		Component verticalStrut = Box.createVerticalStrut(20);
		pnlThongTinPhongBan.add(verticalStrut);

		Box box_2 = Box.createHorizontalBox();
		box_2.setBorder(null);
		pnlThongTinPhongBan.add(box_2);

		JLabel lblSoNv = new JLabel("Số nhân viên");
		box_2.add(lblSoNv);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_3);

		txtSoNv = new JTextField();
		txtSoNv.setForeground(Color.BLACK);
		txtSoNv.setFont(fontText);
		txtSoNv.setBackground(bgColor);
		txtSoNv.setColumns(10);
		txtSoNv.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),

						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtSoNv.setBackground(Color.WHITE);
		box_2.add(txtSoNv);

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(50);
		box_2.add(horizontalStrut_1_1);

		JLabel lblMoTa = new JLabel("Mô tả");
		box_2.add(lblMoTa);

		Component horizontalStrut_2_1 = Box.createHorizontalStrut(20);
		box_2.add(horizontalStrut_2_1);

		txtMota = new JTextField();
		txtMota.setForeground(Color.BLACK);
		txtMota.setFont(fontText);
		txtMota.setBackground(bgColor);
		txtMota.setColumns(10);
		txtMota.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		box_2.add(txtMota);

		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBackground(bgColor);
		pnlChucNang.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		pnlPhongBan.add(pnlChucNang, BorderLayout.SOUTH);
		pnlChucNang.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnThem = new RoundedButton("Thêm", null, 20, 0, 1.0f);
		btnThem.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(Color.decode("#3B71CA"));
		btnThem.setIcon(new ImageScaler("/image/addHopDong_icon.png", 24, 24).getScaledImageIcon());
		btnThem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnThem);
		pnlChucNang.add(Box.createHorizontalStrut(50));

		btnSua = new RoundedButton("Sửa", null, 20, 0, 1.0f);
		btnSua.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnSua.setForeground(Color.WHITE);
		btnSua.setBackground(Color.decode("#ffc107"));
		btnSua.setIcon(new ImageScaler("/image/editHopDong_Icon.png", 24, 24).getScaledImageIcon());
		btnSua.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnSua);
		pnlChucNang.add(Box.createHorizontalStrut(50));

		btnXoa = new RoundedButton("Xóa", null, 20, 0, 1.0f);
		btnXoa.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(Color.decode("#dc3545"));
		btnXoa.setIcon(new ImageScaler("/image/deleteHD_Icon.png", 24, 24).getScaledImageIcon());
		btnXoa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnXoa);
		pnlChucNang.add(Box.createHorizontalStrut(50));

		pnlChucNang.add(Box.createHorizontalStrut(100));

		btnLuu = new RoundedButton("Lưu", null, 20, 0, 0.6f);
		btnLuu.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setBackground(Color.decode("#28a745"));
		btnLuu.setIcon(new ImageScaler("/image/save_Icon.png", 24, 24).getScaledImageIcon());
		btnLuu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnLuu);
		pnlChucNang.add(Box.createHorizontalStrut(40));

		btnHuy = new RoundedButton("Hủy", null, 20, 0, 0.6f);
		btnHuy.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBackground(Color.decode("#ffc107"));
		btnHuy.setIcon(new ImageScaler("/image/cancelHD_Icon.png", 24, 24).getScaledImageIcon());
		btnHuy.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		pnlChucNang.add(btnHuy);

		// create JPanel in Pb
		JPanel pnlListPb = new JPanel();
		pnlListPb.setLayout(new BoxLayout(pnlListPb, BoxLayout.X_AXIS));
		add(pnlListPb, BorderLayout.CENTER);

		String cols[] = { "Mã Phòng ban", "Tên Phòng ban", "Số Nhân Viên", "Mô tả" };
		tabModel = new DefaultTableModel(cols, 0);

		tblPb = new JTable(tabModel);

		tbhPb = new JTableHeader(tblPb.getColumnModel());
		tbhPb.setReorderingAllowed(false);
		tbhPb.setBackground(componentColor);
		tbhPb.setForeground(Color.WHITE);
		tbhPb.setFont(fontText);
		tblPb.setTableHeader(tbhPb);

		tblPb.setRowHeight(20);
		tblPb.getColumnModel().getColumn(0).setPreferredWidth(100);

		// create scroll table
		JScrollPane scrPb = new JScrollPane(tblPb, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlListPb.add(scrPb);

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

		hanleButtonSaveAndCanle(false);
		setEditTextFiled(false);

		txtMaPhongBan.setText("PB0001");

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
		if (o == btnThem) {
			hanleButtonSaveAndCanle(true);
			setEditTextFiled(true);
			resetTextFiled();
		}
		if (o == btnSua) {
			hanleButtonSaveAndCanle(true);
			setEditTextFiled(true);
		}
		if (o == btnXoa) {

		}
		if(o==btnLuu) {
			hanleButtonSaveAndCanle(false);
			setEditTextFiled(false);
		}
		if(o==btnHuy) {
			hanleButtonSaveAndCanle(false);
			setEditTextFiled(false);
		}
	}

	private void hanleButtonSaveAndCanle(boolean display) {
		if (display == true) {
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
		} else {
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
		}
	}

	private void setEditTextFiled(boolean edit) {
		if (edit == true) {
			txtMaPhongBan.setEditable(true);
			txtTenPb.setEditable(true);
			txtSoNv.setEditable(true);
			txtMota.setEditable(true);
		} else {
			txtMaPhongBan.setEditable(false);
			txtTenPb.setEditable(false);
			txtSoNv.setEditable(false);
			txtMota.setEditable(false);
		}
	}

	private void resetTextFiled() {
		txtMaPhongBan.setText("");
		txtTenPb.setText("");
		txtSoNv.setText("");
		txtMota.setText("");
	}

}
