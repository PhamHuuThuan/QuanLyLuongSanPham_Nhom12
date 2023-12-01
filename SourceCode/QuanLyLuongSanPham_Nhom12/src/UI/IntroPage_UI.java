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
	private MainUI main;
	private SoundPlay sound = new SoundPlay();
	
	ImageIcon appIcon = new ImageIcon("assets/logo_v1.png");
	
	
	private boolean isShowPageLogin = false;
	// HÀM MỞ PAGE LOGIN
	private void OpenPageLogin() {
		this.dispose();
		
		if(!isShowPageLogin) {
			Login_UI viewLogin = new Login_UI(main);
			viewLogin.setVisible(true);
			isShowPageLogin= true; 
		}
	}
	
	public IntroPage_UI() {
		this.setSize(1200,600);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		setIconImage(appIcon.getImage());
		
		ImageIcon gifAnimation = new ImageIcon("assets/crop_v8.gif");
		JLabel gifAniLabel = new JLabel(gifAnimation);
		
		
		JPanel PnlBoxLoading = new JPanel();
		
		PnlBoxLoading.setPreferredSize(new Dimension(1200,80));
		PnlBoxLoading.setBackground(Color.decode("#424242"));
		
		setLayout(new BorderLayout(0,0));
		
		//thanh loading
        JProgressBar pgbLoadingBar = new JProgressBar();
        pgbLoadingBar.setPreferredSize(new Dimension(500, 15));
        pgbLoadingBar.setStringPainted(true); 
        pgbLoadingBar.setForeground(Color.decode("#424244")); 
        pgbLoadingBar.setBackground(Color.WHITE); 

        Timer timer = new Timer(10, new ActionListener() {
            int progress = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(progress == 30)
            		sound.playSE(0); // chạy âm thanh loading
                if (progress < 70) {
                    progress++;
                    pgbLoadingBar.setValue(progress);
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
                pgbLoadingBar.setValue(100);
            }
        });

        timer2.setRepeats(false); 
        timer2.start();
		
		add(gifAniLabel,BorderLayout.CENTER);
		PnlBoxLoading.add(pgbLoadingBar, BorderLayout.NORTH);
		add(PnlBoxLoading,BorderLayout.SOUTH);
		
		
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
