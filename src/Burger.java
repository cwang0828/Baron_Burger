/**
 * 
 */

/**
 * This class creates a burger. 
 * @author Hsin-Jung Wang
 * @version 1.0
 */
public class Burger {

	/**
	 * This is the Burger object. 
	 */
	private MyStack<Object> myBurger;
	
	/**
	 * This is a temporary burger that holds 
	 * ingredients temporarily. 
	 */
	private MyStack<Object> myTempBurger; 
	
	/**
	 * This variable stores the number of patty. 
	 */
	private int myNumPatty; 
	
	/**
	 * This is the recipe that contains all the
	 * ingredients for the full Baron burger. 
	 */
	private MyStack<Object> myRecipe;

	/**
	 * This method makes full burger. 
	 * @param burger is an object Burger. 
	 */
	private void  makeFullStack(MyStack<Object> burger){
			burger.push(new Bun());  // bottom (push in first)
			burger.push(new Ketchup());
			burger.push(new Mustard());
			burger.push(new Mushrooms());
			burger.push(new Beef());
			burger.push(new Cheddar());
			burger.push(new Mozzarella());
			burger.push(new Pepperjack());
			burger.push(new Onions());
			burger.push(new Tomato());
			burger.push(new Lettuce());
			burger.push(new Baron_Sauce());
			burger.push(new Mayonnaise());
			burger.push(new Bun());
			burger.push(new Pickle());	// Top of the burger
	}
	
	
	
	/**
	 * A constructor that initializes a Burger with 
	 * only a bun and patty if theWorks is false
	 * and a Baron Burger if theWorks is true.
	 * @param theWorks
	 */
	public Burger(boolean theWorks) {
		myBurger = new MyStack<Object>();
		myTempBurger = new MyStack<Object>();
		myNumPatty = 1; 
		myRecipe = new MyStack<Object>();
		makeFullStack(myRecipe);
		if(theWorks) {
			CreateFullBurger();
		} else {
			CreateEmptyBurger();
		}
	}

	/**
	 * Initialize a Baron Burger.
	 */
	private void CreateFullBurger() {
		makeFullStack(myBurger);
	}

	/**
	 * Initializes a Burger with only a bun and patty.
	 */
	private void CreateEmptyBurger() {
		myBurger.push(new Bun());
		myBurger.push(new Beef());
		myBurger.push(new Bun());
	}

	/**
	 * This method changes all patties in the Burger to 
	 * the patty type
	 * @param thePattyType
	 */
	public void changePatties(String thePattyType) {
		while(!myBurger.isEmpty()) {
			Object topping = myBurger.pop();
			if (topping instanceof Patties) {			
				if(thePattyType.equals("Chicken")) {
					topping = new Chicken();
				} else if (thePattyType.equals("Beef")) {
					topping = new Beef();
				} else {
					topping = new Veggie();
				}
			}
			myTempBurger.push(topping);
		}
		popBack();
	}

	/**
	 * Thus method adds one patty to the Burger up to
	 * the maximum of 3. 
	 */
	public void addPatty() {
		if(myNumPatty <= 3) {
			while(!myBurger.isEmpty()) {
				Object topping = myBurger.pop();
				 if (topping instanceof Patties || topping instanceof Cheese) {
					myTempBurger.push(new Beef());
					myTempBurger.push(topping);
					break;
				}
				myTempBurger.push(topping);
			}
			myNumPatty++;
		}
		popBack();
	}

	/**
	 * This method removes all items of the type 
	 * from the Burger. 
	 * @param theType
	 */
	public void removeCategory(String theType) {
		if(theType.equals("Cheese")) {
			removeIngredient("Cheddar");
			removeIngredient("Mozzarella");
			removeIngredient("Pepperjack");
		} else if (theType.equals("Veggies") ) {
			removeIngredient("Lettuce");
			removeIngredient("Tomato");
			removeIngredient("Onions");
			removeIngredient("Pickle");
			removeIngredient("Mushrooms");
		} else { // Sauces
			removeIngredient("Ketchup");
			removeIngredient("Mustard");
			removeIngredient("Mayonnaise");
			removeIngredient("Baron_Sauce");
		}
	}
	
