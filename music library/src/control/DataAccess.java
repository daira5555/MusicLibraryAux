package control;

import java.sql.Date;

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

	public Vinyl getVinyl() throws Exception;
	
	
}
