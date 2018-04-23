package fail.example;

/**
 * This class is an exception raised when a null string variable is used as a
 * word
 */
public class NullWordException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;

	/**
	 * create a new NullWordException Object with given message
	 * 
	 * @param message
	 *            the message to be shown for the exception
	 */

	public NullWordException(String message) {
		this.message = message;
	}



	/**
	 * Override toString method to show the message when the exception is raised
	 * 
	 * @return the message to be shown
	 */
	@Override
	public String toString() {
		return this.message;
	}

}
