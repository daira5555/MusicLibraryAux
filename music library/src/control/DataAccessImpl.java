package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import model.AdvancedSearch;
import model.Artist;
import model.Client;
import model.Genre;
import model.Purchase;
import model.Taste;
import model.Vinyl;

public class DataAccessImpl implements DataAccess{
	private Connection con;
	private PreparedStatement stmt;
	private String dbHost;
	private String dbName;
	private String dbUser;
	private String dbPassword;
	
	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	private void connect() throws ClassNotFoundException, SQLException, IOException {
		if (dbHost == null) {
			Properties config = new Properties();
			FileInputStream input = null;
			try {
				input = new FileInputStream("./resources/db.properties");
				config.load(input);
				dbHost = config.getProperty("ip");
				dbName = config.getProperty("dbname");
				dbUser = config.getProperty("username");
				dbPassword = config.getProperty("password");
			} finally {
				if (input != null)
					input.close();
			}
		}
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://" + dbHost + "/" + dbName + "?serverTimezone=Europe/Madrid";
		con = DriverManager.getConnection(url, dbUser, dbPassword);
	}
	/**
	 * 
	 * @throws SQLException
	 */
	private void disconnect() throws SQLException {
		if (stmt != null)
			stmt.close();
		if (con != null) {
			
			con.close();
			con =null;
		}
	}
	/**
	 * This method is to check the type of the user
	 * @param username The username of the user you want to check
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 * 
	 */
	public void userType (String username) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "insert into users values (?,'C')";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.executeUpdate();
		}finally {
			disconnect();
		}
	}
	
	/**
	 * This method is to add the new client artist taste to the taste_artist table in the database
	 * @param client The Client class with all the information about the client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	
	public void insertArtistTaste (Client client) throws ClassNotFoundException, SQLException, IOException{
		ArrayList<Artist> artists = client.getTastes().getArtists();
		try {
			connect();
			for (Artist artist : artists) {
				String sql = "insert into taste_artist values (?, ?)";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, client.getUsername());
				stmt.setInt(2, artist.getCode());
				stmt.executeUpdate();
			}
		} finally {
			disconnect();
		}
	}
	
	/**
	 * This method inserts into the database the new client genre taste to the taste_genre table in the database
	 * @param client The Client class with all the information about the client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	
	public void insertGenreTaste (Client client) throws ClassNotFoundException, SQLException, IOException{
		ArrayList<Genre> genres = client.getTastes().getGenres();
		try {
			connect();
			for (Genre genre : genres) {
				String sql = "insert into taste_genre values (?, ?)";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, client.getUsername());
				stmt.setInt(2, genre.getCode());
				stmt.executeUpdate();
			}
		} finally {
			disconnect();
		}
	}
	
	/**
	 * This method is to register a new client into the database
	 * @param client The Client class with all the information about the client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	
	public void registerClient(Client client) throws ClassNotFoundException, SQLException, IOException{
		try {
			userType(client.getUsername());
			connect();
			String sql = "insert into clients values (?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, client.getUsername());
			stmt.setString(2, client.getPassword());
			stmt.setString(3, client.getName());
			stmt.setString(4, client.getSurname());
			stmt.setString(5, client.getEmail());
			stmt.setInt(6, client.getPhoneNumber());
			stmt.setString(7, client.getAddress());
			stmt.setLong(8, client.getAccountNumber());
			stmt.executeUpdate();
			insertArtistTaste(client);
			insertGenreTaste(client);
		}finally {
			
			disconnect();
		}
	}
	
	/**
	 * This method is to modify the data of the client (this doesn't apply to the username)
	 * @param client The Client class with all the information about the client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	
	public void modifyClientData (Client client) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "update clients set password=?, name=?, surname=?, email=?, phonenumber=?, address=?, accountnumber=? where username=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, client.getPassword());
			stmt.setString(2, client.getName());
			stmt.setString(3, client.getSurname());
			stmt.setString(4, client.getEmail());
			stmt.setInt(5, client.getPhoneNumber());
			stmt.setString(6, client.getAddress());
			stmt.setLong(7, client.getAccountNumber());
			stmt.setString(8, client.getUsername());
			stmt.executeUpdate();
		}finally {
			disconnect();
		}
	}

	
	/**
	 * This method is to insert a new vinyl into the database (is meant to be used only by administrators)
	 * @param vinyl The Vinyl class with all the information about the vinyl
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	
	public void insertNewVinyl(Vinyl vinyl) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "insert into vinyls (title,artistcode,genrecode,price,publicationdate,description,onsale,salepercentage,stock,amountsold,cover) values (?,?,?,?,?,?,?,?,?,0,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, vinyl.getTitle());
			stmt.setInt(2, vinyl.getArtist().getCode());
			stmt.setInt(3, vinyl.getGenre().getCode());
			stmt.setDouble(4, vinyl.getPrice());
			stmt.setDate(5, Date.valueOf(vinyl.getPublicationDate()));
			stmt.setString(6, vinyl.getDescription());
			stmt.setBoolean(7, vinyl.isOnSale());
			stmt.setDouble(8, vinyl.getSalePercentage());
			stmt.setInt(9, vinyl.getStock());
			stmt.setString(10, vinyl.getCover());
			stmt.executeUpdate();
		}finally {
			disconnect();
		}
	}

	/**
	 * This method is to add a new artist in the database
	 * @param name The name of the artist
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void insertNewArtist(String name) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "insert into artists (name) values (?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.executeUpdate();
		} finally {
			disconnect();
		}
	}
	/**
	 * This method is to add a new genre in the database
	 * @param name The name of the genre
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void insertNewGenre(String name) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "insert into artists (name) values (?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.executeUpdate();
		} finally {
			disconnect();
		}
	}
	/**
	 * This method is to check if the artist already exists in the database
	 * @param name The name of the artist we want to check
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public int artistExists(String name) throws ClassNotFoundException, SQLException, IOException{
		int cont = 0;
		ResultSet rs = null;
		try {
			connect();
			String sql = "select count(*) from artists where name = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if(rs.next()) {
				cont = rs.getInt(1);
			}
		} finally {
			disconnect();
		}
		return cont;
	}
	/**
	 * This method is to check if the genre already exists in the database
	 * @param name The name of the genre we want to check
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public int genreExists(String name) throws ClassNotFoundException, SQLException, IOException{
		int cont = 0;
		ResultSet rs = null;
		try {
			connect();
			String sql = "select count(*) from genre where name = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if(rs.next()) {
				cont = rs.getInt(1);
			}
		} finally {
			disconnect();
		}
		return cont;
	}
	/**
	 * This method check if the user already exists in the database
	 * @param username The username of the user we want to check
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public int userExists(String username) throws ClassNotFoundException, SQLException, IOException{
		int cont = 0;
		ResultSet rs = null;
		try {
			connect();
			String sql = "select count(*) from users where username = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if(rs.next()) {
				cont = rs.getInt(1);
			}
		}finally {
			disconnect();
		}
		return cont;
	}
	/**
	 * This method returns the type of the user 'A' if the user is an admin and 'C' if the user is a client
	 * @param username The username of the user we want to check the type
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public char getUserType(String username) throws ClassNotFoundException, SQLException, IOException{
		char type = 0;
		ResultSet rs = null;
		try {
			connect();
			String sql = "select type from users where username = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if(rs.next()) {
				type = rs.getString(1).charAt(0);
			}
		} finally {
			disconnect();
		}
		return type;
	}
	/**
	 * This method returns the password of the user we want
	 * @param username The username of the user we are checking
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public String getPassword (String username) throws ClassNotFoundException, SQLException, IOException{
		String password = null;
		ResultSet rs = null;
		try {
			if (userExists(username)!=0) {
				if(getUserType(username) == 'C') {
					connect();
					String sql = "select password from clients where username = ?";
					stmt = con.prepareStatement(sql);
					stmt.setString(1, username);
					rs = stmt.executeQuery();
					if(rs.next()) {
						password = rs.getString(1);
					}
				}else if (getUserType(username) == 'A') {
					connect();
					String sql = "select password from admins where username = ?";
					stmt = con.prepareStatement(sql);
					stmt.setString(1, username);
					rs = stmt.executeQuery();
					if(rs.next()) {
						password = rs.getString(1);
					}
				}
			}
		}finally {
			disconnect();
		}
		return password;
	}
	/**
	 * This method returns an ArrayList of Strings with the name of all the artists
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<String> getArtists() throws ClassNotFoundException, SQLException, IOException{
		ArrayList<String> artists = new ArrayList<String>();
		ResultSet rs = null;
		try {
			connect();
			String sql = "select name from artists";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				artists.add(rs.getString("name"));
			}
		} finally {
			disconnect();
		}
		return artists;
	}
	/**
	 * This method returns an ArrayList of Strings with the name of all the artists
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<String> getGenres() throws ClassNotFoundException, SQLException, IOException{
		ArrayList<String> genres = new ArrayList<String>();
		ResultSet rs = null;
		try {
			connect();
			String sql = "select name from genres";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				genres.add(rs.getString("name"));
			}
		} finally {
			disconnect();
		}
		return genres;
	}
	/**
	 * This method returns the artist name
	 * @param artistCode Is the code of the artist
	 * @return 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public String getArtist(int artistCode) throws ClassNotFoundException, SQLException, IOException{
		String artistName = null;
		ResultSet rs = null;
		try {
			connect();
			String sql = "select name from artists where artistcode=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, artistCode);
			rs = stmt.executeQuery();
			if(rs.next()) {
				artistName = rs.getString("name");
			}
		} finally {
			disconnect();
		}
		return artistName;
	}
	/**
	 * This method returns the genre name
	 * @param genreCode Is the code of the genre
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public String getGenre(int genreCode) throws ClassNotFoundException, SQLException, IOException{
		String genreName = null;
		ResultSet rs = null;
		try {
			connect();
			String sql = "select name from genres where genrecode=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, genreCode);
			rs = stmt.executeQuery();
			if(rs.next()) {
				genreName = rs.getString("name");
			}
		} finally {
			disconnect();
		}
		return genreName;
	}
	/**
	 * This method returns a Vinyl object 
	 * @param vinylCode This code correspond to the vinyl we want to search
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Vinyl getVinyl(int vinylCode) throws ClassNotFoundException, SQLException, IOException{
		Vinyl vin = new Vinyl();
		Artist art = new Artist();
		Genre gen = new Genre();
		ResultSet rs = null;
		try {
			connect();
			String sql = "select * from vinyls where vinylcode=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, vinylCode);
			rs = stmt.executeQuery();
			if(rs.next()) {
				vin.setVinylCode(vinylCode);
				vin.setTitle(rs.getString("title"));
				vin.setPrice(rs.getDouble("price"));
				vin.setPublicationDate(rs.getDate("publicationdate").toLocalDate());
				vin.setDescription(rs.getString("description"));
				vin.setOnSale(rs.getBoolean("onsale"));
				vin.setSalePercentage(rs.getDouble("salepercentage"));
				vin.setStock(rs.getInt("stock"));
				vin.setAmountSold(rs.getInt("amountsold"));
				vin.setCover(rs.getString("cover"));
				art.setCode(rs.getInt("artistcode"));
				art.setName(getArtist(rs.getInt("artistcode")));
				vin.setArtist(art);
				gen.setCode(rs.getInt("genrecode"));
				gen.setName(getGenre(rs.getInt("genrecode")));
				vin.setGenre(gen);
			}
		} finally {
			disconnect();
		}
		return vin;
	}
	/**
	 * This method is to update the information of the vinyl in the database
	 * @param vinyl this object contains all the information about the vinyl
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void updateVinyl(Vinyl vinyl) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "update vinyls set title=?, artistcode=?, genrecode=?, price=?, publicationdate=?, description=?, onsale=?, salepercentage=?, stock=?, cover=? where vinylcode=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, vinyl.getTitle());
			stmt.setInt(2, vinyl.getArtist().getCode());
			stmt.setInt(3, vinyl.getGenre().getCode());
			stmt.setDouble(4, vinyl.getPrice());
			stmt.setDate(5, Date.valueOf(vinyl.getPublicationDate()));
			stmt.setString(6, vinyl.getDescription());
			stmt.setBoolean(7, vinyl.isOnSale());
			stmt.setDouble(8, vinyl.getSalePercentage());
			stmt.setInt(9, vinyl.getStock());
			stmt.setString(10, vinyl.getCover());
			stmt.setInt(11, vinyl.getVinylCode());
			stmt.executeUpdate();
		} finally {
			disconnect();
		}
	}
	/**
	 * This method returns a object of type Artist
	 * @param name the name of the artist we want to search
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Artist getArtist(String name) throws ClassNotFoundException, SQLException, IOException{
		Artist temp = new Artist();
		ResultSet rs = null;
		try {
			connect();
			String sql = "select * from artists where name=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if (rs.next()) {
				temp.setCode(rs.getInt("artistcode"));
				temp.setName(rs.getString("name"));
			}
		} finally {
			disconnect();
		}
		return temp;
	}
	/**
	 * This method returns a object of type Genre
	 * @param name The name of the genre we want to search
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Genre getGenre(String name) throws ClassNotFoundException, SQLException, IOException{
		ResultSet rs = null;
		Genre temp = new Genre();
		try {
			connect();
			String sql = "select * from genres where name=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if(rs.next()) {
				temp.setCode(rs.getInt("genrecode"));
				temp.setName(rs.getString("name"));
			}
		} finally {
			disconnect();
		}
		return temp;
	}
	/**
	 * This method insert an artist into the database
	 * @param name The name of the artist we want to insert
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void insertArtist(String name) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "insert into artists (name) values (?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.executeUpdate();
		} finally {
			disconnect();
		}
	}
	/**
	 * This method inserts a genre into the database
	 * @param name the name of the genre we want to insert
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void insertGenre(String name) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "insert into genres (name) values (?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.executeUpdate();
		} finally {
			disconnect();
		}
	}
	/**
	 * This method deletes from the database a vinyl
	 * @param vinylCode the code of the vinyl we want to delete
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void deleteVinyl(int vinylCode) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "delete from vinyls where vinylcode = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, vinylCode);
			stmt.executeUpdate();
		} finally {
			disconnect();
		}
	}
	/**
	 * This method returns an ArrayList of Vinyl
	 * @param date The date from you want to look the best sellers
	 * @throws ClassNotFoundException
	 * @throws SQLException if is a SQL Exception
	 * @throws IOException
	 */
	public ArrayList<Vinyl> getBestSellersDate (LocalDate date) throws ClassNotFoundException, SQLException, IOException{
		ArrayList<Vinyl> vinyls = new ArrayList<Vinyl>();
		ResultSet rs = null;
		int cont = 0;
		try {
			connect();
			String sql = "select vinyls.vinylcode, title, artistcode, genrecode, price, publicationdate, description, onsale, salepercentage, stock, amountsold, cover,  amount "
					+ "from vinyls, purchases "
					+ "where vinyls.vinylcode=purchases.vinylcode and purchasedate>=? and purchasedate<=sysdate()"
					+ "order by amountsold desc";
			stmt = con.prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(date));
			rs = stmt.executeQuery();
			while(rs.next() && cont<10) {
				Vinyl v = new Vinyl();
				Artist ar = new Artist();
				Genre ge = new Genre();
				v.setVinylCode(rs.getInt("vinyls.vinylcode"));
				v.setTitle(rs.getString("title"));
				ar.setCode(rs.getInt("artistcode"));
				ar.setName(getArtist(rs.getInt("artistcode")));
				v.setArtist(ar);
				ge.setCode(rs.getInt("genrecode"));
				ge.setName(getGenre(rs.getInt("genrecode")));
				v.setGenre(ge);
				v.setPrice(rs.getDouble("price"));
				v.setPublicationDate(rs.getDate("publicationdate").toLocalDate());
				v.setDescription(rs.getString("description"));
				v.setOnSale(rs.getBoolean("onsale"));
				v.setSalePercentage(rs.getDouble("salepercentage"));
				v.setStock(rs.getInt("stock"));
				v.setAmountSold(rs.getInt("amountsold"));
				v.setCover(rs.getString("cover"));
				vinyls.add(v);
				cont++;
			}
		} finally {
			disconnect();
		}
		return vinyls;
	}
	/**
	 * This method returns an ArrayList of Vinyls of the all time best sellers
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Vinyl> getBestSellers() throws ClassNotFoundException, SQLException, IOException{
			ArrayList<Vinyl> vinyls = new ArrayList<Vinyl>();
			int cont = 0;
			ResultSet rs = null;
			try {
				connect();
				String sql = "select * from vinyls";
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next() && cont<10) {
					Vinyl v = new Vinyl();
					Artist ar = new Artist();
					Genre ge = new Genre();
					v.setVinylCode(rs.getInt("vinylcode"));
					v.setTitle(rs.getString("title"));
					ar.setCode(rs.getInt("artistcode"));
					ar.setName(getArtist(rs.getInt("artistcode")));
					v.setArtist(ar);
					ge.setCode(rs.getInt("genrecode"));
					ge.setName(getGenre(rs.getInt("genrecode")));
					v.setGenre(ge);
					v.setPrice(rs.getDouble("price"));
					v.setPublicationDate(rs.getDate("publicationdate").toLocalDate());
					v.setDescription(rs.getString("description"));
					v.setOnSale(rs.getBoolean("onsale"));
					v.setSalePercentage(rs.getDouble("salepercentage"));
					v.setStock(rs.getInt("stock"));
					v.setAmountSold(rs.getInt("amountsold"));
					v.setCover(rs.getString("cover"));
					vinyls.add(v);
					cont++;
				}
			} finally {
				disconnect();
			}
		return vinyls;
	}
	/**
	 * This method returns an ArrayList with the code of the artists that user likes
	 * @param username the username of the user you want to search his tastes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Integer> getArtistTaste (String username) throws ClassNotFoundException, SQLException, IOException{
		ArrayList<Integer> artists = new ArrayList<Integer>();
		ResultSet rs = null;
		try {
			connect();
			String sql = "select artistcode from taste_artist where username = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while(rs.next()) {
				artists.add(rs.getInt("artistcode"));
			}
		} finally {
			disconnect();
		}
		return artists;
	}
	/**
	 * This method returns an arraylist of genre codes
	 * @param username Is the username of the user we want to get his genre tastes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Integer> getGenreTaste (String username) throws ClassNotFoundException, SQLException, IOException{ 
		ResultSet rs = null;
		ArrayList<Integer> genres = new ArrayList<Integer>();
		try {
			connect();
			String sql = "select genrecode from taste_genre where username = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while(rs.next()) {
				genres.add(rs.getInt("genrecode"));
			}
		} finally {
			disconnect();
		}
		return genres;
	}
	/**
	 * This method returns an ArrayList with vinyls that the program suggest to the user
	 * @param username Is the username of the user we want to suggest vinyls 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Vinyl> getSuggestions(String username) throws ClassNotFoundException, SQLException, IOException{
		ArrayList<Vinyl> vinyls = new ArrayList<Vinyl>();
		int cont = 0;
		
		ArrayList<Integer> artistsTastes = new ArrayList<Integer>();
		artistsTastes = getArtistTaste(username);
		
		
		ArrayList<Integer> genresTastes = new ArrayList<Integer>();
		genresTastes = getGenreTaste(username);
		
		
		
		ResultSet rs = null;
		try {
			connect();
			String sql = "select * from vinyls "; 
			//"where artistcode in ? or genrecode in ? "
			//		+ "order by amountsold descending";
			if (artistsTastes.size() != 0) {
				sql += "where artistcode in (";
				if (artistsTastes.size() == 1) {
					sql+=artistsTastes.get(0).intValue()+")";
				}else {
					for (int i = 0; i < artistsTastes.size(); i++) {
						sql += artistsTastes.get(i).intValue();
						if (i == artistsTastes.size()-1) {
							sql += ") ";
						}else {
							sql += ",";
						}
					}
				}
			}
			
			if (genresTastes.size() != 0) {
				sql += " or genrecode in (";
				if (genresTastes.size() == 1) {
					sql+=genresTastes.get(0).intValue()+")";
				}else {
					for (int i = 0; i < genresTastes.size(); i++) {
						sql += genresTastes.get(i).intValue();
						if (i == genresTastes.size()-1) {
							sql += ")";
						}else {
							sql += ",";
						}
					}
				}
			}
			
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && cont<10) {
				Vinyl temp = new Vinyl();
				temp.setVinylCode(rs.getInt("vinylcode"));
				temp.setTitle(rs.getString("title"));
				
				Artist artTemp = new Artist();
				artTemp.setCode(rs.getInt("artistcode"));
				artTemp.setName(getArtist(rs.getInt("artistcode")));
				temp.setArtist(artTemp);
				
				Genre genTemp = new Genre();
				genTemp.setCode(rs.getInt("genrecode"));
				genTemp.setName(getGenre(rs.getInt("genrecode")));
				temp.setGenre(genTemp);
				
				temp.setPrice(rs.getDouble("price"));
				temp.setPublicationDate(rs.getDate("publicationdate").toLocalDate());
				temp.setDescription(rs.getString("description"));
				temp.setOnSale(rs.getBoolean("onsale"));
				temp.setSalePercentage(rs.getDouble("salepercentage"));
				temp.setStock(rs.getInt("stock"));
				temp.setAmountSold(rs.getInt("amountsold"));
				temp.setCover(rs.getString("cover"));
				
				vinyls.add(temp);
				cont++;
			}
		} finally {
			disconnect();
		}
		return vinyls;
	}
	/**
	 * This method returns a client from the database
	 * @param username The username of the client we want to search
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Client getClient (String username) throws ClassNotFoundException, SQLException, IOException{
		Client client = new Client();
		ResultSet rs = null;
		try {
			connect();
			String sql = "select * from clients where username = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if(rs.next()) {
				client.setUsername(username);
				client.setPassword(rs.getString("password"));
				client.setName(rs.getString("name"));
				client.setSurname(rs.getString("surname"));
				client.setEmail(rs.getString("email"));
				client.setPhoneNumber(rs.getInt("phonenumber"));
				client.setAddress(rs.getString("address"));
				client.setAccountNumber(rs.getLong("accountnumber"));
				Taste temp = new Taste();
				ArrayList<Artist> artists = new ArrayList<Artist>();
				artists = getArtistsTaste(username);
				ArrayList<Genre> genres = new ArrayList<Genre>();
				genres = getGenresTaste(username);
				temp.setArtists(artists);
				temp.setGenres(genres);
				client.setTastes(temp);
			}
		} finally {
			disconnect();
		}
		return client;
	}
	/**
	 * This method updates the vinyl that user purchases 
	 * @param vinylCode The code of the vinyl we want to update
	 * @param amountSold The amount of vinyls that the user bought
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void updatePurchasedVinyl (int vinylCode, int amountSold) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "update vinyls set amountsold = amountsold + ?, stock = stock - ? where vinylcode = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, amountSold);
			stmt.setInt(2, amountSold);
			stmt.setInt(3, vinylCode);
			stmt.executeUpdate();
		} finally {
			disconnect();
		}
	}
	public boolean haveStock (int vinylCode, int amountSold) throws ClassNotFoundException, SQLException, IOException{
		boolean stockResult = false;
		ResultSet rs = null;
		try {
			connect();
			String sql = "select stock from vinyls where vinylcode = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, vinylCode);
			rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("stock")>amountSold) {
					stockResult = true;
				}else {
					stockResult = false;
				}
			}
		} finally {
			disconnect();
		}
		return stockResult;
	}
	/**
	 * This method writes the purchase into the database
	 * @param purchase The object Purchase with all the information about the purchase
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void writePurchase (Purchase purchase) throws ClassNotFoundException, SQLException, IOException{
		try {
			for (Map.Entry<Integer, Integer> entry : purchase.getWithAmount().entrySet()) {
				
				int key = entry.getKey();
				int amount = entry.getValue();
				boolean stock = haveStock(key, amount);
				if (stock == false) {
					throw new Exception("There isn't enought stock");
				}
				connect();
				Double price = purchase.getWithPrice().get(key);
				Double totalPrice = amount * price;
				String sql = "insert into purchases values (?, ?, ?, ?, ?)";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, purchase.getBuyer());
				stmt.setInt(2, key);
				stmt.setTimestamp(3, Timestamp.valueOf(purchase.getDATE()));
				stmt.setInt(4, amount);
				stmt.setDouble(5, totalPrice);
				stmt.executeUpdate();
				updatePurchasedVinyl(key, amount);
			}
		}catch (Exception e) {
				e.printStackTrace();
			
		} finally {
			disconnect();
		}
	}
	/**
	 * This method is to do a search with different parameters vinyls the user wants
	 * @param search Is an AdvanceSearch object with all the search parameters
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Vinyl> advancedSearch(AdvancedSearch search) throws ClassNotFoundException, SQLException, IOException{
		ArrayList<Vinyl> vinyls = new ArrayList<Vinyl>();
		ResultSet rs = null;
		int cont = 0;
		int first = 0;
		try {
			connect();
			String sql = "select * from vinyls ";
			if (!search.getArtist().isEmpty()) {
				if (first == 0) {
					sql = sql + "where ";
					first++;
				}
				Artist artist = getArtist(search.getArtist());
				connect();
				sql = sql + " artistcode = "+artist.getCode();
				cont++;
			}
			if (!search.getTitle().isEmpty()) {
				if (first == 0) {
					sql = sql + "where ";
					first++;
				}
				if(cont>0) {
					sql = sql + " or ";
				}
				sql = sql + " title like '%"+search.getTitle()+"%' ";
				cont++;
			}
			if (!search.getGenre().isEmpty()) {
				if (first == 0) {
					sql = sql + "where ";
					first++;
				}
				if(cont>0) {
					sql = sql + " or ";
				}
				Genre genre = getGenre(search.getGenre());
				connect();
				sql = sql + " genrecode = "+genre.getCode();
				cont++;
			}
			if (search.getPublicationYear() != 0) {
				if (first == 0) {
					sql = sql + "where ";
					first++;
				}
				if(cont>0) {
					sql = sql + " or ";
				}
				sql = sql + " YEAR(publicationdate) = "+search.getPublicationYear();
				cont++;
			}
			if(search.getPrice() != 0) {
				if (first == 0) {
					sql = sql + "where ";
					first++;
				}
				if(cont>0) {
					sql = sql + " or ";
				}
				sql = sql + " price = "+search.getPrice();
				cont++;
			}
			if(search.getStockLessThan() != 0) {
				if (first == 0) {
					sql = sql + "where ";
					first++;
				}
				if(cont>0) {
					sql = sql + " or ";
				}
				sql = sql + " stock < "+search.getStockLessThan();
			}
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Vinyl temp = new Vinyl();
				temp.setVinylCode(rs.getInt("vinylcode"));
				temp.setTitle(rs.getString("title"));
				
				Artist artTemp = new Artist();
				artTemp.setCode(rs.getInt("artistcode"));
				artTemp.setName(getArtist(rs.getInt("artistcode")));
				temp.setArtist(artTemp);
				
				Genre genTemp = new Genre();
				genTemp.setCode(rs.getInt("genrecode"));
				genTemp.setName(getGenre(rs.getInt("genrecode")));
				temp.setGenre(genTemp);
				
				temp.setPrice(rs.getDouble("price"));
				temp.setPublicationDate(rs.getDate("publicationdate").toLocalDate());
				temp.setDescription(rs.getString("description"));
				temp.setOnSale(rs.getBoolean("onsale"));
				temp.setSalePercentage(rs.getDouble("salepercentage"));
				temp.setStock(rs.getInt("stock"));
				temp.setAmountSold(rs.getInt("amountsold"));
				temp.setCover(rs.getString("cover"));
				
				vinyls.add(temp);
			}
		} finally {
			disconnect();
		}
		return vinyls;
	}
	/**
	 * This method gets all the vinyl codes of the vinyls that the user bought
	 * @param username The user that bought the vinyls
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Integer> getBoughtVinylsCodes (String username) throws ClassNotFoundException, SQLException, IOException{
		ArrayList<Integer> vinylCodes = new ArrayList<Integer>();
		ResultSet rs = null;
		try {
			connect();
			String sql = "select distinct vinylcode from purchases where username=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while(rs.next()) {
				vinylCodes.add(rs.getInt("vinylcode"));
			}
		} finally {
			disconnect();
		}
		return vinylCodes;
	}
	/**
	 * This method returns an ArrayList of vinyls that the user bought
	 * @param username The username of the user that bought those vinyls
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public ArrayList<Vinyl> getBoughtVinyls(String username) throws ClassNotFoundException, SQLException, IOException{
		ArrayList<Vinyl> vinyls = new ArrayList<Vinyl>();
		ArrayList<Integer> vinylCodes = new ArrayList<Integer>();
		ResultSet rs = null;
		try {
			vinylCodes = getBoughtVinylsCodes(username);
			for (Integer vinyl : vinylCodes) {
				connect();
				String sql = "select * from vinyls where vinylcode = ?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, vinyl);
				rs = stmt.executeQuery();
				while (rs.next()) {
					Vinyl temp = new Vinyl();
					temp.setVinylCode(rs.getInt("vinylcode"));
					temp.setTitle(rs.getString("title"));
					
					Artist artTemp = new Artist();
					artTemp.setCode(rs.getInt("artistcode"));
					artTemp.setName(getArtist(rs.getInt("artistcode")));
					temp.setArtist(artTemp);
					
					Genre genTemp = new Genre();
					genTemp.setCode(rs.getInt("genrecode"));
					genTemp.setName(getGenre(rs.getInt("genrecode")));
					temp.setGenre(genTemp);
					
					temp.setPrice(rs.getDouble("price"));
					temp.setPublicationDate(rs.getDate("publicationdate").toLocalDate());
					temp.setDescription(rs.getString("description"));
					temp.setOnSale(rs.getBoolean("onsale"));
					temp.setSalePercentage(rs.getDouble("salepercentage"));
					temp.setStock(rs.getInt("stock"));
					temp.setAmountSold(rs.getInt("amountsold"));
					temp.setCover(rs.getString("cover"));
					vinyls.add(temp);
				}
			}
			
		} finally {
			disconnect();
		}
		return vinyls;
	}
	
	public ArrayList<Artist> getArtistsAllData() throws ClassNotFoundException, SQLException, IOException{
		ArrayList<Artist> artists = new ArrayList<Artist>();
		ResultSet rs = null;
		try {
			connect();
			String sql = "select * from artists";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Artist temp = new Artist();
				temp.setCode(rs.getInt("artistcode"));
				temp.setName(rs.getString("name"));
				artists.add(temp);
			}
		} finally {
			disconnect();
		}
		return artists;
	}
	
	public ArrayList<Genre> getGenresAllData() throws ClassNotFoundException, SQLException, IOException{
		ArrayList<Genre> genres = new ArrayList<Genre>();
		ResultSet rs = null;
		try {
			connect();
			String sql = "select * from genres";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Genre temp = new Genre();
				temp.setCode(rs.getInt("genrecode"));
				temp.setName(rs.getString("name"));
				genres.add(temp);
			}
		} finally {
			disconnect();
		}
		return genres;
	}
	
	public ArrayList<Artist> getArtistsTaste (String username) throws ClassNotFoundException, SQLException, IOException{
		ArrayList<Artist> artists = new ArrayList<Artist>();
		ArrayList<Integer> artistscodes = new ArrayList<Integer>();
		artistscodes = getArtistTaste(username);
		ResultSet rs = null;
		try {
			for (Integer integer : artistscodes) {
				connect();
				String sql = "select * from artists where artistcode = ?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, integer);
				rs = stmt.executeQuery();
				if (rs.next()) {
					Artist temp = new Artist();
					temp.setCode(integer);
					temp.setName(rs.getString("name"));
					artists.add(temp);
				}
			}
			
		} finally {
			disconnect();
		}
		return artists;
	}
	
	public ArrayList<Genre> getGenresTaste (String username) throws ClassNotFoundException, SQLException, IOException{
		ArrayList<Genre> genres = new ArrayList<Genre>();
		ArrayList<Integer> genrescodes = new ArrayList<Integer>();
		genrescodes = getGenreTaste(username);
		ResultSet rs = null;
		try {
			for (Integer integer : genrescodes) {
				connect();
				String sql = "select * from genres where genrecode = ?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, integer);
				rs = stmt.executeQuery();
				if (rs.next()) {
					Genre temp = new Genre();
					temp.setCode(integer);
					temp.setName(rs.getString("name"));
					genres.add(temp);
				}
			}
			
		} finally {
			disconnect();
		}
		return genres;
	}
}
