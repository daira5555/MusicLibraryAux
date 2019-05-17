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
public class UIModifyVinyl extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JButton btnSelect;
	private JTextField textField_4;
	private JTextField textField_5;

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
		
		textField = new JTextField();
		textField.setBounds(147, 25, 205, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(147, 61, 205, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(147, 98, 205, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(147, 198, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
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
		
		JButton btnSubmitChanges = new JButton("Submit changes");
		btnSubmitChanges.setBackground(new Color(255, 218, 185));
		btnSubmitChanges.setBounds(24, 356, 142, 23);
		contentPane.add(btnSubmitChanges);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 218, 185));
		btnCancel.setBounds(302, 356, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(45, 138, 68, 14);
		contentPane.add(lblDescription);
		
		textField_4 = new JTextField();
		textField_4.setBounds(147, 138, 205, 43);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblSalePercentage = new JLabel("Sale percentage:");
		lblSalePercentage.setBounds(45, 257, 138, 14);
		contentPane.add(lblSalePercentage);
		
		textField_5 = new JTextField();
		textField_5.setBounds(147, 254, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel label_1 = new JLabel("%");
		label_1.setBounds(239, 257, 46, 14);
		contentPane.add(label_1);
	}
}
