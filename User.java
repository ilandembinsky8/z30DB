package lib;
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
	private int userTypeID;
	private int curatorRequest;
	
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
		this.userTypeID = student;
		this.curatorRequest = curatorRequest;
	}
	public User(int userID, String email, String password, String firstName, String lastName, genderType enteredGender,
			Date birthDate, boolean wantToPlay, int userTypeID, int curatorRequest) {
		super();
		this.userID = userID;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = enteredGender;
		this.birthDate = birthDate;
		this.wantToPlay = wantToPlay;
		this.userTypeID = userTypeID;
		this.curatorRequest = curatorRequest;
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

	public int getUserTypeID() {
		return userTypeID;
	}

	public void setUserTypeID(int userTypeID) {
		this.userTypeID = userTypeID;
	}

	public int getCuratorRequest() {
		return curatorRequest;
	}

	public void setCuratorRequest(int curatorRequest) {
		this.curatorRequest = curatorRequest;
	}
	
}
