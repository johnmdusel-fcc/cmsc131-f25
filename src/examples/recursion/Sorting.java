package examples.recursion;

import java.util.Random;

public class Sorting {

    private static final Random rng = new Random(42);

    private static void validateInput(double[] data) {
        if (data == null) {
            throw new IllegalArgumentException(
                "Parameter data cannot be null."
            );
        }
    }

    /**
     * Sort an array in-place using selection sort.
     * @param data - Nonempty array of values to be sorted.
     * @return Sorted input array.
     */
    public static void selectionSort(double[] data) {
        validateInput(data);
        int nextUnsortedIdx = 0;
        // data behind nextUnsortedIdx is sorted
        while (nextUnsortedIdx < data.length) {
            // find index of minumum value, starting from nextUnsortedIdx
            int curIdx = nextUnsortedIdx;  // looking here
            int minIdx = nextUnsortedIdx;  // idx of running min value
            double minValue = data[nextUnsortedIdx];  // guess of min value
            while(curIdx < data.length) {
                if (data[curIdx] < minValue) { // found new min value
                    minIdx = curIdx; // update idx
                    minValue = data[curIdx]; // update running min
                }
                curIdx++;
            }
            // swap element at minIdx with element at nextUnsortedIdx
            double placeholder = data[nextUnsortedIdx];
            data[nextUnsortedIdx] = data[minIdx];
            data[minIdx] = placeholder;
            nextUnsortedIdx++;
        }
    }

    /**
     * @param data - Nonempty array.
     * @return Pivot element chosen from input array.
     */
    private static double getPivot(double[] data) {
        return data[rng.nextInt(data.length)];
    }

    /**
     * Comparison operations for filtering array elements relative to a pivot.
     */
    private enum Comparison {
        LESS_THAN,
        EQUAL_TO,
        GREATER_THAN
    }

    /**
     * Check if a value matches the comparison criterion relative to pivot.
     * @param value - Value to check.
     * @param pivot - Pivot value for comparison.
     * @param comp - Type of comparison to perform.
     * @return true if value satisfies the comparison, false otherwise.
     */
    private static boolean filter(
        double value, 
        double pivot, 
        Comparison comp
    ) {
        return switch (comp) {
            case LESS_THAN -> value < pivot;
            case EQUAL_TO -> value == pivot;
            case GREATER_THAN -> value > pivot;
        };
    }

    /**
     * Filter array elements based on comparison with pivot.
     * @param data - Array to filter.
     * @param pivot - Pivot value for comparison.
     * @param comp - Type of comparison to perform.
     * @return Array containing elements matching the comparison criterion.
     */
    private static double[] filterByPivot(double[] data, double pivot, Comparison comp) {
        // first pass: count matching elements
        int count = 0;
        for (int idx = 0; idx < data.length; idx++) {
            if (filter(data[idx], pivot, comp)) {
                count++;
            }
        }
        
        // second pass: fill result array
        double[] output = new double[count];
        int outputIdx = 0;
        for (int dataIdx = 0; dataIdx < data.length; dataIdx++) {
            if (filter(data[dataIdx], pivot, comp)) {
                output[outputIdx] = data[dataIdx];
                outputIdx++;
            }
        }
        
        return output;
    }

    public static double[] getLT(double[] data, double pivot) {
        return filterByPivot(data, pivot, Comparison.LESS_THAN);
    }

    public static double[] getEq(double[] data, double pivot) {
        return filterByPivot(data, pivot, Comparison.EQUAL_TO);
    }

    public static double[] getGT(double[] data, double pivot) {
        return filterByPivot(data, pivot, Comparison.GREATER_THAN);
    }

    /**
     * Sort an array using quicksort.
     * @param data - Nonempty array of values to be sorted.
     * @return Sorted input array.
     */
    public static void quickSort(double[] data) {
        validateInput(data);
        if (data.length <= 1) {
            // base case, no sorting required
        }
        else if (data.length == 2) {
            if (data[0] > data[1]) { // swap elements if needed
                double placeholder = data[0];
                data[0] = data[1];
                data[1] = placeholder;
            }
        } else {
            double pivot = getPivot(data);
            double[] dataLT = getLT(data, pivot);
            double[] dataEq = getEq(data, pivot);
            double[] dataGT = getGT(data, pivot);
            quickSort(dataLT);
            quickSort(dataGT);
            for (int idxData = 0; idxData < data.length; idxData++) {
                if (idxData < dataLT.length) {
                    int idxLT = idxData;
                    data[idxData] = dataLT[idxLT];
                } else if (
                    dataLT.length <= idxData
                    && idxData < dataLT.length + dataEq.length
                ) {
                    int idxEq = idxData - dataLT.length;
                    data[idxData] = dataEq[idxEq];
                }
                else { // dataLT.length + dataEq.length <= idxData
                    int idxGT = idxData - (dataLT.length + dataEq.length);
                    data[idxData] = dataGT[idxGT];
                }
            }
        }
    }

}
