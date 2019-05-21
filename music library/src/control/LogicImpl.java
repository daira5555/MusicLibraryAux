package control;

import java.sql.Date;
import java.util.ArrayList;

import model.Artist;
import model.Genre;
import model.Vinyl;

public class LogicImpl implements Logic {

	private DataAccess dataAccess = DataAccessFactory.getDataAccess();

	public void registerClient(String username, String password, String name, String surname, String email,
			int phonenumber, String address, long accountnumber) throws Exception {
		dataAccess.registerClient(username, password, name, surname, email, phonenumber, address, accountnumber);
	}

	public void userType(String username) throws Exception {
		dataAccess.userType(username);

	}

	public void modifyClientData(String username, String password, String name, String surname, String email,
			int phonenumber, String address, long accountnumber) throws Exception {
		dataAccess.modifyClientData(username, password, name, surname, email, phonenumber, address, accountnumber);

	}

	public void insertNewVinyl(String title, int artistcode, int genrecode, double price, Date publicationdate,
			String description, boolean onsale, double salepercentage, int stock, String cover) throws Exception {
		dataAccess.insertNewVinyl(title, artistcode, genrecode, price, publicationdate, description, onsale,
				salepercentage, stock, cover);

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
	
	
}
