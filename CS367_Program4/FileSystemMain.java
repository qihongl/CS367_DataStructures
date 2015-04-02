import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Scanner;


public class FileSystemMain {

	private static Scanner scnr;
	private static Scanner inputScnr = new Scanner(System.in);
	private static File inputFile;
	private static SimpleFileSystem myFileSystem;

	public static void main(String[] args) {
		// check if the length is valid
		if(args.length == 1) {
			inputFile = new File(args[0]);
		} else {
			// TODO what to print out?
			System.out.println("Usage: java FileSystemMain FileName");
			System.exit(0);
		}

		// create file structure in accordance to the info from inputFile
		processInputFile(inputFile);
		// fire up the command interface! 
		cmdInterface();


		System.out.println("REACH THE END OF THE PROGRAM!!!");
	}	// end of the main method	



	private static void processInputFile(File inputFile){
		// connect the inputFile to a scanner 
		try {
			scnr = new Scanner(inputFile);
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}

		// read and create the root folder
		String rootName = scnr.nextLine();

		SimpleFolder rootFolder = makeRoot(rootName);

		// read and create users  
		String [] userNames = scnr.nextLine().split(", ");
		ArrayList<User> users = makeUsers(userNames);
		// add admin 
		users.add(new User("admin"));

		// create the file sys
		myFileSystem = new SimpleFileSystem(rootFolder, users);

		// process the inputFile, create the file system
		System.out.println("THE SUB-FOLDERS: ");
		while(scnr.hasNext()){
			String pathText = scnr.nextLine();

			// create folders and files
			//			System.out.println(readPathText(pathText));
			readPathText(pathText);
		}
	}



	private static void readPathText(String pathText) {
		// TODO Auto-generated method stub

		String [] filesFolders = pathText.split("/");
		for(int i = 1 ; i < filesFolders.length; i ++){
			if(filesFolders[i].contains(".")){
				// create a file 
				//				System.out.println("FILE: " + filesFolders[i]);
				makeFile(filesFolders, i);
			} else {
				// create a folder
				//				System.out.println("FOLDER: " + filesFolders[i]);
				//				makeFolder(filesFolders[i], filesFolders[i - 1]);
			}

		}// end of the for loop

	}

	private static void makeFolder(String folderName, String parentName) {
		// TODO Auto-generated method stub
		//		SimpleFolder newFolder = new SimpleFolder(folderName, path, parent, owner);

	}



	private static void makeFile(String[] filesFolders, int fileIndex) {
		// TODO Auto-generated method stub
		// reconstruct the path
		String path = filesFolders[0];
		for(int i = 1; i < fileIndex; i ++){
			path += "/" + filesFolders[i];
		}

		// separate the content from the file name 
		int sep = filesFolders[fileIndex].indexOf(" ");
		String content = filesFolders[fileIndex].substring(sep);
		String filenameWithExt = filesFolders[fileIndex].substring(0, sep);

		
		myFileSystem.addFile(filenameWithExt, content);
	}



	
	//	/**
	//	 * Takes a input text line and create files and folders recursively
	//	 * (incomplete)
	//	 * @param pathText
	//	 * @return
	//	 */
	//	private static String readPathText(String pathText) {
	//		return readPathText(pathText, pathText);
	//		// TODO Auto-generated method stub
	//
	//
	//	}
	//
	//	private static String readPathText(String pathText, String fullPath) {
	//		int slashInd = pathText.indexOf("/", 0);
	//		
	//		// if this is the last item (might need to create a file)
	//		if(slashInd == 0) {
	//			System.out.println();
	//			System.out.println("FULL: " + fullPath);
	//			System.out.println("TRIM: " + pathText);
	//			return "AFTER TRIM: " + pathText;
	//		} else {
	//			return readPathText(pathText.substring(slashInd), fullPath);
	//		}
	//	}





	/**
	 * This method takes an array of user names and create users 
	 * @param userNames
	 * @return users
	 */
	private static ArrayList<User> makeUsers(String[] userNames) {
		System.out.println("THE USERS NAMES: ");
		// loop over every user name
		ArrayList<User> users = new ArrayList<User>();
		for(int i = 0 ; i < userNames.length; i ++){
			System.out.println(userNames[i]);
			// create users
			users.add(new User (userNames[i]));
		}
		return users;
	}



	private static SimpleFolder makeRoot(String rootName) {
		System.out.print("THE ROOT FOLDER: ");
		System.out.println(rootName);
		// TODO who's the owner? what's the path? parent for the root? 

		SimpleFolder rootFolder = new SimpleFolder(rootName, rootName, 
				null, null);
		return rootFolder;
	}

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
					System.out.println("rm <name> DETECTED");
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

}
