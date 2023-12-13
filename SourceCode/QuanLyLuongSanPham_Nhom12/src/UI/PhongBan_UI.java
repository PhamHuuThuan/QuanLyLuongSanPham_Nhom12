		package UI;
		
		import java.awt.BorderLayout;
		import java.awt.Color;
		
		import javax.imageio.ImageIO;
		import javax.swing.BorderFactory;
		import javax.swing.JPanel;
		
		import java.awt.Font;
		import java.awt.event.ActionEvent;
		import java.awt.event.ActionListener;
		import java.awt.event.MouseEvent;
		import java.awt.event.MouseListener;
		import java.io.File;
		import java.text.ParseException;
		import java.text.SimpleDateFormat;
		import java.util.ArrayList;
		import java.util.Calendar;
		import java.util.Date;
		import java.awt.FlowLayout;
		import javax.swing.JLabel;
		import javax.swing.JOptionPane;
		import javax.swing.BoxLayout;
		import javax.swing.JFileChooser;
		
		import java.awt.Component;
		import javax.swing.Box;
		import javax.swing.border.CompoundBorder;
		import javax.swing.border.TitledBorder;
		import javax.swing.filechooser.FileNameExtensionFilter;
		import javax.swing.table.DefaultTableModel;
		import javax.swing.table.JTableHeader;
		
		import CustomUI.ImageScaler;
		import CustomUI.RoundedButton;
		import Dao.NhanVien_Dao;
		import Dao.PhongBan_Dao;
		import Entity.NhanVien;
		import Entity.PhongBan;
		import Util.SinhMaTuDong;
		
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
			private JTextField txtMaPB, txtTenPB, txtSoNv, txtMota;
			private Font fontText;
			private RoundedButton btnThem, btnSua, btnXoa, btnLuu, btnHuy;
			private JTable tblPb;
			private JLabel lblMessage;
			private JTableHeader tbhPb;
			private RoundedButton btnFocus, btnIn;
			private DefaultTableModel tabModel;
			private PhongBan_Dao pb_Dao = new PhongBan_Dao();
			private ArrayList<PhongBan> dsPB = new ArrayList<>();
			private boolean isThem = false;
			private Object maPhongBan;
		
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
		
				txtMaPB = new JTextField();
				txtMaPB.setFont(fontText);
				txtMaPB.setForeground(textColor);
				txtMaPB.setBackground(bgColor);
				txtMaPB.setBorder(
						BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
								BorderFactory.createEmptyBorder(5, 20, 5, 20)));
				box_1.add(txtMaPB);
				txtMaPB.setColumns(10);
		
				Component horizontalStrut_1 = Box.createHorizontalStrut(50);
				box_1.add(horizontalStrut_1);
		
				JLabel lblTenPb = new JLabel("Tên Phòng Ban");
				box_1.add(lblTenPb);
		
				Component horizontalStrut_2 = Box.createHorizontalStrut(20);
				box_1.add(horizontalStrut_2);
		
				txtTenPB = new JTextField();
				txtTenPB.setForeground(Color.BLACK);
				txtTenPB.setFont(fontText);
				txtTenPB.setBackground(bgColor);
				txtTenPB.setColumns(10);
				txtTenPB.setBorder(
						BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, componentColor),
								BorderFactory.createEmptyBorder(5, 20, 5, 20)));
				;
				box_1.add(txtTenPB);
				txtTenPB.setColumns(10);
		
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
				
				JPanel pnlMessage = new JPanel(new FlowLayout(FlowLayout.CENTER));
				pnlMessage.setBackground(bgColor);
				pnlThongTinPhongBan.add(pnlMessage);
				pnlMessage.add(lblMessage = new JLabel(""));
				lblMessage.setForeground(Color.decode("#dc3545"));
				lblMessage.setFont(fontText.deriveFont(Font.ITALIC));
		
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
		
				String cols[] = {"STT", "Mã Phòng ban", "Tên Phòng ban", "Số Nhân Viên", "Mô tả" };
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
				tblPb.addMouseListener(this);
		
				hanleButtonSaveAndCanle(false);
				//Không thể chỉnh sửa txt
				setEditableForTextField(false);
				
				//Set giá trị mặc định để hiển thị
				txtMaPB.setText("PB12");
				txtTenPB.setText("PhongBan");
				txtSoNv.setText("1234");
				txtMota.setText("LL");
		
				
				//get du lieu len
				dsPB= pb_Dao.getAllPhongBan();
				themTatCaPhongBanVaoBang(dsPB);
				setEditableForTextField(false);
				resetTextFiled();
				
			}
		
		
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == tblPb) {
					int index = tblPb.getSelectedRow();
					if(index != -1) {
						hienThiThongTinPB(index);
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
				Object o = e.getSource();
				if (o == btnThem) {
					hanleButtonSaveAndCanle(true);
					setEditTextFiled(true);
					isThem = true;
					resetTextFiled();
				}
				if (o == btnSua) {
					isThem = false;
					hanleButtonSaveAndCanle(true);
					setEditTextFiled(true);
				    // Lấy chỉ số của hàng được chọn
				}
		
				if (o == btnXoa) {
				  xoaPhongBan();
				}
				if(o==btnLuu) {
					if(isThem==true) {
						themPhongban();	
					}else {
						suaPhongBan();
					}
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
					txtTenPB.setEditable(true);
					txtSoNv.setEditable(true);
					txtMota.setEditable(true);
				} else {
					txtMaPB.setEditable(false);
					txtTenPB.setEditable(false);
					txtSoNv.setEditable(false);
					txtMota.setEditable(false);
				}
			}
		
			private void resetTextFiled() {
				txtMaPB.setText(new SinhMaTuDong().sinhMaPB());
				txtTenPB.setText("");
				txtSoNv.setText("");
				txtMota.setText("");
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
				else if(btnFocus == btnThem || btnFocus == btnSua) {
					if(o == btnHuy || o == btnLuu) {
						btnFocus = (RoundedButton) o;
						btnFocus.setFocusButton(main.borderFocusColor, 3);
					}
				}else if(btnFocus == btnLuu || btnFocus == btnHuy) {
					if(o == btnThem || o == btnSua || o == btnXoa || o == btnIn) {
						btnFocus = (RoundedButton) o;
						btnFocus.setFocusButton(main.borderFocusColor, 3);
					}
				}else {
					btnFocus = (RoundedButton) o;
					btnFocus.setFocusButton(main.borderFocusColor, 3);
				}
			}
			//Hàm get dữ liệu trên txt ra đối tượng nhân viên
			private PhongBan convertDataToPhongBan() {
				String maPhongBan = txtMaPB.getText();
				String tenPb = txtTenPB.getText();
				String soNv = txtSoNv.getText();
				String moTa = txtMota.getText();
				
				return new PhongBan(maPhongBan, tenPb, Integer.parseInt(soNv), moTa);
			}
			//Hiển thị nhân viên được chọn từ table lên bảng thông tin
			private void hienThiThongTinPB(int index) {
				txtMaPB.setText(dsPB.get(index).getMaPhongBan());
				txtTenPB.setText(dsPB.get(index).getTenPhongBan());
				txtSoNv.setText(dsPB.get(index).getSoNhanVien()+"");
				txtMota.setText(dsPB.get(index).getMoTa());
			}
			
			private boolean validDataPB() {
			    String maPhongBan = txtMaPB.getText();
			    String tenPb = txtTenPB.getText();
			    String soNv = txtSoNv.getText();
			    String moTa = txtMota.getText();

			    if (!maPhongBan.matches("^PB\\d{2}$")) {
			        setTextError("Mã phòng ban phải có dạng: PB12!");
			        return false;
			    }

			    if (tenPb.trim().isEmpty()) {
			        setTextError("Tên Phòng ban không được để trống!");
			        return false;
			    }

			    if (soNv.matches("\\d+")) { // Kiểm tra xem soNv có phải là một chuỗi chỉ chứa số hay không
			        int soNvInt = Integer.parseInt(soNv); // Chuyển đổi chuỗi thành số nguyên
			        if (soNvInt <= 0) { // Sửa từ '<' thành '<=' để bao gồm cả trường hợp soNvInt bằng 0
			            setTextError("Số nhân viên phải lớn hơn 0!");
			            return false;
			        }
			    } else {
			        setTextError("Số nhân viên không hợp lệ!");
			        return false;
			    }

			    return true;
			}

			//Thêm nhân viên từ giao diện vào csdl
			private void themPhongban() {
				if(validDataPB()==true) {
					PhongBan pbNew = convertDataToPhongBan();
					if(pbNew != null) {
						if(pb_Dao.themPhongBan(pbNew)) {
							lblMessage.setText("Thêm phòng ban thành công!");
							themPhongBanVaoBang(pbNew);
							resetTextFiled();
						}else {
							setTextError("Thêm thất bại! Trùng mã!");
						}
					}else {
						setTextError("Thêm thất bại! Có lỗi xảy ra!");
					}
				}
			}
		
		
			// sửa một pb được chọn
			private void suaPhongBan() {
				if(tblPb.getSelectedRow()!=-1) {
					if(validDataPB()==true) {
						PhongBan pbNew = convertDataToPhongBan();
						if(pbNew != null) {
							if(pb_Dao.suaPhongBan(pbNew)) {
								lblMessage.setText("Sửa thành công!");
								displayButtonSaveAndCancel(false);
								setEditableForTextField(false);
								resetTextFiled();
								dsPB = pb_Dao.getAllPhongBan();
								themTatCaPhongBanVaoBang(dsPB);
							}else {
								setTextError("Sửa thất bại! Không tìm thấy phòng ban!");
							}
						}else {
							setTextError("Sửa thất bại! Lỗi không xác định!");
						}
					}
				}else {
					setTextError("Bạn cần chọn phòng ban muốn sửa!");
				}
			}
			
			
			private void setEditableForTextField(boolean b) {
				// TODO Auto-generated method stub
				
			}
		
			private void displayButtonSaveAndCancel(boolean b) {
				// TODO Auto-generated method stub
				
			}
		
			//Xóa phòng ban được chọn
			private void xoaPhongBan() {
				if(tblPb.getSelectedRow()!=-1) {
					String maPB = txtMaPB.getText();
					if(maPB != null) {
						if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa nhân viên đã chọn?", "Cảnh báo!", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
							if(pb_Dao.xoaPhongBan(maPB)) {
								lblMessage.setText("Xóa thành công!");
								displayButtonSaveAndCancel(false);
								setEditableForTextField(false);
								resetTextFiled();
								dsPB = pb_Dao.getAllPhongBan();
								themTatCaPhongBanVaoBang(dsPB);
							}else {
								setTextError("Xóa thất bại! Không tìm thấy nhân viên!");
							}
						}
					}else {
						setTextError("Xóa thất bại! Có lỗi xảy ra!");
					}
				}else {
					setTextError("Bạn cần chọn nhân viên muốn xóa!");
				}
			}
			// thông báo lỗi
			private void setTextError(String message) {
				main.music.playSE(3);
				lblMessage.setText(message);
			}
			//get danh sách phòng ban từ csdl lên table
			private void getDataToTable() {
				dsPB = pb_Dao.getAllPhongBan();
				themTatCaPhongBanVaoBang(dsPB);
			}
			//thêm một phòng ban vào table 
			private void themPhongBanVaoBang(PhongBan pb) {
			    Object[] row = new Object[10];
			    row[0] = tabModel.getRowCount() + 1;  // STT
			    row[1] = String.valueOf(tblPb.getRowCount());
			    row[1] = pb.getMaPhongBan();  // Mã NV
			    row[2] = pb.getTenPhongBan();  // Họ tên
			    row[3] = String.valueOf(pb.getSoNhanVien());  // SDT
			    row[4] = pb.getMoTa();  // Email
			    
			    tabModel.addRow(row);
			}
			//thêm một ds phòng ban vào bảng
			private void themTatCaPhongBanVaoBang(ArrayList<PhongBan> list) {
				tabModel.setRowCount(0);
			    for (PhongBan pb : list) {
			        themPhongBanVaoBang(pb);
			    }
			}
		}
		
