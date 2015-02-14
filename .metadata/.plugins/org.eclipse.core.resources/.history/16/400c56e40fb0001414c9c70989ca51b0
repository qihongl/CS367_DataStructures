import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class InputHandler {

	/**
	 * Read Reddit text files 
	 * 
	 * @param file - the reddit file 
	 */
	public static void loadRedditFile(File file, User user) 
			throws FileNotFoundException{
		boolean isFirstLine = true; 
		Scanner input = new Scanner(file);
		while (input.hasNext()) {
			// read all lines
			String textLine = input.nextLine();
			// treat the first line as a list of subreddits 
			if (isFirstLine){
				loadSubreddits(textLine, user);
				isFirstLine = false;
			} else {
				// treat other lines as the posts
				loadPost(textLine, user);
			}
		}
		input.close();	// close the scanner
	} 
	
	private static void loadSubreddits(String subredditLine, 
			User user){
		// read all subreddits into an arraylist
		String[] temp = subredditLine.split(", ");
		for(int i = 0; i < temp.length; i ++) {
			String subredditName = temp[i].toLowerCase();
			user.subscribe(subredditName);
		}
	}
	
	private static void loadPost(String textLine, User user){
		// read the entire line
		Scanner input = new Scanner(textLine);
		// load subreddit, type, title in order
		String subreddit = input.next().split(",")[0].toLowerCase();
		String type = input.next().split(",")[0].toLowerCase();
		String title = input.nextLine();
		// add post with corresponding information 
		switch (type) {
		case "comment":
			user.addPost(subreddit, PostType.COMMENT, title);
			break;
		case "link":
			user.addPost(subreddit, PostType.LINK, title);
			break;
		case "self":
			user.addPost(subreddit, PostType.SELF, title);
			break;
		default:
			System.out.println("Invalid PostType detected");
			break;
		}
		input.close();
	}
	
	/**
	 * This method takes a array of strings and display it
	 * I wrote it for testing purposes  
	 * */
	public static void printStingArray(String [] strs){
		for (int i = 0; i < strs.length; i ++){
			System.out.print(strs[i] + " ");
		}
		System.out.print("\n");
	}
	
	
	/**
	 * This method display all user's information 
	 * I wrote it for debugging purposes
	 */
	public static void displayUserInfo(RedditDB reddit){
		System.out.println("\n------------------------------------------------");
		System.out.println("Displaying information for all users: ");
		System.out.println("------------------------------------------------");
		Iterator<User> userItr = reddit.getUsers().iterator();
		while(userItr.hasNext()){
			User temp = userItr.next();
			System.out.println("Get name: " + temp.getName());
			System.out.println("Get subs: " + temp.getSubscribed());
			System.out.println("Get posts: ");
			List<Post> posts = temp.getPosted();
			Iterator <Post> postItr = posts.iterator();
			while(postItr.hasNext()){
				Post curPost = postItr.next();
				System.out.println("\t" + curPost.getSubreddit() + "\t" + curPost.getType() 
						+ "\t" + curPost.getTitle());
			}
			System.out.println("------------------------------------------"
					+ "------");
		}
	}

	/**
	 * Test if the list has next element with iterator, and print the 
	 * corresponding information 
	 * */
	public static boolean noNextElement(Iterator<Post> itr) {
		if(itr.hasNext()){
			return false;
		} else {
			System.out.println("No posts left to display.\n"
					+ "Exiting to the main menu...");
			Util.scnr.nextLine();	// clean all input before exit 
			return true;
		} 
	}
	
}
