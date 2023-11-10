package UI;

import javax.swing.JPanel;

import CustomUI.ImageScaler;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class TrangChu_UI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TrangChu_UI() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBoxImg = new JPanel();
		add(pnlBoxImg);
		pnlBoxImg.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageScaler("/image/image_home.png", 1400, 700).getScaledImageIcon());
		pnlBoxImg.add(lblNewLabel);
		
	}
}
