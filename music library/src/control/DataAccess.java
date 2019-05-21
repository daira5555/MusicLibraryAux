package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Artist;
import model.Client;
import model.Genre;
import model.Vinyl;

public interface DataAccess {

	
	public void userType (String username) throws Exception;
	
	public void registerClient(Client client) throws Exception;
	
	public void modifyClientData(Client client) throws Exception;
	
	//public void modifyClientData (String username, String password, String name, String surname, String email, int phonenumber, String address, long accountnumber) throws Exception;
	
	public void insertNewVinyl(String title, int artistcode, int genrecode, double price, Date publicationdate, String description, boolean onsale, double salepercentage, int stock, String cover) throws Exception;
	
	public void insertNewArtist(String name) throws Exception;

	public void insertNewGenre(String name) throws Exception;
	
	public int artistExists(String name) throws Exception;
	
	public int genreExists(String name) throws Exception;
	
	public int userExists(String username) throws Exception;
	
	public char getUserType(String username) throws Exception;
	
	public String getPassword (String username) throws Exception;
	
	public ArrayList<String> getArtists() throws Exception;
	
	public ArrayList<String> getGenres() throws Exception;
	
	public String getArtist(int artistCode) throws Exception;
	
	public String getGenre(int genreCode) throws Exception;
	
	public Vinyl getVinyl(int vinylCode) throws Exception;
	
	public void updateVinyl(Vinyl vinyl) throws Exception;
	
	public Artist getArtist(String name) throws Exception;
	
	public Genre getGenre(String name) throws Exception;
	
	public void insertArtist(String name) throws Exception;
	
	public void insertGenre(String name) throws Exception;

}
