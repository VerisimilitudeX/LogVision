import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class IlluminaLogAnalyzer {
  private final ArrayList<IlluminaLogEntryExtended> records;

  public IlluminaLogAnalyzer() {
    records = new ArrayList<>();
  }

  public void readFile(String filename) {
    FileResource fr = new FileResource("assets/" + filename);
    for (String line : fr.lines()) {
      IlluminaLogEntryExtended entry = IlluminaLogParser.parseLine(line);
      if (entry != null) {
        records.add(entry);
      }
    }
  }

  public void printAll() {
    System.out.println("All Log Entries:");
    System.out.println("------------------------------");
    for (IlluminaLogEntryExtended entry : records) {
      System.out.println(entry);
    }
  }

  public double averageErrorRate() {
    if (records.isEmpty()) return 0.0;
    double sum = 0.0;
    for (IlluminaLogEntryExtended entry : records) {
      sum += entry.getErrorRate();
    }
    return sum / records.size();
  }

  public double averageClusterDensity() {
    if (records.isEmpty()) return 0.0;
    double sum = 0.0;
    for (IlluminaLogEntryExtended entry : records) {
      sum += entry.getClusterDensity();
    }
    return sum / records.size();
  }

  public long totalReads() {
    long total = 0;
    for (IlluminaLogEntryExtended entry : records) {
      total += entry.getReadCount();
    }
    return total;
  }

  public double totalYieldGb() {
    double sum = 0.0;
    for (IlluminaLogEntryExtended entry : records) {
      sum += entry.getYieldGb();
    }
    return sum;
  }

  // Revolutionary new metrics: average Q30, HPC usage counts, standard deviation for error rates:

  public double averageQ30() {
    if (records.isEmpty()) return 0.0;
    double sum = 0.0;
    for (IlluminaLogEntryExtended entry : records) {
      sum += entry.getQ30();
    }
    return sum / records.size();
  }

  public double errorRateStandardDeviation() {
    double mean = averageErrorRate();
    if (records.size() < 2) return 0.0;
    double sumSquares = 0.0;
    for (IlluminaLogEntryExtended entry : records) {
      double diff = entry.getErrorRate() - mean;
      sumSquares += diff * diff;
    }
    return Math.sqrt(sumSquares / (records.size() - 1));
  }

  public HashMap<String, Integer> hpcUsageCounts() {
    HashMap<String, Integer> usage = new HashMap<>();
    for (IlluminaLogEntryExtended entry : records) {
      String node = entry.getHpcNode();
      if (node != null && !node.isEmpty()) {
        usage.put(node, usage.getOrDefault(node, 0) + 1);
      }
    }
    return usage;
  }

  public Set<String> lanesAboveErrorThreshold(double threshold) {
    Set<String> lanes = new HashSet<>();
    for (IlluminaLogEntryExtended entry : records) {
      if (entry.getErrorRate() > threshold) {
        lanes.add(entry.getLane());
      }
    }
    return lanes;
  }

  public IlluminaLogEntryExtended maxReadCountEntry() {
    IlluminaLogEntryExtended maxEntry = null;
    for (IlluminaLogEntryExtended entry : records) {
      if (maxEntry == null || entry.getReadCount() > maxEntry.getReadCount()) {
        maxEntry = entry;
      }
    }
    return maxEntry;
  }
}