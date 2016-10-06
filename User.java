package lib;
import java.util.ArrayList;
import java.util.Date;


public class User {
	public static final int student = 1;
	public static final int curator = 2;
	public static final int admin = 3;
	public static final int curatorNo = 1;
	public static final int curatorPending = 2;
	public static final int curatorYes = 3;
	private int userID;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private genderType gender;
	private Date birthDate;
	private boolean wantToPlay;
	private String userType;
	private int curatorRequest;
	private ArrayList<Bibiliography> userBibiliography;
	
	public User(int userID, String email, String password, String firstName, String lastName, genderType enteredGender,
			Date birthDate, boolean wantToPlay, int curatorRequest) {
		super();
		this.userID = userID;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = enteredGender;
		this.birthDate = birthDate;
		this.wantToPlay = wantToPlay;
		this.userType = "Regular";
		this.curatorRequest = curatorRequest;
		this.userBibiliography = new ArrayList<Bibiliography>();
		this.userBibiliography.add(new Bibiliography());
	}
	
	public User(int userID, String email, String password, String firstName, String lastName, genderType enteredGender,
			Date birthDate, boolean wantToPlay, String userType, int curatorRequest) {
		super();
		this.userID = userID;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = enteredGender;
		this.birthDate = birthDate;
		this.wantToPlay = wantToPlay;
		this.userType = userType;
		this.curatorRequest = curatorRequest;
		this.userBibiliography = new ArrayList<Bibiliography>();
		this.userBibiliography.add(new Bibiliography());
	}
	

	public ArrayList<Bibiliography> getUserBibiliography() {
		return userBibiliography;
	}

	public void setUserBibiliography(ArrayList<Bibiliography> userBibiliography) {
		this.userBibiliography = userBibiliography;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public genderType getGender() {
		return gender;
	}

	public void setGender(genderType gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isWantToPlay() {
		return wantToPlay;
	}

	public void setWantToPlay(boolean wantToPlay) {
		this.wantToPlay = wantToPlay;
	}

	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getCuratorRequest() {
		return curatorRequest;
	}

	public void setCuratorRequest(int curatorRequest) {
		this.curatorRequest = curatorRequest;
	}
	
}
