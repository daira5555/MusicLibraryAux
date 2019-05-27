package gui;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdesktop.swingx.JXDatePicker;

import com.toedter.calendar.JDateChooser;

import control.Logic;
import control.LogicFactory;
import model.DateConverter;
import model.Vinyl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;

@SuppressWarnings("serial")
public class UINewVinyl extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField titleField;
	private JTextField priceField;
	private JTextField descriptionField;
	private JRadioButton rdbtnNo;
	private JRadioButton rdbtnYes;
	private JTextField saleField;
	private JButton btnSelect;
	private JButton btnSubmitChanges;
	private JButton btnCancel;
	private JDateChooser calendarPublication = new JDateChooser();
	private Vinyl v = new Vinyl();
	private JButton btnNewGenre;
	private JButton btnNewArtist;
	private JScrollPane artistsSP;
	private JScrollPane genresSP;
	private JList artistsList;
	private JList genresList;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private DefaultListModel<String> model2 = new DefaultListModel<String>();
	private Logic logic = LogicFactory.getLogic();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UINewVinyl frame = new UINewVinyl();
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
	public UINewVinyl() {
		setTitle("Insert new vinyl");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 245, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(50, 32, 46, 14);
		contentPane.add(lblTitle);

		JLabel lblArtist = new JLabel("Artist:");
		lblArtist.setBounds(50, 80, 46, 14);
		contentPane.add(lblArtist);

		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setBounds(50, 132, 46, 14);
		contentPane.add(lblGenre);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(50, 179, 46, 14);
		contentPane.add(lblPrice);

		JLabel lblDescription = new JLabel("Description: ");
		lblDescription.setBounds(50, 221, 84, 14);
		contentPane.add(lblDescription);

		JLabel lblSale = new JLabel("sale:");
		lblSale.setBounds(50, 273, 46, 14);
		contentPane.add(lblSale);

		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBackground(new Color(255, 245, 238));
		rdbtnYes.setBounds(144, 269, 59, 23);
		contentPane.add(rdbtnYes);

		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBackground(new Color(255, 245, 238));
		rdbtnNo.setBounds(279, 269, 65, 23);
		contentPane.add(rdbtnNo);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNo);
		bg.add(rdbtnYes);

		titleField = new JTextField();
		titleField.setBounds(144, 29, 178, 20);
		contentPane.add(titleField);
		titleField.setColumns(10);

		priceField = new JTextField();
		priceField.setBounds(144, 176, 178, 20);
		contentPane.add(priceField);
		priceField.setColumns(10);

		descriptionField = new JTextField();
		descriptionField.setBounds(144, 221, 178, 47);
		contentPane.add(descriptionField);
		descriptionField.setColumns(10);

		btnSubmitChanges = new JButton("Submit");
		btnSubmitChanges.setBackground(new Color(255, 235, 205));
		btnSubmitChanges.setBounds(105, 384, 83, 23);
		contentPane.add(btnSubmitChanges);

		btnSubmitChanges.addActionListener(this);

		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 239, 213));
		btnCancel.setBounds(233, 384, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(this);

		JLabel label = new JLabel("Sale percentage:");
		label.setBounds(50, 328, 138, 14);
		contentPane.add(label);

		saleField = new JTextField();
		saleField.setColumns(10);
		saleField.setBounds(156, 325, 165, 20);
		contentPane.add(saleField);

		JLabel label_1 = new JLabel("Image:");
		label_1.setBounds(50, 353, 46, 14);
		contentPane.add(label_1);

		btnSelect = new JButton("Select");
		btnSelect.setBackground(new Color(255, 218, 185));
		btnSelect.setBounds(156, 350, 89, 23);
		contentPane.add(btnSelect);
		btnSelect.addActionListener(this);

		JLabel lblPublicationDate = new JLabel("Publication date:");
		lblPublicationDate.setBounds(50, 298, 153, 14);
		contentPane.add(lblPublicationDate);

		calendarPublication.setBounds(154, 295, 165, 22);
		contentPane.add(calendarPublication);
		calendarPublication.setDate(Calendar.getInstance().getTime());

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
		
		displayArtists(model);
		artistsList.setModel(model);
		artistsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		genresSP = new JScrollPane();
		genresSP.setBounds(144, 118, 178, 47);
		contentPane.add(genresSP);

		genresList = new JList();
		genresSP.setViewportView(genresList);
		
		displayGenres(model2);
		genresList.setModel(model2);
		genresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnSubmitChanges)) {
			try {
				//Logic logic = LogicFactory.getLogic();

				v.setTitle(titleField.getText());
				v.setArtist(logic.getArtist(artistsList.getSelectedValue().toString()));
				v.setDescription(descriptionField.getText());
				v.setGenre(logic.getGenre(genresList.getSelectedValue().toString()));
				v.setPrice(Double.parseDouble(priceField.getText()));
				v.setSalePercentage(Double.parseDouble(saleField.getText()));
				v.setAmountSold(0);
				
				v.setPublicationDate(DateConverter.convertToLocalDateViaInstant(calendarPublication.getDate()));
				if (v.getCover() == null) {
					v.setCover("no cover");
				}

				if (rdbtnYes.isSelected()) {
					v.setOnSale(true);
				} else {
					v.setOnSale(false);
				}

				logic.insertNewVinyl(v);
			} catch (Exception e1) {

				e1.printStackTrace();
			}

		} else if (e.getSource().equals(btnCancel)) {
			this.dispose();
			UIMenuAdmin admin = new UIMenuAdmin();
			admin.setVisible(true);

		} else if (e.getSource().equals(btnSelect)) {
			JFileChooser chooser = new JFileChooser("././imagenes/");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("Images", "jpg", "png", "jpeg");
			chooser.setFileFilter(imgFilter);
			chooser.showOpenDialog(this);
			File result = chooser.getSelectedFile();
			if (result != null) {
				String path = null;
				path = result.getPath();
				int index = path.lastIndexOf("\\");
				String name = path.substring(index + 1);
				name = "././imagenes/" + name;
				System.out.println(name);
				v.setCover(name);
			}else {
				JOptionPane.showMessageDialog(null, "Error you must enter an image", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}else if(e.getSource().equals(btnNewArtist)) {
			String newArtist = JOptionPane.showInputDialog("Insert new Artist: ");
			//System.out.println(newArtist);
			if (newArtist != null) {
				try {
					logic.insertArtist(newArtist);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error. Maybe the artist arleady exists","Error", JOptionPane.ERROR_MESSAGE);
					//e1.printStackTrace();
				}
			}
			
		}else if(e.getSource().equals(btnNewGenre)) {
			String newGenre = JOptionPane.showInputDialog("Insert new Genre: ");
			if (newGenre != null) {
				try {
					logic.insertGenre(newGenre);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error. Maybe the genre arleady exists","Error", JOptionPane.ERROR_MESSAGE);
					//e2.printStackTrace();
				}
			}
			
		}

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
}


