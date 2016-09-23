
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * @author Hsin-Jung Wang
 * @version 1.0
 * This is the main class that is used to read in the input file
 * and test and run the Burger class. 
 */
public class Main {
	/**
	 * The input file name. 
	 */
	public static final String INPUTNAME = "customer.txt";
	
	/**
	 * A variable that stores the processing order string.
	 */
	public static final String PROCESS_ORDER = "Processing Order ";
	
	/**
	 * A variable that stores the number of orders.
	 */
	private static int myOrderNum; 
	
	/**
	 * A variable that stores the number of patties in each order. 
	 */
	private static int myNumPattyCount;
	
	/**
	 * A boolean variable deciding whether to initialize 
	 * a baron burger or an empty burger. 
	 */
	private static boolean myBooleanBaronBurger;
	
	/**
	 * A list that stores the categories/ingredients
	 * for the omission or addition. 
	 */
	private static ArrayList<String> myTopping1;
	
	/**
	 * A list that stores the ingredients for
	 * the exception. 
	 */
	private static ArrayList<String> myTopping2;
	
	/**
	 * An object for the Burger class. 
	 */
	private static Burger myBurger;
	
	/**
	 * A variable that stores the patty type. 
	 */
	private static String myPattyType;

	/**
	 * This is the main. 
	 * @param args
	 */
	public static void main(String[] args) {		
		myTopping1 = new ArrayList<String>();
		myTopping2 = new ArrayList<String>();
		myBooleanBaronBurger = false; 
		myNumPattyCount = 1; 
		myPattyType = "";
		myOrderNum = 0;
		parseFile();

//		testMyStack();
//		testBurger();

	}

