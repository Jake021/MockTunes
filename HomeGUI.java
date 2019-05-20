package edu.century.finalproject.mocktunes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Font;


@SuppressWarnings("serial")
public class HomeGUI extends JFrame implements FileWork, ActionListener {

	private JPanel contentPane;
	private static JTable libraryTable_1;
	public Library library;
	Thread t;
	SongThreadWork s;
	static HomeGUI frame;
	private JTextArea playlistTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new HomeGUI();
					frame.setVisible(true);

					// TODO: MAKE THIS INTO METHOD
					String filepath = "songdata.txt";
					File file = new File(filepath);

				} catch (Exception e) {
					System.out.println("File not found.");
				}
			}
		});

	}

	/**
	 * Definition: HomeGUI constructor
	 * Precondition: requires a songdata.txt file to read
	 * Postcondition: Loads the gui page/jtables with data from the songdata.txt file
	 */
	public HomeGUI() {
		// create new library
		library = new Library();
		System.out.println(library.songs);
		setTitle("Library");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1151, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel fullbackgroundpanel = new JPanel();
		fullbackgroundpanel.setBackground(Color.LIGHT_GRAY);
		fullbackgroundpanel.setForeground(Color.BLACK);
		fullbackgroundpanel.setBounds(0, 0, 1141, 448);
		contentPane.add(fullbackgroundpanel);
		fullbackgroundpanel.setLayout(null);

		JPanel tablecontainerpanel = new JPanel();
		tablecontainerpanel.setBackground(SystemColor.infoText);
		tablecontainerpanel.setBounds(44, 59, 468, 262);
		fullbackgroundpanel.add(tablecontainerpanel);
		tablecontainerpanel.setLayout(new BorderLayout(0, 0));

		libraryTable_1 = new JTable();
		try {
			BufferedReader br = new BufferedReader(new FileReader("songdata.txt"));

			String[] columnsName = { "Artist", "Album", "Song", "Genre", "Year" };

			DefaultTableModel model = (DefaultTableModel) libraryTable_1.getModel();
			model.setColumnIdentifiers(columnsName);

			Object[] tlines = br.lines().toArray();

			for (int i = 0; i < tlines.length; i++) {
				String line = tlines[i].toString().trim();
				String[] dRow = line.split(",");
				model.addRow(dRow);
			}

			br.close();

		} catch (IOException e) {
			System.out.println("songdata.txt not found.");
		}
		libraryTable_1.setDefaultEditor(Object.class, null);
		tablecontainerpanel.add(new JScrollPane(libraryTable_1), BorderLayout.CENTER);

		JButton btnStore = new JButton("Store");
		btnStore.setBackground(Color.LIGHT_GRAY);
		btnStore.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnStore.setIcon(new ImageIcon("icons/button_store.png"));
		btnStore.setBounds(522, 34, 76, 23);
		fullbackgroundpanel.add(btnStore);
		btnStore.addActionListener(this);

		JButton btnPlay = new JButton("Play");
		btnPlay.setBackground(Color.LIGHT_GRAY);
		btnPlay.setIcon(new ImageIcon("icons/button_play.png"));
		btnPlay.addActionListener(this);
		btnPlay.setBounds(155, 334, 89, 23);
		fullbackgroundpanel.add(btnPlay);

		JButton btnPause = new JButton("Pause");
		btnPause.setBackground(Color.LIGHT_GRAY);
		btnPause.setForeground(Color.LIGHT_GRAY);
		btnPause.setIcon(new ImageIcon("icons/button_remove.png"));
		btnPause.addActionListener(this);
		btnPause.setBounds(256, 334, 89, 23);
		fullbackgroundpanel.add(btnPause);

		JButton btnStop = new JButton("Stop");
		btnStop.setBackground(Color.LIGHT_GRAY);
		btnStop.setIcon(new ImageIcon("icons/button_stop.png"));
		btnStop.addActionListener(this);
		btnStop.setBounds(54, 334, 89, 23);
		fullbackgroundpanel.add(btnStop);

		JPanel playlistPanel = new JPanel();
		playlistPanel.setBorder(null);
		playlistPanel.setBounds(607, 59, 468, 267);
		fullbackgroundpanel.add(playlistPanel);
		playlistPanel.setLayout(new BorderLayout(0, 0));

		playlistTextArea = new JTextArea();
		playlistTextArea.setEditable(false);
		playlistTextArea.setBackground(SystemColor.control);
		playlistPanel.add(playlistTextArea, BorderLayout.CENTER);

		JButton btnPlayNextFrom = new JButton("Play next from playlist");
		btnPlayNextFrom.setBackground(Color.LIGHT_GRAY);
		btnPlayNextFrom.setIcon(new ImageIcon("icons/button_play-next-from-playlist.png"));
		btnPlayNextFrom.setBounds(722, 334, 240, 25);
		fullbackgroundpanel.add(btnPlayNextFrom);
		btnPlayNextFrom.addActionListener(this);

		JButton btnAddToPlaylist = new JButton("Add to playlist");
		btnAddToPlaylist.setIcon(new ImageIcon("icons/button_add-to-playlist.png"));
		btnAddToPlaylist.setForeground(Color.LIGHT_GRAY);
		btnAddToPlaylist.setBackground(Color.LIGHT_GRAY);
		btnAddToPlaylist.setBounds(357, 333, 155, 25);
		btnAddToPlaylist.addActionListener(this);
		fullbackgroundpanel.add(btnAddToPlaylist);
	}
	
	/**
	 * Definition: actionPerformed controlling the events for each button on the HomeGUI interface
	 * Precondition:
	 * Postcondition: 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String nameBtn = e.getActionCommand();

		if (nameBtn.equalsIgnoreCase("play")) {

			try {
				int column = 2;
				int row = libraryTable_1.getSelectedRow();
				String value = libraryTable_1.getModel().getValueAt(row, column).toString();

				s = new SongThreadWork(value.trim() + ".wav"); // start new thread
				t = new Thread(s);
				t.start(); // plays song via thread
			} catch (Exception e1) {
				System.out.println("Must select song to play");
			}

		} else if (nameBtn.equalsIgnoreCase("Stop")) {
			try {
				s.stopThread();
			} catch (Exception e2) {
				System.out.println("Must select song to play");
			}
		} else if (nameBtn.equalsIgnoreCase("Remove")) {
			try {

				int row = libraryTable_1.getSelectedRow();

				String artist = libraryTable_1.getModel().getValueAt(row, 0).toString();
				String album = libraryTable_1.getModel().getValueAt(row, 1).toString();
				String songname = libraryTable_1.getModel().getValueAt(row, 2).toString();
				String genre = libraryTable_1.getModel().getValueAt(row, 3).toString();
				String year = libraryTable_1.getModel().getValueAt(row, 4).toString();

				int yearInteger = Integer.parseInt(year.trim());

				Song song = new Song(artist, album, songname, genre, yearInteger);
				Library.removeFromLibrary(song);
			} catch (Exception e8) {
				System.out.println("Please select a song to remove.");
			}

		} else if (nameBtn.equalsIgnoreCase("Add to playlist")) {

			try {
				int row = libraryTable_1.getSelectedRow();

				String artist = libraryTable_1.getModel().getValueAt(row, 0).toString();
				String album = libraryTable_1.getModel().getValueAt(row, 1).toString();
				String song = libraryTable_1.getModel().getValueAt(row, 2).toString();
				String genre = libraryTable_1.getModel().getValueAt(row, 3).toString();
				String year = libraryTable_1.getModel().getValueAt(row, 4).toString();

				int yearInteger = Integer.parseInt(year.trim());

				Song newsong = new Song(artist, album, song, genre, yearInteger);
				library.addToPlaylist(newsong);
				library.playlist.toString();

				playlistTextArea.setText("");
				playlistTextArea.append("UP NEXT IN PLAYLIST: \n");
				playlistTextArea.append(library.playlist.toString());
			} catch (Exception e3) {
				System.out.println("Must select song first!");

			}

		} else if (nameBtn.equalsIgnoreCase("Store")) {
			dispose();

			StoreGUI secondFrame = new StoreGUI();

			secondFrame.setLocationRelativeTo(null);
			secondFrame.setVisible(true);
		} else if (nameBtn == "Play next from playlist") {
			System.out.println(s);
			if (s != null) {
				if (s.running) {
					s.stopThread();// kill current thread
				}
			}
			String songname = library.playNextSongInPlaylist(); // getting next song from the playlist
			System.out.println(songname);
			s = new SongThreadWork(songname); // starting new thread
			t = new Thread(s);
			t.start();

			// update the playlist text area
			playlistTextArea.setText("");
			playlistTextArea.append("UP NEXT IN PLAYLIST: \n");
			playlistTextArea.append(library.playlist.toString());
			System.out.println(library.playlist);
		}
	}

}
