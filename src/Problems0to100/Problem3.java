package Problems0to100;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 */

public class Problem3 {

    /**
     * To find the longest substring without repeating chars, we will:
     *  1) iterate through the input string while tracking the chars seen so far (in a set)
     *      and the start index of the current substring
     *  2) once we encounter a char that is already in the set, then we can check if the size of
     *     the current set exceeds the longest substring found so far and update accordingly
     *  3) remove all chars from the set between the start of the current substring and this found char,
     *      add the current char to it, and move on to the next char in the string
     *  4) once we've made it through the entire string, return the longest length found
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> charsEncountered = new HashSet<>();
        int longestSubstringLength = 0, substringStartIndex = 0;
        for (int i=0 ; i<s.length(); i++) {
            final char c = s.charAt(i);
            if (charsEncountered.contains(c)) {
                if (charsEncountered.size() > longestSubstringLength) {
                    longestSubstringLength = charsEncountered.size();
                }
                int numRemoved = 0;
                char removeChar = s.charAt(substringStartIndex);
                while (numRemoved == 0 || removeChar != c) {
                    charsEncountered.remove(removeChar);
                    int nextRemoveCharIndex = substringStartIndex+numRemoved;
                    if (nextRemoveCharIndex >= s.length()) {
                        break;
                    }
                    removeChar = s.charAt(nextRemoveCharIndex);
                    numRemoved++;
                }
                substringStartIndex += numRemoved;
            }
            charsEncountered.add(c);
        }
        if (charsEncountered.size() > longestSubstringLength) {
            longestSubstringLength = charsEncountered.size();
        }

        return longestSubstringLength;
    }
}
