package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import control.Logic;
import control.LogicFactory;
import model.AdvancedSearch;
import model.Artist;
import model.Client;
import model.CloseTabButton;
import model.DateConverter;
import model.Genre;
import model.Purchase;
import model.Taste;
import model.Vinyl;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
public class UIClientMenu extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CloseTabButton tabbedPane;
	private JTable tableSuggestions;
	private JTable tableBestSellers;
	private JTable tableAdvancedSearch;
	private JTable tableBoughtVinyls;
	private JTable cartTable;
	private JButton buttonAdvancedSearch;
	private JButton buttonModInfo;
	private JButton buttonBoughtVinyls;
	private JButton buttonAddCart;
	private JTextField artistField;
	private JTextField albumTitleField;
	private JTextField genreField;
	private JTextField publicationDateField;
	private JTextField priceField;
	private JButton btnBuySelected;
	private JButton btnGoToCart;
	private JButton btnReturnToMenu;
	private JButton btnSearch;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField phoneNumberField;
	private JTextField emailField;
	private JTextField bankNumberField;
	private JTextField addressField;
	private JButton btnCancel;
	private JButton btnSubmit;
	private JPasswordField passwordField;
	private JButton btnGoBackToMenu;
	private JButton btnConfirm;
	private JTextField cartPriceField;
	private JTextField cartDateField;
	private JButton btnClearCart;
	private JScrollPane scrollPaneSuggestions;
	private JScrollPane scrollPaneBestSellers;
	private JScrollPane scrollPaneAdvancedSearch;
	private JScrollPane scrollPaneCartTable;
	private JButton btnFromTheBeginningOfTime;
	private JLabel lblChooseADate;
	private JButton btnSearchBestSeller;
	private Logic logic = LogicFactory.getLogic();
	private JDateChooser bestSellerCalendar = new JDateChooser();
	private JButton btnSeeCart;
	private Purchase cart = new Purchase();
	private Client client;
	private DefaultTableModel modelSuggestions;
	private DefaultTableModel modelBestSellers;
	private DefaultTableModel modelAdvancedSearch;
	private DefaultTableModel cartModel;
	private final String[] columnNames = {"Cover Art", "Album Title", "Artist", "Genre", "Price", "On sale:", "Sale percentage:"};
	private ArrayList<Vinyl> bestSellers;
	private ArrayList<Vinyl> suggestionsList;
	private ArrayList<Vinyl> searchResultList;
	private ArrayList<Vinyl> boughtVinylsList;
	private AdvancedSearch advancedSearch;
	private JList<String> listOfGenres;
	private JList<String> listOfArtists;
	private JPanel personalInfo;
	private ArrayList<Artist> artists;
	private ArrayList<Genre> genres;
	/**
	 * Create the frame.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UIClientMenu frame = new UIClientMenu(new Client());
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	public UIClientMenu(Client clientLogged) {
		setTitle("Client Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1132, 822);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tabbedPane = new CloseTabButton();
		getContentPane().add(tabbedPane);
		tabbedPane.setBounds(10, 10, 1100, 780);
		contentPane.add(tabbedPane);
		this.client = clientLogged;
		mainMenu();
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(buttonAdvancedSearch)) {
			advancedSearch();
		} else if (event.getSource().equals(buttonModInfo)) {
			modifyPersonalInfo();
		} else if (event.getSource().equals(buttonBoughtVinyls)) {
			boughtVinyls();
		} else if (event.getSource().equals(buttonAddCart)) {
			try {
				for (Integer i : tableBestSellers.getSelectedRows()) {
					cart.addVinyl(bestSellers.get(i));
				}
				if (tableSuggestions != null) {
					for (Integer i : tableSuggestions.getSelectedRows()) {
						cart.addVinyl(suggestionsList.get(i));
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (event.getSource().equals(btnSeeCart)) {
			seeCart();
		} else if (event.getSource().equals(btnSearchBestSeller)) {
			try {
				searchResultList = logic.getBestSellersDate(DateConverter.convertToLocalDateViaInstant(bestSellerCalendar.getDate()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			modelBestSellers = new DefaultTableModel(fillData(searchResultList), columnNames);
			tableBestSellers = new JTable(modelBestSellers);
			scrollPaneBestSellers.setViewportView(tableBestSellers);
		} else if (event.getSource().equals(btnFromTheBeginningOfTime)) {
			try {
				searchResultList = logic.getBestSellers();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			modelBestSellers = new DefaultTableModel(fillData(searchResultList), columnNames);
			tableBestSellers = new JTable(modelBestSellers);
			scrollPaneBestSellers.setViewportView(tableBestSellers);
		} else if (event.getSource().equals(btnConfirm)) {
			try {
				logic.writePurchase(cart);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (event.getSource().equals(btnClearCart)) {
			cart.getVinyls().clear();
		} else if (event.getSource().equals(btnSearch)) {
			try {
				advancedSearch = new AdvancedSearch();
				advancedSearch.setArtist(artistField.getText());
				advancedSearch.setGenre(genreField.getText());
				if (priceField.getText().isEmpty()) {
					advancedSearch.setPrice(0);
				}else {
					advancedSearch.setPrice(Double.valueOf(priceField.getText()));
				}
				if (publicationDateField.getText().isEmpty()) {
					advancedSearch.setPublicationYear(0);
				}else {
					advancedSearch.setPublicationYear(Integer.valueOf(publicationDateField.getText()));
				}
				advancedSearch.setTitle(albumTitleField.getText());
				advancedSearch.setStockLessThan(Integer.MAX_VALUE);
				searchResultList = logic.advancedSearch(advancedSearch);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			modelAdvancedSearch = new DefaultTableModel(fillData(searchResultList), columnNames);
			tableAdvancedSearch = new JTable(modelAdvancedSearch);
			scrollPaneAdvancedSearch.setViewportView(tableAdvancedSearch);
		} else if (event.getSource().equals(btnBuySelected)) {
			for (Integer i : tableAdvancedSearch.getSelectedRows()) {
				cart.addVinyl(searchResultList.get(i));
			}
		} else if (event.getSource().equals(btnSubmit)) {
			client.setAccountNumber(Long.valueOf(bankNumberField.getText()));
			client.setAddress(addressField.getText());
			client.setEmail(emailField.getText());
			client.setName(nameField.getText());
			client.setPassword(passwordField.getText());
			client.setPhoneNumber(Integer.valueOf(phoneNumberField.getText()));
			client.setSurname(surnameField.getText());
			Taste auxTaste = new Taste();
			for (Integer i : listOfArtists.getSelectedIndices()) {
				auxTaste.addArtist(artists.get(i));
			}
			for (Integer i : listOfGenres.getSelectedIndices()) {
				auxTaste.addGenre(genres.get(i));
			}
			client.setTastes(auxTaste);
			try {
				logic.modifyClientData(client);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (event.getSource().equals(btnCancel)) {
			personalInfo.repaint();
		} else if (event.getSource().equals(btnGoToCart)) {
			seeCart();
		}
	}
	/**
	 * private void fillBestSellerList(ArrayList<Vinyl> bestSellers)
	 * 
	 * @param bestSellers
	 *            Fills the list of best sellers based on the ArrayList of best
	 *            sold Vinyls given by the Data Base
	 */
	private Object[][] fillData(ArrayList<Vinyl> auxVinylList) {
		Object[][] data = new Object[auxVinylList.size()][7];
		for (int i = 0; i < auxVinylList.size(); i++) {
			data[i][0] = "Placeholder";
			data[i][1] = auxVinylList.get(i).getTitle();
			data[i][2] = auxVinylList.get(i).getArtist().getName();
			data[i][3] = auxVinylList.get(i).getGenre().getName();
			data[i][4] = auxVinylList.get(i).getPrice();
			data[i][5] = auxVinylList.get(i).isOnSale();
			if (auxVinylList.get(i).isOnSale()) {
				data[i][6] = ((auxVinylList.get(i).getSalePercentage())-1)*100;
			} else {
				data[i][6] = "Not on Sale";
			}
		}
		return data;
	}
	private void seeCart() {
		JPanel cartPanel = new JPanel();
		cartPanel.setBackground(new Color(255, 239, 213));
		cartPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		cartPanel.setLayout(null);
		tabbedPane.addTab("Cart", null, cartPanel, null);
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBackground(new Color(255, 250, 250));
		btnConfirm.setBounds(632, 557, 89, 23);
		cartPanel.add(btnConfirm);
		btnConfirm.addActionListener(this);
		btnClearCart = new JButton("Clear cart");
		btnClearCart.setBackground(new Color(255, 250, 250));
		btnClearCart.setBounds(467, 557, 111, 23);
		cartPanel.add(btnClearCart);
		btnClearCart.addActionListener(this);
		JLabel lblNewLabel = new JLabel("Vinyls in your cart:");
		lblNewLabel.setForeground(new Color(160, 82, 45));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(430, 24, 148, 28);
		cartPanel.add(lblNewLabel);
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(88, 536, 46, 14);
		cartPanel.add(lblPrice);
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(88, 561, 46, 14);
		cartPanel.add(lblDate);
		cartPriceField = new JTextField();
		cartPriceField.setEditable(false);
		cartPriceField.setBounds(144, 533, 86, 20);
		cartPanel.add(cartPriceField);
		Double fullPrice = 0.0;
		for (Vinyl vinyl : cart.getVinyls()) {
			fullPrice += vinyl.getPriceWithSale();
		}
		cart.setFullPrice(fullPrice);
		cartPriceField.setText(String.valueOf(cart.getFullPrice()));
		//cartPriceField.setColumns(10);
		cartDateField = new JTextField();
		cartDateField.setEditable(false);
		cartDateField.setBounds(144, 558, 86, 20);
		cartPanel.add(cartDateField);
		cartDateField.setColumns(10);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		cartDateField.setText(cart.getDATE().format(formatter));
		// TODO
		scrollPaneCartTable = new JScrollPane();
		scrollPaneCartTable.setBounds(48, 100, 864, 206);
		cartModel = new DefaultTableModel(fillData(cart.getVinyls()), columnNames);
		cartTable = new JTable(cartModel);
		scrollPaneCartTable.setViewportView(cartTable);
		cartPanel.add(scrollPaneCartTable);
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
		btnGoBackToMenu = new JButton("Volver al men\u00FA");
		btnGoBackToMenu.setBounds(451, 577, 134, 37);
		boughtVinyls.add(btnGoBackToMenu);
		try {
			boughtVinylsList = logic.getBoughtVinyls(client.getUsername());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 91, 969, 444);
		boughtVinyls.add(scrollPane);
		tableBoughtVinyls = new JTable(fillData(boughtVinylsList), columnNames);
		scrollPane.setViewportView(tableBoughtVinyls);
	}
	private void modifyPersonalInfo() {
		personalInfo = new JPanel();
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
		nameField.setText(client.getName());
		addressField = new JTextField();
		addressField.setBounds(189, 37, 133, 20);
		personalInfo.add(addressField);
		addressField.setColumns(10);
		addressField.setText(client.getAddress());
		surnameField = new JTextField();
		surnameField.setBounds(189, 79, 133, 20);
		personalInfo.add(surnameField);
		surnameField.setColumns(10);
		surnameField.setText(client.getSurname());
		phoneNumberField = new JTextField();
		phoneNumberField.setBounds(188, 122, 134, 20);
		personalInfo.add(phoneNumberField);
		phoneNumberField.setColumns(10);
		phoneNumberField.setText(String.valueOf(client.getPhoneNumber()));
		emailField = new JTextField();
		emailField.setBounds(188, 159, 134, 20);
		personalInfo.add(emailField);
		emailField.setColumns(10);
		emailField.setText(client.getEmail());
		bankNumberField = new JTextField();
		bankNumberField.setBounds(188, 198, 134, 20);
		personalInfo.add(bankNumberField);
		bankNumberField.setColumns(10);
		bankNumberField.setText(String.valueOf(client.getAccountNumber()));
		passwordField = new JPasswordField(client.getPassword());
		passwordField.setBounds(189, 240, 133, 20);
		personalInfo.add(passwordField);
		btnSubmit = new JButton("Save changes");
		btnSubmit.setBackground(new Color(255, 228, 196));
		btnSubmit.setBounds(28, 307, 115, 33);
		personalInfo.add(btnSubmit);
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 228, 196));
		btnCancel.setBounds(188, 307, 115, 33);
		personalInfo.add(btnCancel);
		btnSubmit.addActionListener(this);
		btnCancel.addActionListener(this);
		listOfGenres = new JList<String>();
		DefaultListModel<String> modelGenre = new DefaultListModel<String>();
		fillModelGenres(modelGenre);
		listOfGenres.setModel(modelGenre);
		listOfArtists = new JList<String>();
		DefaultListModel<String> modelArtists = new DefaultListModel<String>();
		fillModelArtists(modelArtists);
		listOfArtists.setModel(modelArtists);
	}
	private void advancedSearch() {
		JPanel advanced = new JPanel();
		advanced.setBackground(new Color(250, 235, 215));
		advanced.setBorder(new EmptyBorder(5, 5, 5, 5));
		advanced.setLayout(null);
		tabbedPane.addTab("Advanced Search", null, advanced, null);
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
		btnBuySelected.addActionListener(this);
		btnGoToCart = new JButton("Go to cart");
		btnGoToCart.setBackground(new Color(255, 218, 185));
		btnGoToCart.setBounds(332, 616, 123, 42);
		advanced.add(btnGoToCart);
		btnGoToCart.addActionListener(this);
//		btnReturnToMenu = new JButton("Return to menu");
//		btnReturnToMenu.setBackground(new Color(255, 218, 185));
//		btnReturnToMenu.setBounds(652, 616, 137, 42);
//		advanced.add(btnReturnToMenu);
		btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(255, 218, 185));
		btnSearch.setBounds(652, 616, 137, 42);
		advanced.add(btnSearch);
		btnSearch.addActionListener(this);
		scrollPaneAdvancedSearch = new JScrollPane();
		scrollPaneAdvancedSearch.setBounds(84, 240, 930, 344);
		advanced.add(scrollPaneAdvancedSearch);
	}
	private void mainMenu() {
		JPanel panelMainMenu = new JPanel();
		panelMainMenu.setLayout(null);
		panelMainMenu.setForeground(new Color(255, 240, 245));
		panelMainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelMainMenu.setBackground(new Color(250, 240, 230));
		tabbedPane.addTabNoExit("Main Menu", null, panelMainMenu, null);
		buttonAdvancedSearch = new JButton("Advanced search");
		buttonAdvancedSearch.setFont(new Font("Arial", Font.PLAIN, 13));
		buttonAdvancedSearch.setBackground(new Color(245, 222, 179));
		buttonAdvancedSearch.setBounds(62, 44, 161, 37);
		panelMainMenu.add(buttonAdvancedSearch);
		buttonAdvancedSearch.addActionListener(this);
		buttonModInfo = new JButton("Modify personal info");
		buttonModInfo.setFont(new Font("Arial", Font.PLAIN, 13));
		buttonModInfo.setBackground(new Color(245, 222, 179));
		buttonModInfo.setBounds(62, 106, 161, 37);
		panelMainMenu.add(buttonModInfo);
		buttonModInfo.addActionListener(this);
		buttonBoughtVinyls = new JButton("Bought vinyls");
		buttonBoughtVinyls.setFont(new Font("Arial", Font.PLAIN, 13));
		buttonBoughtVinyls.setBackground(new Color(245, 222, 179));
		buttonBoughtVinyls.setBounds(62, 164, 161, 37);
		panelMainMenu.add(buttonBoughtVinyls);
		buttonBoughtVinyls.addActionListener(this);
		JLabel label = new JLabel("Suggested vinyls for you:");
		label.setForeground(new Color(188, 143, 143));
		label.setFont(new Font("Arial", Font.BOLD, 13));
		label.setBounds(526, 17, 171, 27);
		panelMainMenu.add(label);
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(281, 23, 2, 258);
		panelMainMenu.add(separator);
		buttonAddCart = new JButton("Add to cart");
		buttonAddCart.setFont(new Font("Arial", Font.PLAIN, 14));
		buttonAddCart.setBackground(new Color(255, 218, 185));
		buttonAddCart.setBounds(386, 589, 161, 37);
		panelMainMenu.add(buttonAddCart);
		buttonAddCart.addActionListener(this);
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(38, 291, 228, 2);
		panelMainMenu.add(separator_1);
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(297, 291, 636, 2);
		panelMainMenu.add(separator_2);
		JLabel labelBestSellers = new JLabel("Best sellers:");
		labelBestSellers.setForeground(new Color(188, 143, 143));
		labelBestSellers.setFont(new Font("Arial", Font.BOLD, 15));
		labelBestSellers.setBounds(48, 304, 134, 27);
		panelMainMenu.add(labelBestSellers);
		scrollPaneSuggestions = new JScrollPane();
		scrollPaneSuggestions.setBounds(302, 55, 605, 193);
		panelMainMenu.add(scrollPaneSuggestions);
		try {
			//suggestionsList = logic.getSuggestions(client.getUsername());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		//modelSuggestions = new DefaultTableModel(fillData(suggestionsList), columnNames);
		//tableSuggestions = new JTable(modelSuggestions);
		//scrollPaneSuggestions.setViewportView(tableSuggestions);
		scrollPaneBestSellers = new JScrollPane();
		scrollPaneBestSellers.setBounds(48, 364, 864, 206);
		panelMainMenu.add(scrollPaneBestSellers);
		bestSellerCalendar.setBounds(151, 333, 242, 20);
		panelMainMenu.add(bestSellerCalendar);
		btnFromTheBeginningOfTime = new JButton("From the beginning of time");
		btnFromTheBeginningOfTime.setBounds(679, 330, 228, 23);
		btnFromTheBeginningOfTime.addActionListener(this);
		panelMainMenu.add(btnFromTheBeginningOfTime);
		lblChooseADate = new JLabel("Choose a Date:");
		lblChooseADate.setBounds(62, 333, 99, 14);
		panelMainMenu.add(lblChooseADate);
		btnSearchBestSeller = new JButton("Search");
		btnSearchBestSeller.setBounds(413, 330, 189, 23);
		panelMainMenu.add(btnSearchBestSeller);
		btnSeeCart = new JButton("See Cart");
		btnSeeCart.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSeeCart.setBackground(new Color(245, 222, 179));
		btnSeeCart.setBounds(62, 222, 161, 37);
		panelMainMenu.add(btnSeeCart);
		btnSeeCart.addActionListener(this);
		btnSearchBestSeller.addActionListener(this);
		try {
			bestSellers = logic.getBestSellers();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		modelBestSellers = new DefaultTableModel(fillData(bestSellers), columnNames);
		tableBestSellers = new JTable(modelBestSellers);
		scrollPaneBestSellers.setViewportView(tableBestSellers);
	}
	private void fillModelGenres(DefaultListModel<String> model) {
		try {
			artists = logic.getArtistsAllData();
			for (Artist art : artists) {
				model.addElement(art.getName());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void fillModelArtists(DefaultListModel<String> model) {
		try {
			genres = logic.getGenresAllData();
			for (Genre g : genres) {
				model.addElement(g.getName());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
