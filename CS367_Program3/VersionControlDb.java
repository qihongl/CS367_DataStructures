import java.util.ArrayList;
import java.util.List;

/**
 * Represent the database which stores lists of all the registered users 
 * and repositories. 
 * 
 * Assumptions: 
 * 1. username is unique for each user.
 * 2. reponame is unique for each repository.
 * 
 * @author
 *
 */
public class VersionControlDb {

	/* Stores list of all users. */
	private static List<User> users = new ArrayList<User>();

	/* Stores list of all repositories. */
	private static List<Repo> repos = new ArrayList<Repo>();

	/**
	 * Finds a repository in the database.
	 * @param repoName The name of the repository to be searched.
	 * @return The Repo object if the repository is found, null otherwise.
	 * @throws IllegalArgumentException for any null arguments.
	 */
	public static Repo findRepo(String repoName) {
		
		if (repoName == null) {
			throw new IllegalArgumentException();
		}
		
		for (Repo r : repos) {
			if (r.getName().equals(repoName)) {
				return r;
			}
		}
		return null;
	}

	/**
	 * Finds a user in the database.
	 * @param userName The name of the user to be searched.
	 * @return The User object if the user is found, null otherwise.
	 * @throws IllegalArgumentException for any null arguments.
	 */
	public static User findUser(String userName) {
		
		if (userName == null) {
			throw new IllegalArgumentException();
		}
		
		for (User u : users) {
			if (u.getName().equals(userName)) {
				return u;
			}
		}
		return null;
	}

	/**
	 * Creates a new repository and adds it to the database if there exists no 
	 * repository in the database with same name.
	 * @param repoName The name of the repository to be added. 
	 * @param user The user who is creating the repository.
	 * @return The repo object if repository got successfully created and added, 
	 * null otherwise.
	 * @throws IllegalArgumentException for any null arguments.
	 */
	public static Repo addRepo(String repoName, User user) {
		
		if (repoName == null || user == null) {
			throw new IllegalArgumentException();
		}
		
		if (findRepo(repoName) == null) {
			Repo repo = new Repo(user, repoName);
			repos.add(repo);
			return repo;
		}
		
		return null;
	}

	/**
	 * Deletes an existing repository from the database. Also un-subscribes all
	 * the subscribed users.
	 * @param repo The repository to be deleted. 
	 * @throws IllegalArgumentException for any null arguments.
	 */
	public static void delRepo(Repo repo) {
		
		if (repo == null) {
			throw new IllegalArgumentException();
		}
		
		for (User u : users) {
			u.unsubscribeRepo(repo.getName());
		}
		
		repos.remove(repo);
	}

	/**
	 * Adds a new user to the database if there exists no user in the database
	 * with same name as the user being added.
	 * @param userName The name of the user to be added. 
	 * @return The user object if user got added successfully, null otherwise.
	 * @throws IllegalArgumentException for any null arguments.
	 */
	public static User addUser(String userName) {
		
		if (userName == null) {
			throw new IllegalArgumentException();
		}
		
		if (findUser(userName) == null) {
			User user = new User(userName);
			users.add(user);
			return user;
		}
		return null;
	}

	/**
	 * Deletes an existing user from the database. Also deletes any existing 
	 * repository that was created by that user.
	 * @param user The user to be deleted. 
	 * @throws IllegalArgumentException for any null arguments.
	 */
	public static void delUser(User user) {
		
		if (user == null) {
			throw new IllegalArgumentException();
		}
		
		for (String repo : user.getAllSubRepos()) {
			if (findRepo(repo).getAdmin() == user) {
				delRepo(findRepo(repo));
			}
		}
		users.remove(user);
	}

}
