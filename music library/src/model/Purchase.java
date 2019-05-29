package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Purchase {
	private final LocalDateTime DATE = LocalDateTime.now();
	private double fullPrice;
	private int amount;
	private String buyer;
	private ArrayList<Vinyl> vinyls = new ArrayList<Vinyl>();
	private HashMap<Integer, Integer> withAmount = new HashMap<Integer, Integer>();
	private HashMap<Integer, Double> withPrice = new HashMap<Integer, Double>();

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

	public LocalDateTime getDATE() {
		return DATE;
	}

	public Purchase(double fullPrice, int amount, String buyer, ArrayList<Vinyl> vinyls,
			HashMap<Integer, Integer> withAmount, HashMap<Integer, Double> withPrice) {
		super();
		this.fullPrice = fullPrice;
		this.amount = amount;
		this.buyer = buyer;
		this.vinyls = vinyls;
		this.withAmount = withAmount;
		this.withPrice = withPrice;
	}

	public Purchase() {
		super();
	}

	/**
	 * This method add a vinyl to the Vinyls ArrayList
	 * 
	 * @param wantedVinyl The vinyl we want to insert into the ArrayList
	 */
	public void addVinyl(Vinyl wantedVinyl) {
		int amount, code = wantedVinyl.getVinylCode();
		if (withAmount.containsKey(code)) {
			amount = withAmount.get(code).intValue() + 1;
			withAmount.replace(code, amount);
		} else {
			withAmount.put(code, 1);
			withPrice.put(code, wantedVinyl.getPriceWithSale());
		}
		vinyls.add(wantedVinyl);
	}
}
