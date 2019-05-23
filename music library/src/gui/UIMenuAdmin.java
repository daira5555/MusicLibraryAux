package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class UIMenuAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField artistField;
	private JTextField albumTitleField;
	private JTextField genreField;
	private JTextField publicationDateField;
	private JTextField priceField;
	private JButton btnDelete;
	private JButton btnModify;
	@SuppressWarnings("unused")
	private JTable table;
	private JTextField stockField;
	private JButton btnOrderVinyl;
	private JButton btnNewVinyl;
	private JRadioButton rdbtnThisWeek;
	private JRadioButton rdbtnThisMonth;
	private JRadioButton rdbtnThisYear;
	private JRadioButton rdbtnFromTheBegining;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIMenuAdmin frame = new UIMenuAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UIMenuAdmin() {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1149, 707);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblByArtist = new JLabel("By artist:");
		lblByArtist.setBounds(41, 42, 75, 14);
		contentPane.add(lblByArtist);

		JLabel lblByAlbumTitle = new JLabel("By album title:");
		lblByAlbumTitle.setBounds(41, 67, 107, 14);
		contentPane.add(lblByAlbumTitle);

		JLabel lblByGenre = new JLabel("By genre:");
		lblByGenre.setBounds(415, 42, 58, 14);
		contentPane.add(lblByGenre);

		JLabel lblByPublicationDate = new JLabel("By publication date:");
		lblByPublicationDate.setBounds(415, 67, 116, 14);
		contentPane.add(lblByPublicationDate);

		JLabel lblByPrice = new JLabel("By price: ");
		lblByPrice.setBounds(679, 42, 58, 14);
		contentPane.add(lblByPrice);

		artistField = new JTextField();
		artistField.setBounds(101, 39, 309, 20);
		contentPane.add(artistField);
		artistField.setColumns(10);

		albumTitleField = new JTextField();
		albumTitleField.setBounds(133, 64, 277, 20);
		contentPane.add(albumTitleField);
		albumTitleField.setColumns(10);

		genreField = new JTextField();
		genreField.setBounds(481, 39, 186, 20);
		contentPane.add(genreField);
		genreField.setColumns(10);

		publicationDateField = new JTextField();
		publicationDateField.setBounds(531, 64, 136, 20);
		contentPane.add(publicationDateField);
		publicationDateField.setColumns(10);

		priceField = new JTextField();
		priceField.setBounds(747, 39, 102, 20);
		contentPane.add(priceField);
		priceField.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(41, 320, 1056, 2);
		contentPane.add(separator);

		btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(255, 218, 185));
		btnDelete.setBounds(981, 591, 116, 42);
		contentPane.add(btnDelete);

		btnModify = new JButton("Modify");
		btnModify.setBackground(new Color(255, 218, 185));
		btnModify.setBounds(663, 591, 123, 42);
		contentPane.add(btnModify);
		String[] columnNames = { "Album Title", "Arrtist", "Genre", "Price", "On sale:", "Sale percentage:" };

		Object[][] data = {
				{ "The Dark Side of the Moon'", "Pink Floyd", "Rock psicodélico", new Integer(20), new Boolean(false), new Integer(20) },
				{ "London Calling", "The Clash", "New wave", new Integer(22), new Boolean(true),new Integer(20) },
				{ "Shilling the Rubes", "David Bowie", "New wave", new Integer(21), new Boolean(false),new Integer(20) },
				{ "Back in Black", "AC/DC", "Hard rock", new Integer(20), new Boolean(true),new Integer(20) },
				{ "Nevermind", "Nirvana", "Grunge", new Integer(21), new Boolean(false),new Integer(20) } };

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 92, 1056, 202);
		contentPane.add(scrollPane);

		JTable table_1 = new JTable(data, columnNames);
		scrollPane.setViewportView(table_1);

		JLabel lblStockLessThan = new JLabel("Stock less than:");
		lblStockLessThan.setBounds(679, 67, 107, 14);
		contentPane.add(lblStockLessThan);

		stockField = new JTextField();
		stockField.setBounds(775, 64, 74, 20);
		contentPane.add(stockField);
		stockField.setColumns(10);

		btnOrderVinyl = new JButton("Order vinyl");
		btnOrderVinyl.setBackground(new Color(255, 218, 185));
		btnOrderVinyl.setBounds(336, 591, 137, 42);
		contentPane.add(btnOrderVinyl);

		btnNewVinyl = new JButton("New Vinyl");
		btnNewVinyl.setBackground(new Color(255, 218, 185));
		btnNewVinyl.setBounds(41, 591, 116, 42);
		contentPane.add(btnNewVinyl);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(222, 184, 135));
		btnSearch.setBounds(859, 38, 238, 42);
		contentPane.add(btnSearch);

		JLabel lblBestSellers = new JLabel("Best sellers:");
		lblBestSellers.setForeground(new Color(188, 143, 143));
		lblBestSellers.setFont(new Font("Arial", Font.BOLD, 15));
		lblBestSellers.setBounds(62, 330, 134, 27);
		contentPane.add(lblBestSellers);

		rdbtnThisWeek = new JRadioButton("This week");
		rdbtnThisWeek.setBackground(new Color(250, 235, 215));
		rdbtnThisWeek.setBounds(41, 364, 109, 23);
		contentPane.add(rdbtnThisWeek);

		rdbtnThisMonth = new JRadioButton("This month");
		rdbtnThisMonth.setBackground(new Color(250, 235, 215));
		rdbtnThisMonth.setBounds(41, 420, 109, 23);
		contentPane.add(rdbtnThisMonth);

		rdbtnThisYear = new JRadioButton("This year");
		rdbtnThisYear.setBackground(new Color(250, 235, 215));
		rdbtnThisYear.setBounds(41, 477, 109, 23);
		contentPane.add(rdbtnThisYear);

		rdbtnFromTheBegining = new JRadioButton("From the begining of the times");
		rdbtnFromTheBegining.setBackground(new Color(250, 235, 215));
		rdbtnFromTheBegining.setBounds(41, 534, 221, 23);
		contentPane.add(rdbtnFromTheBegining);

		String[] columnNames1 = { "Album Title", "Arrtist", "Genre", "Price", "On sale:", "Sale percentage:" };

		Object[][] data1 = {
				{ "The Dark Side of the Moon'", "Pink Floyd", "Rock psicodélico", new Integer(20), new Boolean(false), new Integer(20) },
				{ "London Calling", "The Clash", "New wave", new Integer(22), new Boolean(true),new Integer(20) },
				{ "Shilling the Rubes", "David Bowie", "New wave", new Integer(21), new Boolean(false),new Integer(20) },
				{ "Back in Black", "AC/DC", "Hard rock", new Integer(20), new Boolean(true),new Integer(20) },
				{ "Nevermind", "Nirvana", "Grunge", new Integer(21), new Boolean(false),new Integer(20) } };

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(289, 354, 808, 226);
		contentPane.add(scrollPane_1);

		table_1 = new JTable(data1, columnNames1);
		scrollPane_1.setViewportView(table_1);
	}
}
