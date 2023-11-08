package UI;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import CustomUI.*;
import Dao.HeThong_Dao;
import Util.ConfigManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class OtherUI extends JPanel implements ChangeListener, ItemListener, ActionListener{
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JComboBox<String> cmbLanguage;
	private RoundedButton btnON, btnOFF, btnSaoLuu, btnKhoiPhuc, btnChonFile;
	private PlaceholderTextField txtSaoLuu, txtKhoiPhuc;
	private JSlider slider;
	private ConfigManager config = new ConfigManager("/config/config.properties");
	private HeThong_Dao ht_Dao = new HeThong_Dao();
	/**
	 * Create the panel.
	 */
	public OtherUI(MainUI main) {
		this.main = main;
		setLayout(new BorderLayout(0, 0));
		setBackground(bgColor);
		
		
		JPanel pnlCaiDat = new JPanel();
		add(pnlCaiDat, BorderLayout.NORTH);
		pnlCaiDat.setLayout(new BoxLayout(pnlCaiDat, BoxLayout.Y_AXIS));
		pnlCaiDat.setBackground(bgColor);
		pnlCaiDat.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
		
		JPanel pnlTitle1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlTitle1.setBackground(bgColor);
		pnlCaiDat.add(pnlTitle1);
		
		JLabel lblTitle1 = new JLabel(main.read_file_languages.getString("lblCDUD"));
		lblTitle1.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle1.add(lblTitle1);
		
		GridLayout grid = new GridLayout(2, 3);
		JPanel pnlCDUD = new JPanel(grid);
		pnlCDUD.setBackground(bgColor);
		pnlCDUD.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
		grid.setHgap(150);
		grid.setVgap(10);
		pnlCaiDat.add(pnlCDUD);
		pnlCaiDat.add(Box.createVerticalStrut(25));
		
		JLabel lblAmThanh = new JLabel(main.read_file_languages.getString("lblAmThanh"));
		lblAmThanh.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 18F));
		pnlCDUD.add(lblAmThanh);
		
		JLabel lblLanguage = new JLabel(main.read_file_languages.getString("lblLanguage"));
		lblLanguage.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 18F));
		pnlCDUD.add(lblLanguage);
		
		JLabel lblDarkMode = new JLabel("DarkMode");
		lblDarkMode.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 18F));
		pnlCDUD.add(lblDarkMode);

		slider = new JSlider(JSlider.HORIZONTAL, 0, 5, config.getSoundSetting());
		slider.setUI(new CustomSliderUI(slider, componentColor, 5, Color.GREEN, 15));
		slider.setBackground(bgColor);
        // Hiển thị các dấu gạch chính và phụ
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
		pnlCDUD.add(slider);
		
		cmbLanguage = new JComboBox();
		pnlCDUD.add(cmbLanguage);
		cmbLanguage.setModel(new DefaultComboBoxModel(new String[] {"Tiếng Việt", "English"}));
		Border cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cmbLanguage.setUI(new CustomComboBoxUI(new ImageScaler("/image/down-arrow.png", 18, 18).getScaledImageIcon(), bgColor, cboBorder));
		cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cmbLanguage.setRenderer(new CustomListCellRenderer(Color.decode("#DADBDD"), bgColor, cboBorder));
		cmbLanguage.setBackground(bgColor);
		cmbLanguage.setForeground(textColor);
		cmbLanguage.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		cmbLanguage.setSelectedIndex(config.getLanguage());
		
		JPanel pnlDarkMode = new JPanel();
		pnlDarkMode.setBackground(bgColor);
		pnlDarkMode.setLayout(new BoxLayout(pnlDarkMode, BoxLayout.X_AXIS));
		pnlDarkMode.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 200));
		pnlCDUD.add(pnlDarkMode);
		
		
		btnON = new RoundedButton("ON", componentColor, 0, 0, 0.6f);
		btnON.setForeground(bgColor);
		btnON.setBackground(componentColor);
		btnON.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnON.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
		pnlDarkMode.add(btnON);
		
		btnOFF = new RoundedButton("OFF", componentColor, 0, 0, 0.6f);
		btnOFF.setForeground(componentColor);
		btnOFF.setBackground(bgColor);
		btnOFF.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		btnOFF.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		pnlDarkMode.add(btnOFF);
		
		JPanel pnlTitle2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlTitle2.setBackground(bgColor);
		pnlCaiDat.add(pnlTitle2);
		
		JLabel lblTitle2 = new JLabel(main.read_file_languages.getString("lblSaoLuu"));
		lblTitle2.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle2.add(lblTitle2);
		
		JPanel pnlSaoLuu = new JPanel();
		pnlSaoLuu.setLayout(new BoxLayout(pnlSaoLuu, BoxLayout.Y_AXIS));
		pnlSaoLuu.setBackground(bgColor);
		pnlSaoLuu.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
		pnlCaiDat.add(pnlSaoLuu);
		pnlCaiDat.add(Box.createVerticalStrut(25));
		
		JPanel pnlB1= new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB1.setBackground(bgColor);
		pnlSaoLuu.add(pnlB1);
		
		txtSaoLuu = new PlaceholderTextField();
		txtSaoLuu.setPlaceholder(main.read_file_languages.getString("lblInputFile"));
		txtSaoLuu.setColumns(20);
		txtSaoLuu.setForeground(textColor);
		txtSaoLuu.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtSaoLuu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtSaoLuu.setBackground(bgColor);
		pnlB1.add(txtSaoLuu);
		
		JLabel lblBAK = new JLabel(".bak");
		pnlB1.add(lblBAK);
		pnlB1.add(Box.createHorizontalStrut(10));
		lblBAK.setForeground(textColor);
		lblBAK.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		btnSaoLuu = new RoundedButton(main.read_file_languages.getString("btnSaoLuu"), null, 20, 0, 1.0f);
		btnSaoLuu.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnSaoLuu.setForeground(Color.WHITE);
		btnSaoLuu.setBackground(Color.decode("#28a745"));
		btnSaoLuu.setIcon(new ImageScaler("/image/refresh.png", 24, 24).getScaledImageIcon());
		btnSaoLuu.setBorder(BorderFactory.createEmptyBorder(10, 35, 10, 35));
		pnlB1.add(btnSaoLuu);
		
		JPanel pnlGhiChu1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlGhiChu1.setBackground(bgColor);
		pnlSaoLuu.add(Box.createVerticalStrut(5));
		pnlSaoLuu.add(pnlGhiChu1);
		JLabel lblGhiChu1 = new JLabel(main.read_file_languages.getString("lblCBBU"));
		pnlGhiChu1.add(lblGhiChu1);
		lblGhiChu1.setForeground(textColor);
		lblGhiChu1.setFont(main.roboto_light.deriveFont(Font.ITALIC, 14F));
		
		JPanel pnlTitle3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlTitle3.setBackground(bgColor);
		pnlCaiDat.add(pnlTitle3);
		
		JLabel lblTitle3 = new JLabel(main.read_file_languages.getString("lblKhoiPhuc"));
		lblTitle3.setFont(main.roboto_bold.deriveFont(Font.BOLD, 22F));
		pnlTitle3.add(lblTitle3);
		
		JPanel pnlKhoiPhuc = new JPanel();
		pnlKhoiPhuc.setBackground(bgColor);
		pnlKhoiPhuc.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
		pnlKhoiPhuc.setLayout(new BoxLayout(pnlKhoiPhuc, BoxLayout.Y_AXIS));
		pnlCaiDat.add(pnlKhoiPhuc);
		
		JPanel pnlB2= new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlB2.setBackground(bgColor);
		pnlKhoiPhuc.add(pnlB2);
		
		txtKhoiPhuc = new PlaceholderTextField();
		txtKhoiPhuc.setEditable(false);
		txtKhoiPhuc.setPlaceholder(main.read_file_languages.getString("lblInputFileR"));
		txtKhoiPhuc.setColumns(20);
		txtKhoiPhuc.setForeground(textColor);
		txtKhoiPhuc.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		txtKhoiPhuc.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtKhoiPhuc.setBackground(bgColor);
		pnlB2.add(txtKhoiPhuc);
		
		btnChonFile = new RoundedButton("", null, 0, 0, 1.0f);
		btnChonFile.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnChonFile.setForeground(Color.WHITE);
		btnChonFile.setBackground(Color.decode("#3B71CA"));
		btnChonFile.setIcon(new ImageScaler("/image/folder (1).png", 24, 24).getScaledImageIcon());
		btnChonFile.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		pnlB2.add(btnChonFile);
		
		btnKhoiPhuc = new RoundedButton(main.read_file_languages.getString("btnKhoiPhuc"), null, 20, 0, 1.0f);
		btnKhoiPhuc.setFont(main.roboto_regular.deriveFont(Font.BOLD, 16F));
		btnKhoiPhuc.setForeground(Color.WHITE);
		btnKhoiPhuc.setBackground(Color.decode("#ffc107"));
		btnKhoiPhuc.setIcon(new ImageScaler("/image/folder.png", 24, 24).getScaledImageIcon());
		btnKhoiPhuc.setBorder(BorderFactory.createEmptyBorder(10, 35, 10, 35));
		pnlB2.add(btnKhoiPhuc);
		btnSaoLuu.setPreferredSize(btnKhoiPhuc.getPreferredSize());
		
		JPanel pnlGhiChu2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlGhiChu2.setBackground(bgColor);
		pnlKhoiPhuc.add(Box.createVerticalStrut(5));
		pnlKhoiPhuc.add(pnlGhiChu2);
		
		JLabel lblGhiChu2 = new JLabel(main.read_file_languages.getString("lblCBKP"));
		pnlGhiChu2.add(lblGhiChu2);
		lblGhiChu2.setForeground(Color.decode("#dc3545"));
		lblGhiChu2.setFont(main.roboto_light.deriveFont(Font.ITALIC, 14F));
		
		cmbLanguage.addItemListener(this);
		slider.addChangeListener(this);
		btnChonFile.addActionListener(this);
		btnSaoLuu.addActionListener(this);
		btnKhoiPhuc.addActionListener(this);
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == cmbLanguage && e.getStateChange() == ItemEvent.SELECTED) {
			config.setLanguage(cmbLanguage.getSelectedIndex());
			if(JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất để cập lại lại ngôn ngữ?", "Cảnh báo", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				main.dispose();
				Login_UI loginUi = new Login_UI(main);
				loginUi.setVisible(true);
			}
		}
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == slider) {
			int value = slider.getValue();
			config.setSoundSetting(value);
			main.music.music.setVolumeScale(value);
			main.music.se.setVolumeScale(value);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o==btnSaoLuu) {
			String fileInput = txtSaoLuu.getText();
			if(fileInput!=null && fileInput.trim().length()>=0) {
				
		        // Tạo thư mục backup nếu nó chưa tồn tại
		        File directory = new File("D:\\backup");
		        if (! directory.exists()){
		            directory.mkdir();
		        }
		        
		        //
		        String fileName = fileInput + ".bak"; 
		        if (ht_Dao.BackUp(fileName)) {
		            JOptionPane.showMessageDialog(this, "Sao lưu dữ liệu thành công!");
		        } else {
		            JOptionPane.showMessageDialog(this, "Sao lưu dữ liệu thất bại!");
		        }
			}
		}
		if(o == btnChonFile) {
			JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        int option = fileChooser.showOpenDialog(null);
	        if (option == JFileChooser.APPROVE_OPTION) {
	            File file = fileChooser.getSelectedFile();
	            String fileName = file.getAbsolutePath();
	            txtKhoiPhuc.setText(fileName);
	        }
		}
		if(o == btnKhoiPhuc) {
            String fileName = txtKhoiPhuc.getText();
            if(fileName!=null&&fileName.trim().length()>=0) {
            	if(JOptionPane.showConfirmDialog(this, "Tất cả dữ liệu hiện tại sẽ bị ghi đè,Bạn có chắc muốn khôi phục dữ liệu mới?", "Cảnh báo", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                    if (ht_Dao.Restore(fileName)) {
                        JOptionPane.showMessageDialog(null, "Khôi phục dữ liệu thành công!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Khôi phục dữ liệu thất bại!");
                    }
            	}
            }else {
            	
            }
		}
	}
}
