package neural.and;

import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class DriverNOT {
    static double m = 0;
    static double b = 0;

    public static void main(String[] args) {
        int[][] data = Perceptron.notData;
        double[] weights = Perceptron.INITIAL_WEIGHTS;

        DriverNOT driver = new DriverNOT();
        Perceptron perceptron = new Perceptron();
        boolean errorFlag = true;
        int epochNumber = 0;
        double error = 0;
        double[] adjustedWeights = null;
        while (errorFlag) {
            driver.printHeading(epochNumber++);
            errorFlag = false;
            error = 0;
            for (int x = 0; x < data.length; x++) {
                double weightedSum = perceptron.calculateWeightedSum(data[x], weights);
                int result = perceptron.applyActivationFunction(weightedSum);
                error = data[x][1] - result;
                if (error != 0) {
                    errorFlag = true;
                }
                adjustedWeights = perceptron.adjustedWeights(data[x], weights, error);
                driver.printVector(data[x], weights, result, error, weightedSum, adjustedWeights);
                weights = adjustedWeights;
            }
        }

        //m = -weights[1] / weights[0];
        //b = Perceptron.THRESHOLD / weights[0];
        //System.out.println("\ny = " + String.format("%.2f", m) + "x " + String.format("%.2f", b));
        //launch(args);
    }

    public void printHeading(int epochNumber) {
        System.out.println("\n=============================================Epoch # " + epochNumber + "================================================");
        System.out.println(" w1  |  x1  | Target Result | Result | Error | Weighted Sum | Adjusted w1 ");
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    public void printVector(int[] data, double[] weights, int result, double error, double weightedSum, double[] adjustedWeights) {
        System.out.println(String.format("%.2f", weights[0]) + " |  " + data[0] + "   |       " +
                data[1] + "       |    " + result + "   |  " + error + "  |     " + String.format("%.2f", weightedSum) + "     |    " +
                String.format("%.2f", adjustedWeights[0]));
    }
}
