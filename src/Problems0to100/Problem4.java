package Problems0to100;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 */

public class Problem4 {
    /**
     * Let's first code a basic solution to this problem that works
     *  but does not satisfy the O(log(m+n)) constraint
     * It will iterate over the 2 input arrays simultaneously
     *  to merge them into 1 single sorted array with O(n+m)
     * Maintain pointers to the current "head" of each array
     * Determine which of the 2 heads to add next to the new array
     *  if the head for either array is past its length, then use the other head
     * Increment the head pointer for whichever head was used
     *
     * Now thinking about a more optimized solution, let's describe two scenarios for the input arrays:
     *  no overlap
     *  [1,2,3] 3 => 1  2
     *  [5,6,7,8,9,10] 6 => (2+3)/2  7.5
     *  [1,2,3,5,6,7,8,9,10] 9 => 4  6
     *
     *  overlap
     *  [5,6,7] 3 => 1   6
     *  [1,2,3,8,9,10] 6 => (2+3)/2  5.5
     *  [1,2,3,5,6,7,8,9,10] 9 => 4  6
     *
     *  overlap2
     *  [-1,4,5,6,7,11,15] 7 => 3   6
     *  [1,2,3,8,9,10] 6 => (2+3)/2  5.5
     *  [1,2,3,5,6,7,8,9,10] 9 => 4  6
     *
     * For non overlapping arrays, you don't even need to do any iteration and comparison.
     * You can simply calculate the index of the median of the combined array
     *  (either (m+n)/2 if m+n is odd or ((((m+n)/2)-1) + (m+n)/2) / 2 if even)
     *  Then find that index (or average of indices for even length combined array)
     *  This could be done in O(1) time
     * Overlapping arrays is where things get more complicated
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] combined = new int[nums1.length+nums2.length];
        int n1 = 0, n2 = 0;
        for (int cIndex = 0; n1 < nums1.length || n2 < nums2.length; cIndex++) {
            if (n1 == nums1.length) {
                combined[cIndex] = nums2[n2];
                n2++;
            }
            else if (n2 == nums2.length) {
                combined[cIndex] = nums1[n1];
                n1++;
            }
            else {
                int num1 = nums1[n1];
                int num2 = nums2[n2];
                if (num1 < num2) {
                    combined[cIndex] = nums1[n1];
                    n1++;
                }
                else {
                    combined[cIndex] = nums2[n2];
                    n2++;
                }
            }
        }
        if (combined.length % 2 == 1) {
            return (double) combined[combined.length / 2];
        }
        else {
            int left = combined[(combined.length / 2) -1];
            int right = combined[(combined.length / 2)];
            return (double) (left + right) / 2;
        }
    }
}
