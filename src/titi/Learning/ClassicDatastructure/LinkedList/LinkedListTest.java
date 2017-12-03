package titi.Learning.ClassicDatastructure.LinkedList;

import junit.framework.Assert;

import org.junit.Test;

public class LinkedListTest {
    private LinkedList<Integer> testList, testReverseList, testLoopedList;
    private static Integer[] fromArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    @Test
    public void testToString() {
        testList = new LinkedList<Integer>(fromArray);
        Assert.assertEquals(testList.toString(), "1->2->3->4->5->6->7->8->9->NULL");
    }

    @Test
    public void testLoopInList() {
        testList = new LinkedList<Integer>(fromArray);
        testLoopedList = new LinkedList<Integer>(fromArray, 4);
        Assert.assertTrue(testLoopedList.FindBeginOfLoop().getData() == 5);
        Assert.assertNull(testList.FindBeginOfLoop());
    }

    @Test
    public void testReverseList() {
        testReverseList = new LinkedList<Integer>(fromArray);
        Assert.assertEquals(testReverseList.ReverseList().toString(), "9->8->7->6->5->4->3->2->1->NULL");
    }

    @Test
    public void testNthNodeFromEnd() {
        testList = new LinkedList<Integer>(fromArray);
        Assert.assertTrue(testList.NthNodeFromEnd(2).getData() == 8);
        Assert.assertNull(testList.NthNodeFromEnd(10));
        Assert.assertNull(testList.NthNodeFromEnd(0));
    }

    @Test
    public void testInsertAndDeleteInList() throws Exception {
        testList = new LinkedList<Integer>(fromArray);
        testList.InsertAtPosition(new ListNode<Integer>(66), 7);
        Assert.assertEquals(testList.toString(), "1->2->3->4->5->6->66->7->8->9->NULL");
        testList.DeleteAtPosition(7);
        testList.DeleteAtPosition(9);
        Assert.assertEquals(testList.toString(), "1->2->3->4->5->6->7->8->NULL");
    }
    
    @Test
    public void testReverseBetween() {
    	testList = new LinkedList<Integer>(fromArray);
    	testReverseList = testList.ReverseBetween(3, 6);
    	Assert.assertEquals("1->2->6->5->4->3->7->8->9->NULL", testReverseList.toString());
    	System.out.println(testReverseList.toString());
    }
    
    @Test
    public void testReverseBetween2() {
    	testList = new LinkedList<Integer>(fromArray);
    	testReverseList = testList.ReverseBetween(1, 4);
    	Assert.assertEquals("4->3->2->1->5->6->7->8->9->NULL", testReverseList.toString());
    	System.out.println(testReverseList.toString());
    }
    
    @Test
    public void testReverseBetween3() {
    	testList = new LinkedList<Integer>(fromArray);
    	testReverseList = testList.ReverseBetween(6, 9);
    	Assert.assertEquals("1->2->3->4->5->9->8->7->6->NULL", testReverseList.toString());
    	System.out.println(testReverseList.toString());
    }
    
    @Test
    public void testReverseByPair() {
    	testList = new LinkedList<Integer>(fromArray);
    	testReverseList = testList.ReverseByPair();
    	Assert.assertEquals("2->1->4->3->6->5->8->7->9->NULL", testReverseList.toString());
    	System.out.println(testReverseList.toString());
    }
    
    @Test
    public void testReverseByBlock() {
    	testList = new LinkedList<Integer>(fromArray);
    	testReverseList = testList.ReverseBlockOfKNodes(2);
    	Assert.assertEquals("2->1->4->3->6->5->8->7->9->NULL", testReverseList.toString());
    	System.out.println(testReverseList.toString());
    }
    
    @Test
    public void testReverseByBlock2() {
    	testList = new LinkedList<Integer>(fromArray);
    	testReverseList = testList.ReverseBlockOfKNodes(3);
    	//Assert.assertEquals("3->2->1->6->5->4->9->8->7->NULL", testReverseList.toString());
    	System.out.println(testReverseList.toString());
    }
}
