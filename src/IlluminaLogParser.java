import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class IlluminaLogParser {
  private static final SimpleDateFormat iso8601Format =
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);

  public static IlluminaLogEntry parseLine(String line) {
    // Example line:
    // RUN-20250101 Lane1 2025-09-02T17:22:11Z 42000000 0.003 315 18.5
    // Fields: runId, lane, timestamp, readCount, errorRate, clusterDensity, yieldGb
    String[] parts = line.trim().split("\\s+");
    if (parts.length < 7) {
      return null; // or throw an exception
    }
    String runId = parts[0];
    String lane = parts[1];
    Date date = parseTimestamp(parts[2]);
    long readCount = Long.parseLong(parts[3]);
    double errorRate = Double.parseDouble(parts[4]);
    double clusterDensity = Double.parseDouble(parts[5]);
    double yieldGb = Double.parseDouble(parts[6]);
    return new IlluminaLogEntry(runId, lane, date, readCount, errorRate, clusterDensity, yieldGb);
  }

  private static Date parseTimestamp(String timestamp) {
    ParsePosition pos = new ParsePosition(0);
    Date dt = iso8601Format.parse(timestamp, pos);
    return dt;
  }
}
