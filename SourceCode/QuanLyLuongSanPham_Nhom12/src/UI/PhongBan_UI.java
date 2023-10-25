package UI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PhongBan_UI extends JPanel {
	private MainUI main;
	private Color bgColor = Color.WHITE;
	
	
	
	public PhongBan_UI(MainUI main) {
		this.main = main;
		
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(bgColor);
		
	}
}
