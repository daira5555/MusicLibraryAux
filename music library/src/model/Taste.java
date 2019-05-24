package model;
import java.util.ArrayList;
public class Taste {
	private ArrayList<Artist> artists;
	private ArrayList<Genre> genres;
	public Taste(ArrayList<Artist> artists, ArrayList<Genre> genres) {
		super();
		this.artists = artists;
		this.genres = genres;
	}
	public Taste() {
		super();
	}
	public ArrayList<Artist> getArtists() {
		return artists;
	}
	public void setArtists(ArrayList<Artist> artists) {
		this.artists = artists;
	}
	public ArrayList<Genre> getGenres() {
		return genres;
	}
	public void setGenres(ArrayList<Genre> genres) {
		this.genres = genres;
	}
	public void addArtist(Artist artistToAdd) {
		artists.add(artistToAdd);
	}
	public void addgenre(Genre genreToAdd) {
		genres.add(genreToAdd);
	}
}
