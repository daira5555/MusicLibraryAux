package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import control.Logic;
import control.LogicFactory;
import model.DateConverter;
import model.Vinyl;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
@SuppressWarnings("serial")
public class UIModifyVinyl extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField titleField;
	private JTextField priceField;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JButton btnSelect;
	private JTextField descriptionField;
	private JTextField salePercentageField;
	private JButton btnSubmitChanges;
	private JButton btnCancel;
	private int vinylCode;
	private Vinyl vToMod = null;
	private Logic logic = LogicFactory.getLogic();
	private JLabel lblStock;
	private JTextField stockField;
	private JDateChooser dateChooser;
	private JFileChooser fileChooser;
	private JScrollPane artistsSP;
	private JScrollPane genresSP;
	private JList artistsList;
	private JList genresList;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private DefaultListModel<String> model2 = new DefaultListModel<String>();
	private JButton btnNewGenre;
	private JButton btnNewArtist;
	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// UIModifyVinyl frame = new UIModifyVinyl(4);
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }
	/**
	 * Create the frame.
	 * 
	 * @param vinylMod
	 */
	public UIModifyVinyl(int vinylMod) {
		try {
			vToMod = logic.getVinyl(vinylCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Modify vinyl");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 539);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 245, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(45, 28, 46, 14);
		contentPane.add(lblTitle);
		JLabel lblArtist = new JLabel("Artist:");
		lblArtist.setBounds(45, 64, 46, 14);
		contentPane.add(lblArtist);
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setBounds(45, 101, 46, 14);
		contentPane.add(lblGenre);
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(45, 254, 46, 14);
		contentPane.add(lblPrice);
		JLabel lblOnSale = new JLabel("On sale:");
		lblOnSale.setBounds(45, 279, 46, 14);
		contentPane.add(lblOnSale);
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBackground(new Color(255, 250, 240));
		rdbtnYes.setBounds(147, 278, 46, 23);
		contentPane.add(rdbtnYes);
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBackground(new Color(255, 250, 240));
		rdbtnNo.setBounds(284, 278, 68, 23);
		contentPane.add(rdbtnNo);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNo);
		bg.add(rdbtnYes);
		vToMod = getVinyl(vinylMod);
		System.out.println(vToMod.getTitle());
		if (vToMod.isOnSale()) {
			rdbtnYes.setSelected(true);
		} else {
			rdbtnNo.setSelected(true);
		}
		titleField = new JTextField();
		titleField.setBounds(147, 25, 205, 20);
		contentPane.add(titleField);
		titleField.setColumns(10);
		titleField.setText(vToMod.getTitle());
		priceField = new JTextField();
		priceField.setBounds(147, 251, 178, 20);
		contentPane.add(priceField);
		priceField.setColumns(10);
		priceField.setText(String.valueOf(vToMod.getPrice()));
		JLabel label = new JLabel("\u20AC");
		label.setBounds(341, 254, 11, 14);
		contentPane.add(label);
		JLabel lblNewLabel = new JLabel("Image:");
		lblNewLabel.setBounds(45, 335, 46, 14);
		contentPane.add(lblNewLabel);
		btnSelect = new JButton("Select");
		btnSelect.setBackground(new Color(255, 218, 185));
		btnSelect.setBounds(147, 331, 205, 23);
		contentPane.add(btnSelect);
		btnSelect.addActionListener(this);
		btnSubmitChanges = new JButton("Submit changes");
		btnSubmitChanges.setBackground(new Color(255, 218, 185));
		btnSubmitChanges.setBounds(45, 443, 142, 23);
		contentPane.add(btnSubmitChanges);
		btnSubmitChanges.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 218, 185));
		btnCancel.setBounds(263, 443, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(this);
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(45, 191, 68, 14);
		contentPane.add(lblDescription);
		descriptionField = new JTextField();
		descriptionField.setBounds(147, 191, 205, 43);
		contentPane.add(descriptionField);
		descriptionField.setColumns(10);
		descriptionField.setText(vToMod.getDescription());
		JLabel lblSalePercentage = new JLabel("Sale percentage:");
		lblSalePercentage.setBounds(45, 310, 138, 14);
		contentPane.add(lblSalePercentage);
		salePercentageField = new JTextField();
		salePercentageField.setBounds(147, 307, 178, 20);
		contentPane.add(salePercentageField);
		salePercentageField.setColumns(10);
		salePercentageField.setText(String.valueOf(vToMod.getSalePercentage()));
		JLabel label_1 = new JLabel("%");
		label_1.setBounds(335, 310, 17, 14);
		contentPane.add(label_1);
		dateChooser = new JDateChooser();
		dateChooser.setBounds(147, 365, 205, 22);
		contentPane.add(dateChooser);
		JLabel lblPublicationDate = new JLabel("Publication date:");
		lblPublicationDate.setBounds(45, 368, 92, 14);
		contentPane.add(lblPublicationDate);
		lblStock = new JLabel("Stock:");
		lblStock.setBounds(45, 402, 46, 14);
		contentPane.add(lblStock);
		stockField = new JTextField();
		stockField.setBounds(147, 399, 205, 20);
		contentPane.add(stockField);
		stockField.setColumns(10);
		stockField.setText(String.valueOf(vToMod.getStock()));
		
		//Atists and genres
		
		btnNewArtist = new JButton("New Artist");
		btnNewArtist.setBounds(355, 76, 122, 23);
		contentPane.add(btnNewArtist);
		btnNewArtist.addActionListener(this);

		btnNewGenre = new JButton("New Genre");
		btnNewGenre.setBounds(355, 128, 122, 23);
		contentPane.add(btnNewGenre);
		btnNewGenre.addActionListener(this);

		artistsSP = new JScrollPane();
		artistsSP.setBounds(144, 60, 178, 47);
		contentPane.add(artistsSP);

		artistsList = new JList();
		artistsSP.setViewportView(artistsList);

		fillArtistsList();
		

		genresSP = new JScrollPane();
		genresSP.setBounds(144, 118, 178, 47);
		contentPane.add(genresSP);

		genresList = new JList();
		genresSP.setViewportView(genresList);
		
		fillGenresList();
		
	}
	private Vinyl getVinyl(int vinylCode) {
		Vinyl v = new Vinyl();
		Logic logic = LogicFactory.getLogic();
		try {
			v = logic.getVinyl(vinylCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	private void fillGenresList() {
		model2 = new DefaultListModel<String>();
		displayGenres(model2);
		genresList.setModel(model2);
		genresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void fillArtistsList() {
		model = new DefaultListModel<String>();
		displayArtists(model);
		artistsList.setModel(model);
		artistsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void displayGenres(DefaultListModel<String> model) {
		Logic logic = LogicFactory.getLogic();
		try {
			ArrayList<String> names = logic.getGenres();
			for (String g : names) {
				model.addElement(g);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void displayArtists(DefaultListModel<String> model) {
		Logic logic = LogicFactory.getLogic();
		try {
			ArrayList<String> names = logic.getArtists();
			for (String art : names) {
				model.addElement(art);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(btnCancel)) {
			this.dispose();
		} else if (event.getSource().equals(btnSelect)) {
			fileChooser = new JFileChooser("C:/");
			fileChooser.showOpenDialog(this);
		} else if (event.getSource().equals(btnSubmitChanges)) {
			try {
				File coverArtSrc = fileChooser.getSelectedFile();
				File coverArtDest = new File("././Images/" + coverArtSrc.getName());
				Files.copy(coverArtSrc.toPath(), coverArtDest.toPath());
				vToMod.setArtist(logic.getArtist(artistsList.getSelectedValue().toString()));
				vToMod.setCover(coverArtDest.toPath().toString());
				vToMod.setDescription(descriptionField.getText());
				vToMod.setGenre(logic.getGenre(genresList.getSelectedValue().toString()));
				if (rdbtnYes.isSelected()) {
					vToMod.setOnSale(true);
					vToMod.setSalePercentage((Double.valueOf(priceField.getText()) / 100));
				} else {
					vToMod.setOnSale(false);
					vToMod.setSalePercentage(1);
				}
				vToMod.setPrice((Double.valueOf(priceField.getText())));
				vToMod.setPublicationDate(DateConverter.convertToLocalDateViaInstant(dateChooser.getDate()));
				vToMod.setStock(Integer.valueOf(stockField.getText()));
				vToMod.setTitle(titleField.getText());
				logic.updateVinyl(vToMod);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if(event.getSource().equals(btnNewArtist)) {
			String newArtist = JOptionPane.showInputDialog("Insert new Artist: ");
			//System.out.println(newArtist);
			if (newArtist != null) {
				try {
					logic.insertArtist(newArtist);
					fillArtistsList();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error. Maybe the artist arleady exists","Error", JOptionPane.ERROR_MESSAGE);
					//e1.printStackTrace();
				}
			}
			
		}else if(event.getSource().equals(btnNewGenre)) {
			String newGenre = JOptionPane.showInputDialog("Insert new Genre: ");
			if (newGenre != null) {
				try {
					logic.insertGenre(newGenre);
					fillGenresList();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error. Maybe the genre arleady exists","Error", JOptionPane.ERROR_MESSAGE);
					//e2.printStackTrace();
				}
			}
			
		}
	}
}
