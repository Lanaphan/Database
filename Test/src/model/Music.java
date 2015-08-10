package model;

import sun.audio.AudioStream;
import sun.audio.AudioPlayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("restriction")
public class Music {

	private String filename;
	private AudioStream stream;
	private String title;
	private String artist;
	private int like;
	
	/**
	 * Constructor that builds a music file with the location of file
	 * the title of the music
	 * and the artist of the music
	 * @param filename
	 * @param title
	 * @param artist
	 */
	public Music(String filename, String title, String artist) {
		this.filename = filename;
		this.title = title;
		this.artist = artist;
		this.like = 0;
	}
	
	/**
	 * Plays the music from the given filename
	 * @throws IOException if the file cannot be found or the audio player could not start
	 */
	public void play() throws IOException {
		if (stream == null) {
			InputStream in = new FileInputStream(filename);
			stream = new AudioStream(in);
		}
		AudioPlayer.player.start(stream);
	}
	
	/**
	 * Stops the music playing 
	 * @throws IOException when the music player could not start
	 */
	public void stop() throws IOException {
		if (stream != null) {
			AudioPlayer.player.stop(stream);
		}	
	}
	
	/**
	 * Clears the memory of all file paths before the garbage collector get it
	 */
	public void clearMemory() {
		stream = null;
		filename = null;
		title = null;
		artist = null;
	}
	
	/**
	 * Returns the title of the song
	 * @return String title
	 */
	public String getTitle() {
		String aTitle = title;
		return aTitle;
	}
	
	/**
	 * Returns the artist of the song
	 * @return String artist
	 */
	public String getArtist() {
		String aArtist = artist;
		return aArtist;
	}
	
	/**
	 * Returns the filename of the song
	 * @param String filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	/**
	 * Adds one to like
	 */
	public void incrementLike() {
		like++;
	}
	
	/**
	 * Returns the like counter
	 * @return int like
	 */
	public int getLike() {
		int aLike = like;
		return aLike;
	}

}

