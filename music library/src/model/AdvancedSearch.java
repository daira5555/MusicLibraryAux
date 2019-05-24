package model;

public class AdvancedSearch {
	private String artist;
	private String title;
	private String genre;
	private int publicationYear;
	private double price;
	private int stockLessThan;
	public AdvancedSearch(String artist, String title, String genre, int publicationYear, double price,
			int stockLessThan) {
		super();
		this.artist = artist;
		this.title = title;
		this.genre = genre;
		this.publicationYear = publicationYear;
		this.price = price;
		this.stockLessThan = stockLessThan;
	}
	public AdvancedSearch() {
		super();
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStockLessThan() {
		return stockLessThan;
	}
	public void setStockLessThan(int stockLessThan) {
		this.stockLessThan = stockLessThan;
	}

	
	
	
	
	
	
	
	
	
}
