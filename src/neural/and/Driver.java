package neural.and;

import javafx.application.Application;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class Driver {
    static double m = 0;
    static double b = 0;

    public static void main(String[] args) {
        int[][][] data = Perceptron.andData;
        double[] weights = Perceptron.INITIAL_WEIGHTS;

        Driver driver = new neural.and.Driver();
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
                double weightedSum = perceptron.calculateWeightedSum(data[x][0], weights);
                int result = perceptron.applyActivationFunction(weightedSum);
                error = data[x][1][0] - result;
                if (error != 0) {
                    errorFlag = true;
                }
                adjustedWeights = perceptron.adjustedWeights(data[x][0], weights, error);
                driver.printVector(data[x], weights, result, error, weightedSum, adjustedWeights);
                weights = adjustedWeights;
            }
        }

        //m = -weights[1] / weights[0];
        //b = Perceptron.THRESHOLD / weights[0];
        //System.out.println("\ny = " + String.format("%.2f", m) + "x " + String.format("%.2f", b));
        //launch(args);
    }

    public void start(Stage stage) throws Exception {
        stage.setTitle(" Neural Network AND ");
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("zero");
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series1.setName("one");
        IntStream.range(0, Perceptron.andData.length).forEach(i -> {
            int x = Perceptron.andData[i][0][0], y = Perceptron.andData[i][0][1];
            if (Perceptron.andData[i][1][0] == 0) series1.getData().add(new XYChart.Data<Number, Number>(x, y));
            else series2.getData().add(new XYChart.Data<Number, Number>(x, y));
        });

    }

    public void printHeading(int epochNumber) {
        System.out.println("\n=============================================Epoch # " + epochNumber + "================================================");
        System.out.println(" w1  |  w2  |  x1  |  x2  | Target Result | Result | Error | Weighted Sum | Adjusted w1 |  Adjusted w2");
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    public void printVector(int[][] data, double[] weights, int result, double error, double weightedSum, double[] adjustedWeights) {
        System.out.println(String.format("%.2f", weights[0]) + " | " + String.format("%.2f", weights[1]) + " |  " + data[0][0] + "   |  " +
                data[0][1] + "   |       " + data[1][0] + "       |    " + result + "   |  " + error + "  |     " + String.format("%.2f", weightedSum) + "     |    " +
                String.format("%.2f", adjustedWeights[0]) + "     |     " + String.format("%.2f", adjustedWeights[1]));
    }
}
