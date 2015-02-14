
import java.util.Iterator;
import java.util.List;


public class InputHandler {

	/**
	 * Read Reddit text files 
	 * 
	 * @param file - the reddit file 
	 */

	
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


	
}
