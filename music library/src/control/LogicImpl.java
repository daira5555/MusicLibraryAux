package control;

import java.util.ArrayList;

import model.Artist;
import model.Client;
import model.Genre;
import model.Vinyl;

public class LogicImpl implements Logic {

	private DataAccess dataAccess = DataAccessFactory.getDataAccess();
	
	public void registerClient(Client client) throws Exception {
		dataAccess.registerClient(client);
	}
	
	public void userType(String username) throws Exception {
		dataAccess.userType(username);

	}

	public void modifyClientData(Client client) throws Exception {
		dataAccess.modifyClientData(client);
	}
	
	public void insertNewVinyl(Vinyl vinyl) throws Exception {
		dataAccess.insertNewVinyl(vinyl);
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
	
	public int userExists(String username) throws Exception{
		int n = dataAccess.userExists(username);
		return n;
	}
	
	public char getUserType(String username) throws Exception{
		char c = dataAccess.getUserType(username);
		return c;
	}
	
	public String getPassword (String username) throws Exception{
		String s = dataAccess.getPassword(username);
		return s;
	}
	
	public ArrayList<String> getArtists() throws Exception{
		ArrayList<String> ar = dataAccess.getArtists();
		return ar;
	}
	
	public ArrayList<String> getGenres() throws Exception{
		ArrayList<String> ar = dataAccess.getGenres();
		return ar;
	}

	public String getArtist(int artistCode) throws Exception {
		String s = dataAccess.getArtist(artistCode);
		return s;
	}

	public String getGenre(int genreCode) throws Exception {
		String s = dataAccess.getGenre(genreCode);
		return s;
	}

	public Vinyl getVinyl(int vinylCode) throws Exception {
		Vinyl vin = new Vinyl();
		vin = dataAccess.getVinyl(vinylCode);
		return vin;
	}

	public void updateVinyl(Vinyl vinyl) throws Exception {
		dataAccess.updateVinyl(vinyl);
	}

	public Artist getArtist(String name) throws Exception {
		Artist art = new Artist();
		art = dataAccess.getArtist(name);
		return art;
	}

	public Genre getGenre(String name) throws Exception {
		Genre gen = new Genre();
		gen = dataAccess.getGenre(name);
		return gen;
	}
	
	public void insertArtist(String name) throws Exception {
		dataAccess.insertArtist(name);
	}

	public void insertGenre(String name) throws Exception {
		dataAccess.insertGenre(name);
	}

	
	public void deleteVinyl(int vinylCode) throws Exception {
		dataAccess.deleteVinyl(vinylCode);
	}


	
	public ArrayList<Vinyl> getBestSellers() throws Exception{
		ArrayList<Vinyl> vinyls = new ArrayList<Vinyl>();
		vinyls = dataAccess.getBestSellers();
		return vinyls;
	}
}
