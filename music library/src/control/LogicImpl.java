package control;

import java.sql.Date;

import model.Artist;
import model.Genre;
import model.Vinyl;

public class LogicImpl implements Logic {

	
	
	
private DataAccess dataAccess = DataAccessFactory.getDataAccess();

public void registerClient (String username, String password, String name, String surname, String email, int phonenumber, String address, long accountnumber) throws Exception{
	dataAccess.registerClient(username, password, name, surname, email, phonenumber, address, accountnumber);
}


public void userType(String username) throws Exception {
	dataAccess.userType(username);
	
}



public void modifyClientData(String username, String password, String name, String surname, String email,
		int phonenumber, String address, long accountnumber) throws Exception {
	dataAccess.modifyClientData(username, password, name, surname, email, phonenumber, address, accountnumber);
	
}



public void insertNewVinyl(String title, int artistcode, int genrecode, double price, Date publicationdate,
		String description, boolean onsale, double salepercentage, int stock, String cover) throws Exception {
	 dataAccess.insertNewVinyl(title, artistcode, genrecode, price, publicationdate, description, onsale, salepercentage, stock, cover);
	
}



public void insertNewArtist(String name) throws Exception {
	dataAccess.insertNewArtist(name);
	
}

public void insertNewGenre(String name) throws Exception {
	dataAccess.insertNewGenre(name);
}



public int artistExists(String name) throws Exception {
	int n=dataAccess.artistExists(name);
	return n;
}



public int genreExists(String name) throws Exception {
	int n=dataAccess.genreExists(name);
	return n;
}


@Override
public Vinyl getVynil(int vinylCode) throws Exception {
	// TODO Auto-generated method stub
	return null;
}


@Override
public void updateVynil(Vinyl vinyl) throws Exception {
	// TODO Auto-generated method stub
	
}


@Override
public Artist getArtist(String name) throws Exception {
	// TODO Auto-generated method stub
	return null;
}


@Override
public Genre getGenre(String name) throws Exception {
	// TODO Auto-generated method stub
	return null;
}




}

	
	

