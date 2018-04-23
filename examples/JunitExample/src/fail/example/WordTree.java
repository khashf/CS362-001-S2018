package fail.example;

/**
 * This class provide some basic operations for generating and manipulating a
 * binary tree, each node in the tree has a string as its data

 */
public class WordTree {
	private String data;
	private WordTree leftChild;
	private WordTree rightChild;

	/**
	 * Constructs a WordTree node, specify its data with the parameter word,
	 * both children of the WordTree node are null
	 * 
	 * @param word
	 *            the string value stored as the data of the node, cannot be
	 *            null
	 * @exception NullWordException
	 *                an exception raised when the parameter word is null
	 */
	public WordTree(String word) throws NullWordException {
		if (word == null) {
			throw new NullWordException("WordTree data cannot be null");
		} else {
			this.data = word;
			this.leftChild = null;
			this.rightChild = null;
		}
	}
//	public WordTree(String word) throws NullWordException {
//			this.data = word;
//			this.leftChild = null;
//			this.rightChild = null;
//
//	}
	/**
	 * fetch the data of current WordTree node
	 * 
	 * @return the data stored at current node
	 */
	public String getData() {
		return this.data;
	}
	/**
	 * fetch the left child of current WordTree node
	 * 
	 * @return the left child at current node
	 */
	public WordTree getLeftChild() {
		return this.leftChild;
	}
	/**
	 * fetch the right child of current WordTree node
	 * 
	 * @return the right child at current node
	 */
	public WordTree getRightChild() {
		return this.rightChild;
	}
}