	/**
	 * This method parses a line of input from the file and
	 * outputs the burger. 
	 */
	public static void parseFile() {
		Scanner scanner = null;
		try { 
			scanner = new Scanner(new File(INPUTNAME));
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				
				try {
					System.out.print(PROCESS_ORDER + myOrderNum + ": ");
					System.out.println(line);
					parseLine(line);
					System.out.println("["+myBurger+"]");
					System.out.println();
					myOrderNum++;
					myTopping1 = new ArrayList<String>();
					myTopping2 = new ArrayList<String>();
				} catch (Exception theException){
					theException.printStackTrace();
				}
			}
		} catch (Exception theException){
			theException.printStackTrace();
		} finally {
			scanner.close();
		}
	}


	/**
	 * This private method checks how many patties in a given order. 
	 * @param theParts is the number of patties in string. 
	 * @return an integer value for the number of patties. 
	 */
	private static int checkPattyCountFromOrder(String theParts){
		if (theParts.equals("Double")){
			myNumPattyCount = 2;
		} else if (theParts.equals("Triple")) {
			myNumPattyCount = 3;
		} else {
			myNumPattyCount = 1; 
		}

		return myNumPattyCount;
	}

	/**
	 * This private method checks the burger type. 
	 * @param parts is a list that stores all the words of a
	 * given line. 
	 * @return a boolean indicating which burger type to choose. 
	 */
	private static  boolean checkBurgerType(String[] parts) {
		for (int i = 0; i < parts.length; i++) {
			if(parts[i].equals("Baron")) {
				myBooleanBaronBurger = true; 
				break;
			} else {
				myBooleanBaronBurger = false;
			}
		}
		return myBooleanBaronBurger;
	}


	/**
	 * This private helper method stores all the ingredients
	 * for either the omission/addition. 
	 * @param parts is a list that stores all the words of a
	 * given line. 
	 * @return an array list that stores all the ingredients
	 * for either the omission/addition. 
	 */
	private  static ArrayList<String> checkTopping1(String[] parts){
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].equals("with") 
					&& parts[i+1].equals("no") ) {

				for (int j = i+2; j < parts.length; j++) {
					
					if(parts[j].equals("but")) {
						break;
					}
					myTopping1.add(parts[j]);
				}

			} else if (parts[i].equals("with")) {
				for (int j = i+1; j < parts.length; j++) {
					
					if(parts[j].equals("but")) {
						break;
					}
					myTopping1.add(parts[j]);
				}
			}
		}
		return myTopping1; 
	}


	/**
	 * This private helper method stores all the ingredients
	 * for either the exception. 
	 * @param parts is a list that stores all the words of a
	 * given line. 
	 * @return an array list that stores all the ingredients
	 * for either the exception. 
	 */
	private  static ArrayList<String> checkTopping2(String[] parts){
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].equals("but") 
					&& parts[i+1].equals("no") ) {
				for (int j = i+2; j < parts.length; j++) {
					myTopping2.add(parts[j]);
				}
			} else if (parts[i].equals("but")) {
				for (int j = i+1; j < parts.length; j++) {
					myTopping2.add(parts[j]);
				}
			}
		}
		return myTopping2;
	}

	/**
	 * This method parses a line of input from the file 
	 * and outputs the burger. 
	 * @param theLine reads a customer order.
	 */
	public static  void parseLine(String theLine) {
		String[] parts = theLine.split(" ");
		checkPattyCountFromOrder(parts[0]);
		checkPattyType(parts);
		checkBurgerType(parts);
		checkTopping1(parts);
		checkTopping2(parts);
		
		myBurger = new Burger(myBooleanBaronBurger);
		if (myBooleanBaronBurger) {
			removeMethod(myTopping1);
			addMethod(myTopping2);
		} else {	
			addMethod(myTopping1);
			removeMethod(myTopping2);
		}
		addPattyToBurger();
	}

	/**
	 * This method adds ingredients onto the burger.
	 * @param theTopping adds the ingredients/categories
	 * to the existing burger. 
	 */
	private  static void addMethod(ArrayList<String> theTopping) {
		for (String topping: theTopping) {
			if (topping.equals("Cheese")
					|| topping.equals("Sauce")
					||topping.equals("Veggies")) {
				myBurger.addCategory(topping);
			} else {
				myBurger.addIngredient(topping);
			}		
		}
	}

	/**
	 * This method removes ingredients onto the burger.
	 * @param theTopping removes the ingredients/categories
	 * to the existing burger. 
	 */
	private static  void removeMethod(ArrayList<String> theTopping) {
		for (String topping: theTopping) {
			if (topping.equals("Cheese")
					|| topping.equals("Sauce")
					||topping.equals("Veggies")) {
				myBurger.removeCategory(topping);
			} else {
				myBurger.removeIngredient(topping);
			}		
		}
	}
	
	/**
	 * This method checks the patty type.
	 * @param parts is a list that stores all the words of a
	 * given line. 
	 * @return a string for the type of patty.
	 */
	private static String checkPattyType(String[] parts) {
		myPattyType = "";
		if(parts.length > 1) {
			if (parts[0].equals("Chicken") 
					|| parts[1].equals("Chicken")) {
				myPattyType += "Chicken";
			} else if (parts[0].equals("Veggie") 
					|| parts[1].equals("Veggie")) {
				myPattyType += "Veggie";
			} else {
				myPattyType += "Beef";
			}
		} else {
			if (parts[0].equals("Chicken")) {
				myPattyType += "Chicken";
			} else if (parts[0].equals("Veggie")) {
				myPattyType += "Veggie";
			} else {
				myPattyType += "Beef";
			}		
		}
		return myPattyType; 
	}
	
	/**
	 * This method adds patties to the burger.
	 */
	private static void addPattyToBurger() {
		for (int i = 0; i < myNumPattyCount - 1; i++) {
			myBurger.addPatty();
			myBurger.changePatties(myPattyType);
		}
	}
	
	/**
	 * This a the test method for MyStack.
	 */
	public static  void testMyStack() {
		MyStack<String> stack = new MyStack<String>();
		System.out.println("my initial stack" + stack.toString());
		System.out.println("my initial stack size ... " + stack.size());
		int count = 3;
		while(count > 0) {
			stack.push(count + "");
			System.out.println("my stack size is now : " + stack.size());
			count--;
		}

		while (count < 3) {
			stack.pop();
			System.out.println("my stack size is now : " + stack.size());
			count++;
		}
		System.out.println("my final stack" + stack.toString());
		System.out.println("my final stack size ... " + stack.size());
	}

	/**
	 * This is the test method for Burger.
	 */
	public static  void testBurger() {

		Burger myTestBurger = new Burger(false);
		System.out.println(myTestBurger);
		myTestBurger.addIngredient("Tomato");
		System.out.println(myTestBurger);
		myTestBurger.addIngredient("Cheddar");
		System.out.println(myTestBurger);
		myTestBurger.addIngredient("Lettuce");
		System.out.println(myTestBurger);
		myTestBurger.addCategory("Veggies");
		System.out.println(myTestBurger);
		myTestBurger.addPatty();
		System.out.println(myTestBurger);
		myTestBurger.changePatties("Chicken");
		myTestBurger.addPatty();
		System.out.println(myTestBurger);

		burgerHelper(true);
		burgerHelper(false);
	}

	/**
	 * This is a helper method for testing the burger.
	 * @param b is a boolean for the burger type.
	 */
	private  static void burgerHelper(boolean b) {
		Burger burger = new Burger(b);
		System.out.println("The boolean for this burger is ... " + b);
		System.out.println("Initial Burger is " + burger);
		burger.changePatties("Chicken");
		System.out.println("Change Patty to chicken..." + burger);
		burger.addPatty();
		System.out.println("Another patty is added" + burger);
		burger.addPatty();
		System.out.println("Another patty is added" + burger);
		if (!b) {
			burger.addCategory("Cheese");
			System.out.println("Add category Cheese : " + burger);
			burger.removeCategory("Cheese");
			System.out.println("Remove category Cheese" + burger);

			burger.addIngredient("Lettuce");
			System.out.println("add Lettuce ... " + burger);
			burger.addIngredient("Mayonnaise");
			System.out.println("add Mayonnaise ... " + burger);

			burger.removeIngredient("Lettuce");
			System.out.println("remove Lettuce ... " + burger);
			burger.removeIngredient("Mayonnaise");
			System.out.println("remove Mayonnaise ... " + burger);

		} else {
			burger.removeCategory("Cheese");
			System.out.println("Remove category Cheese" + burger);
			burger.addCategory("Cheese");
			System.out.println("Add category Cheese : " + burger);

			burger.removeIngredient("Lettuce");
			System.out.println("remove Lettuce ... " + burger);
			burger.removeIngredient("Mayonnaise");
			System.out.println("remove Mayonnaise ... " + burger);
			burger.addIngredient("Lettuce");
			System.out.println("add Lettuce ... " + burger);
		}
	}
}
