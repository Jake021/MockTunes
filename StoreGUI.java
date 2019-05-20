package edu.century.finalproject.mocktunes;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class StoreGUI extends JFrame implements ActionListener, FileWork {

	private JPanel contentPane;
	private static JTable table;
	JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					StoreGUI frame = new StoreGUI();
					frame.setVisible(true);

					// TODO: MAKE THIS INTO METHOD
					File file = new File("storedata.txt");

					//
					// System.out.println(columnsName[0]);

					// Java IO- How to import text to jtable video
					try {
						BufferedReader br = new BufferedReader(new FileReader(file));

						String[] columnsName = { "Artist", "Album", "Song", "Genre", "Year" };

						DefaultTableModel model = (DefaultTableModel) table.getModel();

						model.setColumnIdentifiers(columnsName);
						Object[] tlines = br.lines().toArray();

						for (int i = 0; i < tlines.length; i++) {
							String line = tlines[i].toString().trim();
							String[] dRow = line.split(",");
							model.addRow(dRow);
						}

						br.close();

					} catch (IOException e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * Definition: StoreGUI constructor
	 * Precondition: requires a storedata.txt file to get jtable info from
	 * Postcondition: loads GUI and jtable component with storedata.txt info
	 */
	public StoreGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Store");
		setBounds(100, 100, 1000, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 500, 308);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 490, 269);
		panel.add(scrollPane);

		table = new JTable();
		try {
			BufferedReader br = new BufferedReader(new FileReader("storedata.txt"));

			String[] columnsName = { "Artist", "Album", "Song", "Genre", "Year" };

			DefaultTableModel model = (DefaultTableModel) table.getModel();

			model.setColumnIdentifiers(columnsName);
			Object[] tlines = br.lines().toArray();

			for (int i = 0; i < tlines.length; i++) {
				String line = tlines[i].toString().trim();
				String[] dRow = line.split(",");
				model.addRow(dRow);
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();

				String artist = table.getModel().getValueAt(row, 0).toString();
				String album = table.getModel().getValueAt(row, 1).toString();
				String songname = table.getModel().getValueAt(row, 2).toString();
				String genre = table.getModel().getValueAt(row, 3).toString();
				String year = table.getModel().getValueAt(row, 4).toString();

				int yearInt = Integer.parseInt(year.trim());
				Song song = new Song(artist, album, songname, genre, yearInt);
				addRandomReviews(song);

				textArea.setText("REVIEWS: \n\n");
				textArea.append(song.reviews.toString());

				// TODO: when the user clicks on something - load in the linked list of nodes to
				// be displayed on output area
			}
			
			/**
			 * Definition: Uses Random to take data from 3 different arrays to construct a review for each song in jtable
			 * Precondition: requires an initialized Song.
			 * Postcondition: outputs a review to the StoreGUI textarea for each song that the user clicks on.
			 * @param song
			 */
			private void addRandomReviews(Song song) {
				int count = 0;
				while (count < 3) {
					String[] names = new String[] { "Marcus", "Tom", "Jake", "Martha", "Elise", "Lupita", "Earl",
							"Angela", "Jenny" };
					int[] ratings = new int[] { 5, 1, 2, 3, 5, 4 };
					String[] reviews = new String[] { "This is good", "This is bad", "This is okay",
							"What is going on?", "Needs more Spongebob", "First" };
					Random rand = new Random();
					int n = rand.nextInt(6);
					int m = rand.nextInt(9);

					song.addReview(names[m], ratings[n], reviews[n]);

					System.out.println(count);
					count++;
				}
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblSongsAvailable = new JLabel("Songs Available");
		lblSongsAvailable.setBounds(190, 11, 133, 14);
		panel.add(lblSongsAvailable);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(500, 0, 482, 308);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(12, 36, 458, 272);
		panel_1.add(textArea);

		JButton btnNewButton = new JButton("Add to library");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setIcon(new ImageIcon("icons/button_add-to-library.png"));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(322, 322, 165, 25);
		contentPane.add(btnNewButton);

		JButton btnReturn = new JButton("Return to Home");
		btnReturn.setIcon(new ImageIcon("icons/button_return-to-home.png"));
		btnReturn.setBackground(Color.LIGHT_GRAY);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				HomeGUI frame = new HomeGUI();
				frame.setVisible(true);
			}
		});
		btnReturn.setBounds(510, 322, 186, 25);
		contentPane.add(btnReturn);
	}
	
	/**
	 * Definition: actionPerformed for StoreGUI
	 * Precondition: 
	 * Postcondition: 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String callBtn = e.getActionCommand();

		try {
			if (callBtn.equalsIgnoreCase("Add to Library")) {
				int row = table.getSelectedRow();

				String artist = table.getModel().getValueAt(row, 0).toString();
				String album = table.getModel().getValueAt(row, 1).toString();
				String songname = table.getModel().getValueAt(row, 2).toString();
				String genre = table.getModel().getValueAt(row, 3).toString();
				String year = table.getModel().getValueAt(row, 4).toString();

				int yearInteger = Integer.parseInt(year.trim());

				// FileWork.readFile("songdata.txt");

				Song song = new Song(artist, album, songname, genre, yearInteger);

				Library.buyFromStore(song);
			}
		} catch (Exception E1) {
			System.out.println("Please select a song!");
		}

	}
}
