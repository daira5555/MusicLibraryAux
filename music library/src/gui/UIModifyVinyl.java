package gui;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Logic;
import control.LogicFactory;
import model.Genre;
import model.Vinyl;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
@SuppressWarnings("serial")
public class UIModifyVinyl extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField titleField;
	private JTextField artistField;
	private JTextField genreField;
	private JTextField priceField;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JButton btnSelect;
	private JTextField descriptionField;
	private JTextField salePercentageField;
	private JButton btnSubmitChanges;
	private JButton btnCancel;
	private Vinyl vToMod = new Vinyl(12, "test", null, null, 69.69, LocalDate.now(), "pretty gud albun", true, 0.69, 12, 23, "wew");
	private Logic logic = LogicFactory.getLogic();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIModifyVinyl frame = new UIModifyVinyl();
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
	public UIModifyVinyl() {
		setTitle("Modify vinyl");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 430);
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
		lblPrice.setBounds(45, 201, 46, 14);
		contentPane.add(lblPrice);
		JLabel lblOnSale = new JLabel("On sale:");
		lblOnSale.setBounds(45, 226, 46, 14);
		contentPane.add(lblOnSale);
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBackground(new Color(255, 250, 240));
		rdbtnYes.setBounds(147, 225, 46, 23);
		contentPane.add(rdbtnYes);
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBackground(new Color(255, 250, 240));
		rdbtnNo.setBounds(194, 225, 68, 23);
		contentPane.add(rdbtnNo);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNo);
		bg.add(rdbtnYes);
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
		artistField = new JTextField();
		artistField.setBounds(147, 61, 205, 20);
		contentPane.add(artistField);
		artistField.setColumns(10);
		artistField.setText("testibles");
		genreField = new JTextField();
		genreField.setBounds(147, 98, 205, 20);
		contentPane.add(genreField);
		genreField.setColumns(10);
		genreField.setText("neo-test");
		priceField = new JTextField();
		priceField.setBounds(147, 198, 86, 20);
		contentPane.add(priceField);
		priceField.setColumns(10);
		priceField.setText(String.valueOf(vToMod.getPrice()));
		JLabel label = new JLabel("\u20AC");
		label.setBounds(239, 201, 46, 14);
		contentPane.add(label);
		JLabel lblNewLabel = new JLabel("Image:");
		lblNewLabel.setBounds(45, 282, 46, 14);
		contentPane.add(lblNewLabel);
		btnSelect = new JButton("Select");
		btnSelect.setBackground(new Color(255, 218, 185));
		btnSelect.setBounds(147, 278, 86, 23);
		contentPane.add(btnSelect);
		btnSelect.addActionListener(this);
		btnSubmitChanges = new JButton("Submit changes");
		btnSubmitChanges.setBackground(new Color(255, 218, 185));
		btnSubmitChanges.setBounds(24, 356, 142, 23);
		contentPane.add(btnSubmitChanges);
		btnSubmitChanges.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 218, 185));
		btnCancel.setBounds(302, 356, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(this);
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(45, 138, 68, 14);
		contentPane.add(lblDescription);
		descriptionField = new JTextField();
		descriptionField.setBounds(147, 138, 205, 43);
		contentPane.add(descriptionField);
		descriptionField.setColumns(10);
		descriptionField.setText(vToMod.getDescription());
		JLabel lblSalePercentage = new JLabel("Sale percentage:");
		lblSalePercentage.setBounds(45, 257, 138, 14);
		contentPane.add(lblSalePercentage);
		salePercentageField = new JTextField();
		salePercentageField.setBounds(147, 254, 86, 20);
		contentPane.add(salePercentageField);
		salePercentageField.setColumns(10);
		salePercentageField.setText(String.valueOf(vToMod.getSalePercentage()));
		JLabel label_1 = new JLabel("%");
		label_1.setBounds(239, 257, 46, 14);
		contentPane.add(label_1);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnCancel)) {
			this.dispose();
		} else if (e.getSource().equals(btnSelect)) {
			JFileChooser fileChooser = new JFileChooser("C:/");
			fileChooser.showOpenDialog(this);
		} else if (e.getSource().equals(btnSubmitChanges)) {
			
		}
	}
}
