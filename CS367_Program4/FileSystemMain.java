import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The central class for the FileSystem, which controls the flow of the 
 * execution. This program allows the user to make file, folders, and 
 * do some basic operations on them.    
 * @author Qihong
 */
public class FileSystemMain {

	private static Scanner scnr;
	private static Scanner inputScnr = new Scanner(System.in);
	private static File inputFile;
	private static SimpleFileSystem myFileSystem;
	private static User admin = new User("admin");
	private static ArrayList<User> users = new ArrayList<User>();
	private static SimpleFolder rootFolder;

	/**
	 * The main method for running the file system
	 * @param args
	 */
	public static void main(String[] args) {
		// input command must has length one (confirmed with TA)
		if(args.length == 1) {
			inputFile = new File(args[0]);
		} else {
			// exit if input invalid
			System.out.println("Usage: java FileSystemMain FileName");
			System.exit(0);
		}
		// add the admin first! 
		users.add(admin);
		// create file structure in accordance to the info from inputFile
		processInputFile(inputFile);
		// fire up the command interface! 
		cmdInterface();
	}	// end of the main method	

	/**
	 * This method takes the input file and initialize the fileSystem 
	 * @param inputFile - a text file contains information about the fileSys
	 */
	private static void processInputFile(File inputFile){
		// connect the inputFile to a scanner 
		try {
			scnr = new Scanner(inputFile);
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
		// read and create the root folder
		String rootName = scnr.nextLine();// 1st line always has the root name
		rootFolder = new SimpleFolder(rootName, "", null, admin);
		
		// read and create users (2nd line always has the user names)  
		String [] userNames = scnr.nextLine().split(", ");
		for(int i = 0 ; i < userNames.length; i ++){
			users.add(new User (userNames[i]));
		}

		// create the fileSystem
		myFileSystem = new SimpleFileSystem(rootFolder, users);

		// process the inputFile, create the file system
		while(scnr.hasNext()){
			String textLine = scnr.nextLine();
			// create folders and files
			readPathText(textLine);
		}
		myFileSystem.reset();
	}

	/**
	 * 
	 * @param textLine
	 */
	private static void readPathText(String textLine) {
		// TODO Auto-generated method stub
		String [] filesFolders = textLine.split("/");
		// get the last segment 
		String lastSegment = filesFolders[filesFolders.length - 1];
		// ... which contains the file or folder name that I want to create! 
		if(lastSegment.contains(".")){
			// create a file 
			makeFile(textLine);
		} else {
			// create a folder
			makeFolder(textLine);
		}
	}

	/**
	 * Takes a line of information and make a folder
	 * @param filesFolders
	 */
	private static void makeFolder(String textLine) {
		// TODO Auto-generated method stub
		String [] filesFolders = textLine.split("/");
		// get the last segment 
		String foldername = filesFolders[filesFolders.length - 1];
		// get the parent of the last segment 
		String absPath = textLine.substring(0, textLine.length() - foldername.length());

		// move to the correct location and create the directory 
		myFileSystem.moveLoc(absPath);
		myFileSystem.mkdir(foldername);
		// grant all users 'r' access
		for(int i = 1; i < users.size(); i ++){
			myFileSystem.addUser(foldername, users.get(i).getName(), 'r');
		}
		//				System.out.println("*<" + foldername + "> with path <" + absPath + ">"
		//						+ " was created in <" + myFileSystem.currLoc.getName() + ">\n");	//TODO DELETE
	}


	/**
	 * Takes a line of information and make a file 
	 * @param filesFolders
	 */
	private static void makeFile(String textLine) {
		String [] filesFolders = textLine.split("/");
		// get the last segment, which should contains file info 
		String fileInfo = filesFolders[filesFolders.length - 1];
		// get the parent of the last segment 
		String absPath = textLine.substring(0, textLine.length() - fileInfo.length());
		// separate the content from the file name
		int sep = fileInfo.indexOf(" ");
		String filenameWithExt = fileInfo.substring(0, sep);
		String content = fileInfo.substring(sep + 1);
		// move to the correct location and create the file 
		myFileSystem.moveLoc(absPath);
		myFileSystem.addFile(filenameWithExt, content);
		// grant all users 'r' access
		for(int i = 1; i < users.size(); i ++){
			myFileSystem.addUser(filenameWithExt, users.get(i).getName(), 'r');
		}
	}


	/**
	 * This method in charges of the interactive command line interface, 
	 * it handles inputs with appropriate outputs
	 */
	private static void cmdInterface() {
		while(true){
			displayCommandPrompt();
			// get user input 
			String input = inputScnr.nextLine();
			// covert the input to lower case and split it to command pieces
			String [] commands = input.toLowerCase().split(" ");
			// decide which command to execute according to the input command
			switch (commands[0]) {
			case "reset":
				// reset to admin and root location 
				if(commands.length != 1){
					System.out.println("No Argument Needed");
				} else {
					myFileSystem.reset();
					displayCommandPrompt();
					System.out.println("Reset done");
				}
				break;

			case "pwd":
				// display the current working directory 
				if(commands.length != 1){
					System.out.println("No Argument Needed");
				} else {
					System.out.println(myFileSystem.getPWD());
				}
				break;

			case "ls":
				// display what's in the current directory 
				if(commands.length != 1){
					System.out.println("No Argument Needed");
				} else {
					myFileSystem.printAll();
				}
				break;

			case "u":
				// change user 
				if(commands.length != 2){
					System.out.println("One Argument Needed");
				} else {
					// set the current user according to the input command
					if(!myFileSystem.setCurrentUser(commands[1])){
						displayCommandPrompt();
						System.out.println("user <" + commands[1] + 
								"> does not exist");
					};
				}
				break;

			case "uinfo":
				// display user info 
				if(commands.length != 1){
					System.out.println("No Argument Needed");
				} else {
					if(!myFileSystem.printUsersInfo()){
						// return message if printing was denied
						displayCommandPrompt();
						System.out.println("Insufficient privileges");
					};
				}
				break;

			case "cd":
				// change dir
				if(commands.length != 2){
					System.out.println("One Argument Needed");
				} else {
					boolean moveSuccess = myFileSystem.moveLoc(commands[1]);
					if(!moveSuccess){
						displayCommandPrompt();
						System.out.println("Invalid location passed");
					}
				}
				break;

			case "rm":	// TODO
				// remove file or folder 
				if(commands.length != 2){
					System.out.println("One Argument Needed");
				} else {
					// check w permission, if no permission // TODO
					boolean success = myFileSystem.remove(commands[1]);
					if(success){
						displayCommandPrompt();
						System.out.println(commands[1] + " removed");
					} else {
						displayCommandPrompt();
						System.out.println("Insufficient privilege");
					}
				}
				break;

			case "mkdir":
				// make a dir
				if(commands.length != 2){
					System.out.println("One Argument Needed");
				} else {
					// make the directory
					myFileSystem.mkdir(commands[1]);
					// if the currUser is not admin, add the admin//TODO 
					// or do it in the SimpleFileSystem
					
					displayCommandPrompt();
					System.out.println(commands[1] + " added");
				}
				break;

			case "mkfile":
				// make a file 
				if(commands.length < 3){ 
					System.out.println("One Argument Needed");
				} else {
					// read the filename and the content
					String filename = commands[1];
					String content = input.substring(commands[0].length() 
							+ commands[1].length() + 2);
					// add the file 
					myFileSystem.addFile(filename, content);
					// display the confirmation message 
					displayCommandPrompt();
					System.out.println(filename + " added");
				}
				break;

			case "sh":
				// grant the permission 
				if(commands.length != 4){
					System.out.println("Four Arguments Needed");
				} else if (commands[3].charAt(0)!= 'w' && 
						commands[3].charAt(0)!= 'r'){
					System.out.println("Invalid permission type");
				} else {
					// read the commands
					String filename = commands[1];
					String username =  commands[2];
					char accessType =  commands[3].charAt(0);
					// grant the permission to a user
					boolean success = myFileSystem.addUser(filename, username, accessType);
					if(success){
						displayCommandPrompt();
						System.out.println("Privilege granted");
					} else {
						System.out.println("Invalid command");
					}
				}

				break;

			case "x":
				// exit the program
				// don't check # arguments (by instruction)
				System.exit(0);
				break;

			default:
				System.out.println("Invalid command");
				break;
			} // end of the switch 
		}// end of the while 
	}// end of the cmdInterface method

	/**
	 * A helper method that displays the command prompt to the interface
	 */
	private static void displayCommandPrompt() {
		System.out.print(myFileSystem.currUser.getName() + "@CS367$ ");
	}

}// end of the class
