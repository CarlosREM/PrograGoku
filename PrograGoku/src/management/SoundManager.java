package management;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundManager {
	
	private static String currentTrackName = "No track";
	private static Music currentTrack = null;
	
	private static final String resPath = "res/sound/";
	
	public static String getTrackName() { return currentTrackName; }
	
	public static void playMusic(String trackName) throws SlickException {
		currentTrackName = trackName;
		currentTrack = new Music(resPath + "theme_" + trackName + ".ogg");
		currentTrack.loop();
	}
	
	public static void stopMusic() {
		currentTrack.stop();
	}
	
	public static void playSound(String path) throws SlickException {
		Sound sfx = new Sound(resPath + "sfx_" + path + ".wav");
		sfx.play();
	}
}
