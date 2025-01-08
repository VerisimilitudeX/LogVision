# IlluminaLogVision for NovaSeq 6000

Effective management of Illumina NovaSeq 6000 workflows requires real-time insights into server logs, including activity, performance, and potential issues. This software parses and analyzes logs from BaseSpace Sequence Hub and bcl2fastq to optimize epigenomic data pipelines. The code below offers essential methods to parse run information and gather relevant metrics such as error rates, cluster density, total reads, and yield in gigabases. This helps researchers quickly identify bottlenecks and ensure high-quality data for downstream analyses.

## Mechanisms
Please refer to the official Illumina documentation for more information on NovaSeq logging:
- [Illumina NovaSeq 6000 System Guide](https://support.illumina.com/documentation.html)
- [bcl2fastq Conversion Guide](https://support.illumina.com/sequencing/sequencing_software/bcl2fastq-conversion-software.html)

## Features
1. **Log file parsing**  
   Parses lines like `RUN-20250101 Lane1 2025-09-02T17:22:11Z 42000000 0.003 315 18.5`, extracting:
   - Run ID
   - Lane
   - Timestamp (ISO 8601)
   - Read count
   - Error rate
   - Cluster density
   - Yield (Gb)

2. **Run performance analytics**  
   Analyzes average error rate, cluster density, total read count, and total yield.

3. **Error and anomaly detection**  
   Identifies runs with error rates above a certain threshold, allowing for quick detection of problematic lanes.

4. **Workflow optimization**  
   Researchers can integrate the data into dashboards or automation scripts to optimize run performance, track trends, and allocate resources efficiently.

5. **Scalability**  
   Easily extended to read large log files from multiple runs.

6. **Customizable**  
   Adjust fields and thresholds to suit your lab’s unique NovaSeq workflows.

## Example Illumina Server Log

```py
RUN-20250101 Lane1 2025-09-02T17:22:11Z 42000000 0.003 315 18.5
```

Where:
- `RUN-20250101` is the run identifier  
- `Lane1` is the lane  
- `2025-09-02T17:22:11Z` is the timestamp  
- `42000000` is the read count  
- `0.003` is the error rate  
- `315` is the cluster density  
- `18.5` is the yield in gigabases  

## Usage
Compile the Java classes, place your Illumina log files in `NovaSeqLogs/`, and run:
```bash
java Main
```

This will display summary statistics such as average error rates, total yield, and more.