package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Util.SoundPlay;


@SuppressWarnings("serial")
public class IntroPage_UI extends JFrame{
	
	private SoundPlay sound = new SoundPlay();
	private boolean isShowPageLogin = false;
	private void OpenPageLogin() {
		this.dispose();
		
		if(!isShowPageLogin) {
			Login_UI viewLogin = new Login_UI();
			viewLogin.setVisible(true);
			isShowPageLogin= true;
		}
	}
	
	public IntroPage_UI() {
		this.setSize(1200,600);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		
		ImageIcon gifAnimation = new ImageIcon("assets/crop_v8.gif");
		JLabel gifAniLabel = new JLabel(gifAnimation);
		
		
		JPanel box_loading = new JPanel();
		
		box_loading.setPreferredSize(new Dimension(1200,80));
		box_loading.setBackground(Color.decode("#424242"));
		
		setLayout(new BorderLayout(0,0));
		
		//loading
        JProgressBar loadingBar = new JProgressBar();
        loadingBar.setPreferredSize(new Dimension(500, 15));
        loadingBar.setStringPainted(true); 
        loadingBar.setForeground(Color.decode("#424244")); 
        loadingBar.setBackground(Color.WHITE); 

        Timer timer = new Timer(10, new ActionListener() {
            int progress = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(progress == 30)
            		sound.playSE(0); // chạy âm thanh loading
                if (progress < 70) {
                    progress++;
                    loadingBar.setValue(progress);
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.setInitialDelay(0); 
        timer.start();

        Timer timer2 = new Timer(3800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadingBar.setValue(100);
            }
        });

        timer2.setRepeats(false); 
        timer2.start();
		
		add(gifAniLabel,BorderLayout.CENTER);
		box_loading.add(loadingBar, BorderLayout.NORTH);
		add(box_loading,BorderLayout.SOUTH);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		Timer timeClosePage = new Timer(4000,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OpenPageLogin();
				
			}
		});
		timeClosePage.start();
		
	}
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			new IntroPage_UI();
		});
		
	}
}
