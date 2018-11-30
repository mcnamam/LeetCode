package Problems0to100;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 */
public class Problem19 {
    /**
     * This method removes the nth node from the end of a given linked list
     *  (last node is 1 node from end)
     * It first iterates over the entirety of the list to determine its length
     * Then it iterates to the (length - n - 1)th node to pull out the next node (nth node)
     *  from the list.
     * It takes O(n) = 2n time
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // determine the length of the given LinkedList
        int len = 0;
        ListNode curNode = head;
        while (curNode != null) {
            len++;
            curNode = curNode.next;
        }

        // edge case for removing the head
        if (len == n) {
            return head.next;
        }

        // move to the (length-n-1)th node in the list
        curNode = head;
        for (int i=0; i<len-n-1; i++) {
            curNode = curNode.next;
        }

        // remove the next node and update the current node
        ListNode nodeToRemove = curNode.next;
        curNode.next = nodeToRemove == null ? null : nodeToRemove.next;

        // return the head node of the list
        return head;
    }

    /**
     * Definition for singly-linked list (given in problem)
     */
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}