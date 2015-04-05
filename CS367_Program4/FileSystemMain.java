import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


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
		rootFolder = new SimpleFolder(rootName, "/" + rootName, null, admin);

		// read and create users (2nd line always has the user names)  
		String [] userNames = scnr.nextLine().split(", ");
		for(int i = 0 ; i < userNames.length; i ++)
			users.add(new User (userNames[i]));

		// create the fileSystem
		myFileSystem = new SimpleFileSystem(rootFolder, users);

		//		System.out.println(myFileSystem.currLoc);	// TODO: problematic
		//		System.out.println(myFileSystem.root);		// TODO: problematic

		// process the inputFile, create the file system
		while(scnr.hasNext()){
			String textLine = scnr.nextLine();
			// create folders and files
			readPathText(textLine);
		}
		myFileSystem.reset();
//		System.out.println(myFileSystem.root);
		myFileSystem.printAll();
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
			makeFile(filesFolders);
		} else {
			// create a folder
			makeFolder(filesFolders);
		}
	}


	/**
	 * 
	 * @param filesFolders
	 */
	private static void makeFolder(String [] filesFolders) {
		// TODO Auto-generated method stub
		// get the last segment 
		String foldername = filesFolders[filesFolders.length - 1];
		// get the parent of the last segment 
		String path = filesFolders[filesFolders.length - 2];
		// make the folder and move the location
		myFileSystem.mkdir(foldername);
		myFileSystem.moveLoc(path);
		
		System.out.println(foldername + " was created in : " + myFileSystem.currLoc.getName());	//TODO DELETE
//		System.out.println("MOVE TO NEXT CURR_LOC: " + myFileSystem.currLoc.getName()); // TODO DELETE
		System.out.println();
		
	}


	/**
	 * 
	 * @param filesFolders
	 */
	private static void makeFile(String [] filesFolders) {
		// get the last segment, which should contains file info 
		String fileInfo = filesFolders[filesFolders.length - 1];
		// get the parent of the last segment 
		String path = filesFolders[filesFolders.length - 2];
		
		// separate the content from the file name
		int sep = fileInfo.indexOf(" ");
		String filenameWithExt = fileInfo.substring(0, sep);
		String content = fileInfo.substring(sep + 1);
		// add the file 
		myFileSystem.addFile(filenameWithExt, content);
		myFileSystem.moveLoc(path);
		
		System.out.println(filenameWithExt + " was created in : " + myFileSystem.currLoc.getName());	//TODO DELETE
		
//		System.out.println("MOVE TO NEXT CURR_LOC: " + myFileSystem.currLoc.getName()); // TODO DELETE
		System.out.println();
	}


	/**
	 * This method in charges of the interactive command line interface, 
	 * it handles inputs with appropriate outputs
	 */
	private static void cmdInterface() {
		// TODO Auto-generated method stub
		// flag variable for the keep running the while loop 
		boolean execute = true;
		while(execute){
			// command prompt
			System.out.print(myFileSystem.currUser.getName() + "@CS367$ ");
			// get user input 
			String input = inputScnr.nextLine();
			// covert the input to lower case and split it to command pieces
			String [] commands = input.toLowerCase().split(" ");

			// decide which command to execute according to the input command
			switch (commands[0]) {
			case "reset":
				if(commands.length != 1){
					System.out.println("Invalid command");
				} else {
					myFileSystem.reset();
				}
				break;

			case "pwd":
				if(commands.length != 1){
					System.out.println("Invalid command");
				} else {
					System.out.println(myFileSystem.getPWD());
				}
				break;

			case "ls":
				if(commands.length != 1){
					System.out.println("Invalid command");
				} else {
					myFileSystem.printAll();
				}
				break;

			case "u":
				if(commands.length != 2){
					System.out.println("Invalid command");
				} else {
					// set the current user according to the input command
					myFileSystem.setCurrentUser(commands[1]);
				}
				break;

			case "uinfo":
				if(commands.length != 1){
					System.out.println("Invalid command");
				} else {
					myFileSystem.printUsersInfo();
				}
				break;

			case "cd":
				if(commands.length != 2){
					System.out.println("Invalid command");
				} else {
					myFileSystem.moveLoc(commands[1]);
				}
				break;

			case "rm":
				if(commands.length != 2){
					System.out.println("Invalid command");
				} else {
					System.out.println("rm <" + commands[1] + "> DETECTED");
				}
				break;

			case "mkdir":
				if(commands.length != 2){
					System.out.println("Invalid command");
				} else {
					myFileSystem.mkdir(commands[1]);
					System.out.println(commands[1] + "ADDED!");
				}

				break;

			case "mkfile":
				if(commands.length != 2){
					System.out.println("Invalid command");
				} else {
					System.out.println("mkfile <name> DETECTED");
				}
				break;

			case "sh":
				if(commands.length != 4){
					System.out.println("Invalid command");
				} else {
					System.out.println("sh <fname> <username> <accesstype> DETECTED");
				}
				break;

			case "x":
				if(commands.length != 1){
					System.out.println("Invalid command");
				} else {
					System.exit(0);
				}
				break;

			default:
				System.out.println("Invalid command");
				break;
			} // end of the switch 
		}// end of the while 
	}// end of the cmdInterface method


	/**
	 * print all users, for debugging purpose merely 
	 * @param users
	 */
	private static void PrintUsers_debugging(ArrayList<User> users) {
		// TODO Auto-generated method stub
		System.out.println("Users: ");
		for (int i = 0; i < users.size(); i ++){
			System.out.println(users.get(i));
		}
	}




}
