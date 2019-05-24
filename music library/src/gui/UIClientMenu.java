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
import model.Client;
import model.CloseTabButton;
import model.Purchase;
import model.Vinyl;

import java.awt.Color;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JLabel;
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
	private JTextField numberField;
	private JTextField emailField;
	private JTextField bankNumberField;
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
	/**
	 * Create the frame.
	 */
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
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonAdvancedSearch)) {
			advancedSearch();
		} else if (e.getSource().equals(buttonModInfo)) {
			modifyPersonalInfo();
		} else if (e.getSource().equals(buttonBoughtVinyls)) {
			boughtVinyls();
		} else if (e.getSource().equals(buttonAddCart)) {
			try {
				for (Integer i : tableBestSellers.getSelectedRows()) {
					cart.addVinyl(bestSellers.get(i));
				}
				for (Integer i : tableSuggestions.getSelectedRows()) {
					cart.addVinyl(suggestionsList.get(i));
				}
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		} else if (e.getSource().equals(btnSeeCart)) {
			seeCart();
		} else if (e.getSource().equals(btnSearchBestSeller)) {
			try {
				searchResultList = logic.getBestSellersDate(bestSellerCalendar.getDate());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			modelBestSellers = new DefaultTableModel(fillData(searchResultList), columnNames);
			tableBestSellers = new JTable(modelBestSellers);
			scrollPaneBestSellers.setViewportView(tableBestSellers);
		} else if (e.getSource().equals(btnFromTheBeginningOfTime)) {
			try {
				searchResultList = logic.getBestSellers();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			modelBestSellers = new DefaultTableModel(fillData(searchResultList), columnNames);
			tableBestSellers = new JTable(modelBestSellers);
			scrollPaneBestSellers.setViewportView(tableBestSellers);
		} else if (e.getSource().equals(btnConfirm)) {
			try {
				logic.writePurchase(cart);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource().equals(btnClearCart)) {
			cart.getVinyls().clear();
		} else if (e.getSource().equals(btnSearch)) {
			advancedSearch = new AdvancedSearch();
			advancedSearch.setArtist(artistField.getText());
			advancedSearch.setGenre(genreField.getText());
			advancedSearch.setPrice(Double.valueOf(priceField.getText()));
			advancedSearch.setPublicationYear(Integer.valueOf(publicationDateField.getText()));
			advancedSearch.setTitle(albumTitleField.getText());
			advancedSearch.setStockLessThan(Integer.MAX_VALUE);
			try {
				searchResultList = logic.advancedSearch(advancedSearch);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			modelAdvancedSearch = new DefaultTableModel(fillData(searchResultList), columnNames);
			tableAdvancedSearch = new JTable(modelAdvancedSearch);
			scrollPaneAdvancedSearch.setViewportView(tableAdvancedSearch);
		} else if (e.getSource().equals(btnBuySelected)) {
			for (Integer i : tableAdvancedSearch.getSelectedRows()) {
				cart.addVinyl(searchResultList.get(i));
			}
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
		Object[][] data = new Object[7][auxVinylList.size()];
		for (int i = 0; i < auxVinylList.size(); i++) {
			data[0][i] = "Placeholder";
			data[1][i] = auxVinylList.get(i).getTitle();
			data[2][i] = auxVinylList.get(i).getArtist().getName();
			data[3][i] = auxVinylList.get(i).getGenre().getName();
			data[4][i] = auxVinylList.get(i).getPrice();
			data[5][i] = auxVinylList.get(i).isOnSale();
			if (auxVinylList.get(i).isOnSale()) {
				data[6][i] = auxVinylList.get(i).getSalePercentage();
			} else {
				data[6][i] = "Not on Sale";
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
		cartPriceField.setText(String.valueOf(cart.getFullPrice()));
		cartPriceField.setColumns(10);
		cartDateField = new JTextField();
		cartDateField.setEditable(false);
		cartDateField.setBounds(144, 558, 86, 20);
		cartPanel.add(cartDateField);
		cartDateField.setColumns(10);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		cartDateField.setText(cart.getDate().format(formatter));
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
			boughtVinylsList = logic.getBoughtVinyls(client);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 91, 969, 444);
		boughtVinyls.add(scrollPane);
		tableBoughtVinyls = new JTable(fillData(boughtVinylsList), columnNames);
		scrollPane.setViewportView(tableBoughtVinyls);
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
		btnGoToCart = new JButton("Go to cart");
		btnGoToCart.setBackground(new Color(255, 218, 185));
		btnGoToCart.setBounds(332, 616, 123, 42);
		advanced.add(btnGoToCart);
		btnReturnToMenu = new JButton("Return to menu");
		btnReturnToMenu.setBackground(new Color(255, 218, 185));
		btnReturnToMenu.setBounds(652, 616, 137, 42);
		advanced.add(btnReturnToMenu);
		btnSearch = new JButton("Return to menu");
		btnSearch.setBackground(new Color(255, 218, 185));
		btnSearch.setBounds(700, 700, 137, 42);
		advanced.add(btnSearch);
		btnSearch.addActionListener(this);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 240, 930, 344);
		advanced.add(scrollPane);
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
			suggestionsList = logic.getSuggestions(client);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		modelSuggestions = new DefaultTableModel(fillData(suggestionsList), columnNames);
		tableSuggestions = new JTable(modelSuggestions);
		scrollPaneSuggestions.setViewportView(tableSuggestions);
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
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		modelBestSellers = new DefaultTableModel(fillData(bestSellers), columnNames);
		tableBestSellers = new JTable(modelBestSellers);
		scrollPaneBestSellers.setViewportView(tableBestSellers);
	}
}
