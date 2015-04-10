import java.util.ArrayList;
import java.util.Iterator;

/**
 * The SimpleFileSystem class stores the root of file system along with the 
 * list of users. It also has a variables current user and current location.
 * @author Qihong
 */
public class SimpleFileSystem {

	SimpleFolder root;
	ArrayList<User> users;
	SimpleFolder currLoc;
	User currUser;

	/**
	 * Constructor - Initializes the class variables appropriately.
	 * @param _root
	 * @param _users
	 */
	public SimpleFileSystem(SimpleFolder _root, ArrayList<User> _users) {
		if(_root == null || _users == null) 
			throw new IllegalArgumentException();
		this.root = _root;
		this.users = _users;
		setCurrentUser("admin");
	}

	/**
	 * Resets current location to root and current user to admin. 
	 */
	public void reset(){
		// set the current user to admin
		setCurrentUser("admin");
		// go back to the root dir
		currLoc = root;		
	}

	/**
	 * gets currUser.
	 * @return currUser
	 */
	public User getCurrUser() {
		return currUser;
	}

	/**
	 * Sets the current user to the user with name passed in the argument. 
	 * Also, note that when a user is set, current location points to root.
	 * @param name
	 * @return If no such user found, return false, otherwise return true.  
	 */
	public boolean setCurrentUser(String name){
		if(name == null) throw new IllegalArgumentException();
		// if a user with the input name cannot be found 
		if(containsUser(name) != null){
			// set the current user 
			currUser = containsUser(name);
			currLoc = root;
			return true;
		}
		return false;
	}

