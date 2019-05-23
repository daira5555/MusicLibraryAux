package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.CloseTabButton;

import javax.swing.JTabbedPane;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class UIClientMenu extends JFrame implements ActionListener{

	private JPanel contentPane;
	private CloseTabButton tabbedPane;
	private JTable table;
	private JTable table_1;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JTextField artistField;
	private JTextField albumTitleField;
	private JTextField genreField;
	private JTextField publicationDateField;
	private JTextField priceField;
	private JButton btnBuySelected;
	private JButton btnGoToCart;
	private JButton btnReturnToMenu;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField numberField;
	private JTextField emailField;
	private JTextField bankNumberField;
	private JButton btnCancel;
	private JButton btnSubmit;
	private JPasswordField passwordField;
	private JButton btnVolverAlMen;
	private JButton btnConfirm;
	
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnClearCart;
	// algo2
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIClientMenu frame = new UIClientMenu();
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
	public UIClientMenu() {
		setTitle("Client Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1132, 822);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new CloseTabButton();
		getContentPane().add(tabbedPane);
		tabbedPane.setBounds(10, 11, 1096, 762);
		contentPane.add(tabbedPane);
		
		mainMenu();
		
		
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(button)) {
			advancedSearch();
		}else if(e.getSource().equals(button_1)) {
			modifyPersonalInfo();
		}else if (e.getSource().equals(button_2)) {
			boughtVinyls();
		}else if(e.getSource().equals(button_3)) {
			seeCart();
		}
		
	}

	private void seeCart() {
		JPanel cart = new JPanel();
		cart.setBackground(new Color(255, 239, 213));
		cart.setBorder(new EmptyBorder(5, 5, 5, 5));
		cart.setLayout(null);
		tabbedPane.addTab("Cart", null, cart, null);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBackground(new Color(255, 250, 250));
		btnConfirm.setBounds(632, 557, 89, 23);
		cart.add(btnConfirm);
		
		btnReturnToMenu = new JButton("Return to menu");
		btnReturnToMenu.setBackground(new Color(255, 250, 250));
		btnReturnToMenu.setBounds(858, 557, 129, 23);
		cart.add(btnReturnToMenu);
		
		btnClearCart = new JButton("Clear cart");
		btnClearCart.setBackground(new Color(255, 250, 250));
		btnClearCart.setBounds(467, 557, 111, 23);
		cart.add(btnClearCart);
		
		JLabel lblNewLabel = new JLabel("Vinyls in your cart:");
		lblNewLabel.setForeground(new Color(160, 82, 45));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(430, 24, 148, 28);
		cart.add(lblNewLabel);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(88, 536, 46, 14);
		cart.add(lblPrice);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(88, 561, 46, 14);
		cart.add(lblDate);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(144, 533, 86, 20);
		cart.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(144, 558, 86, 20);
		cart.add(textField_1);
		textField_1.setColumns(10);
		
		
		String[] columnNames = {"Album Title", "Arrtist", "Genre", "Price", "On sale:", "Sale percentage:" };
		
		Object[][] data = {
				{ "The Dark Side of the Moon'", "Pink Floyd", "Rock psicodélico", new Integer(20), new Boolean(false), new Integer(20) },
				{ "London Calling", "The Clash", "New wave", new Integer(22), new Boolean(true),new Integer(20) },
				{ "Shilling the Rubes", "David Bowie", "New wave", new Integer(21), new Boolean(false),new Integer(20) },
				{ "Back in Black", "AC/DC", "Hard rock", new Integer(20), new Boolean(true),new Integer(20) },
				{ "Nevermind", "Nirvana", "Grunge", new Integer(21), new Boolean(false),new Integer(20) } };


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 78, 960, 427);
		cart.add(scrollPane);

		JTable table_1 = new JTable(data, columnNames);
		scrollPane.setViewportView(table_1);
		
	}

	private void boughtVinyls() {
		JPanel boughtVinyls = new JPanel();
		boughtVinyls.setBackground(new Color(250, 235, 215));
		boughtVinyls.setBorder(new EmptyBorder(5, 5, 5, 5));
		boughtVinyls.setLayout(null);
		tabbedPane.addTab("Bought Vinyls", null, boughtVinyls, null);
		
		JLabel lblBoughtVinyls = new JLabel("Bought vinyls:");
		lblBoughtVinyls.setForeground(new Color(160, 82, 45));
		lblBoughtVinyls.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBoughtVinyls.setBounds(451, 40, 134, 26);
		boughtVinyls.add(lblBoughtVinyls);
		
		btnVolverAlMen = new JButton("Volver al men\u00FA");
		btnVolverAlMen.setBounds(451, 577, 134, 37);
		boughtVinyls.add(btnVolverAlMen);
		
		String[] columnNames = { "Album Title", "Arrtist", "Genre", "Price", "On sale:" };

		Object[][] data = {
				{ "The Dark Side of the Moon'", "Pink Floyd", "Rock psicodélico", new Integer(5), new Boolean(false) },
				{ "London Calling", "The Clash", "New wave", new Integer(3), new Boolean(true) },
				{ "Shilling the Rubes", "David Bowie", "New wave", new Integer(2), new Boolean(false) },
				{ "Back in Black", "AC/DC", "Hard rock", new Integer(20), new Boolean(true) },
				{ "Nevermind", "Nirvana", "Grunge", new Integer(10), new Boolean(false) } };

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 91, 969, 444);
		boughtVinyls.add(scrollPane);

		JTable table_1 = new JTable(data, columnNames);
		scrollPane.setViewportView(table_1);
		
	}

	private void modifyPersonalInfo() {
		JPanel personalInfo = new JPanel();
		personalInfo.setBackground(new Color(250, 235, 215));
		personalInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
		personalInfo.setLayout(null);
		tabbedPane.addTab("Modify Personal Info", null, personalInfo, null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(20, 40, 46, 14);
		personalInfo.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(20, 82, 104, 14);
		personalInfo.add(lblSurname);
		
		JLabel lblPhoneNumber = new JLabel("Phone number:");
		lblPhoneNumber.setBounds(20, 125, 104, 14);
		personalInfo.add(lblPhoneNumber);
		
		JLabel lblEmail = new JLabel("e-mail:");
		lblEmail.setBounds(20, 162, 65, 14);
		personalInfo.add(lblEmail);
		
		JLabel lblNewBankAccount = new JLabel("New bank account number:");
		lblNewBankAccount.setBounds(20, 201, 199, 14);
		personalInfo.add(lblNewBankAccount);
		
		JLabel lblInsertNewPassword = new JLabel("Insert new password:");
		lblInsertNewPassword.setBounds(20, 243, 153, 14);
		personalInfo.add(lblInsertNewPassword);
		
		nameField = new JTextField();
		nameField.setBounds(189, 37, 133, 20);
		personalInfo.add(nameField);
		nameField.setColumns(10);
		
		surnameField = new JTextField();
		surnameField.setBounds(189, 79, 133, 20);
		personalInfo.add(surnameField);
		surnameField.setColumns(10);
		
		numberField = new JTextField();
		numberField.setBounds(188, 122, 134, 20);
		personalInfo.add(numberField);
		numberField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(188, 159, 134, 20);
		personalInfo.add(emailField);
		emailField.setColumns(10);
		
		bankNumberField = new JTextField();
		bankNumberField.setBounds(188, 198, 134, 20);
		personalInfo.add(bankNumberField);
		bankNumberField.setColumns(10);
		
		btnSubmit = new JButton("Save changes");
		btnSubmit.setBackground(new Color(255, 228, 196));
		btnSubmit.setBounds(28, 307, 115, 33);
		personalInfo.add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 228, 196));
		btnCancel.setBounds(188, 307, 115, 33);
		personalInfo.add(btnCancel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(189, 240, 133, 20);
		personalInfo.add(passwordField);
		
	}

	private void advancedSearch() {		
		
		JPanel advanced = new JPanel();
		advanced.setBackground(new Color(250, 235, 215));
		advanced.setBorder(new EmptyBorder(5, 5, 5, 5));
		advanced.setLayout(null);
		tabbedPane.addTab("Advanced Search", null,advanced,null);
		
		
		JLabel lblByArtist = new JLabel("By artist:");
		lblByArtist.setBounds(84, 45, 75, 14);
		advanced.add(lblByArtist);
		
		JLabel lblByAlbumTitle = new JLabel("By album title:");
		lblByAlbumTitle.setBounds(84, 87, 147, 14);
		advanced.add(lblByAlbumTitle);
		
		JLabel lblByGenre = new JLabel("By genre:");
		lblByGenre.setBounds(781, 45, 58, 14);
		advanced.add(lblByGenre);
		
		JLabel lblByPublicationDate = new JLabel("By publication date:");
		lblByPublicationDate.setBounds(781, 87, 116, 14);
		advanced.add(lblByPublicationDate);
		
		JLabel lblByPrice = new JLabel("By price: ");
		lblByPrice.setBounds(781, 130, 58, 14);
		advanced.add(lblByPrice);
		
		artistField = new JTextField();
		artistField.setBounds(179, 42, 107, 20);
		advanced.add(artistField);
		artistField.setColumns(10);
		
		albumTitleField = new JTextField();
		albumTitleField.setBounds(179, 84, 107, 20);
		advanced.add(albumTitleField);
		albumTitleField.setColumns(10);
		
		genreField = new JTextField();
		genreField.setBounds(907, 42, 107, 20);
		advanced.add(genreField);
		genreField.setColumns(10);
		
		publicationDateField = new JTextField();
		publicationDateField.setBounds(907, 84, 107, 20);
		advanced.add(publicationDateField);
		publicationDateField.setColumns(10);
		
		priceField = new JTextField();
		priceField.setBounds(907, 127, 107, 20);
		advanced.add(priceField);
		priceField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(29, 194, 997, 2);
		advanced.add(separator);
		
		btnBuySelected = new JButton("Add to cart");
		btnBuySelected.setBackground(new Color(255, 218, 185));
		btnBuySelected.setBounds(493, 616, 116, 42);
		advanced.add(btnBuySelected);
		
		btnGoToCart = new JButton("Go to cart");
		btnGoToCart.setBackground(new Color(255, 218, 185));
		btnGoToCart.setBounds(332, 616, 123, 42);
		advanced.add(btnGoToCart);
		
		btnReturnToMenu = new JButton("Return to menu");
		btnReturnToMenu.setBackground(new Color(255, 218, 185));
		btnReturnToMenu.setBounds(652, 616, 137, 42);
		advanced.add(btnReturnToMenu);
		
		String[] columnNames = { "Album Title", "Arrtist", "Genre", "Price", "On sale:", "Sale percentage:" };

		Object[][] data = {
				{ "The Dark Side of the Moon'", "Pink Floyd", "Rock psicodélico", new Integer(20), new Boolean(false), new Integer(20) },
				{ "London Calling", "The Clash", "New wave", new Integer(22), new Boolean(true),new Integer(20) },
				{ "Shilling the Rubes", "David Bowie", "New wave", new Integer(21), new Boolean(false),new Integer(20) },
				{ "Back in Black", "AC/DC", "Hard rock", new Integer(20), new Boolean(true),new Integer(20) },
				{ "Nevermind", "Nirvana", "Grunge", new Integer(21), new Boolean(false),new Integer(20) } };
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 240, 930, 344);
		advanced.add(scrollPane);
		
		JTable table_1 = new JTable(data,columnNames);
		scrollPane.setViewportView(table_1);
	}
	
	private void mainMenu() {
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(255, 240, 245));
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_1.setBackground(new Color(250, 240, 230));
		tabbedPane.addTabNoExit("Main Menu", null, panel_1, null);
		
		button = new JButton("Advanced search");
		button.setFont(new Font("Arial", Font.PLAIN, 13));
		button.setBackground(new Color(245, 222, 179));
		button.setBounds(62, 60, 161, 37);
		panel_1.add(button);
		button.addActionListener(this);
		
		button_1 = new JButton("Modify personal info");
		button_1.setFont(new Font("Arial", Font.PLAIN, 13));
		button_1.setBackground(new Color(245, 222, 179));
		button_1.setBounds(62, 127, 161, 37);
		panel_1.add(button_1);
		button_1.addActionListener(this);
		
		button_2 = new JButton("Bought vinyls");
		button_2.setFont(new Font("Arial", Font.PLAIN, 13));
		button_2.setBackground(new Color(245, 222, 179));
		button_2.setBounds(62, 196, 161, 37);
		panel_1.add(button_2);
		button_2.addActionListener(this);
		
		JLabel label = new JLabel("Suggested vinyls for you:");
		label.setForeground(new Color(188, 143, 143));
		label.setFont(new Font("Arial", Font.BOLD, 13));
		label.setBounds(556, 11, 171, 27);
		panel_1.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(281, 11, 2, 270);
		panel_1.add(separator);
		
		button_3 = new JButton("Add to cart");
		button_3.setFont(new Font("Arial", Font.PLAIN, 14));
		button_3.setBackground(new Color(255, 218, 185));
		button_3.setBounds(526, 594, 161, 37);
		panel_1.add(button_3);
		button_3.addActionListener(this);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(38, 291, 228, 2);
		panel_1.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(297, 291, 636, 2);
		panel_1.add(separator_2);
		
		JLabel label_1 = new JLabel("Best sellers:");
		label_1.setForeground(new Color(188, 143, 143));
		label_1.setFont(new Font("Arial", Font.BOLD, 15));
		label_1.setBounds(423, 304, 134, 27);
		panel_1.add(label_1);
		
		JRadioButton radioButton = new JRadioButton("This week");
		radioButton.setBackground(new Color(250, 240, 230));
		radioButton.setBounds(62, 364, 109, 23);
		panel_1.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("This month");
		radioButton_1.setBackground(new Color(250, 240, 230));
		radioButton_1.setBounds(62, 406, 109, 23);
		panel_1.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("This year");
		radioButton_2.setBackground(new Color(250, 240, 230));
		radioButton_2.setBounds(62, 447, 109, 23);
		panel_1.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("From the begining of the times");
		radioButton_3.setBackground(new Color(250, 240, 230));
		radioButton_3.setBounds(62, 488, 221, 23);
		panel_1.add(radioButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(302, 55, 605, 193);
		panel_1.add(scrollPane);

		
		String[] columnNames = { "Album Title", "Arrtist", "Genre", "Price", "On sale:", "Sale percentage:" };

		Object[][] data = {
				{ "The Dark Side of the Moon'", "Pink Floyd", "Rock psicodélico", new Integer(20), new Boolean(false), new Integer(20) },
				{ "London Calling", "The Clash", "New wave", new Integer(22), new Boolean(true),new Integer(20) },
				{ "Shilling the Rubes", "David Bowie", "New wave", new Integer(21), new Boolean(false),new Integer(20) },
				{ "Back in Black", "AC/DC", "Hard rock", new Integer(20), new Boolean(true),new Integer(20) },
				{ "Nevermind", "Nirvana", "Grunge", new Integer(21), new Boolean(false),new Integer(20) } };
		DefaultTableModel model = new DefaultTableModel(data,columnNames);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(302, 364, 610, 206);
		panel_1.add(scrollPane_1);
		
		String[] columnNames1 = { "Album Title", "Arrtist", "Genre", "Price", "On sale:", "Sale percentage:" };

		Object[][] data1 = {
				{ "The Dark Side of the Moon'", "Pink Floyd", "Rock psicodélico", new Integer(20), new Boolean(false), new Integer(20) },
				{ "London Calling", "The Clash", "New wave", new Integer(22), new Boolean(true),new Integer(20) },
				{ "Shilling the Rubes", "David Bowie", "New wave", new Integer(21), new Boolean(false),new Integer(20) },
				{ "Back in Black", "AC/DC", "Hard rock", new Integer(20), new Boolean(true),new Integer(20) },
				{ "Nevermind", "Nirvana", "Grunge", new Integer(21), new Boolean(false),new Integer(20) }};
		
		table_1 = new JTable(data1,columnNames1);
		scrollPane_1.setViewportView(table_1);
	}
}
