package model;

import java.sql.Date;

public class Vinyl {
	private int vinylCode;
	private String title;
	private Genre genre;
	private Artist artist;
	private double price;
	private Date publicationDate;
	private String description;
	private boolean onSale;
	private double salePercentage;
	private int stock;
	private int amountSold;
	private String cover;
	
	public Vinyl(int vinylCode, String title, Genre genre, Artist artist, double price, Date publicationDate,
			String description, boolean onSale, double salePercentage, int stock, int amountSold, String cover) {
		super();
		this.vinylCode = vinylCode;
		this.title = title;
		this.genre = genre;
		this.artist = artist;
		this.price = price;
		this.publicationDate = publicationDate;
		this.description = description;
		this.onSale = onSale;
		this.salePercentage = salePercentage;
		this.stock = stock;
		this.amountSold = amountSold;
		this.cover = cover;
	}

	public Vinyl() {
		super();
	}

	public int getVinylCode() {
		return vinylCode;
	}

	public void setVinylCode(int vinylCode) {
		this.vinylCode = vinylCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isOnSale() {
		return onSale;
	}

	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}

	public double getSalePercentage() {
		return salePercentage;
	}

	public void setSalePercentage(double salePercentage) {
		this.salePercentage = salePercentage;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getAmountSold() {
		return amountSold;
	}

	public void setAmountSold(int amountSold) {
		this.amountSold = amountSold;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
	
	

}
