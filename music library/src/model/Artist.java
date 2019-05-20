package model;

import gui.Artist;

public class Artist artis {
	
	private int code;
	private String name;
	
	public Artist() {
		super();
	}
	public Artist(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
}
