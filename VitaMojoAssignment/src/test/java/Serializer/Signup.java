package Serializer;

public class Signup {
	private String email;
	private String password;
	private String locale;
	private String storeUUID;
	private Profile profile;
	
	public Signup() {

	}
	
	public Signup(String email, String password, String locale, String storeUUID, Profile profile) {
		this.email = email;
		this.password = password;
		this.locale = locale;
		this.storeUUID = storeUUID;
		this.profile = profile;
	}
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getStoreUUID() {
		return storeUUID;
	}
	public void setStoreUUID(String storeUUID) {
		this.storeUUID = storeUUID;
	}
	
	
}
