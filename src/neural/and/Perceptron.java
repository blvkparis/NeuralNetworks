package neural.and;

public class Perceptron {
    public static final int[][][] andData = {
            {{0, 0}, {0}},
            {{0, 1}, {0}},
            {{1, 0}, {0}},
            {{1, 1}, {1}}
    };

    // TODO - XOR_Training
    public static final int[][][] xorData = {
            {{0, 0}, {0}},
            {{0, 1}, {1}},
            {{1, 0}, {1}},
            {{1, 1}, {0}}
    };

    public static final double LEARNING_RATE = 0.05;
    public static final double[] INITIAL_WEIGHTS = {Math.random(), Math.random()};

    public double calculateWeightedSum(int[] data, double[] weights) {
        double weightedSum = 0;
        for (int x = 0; x < data.length; x++) {
            weightedSum += data[x] * weights[x];
        }
        return weightedSum;
    }

    public int applyActivationFunction(double weightedSum) {
        int result = 0;
        if (weightedSum > 1) result = 1;
        return result;
    }

    public double[] adjustedWeights(int[] data, double[] weights, double error) {
        double[] adjustedWeights = new double[weights.length];
        for (int x = 0; x < weights.length; x++) {
            adjustedWeights[x] = LEARNING_RATE * data[x] * error + weights[x];
        }
        return adjustedWeights;
    }
}
