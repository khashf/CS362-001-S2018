
package edu.osu.cs362;


import org.junit.Test;
import static org.junit.Assert.*;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Random;

public class StackTest {




  //Test case number: 0

 @Test
  public void test0()  throws Throwable  {
      Stack stack0 = new Stack();
      stack0.push((-1636));
      stack0.push((-1636));
      stack0.push((-1636));
      stack0.top();
      stack0.push((-1636));
      stack0.push((-1636));
      stack0.push((-1636));
      stack0.push((-1636));
      stack0.push((-1636));
      stack0.push((-1636));
      stack0.push((-1636));
       // Undeclared exception!
      try {
        stack0.push((-1636));
      } catch(NoSuchElementException e) {

      }
  }

  //Test case number: 1

  @Test
  public void test1()  throws Throwable  {
      Stack stack0 = new Stack();
      stack0.push((-1636));
      assertEquals(-1636, stack0.top());
      
      int int0 = stack0.pop();
      assertEquals((-1636), int0);
  }

  //Test case number: 2

  @Test
  public void test2()  throws Throwable  {
      Stack stack0 = new Stack();
      // Undeclared exception!
      try {
        stack0.pop();

      
      } catch(NoSuchElementException e) {
      }
  }

  //Test case number: 3


  @Test
  public void test3()  throws Throwable  {
      Stack stack0 = new Stack();
      // Undeclared exception!
      try {
        stack0.top();
      
      } catch(NoSuchElementException e) {

      }
  }

}
