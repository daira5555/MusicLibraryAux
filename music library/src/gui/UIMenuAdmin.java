package gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import control.Logic;
import control.LogicFactory;
import model.AdvancedSearch;
import model.Vinyl;

import javax.swing.JScrollPane;
@SuppressWarnings("serial")
public class UIMenuAdmin extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField artistField;
	private JTextField albumTitleField;
	private JTextField genreField;
	private JTextField publicationDateField;
	private JTextField priceField;
	private JTable tableBestSellers;
	private JTable tableAdvancedSearch;
	private JTextField stockField;
	private JScrollPane scrollPaneBestSellers;
	private DefaultTableModel modelBestSellers;
	private DefaultTableModel modelAdvancedSearch;
	private JScrollPane scrollPaneAdvancedSearch;
	private JButton btnSearch;
	private JButton btnSearchBestSellers;
	private JButton btnOrderVinylFromSearch;
	private JButton btnNewVinyl;
	private JButton btnDeleteFromBestSellers;
	private JButton btnModifyFromBestSellers;
	private JButton btnModifyFromSearch;
	private JButton btnOrderFromSearch;
	private JButton btnDeleteFromSearch;
	private final String[] columnNames = {"Cover Art", "Title", "Genre", "Price", "On sale:", "Sale percentage:"};
	private Logic logic = LogicFactory.getLogic();
	private ArrayList<Vinyl> advancedSearchList;
	private ArrayList<Vinyl> bestSellersResultList;
	private AdvancedSearch search = new AdvancedSearch();
	private JDateChooser bestSellerCalendar = new JDateChooser();
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
		JLabel lblByPublicationDate = new JLabel("By publication year:");
		lblByPublicationDate.setBounds(415, 67, 116, 14);
		contentPane.add(lblByPublicationDate);
		JLabel lblByPrice = new JLabel("By price: ");
		lblByPrice.setBounds(679, 42, 58, 14);
		contentPane.add(lblByPrice);
		artistField = new JTextField();
		artistField.setBounds(98, 39, 309, 20);
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
		separator.setBounds(41, 330, 1056, 2);
		contentPane.add(separator);
		btnDeleteFromBestSellers = new JButton("Delete");
		btnDeleteFromBestSellers.setBackground(new Color(255, 218, 185));
		btnDeleteFromBestSellers.setBounds(981, 591, 116, 42);
		contentPane.add(btnDeleteFromBestSellers);
		btnModifyFromBestSellers = new JButton("Modify");
		btnModifyFromBestSellers.setBackground(new Color(255, 218, 185));
		btnModifyFromBestSellers.setBounds(663, 591, 123, 42);
		contentPane.add(btnModifyFromBestSellers);
		btnModifyFromBestSellers.addActionListener(this);
		JLabel label = new JLabel();
		Image imagen = new ImageIcon("././koala.jpg").getImage();
		ImageIcon imagen2 = new ImageIcon(imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		label.setIcon(imagen2);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 92, 1056, 202);
		contentPane.add(scrollPane);
		JLabel lblStockLessThan = new JLabel("Stock less than:");
		lblStockLessThan.setBounds(679, 67, 107, 14);
		contentPane.add(lblStockLessThan);
		stockField = new JTextField();
		stockField.setBounds(775, 64, 74, 20);
		contentPane.add(stockField);
		stockField.setColumns(10);
		btnOrderVinylFromSearch = new JButton("Order vinyl");
		btnOrderVinylFromSearch.setBackground(new Color(255, 218, 185));
		btnOrderVinylFromSearch.setBounds(336, 591, 137, 42);
		contentPane.add(btnOrderVinylFromSearch);
		btnNewVinyl = new JButton("New Vinyl");
		btnNewVinyl.setBackground(new Color(255, 218, 185));
		btnNewVinyl.setBounds(50, 591, 116, 42);
		contentPane.add(btnNewVinyl);
		btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(222, 184, 135));
		btnSearch.setBounds(859, 38, 238, 42);
		contentPane.add(btnSearch);
		JLabel lblBestSellers = new JLabel("Best sellers:");
		lblBestSellers.setForeground(new Color(188, 143, 143));
		lblBestSellers.setFont(new Font("Arial", Font.BOLD, 15));
		lblBestSellers.setBounds(62, 330, 134, 27);
		contentPane.add(lblBestSellers);
		ImageIcon imagen3 = new ImageIcon(imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		label.setIcon(imagen3);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		scrollPaneBestSellers = new JScrollPane();
		scrollPaneBestSellers.setBounds(352, 354, 771, 226);
		contentPane.add(scrollPaneBestSellers);
		try {
			bestSellersResultList = logic.getBestSellers();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		modelBestSellers = new DefaultTableModel(fillData(bestSellersResultList), columnNames);
		tableBestSellers = new JTable(modelBestSellers);
		scrollPaneBestSellers.setViewportView(tableBestSellers);
		btnSearchBestSellers = new JButton("show best sellers");
		btnSearchBestSellers.setBounds(41, 506, 153, 27);
		contentPane.add(btnSearchBestSellers);
		btnModifyFromSearch = new JButton("Modify");
		btnModifyFromSearch.setBounds(558, 296, 89, 23);
		contentPane.add(btnModifyFromSearch);
		btnOrderFromSearch = new JButton("Order");
		btnOrderFromSearch.setBounds(380, 296, 89, 23);
		contentPane.add(btnOrderFromSearch);
		btnDeleteFromSearch = new JButton("Delete");
		btnDeleteFromSearch.setBounds(730, 296, 89, 23);
		contentPane.add(btnDeleteFromSearch);
		btnDeleteFromBestSellers.addActionListener(this);
		btnDeleteFromSearch.addActionListener(this);
		btnModifyFromSearch.addActionListener(this);
		btnSearchBestSellers.addActionListener(this);
		btnSearch.addActionListener(this);
		bestSellerCalendar.setBounds(39, 438, 242, 20);
		contentPane.add(bestSellerCalendar);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnSearch)) {
			search.setArtist(artistField.getText());
			search.setGenre(genreField.getText());
			search.setPrice(Double.parseDouble(priceField.getText()));
			search.setPublicationYear(Integer.parseInt(publicationDateField.getText()));
			search.setStockLessThan(Integer.parseInt(stockField.getText()));
			search.setTitle(albumTitleField.getText());
			try {
				advancedSearchList = logic.advancedSearch(search);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			modelAdvancedSearch = new DefaultTableModel(fillData(advancedSearchList), columnNames);
			tableAdvancedSearch = new JTable(modelAdvancedSearch);
			scrollPaneAdvancedSearch.setViewportView(tableAdvancedSearch);
		} else if (e.getSource().equals(btnSearchBestSellers)) {
			try {
				bestSellersResultList = logic.getBestSellersDate(bestSellerCalendar.getDate());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			modelBestSellers = new DefaultTableModel(fillData(bestSellersResultList), columnNames);
			tableBestSellers = new JTable(modelBestSellers);
			scrollPaneBestSellers.setViewportView(tableBestSellers);

		} else if (e.getSource().equals(btnModifyFromBestSellers)) {
			int index = tableBestSellers.getSelectedRow();
			TableModel model = tableBestSellers.getModel();
			int vinylMod = (Integer.parseInt(model.getValueAt(index, 6).toString()));
			UIModifyVinyl mod = new UIModifyVinyl(vinylMod);
			this.dispose();
			mod.setVisible(true);
		} else if (e.getSource().equals(btnDeleteFromBestSellers)) {
			int index = tableBestSellers.getSelectedRow();
			TableModel model = tableBestSellers.getModel();
			int vinylDel = (Integer.parseInt(model.getValueAt(index, 6).toString()));
			deleteVinyl(vinylDel);
		} else if (e.getSource().equals(btnModifyFromSearch)) {
			int index = tableAdvancedSearch.getSelectedRow();
			TableModel model = tableAdvancedSearch.getModel();
			int vinylMod = (Integer.parseInt(model.getValueAt(index, 6).toString()));
			UIModifyVinyl mod = new UIModifyVinyl(vinylMod);
			mod.setVisible(true);
			this.dispose();
		} else if (e.getSource().equals(btnDeleteFromSearch)) {
			int index = tableAdvancedSearch.getSelectedRow();
			TableModel model = tableAdvancedSearch.getModel();
			int vinylDel = (Integer.parseInt(model.getValueAt(index, 6).toString()));
			deleteVinyl(vinylDel);
		}
	}
	private void deleteVinyl(int vinylDel) {
		Logic logic = LogicFactory.getLogic();
		try {
			logic.deleteVinyl(vinylDel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	class LabelRenderer implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			TableColumn tc = table.getColumn("image");
			tc.setMinWidth(100);
			tc.setMaxWidth(100);
			table.setRowHeight(100);
			return (Component) value;
		}
	}
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
}
