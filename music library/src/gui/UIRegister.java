package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DataAccessImpl;
import control.Logic;
import control.LogicFactory;
import model.Client;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;

public class UIRegister extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField nurnameField;
	private JTextField addressField;
	private JTextField phoneNumberField;
	private JTextField emailField;
	private JTextField bankNumberField;
	private JTextField usernameField;
	private JButton okButton;
	private JButton cancelButton;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIRegister dialog = new UIRegister();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UIRegister() {
		setTitle("Sign up");
		setBounds(100, 100, 380, 542);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 239, 213));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setBounds(42, 51, 59, 14);
			contentPanel.add(lblName);
		}
		{
			JLabel lblSurname = new JLabel("Surname:");
			lblSurname.setBounds(42, 86, 86, 14);
			contentPanel.add(lblSurname);
		}
		{
			JLabel lblAddress = new JLabel("Address:");
			lblAddress.setBounds(42, 133, 59, 14);
			contentPanel.add(lblAddress);
		}
		
		JSeparator separator = new JSeparator();
		separator.setBounds(42, 120, 266, 2);
		contentPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(42, 173, 266, 2);
		contentPanel.add(separator_1);
		
		JLabel lblPhoneNumber = new JLabel("Phone number:");
		lblPhoneNumber.setBounds(42, 186, 97, 14);
		contentPanel.add(lblPhoneNumber);
		
		JLabel lblEmail = new JLabel("e-mail:");
		lblEmail.setBounds(42, 222, 59, 14);
		contentPanel.add(lblEmail);
		
		JLabel lblBankAccount = new JLabel("Bank account number:");
		lblBankAccount.setBounds(42, 259, 149, 14);
		contentPanel.add(lblBankAccount);
		{
			JSeparator separator_2 = new JSeparator();
			separator_2.setBounds(42, 309, 268, 2);
			contentPanel.add(separator_2);
		}
		{
			JLabel lblUsername = new JLabel("Username:");
			lblUsername.setBounds(42, 322, 73, 14);
			contentPanel.add(lblUsername);
		}
		{
			JLabel lblInsertNewPassword = new JLabel("Insert password:");
			lblInsertNewPassword.setBounds(42, 372, 117, 14);
			contentPanel.add(lblInsertNewPassword);
		}
		{
			JLabel lblyouWillNot = new JLabel("(You will not be able to change this later)");
			lblyouWillNot.setFont(new Font("Arial", Font.PLAIN, 9));
			lblyouWillNot.setBounds(42, 347, 215, 14);
			contentPanel.add(lblyouWillNot);
		}
		{
			nameField = new JTextField();
			nameField.setBackground(new Color(255, 255, 255));
			nameField.setForeground(Color.GRAY);
			nameField.setText("Mar\u00EDa");
			nameField.setBounds(224, 48, 86, 20);
			contentPanel.add(nameField);
			nameField.setColumns(10);
		}
		{
			nurnameField = new JTextField();
			nurnameField.setForeground(Color.GRAY);
			nurnameField.setText("Fern\u00E1ndez");
			nurnameField.setBounds(224, 83, 86, 20);
			contentPanel.add(nurnameField);
			nurnameField.setColumns(10);
		}
		{
			addressField = new JTextField();
			addressField.setForeground(Color.GRAY);
			addressField.setText("Something Street, 45, 2I");
			addressField.setBounds(142, 130, 168, 32);
			contentPanel.add(addressField);
			addressField.setColumns(10);
		}
		{
			phoneNumberField = new JTextField();
			phoneNumberField.setForeground(Color.GRAY);
			phoneNumberField.setText("600000000");
			phoneNumberField.setBounds(174, 183, 136, 20);
			contentPanel.add(phoneNumberField);
			phoneNumberField.setColumns(10);
		}
		{
			emailField = new JTextField();
			emailField.setForeground(Color.GRAY);
			emailField.setText("ex: person@gmail.com");
			emailField.setBounds(174, 219, 136, 20);
			contentPanel.add(emailField);
			emailField.setColumns(10);
		}
		{
			bankNumberField = new JTextField();
			bankNumberField.setForeground(Color.GRAY);
			bankNumberField.setText("4567235612341243");
			bankNumberField.setBounds(174, 259, 136, 39);
			contentPanel.add(bankNumberField);
			bankNumberField.setColumns(10);
		}
		{
			usernameField = new JTextField();
			usernameField.setForeground(Color.GRAY);
			usernameField.setText("MyUserName100");
			usernameField.setBounds(174, 322, 136, 20);
			contentPanel.add(usernameField);
			usernameField.setColumns(10);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(174, 369, 136, 20);
			contentPanel.add(passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(okButton)){
			Logic logic = LogicFactory.getLogic();
			Client client = new Client();
			try {
				client.setUsername(usernameField.getText());
				client.setPassword(passwordField.getText());
				client.setName(nameField.getText());
				client.setSurname(nurnameField.getText());
				client.setEmail(emailField.getText());
				client.setPhoneNumber(Integer.parseInt(phoneNumberField.getText()));
				client.setAddress(addressField.getText());
				client.setAccountNumber(Long.parseLong(bankNumberField.getText()));
				logic.registerClient(client);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}else if (e.getSource().equals(cancelButton)) {
			this.dispose();
			UILogin login = new UILogin();
			login.setVisible(true);
		}
		
	}
}
