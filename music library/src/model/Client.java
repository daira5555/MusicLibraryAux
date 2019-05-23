package model;

public class Client extends User {

	private String address;
	private long accountNumber;
	private Taste tastes;
	
	public Client() {
		super();
	}

	public Client(String username, String password, String name, String surname, String email, int phoneNumber,
			String address, long accountNumber, Taste tastes) {
		super(username, password, name, surname, email, phoneNumber);
		this.address = address;
		this.accountNumber = accountNumber;
		this.tastes = tastes;
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

	public Taste getTastes() {
		return tastes;
	}

	public void setTastes(Taste tastes) {
		this.tastes = tastes;
	}

	

}