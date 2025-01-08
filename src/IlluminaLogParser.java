import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class IlluminaLogParser {
  private static final SimpleDateFormat iso8601Format =
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);

  // This parser is designed to handle lines that look like:
  // 0          1     2           3            4                                             5
  //                  6         7       8    9    10     11         12           13            14
  // RUN-20250903 Lane1 HPC-Node4 SN3000123456 /seqdata/... 2025-09-03T09:10:22Z 42000000 0.0030 315
  // 38.2 91.5   Q30=88.9  Index=ACTG NGS-v2.2.1 bcl2fastq2.20
  //
  // If the line doesn't have 15 fields, we'll do a fallback approach.
  public static IlluminaLogEntryExtended parseLine(String line) {
    String[] parts = line.trim().split("\\s+");
    // For a fully extended log, we expect at least 15 fields.
    // But we can handle partial lines if needed.
    if (parts.length < 7) {
      return null;
    }

    // Indices for the columns we expect
    String runId = safePart(parts, 0);
    String lane = safePart(parts, 1);
    String hpcNode = safePart(parts, 2);
    String machineSerial = safePart(parts, 3);
    String runFolderPath = safePart(parts, 4);
    Date date = parseTimestamp(safePart(parts, 5));
    long readCount = parseLongSafely(safePart(parts, 6));
    double errorRate = 0.0;
    double clusterDensity = 0.0;
    double yieldGb = 0.0;
    double passFilter = 0.0;
    double q30 = 0.0;
    String indexBarcode = null;
    String pipelineVersion = null;
    String analysisSoftware = null;

    // If parts.length >= 8, parse errorRate from parts[7]
    if (parts.length > 7) {
      errorRate = parseDoubleSafely(safePart(parts, 7));
    }
    if (parts.length > 8) {
      clusterDensity = parseDoubleSafely(safePart(parts, 8));
    }
    if (parts.length > 9) {
      yieldGb = parseDoubleSafely(safePart(parts, 9));
    }
    if (parts.length > 10) {
      passFilter = parseDoubleSafely(safePart(parts, 10));
    }
    if (parts.length > 11) {
      q30 = parseQ30IfPresent(safePart(parts, 11));
    }
    if (parts.length > 12) {
      indexBarcode = parseIndexIfPresent(safePart(parts, 12));
    }
    if (parts.length > 13) {
      pipelineVersion = safePart(parts, 13);
    }
    if (parts.length > 14) {
      analysisSoftware = safePart(parts, 14);
    }

    return new IlluminaLogEntryExtended(
        runId,
        lane,
        hpcNode,
        machineSerial,
        runFolderPath,
        date,
        readCount,
        errorRate,
        clusterDensity,
        yieldGb,
        passFilter,
        q30,
        indexBarcode,
        pipelineVersion,
        analysisSoftware);
  }

  private static String safePart(String[] arr, int index) {
    if (index < arr.length) {
      return arr[index];
    }
    return "";
  }

  private static Date parseTimestamp(String timestamp) {
    ParsePosition pos = new ParsePosition(0);
    Date dt = iso8601Format.parse(timestamp, pos);
    return dt;
  }

  private static long parseLongSafely(String val) {
    try {
      return Long.parseLong(val);
    } catch (NumberFormatException e) {
      return 0L;
    }
  }

  private static double parseDoubleSafely(String val) {
    try {
      // Remove possible "PF", "Q30=", etc.
      return Double.parseDouble(val.replace("PF", "").replace("Q30=", ""));
    } catch (NumberFormatException e) {
      return 0.0;
    }
  }

  // If the field is "Q30=88.9", parse out 88.9
  private static double parseQ30IfPresent(String field) {
    if (field.startsWith("Q30=")) {
      return parseDoubleSafely(field.substring(4));
    }
    return parseDoubleSafely(field);
  }

  // If the field is "Index=ACTG", parse out "ACTG"
  private static String parseIndexIfPresent(String field) {
    if (field.startsWith("Index=")) {
      return field.substring(6);
    }
    return field;
  }
}
