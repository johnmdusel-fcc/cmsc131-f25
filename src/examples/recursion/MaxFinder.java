package examples.recursion;

public class MaxFinder {

    /**
     * This method does data validation.
     * @param data Non-empty array of doubles.
     * @return Maximum value from input array.
     */
    private static void validateInput(double[] data) {
        if (data.length == 0) {
            throw new IllegalArgumentException(
                "Parameter data cannot be empty."
            );
        }
    }

    public static double maxIterative(double[] data) {
        validateInput(data);
        double max = Double.NEGATIVE_INFINITY;
        for (int idx = 0; idx < data.length; idx++) {
            if (data[idx] > max) {
                max = data[idx];
            }
        }
        return max;
    }

    public static double maxRecursive(double[] data) {
        validateInput(data);
        if (data.length == 1) {  // base case
            return data[0];
        } else {  // recursive case 
            // get array of remaining data
            double[] remainingData = new double[data.length - 1];
            for (int i = 0; i < remainingData.length; i++) {
                remainingData[i] = data[i + 1];
            }
            // find max of remaining data
            double maxOfRemaining = maxRecursive(remainingData);
            // return the greater value
            if (data[0] > maxOfRemaining) {
                return data[0];
            }
            else {
                return maxOfRemaining;
            }
        }
    }

    public static void main(String[] args) {
        double[] data = {2, 0, 5, 3};
        double maxIter = maxIterative(data);
        double maxRecr = maxRecursive(data);
    }
    
}
