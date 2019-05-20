package edu.century.finalproject.mocktunes;

import javax.swing.JOptionPane;

public class Library implements FileWork {
	public final static String DATA_FILE = "songdata.txt";
	public SongCollection songs;
	public Song song;
	public PlaylistQueue playlist;
	public static SongCollection s; // new
	/**
	 * Definition: No argo Constuctor.
	 * Precondition: multiple classes have to exist.
	 * Postcondition: Creates multiple variables for this class.
	 */
	public Library() {
		songs = new SongCollection();
		playlist = new PlaylistQueue();
		FileWork.readFile(DATA_FILE);
		this.songs.addAll(FileWork.songs);

	}

	/**
	 * Definition: Adds the song object to the play list queue
	 * Precondition: Song object exist.
	 * Postcondition: Adds the song to the queue.
	 * @param song
	 */
	public void addToPlaylist(Song song) {
		playlist.enqueue(song);
		// System.out.println(this.playlist);
	}

	/**
	 * Definition: Plays the next song in the play list
	 * Precondition: Must have something in the queue
	 * Postcondition: Goes to next song in the queue.
	 * @return
	 */
	public String playNextSongInPlaylist() {
		return playlist.dequeue().trim() + ".wav";
	}

	/**
	 * Definition: Buys the song to store and adds it to the users library.
	 * Precondition: Song object has to exist.
	 * Postcondition: adds the song to the users library.
	 * @param song
	 */
	public static void buyFromStore(Song song) {
		if (FileWork.songs.searchBySongname(song.getSongName().trim()))
			JOptionPane.showMessageDialog(null, "Song already in library!"
                    , "Error", 1);

		else
			FileWork.updateFile(song, "songdata.txt");
	}

	/**
	 * Definition: Removes the current song selected from the play list.
	 * Precondition: Song must be selected in the queue
	 * Postcondition: Removes the song from the queue.
	 * @param song
	 */
	public static void removeFromLibrary(Song song) {

		if (FileWork.songs.searchBySongname(song.getSongName().trim())) {
			FileWork.songs.removeSong(song);
			FileWork.overWriteFile("songdata.txt");
		} else
			System.out.println("Song not in library!");
	}

	/**
	 * Definition: Gets the songs from the songs collection class.
	 * Precondition: SongCollections class must exist.
	 * Postcondition: Adds the music to the Library.
	 * @return
	 */
	public SongCollection getSongs() {
		return this.songs;
	}

}
