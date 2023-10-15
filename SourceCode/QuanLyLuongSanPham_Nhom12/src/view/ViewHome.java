package view;

import java.awt.BorderLayout;
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

@SuppressWarnings("serial")
public class ViewHome extends JFrame {

	public String pathFileLanguage;
	public String pathFileTheme;

	private void readPathFileLanguage() {
		try {
			FileReader reader = new FileReader("src/config/languages/selectedLanguage.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			pathFileLanguage = bufferedReader.readLine();
			System.out.println(pathFileLanguage);
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
			System.out.println(pathFileTheme);
			bufferedReader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public ViewHome() {

		readPathFileLanguage();
		readPathFileTheme();

		setTitle("TPT_FUNITURE");
		setSize(getToolkit().getScreenSize());
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		JLabel welcomeLabel = new JLabel("Chào mừng bạn đến với trang chủ");
		add(welcomeLabel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		ResourceBundle read_file_languages = ResourceBundle.getBundle(pathFileLanguage);
		ResourceBundle read_file_themes = ResourceBundle.getBundle(pathFileTheme);

		JButton button1 = new JButton(read_file_languages.getString("text_button_login"));
		JButton button2 = new JButton("#Nút 2");
		JButton button3 = new JButton("Nút 3");
		JButton button4 = new JButton(read_file_themes.getString("background_color_main"));

		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);

		add(buttonPanel, BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {

	}

}
