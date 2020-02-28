import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;

/*The purpose of this test class is to test the methods findTailExhaustive()
 *and findTailRecursive(). These tests aim to test their correctness and runtime.
 */

public class SnakeGameTester {
    /*Testing for findTailExhaustive()*/

    /* Test #1:
     * This test checks checks if the method recognizes a snake of size 1, since the head and tail are two unique true cells,
     * the method should print out an error message saying that the snake is not valid. This is being tested to check that the method
     * can correctly identify that head and tail are two separate cells.
     *  The test passed.
     */
    @Test
    public void testFindTailExhaustive1(){
        boolean[][] a = {{false, true, false},{false, false, false},{false, false, false}};
        SnakeGame test1 = new SnakeGame(a, 0, 1);
        /*expected array returned*/
        int[] result = {0, 1, 1};
        assertArrayEquals(result, test1.findTailExhaustive());

    }

    /* Test #2
     * This test aims to see if the method correctly returns the correct snake tail position and length IF there are
     * two snakes on the board. This test is aimed to fail. I used this test case to break the method.
     * The test is aimed to fail.
     * The test failed.
     */
    @Test
    public void testFindTailExhaustive2(){
        boolean[][] b = {{false, false, false, false, false, false},
                         {false, false, true, false, false, true},
                         {false, false, true, true, false, true},
                         {false, false, false, true, false, true},
                         {false, false, false, true, false, false},
                         {false, false, false, false, false, false}};

        SnakeGame test2 = new SnakeGame(b, 1,2);
        int[] result = {4, 3, 5};
        assertArrayEquals(result, test2.findTailExhaustive());
        System.out.println(test2.getExhaustiveChecks());
    }

    /* Test #3
     *This test checks if the snake length is correct in case the tail is in the first cell. Meaning that if the tail
     * is found right away, it should not return a length of 1, but continue to calculate the snakes length and return it.
     * I believe that this test case should be necessary when the tail is the first cell we check.
     * The test passed.
     */
    @Test
    public void testFindTailExhaustive3(){
        boolean[][] c = {{true, true, true, false},
                         {false, false, true, false},
                         {false, false, true, false},
                         {false, false, true, true}};

        SnakeGame test3 = new SnakeGame(c,3, 3);
        int[] result = {0, 0, 7};
        System.out.println(test3.getExhaustiveChecks());
        assertArrayEquals(result, test3.findTailExhaustive());
    }

    /*Test #4
     *This test should be able to print out an error message saying that the snake is not valid due to the snake
     * not having a barrier of one cell between another true cell. This test should fail.
     * The test passed(it shouldn't) but this is due to not checking the diagonal neighbors, however if we did add them
     * it would only work for snakes that are straight, zig zag snakes would get caught as part of the "barrier".
     */
    @Test
    public void testFindTailExhaustive4(){
        boolean[][] d = {{false, true, true},
                         {false, true, true},
                         {false, false, false}};

        SnakeGame test4 = new SnakeGame(d, 0,2);
        int[] result = {0,0,4};
        assertArrayEquals(result, test4.findTailExhaustive());
        System.out.print("There is no barrier.");
    }

    /*Test #5
     * If the snakes head position is negative, then the method won't be able to differentiate the head and tail.
     * In this case, it should return the incorrect tail position.
     * The test passed.
     */
    @Test
    public void testFindTailExhaustive5(){
        boolean[][] e = {{false,false,false},
                         {false, false, true},
                         {false, true, true},
                         {false, true, false},
                         {false, true, false}};

        SnakeGame test5 = new SnakeGame(e,-1,-2);
        /*Actual head position is [1,2]*/
        int[] result = {4,1,5};
        assertArrayEquals(result,test5.findTailExhaustive());
        System.out.println("Test can't find tail.");


    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /*Testing for findTailRecursive()*/

    /*Test #1
     * This test is a regular test case for a snake of length 3. It should return the array tailFound with the tail position and
     * length. I'm doing this case as a general test to make sure that it works on a basic snake. The test passed.
     */
    @Test
    public void testFindTailRecursive1(){
        boolean[][] a = {{true, false, false},
                        {true, true, false},
                        {false, false, false}};
        SnakeGame test1 = new SnakeGame(a,0,0 );
        int[] result = {1, 1, 3};
        assertArrayEquals(result,test1.findTailRecursive());
    }



    /*Test #2
     * While this test is the same as Test #1 from the findTailExhaustive() method, I believe checking the correctness
     * for a snake of size 1 is important for the general rules of the snake game. I expect this test to fail, mainly
     * due to the method not being able to distinguish the amount of neighbors.
     * The test ended up passing, so I modified the code to print that the snake is not valid if the amount of recursive
     * checks is one.
     */
    @Test
    public void testFindTailRecursive2(){
        boolean[][] a = {{false, true, false},{false, false, false},{false, false, false}};
        SnakeGame test2 = new SnakeGame(a, 0, 1);
        /*expected array returned*/
        int[] result = {0, 1, 1};
        assertArrayEquals(result, test2.findTailRecursive());
    }

    /*Test #3
     * This test aims to see if the method can find the tail if the snakes is windy and of a considerable size.
     * It is more aimed to test the etch cases in the recursive method and see if it is behaving properly. I
     * expect the test to pass.
     * The test failed, this could be due to the size of the snake. After adding an if statement that makes sure the bottom
     * square isn't the previous(It was unexpected)- it passed and correctly found the tail and length.
     * NOTE: I actually was messing around with the if cases and somehow it worked.
     */
    @Test
    public void testFindTailRecursive3(){
        boolean[][] b = {{false, false, true, false, false, false, false, true},
                         {false, true, true, false, false, false, false, true},
                         {true, true, false, false, true, true, false, true},
                         {true, false, true, true, true, false, true, true},
                         {true, false, true, false, true, false, true, false},
                         {true, false, true, false, true, false, true, false},
                         {true, true, true, false, true, true, true, false},
                         {false, false, false, false, false, false, false, false}};
        SnakeGame test3 = new SnakeGame(b, 0, 2);
        int[] result = {0, 7, 28};
        assertArrayEquals(result, test3.findTailRecursive());
    }

    /*Test #4
     * This test aims to see if it will find the length of the snake if the snake is in the shape of a parabola.
     * The snake is similar to the snake in our lab's README, wrapping around the board. I expect the test to pass.
     * The test passed.
     */
    @Test
    public void testFindTailRecursive4(){
        boolean[][] c = {{true, false, true},
                         {true, false, true},
                         {true, false, true},
                         {true, true, true}};
        SnakeGame test4 = new SnakeGame(c, 0,2);
        int[] result = {0, 0, 9};
        assertArrayEquals(result, test4.findTailRecursive());
    }

    /*Test #5
     * Originally in the README for this lab, it said that the snake will always have a barrier of size 1, or else the snake isn't
     * valid, and crashes into itself. So, similar to my testcase #4 for my findTailExhaustive() I'm testing an invalid snake
     * that has no barrier. I expect that the test will pass( the intent is to fail actually) but it should print a statement
     * saying that the snake is not valid because there is no barrier.
     * The test failed, so we can see that for the findTailRecursive() it cannot do a proper search if there is no barrier, unlike
     * findTailExhaustive(), which ignores that there is no barrier and still looks for the tail like normal.
     *
     */
    @Test
    public void testFindTailRecursive5(){
        boolean[][] d = {{true, true, true},{true, true, true},{true, true, true}};
        SnakeGame test5 = new SnakeGame(d, 0,2);
        int[] result = {0,0,9};
        assertArrayEquals(result, test5.findTailRecursive());
        System.out.print("There is no barrier.");
    }




}
