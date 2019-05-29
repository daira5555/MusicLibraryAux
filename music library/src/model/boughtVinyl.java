package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class boughtVinyl extends Vinyl {
	private double priceAtTheTime;
	private LocalDateTime dateBought;
	private int amount;

	public boughtVinyl() {
		super();
	}

	public boughtVinyl(int vinylCode, String title, Genre genre, Artist artist, double price, LocalDate publicationDate,
			String description, boolean onSale, double salePercentage, int stock, int amountSold, String cover,
			double priceAtTheTime, LocalDateTime dateBought, int amount) {
		super(vinylCode, title, genre, artist, price, publicationDate, description, onSale, salePercentage, stock,
				amountSold, cover);
		this.priceAtTheTime = priceAtTheTime;
		this.dateBought = dateBought;
		this.amount = amount;
	}

	public double getPriceAtTheTime() {
		return priceAtTheTime;
	}

	public void setPriceAtTheTime(double priceAtTheTime) {
		this.priceAtTheTime = priceAtTheTime;
	}

	public LocalDateTime getDateBought() {
		return dateBought;
	}

	public void setDateBought(LocalDateTime dateBought) {
		this.dateBought = dateBought;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
