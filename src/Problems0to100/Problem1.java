package Problems0to100;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers
 *  such that they add up to a specific target.
 * You may assume that each input would have exactly one solution,
 *  and you may not use the same element twice.
 */
public class Problem1 {
    public int[] twoSum(int[] nums, int target) {
        /**
         * To calculate the indices of the two numbers that add up to target
         *  we'll iterate over all of the indices and put the numbers into a map
         *  from number to index
         * We'll then iterate over nums and for each num, look to see if
         *  there exists a complement in the map that adds up to target
         *  If so, return the index of the num and its complement
         */
        final Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            final int num = nums[i];
            numToIndex.put(num, i);
        }

        final int[] returnArr = new int[2];

        for (int i=0; i<nums.length; i++) {
            final int num = nums[i];
            final int complement = target - num;
            final Integer mapComplement = numToIndex.get(complement);
            if (mapComplement == null) {
                continue;
            }
            else {
                final int complementIndex = mapComplement;
                if (complementIndex == i) {
                    continue;
                }
                if (complementIndex > i) {
                    returnArr[0] = i;
                    returnArr[1] = complementIndex;
                }
                else {
                    returnArr[0] = complementIndex;
                    returnArr[1] = i;
                }
                break;
            }
        }
        return returnArr;
    }
}
