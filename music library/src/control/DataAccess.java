package control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.AdvancedSearch;
import model.Artist;
import model.Client;
import model.Genre;
import model.Purchase;
import model.Vinyl;

public interface DataAccess {

	/**
	 * This method is to check the type of the user
	 * @param username The username of the user you want to check
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 * 
	 */
	public void userType (String username) throws Exception;
	/**
	 * This method is to add the new client artist taste to the taste_artist table in the database
	 * @param client The Client class with all the information about the client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void insertArtistTaste (Client client) throws Exception;
	/**
	 * This method inserts into the database the new client genre taste to the taste_genre table in the database
	 * @param client The Client class with all the information about the client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void insertGenreTaste (Client client) throws Exception;
	/**
	 * This method is to register a new client into the database
	 * @param client The Client class with all the information about the client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void registerClient(Client client) throws Exception;
	/**
	 * This method is to modify the data of the client (this doesn't apply to the username)
	 * @param client The Client class with all the information about the client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void modifyClientData(Client client) throws Exception;
	/**
	 * This method is to insert a new vinyl into the database (is meant to be used only by administrators)
	 * @param vinyl The Vinyl class with all the information about the vinyl
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void insertNewVinyl(Vinyl vinyl) throws Exception;
	/**
	 * This method is to add a new artist in the database
	 * @param name The name of the artist
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void insertNewArtist(String name) throws Exception;
	/**
	 * This method is to add a new genre in the database
	 * @param name The name of the genre
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void insertNewGenre(String name) throws Exception;
	/**
	 * This method is to check if the artist already exists in the database
	 * @param name The name of the artist we want to check
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public int artistExists(String name) throws Exception;
	/**
	 * This method is to check if the genre already exists in the database
	 * @param name The name of the genre we want to check
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public int genreExists(String name) throws Exception;
	/**
	 * This method check if the user already exists in the database
	 * @param username The username of the user we want to check
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public int userExists(String username) throws Exception;
	/**
	 * This method returns the type of the user 'A' if the user is an admin and 'C' if the user is a client
	 * @param username The username of the user we want to check the type
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public char getUserType(String username) throws Exception;
	/**
	 * This method returns the password of the user we want
	 * @param username The username of the user we are checking
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public String getPassword (String username) throws Exception;
	/**
	 * This method returns an ArrayList of Strings with the name of all the artists
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<String> getArtists() throws Exception;
	/**
	 * This method returns an ArrayList of Strings with the name of all the artists
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<String> getGenres() throws Exception;
	/**
	 * This method returns the artist name
	 * @param artistCode Is the code of the artist
	 * @return 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public String getArtist(int artistCode) throws Exception;
	/**
	 * This method returns the genre name
	 * @param genreCode Is the code of the genre
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public String getGenre(int genreCode) throws Exception;
	/**
	 * This method returns a Vinyl object 
	 * @param vinylCode This code correspond to the vinyl we want to search
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Vinyl getVinyl(int vinylCode) throws Exception;
	/**
	 * This method is to update the information of the vinyl in the database
	 * @param vinyl this object contains all the information about the vinyl
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void updateVinyl(Vinyl vinyl) throws Exception;
	/**
	 * This method returns a object of type Artist
	 * @param name the name of the artist we want to search
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Artist getArtist(String name) throws Exception;
	/**
	 * This method returns a object of type Genre
	 * @param name The name of the genre we want to search
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Genre getGenre(String name) throws Exception;
	/**
	 * This method insert an artist into the database
	 * @param name The name of the artist we want to insert
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void insertArtist(String name) throws Exception;
	/**
	 * This method inserts a genre into the database
	 * @param name the name of the genre we want to insert
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void insertGenre(String name) throws Exception;
	/**
	 * This method deletes from the database a vinyl
	 * @param vinylCode the code of the vinyl we want to delete
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void deleteVinyl(int vinylCode) throws Exception;
	/**
	 * This method returns an ArrayList of Vinyl
	 * @param date The date from you want to look the best sellers
	 * @throws ClassNotFoundException
	 * @throws SQLException if is a SQL Exception
	 * @throws IOException
	 */
	public ArrayList<Vinyl> getBestSellersDate (LocalDate date) throws Exception;
	/**
	 * This method returns an ArrayList of Vinyls of the all time best sellers
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Vinyl> getBestSellers() throws Exception;
	/**
	 * This method returns an ArrayList with the code of the artists that user likes
	 * @param username the username of the user you want to search his tastes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Integer> getArtistTaste (String username) throws Exception;
	/**
	 * This method returns an arraylist of genre codes
	 * @param username Is the username of the user we want to get his genre tastes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Integer> getGenreTaste (String username) throws Exception;
	/**
	 * This method returns an ArrayList with vinyls that the program suggest to the user
	 * @param username Is the username of the user we want to suggest vinyls 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Vinyl> getSuggestions(String username) throws Exception;
	/**
	 * This method returns a client from the database
	 * @param username The username of the client we want to search
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Client getClient (String username) throws Exception;
	/**
	 * This method updates the vinyl that user purchases 
	 * @param vinylCode The code of the vinyl we want to update
	 * @param amountSold The amount of vinyls that the user bought
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void updatePurchasedVinyl (int vinylCode, int amountSold) throws Exception;
	/**
	 * This method writes the purchase into the database
	 * @param purchase The object Purchase with all the information about the purchase
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void writePurchase (Purchase purchase) throws Exception;
	/**
	 * This method is to do a search with different parameters vinyls the user wants
	 * @param search Is an AdvanceSearch object with all the search parameters
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Vinyl> advancedSearch(AdvancedSearch search) throws Exception;
	
	/**
	 * This method returns an ArrayList of vinyls that the user bought
	 * @param username The username of the user that bought those vinyls
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Vinyl> getBoughtVinyls(String username) throws Exception;
	/**
	 * This method gets all the vinyl codes of the vinyls that the user bought
	 * @param username The user that bought the vinyls
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Integer> getBoughtVinylsCodes (String username) throws Exception;
	
	public boolean haveStock (int vinylCode, int amountSold) throws Exception;
	
	public ArrayList<Artist> getArtistsAllData() throws Exception;
	
	public ArrayList<Genre> getGenresAllData() throws Exception;
	
	public ArrayList<Genre> getGenresTaste (String username) throws Exception;
	
	public ArrayList<Artist> getArtistsTaste (String username) throws Exception;
}
