package control;

import java.sql.Date;
import java.util.ArrayList;

import model.Vinyl;

public interface DataAccess {

	
	public void userType (String username) throws Exception;
	
	public void registerClient (String username, String password, String name, String surname, String email, int phonenumber, String address, long accountnumber) throws Exception;
	
	public void modifyClientData (String username, String password, String name, String surname, String email, int phonenumber, String address, long accountnumber) throws Exception;
	
	public void insertNewVinyl(Vinyl v) throws Exception;
	
	public void insertNewArtist(String name) throws Exception;

	public void insertNewGenre(String name) throws Exception;
	
	public int artistExists(String name) throws Exception;
	
	public int genreExists(String name) throws Exception;

	public void deleteVinyl(int vinylDel) throws Exception;

	public Vinyl getVinyl(int vinylCode) throws Exception;
	
	
	public int userExists(String username) throws Exception;
	
	public char getUserType(String username) throws Exception;
	
	public String getPassword (String username) throws Exception;
	
	public ArrayList<String> getArtists() throws Exception;
	
	public ArrayList<String> getGenres() throws Exception;
}
