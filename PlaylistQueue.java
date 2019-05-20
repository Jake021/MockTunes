package edu.century.finalproject.mocktunes;

import java.util.NoSuchElementException;

public class PlaylistQueue {
	private PlayListNode front;
	private PlayListNode rear;
	private int playlistLength;

	/**
	 * Definition: Gets the length of the queue
	 * Precondition: Queue must exist.
	 * Postcondition: Gets the length of the play list queue
	 * @return
	 */
	public int getLength() {
		return playlistLength;
	}

	/**
	 * Definition: Checks to see if the queue is empty.
	 * Precondition: play list queue must exist.
	 * Postcondition: Checks to see if the queue is empty.
	 * @return
	 */
	public boolean isEmpty() {
		return playlistLength == 0;
	}

	/** 
	 * Definition: Adds song to the queue.
	 * Precondition: Queue object must exist.
	 * Postcondition: Adds song to the queue
	 * @param song
	 */
	public void enqueue(Song song) {
		PlayListNode temp = new PlayListNode(song);
		if (isEmpty())
			front = temp;
		else
			rear.next = temp;

		rear = temp;
		playlistLength++;

	}

	/**
	 * Definition: Removes the song from the play list queue
	 * Precondition: Song must be in the queue to be removed.
	 * Postcondition: Removes the selected song from the queue.
	 * @return
	 */
	public String dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("There are no songs in the playlist");

		Song song = front.getSong();

		if (front == null)
			rear = null;
		else if (front.getNext() == null) {
			front = null;
			rear = null;
		} else {
			front = front.getNext();
		}

		playlistLength--;
		return song.getSongName();

	}

	/**
	 * Definition: Outputs the information to the JtextField.
	 * Precondition: JtextField must exist and song in queue must exist.
	 * Postcondition: Outsputs the song information to the JTextField.
	 */
	public String toString() {
		String data = "";
		PlayListNode cursor = new PlayListNode();
		cursor = front;
		if (cursor == null)
			data = "There are no songs in playlist";
		while (cursor != null) {
			data += "Song Name: " + cursor.getSong().getSongName() + "\n";

			cursor = cursor.getNext();
		}

		return data;
	}


	class PlayListNode {

		private Song song;
		PlayListNode next;

		/**
		 * Definition: Play list linked list
		 * Precondition: Sets the variables to null.
		 * Postcondition: Sets the variables to null.
		 * 
		 */
		public PlayListNode() {
			this.song = null;
			this.next = null;
		}

		/**
		 * Definition: Overloads the no arg constructor.
	     * Precondition: Song object must exist.
	     * Postcondition: Overloads the no arg constructor.
		 * @param song
		 */
		public PlayListNode(Song song) {
			this.song = song;
			this.next = null;
		}

		/**
		 * Definition: Gets the song in the linked list
		 * Precondition: Song must exist in the linked list.
		 * Postcondition: Gets the next song in the linked list.
		 * @return
		 */
		public Song getSong() {
			return this.song;
		}

		/**
		 * Definition: Gets the next head in the linked list.
		 * Precondition: Linked list must exist.
		 * Postcondition: Gets the next head in the link list.
		 * @return
		 */
		public PlayListNode getNext() {
			return this.next;
		}

	}
}
