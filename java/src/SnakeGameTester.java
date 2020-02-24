import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;

/*The purpose of this test class is to test the methods findTailExhaustive()
 *and findTailRecursive(). These tests aim to test their correctness and runtime.
 */

public class SnakeGameTester {
    /*Testing for findTailExhaustive()*/

    /* Test #1:
     * This test checks checks if the method recognizes a snake of size 1, since the head and tail are two unique true cells,
     * the method should print out an error message saying that the snake is not valid.
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
     * two snakes on the board. This test is aimed to fail.
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
    }

    /* Test #3
     *This test checks if the snake length is correct in case the tail is in the first cell. Meaning that if the tail
     * is found right away, it should not return a length of 1, but continue to calculate the snakes length and return it.
     */
    @Test
    public void testFindTailExhaustive3(){
        boolean[][] c = {{true, true, true, false},
                         {false, false, true, false},
                         {false, false, true, false},
                         {false, false, true, true}};

        SnakeGame test3 = new SnakeGame(c,3, 3);
        int[] result = {0, 0, 7};
        assertArrayEquals(result, test3.findTailExhaustive());
    }

    /*Test #4
     *This test should be able to print out an error message saying that the snake is not valid due to the snake
     * not having a barrier of one cell between another true cell. This test should fail.
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
     * In this case, it should return the incorrect tail position. I expect the test to fail.
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
    @Test
    public void testFindTailRecursive1(){}

    @Test
    public void testFindTailRecursive2(){}

    @Test
    public void testFindTailRecursive3(){}

    @Test
    public void testFindTailRecursive4(){}

    @Test
    public void testFindTailRecursive5(){}




}
