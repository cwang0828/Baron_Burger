

/**
 * 
 */

/**
 * This class creates a stack. 
 * @author Hsin-Jung Wang
 * @version 1.0
 * @param <E> is an unknown type.
 *
 */
public class MyStack<E> {
	
	/**
	 * The variable stores the number of elements 
	 * in the stack. 
	 */
	private int myElementCount; 
	
	/**
	 * This is a node variable that stores the 
	 * variable type E. 
	 */
	private Node<E> myNode;
	
	/**
	 * This variable stores a string output
	 * that displays the elements in the stack. 
	 */
	private String myString;

	/**
	 * Creates an empty stack. 
	 */
	public MyStack() {
		myElementCount = 0; 
		myString = "";
		myNode = null;
	}
	
	/**
	 * Return true if the MyStack is empty.
	 */
	public boolean isEmpty() {
		return myElementCount == 0;
	}
	
	/**
	 * This method adds the item to
	 * the top of the MyStack. 
	 * @param theItem is the item that is 
	 * added to the top of the MyStack. 
	 */
	public void push(E theItem) {
		if(isEmpty()) {
			myNode = new Node<E>(theItem);
			myNode.setNext(null);
		} else {	// ! isEmpty()
			Node<E> tempNode = new Node<E>(theItem);
			tempNode.setNext(myNode);
			myNode = tempNode;
		}
		myElementCount++;
	}
	
	/**
	 * This method removes and returns the item
	 * on the top of the MyStack. 
	 * @return the item on the top of the MyStack.
	 */
	public E pop() {
		if(!isEmpty()) {
			Node<E> tempNode = myNode; 
			myNode = myNode.myNext;
			myElementCount--;
			return tempNode.getItem();
		}
		return null;
	}
	
	/**
	 * This method returns the item on 
	 * the top of the MyStack. 
	 * @return the item on the 
	 * top of the MyStack. 
	 */
	public E peek() {
		if (!isEmpty()) {
			return myNode.getItem();
		}
		return null;
	}
	
	/**
	 * THis method returns the number of items in the MyStack. 
	 * @return the number of items in the MyStack.
	 */
	public int size() {
		return myElementCount; 
	}
	
	
	
	/**
	 * This Method converts the contents of the MyStack
	 * to a string for display. 
	 */
	public String toString() {
		int curCount = 0; 
		while(curCount != myElementCount) {
			myString += myNode.getItem().toString();
			myNode = myNode.myNext;
			curCount++;
		}
		return myString;
	}
	
	/**
	 * This is a class that holds all
	 * the properties of a node. 
	 * @author  Hsin-Jung Wang
	 * @version 1.0
	 * @param <E>
	 */
	@SuppressWarnings("hiding")
	private class Node<E> {

		E myItem;
		Node<E> myNext;
		
		public Node(E theItem) {
			this.myItem = theItem; 
		}
		
		public void setNext(Node<E> theNext) {
			myNext = theNext;
		}
		
		public E getItem() {
			return myItem; 
		}
		
	}
}
