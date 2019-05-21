package control;


import java.sql.Date;
import java.util.ArrayList;

import model.Artist;
import model.Client;
import model.Genre;
import model.Vinyl;


public interface Logic {

	public void userType (String username) throws Exception;
	public void registerClient (Client client) throws Exception;
	public void modifyClientData (String username, String password, String name, String surname, String email, int phonenumber, String address, long accountnumber) throws Exception;
	public void insertNewVinyl(Vinyl v) throws Exception;
	public void insertNewArtist(String name) throws Exception;
	public int artistExists(String name) throws Exception;
	public int genreExists(String name) throws Exception;
	public ArrayList<Vinyl> showBestSellers(String time);
	public Vinyl getVynil(int vinylCode) throws Exception;
	public void updateVynil(Vinyl vinyl) throws Exception;
	public Artist getArtist(String name) throws Exception;
	public Genre getGenre(String name) throws Exception;
	public ArrayList<Genre> getGenres() throws Exception;
	public ArrayList<Artist> getArtists() throws Exception;
	void modifyClientData(Client client) throws Exception;

	
}
