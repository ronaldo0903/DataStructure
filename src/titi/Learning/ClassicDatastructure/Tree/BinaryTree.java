/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package titi.Learning.ClassicDatastructure.Tree;

import java.util.ArrayDeque;

public class BinaryTree<K extends Comparable<K>, V> {
    private BinaryTreeNode<K, V> root = null;

    public BinaryTree(BinaryTreeNode<K, V> node) {
	root = node;
    }

    public boolean IsEmpty() {
	return root == null;
    }

    public int Height() {
	return height(root);
    }

    public int Size() {
	return size(root);
    }

    public void Destroy() {
	destroy(root);
    }

    public BinaryTreeNode<K, V> getRoot() {
	return root;
    }

    // 前序遍历(递归实现)
    public void PreOrder(BinaryTreeNode<K, V> node) {
	if (node != null) {
	    visit(node);
	    PreOrder(node.getLeft());
	    PreOrder(node.getRight());
	}
    }

    // 中序遍历(递归实现)
    public void InOrder(BinaryTreeNode<K, V> node) {
	if (node != null) {
	    InOrder(node.getLeft());
	    visit(node);
	    InOrder(node.getRight());
	}
    }

    // 后序遍历(递归实现)
    public void PostOrder(BinaryTreeNode<K, V> node) {
	if (node != null) {
	    PostOrder(node.getLeft());
	    PostOrder(node.getRight());
	    visit(node);
	}
    }

    // 层次遍历
    // 这里以队列的方式使用ArrayDeque
    public void HierarchicalOrder(BinaryTreeNode<K, V> node) {
	ArrayDeque<BinaryTreeNode<K, V>> nodesQueue = new ArrayDeque<BinaryTreeNode<K, V>>();
	if (node != null) {
	    nodesQueue.addLast(node);
	    while (!nodesQueue.isEmpty()) {
		BinaryTreeNode<K, V> curNode = nodesQueue.pollFirst();
		visit(curNode);
		if (curNode.getLeft() != null) {
		    nodesQueue.addLast(curNode.getLeft());
		}
		if (curNode.getRight() != null) {
		    nodesQueue.addLast(curNode.getRight());
		}
	    }
	}
    }

    // 前序遍历的非递归实现
    public void PreOrderNonRecursive(BinaryTreeNode<K, V> node) {
	ArrayDeque<BinaryTreeNode<K, V>> nodesStack = new ArrayDeque<BinaryTreeNode<K, V>>();
	while ((node != null) || !nodesStack.isEmpty()) {
	    while (node != null) {
		visit(node);
		nodesStack.push(node);
		node = node.getLeft();
	    }
	    if (!nodesStack.isEmpty()) {
		node = nodesStack.pop();
		node = node.getRight();
	    }
	}
    }

    // 中序遍历的非递归实现
    public void InOrderNonRecursive(BinaryTreeNode<K, V> node) {
	ArrayDeque<BinaryTreeNode<K, V>> nodesStack = new ArrayDeque<BinaryTreeNode<K, V>>();
	while ((node != null) || !nodesStack.isEmpty()) {
	    while (node != null) {
		nodesStack.push(node);
		node = node.getLeft();
	    }
	    if (!nodesStack.isEmpty()) {
		node = nodesStack.pop();
		visit(node);
		node = node.getRight();
	    }
	}
    }

    // 后序遍历的非递归实现(双栈实现)
    public void PostOrderNonRecursive_DoubleStack(BinaryTreeNode<K, V> node) {
	ArrayDeque<BinaryTreeNode<K, V>> helpStack = new ArrayDeque<BinaryTreeNode<K, V>>();
	ArrayDeque<BinaryTreeNode<K, V>> nodesStack = new ArrayDeque<BinaryTreeNode<K, V>>();
	while ((node != null) || !helpStack.isEmpty()) {
	    while (node != null) {
		helpStack.push(node);
		nodesStack.push(node);
		node = node.getRight();
	    }
	    if (!helpStack.isEmpty()) {
		node = helpStack.pop();
		while ((node.getLeft() == null) && !helpStack.isEmpty()) {
		    node = helpStack.pop();
		}
	    }
	    node = node.getLeft();
	}
	while (!nodesStack.isEmpty()) {
	    visit(nodesStack.pop());
	}
    }

    // 后序遍历的非递归实现(栈实现)
    public void PostOrderNonRecursive_SingleStack(BinaryTreeNode<K, V> node) {
	ArrayDeque<BinaryTreeNode<K, V>> nodesStack = new ArrayDeque<BinaryTreeNode<K, V>>();
	BinaryTreeNode<K, V> pNode = node;
	while (node != null) {
	    for (; node.getLeft() != null; node = node.getLeft()) {
		nodesStack.push(node);
	    }
	    while ((node != null) && ((node.getRight() == null) || (node.getRight() == pNode))) {
		visit(node);
		pNode = node;
		if (nodesStack.isEmpty()) {
		    return;
		}
		node = nodesStack.pop();
	    }
	    nodesStack.push(node);
	    node = node.getRight();
	}
    }

    public BinaryTreeNode<K, V> ParentNode(BinaryTreeNode<K, V> node) {
	if ((null == root) || (node == root)) {
	    return null;
	}
	return parent(root, node);
    }

    private BinaryTreeNode<K, V> parent(BinaryTreeNode<K, V> subTreeRoot, BinaryTreeNode<K, V> node) {
	if (subTreeRoot == null) {
	    return null;
	}
	if ((node == subTreeRoot.getLeft()) || (node == subTreeRoot.getRight())) {
	    return subTreeRoot;
	}
	BinaryTreeNode<K, V> pNode;
	if ((pNode = parent(subTreeRoot.getLeft(), node)) != null) {
	    return pNode;
	}
	return parent(subTreeRoot.getRight(), node);
    }

    private void destroy(BinaryTreeNode<K, V> tree) {
	if (tree != null) {
	    destroy(tree.getLeft());
	    destroy(tree.getRight());
	    tree = null;
	}
    }

    private int size(BinaryTreeNode<K, V> tree) {
	if (tree == null) {
	    return 0;
	} else {
	    return 1 + size(tree.getLeft()) + size(tree.getRight());
	}
    }

    private int height(BinaryTreeNode<K, V> tree) {
	if (tree == null) {
	    return 0;
	} else {
	    int leftHeight = height(tree.getLeft());
	    int rightHeight = height(tree.getRight());
	    return (leftHeight < rightHeight) ? (rightHeight + 1) : (leftHeight + 1);
	}
    }

    private void visit(BinaryTreeNode<K, V> node) {
	if (node != null) {
	    node.setVisited(true);
	    System.out.println("Now visiting node with key:" + node.getKey().toString() + ", value:"
		    + node.getData().toString());
	}
    }
}
