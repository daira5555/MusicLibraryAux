package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

@SuppressWarnings("serial")
public class UINewVinyl extends JFrame {

	private JPanel contentPane;
	private JTextField titleField;
	private JTextField artistField;
	private JTextField genreField;
	private JTextField priceField;
	private JTextField descriptionField;
	private JRadioButton rdbtnNo;
	private JRadioButton rdbtnYes;
	private JTextField textField;

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
		setBounds(100, 100, 489, 462);
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
		
		JButton btnSubmitChanges = new JButton("Submit");
		btnSubmitChanges.setBackground(new Color(255, 235, 205));
		btnSubmitChanges.setBounds(105, 374, 83, 23);
		contentPane.add(btnSubmitChanges);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 239, 213));
		btnCancel.setBounds(233, 374, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel label = new JLabel("Sale percentage:");
		label.setBounds(50, 308, 138, 14);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(156, 305, 89, 20);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("Image:");
		label_1.setBounds(50, 343, 46, 14);
		contentPane.add(label_1);
		
		JButton button = new JButton("Select");
		button.setBackground(new Color(255, 218, 185));
		button.setBounds(156, 340, 89, 23);
		contentPane.add(button);
	}
}
