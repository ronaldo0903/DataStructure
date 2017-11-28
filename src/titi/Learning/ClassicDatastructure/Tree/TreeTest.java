package titi.Learning.ClassicDatastructure.Tree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class TreeTest {
	private static List<BinaryTreeNode<Integer,String>> treeNodeList = new ArrayList();
	private static BinaryTree<Integer,String> root;
	
	/*
	 * 构造如下结构的二叉树
	 * 			  1
	 * 		2   	    3
	 	4		5     9   
	 	  8	   6    10  11
	*           7          12
	*/
	@BeforeClass
	public static void buildTree() {
		for(int i=1; i<=12; i++) {
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
		root = new BinaryTree(treeNodeList.get(1));
	}

	@Test
	public void testPreOrder() {
		
	}
	
	@Test
	public void testPostOrder() {
		
	}
	
	@Test
	public void testInOrder() {
		
	}
	
	@Test
	public void testHierarchicalOrder() {
		
	}

}
