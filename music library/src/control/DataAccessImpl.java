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
}
