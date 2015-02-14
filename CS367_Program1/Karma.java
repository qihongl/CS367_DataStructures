///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 1 - Reddit
// Files:            karma.java
// Semester:         CS367 Spring 2015
//
// Author:           Qihong Lu
// Email:            qlu36@wisc.edu
// CS Login:         qihong
// Lecturer's Name:  Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This Karma class represents the points accrued by a single user.
 * linkKarma : LINK PostTypes
 * commentKarma: COMMENT PostTypes
 * 
 * SELF posts do not affect the Karma of the creating user.   
 */
public class Karma {
	private int linkKarma;
	private int commentKarma;

	/**
	 * No-arg constructor for Karma, initializes linkKarma and commentKarma 
	 */
	public Karma() {
		this.linkKarma = 0;
		this.commentKarma = 0;
	}

	/**
	 * This method Increases the karma of this type by two for the 
	 * current instance.
	 *
	 * @param type - the type of the post    
	 */
	public void upvote(PostType type) {
		if (type.equals(PostType.COMMENT)){
			commentKarma += 2;
		} else if (type.equals(PostType.LINK)){
			linkKarma += 2;
		} 
			
	}

	/**
	 * This method Decreases the karma of this type by one for the 
	 * current instance.
	 *
	 * @param type - the type of the post    
	 */
	public void downvote(PostType type) {
		if (type.equals(PostType.COMMENT)){
			commentKarma --;
		} else if (type.equals(PostType.LINK)){
			linkKarma --;
		} 
	}

	/**
	 * Returns the linkKarma associated with the current instance.
	 *
	 * @param linkKarma   
	 */
	public int getLinkKarma() {
		return this.linkKarma;
	}

	/**
	 * Returns the commentKarma associated with the current instance.
	 *
	 * @param commentKarma  
	 */
	public int getCommentKarma() {
		return this.commentKarma;
	}
}
