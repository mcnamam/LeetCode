package Problems0to100;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 *
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
public class Problem2 {

    private class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    /**
     * To add the 2 numbers together, we can iterate/traverse through both lists
     *  one node at a time, add those two together, and if sum > 10 then carry that
     *  over to the next 2 nodes addition.  Proceed until we've reached the end of both lists
     *
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumHead = null, previousNode = null;
        ListNode currentL1Node = l1;
        ListNode currentL2Node = l2;
        int index = 0, carryOver = 0;
        while (currentL1Node != null || currentL2Node != null || carryOver != 0) {
            int l1Val = currentL1Node == null ? 0 : currentL1Node.val;
            int l2Val = currentL2Node == null ? 0 : currentL2Node.val;
            int sum = l1Val + l2Val + carryOver;
            if (sum >= 10) {
                carryOver = sum / 10;
            }
            else {
                carryOver = 0;
            }
            sum %= 10;
            ListNode newNode = new ListNode(sum);
            if (index == 0) {
                sumHead = newNode;
                previousNode = newNode;
            }
            else {
                previousNode.next = newNode;
                previousNode = newNode;
            }
            index++;
            currentL1Node = currentL1Node == null ? null : currentL1Node.next;
            currentL2Node = currentL2Node == null ? null : currentL2Node.next;
        }

        return sumHead;
    }


}
