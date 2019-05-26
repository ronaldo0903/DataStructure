/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package titi.learning.ClassicDatastructure.Tree;

public class BinaryTreeNode<K extends Comparable<K>, V> {
    private K key;
    private V data;
    private boolean visited = false;
    private BinaryTreeNode<K, V> left;
    private BinaryTreeNode<K, V> right;

    public BinaryTreeNode(K key, V value) {
        this(key, value, null, null);
    }

    public BinaryTreeNode(K key, V value, BinaryTreeNode<K, V> leftChild, BinaryTreeNode<K, V> rightChild) {
        this.setKey(key);
        this.setData(value);
        this.setLeft(leftChild);
        this.setRight(rightChild);
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    public BinaryTreeNode<K, V> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<K, V> right) {
        this.right = right;
    }

    public BinaryTreeNode<K, V> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<K, V> left) {
        this.left = left;
    }

    /**
     * @return Returns the visited.
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * @param visited The visited to set.
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
