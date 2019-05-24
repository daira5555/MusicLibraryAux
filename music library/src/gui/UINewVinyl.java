package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

import control.Logic;
import control.LogicFactory;
import model.Vinyl;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;

@SuppressWarnings("serial")
public class UINewVinyl extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField titleField;
	private JTextField artistField;
	private JTextField genreField;
	private JTextField priceField;
	private JTextField descriptionField;
	private JRadioButton rdbtnNo;
	private JRadioButton rdbtnYes;
	private JTextField saleField;
	private JButton button;
	private JButton btnSubmitChanges;
	private JButton btnCancel;
	private JXDatePicker picker;

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
		setBounds(100, 100, 373, 468);
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
		lblGenre.setBounds(50, 122, 46, 14);
		contentPane.add(lblGenre);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(50, 169, 46, 14);
		contentPane.add(lblPrice);

		JLabel lblDescription = new JLabel("Description: ");
		lblDescription.setBounds(50, 211, 84, 14);
		contentPane.add(lblDescription);

		JLabel lblSale = new JLabel("sale:");
		lblSale.setBounds(50, 263, 46, 14);
		contentPane.add(lblSale);

		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBackground(new Color(255, 245, 238));
		rdbtnYes.setBounds(144, 259, 59, 23);
		contentPane.add(rdbtnYes);

		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBackground(new Color(255, 245, 238));
		rdbtnNo.setBounds(279, 259, 65, 23);
		contentPane.add(rdbtnNo);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNo);
		bg.add(rdbtnYes);

		titleField = new JTextField();
		titleField.setBounds(144, 29, 178, 20);
		contentPane.add(titleField);
		titleField.setColumns(10);

		artistField = new JTextField();
		artistField.setBounds(144, 77, 178, 20);
		contentPane.add(artistField);
		artistField.setColumns(10);

		genreField = new JTextField();
		genreField.setBounds(144, 119, 178, 20);
		contentPane.add(genreField);
		genreField.setColumns(10);

		priceField = new JTextField();
		priceField.setBounds(144, 166, 178, 20);
		contentPane.add(priceField);
		priceField.setColumns(10);

		descriptionField = new JTextField();
		descriptionField.setBounds(144, 211, 178, 47);
		contentPane.add(descriptionField);
		descriptionField.setColumns(10);

		btnSubmitChanges = new JButton("Submit");
		btnSubmitChanges.setBackground(new Color(255, 235, 205));
		btnSubmitChanges.setBounds(105, 374, 83, 23);
		contentPane.add(btnSubmitChanges);

		btnSubmitChanges.addActionListener(this);

		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 239, 213));
		btnCancel.setBounds(233, 374, 89, 23);
		contentPane.add(btnCancel);

		JLabel label = new JLabel("Sale percentage:");
		label.setBounds(50, 318, 138, 14);
		contentPane.add(label);

		saleField = new JTextField();
		saleField.setColumns(10);
		saleField.setBounds(156, 315, 165, 20);
		contentPane.add(saleField);

		JLabel label_1 = new JLabel("Image:");
		label_1.setBounds(50, 343, 46, 14);
		contentPane.add(label_1);

		button = new JButton("Select");
		button.setBackground(new Color(255, 218, 185));
		button.setBounds(156, 340, 89, 23);
		contentPane.add(button);

		JLabel lblPublicationDate = new JLabel("Publication date:");
		lblPublicationDate.setBounds(50, 288, 153, 14);
		contentPane.add(lblPublicationDate);

		picker = new JXDatePicker();
		picker.setBounds(154, 285, 165, 22);
		contentPane.add(picker);
		picker.setDate(Calendar.getInstance().getTime());
		picker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnSubmitChanges)) {
			try {
				Logic logic = LogicFactory.getLogic();

				Vinyl v = new Vinyl();

				v.setTitle(titleField.getText());
				v.setArtist(logic.getArtist(artistField.getText()));
				v.setDescription(descriptionField.getText());
				v.setGenre(logic.getGenre(genreField.getText()));
				v.setPrice(Double.parseDouble(priceField.getText()));
				v.setSalePercentage(Double.parseDouble(saleField.getText()));
				v.setAmountSold(0);

				String aux = picker.getDate().toString();
				System.out.println(aux);
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(aux, format);
				// v.setPublicationDate(date);

				if (rdbtnYes.isSelected()) {
					v.setOnSale(true);
				} else {
					v.setOnSale(false);
				}

				logic.insertNewVinyl(v);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource().equals(btnCancel)) {
				this.dispose();
				UIMenuAdmin admin = new UIMenuAdmin();
				admin.setVisible(true);

		}

	}
}
