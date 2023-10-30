package Util;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	Clip clip;
	URL[] soundURL = new URL[30];
	FloatControl fc;
	int volumeScale = 3;
	float volume;
	
	// set url tới file âm thanh
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/loadingSound.wav");
		soundURL[1] = getClass().getResource("/sound/export_Sound.wav");
		soundURL[2] = getClass().getResource("/sound/mouseClickSound.wav");
		soundURL[3] = getClass().getResource("/sound/wrong-answer-126515.wav");
	}
	
	//set audio từ url
	public void setFile(int i) {
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//bắt đầu
	public void play() {
		clip.start();
	}
	// tạo vòng lặp
	public void loop() {
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	//dừng âm thanh
	public void stop() {
		clip.stop();
	}
	// âm lượng của âm thanh
	public void checkVolume() {
		switch(volumeScale) {
		case 0:	volume = -80f;	break;
		case 1:	volume = -20f;	break;
		case 2:	volume = -12f;	break;
		case 3:	volume = -5f;	break;
		case 4:	volume = 1f;	break;
		case 5:	volume = 6f;	break;
		}
		fc.setValue(volume);
	}
}