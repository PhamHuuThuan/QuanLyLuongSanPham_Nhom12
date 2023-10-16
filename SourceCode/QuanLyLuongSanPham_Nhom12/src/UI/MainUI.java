package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class MainUI extends JFrame {

	private JPanel contentPane;
	public Font roboto_light, roboto_bold, roboto_regular;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainUI() {
		getFonts();
		setTitle("TPT_FUNITURE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setBounds(0, 0, 1500, 800);
		add(new MenuUI(this), BorderLayout.NORTH);
		
	}
	public void getFonts() {
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/Roboto-Light.ttf");
			roboto_light = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/fonts/Roboto-Bold.ttf");
			roboto_bold = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf");
			roboto_regular = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