	/**
	 * This method adds all items of the type 
	 * to the Burger in the proper location.
	 * @param theType
	 */
	public void addCategory(String theType) {
		if (theType.equals("Cheese") ) {
			addIngredient("Cheddar");
			addIngredient("Mozzarella");
			addIngredient("Pepperjack");
		} else if (theType.equals("Veggies")) {
			addIngredient("Lettuce");
			addIngredient("Tomato");
			addIngredient("Onions");
			addIngredient("Pickle");
			addIngredient("Mushrooms");
		} else { // Sauces
			addIngredient("Ketchup");
			addIngredient("Mustard");
			addIngredient("Mayonnaise");
			addIngredient("Baron_Sauce");
		}
	}

	
	/**
	 * This method adds the ingredient type to 
	 * the Burger in the proper location. 
	 * @param theType
	 */
	public void addIngredient(String name) {
		MyStack<Object> myTempReceipe = new MyStack<Object>();
		while(!myBurger.isEmpty() && !myRecipe.isEmpty() ) {
			Object peekTopping = myBurger.peek();
			Object receipeTopping = myRecipe.pop();
			myTempReceipe.push(receipeTopping);
			if(peekTopping.getClass().getName().replace("Burger$","" )
					.equals(receipeTopping.getClass().getName().replace("Burger$","" ))) {
				 myTempBurger.push(myBurger.pop());
			} 
			else if (receipeTopping.getClass().getName().replace("Burger$","" ).equals(name)) {
				 myTempBurger.push(receipeTopping);
				break;
			}
		}
		
		popBack();
		while(!myTempReceipe.isEmpty()) {
			myRecipe.push(myTempReceipe.pop());
		}
	}


	/**
	 * This method adds the ingredient type to
	 * the Burger in the proper location. 
	 * @param theType
	 */
	public void removeIngredient(String name) {
		while(!myBurger.isEmpty()) {
			Object topping = myBurger.pop();
			myTempBurger.push(topping);	
			if(topping.getClass().getName().replace("Burger$","" ).equals(name)) {
				myTempBurger.pop();
				break;
			}
			
		}	
		popBack();
	}
	
	/**
	 * This method converts the Burger to a string for display. 
	 */
	public String toString() {
		String result = "";
		while(!myBurger.isEmpty()) {
			Object temp = myBurger.pop();
			result += (temp.getClass().getSimpleName() + ", ");
			myTempBurger.push(temp);
		
		}
		if (!result.isEmpty()) {
			result = result.replace("Burger$", "").replace("_", " ");
			result = result.substring(0, result.length() - 2);
		}
		popBack();
		return result;
	}
	
	/**
	 *	put all the toppings back to the original burger
	 */
	private void popBack() {
		while(!myTempBurger.isEmpty()) {
			myBurger.push(myTempBurger.pop());
		}	
	}

	// This is a bun category
	class Bun {}

	// This is the patties category
	class Patties {}
	class Beef extends Patties {}
	class Chicken extends Patties {}
	class Veggie extends Patties {}

	// This is a cheese category
	class Cheese {}
	class Cheddar extends Cheese {}
	class Mozzarella extends Cheese {}
	class Pepperjack extends Cheese {}

	// This is a veggies category.
	class Veggies {}
	class Lettuce extends Veggies {}
	class Tomato extends Veggies {}
	class Onions extends Veggies {}
	class Pickle extends Veggies {}
	class Mushrooms extends Veggies {}

	// This is a sauce category. 
	class Sauces {}
	class Ketchup extends Sauces {}
	class Mustard extends Sauces {}
	class Mayonnaise extends Sauces {}
	class Baron_Sauce extends Sauces {}

}
