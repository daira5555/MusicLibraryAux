package control;

import java.sql.Date;
import java.util.ArrayList;

import model.AdvancedSearch;
import model.Artist;
import model.Client;
import model.Genre;
import model.Vinyl;

public interface DataAccess {

	
	public void userType (String username) throws Exception;
	
	public void modifyClientData (Client client) throws Exception;
	
	public void insertNewVinyl(Vinyl v) throws Exception;
	
	public void insertNewArtist(String name) throws Exception;

	public void insertNewGenre(String name) throws Exception;
	
	public int artistExists(String name) throws Exception;
	
	public int genreExists(String name) throws Exception;

	public void deleteVinyl(int vinylDel) throws Exception;

	public Vinyl getVinyl(int vinylCode) throws Exception;

	public Artist getArtist(String name) throws Exception;

	public Genre getGenre(String name) throws Exception;

	public ArrayList<Vinyl> getBestSellers(String time) throws Exception;

	public void registerClient(Client client) throws Exception;

	public ArrayList<Genre> getGenres() throws Exception;

	public ArrayList<Artist> getArtists() throws Exception;

	public ArrayList<Vinyl> advancedSearch(AdvancedSearch search);


	
	
}
