package model;

public class Client extends User {

	private String address;
	private long accountNumber;
	private Taste tastes;

	public Client(String username, String password, String name, String surname, String email, int phoneNumber,
			String address, long accountNumber) {
		super(username, password, name, surname, email, phoneNumber);
		this.address = address;
		this.accountNumber = accountNumber;
	}

	public Client(String username, String password, String name, String surname, String email, int phoneNumber) {
		super(username, password, name, surname, email, phoneNumber);
	}

	public Client() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

}