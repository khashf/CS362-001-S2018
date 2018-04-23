package fail.example.test;

import org.junit.Test;

import fail.example.NullWordException;
import fail.example.WordTree;
import junit.framework.TestCase;

public class WordTreeTest extends TestCase{

	@Test
	public void test01(){
		try {
			WordTree testTree = new WordTree(null);
			fail("The constructor should throw null exception!");
			System.out.println("**The constructor should throw null exception**");
			
		} catch (NullWordException e) {
			System.err.println(e.toString());
		}

	}

	@Test
	public void test02(){
		try {
			WordTree testTree = new WordTree("root");
			assertNull("Left child of root", testTree.getLeftChild());
			assertNull("Right child of root", testTree.getRightChild());
			assertEquals("Data on root node", "root", testTree.getData());
		} catch (NullWordException e) {
			System.err.println(e.toString());
		}

	}

}
