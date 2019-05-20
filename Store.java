package edu.century.finalproject.mocktunes;

public class Store implements FileWork {
	public SongCollection songsInStore;
	/**
	 * Definition: Store class constructor
	 * Precondition: 
	 * Postcondition: initializes songsInStore to a new SongCollection, and adds the songs that FileWork.songs grabs
	 */
	public Store() {
		songsInStore = new SongCollection();
		FileWork.readFile("storedata.txt");
		this.songsInStore.addAll(FileWork.songs);

	}
}
