import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class IlluminaLogAnalyzer {
  private final ArrayList<IlluminaLogEntry> records;

  public IlluminaLogAnalyzer() {
    records = new ArrayList<>();
  }

  public void readFile(String filename) {
    FileResource fr = new FileResource("assets/" + filename);
    for (String line : fr.lines()) {
      IlluminaLogEntry entry = IlluminaLogParser.parseLine(line);
      if (entry != null) {
        records.add(entry);
      }
    }
  }

  public void printAll() {
    System.out.println("All Log Entries:");
    System.out.println("------------------------------");
    for (IlluminaLogEntry entry : records) {
      System.out.println(entry);
    }
  }

  public double averageErrorRate() {
    double sum = 0.0;
    int count = 0;
    for (IlluminaLogEntry entry : records) {
      sum += entry.getErrorRate();
      count++;
    }
    return count == 0 ? 0.0 : sum / count;
  }

  public double averageClusterDensity() {
    double sum = 0.0;
    int count = 0;
    for (IlluminaLogEntry entry : records) {
      sum += entry.getClusterDensity();
      count++;
    }
    return count == 0 ? 0.0 : sum / count;
  }

  public long totalReads() {
    long total = 0;
    for (IlluminaLogEntry entry : records) {
      total += entry.getReadCount();
    }
    return total;
  }

  public double totalYieldGb() {
    double sum = 0.0;
    for (IlluminaLogEntry entry : records) {
      sum += entry.getYieldGb();
    }
    return sum;
  }

  public Set<String> lanesAboveErrorThreshold(double threshold) {
    Set<String> lanes = new HashSet<>();
    for (IlluminaLogEntry entry : records) {
      if (entry.getErrorRate() > threshold) {
        lanes.add(entry.getLane());
      }
    }
    return lanes;
  }

  public IlluminaLogEntry maxReadCountEntry() {
    IlluminaLogEntry maxEntry = null;
    for (IlluminaLogEntry entry : records) {
      if (maxEntry == null || entry.getReadCount() > maxEntry.getReadCount()) {
        maxEntry = entry;
      }
    }
    return maxEntry;
  }
}