import java.util.Scanner;


public class Util {
	// some strings that are commonly user in the Reddit program
	public static final String ADMIN_NAME = "admin";
	public static final String INITIAL_LOGIN_NAME = "anon";
	public static final String NO_INPUT_FILE= "Usage: java Reddit <FileNames>";
	public static final String INVALID_COMMAND_MSG = "Invalid command!";
	
	
	// all commands for controling the Reddit program 
	public static final String SUMMARY = "s";
	public static final String DELETE = "d";
	public static final String LOGIN = "l";
	public static final String FRONT_PAGE = "f";
	public static final String SUBREDDIT_MENU = "r";
	public static final String USER_NAME_MENU = "u";
	public static final String EXIT = "x";
	public static final String LIKE = "a";
	public static final String DISLIKE = "z";
	public static final String NEXT = "j";	
	
	// A Scanner 
	public static final Scanner scnr = new Scanner (System.in);
}
