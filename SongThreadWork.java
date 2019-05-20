package edu.century.finalproject.mocktunes;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class SongThreadWork implements LineListener, Runnable {

	Clip clip;
	boolean songover;
	volatile boolean running = true;

	// initializes a clip with the song provided via filename
	/**
	 * Definition: Constructor for SongThreadWork
	 * Precondition: requires a valid filename
	 * Postcondition: loads a clip class object with a .wav file and opens it ready for playing
	 * @param filename
	 */
	public SongThreadWork(String filename) {
		try {
			File file = new File(filename);
			if (file.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.addLineListener(this);
				clip.open(audioInput);
			} else {
				System.out.println("didn't work");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Definition: Play method to play the Clip object
	 * Precondition: only works with .wav files
	 * Postcondition: starts playing the song that is loaded in the Clip object
	 */
	public void play() {
		System.out.println("before");
		clip.setFramePosition(0);
		clip.start();
		while (!songover) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * Definition: Stopping method to stop the Clip object from running
	 * Precondition: requires a running Clip object
	 * Postcondition: stops the Clip 
	 */
	public void stop() {
		clip.stop();
	}


	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();
		if (type == LineEvent.Type.START) {
		}
	}

	// creates a thread specifically for the clip, and plays when play button,
	/**
	 * Definition: run method that the thread looks for
	 * Precondition: .wav files in program
	 * Postcondition: starts the thread for the Clip
	 */
	@Override
	public void run() {
		while (running) {
			clip.setFramePosition(0);
			clip.start();
			while (!songover) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	// stops the song playing, running is supposed to kill the loop but clip.stop
	/**
	 * Definition: Stops thread from running in background
	 * Precondition: requires a thread to be running
	 * Postcondition: stops the thread
	 */
	public void stopThread() {
		running = false;
		songover = true;
		clip.stop();
	}
}
