import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * The IlluminaLogAnalyzer class provides methods to read and analyze Illumina log entries,
 * including computations for averages, totals, and other metrics. All data is stored in an
 * ArrayList of IlluminaLogEntryExtended objects.
 */
public class IlluminaLogAnalyzer {
  // where we keep track of all the log entries
  private final ArrayList<IlluminaLogEntryExtended> records;

  /**
   * Constructs an IlluminaLogAnalyzer with an empty list of records. basically the default
   * constructor
   */
  public IlluminaLogAnalyzer() {
    records = new ArrayList<>();
  }

  /**
   * Reads a file from the assets folder and populates the records list with parsed
   * IlluminaLogEntryExtended objects. lines that don't parse are skipped.
   *
   * @param filename the name of the file to read from assets/
   */
  public void readFile(String filename) {
    // we use edu.duke's FileResource for convenience
    FileResource fr = new FileResource("assets/" + filename);
    for (String line : fr.lines()) {
      // parse each line into a log entry
      IlluminaLogEntryExtended entry = IlluminaLogParser.parseLine(line);
      // only add if it was successfully parsed
      if (entry != null) {
        records.add(entry);
      }
    }
  }

  /** Prints all the log entries to standard output. you can use this to see raw data quickly. */
  public void printAll() {
    System.out.println("All Log Entries:");
    System.out.println("------------------------------");
    for (IlluminaLogEntryExtended entry : records) {
      // printing each entry
      System.out.println(entry);
    }
  }

  /**
   * Calculates the average error rate across all records.
   *
   * @return the average error rate, or 0.0 if no records exist
   */
  public double averageErrorRate() {
    if (records.isEmpty()) {
      return 0.0;
    }
    double sum = 0.0;
    // sum up all error rates
    for (IlluminaLogEntryExtended entry : records) {
      sum += entry.getErrorRate();
    }
    // divide by count for the average
    return sum / records.size();
  }

  /**
   * Computes the average cluster density for all records.
   *
   * @return the average cluster density, or 0.0 if no records exist
   */
  public double averageClusterDensity() {
    if (records.isEmpty()) {
      return 0.0;
    }
    double sum = 0.0;
    for (IlluminaLogEntryExtended entry : records) {
      sum += entry.getClusterDensity();
    }
    return sum / records.size();
  }

  /**
   * Sums up the total number of reads across all entries.
   *
   * @return the total reads
   */
  public long totalReads() {
    long total = 0;
    for (IlluminaLogEntryExtended entry : records) {
      total += entry.getReadCount();
    }
    return total;
  }

  /**
   * Calculates the total yield in gigabases by summing the yield of each entry.
   *
   * @return the total yield in gigabases
   */
  public double totalYieldGb() {
    double sum = 0.0;
    for (IlluminaLogEntryExtended entry : records) {
      sum += entry.getYieldGb();
    }
    return sum;
  }

  /**
   * Computes the average Q30 value across all records.
   *
   * @return the average Q30, or 0.0 if no records exist
   */
  public double averageQ30() {
    if (records.isEmpty()) {
      return 0.0;
    }
    double sum = 0.0;
    for (IlluminaLogEntryExtended entry : records) {
      sum += entry.getQ30();
    }
    return sum / records.size();
  }

  /**
   * Calculates the standard deviation of error rates for all log entries.
   *
   * @return the standard deviation of error rates, or 0.0 if fewer than 2 records exist
   */
  public double errorRateStandardDeviation() {
    double mean = averageErrorRate();
    // watch out if we have only one or none
    if (records.size() < 2) {
      return 0.0;
    }
    double sumSquares = 0.0;
    for (IlluminaLogEntryExtended entry : records) {
      double diff = entry.getErrorRate() - mean;
      sumSquares += diff * diff;
    }
    // sample standard deviation using n-1
    return Math.sqrt(sumSquares / (records.size() - 1));
  }

  /**
   * Counts how many times each HPC node was used, based on the HPC node stored in each log entry.
   *
   * @return a HashMap mapping HPC node to the count of entries that used it
   */
  public HashMap<String, Integer> hpcUsageCounts() {
    HashMap<String, Integer> usage = new HashMap<>();
    for (IlluminaLogEntryExtended entry : records) {
      String node = entry.getHpcNode();
      // only count if node is not null/empty
      if (node != null && !node.isEmpty()) {
        usage.put(node, usage.getOrDefault(node, 0) + 1);
      }
    }
    return usage;
  }

  /**
   * Finds all lanes whose error rate is above a given threshold.
   *
   * @param threshold the error rate threshold
   * @return a set of lane IDs above the threshold
   */
  public Set<String> lanesAboveErrorThreshold(double threshold) {
    Set<String> lanes = new HashSet<>();
    for (IlluminaLogEntryExtended entry : records) {
      if (entry.getErrorRate() > threshold) {
        // add that lane to the set
        lanes.add(entry.getLane());
      }
    }
    return lanes;
  }

  /**
   * Identifies the log entry with the maximum read count across all records.
   *
   * @return the IlluminaLogEntryExtended with the maximum read count, or null if there are no
   *     records
   */
  public IlluminaLogEntryExtended maxReadCountEntry() {
    IlluminaLogEntryExtended maxEntry = null;
    for (IlluminaLogEntryExtended entry : records) {
      // keep track of the largest read count so far
      if (maxEntry == null || entry.getReadCount() > maxEntry.getReadCount()) {
        maxEntry = entry;
      }
    }
    return maxEntry;
  }
}
