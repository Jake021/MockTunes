package edu.century.finalproject.mocktunes;

public class ReviewsNode {
	private String name;
	private int rating;
	private String review;
	private String stars = "";
	private ReviewsNode next;
	private ReviewsNode head;

	/**
	 * Definition: Gets the name of the review.
	 * Precondition: Name varaible must exist.
	 * Postcondition: Gets the name of the reviewer.
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Definition: Sets the name of the review.
	 * Precondition: Name variable must exist.
	 * Postcondition: Sets the name of the reviewer.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Definition: Gets the rating of the review.
	 * Precondition: Rating variable has to exist.
	 * Postcondition: Gets the rating of the review.
	 * @return
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Definition: Sets the rating of the review.
	 * Precondition: Rating must exist.
	 * Postcondition: Sets the rating on the review.
	 * @param rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Definition: Gets the review.
	 * Precondition: Review object must exist.
	 * Postcondition: Gets the review.
	 * @return
	 */
	public String getReview() {
		return review;
	}

	/**
	 * Definition: Sets the review.
	 * Precondition: Review must exist.
	 * Postcondition: Sets the review to the object.
	 * @param review
	 */
	public void setReview(String review) {
		this.review = review;
	}

	/**
	 * Definition: Gets the next node in the linkedlist
	 * Precondition: Node must exist.
	 * Postcondition: Gets the next link.
	 * @return
	 */
	public ReviewsNode getNext() {
		return next;
	}

	/**
	 * Definition: Sets the next node in the linkedlist
	 * Precondition: Node must exist.
	 * Postcondition: Sets the next link in the next node.
	 * @param next
	 */
	public void setNext(ReviewsNode next) {
		this.next = next;
	}

	/**
	 * Definition: Gets the head of the node.
	 * Precondition: Node must exist.
	 * Postcondition: Gets the head of the next node.
	 * @return
	 */
	public ReviewsNode getHead() {
		return head;
	}

	public void setHead(ReviewsNode head) {
		this.head = head;
	}

	/**
	 * Definition: No arg Constructor.
	 * Precondition: Sets the variables to null
	 * Postcondition: Variables must exist.
	 */
	public ReviewsNode() {
		this.name = null;
		this.rating = 0;
		this.review = null;
		this.next = null;
		head = null;
	}

	/**
	 * Definition: Overloading the no args constuctor.
	 * Precondition: Variables must exist.
	 * Postcondition: Overloads the method.
	 * @param name
	 * @param rating
	 * @param review
	 */
	public ReviewsNode(String name, int rating, String review) {
		this.name = name;
		this.rating = rating;
		this.review = review;
		this.next = null;
		head = null;
	}

	/**
	 * Definition: Adds review to the next link
	 * Precondition: Node must exist.
	 * Postcondition: Adds review to the next link.
	 * @param name
	 * @param rating
	 * @param review
	 */
	public void addReview(String name, int rating, String review) {
		ReviewsNode temp = new ReviewsNode(name, rating, review);
		temp.next = head;
		head = temp;
	}

	/**
	 * Definition: Prints the stars for the reviews using recursion.
	 * Precondition: Reviews must exist.
	 * Postcondition: Adds stars to the review.
	 * @param n
	 * @return
	 */
	public String printStars(int n) {

		if (n == 1)
			return "*";
		else
			return "*" + printStars(n - 1);
	}

	/**
	 * Definition: toString method. Outputs information for the review.
	 * Precondition: linked list must exist.
	 * Postcondition: Outputs the review.
	 */
	public String toString() {
		String data = "";

		ReviewsNode cursor = new ReviewsNode();
		cursor = head;

		while (cursor != null) {

			int stars = cursor.getRating();

			data += "Name: " + cursor.getName() + "\n" + "Rating: " + printStars(cursor.getRating()) + "\n" + "Review: "
					+ cursor.getReview() + "\n" + "\n";

			cursor = cursor.next;
		}

		return data;
	}

	public static void main(String[] args) {

	}
}
