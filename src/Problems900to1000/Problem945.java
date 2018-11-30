package Problems900to1000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class Problem945 {
    /**
     * Given an array of integers A, a move consists of choosing any A[i],
     * and incrementing it by 1.
     * Return the least number of moves to make every value in A unique.
     */
    /**
     * Determine the number of increments by:
     * 1) Iterate over the elements in A
     * 2) If we have not seen the element yet, add it to unique elements set
     * 3) If we have (each non-unique element), increment it until it is unique
     * 3) return the number of increments performed
     */
    public int minIncrementForUnique(int[] A) {
        final Set<Integer> uniqueElements = new HashSet<>();
        int numIncrements = 0;
        for (int i : A) {
            while (uniqueElements.contains(i)) {
                i++;
                numIncrements++;
            }
            uniqueElements.add(i);
        }

        return numIncrements;
    }

    /**
     * Determine the number of increments by:
     * 1) Iterate over the elements in A and map from element -> # occurrences
     * 2) Iterate in sorted over the keys from the map, with a tracker for the next unique element available
     * 3) For each key with multiple occurrences:
     *     For each occurrence:
     *      Calculate the number of increments to the next available
     *       unique element and add to increment counter
     *      Increment the next unique available element tracker until
     *       it is not equal to an existing key
     * 4) Return the number of increments performed
     */
    public int minIncrementForUnique2(int[] A) {
        // quick sanity checks on A
        if (A.length == 0 || A.length == 1) {
            return 0;
        }
        else if (A.length == 2 && A[0] == A[1]) {
            return 1;
        }

        // create map from element to # of occurrences in A
        final Map<Integer,Integer> elementToOccurrences = new HashMap<>();
        for (int i : A) {
            final Integer currentValue = elementToOccurrences.get(i);
            int numOccurrences = currentValue == null ? 0 : currentValue;
            numOccurrences++;
            elementToOccurrences.put(i, numOccurrences);
        }

        // quick sanity check for already unique array
        if (A.length == elementToOccurrences.keySet().size()) {
            return 0;
        }

        // create a sorted list of the unique elements in A (keys from the map)
        final List<Integer> sortedKeys = new ArrayList<>(elementToOccurrences.keySet());
        Collections.sort(sortedKeys);

        int numIncrements = 0;
        int currentKeyIndex = 0;
        int nextAvailableElement = getNextAvailableElement(sortedKeys,0, sortedKeys.get(0));

        // iterate over the sorted keys
        while (currentKeyIndex < sortedKeys.size()) {
            final int currentKey = sortedKeys.get(currentKeyIndex);
            // update nextAvailableElement if necessary
            if (nextAvailableElement <= currentKey) {
                nextAvailableElement = getNextAvailableElement(sortedKeys, currentKeyIndex, currentKey);
            }
            int numOccurrences = elementToOccurrences.get(currentKey);
            while (numOccurrences > 1) {
                numIncrements += nextAvailableElement - currentKey;
                numOccurrences--;
                nextAvailableElement = getNextAvailableElement(sortedKeys, currentKeyIndex, nextAvailableElement);
            }
            currentKeyIndex++;
        }

        return numIncrements;
    }

    /**
     * Finds the next available element from the given start index in the input list and
     *  the most recently available element
     * Iterates from the next key after start key until it finds a key > previouslyAvailableElement + 1
     */
    private int getNextAvailableElement(List<Integer> sortedKeys, int startKeyIndex, int previouslyAvailableElement) {
        for (int i=startKeyIndex+1; i<sortedKeys.size(); i++) {
            final int nextKey = sortedKeys.get(i);
            if (nextKey > previouslyAvailableElement) {
                if (nextKey - previouslyAvailableElement > 1) {
                    return previouslyAvailableElement + 1;
                } else {
                    previouslyAvailableElement = nextKey;
                }
            }
        }
        return previouslyAvailableElement + 1;
    }

    public int minIncrementForUnique3(int[] A) {
        // quick sanity checks on A
        if (A.length == 0 || A.length == 1) {
            return 0;
        } else if (A.length == 2 && A[0] == A[1]) {
            return 1;
        }

        // find num duplicates in A
        final Map<Integer, Integer> elementToOccurrences = new HashMap<>();
        for (int i : A) {
            final Integer currentValue = elementToOccurrences.get(i);
            int numOccurrences = currentValue == null ? 0 : currentValue;
            numOccurrences++;
            elementToOccurrences.put(i, numOccurrences);
        }

        final int numDuplicates = A.length - elementToOccurrences.keySet().size();

        // quick sanity check for already unique array
        if (A.length == elementToOccurrences.keySet().size()) {
            return 0;
        }

        // create a sorted list of the unique elements in A (keys from the map)
        final List<Integer> sortedKeys = new ArrayList<>(elementToOccurrences.keySet());
        Collections.sort(sortedKeys);

        int[] availableElements = getAvailableElements(sortedKeys, numDuplicates);
        int numIncrements = 0;
        int index = 0;
        for (int i : sortedKeys) {
            int numOccurrences = elementToOccurrences.get(i);
            while (numOccurrences > 1) {
                numIncrements += availableElements[index] - i;
                index++;
                numOccurrences--;
            }
        }

        return numIncrements;
    }

    // wrong for [0,2,2] because first dupe is 2 and first available element is 1 but cant decrement
    private int[] getAvailableElements(List<Integer> sortedKeys, int numberOfDuplicates) {
        final int[] availableElements = new int[numberOfDuplicates];
        int index = 0;
        for (int i=0; i<sortedKeys.size()-1 && index < numberOfDuplicates; i++) {
            final int currentKey = sortedKeys.get(i);
            final int nextKey = sortedKeys.get(i + 1);

            for (int j = currentKey + 1; j < nextKey && index < numberOfDuplicates; j++) {
                availableElements[index] = j;
                index++;
            }
        }
        int lastKey = sortedKeys.get(sortedKeys.size()-1);
        for (int i=index; i< numberOfDuplicates; i++) {
            availableElements[i] =  lastKey + 1;
            lastKey++;
        }

        return availableElements;
    }
}
