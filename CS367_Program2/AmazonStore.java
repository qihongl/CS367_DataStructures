import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.Scanner;


public class AmazonStore {

	//Store record of users and products
	private static ListADT<Product> products = new DLinkedList<Product>();
	private static ListADT<User> users = new DLinkedList<User>();
	private static User currentUser = null;//current user logged in

	//scanner for console input
	public static final Scanner stdin = new Scanner(System.in);

	//main method
	public static void main(String args[]) {

		//Populate the two lists using the input files: Products.txt User1.txt User2.txt ... UserN.txt
		if (args.length < 2) {
			System.out.println("Usage: java AmazonStore [PRODUCT_FILE] [USER1_FILE] [USER2_FILE] ...");
			System.exit(0);
		}

		//load store products
		loadProducts(args[0]);

		//load users one file at a time
		for(int i = 1; i < args.length; i++)
			loadUser(args[i]);

		//User Input for login
		boolean done = false;
		while (!done) 
		{
			System.out.print("Enter username : ");
			String username = stdin.nextLine();
			System.out.print("Enter password : ");
			String passwd = stdin.nextLine();
			System.out.println(login(username, passwd)); // TODO testing
			if(login(username, passwd)!=null)
			{
				//generate random items in stock based on this user's wish list
				ListADT<Product> inStock=currentUser.generateStock();
				//show user menu
				userMenu(inStock);
			}
			else
				System.out.println("Incorrect username or password");

			System.out.println("Enter 'exit' to exit program or anything else to go back to login");
			if(stdin.nextLine().equals("exit"))
				done = true;
		}

	}

	/**
	 * Tries to login for the given credentials. Updates the currentUser if 
	 * successful login
	 * 
	 * @param username name of user
	 * @param passwd password of user
	 * @returns the currentUser 
	 */
	public static User login(String username, String passwd){

		for(int i = 0; i < users.size(); i ++){
			// loop over all users to find match
			if(users.get(i).checkLogin(username, passwd)){
				// return the user if a match is found
				System.out.println("* Found a match for login()");
				currentUser = users.get(i); 
				return users.get(i);
			}
		}
		return null;
	}

