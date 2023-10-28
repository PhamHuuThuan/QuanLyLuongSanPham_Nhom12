package UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import CustomUI.ImageScaler;

import java.awt.Panel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class TrangCaNhan extends JPanel{
	private MainUI main;
	private Font fontText;
	private JFrame frameEditTrangCaNhan; 
	private JTextField txtName;
	private JTextField txtMa;
	private JComboBox<String> cmbGioiTinh;
	private JTextField jdcNgaySinh;	
	private JTextField txtSoDt;
	private JTextField txtEmail;
	private JTextField txtSoCCCD;
	private JTextField txtNgayVaoLam;
	
	public TrangCaNhan(MainUI main) {
		this.main = main;
		fontText = main.roboto_regular.deriveFont(Font.PLAIN, 16F);
		

		setLayout(new BorderLayout(0, 0));
		 
		JPanel pnlTitile = new JPanel();
		pnlTitile.setBackground(new Color(255, 255, 255));
		add(pnlTitile, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("TRANG CÁ NHÂN");
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitile.add(lblTitle);
		
		JPanel pnlBodyTrangCaNhan = new JPanel();
		add(pnlBodyTrangCaNhan);
		pnlBodyTrangCaNhan.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlBodyTrangCaNhan.add(scrollPane);
		
		JPanel pnlIfnormation = new JPanel();
		pnlIfnormation.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(pnlIfnormation);
		pnlIfnormation.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin c\u00E1 nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(20, 15, 15, 15)));
		pnlIfnormation.setLayout(new BoxLayout(pnlIfnormation, BoxLayout.X_AXIS));
		
		JPanel pnlBox_left = new JPanel();
		pnlBox_left.setBackground(new Color(255, 255, 255));
		pnlIfnormation.add(pnlBox_left);
		pnlBox_left.setLayout(new GridLayout(10, 1, 0, 0));
		
		JLabel lblName = new JLabel("Họ Tên");
		pnlBox_left.add(lblName);
		
		JLabel lblMa = new JLabel("Mã CN");
		pnlBox_left.add(lblMa);
		
		JLabel lblGioiTinh = new JLabel("Giới tính");
		pnlBox_left.add(lblGioiTinh);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		pnlBox_left.add(lblNgaySinh);
		
		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		pnlBox_left.add(lblSoDienThoai);
		
		JLabel lblEmail = new JLabel("Email");
		pnlBox_left.add(lblEmail);
		
		JLabel lblSoCCCD = new JLabel("SỐ CCCD");
		pnlBox_left.add(lblSoCCCD);
		
		JLabel lblNgayVaoLam = new JLabel("Ngày vào làm");
		pnlBox_left.add(lblNgayVaoLam);
		
		JPanel pngBox_right = new JPanel();
		pngBox_right.setBackground(new Color(255, 255, 255));
		pnlIfnormation.add(pngBox_right);
		pngBox_right.setLayout(new GridLayout(10, 2, 0, 0));
		
		txtName = new JTextField("Nguyễn Văn Phong");
		pngBox_right.add(txtName);
		txtName.setColumns(10);
		
		txtMa = new JTextField("CN00004");
		pngBox_right.add(txtMa);
		txtMa.setColumns(10);
		
		cmbGioiTinh = new JComboBox<>();
		cmbGioiTinh.addItem("nam");
		cmbGioiTinh.addItem("nữ");
		pngBox_right.add(cmbGioiTinh);
		
		jdcNgaySinh = new JTextField("12/02/2000");
		pngBox_right.add(jdcNgaySinh);
		
		txtSoDt = new JTextField("0992888832");
		pngBox_right.add(txtSoDt);
		txtSoDt.setColumns(10);
		
		txtEmail = new JTextField("abcd_admin@gmail.com");
		pngBox_right.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtSoCCCD = new JTextField("123981230982");
		pngBox_right.add(txtSoCCCD);
		txtSoCCCD.setColumns(10);
		
		txtNgayVaoLam = new JTextField("12/09/2018");
		pngBox_right.add(txtNgayVaoLam);
		txtNgayVaoLam.setColumns(10);
		
		JPanel pnlControl = new JPanel();
		pnlBodyTrangCaNhan.add(pnlControl);
		pnlControl.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBoxInfor = new JPanel();
		pnlBoxInfor.setBorder(new EmptyBorder(10, 30, 20, 30));
		pnlControl.add(pnlBoxInfor);
		pnlBoxInfor.setLayout(new BorderLayout(0, 10));
		
		JLabel lblAvatar = new JLabel("");
		lblAvatar.setIcon(new ImageScaler("/image/image_account.jpg",340,340).getScaledImageIcon());
		lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
		pnlBoxInfor.add(lblAvatar);
		
		Panel panel_1 = new Panel();
		pnlBoxInfor.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnEditInfo = new JButton("Cập nhật thông tin cá nhân");
		btnEditInfo.setVisible(true);
		panel_1.add(btnEditInfo);
		
		JButton btnSave = new JButton("Lưu");
		btnSave.setVisible(false);
		panel_1.add(btnSave);
		
		JButton btnCannelEdit = new JButton("Hủy");
		btnCannelEdit.setVisible(false);
		panel_1.add(btnCannelEdit);
		EditTrangCaNhan(false);
		
		btnEditInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEditInfo.setVisible(false);
				btnSave.setVisible(true);
				btnCannelEdit.setVisible(true);
				EditTrangCaNhan(true);
			}
		});
		
		btnCannelEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEditInfo.setVisible(true);
				btnSave.setVisible(false);
				btnCannelEdit.setVisible(false);
				EditTrangCaNhan(false);
			}
		});
		
		
		
		Panel panel = new Panel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(70);
		flowLayout.setVgap(10);
		pnlControl.add(panel, BorderLayout.SOUTH);
		
		JLabel lblVersion = new JLabel("Version 2.1.0");
		panel.add(lblVersion);
		
		JButton btnLogOut = new JButton("ĐĂNG XUẤT");
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.setBackground(new Color(186, 81, 69));
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(main, "Bạn có muốn đăng xuất khỏi hệ thống?","ĐĂNG XUẤT HỆ THỐNG",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE );
				if(result==JOptionPane.YES_OPTION) {
					main.setVisible(false);
					Login_UI loginUi = new Login_UI();
					loginUi.setVisible(true);
				}else if(result==JOptionPane.NO_OPTION) {
					
				}
			}
		});
		panel.add(btnLogOut);
	}
	
	public void EditTrangCaNhan(boolean editer) {
		 if(editer==true) {
			 txtName.setEditable(true);
			 txtMa.setEditable(true);
			 cmbGioiTinh.setEnabled(true);
			 jdcNgaySinh.setEditable(true);
			 txtSoDt.setEditable(true);
			 txtEmail.setEditable(true);
			 txtSoCCCD.setEditable(true);
			 txtNgayVaoLam.setEditable(true);
		 }else {
			 txtName.setEditable(false);
			 txtMa.setEditable(false);
			 cmbGioiTinh.setEnabled(false);
			 jdcNgaySinh.setEditable(false);
			 txtSoDt.setEditable(false);
			 txtEmail.setEditable(false);
			 txtSoCCCD.setEditable(false);
			 txtNgayVaoLam.setEditable(false);
		 }
	}
	
}
