package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import model.Artist;
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
	 * 
	 */
	public void registerClient (String username, String password, String name, String surname, String email, int phonenumber, String address, long accountnumber) throws ClassNotFoundException, SQLException, IOException{
		try {
			userType(username);
			connect();
			String sql = "insert into clients values (?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, name);
			stmt.setString(4, surname);
			stmt.setString(5, email);
			stmt.setInt(6, phonenumber);
			stmt.setString(7, address);
			stmt.setLong(8, accountnumber);
			stmt.executeUpdate();
		}finally {
			
			disconnect();
		}
	}
	
	public void modifyClientData (String username, String password, String name, String surname, String email, int phonenumber, String address, long accountnumber) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "update clients set password=?, name=?, surname=?, email=?, phonenumber=?, address=?, accountnumber=? where username=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, password);
			stmt.setString(2, name);
			stmt.setString(3, surname);
			stmt.setString(4, email);
			stmt.setInt(5, phonenumber);
			stmt.setString(6, address);
			stmt.setLong(7, accountnumber);
			stmt.setString(8, username);
			stmt.executeUpdate();
		}finally {
			disconnect();
		}
	}
	public void insertNewVinyl(Vinyl v) throws ClassNotFoundException, SQLException, IOException{
		try {
			connect();
			String sql = "insert into vinyls (title,artiscode,genrecode,price,publicationdate,description,onsale,salepercentage,stock,amountsold,cover) values (?,?,?,?,?,?,?,?,?,0,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, v.getTitle() );
			stmt.setInt(2, v.getArtist().getCode());
			stmt.setInt(3, v.getGenre().getCode());
			stmt.setDouble(4, v.getPrice());
			stmt.setDate(5, v.getPublicationDate());
			stmt.setString(6, v.getDescription());
			stmt.setBoolean(7, v.isOnSale());
			stmt.setDouble(8, v.getSalePercentage());
			stmt.setInt(9, v.getStock());
			stmt.setString(10, v.getCover());
			stmt.executeUpdate();
		}finally {
			disconnect();
		}
	}
	
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
	public String getPassword (String username) throws ClassNotFoundException, SQLException, IOException{
		try {
			if (userExists(username)!=0) {
				if(getUserType(username) == 'C') {
					connect();
				}else if (getUserType(username) == 'A') {
					
				}
			}
		}finally {
			disconnect();
		}
		return null;
	}
	@Override
	public void deleteVinyl(int vinylDel) throws Exception {
		
		
		
		
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
	
	
}
