package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Purchase {
	private LocalDate date;
	private double fullPrice;
	private int amount;
	private Client buyer;
	private ArrayList<Vinyl> vinyls;

	public Purchase(LocalDate date, double fullPrice, int amount, Client buyer, ArrayList<Vinyl> vinyls) {
		super();
		this.date = date;
		this.fullPrice = fullPrice;
		this.amount = amount;
		this.buyer = buyer;
		this.vinyls = vinyls;
	}

	public Purchase() {
		super();
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(double fullPrice) {
		this.fullPrice = fullPrice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Client getBuyer() {
		return buyer;
	}

	public void setBuyer(Client buyer) {
		this.buyer = buyer;
	}

	public ArrayList<Vinyl> getVinyls() {
		return vinyls;
	}

	public void setVinyls(ArrayList<Vinyl> vinyls) {
		this.vinyls = vinyls;
	}

}
