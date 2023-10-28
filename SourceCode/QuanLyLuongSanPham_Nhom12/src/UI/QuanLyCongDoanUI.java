package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.JXDatePicker;

import CustomUI.CustomComboBoxUI;
import CustomUI.CustomListCellRenderer;
import CustomUI.ImageScaler;
import CustomUI.RoundedButton;

public class QuanLyCongDoanUI extends JPanel implements ActionListener, MouseListener{
		private MainUI main;
		private Color bgColor = Color.WHITE;
		private Color componentColor = Color.decode("#424242");
		private Color textColor = Color.BLACK;
		private JTextField txtMaCD, txtTenCD, txtDonGia, txtNgayHoanThanh, txtMaSP, txtThuTu, txtTinhTrang, txtSoLuong;
		private RoundedButton btnThem, btnSua, btnXoa, btnLuu, btnHuy, btnFocus;
		private DefaultTableModel dtblModelSP;
		private JTable tblSP;
		private JTableHeader tbhSP;
		private JTable tblCD;
		private JTableHeader tbhCD;
		private JPanel pnlChucNang;
		private JComboBox cmbTinhTrang;
		private JXDatePicker dtpNgayHoanThanh ;
		private TableModel dtblModel;
		/**
		 * Create the panel.
		 */
		public QuanLyCongDoanUI(MainUI main) {
			this.main = main;
			
			//set gia tri cho jpanel CongDoan
			setLayout(new BorderLayout(0, 0));
			setBackground(bgColor);
			
			//tao jpanel chua Title va Thong tin CD
			JPanel pnNorth = new JPanel();
			pnNorth.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
			pnNorth.setBackground(bgColor);
			add(pnNorth, BorderLayout.NORTH);
			pnNorth.setLayout(new BorderLayout(0, 0));
			
			//Tao jpanel Title
			JPanel pnTitle = new JPanel();
			pnTitle.setBackground(bgColor);
			pnNorth.add(pnTitle, BorderLayout.NORTH);
			
			JLabel lblTitle = new JLabel("QUẢN LÝ CÔNG ĐOẠN");
			lblTitle.setForeground(textColor);
			lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
			pnTitle.add(lblTitle);
			
			//Danh sách SP
			JPanel pnlDSSP = new JPanel();
			pnlDSSP.setLayout(new BorderLayout());
			pnlDSSP.setBackground(bgColor);
			TitledBorder titleBorderDSSP = BorderFactory.createTitledBorder(
	                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), main.read_file_languages.getString("border_title_SP"));
			pnlDSSP.setBorder(BorderFactory.createCompoundBorder(titleBorderDSSP, BorderFactory.createEmptyBorder(10, 5, 5, 5)));
			add(pnlDSSP, BorderLayout.WEST);
			
			//Bảng San Pham
						JPanel pnlBangSP = new JPanel();
						pnlBangSP.setLayout(new BoxLayout(pnlBangSP, BoxLayout.X_AXIS));
						add(pnlBangSP, BorderLayout.CENTER);
						String cols1[] = {"Mã SP", "Tên NV", "Số Lượng ", "Đơn giá "};
						dtblModel = new DefaultTableModel(cols1, 0);
						tblSP = new JTable(dtblModel);
						
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
						