	/**
	 * Reads the specified file to create and load products into the store.
	 * Every line in the file has the format: 
	 * 				<NAME>#<CATEGORY>#<PRICE>#<RATING>
	 * Create new products based on the attributes specified in each line and 
	 * insert them into the products list
	 * Order of products list should be the same as the products in the file
	 * For any problem in reading the file print: 'Error: Cannot access file'
	 * 
	 * @param fileName name of the file to read
	 */
	public static void loadProducts(String fileName){
		Scanner scnr = null; 
		File productFile = new File(fileName);
		try {
			scnr = new Scanner(productFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ProductFile <" + fileName + "> not found");
		}
		// read all text lines, create products, and add them to the list 
		while(scnr.hasNext()){
			String productInfoLine = scnr.nextLine();
			createNewProduct(productInfoLine);
		}
	}

	/**
	 * It take a line in the productFile, which represents the information of 
	 * one product, add the product to the list.
	 * 
	 * @param productInfoLine - a line of text contains the product information 
	 */
	private static void createNewProduct(String productInfoLine){
		// declare all parameters needed for creating a new product 
		String name, category;
		int price;
		float rating;
		String [] productInfo = productInfoLine.split("#");
		// if the length of the text element is not 4
		if(productInfo.length != 4){
			System.out.println("Unexpected numElements: " + productInfoLine);
		} else {
			// create a new product 
			name = productInfo[0];
			category = productInfo[1];
			price = Integer.parseInt(productInfo[2]);
			rating = Float.parseFloat(productInfo[3]);
			// create and add the new product with corresponding information  
			Product newProduct = new Product(name, category, price, rating);
			products.add(newProduct);
		}
	}

	/**
	 * Reads the specified file to create and load a user into the store.
	 * The first line in the file has the format:<NAME>#<PASSWORD>#<CREDIT>
	 * Every other line after that is a name of a product in the user's 
	 * wishlist, format:<NAME> For any problem in reading the file 
	 * print: 'Error: Cannot access file'
	 * 
	 * @param fileName name of the file to read
	 */
	public static void loadUser(String fileName){
		// read the user info to a file
		File userFile = new File(fileName);
		Scanner scnr = null;
		try {
			// connect a scanner to the userFile
			scnr = new Scanner(userFile);
		} catch (FileNotFoundException e) {
			System.out.println("User "+ fileName +" not found");
		}


		// use the information in the first line to create an user
		String userInfo = scnr.nextLine();
		User newUser = createUser(userInfo);
		System.out.println("*First line: " + newUser.toString());

		// get all items in the wish list
		while(scnr.hasNext()){
			String wishedProductName = scnr.nextLine();
			// find a product with the corresponding name
			Product wishedProduct = findProduct(wishedProductName);
			newUser.addToWishList(wishedProduct);
			// TODO 
			//			newUser.printWishList(new PrintStream(file));
		}
		System.out.println();
	}


	/**
	 * It finds a product by the name of the product
	 * 
	 * @param productName
	 * @return the product if found, null if not found
	 */
	private static Product findProduct(String productName){
		// search from all products
		for(int i = 0; i < products.size(); i ++){
			//			System.out.println(products.get(i).getName());	// TODO test
			if(products.get(i).getName().equals(productName)){
				// return the product if find a match of name
				return products.get(i);
			}
		}
		System.out.println( "<" + productName + "> Not Found");
		return null;
	}

	/**
	 * This method takes a line of text and create a user, and add the 
	 * new user to the list. The text line should be the first line of 
	 * the user file.
	 * 
	 * @param userInfo - a line of text
	 * @return newUser - the user just created, null if the information is not 
	 * 						recognizable
	 */
	private static User createUser(String userInfo) {
		// split the text line
		String [] allInfo = userInfo.split("#");
		if(allInfo.length != 3){
			System.out.println("Error: Cannot access file");
			return null;
		} else {
			// read all information 
			String name = allInfo[0];
			String password = allInfo[1];
			int credit = Integer.parseInt(allInfo[2]);
			// create and add a new user to the users list 
			User newUser = new User(name, password, credit); 
			users.add(newUser);
			return newUser;
		}
	}

	/**
	 * See sample outputs
	 * Prints the entire store inventory formatted by category
	 * The input text file for products is already grouped by category, 
	 * use the same order as given in the text file 
	 * format:
	 * <CATEGORY1>
	 * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
	 * ...
	 * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
	 * 
	 * <CATEGORY2>
	 * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
	 * ...
	 * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
	 */
	public static void printByCategory(){
		// first, get a non-redundant list of all categories
		ArrayList<String> allCategories = new ArrayList<String>();
		// loop over all products 
		for(int i = 0; i < products.size(); i ++){
			// check product's category, if not in the category list... 
			if(!allCategories.contains(products.get(i).getCategory())){
				// ... add it to allCateogry list
				allCategories.add(products.get(i).getCategory());
			}
		}	// end of for - products
		// loop over all categories 
		System.out.println();	// formatting line
		for (int i = 0; i < allCategories.size(); i ++){
			// print the current category 
			System.out.println(allCategories.get(i) + ":");
			// find all product belongs to this category...
			for(int j = 0; j < products.size(); j ++){
				if(products.get(j).getCategory().equals(allCategories.get(i))){
					// ... and print it
					System.out.println(products.get(j).toString());
				}
			}
			System.out.println();	// formatting line
		}	// end of for - all categories
	}


	/**
	 * Interacts with the user by processing commands
	 * 
	 * @param inStock list of products that are in stock
	 */
	public static void userMenu(ListADT<Product> inStock){

		boolean done = false;
		while (!done) 
		{
			System.out.print("Enter option : ");
			String input = stdin.nextLine();

			//only do something if the user enters at least one character
			if (input.length() > 0) 
			{
				String[] commands = input.split(":");//split on colon, because names have spaces in them
				if(commands[0].length()>1)
				{
					System.out.println("Invalid Command");
					continue;
				}
				switch(commands[0].charAt(0)){
				case 'v':
					System.out.println("INPUT COMMAND \"v\" DETECTED");
					if (commands.length >= 2){
						viewHandler(commands[1], inStock);
					} else {
						System.out.println("Command length invalid");
					}
					break;

				case 's':
					System.out.println("INPUT COMMAND \"s\" DETECTED");
					if (commands.length >= 2){
						searchHandler(commands[1]);
					} else {
						System.out.println("Command length invalid");
					}
					break;

				case 'a':
					System.out.println("INPUT COMMAND \"a\" DETECTED");
					break;

				case 'r':
					System.out.println("INPUT COMMAND \"r\" DETECTED");
					break;

				case 'b':
					System.out.println("INPUT COMMAND \"b\" DETECTED");
					break;

				case 'c':
					System.out.println("INPUT COMMAND \"c\" DETECTED");
					System.out.println("$" + currentUser.getCredit());
					break;

				case 'l':
					System.out.println("INPUT COMMAND \"l\" DETECTED");
					currentUser = null;
					done = true;
					System.out.println("Logged Out");
					break;

				default:  //a command with no argument
					System.out.println("Invalid Command");
					break;
				}
			}
		}
	}	// end of userMenu method

	/**
	 * This method takes a string and search all products, and print the product
	 * that contains the string.
	 * 
	 * @param command - the name of the product you want to search 
	 */
	private static void searchHandler(String command) {
		// TODO Auto-generated method stub
		for(int i = 0; i < products.size(); i ++){
			// if find a name that contains the input command
			if(products.get(i).getName().contains(command)){
				System.out.println(products.get(i).toString());//TODO Stream
			}
		}
		
	}

	/**
	 * Display products in a way the corresponds to the input command
	 * 
	 * @param command - indicate the list that you want to see
	 * @param inStock - the list of in stock products
	 */
	private static void viewHandler(String command, ListADT<Product> inStock) {
		switch (command) {
		case "all":
			printByCategory();
			break;
		case "wishlist":
			// TODO print wish list - PrintStream
			break;
		case "instock":
			// TODO printStream 
			for(int i = 0; i < inStock.size(); i ++){
				System.out.println(inStock.get(i).toString());
			}
			break;
		default:
			System.out.println("Invalid Command");
			break;
		}
	}



}	// end of the class
