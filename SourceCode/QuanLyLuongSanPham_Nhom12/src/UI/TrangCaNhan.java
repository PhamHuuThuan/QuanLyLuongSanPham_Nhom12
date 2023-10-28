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
	private JTextField textName;
	private JTextField textMa;
	private JComboBox<String> cmbGioiTinh;
	private JTextField jdcNgaySinh;	
	
	public TrangCaNhan(MainUI main) {
		this.main = main;
		fontText = main.roboto_regular.deriveFont(Font.PLAIN, 16F);
		

		setLayout(new BorderLayout(0, 0));
		 
		JPanel pnlTitile = new JPanel();
		add(pnlTitile, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Trang cá nhân");
		lblTitle.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitile.add(lblTitle);
		
		JPanel pnlBodyTrangCaNhan = new JPanel();
		add(pnlBodyTrangCaNhan);
		pnlBodyTrangCaNhan.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlBodyTrangCaNhan.add(scrollPane);
		
		JPanel pnlIfnormation = new JPanel();
		scrollPane.setViewportView(pnlIfnormation);
		pnlIfnormation.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin c\u00E1 nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(20, 15, 15, 15)));
		pnlIfnormation.setLayout(new BoxLayout(pnlIfnormation, BoxLayout.X_AXIS));
		
		JPanel panel_4 = new JPanel();
		pnlIfnormation.add(panel_4);
		panel_4.setLayout(new GridLayout(10, 1, 0, 0));
		
		JLabel lblName = new JLabel("Họ Tên");
		panel_4.add(lblName);
		
		JLabel lblMa = new JLabel("Mã CN");
		panel_4.add(lblMa);
		
		JLabel lblGioiTinh = new JLabel("Giới tính");
		panel_4.add(lblGioiTinh);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		panel_4.add(lblNgaySinh);
		
		JPanel panel_2 = new JPanel();
		pnlIfnormation.add(panel_2);
		panel_2.setLayout(new GridLayout(10, 2, 0, 0));
		
		textName = new JTextField("Nguyễn Văn Phong");
		panel_2.add(textName);
		textName.setColumns(10);
		
		textMa = new JTextField("CN00004");
		panel_2.add(textMa);
		textMa.setColumns(10);
		
		cmbGioiTinh = new JComboBox<>();
		cmbGioiTinh.addItem("nam");
		cmbGioiTinh.addItem("nữ");
		panel_2.add(cmbGioiTinh);
		
		jdcNgaySinh = new JTextField("12/02/2000");
		panel_2.add(jdcNgaySinh);
		
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
		
		JButton btnLogOut = new JButton("Đăng xuất ");
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
			 textName.setEditable(true);
			 textMa.setEditable(true);
			 cmbGioiTinh.setEnabled(true);
			 jdcNgaySinh.setEditable(true);
		 }else {
			 textName.setEditable(false);
			 textMa.setEditable(false);
			 cmbGioiTinh.setEnabled(false);
			 jdcNgaySinh.setEditable(false);
		 }
	}
	
}
