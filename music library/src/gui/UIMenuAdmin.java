package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import control.Logic;
import control.LogicFactory;
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
	private JButton btnSearch;
	private JButton btnBestSellers;
	private Object[][] data1;

	/**
	 * Launch the application. BORRAR AL ACABAR
	 */
	public static void main(String[] args) {
				try {
					UIMenuAdmin frame = new UIMenuAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			
	
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
				{ "The Dark Side of the Moon'", "Pink Floyd", "Rock psicodélico", new Integer(20), new Boolean(false),
						new Integer(20) },
				{ "London Calling", "The Clash", "New wave", new Integer(22), new Boolean(true), new Integer(20) },
				{ "Shilling the Rubes", "David Bowie", "New wave", new Integer(21), new Boolean(false),
						new Integer(20) },
				{ "Back in Black", "AC/DC", "Hard rock", new Integer(20), new Boolean(true), new Integer(20) },
				{ "Nevermind", "Nirvana", "Grunge", new Integer(21), new Boolean(false), new Integer(20) } };

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

		rdbtnThisWeek = new JRadioButton("This week");
		rdbtnThisWeek.setBackground(new Color(250, 235, 215));
		rdbtnThisWeek.setBounds(41, 364, 109, 23);
		contentPane.add(rdbtnThisWeek);

		rdbtnThisMonth = new JRadioButton("This month");
		rdbtnThisMonth.setBackground(new Color(250, 235, 215));
		rdbtnThisMonth.setBounds(41, 401, 109, 23);
		contentPane.add(rdbtnThisMonth);

		rdbtnThisYear = new JRadioButton("This year");
		rdbtnThisYear.setBackground(new Color(250, 235, 215));
		rdbtnThisYear.setBounds(39, 438, 109, 23);
		contentPane.add(rdbtnThisYear);

		rdbtnFromTheBegining = new JRadioButton("From the begining of the times");
		rdbtnFromTheBegining.setBackground(new Color(250, 235, 215));
		rdbtnFromTheBegining.setBounds(39, 476, 221, 23);
		contentPane.add(rdbtnFromTheBegining);
		
		
		ButtonGroup bestS = new ButtonGroup();
		bestS.add(rdbtnThisWeek);
		bestS.add(rdbtnThisMonth);
		bestS.add(rdbtnThisYear);
		bestS.add(rdbtnFromTheBegining);
		
		

		String[] columnNames1 = { "image", "fefeg", "Genre", "Price", "On sale:", "Sale percentage:", "codigo" };

		
		JLabel label = new JLabel();
		
		Image imagen = new ImageIcon("././koala.jpg").getImage();
		ImageIcon imagen2 = new ImageIcon(imagen.getScaledInstance(100, 100, imagen.SCALE_SMOOTH));
		
			label.setIcon(imagen2);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setVerticalAlignment(JLabel.CENTER);

		Object[][] data1 = {
				{ label, "The Dark Side of the Moon'", "Pink Floyd", "Rock psicodélico", new Integer(20), new Boolean(false),
						new Integer(20), new Integer(1) },
				{ label, "London Calling", "The Clash", "New wave", new Integer(22), new Boolean(true), new Integer(20) , new Integer(2)},
				{ label, "Shilling the Rubes", "David Bowie", "New wave", new Integer(21), new Boolean(false),
						new Integer(20), new Integer(1) },
				{ label, "Back in Black", "AC/DC", "Hard rock", new Integer(20), new Boolean(true), new Integer(20), new Integer(1) },
				{ label, "Nevermind", "Nirvana", "Grunge", new Integer(21), new Boolean(false), new Integer(20),new Integer(1) } };
		
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(289, 354, 808, 226);
		contentPane.add(scrollPane_1);

		table_1 = new JTable(data1, columnNames1);
		table_1.getColumn("image").setCellRenderer(new LabelRenderer());
		scrollPane_1.setViewportView(table_1);
		
		btnBestSellers = new JButton("show best sellers");
		btnBestSellers.setBounds(41, 506, 153, 27);
		contentPane.add(btnBestSellers);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnSearch)) {
			Logic logic = LogicFactory.getLogic();

			Vinyl v = new Vinyl();

			v.setTitle(albumTitleField.getText());
		//v.setArtist.setName(artistField.getText());
		//	v.setGenre.setName(genreField.getText());
			v.setPrice(Double.parseDouble(priceField.getText()));
			v.setStock(Integer.parseInt(stockField.getText()));

		//	v.setPublicationDate(Date.parse(publicationDateField.getText()));
			

		
			try {
				logic.insertNewVinyl(v);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}

		} else if (e.getSource().equals(btnBestSellers)) {
			ArrayList<Vinyl> vinyls = new ArrayList<Vinyl>();
			
			Logic logic = LogicFactory.getLogic();
			if(rdbtnThisWeek.isSelected()) {
				vinyls=logic.showBestSellers("week");
				tableRedone(vinyls);
			}else if(rdbtnThisMonth.isSelected()) {
				vinyls=logic.showBestSellers("month");
				tableRedone(vinyls);
			}else if(rdbtnThisYear.isSelected()) {
				vinyls=logic.showBestSellers("year");
				tableRedone(vinyls);
			}else {
				vinyls=logic.showBestSellers("beginning");
				tableRedone(vinyls);
			}
			

		}else if(e.getSource().equals(btnModify)) {
			Vinyl vinyl = new Vinyl();
			
		int index= table.getSelectedRow();
		TableModel model = table.getModel();
		vinyl.setVinylCode(Integer.parseInt(model.getValueAt(index, 6).toString()));
		
		
		}else if(e.getSource().equals(btnDelete)) {
		
		Vinyl vinyl = new Vinyl();
		
		int index= table.getSelectedRow();
		TableModel model = table.getModel();
		vinyl.setVinylCode(Integer.parseInt(model.getValueAt(index, 6).toString()));
		
		}

	}

	public void tableRedone(ArrayList<Vinyl> vinyls) {
		
		
for(int i=0; i<vinyls.size(); i++) {
			
			JLabel label = new JLabel();

			Image imagen = new ImageIcon(vinyls.get(i).getCover()).getImage();
			ImageIcon imagen2 = new ImageIcon(imagen.getScaledInstance(100, 100, imagen.SCALE_SMOOTH));

			label.setIcon(imagen2);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setVerticalAlignment(JLabel.CENTER);
			
			data1[i][0] =label;
			
			data1[i][1] =vinyls.get(i).getTitle();
			data1[i][2] =vinyls.get(i).getArtist().getName();
			data1[i][3] =vinyls.get(i).getGenre().getName();
			data1[i][4] =vinyls.get(i).getPrice();
			data1[i][5] =vinyls.get(i).isOnSale();
			data1[i][6] =vinyls.get(i).getSalePercentage();
			
		}
		
		
		
		
	}
	
	class LabelRenderer implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			TableColumn tc = table.getColumn("image");
			tc.setMinWidth(100);
			tc.setMaxWidth(100);
			table.setRowHeight(100);

			return (Component) value;
		}

	}
}