						// Tạo JScrollPane sau khi đã gắn tblSP với DefaultTableModel
						JScrollPane scrSP = new JScrollPane(tblSP, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						pnlBangSP.add(scrSP);
			
						//Tao jpanel Thong tin hop dong
						JPanel pnThongTinCD = new JPanel();
						pnThongTinCD.setLayout(new BoxLayout(pnThongTinCD, BoxLayout.Y_AXIS));
						pnThongTinCD.setBackground(bgColor);
						TitledBorder titleBorder = BorderFactory.createTitledBorder(
				                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Thông tin công đoạn");
						titleBorder.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
						pnThongTinCD.setBorder(BorderFactory.createCompoundBorder(titleBorder, BorderFactory.createEmptyBorder(10, 50, 10, 50)));
						pnNorth.add(pnThongTinCD, BorderLayout.CENTER);
			
			// Tao box chua cac phan tu hang 1:
			Box b1 = Box.createHorizontalBox();
			pnThongTinCD.add(b1);
			
			JLabel lblMaCD = new JLabel("Mã CĐ:");
			lblMaCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			lblMaCD.setForeground(textColor);
			b1.add(lblMaCD);
			b1.add(Box.createHorizontalStrut(10));
			
			txtMaCD = new JTextField();
			txtMaCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			txtMaCD.setForeground(textColor);
			txtMaCD.setBackground(bgColor);
			txtMaCD.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
			b1.add(txtMaCD);
			b1.add(Box.createHorizontalStrut(30));
			
			JLabel lblTenCD = new JLabel("Tên CĐ:");
			lblTenCD.setForeground(textColor);
			lblTenCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			b1.add(lblTenCD);
			b1.add(Box.createHorizontalStrut(10));
			
			txtTenCD = new JTextField();
			txtTenCD.setForeground(textColor);
			txtTenCD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			txtTenCD.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
			txtTenCD.setBackground(bgColor);
			b1.add(txtTenCD);
			b1.add(Box.createHorizontalStrut(30));
			
			//dong 2
			Box b2 = Box.createHorizontalBox();
			pnThongTinCD.add(b2);
			JLabel lblMaSP = new JLabel("Mã SP :");
			lblMaSP.setForeground(textColor);
			lblMaSP.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			b2.add(lblMaSP);
			b2.add(Box.createHorizontalStrut(10));
			
			txtMaSP = new JTextField();
			txtMaSP.setForeground(textColor);
			txtMaSP.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			txtMaSP.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
			txtMaSP.setBackground(bgColor);
			b2.add(txtMaSP);
			b2.add(Box.createHorizontalStrut(30));
			
			JLabel lblDonGia = new JLabel("Đơn giá :");
			lblDonGia.setForeground(textColor);
			lblDonGia.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			b1.add(lblDonGia);
			b1.add(Box.createHorizontalStrut(10));
			
			txtDonGia = new JTextField();
			txtDonGia.setForeground(textColor);
			txtDonGia.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			txtDonGia.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
			txtDonGia.setBackground(bgColor);
			b1.add(txtDonGia);
			
			pnThongTinCD.add(Box.createVerticalStrut(20));
			
			//Tao box chua thong tin hang 3: 
			Box b3 = Box.createHorizontalBox();
			pnThongTinCD.add(b3);
			
			// ngay bat dau
			JLabel lblNgayHoanThanh = new JLabel("Ngày hoàn thành:");
			lblNgayHoanThanh.setForeground(textColor);
			lblNgayHoanThanh.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			b3.add(lblNgayHoanThanh);
			
			b3.add(Box.createHorizontalStrut(10));
			
			dtpNgayHoanThanh = new JXDatePicker(new Date());
			dtpNgayHoanThanh.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
			dtpNgayHoanThanh.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			dtpNgayHoanThanh.setBackground(bgColor);
			dtpNgayHoanThanh.setForeground(textColor);
			dtpNgayHoanThanh.setLocale(new Locale("vi", "VN"));	// set thoi gian local la VN
			JButton btnDateBD = (JButton) dtpNgayHoanThanh.getComponent(1);
			btnDateBD.setIcon(new ImageScaler("/image/calendar_icon.png", 18, 18).getScaledImageIcon());
			btnDateBD.setBackground(bgColor);
			btnDateBD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			dtpNgayHoanThanh.getEditor().setBackground(bgColor);
			dtpNgayHoanThanh.getEditor().setForeground(textColor);
			b3.add(dtpNgayHoanThanh);
			
			b3.add(Box.createHorizontalStrut(30));
			
			
			
			JLabel lblThuTu = new JLabel("Thứ tự:");
			lblThuTu.setForeground(textColor);
			lblThuTu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			b3.add(lblThuTu);
			
			b3.add(Box.createHorizontalStrut(10));
			
			txtThuTu = new JTextField();
			txtThuTu.setForeground(textColor);
			txtThuTu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			txtThuTu.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
			txtThuTu.setBackground(bgColor);
			b3.add(txtThuTu);

			b3.add(Box.createHorizontalStrut(30));
			
			Box b4 = Box.createHorizontalBox();
			pnThongTinCD.add(b4);
			JLabel lblTinhTrang = new JLabel("Tình trạng:");
			lblTinhTrang.setForeground(textColor);
			lblTinhTrang.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			b4.add(lblTinhTrang);
			
			b4.add(Box.createHorizontalStrut(10));
			
			txtTinhTrang = new JTextField();
			txtTinhTrang.setForeground(textColor);
			txtTinhTrang.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			txtTinhTrang.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
			txtTinhTrang.setBackground(bgColor);
			b4.add(txtTinhTrang);
			
			pnThongTinCD.add(Box.createVerticalStrut(20));
	
			JLabel lblSoLuong = new JLabel("Số lượng:");
			lblSoLuong.setForeground(textColor);
			lblSoLuong.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			b4.add(lblSoLuong);
			
			b4.add(Box.createHorizontalStrut(10));
			
			txtSoLuong = new JTextField();
			txtSoLuong.setForeground(textColor);
			txtSoLuong.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
			txtSoLuong.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, componentColor));
			txtSoLuong.setBackground(bgColor);
			b4.add(txtSoLuong);
			
