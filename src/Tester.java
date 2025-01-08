public class Tester {
  public void testLogAnalyzer() {
    IlluminaLogAnalyzer analyzer = new IlluminaLogAnalyzer();
    analyzer.readFile("example_illumina_log.txt");
    analyzer.printAll();
    System.out.println("\nAverage Error Rate: " + analyzer.averageErrorRate());
    System.out.println("Average Cluster Density: " + analyzer.averageClusterDensity());
    System.out.println("Total Reads: " + analyzer.totalReads());
    System.out.println("Total Yield (Gb): " + analyzer.totalYieldGb());
    System.out.println("Lanes Above Error Threshold (0.01): " + analyzer.lanesAboveErrorThreshold(0.01));
    IlluminaLogEntry maxReads = analyzer.maxReadCountEntry();
    if (maxReads != null) {
      System.out.println("\nEntry with max reads: " + maxReads);
    }
  }
}