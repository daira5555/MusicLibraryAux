package control;
import java.util.ArrayList;
import java.util.Date;

import model.Artist;
import model.Client;
import model.Genre;
import model.Purchase;
import model.Vinyl;
public interface Logic {
	public void userType(String username) throws Exception;
	public void registerClient(String username, String password, String name, String surname, String email, int phonenumber, String address, long accountnumber) throws Exception;
	public void modifyClientData(String username, String password, String name, String surname, String email, int phonenumber, String address, long accountnumber) throws Exception;
	public void insertNewVinyl(String title, int artistcode, int genrecode, double price, Date publicationdate, String description, boolean onsale, double salepercentage, int stock, String cover)
			throws Exception;
	public void insertNewArtist(String name) throws Exception;
	public int artistExists(String name) throws Exception;
	public int genreExists(String name) throws Exception;
	public Vinyl getVinyl(int vinylCode) throws Exception;
	public void updateVinyl(Vinyl vinyl) throws Exception;
	public Artist getArtist(String name) throws Exception;
	public Genre getGenre(String name) throws Exception;
	public int userExists(String username) throws Exception;
	public char getUserType(String username) throws Exception;
	public String getPassword(String username) throws Exception;
	public ArrayList<String> getArtists() throws Exception;
	public ArrayList<String> getGenres() throws Exception;
	public ArrayList<Vinyl> getBestSellersDate(Date dateChosen) throws Exception;
	public ArrayList<Vinyl> getBestSellers() throws Exception;
	public ArrayList<Vinyl> getBoughtVinyls(Client client) throws Exception;
	public ArrayList<Vinyl> getSuggestions(Client client) throws Exception;
	public Client getClient(String username) throws Exception;
	public void writePurchase(Purchase cart) throws Exception;
}
