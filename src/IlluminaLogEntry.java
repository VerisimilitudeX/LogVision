import java.util.Date;

public class FullIlluminaLogEntry {
  private final String runId;
  private final String lane;
  private final String hpcNode;
  private final String machineSerial;
  private final String runFolderPath;
  private final Date timestamp;
  private final long readCount;
  private final double errorRate;
  private final double clusterDensity;
  private final double yieldGb;
  private final double passFilterCount;
  private final double q30;
  private final String indexBarcode;
  private final String pipelineVersion;
  private final String analysisSoftware;

  public FullIlluminaLogEntry(
      String runId,
      String lane,
      String hpcNode,
      String machineSerial,
      String runFolderPath,
      Date timestamp,
      long readCount,
      double errorRate,
      double clusterDensity,
      double yieldGb,
      double passFilterCount,
      double q30,
      String indexBarcode,
      String pipelineVersion,
      String analysisSoftware) {
    this.runId = runId;
    this.lane = lane;
    this.hpcNode = hpcNode;
    this.machineSerial = machineSerial;
    this.runFolderPath = runFolderPath;
    this.timestamp = timestamp;
    this.readCount = readCount;
    this.errorRate = errorRate;
    this.clusterDensity = clusterDensity;
    this.yieldGb = yieldGb;
    this.passFilterCount = passFilterCount;
    this.q30 = q30;
    this.indexBarcode = indexBarcode;
    this.pipelineVersion = pipelineVersion;
    this.analysisSoftware = analysisSoftware;
  }

  public String getRunId() {
    return runId;
  }

  public String getLane() {
    return lane;
  }

  public String getHpcNode() {
    return hpcNode;
  }

  public String getMachineSerial() {
    return machineSerial;
  }

  public String getRunFolderPath() {
    return runFolderPath;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public long getReadCount() {
    return readCount;
  }

  public double getErrorRate() {
    return errorRate;
  }

  public double getClusterDensity() {
    return clusterDensity;
  }

  public double getYieldGb() {
    return yieldGb;
  }

  public double getPassFilterCount() {
    return passFilterCount;
  }

  public double getQ30() {
    return q30;
  }

  public String getIndexBarcode() {
    return indexBarcode;
  }

  public String getPipelineVersion() {
    return pipelineVersion;
  }

  public String getAnalysisSoftware() {
    return analysisSoftware;
  }

  @Override
  public String toString() {
    return runId
        + " "
        + lane
        + " "
        + hpcNode
        + " "
        + machineSerial
        + " "
        + runFolderPath
        + " "
        + timestamp
        + " "
        + readCount
        + " "
        + errorRate
        + " "
        + clusterDensity
        + " "
        + yieldGb
        + " "
        + passFilterCount
        + " "
        + q30
        + " "
        + indexBarcode
        + " "
        + pipelineVersion
        + " "
        + analysisSoftware;
  }
}
