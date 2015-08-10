package model;

public class Producer extends User {
	private String myCompanyName;
	private String myContactInfo;
	public Producer(String email, String password, String bday,
			String displayName, boolean isArtist, boolean isProducer,
			String companyName, String contactInfo) {
			
		super(email, password, bday, displayName, isArtist, isProducer);
		myCompanyName = companyName;
		myContactInfo = contactInfo;
	}

	public String getCompanyName(){
		return myCompanyName;
	}
	
	public void setCompanyName(String companyName) {
		myCompanyName = companyName;
	}
	
	public String getContactInfo() {
		return myContactInfo;
	}
	
	public void setContactInfo(String contactInfo) {
		myContactInfo = contactInfo;
	}
}
