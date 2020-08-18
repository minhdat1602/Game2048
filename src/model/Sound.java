package model;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	private volatile static Sound uniqueInstance;
	private static Clip clip;
	private boolean playing;
	private static long clipTimePosition;

	public boolean isPlaying() {
		return playing;
	}

	private Sound() {
	}

	public static Sound getInstance() {
		if (uniqueInstance == null) {
			synchronized (Sound.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Sound();
				}
			}
		}
		return uniqueInstance;
	}

	public void playSoundTrack() {
		String soundTrack = "src/music/TungNuiMTP.wav";
		try {
			File filePath = new File(soundTrack);
			if (filePath.exists()) {
				AudioInputStream ais = AudioSystem.getAudioInputStream(filePath);
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				System.out.println("Playing soundtrack...");
			} else {
				System.out.println("Can't find filepath to soundtrack...");
			}
			playing = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loopSoundTrack() {
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		System.out.println("Playing soundtrack...");
	}

	public void startSoundTrack() {
		clip.setMicrosecondPosition(clipTimePosition);
		clip.start();
		playing = true;
		System.out.println("Resume soundtrack...");
	}

	public void stopSoundTrack() {
		clipTimePosition = clip.getMicrosecondPosition();
		clip.stop();
		playing = false;
		System.out.println("Pause soundtrack...");
	}

}