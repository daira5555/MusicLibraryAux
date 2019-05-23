package control;

import java.sql.Date;
import java.util.ArrayList;

import model.Vinyl;
import model.AdvancedSearch;
import model.Artist;
import model.Client;
import model.Genre;
import model.Vinyl;

public class LogicImpl implements Logic {

	private DataAccess dataAccess = DataAccessFactory.getDataAccess();


	public void userType(String username) throws Exception {
		dataAccess.userType(username);

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
		int n = dataAccess.artistExists(name);
		return n;
	}

	public int genreExists(String name) throws Exception {
		int n = dataAccess.genreExists(name);
		return n;
	}


	public Vinyl getVinyl(int vinylCode) throws Exception {
		Vinyl v= new Vinyl();
		v= dataAccess.getVinyl(vinylCode);
		return v;
	}


	public void updateVynil(Vinyl vinyl) throws Exception {
		
	}


	public Artist getArtist(String name) throws Exception {
		Artist a = new Artist();
		a=dataAccess.getArtist(name);
		return a;
	}


	public Genre getGenre(String name) throws Exception {
		Genre a = new Genre();
		a=dataAccess.getGenre(name);
		return a;
	}

//	public ArrayList<Vinyl> vinyls(String time) throws Exception {
//
//
//		
//
//	}

	public ArrayList<Vinyl> getBestSellers(String time) throws Exception {
		ArrayList <Vinyl> vinyls = new ArrayList<Vinyl>();
		
		vinyls=dataAccess.getBestSellers(time);
		
		return vinyls;
	}


	public void registerClient(Client client) throws Exception {
		dataAccess.registerClient(client);

	}


	public void modifyClientData(Client client) throws Exception {
		dataAccess.modifyClientData(client);

	}


	public ArrayList<Genre> getGenres() throws Exception {
		ArrayList<Genre>genres = new ArrayList <Genre>();
		genres=dataAccess.getGenres();
		
		return genres;
	}


	public ArrayList<Artist> getArtists() throws Exception {
		ArrayList<Artist> artists = new ArrayList<Artist>();
		artists=dataAccess.getArtists();
		return artists;
	}


	public void deleteVinyl(int vinylDel) throws Exception {
		dataAccess.deleteVinyl(vinylDel);
		
	}

	@Override
	public ArrayList<Vinyl> advancedSearch(AdvancedSearch search) {
		ArrayList <Vinyl> vinyls= new ArrayList<Vinyl>();
		vinyls=dataAccess.advancedSearch(search);
		
		
		return vinyls;
	}

}
