package Util;

public class SoundPlay {
	public Sound music = new Sound();
	public Sound se = new Sound();
	
	//bắt đầu vòng lặp phát âm thanh
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	//dừng vòng lặp
	public void stopMusic() {
		music.stop();
	}
	// phát 1 âm thanh
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}
