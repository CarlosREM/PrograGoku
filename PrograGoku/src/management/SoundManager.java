package management;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundManager {
	
	private static String currentTrackName = "No track";
	private static Music currentTrack = null;
	private static float volume = 1;
	private static final String resPath = "res/sound/";
	
	public static String getTrackName() { return currentTrackName; }
	
	public static float getVolume() { return volume; }
	public static void setVolume(float volume) {
		SoundManager.volume = volume;
		currentTrack.setVolume(volume);
	}
	
	public static void playMusic(String trackName) {
		try {
			currentTrackName = trackName;
			currentTrack = new Music(resPath + "theme_" + trackName + ".ogg");
			currentTrack.setVolume(volume);
			currentTrack.loop();
		}
		catch(SlickException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void stopMusic() {
		currentTrack.stop();
	}
	
	public static void playSound(String path) {
		try {
			Sound sfx = new Sound(resPath + "sfx_" + path + ".wav");
			sfx.play();
		}
		catch(SlickException ex) {
			ex.printStackTrace();
		}
	}
}
