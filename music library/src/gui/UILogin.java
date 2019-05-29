package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Logic;
import control.LogicFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class UILogin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnLogIn;
	private JButton btnSignUp;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UILogin frame = new UILogin();
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
	public UILogin() {
		setTitle("Log in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 410);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 235, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Arial", Font.BOLD, 13));
		lblUsername.setBounds(309, 59, 77, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 13));
		lblPassword.setBounds(307, 147, 67, 14);
		contentPane.add(lblPassword);

		btnLogIn = new JButton("Log in ");
		btnLogIn.setBackground(new Color(255, 228, 181));
		btnLogIn.setFont(new Font("Arial", Font.BOLD, 11));
		btnLogIn.setBounds(297, 279, 89, 23);
		contentPane.add(btnLogIn);
		btnLogIn.addActionListener(this);

		btnSignUp = new JButton("Sign up");
		btnSignUp.setBackground(new Color(255, 228, 181));
		btnSignUp.setFont(new Font("Arial", Font.BOLD, 11));
		btnSignUp.addActionListener(this);
		btnSignUp.setBounds(297, 245, 89, 23);
		contentPane.add(btnSignUp);

		usernameField = new JTextField();
		usernameField.setBounds(189, 84, 302, 23);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(189, 172, 302, 23);
		contentPane.add(passwordField);

	}

	/**
	 * This method is to implement code to the buttons that have an ActionListener
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnLogIn)) {
			Logic logic = LogicFactory.getLogic();
			String username = usernameField.getText();
			try {
				String pass = logic.getPassword(usernameField.getText());
				if (logic.userExists(username) == 1) {
					if (logic.getUserType(username) == 'C') {
						if (passwordField.getText().equals(pass)) {
							this.dispose();
							UIClientMenu clientMenu = new UIClientMenu(logic.getClient(username));
							clientMenu.setVisible(true);
						} else {
							String message = "Login failed, incorrect password";
							JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
						}
					} else if (logic.getUserType(username) == 'A') {
						if (passwordField.getText().equals(pass)) {
							this.dispose();
							UIMenuAdmin adminMenu = new UIMenuAdmin();
							adminMenu.setVisible(true);
						} else {
							String message = "Login failed, incorrect password";
							JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					String message = "Login failed, incorrect username";
					JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource().equals(btnSignUp)) {
			this.dispose();
			UIRegister singUp = new UIRegister();
			singUp.setVisible(true);
		}

	}
}
