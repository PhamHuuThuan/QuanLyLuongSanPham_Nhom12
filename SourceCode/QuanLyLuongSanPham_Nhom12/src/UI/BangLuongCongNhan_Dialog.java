package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomUI.RoundedButton;
import Dao.TinhLuongCongNhan_Dao;
import Entity.BangLuongCongNhan;

import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class BangLuongCongNhan_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlContent = new JPanel();
	private JTextField txtMaCN;
	private JTextField txtTenCN;
	private JTextField txtSoNgayLam;
	private JTextField txtTongLuongThang;
	private JTextField txtTongLuongCongDoan;
	private JTextField txtThucLanh;
	private JTextField txtLuongCoBan;

	private MainUI main;
	private DefaultTableModel dtblModelTLCN, dtblModelCTCN;
	private JComboBox<String> cmbFilter;
	private JTable tblDSTL, tblCTCN;
	private JTableHeader tbhCNCC, tbhCTCN;
	private RoundedButton btnXuat;
	private RoundedButton btnTinhLuongTatCa, btnXemChiTiet;
	private TinhLuongCongNhan_Dao tlcn_dao = new TinhLuongCongNhan_Dao();
	private ArrayList<BangLuongCongNhan> dsBLCN = new ArrayList<>();
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BangLuongCongNhan_Dialog dialog = new BangLuongCongNhan_Dialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BangLuongCongNhan_Dialog() {
		setBounds(100, 100, 859, 471);
		getContentPane().setLayout(new BorderLayout());
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContent, BorderLayout.CENTER);
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
					{
						JLabel lblNewLabel = new JLabel("New label");
						scrCD.setRowHeaderView(lblNewLabel);
					}
				}
			}
		}
	}

}
