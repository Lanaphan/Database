package model;
public class Artist extends User{

	private String myRecordCompany;

	public Artist(String email, String password, String bday,
			String displayName, boolean isArtist, boolean isProducer,
			String companyName) {
			
		super(email, password, bday, displayName, isArtist, isProducer);
		myRecordCompany = companyName;
	}

	public String getCompanyName(){
		return myRecordCompany;
	}
	
	public void setCompanyName(String companyName) {
		myRecordCompany = companyName;
	}
	

}
