package examples.recursion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SortingTest {

    @Test
    void testSelectionSort() {
        double[] data = {2.0, 5.3, 4.7, 1.8, -1.34};
        double[] dataSorted = {-1.34, 1.8, 2.0, 4.7, 5.3};
        Sorting.selectionSort(data);
        for (int idx = 0; idx < data.length; idx++) {
            assertEquals(dataSorted[idx], data[idx]);
        }
    }

    @Test
    void testGetLT() {
        double[] data = {2, 1, 7, 1, 4};
        double[] expected = {2, 1, 1};
        double[] actual = Sorting.getLT(data, 4);
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
        // edge case
        assertEquals(
            0, Sorting.getLT(data, 0).length
        );
    }

    @Test
    void testGetEq() {
        double[] data = {2, 1, 7, 1, 4};
        double[] expected = {1, 1};
        double[] actual = Sorting.getEq(data, 1);
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
        // edge case
        assertEquals(
            0, Sorting.getLT(data, 0).length
        );
    }

    @Test
    void testGetGT() {
        double[] data = {2, 1, 7, 1, 4};
        double[] expected = {7};
        double[] actual = Sorting.getGT(data, 4);
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
        // edge case
        assertEquals(
            0, Sorting.getGT(data, 7).length
        );
    }

    @Test
    void testQuickSort() {
        double[] data = {2.0, 5.3, 4.7, 1.8, -1.34};
        double[] dataSorted = {-1.34, 1.8, 2.0, 4.7, 5.3};
        Sorting.quickSort(data);
        for (int idx = 0; idx < data.length; idx++) {
            assertEquals(dataSorted[idx], data[idx]);
        }
    }

    @Test
    void testQuickSortDuplicates() {
        double[] data = {0, 0, 0, 0, 0, 0, 0, 0};
        double[] dataSorted = data.clone();
        Sorting.quickSort(data);
        for (int idx = 0; idx < data.length; idx++) {
            assertEquals(dataSorted[idx], data[idx]);
        }
    }

}
