package control;
import java.util.ArrayList;
import java.util.Date;

import model.AdvancedSearch;
import model.Artist;
import model.Client;
import model.Genre;
import model.Purchase;
import model.Vinyl;
public interface Logic {
	public void userType (String username) throws Exception;
	public void registerClient (Client client) throws Exception;
	void modifyClientData(Client client) throws Exception;
	public void insertNewVinyl(Vinyl v) throws Exception;
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
	public ArrayList<Artist> getArtists() throws Exception;
	public ArrayList<Genre> getGenres() throws Exception;
	public ArrayList<Vinyl> getBestSellersDate(Date dateChosen) throws Exception;
	public ArrayList<Vinyl> getBestSellers() throws Exception;
	public ArrayList<Vinyl> getBoughtVinyls(Client client) throws Exception;
	public ArrayList<Vinyl> getSuggestions(Client client) throws Exception;
	public Client getClient(String username) throws Exception;
	public void writePurchase(Purchase cart) throws Exception;
	public ArrayList<Vinyl> advancedSearch(AdvancedSearch advancedSearch) throws Exception;
	public void deleteVinyl(int vinylDel) throws Exception;
}
