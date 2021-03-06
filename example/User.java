package example;

public class User {
	
	private String bUserID;
	private String bPassword;
	private String sUserID;
	private String sPassword;
	private int portNum;

	/**
	 * Constructor Creates a User Object and populates all the Fields
	 * User's data cannot be manipulated from the outside by goblins
	 * @param bUserID
	 * @param bPassword
	 * @param sUserID
	 * @param sPassword
	 * @param portNum
	 */
	public User(String bUserID, String bPassword, String sUserID, String sPassword, int portNum){
		this.bUserID = bUserID;
		this.bPassword = bPassword;
		this.sUserID = sUserID;
		this.sPassword = sPassword;
		this.portNum = portNum;
	}
	
	public String getbUserID() {
		return bUserID;
	}

	public String getbPassword() {
		return bPassword;
	}


	public String getsUserID() {
		return sUserID;
	}


	public String getsPassword() {
		return sPassword;
	}


	public int getPortNum() {
		return portNum;
	}
	
	

}