			pnThongTinCD.add(Box.createVerticalStrut(10));
			
			//Khởi tạo jpanel chức năng chứa các button chức năng: thêm, sửa, xóa, lưu, hủy
			pnlChucNang = new JPanel();
			pnNorth.add(pnlChucNang, BorderLayout.SOUTH);
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
			
			// tạo jpanel chứa table cong doan
			JPanel pnlBangCD = new JPanel();
			pnlBangCD.setLayout(new BoxLayout(pnlBangCD, BoxLayout.X_AXIS));
			add(pnlBangCD, BorderLayout.CENTER);
			String cols[] = {"Mã CĐ", "Tên CĐ", "Thứ tự ", "Đơn giá ", "Số lượng ", "Tình trạng", "Ngày hoàn thành ", "Ghi chú"};
			dtblModel = new DefaultTableModel(cols, 0);
			tblCD = new JTable(dtblModel);
			
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
			pnlBangCD.add(scrCD);

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
			//Không thể thao tác với button lưu và hủy
			displayButtonSaveAndCancel(false);
			
			//Không thể chỉnh sửa txt
			setEditableForTextField(false);
		}


		private void setLayout(BorderLayout borderLayout) {
			// TODO Auto-generated method stub
			
		}
		private void add(JPanel pnlBangHD, String center) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {

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
			Object o = e.getSource();
			if(o == btnThem) {
				displayButtonSaveAndCancel(true);
				setEditableForTextField(true);
				xoaRong();
				
			}
			if(o == btnSua) {
				displayButtonSaveAndCancel(true);
				setEditableForTextField(true);
				
			}
			if(o == btnXoa) {
				
			}
			if(o == btnLuu) {
				displayButtonSaveAndCancel(false);
				setEditableForTextField(false);
				
			}
			if(o == btnHuy) {
				displayButtonSaveAndCancel(false);
				setEditableForTextField(false);
				
			}
		}
		public void displayButtonSaveAndCancel(boolean display) {
			if(display == true) {
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
				
			}else {
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
		public void setEditableForTextField(boolean edit) {
			if(edit == true) {
				txtMaCD.setEditable(true);
				txtTenCD.setEditable(true);
				txtMaSP.setEditable(true);
				txtDonGia.setEditable(true);
				dtpNgayHoanThanh.setEditable(true);
				txtThuTu.setEditable(true);
				txtTinhTrang.setEditable(true);
				txtSoLuong.setEditable(true);
			}else {
				txtMaCD.setEditable(false);
				txtTenCD.setEditable(false);
				txtMaSP.setEditable(false);
				txtDonGia.setEditable(false);
				dtpNgayHoanThanh.setEditable(false);
				txtThuTu.setEditable(false);
				txtTinhTrang.setEditable(false);
				txtSoLuong.setEditable(false);
			}
		}
		public void xoaRong() {
			txtMaCD.setText("");
			txtTenCD.setText("");
			txtMaSP.setText("");
			txtDonGia.setText("");
			dtpNgayHoanThanh.setDate(new Date());

			txtThuTu.setText("");
			txtTinhTrang.setText("");

			txtSoLuong.setText("");
		}
		//hiển thị border cho button được user nhấn
		public void setBorderForFocusButton(Object o) {
			if(btnFocus!=null && btnFocus!=o) {
				btnFocus.setFocusButton(null, 0);
			}
			if(btnFocus==null) {
				btnFocus = (RoundedButton) o;
				btnFocus.setFocusButton(main.borderFocusColor, 3);
			}
			else if(btnFocus == btnThem || btnFocus == btnSua) {
				if(o == btnHuy || o == btnLuu) {
					btnFocus = (RoundedButton) o;
					btnFocus.setFocusButton(main.borderFocusColor, 3);
				}
			}else if(btnFocus == btnLuu || btnFocus == btnHuy) {
				if(o == btnThem || o == btnSua || o == btnXoa) {
					btnFocus = (RoundedButton) o;
					btnFocus.setFocusButton(main.borderFocusColor, 3);
				}
			}else {
				btnFocus = (RoundedButton) o;
				btnFocus.setFocusButton(main.borderFocusColor, 3);
			}
		}
	}