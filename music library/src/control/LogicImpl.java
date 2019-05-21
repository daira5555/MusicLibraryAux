package control;

import java.sql.Date;
import java.util.ArrayList;

import model.Vinyl;

import model.Artist;
import model.Client;
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



public ArrayList <Vinyl> vinyls(String time) throws Exception {
	ArrayList <Vinyl> vinyls = new ArrayList<Vinyl>();
	if(time.equals("week")) {
		//vinils.getBestSellersWeek();
	}else if(time.equals("month")) {
		//vinils.getBestSellersMonth();
	}else if(time.equals("year")) {
		//vinils.getBestSellersYear();
	}else {
		//vinils.getBestSellersAll();
	}
	
	return vinyls;
	
}


public ArrayList<Vinyl> showBestSellers(String time) {
	
	return null;
}


@Override
public void registerClient(Client client) throws Exception {
	// TODO Auto-generated method stub
	
}


@Override
public void modifyClientData(Client client) throws Exception {
	// TODO Auto-generated method stub
	
}


@Override
public ArrayList<Genre> getGenres() throws Exception {
	// TODO Auto-generated method stub
	return null;
}


@Override
public ArrayList<Artist> getArtists() throws Exception {
	// TODO Auto-generated method stub
	return null;
}




}

	
	

