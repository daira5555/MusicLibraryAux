package control;
import java.sql.Date;
import java.util.ArrayList;

import model.Vinyl;
import model.AdvancedSearch;
import model.Artist;
import model.Client;
import model.Genre;
import model.Purchase;
import model.Vinyl;
public class LogicImpl implements Logic {
	private DataAccess dataAccess = DataAccessFactory.getDataAccess();
	public void userType(String username) throws Exception {
		dataAccess.userType(username);
	}
	public void insertNewArtist(String name) throws Exception {
		dataAccess.insertNewArtist(name);
	}
	public void insertNewGenre(String name) throws Exception {
		dataAccess.insertNewGenre(name);
	}
	public int artistExists(String name) throws Exception {
		int n = dataAccess.artistExists(name);
		return n;
	}
	public int genreExists(String name) throws Exception {
		int n = dataAccess.genreExists(name);
		return n;
	}
	public int userExists(String username) throws Exception {
		int n = dataAccess.userExists(username);
		return n;
	}
	public char getUserType(String username) throws Exception {
		char c = dataAccess.getUserType(username);
		return c;
	}
	public String getPassword(String username) throws Exception {
		String s = dataAccess.getPassword(username);
		return s;
	}
	public ArrayList<String> getArtists() throws Exception {
		ArrayList<String> ar = dataAccess.getArtists();
		return ar;
	}
	public ArrayList<String> getGenres() throws Exception {
		ArrayList<String> ar = dataAccess.getGenres();
		return ar;
	}
	@Override
	public Vinyl getVinyl(int vinylCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateVinyl(Vinyl vinyl) throws Exception {
		// TODO Auto-generated method stub
	}
	@Override
	public Artist getArtist(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Genre getGenre(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Vinyl> getBestSellersDate(java.util.Date dateChosen) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Vinyl> getBestSellers() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Vinyl> getBoughtVinyls(Client client) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Vinyl> getSuggestions(Client client) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Client getClient(String username) throws Exception {
		return null;
		// TODO Auto-generated method stub
	}
	@Override
	public void writePurchase(Purchase cart) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void registerClient(Client client) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modifyClientData(Client client) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insertNewVinyl(Vinyl v) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<Vinyl> advancedSearch(AdvancedSearch advancedSearch) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteVinyl(int vinylDel) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
