package titi.learning.ClassicDatastructure.Tree;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class TreeTest {
    private static List<BinaryTreeNode<Integer, String>> treeNodeList = new ArrayList();
    private static BinaryTree<Integer, String> tree;

    /*
     * 构造如下结构的二叉树: 
     * <pre>
     *		      1
     *  	2   	       3
     * 	   4	      5      9   
     *       8	   6      10   11
     *                7           12 
     * 
     */
    @BeforeClass
    public static void buildTree() {
	for (int i = 0; i <= 12; i++) {
	    treeNodeList.add(i, new BinaryTreeNode(i, String.valueOf(i)));
	}
	treeNodeList.get(1).setLeft(treeNodeList.get(2));
	treeNodeList.get(1).setRight(treeNodeList.get(3));
	treeNodeList.get(2).setLeft(treeNodeList.get(4));
	treeNodeList.get(2).setRight(treeNodeList.get(5));
	treeNodeList.get(3).setLeft(treeNodeList.get(9));
	treeNodeList.get(4).setRight(treeNodeList.get(8));
	treeNodeList.get(5).setLeft(treeNodeList.get(6));
	treeNodeList.get(6).setRight(treeNodeList.get(7));
	treeNodeList.get(9).setLeft(treeNodeList.get(10));
	treeNodeList.get(9).setRight(treeNodeList.get(11));
	treeNodeList.get(11).setRight(treeNodeList.get(12));
	tree = new BinaryTree(treeNodeList.get(1));
    }

    @Test
    public void testPreOrder() {
	System.out.println("PreOrder in Recursive way:");
	tree.PreOrder(tree.getRoot());
	System.out.println("==================");
	System.out.println("PreOrder in Non-Recursive way:");
	tree.PreOrderNonRecursive(tree.getRoot());
    }

    @Test
    public void testPostOrder() {
	System.out.println("PostOrder in Recursive way:");
	tree.PostOrder(tree.getRoot());
	System.out.println("==================");
	System.out.println("PostOrder in Non-Recursive way using double stack:");
	tree.PostOrderNonRecursive_DoubleStack(tree.getRoot());
	System.out.println("==================");
	System.out.println("PostOrder in Non-Recursive way using single stack:");
	tree.PostOrderNonRecursive_SingleStack(treeNodeList.get(2));
    }

    @Test
    public void testInOrder() {
	System.out.println("InOrder in Recursive way:");
	tree.InOrder(tree.getRoot());
	System.out.println("==================");
	System.out.println("InOrder in Non-Recursive way:");
	tree.InOrderNonRecursive(tree.getRoot());
    }

    @Test
    public void testHierarchicalOrder() {
	System.out.println("Traverse tree in Hierarchical Order:");
	tree.HierarchicalOrder(tree.getRoot());
    }

    @Test
    public void testParent() {
	Assert.assertEquals(tree.ParentNode(treeNodeList.get(6)).getKey().intValue(), 5);
	Assert.assertEquals(tree.ParentNode(treeNodeList.get(7)).getKey().intValue(), 6);
	Assert.assertEquals(tree.ParentNode(treeNodeList.get(9)).getKey().intValue(), 3);
    }

}
