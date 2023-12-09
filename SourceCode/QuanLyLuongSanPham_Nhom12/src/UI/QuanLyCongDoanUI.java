package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import Dao.CongDoan_Dao;
import Dao.HopDong_Dao;
import Dao.SanPham_Dao;
import Entity.CongDoan;
import Entity.HopDong;
import Entity.SanPham;
import Util.SinhMaTuDong;

public class QuanLyCongDoanUI extends JPanel implements ActionListener, MouseListener {

	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField txtMaSP, txtMaCD, txtTenCD, txtThuTu, txtTinhTrang, txtSoLuong, txtDonGia, txtNgayHT;
	private RoundedButton btnThem, btnSua, btnXoa, btnLuu, btnHuy, btnIn, btnFocus;
	private DefaultTableModel dtblModelCD, dtblModelSP;
	private JTable tblCD, tblSP;
	private JXDatePicker dtpNgayHT;
	private JTableHeader tbhCD, tbhSP;
	private JPanel pnlChucNang;
	private JLabel lblMessage;
	private JTextArea txaTinhTrang;
	private JSpinner spnSoLuong, spnThuTu;
	private CongDoan_Dao cd_Dao = new CongDoan_Dao();
	private SanPham_Dao sp_Dao = new SanPham_Dao();
	private ArrayList<SanPham> dsSP = new ArrayList<>();
	private ArrayList<CongDoan> dsCD = new ArrayList<>();
	private boolean isThem = false;
	private JSpinner modelCD;

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

		// Tao jpanel Title
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBackground(bgColor);
		pnlNorth.add(pnlTitle, BorderLayout.NORTH);

		// Tiêu đề
		JLabel lblTitle = new JLabel("QUẢN LÝ CÔNG ĐOẠN");
		lblTitle.setForeground(textColor);
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle.add(lblTitle);

