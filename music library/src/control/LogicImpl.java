package control;

import java.sql.Date;

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



public void insertNewVinyl(Vinyl v) throws Exception {
	 dataAccess.insertNewVinyl(v);
	
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




}

	
	

