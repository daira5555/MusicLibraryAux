package control;


import java.sql.Date;


public interface Logic {

	public void userType (String username) throws Exception;
	public void registerClient (String username, String password, String name, String surname, String email, int phonenumber, String address, long accountnumber) throws Exception;
	public void modifyClientData (String username, String password, String name, String surname, String email, int phonenumber, String address, long accountnumber) throws Exception;
	public void insertNewVinyl(String title, int artistcode, int genrecode, double price, Date publicationdate, String description, boolean onsale, double salepercentage, int stock, String cover) throws Exception;
	public void insertNewArtist(String name) throws Exception;
	public int artistExists(String name) throws Exception;
	public int genreExists(String name) throws Exception;
}
