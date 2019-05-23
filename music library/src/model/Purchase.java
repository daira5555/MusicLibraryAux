package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Purchase {
	private LocalDateTime date;
	private double fullPrice;
	private int amount;
	private String buyer;
	private ArrayList<Vinyl> vinyls = new ArrayList<Vinyl>();
	private HashMap<Integer, Integer> withAmount = new HashMap<Integer, Integer>();
	private HashMap<Integer, Double> withPrice = new HashMap<Integer, Double>();
	
	public Purchase() {
		super();
	}

	public Purchase(LocalDateTime date, double fullPrice, int amount, String buyer, ArrayList<Vinyl> vinyls,
			HashMap<Integer, Integer> withAmount, HashMap<Integer, Double> withPrice) {
		super();
		this.date = date;
		this.fullPrice = fullPrice;
		this.amount = amount;
		this.buyer = buyer;
		this.vinyls = vinyls;
		this.withAmount = withAmount;
		this.withPrice = withPrice;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
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

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public ArrayList<Vinyl> getVinyls() {
		return vinyls;
	}

	public void setVinyls(ArrayList<Vinyl> vinyls) {
		this.vinyls = vinyls;
	}

	public HashMap<Integer, Integer> getWithAmount() {
		return withAmount;
	}

	public void setWithAmount(HashMap<Integer, Integer> withAmount) {
		this.withAmount = withAmount;
	}

	public HashMap<Integer, Double> getWithPrice() {
		return withPrice;
	}

	public void setWithPrice(HashMap<Integer, Double> withPrice) {
		this.withPrice = withPrice;
	}

	public void addWithPrice(int vinylCode, double price) {
		withPrice.put(vinylCode, price);
	}
	
	public void addWithAmount(int vinylCode, int amount) {
		withAmount.put(vinylCode, amount);
	}
	
	public void addVinyl(Vinyl vinyl) {
		vinyls.add(vinyl);
	}
	
	public void getVinylAmount (ArrayList<Vinyl> vinyls) {
		Collections.sort(vinyls);
		int cont = 0;
		int vinylCode = vinyls.get(0).getVinylCode();
		for (int i = 0; i < vinyls.size(); i++) {
			if (vinylCode == vinyls.get(i).getVinylCode()) {
				cont++;
			}else {
				addWithAmount(vinylCode, cont);
				cont = 0;
				vinylCode = vinyls.get(i).getVinylCode();
				i--;
			}
		}
		addWithAmount(vinylCode, cont);
	}
	
}