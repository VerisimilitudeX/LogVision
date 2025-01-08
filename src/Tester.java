public class Tester {
  public void testLogAnalyzer(String fileName) {
    IlluminaLogAnalyzer analyzer = new IlluminaLogAnalyzer();
    analyzer.readFile(fileName);

    analyzer.printAll();
    System.out.println("\nAverage Error Rate: " + analyzer.averageErrorRate());
    System.out.println("Std Dev of Error Rate: " + analyzer.errorRateStandardDeviation());
    System.out.println("Average Cluster Density: " + analyzer.averageClusterDensity());
    System.out.println("Total Reads: " + analyzer.totalReads());
    System.out.println("Total Yield (Gb): " + analyzer.totalYieldGb());
    System.out.println("Average Q30: " + analyzer.averageQ30());
    System.out.println("HPC Usage Counts: " + analyzer.hpcUsageCounts());
    System.out.println(
        "Lanes Above Error Threshold (0.01): " + analyzer.lanesAboveErrorThreshold(0.01));

    IlluminaLogEntryExtended maxReads = analyzer.maxReadCountEntry();
    if (maxReads != null) {
      System.out.println("\nEntry with max reads: " + maxReads);
    }
  }
}
