package titi.learning.ClassicDatastructure.LinkedList;

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

    //LeetCode 206
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
    
    /*LeetCode 92
    * Reverse a linked list from position m to n. Do it in-place and in one-pass.
		For example:
		Given 1->2->3->4->5->NULL, m = 2 and n = 4,
		
		return 1->4->3->2->5->NULL.
	
		Note:
		Given m, n satisfy the following condition:
		1 ≤ m ≤ n ≤ length of list.
    */
    public LinkedList<T> ReverseBetween(int m, int n) {
    	ListNode<T> iterator = head, preHead = null, targetHead = null, tail = null, postTail;
    	for(int i=1; i<m; i++) {
    		preHead = iterator;
    		iterator = iterator.getNext();
    	}
    	targetHead = iterator;
    	//Do reverse between position m and n
    	for(int j=m; j<=n; j++) {
    		tail = iterator;
    		iterator = iterator.getNext();
    	}
    	postTail = iterator;
    	//Reset iterator
    	ListNode<T> temp = targetHead;
    	iterator = targetHead.getNext();
    	for(int k=m; k<n; k++) {
    		ListNode<T> next = iterator.getNext();
    		iterator.setNext(temp);
    		temp = iterator;
    		iterator = next;
    	}
    	if(preHead != null) preHead.setNext(temp);
    	targetHead.setNext(postTail); 
    	if(m > 1) return new LinkedList<T>(head);
    	else return new LinkedList<T>(tail);
    }
    
    //逐对逆置链表
    public LinkedList<T> ReverseByPair()
    {
    	ListNode<T> newHead = null, iterator = head, next = null, nnext, preTail = null;
    	while(iterator != null && iterator.getNext() != null) {
    		next = iterator.getNext();
    		if(preTail != null) preTail.setNext(next);
    		nnext = next.getNext();
    		next.setNext(iterator);
    		iterator.setNext(nnext);
    		if(newHead == null) newHead = next;
    		preTail = iterator;
    		iterator = nnext;    		
    	}
    	return new LinkedList(newHead);
    }
    /*以每连续K个结点为块，逐块逆置块内的结点，如果不足块内结点数不足K个，则不逆置该块
     *例如:输入1 2 3 4 5 6 7 8 9 10,对于不同的K值，输出如下:
     *K=2: 2 1 4 3 6 5 8 7 10 9
     *K=3: 3 2 1 6 5 4 9 8 7 10
     *K=4: 4 3 2 1 8 7 6 5 9 10    
    */
    public LinkedList<T> ReverseBlockOfKNodes(int K){
    	ListNode<T> temp, next, cur = head, newHead, firstNodeInGroup = null, lastNodeInGroup = null;
    	if(K==0 || K==1) return this;
    	if(getKPlusOneThNode(K-1, cur) != null) {
    		newHead = getKPlusOneThNode(K-1, cur);
    	}
    	else newHead = head;
    	while(cur != null && hasKnodes(cur, K)) {
    		 int i=0;
    		 ListNode<T> preKthNode = cur;
    		 temp = getKPlusOneThNode(K, cur);
    		 while(i<K) {
    			 next = cur.getNext();
    			 cur.setNext(temp);
    			 temp = cur;
    			 cur = next;
    			 i++;
    		 }    		 
    		 lastNodeInGroup = temp;
    		 if(firstNodeInGroup != null) {
    			 firstNodeInGroup.setNext(lastNodeInGroup);
    		 }
    		 firstNodeInGroup = preKthNode;
    	}
    	return new LinkedList(newHead);
    }

    /*
    LeetCode #83: Remove duplicates from Sorted List

    Example Input: 1->1->2->3->4->4->5 Output: 1->2->3->4->5

     */
    public ListNode reserveDisctinctValues(ListNode head) {
        ListNode outCur = head;
        if(head == null || head.getNext() == null) return head;
        ListNode innerPtr = outCur.getNext();
        while(innerPtr != null) {
            while(innerPtr != null && innerPtr.getData() == outCur.getData()) {
                innerPtr = innerPtr.getNext();
            }
            outCur.setNext(innerPtr);
            outCur = innerPtr;
            if(innerPtr != null) innerPtr = innerPtr.getNext();

        }
        return head;
    }
    /*
    LeetCode #82: Remove Duplicates from Sorted List II(Reserve only nodes with numOfSameValues =1)
    Example Input: 1->1->2->3->4->4->5 Output: 2->3->5
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.getNext() == null) return head;
        ListNode prev = head, cur = prev.getNext(), next = cur.getNext();
        ListNode newHead = null, itrNode = null;
        if(cur.getData() != prev.getData()) {
            newHead = prev;
            newHead.setNext(null);
            itrNode = newHead;
        }
        while(next != null) {
            if(cur.getData() != prev.getData() && cur.getData() != next.getData()) {
                if(newHead == null) {
                    newHead = cur;
                    itrNode = newHead;
                    itrNode.setNext(null);
                }
                else {
                    if(itrNode != null) itrNode.setNext(cur);
                    itrNode = cur;
                    itrNode.setNext(null);
                }
            }
            prev = cur;
            cur = next;
            next = next.getNext();
        }
        if(cur.getData() != prev.getData()) {
            if(itrNode != null) itrNode.setNext(cur);
            else if(newHead == null) newHead = cur;
        }
        return newHead;

    }
    
    private ListNode<T> getKPlusOneThNode(int K, ListNode<T> node) {
    	ListNode<T> KthNode = node;
    	int i=0;
    	if(node == null) return node;
    	for(i=0; KthNode!=null && i<K; i++) {
    		 KthNode = KthNode.getNext();
    	}
    	if(i == K && KthNode != null) return KthNode;
    	else return null;
    }
    
    private boolean hasKnodes(ListNode<T> node, int K) {
    	int i=0;
    	for(i=0;node!=null && i<K; i++, node = node.getNext());
    	if(i==K) return true;
    	else return false;
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