	/**
	 * checks if the user is contained in the existing users list or not.
	 * @param name
	 * @return the user object if a match is found; null otherwise 
	 */
	public User containsUser(String name){
		if(name == null) throw new IllegalArgumentException();
		// loop over all users
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()){
			User curUser = itr.next();
			// if a name match is found, return that user
			if(curUser.getName().equals(name))
				return curUser; 
		}
		// return null if user with the input name does not exist 
		return null;
	}

	/**
	 * checks whether curr location contains 
	 * any file/folder with name name = fname
	 * @param fname
	 * @return true if contains, false otherwise
	 */
	public boolean containsFileFolder(String fname){
		if(fname == null) throw new IllegalArgumentException();
		// if there is no file or folders with this name in the curreLoc...
		if(currLoc.getSubFolder(fname) == null 
				&& currLoc.getFile(fname) == null ){
			// ... implies doesn't contains
			return false;
		}
		return true;
	}

	/**
	 * Changes the current location as per the argument. 
	 * Argument can be absolute/relative path. 
	 * @param argument
	 * @return true if successful, false otherwise.
	 */
	public boolean moveLoc(String argument){ 
		if(argument == null) throw new IllegalArgumentException();
		// split the input command into a array of folder names 
		String [] pathSeq = argument.split("/");
		// memorize the initial location (restore it if move falied)
		SimpleFolder initialLoc = currLoc;
		// CASE 1: if the 1st folder is the root, it is absolute path
		if(pathSeq[0].equals(root.getName())){
			// start from the root
			currLoc = root;
			// complete all the remaining movements in the path sequence
			for(int i = 1; i < pathSeq.length; i ++){
				// if the folder is contained in the currLoc
				if(containsFileFolder(pathSeq[i])){
					// move down to that folder 
					currLoc = currLoc.getSubFolder(pathSeq[i]);
				} else {
					// otherwise it fails to move, go back to initial location
					currLoc = initialLoc;
					return false;
				}
			}
		} else {
			// CASE 2: relative path, if argument doesn't start with the root
			// read every folder info in the argument  
			for(int i = 0; i < pathSeq.length; i ++){
				// if the folder is contained in the current location 
				if (containsFileFolder(pathSeq[i])){
					// move down to that folder
					currLoc = currLoc.getSubFolder(pathSeq[i]);
					// if the input is ".."
				} else if (pathSeq[i].equals("..") && currLoc.getParent()!= null){
					// move up
					currLoc = currLoc.getParent();
				} else {
					// move is not successful, go back to initial location
					currLoc = initialLoc;
					return false;
				} // end of parsing a single path seq (if-else)
			} // end of parsing all path seq (for)
		}// end of the all moves
		return true;
	}

	/**
	 * Return the corrent working directory
	 * @return the currentlocation.path + currentlocation.name.
	 */
	public String getPWD(){
		return ((currLoc.getPath().isEmpty()?"":(currLoc.getPath()+"/"))
				+currLoc.getName());
	}

	/**
	 * deletes the folder/file identified by the 'name'
	 * @param name
	 * @return true if the removal is successful, false otherwise.
	 */
	public boolean remove(String name){
		if(name == null) throw new IllegalArgumentException();
		// a flag that indicate if the removal was successful
		boolean success = false; 
		// split the string with a dot
		String [] fname = name.split("\\.");
		if(containsFileFolder(fname[0])){
			// check the length of the resulting array
			if(fname.length == 1){
				// remove folder
				success = currLoc.getSubFolder(name).removeFolder(currUser);
			} else if (fname.length == 2){
				// remove file
				success = currLoc.getFile(fname[0]).removeFile(currUser);
			} 
		}
		return success;
	}

	/**
	 * Gives the access 'permission' of the file/folder fname to the user if 
	 * the current user is the owner of the fname.   
	 * @param fname
	 * @param username
	 * @param permission
	 * @return true if add is successful, false otherwise.
	 */
	public boolean addUser(String fname, String username, char permission){
		if(fname == null || username == null || 
				(permission != 'w' && permission != 'r')) 
			throw new IllegalArgumentException();
		// if there exist such a file or folder 
		if(containsFileFolder(fname) && containsUser(username)!= null ){
			// if the target is not a file, it must be a folder 
			if(currLoc.getFile(fname) == null){
				// if the current user is the owner
				if(currLoc.getSubFolder(fname).getOwner().equals(currUser)){
					// give the permission
					Access newAccess = new Access(containsUser(username), permission);
					currLoc.getSubFolder(fname).addAllowedUser(newAccess);
					return true;
				}
				// ... otherwise the target is a file 
			} else {
				String [] filenames = fname.split("\\.");
				
				// if the current user is the owner
				if(currLoc.getFile(filenames[0]).getOwner().equals(currUser)){
					// give the permission
					Access newAccess = new Access(containsUser(username), permission);
					currLoc.getFile(filenames[0]).addAllowedUser(newAccess);
					return true;
				}	
			}
		}// if the code reaches here simply return false
		return false;
	}

	/**
	 * Displays the user info if the current user is admin. 
	 * @return true if successful, otherwise false.
	 */
	public boolean printUsersInfo(){
		if(currUser.getName().equals("admin")){ 
			System.out.println("admin");
			System.out.println("Folders owned :");
			// print folders info 
			printFoldersInfo(root);
			System.out.println();
			// print files info
			System.out.println("Files owned :");
			printFilesInfo(root);
			System.out.println();
			// print all users
			for(int i = 1; i < users.size(); i ++){
				System.out.println(users.get(i));
			}
			return true;
		} 
		return false;
	}

	/**
	 * Print all folders infomation 
	 * @param parent
	 */
	private void printFilesInfo(SimpleFolder parent) {
		if(parent == null) throw new IllegalArgumentException();
		// print all child info recursively 
		for (SimpleFolder child : parent.getSubFolders()){
			for(SimpleFile file: child.getFiles()){
//				System.out.println("FOLDER: <" + child.getPath() + "/" 
//						+ child.getName() + ">");// TODO
				System.out.println("\t"+ file.getPath() + "/" 
						+ file.getName() + "." + file.getExtension());
			}
			printFilesInfo(child);
		}
	}

	/**
	 * Print all files information 
	 * @param parent
	 */
	private void printFoldersInfo(SimpleFolder parent) {
		if(parent == null) throw new IllegalArgumentException();
		// print all child info recursively 
		for ( SimpleFolder child : parent.getSubFolders()){
			System.out.println("\t" + child.getPath() + "/" + child.getName());
			printFoldersInfo(child);
		}
	}

	/**
	 * makes a new folder under the current folder with owner = current user.
	 * @param name
	 */
	public void mkdir(String name){
		if(name == null) throw new IllegalArgumentException();
		// if the name doesn't conflict with any files or folders
		if(!containsFileFolder(name)){
			// create and add the folder 
			SimpleFolder newfolder = new SimpleFolder(name, getPWD(), 
					currLoc, currUser);
			// grant admin permission if admin is not the creator
			if(!currUser.getName().equals("admin")){
				User admin = users.get(0);	// the first user is admin
				Access adminAccess = new Access(admin, 'w');
				newfolder.addAllowedUser(adminAccess);
			}
			// add the folder to current location and user's dir list 
			currLoc.addSubFolder(newfolder);
			currUser.addFolder(newfolder);
		}

	}


	/**
	 * Makes a new file with name=filename and content=filecontent under 
	 * the current folder with owner = current user.
	 * @param filename
	 * @param fileContent
	 */
	public void addFile(String filename, String fileContent){
		if(filename == null || fileContent == null)  
			throw new IllegalArgumentException();
		// read the information 
		String [] filenames = filename.split("\\.");
		if(filenames.length == 2){
			String fname = filenames[0];
			Extension extension = Extension.valueOf(filenames[1]);

			// create and add the file  
			SimpleFile newFile = new SimpleFile(fname, extension, getPWD(), 
					fileContent, currLoc, currUser);
			currLoc.addFile(newFile);
			currUser.addFile(newFile);
		}
//		System.out.println("**<" + filename + "> was created "
//				+ "in <" + currLoc.getName() + ">\n");	//TODO DELETE
		
	}


	/**
	 * prints all the folders and files under the current user for which user 
	 * has access.
	 */
	public void printAll(){
		for(SimpleFile f : currLoc.getFiles()){
			if(f.containsAllowedUser(currUser.getName()))
			{
				System.out.print(f.getName() + "." + f.getExtension().toString() + " : " + f.getOwner().getName() + " : ");
				for(int i =0; i<f.getAllowedUsers().size(); i++){
					Access a = f.getAllowedUsers().get(i);
					System.out.print("("+a.getUser().getName() + "," + a.getAccessType() + ")");
					if(i<f.getAllowedUsers().size()-1){
						System.out.print(",");
					}
				}
				System.out.println();
			}
		}
		for(SimpleFolder f: currLoc.getSubFolders()){
			if(f.containsAllowedUser(currUser.getName()))
			{
				System.out.print(f.getName() + " : " + f.getOwner().getName() + " : ");
				for(int i =0; i<f.getAllowedUsers().size(); i++){
					Access a = f.getAllowedUsers().get(i);
					System.out.print("("+a.getUser().getName() + "," + a.getAccessType() + ")");
					if(i<f.getAllowedUsers().size()-1){
						System.out.print(",");
					}
				}
				System.out.println();
			}
		}
	}

}
