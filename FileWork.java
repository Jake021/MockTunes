package edu.century.finalproject.mocktunes;

import java.util.Scanner;
import java.io.*;

public interface FileWork {
	public SongCollection songs = new SongCollection();

	/**
	 * Definition: Reads the songdata.txt file and initializes the songs SongCollection 
	 * Precondition: requires songdata.txt 
	 * Postcondition: initializes the SongCollection to be used with the library
	 * @param file
	 */
	public static void readFile(String file) {
		Scanner reader;

		try {
			reader = new Scanner(new FileInputStream(file));

			while (reader.hasNext()) {
				String line = reader.nextLine();
				String tokens[] = line.split(" , ");

				String artist = tokens[0];
				String album = tokens[1];
				String songName = tokens[2];
				String genre = tokens[3];
				int year = Integer.parseInt(tokens[4]);

				Song song = new Song(artist, album, songName, genre, year);
				FileWork.songs.addSong(song);

			}
			reader.close();
		} catch (Exception e) {
			System.out.println("ERROR: Error in Read File method. " + "\nCheck spacing between commas in songdata.txt");
		}
	}

	/**
	 * Definition: Used by buyfromstore method to append a new song to the songdata.txt songlist
	 * Precondition: initialized arguments
	 * Postcondition: appends passed argument to end of file
	 * @param file
	 */
	public static void updateFile(Song song, String file) {

		String artist = song.getArtist();
		String album = song.getAlbum();
		String songname = song.getSongName();
		String genre = song.getGenre();
		int year = song.getYear();

		String appendedString = artist + " , " + album + " , " + songname + " , " + genre + " , " + year;

		try {
			PrintWriter readOut = new PrintWriter(new FileWriter(file, true));
			readOut.println(appendedString);
			readOut.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error with updating file");
		}

	}
	/**
	 * Definition: OverWrites the songdata.txt file with the newest songList (specifically for when songs are removed)
	 * Precondition: requires a songdata file
	 * Postcondition: overwrites the songdata file with the current FileWork.songs playlist
	 * @param filename
	 */
	public static void overWriteFile(String filename) {
		try {
			PrintWriter devastator = new PrintWriter(new FileWriter(filename, true));
			System.out.println(FileWork.songs.toString());
			devastator.print(FileWork.songs.toString());
		} catch (IOException e) {
			System.out.println("devastator died");
		}

	}

}
