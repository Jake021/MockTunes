package edu.century.finalproject.mocktunes;

public class SongCollection {
	private Song[] songList;
	private int index;
	
	/**
	 * Definition: getter for index variable
	 * Precondition: no precondition needed
	 * Postcondition: returns the index instance value
	 * @return
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Definition: Constructor for SongCollection
	 * Precondition: 
	 * Postcondition: initializes an array of Songs to size 20, and initializes the index 
	 */
	public SongCollection() {
		final int INITIAL_CAPACITY = 20;
		songList = new Song[INITIAL_CAPACITY];
		index = 0;
	}

	/**
	 * Definition: Adds a Song class object to SongCollection's songList
	 * Precondition: requires a SongCollection array
	 * Postcondition: adds song to the SongCollection, and resizes array if needed via ensureCapacity
	 * @param song
	 */
	public void addSong(Song song) {

		if (index == songList.length) {
			ensureCapacity(index * 2 + 1);
		} else {
			songList[index] = song;
			index++;
		}
	}

	/**
	 * Definition: ensureCapacity to extend the length of SongCollection's songList array when needed
	 * Precondition: requires songList to not be able to fit anymore values + positive integer passed
	 * Postcondition: extends the size of songList array by passed integer
	 * @param i
	 */
	private void ensureCapacity(int i) {
		System.out.print("in ensurecapacity");
		Song[] tmp = new Song[i];

		for (int a = 0; a < songList.length; a++) {
			tmp[a] = songList[a];
		}

		songList = tmp;
	}

	/**
	 * Definition: Removes the Song object passed which matches the Song object in the songList
	 * Precondition: requires initialized songlist
	 * Postcondition: removes the song
	 * @param song
	 */
	public void removeSong(Song song) {
		for (int b = 0; b < index; b++) {
			if (song == songList[b]) {
				songList[b] = songList[index - 1];
				index--;

			}
		}
	}

	/**
	 * Definition: Searches for a song inside the songList array by its name
	 * Precondition: requires initialized songlist
	 * Postcondition: returns true if the songlist contains the passed songname
	 * @param songname
	 * @return
	 */
	public boolean searchBySongname(String songname) {
		for (int i = 0; i < index; i++) {
			if (songname.equals(songList[i].getSongName().trim()))
				return true;
		}

		return false;

	}

	/**
	 * Definition: Searches for the passed Song object inside songList array
	 * Precondition: requires initialized Song object and songList array
	 * Postcondition: returns the Song object matching the song you passed
	 * @param song
	 * @return
	 */
	public Song findSong(Song song) {
		if (song == null)
			throw new IllegalArgumentException();

		for (int a = 0; a < index; a++) {
			if (songList[a].getSongName().equals(song.getSongName()))
				return song;

		}
		return null;
	}

	/**
	 * Definition: toString formatted in the same style the FileWork methods read data 
	 * Precondition: songList needs to be initialized with data 
	 * Postcondition: outputs formatted String
	 */
	public String toString() {
		String data = "";

		for (int a = 0; a < index; a++) {
			data += songList[a].getArtist() + " , " + songList[a].getSongName() + " , " + songList[a].getAlbum() + " , "
					+ songList[a].getGenre() + " , " + songList[a].getYear() + "\r\n";
		}

		return data;
	}

	/**
	 * Definition: addAll method for SongCollection
	 * Precondition: requires two SongCollection objects to be initialized
	 * Postcondition: original SongCollection will append the argument SongCollection to it
	 * @param addend
	 */
	public void addAll(SongCollection addend) {
		if (index == songList.length)
			ensureCapacity((index + addend.index) * 2 + 1);
		System.arraycopy(addend.songList, 0, songList, index, addend.index);
		index = addend.index;

	}

}
