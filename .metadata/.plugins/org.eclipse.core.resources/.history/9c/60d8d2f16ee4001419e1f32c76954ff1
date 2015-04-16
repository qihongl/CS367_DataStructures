//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  FileSystemMain.java
// File:             Access.java
// Semester:         CS367 Spring 2015
//
// Author:           Qihong Lu
// Email:            qlu36@wisc.edu
// CS Login:         qihong
// Lecturer's Name:  Jim Skrentny
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION //////////////////
//
// Pair Partner:     Qianyun Ma
// Email:            qma27@wisc.edu
// CS Login:         qianyun
// Lecturer's Name:  Jim Skrentny
//
//////////////////////////// 80 columns wide /////////////////////////////////

/**
 * The Access class represents an access (user with access - r/w) having a 
 * user (as a User) and accessType (as a char).
 * @author Qihong
 */
public class Access {
	
	private User user;
	private char accessType;
	
	/**
	 * Constructor
	 * @param user
	 * @param accessType
	 */
	public Access(User user, char accessType) {
		this.user = user;
		this.accessType = accessType;
	}

	/**
	 * Accessor for the user object
	 * @return user 
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * Get the type of the access. It can be 'r' or 'w'
	 * @return
	 */
	public char getAccessType() {
		return this.accessType;
	}

	/**
	 * Mutator for the access type 
	 * @param accessType
	 */
	public void setAccessType(char accessType) {
		// input validation 
		if(accessType != 'r' || accessType != 'w')
			throw new IllegalArgumentException();
		this.accessType = accessType;
	}
	
	/**
	 * Return a string representation of an access 
	 */
	@Override
	public String toString() {
		return (user.getName() + ":" + accessType);
	}
	
}
