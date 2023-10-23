package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

import CustomUI.RoundedButton;
import CustomUI.RoundedPanel;

import java.awt.Font;

@SuppressWarnings("serial")
public class Home_UI extends JPanel {

	public String pathFileLanguage;
	public String pathFileTheme;
	private JTextField txtFff;

	private void readPathFileLanguage() {
		try {
			FileReader reader = new FileReader("src/config/languages/selectedLanguage.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			pathFileLanguage = bufferedReader.readLine();
			bufferedReader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void readPathFileTheme() {
		try {
			FileReader reader = new FileReader("src/config/themes/selectedTheme.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			pathFileTheme = bufferedReader.readLine();
			bufferedReader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public Home_UI() {

		readPathFileLanguage();
		readPathFileTheme();

		ResourceBundle read_file_languages = ResourceBundle.getBundle(pathFileLanguage);
		ResourceBundle read_file_themes = ResourceBundle.getBundle(pathFileTheme);

		setLayout(new BorderLayout(0, 0));

		JPanel topRow = new JPanel();
		topRow.setBorder(new EmptyBorder(25, 0, 0, 0));
		topRow.setLayout(new GridLayout(1, 3)); 

		
		topRow.add(Box.createHorizontalStrut(10)); 
		
		
		RoundedPanel pnl_box_1 = new RoundedPanel( new Color(64, 0, 128), 20, 3);
		pnl_box_1.setBorder(new LineBorder(new Color(64, 0, 128), 3, true));
		topRow.add(pnl_box_1);

		JLabel lblSoPhongBan = new JLabel("Số Phòng Ban");
		lblSoPhongBan.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblSoPhongBan.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNumberPb = new JLabel("22");
		lblNumberPb.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNumberPb.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_box_1.setLayout(new GridLayout(0, 1, 0, 0));
		pnl_box_1.add(lblSoPhongBan);
		pnl_box_1.add(lblNumberPb);
		
		topRow.add(Box.createHorizontalStrut(20)); 

		RoundedPanel pnl_box_2 = new RoundedPanel(new Color(0, 0, 0), 20, 3);
		topRow.add(pnl_box_2);
		pnl_box_2.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblSoNhanVien = new JLabel("Số Nhân Viên");
		pnl_box_2.add(lblSoNhanVien);
		lblSoNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNumberNv = new JLabel("33");
		lblNumberNv.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_box_2.add(lblNumberNv);
		
		topRow.add(Box.createHorizontalStrut(20)); 

		RoundedPanel pnl_box_3 = new RoundedPanel(new Color(0, 0, 0), 20, 3);
		topRow.add(pnl_box_3);
		pnl_box_3.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblSoCongNhan = new JLabel("Số Công Nhân");
		pnl_box_3.add(lblSoCongNhan);
		lblSoCongNhan.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNumberCn = new JLabel("12");
		lblNumberCn.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_box_3.add(lblNumberCn);

		topRow.add(Box.createHorizontalStrut(10)); 
		
		topRow.setPreferredSize(new Dimension(0, 90));

		JPanel rootPanel = new JPanel(new BorderLayout());
		rootPanel.add(topRow, BorderLayout.NORTH);

		
		add(rootPanel);

		JPanel panel = new JPanel();
		rootPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(20, 0, 0, 0));
		panel.add(panel_1);

		JLabel pnlIntroduce = new JLabel("CÔNG TY TPT_FUNITURE");
		panel_1.add(pnlIntroduce);
		pnlIntroduce.setFont(new Font("Tahoma", Font.PLAIN, 28));

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Home_UI());
	}
}
