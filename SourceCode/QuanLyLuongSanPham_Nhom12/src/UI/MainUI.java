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

import Util.ImportFont;

public class MainUI extends JFrame {

	private JPanel contentPane;
	public Font roboto_light, roboto_bold, roboto_regular;
	private ImportFont impFont;
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
		impFont = new ImportFont();
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
			roboto_light = impFont.importFontFromFile("/fonts/Roboto-Light.ttf");
			roboto_bold = impFont.importFontFromFile("/fonts/Roboto-Bold.ttf");
			roboto_regular = impFont.importFontFromFile("/fonts/Roboto-Regular.ttf");
	}
}
