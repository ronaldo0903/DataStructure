package titi.Learning.ClassicDatastructure.LinkedList;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LinkedListTest {
    private static LinkedList<Integer> testList, testReverseList, testLoopedList;
    private static Integer[] fromArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        testList = new LinkedList<Integer>(fromArray);
        testReverseList = new LinkedList<Integer>(fromArray);
        testLoopedList = new LinkedList<Integer>(fromArray, 4);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        testList = null;
        testReverseList = null;
        testLoopedList = null;
    }

    @Test
    public void testToString() {
        Assert.assertEquals(testList.toString(), "1->2->3->4->5->6->7->8->9->NULL");
    }

    @Test
    public void testLoopInList() {
        Assert.assertTrue(testLoopedList.FindBeginOfLoop().getData() == 5);
        Assert.assertNull(testList.FindBeginOfLoop());
    }

    @Test
    public void testReverseList() {
        Assert.assertEquals(testReverseList.ReverseList().toString(), "9->8->7->6->5->4->3->2->1->NULL");
    }

    @Test
    public void testNthNodeFromEnd() {
        Assert.assertTrue(testList.NthNodeFromEnd(2).getData() == 8);
        Assert.assertNull(testList.NthNodeFromEnd(10));
        Assert.assertNull(testList.NthNodeFromEnd(0));
    }

}