		// Danh sách Cong Doan
		JPanel pnlDSSP = new JPanel();
		pnlDSSP.setLayout(new BorderLayout());
		pnlDSSP.setBackground(bgColor);
		TitledBorder titleBorderDSHD = BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), ("Thông tin sản phẩm"));
		pnlDSSP.setBorder(
				BorderFactory.createCompoundBorder(titleBorderDSHD, BorderFactory.createEmptyBorder(10, 5, 5, 5)));
		add(pnlDSSP, BorderLayout.WEST);

		// Bảng Sản phâmr
		String colsSPName[] = { main.read_file_languages.getString("lblMaSP"),
				main.read_file_languages.getString("lblTenSP"), main.read_file_languages.getString("lblSoLuong"),
				main.read_file_languages.getString("lblDonGia") };
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

		JScrollPane scrHD = new JScrollPane(tblSP, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlDSSP.add(scrHD, BorderLayout.CENTER);

		// Tao jpanel Thong tin công đoạn
		JPanel pnlThongTinCD = new JPanel();
		pnlThongTinCD.setLayout(new BoxLayout(pnlThongTinCD, BoxLayout.Y_AXIS));
		pnlThongTinCD.setBackground(bgColor);
		TitledBorder titleBorderTTSP = BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), ("Thông tin công đoạn"));
		titleBorderTTSP.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlThongTinCD.setBorder(
				BorderFactory.createCompoundBorder(titleBorderTTSP, BorderFactory.createEmptyBorder(20, 50, 20, 50)));
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
		txtMaSP.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
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
		txtMaCD.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
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
		txtTenCD.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 20, 5, 20)));
		txtTenCD.setBackground(bgColor);
		b1.add(txtTenCD);

		pnlThongTinCD.add(Box.createVerticalStrut(20));

		// Tao box chua thong tin hang 2:

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
		txtThuTu.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
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
		txtDonGia.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
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
		CompoundBorder cboBorder = BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor),
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

		// Tao box chua thong tin hang 4:
		Box b4 = Box.createHorizontalBox();
		pnlThongTinCD.add(b4);
		JLabel lblTinhTrang = new JLabel("Tình trạng:");
		lblTinhTrang.setForeground(textColor);
		lblTinhTrang.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		b4.add(lblTinhTrang);

		b4.add(Box.createHorizontalStrut(15));

		txtTinhTrang = new JTextField();
		txtTinhTrang.setForeground(textColor);
		txtTinhTrang.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtTinhTrang.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtTinhTrang.setBackground(bgColor);
		txtTinhTrang.setColumns(7);
		b4.add(txtTinhTrang);
		b4.add(Box.createHorizontalStrut(75));

		pnlThongTinCD.add(Box.createVerticalStrut(20));

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
		btnDateBD.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor),
						BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		dtpNgayHT.getEditor().setBackground(bgColor);
		dtpNgayHT.getEditor().setForeground(textColor);
		b4.add(dtpNgayHT);
		b4.add(Box.createHorizontalStrut(30));

		JPanel pnlMessage = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlMessage.setBackground(bgColor);
		pnlThongTinCD.add(pnlMessage);
		pnlMessage.add(lblMessage = new JLabel());
		lblMessage.setForeground(Color.decode("#dc3545"));
		lblMessage.setFont(main.roboto_regular.deriveFont(Font.BOLD, 14F));

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
		String cols[] = { "Mã CĐ", "Tên CĐ", "Thứ tự ", "Đơn giá ", "Số lượng ", "Tình trạng", "Ngày hoàn thành ",
				"Ghi chú" };
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
		JScrollPane scrCD = new JScrollPane(tblCD, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlBangCD.add(scrCD, BorderLayout.CENTER);
		// Tiếp theo, bạn có thể thêm các ActionListener và MouseListener như bạn đã làm
		// trước đó.
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
		tblCD.addMouseListener(this);
		tblSP.addMouseListener(this);

		// get danh sách hợp đồng từ cơ sở dữ liệu
		dsSP = sp_Dao.getALLSanPham();
		themTatCaSanPhamVaoBang(dsSP);
		xoaRong(false);

		dsCD = cd_Dao.getAllCongDoan();
		if (dsCD.size() > 0) {
			themTatCaCongDoanVaoBang(dsCD);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblCD) {
			int index = tblCD.getSelectedRow();
			if (index != -1) {
				hienThiThongTinCD(index);
			}
		}

		if (e.getSource() == tblSP) {
			int index = tblSP.getSelectedRow();
			if (index != -1) {
				txtMaSP.setText(tblSP.getValueAt(index, 0).toString());
				dsCD = cd_Dao.getCongDoanTheoSanPham(tblSP.getValueAt(index, 0).toString());
				themTatCaCongDoanVaoBang(dsCD);
			}
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

	public void actionPerformed(ActionEvent e) {
		lblMessage.setText("");
		Object o = e.getSource();
		main.music.playSE(2);
		if (o == btnThem) {
			System.out.println("555555");
			hanleButtonSaveAndCanle(true);
			xoaRong(true);
			resetTextFiled();
			txtMaCD.setText(new SinhMaTuDong().sinhMaCD());

			isThem = true;

		}
		if (o == btnSua) {
			isThem = false;
			hanleButtonSaveAndCanle(true);
			xoaRong(true);
			// Lấy chỉ số của hàng được chọn

		}

		if (o == btnXoa) {
			xoaCongDoan();
		}
		if (o == btnLuu) {
			if (isThem == true) {
				themCongDoan();
			} else {
				suaCongDoan();
			}
			hanleButtonSaveAndCanle(false);
			xoaRong(false);
		}
		if (o == btnHuy) {
			hanleButtonSaveAndCanle(false);
			xoaRong(false);
		}
	}

// HÀM HANLE BUTTON SAVE AND CANLE
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

// HÀM XÓA RỖNG
	private void xoaRong(boolean edit) {
		if (edit == true) {
			txtDonGia.setEditable(true);
			txtTenCD.setEditable(true);
			txtDonGia.setEditable(true);
			txtThuTu.setEditable(true);
			txtTinhTrang.setEditable(true);

		} else {
			txtMaCD.setEditable(false);
			txtMaSP.setEditable(false);
			txtTenCD.setEditable(false);
			txtDonGia.setEditable(false);
			txtThuTu.setEditable(false);
			txtTinhTrang.setEditable(false);

		}
	}

//xóa rỗng thông tin Cong doan
	private void resetTextFiled() {
		txtMaCD.setText(new SinhMaTuDong().sinhMaCD());
		txtTenCD.setText("");
		txtThuTu.setText("");
		txtDonGia.setText("");
		txtTinhTrang.setText("");
		dtpNgayHT.setDate(null);

	}

//hiển thị border cho button được user nhấn
	private void setBorderForFocusButton(Object o) {
		if (btnFocus != null && btnFocus != o) {
			btnFocus.setFocusButton(null, 0);
		}
		if (btnFocus == null) {
			btnFocus = (RoundedButton) o;
			btnFocus.setFocusButton(main.borderFocusColor, 3);
		} else if (btnFocus == btnThem || btnFocus == btnSua) {
			if (o == btnHuy || o == btnLuu) {
				btnFocus = (RoundedButton) o;
				btnFocus.setFocusButton(main.borderFocusColor, 3);
			}
		} else if (btnFocus == btnLuu || btnFocus == btnHuy) {
			if (o == btnThem || o == btnSua || o == btnXoa || o == btnIn) {
				btnFocus = (RoundedButton) o;
				btnFocus.setFocusButton(main.borderFocusColor, 3);
			}
		} else {
			btnFocus = (RoundedButton) o;
			btnFocus.setFocusButton(main.borderFocusColor, 3);
		}
	}

//kiểm tra dữ liệu người dùng nhập vào có đúng không
	private boolean validDataCD() {
		String maCD = txtMaCD.getText();
		String tenCD = txtTenCD.getText();
		int thuTu = Integer.parseInt(txtThuTu.getText());
		int soLuong = Integer.parseInt(spnSoLuong.getValue().toString());
		String donGia = txtDonGia.getText().replace(",", "");

		if (!maCD.matches("\\S+") || !maCD.matches("^CD\\d{7}$")) {
			setTextError("Mã công đoạn phải có dạng: CD12345!");
			return false;
		}
		if (tenCD == null || tenCD.trim().length() <= 0) {
			setTextError("Tên công đoạn không được rỗng");
			return false;
		}
		if (thuTu < 0) {
			setTextError("Số lượng sản phẩm phải lớn hơn 0");
			return false;
		}
		if (soLuong < 0) {
			setTextError("Số lượng sản phẩm phải lớn hơn 0");
			return false;
		}
//	if(donGia.matches("\\d+")==false && Double.parseDouble(donGia)<0) {
//		setTextError("Đơn giá công đoạn có định dạng #,### hoặc chỉ gồm số và >= 0");
//		return false;
//	}

		return true;
	}

//thêm một hợp đồng vào table 
	private void themSanPhamVaoBang(SanPham sp) {
		String[] row = new String[10];
		row[0] = sp.getMaSP();
		row[1] = sp.getTenSP();
		row[2] = String.valueOf(sp.getSoLuong());
		row[3] = new DecimalFormat("#,###").format(sp.getDonGia());

		dtblModelSP.addRow(row);
	}

	// thêm một ds hợp đồng vào bảng
	private void themTatCaSanPhamVaoBang(ArrayList<SanPham> list) {
		dtblModelSP.setRowCount(0);
		for (SanPham sp : list) {
			themSanPhamVaoBang(sp);
		}
	}

///thêm một sản phẩm vào table 
	private void themCongDoanVaoBang(CongDoan cd) {
		String[] row = new String[10];
		row[0] = cd.getMaCD();
		row[1] = cd.getTenCD();
		row[2] = String.valueOf(cd.getThuTu());
		row[3] = new DecimalFormat("#,###").format(cd.getDonGia());
		row[4] = String.valueOf(cd.getSoLuong());
		row[5] = String.valueOf(cd.isTinhTrang());
		row[6] = new SimpleDateFormat("dd-MM-yyyy").format(cd.getNgayHoanThanh());

		dtblModelCD.addRow(row);
	}

	// thêm một ds công đoạn vào bảng
	private void themTatCaCongDoanVaoBang(ArrayList<CongDoan> list) {
		dtblModelCD.setRowCount(0);
		for (CongDoan cd : list) {
			themCongDoanVaoBang(cd);
		}
	}

//chuyển dữ liệu thành đối tượng công đoạn
	private CongDoan convertDataToCongDoan() {
		String maSP = txtMaSP.getText();
		String maCD = txtMaCD.getText();
		String tenCD = txtTenCD.getText();
		int thuTu = Integer.parseInt(txtThuTu.getText());
		String donGiaStr = txtDonGia.getText().replace(",", "");
		double donGia = Double.parseDouble(donGiaStr);
		int soLuong = Integer.parseInt(spnSoLuong.getValue().toString());

		Date ngayHT = dtpNgayHT.getDate();

		return new CongDoan(maCD, tenCD, thuTu, soLuong, donGia, false, ngayHT, new SanPham(maSP));
	}

//get dữ liệu từ csdl lên table
	private void getDataToTable(int index) {
		dsCD = cd_Dao.getCongDoanTheoSanPham(tblSP.getValueAt(index, 0).toString());
		themTatCaCongDoanVaoBang(dsCD);
	}

//Thêm sản phẩm từ giao diện vào csdl
	private void themCongDoan() {
		if (cd_Dao.getCongDoanTheoMa(txtMaCD.getText()) == null) {
			if (validDataCD() == true) {
				CongDoan cdNew = convertDataToCongDoan();
				if (cdNew != null) {
					if (cd_Dao.themCongDoan(cdNew)) {
						lblMessage.setText("Thêm thành công Công đoạn");
						dsCD = cd_Dao.getAllCongDoan();
						themCongDoanVaoBang(cdNew);
						resetTextFiled();
					} else {
						setTextError("Thêm công đoạn thất bại! Trùng mã!");
					}
				} else {
					setTextError("Thêm công đoạn thất bại! Có lỗi xảy ra!");
				}
			}
		} else {
		}
	}

// sửa một sản phẩm được chọn
	private void suaCongDoan() {
		if (tblCD.getSelectedRow() != -1) {
			if (validDataCD() == true) {
				CongDoan cdNew = convertDataToCongDoan();
				if (cdNew != null) {
					if (cd_Dao.suaCongDoan(cdNew)) {
						dsCD.set(tblCD.getSelectedRow(), cdNew);
						themTatCaCongDoanVaoBang(dsCD);
						lblMessage.setText("Sửa thành công Công đoạn!");
						resetTextFiled();
					} else {
						setTextError("Sửa Công đoạn thất bại! Không tìm thấy trong csdl!");
					}
				} else {
					setTextError("Sửa Công đoạn thất bại! Có lỗi xảy ra!");
				}
			}
		} else {
			setTextError("Bạn cần chọn một công đoạn cần sửa!");
		}
	}

//Xóa sản phẩm được chọn
	private void xoaCongDoan() {
		int index = tblCD.getSelectedRow();
		// if(index != -1 && cd_Dao.getCongDoanTheoMa(txtMaCD.getText()).size()>1) {
		if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa cong doan đã chọn?", "Cảnh báo!",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			System.out.println("check xoa cd");
			if (cd_Dao.xoaCongDoan(dsCD.get(index).getMaCD())) {
				lblMessage.setText("Xóa thành công công đoạn!");
				dsCD = cd_Dao.getAllCongDoan();
				themTatCaCongDoanVaoBang(dsCD);
				resetTextFiled();
			} else {
				setTextError("Có lỗi ở csdl");
			}

		} else {
//			setTextError("Xóa thất bại! Không tìm thấy công đoạn trong csdl!");
			setTextError("Bạn cần chọn công đoạn muốn xóa và không thể xóa toàn bộ công đoạn");
		}

	}

//}
//Hiển thị sản phẩm được chọn từ table lên bảng thông tin
	private void hienThiThongTinCD(int index) {
		if (index < 0) {
			return;
		}
		txtMaSP.setText(dsCD.get(index).getSanPham().getMaSP());
		txtMaCD.setText(dsCD.get(index).getMaCD());
		txtTenCD.setText(dsCD.get(index).getTenCD());
		txtThuTu.setText(dsCD.get(index).getThuTu() + "");
		txtDonGia.setText(new DecimalFormat("#,###").format(dsCD.get(index).getDonGia()));
		spnSoLuong.setValue(dsCD.get(index).getSoLuong());
		String dateString = (String) dtblModelCD.getValueAt(index, 6); // Lấy chuỗi ngày từ bảng
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			java.util.Date date = formatter.parse(dateString); // Chuyển đổi chuỗi thành java.util.Date
			dtpNgayHT.setDate(date); // Đặt giá trị cho JXDatePicker
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	private void setTextError(String message) {
		main.music.playSE(3);
		lblMessage.setText(message);
	}
}
