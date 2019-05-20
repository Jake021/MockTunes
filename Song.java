package edu.century.finalproject.mocktunes;

public class Song {
	private String artist;
	private String album;
	private String songName;
	private String genre;
	private int year;
	public ReviewsNode reviews;

	// some constant values for genre, want to keep the genres as broad as possible
	final String ROCK = "Rock";
	final String RAP = "Hip Hop";
	final String COUNTRY = "Country";
	final String ELECTRONIC = "Electronic";
	final String GOSPEL = "Gospel"; // lolgospel

	/**
	 * Definition: Gets the artists name.
	 * Precondition: Requires something to be initialized.
	 * Postcondition: Returns the artist name.
	 * @return
	 */
	public String getArtist() {
		return artist;
	}
	
	/**
	 * Definition: Sets the artists name.
	 * Precondition: Sets the objects name.
	 * Postcondition: Sets the artists name.
	 * @param artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	/**
	 * Definition: Gets the albums name and applies to the object.
	 * Precondition: Must have a varaible to set this to.
	 * Postcondition: Returns the albums name.
	 * @return
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * Definition: Sets the albums name.
	 * Precondition: Must have a variable to set.
	 * Postcondition: Sets the album name.
	 * @param album
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * Definition: Gets the song name.
	 * Precondition: Must have some variable to get.
	 * Postcondition: Returns the song name.
	 * @return
	 */
	public String getSongName() {
		return songName;
	}

	/**
	 * Definition: Sets the song name.
	 * Precondition: Must have some variable to get.
	 * Postcondition: Sets the song name.
	 * @param songName
	 */
	public void setSongName(String songName) {
		this.songName = songName;
	}

	/**
	 * Definition: Gets the genre.
	 * Precondition: Must have some variable to get.
	 * Postcondition: Returns the genre.
	 * @return
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Definition: Sets the song genre.
	 * Precondition: Must have some variable to set genre to.
	 * Postcondition: Sets the genre.
	 * @param genre
	 */
	public void setgenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Definition: Gets the year of the song.
	 * Precondition: Must have some variable to get.
	 * Postcondition: Returns the year.
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Definition: Sets the year of the object.
	 * Precondition: Must have an object to set to.
	 * Postcondition: Sets the year of an object.
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Definition: No Args Constructor Method: Sets the variables up to have them getted and setted.
	 * Precondition: Variables must exist.
	 * Postcondition: Sets the variable to their value.
	 */
	public Song() {
		artist = null;
		album = null;
		songName = null;
		genre = null;
		year = 1900;
		reviews = new ReviewsNode();
	}

	/**
	 * Definition: Overloading the no args constructor.
	 * Precondition: Variables must exist.
	 * Postcondition: Sets the varaibles.
	 * @param artist
	 * @param album
	 * @param songName
	 * @param genre
	 * @param year
	 */
	public Song(String artist, String album, String songName, String genre, int year) {
		this.artist = artist;
		this.album = album;
		this.songName = songName;
		this.genre = genre;
		this.year = year;
		reviews = new ReviewsNode();
	}

	/**
	 * Definition: Adds reviews to the songs.
	 * Precondition: Variables must exist and addreview method must exist.
	 * Postcondition: Adds review to the song object.
	 * @param name
	 * @param rating
	 * @param review
	 */
	public void addReview(String name, int rating, String review) {
		this.reviews.addReview(name, rating, review);
	}

	/**
	 * Definition: toString method. Outputs the objects information.
	 * Precondition: Song object has to exist.
	 * Postcondition: Outputs song objects information.
	 */
	public String toString() {

		return artist + " - " + songName + "\n\n" + reviews;
	}
	/**
	 * Description: Main Method.
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
