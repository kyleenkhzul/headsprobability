import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class BiasedCoinSimulationCSV {
    public static void main(String[] args) {
        int[] nValues = {100, 500, 1000, 5000, 10000};
        double[] pValues = {0.3, 0.5, 0.7};
        int m = 10; // number of trials

        Random rand = new Random();
        List<String> rows = new ArrayList<>();

        // Header row for CSV
        rows.add("n,p,trial,heads,expected_heads");

        for (int n : nValues) {
            for (double p : pValues) {
                for (int trial = 1; trial <= m; trial++) {
                    int heads = runTrial(n, p, rand);
                    double expectedHeads = n * p;

                    // Build CSV row
                    String row = String.format("%d,%.1f,%d,%d,%.2f", n, p, trial, heads, expectedHeads);
                    rows.add(row);
                }
            }
        }

        // Write all rows to CSV file
        try (FileWriter writer = new FileWriter("simulation_results.csv")) {
            for (String row : rows) {
                writer.write(row + "\n");
            }
            System.out.println("Simulation complete. Results saved to simulation_results.csv.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the CSV file.");
            e.printStackTrace();
        }
    }

    public static int runTrial(int n, double p, Random rand) {
        int heads = 0;
        for (int i = 0; i < n; i++) {
            if (biasedCoinFlip(p, rand)) {
                heads++;
            }
        }
        return heads;
    }

    public static boolean biasedCoinFlip(double p, Random rand) {
        return rand.nextDouble() < p;
    }
}
