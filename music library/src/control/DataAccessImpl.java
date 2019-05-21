package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import model.Artist;
import model.Client;
import model.Genre;
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
		if (con != null)
			con.close();
	}
	/**
	 * This method is to add to the clients table in the database
	 * @param username The username of the user you want to register
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
	 * This method is to add the new client to the clients table in the database
	 * @param username The username of the client
	 * @param password The client's password
	 * @param name The client's name
	 * @param surname The client's surname
	 * @param email The client's email
	 * @param phonenumber The client's phone number
	 * @param address The addres of the client
	 * @param accountnumber The account number of the client
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
		}finally {
			
			disconnect();
		}
	}
	
	/**
	 * This method is to modify the data of the client (this doesn't apply to the username)
	 * @param username The client username
	 * @param password The client password
	 * @param name The client's name
	 * @param surname The client's surname
	 * @param email The client's email
	 * @param phonenumber The client's phone number
	 * @param address The client's address
	 * @param accountnumber The client's account number
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
	 * @param title The vinyl title
	 * @param artistcode The code of the vinyl artist
	 * @param genrecode The code of the vinyl genre
	 * @param price The price of the vinyl
	 * @param publicationdate The publication date of the vinyl
	 * @param description The vinyl description
	 * @param onsale If the vinyl is on sale or not
	 * @param salepercentage The percentage of the discount of the vinyl (Ex:8%)
	 * @param stock The amount of vinyls we have
	 * @param cover The cover relative route
	 */
	
	public void insertNewVinyl(Vinyl vinyl) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "insert into vinyls (title,artiscode,genrecode,price,publicationdate,description,onsale,salepercentage,stock,amountsold,cover) values (?,?,?,?,?,?,?,?,?,0,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, vinyl.getTitle());
			stmt.setInt(2, vinyl.getArtist().getCode());
			stmt.setInt(3, vinyl.getGenre().getCode());
			stmt.setDouble(4, vinyl.getPrice());
			stmt.setDate(5, vinyl.getPublicationDate());
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

	public void insertNewVinyl(String title, int artistcode, int genrecode, double price, Date publicationdate, String description, boolean onsale, double salepercentage, int stock, String cover) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "insert into vinyls (title,artiscode,genrecode,price,publicationdate,description,onsale,salepercentage,stock,amountsold,cover) values (?,?,?,?,?,?,?,?,?,0,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setInt(2, artistcode);
			stmt.setInt(3, genrecode);
			stmt.setDouble(4, price);
			stmt.setDate(5, publicationdate);
			stmt.setString(6, description);
			stmt.setBoolean(7, onsale);
			stmt.setDouble(8, salepercentage);
			stmt.setInt(9, stock);
			stmt.setString(10, cover);
			stmt.executeUpdate();
		}finally {
			disconnect();
		}
	}
	/**
	 * This method is to add a new artist in the database
	 * @param name The name of the artist
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
				vin.setPublicationDate(rs.getDate("publicationdate"));
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
	
	public void updateVinyl(Vinyl vinyl) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "update vinyls set title=?, artistcode=?, genrecode=?, price=?, publicationdate=?, description=?, onsale=?, salepercentage=?, stock=?, cover=? where vinylcode=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, vinyl.getTitle());
			stmt.setInt(2, vinyl.getArtist().getCode());
			stmt.setInt(3, vinyl.getGenre().getCode());
			stmt.setDouble(4, vinyl.getPrice());
			stmt.setDate(5, vinyl.getPublicationDate());
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
	
	public Artist getArtist(String name) throws ClassNotFoundException, SQLException, IOException{
		Artist temp = new Artist();
		ResultSet rs = null;
		try {
			connect();
			String sql = "select * from artist where name=?";
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
}
