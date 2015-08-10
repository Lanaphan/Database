package model;

public class User {
	private final String myEmail;
	private final String myBday;
	private String myPassword;
	private String myDisplayName;
	
	private  boolean artist;
	private  boolean producer;
	
	public User (String email, String password, String bday, String displayName, boolean isArtist, boolean isProducer) {
		myEmail = email;
		myPassword = password;
		myBday = bday;
		myDisplayName = displayName;
		artist = isArtist;
		producer = isProducer;
	}
	
	public String getPassword(){
		return myPassword;
	}
	
	public void setPassword(String password) {
		myPassword = password;
	}
	
	public String getDisplayName() {
		return myDisplayName;
	}
	
	public void setDisplayName(String displayName) {
		myDisplayName = displayName;
	}
	
	public void inbox () {
		//think about it
	}
	
	public void outbox() {
		//think about it
	}
	
}
