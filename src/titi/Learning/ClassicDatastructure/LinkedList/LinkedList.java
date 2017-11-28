package titi.Learning.ClassicDatastructure.LinkedList;

import java.util.ArrayList;
import java.util.List;

public class LinkedList<T> {
    private ListNode<T> head;

    public LinkedList(ListNode<T> node) {
        head = node;
    }

    public LinkedList(T[] valuesArray) {
        if ((valuesArray == null) || (valuesArray.length < 1)) {
            head = null;
        } else {
            List<ListNode<T>> nodesArray = new ArrayList<ListNode<T>>();
            for (int i = 0; i < valuesArray.length; i++) {
                nodesArray.add(i, new ListNode<T>(valuesArray[i]));
                if (i > 0) {
                    nodesArray.get(i - 1).setNext(nodesArray.get(i));
                }
            }
            head = nodesArray.get(0);
        }
    }

    public LinkedList(T[] valuesArray, int loopStartPos) {
        if ((valuesArray == null) || (valuesArray.length < 1)) {
            head = null;
        } else {
            List<ListNode<T>> nodesArray = new ArrayList<ListNode<T>>();
            for (int i = 0; i < valuesArray.length; i++) {
                nodesArray.add(i, new ListNode<T>(valuesArray[i]));
                if (i > 0) {
                    nodesArray.get(i - 1).setNext(nodesArray.get(i));
                }
            }
            nodesArray.get(valuesArray.length - 1).setNext(nodesArray.get(loopStartPos));
            head = nodesArray.get(0);
        }
    }

    public int length() {
        int length = 0;
        ListNode<T> iterator = head;
        while (iterator != null) {
            length++;
            iterator = iterator.getNext();
        }
        return length;
    }

    public void InsertAtPosition(ListNode<T> newNode, int position) throws Exception {
        if (head == null) {
            head = newNode;
            return;
        }
        int size = length();
        if ((position > (size + 1)) || (position < 1)) {
            throw new Exception("Position to insert is invalid. Valid position is 1 to " + (size + 1));
        }
        if (position == 1) {
            newNode.setNext(head);
            head = newNode;
        } else {
            ListNode<T> preNode = head;
            int count = 1;
            while (count < (position - 1)) {
                preNode = preNode.getNext();
                count++;
            }
            ListNode<T> curNode = preNode.getNext();
            newNode.setNext(curNode);
            preNode.setNext(newNode);
        }
    }

    public void DeleteAtPosition(int position) throws Exception {
        int size = length();
        if ((position < 1) || (position > size)) {
            throw new Exception("Position to insert is invalid. Valid position is 1 to " + (size));
        }
        if (position == 1) {
            ListNode<T> curNode = head.getNext();
            head = curNode;
        } else {
            ListNode<T> preNode = head;
            int count = 1;
            while (count < (position - 1)) {
                preNode = preNode.getNext();
                count++;
            }
            ListNode<T> curNode = preNode.getNext();
            preNode.setNext(curNode != null ? curNode.getNext() : null);
            curNode = null;
        }
    }

    public void DeleteList() {
        ListNode<T> tmpNode, iterator = head;
        while (iterator != null) {
            tmpNode = iterator.getNext();
            iterator = null;
            iterator = tmpNode;
        }
    }

    public ListNode<T> NthNodeFromEnd(int n) {
        ListNode<T> pTemp = head, pNthNode = null;
        if (n < 1) {
            return null;
        }
        for (int i = 1; i < n; i++) {
            if (pTemp != null) {
                pTemp = pTemp.getNext();
            }
        }
        while (pTemp != null) {
            if (pNthNode == null) {
                pNthNode = head;
            } else {
                pNthNode = pNthNode.getNext();
            }
            pTemp = pTemp.getNext();
        }
        return pNthNode;
    }

    public ListNode<T> FindBeginOfLoop() {
        ListNode<T> slowPtr = head, fastPtr = head;
        boolean loopExists = false;
        if (head == null) {
            return null;
        }
        while ((fastPtr.getNext() != null) && (fastPtr.getNext().getNext() != null)) {
            slowPtr = slowPtr.getNext();
            fastPtr = fastPtr.getNext().getNext();
            if (slowPtr == fastPtr) {
                loopExists = true;
                break;
            }
        }
        if (loopExists) {
            slowPtr = head;
            while (slowPtr != fastPtr) {
                fastPtr = fastPtr.getNext();
                slowPtr = slowPtr.getNext();
            }
            return slowPtr;
        }
        return null;
    }

    public LinkedList<T> ReverseList() {
        if (head == null) {
            return null;
        }
        ListNode<T> temp = null, next = null;
        while (head != null) {
            next = head.getNext();
            head.setNext(temp);
            temp = head;
            head = next;
        }
        return new LinkedList<T>(temp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode<T> iterator = head;
        while (iterator != null) {
            sb.append(iterator.getData().toString());
            sb.append("->");
            iterator = iterator.getNext();
        }
        sb.append("NULL");
        return sb.toString();
    }

}
