package edu.osu.cs362;


import java.util.NoSuchElementException;



public class Stack {
	private static int MAX_ELEMENTS=10 ;
	private int[] values= new int[MAX_ELEMENTS];
	private int size=0;	
	public Stack() {	

	}
	
	public void push(int x) {
		if (isFull())
			throw new NoSuchElementException("Cannot add to full stack");
	else
			values[size++] = (Integer) x;//1

}
	private boolean isFull() {
		if (size >= MAX_ELEMENTS)
			return true;//5
		else
			return false;//6

	}
	public int pop() {

		if (isEmpty()) { //May imply coverage in push and resize
			throw new NoSuchElementException("Cannot pop from empty stack");
		} else {
			return values[--size];//4
		}
	}
	private boolean isEmpty() {
		if (size == 0)
			return true;//7
		else
			return false;//8
	}

	public int top() {
		if (isEmpty())
			throw new NoSuchElementException("Cannot pop from empty stack");
		else
			return values[size - 1];
	}
	
	private int getSize(){
		return size;
	}
}
